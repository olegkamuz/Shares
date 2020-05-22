import java.util.Scanner;

public class Order implements FindCriteria{
    private static String ACTION = "o";
    private static int price = -1;
    private static int size = -1;
    private static String type = "";
    private static String queryAction = "";
    private static String orderAction;

    @Override
    public void findCriteria(String line) {
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
    private void printOrder() {
        System.out.println("action: " + Order.ACTION + " orderAction: " + orderAction + " size: " + size);
    }
}
