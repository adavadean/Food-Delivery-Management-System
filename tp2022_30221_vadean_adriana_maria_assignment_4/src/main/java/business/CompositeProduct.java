package business;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem {
    private List<BaseProduct> items = new ArrayList<>();
    public CompositeProduct(String titlu, double pret) {
        super(titlu, pret);
    }
    public void addItem(BaseProduct toAdd){
        items.add(toAdd);
        this.calorii += toAdd.getCalories();
        this.proteine += toAdd.getProtein();
        this.grasimi += toAdd.getFat();
        this.sodiu += toAdd.getSodium();
    }
    public String toString(){
        String s = id + " " + titlu + ":\n";
        for(BaseProduct product: items)
        {
            s += "  " + product.getTitle() + "\n";
        }
        s += "  Rating: " + rating + "\n";
        s+= "  Pret: " + pret;
        return s;
    }
}
