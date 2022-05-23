package presentation;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
public class LogInView {
    private static LogInView loginView = new LogInView();
    public static LogInView getInstance(){
        return loginView;
    }
    public JFrame frame = new JFrame();
    public JPanel panel = new JPanel();
    public JLabel titlul = new JLabel("Food Delivery Management System");
    public JPanel panellogin= new JPanel();
    public JPanel panelu = new JPanel();
    public JPanel panelp = new JPanel();
    public JLabel userl= new JLabel("username:");
    public JLabel passl= new JLabel("password:");
    public JTextField usert = new JTextField(10);
    public JTextField passt = new JTextField(10);
    public JButton b= new JButton("Log in");
    public JPanel panels= new JPanel();
    public JButton bs = new JButton("Sign up");

    public LogInView(){

        frame.setSize(700, 300);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        usert.setColumns(30);
        passt.setColumns(30);
        panelu.add(userl);
        panelu.add(usert);
        panelp.add(passl);
        panelp.add(passt);
        b.setAlignmentX(0.5f);
        panellogin.setBorder(new EmptyBorder(40, 0, 20, 0));
        panellogin.add(panelu);
        panellogin.add(panelp);
        panellogin.add(b);
        panellogin.setLayout(new BoxLayout(panellogin, BoxLayout.Y_AXIS));
        bs.setAlignmentX(0.5f);
        panels.setBorder(new EmptyBorder(20, 0, 20, 0));
        panels.add(bs);
        panels.setLayout(new BoxLayout(panels, BoxLayout.Y_AXIS));
        panel.add(titlul);
        panel.add(panellogin);
        panel.add(panels);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.add(panel);
        frame.setVisible(true);
    }
}
