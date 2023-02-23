package personalaccountant.gui.table;

import javax.swing.ImageIcon;
import personalaccountant.gui.handler.FunctionsHandler;
import personalaccountant.gui.table.model.ArticleTableModel;
import personalaccountant.settings.Style;

public class ArticleTableData extends TableData {
    
    private static final String[] columns = new String[]{"TITLE"};
    private static final ImageIcon[] icons = new ImageIcon[]{Style.ICON_TITLE};
    
    public ArticleTableData(FunctionsHandler handler) {
        super(new ArticleTableModel(columns), handler, columns, icons);
    }
    
}
