package ra.config;

public class Constant {
    public static final String URL_CATALOG_FILE = "D:\\Project_MD2\\Project_MD2\\src\\ra\\data\\Catalog.txt";
    public static final String URL_PRODUCT_FILE = "D:\\Project_MD2\\Project_MD2\\src\\ra\\data\\Product.txt";
    public static final String URL_TABLE_FILE = "D:\\Project_MD2\\Project_MD2\\src\\ra\\data\\Table.txt";
    public static final String URL_USER_FILE = "D:\\Project_MD2\\Project_MD2\\src\\ra\\data\\User.txt";
    public static final String FORMAT_TABLE_ID = "^[A-Z]{2}[A-Za-z0-9]{3}$";
    public static final String FORMAT_EMAIL = "^[A-Za-z0-9]+[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";
    public static final String FORMAT_ACCOUNT = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,39}$";
    public static final String FORMAT_PASSWORD = "^[a-zA-Z0-9]{6,39}$";
    public static final String FORMAT_PHONE = "^\\([0-9]{2}\\)\\-\\(0[0-9]{9}\\)$";
}
