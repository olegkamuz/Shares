import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static String action = "";
    private static int price = -1;
    private static int size = -1;
    private static String type = "";
    private static String queryAction = "";
    private static String orderAction;

    public static void main(String[] args) {
        scanningFile(); // todo use pattern strategy
    }

    private static void scanningFile() {
        try {
            FileInputStream fis = new FileInputStream("input.txt");
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                findAction(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void findAction(String line) {
        Scanner s1 = new Scanner(line);
        action = s1.findInLine("(u|q|o)");
        s1.close();
        if (action.equals("u")) {
            findUpdateCriteria(line);
        }
        if (action.equals("q")) {
            identifyQuery(line);
        }
        if (action.equals("o")) {
            identifyOrder(line);
        }
    }

    private static void identifyQuery(String line) {
        Scanner s1 = new Scanner(line);
        queryAction = s1.findInLine("(best_bid|best_ask|size)");
        if(queryAction.equals("best_bid")){
            findBestBid();
            printQuery();
        }
        if(queryAction.equals("best_ask")){
            findBestAsk();
            printQuery();
        }
        if(queryAction.equals("size")){
            Scanner s2 = new Scanner(line);
            s2.useDelimiter(",");
            while (s2.hasNext()) {
                if (s2.hasNextInt()) {
                    price = s2.nextInt();
                } else {
                    s2.next();
                }
            }
            s2.close();
            printQueryPrice();
        }
    }

    private static void findBestAsk() {
    }
    private static void findBestBid() {
    }

    private static void identifyOrder(String line) {
        Scanner s1 = new Scanner(line);
        orderAction = s1.findInLine("(sell|buy)");
        s1.close();
        Scanner s2 = new Scanner(line);
        s2.useDelimiter(",");
        while (s2.hasNext()) {
            if (s2.hasNextInt()) {
                size = s2.nextInt();
            } else {
                s2.next();
            }
        }
        s2.close();
        printOrder();
    }

    private static void findUpdateCriteria(String line) {
        Scanner s1 = new Scanner(line);
        type = s1.findInLine("(bid|ask|spread)");
        s1.close();
        Scanner s2 = new Scanner(line);
        s2.useDelimiter(",");
        while (s2.hasNext()) {
            if (s2.hasNextInt()) {
                price = s2.nextInt();
                size = s2.nextInt();
            } else {
                s2.next();
            }
        }
        s2.close();
        printUpdate();
    }

    private static void printUpdate() {
        System.out.println("action: " + action + " price: " + price + " size: " + size + " type: " + type);
    }
    private static void printQuery() {
        System.out.println("action: " + action + " queryAction: " + queryAction + " size: " + size + " price: " + price);
    }
    private static void printOrder() {
        System.out.println("action: " + action + " orderAction: " + orderAction + " size: " + size);
    }
    private static void printQueryPrice() {
        System.out.println("action: " + action + " queryAction: " + queryAction + " price: " + price);
    }
}
