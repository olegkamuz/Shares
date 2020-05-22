import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class OutputFileOrganizer {

    public void makeOutputFile(HashMap orders){
        File f = new File("output.txt");
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(orders.toString());
            myWriter.close();
            System.out.println(orders.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
