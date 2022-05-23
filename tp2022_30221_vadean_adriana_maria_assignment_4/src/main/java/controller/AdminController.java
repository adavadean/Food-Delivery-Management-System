package controller;
import business.DeliveryService;
import presentation.AdminView;
import business.BaseProduct;
import business.MenuItem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
public class AdminController implements Controller{
    private static AdminController adminc= new AdminController();
    public static AdminController getInstance(){
        return adminc;
    }
    public AdminView adminv;
    boolean isModify = false;
    List<BaseProduct> cpr = new ArrayList<>();
    public AdminController(){
        adminv= AdminView.getInstance();
        adminv.bl2.addActionListener(new LogOut1Listener());
        adminv.bm.addActionListener(new ManageListener());
        adminv.bb.addActionListener(new BackListener());
        adminv.ba2.addActionListener(new Adaugare1Listener());
        adminv.bap1.addActionListener(new CreareProdus1Listener());
        adminv.br3.addActionListener(new Stergere1Listener());
        adminv.brp.addActionListener(new StergereProdus1Listener());
        adminv.bm4.addActionListener(new Modificare1Listener());
        adminv.bmp.addActionListener(new ModificareProdus1Listener());
        adminv.bi.addActionListener(new Import1Listener());
        adminv.bm2.addActionListener(new GenerareComp1Listener());
        adminv.bac.addActionListener(new AdaugareComp1Listener());
        adminv.bcreate.addActionListener(new CreareComp1Listener());
        adminv.br2.addActionListener(new RaportListener());
        adminv.bgr.addActionListener(new GenRaportListener());
        final CloseAction closeAction = new CloseAction(adminv.framea);
        adminv.framea.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeAction.confirm();
            }
        });
    }
    @Override
    public Object checkInput() {
        return null;
    }
    public class BackListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            adminv.panelm2.setVisible(false);
            adminv.panelcp1.setVisible(false);
            adminv.panelre.setVisible(false);
            adminv.panela3.setVisible(false);
            adminv.panelr3.setVisible(false);
            adminv.panelmo.setVisible(false);
            adminv.panelcp1.setVisible(false);
            adminv.panelre.setVisible(false);
            adminv.panelm.setVisible(true);
        }
    }

    public class LogOut1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            LogInController loginController = LogInController.getInstance();
            adminv.framea.setVisible(false);
            loginController.loginv.frame.setVisible(true);
        }
    }
    public class ManageListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            adminv.panelm.setVisible(false);
            adminv.panelm2.setVisible(true);
        }
    }
    public class Adaugare1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            adminv.panelm2.setVisible(false);
            adminv.panela3.setVisible(true);
        }
    }
    public class Stergere1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            adminv.panelm2.setVisible(false);
            adminv.panelr3.setVisible(true);
        }
    }
    public class Modificare1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            adminv.panelmo.setVisible(true);
            adminv.panelm2.setVisible(false);
        }
    }
    public class Import1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            DeliveryService.getInstance().importProduse();
            showProducts();
        }
    }
    public class AdaugareComp1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            cpr.add((BaseProduct) DeliveryService.getInstance().findById((Integer) adminv.c2.getSelectedItem()));
        }
    }
    public class CreareComp1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String confirmOrder = "Componentele:\n";
            for(MenuItem item: cpr){
                confirmOrder += item.toString() + "\n";
            }

            int confirmed = JOptionPane.showConfirmDialog(null, confirmOrder, "Confirmare produs nou", JOptionPane.YES_NO_OPTION);
            if (confirmed == JOptionPane.YES_OPTION) {
                DeliveryService.getInstance().createCompositeProduct(cpr, adminv.cpt.getText(), Double.parseDouble(adminv.cpp.getText()));
                JOptionPane.showMessageDialog(null, "Produs creat cu succes!");
                cpr.removeAll(cpr);
            }
        }
    }
    public class GenerareComp1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            populare();
            adminv.panelm.setVisible(false);
            adminv.panelcp1.setVisible(true);
        }
    }
    public class CreareProdus1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            isModify = false;
            try {
                checkInput();
                JOptionPane.showMessageDialog(null, "Produs creat cu succes!", "Produs creat", JOptionPane.OK_OPTION);

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public class ModificareProdus1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            isModify = true;
            try {
                checkInput();
                JOptionPane.showMessageDialog(null, "Produs modificat cu succes!", "Produs modificat", JOptionPane.OK_OPTION);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    public class StergereProdus1Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            DeliveryService.getInstance().deleteProduct(Integer.parseInt(adminv.stergt.getText()));
            showProducts();
            JOptionPane.showMessageDialog(null, "Produs sters cu succes!", "Produs sterg", JOptionPane.OK_OPTION);
        }
    }
    public class RaportListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            adminv.c3.addItem("Interval de timp");
            adminv.c3.addItem("De cate ori a fost comandat");
            adminv.c3.addItem("Clienti");
            adminv.c3.addItem("Data si de cate ori a fost comandat");

            adminv.panelre.setVisible(true);
            adminv.panelm.setVisible(false);
        }
    }
    public class GenRaportListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            String option = (String) adminv.c3.getSelectedItem();
            System.out.println(option);
            if(option.equals("De cate ori a fost comandat"))
            {
                int timesOrdered = Integer.parseInt(adminv.cate.getText());
                DeliveryService.getInstance().generarer2(timesOrdered);
            }
            else if(option.equals("Clienti"))
            {
                int timesOrdered = Integer.parseInt(adminv.cate.getText());
                double minPrice = Double.parseDouble(adminv.minp.getText());
                DeliveryService.getInstance().generarer3(timesOrdered, minPrice);
            }
            else if(option.equals("Interval de timp"))
            {
                LocalTime startTime = LocalTime.parse(adminv.start.getText());
                LocalTime endTime = LocalTime.parse(adminv.end.getText());
                DeliveryService.getInstance().generarer1(startTime, endTime);
            }
            else if(option.equals("Data si de cate ori a fost comandat"))
            {
                LocalDate date = LocalDate.parse(adminv.data.getText());
                int timesOrdered = Integer.parseInt(adminv.cate.getText());
                DeliveryService.getInstance().generarer4(date, timesOrdered);
            }
        }
    }
    public void populare()
    {
        List<MenuItem> allItems = DeliveryService.getInstance().fetchMenuItems();
        for(MenuItem item: allItems){
            adminv.c2.addItem(item.getId());;
        }

    }
    public void showProducts(){
        String productsColumns[] = {"Id", "Titlu", "Rating", "Pret", "Calorii", "Proteine", "Grasimi", "Sodiu"};
        DefaultTableModel model = new DefaultTableModel(productsColumns, 0);
        adminv.tp2.setModel(model);

        List<MenuItem> allItems = DeliveryService.getInstance().fetchMenuItems();
        for(MenuItem item: allItems){
            String[] currentProduct = {
                    Integer.toString(item.getId()), item.getTitle(), Double.toString(item.getPrice()),
                    Double.toString(item.getRating()), Integer.toString(item.getCalories()), Integer.toString(item.getProtein()),
                    Integer.toString(item.getFat()), Integer.toString(item.getSodium())
            };
            model.addRow(currentProduct);
        }
        adminv.tp2.setBounds(20, 20, 800, 200);
        JScrollPane scrollPane = new JScrollPane(adminv.tp2);
        adminv.paneltp2.add(scrollPane);
    }
}

