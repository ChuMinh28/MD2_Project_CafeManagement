package ra.run;


import ra.bussiness.implement.ProductImp;
import ra.config.StoreMessages;
import ra.config.StoreValidation;

import java.util.Scanner;

public class ProductMenu extends ProductImp {
    private final static ProductImp productImp = new ProductImp();

    public static void productManagement(Scanner scanner) {
        boolean exitProductMenu = true;
        do {
            System.out.println();
            System.out.println("*---------------QUẢN LÝ SẢN PHẨM----------------*");
            System.out.println("|-----------------------------------------------|");
            System.out.println("|          1.Danh sách sản phẩm theo danh mục   |");
            System.out.println("|          2.Tạo mới sản phẩm                   |");
            System.out.println("|          3.Cập nhật sản phẩm                  |");
            System.out.println("|          4.Tìm kiếm sản phẩm                  |");
            System.out.println("|          5.Xóa sản phẩm                       |");
            System.out.println("|          6.Thoát                              |");
            System.out.println("*-----------------------------------------------*");

            String choice;
            do {
                choice = scanner.nextLine();
                if (choice.trim().length() != 0) {
                    if (StoreValidation.checkInteger(choice)) {
                        if (StoreValidation.checkChoiceCatalogMenu(choice, 1, 6)) {
                            break;
                        } else {
                            System.err.println(StoreMessages.NOTIFY_PRODUCT_MENU);
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
                    productImp.displayData();
                    break;
                case 2:
                    productImp.create(productImp.inputData(scanner));
                    break;
                case 3:
                    productImp.updateProduct(scanner);
                    break;
                case 4:
                    productImp.searchProductByName(scanner);
                    break;
                case 5:
                    System.out.println("Nhập mã sản phẩm cần xóa: ");
                    String id = scanner.nextLine();
                    productImp.updateStatus(id);
                    break;
                case 6:
                    exitProductMenu = false;
                    break;
            }
        } while (exitProductMenu);
    }
}
