public class Update {
    private static Update instance = new Update();
    private int price = -1;
    private int size = -1;
    private String type = "";
    private OrderBookBidStorage orderBookBidStorage = OrderBookBidStorage.getInstance();
    private OrderBookAskStorage orderBookAskStorage = OrderBookAskStorage.getInstance();
    private OrderBookSpreadStorage orderBookSpreadStorage = OrderBookSpreadStorage.getInstance();
    private OrderBookAsk orderBookAsk = OrderBookAsk.getInstance();
    private OrderBookBid orderBookBid = OrderBookBid.getInstance();

    private Update() {
    }

    public static Update getInstance() {
        return instance;
    }

    public void findCriteria(int price, int size, String type) {
        if (type.equals("bid")) {
            updateBid(price, size);
        }
        if (type.equals("ask")) {
            updateAsk(price, size);
        }
        if (type.equals("spread")) {
            updateSpread(price, size);
        }

    }

    private void updateBid(int price, int size) {
        orderBookBid.setPriceAndSize(price, size);
        orderBookBidStorage.add(price, size);
    }

    private void updateAsk(int price, int size) {
        orderBookAsk.setPriceAndSize(price, size);
        orderBookAskStorage.add(price, size);
    }

    private void updateSpread(int price, int size) {
        orderBookSpreadStorage.add(price, size);
    }
}
