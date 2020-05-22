public class OrderBookBid implements OrderBook{
    private static OrderBookBid instance = new OrderBookBid();
    private static String TYPE = "bid";
    private int price;
    private int size;
    private OrderBookBid() {}
    public static OrderBookBid getInstance() {
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
    public void setPriceAndSize(int price, int size) {
        if(price > this.price && size != 0){
            this.price = price;
            this.size = size;
        }
    }
    @Override
    public int getPrice() {
        return price;
    }
    @Override
    public int getSize() {
        return size;
    }

    public void print(){
        System.out.println("price: " + price + " size " + size);
    }

}
