package validator;



import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties.Validation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import dao.AppUserDAO;
import formbean.AppUserForm;
import model.AppUser;
@Component
public class AppUserValidator implements Validator {
	
	//common validator library
	private EmailValidator emailValidator=EmailValidator.getInstance();
	@Autowired
	private AppUserDAO appUserDAO;
	
	
//the classes are supported by this validator.
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz==AppUserForm.class;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		AppUserForm appUserForm=(AppUserForm)target;
		
		//check the fields of AppUserform.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName","NotEmpty.AppUserForm.userName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName","NotEmpty.AppUserForm.firstName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty.AppUserForm.lastName");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.AppUserForm.email");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "NotEmpty.AppUserForm.password");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors,"conformPassword","NotEmpty.AppUserForm.conformPassword");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender","NotEmpty.AppUserForm.gender");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "countryCode", "NotEmpty.AppUserForm.countryCode");
		
		if (!this.emailValidator.isValid(AppUserForm.getEmail(), null)) {
			//invalid email
			errors.rejectValue("email","Pattern.appUserForm.email");
			
		}
		else if (appUserForm.getUserId()==null) {
			AppUser.dbUser=appUserDAO.findAppUserByEmail(appUserForm.getEmail());
			Object dbUser;
			if (dbUser !=null) {
				//email has been used by another account
				errors.rejectValue("email", "Duplicate.appUserForm.email");
				
				
			}
			
		}
		
		if (!errors.hasFieldErrors("userName")) {
			AppUser dbUser=appUserDAO.findAppUserByUserName(appUserForm.getUserName());
			if (dbUser !=null) {
				//user name is not available
				//Errors.rejectValue("userName","Duplicate.appUserForm.userName");
				errors.rejectValue("userName","Duplicate.appUserForm.userName"); 
			}
			
		}
		if (!errors.hasErrors()) {
			if (!appUserForm.getConfirmPassword().equals(appUserForm.getPassword())) {
				errors.rejectValue("confirmPassword","Match.appUserForm.conformPassword");
			}
			
		}
		
	}

	
}
