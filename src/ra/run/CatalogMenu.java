package ra.run;

import ra.bussiness.implement.CatalogImp;
import ra.config.StoreMessages;
import ra.config.StoreValidation;

import java.util.Scanner;

public class CatalogMenu {
    private final static CatalogImp catalogImp = new CatalogImp();

    public static void catalogManagement(Scanner scanner) {
        boolean exitCatalogMenu = true;
        do {
            System.out.println();
            System.out.println("*---------------QUẢN LÝ DANH MỤC---------------*");
            System.out.println("|----------------------------------------------|");
            System.out.println("|             1.Danh sách danh mục             |");
            System.out.println("|             2.Tạo mới danh mục               |");
            System.out.println("|             3.Cập nhật danh mục              |");
            System.out.println("|             4.Xóa danh mục                   |");
            System.out.println("|             5.Tìm kiếm danh mục theo tên     |");
            System.out.println("|             6.Thoát                          |");
            System.out.println("*----------------------------------------------*");

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
                    catalogImp.displayData();
                    break;
                case 2:
                    catalogImp.create(catalogImp.inputData(scanner));
                    break;
                case 3:
                    catalogImp.updateCatalog(scanner);
                    break;
                case 4:
                    System.out.println("Nhập mã danh mục cần xóa: ");
                    String id;
                    do {
                        id = scanner.nextLine();
                        if (id.trim().length() != 0) {
                            if (StoreValidation.checkInteger(id)) {
                                break;
                            } else {
                                System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                            }
                        } else {
                            System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
                        }
                    } while (true);
                    catalogImp.updateStatus(Integer.parseInt(id));
                    break;
                case 5:
                    System.out.println("Nhập tên danh mục cần tìm kiếm: ");
                    String name;
                    do {
                        name = scanner.nextLine();
                        if (name.trim().length() != 0) {
                            break;
                        } else {
                            System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
                        }
                    } while (true);
                    catalogImp.searchCatalogByName(name);
                    break;
                case 6:
                    exitCatalogMenu = false;
                    break;
            }
        } while (exitCatalogMenu);
    }
}
