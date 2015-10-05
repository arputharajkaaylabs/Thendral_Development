package Model;

/**
 * Created by KDintakurthi on 6/11/2015.
 */
public class ProductDealDetails {


     /*   "strID":10,
            "saleStartDate":"10-04-2015",
            "saleEndDate":"10-05-2015",
            "onSale":"Y",
            "salePrice":180.5*/

    long strID;
    String saleStartDate;
    String saleEndDate;
    String onSale;
    double salePrice;

    public ProductDealDetails(long strID,String saleStartDate,String saleEndDate,String onSale,double salePrice)
    {
        this.strID=strID;
        this.saleStartDate=saleStartDate;
        this.saleEndDate=saleEndDate;
        this.onSale=onSale;
        this.salePrice=salePrice;
    }




}
