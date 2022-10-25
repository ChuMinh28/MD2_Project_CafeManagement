package ra.bussiness.entity;

import java.io.Serializable;

public class Table implements Serializable {
    private String tableID;
    private String tableName;
    private int seatNumber;
    private int tableStatus;

    public Table() {
    }

    public Table(String tableID, String tableName, int seatNumber, int tableStatus) {
        this.tableID = tableID;
        this.tableName = tableName;
        this.seatNumber = seatNumber;
        this.tableStatus = tableStatus;
    }

    public String getTableID() {
        return tableID;
    }

    public void setTableID(String tableID) {
        this.tableID = tableID;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int tableStatus) {
        this.tableStatus = tableStatus;
    }
}
