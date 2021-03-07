package profiles;

import java.util.Base64;

public class Profile {
	private int id;
	private String first_name;
	private String last_name;
	private String college_name;
	private String highschool_name;
	private UseDate dob;
	private String email_address;
	private long phone_number;
	private byte[] profilepic; 
	private String encodedpfp; 
	private UseDate last_login;
	private UseDate last_profile_modified;
	
	
	public Profile(int id, String first_name, String last_name, String college_name, String highschool_name, UseDate dob,
			String email_address, long phone_number, byte[] profilepic, UseDate last_login,
			UseDate last_profile_modified) {
		this.id = id; 
		this.first_name = first_name;
		this.last_name = last_name;
		this.college_name = college_name;
		this.highschool_name = highschool_name;
		this.dob = dob;
		this.email_address = email_address;
		this.phone_number = phone_number;
		this.profilepic = profilepic;
		this.encodedpfp = Base64.getEncoder().encodeToString(profilepic);
		this.last_login = last_login;
		this.last_profile_modified = last_profile_modified;
	}
	public int getID() {
		return id; 
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getCollege_name() {
		return college_name;
	}
	public void setCollege_name(String college_name) {
		this.college_name = college_name;
	}
	public String getHighschool_name() {
		return highschool_name;
	}
	public void setHighschool_name(String highschool_name) {
		this.highschool_name = highschool_name;
	}
	public UseDate getDob() {
		return dob;
	}
	public void setDob(UseDate dob) {
		this.dob = dob;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public long getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}
	public byte[] getProfilePic() {
		return profilepic;
	}
	public void setProfilePic(byte[] data) {
		this.profilepic = data;
		this.setEncodedpfp(Base64.getEncoder().encodeToString(data)); 
	}
	public UseDate getLast_login() {
		return last_login;
	}
	public void setLast_login(UseDate last_login) {
		this.last_login = last_login;
	}
	public UseDate getLast_profile_modified() {
		return last_profile_modified;
	}
	public void setLast_profile_modified(UseDate last_profile_modified) {
		this.last_profile_modified = last_profile_modified;
	}
	public String getEncodedpfp() {
		return encodedpfp;
	}
	public void setEncodedpfp(String encodedpfp) {
		this.encodedpfp = encodedpfp;
	}
	
	

	


}
