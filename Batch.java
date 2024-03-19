package InstituteProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import  java.awt.event.*;
import java.sql.*;

public class Batch extends JFrame implements ActionListener,MouseListener
{
    JLabel lblbid, lblcname, lblsd, lblbtime, lbltname;
    JTextField txtbid, txtsd, txtbtime;
    JComboBox cbcname, cbtname;
    JButton btnnew, btnsave, btnupdate, btndel, btndp;

    DefaultTableModel model;
    JTable tbl;
    JScrollPane scroll;
    JMenuBar mb;
    JMenuItem course, trainer, batch, enquiry,admission,receipt;
    Connection con;
    Statement stmt;
    ResultSet rs;
    int bid;
    String cname,sdate,btime,tname,sql = "";
    Color c1 = new Color(203, 255, 250);
    Batch()
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

        lblbid = new JLabel("Batch Id");
        lblbid.setBounds(100,50,200,30);
        add(lblbid);

        txtbid = new JTextField();
        txtbid.setBounds(250,50,230,30);
        add(txtbid);

        lblcname = new JLabel("Course Name");
        lblcname.setBounds(100,100,200,30);
        add(lblcname);

        String cb[]={"Select Course Name"};
        cbcname = new JComboBox<>(cb);
        cbcname.setBounds(250,100,230,30);
        add(cbcname);

        lblsd = new JLabel("Start Date");
        lblsd.setBounds(100,150,200,30);
        add(lblsd);

        txtsd = new JTextField();
        txtsd.setBounds(250,150,230,30);
        add(txtsd);

        lblbtime = new JLabel("Batch Time");
        lblbtime.setBounds(100,200,200,30);
        add(lblbtime);

        txtbtime = new JTextField();
        txtbtime.setBounds(250,200,230,30);
        add(txtbtime);

        lbltname = new JLabel("Trainer Name");
        lbltname.setBounds(100,250,200,30);
        add(lbltname);

        String cb2[] = {"Select Trainer Name"};
        cbtname = new JComboBox<>(cb2);
        cbtname.setBounds(250,250,230,30);
        add(cbtname);

        btnnew = new JButton("New");
        btnnew.setBounds(60,300,80,30);
        add(btnnew);
        btnnew.setBackground(c1);
        btnnew.addActionListener(this);

        btnsave = new JButton("Save");
        btnsave.setBounds(150,300,80,30);
        add(btnsave);
        btnsave.setBackground(c1);
        btnsave.addActionListener(this);

        btnupdate = new JButton("Update");
        btnupdate.setBounds(240,300,80,30);
        add(btnupdate);
        btnupdate.setBackground(c1);
        btnupdate.addActionListener(this);

        btndel = new JButton("Delete");
        btndel.setBounds(330,300,80,30);
        add(btndel);
        btndel.setBackground(c1);
        btndel.addActionListener(this);

        btndp = new JButton("Display");
        btndp.setBounds(420,300,80,30);
        add(btndp);
        btndp.setBackground(c1);
        btndp.addActionListener(this);

        model = new DefaultTableModel();
        tbl = new JTable(model);
        scroll = new JScrollPane(tbl);
        scroll.setBounds(20,360,540,200);
        model.addColumn("Batch Id");
        model.addColumn("Course Name");
        model.addColumn("Start Date");
        model.addColumn("Batch Time");
        model.addColumn("Trainer Name");
        add(scroll);


        ImageIcon img=new ImageIcon("C:\\Users\\Admin\\IdeaProjects\\myProject\\src\\InstituteProject\\img5.jpg");
        JLabel background=new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,600,680);

        setLayout(null);
        setSize(600,680);
        setVisible(true);
        setTitle("Batch");
        setLocation(350,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(background);
        addMouseListener(this);

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

                try
                {
                    stmt = con.createStatement();
                    rs = stmt.executeQuery("select tname from trainer");
                    while(rs.next())
                    {
                        String t1 = rs.getString("tname");
                        cbtname.addItem(t1);

                    }
                }
                catch(Exception e8)
                {
                    JOptionPane.showMessageDialog(null, e8);
                }
            }
            catch(Exception e7)
            {
                JOptionPane.showMessageDialog(null, e7);
            }
        }
        catch (Exception e1)
        {
            JOptionPane.showMessageDialog(null,e1);
        }
    }

    public boolean validateFields()
    {
        if(txtbid.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Batch id...");
            return false;
        }
        else if(!txtbid.getText().matches("^[0-9]+"))
        {
            JOptionPane.showMessageDialog(null,"Enter valid Id. Only contains number","alert",JOptionPane.WARNING_MESSAGE);
        }
        else if (cbcname.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Select Course Name","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtsd.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Date...");
            return false;
        }
        else if(!txtsd.getText().matches("^[0-3][0-9][/]+[0-1][0-2][/]+[0-9]{4}"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Date","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if(txtbtime.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(null,"Enter Time...");
            return false;
        }
        else if(!txtbtime.getText().matches("^[0-9][0-9][:][0-9][0-9][-][0-9][0-9][:][0-9][0-9]"))
        {
            JOptionPane.showMessageDialog(null,"Invalid Time","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        else if (cbtname.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(null, "Please Select Trainer Name","alert",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new Batch();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnnew == e.getSource())
        {
            try
            {
                String query = "Select MAX(bid) as max_bid from batch";
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    int no = rs.getInt("max_bid");
                    no = no + 1;
                    txtbid.setText(Integer.toString(no));
                    System.out.println(no);
                    cbcname.setSelectedIndex(0);
                    txtsd.setText(null);
                    txtbtime.setText(null);
                    cbtname.setSelectedIndex(0);
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
                    bid = Integer.parseInt(txtbid.getText());
                    cname = cbcname.getSelectedItem().toString();
                    sdate = txtsd.getText();
                    btime = txtbtime.getText();
                    tname = cbtname.getSelectedItem().toString();
                    stmt = con.createStatement();
                    String sql = "insert into batch values(" + bid + ",'" + cname + "','" + sdate + "','" + btime + "','" + tname + "')";
                    int i = stmt.executeUpdate(sql);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Save Successfully");
                    } else {
                        JOptionPane.showMessageDialog(null, "Not Save Successfully");
                    }

                } catch (Exception e3) {
                    JOptionPane.showMessageDialog(null, e3);
                }
            }
        }

        if(btnupdate == e.getSource())
        {
            try
            {
                bid = Integer.parseInt(txtbid.getText());
                cname = cbcname.getSelectedItem().toString();
                sdate = txtsd.getText();
                btime = txtbtime.getText();
                tname = cbtname.getSelectedItem().toString();
                stmt = con.createStatement();
                String sql = "update batch set cname = '"+ cname +"',startDate = '"+ sdate +"',batchtime = '"+ btime +"',trainerName = '"+ tname +"' where bid = "+ bid +" ";
                int i = stmt.executeUpdate(sql);
                if(i>0)
                {
                    JOptionPane.showMessageDialog(null,"Update Successfully");
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Not Update Successfully");
                }

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
                bid = Integer.parseInt(txtbid.getText());
                sql = "delete from batch where bid = "+ bid +" ";
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
                rs = stmt.executeQuery("select * from batch");
                while(rs.next())
                {
                    model.insertRow(r++ ,new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)});
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

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

//        DefaultTableModel dtm = (DefaultTableModel) tbl.getModel();
//        int selectedRowIndex = tbl.getSelectedRow();
//        int bid = (Integer)dtm.getValueAt(selectedRowIndex,0);
//        try
//        {
//            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            rs = stmt.executeQuery("select * from batch where bid = "+ bid +" ");
//            while(rs.next())
//            {
//                txtbid.setText(""+rs.getInt(1));
//                cbcname.setSelectedIndex(getSelectedItem().toString());
//            }
//        }
//        catch (Exception e9)
//        {
//            JOptionPane.showMessageDialog(null,e9);
//        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
