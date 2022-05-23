package presentation;
import javax.swing.*;
public class SignUpView {
    private static SignUpView signupView = new SignUpView();
    public static SignUpView getInstance(){
        return signupView;
    }
    public JLabel titlul1 = new JLabel("Inregistrare");
    public JPanel panelu1 = new JPanel();
    public JLabel userl1 = new JLabel("username:");
    public JTextField usert1 = new JTextField(10);
    public JPanel panelp1 = new JPanel();
    public JLabel passl1 = new JLabel("password:");
    public JTextField passt1 = new JTextField(10);
    public JButton bs1= new JButton("Sign up");
    public JPanel panel1= new JPanel();
    public JFrame frames1 = new JFrame();
    public SignUpView(){
        frames1.setSize(700, 300);
        frames1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        usert1.setColumns(30);
        panelu1.add(userl1);
        panelu1.add(usert1);
        passt1.setColumns(30);
        panelp1.add(passl1);
        panelp1.add(passt1);
        panel1.add(titlul1);
        panel1.add(panelu1);
        panel1.add(panelp1);
        panel1.add(bs1);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        frames1.add(panel1);
        frames1.setVisible(false);
    }
}
