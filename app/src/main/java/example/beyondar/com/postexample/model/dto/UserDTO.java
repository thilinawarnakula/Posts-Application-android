package example.beyondar.com.postexample.model.dto;

import com.google.gson.annotations.SerializedName;

public class UserDTO{

	@SerializedName("website")
	private String website;

	@SerializedName("address")
	private AddressDTO addressDTO;

	@SerializedName("phone")
	private String phone;

	@SerializedName("name")
	private String name;

	@SerializedName("company")
	private CompanyDTO companyDTO;

	@SerializedName("id")
	private int id;

	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setAddressDTO(AddressDTO addressDTO){
		this.addressDTO = addressDTO;
	}

	public AddressDTO getAddressDTO(){
		return addressDTO;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCompanyDTO(CompanyDTO companyDTO){
		this.companyDTO = companyDTO;
	}

	public CompanyDTO getCompanyDTO(){
		return companyDTO;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"UserDTO{" + 
			"website = '" + website + '\'' + 
			",addressDTO = '" + addressDTO + '\'' +
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",companyDTO = '" + companyDTO + '\'' +
			",id = '" + id + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}