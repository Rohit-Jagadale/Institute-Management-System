package InstituteProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Course extends JFrame implements ActionListener,KeyListener {
    JLabel lblcid, lblcname, lblduration, lblInstallment, lbloneTime;
    JTextField txtcid, txtcname, txtduration, txtInstallment, txtoneTime;
    JButton btnnew, btnsave, btnupdate, btndel, btndp;

    JLabel vlblcid;
    DefaultTableModel model;
    JTable tbl;
    JMenuBar mb;
    JMenuItem course, trainer, batch, enquiry, admission,receipt;
    JScrollPane scroll;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int cid, installment, onetime;
    String cname, duration, sql = "";
    Color c1 = new Color(203, 255, 250);

    Course() {
        mb = new JMenuBar();

        course = new JMenuItem("Course");
        trainer = new JMenuItem("Trainer");
        batch = new JMenuItem("Batch");
        enquiry = new JMenuItem("Enquiry");
        admission = new JMenuItem("Admission");
        receipt = new JMenuItem("Receipt");

        mb.add(course);
        mb.add(trainer);
        mb.add(batch);
        mb.add(enquiry);
        mb.add(admission);
        mb.add(receipt);

        course.addActionListener(this);
        trainer.addActionListener(this);
        batch.addActionListener(this);
        enquiry.addActionListener(this);
        admission.addActionListener(this);
        receipt.addActionListener(this);

        setJMenuBar(mb);

        lblcid = new JLabel("Course Id");
        lblcid.setBounds(100, 50, 200, 30);
        add(lblcid);

        txtcid = new JTextField();
        txtcid.setBounds(250, 50, 230, 30);
        add(txtcid);

        lblcname = new JLabel("Course Name");
        lblcname.setBounds(100, 100, 200, 30);
        add(lblcname);

        txtcname = new JTextField();
        txtcname.setBounds(250, 100, 230, 30);
        add(txtcname);

        lblduration = new JLabel("Duration");
        lblduration.setBounds(100, 150, 200, 30);
        add(lblduration);

        txtduration = new JTextField();
        txtduration.setBounds(250, 150, 230, 30);
        add(txtduration);

        lblInstallment = new JLabel("Installment");
        lblInstallment.setBounds(100, 200, 200, 30);
        add(lblInstallment);

        txtInstallment = new JTextField();
        txtInstallment.setBounds(250, 200, 230, 30);
        add(txtInstallment);

        lbloneTime = new JLabel("One Time");
        lbloneTime.setBounds(100, 250, 200, 30);
        add(lbloneTime);

        txtoneTime = new JTextField();
        txtoneTime.setBounds(250, 250, 230, 30);
        add(txtoneTime);

        btnnew = new JButton("New");
        btnnew.setBounds(60, 300, 80, 30);
        add(btnnew);
        btnnew.setBackground(c1);
        btnnew.addActionListener(this);

        btnsave = new JButton("Save");
        btnsave.setBounds(150, 300, 80, 30);
        add(btnsave);
        btnsave.setBackground(c1);
        btnsave.addActionListener(this);
        btnsave.addKeyListener(this);

        btnupdate = new JButton("Update");
        btnupdate.setBounds(240, 300, 80, 30);
        add(btnupdate);
        btnupdate.setBackground(c1);
        btnupdate.addActionListener(this);

        btndel = new JButton("Delete");
        btndel.setBounds(330, 300, 80, 30);
        add(btndel);
        btndel.setBackground(c1);
        btndel.addActionListener(this);

        btndp = new JButton("Display");
        btndp.setBounds(420, 300, 80, 30);
        add(btndp);
        btndp.setBackground(c1);
        btndp.addActionListener(this);

        model = new DefaultTableModel();
        tbl = new JTable(model);
        scroll = new JScrollPane(tbl);
        scroll.setBounds(20, 360, 540, 200);
        model.addColumn("Course Id");
        model.addColumn("Course Name");
        model.addColumn("Duration");
        model.addColumn("Installment");
        model.addColumn("One Time");
        add(scroll);

        vlblcid = new JLabel();
        vlblcid.setBounds(250, 10, 230, 30);
        add(vlblcid);

        ImageIcon img = new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\myProject\\src\\InstituteProject\\img5.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 600, 680);

        setLayout(null);
        setSize(600, 680);
        setVisible(true);
        setTitle("Course");
        setLocation(350, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(background);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //JOptionPane.showMessageDialog(null, "Driver Registered");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituteprj", "root", "Rohit@1410");
            //JOptionPane.showMessageDialog(null, "Connected");
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1);
        }

    }

    public boolean validateFields() {
        String cid=txtcid.getText();
        String cname= txtcname.getText();
        //String cduration = txtduration.getText();
        String Installment = txtInstallment.getText();
        String OneTime = txtoneTime.getText();
        if(txtcid.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter id");
            return false;
        }
        else if(!cid.matches("^[0-9]+")){
            JOptionPane.showMessageDialog(null,"Enter valid id. Only contains number","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtcname.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter Course Name");
            return false;
        }
        else if(!cname.matches("^[a-z A-Z ]+")){
            JOptionPane.showMessageDialog(null,"Enter valid Course name only contain character","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtduration.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter Course duration");
            return false;
        }
        else if(txtInstallment.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter Installment");
            return false;
        }
        else if(!Installment.matches("^[0-9]+")) {
            JOptionPane.showMessageDialog(null,"Enter valid Installment","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtoneTime.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter OneTime Installment");
            return false;
        }
        else if(!OneTime.matches("^[0-9]+")) {
            JOptionPane.showMessageDialog(null,"Enter valid OneTime","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }



    public static void main(String[] args)
    {
        new Course();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnnew==e.getSource())
        {
            try
            {
                String query = "Select MAX(cid) as max_cid from course";
                PreparedStatement ps=con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    int value = rs.getInt("max_cid");
                    value = value+1;
                    txtcid.setText(Integer.toString(value));
                    System.out.println(value);
                    txtcname.setText(null);
                    txtduration.setText(null);
                    txtInstallment.setText(null);
                    txtduration.setText(null);
                    txtoneTime.setText(null);
                }
            }
            catch (Exception e2)
            {
                JOptionPane.showMessageDialog(null ,e2);
            }
        }


            if (btnsave == e.getSource()) {
                if(validateFields()) {
                try {
                    cid = Integer.parseInt(txtcid.getText());
                    cname = txtcname.getText();
                    duration = txtduration.getText();
                    installment = Integer.parseInt(txtInstallment.getText());
                    onetime = Integer.parseInt(txtoneTime.getText());
                    sql = "insert into course values(" + cid + ",'" + cname + "','" + duration + "'," + installment + "," + onetime + ")";
                    stmt = con.createStatement();
                    int i = stmt.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Save Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not save Successfully");
                    }
                    txtcid.setText("");
                    txtcname.setText("");
                    txtduration.setText("");
                    txtInstallment.setText("");
                    txtduration.setText("");
                    txtoneTime.setText("");
                } catch (Exception e3) {
                    JOptionPane.showMessageDialog(null, e3);
                }
            }
        }

        if(btnupdate == e.getSource())
        {
            try {
                cid = Integer.parseInt(txtcid.getText());
                cname = txtcname.getText();
                duration = txtduration.getText();
                installment = Integer.parseInt(txtInstallment.getText());
                onetime = Integer.parseInt(txtoneTime.getText());
                sql = "update course set cname = '" + cname + "', duration = '" + duration + "', installment = '" + installment + "', onetime = " + onetime + " where cid = "+ cid +"";
                stmt = con.createStatement();
                int i = stmt.executeUpdate(sql);
                if (i > 0) {
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Not updated");
                }
                txtcid.setText("");
                txtcname.setText("");
                txtduration.setText("");
                txtInstallment.setText("");
                txtduration.setText("");
                txtoneTime.setText("");
            }
            catch(Exception e4)
            {
                JOptionPane.showMessageDialog(null, e4);
            }
        }

        if(btndel == e.getSource())
        {
            try
            {
                cid = Integer.parseInt(txtcid.getText());
                sql = "delete from course where cid = "+ cid +" ";
                stmt = con.createStatement();
                int i = stmt.executeUpdate(sql);
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null,"Delete Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Delete Failed");
                }
                txtcid.setText("");
                txtcname.setText("");
                txtduration.setText("");
                txtInstallment.setText("");
                txtduration.setText("");
                txtoneTime.setText("");
            }
            catch (Exception e5)
            {
                JOptionPane.showMessageDialog(null, e5);
            }
        }

        if(btndp == e.getSource())
        {
            int r= 0;
            model.setRowCount(0);
            try
            {
                stmt = con.createStatement();
                rs = stmt.executeQuery("select * from course");
                while(rs.next())
                {
                    model.insertRow(r++, new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5)});
                }
            }
            catch (Exception e6)
            {
                JOptionPane.showMessageDialog(null, e6);
            }
        }

        if(course == e.getSource())
        {
            new Course();
        }
        if(trainer == e.getSource())
        {
            new Trainer();
        }
        if(batch == e.getSource())
        {
            new Batch();
        }
        if(enquiry == e.getSource())
        {
            new Enquiry();
        }
        if(admission == e.getSource())
        {
            new Addmission();
        }
        if(receipt == e.getSource())
        {
            new Receipt();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

//    @Override
//    public void keyPressed(KeyEvent e) {
//        if(btnsave == e.getSource())
//
//        {
//                if(txtcid.getText().isEmpty()) {
//                    vlblcid.setText("Plese Enter Course id...");
//                } else if(txtcid.getText().matches("[0-9]+")) {
//                    vlblcid.setText("Plese Enter Course id...");
//                }
//        }
//    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
