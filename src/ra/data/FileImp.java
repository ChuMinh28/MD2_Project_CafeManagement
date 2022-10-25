package ra.data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileImp<T> {
    public List<T> readDataFromFile(String filePath) {
        File file = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        List<T> list = new ArrayList<>();
        try {
            file = new File(filePath);
            if (file.exists()) {
                fis = new FileInputStream(file);
                ois = new ObjectInputStream(fis);
                List<Object> listObject = (List<Object>) ois.readObject();
                if (listObject != null) {
                    list = (List<T>) listObject;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public boolean writeDataToFile(List<T> list, String filePath) {
        File file = null;
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        boolean returnData = true;
        try {
            file = new File(filePath);
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
        } catch (Exception e) {
            returnData = false;
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return returnData;
    }
}
