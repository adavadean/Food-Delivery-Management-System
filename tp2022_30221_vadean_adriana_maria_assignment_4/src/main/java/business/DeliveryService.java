package business;
import data.Factura;
import data.ImportFisier;
import data.RaportGen;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.List;

import static java.util.stream.Collectors.toList;
public class DeliveryService extends Observable implements Serializable, IDeliveryServiceProcessing {
    private static DeliveryService singleInstance = new DeliveryService();
    public static DeliveryService getInstance() {
        return singleInstance;
    }
    private List<MenuItem> mitems = new ArrayList<>();
    private HashMap<Order, List<MenuItem>> comenzi = new HashMap<>();
    private List<Client> clienti = new ArrayList<>();
    Factura fac = new Factura();
    @Override
    public List<BaseProduct> importProduse()
    {
        String neededFile = "products.csv";
        assert neededFile.isEmpty() == false : "Eroare de a se lua fisierul ca parametru!";
        ImportFisier importer = new ImportFisier();
        List<BaseProduct> importedi= new ArrayList<>();
        try {
            importedi = importer.importfromfisier();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for(int i = 1; i <= importedi.size(); i++)
        {
            importedi.get(i-1).setId(i);
            mitems.add(importedi.get(i-1));
        }
        assert isWellFormed() : "Eroare!Nu se poate prelua products.csv";
        return importedi;
    }
    /**
     * Adaugare produs
     * @param a-este de tip MenuItem si contine toate campurile clasei respective
     * @return id-ul produsului
     */
    @Override
    public int addProdus(MenuItem a)
    {
        if(a != null)
        {
            a.setId(mitems.size() + 1);
            mitems.add(a);
        }

        return a.getId();
    }
    /**
     * Creare composite product
     * @param listaprodus-lista cu produse
     * @param titlu-titlul produsului
     * @param pret-pretul
     * @return id-ul prodului adaugat
     */
    @Override
    public int createCompositeProduct(List<BaseProduct> listaprodus, String titlu, double pret)
    {
        CompositeProduct produsnou= new CompositeProduct(titlu, pret);
        for(BaseProduct p: listaprodus)
        {
            produsnou.addItem(p);
        }

        produsnou.setId(mitems.size() + 1);
        mitems.add(produsnou);
        return produsnou.getId();
    }
    /**
     * Stergere produs
     * @param d-id
     * @return id-ul
     */
    @Override
    public int deleteProduct(int d)
    {
        mitems.remove(findById(d));
        return d;
    }
    /**
     * Modificare produs
     * @param idm-id
     * @param m-produs
     * @return id-ul
     */
    @Override
    public int modifyProduct(int idm, MenuItem m)
    {
        assert m!=null;
        int i = mitems.lastIndexOf(findById(idm));
        m.setId(idm);
        mitems.add(i, m);
        return idm;
    }
    @Override
    public void generateReports()
    {

    }
    public List<Order> generarer1( LocalTime start, LocalTime end)

    {
        String titlu = "RAPORT1:DE LA " + start.toString() + " PANA LA: " + end.toString();
        List<Order> comenzigasite = getOrders().stream().filter(t -> t.getTime().isAfter(start) ).filter(t -> t.getTime().isBefore(end)).collect(toList());
        RaportGen.getInstance().generareRaport1(titlu, comenzigasite);
        return comenzigasite;
    }
    public List<MenuItem> generarer2(int min_ori)
    {
        assert min_ori>=0;
        String titlu = "Raport2: CUMPARATE DE MAI MULTE ORI MAI MARI DECAT " + min_ori;
        List<MenuItem> produsegasite = mitems.stream().filter(m->m.getTimesOrdered() >= min_ori).collect(toList());
        RaportGen.getInstance().generareRaport2(titlu, produsegasite);
        return produsegasite;
    }
    public List<Client> generarer3(int min_comenzi, double minpret)
    {
        assert min_comenzi>=0 && minpret>=0;
        String titlu = "Raport3:DE CATE ORI S-A CUMPARAT MAI MARE DECAT" + min_comenzi + " CU O VALOARE MINIMA DE" + minpret;
        List<Client> fclienti = new ArrayList<>();
        comenzi.keySet().stream().filter(entry -> entry.getTotalPrice() >= minpret).forEach(e -> fclienti.add(findClientById(e.getClientId())));
        List<Client> clientigasiti = fclienti.stream().distinct().filter(e -> e.getNumberOfOrders() >= min_comenzi).collect(toList());
        RaportGen.getInstance().generareRaport3(titlu, clientigasiti);
        return clientigasiti;
    }
    public List<MenuItem> generarer4(LocalDate data, int nr)
    {
        assert nr>=0;
        String titlu = "RAPORT4:PRODUSE COMANDATE LA DATA SI ORA SI DE CATE ORI:";
        List<MenuItem> flista = new ArrayList<>();
        List<MenuItem> res = new ArrayList<>();
        comenzi.entrySet().stream().filter(entry -> entry.getKey().getDate().isAfter(data)).forEach(e -> e.getValue().stream().filter(k -> k.getTimesOrdered() >= nr).forEach(k -> flista.add(k)));
        for(MenuItem i : flista)
        {
            if(!res.contains(i))
            {
                res.add(i);
            }
        }

        RaportGen.getInstance().generareRaport4(titlu, res);
        return res;
    }
    /**
     * Punere comanda noua
     * @param client-clientul
     * @param produsecomandate-lista cu produsele comandate
     * @return id-ul comenzii
     */
    @Override
    public int comandanoua(Client client, List<MenuItem> produsecomandate)
    {
        Order order = new Order();
        order.setId(comenzi.size() + 1);
        client.addOrder(order);
        client.incTimesOrdered();
        order.placeOrder();
        comenzi.put(order, produsecomandate);
        int totalPrice = 0;
        for(MenuItem item: produsecomandate)
        {
            totalPrice += item.getPrice();
            item.incTimesOrdered();
        }

        order.setTotalPrice(totalPrice);
        fac.scrierefac(order, client, produsecomandate);
        return order.getId();
    }
    public Client findClientById(int id)
    {
        for(Client c: clienti)
        {
            if(c.getId() == id)
            {
                return c;
            }
        }
        return null;
    }

    /**
     * @invariant isWellFormed()
     */
    private boolean isWellFormed(){
        List<Integer> idList = new ArrayList<>();
        List<Integer> menuIds = new ArrayList<>();
        List<String> userIds = new ArrayList<>();

        for(Order order :comenzi.keySet())
        {
            if(!idList.contains(order.getId()))
            {
                idList.add(order.getId());
            }else{
                return false;
            }
        }

        for(MenuItem i : mitems)
        {
            if(!menuIds.contains(i.getId()))
            {
                menuIds.add(i.getId());
            }else{
                return false;
            }
        }

        for(Client c : clienti)
        {
            if(!userIds.contains(c.getUsername()))
            {
                userIds.add(c.getUsername());
            }else {
                return false;
            }
        }

        return true;
    }
    @Override
    public List<MenuItem> fetchMenuItems()
    {
        return mitems;
    }
    @Override
    public Set<Order> getOrders()
    {
        return comenzi.keySet();
    }
    public List<Client> getClients()
    {
        return clienti;
    }
    public void creareUser(User user)
    {
        if(user instanceof Client)
        {
            clienti.add((Client) user);
            user.setId(clienti.indexOf(user) + 1);
        }
    }
    public MenuItem findById(int id)
    {
        for(MenuItem item:mitems)
        {
            if(item.getId() == id)
            {
                return item;
            }
        }
        return null;
    }
}
