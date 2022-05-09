package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.SystemUserManager;
import cn.edu.zucc.booklib.model.BeanSystemUser;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class FrmUserManager extends JDialog implements ActionListener {
    private final JPanel toolBar = new JPanel();
    private final JButton btnAdd = new JButton("添加用户");
    private final JButton btnResetPwd = new JButton("重置密码");
    private final JButton btnDelete = new JButton("删除用户");
    private final Object[] tblTitle = {"账号", "姓名", "类别"};
    private Object[][] tblData;
    DefaultTableModel tablmod = new DefaultTableModel();
    private final JTable userTable = new JTable(tablmod);

    private void reloadUserTable() {
        try {
            List<BeanSystemUser> users = (new SystemUserManager()).loadAllUsers(false);
            tblData = new Object[users.size()][3];
            for (int i = 0; i < users.size(); i++) {
                tblData[i][0] = users.get(i).getUserid();
                tblData[i][1] = users.get(i).getUsername();
                tblData[i][2] = users.get(i).getUsertype();
            }
            tablmod.setDataVector(tblData, tblTitle);
            this.userTable.validate();
            this.userTable.repaint();
        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public FrmUserManager(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        toolBar.add(btnAdd);
        toolBar.add(btnResetPwd);
        toolBar.add(this.btnDelete);
        this.getContentPane().add(toolBar, BorderLayout.NORTH);
        //提取现有数据
        this.reloadUserTable();
        this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);

        // 屏幕居中显示
        this.setSize(800, 600);
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        this.btnAdd.addActionListener(this);
        this.btnResetPwd.addActionListener(this);
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
            FrmUserManager_AddUser dlg = new FrmUserManager_AddUser(this, "添加账号", true);
            dlg.setVisible(true);
            if (dlg.getUser() != null) {//刷新表格
                this.reloadUserTable();
            }
        } else if (e.getSource() == this.btnResetPwd) {
            int i = this.userTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择账号", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "确定重置密码吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String userid = this.tblData[i][0].toString();
                try {
                    (new SystemUserManager()).resetUserPwd(userid);
                    JOptionPane.showMessageDialog(null, "密码重置完成", "提示", JOptionPane.INFORMATION_MESSAGE);
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        } else if (e.getSource() == this.btnDelete) {
            int i = this.userTable.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择账号", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (JOptionPane.showConfirmDialog(this, "确定删除账号吗？", "确认", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                String userid = this.tblData[i][0].toString();
                try {
                    (new SystemUserManager()).deleteUser(userid);
                    this.reloadUserTable();
                } catch (BaseException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        }
    }
}
