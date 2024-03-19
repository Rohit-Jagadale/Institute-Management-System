package InstituteProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Enquiry extends JFrame implements ActionListener
{
    JLabel lblsid, lbldate, lblsname, lblsadd, lblsgen, lblsphno, lblsemail, lblcname, lblbtime, lblmode;
    JTextField txtsid, txtdate, txtsname,txtsaddr, txtsphno, txtsemail,  txtbtime;
    JRadioButton rbmale, rbfemale, rboff, rbon;
    JButton btnnew, btnsave, btnupdate, btndel, btndp;
    JComboBox cbcname;
    ButtonGroup bg, BG;
    DefaultTableModel model;
    JTable tbl;
    JScrollPane scroll;
    JMenuBar mb;
    JMenuItem course, trainer, batch, enquiry,admission,receipt;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int studid;
    String date,studname,studaddr,studgen,studphno,studemail,cname,btime,mode,sql="";
    Color c1 = new Color(203, 255, 250);
    Enquiry()
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

        lblsid = new JLabel("Student Id");
        lblsid.setBounds(100,10,200,30);
        add(lblsid);

        txtsid = new JTextField();
        txtsid.setBounds(250,10,230,30);
        add(txtsid);

        lbldate = new JLabel("Date");
        lbldate.setBounds(100,60,200,30);
        add(lbldate);

        txtdate = new JTextField();
        txtdate.setBounds(250,60,230,30);
        add(txtdate);

        lblsname = new JLabel("Student Name");
        lblsname.setBounds(100,110,200,30);
        add(lblsname);

        txtsname = new JTextField();
        txtsname.setBounds(250,110,230,30);
        add(txtsname);

        lblsadd = new JLabel("Student Address");
        lblsadd.setBounds(100,160,200,30);
        add(lblsadd);

        txtsaddr = new JTextField();
        txtsaddr.setBounds(250,160,230,30);
        add(txtsaddr);

        lblsgen = new JLabel("Gender");
        lblsgen.setBounds(100,200,200,30);
        add(lblsgen);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(250,200,70,30);
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(320,200,90,30);
        bg =new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        add(rbmale);
        add(rbfemale);

        lblsphno = new JLabel("Phone No.");
        lblsphno.setBounds(100,240,200,30);
        add(lblsphno);

        txtsphno = new JTextField();
        txtsphno.setBounds(250,240,230,30);
        add(txtsphno);

        lblsemail = new JLabel("Email");
        lblsemail.setBounds(100,290,200,30);
        add(lblsemail);

        txtsemail = new JTextField();
        txtsemail.setBounds(250,290,230,30);
        add(txtsemail);


        lblcname = new JLabel("Course Name");
        lblcname.setBounds(100,340,200,30);
        add(lblcname);

        String cb[] = {"Select Course Name"};
        cbcname = new JComboBox<>(cb);
        cbcname.setBounds(250,340,230,30);
        add(cbcname);

        lblbtime = new JLabel("Batch Time");
        lblbtime.setBounds(100,390,200,30);
        add(lblbtime);

        txtbtime = new JTextField();
        txtbtime.setBounds(250,390,230,30);
        add(txtbtime);

        lblmode = new JLabel("Mode");
        lblmode.setBounds(100,430,200,30);
        add(lblmode);

        rbon = new JRadioButton("Online");
        rbon.setBounds(250,430,70,30);
        rboff = new JRadioButton("Offline");
        rboff.setBounds(320,430,90,30);
        BG =new ButtonGroup();
        BG.add(rbon);
        BG.add(rboff);
        add(rbon);
        add(rboff);

        btnnew = new JButton("New");
        btnnew.setBounds(60,470,80,30);
        add(btnnew);
        btnnew.setBackground(c1);
        btnnew.addActionListener(this);

        btnsave = new JButton("Save");
        btnsave.setBounds(150,470,80,30);
        add(btnsave);
        btnsave.setBackground(c1);
        btnsave.addActionListener(this);

        btnupdate = new JButton("Update");
        btnupdate.setBounds(240,470,80,30);
        add(btnupdate);
        btnupdate.setBackground(c1);
        btnupdate.addActionListener(this);

        btndel = new JButton("Delete");
        btndel.setBounds(330,470,80,30);
        add(btndel);
        btndel.setBackground(c1);
        btndel.addActionListener(this);

        btndp = new JButton("Display");
        btndp.setBounds(420,470,80,30);
        add(btndp);
        btndp.setBackground(c1);
        btndp.addActionListener(this);

        model = new DefaultTableModel();
        tbl = new JTable(model);
        scroll = new JScrollPane(tbl);
        scroll.setBounds(20,510,540,100);
        model.addColumn("Student Id");
        model.addColumn("Date");
        model.addColumn("Student Name");
        model.addColumn("Student Address");
        model.addColumn("Gender");
        model.addColumn("Phone No.");
        model.addColumn("Email");
        model.addColumn("Course Name");
        model.addColumn("Batch Name");
        model.addColumn("Mode");
        add(scroll);

        ImageIcon img=new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\myProject\\src\\InstituteProject\\img5.jpg");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,600,680);

        setLayout(null);
        setSize(600,680);
        setVisible(true);
        setTitle("Enquiry");
        setLocation(350,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(background);

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //JOptionPane.showMessageDialog(null,"Diver Connected");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituteprj","root","Rohit@1410");
            //JOptionPane.showMessageDialog(null, "Connected");
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
            catch(Exception e8)
            {
                JOptionPane.showMessageDialog(null, e8);
            }
        }
        catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null, e1);
        }
    }

    public boolean validatefields()
    {
        if(txtsid.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Student Id...");
            return false;
        }
        else if(!txtsid.getText().matches("^[0-9]+"))
        {
            JOptionPane.showMessageDialog(null,"Enter valid Id. Only contains number","alert",JOptionPane.WARNING_MESSAGE);
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
        else if(txtsaddr.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Address...");
            return false;
        }
        else if (!rbmale.isSelected() && !rbfemale.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select Gender...","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtsphno.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Mobile No...");
            return false;
        }
        else if(!txtsphno.getText().matches("^[0-9]{10}+"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Mobile No...","alert",JOptionPane.WARNING_MESSAGE);
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
        else if (cbcname.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Select Course Name","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(!txtbtime.getText().matches("^[0-9][0-9][:][0-9][0-9][-][0-9][0-9][:][0-9][0-9]"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Time");
            return false;
        }
        else if (!rbon.isSelected() && !rboff.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select Mode...","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
       return true;
    }

    public static void main(String[] args) {
        new Enquiry();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(btnnew == e.getSource())
            {
                try
                {
                    String query = "Select MAX(studId) as max_sid from enquiry";
                    PreparedStatement ps = con.prepareStatement(query);
                    ResultSet rs = ps.executeQuery();
                    if(rs.next()) {
                        int no = rs.getInt("max_sid");
                        no = no + 1;
                        txtsid.setText(Integer.toString(no));
                        System.out.println(no);
                        txtdate.setText(null);
                        txtsname.setText(null);
                        txtsaddr.setText(null);
                        bg.clearSelection();
                        txtsphno.setText(null);
                        txtsemail.setText(null);
                        cbcname.setSelectedIndex(0);
                        txtbtime.setText(null);
                        BG.clearSelection();
                    }
                }
                catch (Exception e2)
                {
                    JOptionPane.showMessageDialog(null, e2);
                }
            }

            if(btnsave == e.getSource())
            {
                if(validatefields()) {
                    try {
                        studid = Integer.parseInt(txtsid.getText());
                        date = txtdate.getText();
                        studname = txtsname.getText();
                        studaddr = txtsaddr.getText();
                        if (rbmale.isSelected())
                            studgen = "Male";
                        else if (rbfemale.isSelected())
                            studgen = "Female";
                        studphno = txtsphno.getText();
                        studemail = txtsemail.getText();
                        cname = cbcname.getSelectedItem().toString();
                        btime = txtbtime.getText();
                        if (rbon.isSelected())
                            mode = "Online";
                        else if (rboff.isSelected())
                            mode = "Offline";

                        sql = "insert into enquiry value(" + studid + ",'" + date + "','" + studname + "','" + studaddr + "','" + studgen + "','" + studphno + "','" + studemail + "','" + cname + "','" + btime + "','" + mode + "')";
                        stmt = con.createStatement();
                        int i = stmt.executeUpdate(sql);
                        if (i > 0) {
                            JOptionPane.showMessageDialog(null, "Save Successfully");
                        } else {
                            JOptionPane.showMessageDialog(null, "Not Save Successfully");
                        }
                        txtsid.setText("");
                        txtdate.setText("");
                        txtsname.setText("");
                        txtsaddr.setText("");
                        txtsphno.setText("");
                        txtsemail.setText("");
                        txtbtime.setText("");
                    } catch (Exception e3) {
                        JOptionPane.showMessageDialog(null, e3);
                    }
                }
            }

            if(btnupdate == e.getSource())
            {
                try
                {
                    studid = Integer.parseInt(txtsid.getText());
                    date = txtdate.getText();
                    studname = txtsname.getText();
                    studaddr = txtsaddr.getText();
                    if(rbmale.isSelected())
                        studgen="Male";
                    else if(rbfemale.isSelected())
                        studgen="Female";
                    studphno = txtsphno.getText();
                    studemail = txtsemail.getText();
                    cname = cbcname.getSelectedItem().toString();
                    btime = txtbtime.getText();
                    if(rbon.isSelected())
                        mode="Online";
                    else if(rboff.isSelected())
                        mode="Offline";

                    sql = "update enquiry set date = '"+ date +"', studName = '"+ studname +"', studAddr = '"+ studaddr +"', studgen = '"+ studgen +"', studphno = '"+ studphno +"', studEmail = '"+ studemail +"', cname = '"+ cname +"', btime = '"+ btime +"', mode = '"+ mode +"' where studId = "+ studid +" ";
                    stmt = con.createStatement();
                    int i = stmt.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Updated Successfully");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Not updated");
                    }
                    txtsid.setText("");
                    txtdate.setText("");
                    txtsname.setText("");
                    txtsaddr.setText("");
                    txtsphno.setText("");
                    txtsemail.setText("");
                    txtbtime.setText("");
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
                    studid = Integer.parseInt(txtsid.getText());
                    sql = "delete from batch where bid = "+ studid +" ";
                    stmt = con.createStatement();
                    int i = stmt.executeUpdate(sql);
                    if(i>0)
                    {
                        JOptionPane.showMessageDialog(null, "Delete Successfully");
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Not Delete");
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
                    rs = stmt.executeQuery("select * from enquiry");
                    while(rs.next())
                    {
                        model.insertRow(r++,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10)});
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
