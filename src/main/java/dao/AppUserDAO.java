package dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import formbean.AppUserForm;
import model.AppUser;
import model.Gender;

@Repository
public class AppUserDAO {
	
//https://o7planning.org/11655/create-a-user-registration-application-with-spring-boot-spring-form-validation
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private static final Map<Long, AppUser>USERS_MAP=new HashMap<>();
	
	static {
		initDATA();
	}

	private static void initDATA() {
		// TODO Auto-generated method stub
		String encryptedPassword="";
		
		AppUser tom=new AppUser(1l,"tom","TOM","TOM",// 
				true,Gender.MALE,"tom@waltdisney.com",encryptedPassword,"US");
		AppUser jerry=new AppUser(2l,"jerry","Jerry","Jerry",//
				true,Gender.MALE,"jerry@waltdisney.com",encryptedPassword,"US");
		
		USERS_MAP.put(tom.getUserId(),tom);
		USERS_MAP.put(jerry.getUserId(),jerry);
	}

	public Long getMaxUserId() {
		Long max=(long) 0;
		for(Long id:USERS_MAP.keySet()) {
			if(id>max) {
				max=id;
			}
		}
		
		return max;
		
	}
	
	public AppUser findAppUserByUserName(String userName) {
		Collection<AppUser>appUsers=USERS_MAP.values();
		for(AppUser u:appUsers) {
			if (u.getUserName().equals(userName)) {
				return u;	
			}
		}
		return null;
}
    public AppUser findAppUserByEmail(String email) {
    	Collection<AppUser>appUsers=USERS_MAP.values();
    	for(AppUser u:appUsers) {
    		if (u.getEmail().equals(email)) {
    			return u;
			}
    	}
		return null;
    }
	
    public List<AppUser>getAppUsers(){
		List<AppUser> list=new ArrayList<>();
		list.addAll(USERS_MAP.values());
		return list;
    }
    
    public AppUser createAppUser(AppUserForm form) {
    	Long userId=this.getMaxUserId()+1;
    	String encryptedPassword=this.passwordEncoder.encode(form.getPassword());
    	AppUser user=new AppUser(userId,form.getUserName(),//
    		form.getUserName(),form.getLastName(),false,//
    		form.getGender(),form.getEmail(),form.getCountryCode(),//
    		encryptedPassword);
    	USERS_MAP.put(userId, user);
		return user;
    	
    }
}