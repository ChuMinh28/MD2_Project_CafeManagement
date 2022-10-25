package ra.run;

import ra.bussiness.implement.UserImp;
import ra.config.StoreMessages;
import ra.config.StoreValidation;

import java.util.Scanner;

public class UserMenu {
    private final static UserImp userImp = new UserImp();

    public static void userManagement(Scanner scanner) {
        boolean exitUserMenu = true;
        do {
            System.out.println();
            System.out.println("*----------------QUẢN TRỊ USER-----------------*");
            System.out.println("|----------------------------------------------|");
            System.out.println("|           1.Danh sách tài khoản              |");
            System.out.println("|           2.Thêm tài khoản                   |");
            System.out.println("|           3.Cập nhật trạng thái tài khoản    |");
            System.out.println("|           4.Tìm kiếm tài khoản               |");
            System.out.println("|           5.Thoát                            |");
            System.out.println("|----------------------------------------------|");
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
                    System.out.println("Thông Tin Người Dùng");
                    userImp.displayData();
                    break;
                case 2:
                    userImp.create(userImp.inputData(scanner));
                    break;
                case 3:
                    System.out.println("Nhập mã người dùng cần cập nhật trạng thái: ");
                    String nameSearch;
                    do {
                        nameSearch = scanner.nextLine();
                        if (nameSearch.trim() != "" && nameSearch.length() != 0) {
                            if (StoreValidation.checkInteger(nameSearch)) {
                                break;
                            } else {
                                System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                            }
                        } else {
                            System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
                        }
                    } while (true);
                    userImp.updateStatus(Integer.parseInt(nameSearch));
                    break;
                case 4:
                    System.out.println("Nhập tên đăng nhập hoặc tên chủ tài khoản: ");
                    String search = scanner.nextLine();
                    userImp.searchUser(search);
                    break;
                case 5:
                    exitUserMenu = false;
                    break;
            }
        } while (exitUserMenu);
    }
}
