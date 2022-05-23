package data;
import business.BaseProduct;
import business.MenuItem;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;
public class ImportFisier {
    /**
     * Se extrag produsele din fisierul products.csv
     * @return lista -lista cu produsele din fisier
     */
    public List<BaseProduct> importfromfisier() throws FileNotFoundException {
        List<BaseProduct> lista = new ArrayList<>();
        File toimport = new File("products.csv");
        InputStream inputStream = new FileInputStream(toimport);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        lista = br.lines().skip(1).map(
                line->{
                    String[] p = line.split(",");
                    String titlu = p[0];
                    double rating = Double.parseDouble(p[1]);
                    int calorii = Integer.parseInt(p[2]);
                    int proteine = Integer.parseInt(p[3]);
                    int grasimi = Integer.parseInt(p[4]);
                    int sodiu = Integer.parseInt(p[5]);
                    double pret = Double.parseDouble(p[6]);
                    BaseProduct item = new BaseProduct(titlu, rating, calorii, proteine, grasimi, sodiu, pret);
                    return item;
                }
        ).collect(Collectors.toList());
        lista = lista.stream().collect(collectingAndThen(toCollection(()->
                new TreeSet<>(Comparator.comparing(MenuItem::getTitle))), ArrayList::new));
        lista.sort(Comparator.comparing(MenuItem::getTitle));
        return lista;
    }
}
