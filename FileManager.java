package guide;

import java.io.FileWriter;

public class FileManager {

    public static void save(
            String fileName,
            String data) {

        try {

            FileWriter fw =
                    new FileWriter(
                            fileName,
                            true);

            fw.write(data + "\n");

            fw.close();

        }
        catch(Exception e) {

            System.out.println(
                    e.getMessage());
        }
    }
}