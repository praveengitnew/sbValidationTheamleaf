package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dao.AppUserDAO;
import dao.CountryDAO;
import formbean.AppUserForm;
import model.AppUser;
import model.Country;
import validator.AppUserValidator;

@Controller
public class MainController {
	
	@Autowired
	private AppUserDAO appUserDAO;
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private AppUserValidator appUserValidator;
	
	//set a form validator
	@InitBinder
	protected void initBinder(WebDataBinder dataBinder) {
		//form target
		Object target=dataBinder.getTarget();
		if (target==null) {
			return;
			
		}
		System.out.println("Target="+target);
		if (target.getClass()==AppUserForm.class) {
			dataBinder.setValidator(appUserValidator);
			
		}
		//..
		
	}
	@RequestMapping("/")
	public String viewHome(Model model) {
		return "welcomePage";
		
	}
	
	@RequestMapping("/members")
	public String viewMembers(Model model) {
	List<AppUser>list=appUserDAO.getAppUsers();
	model.addAttribute("members",list);
		return "membersPage";
		
	}
	@RequestMapping("/registersuccessful")
	public String viewRegisterSuccessful(Model model) {
		return "registerSuccessfulPage";
	}
	
	//show register page
	
	@RequestMapping(value = "/register",method = RequestMethod.GET)
	public String viewRegister(Model model) {
		
		AppUserForm form=new AppUserForm();
		List<Country>countries= countryDAO.getCountries();
		
		model.addAttribute("appUserForm",form);
		model.addAttribute("countries",countries);
		
		
		return "registerPage";
		
	}
	
	//this method is called to save the registration information.
	//@Validate: To ensure that this form
	//has been validated Before this method is invoked.
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String saveRegister(Model model,
			@ModelAttribute("appUserForm")@Validated AppUserForm appUserForm,
	        BindingResult result,//
	        final RedirectAttributes redirectAttributes){
	        	
	        	//Validate results
	        	if (result.hasErrors()) {
	        	List<Country>countries=	countryDAO.getCountries();
	        	
	        	model.addAttribute("countries",countries);
	        	return "registerPage";
					
				}
	        	
	        	AppUser newUser=null;
	        	try {
					newUser=appUserDAO.createAppUser(appUserForm);
				} 
	        	//other error!!
	        	catch (Exception e) {
					// TODO: handle exception
	        	List<Country>countries=	countryDAO.getCountries();
	        	model.addAttribute("errorMessage","Error:"+e.getMessage());
	        	return "registerPage";
	        	}
	        	redirectAttributes.addFlashAttribute("flashUser",newUser);
	        	return "redirect:/registerSuccessful";
	        }
   
	

}