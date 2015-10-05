package API;

import java.util.List;

import Model.MerchantDetails;
import Model.Product;
import Model.ProductDealDetails;
import Model.StoreTypes;
import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;


/**
 * Created by Kdintakurthi on 6/11/2015.
 */
public interface GetAPI {

   @GET("/getMerchandetailByStoreId")
    public void  getMerchantDetails(@Query("storeid")long id, Callback<MerchantDetails> response);

    //https://intmobile.orderrabbit.in/marchantmappedproducts?storeid=1000&firstNode=0&&lastNode=10
    @GET("/marchantmappedproducts")
    public void getMappedProducts(@Query("storeid") long id,@Query("firstNode") int firstNode,@Query("lastNode") int lastNode,Callback<List<Product>> response);

   //https://intmobile.orderrabbit.in/storetypes
   @GET("/storetypes")
   public void getStoreTypes(Callback<List<StoreTypes>> response);


    //: https://intmobile.orderrabbit.in/addDealToProduct
    @POST("/addDealToProduct")
    public void updateDeal(@Body ProductDealDetails productDealDetails,Callback<Response> responseCallback);




}
