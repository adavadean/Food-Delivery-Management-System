package data;
import business.Client;
import business.MenuItem;
import business.Order;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
public class RaportGen {
    private static RaportGen raportgenerator = new RaportGen();
    public static RaportGen getInstance(){
        return raportgenerator;
    }
    /**
     * Raportul 1
     * @param titlu-titlul
     * @param comenzigasite -comenzile gasite
     */
    public void generareRaport1(String titlu, List<Order> comenzigasite)
    {
        try {
            FileWriter raport = new FileWriter("Raport1.txt");
            raport.write(titlu + "\n\n");
            for(Order o: comenzigasite)
            {
                raport.write(o.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Raportul 2
     * @param titlu-titlul
     * @param chestiigasite -produsele gasite
     */
    public void generareRaport2(String titlu, List<MenuItem> chestiigasite)
    {
        try {
            FileWriter report = new FileWriter("Raport2.txt");
            report.write(titlu + "\n\n");
            for(MenuItem m: chestiigasite)
            {
                report.write(m.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Raportul 3
     * @param titlu-titlul
     * @param clientigasiti -clientii gasite
     */
    public void generareRaport3(String titlu, List<Client> clientigasiti)
    {
        try {
            FileWriter report = new FileWriter("Raport3.txt");
            report.write(titlu + "\n\n");
            for(Client c: clientigasiti)
            {
                report.write(c.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Raportul 4
     * @param titlu-titlul
     * @param chestiigasite -produsele gasite
     */
    public void generareRaport4(String titlu, List<MenuItem> chestiigasite)
    {
        try {
            FileWriter report = new FileWriter("Raport4.txt");
            report.write(titlu+ "\n\n");
            for(MenuItem m: chestiigasite)
            {
                report.write(m.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}