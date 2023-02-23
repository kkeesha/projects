package personalaccountant.gui.panel;

import personalaccountant.gui.MainFrame;
import personalaccountant.gui.dialog.AccountAddEditDialog;
import personalaccountant.gui.handler.FunctionsHandler;
import personalaccountant.gui.table.AccountTableData;
import personalaccountant.gui.toolbar.FunctionsToolBar;
import personalaccountant.settings.Style;

public class AccountPanel extends RightPanel {
    
    public AccountPanel(MainFrame frame) {
        super(frame, new AccountTableData(new FunctionsHandler(frame, new AccountAddEditDialog(frame))),
                "ACCOUNTS", Style.ICON_PANEL_ACCOUNTS,
                new FunctionsToolBar(new FunctionsHandler(frame, new AccountAddEditDialog(frame))));
    }
    
}
