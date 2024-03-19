package InstituteProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Addmission extends JFrame implements ActionListener {
    JLabel lblaid, lbldate, lblsid, lblsname, lblsemail, lblsaddr, lblsphno, lblsgen, lblcname, lbltime, lblfeetype, lblfee;
    JTextField txtaid, txtdate, txtsid, txtsname, txtsemail, txtsaddr, txtsphno,  txtbtime, txtfee;
    JRadioButton rbmale, rbfemale;
    ButtonGroup bg;
    JComboBox cbcname, cbfeetype;
    JButton btnnew, btnsave, btnupdate, btndel, btndp;

    DefaultTableModel model;
    JTable tbl;
    JScrollPane scroll;
    JMenuBar mb;
    JMenuItem course, trainer, batch, enquiry,admission,receipt;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int aid,studid,fee;
    String date, studname, studemail, studaddr, studphno, gen, cname, btime, feetype, sql = "";
    Color c1 = new Color(203, 255, 250);
    Addmission()
    {
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

        lblaid = new JLabel("Admission Id");
        lblaid.setBounds(100,10,200,30);
        add(lblaid);

        txtaid = new JTextField();
        txtaid.setBounds(250,10,230,30);
        add(txtaid);

        lbldate = new JLabel("Date");
        lbldate.setBounds(100,50,200,30);
        add(lbldate);

        txtdate = new JTextField();
        txtdate.setBounds(250,50,230,30);
        add(txtdate);

        lblsid = new JLabel("Student id");
        lblsid.setBounds(100,90,200,30);
        add(lblsid);

        txtsid = new JTextField();
        txtsid.setBounds(250,90,230,30);
        add(txtsid);

        lblsname = new JLabel("Student Name");
        lblsname.setBounds(100,130,200,30);
        add(lblsname);

        txtsname = new JTextField();
        txtsname.setBounds(250,130,230,30);
        add(txtsname);

        lblsemail = new JLabel("Email");
        lblsemail.setBounds(100,170,200,30);
        add(lblsemail);

        txtsemail = new JTextField();
        txtsemail.setBounds(250,170,230,30);
        add(txtsemail);

        lblsaddr = new JLabel("Student Address");
        lblsaddr.setBounds(100,210,200,30);
        add(lblsaddr);

        txtsaddr = new JTextField();
        txtsaddr.setBounds(250,210,230,30);
        add(txtsaddr);

        lblsphno = new JLabel("Phone No.");
        lblsphno.setBounds(100,250,200,30);
        add(lblsphno);

        txtsphno = new JTextField();
        txtsphno.setBounds(250,250,230,30);
        add(txtsphno);

        lblsgen = new JLabel("Gender");
        lblsgen.setBounds(100,290,200,30);
        add(lblsgen);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(250,290,60,30);
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(320,290,90,30);
        bg =new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        add(rbmale);
        add(rbfemale);

        lblcname = new JLabel("Course Name");
        lblcname.setBounds(100,330,200,30);
        add(lblcname);

        String cb[] = {"Select Course Name"};
        cbcname = new JComboBox<>(cb);
        cbcname.setBounds(250,330,230,30);
        add(cbcname);

        lbltime = new JLabel("Batch Time");
        lbltime.setBounds(100,370,200,30);
        add(lbltime);

        txtbtime = new JTextField();
        txtbtime.setBounds(250,370,230,30);
        add(txtbtime);

        lblfeetype = new JLabel("Fee Type");
        lblfeetype.setBounds(100,410,200,30);
        add(lblfeetype);

        String feetype[] = {"Select Feetype","OneTime", "Installment"};
        cbfeetype = new JComboBox(feetype);
        cbfeetype.setBounds(250,410,200,30);
        add(cbfeetype);

        lblfee = new JLabel("Fee");
        lblfee.setBounds(100,450,200,30);
        add(lblfee);

        txtfee = new JTextField();
        txtfee.setBounds(250,450,230,30);
        add(txtfee);

        btnnew = new JButton("New");
        btnnew.setBounds(60,490,80,30);
        add(btnnew);
        btnnew.setBackground(c1);
        btnnew.addActionListener(this);

        btnsave = new JButton("Save");
        btnsave.setBounds(150,490,80,30);
        add(btnsave);
        btnsave.setBackground(c1);
        btnsave.addActionListener(this);

        btnupdate = new JButton("Update");
        btnupdate.setBounds(240,490,80,30);
        add(btnupdate);
        btnupdate.setBackground(c1);
        btnupdate.addActionListener(this);

        btndel = new JButton("Delete");
        btndel.setBounds(330,490,80,30);
        add(btndel);
        btndel.setBackground(c1);
        btndel.addActionListener(this);

        btndp = new JButton("Display");
        btndp.setBounds(420,490,80,30);
        add(btndp);
        btndp.setBackground(c1);
        btndp.addActionListener(this);

        model = new DefaultTableModel();
        tbl = new JTable(model);
        scroll = new JScrollPane(tbl);
        scroll.setBounds(20,530,540,80);
        model.addColumn("Admission Id");
        model.addColumn("Date");
        model.addColumn("Student Id");
        model.addColumn("Student Name");
        model.addColumn("Email");
        model.addColumn("Student Address");
        model.addColumn("Phone No.");
        model.addColumn("Gender");
        model.addColumn("Course Name");
        model.addColumn("Batch Name");
        model.addColumn("Feetype");
        model.addColumn("Fee");
        add(scroll);

        ImageIcon img=new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\myProject\\src\\InstituteProject\\img5.jpg");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,600,680);

        setLayout(null);
        setSize(600,680);
        setVisible(true);
        setTitle("Admission");
        setLocation(350,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(background);
        cbfeetype.addActionListener(this);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //JOptionPane.showMessageDialog(null,"Driver Registered");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituteprj","root","Rohit@1410");
            //JOptionPane.showMessageDialog(null,"Connected");

            try
            {
                stmt = con.createStatement();
                rs = stmt.executeQuery("select cname from course");
                while(rs.next())
                {
                    String c1 = rs.getString("cname");
                    cbcname.addItem(c1);
                }
            }
            catch(Exception e7)
            {
                JOptionPane.showMessageDialog(null, e7);
            }
        }

        catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null, e1);
        }
    }

    public boolean validateFeilds()
    {
        if(txtaid.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Admission id...");
            return false;
        }
        else if(!txtaid.getText().matches("^[0-9]+"))
        {
            JOptionPane.showMessageDialog(null,"Enter valid Admission Id. Only contains number","alert",JOptionPane.WARNING_MESSAGE);
        }
        else if(txtdate.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Date...");
            return false;
        }
        else if(!txtdate.getText().matches("^[0-3][0-9][/]+[0-1][0-2][/]+[0-9]{4}"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Date","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(txtsid.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Student id...");
            return false;
        }
        else if(!txtsid.getText().matches("^[0-9]+"))
        {
            JOptionPane.showMessageDialog(null,"Enter valid Student Id. Only contains number","alert",JOptionPane.WARNING_MESSAGE);
        }
        else if(txtsname.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Student Name...");
            return false;
        }
        else if(!txtsname.getText().matches("^[a-zA-Z ]+"))
        {
            JOptionPane.showMessageDialog(null,"Enter valid Name. Only contains characters","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtsemail.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Email...");
            return false;
        }
        else if(!txtsemail.getText().matches( "^[a-zA-Z0-9_+&*-]+(?://.[a-zA-Z0-9_+&*-]*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$)"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Email Address...","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtsaddr.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Address...");
            return false;
        }
        else if(txtsphno.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Phone No...");
            return false;
        }
        else if(!txtsphno.getText().matches("^[0-9]{10}+"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Phone No...","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (!rbmale.isSelected() && !rbfemale.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select Gender...","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (cbcname.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Select Course Name","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtbtime.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Batch Time...");
            return false;
        }
        else if(!txtbtime.getText().matches("[0-9][0-9][:][0-9][0-9][-][0-9][0-9][:][0-9][0-9]"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Time","alert",JOptionPane.WARNING_MESSAGE);
        }
        else if (cbfeetype.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Select Feetype","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtfee.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Fee...");
            return false;
        }
        else if(!txtfee.getText().matches("^[0-9]+"))
        {
            JOptionPane.showMessageDialog(null,"Enter valid fee. Only contains number","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        new Addmission();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnnew == e.getSource())
        {
            try
            {
                String query = "Select MAX(aid) as max_aid from admission";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    int no = rs.getInt("max_aid");
                    no = no + 1;
                    txtaid.setText(Integer.toString(no));
                    System.out.println(no);
                    txtdate.setText(null);
                    txtsid.setText(null);
                    txtsname.setText(null);
                    txtsemail.setText(null);
                    txtsaddr.setText(null);
                    txtsphno.setText(null);
                    bg.clearSelection();
                    cbcname.setSelectedIndex(0);
                    txtbtime.setText(null);
                    cbfeetype.setSelectedIndex(0);
                    txtfee.setText(null);
                }
            }
            catch(Exception e2)
            {
                JOptionPane.showMessageDialog(null,e2);
            }
        }

        if(btnsave == e.getSource())
        {
            if(validateFeilds()) {
                try {
                    aid = Integer.parseInt(txtaid.getText());
                    date = txtdate.getText();
                    studid = Integer.parseInt(txtsid.getText());
                    studname = txtsname.getText();
                    studemail = txtsemail.getText();
                    studaddr = txtsaddr.getText();
                    studphno = txtsphno.getText();
                    if (rbmale.isSelected())
                        gen = "Male";
                    else if (rbfemale.isSelected())
                        gen = "Female";
                    cname = cbcname.getSelectedItem().toString();
                    btime = txtbtime.getText();
                    feetype = cbfeetype.getSelectedItem().toString();
                    fee = Integer.parseInt(txtfee.getText());
                    stmt = con.createStatement();
                    String sql = "insert into admission values(" + aid + ",'" + date + "'," + studid + ",'" + studname + "','" + studemail + "','" + studaddr + "','" + studphno + "','" + gen + "','" + cname + "','" + btime + "','" + feetype + "'," + fee + ")";
                    int i = stmt.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Save Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not Save Successfully");
                    }
                    txtaid.setText("");
                    txtdate.setText("");
                    txtsid.setText("");
                    txtsname.setText("");
                    txtsemail.setText("");
                    txtsaddr.setText("");
                    txtsphno.setText("");
                    txtbtime.setText("");
                    txtfee.setText("");
                } catch (Exception e3) {
                    JOptionPane.showMessageDialog(null, e3);
                }
            }
        }

        if(btnupdate == e.getSource())
        {
            try
            {
                aid = Integer.parseInt(txtaid.getText());
                date = txtdate.getText();
                studid = Integer.parseInt(txtsid.getText());
                studname = txtsname.getText();
                studemail = txtsemail.getText();
                studaddr = txtsaddr.getText();
                studphno = txtsphno.getText();
                if(rbmale.isSelected())
                    gen="Male";
                else if(rbfemale.isSelected())
                    gen="Female";
                cname = cbcname.getSelectedItem().toString();
                btime = txtbtime.getText();
                feetype = cbfeetype.getSelectedItem().toString();
                fee = Integer.parseInt(txtfee.getText());
                stmt = con.createStatement();
                String sql = "update admission set date = '"+ date +"', sid = "+ studid +", sname = '"+ studname +"', email = '"+ studemail +"', saddr = '"+ studaddr +"', sphno = '"+ studphno +"', gen = '"+ gen +"', cname = '"+ cname +"', btime = '"+ btime +"', feetype = '"+ feetype +"', fee = "+ fee +" where aid = "+ aid +" ";
                int i = stmt.executeUpdate(sql);
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null,"Update Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Not Updated");
                }
                txtaid.setText("");
                txtdate.setText("");
                txtsid.setText("");
                txtsname.setText("");
                txtsemail.setText("");
                txtsaddr.setText("");
                txtsphno.setText("");
                txtbtime.setText("");
                txtfee.setText("");
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
                aid = Integer.parseInt(txtaid.getText());
                sql = "delete from admission where aid = "+ aid +" ";
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
        if(cbfeetype== e.getSource())
        {
            try
            {
                sql = "select * from course where cname = '"+ cbcname.getSelectedItem() +"' ";
                stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    String s1=(String)cbfeetype.getSelectedItem();
                    if(s1.equals("OneTime"))
                    {
                        txtfee.setText(""+rs.getInt(5));
                    }
                    else
                    {
                        txtfee.setText(""+rs.getInt(4));
                    }
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
                rs = stmt.executeQuery("select * from admission");
                while(rs.next())
                {
                    model.insertRow(r++,new Object[]{rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getInt(12)});
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
