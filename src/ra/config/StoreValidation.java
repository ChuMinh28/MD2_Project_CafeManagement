package ra.config;

import javafx.scene.control.Tab;
import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.Table;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StoreValidation {
    public static boolean checkLengthString(String str, int min, int max) {
        if (str.length() >= min && str.length() <= max) {
            return true;
        }
        return false;
    }


    public static boolean checkProductPrice(String price) {
        if (Float.parseFloat(price) > 0) {
            return true;
        }
        return false;
    }


    public static boolean checkInteger(String str) {
        try {
            int number = Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkFloat(String str) {
        try {
            float number = Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static Catalog checkChoiceCatalog(List<Catalog> listCatalog, int catalogId) {
        for (Catalog cat : listCatalog) {
            if (cat.getCatalogID() == catalogId) {
                return cat;
            }
        }
        return null;
    }

    public static boolean checkChoiceCatalogMenu(String choice, Integer int1, Integer int2) {
        if (int1 >= 1 && int2 <= 6) {
            return true;
        }
        return false;
    }


    public static boolean checkTableId(String idTable) {
        boolean checkFormat = idTable.matches(Constant.FORMAT_TABLE_ID);
        return checkFormat;
    }

    public static boolean checkAccount(String account) {
        Pattern pattern = Pattern.compile(Constant.FORMAT_ACCOUNT);
        Matcher matcher = pattern.matcher(account);
        return matcher.matches();
    }

    public static boolean checkPassword(String password) {
        Pattern pattern = Pattern.compile(Constant.FORMAT_PASSWORD);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(Constant.FORMAT_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkPhone(String phone) {
        Pattern pattern = Pattern.compile(Constant.FORMAT_PHONE);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean checkSeatNumber(String seat) {
        if (Integer.parseInt(seat) != 0) {
            return true;
        }
        return false;
    }

}
