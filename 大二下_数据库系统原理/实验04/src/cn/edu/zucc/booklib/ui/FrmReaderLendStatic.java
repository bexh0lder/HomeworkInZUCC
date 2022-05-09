package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.BookLendManager;
import cn.edu.zucc.booklib.model.StaticBeanReaderLend;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FrmReaderLendStatic extends JDialog {

    private final Object[] tblTitle = {"读者证号", "姓名", "借阅数量", "罚金总额"};
    private Object[][] tblData;
    DefaultTableModel tablmod = new DefaultTableModel();
    private final JTable dataTable = new JTable(tablmod);

    private void reloadTable() {
        try {
            List<StaticBeanReaderLend> records = (new BookLendManager()).staticReaderLend();
            tblData = new Object[records.size()][4];
            for (int i = 0; i < records.size(); i++) {
                tblData[i][0] = records.get(i).getReaderId();
                tblData[i][1] = records.get(i).getReaderName();
                tblData[i][2] = "" + records.get(i).getCount();
                tblData[i][3] = records.get(i).getPenalSum() + "";
            }

            tablmod.setDataVector(tblData, tblTitle);
            this.dataTable.validate();
            this.dataTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmReaderLendStatic(Frame f, String s, boolean b) {
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
