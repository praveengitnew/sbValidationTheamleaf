package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dao.AppUserDAO;
import dao.CountryDAO;
import validator.AppUserValidator;

@Controller
public class MainController {
	
	@Autowired
	private AppUserDAO appUserDAO;
	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private AppUserValidator appUserValidator;

}
