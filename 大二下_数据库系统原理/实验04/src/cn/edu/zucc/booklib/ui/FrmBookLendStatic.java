package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.BookLendManager;
import cn.edu.zucc.booklib.model.StaticBeanBookLend;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmBookLendStatic extends JDialog {

    private final Object[] tblTitle = {"条码", "图书名称", "借阅次数"};
    private Object[][] tblData;
    DefaultTableModel tablmod = new DefaultTableModel();
    private final JTable dataTable = new JTable(tablmod);

    private void reloadTable() {
        try {
            List<StaticBeanBookLend> records = (new BookLendManager()).staticBookLend();
            tblData = new Object[records.size()][3];
            for (int i = 0; i < records.size(); i++) {
                tblData[i][0] = records.get(i).getBarcode();
                tblData[i][1] = records.get(i).getBookname();
                tblData[i][2] = "" + records.get(i).getCount();
            }

            tablmod.setDataVector(tblData, tblTitle);
            this.dataTable.validate();
            this.dataTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmBookLendStatic(Frame f, String s, boolean b) {
        super(f, s, b);
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
    }
}
