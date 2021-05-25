package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import model.Country;

@Repository
public class CountryDAO {
	private static final Map<String, Country>COUNTRIES_MAP=new HashMap<>();
  static {
	  initDATA();
  }
private static void initDATA() {
	// TODO Auto-generated method stub
	Country vn=new Country("VN","Vietnam");
	Country en=new Country("EN","ENGLAND");
	Country fr=new Country("FN","France");
	Country us=new Country("US","Usa");
	Country ru=new Country("RU","Russia");

}
public Country findCountryByCode(String countryCode) {
	return COUNTRIES_MAP.get(countryCode);
	
}
public List<Country>getCountries(){
	List<Country>list=new ArrayList<>();
	list.addAll(COUNTRIES_MAP.values());
	return list;
	
}
}
