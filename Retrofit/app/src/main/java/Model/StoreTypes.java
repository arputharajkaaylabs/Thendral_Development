package Model;

import com.google.gson.annotations.SerializedName;

public class StoreTypes {

	@SerializedName("store_type_id")
	Long store_type_id;
	@SerializedName("store_type")
	String store_type;
	
	public Long getStore_type_id() {
		return store_type_id;
	}


	public void setStore_type_id(Long store_type_id) {
		this.store_type_id = store_type_id;
	}


	public String getStore_type() {
		return store_type;
	}


	public void setStore_type(String store_type) {
		this.store_type = store_type;
	}
	
	
}
