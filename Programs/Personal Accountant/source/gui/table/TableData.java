package personalaccountant.gui.table;

import java.awt.Point;
import javax.swing.ImageIcon;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import personalaccountant.gui.Refresh;
import personalaccountant.gui.handler.FunctionsHandler;
import personalaccountant.gui.menu.TablePopupMenu;
import personalaccountant.gui.table.model.MainTableModel;
import personalaccountant.gui.table.renderer.MainTableCellRenderer;
import personalaccountant.gui.table.renderer.TableHeaderIconRenderer;
import personalaccountant.settings.Style;
import personalaccountant.settings.Text;

abstract public class TableData extends JTable implements Refresh {
    
    private final FunctionsHandler handler;
    private final TablePopupMenu popup;
    private final String[] columns;
    private final ImageIcon[] icons;
    
    public TableData(MainTableModel model, FunctionsHandler handler, String[] columns, ImageIcon[] icons) {
        super(model);
        this.handler = handler;
        this.popup = new TablePopupMenu(handler);
        this.columns = columns;
        this.icons = icons;
        
        getTableHeader().setFont(Style.FONT_TABLE_HEADER);
        setFont(Style.FONT_TABLE);
        setRowHeight(getRowHeight() + Style.TABLE_ADD_ROW_HEIGHT);
        
        setAutoCreateRowSorter(true);
        setPreferredScrollableViewportSize(Style.DIMENSION_TABLE_SHOW_SIZE);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        addMouseListener(handler);
        addKeyListener(handler);
        
        for (int i = 0; i < columns.length; i++) {
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }
        
        MainTableCellRenderer renderer = new MainTableCellRenderer();
        setDefaultRenderer(String.class, renderer);
        setComponentPopupMenu(popup);
    }
    
    @Override
    public JPopupMenu getComponentPopupMenu() {
        Point p = getMousePosition();
        if (p != null) {
            int row = rowAtPoint(p);
            if (isRowSelected(row)) return super.getComponentPopupMenu();
            else return null;
        }
        return super.getComponentPopupMenu();
    }
    
    @Override
    public void refresh() {
        int selectedRow = getSelectedRow();
        ((MainTableModel) getModel()).refresh();
        for (int i = 0; i < columns.length; i++) {
            getColumn(Text.get(columns[i])).setHeaderRenderer(new TableHeaderIconRenderer(icons[i]));
        }
        if (selectedRow != -1 && selectedRow < getRowCount()) {
            setRowSelectionInterval(selectedRow, selectedRow);
            requestFocus();
        }
        init();
    }
    
    protected void init() {}

    public FunctionsHandler getFunctionHandler() {
        return handler;
    }
    
}
