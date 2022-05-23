package business;
public class BaseProduct extends MenuItem  {
    public BaseProduct(String titlu, double rating, int calorii, int proteine, int grasimi, int sodiu, double pret)
    {
        super(titlu,rating, calorii, proteine, grasimi, sodiu,pret);
    }
    public String toString(){
        String s= id + " " + titlu + ":\n";
        s += "Rating: " + rating + "\n";
        s += "Pret: " + pret + "\n";
        return s;
    }
}
