package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.SystemUserManager;
import cn.edu.zucc.booklib.model.BeanSystemUser;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmUserManager_AddUser extends JDialog implements ActionListener {
    private BeanSystemUser user = null;

    private final JPanel toolBar = new JPanel();
    private final JPanel workPane = new JPanel();
    private final JButton btnOk = new JButton("确定");
    private final JButton btnCancel = new JButton("取消");
    private final JLabel labelUserid = new JLabel("账号：");
    private final JLabel labelUsername = new JLabel("姓名：");
    private final JLabel labelUsertype = new JLabel("类别：");

    private final JTextField edtUserid = new JTextField(20);
    private final JTextField edtUsername = new JTextField(20);
    private final JComboBox cmbUsertype = new JComboBox(new String[]{"管理员", "借阅员"});

    public FrmUserManager_AddUser(JDialog f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelUserid);
        workPane.add(edtUserid);
        workPane.add(labelUsername);
        workPane.add(edtUsername);
        workPane.add(labelUsertype);
        workPane.add(cmbUsertype);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 180);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            return;
        } else if (e.getSource() == this.btnOk) {
            if (this.cmbUsertype.getSelectedIndex() < 0) {
                JOptionPane.showMessageDialog(null, "请选择账号类别", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String userid = this.edtUserid.getText();
            String username = this.edtUsername.getText();
            String usertype = this.cmbUsertype.getSelectedItem().toString();
            user = new BeanSystemUser();
            user.setUserid(userid);
            user.setUsername(username);
            user.setUsertype(usertype);
            try {
                (new SystemUserManager()).createUser(user);
                this.setVisible(false);
            } catch (BaseException e1) {
                this.user = null;
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public BeanSystemUser getUser() {
        return user;
    }

}
