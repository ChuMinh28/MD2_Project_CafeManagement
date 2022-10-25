package ra.run;

import ra.bussiness.entity.User;
import ra.bussiness.implement.UserImp;
import ra.config.StoreMessages;
import ra.config.StoreValidation;

import java.util.List;
import java.util.Scanner;

public class StoreRun {
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        do {
            System.out.println("*-----------------WEOCOME TO MY WORLD--------------------*");
            System.out.println("|                     1.Đăng Ký                          |");
            System.out.println("|                     2.Đăng Nhập                        |");
            System.out.println("|                     3.Thoát                            |");
            System.out.println("*--------------------------------------------------------*");
            String choice;
            do {
                choice = scanner.nextLine();
                if (choice.trim().length() != 0) {
                    if (StoreValidation.checkInteger(choice)) {
                        if (Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= 3) {
                            break;
                        } else {
                            System.err.println("Chọn từ 1 đến 3 thôi");
                        }
                    } else {
                        System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                    }
                } else {
                    System.err.println("?????");
                }
            } while (true);
            switch (Integer.parseInt(choice)) {
                case 1:
                    System.err.println("HeHeHe");
                    System.exit(0);
                case 2:
                    UserImp userImp = new UserImp();
                    List<User> listUser = userImp.readFromFile();
                    System.out.println("Nhập tên đăng nhập: ");
                    String accountName = scanner.nextLine();
                    System.out.println("Nhập mật khẩu: ");
                    String password = scanner.nextLine();
                    boolean logIn = false;
                    int bye = 1;
                    for (User user : listUser) {
                        if (user.getAccountName().equals(accountName) && user.getPassword().equals(password)) {
                            bye = -1;
                            if (user.isUserStatus()) {
                                logIn = true;
                                break;
                            }
                        }
                    }
                    if (bye != -1) {
                        System.err.println("Nhập sai rồi, Bye!");
                        System.exit(0);
                        break;
                    } else if (!logIn) {
                        System.err.println("Tài khoản đang bị khóa, Bye!");
                        System.exit(0);
                        break;
                    } else {
                        StoreManagement.cafeStoreManagement(scanner);
                    }
                    break;
                case 3:
                    scanner.close();
                    System.exit(0);
            }
        } while (true);
    }
}
