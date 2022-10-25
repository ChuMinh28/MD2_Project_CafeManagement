package ra.run;

import ra.bussiness.implement.TableImp;
import ra.config.StoreMessages;
import ra.config.StoreValidation;

import java.util.Scanner;

public class TableMenu {
    private final static TableImp tableImp = new TableImp();

    public static void tableManagement(Scanner scanner) {
        boolean exitTableMenu = true;
        do {
            System.out.println();
            System.out.println("*-----------------QUẢN LÝ BÀN------------------*");
            System.out.println("|----------------------------------------------|");
            System.out.println("|             1.Danh sách bàn                  |");
            System.out.println("|             2.Thêm mới bàn                   |");
            System.out.println("|             3.Cập nhật thông tin bàn         |");
            System.out.println("|             4.Cập nhật trạng thái bàn        |");
            System.out.println("|             5.Thoát                          |");
            System.out.println("*----------------------------------------------*");
            String choice;
            do {
                choice = scanner.nextLine();
                if (choice.trim().length() != 0) {
                    if (StoreValidation.checkInteger(choice)) {
                        if (choice.length() >= 1 && choice.length() <= 5) {
                            break;
                        } else {
                            System.err.println("Vui lòng chọn từ 1 đến 5");
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
                    System.out.println("Danh Sách Bàn");
                    tableImp.displayData();
                    break;
                case 2:
                    tableImp.create(tableImp.inputData(scanner));
                    break;
                case 3:
                    tableImp.updateTable(scanner);
                    break;
                case 4:
                    System.out.println("Nhập mã bàn cần cập nhật: ");
                    String name = scanner.nextLine();
                    tableImp.updateStatus(name);
                    break;
                case 5:
                    exitTableMenu = false;
                    break;
            }
        } while (exitTableMenu);
    }
}
