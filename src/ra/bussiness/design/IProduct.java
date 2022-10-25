package ra.bussiness.design;

import ra.bussiness.entity.Catalog;
import ra.bussiness.implement.ProductImp;

import java.util.List;
import java.util.Scanner;

public interface IProduct<T, E> extends ICoffeeStore<T, E> {
    T updateProduct(Scanner scanner);
    T searchProductByName(Scanner scanner);
}
