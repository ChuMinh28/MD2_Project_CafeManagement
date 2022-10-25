package ra.bussiness.design;

import java.util.Scanner;

public interface IUser<T, E> extends ICoffeeStore<T, E>{
    T searchUser(String name);
}
