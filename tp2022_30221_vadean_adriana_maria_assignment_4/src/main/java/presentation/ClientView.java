package presentation;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class ClientView {
    private static ClientView clientView = new ClientView();
    public static ClientView getInstance(){
        return clientView;
    }
    public JFrame framec= new JFrame();
    public JPanel panelc = new JPanel();
    public JButton br = new JButton("Refresh");
    public JButton bl = new JButton("Log out");
    public JPanel panel2= new JPanel();
    public JPanel panela = new JPanel();
    JLabel addl = new JLabel("Adaugare comanda");
    JPanel panelid = new JPanel();
    JLabel idl = new JLabel("Id:");
    public JComboBox<Integer> c = new JComboBox<>();
    public JButton ba = new JButton("Adaugare produs");
    public JButton bf = new JButton("Pune comanda");
    public JPanel panels= new JPanel();
    JLabel searchl= new JLabel("Cautare produse");
    public JTextField key = new JTextField("Cautati dupa un cuvant cheie", 30);
    public JButton bs2 = new JButton("Cautare");
    public JPanel paneltp = new JPanel();
    public JTable tp = new JTable();
    public ClientView(){

        framec.setTitle("Client");
        framec.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        framec.setSize(1000, 500);
        searchl.setAlignmentX(0.5f);
        key.setColumns(30);
        panels.add(searchl);
        panels.add(key);
        panels.add(bs2);
        panels.setBorder(new EmptyBorder(40, 30, 40, 30));
        panels.setLayout(new BoxLayout(panels, BoxLayout.Y_AXIS));
        addl.setAlignmentX(0.5f);
        panelid.add(idl);
        panelid.add(c);
        panela.add(addl);
        panela.add(panelid);
        panela.add(ba);
        panela.add(bf);
        panela.setBorder(new EmptyBorder(40, 30, 40, 30));
        panela.setLayout(new BoxLayout(panela, BoxLayout.Y_AXIS));
        panel2.add(panels);
        panel2.add(panela);
        panel2.add(br);
        panel2.add(bl);
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panelc.add(panel2);
        paneltp.add(tp);
        panelc.add(paneltp);
        framec.add(panelc);
        framec.setVisible(true);
        br.setAlignmentX(0.5f);
        bl.setAlignmentX(0.5f);

    }
}
