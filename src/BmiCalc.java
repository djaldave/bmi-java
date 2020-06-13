

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BmiCalc {

    private JFrame frame;
    private JTextField tfFeet;
    private JTextField tfInches;
    private JTextField tfWeight;
    private JTextField tfOutput;

    /**
     * Launch the application.
     */


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BmiCalc window = new BmiCalc();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public BmiCalc() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {



        frame = new JFrame();
        frame.setBounds(100, 100, 450, 410);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 408, 212);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        panel_1.setBounds(10, 11, 388, 97);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel label_1 = new JLabel("Height:");
        label_1.setHorizontalAlignment(SwingConstants.RIGHT);
        label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        label_1.setBounds(58, 11, 89, 20);
        panel_1.add(label_1);

        JLabel label_2 = new JLabel("Feet");
        label_2.setHorizontalAlignment(SwingConstants.RIGHT);
        label_2.setBounds(128, 42, 46, 14);
        panel_1.add(label_2);

        tfFeet = new JTextField();
        tfFeet.setColumns(10);
        tfFeet.setBounds(184, 39, 109, 20);
        panel_1.add(tfFeet);

        tfInches = new JTextField();
        tfInches.setColumns(10);
        tfInches.setBounds(184, 66, 109, 20);
        panel_1.add(tfInches);

        JLabel lblInch = new JLabel("inches");
        lblInch.setHorizontalAlignment(SwingConstants.RIGHT);
        lblInch.setBounds(128, 69, 46, 14);
        panel_1.add(lblInch);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 3));
        panel_2.setBounds(10, 126, 388, 75);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel label = new JLabel("Weight:");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setFont(new Font("Tahoma", Font.BOLD, 16));
        label.setBounds(55, 11, 91, 20);
        panel_2.add(label);

        tfWeight = new JTextField();
        tfWeight.setColumns(10);
        tfWeight.setBounds(182, 34, 109, 20);
        panel_2.add(tfWeight);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tfFeet.setText("");
                tfInches.setText("");
                tfWeight.setText("");
                tfOutput.setText("");
            }
        });
        btnClear.setBounds(84, 276, 101, 31);
        frame.getContentPane().add(btnClear);

        JButton btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(!(tfFeet.getText().equals("") || tfWeight.getText().equals("")) && !tfInches.getText().equals("")){
                    try {
                        Double BmiCalculation = new BmiCalculation().conversion(Double.parseDouble(tfWeight.getText()),Double.parseDouble(tfFeet.getText()),Double.parseDouble( tfInches.getText()));
                        tfOutput.setText(new BmiCalculation().display(Double.parseDouble(BmiCalculation.toString())));

                    }catch(Exception e1) {
                        JOptionPane.showMessageDialog(null, "incorrect value  entered");
                    }
                }else if("".equals(tfInches.getText())) {
                    try {
                        Double BmiCalculation = new BmiCalculation().conversion(Double.parseDouble(tfWeight.getText()),Double.parseDouble(tfFeet.getText()));
                        tfOutput.setText(new BmiCalculation().display(Double.parseDouble(BmiCalculation.toString())));
                    }catch(Exception e2) {
                        JOptionPane.showMessageDialog(null, "incorrect value  entered");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "no value entered");
                }
            }
        });
        btnCalculate.setBounds(231, 276, 101, 31);
        frame.getContentPane().add(btnCalculate);

        tfOutput = new JTextField();
        tfOutput.setFont(new Font("Source Sans Pro", Font.BOLD, 12));
        tfOutput.setBorder(new LineBorder(new Color(171, 173, 179), 2, true));
        tfOutput.setEditable(false);
        tfOutput.setBounds(174, 234, 158, 31);
        frame.getContentPane().add(tfOutput);
        tfOutput.setColumns(10);

        JLabel lblOutput = new JLabel("Output:");
        lblOutput.setFont(new Font("Source Sans Pro", Font.BOLD, 16));
        lblOutput.setBounds(84, 234, 64, 31);
        frame.getContentPane().add(lblOutput);

        JPanel panel_3 = new JPanel();
        panel_3.setBounds(10, 327, 408, 28);
        frame.getContentPane().add(panel_3);
        panel_3.setLayout(null);

        JLabel lblNewLabel = new JLabel("BMI Calculator");
        lblNewLabel.setFont(new Font("Tempus Sans ITC", Font.BOLD, 14));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(138, 0, 128, 28);
        panel_3.add(lblNewLabel);
    }
}
interface Calculation  {

    double conversion(double weight, double feet, double inches);
    double conversion(double weight, double feet);
}
class BmiCalculation implements Calculation{


    @Override
    public double conversion(double weight,double feet, double inches) {


        double heightInMeters = (((feet * 12) + inches) * .0254);
        double bmi = weight / Math.pow(heightInMeters, 2.0);
        return Math.round(bmi * 10.0) / 10.0;
    }

    @Override
    public double conversion(double weight, double feet) {

        double heightInMeters = (((feet * 12) + 0) * .0254);
        double bmi = weight / Math.pow(heightInMeters, 2.0);
        return Math.round(bmi * 10.0) / 10.0;
    }

    public String display(double bmi) {
        String m = null ;
        if (bmi < 18.5 ) {
            m = (bmi+ " -> Underweight");
        }

        else if (bmi >= 18.5 && bmi < 25) {
            m =(bmi+ " -> Normal");
        }

        else if (bmi >= 25 && bmi < 30) {
            m = (bmi+ " -> Overweight");
        }

        else if (bmi >= 30) {
            m = (bmi+ " -> Obese");
        }
        return m;
    }
}
