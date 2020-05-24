public class Query {
    private static final Query instance = new Query();
    private final OutputFileOrganizer outputFileOrganizer = OutputFileOrganizer.getInstance();
    private final OrderBookBid orderBookBid = OrderBookBid.getInstance();
    private final OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();
    private final OrderBookBidStorage orderBookBidStorage = OrderBookBidStorage.getInstance();
    private final OrderBookAskStorage orderBookAskStorage = OrderBookAskStorage.getInstance();
    private final OrderBookSpreadStorage orderBookSpreadStorage = OrderBookSpreadStorage.getInstance();

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
