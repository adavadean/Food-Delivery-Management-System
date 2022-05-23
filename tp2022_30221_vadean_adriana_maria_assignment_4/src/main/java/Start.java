import business.DeliveryService;
import controller.LogInController;
public class Start {
    public static void main(String[] args) {
        DeliveryService.getInstance().importProduse();
        DeliveryService.getInstance().generarer2(0);
        LogInController.getInstance();
    }
}