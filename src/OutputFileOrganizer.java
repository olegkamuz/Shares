import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputFileOrganizer {
    private static OutputFileOrganizer instance = new OutputFileOrganizer();
    private OutputFileOrganizer(){}
    private static StringBuilder sb = new StringBuilder();
    public static OutputFileOrganizer getInstance(){
        return instance;
    }
    public void add (int size){
        sb.append("" + size + "\n");
    }
    public void add (int price, int size){
        sb.append(price + "," + size + "\n");
    }
    public void createOutputFile(){
        File f = new File("output.txt");
        try {
            FileWriter myWriter = new FileWriter("output.txt");
            myWriter.write(sb.toString());
            myWriter.close();
//            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
