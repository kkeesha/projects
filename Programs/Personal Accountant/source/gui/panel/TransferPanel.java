package personalaccountant.gui.panel;

import javax.swing.JPanel;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.dialog.TransferAddEditDialog;
import personalaccountant.gui.handler.FunctionsHandler;
import personalaccountant.gui.table.TransferTableData;
import personalaccountant.gui.toolbar.FunctionsToolBar;
import personalaccountant.settings.Style;

public class TransferPanel extends RightPanel {
    
    public TransferPanel(MainFrame frame) {
        super(frame, new TransferTableData(new FunctionsHandler(frame, new TransferAddEditDialog(frame))),
                "TRANSFERS", Style.ICON_PANEL_TRANSFERS,
                new JPanel[] {new FunctionsToolBar(new FunctionsHandler(frame, new TransferAddEditDialog(frame))), new FilterPanel(frame)});
    }
    
}
