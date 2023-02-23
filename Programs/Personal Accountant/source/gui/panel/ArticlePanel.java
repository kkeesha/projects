package personalaccountant.gui.panel;

import personalaccountant.gui.MainFrame;
import personalaccountant.gui.dialog.ArticleAddEditDialog;
import personalaccountant.gui.handler.FunctionsHandler;
import personalaccountant.gui.table.ArticleTableData;
import personalaccountant.gui.toolbar.FunctionsToolBar;
import personalaccountant.settings.Style;

public class ArticlePanel extends RightPanel {
    
    public ArticlePanel(MainFrame frame) {
        super(frame, new ArticleTableData(new FunctionsHandler(frame, new ArticleAddEditDialog(frame))),
                "ARTICLES", Style.ICON_PANEL_ARTICLES,
                new FunctionsToolBar(new FunctionsHandler(frame, new ArticleAddEditDialog(frame))));
    }
    
}
