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
    private JButton submitButton, clearButton;
    
    
    public GradeCalculator(){
    
        setTitle("Grade Calculator");
        setSize(325, 275);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10); //padding

        int row = 0;
        
        addLabelAndField("Milestone 1 Grade 25% : ", ms1Txt = new JTextField(), row++, gbc);
        addLabelAndField("Milestone 2 Grade 40% : ", ms2Txt = new JTextField(), row++, gbc);
        addLabelAndField("TA Grade 35% : ", taTxt = new JTextField(), row++, gbc);
        
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        submitButton = new JButton("Calculate Grade");
        clearButton = new JButton("Clear");

        buttonPanel.add(clearButton);
        buttonPanel.add(submitButton);

        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2; 
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE; 
        add(buttonPanel, gbc);

        

         // Button actions
        submitButton.addActionListener(e -> calculateGrade());
        clearButton.addActionListener(e -> clearFields());
        
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
    
    private void clearFields(){
        ms1Txt.setText("");
        ms2Txt.setText("");
        taTxt.setText("");
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
            
            JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));

            gradePanel.add(new JLabel("Milestone 1 Grade: " + ms1Grade +"%"));
            gradePanel.add(new JLabel("Milestone 2 Grade: " + ms2Grade+"%"));
            gradePanel.add(new JLabel("TA Grade: " + taGrade+"%"));
            
            
            gradePanel.add(Box.createVerticalStrut(10)); 
            gradePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
            gradePanel.add(Box.createVerticalStrut(10)); 
            
            gradePanel.add(new JLabel("Total Grade: " + totalGrade+"%"));
             gradePanel.add(Box.createVerticalStrut(10)); 

            JOptionPane.showMessageDialog(this, gradePanel, "Grade Summary", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            
            
        }
        catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this,
            "Please enter valid numeric values:\n- Grades should not be less than 0 or greater than their percentage \n",
            "Input Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
     private void addLabelAndField(String labelText, JTextField field, int row, GridBagConstraints gbc) {
    gbc.gridy = row;
    gbc.gridwidth = 1;

    gbc.gridx = 0;
    gbc.weightx = 0;
    gbc.anchor = GridBagConstraints.EAST;
    add(new JLabel(labelText), gbc);

    
    gbc.gridx = 1;
    gbc.weightx = 1;
    gbc.anchor = GridBagConstraints.WEST;
    field.setPreferredSize(new Dimension(100, 25)); 
    add(field, gbc);
}
    

    public static void main(String[] args) {
        new GradeCalculator();
    }
}
