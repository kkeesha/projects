package personalaccountant.gui.panel;

import java.awt.BorderLayout;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import personalaccountant.gui.MainFrame;
import personalaccountant.model.Currency;
import personalaccountant.model.Statistics;
import personalaccountant.saveload.SaveData;
import personalaccountant.settings.Format;
import personalaccountant.settings.Settings;
import personalaccountant.settings.Style;
import personalaccountant.settings.Text;

public final class LeftPanel extends AbstractPanel {

    public LeftPanel(MainFrame frame) {
        super(frame);
        init();
    }

    @Override
    protected void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(Style.BORDER_LEFT_PANEL);
        JLabel headerBC = new JLabel(Text.get("BALANCE_CURRENCIES"));
        headerBC.setFont(Style.FONT_LABEL_HEADER);
        headerBC.setIcon(Style.ICON_LEFT_PANEL_BALANCE_CURRENCIES);
        headerBC.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(headerBC);
        
        addBalanceCurrency();
        
        add(Box.createVerticalStrut(Style.PADDING_PANEL_BIG));
        
        JLabel headerB = new JLabel(Text.get("BALANCE"));
        headerB.setFont(Style.FONT_LABEL_HEADER);
        headerB.setIcon(Style.ICON_LEFT_PANEL_BALANCE);
        headerB.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        add(headerB);
        
        addBalance();
    }

    private void addBalanceCurrency() {
        SaveData.getInstance().getEnableCurrencies().stream().map((currency) -> {
            if(Settings.getLanguage().equals("ru")) {
                if(currency.getTitle().equals(Text.get("EN_RUBLE"))) currency.setTitle(Text.get("RU_RUBLE"));
                if(currency.getTitle().equals(Text.get("EN_EURO"))) currency.setTitle(Text.get("RU_EURO"));
                if(currency.getTitle().equals(Text.get("EN_SOM"))) currency.setTitle(Text.get("RU_SOM"));
                if(currency.getTitle().equals(Text.get("EN_DOLLAR"))) currency.setTitle(Text.get("RU_DOLLAR"));
                if(currency.getTitle().equals(Text.get("EN_FORINT"))) currency.setTitle(Text.get("RU_FORINT"));
            }
            else if(Settings.getLanguage().equals("en")) {
                if(currency.getTitle().equals(Text.get("RU_RUBLE"))) currency.setTitle(Text.get("EN_RUBLE"));
                if(currency.getTitle().equals(Text.get("RU_EURO"))) currency.setTitle(Text.get("EN_EURO"));
                if(currency.getTitle().equals(Text.get("RU_SOM"))) currency.setTitle(Text.get("EN_SOM"));
                if(currency.getTitle().equals(Text.get("RU_DOLLAR"))) currency.setTitle(Text.get("EN_DOLLAR"));
                if(currency.getTitle().equals(Text.get("RU_FORINT"))) currency.setTitle(Text.get("EN_FORINT"));
            }
            add(Box.createVerticalStrut(Style.PADDING_BALANCE));
            return currency;
        }).forEachOrdered((currency) -> {
            add(new PanelBalanceCurrency(currency, Statistics.getBalanceCurrency(currency)));
        });   
    }

    private void addBalance() {
        SaveData.getInstance().getEnableCurrencies().stream().map((currency) -> {
            if(Settings.getLanguage().equals("ru")) {
                if(currency.getTitle().equals(Text.get("EN_RUBLE"))) currency.setTitle(Text.get("RU_RUBLE"));
                if(currency.getTitle().equals(Text.get("EN_EURO"))) currency.setTitle(Text.get("RU_EURO"));
                if(currency.getTitle().equals(Text.get("EN_SOM"))) currency.setTitle(Text.get("RU_SOM"));
                if(currency.getTitle().equals(Text.get("EN_DOLLAR"))) currency.setTitle(Text.get("RU_DOLLAR"));
                if(currency.getTitle().equals(Text.get("EN_FORINT"))) currency.setTitle(Text.get("RU_FORINT"));
            }
            else if(Settings.getLanguage().equals("en")) {
                if(currency.getTitle().equals(Text.get("RU_RUBLE"))) currency.setTitle(Text.get("EN_RUBLE"));
                if(currency.getTitle().equals(Text.get("RU_EURO"))) currency.setTitle(Text.get("EN_EURO"));
                if(currency.getTitle().equals(Text.get("RU_SOM"))) currency.setTitle(Text.get("EN_SOM"));
                if(currency.getTitle().equals(Text.get("RU_DOLLAR"))) currency.setTitle(Text.get("EN_DOLLAR"));
                if(currency.getTitle().equals(Text.get("RU_FORINT"))) currency.setTitle(Text.get("EN_FORINT"));
            }
            return currency;
        }).map((currency) -> {
            add(Box.createVerticalStrut(Style.PADDING_BALANCE));
            return currency;
        }).forEachOrdered((currency) -> {
            add(new PanelBalanceCurrency(currency, Statistics.getBalance(currency)));
        });
    }
    
    private class PanelBalanceCurrency extends JPanel {
        
        public PanelBalanceCurrency(Currency currency, double amount) {
            super();
            setLayout(new BorderLayout());
            setBackground(Style.COLOR_LEFTPANEL_BALANCE);
            setBorder(Style.BORDER_PANEL);
            
            JLabel currencyLabel = new JLabel(currency.getTitle());
            JLabel amountLabel = new JLabel(Format.amount(amount, currency));
            
            currencyLabel.setFont(Style.FONT_LABEL_LEFT_PANEL_CURRENCY);
            amountLabel.setFont(Style.FONT_LABEL_LEFT_PANEL_AMOUNT);
            
            add(currencyLabel, BorderLayout.WEST);
            add(Box.createRigidArea(Style.DIMENSION_PADDING_BALANCE));
            add(amountLabel, BorderLayout.EAST);
        }
    }
    
}
