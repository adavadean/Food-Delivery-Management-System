package business;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    protected int id;
    protected String titlu;
    protected double rating;
    protected int calorii;
    protected int proteine;
    protected int grasimi;
    protected int sodiu;
    protected double pret;
    protected int nrcomandate = 0;

    public MenuItem(String title, double price)
    {
        this.titlu = title;
        this.pret = price;
    }
    public MenuItem(String titlu, double rating, int calorii, int proteine, int grasimi, int sodiu,double pret)
    {
        this.titlu = titlu;
        this.pret= pret;
        this.rating = rating;
        this.calorii = calorii;
        this.proteine= proteine;
        this.grasimi= grasimi;
        this.sodiu = sodiu;
    }
    public void incTimesOrdered(){
        nrcomandate++;
    }
    public String getTitle() {
        return titlu;
    }
    public double getPrice() {
        return pret;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public double getRating() {
        return rating;
    }
    public int getCalories() {
        return calorii;
    }
    public int getProtein() {
        return proteine;
    }
    public int getFat() {
        return grasimi;
    }
    public int getSodium() {
        return sodiu;
    }
    public abstract String toString();
    public int getTimesOrdered(){
        return nrcomandate;
    }
}
