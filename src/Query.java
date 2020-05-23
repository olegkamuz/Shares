import java.util.Scanner;

public class Query implements FindCriteria {
    private static Query instance = new Query();
    private static String ACTION = "q";
    private String queryAction = "";
    private int price = -1;
    private OutputFileOrganizer outputFileOrganizer = OutputFileOrganizer.getInstance();
    private OrderBookBid orderBookBid = OrderBookBid.getInstance();
    private OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();
    private Query(){}
    public static Query getInstance() {
        return instance;
    }

    @Override
    public void findCriteria(String line) {
        Scanner s1 = new Scanner(line);
        queryAction = s1.findInLine("(best_bid|best_ask|size)");
        if (queryAction.equals("best_bid")) {
            writeToOutputFile(orderBookBid);
        }
        if (queryAction.equals("best_ask")) {
            writeToOutputFile(orderBookAsk);
        }
        if (queryAction.equals("size")) {
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
            if((OrderBookStorage.getInstance()).getSizeAtPrice(price) != null) {
                writeToOutputFile((OrderBookStorage.getInstance()).getSizeAtPrice(price));
            }
        }
    }

    private void writeToOutputFile(int size) {
        outputFileOrganizer.add(size);
    }

    private void writeToOutputFile(OrderBookAsk ob) {
        if (ob.bestExists()) {
            outputFileOrganizer.add(ob.getPrice(), ob.getSize());
        }
    }

    private void writeToOutputFile(OrderBookBid ob) {
        if (ob.bestExists()) {
            outputFileOrganizer.add(ob.getPrice(), ob.getSize());
        }
    }
}
