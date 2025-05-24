/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gradecalculator;

/**
 *
 * @author Admin
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GradeCalculator extends JFrame{
    
    private JTextField ms1Txt, ms2Txt, taTxt;
    private JButton submitButton;
    private JLabel ms1Label, ms2Label, taLabel, totalGradeLabel;
    
    public GradeCalculator(){
    
        setTitle("Grade Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10); //padding

        int row = 0;
        
        addLabelAndField("Milestone 1 Grade 25% : ", ms1Txt = new JTextField(), row++, gbc);
        addLabelAndField("Milestone 2 Grade 40% : ", ms2Txt = new JTextField(), row++, gbc);
        addLabelAndField("TA Grade 35% : ", taTxt = new JTextField(), row++, gbc);
        
        // Submit button
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        submitButton = new JButton("Calculate Grade");
        add(submitButton, gbc);

        // Output labels
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // center the button
        gbc.fill = GridBagConstraints.NONE;
        ms1Label = new JLabel(" ");
        ms2Label = new JLabel(" ");
        taLabel = new JLabel(" ");
        totalGradeLabel = new JLabel(" ");

        gbc.gridy = row++; add(ms1Label, gbc);
        gbc.gridy = row++; add(ms2Label, gbc);
        gbc.gridy = row++; add(taLabel, gbc);
        gbc.gridy = row++; add(totalGradeLabel, gbc);
        

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateGrade();   
            }
        });
        
        setVisible(true);
        
    }

    
    private void calculateGrade(){
    
        String ms1Grade = ms1Txt.getText().trim();
        String ms2Grade = ms2Txt.getText().trim();
        String taGrade = taTxt.getText().trim();
        
        if (ms1Grade.isEmpty() || ms2Grade.isEmpty() || taGrade.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
        try{
            int ms1GradeInteger = Integer.parseInt(ms1Grade);
            
            if(ms1GradeInteger < 0 || ms1GradeInteger > 25) throw new NumberFormatException("Milestone 1 grade should not be less than 0 or greater than 25");
            
            int ms2GradeInteger = Integer.parseInt(ms2Grade);
            
            if(ms2GradeInteger < 0 || ms2GradeInteger > 40) throw new NumberFormatException("Milestone 2 grade should not be less than 0 or greater than 40");
            
            int taGradeInteger = Integer.parseInt(taGrade);
            
            if(taGradeInteger < 0 || taGradeInteger > 35) throw new NumberFormatException("Milestone 1 grade should not be less than 0 or greater than 35");
            
            int totalGrade = taGradeInteger + ms1GradeInteger + ms2GradeInteger;
            
            ms1Label.setText("Milestone 1 Grade: " + ms1Grade);
            ms2Label.setText("Milestone 2 Grade: " + ms2Grade);
            taLabel.setText("TA Grade: " + taGrade);
            totalGradeLabel.setText("Total Grade: " + Integer.toString(totalGrade));
            
            
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
            "Please enter valid numeric values:\n- Grades should not be less than 0 or greater than their percentage \n",
            "Input Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private void addLabelAndField(String labelText, JTextField field, int row, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        field.setPreferredSize(new Dimension(250, 25));
        add(field, gbc);
    }
    

    public static void main(String[] args) {
        new GradeCalculator();
    }
}
