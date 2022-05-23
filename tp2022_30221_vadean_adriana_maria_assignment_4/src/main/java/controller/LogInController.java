package controller;
import business.DeliveryService;
import presentation.LogInView;
import business.Admin;
import business.Client;
import business.User;
import javax.swing.*;
import java.awt.event.*;
import java.util.List;
public class LogInController implements Controller{
    private static LogInController loginc = new LogInController();
    public static LogInController getInstance(){
        return loginc;
    }
    public LogInView loginv;
    private DeliveryService d;
    public Client clientcurent= null;
    public LogInController(){

        this.loginv = LogInView.getInstance();
        this.d = DeliveryService.getInstance();
        loginv.b.addActionListener(new LoginListener());
        loginv.bs.addActionListener(new SignupListener());

        final CloseAction closeAction = new CloseAction(loginv.frame);
        loginv.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeAction.confirm();
            }
        });
    }
    @Override
    public User checkInput() throws Exception {
        String username = loginv.usert.getText();
        String password = loginv.passt.getText();
        if(username.equals("")){
            JOptionPane.showMessageDialog(null, "Username-ul lipseste!", "Eroare!", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Username field is empty");
        }
        if(password.equals("")){
            JOptionPane.showMessageDialog(null, "Parola lipseste!", "Eroare", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Password field is empty");
        }

        if(username.equals("Ada"))
        {
            if(password.equals("admin"))
            {
                return new Admin();
            }
        }

        List<Client> clients = d.getClients();
        for(Client client: clients){
            if(client.getUsername().equals(username))
            {
                clientcurent = client;
                break;
            }
        }
        if(clientcurent == null)
        {
            JOptionPane.showMessageDialog(null, "Acest user nu exista!", "Eroare!", JOptionPane.ERROR_MESSAGE);

        }

        if(!clientcurent.getPassword().equals(password))
        {
            JOptionPane.showMessageDialog(null, "Parola incorecta!", "Eroare!", JOptionPane.ERROR_MESSAGE);

        }

        return clientcurent;
    }
    class LoginListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            User usercurent= null;
            try {
                usercurent = checkInput();
                if(usercurent!= null)
                {
                    loginv.frame.setVisible(false);
                    if(usercurent instanceof Client)
                    {
                        ClientController.getInstance().clientv.framec.setVisible(true);
                    }else if(usercurent instanceof Admin)
                    {

                        AdminController.getInstance().adminv.framea.setVisible(true);
                    }
                }

            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    class SignupListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e)
        {
            SignUpController signupController = SignUpController.getInstance();
            loginv.frame.setVisible(false);
            signupController.signupv.frames1.setVisible(true);
        }
    }
}
