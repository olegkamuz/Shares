public class FactoryImpl extends AbstractFactory {
    @Override
    public void doAction(String action, String line){
        if(action.equals("u")){
            (new Update()).findCriteria(line);
        } else if (action.equals("q")){
            (new Query()).findCriteria(line);
        } else if(action.equals("o")){
            (new Order()).findCriteria(line);
        }
    }
}
