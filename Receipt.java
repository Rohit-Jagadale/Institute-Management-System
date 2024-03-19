package InstituteProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Receipt extends JFrame implements ActionListener {
    JLabel heading;
    JLabel lblrno;
    JLabel lbldate;
    JLabel date;
    JLabel lblsname;
    JLabel lblphno;
    JLabel lblsemail;
    JLabel lblcname;
    JLabel lblfeemode;
    JLabel lblcfees;
    JLabel lblpaidfee;
    JLabel lbltodaypaidfee;
    JLabel lblbalance;
    JTextField txtrno, txtsphno, txtsemail, txtcname, txtfeemode, txtcfees, txtpaidfee, txttodaypaidfee, txtbalance;

    JComboBox cbsname;
    JButton btnnew, btnsave, btnupdate, btndel, btndp;

    DefaultTableModel model;
    JTable tbl;
    JScrollPane scroll;
    JMenuBar mb;
    JMenuItem course, trainer, batch, enquiry,admission,receipt;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int rno;

    String rdate, name, phno, email, cname, feemode, cfees, paidfee, todaypaid, balance, sql = "";
    Color c1 = new Color(203, 255, 250);
    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    LocalDateTime dateTime = LocalDateTime.now();  // Replace this with your LocalDateTime object
    String formattedDateTime = dateTime.format(myFormatObj);
    Receipt() {
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

        heading = new JLabel("Receipt");
        heading.setBounds(250,10,200,30);
        add(heading);

        lblrno = new JLabel("Receipt No");
        lblrno.setBounds(100, 50, 100, 30);
        add(lblrno);

        txtrno = new JTextField();
        txtrno.setBounds(250, 50, 100, 30);
        add(txtrno);

        lbldate = new JLabel("Date : ");
        lbldate.setBounds(370, 50, 50, 30);
        add(lbldate);

        date = new JLabel(formattedDateTime);
        date.setBounds(410, 50, 150, 30);
        add(date);

        lblsname = new JLabel("Student Name");
        lblsname.setBounds(100, 90, 200, 30);
        add(lblsname);

        cbsname = new JComboBox<>();
        cbsname.setBounds(250, 90, 230, 30);
        add(cbsname);

     cbsname.addItemListener(e -> {
            if (e.getStateChange()==ItemEvent.SELECTED)
            {
                try
                {
                    String selectedName = cbsname.getSelectedItem().toString();
                    String admissionQuery="select * from admission where sname=?";
                    PreparedStatement ps=con.prepareStatement(admissionQuery);
                    ps.setString(1,selectedName);
                    ResultSet rs1=ps.executeQuery();
                    if(rs1.next())
                    {
                        txtsphno.setText(rs1.getString("sphno"));
                        txtsemail.setText(rs1.getString("email"));
                        txtcname.setText(rs1.getString("cname"));
                        txtfeemode.setText(rs1.getString("feetype"));
                        txtcfees.setText(rs1.getString("fee"));
                    }

                    String receiptQuery=("select * from receipt where sname=?");
                    PreparedStatement ps2=con.prepareStatement(receiptQuery);
                    ps2.setString(1,selectedName);
                    ResultSet rs= ps2.executeQuery();
                    if(rs.next()){
                        txtrno.setText(rs.getString("rno"));
                        txtpaidfee.setText(rs.getString("paidfee"));
                        txtbalance.setText(rs.getString("balance"));
                    }
                    else
                    {
                        txtpaidfee.setText("0");
                    }
                }
                catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(null,ex);
                }
            }
        });



        lblphno = new JLabel("Phone No.");
        lblphno.setBounds(100, 130, 200, 30);
        add(lblphno);

        txtsphno = new JTextField();
        txtsphno.setBounds(250, 130, 230, 30);
        add(txtsphno);

        lblsemail = new JLabel("Email Id");
        lblsemail.setBounds(100, 170, 200, 30);
        add(lblsemail);

        txtsemail = new JTextField();
        txtsemail.setBounds(250, 170, 230, 30);
        add(txtsemail);

        lblcname = new JLabel("Course Name");
        lblcname.setBounds(100, 210, 200, 30);
        add(lblcname);

        txtcname = new JTextField();
        txtcname.setBounds(250, 210, 230, 30);
        add(txtcname);


        lblfeemode = new JLabel("Fee Mode");
        lblfeemode.setBounds(100, 250, 200, 30);
        add(lblfeemode);

        txtfeemode = new JTextField();
        txtfeemode.setBounds(250, 250, 230, 30);
        add(txtfeemode);

        lblcfees = new JLabel("Course Fee");
        lblcfees.setBounds(100, 290, 200, 30);
        add(lblcfees);

        txtcfees = new JTextField();
        txtcfees.setBounds(250, 290, 230, 30);
        add(txtcfees);

        lblpaidfee = new JLabel("Paid Fee");
        lblpaidfee.setBounds(100, 330, 200, 30);
        add(lblpaidfee);

        txtpaidfee = new JTextField();
        txtpaidfee.setBounds(250, 330, 230, 30);
        add(txtpaidfee);

        lbltodaypaidfee = new JLabel("Today paid Fee");
        lbltodaypaidfee.setBounds(100, 370, 200, 30);
        add(lbltodaypaidfee);

        txttodaypaidfee = new JTextField();
        txttodaypaidfee.setBounds(250, 370, 230, 30);
        add(txttodaypaidfee);
        txttodaypaidfee.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void focusLost(FocusEvent e) {
                // TODO Auto-generated method stub
                try
                {
                    String selectedName = cbsname.getSelectedItem().toString();
                    String receiptQuery = "select * from receipt where sname=?";
                    PreparedStatement ps1 = con.prepareStatement(receiptQuery);
                    ps1.setString(1, selectedName);
                    ResultSet rs1 = ps1.executeQuery();

                    if (rs1.next())
                    {
                        int paidFee = rs1.getInt("paidfee");
                        txtpaidfee.setText(Integer.toString(paidFee));
                        int courseFee = Integer.parseInt(txtcfees.getText());
                        int remFee= rs1.getInt("balance");
                        int todayPaidfee = Integer.parseInt(txttodaypaidfee.getText());
                        String remainingFee = Integer.toString(courseFee - (paidFee + todayPaidfee));
                        txtbalance.setText(remainingFee);
                    }
                    else
                    {
                        int courseFees = Integer.parseInt(txtcfees.getText());
                        int todayPaidfee = Integer.parseInt(txttodaypaidfee.getText());
                        String remFee = Integer.toString(courseFees - todayPaidfee);
                        txtbalance.setText(remFee);
                    }
                }
                catch (Exception e2)
                {
                    System.out.println(e2);
                    JOptionPane.showInternalMessageDialog(null, "Please add atleast 0 in today paid fee");
                }

            }

        });

        lblbalance = new JLabel("Balance");
        lblbalance.setBounds(100, 410, 200, 30);
        add(lblbalance);

        txtbalance = new JTextField();
        txtbalance.setBounds(250, 410, 230, 30);
        add(txtbalance);

        btnnew = new JButton("New");
        btnnew.setBounds(60, 450, 80, 30);
        add(btnnew);
        btnnew.setBackground(c1);
        btnnew.addActionListener(this);

        btnsave = new JButton("Save");
        btnsave.setBounds(150, 450, 80, 30);
        add(btnsave);
        btnsave.setBackground(c1);
        btnsave.addActionListener(this);

        btnupdate = new JButton("Update");
        btnupdate.setBounds(240, 450, 80, 30);
        add(btnupdate);
        btnupdate.setBackground(c1);
        btnupdate.addActionListener(this);

        btndel = new JButton("Delete");
        btndel.setBounds(330, 450, 80, 30);
        add(btndel);
        btndel.setBackground(c1);
        btndel.addActionListener(this);

        btndp = new JButton("Display");
        btndp.setBounds(420, 450, 80, 30);
        add(btndp);
        btndp.setBackground(c1);
        btndp.addActionListener(this);

        model = new DefaultTableModel();
        tbl = new JTable(model);
        scroll = new JScrollPane(tbl);
        scroll.setBounds(20, 490, 540, 110);
        model.addColumn("Receipt Nos");
        model.addColumn("Date");
        model.addColumn("Student Name");
        model.addColumn("Phone no");
        model.addColumn("Email");
        model.addColumn("Course Name");
        model.addColumn("Fee Mode");
        model.addColumn("Course Fee");
        model.addColumn("Paid Fees");
        model.addColumn("Today Paid fee");
        model.addColumn("Balance");
        add(scroll);

        ImageIcon img = new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\myProject\\src\\InstituteProject\\img5.jpg");
        JLabel background = new JLabel("", img, JLabel.CENTER);
        background.setBounds(0, 0, 600, 680);

        setLayout(null);
        setSize(600, 680);
        setVisible(true);
        setTitle("Receipt");
        setLocation(350, 0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(background);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //JOptionPane.showMessageDialog(null,"Driver Registered");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituteprj", "root", "Rohit@1410");
            //JOptionPane.showMessageDialog(null,"Connected");

            try {
                stmt = con.createStatement();
                rs = stmt.executeQuery("select sname from admission");
                while (rs.next()) {
                    String c1 = rs.getString("sname");
                    cbsname.addItem(c1);
                }
            } catch (Exception e7) {
                JOptionPane.showMessageDialog(null, e7);
            }
        } catch (Exception e1) {
            JOptionPane.showMessageDialog(null, e1);
        }
    }

    public static void main(String[] args) {
        new Receipt();
    }
    public void actionPerformed(ActionEvent e) {
        if(btnnew == e.getSource())
        {
            try
            {
                String query = "Select MAX(rno) as max_rno from receipt";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if(rs.next())
                {
                    int no = rs.getInt("max_rno");
                    no = no+1;
                    txtrno.setText(Integer.toString(no));
                    System.out.println(no);
                    cbsname.setSelectedIndex(0);
                    txtsphno.setText(null);
                    txtsemail.setText(null);
                    txtcname.setText(null);
                    txtfeemode.setText(null);
                    txtcfees.setText(null);
                    txtpaidfee.setText(null);
                    txttodaypaidfee.setText(null);
                    txtbalance.setText(null);
                }
            }
            catch(Exception e2)
            {
                JOptionPane.showMessageDialog(null,e2);
            }
        }

        if(btnsave == e.getSource())
        {
            try
            {
                rno = Integer.parseInt(txtrno.getText());
                rdate = date.getText();
                name = cbsname.getSelectedItem().toString();
                phno = txtsphno.getText();
                email = txtsemail.getText();
                cname = txtcname.getText();
                feemode = txtfeemode.getText();
                cfees = txtcfees.getText();
                paidfee = txttodaypaidfee.getText();
                todaypaid = txttodaypaidfee.getText();
                balance = txtbalance.getText();

                stmt = con.createStatement();
                String sql = "insert into receipt values("+ rno +",'"+ rdate +"','"+ name +"','"+ phno +"','"+ email +"','"+ cname +"','"+ feemode +"','"+ cfees +"','"+ paidfee +"','"+ todaypaid +"','"+ balance +"')";
                int i = stmt.executeUpdate(sql);
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null,"Save Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Not Saved");
                }
                txtrno.setText("");
                txtsphno.setText("");
                txtsemail.setText("");
                txtcname.setText("");
                txtfeemode.setText("");
                txtcfees.setText("");
                txtpaidfee.setText("");
                txttodaypaidfee.setText("");
                txtbalance.setText("");
            }
            catch (Exception e3)
            {
                JOptionPane.showMessageDialog(null, e3);
            }
        }

        if(btnupdate == e.getSource())
        {
            try
            {
                rno = Integer.parseInt(txtrno.getText());
                rdate = date.getText();
                name = cbsname.getSelectedItem().toString();
                phno = txtsphno.getText();
                email = txtsemail.getText();
                cname = txtcname.getText();
                feemode = txtfeemode.getText();
                cfees = txtcfees.getText();
                paidfee = txtpaidfee.getText();
                todaypaid = txttodaypaidfee.getText();
                balance = txtbalance.getText();

                String selectedName = cbsname.getSelectedItem().toString();
                String receiptQuery = "select * from receipt where sname=?";
                PreparedStatement ps1 = con.prepareStatement(receiptQuery);
                ps1.setString(1, selectedName);
                ResultSet rs1 = ps1.executeQuery();
                paidfee="";
                if (rs1.next()) {
                    int paidFee1 = rs1.getInt("paidfee");
                    paidfee=Integer.toString(paidFee1+Integer.parseInt(todaypaid));
                }

                if(Integer.parseInt(todaypaid)>Integer.parseInt(balance)) {
                    JOptionPane.showMessageDialog(null, "Today paid fee can not be greater than remaining,fee will be set to remaining fee");

                }
                else
                {
                    String sql = "update receipt set date = '" + rdate + "', sname = '" + name + "', phno = '" + phno + "', semail = '" + email + "', cname = '" + cname + "', feemode = '" + feemode + "', cfees = '" + cfees + "', paidfee = '" + paidfee + "', todaypaidfee = '" + todaypaid + "', balance = '" + balance + "' where rno = " + rno +" ";
                    stmt = con.createStatement();
                    int i = stmt.executeUpdate(sql);

                    if (i > 0)
                    {
                        JOptionPane.showMessageDialog(null, "Update Successfully");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Not Updated");
                    }
                }

                txtrno.setText("");
                txtsphno.setText("");
                txtsemail.setText("");
                txtcname.setText("");
                txtfeemode.setText("");
                txtcfees.setText("");
                txtpaidfee.setText("");
                txttodaypaidfee.setText("");
                txtbalance.setText("");
            }
            catch (Exception e4)
            {
                JOptionPane.showMessageDialog(null,e4);
            }
        }

        if(btndel == e.getSource())
        {
            try
            {
                rno = Integer.parseInt(txtrno.getText());
                sql = "delete from receipt where rno = "+ rno +" ";
                stmt = con.createStatement();
                int i = stmt.executeUpdate(sql);
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null, "Delete Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Not Deleted");
                }
            }
            catch (Exception e5)
            {
                JOptionPane.showMessageDialog(null,e5);
            }
        }

        if(btndp == e.getSource())
        {
            try
            {
                int r = 0;
                model.setRowCount(0);
                stmt = con.createStatement();
                rs = stmt.executeQuery("select * from receipt");
                while(rs.next())
                {
                    model.insertRow(r++,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)});
                }
            }
            catch (Exception e6)
            {
                JOptionPane.showMessageDialog(null,e6);
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
}
