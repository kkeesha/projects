package personalaccountant.gui.handler;

import java.awt.event.ActionEvent;
import personalaccountant.gui.MainFrame;
import personalaccountant.saveload.SaveData;
import personalaccountant.settings.HandlerCode;

public class FilterHandler extends Handler {
    
    public FilterHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.LEFT: {
                SaveData.getInstance().getFilter().prev();
                break;
            }
            case HandlerCode.RIGHT: {
                SaveData.getInstance().getFilter().next();
                break;
            }
            case HandlerCode.STEP: {
                SaveData.getInstance().getFilter().nextPeriod();
            }
        }
        super.actionPerformed(ae);
    }
}
