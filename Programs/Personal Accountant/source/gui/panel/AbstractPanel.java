package personalaccountant.gui.panel;

import javax.swing.JPanel;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.Refresh;

abstract public class AbstractPanel extends JPanel implements Refresh {

    protected final MainFrame frame;
    
    public AbstractPanel(MainFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void refresh() {
        removeAll();
        init();
    }

    abstract protected void init();
    
}
