package ra.run;

import ra.config.StoreMessages;
import ra.config.StoreValidation;

import java.util.Scanner;

public class StoreManagement {

    public static void cafeStoreManagement(Scanner scanner) {
        boolean exitCafeStoreManagement = true;
        do {
            System.out.println();
            System.out.println("*-------------QUẢN LÝ CỬA HÀNG CAFE-------------*");
            System.out.println("|-----------------------------------------------|");
            System.out.println("|              1.Quản trị danh mục              |");
            System.out.println("|              2.Quản lý sản phẩm               |");
            System.out.println("|              3.Quản lý bàn                    |");
            System.out.println("|              4.Quản lý người dùng             |");
            System.out.println("|              5.Đăng xuất                      |");
            System.out.println("*-----------------------------------------------*");

            String choice;
            do {
                choice = scanner.nextLine();
                if (choice.trim().length() != 0) {
                    if (StoreValidation.checkInteger(choice)) {
                        if (choice.length() >= 1 && choice.length() <= 5) {
                            break;
                        } else {
                            System.err.println(StoreMessages.NOTIFY_STORE_MENU);
                        }
                    } else {
                        System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                    }
                } else {
                    System.err.println(StoreMessages.NOTIFY_NO_INPUT);
                }
            } while (true);
            switch (Integer.parseInt(choice)) {
                case 1:
                    CatalogMenu.catalogManagement(scanner);
                    break;
                case 2:
                    ProductMenu.productManagement(scanner);
                    break;
                case 3:
                    TableMenu.tableManagement(scanner);
                    break;
                case 4:
                    UserMenu.userManagement(scanner);
                    break;
                case 5:
                    exitCafeStoreManagement = false;
                    break;
            }
        } while (exitCafeStoreManagement);
    }
}
