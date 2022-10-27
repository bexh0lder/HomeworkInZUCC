package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.BookManager;
import cn.edu.zucc.booklib.model.BeanBook;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmBookManager extends JDialog implements ActionListener {
    private final JPanel toolBar = new JPanel();
    private final JButton btnAdd = new JButton("添加");
    private final JButton btnModify = new JButton("修改");
    private final JButton btnStop = new JButton("下架");
    private final JComboBox cmbState = new JComboBox(new String[]{"在库", "已借出", "已删除"});
    private final JTextField edtKeyword = new JTextField(10);
    private final JButton btnSearch = new JButton("查询");
    private final Object[] tblTitle = {"条码", "书名", "出版社", "价格", "状态"};
    private Object[][] tblData;
    List<BeanBook> books = null;
    DefaultTableModel tablmod = new DefaultTableModel();
    private final JTable dataTable = new JTable(tablmod);

    private void reloadTable() {
        try {
            books = (new BookManager()).searchBook(this.edtKeyword.getText(), this.cmbState.getSelectedItem().toString());
            tblData = new Object[books.size()][5];
            for (int i = 0; i < books.size(); i++) {
                tblData[i][0] = books.get(i).getBarcode();
                tblData[i][1] = books.get(i).getBookname();
                tblData[i][2] = books.get(i).getPubName();
                tblData[i][3] = books.get(i).getPrice() + "";
                tblData[i][4] = books.get(i).getState();
            }
            tablmod.setDataVector(tblData, tblTitle);
            this.dataTable.validate();
            this.dataTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmBookManager(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(btnAdd);
        toolBar.add(btnModify);
        toolBar.add(btnStop);
        toolBar.add(cmbState);
        toolBar.add(edtKeyword);
        toolBar.add(btnSearch);


        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        //提取现有数据
        this.reloadTable();
        this.getContentPane().add(new JScrollPane(this.dataTable), BorderLayout.CENTER);

        // 屏幕居中显示
        this.setSize(800, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.btnAdd.addActionListener(this);
        this.btnModify.addActionListener(this);
        this.btnStop.addActionListener(this);
        this.btnSearch.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                //System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == this.btnAdd) {
            FrmBookManager_AddBook dlg = new FrmBookManager_AddBook(this, "添加图书", true);
            dlg.setVisible(true);
            if (dlg.getBook() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnModify) {
            int i = this.dataTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择图书", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BeanBook book = this.books.get(i);

            FrmBookManager_ModifyBook dlg = new FrmBookManager_ModifyBook(this, "修改图书", true, book);
            dlg.setVisible(true);
            if (dlg.getBook() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnStop) {
            int i = this.dataTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择图书", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BeanBook book = this.books.get(i);
            if (!"在库".equals(book.getState())) {
                JOptionPane.showMessageDialog(null, "当前图书没有‘在库’", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "确定删除" + book.getBookname() + "吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                book.setState("已删除");
                try {
                    (new BookManager()).modifyBook(book);
                    this.reloadTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == this.btnSearch) {
            this.reloadTable();
        }

    }
}
