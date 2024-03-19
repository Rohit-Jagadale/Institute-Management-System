package InstituteProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Institute extends JFrame implements ActionListener
{
    JMenuBar mb;
//    JMenu menu;
    JMenuItem course, trainer, batch, enquiry,admission;
//    JMenu course, trainer, batch, enquiry,admission;
    Institute()
    {
        mb = new JMenuBar();

//        menu = new JMenu("Institute Project");
//
//        course = new JMenuItem("Course");
//        trainer = new JMenuItem("Trainer");
//        batch = new JMenuItem("Batch");
//        enquiry = new JMenuItem("Enquiry");
//        admission = new JMenuItem("Admission");

//        menu.add(course);
//        menu.add(trainer);
//        menu.add(batch);
//        menu.add(enquiry);
//        menu.add(admission);
//
//        course.addActionListener(this);
//        trainer.addActionListener(this);
//        batch.addActionListener(this);
//        enquiry.addActionListener(this);
//        admission.addActionListener(this);
//
//        mb.add(menu);

        course = new JMenuItem("Course");
        trainer = new JMenuItem("Trainer");
        batch = new JMenuItem("Batch");
        enquiry = new JMenuItem("Enquiry");
        admission = new JMenuItem("Admission");

        mb.add(course);
        mb.add(trainer);
        mb.add(batch);
        mb.add(enquiry);
        mb.add(admission);

        course.addActionListener(this);
        trainer.addActionListener(this);
        batch.addActionListener(this);
        enquiry.addActionListener(this);
        admission.addActionListener(this);


        setJMenuBar(mb);
        setLayout(null);
        setSize(600,60);
        setVisible(true);
        setTitle("Institute Project");
        setLocation(350,0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args)
    {
        new Institute();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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

        }
    }

