package example.beyondar.com.postexample.model.dto;

import com.google.gson.annotations.SerializedName;

public class AddressDTO {

	@SerializedName("zipcode")
	private String zipcode;

	@SerializedName("geo")
	private GeoDTO geoDTO;

	@SerializedName("suite")
	private String suite;

	@SerializedName("city")
	private String city;

	@SerializedName("street")
	private String street;

	public void setZipcode(String zipcode){
		this.zipcode = zipcode;
	}

	public String getZipcode(){
		return zipcode;
	}

	public void setGeoDTO(GeoDTO geoDTO){
		this.geoDTO = geoDTO;
	}

	public GeoDTO getGeoDTO(){
		return geoDTO;
	}

	public void setSuite(String suite){
		this.suite = suite;
	}

	public String getSuite(){
		return suite;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	@Override
 	public String toString(){
		return 
			"AddressDTO{" +
			"zipcode = '" + zipcode + '\'' + 
			",geoDTO = '" + geoDTO + '\'' +
			",suite = '" + suite + '\'' + 
			",city = '" + city + '\'' + 
			",street = '" + street + '\'' + 
			"}";
		}
}