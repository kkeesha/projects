package personalaccountant.gui.panel;

import javax.swing.JPanel;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.dialog.TransactionAddEditDialog;
import personalaccountant.gui.handler.FunctionsHandler;
import personalaccountant.gui.table.TransactionTableData;
import personalaccountant.gui.toolbar.FunctionsToolBar;
import personalaccountant.settings.Style;


public class TransactionPanel extends RightPanel {
    
    public TransactionPanel(MainFrame frame) {
        super(frame, new TransactionTableData(new FunctionsHandler(frame, new TransactionAddEditDialog(frame))),
                "TRANSACTIONS", Style.ICON_PANEL_TRANSACTIONS,
                new JPanel[] {new FunctionsToolBar(new FunctionsHandler(frame, new TransactionAddEditDialog(frame))), new FilterPanel(frame)});
    }
    
}
