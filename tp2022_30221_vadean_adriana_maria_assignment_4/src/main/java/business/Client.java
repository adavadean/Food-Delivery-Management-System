package business;

import java.util.ArrayList;
import java.util.List;

public class Client extends User {
    private int nrcomandate = 0;
    private List<Order> orderList = new ArrayList<>();
    public Client(String username, String password)
    {
        super(username, password);

    }
    public void incTimesOrdered(){
        nrcomandate++;
    }
    public int getNumberOfOrders() {
        return nrcomandate;
    }
    public void addOrder(Order newOrder){
        orderList.add(newOrder);
    }
    public String toString(){
        String s = "Clientul" + id + "\n";
        s = s+"De cate ori s-a comandat: " + nrcomandate;
        return s;
    }
}
