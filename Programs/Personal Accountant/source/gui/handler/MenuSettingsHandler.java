package personalaccountant.gui.handler;

import java.awt.event.ActionEvent;
import personalaccountant.gui.MainFrame;
import personalaccountant.settings.HandlerCode;
import personalaccountant.settings.Settings;
import personalaccountant.settings.Text;

public class MenuSettingsHandler extends Handler {
    
    public MenuSettingsHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.MENU_SETTINGS_LANGUAGE_RUSSIAN: {
                Settings.setLanguage("ru");
                break;
            }
            case HandlerCode.MENU_SETTINGS_LANGUAGE_ENGLISH: {
                Settings.setLanguage("en");
            }
        }
        Text.init();
        frame.getMenu().refresh();
        super.actionPerformed(ae);
    }
    
}
