package personalaccountant.gui.handler;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import personalaccountant.gui.MainFrame;
import personalaccountant.gui.dialog.AddEditDialog;
import personalaccountant.gui.dialog.ConfirmDialog;
import personalaccountant.gui.table.TableData;
import personalaccountant.gui.table.model.MainTableModel;
import personalaccountant.model.Common;
import personalaccountant.saveload.SaveData;
import personalaccountant.settings.HandlerCode;

public class FunctionsHandler extends Handler implements MouseListener, KeyListener {
    
    private final AddEditDialog dialog;
    
    public FunctionsHandler(MainFrame frame, AddEditDialog dialog) {
        super(frame);
        this.dialog = dialog;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case HandlerCode.ADD:
                add();
                break;
            case HandlerCode.EDIT:
                edit();
                break;
            case HandlerCode.DELETE:
                delete();
        }
        super.actionPerformed(ae);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() instanceof TableData) {
            if (me.getClickCount() == 2 && me.getButton() == MouseEvent.BUTTON1)
                showAddEditDialog(getSelectedCommon());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        if (me.getSource() instanceof TableData) {
            if (me.getButton() == MouseEvent.BUTTON3) {
                TableData td = frame.getRightPanel().getTableData();
                int row = td.rowAtPoint(me.getPoint());
                td.setRowSelectionInterval(row, row);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}

    @Override
    public void keyTyped(KeyEvent ke) {}

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_DELETE) delete();
        frame.refresh();
    }

    public void add() {
        showAddEditDialog(null);
    }

    public void edit() {
        showAddEditDialog(getSelectedCommon());
    }

    public void delete() {
        Common c = getSelectedCommon();
        if (c != null) {
            int result = ConfirmDialog.show(frame, "CONFIRM_DELETE_TEXT", "CONFIRM_DELETE_TITLE");
            if (result == JOptionPane.YES_OPTION) {
                SaveData.getInstance().remove(c);
            }
        }
    }
    
    private Common getSelectedCommon() {
        TableData td = frame.getRightPanel().getTableData();
        Common c = ((MainTableModel) td.getModel()).getCommonByRow(td.getSelectedRow());
        return c;
    }
    
    private void showAddEditDialog(Common c) {
        dialog.setCommon(c);
        dialog.showDialog();
    }
    
}
