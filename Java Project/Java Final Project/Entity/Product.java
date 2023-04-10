package Entity;

public abstract class Product {
    protected String productName;
    protected short quantity;
    protected int productPrice;


    public abstract void getProductDetails();

    public abstract void setProductDetails(int bookNumber);

    public abstract void insert();
   

    
}
