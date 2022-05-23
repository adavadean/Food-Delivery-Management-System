package controller;

import business.DeliveryService;
import presentation.ClientView;
import business.MenuItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import static java.util.stream.Collectors.toList;
public class ClientController implements Controller{
    private static ClientController clientc = new ClientController();
    public static ClientController getInstance(){
        return clientc;
    }
    public ClientView clientv;
    public List<MenuItem> comandanoua = new ArrayList<>();
    public ClientController(){
        clientv = ClientView.getInstance();
        clientv.br.addActionListener(new RefreshListener());
        clientv.ba.addActionListener(new AdaugareListener());
        clientv.bf.addActionListener(new PuneComandaListener());
        clientv.bs2.addActionListener(new SearchListener());
        clientv.bl.addActionListener(new LogOutListener());
        final CloseAction closeAction = new CloseAction(clientv.framec);
        clientv.framec.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeAction.confirm();
            }
        });
    }
    public class RefreshListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            populateComboBox();
            populateTable();
        }
    }
    public class AdaugareListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            comandanoua.add(DeliveryService.getInstance().findById((Integer) clientv.c.getSelectedItem()));

        }
    }
    public class PuneComandaListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String confirmOrder = "Comanda ta:\n";
            for(MenuItem item: comandanoua)
            {
                confirmOrder += item.toString() + "\n";
            }

            int confirmed = JOptionPane.showConfirmDialog(null, confirmOrder, "Confirmare comanda", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                DeliveryService.getInstance().comandanoua(LogInController.getInstance().clientcurent, comandanoua);
                JOptionPane.showMessageDialog(null, "Comanda s-a plasat cu succes!");
                comandanoua.removeAll(comandanoua);
            }
        }
    }
    public class SearchListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            try {
                checkInput();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public class LogOutListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            LogInController loginController = LogInController.getInstance();
            clientv.framec.setVisible(false);
            loginController.loginv.frame.setVisible(true);
        }
    }
    @Override
    public Object checkInput() throws Exception {
        String keyword = clientv.key.getText();
        if(keyword.equals(" ")){
            JOptionPane.showMessageDialog(null, "Nu exista astfel de parametru de cautare!", "Eroare!", JOptionPane.ERROR_MESSAGE);
            throw new Exception("No search parameter");

        }
        searchByKeyword(keyword);
        return null;
    }

    public void searchByKeyword(String keyword)
    {
        List<MenuItem> searchedItems = DeliveryService.getInstance().fetchMenuItems().stream().filter(t-> t.getTitle().toLowerCase().contains(keyword.toLowerCase())).collect(toList());
        String productsColumns[] = {"Id", "Titlu", "Rating", "Pret", "Calorii", "Proteine", "Grasimi", "Sodiu"};
        DefaultTableModel model = new DefaultTableModel(productsColumns, 0);
        clientv.tp.setModel(model);
        for(MenuItem item: searchedItems)
        {
            String[] currentProduct = {
                    Integer.toString(item.getId()), item.getTitle(), Double.toString(item.getPrice()),
                    Double.toString(item.getRating()), Integer.toString(item.getCalories()), Integer.toString(item.getProtein()),
                    Integer.toString(item.getFat()), Integer.toString(item.getSodium())
            };
            model.addRow(currentProduct);
        }
        clientv.tp.setBounds(20, 20, 800, 200);
        JScrollPane scrollPane = new JScrollPane(clientv.tp);
        clientv.paneltp.add(scrollPane);
    }

    public void populateComboBox(){
        DeliveryService.getInstance().importProduse();
        List<MenuItem> products = DeliveryService.getInstance().fetchMenuItems();
        for(MenuItem item: products){
            clientv.c.addItem(item.getId());
        }
    }
    public void populateTable(){
        DeliveryService.getInstance().importProduse();
        String productsColumns[] = {"Id", "Titlu", "Rating", "Pret", "Calorii", "Proteine", "Grasimi", "Sodiu"};
        DefaultTableModel model = new DefaultTableModel(productsColumns, 0);
        clientv.tp.setModel(model);

        List<MenuItem> allItems = DeliveryService.getInstance().fetchMenuItems();
        for(MenuItem item: allItems){
            String[] currentProduct = {
                    Integer.toString(item.getId()), item.getTitle(), Double.toString(item.getPrice()),
                    Double.toString(item.getRating()), Integer.toString(item.getCalories()), Integer.toString(item.getProtein()),
                    Integer.toString(item.getFat()), Integer.toString(item.getSodium())
            };
            model.addRow(currentProduct);
        }
        clientv.tp.setBounds(20, 20, 800, 200);
        JScrollPane scrollPane = new JScrollPane(clientv.tp);
        clientv.paneltp.add(scrollPane);
    }
}
