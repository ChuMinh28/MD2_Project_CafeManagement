package ra.bussiness.implement;

import ra.bussiness.design.IUser;
import ra.bussiness.entity.User;
import ra.config.Constant;
import ra.config.StoreMessages;
import ra.config.StoreValidation;
import ra.data.FileImp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserImp implements IUser<User, Integer> {

    @Override
    public boolean create(User user) {
        List<User> listUser = readFromFile();
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        listUser.add(user);
        boolean result = writeToFile(listUser);
        return result;
    }

    @Override
    public boolean updateStatus(Integer userId) {
        List<User> listUser = readFromFile();
        boolean returnData = false;
        for (User user : listUser) {
            if (user.getUserID() == userId) {
                user.setUserStatus(!user.isUserStatus());
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listUser);
        if (returnData && result) {
            System.out.println(StoreMessages.NOTIFY_INPUT_UPDATE);
            return true;
        } else {
            System.err.println(StoreMessages.NOTIFY_CHECK_USER_SEARCH);
        }
        return false;
    }

    @Override
    public List<User> findAll() {
        return readFromFile();
    }

    @Override
    public List<User> readFromFile() {
        FileImp<User> fileImp = new FileImp<>();
        return fileImp.readDataFromFile(Constant.URL_USER_FILE);
    }

    @Override
    public boolean writeToFile(List<User> list) {
        FileImp<User> fileImp = new FileImp<>();
        return fileImp.writeDataToFile(list, Constant.URL_USER_FILE);
    }

    @Override
    public void displayData() {
        List<User> listUser = readFromFile();
        String status;

        listUser.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getDate().compareTo(o2.getDate());
            }
        });
        System.out.println("*_____________________________________________________________________________________________________________________________________________________*");
        System.out.printf("%-5s%-20s%-15s%-25s%-25s%-25s%-20s%-15s\n", "ID", "T??n t??i kho???n", "M???t kh???u", "T??n ch??? t??i kho???n", "Email", "Phone", "Ng??y t???o", "Tr???ng th??i");
        System.out.println("*_____________________________________________________________________________________________________________________________________________________*");
        for (User user : listUser) {
            if (user.isUserStatus()) {
                status = "Ho???t ?????ng";
            } else {
                status = "Kh??a";
            }
            System.out.printf("%-5d%-20s%-15s%-25s%-25s%-25s%-20s%-15s\n", user.getUserID(), user.getAccountName(), user.getPassword(), user.getFullName(), user.getEmail(), user.getPhone(), user.getDate(), status);
        }
    }

    @Override
    public User searchUser(String name) {
        List<User> listUserFull = readFromFile();
        boolean exist = false;
        System.out.println("*_____________________________________________________________________________________________________________________________________________________*");
        System.out.printf("%-5s%-20s%-15s%-25s%-25s%-25s%-20s%-15s\n", "ID", "T??n t??i kho???n", "M???t kh???u", "T??n ch??? t??i kho???n", "Email", "Phone", "Ng??y t???o", "Tr???ng th??i");
        System.out.println("*_____________________________________________________________________________________________________________________________________________________*");
        for (User user : listUserFull) {
            if (user.getFullName().contains(name) || user.getAccountName().equals(name)) {
                exist = true;
                String status = (user.isUserStatus()) ? "Ho???t ?????ng" : "Kh??a";
                System.out.printf("%-5d%-20s%-15s%-25s%-25s%-25s%-20s%-15s\n", user.getUserID(), user.getAccountName(), user.getPassword(), user.getFullName(), user.getEmail(), user.getPhone(), user.getDate(), status);
            }
        }
        if (!exist) {
            System.err.println(StoreMessages.NOTIFY_CHECK_USER_SEARCH);
        }
        return null;
    }

    @Override
    public User inputData(Scanner scanner) {
        List<User> listUser = readFromFile();
        if (listUser == null) {
            listUser = new ArrayList<>();
        }
        User userNew = new User();
        if (listUser.size() == 0) {
            userNew.setUserID(1);
        } else {
            int max = 0;
            for (User use : listUser) {
                if (max < use.getUserID()) {
                    max = use.getUserID();
                }
            }
            userNew.setUserID(max + 1);
        }
        System.out.println("Nh???p t??n ????ng nh???p: ");
        do {
            String account = scanner.nextLine();
            if (account.trim().length() != 0) {
                if (StoreValidation.checkAccount(account)) {
                    boolean exist = false;
                    for (User userExist : listUser) {
                        if (userExist.getAccountName().equals(account)) {
                            exist = true;
                            break;
                        }
                    }
                    if (!exist) {
                        userNew.setAccountName(account);
                        break;
                    } else {
                        System.err.println(StoreMessages.NOTIFY_EXIST);
                    }
                } else {
                    System.err.println(StoreMessages.NOTIFY_CHECK_USER_ACCOUNT);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        System.out.println("Nh???p m???t kh???u: ");
        do {
            String password = scanner.nextLine();
            if (password.trim().length() != 0) {
                if (StoreValidation.checkPassword(password)) {
                    userNew.setPassword(password);
                    break;
                } else {
                    System.err.println(StoreMessages.NOTIFY_CHECK_USER_PASSWORD);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        System.out.println("X??c nh???n m???t kh???u: ");

        do {
            String repeatPassword = scanner.nextLine();
            if (repeatPassword.trim().length() != 0) {
                if (userNew.getPassword().equals(repeatPassword)) {
                    break;
                } else {
                    System.err.println("M???t kh???u kh??ng ch??nh x??c, Bye!");
                    System.exit(0);
                }
            }
        } while (true);
        System.out.println("T??n ch??? t??i kho???n: ");
        do {
            userNew.setFullName(scanner.nextLine());
            if (userNew.getFullName().trim().length() != 0) {
                break;
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        userNew.setDate(dateTimeFormatter.format(now));
        System.out.println("Tr???ng th??i t??i kho???n: Ho???t ?????ng");
//        System.out.println("1.Ho???t ?????ng");
//        System.out.println("2.Kh??a");
//        int choice = Integer.parseInt(scanner.nextLine());
//        if (choice == 1) {
        userNew.setUserStatus(true);
//        }
        System.out.println("Nh???p Email: ");
        do {
            String email = scanner.nextLine();
            if (email.trim().length() != 0) {
                if (StoreValidation.checkEmail(email)) {
                    userNew.setEmail(email);
                    break;
                } else {
                    System.err.println(StoreMessages.NOTIFY_CHECK_USER_EMAIL);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        System.out.println("Nh???p s??? ??i???n th???i: ");
        do {
            String phone = scanner.nextLine();
            if (phone.trim().length() != 0) {
                if (StoreValidation.checkPhone(phone)) {
                    userNew.setPhone(phone);
                    break;
                } else {
                    System.err.println(StoreMessages.NOTIFY_CHECK_USER_PHONE);
                }
            }
        } while (true);
        System.out.println(StoreMessages.NOTIFY_INPUT);
        return userNew;
    }
}
