package ra.bussiness.entity;

import java.io.Serializable;

public class Catalog implements Serializable {
    private int catalogID;
    private String catalogName;
    private int priority;
    boolean catalogStatus;

    public Catalog() {
    }

    public Catalog(int catalogID, String catalogName, int priority, boolean catalogStatus) {
        this.catalogID = catalogID;
        this.catalogName = catalogName;
        this.priority = priority;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogID() {
        return catalogID;
    }

    public void setCatalogID(int catalogID) {
        this.catalogID = catalogID;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }
}
