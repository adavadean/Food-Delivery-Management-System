package controller;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
public interface Controller {
    Object checkInput() throws Exception;
    class CloseAction extends AbstractAction {
        private JFrame mainFrame;
        public CloseAction(JFrame mainFrame)
        {
            super("Exit");
            putValue(MNEMONIC_KEY, KeyEvent.VK_X);
            this.mainFrame = mainFrame;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            confirm();
        }
        public void confirm()
            {
            int c = JOptionPane.showConfirmDialog(mainFrame, "Renunti?", "Confirma renuntare", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.YES_OPTION)
            {

                System.exit(0);
            }
        }
    }

}
