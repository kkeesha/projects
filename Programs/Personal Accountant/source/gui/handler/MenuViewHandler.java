package personalaccountant.gui.handler;

import java.awt.event.ActionEvent;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.panel.AccountPanel;
import personalaccountant.gui.panel.ArticlePanel;
import personalaccountant.gui.panel.CurrencyPanel;
import personalaccountant.gui.panel.OverviewPanel;
import personalaccountant.gui.panel.StatisticsPanel;
import personalaccountant.gui.panel.TransactionPanel;
import personalaccountant.gui.panel.TransferPanel;
import personalaccountant.settings.HandlerCode;

public class MenuViewHandler extends Handler {
    
    public MenuViewHandler(MainFrame frame) {
        super(frame);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.MENU_VIEW_OVERVIEW: {
                showOverviewPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_ACCOUNTS: {
                showAccountPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_ARTICLES: {
                showArticlePanel();
                break;
            }
            case HandlerCode.MENU_VIEW_TRANSACTIONS: {
                showTransactionPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_TRANSFERS: {
                showTransferPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_CURRENCIES: {
                showCurrencyPanel();
                break;
            }
            case HandlerCode.MENU_VIEW_STATISTICS: {
                showStatisticsPanel();
            }
            
        }
        super.actionPerformed(ae);
    }
    
    protected void showOverviewPanel() {
        frame.setRightPanel(new OverviewPanel(frame));
    }
    
    protected void showAccountPanel() {
        frame.setRightPanel(new AccountPanel(frame));
    }
    
    protected void showArticlePanel() {
        frame.setRightPanel(new ArticlePanel(frame));
    }
    
    protected void showTransactionPanel() {
        frame.setRightPanel(new TransactionPanel(frame));
    }
    
    protected void showTransferPanel() {
        frame.setRightPanel(new TransferPanel(frame));
    }
    
    protected void showCurrencyPanel() {
        frame.setRightPanel(new CurrencyPanel(frame));
    }
    
    protected void showStatisticsPanel() {
        frame.setRightPanel(new StatisticsPanel(frame));
    }
    
}
