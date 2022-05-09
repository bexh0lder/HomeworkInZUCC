package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.PublisherManager;
import cn.edu.zucc.booklib.model.BeanPublisher;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmPublisherManager extends JDialog implements ActionListener {
    private final JPanel toolBar = new JPanel();
    private final JButton btnAdd = new JButton("添加出版社");
    private final JButton btnModify = new JButton("修改出版社");
    private final JButton btnDelete = new JButton("删除出版社");
    private final Object[] tblTitle = {"出版社ID", "名称", "地址"};
    private Object[][] tblData;
    List<BeanPublisher> pubs;
    DefaultTableModel tablmod = new DefaultTableModel();
    private final JTable dataTable = new JTable(tablmod);

    private void reloadTable() {
        try {
            pubs = (new PublisherManager()).loadAllPublisher();
            tblData = new Object[pubs.size()][3];
            for (int i = 0; i < pubs.size(); i++) {
                tblData[i][0] = pubs.get(i).getPubid() + "";
                tblData[i][1] = pubs.get(i).getPublisherName();
                tblData[i][2] = pubs.get(i).getAddress();
            }
            tablmod.setDataVector(tblData, tblTitle);
            this.dataTable.validate();
            this.dataTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmPublisherManager(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(btnAdd);
        toolBar.add(btnModify);
        toolBar.add(this.btnDelete);
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
        this.btnDelete.addActionListener(this);
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
            FrmPublisherManager_AddPub dlg = new FrmPublisherManager_AddPub(this, "添加出版社", true);
            dlg.setVisible(true);
            if (dlg.getPub() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnModify) {
            int i = this.dataTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择出版社", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BeanPublisher p = this.pubs.get(i);
            FrmPublisherManager_ModifyPub dlg = new FrmPublisherManager_ModifyPub(this, "添加读者类别", true, p);
            dlg.setVisible(true);
            if (dlg.getPub() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnDelete) {
            int i = this.dataTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择出版社", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            BeanPublisher p = this.pubs.get(i);
            if (JOptionPane.showConfirmDialog(this, "确定删除" + p.getPublisherName() + "吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                try {
                    (new PublisherManager()).deletePublisher(p.getPubid());
                    this.reloadTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
}
