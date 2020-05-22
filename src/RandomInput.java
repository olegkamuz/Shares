import java.util.ArrayList;

public class RandomInput {
    public static int U = 1;
    public static int Q = 2;
    public static int O = 3;

    public static void main(String[] args) {
        String[] actions = {"u","q","o"};
        String[] types = {"bid","ask","spread"};
        String[] queries = {"best_bid","best_ask","size"};
        for (int i = 0; i < 20; i++) {
            int randomAction = (int)(Math.round(Math.random()*2));
            int randomType = (int)(Math.round(Math.random()*2));
            int randomPrice = (int)(Math.round(Math.random()*100));
            int randomSize = (int)(Math.round(Math.random()*100));
            if(actions[randomAction].equals("u")){
                System.out.println("u," + randomPrice + "," + randomSize + "," + types[randomType]);
            }
            int randomQuery = (int) (Math.round(Math.random()*2));
            if(actions[randomAction].equals("q")){
                if(queries[randomQuery].equals("size")){

                } else {
                    System.out.println("q," + randomQuery + "," + randomSize + "," + types[randomType]);
                }
            }
        }
    }
}
