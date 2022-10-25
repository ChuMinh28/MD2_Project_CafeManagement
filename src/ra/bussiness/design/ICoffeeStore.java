package ra.bussiness.design;

import java.util.List;
import java.util.Scanner;

public interface ICoffeeStore<T, E> {
    boolean create(T t);
    boolean updateStatus(E e);
    List<T> findAll();
    List<T> readFromFile();
    boolean writeToFile(List<T> list);
    T inputData(Scanner scanner);
    void displayData();
}
