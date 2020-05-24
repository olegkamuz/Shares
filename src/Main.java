import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Main {
    public static void main(String[] args) {
        long d1 = System.currentTimeMillis();
        scanningFile();
        long d2 = System.currentTimeMillis();
        System.out.println("Time: " + (d2 - d1));
    }

    private static void scanningFile() {
        try {
            FileInputStream fis = new FileInputStream("input.txt");

            Scanner sc = new Scanner(fis);
            String pattern = "(?:(u|q|o)[,](\\d+)[,](\\d+)[,](bid|ask|spread))|(?:(u|q|o)[,](?:(best_bid|best_ask)|(?:(size)[,](\\d+))))|(?:(u|q|o)[),](buy|sell)[,](\\d+))";
            while (sc.hasNext(pattern)) {
                sc.next(pattern);
                MatchResult result = sc.match();
                if (result != null) {
                    ArrayList<String> arr = new ArrayList();
                    for (int i = 1; i <= result.groupCount(); i++) {
                        if (result.group(i) != null) {
                            arr.add(result.group(i));
                        }
                    }
                    findAction(arr);
                }
            }
            sc.close();
            (OutputFileOrganizer.getInstance()).createOutputFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findAction(ArrayList<String> arr) {
        if (arr.get(0).equals("u")) {
            int price = Integer.parseInt(arr.get(1));
            int size = Integer.parseInt(arr.get(2));
            (Update.getInstance()).findCriteria(price, size, arr.get(3));
        }
        if (arr.get(0).equals("q")) {
            if (arr.get(1).equals("size")) {
                int price = Integer.parseInt(arr.get(2));
                (Query.getInstance()).findCriteria(arr.get(1), price);
            }
            (Query.getInstance()).findCriteria(arr.get(1));
        }
        if(arr.get(0).equals("o")){
            int size = Integer.parseInt(arr.get(2));
            (Order.getInstance()).findCriteria(arr.get(1), size);
        }
    }
}
