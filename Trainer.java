package InstituteProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


public class Trainer extends JFrame implements ActionListener
{
    JLabel lbltid, lbltname, lbladdr, lblemail, lblphno, lblgender;
    JTextField txttid, txttname, txtaddr, txtemail, txtphno;
    JRadioButton rbmale, rbfemale;
    JButton btnnew, btnsave, btnupdate, btndel, btndp;
    ButtonGroup bg;
    DefaultTableModel model;
    JTable tbl;
    JScrollPane scroll;
    JMenuBar mb;
    JMenuItem course, trainer, batch, enquiry,admission,receipt;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int tid;
    String tname, addr, email, phno, gen, sql="";
    Color c1 = new Color(203, 255, 250);
    Trainer()
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

        lbltid = new JLabel("Trainer Id");
        lbltid.setBounds(100,50,200,30);
        add(lbltid);

        txttid = new JTextField();
        txttid.setBounds(250,50,230,30);
        add(txttid);

        lbltname = new JLabel("Trainer Name");
        lbltname.setBounds(100,100,200,30);
        add(lbltname);

        txttname = new JTextField();
        txttname.setBounds(250,100,230,30);
        add(txttname);

        lbladdr = new JLabel("Address");
        lbladdr.setBounds(100,150,200,30);
        add(lbladdr);

        txtaddr = new JTextField();
        txtaddr.setBounds(250,150,230,30);
        add(txtaddr);

        lblemail = new JLabel("Email");
        lblemail.setBounds(100,200,200,30);
        add(lblemail);

        txtemail = new JTextField();
        txtemail.setBounds(250,200,230,30);
        add(txtemail);

        lblphno = new JLabel("Phone No.");
        lblphno.setBounds(100,250,200,30);
        add( lblphno);

        txtphno = new JTextField();
        txtphno.setBounds(250,250,230,30);
        add(txtphno);

        lblgender = new JLabel("Gender");
        lblgender.setBounds(100,300,200,30);
        add(lblgender);

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(250,300,60,30);
        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(320,300,90,30);
        bg =new ButtonGroup();
        bg.add(rbmale);
        bg.add(rbfemale);
        add(rbmale);
        add(rbfemale);

        btnnew = new JButton("New");
        btnnew.setBounds(60,350,80,30);
        add(btnnew);
        btnnew.setBackground(c1);
        btnnew.addActionListener(this);

        btnsave = new JButton("Save");
        btnsave.setBounds(150,350,80,30);
        add(btnsave);
        btnsave.setBackground(c1);
        btnsave.addActionListener(this);

        btnupdate = new JButton("Update");
        btnupdate.setBounds(240,350,80,30);
        add(btnupdate);
        btnupdate.setBackground(c1);
        btnupdate.addActionListener(this);

        btndel = new JButton("Delete");
        btndel.setBounds(330,350,80,30);
        add(btndel);
        btndel.setBackground(c1);
        btndel.addActionListener(this);

        btndp = new JButton("Display");
        btndp.setBounds(420,350,80,30);
        add(btndp);
        btndp.setBackground(c1);
        btndp.addActionListener(this);

        model = new DefaultTableModel();
        tbl = new JTable(model);
        scroll = new JScrollPane(tbl);
        scroll.setBounds(20,395,540,200);
        model.addColumn("Trainer Id");
        model.addColumn("Trainer Name");
        model.addColumn("Address");
        model.addColumn("Email");
        model.addColumn("Phone No.");
        model.addColumn("Gender");
        add(scroll);

        ImageIcon img=new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\myProject\\src\\InstituteProject\\img5.jpg");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,600,680);

        setLayout(null);
        setSize(600,680);
        setVisible(true);
        setTitle("Trainer");
        setLocation(350,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(background);
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //JOptionPane.showMessageDialog(null,"Diver Connected");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/instituteprj","root","Rohit@1410");
            //JOptionPane.showMessageDialog(null, "Connected");
        }
        catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null, e1);
        }
    }

    public boolean validateFields()
    {
        if(txttid.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null,"Enter Trainer Id...");
            return false;
        }
        else if (!txttid.getText().matches("^[0-9]+")) {
            JOptionPane.showMessageDialog(null, "Enter valid Id. Only contains numbers","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txttname.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Trainer Name...");
            return false;
        }
        else if(!txttname.getText().matches("^[a-zA-Z ]+"))
        {
            JOptionPane.showMessageDialog(null,"Enter valid name. Only contains character","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtaddr.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Address...");
            return false;
        }
        else if(txtemail.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Email...");
            return false;
        }
        else if(!txtemail.getText().matches( "^[a-zA-Z0-9_+&*-]+(?://.[a-zA-Z0-9_+&*-]*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$)"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Email Address...","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtphno.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Phone No...");
            return false;
        }
        else if(!txtphno.getText().matches("^[0-9]{10}+"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Phone No...","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (!rbmale.isSelected() && !rbfemale.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select Gender...","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }


    public static void main(String[] args)
    {
        new Trainer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnnew == e.getSource())
        {
            try
            {
                String query = "Select MAX(tid) as max_tid from trainer";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    int no = rs.getInt("max_tid");
                    no = no + 1;
                    txttid.setText(Integer.toString(no));
                    System.out.println(no);
                    txttname.setText(null);
                    txtaddr.setText(null);
                    txtemail.setText(null);
                    txtphno.setText(null);
                    bg.clearSelection();
                }
            }
            catch (Exception e2)
            {
                JOptionPane.showMessageDialog(null, e2);
            }
        }

        if(btnsave == e.getSource())
        {
            if(validateFields()) {
                try {
                    tid = Integer.parseInt(txttid.getText());
                    tname = txttname.getText();
                    addr = txtaddr.getText();
                    email = txtemail.getText();
                    phno = txtphno.getText();
                    if (rbmale.isSelected())
                        gen = "Male";
                    else if (rbfemale.isSelected())
                        gen = "Female";
                    sql = "insert into trainer values(" + tid + ",'" + tname + "','" + addr + "','" + email + "','" + phno + "','" + gen + "')";
                    stmt = con.createStatement();
                    int i = stmt.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Save Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not Save Successfully");
                    }
                    txttid.setText("");
                    txttname.setText("");
                    txtaddr.setText("");
                    txtemail.setText("");
                    txtphno.setText("");
                } catch (Exception e3) {
                    JOptionPane.showMessageDialog(null, e3);
                }
            }
        }

        if(btnupdate == e.getSource())
        {
            try
            {
                tid = Integer.parseInt(txttid.getText());
                tname = txttname.getText();
                addr = txtaddr.getText();
                email = txtemail.getText();
                phno = txtphno.getText();
                if(rbmale.isSelected())
                    gen="Male";
                else if(rbfemale.isSelected())
                    gen="Female";
                sql = "update trainer set tname = '"+ tname +"',address = '"+ addr +"', email = '"+ email +"', phno = '"+ phno +"', gender = '"+ gen +"' where tid = "+ tid +" ";
                stmt = con.createStatement();
                int i = stmt.executeUpdate(sql);
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null, "Updated Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Not updated");
                }
                txttid.setText("");
                txttname.setText("");
                txtaddr.setText("");
                txtemail.setText("");
                txtphno.setText("");
            }
            catch (Exception e4)
            {
                JOptionPane.showMessageDialog(null, e4);
            }
        }

        if(btndel == e.getSource())
        {
            try
            {
                tid = Integer.parseInt(txttid.getText());
                sql = "delete from trainer where tid = "+ tid +" ";
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
                txttid.setText("");
                txttname.setText("");
                txtaddr.setText("");
                txtemail.setText("");
                txtphno.setText("");
            }
            catch (Exception e5)
            {
                JOptionPane.showMessageDialog(null, e5);
            }
        }

        if(btndp == e.getSource())
        {
            int r = 0;
            model.setRowCount(0);
            try
            {
                stmt = con.createStatement();
                rs = stmt.executeQuery("Select * from trainer");
                while (rs.next())
                {
                    model.insertRow(r++, new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
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
//    private boolean validateIdField() {
//        if (txttid.getText().isEmpty() || !isInteger(txttid.getText())) {
//            JOptionPane.showMessageDialog(this, "Please enter a valid Trainer ID", "Validation Error", JOptionPane.ERROR_MESSAGE);
//            return false;
//        }
//        return true;
//    }
//
//    private boolean isInteger(String s) {
//        try {
//            Integer.parseInt(s);
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//
//    private boolean isValidEmail(String email) {
//        // Basic email validation
//        return email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
//    }
//
//    private boolean isValidPhone(String phone) {
//        // Basic phone number validation (adjust as needed)
//        return phone.matches("^\\d{10}$");
//    }
}
