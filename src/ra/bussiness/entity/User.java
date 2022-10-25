package ra.bussiness.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String accountName;
    private String password;
    private String fullName;
    private String date;
    private String email;
    private String phone;
    private boolean userStatus;

    public User() {
    }

    public User(int userID, String accountName, String password, String fullName, String date, String email, String phone, boolean userStatus) {
        this.userID = userID;
        this.accountName = accountName;
        this.password = password;
        this.fullName = fullName;
        this.date = date;
        this.email = email;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }
}
