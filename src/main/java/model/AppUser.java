package model;

public class AppUser {
	public static AppUser dbUser;
	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private boolean enabled;
	private String gender;
	private String email;
	private String encryptedPassword;
	private String countryCode;
	
	
	
	
	
	public AppUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public String getGender() {
		return gender;
	}
	public AppUser(Long userId, String userName, String firstName, String lastName, boolean enabled, String gender,
			String email, String encryptedPassword, String countryCode) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.enabled = enabled;
		this.gender = gender;
		this.email = email;
		this.encryptedPassword = encryptedPassword;
		this.countryCode = countryCode;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
