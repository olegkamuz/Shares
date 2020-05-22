public class OrderBookAsk implements OrderBook{
    private static OrderBookAsk instance = new OrderBookAsk();
    private int price = Integer.MAX_VALUE;
    private int size = Integer.MAX_VALUE;
    private OrderBookAsk() {}
    public static OrderBookAsk getInstance() {
        return instance;
    }
    @Override
    public boolean bestExists(){
        if(price > 0 && size >= 0){
            return true;
        }
        return false;
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
    }
    @Override
    public void setPriceAndSize(int price, int size) {
        if(price < this.price && size != 0) {
            this.price = price;
            this.size = size;
        }
    }
    @Override
    public int getSize() {
        return size;
    }
}
