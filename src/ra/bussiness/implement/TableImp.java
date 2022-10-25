package ra.bussiness.implement;

import ra.bussiness.design.ITable;
import ra.bussiness.entity.Table;
import ra.config.Constant;
import ra.config.StoreMessages;
import ra.config.StoreValidation;
import ra.data.FileImp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class TableImp implements ITable<Table, String> {

    @Override
    public boolean create(Table table) {
        List<Table> listTable = readFromFile();
        if (listTable == null) {
            listTable = new ArrayList<>();
        }
        listTable.add(table);
        boolean result = writeToFile(listTable);
        return result;
    }

    @Override
    public boolean updateStatus(String s) {
        Scanner scanner = new Scanner(System.in);
        List<Table> listTable = readFromFile();
        boolean returnData = false;
        for (Table tableUpdate : listTable) {
            if (tableUpdate.getTableID().contains(s)) {
                System.out.println("1.Trống");
                System.out.println("2.Đang sử dụng");
                System.out.println("3.Đang ghép");
                System.out.println("4.Hỏng");
                do {
                    String choice = scanner.nextLine();
                    if (StoreValidation.checkInteger(choice)) {
                        if (choice.length() >= 1 && choice.length() <= 4) {
                            tableUpdate.setTableStatus(Integer.parseInt(choice));
                            returnData = true;
                            break;
                        } else {
                            System.err.println(StoreMessages.NOTIFY_TABLE_STATUS);
                        }
                    } else {
                        System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                    }
                } while (true);
            }
            boolean result = writeToFile(listTable);
            if (returnData && result) {
                System.out.println(StoreMessages.NOTIFY_INPUT_UPDATE);
                return true;
            } else {
                System.err.println("Không tìm thấy bàn");
            }
        }
        return false;
    }

    @Override
    public List<Table> findAll() {
        return readFromFile();
    }

    @Override
    public List<Table> readFromFile() {
        FileImp<Table> fileImp = new FileImp<>();
        return fileImp.readDataFromFile(Constant.URL_TABLE_FILE);
    }

    @Override
    public boolean writeToFile(List<Table> list) {
        FileImp<Table> fileImp = new FileImp<>();
        return fileImp.writeDataToFile(list, Constant.URL_TABLE_FILE);
    }

    @Override
    public void displayData() {
        List<Table> listTable = readFromFile();
        String status;

        listTable.sort(new Comparator<Table>() {
            @Override
            public int compare(Table o1, Table o2) {
                return o1.getTableStatus() - o2.getTableStatus();
            }
        });

        System.out.println("_________________________________________________________________________");
        System.out.printf("%-20s%-20s%-20s%-20s\n", "Mã bàn", "Tên bàn", "Số chỗ ngồi", "Trạng thái");
        System.out.println("_________________________________________________________________________");
        for (Table tab : listTable) {
            if (tab.getTableStatus() == 1) {
                status = "Trống";
            } else if (tab.getTableStatus() == 2) {
                status = "Đang ghép";
            } else if (tab.getTableStatus() == 3) {
                status = "Đang sử dụng";
            } else {
                status = "Hỏng";
            }
            System.out.printf("%-20s%-20s%-20s%-20s\n", tab.getTableID(), tab.getTableName(), tab.getSeatNumber(), status);
        }
    }

    @Override
    public Table updateTable(Scanner scanner) {
        List<Table> listTable = readFromFile();
        System.out.println("Chọn mã bàn cần cập nhật: ");
        String tableID;
        do {
            tableID = scanner.nextLine();
            if (tableID.trim() != "" && tableID.length() != 0) {
                break;
            } else {
                System.err.println("?????");
            }
        } while (true);

        boolean exist = false;
        for (Table tableUpdate : listTable) {
            if (tableUpdate.getTableID().equals(tableID)) {
                exist = true;
                System.out.println("Cập nhật tên bàn: ");
                do {
                    boolean existName = false;
                    String nameUpdate = scanner.nextLine();
                    if (nameUpdate.trim() != "" && nameUpdate.length() != 0) {
                        for (Table tab : listTable) {
                            if (tab.getTableName().equals(nameUpdate)) {
                                existName = true;
                                break;
                            }
                        }
                        if (!existName) {
                            tableUpdate.setTableName(nameUpdate);
                            break;
                        } else {
                            System.err.println(StoreMessages.NOTIFY_EXIST);
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Cập nhật số chỗ ngồi: ");
                String seatUpdate = scanner.nextLine();
                if (seatUpdate.trim() != "" && seatUpdate.length() != 0) {
                    if (StoreValidation.checkInteger(seatUpdate)) {
                        tableUpdate.setSeatNumber(Integer.parseInt(seatUpdate));
                    }
                }
                System.out.println("Cập nhật trạng thái bàn: ");
                System.out.println("1.Trống");
                System.out.println("2.Đang ghép");
                System.out.println("3.Đang sử dụng");
                System.out.println("4.Hỏng");
                String choice = scanner.nextLine();
                if (choice.trim() != "" && choice.length() != 0) {
                    if (StoreValidation.checkInteger(choice)) {
                        tableUpdate.setTableStatus(Integer.parseInt(choice));
                    }
                }
            }
        }

        if (!exist) {
            System.err.println("hong tìm thấy bàn");
        } else {
            boolean result = writeToFile(listTable);
            if (result) {
                System.out.println(StoreMessages.NOTIFY_UPDATE);
            }
        }
        return null;
    }

    @Override
    public Table inputData(Scanner scanner) {
        List<Table> listTable = readFromFile();
        Table table = new Table();
        System.out.println("Nhập mã bàn: ");
        do {
            boolean checkExit = false;
            table.setTableID(scanner.nextLine());
            if (table.getTableID().trim().length() != 0) {
                if (StoreValidation.checkTableId(table.getTableID())) {
                    for (Table tableExist : listTable) {
                        if (tableExist.getTableID().equals(table.getTableID())) {
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
                    System.err.println("Mã bàn gồm 5 ký tự, bắt đầu là 2 ký tự A-Z");
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);

        System.out.println("Nhập tên bàn: ");
        do {
            boolean checkExit = false;
            table.setTableName(scanner.nextLine());
            if (table.getTableName().trim().length() != 0) {
                for (Table tableExit : listTable) {
                    if (tableExit.getTableName().equals(table.getTableName())) {
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
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        System.out.println("Số chỗ ngồi: ");
        do {
            String seat = scanner.nextLine();
            if (seat.trim().length() != 0) {
                if (StoreValidation.checkInteger(seat)) {
                    if (StoreValidation.checkSeatNumber(seat)) {
                        table.setSeatNumber(Integer.parseInt(seat));
                        break;
                    } else {
                        System.err.println(StoreMessages.NOTIFY_CHECK_SEAT_TABLE);
                    }
                } else {
                    System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        System.out.println("Trạng thái sử dụng bàn: Trống");
//        System.out.println("1.Trống");
//        System.out.println("2.Đang ghép");
//        System.out.println("3.Đang sử dụng");
//        System.out.println("4.Hỏng");
//        do {
//            String choice = scanner.nextLine();
//            if (choice.trim().length()!=0){
//                if (StoreValidation.checkInteger(choice)){
//                    if (choice.length()>=1 && choice.length()<=4){
        table.setTableStatus(1);
//                        break;
//                    }else {
//                        System.err.println(StoreMessages.NOTIFY_TABLE_STATUS);
//                    }
//                }else {
//                    System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
//                }
//            }else {
//                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
//            }
//        }while (true);
        System.out.println(StoreMessages.NOTIFY_INPUT);
        return table;
    }
}
