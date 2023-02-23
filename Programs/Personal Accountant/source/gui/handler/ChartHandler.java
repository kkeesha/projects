package personalaccountant.gui.handler;

import java.awt.event.ActionEvent;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.panel.StatisticsPanel;
import personalaccountant.settings.HandlerCode;


public class ChartHandler extends Handler {
    
    public ChartHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.TYPE: {
                ((StatisticsPanel) frame.getRightPanel()).nextType();
            }
        }
        super.actionPerformed(ae);
    }
    
}
