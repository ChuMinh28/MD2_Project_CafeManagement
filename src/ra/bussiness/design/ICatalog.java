package ra.bussiness.design;

import java.util.List;
import java.util.Scanner;

public interface ICatalog<T, E> extends ICoffeeStore<T, E> {
    T updateCatalog(Scanner scanner);
    List<T> searchCatalogByName(String name);
}
