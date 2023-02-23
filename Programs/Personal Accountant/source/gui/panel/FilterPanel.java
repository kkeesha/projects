package personalaccountant.gui.panel;

import java.awt.Dimension;
import java.awt.FlowLayout;
import personalaccountant.gui.MainButton;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.handler.FilterHandler;
import personalaccountant.saveload.SaveData;
import personalaccountant.settings.Format;
import personalaccountant.settings.HandlerCode;
import personalaccountant.settings.Style;

public final class FilterPanel extends AbstractPanel {

    public FilterPanel(MainFrame frame) {
        super(frame);
        init();
    }
    
    @Override
    protected void init() {
        FlowLayout layout = new FlowLayout();
        layout.setVgap(0);
        setLayout(layout);
        MainButton left = new MainButton(Style.ICON_LEFT, new FilterHandler(frame), HandlerCode.LEFT);
        MainButton step = new MainButton(Format.getTitleFilter(SaveData.getInstance().getFilter()), new FilterHandler(frame), HandlerCode.STEP);
        MainButton right = new MainButton(Style.ICON_RIGHT, new FilterHandler(frame), HandlerCode.RIGHT);
        
        setBorder(Style.BORDER_FILTER_PANEL);
        
        step.setFont(Style.FONT_BUTTON_FILTER);
        step.setPreferredSize(new Dimension(Style.WIDTH_FILTER_BUTTON, left.getPreferredSize().height));
        
        add(left);
        add(step);
        add(right);
    }
    
}
