package personalaccountant.gui.panel;

import personalaccountant.gui.MainButton;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.handler.ChartHandler;
import personalaccountant.settings.HandlerCode;
import personalaccountant.settings.Text;

public final class StatisticsTypePanel extends AbstractPanel {

    private final String title;

    public StatisticsTypePanel(MainFrame frame, String title) {
        super(frame);
        this.title = Text.get(title);
        init();
    }

    @Override
    protected void init() {
        MainButton type = new MainButton(title, new ChartHandler(frame), HandlerCode.TYPE);
        add(type);
    }
    
}
