package cn.edu.zucc.booklib.ui;

import cn.edu.zucc.booklib.control.BookManager;
import cn.edu.zucc.booklib.control.PublisherManager;
import cn.edu.zucc.booklib.model.BeanBook;
import cn.edu.zucc.booklib.model.BeanPublisher;
import cn.edu.zucc.booklib.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FrmBookManager_AddBook extends JDialog implements ActionListener {
    private BeanBook book = null;

    private final JPanel toolBar = new JPanel();
    private final JPanel workPane = new JPanel();
    private final JButton btnOk = new JButton("确定");
    private final JButton btnCancel = new JButton("取消");
    private final JLabel labelId = new JLabel("图书条码：");
    private final JLabel labelName = new JLabel("图书名称：");
    private final JLabel labelPrice = new JLabel("图书单价：");
    private final JLabel labelPub = new JLabel("出版社：");

    private final JTextField edtId = new JTextField(20);
    private final JTextField edtName = new JTextField(20);
    private final JTextField edtPrice = new JTextField(20);
    private final Map<String, BeanPublisher> pubMap_name = new HashMap<String, BeanPublisher>();
    private final Map<String, BeanPublisher> pubMap_id = new HashMap<String, BeanPublisher>();


    private JComboBox cmbPub = null;

    public FrmBookManager_AddBook(JDialog f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelId);
        workPane.add(edtId);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(labelPrice);
        this.edtPrice.setText("0");
        workPane.add(edtPrice);
        workPane.add(labelPub);
        //提取读出版社信息
        try {
            List<BeanPublisher> pubs = (new PublisherManager()).loadAllPublisher();
            String[] strpubs = new String[pubs.size() + 1];
            strpubs[0] = "";
            for (int i = 0; i < pubs.size(); i++) {
                strpubs[i + 1] = pubs.get(i).getPublisherName();
                this.pubMap_id.put(pubs.get(i).getPubid(), pubs.get(i));
                this.pubMap_name.put(pubs.get(i).getPublisherName(), pubs.get(i));
            }
            this.cmbPub = new JComboBox(strpubs);
            workPane.add(this.cmbPub);

        } catch (BaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(360, 180);
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
            String id = this.edtId.getText();
            String name = this.edtName.getText();
            double price = 0;
            try {
                price = Double.parseDouble(this.edtPrice.getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "单价输入不正确", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }

            BeanBook b = new BeanBook();
            b.setBarcode(id);
            b.setBookname(name);
            b.setPrice(price);
            if (this.cmbPub.getSelectedIndex() >= 0) {
                BeanPublisher p = this.pubMap_name.get(this.cmbPub.getSelectedItem().toString());
                if (p != null) b.setPubid(p.getPubid());
            }


            try {
                (new BookManager()).createBook(b);
                this.book = b;
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    public BeanBook getBook() {
        return book;
    }

}
