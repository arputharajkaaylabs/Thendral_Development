package Model;

import com.google.gson.annotations.SerializedName;

public class MerchantDetails {
	
	@SerializedName("storeId")
	long storeId;
	@SerializedName("proprietorName")
	String proprietorName;
	@SerializedName("city")
	String city;
	
	public long getStoreId() {
		return storeId;
	}
	public void setStoreId(long storeId) {
		this.storeId = storeId;
	}
	public String getProprietorName() {
		return proprietorName;
	}
	public void setProprietorName(String proprietorName) {
		this.proprietorName = proprietorName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	

}
