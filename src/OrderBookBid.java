public class OrderBookBid implements OrderBook {
    private static OrderBookBid instance = new OrderBookBid();
    private int price = -1;
    private int size = -1;

    private OrderBookBid() {
    }

    public static OrderBookBid getInstance() {
        return instance;
    }

    @Override
    public boolean bestExists() {
        if (price > 0 && size >= 0) {
            return true;
        }
        return false;
    }

    @Override
    public void setPriceAndSize(int price, int size) {
        if (price > this.price && size != 0) {
            this.price = price;
            this.size = size;
        }
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
        if(size == 0){
            (OrderBookBidStorage.getInstance()).findNewBestBid();
        }
    }

    @Override
    public void setNewBest(int price, int size) {
        this.price = price;
        this.size = size;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void resetBest() {
        this.price = -1;
        this.size = -1;
    }
}
