package ra.bussiness.implement;

import ra.bussiness.design.IProduct;
import ra.bussiness.entity.Catalog;
import ra.bussiness.entity.Product;
import ra.config.Constant;
import ra.config.StoreMessages;
import ra.config.StoreValidation;
import ra.data.FileImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductImp implements IProduct<Product, String> {

    @Override
    public boolean create(Product product) {
        List<Product> listProduct = readFromFile();
        if (listProduct == null) {
            listProduct = new ArrayList<>();
        }
        listProduct.add(product);
        boolean result = writeToFile(listProduct);
        return result;
    }

    @Override
    public boolean updateStatus(String productId) {
        List<Product> listProduct = readFromFile();
        boolean returnData = false;
        for (Product product : listProduct) {
            if (product.getProductID().equals(productId)) {
                product.setProductStatus(false);
                returnData = true;
                break;
            }
        }
        boolean result = writeToFile(listProduct);
        if (result && returnData) {
            System.err.println(StoreMessages.NOTIFY_INPUT_UPDATE);
            return true;
        } else {
            System.err.println(StoreMessages.NOTIFY_DELETE);
        }
        return false;
    }

    @Override
    public List<Product> findAll() {
        return readFromFile();
    }

    @Override
    public List<Product> readFromFile() {
        FileImp<Product> fileImp = new FileImp<>();
        return fileImp.readDataFromFile(Constant.URL_PRODUCT_FILE);
    }

    @Override
    public boolean writeToFile(List<Product> list) {
        FileImp<Product> fileImp = new FileImp<>();
        return fileImp.writeDataToFile(list, Constant.URL_PRODUCT_FILE);
    }

    @Override
    public Product inputData(Scanner scanner) {
        List<Product> listProduct = readFromFile();
        if (listProduct == null) {
            listProduct = new ArrayList<>();
        }
        Product product = new Product();
        System.out.println("Nh???p m?? s???n ph???m: ");
        do {
            String productId = scanner.nextLine();
            boolean checkExit = false;
            if (productId.trim().length() == 5) {
                if (productId.charAt(0) == 'P') {
                    for (Product pro : listProduct) {
                        if (pro.getProductID().equals(productId)) {
                            checkExit = true;
                            break;
                        }
                    }
                    if (!checkExit) {
                        product.setProductID(productId);
                        break;
                    } else {
                        System.err.println(StoreMessages.NOTIFY_EXIST);
                    }
                } else {
                    System.err.println(StoreMessages.NOTIFY_INPUT_CHAR_PRODUCT);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_INPUT_PRODUCT_LENGTH);
            }
        } while (true);
        System.out.println("Nh???p t??n s???n ph???m: ");
        do {
            String productName = scanner.nextLine();
            boolean checkExit = false;
            if (productName.trim().length() != 0) {
                if (StoreValidation.checkLengthString(productName, 6, 30)) {
                    for (Product pro : listProduct) {
                        if (pro.getProductName().equals(productName)) {
                            checkExit = true;
                            break;
                        }
                    }
                    if (!checkExit) {
                        product.setProductName(productName);
                        break;
                    } else {
                        System.err.println(StoreMessages.NOTIFY_EXIST);
                    }
                } else {
                    System.err.println(StoreMessages.NOTIFY_VALID_PRODUCT_NAME);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        System.out.println("Nh???p gi?? s???n ph???m: ");
        do {
            String price = scanner.nextLine();
            if (price.trim().length() != 0) {
                if (StoreValidation.checkFloat(price)) {
                    if (StoreValidation.checkProductPrice(price)) {
                        product.setPrice(Float.parseFloat(price));
                        break;
                    } else {
                        System.err.println(StoreMessages.NOTIFY_INPUT_PRODUCT_PRICE);
                    }
                } else {
                    System.err.println(StoreMessages.NOTIFY_TYPE_FLOAT);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        System.out.println("Ch???n danh m???c cho s???n ph???m: ");
        CatalogImp catalogImp = new CatalogImp();
        List<Catalog> listCatalog = catalogImp.readFromFile();
        do {
            for (Catalog cat : listCatalog) {
                System.out.printf("%d.%s\n", cat.getCatalogID(), cat.getCatalogName());
            }
            String choice = scanner.nextLine();
            if (StoreValidation.checkInteger(choice)) {
                Catalog cat = StoreValidation.checkChoiceCatalog(listCatalog, Integer.parseInt(choice));
                if (cat != null) {
                    product.setCatalog(cat);
                    break;
                } else {
                    System.err.println(StoreMessages.NOTIFY_CATALOG_CHOICE);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
            }
        } while (true);
        System.out.println("Tr???ng th??i s???n ph???m: Ho???t ?????ng");
//        System.out.println("1.Ho???t ?????ng");
//        System.out.println("2.Ng???ng kinh doanh");
//        int choice = Integer.parseInt(scanner.nextLine());
//        if (choice == 1) {
        product.setProductStatus(true);
//        }
        System.out.println(StoreMessages.NOTIFY_INPUT);
        return product;
    }

    @Override
    public void displayData() {
        CatalogImp catalogImp = new CatalogImp();
        List<Catalog> listCat = catalogImp.findAll();
        ProductImp productImp = new ProductImp();
        List<Product> listPro = productImp.findAll();
        System.out.println("Danh S??ch S???n Ph???m Theo Danh M???c");
        boolean active = false;
        for (Catalog cat : listCat) {
            if (cat.isCatalogStatus()) {
                System.out.printf("\t|%d.%s|\n", cat.getCatalogID(), cat.getCatalogName());
                System.out.println("_________________________________________________________________________");
                System.out.printf("%-20s%-20s%-20s%-20s\n", "M?? s???n ph???m", "T??n s???n ph???m", "Gi??", "Tr???ng th??i");
                System.out.println("_________________________________________________________________________");
                for (Product pro : listPro) {
                    if (pro.getCatalog().getCatalogID() == cat.getCatalogID()) {
                        active = true;
                        String status = (pro.isProductStatus()) ? "Ho???t ?????ng" : "Ng???ng kinh doanh";
                        System.out.printf("%-20s%-20s%-20.0f%-20s\n", pro.getProductID(), pro.getProductName(), pro.getPrice(), status);
                    }
                }
                if (!active) {
                    System.err.println("Kh??ng c?? s???n ph???m ??ang ho???t ?????ng!");
                }
            }
        }
    }

    @Override
    public Product updateProduct(Scanner scanner) {
        CatalogImp catalogImp = new CatalogImp();
        List<Catalog> listCatalog = catalogImp.findAll();
        ProductImp productImp = new ProductImp();
        List<Product> listProduct = productImp.findAll();
        for (Catalog cat : listCatalog) {
            if (cat.isCatalogStatus()) {
                System.out.printf("\t|%d.%s|\n", cat.getCatalogID(), cat.getCatalogName());
                System.out.println("_________________________________________________________________________");
                System.out.printf("%-20s%-20s%-20s%-20s\n", "M?? s???n ph???m", "T??n s???n ph???m", "Gi??", "Tr???ng th??i");
                System.out.println("_________________________________________________________________________");
                for (Product pro : listProduct) {
                    if (pro.getCatalog().getCatalogID() == cat.getCatalogID()) {
                        String status = (pro.isProductStatus()) ? "Ho???t ?????ng" : "Ng???ng kinh doanh";
                        System.out.printf("%-20s%-20s%-20.0f%-20s\n", pro.getProductID(), pro.getProductName(), pro.getPrice(), status);
                    }
                }
            }
        }
        System.out.println("Nh???p m?? s???n ph???m c???n c???p nh???t: ");
        String name = scanner.nextLine();
        boolean exist = false;
        for (Product productUpdate : listProduct) {
            if (productUpdate.getProductID().equals(name)) {
                exist = true;
                System.out.println("C???p nh???t t??n s???n ph???m: ");
                String nameUpdate;
                do {
                    nameUpdate = scanner.nextLine();
                    if (nameUpdate.trim() != "" && nameUpdate.length() != 0) {
                        if (StoreValidation.checkLengthString(nameUpdate, 6, 30)) {
                            boolean existName = false;
                            for (Product pro : listProduct) {
                                if (pro.getProductName().equals(nameUpdate)) {
                                    existName = true;
                                    break;
                                }
                            }
                            if (!existName) {
                                productUpdate.setProductName(nameUpdate);
                                break;
                            } else {
                                System.err.println(StoreMessages.NOTIFY_EXIST);
                            }
                        } else {
                            System.err.println(StoreMessages.NOTIFY_VALID_PRODUCT_NAME);
                        }
                    } else {
                        break;
                    }
                } while (true);

                System.out.println("C???p nh???t gi?? s???n ph???m: ");
                String priceUpdate;
                do {
                    priceUpdate = scanner.nextLine();
                    if (priceUpdate.trim() != "" && priceUpdate.length() != 0) {
                        if (StoreValidation.checkFloat(priceUpdate)) {
                            if (StoreValidation.checkProductPrice(priceUpdate)) {
                                productUpdate.setPrice(Float.parseFloat(priceUpdate));
                                break;
                            } else {
                                System.err.println(StoreMessages.NOTIFY_INPUT_PRODUCT_PRICE);
                            }
                        } else {
                            System.err.println(StoreMessages.NOTIFY_TYPE_FLOAT);
                        }
                    } else {
                        break;
                    }
                } while (true);
                System.out.println("Tr???ng th??i s???n ph???m: Ho???t ?????ng");
                System.out.println("1.Ho???t ?????ng");
                System.out.println("2.Ng???ng kinh doanh");
                String choiceStatus = scanner.nextLine();
                if (choiceStatus.trim() != "" && choiceStatus.length() != 0) {
                    if (Integer.parseInt(choiceStatus) == 1) {
                        productUpdate.setProductStatus(true);
                    }
                }
                System.out.println("C???p nh???t danh m???c cho s???n ph???m: ");
                for (Catalog cat : listCatalog) {
                    System.out.printf("%d.%s\n", cat.getCatalogID(), cat.getCatalogName());
                }
                String choice;
                do {
                    choice = scanner.nextLine();
                    if (choice.trim() != "" && choice.length() != 0) {
                        if (StoreValidation.checkInteger(choice)) {
                            Catalog cat = StoreValidation.checkChoiceCatalog(listCatalog, Integer.parseInt(choice));
                            if (cat != null) {
                                productUpdate.setCatalog(cat);
                                break;
                            } else {
                                System.err.println(StoreMessages.NOTIFY_CATALOG_CHOICE);
                            }
                        } else {
                            System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                        }
                    } else {
                        break;
                    }
                } while (true);
            }
        }
        if (!exist) {
            System.err.println("Kh??ng t??m th???y");
        } else {
            boolean result = writeToFile(listProduct);
            if (result) {
                System.out.println(StoreMessages.NOTIFY_UPDATE);
            }
        }
        return null;
    }

    @Override
    public Product searchProductByName(Scanner scanner) {
        System.out.println("1.T??m ki???m b???ng t??n s???n ph???m");
        System.out.println("2.T??m ki???m s???n ph???m trong kho???ng gi??: ");
        System.out.println("3.Tho??t");
        String choice;
        do {
            choice = scanner.nextLine();
            if (choice.trim().length() != 0) {
                if (StoreValidation.checkInteger(choice)) {
                    if (Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= 3) {
                        break;
                    } else {
                        System.err.println("Vui l??ng ch???n t??? 1-3");
                    }
                } else {
                    System.err.println(StoreMessages.NOTIFY_TYPE_INTEGER);
                }
            } else {
                System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
            }
        } while (true);
        switch (Integer.parseInt(choice)) {
            case 1:
                System.out.println("Nh???p t??n s???n ph???m mu???n t??m ki???m: ");
                String search;
                do {
                    search = scanner.nextLine();
                    if (search.trim().length() != 0) {
                        break;
                    } else {
                        System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
                    }
                } while (true);
                List<Product> listProduct = readFromFile();
                boolean exist = false;
                System.out.println("_________________________________________________________________________");
                System.out.printf("%-20s%-20s%-20s%-20s\n", "M?? s???n ph???m", "T??n s???n ph???m", "Gi??", "Tr???ng th??i");
                System.out.println("_________________________________________________________________________");
                for (Product pro : listProduct) {
                    if (pro.getProductName().toLowerCase().contains(search.toLowerCase())) {
                        exist = true;
                        String status = (pro.isProductStatus()) ? "Ho???t ?????ng" : "Ng???ng kinh doanh";
                        System.out.printf("%-20s%-20s%-20.0f%-20s\n", pro.getProductID(), pro.getProductName(), pro.getPrice(), status);
                        break;
                    }
                }
                System.out.println("_________________________________________________________________________");
                if (!exist) {
                    System.err.println(StoreMessages.NOTIFY_SEARCH_PRODUCT);
                }
                break;
            case 2:
                System.out.println("Nh???p kho???ng gi?? mu???n t??m ki???m: ? ---> ?");
                List<Product> listProduct1 = readFromFile();
                String priceSearchBegin;
                do {
                    priceSearchBegin = scanner.nextLine();
                    if (priceSearchBegin.trim().length() != 0) {
                        if (StoreValidation.checkFloat(priceSearchBegin)) {
                            if (StoreValidation.checkProductPrice(priceSearchBegin)) {
                                break;
                            } else {
                                System.err.println(StoreMessages.NOTIFY_INPUT_PRODUCT_PRICE);
                            }
                        } else {
                            System.err.println(StoreMessages.NOTIFY_TYPE_FLOAT);
                        }
                    } else {
                        System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
                    }
                } while (true);
                String priceSearchEnd;
                do {
                    priceSearchEnd = scanner.nextLine();
                    if (priceSearchEnd.trim().length() != 0) {
                        if (StoreValidation.checkFloat(priceSearchEnd)) {
                            if (StoreValidation.checkProductPrice(priceSearchEnd)) {
                                break;
                            } else {
                                System.err.println(StoreMessages.NOTIFY_INPUT_PRODUCT_PRICE);
                            }
                        } else {
                            System.err.println(StoreMessages.NOTIFY_TYPE_FLOAT);
                        }
                    } else {
                        System.err.println(StoreMessages.NOTIFY_CHECK_SPACE);
                    }
                } while (true);
                System.out.println("_________________________________________________________________________");
                System.out.printf("%-20s%-20s%-20s%-20s\n", "M?? s???n ph???m", "T??n s???n ph???m", "Gi??", "Tr???ng th??i");
                System.out.println("_________________________________________________________________________");
                for (Product pro : listProduct1) {
                    if (pro.getPrice() >= Float.parseFloat(priceSearchBegin) && pro.getPrice() <= Float.parseFloat(priceSearchEnd)) {
                        String status = (pro.isProductStatus()) ? "Ho???t ?????ng" : "Ng???ng kinh doanh";
                        System.out.printf("%-20s%-20s%-20.0f%-20s\n", pro.getProductID(), pro.getProductName(), pro.getPrice(), status);
                    }
                }
                System.out.println("_________________________________________________________________________");
                break;
        }
        return null;
    }
}
