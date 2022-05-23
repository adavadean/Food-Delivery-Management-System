package data;
import business.*;
import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class Factura implements Serializable {
    /**
     * Se printeaza factura
     * @param comanda-inf comenzii
     * @param client -clientul
     * @param chestiicomandate - lucrurile comandate
     *
     */
    public void scrierefac(Order comanda, Client client, List<MenuItem> chestiicomandate)
    {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss, dd/MM/yyyy");
            FileWriter billWriter = new FileWriter("Factura " +comanda.getId() + ".txt");
            billWriter.write("Comanda " + comanda.getId() + "\n");
            billWriter.write(formatter.format(comanda.getDateTime()) + "\n");
            billWriter.write("Clientul " + client.getId() + "\n");
            billWriter.write("Produse comandate:\n");
            for(MenuItem item: chestiicomandate)
            {
                billWriter.write(item.toString());
            }
            billWriter.write("Total: " + comanda.getTotalPrice());
            billWriter.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "A fost o eroare la printarea facturii!", "Eroare", JOptionPane.ERROR_MESSAGE);
        }
    }
}