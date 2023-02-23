package personalaccountant.gui.toolbar;

import personalaccountant.gui.EnableEditDelete;
import personalaccountant.gui.MainButton;
import personalaccountant.gui.handler.Handler;
import personalaccountant.settings.HandlerCode;
import personalaccountant.settings.Style;
import personalaccountant.settings.Text;


public final class FunctionsToolBar extends AbstractToolBar implements EnableEditDelete {
    
    private MainButton editButton;
    private MainButton deleteButton;
    
    public FunctionsToolBar(Handler handler) {
        super(Style.BORDER_FUNCTIONS_TOOLBAR, handler);
        init();
    }
    
    @Override
    protected void init() {
        addButton(Text.get("ADD"), Style.ICON_ADD, HandlerCode.ADD, false);
        editButton = addButton(Text.get("EDIT"), Style.ICON_EDIT, HandlerCode.EDIT, false);
        deleteButton = addButton(Text.get("DELETE"), Style.ICON_DELETE, HandlerCode.DELETE, false);
    }

    @Override
    public void setEnableEditDelete(boolean enable) {
        editButton.setEnabled(enable);
        deleteButton.setEnabled(enable);
    }
    
}
