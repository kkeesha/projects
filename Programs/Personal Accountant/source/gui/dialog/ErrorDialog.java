package personalaccountant.gui.dialog;

import javax.swing.JOptionPane;
import personalaccountant.gui.MainFrame;
import personalaccountant.settings.Text;

public class ErrorDialog {
    
    public static void show(MainFrame frame, String text) {
        JOptionPane.showMessageDialog(frame, Text.get(text), Text.get("ERROR"), JOptionPane.ERROR_MESSAGE);
    }
    
}
