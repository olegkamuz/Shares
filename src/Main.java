import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        long d1 = System.currentTimeMillis();
        scanningFile();
        long d2 = System.currentTimeMillis();
        System.out.println("Time: " + (d2 - d1));
    }

    private static void scanningFile() {
        try {
//            FileInputStream fis = new FileInputStream("input.txt");
            FileInputStream fis = new FileInputStream("inputRandom.txt");
//            FileInputStream fis = new FileInputStream("inputSpecialCase.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String nextLine = sc.nextLine();
                if(!nextLine.isEmpty()){
                    findAction(nextLine);
                }
            }
            sc.close();
            (OutputFileOrganizer.getInstance()).createOutputFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findAction(String line) {
        Scanner s1 = new Scanner(line);
        String action = s1.findInLine("(u|q|o)");
        s1.close();
        if(action.equals("u")){
            (Update.getInstance()).findCriteria(line);
        }
        if (action.equals("q")){
            (Query.getInstance()).findCriteria(line);
        }
        if(action.equals("o")){
            (Order.getInstance()).findCriteria(line);
        }
    }
}
