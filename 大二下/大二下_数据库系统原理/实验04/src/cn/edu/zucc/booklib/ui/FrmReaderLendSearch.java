package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.BookLendManager;
import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.model.BeanBookLendRecord;
import cn.edu.zucc.booklib.model.BeanReader;
import cn.edu.zucc.booklib.util.BaseException;
import cn.edu.zucc.booklib.util.DbException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class FrmReaderLendSearch extends JDialog {
    private final JPanel toolBar = new JPanel();
    private final JLabel lableReader = new JLabel("读者：");
    private final JTextField edtReaderId = new JTextField(10);
    private final JLabel lableReaderName = new JLabel("");
    private final JLabel lableReaderState = new JLabel("");

    private final Object[] tblTitle = {"条码", "借阅时间", "归还时间", "罚金"};
    private Object[][] tblData;
    DefaultTableModel tablmod = new DefaultTableModel();
    private final JTable dataTable = new JTable(tablmod);
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private void reloadTable() {
        if ("".equals(this.lableReaderName.getText())) {
            tblData = new Object[0][4];
            tablmod.setDataVector(tblData, tblTitle);
            this.dataTable.validate();
            this.dataTable.repaint();
            return;
        }
        try {
            List<BeanBookLendRecord> records = (new BookLendManager()).loadReaderAllRecode(this.edtReaderId.getText());
            tblData = new Object[records.size()][4];
            for (int i = 0; i < records.size(); i++) {
                tblData[i][0] = records.get(i).getBookBarcode();
                tblData[i][1] = sdf.format(records.get(i).getLendDate());
                tblData[i][2] = records.get(i).getReturnDate() == null ? "" : sdf.format(records.get(i).getReturnDate());
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

    private void reloadReaderInfo() {
        String s = edtReaderId.getText().trim();
        BeanReader r = null;
        if (!"".equals(s))
            try {
                r = (new ReaderManager()).loadReader(s);
            } catch (DbException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        this.lableReaderName.setText("");
        this.lableReaderState.setText("");
        if (r != null) {
            this.lableReaderName.setText(r.getReaderName());
            this.lableReaderState.setText(r.getRemoveDate() != null ? "已注销" : (r.getStopDate() == null ? "" : "已挂失"));
        }
        this.reloadTable();
    }

    public FrmReaderLendSearch(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.lableReaderState.setForeground(Color.red);
        toolBar.add(lableReader);
        toolBar.add(edtReaderId);
        toolBar.add(lableReaderName);
        toolBar.add(lableReaderState);


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

        this.edtReaderId.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void changedUpdate(DocumentEvent e) {//这是更改操作的处理
                FrmReaderLendSearch.this.reloadReaderInfo();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                FrmReaderLendSearch.this.reloadReaderInfo();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // TODO Auto-generated method stub
                FrmReaderLendSearch.this.reloadReaderInfo();
            }
        });
    }
}
