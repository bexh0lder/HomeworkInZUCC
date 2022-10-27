package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.model.BeanReaderType;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class FrmReaderTypeManager extends JDialog implements ActionListener {
    private final JPanel toolBar = new JPanel();
    private final JButton btnAdd = new JButton("添加读者类别");
    private final JButton btnModify = new JButton("修改读者类别信息");
    private final JButton btnDelete = new JButton("删除读者类别");
    private final Object[] tblTitle = {"类别ID", "类别名称", "借阅数量限制"};
    private Object[][] tblData;
    DefaultTableModel tablmod = new DefaultTableModel();
    private final JTable readerTypeTable = new JTable(tablmod);

    private void reloadTable() {
        try {
            List<BeanReaderType> types = (new ReaderManager()).loadAllReaderType();
            tblData = new Object[types.size()][3];
            for (int i = 0; i < types.size(); i++) {
                tblData[i][0] = types.get(i).getReaderTypeId() + "";
                tblData[i][1] = types.get(i).getReaderTypeName();
                tblData[i][2] = types.get(i).getLendBookLimitted() + "";
            }
            tablmod.setDataVector(tblData, tblTitle);
            this.readerTypeTable.validate();
            this.readerTypeTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmReaderTypeManager(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(btnAdd);
        toolBar.add(btnModify);
        toolBar.add(this.btnDelete);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        //提取现有数据
        this.reloadTable();
        this.getContentPane().add(new JScrollPane(this.readerTypeTable), BorderLayout.CENTER);

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
            FrmReaderTypeManager_AddReaderType dlg = new FrmReaderTypeManager_AddReaderType(this, "添加读者类别", true);
            dlg.setVisible(true);
            if (dlg.getReadertype() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnModify) {
            int i = this.readerTypeTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择读者类别", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int n = Integer.parseInt(this.tblData[i][0].toString());
            BeanReaderType readertype = new BeanReaderType();
            readertype.setReaderTypeId(n);
            readertype.setReaderTypeName(this.tblData[i][1].toString());
            readertype.setLendBookLimitted(Integer.parseInt(this.tblData[i][2].toString()));
            FrmReaderTypeManager_ModifyReaderType dlg = new FrmReaderTypeManager_ModifyReaderType(this, "添加读者类别", true, readertype);
            dlg.setVisible(true);
            if (dlg.getReadertype() != null) {//刷新表格
                this.reloadTable();
            }
        } else if (e.getSource() == this.btnDelete) {
            int i = this.readerTypeTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择读者类别", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "确定删除该类别吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                int n = Integer.parseInt(this.tblData[i][0].toString());
                try {
                    (new ReaderManager()).deleteReaderType(n);
                    this.reloadTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
}
