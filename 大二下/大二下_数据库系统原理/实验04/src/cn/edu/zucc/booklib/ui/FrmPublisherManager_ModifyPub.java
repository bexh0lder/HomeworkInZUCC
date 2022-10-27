package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.PublisherManager;
import cn.edu.zucc.booklib.model.BeanPublisher;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrmPublisherManager_ModifyPub extends JDialog implements ActionListener {
    private BeanPublisher pub = null;

    private final JPanel toolBar = new JPanel();
    private final JPanel workPane = new JPanel();
    private final JButton btnOk = new JButton("确定");
    private final JButton btnCancel = new JButton("取消");
    private final JLabel labelId = new JLabel("编号：");
    private final JLabel labelName = new JLabel("名称：");
    private final JLabel labelAddress = new JLabel("地址：");

    private final JTextField edtId = new JTextField(20);
    private final JTextField edtName = new JTextField(20);
    private final JTextField edtAddress = new JTextField(20);

    public FrmPublisherManager_ModifyPub(JDialog f, String s, boolean b, BeanPublisher p) {
        super(f, s, b);
        this.pub = p;
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelId);
        this.edtId.setEnabled(false);
        this.edtId.setText(p.getPubid());
        workPane.add(edtId);
        workPane.add(labelName);
        this.edtName.setText(p.getPublisherName());
        workPane.add(edtName);
        workPane.add(labelAddress);
        this.edtAddress.setText(p.getAddress());
        workPane.add(edtAddress);
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
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                FrmPublisherManager_ModifyPub.this.pub = null;
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel) {
            this.setVisible(false);
            this.pub = null;
            return;
        } else if (e.getSource() == this.btnOk) {
            pub.setPublisherName(this.edtName.getText());
            pub.setAddress(this.edtAddress.getText());
            try {
                (new PublisherManager()).modifyPublisher(pub);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public BeanPublisher getPub() {
        return pub;
    }

}
