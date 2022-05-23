package business;
import java.io.IOException;
import java.util.List;
import java.util.Set;
public interface IDeliveryServiceProcessing {

    List<BaseProduct> importProduse() throws IOException;
    int addProdus(MenuItem toAdd);
    int createCompositeProduct(List<BaseProduct> productList, String title, double price);
    int deleteProduct(int toDelete);
    int modifyProduct(int idToModify, MenuItem toModify);
    void generateReports();
    int comandanoua(Client client, List<MenuItem> orderedProducts);

    List<MenuItem> fetchMenuItems();

    Set<Order> getOrders();
}
