public class Dispatcher extends AbstractDispatcher {
    @Override
    public void doAction(String action, String line){
        if(action.equals("u")){
            (Update.getInstance()).findCriteria(line);
        }
        if (action.equals("q")){
            (Query.getInstance()).findCriteria(line);
        }
        if(action.equals("o")){
            (Order.getInstance()).findCriteria(line);
        }
    }
}
