import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        scanningFile();
    }

    private static void scanningFile() {
        try {
//            FileInputStream fis = new FileInputStream("input.txt");
            FileInputStream fis = new FileInputStream("inputRandom.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                findAction(sc.nextLine());
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
        FactoryImpl fi = new FactoryImpl();
        if (action.equals("u")) {
            fi.doAction("u", line);
        }
        if (action.equals("q")) {
            fi.doAction("q", line);
        }
        if (action.equals("o")) {
            fi.doAction("o", line);
        }
    }
}
