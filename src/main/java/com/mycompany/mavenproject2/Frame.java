/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author BSmoke
 */
import static com.mycompany.mavenproject2.Program.AStarsearch;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.JTextField;import java.awt.*;
import java.awt.event.*;
public class Frame extends JFrame implements ActionListener {
 public static boolean isNumeric(String strNum) {
 if (strNum == null) {
 return false;
 }
 try {
 double d = Double.parseDouble(strNum);
 } catch (NumberFormatException nfe) {
 return false;
 }
 return true;
}
private JButton button = new JButton("Enter");
JTextField input1 = new JTextField( 5);

private JLabel label = new JLabel("Sourse y");
private JTextField input2 = new JTextField(5);
 private JLabel label2 = new JLabel("Destination");
 private JTextField input3 = new JTextField(5);
private JTextField input4 = new JTextField(5);
 private JTextField input5 = new JTextField("Ответ",20);
public Frame() {
 super("Simple Example");
 this.setBounds(100,100,250,100);
 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 input1.setName("i1");
 input2.setName("i2");
 input3.setName("i3");
 input4.setName("i4");
 input5.setName("i5");
 button.setName("Enter");
 Container container = this.getContentPane();
 container.setLayout(new GridLayout(3,2,2,2));
 container.add(label);
 container.add(input1);
 container.add(input2);
 container.add(label2);
 container.add(input3);
 container.add(input4);
 container.add(input5);
 ButtonGroup group = new ButtonGroup();
 button.addActionListener(new ButtonEventListener());
 container.add(button);
}
 @Override
 public void actionPerformed(ActionEvent e) {
 throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools |

 }
class ButtonEventListener implements ActionListener {
public void actionPerformed(ActionEvent e) {
String message = "";
if(!isNumeric(input1.getText())||!isNumeric(input2.getText())||!isNumeric(input3.getText())||!isNumeric(input4.getText())){
message = "Иди нахуй";
JOptionPane.showMessageDialog(null,
 message,
 "Output",
 JOptionPane.PLAIN_MESSAGE);}
 else {
 int grid[][] =
{
{ 1, 1, 1, 1, 1, 0, 1, 1, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 0, 1, 1 },
{ 1, 1, 1, 1, 1, 0, 1, 1, 0, 1 },
{ 1, 1, 1, 1, 1, 0, 0, 0, 0, 1 },
{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }
};
// Source is the left-most bottom-most corner

point src= new point(Integer.parseInt(input1.getText()),Integer.parseInt(input2.getText()));
 System.out.print(Double.MAX_VALUE);
 System.out.printf("%d, %d",grid.length, grid[0].length);
point dest = new point(Integer.parseInt(input3.getText()),Integer.parseInt(input4.getText()));
System.out.print(AStarsearch(grid, src, dest));
 String str =AStarsearch(grid, src, dest);
 input5.setText(str);
 }

 }
}
}