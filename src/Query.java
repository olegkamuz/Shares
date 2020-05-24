public class Query {
    private static Query instance = new Query();
    private static String ACTION = "q";
    private String queryAction = "";
    private int price = -1;
    private OutputFileOrganizer outputFileOrganizer = OutputFileOrganizer.getInstance();
    private OrderBookBid orderBookBid = OrderBookBid.getInstance();
    private OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();
    private OrderBookBidStorage orderBookBidStorage = OrderBookBidStorage.getInstance();
    private OrderBookAskStorage orderBookAskStorage = OrderBookAskStorage.getInstance();
    private OrderBookSpreadStorage orderBookSpreadStorage = OrderBookSpreadStorage.getInstance();

    private Query() {
    }

    public static Query getInstance() {
        return instance;
    }

    public void findCriteria(String type, int price) {
        if (orderBookBidStorage.getSizeAtPrice(price) != null) {
            writeToOutputFile((OrderBookBidStorage.getInstance()).getSizeAtPrice(price));
        }
        if (orderBookAskStorage.getSizeAtPrice(price) != null) {
            writeToOutputFile((OrderBookAskStorage.getInstance()).getSizeAtPrice(price));
        }
        if (orderBookSpreadStorage.getSizeAtPrice(price) != null) {
            writeToOutputFile((OrderBookSpreadStorage.getInstance()).getSizeAtPrice(price));
        }
    }

    public void findCriteria(String type) {
        if (type.equals("best_bid")) {
            writeToOutputFile(orderBookBid);
        }
        if (type.equals("best_ask")) {
            writeToOutputFile(orderBookAsk);
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
