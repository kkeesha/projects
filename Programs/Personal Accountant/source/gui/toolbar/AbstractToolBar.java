package personalaccountant.gui.toolbar;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import personalaccountant.gui.MainButton;
import personalaccountant.gui.Refresh;
import personalaccountant.gui.handler.Handler;


abstract public class AbstractToolBar extends JPanel implements Refresh {

    private final Handler handler;
    
    public AbstractToolBar(EmptyBorder border, Handler handler) {
        super();
        this.handler = handler;
        setBorder(border);
    }
    
    abstract protected void init();
    
    protected MainButton addButton(String title, ImageIcon icon, String action, boolean topIcon) {
        MainButton button = new MainButton(title, icon, handler, action);
        if (topIcon) {
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
        }
        else {
            button.setHorizontalTextPosition(SwingConstants.RIGHT);
            button.setVerticalTextPosition(SwingConstants.CENTER);
        }
        add(button);
        return button;
    }
    
    @Override
    public void refresh() {
        removeAll();
        init();
    }
    
}
