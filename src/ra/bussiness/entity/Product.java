package ra.bussiness.entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private String productID;
    private String productName;
    private float price;
    private ArrayList<Catalog> listCatalog = new ArrayList<>();
    private Catalog catalog;
    private boolean productStatus;

    public Product() {
    }

    public Product(String productID, String productName, float price, ArrayList<Catalog> listCatalog, Catalog catalog, boolean productStatus) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.listCatalog = listCatalog;
        this.catalog = catalog;
        this.productStatus = productStatus;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<Catalog> getListCatalog() {
        return listCatalog;
    }

    public void setListCatalog(ArrayList<Catalog> listCatalog) {
        this.listCatalog = listCatalog;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }
}
