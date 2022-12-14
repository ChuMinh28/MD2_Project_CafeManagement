package ra.bussiness.implement;

import ra.bussiness.design.ICatalog;
import ra.bussiness.entity.Catalog;
import ra.config.Constant;
import ra.config.StoreMessages;
import ra.config.StoreValidation;
import ra.data.FileImp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class CatalogImp implements ICatalog<Catalog, Integer> {
    @Override
    public boolean create(Catalog catalog) {
        List<Catalog> listCatalog = readFromFile();
        if (listCatalog == null) {
            listCatalog = new ArrayList<>();
        }
        listCatalog.add(catalog);
        return writeToFile(listCatalog);
    }

    @Override
    public boolean updateStatus(Integer id) {
        List<Catalog> listCatalog = readFromFile();
        boolean returnData = false;
        for (Catalog catalog : listCatalog) {
            if (catalog.getCatalogID() == id) {
                catalog.setCatalogStatus(false);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listCatalog);
        if (result && returnData) {
            System.out.println(StoreMessages.NOTIFY_DELETE);
            return true;
        } else {
            System.err.println(StoreMessages.NOTIFY_CATALOG_MENU);
        }
        return false;
    }

    @Override
    public List<Catalog> findAll() {
        return readFromFile();
    }

    @Override
    public List<Catalog> readFromFile() {
        FileImp<Catalog> fileImp = new FileImp<>();
        return fileImp.readDataFromFile(Constant.URL_CATALOG_FILE);
    }

    @Override
    public boolean writeToFile(List<Catalog> list) {
        FileImp<Catalog> fileImp = new FileImp<>();
        return fileImp.writeDataToFile(list, Constant.URL_CATALOG_FILE);
    }

    @Override
    public Catalog inputData(Scanner scanner) {
        List<Catalog> listCatalog = readFromFile();
        if (listCatalog == null) {
            listCatalog = new ArrayList<>();
        }
        Catalog catNew = new Catalog();
        if (listCatalog.size() == 0) {
            catNew.setCatalogID(1);
        } else {
            int max = 0;
            for (Catalog cat : listCatalog) {
                if (max < cat.getCatalogID()) {
                    max = cat.getCatalogID();
                }
            }
            catNew.setCatalogID(max + 1);
        }
        System.out.println("Nh???p t??n danh m???c: ");
        do {
            catNew.setCatalogName(scanner.nextLine());
            boolean checkExit = false;
            if (StoreValidation.checkLengthString(catNew.getCatalogName(), 6, 30)) {
                for (Catalog cat : listCatalog) {
                    if (cat.getCatalogName().equals(catNew.getCatalogName())) {
                        checkExit = true;
                        break;
                    }
                }
                if (!checkExit) {
                    break;
                } else {
                    System.err.println(StoreMessages.NOTIFY_EXIST);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_VALID_CATALOG_NAME);
            }
        } while (true);
        System.out.println("Nh???p ????? ??u ti??n danh m???c: ");
        do {
            String priority = scanner.nextLine();
            boolean checkPriority = StoreValidation.checkInteger(priority);
            if (checkPriority) {
                catNew.setPriority(Integer.parseInt(priority));
                break;
            } else {
                System.err.println(StoreMessages.NOTIFY_TYPE_FLOAT);
            }
        } while (true);
        System.out.println("Tr???ng th??i danh m???c: ");
        System.out.println("1.Ho???t ?????ng");
        System.out.println("2.Kh??ng ho???t ?????ng");
        int choice = Integer.parseInt(scanner.nextLine());
        if (choice == 1) {
            catNew.setCatalogStatus(true);
        }
        System.out.println(StoreMessages.NOTIFY_INPUT);
        return catNew;
    }

    @Override
    public void displayData() {
        List<Catalog> listCatalog = readFromFile();
        listCatalog.sort(new Comparator<Catalog>() {
            @Override
            public int compare(Catalog o1, Catalog o2) {
                return (o1.getPriority() - o2.getPriority());
            }
        });
        boolean active = false;
        System.out.println("____________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s\n", "M?? danh m???c", "T??n danh m???c", "????? ??u ti??n", "Tr???ng th??i");
        System.out.println("____________________________________________________________________________________");
        for (Catalog cat : listCatalog) {
            if (cat.isCatalogStatus()) {
                active = true;
                String status = (cat.isCatalogStatus()) ? "Ho???t ?????ng" : "Kh??ng ho???t ?????ng";
                System.out.printf("%-20s%-20s%-20s%-20s\n", cat.getCatalogID(), cat.getCatalogName(), cat.getPriority(), status);
            }
        }
        System.out.println("____________________________________________________________________________________");
        if (!active) {
            System.err.println("Kh??ng c?? danh m???c ??ang ho???t ?????ng!");
        }
    }

    @Override
    public Catalog updateCatalog(Scanner scanner) {
        List<Catalog> listCatalog = readFromFile();
        System.out.println("_____________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s\n", "M?? danh m???c", "T??n danh m???c", "????? ??u ti??n", "Tr???ng th??i");
        System.out.println("_____________________________________________________________________________________");
        for (Catalog cat : listCatalog) {
            String status = (cat.isCatalogStatus()) ? "Ho???t ?????ng" : "Kh??ng ho???t ?????ng";
            System.out.printf("%-20s%-20s%-20s%-20s\n", cat.getCatalogID(), cat.getCatalogName(), cat.getPriority(), status);
        }
        System.out.println("______________________________________________________________________________________");
        System.out.println("Nh???p m?? danh m???c c???n c???p nh???t: ");
        String idUpdate;
        do {
            idUpdate = scanner.nextLine();
            if (idUpdate.trim().length() != 0) {
                if (StoreValidation.checkInteger(idUpdate)) {
                    break;
                } else {
                    System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);

        boolean exit = false;
        for (Catalog cat : listCatalog) {
            if (cat.getCatalogID() == Integer.parseInt(idUpdate)) {
                exit = true;
                System.out.println("C???p nh???t t??n danh m???c: ");
                String nameUpdate;
                do {
                    nameUpdate = scanner.nextLine();
                    boolean existName = false;
                    if (nameUpdate.length() != 0 && nameUpdate.trim() != "") {
                        if (StoreValidation.checkLengthString(nameUpdate, 6, 30)) {
                            for (Catalog catName : listCatalog) {
                                if (catName.getCatalogName().equals(nameUpdate)) {
                                    existName = true;
                                    break;
                                }
                            }
                            if (!existName) {
                                cat.setCatalogName(nameUpdate);
                                break;
                            } else {
                                System.err.println(StoreMessages.NOTIFY_EXIST);
                            }
                        } else {
                            System.err.println(StoreMessages.NOTIFY_VALID_CATALOG_NAME);
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("C???p nh???t ????? ??u ti??n danh m???c: ");
                String priority = scanner.nextLine();
                if (priority.length() != 0 && priority.trim() != "") {
                    if (StoreValidation.checkInteger(priority)) {
                        cat.setPriority(Integer.parseInt(priority));
                    } else {
                        System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                    }
                }
                System.out.println("Tr???ng th??i danh m???c: ");
                System.out.println("1.Ho???t ?????ng");
                System.out.println("2.Kh??ng ho???t ?????ng");
                String choice = scanner.nextLine();
                if (choice.trim() != "" && choice.length() != 0) {
                    if (Integer.parseInt(choice) == 1) {
                        cat.setCatalogStatus(true);
                    }
                }
            }
        }
        boolean result = writeToFile(listCatalog);
        if (!exit) {
            System.err.println(StoreMessages.NOTIFY_CATALOG_MENU);
        } else {
            if (result) {
                System.out.println(StoreMessages.NOTIFY_UPDATE);
            }
        }
        return null;
    }

    @Override
    public List<Catalog> searchCatalogByName(String name) {
        List<Catalog> listCatalog = readFromFile();
        boolean checkSearch = false;
        System.out.println("_____________________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s\n", "M?? danh m???c", "T??n danh m???c", "????? ??u ti??n", "Tr???ng th??i");
        System.out.println("_____________________________________________________________________________________");
        for (Catalog cat : listCatalog) {
            if (cat.getCatalogName().toLowerCase().contains(name.toLowerCase())) {
                String status = (cat.isCatalogStatus()) ? "Ho???t ?????ng" : "Kh??ng ho???t ?????ng";
                System.out.printf("%-20s%-20s%-20s%-20s\n", cat.getCatalogID(), cat.getCatalogName(), cat.getPriority(), status);
                checkSearch = true;
                break;
            }
        }
        System.out.println("_____________________________________________________________________________________");
        if (!checkSearch) {
            System.err.println(StoreMessages.NOTIFY_CATALOG_MENU);
        }
        return listCatalog;
    }
}
