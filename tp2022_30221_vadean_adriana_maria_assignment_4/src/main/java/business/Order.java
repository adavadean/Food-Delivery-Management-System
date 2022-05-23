package business;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Observable;
public class Order extends Observable implements Serializable {
    private LocalDateTime data;
    private boolean isPlaced = false;
    private int idclient;
    private int idorder;
    private double prettotal = 0;
    public int getClientId() {
        return idclient;
    }
    public void setClientId(int idclient) {
        this.idclient = idclient;
    }
    public int getId() {
        return idorder;
    }
    public void setId(int idorder) {
        this.idorder= idorder;
    }
    public void placeOrder()
    {
        data = LocalDateTime.now();
        isPlaced = true;
    }
    public LocalDateTime getDateTime()
    {
        if(isPlaced){
            return data;
        }
        else{
            return null;
        }
    }
    public void setTotalPrice(double prettotal){
        this.prettotal = prettotal;
    }
    public double getTotalPrice(){
        return prettotal;
    }
    public LocalTime getTime() {
        return LocalTime.of(data.getHour(), data.getMinute(), data.getSecond());
    }
    public LocalDate getDate() {
        return LocalDate.of(data.getYear(), data.getMonth(), data.getDayOfMonth());
    }
    @Override
    public int hashCode(){
        return idorder;
    }

    @Override
    public boolean equals(Object otherOrder)
    {
        if(((Order)otherOrder).getId() == idorder)
        {
            return true;
        }
        return false;
    }
    public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yyyy");
        String s = "Comanda nr." + idorder + "\n";
        s += formatter.format(data) + "\n";
        s += "Id clientului" + idclient + "\n";
        s += "Pret total: " +prettotal + "\n";
        return s;
    }

}
