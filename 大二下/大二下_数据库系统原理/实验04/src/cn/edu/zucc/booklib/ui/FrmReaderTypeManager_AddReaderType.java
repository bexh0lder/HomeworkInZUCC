package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.ReaderManager;
import cn.edu.zucc.booklib.model.BeanReaderType;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmReaderTypeManager_AddReaderType extends JDialog implements ActionListener {
    private BeanReaderType readertype = null;

    private final JPanel toolBar = new JPanel();
    private final JPanel workPane = new JPanel();
    private final JButton btnOk = new JButton("确定");
    private final JButton btnCancel = new JButton("取消");
    private final JLabel labelName = new JLabel("类别名称：");
    private final JLabel labelLimitted = new JLabel("借阅限制：");

    private final JTextField edtName = new JTextField(20);
    private final JTextField edtLimited = new JTextField(20);

    public FrmReaderTypeManager_AddReaderType(JDialog f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelLimitted);
        workPane.add(edtLimited);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(360, 140);
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

            String name = this.edtName.getText();
            int n = 0;
            try {
                n = Integer.parseInt(this.edtLimited.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, this.edtLimited.getText() + "不是一个合法的整数", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (n < 0 || n > 100) {
                JOptionPane.showMessageDialog(null, "借阅限制必须在0-100之间", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            this.readertype = new BeanReaderType();
            this.readertype.setLendBookLimitted(n);
            this.readertype.setReaderTypeName(name);
            try {
                (new ReaderManager()).createReaderType(this.readertype);
                this.setVisible(false);
            } catch (BaseException e1) {
                this.readertype = null;
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public BeanReaderType getReadertype() {
        return readertype;
    }

}
