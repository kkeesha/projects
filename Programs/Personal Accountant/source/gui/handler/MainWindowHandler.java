package personalaccountant.gui.handler;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.dialog.ConfirmDialog;
import personalaccountant.saveload.SaveData;

public class MainWindowHandler extends WindowAdapter {
    
    @Override
    public void windowClosing(WindowEvent we) {
        if (SaveData.getInstance().isSaved()) System.exit(0);
        else {
            int result = ConfirmDialog.show((MainFrame) we.getWindow(), "CONFIRM_EXIT_TEXT", "CONFIRM_EXIT_TITLE");
            if (result == JOptionPane.YES_OPTION) System.exit(0);
        }
    }
}
