package personalaccountant.gui.panel;

import personalaccountant.gui.MainFrame;
import personalaccountant.gui.dialog.TransactionAddEditDialog;
import personalaccountant.gui.handler.FunctionsHandler;
import personalaccountant.gui.table.TransactionTableData;
import personalaccountant.settings.Settings;
import personalaccountant.settings.Style;

public class OverviewPanel extends RightPanel {
    
    public OverviewPanel(MainFrame frame) {
        super(frame,
                new TransactionTableData(new FunctionsHandler(frame, new TransactionAddEditDialog(frame)), Settings.COUNT_OVERVIEW_ROWS),
                "LAST_TRANSACTIONS", Style.ICON_PANEL_OVERVIEW);
    }
    
}
