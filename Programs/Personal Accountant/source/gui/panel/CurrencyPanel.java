package personalaccountant.gui.panel;

import personalaccountant.gui.MainFrame;
import personalaccountant.gui.dialog.CurrencyAddEditDialog;
import personalaccountant.gui.handler.FunctionsHandler;
import personalaccountant.gui.table.CurrencyTableData;
import personalaccountant.gui.toolbar.FunctionsToolBar;
import personalaccountant.settings.Style;

public class CurrencyPanel extends RightPanel {
    
    public CurrencyPanel(MainFrame frame) {
        super(frame, new CurrencyTableData(new FunctionsHandler(frame, new CurrencyAddEditDialog(frame))),
                "CURRENCIES", Style.ICON_PANEL_CURRENCIES,
                new FunctionsToolBar(new FunctionsHandler(frame, new CurrencyAddEditDialog(frame))));
    }
    
}
