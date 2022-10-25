package ra.bussiness.design;

import java.util.Scanner;

public interface ITable<T, E> extends ICoffeeStore<T, E> {
    T updateTable(Scanner scanner);
}
