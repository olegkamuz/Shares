import java.util.Scanner;

public class Query implements FindCriteria {
    private static String ACTION = "q";
    private String queryAction = "";
    private int price = -1;
    private OutputFileOrganizer outputFileOrganizer = OutputFileOrganizer.getInstance();
    private OrderBookBid orderBookBid = OrderBookBid.getInstance();
    private OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();

    @Override
    public void findCriteria(String line) {
        Scanner s1 = new Scanner(line);
        queryAction = s1.findInLine("(best_bid|best_ask|size)");
        if (queryAction.equals("best_bid")) {
            writeToOutputFile(orderBookBid);
            printQuery();
        }
        if (queryAction.equals("best_ask")) {
            writeToOutputFile(orderBookAsk);
            printQuery();
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
            int size = (OrderBookStorage.getInstance()).getSizeAtPrice(price);
            writeToOutputFile(size);
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

    private void printQuery() {
        System.out.println("action: " + Query.ACTION + " queryAction: " + queryAction);
    }

    private void printQueryPrice() {
        System.out.println("action: " + Query.ACTION + " queryAction: " + queryAction + " price: " + price);
    }
}
