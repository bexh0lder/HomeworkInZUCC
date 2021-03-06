package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.SystemUserManager;
import cn.edu.zucc.booklib.model.BeanSystemUser;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmLogin extends JDialog implements ActionListener {
    private final JPanel toolBar = new JPanel();
    private final JPanel workPane = new JPanel();
    private final JButton btnLogin = new JButton("登陆");
    private final JButton btnCancel = new JButton("退出");
    private final JLabel labelUser = new JLabel("用户：");
    private final JLabel labelPwd = new JLabel("密码：");
    private final JTextField edtUserId = new JTextField(20);
    private final JPasswordField edtPwd = new JPasswordField(20);

    public FrmLogin(Frame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnLogin);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelUser);
        workPane.add(edtUserId);
        workPane.add(labelPwd);
        workPane.add(edtPwd);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(300, 140);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnLogin) {
            SystemUserManager sum = new SystemUserManager();
            String userid = this.edtUserId.getText();
            String pwd = new String(this.edtPwd.getPassword());
            try {
                BeanSystemUser user = sum.loadUser(userid);
                if (pwd.equals(user.getPwd())) {
                    SystemUserManager.currentUser = user;
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "密码错误", "错误提示", JOptionPane.ERROR_MESSAGE);
                }
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误提示", JOptionPane.ERROR_MESSAGE);
            }


        } else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        }
    }

}
