package Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by user on 6/11/2015.
 */
public class Product {
    @SerializedName("productName")
    String productName;
    @SerializedName("productDescription")
    String productDescription;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
