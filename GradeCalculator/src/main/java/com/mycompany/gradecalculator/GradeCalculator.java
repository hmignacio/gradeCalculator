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

public class GradeCalculator extends JFrame{
    
    // JTextField is used for grade input, while JButton handles calculation and clearing actions
    private JTextField ms1Txt, ms2Txt, taTxt;
    private JButton submitButton, clearButton;
    
    
    // Constructor sets up GUI components and layout
    public GradeCalculator(){
    
        setTitle("Grade Calculator");
        setSize(325, 275);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10); // padding

        int row = 0;
        
        
        // Add input fields for grades
        addLabelAndField("Milestone 1 (25%) : ", ms1Txt = new JTextField(), row++, gbc);
        addLabelAndField("Milestone 2 (40%) : ", ms2Txt = new JTextField(), row++, gbc);
        addLabelAndField("Terminal Assessment (35%) : ", taTxt = new JTextField(), row++, gbc);
        
        
        // Panel for Calculate Grade Button and Clear Button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        submitButton = new JButton("Calculate");
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
        
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
        
    }
    
    // Clears all input fields
    private void clearFields(){
        ms1Txt.setText("");
        ms2Txt.setText("");
        taTxt.setText("");
    }
    
    // Validates input, calculates total grade, and displays summary of grade in a dialog
    private void calculateGrade(){
    
        String ms1Grade = ms1Txt.getText().trim();
        String ms2Grade = ms2Txt.getText().trim();
        String taGrade = taTxt.getText().trim();
        
        // Check for empty inputs
        if (ms1Grade.isEmpty() || ms2Grade.isEmpty() || taGrade.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
        try{
            int ms1GradeInteger = Integer.parseInt(ms1Grade);            
            if(ms1GradeInteger < 0 || ms1GradeInteger > 25) throw new NumberFormatException("Milestone 1 grade should not be less than 0 or greater than 25");           
            int ms2GradeInteger = Integer.parseInt(ms2Grade);
            
            
            // Validate input ranges
            if(ms2GradeInteger < 0 || ms2GradeInteger > 40) throw new NumberFormatException("Milestone 2 grade should not be less than 0 or greater than 40");
            
            int taGradeInteger = Integer.parseInt(taGrade);
            
            if(taGradeInteger < 0 || taGradeInteger > 35) throw new NumberFormatException("Milestone 1 grade should not be less than 0 or greater than 35");
            
            int totalGrade = taGradeInteger + ms1GradeInteger + ms2GradeInteger;
            
            
            // Prepare a panel to display grade summary
            JPanel gradePanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
            gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));

            gradePanel.add(new JLabel("Milestone 1 Grade: " + ms1Grade +"%"));
            gradePanel.add(new JLabel("Milestone 2 Grade: " + ms2Grade+"%"));
            gradePanel.add(new JLabel("Terminal Assessment Grade: " + taGrade+"%"));
            
            
            gradePanel.add(Box.createVerticalStrut(10)); 
            gradePanel.add(new JSeparator(SwingConstants.HORIZONTAL));
            gradePanel.add(Box.createVerticalStrut(10)); 
            
            gradePanel.add(new JLabel("Total Grade: " + totalGrade+"%"));
             gradePanel.add(Box.createVerticalStrut(10)); 
            
            // Show grade summary in a pop-up dialog
            JOptionPane.showMessageDialog(this, gradePanel, "Grade Summary", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
            
            
        }
        catch(NumberFormatException ex){
            // Show error message for invalid input
            JOptionPane.showMessageDialog(this,
            "Please enter valid numbers between 0 and \nthe maximum allowed points for each grading category.",
            "Input Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Adds a label and corresponding text field to the form
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
        field.setPreferredSize(new Dimension(100, 25)); // Set field size
        add(field, gbc);
    }

    
    public static void main(String[] args) {
        new GradeCalculator();
    }
}
