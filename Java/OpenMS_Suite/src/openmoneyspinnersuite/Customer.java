
// Open MoneySpinner Suite v1
// An open source business management software system written in Java and MySQL
// Recommended IDE is NetBeans IDE 7.0.1
// Support Web Site: http://www.milliscript.com
//
// Copyright (C) 2014, Abiodun Aremu, Ibadan/NIGERIA.
// Open MoneySpinner Suite is distributed under the terms of the Apache License version 2.0
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/*
 * Customer.java
 *
 * Created on Jan 27, 2011, 10:40:46 PM
 */

package openmoneyspinnersuite;

/**
 *
 * @author Abiodun Aremu
 */
import javax.swing.*;
import java.sql.*;

final class Customer extends javax.swing.JInternalFrame {
PreparedStatement statement;
Systems systems=new Systems();
Thread customerThread=new Thread();
Thread customerTitleThread=new Thread();
    /** Creates new form Customer */
     Customer() {
        initComponents();
        resetCountry();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jAddressLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jCountryComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jAddressTextArea = new javax.swing.JTextArea();
        jRegisterButton = new javax.swing.JButton();
        jNameTextField = new javax.swing.JTextField();
        jPhoneTextField = new javax.swing.JTextField();
        jZipTextField = new javax.swing.JTextField();
        jRegNoTextField = new javax.swing.JTextField();
        jCityTextField = new javax.swing.JTextField();
        jStateTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setClosable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(Customer.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setVisible(true);

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jAddressLabel.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jAddressLabel.setText(resourceMap.getString("jAddressLabel.text")); // NOI18N
        jAddressLabel.setName("jAddressLabel"); // NOI18N

        jLabel6.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jLabel7.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setFont(resourceMap.getFont("jLabel3.font")); // NOI18N
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jCountryComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Select --", "Item 2", "Item 3", "Item 4" }));
        jCountryComboBox.setName("jCountryComboBox"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jAddressTextArea.setColumns(20);
        jAddressTextArea.setLineWrap(true);
        jAddressTextArea.setRows(5);
        jAddressTextArea.setText(resourceMap.getString("jAddressTextArea.text")); // NOI18N
        jAddressTextArea.setName("jAddressTextArea"); // NOI18N
        jAddressTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jAddressTextAreaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(jAddressTextArea);

        jRegisterButton.setIcon(resourceMap.getIcon("jRegisterButton.icon")); // NOI18N
        jRegisterButton.setText(resourceMap.getString("jRegisterButton.text")); // NOI18N
        jRegisterButton.setName("jRegisterButton"); // NOI18N
        jRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRegisterButtonActionPerformed(evt);
            }
        });

        jNameTextField.setText(resourceMap.getString("jNameTextField.text")); // NOI18N
        jNameTextField.setName("jNameTextField"); // NOI18N
        jNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNameTextFieldActionPerformed(evt);
            }
        });
        jNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jNameTextFieldKeyTyped(evt);
            }
        });

        jPhoneTextField.setText(resourceMap.getString("jPhoneTextField.text")); // NOI18N
        jPhoneTextField.setName("jPhoneTextField"); // NOI18N
        jPhoneTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPhoneTextFieldKeyTyped(evt);
            }
        });

        jZipTextField.setText(resourceMap.getString("jZipTextField.text")); // NOI18N
        jZipTextField.setName("jZipTextField"); // NOI18N
        jZipTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jZipTextFieldKeyTyped(evt);
            }
        });

        jRegNoTextField.setText(resourceMap.getString("jRegNoTextField.text")); // NOI18N
        jRegNoTextField.setName("jRegNoTextField"); // NOI18N
        jRegNoTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jRegNoTextFieldKeyTyped(evt);
            }
        });

        jCityTextField.setText(resourceMap.getString("jCityTextField.text")); // NOI18N
        jCityTextField.setName("jCityTextField"); // NOI18N
        jCityTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jCityTextFieldKeyTyped(evt);
            }
        });

        jStateTextField.setText(resourceMap.getString("jStateTextField.text")); // NOI18N
        jStateTextField.setName("jStateTextField"); // NOI18N
        jStateTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jStateTextFieldKeyTyped(evt);
            }
        });

        jLabel5.setFont(resourceMap.getFont("jLabel5.font")); // NOI18N
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jAddressLabel)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPhoneTextField)
                                    .addComponent(jScrollPane1)
                                    .addComponent(jNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))))
                        .addGap(69, 69, 69)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(196, 196, 196))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jZipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCountryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jStateTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jCityTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                    .addComponent(jRegNoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(187, 187, 187)
                        .addComponent(jRegisterButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jAddressLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jRegNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jStateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(jCountryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jPhoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jZipTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jRegisterButton, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNameTextFieldActionPerformed

    private void jRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRegisterButtonActionPerformed
        // TODO add your handling code here:
        String value=jPhoneTextField.getText().trim();
        int x=value.length();
        int y=0;
        char[] ch=value.toCharArray();

        if(jNameTextField.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter Customer's Name ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(jAddressTextArea.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter Customer's Address ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(jPhoneTextField.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter Customer's Phone Number ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }


        while(y<x)
        {
        try
        {
            if(Character.isDigit(ch[y]))
            {}
            else{
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Customer's Phone Number Must Be in Digits","ERROR !",JOptionPane.ERROR_MESSAGE);
            return; }
        }
        catch(Exception e)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Phone Number Can Not Contain Alphabets","ERROR !",JOptionPane.ERROR_MESSAGE);
        jPhoneTextField.setText("");
        System.out.println(e+" Non int available in "+value+" non int is "+ch[y]);
        return;
        }
        y++;
        }


        if(jCityTextField.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter Customer's City ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(jStateTextField.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter Customer's State ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(jCountryComboBox.getSelectedItem().equals("-- Select --"))
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Select Customer's Country ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {
            /* Execute submit thread */
            customerThread=new Thread(new Runnable() {
                public void run() {
                    disableControls();
                    submitData();
                    reset();
                }
            });
            customerTitleThread=new Thread(new Runnable() {
                public void run() {
                    while(customerThread.isAlive())setProcessingTitle(customerTitleThread);
                    enableControls();
                    System.out.println("customerThread stopped runing.");
                }
            });
            customerThread.start();
            customerTitleThread.start();
        }
    }//GEN-LAST:event_jRegisterButtonActionPerformed
                               
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. New Customer - Processing Request");
            thread.sleep(500);
            this.setTitle("::. New Customer - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. New Customer - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. New Customer - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println("Title thread"+e);}
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(customerThread.isAlive())
        {
            try{
                customerThread.stop();
                customerTitleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. New Customer");
            enableControls();
        }
        else
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jNameTextFieldKeyTyped
        // TODO add your handling code here:
        String value=jNameTextField.getText();
        if(value.length()>39)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (40) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 39);
        jNameTextField.setText(newValue);
        }
    }//GEN-LAST:event_jNameTextFieldKeyTyped

    private void jAddressTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jAddressTextAreaKeyTyped
        // TODO add your handling code here:
        String value=jAddressTextArea.getText();
        if(value.length()>79)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (80) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 79);
        jAddressTextArea.setText(newValue);
        }
    }//GEN-LAST:event_jAddressTextAreaKeyTyped

    private void jCityTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCityTextFieldKeyTyped
        // TODO add your handling code here:
        String value=jCityTextField.getText();
        if(value.length()>14)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (15) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 14);
        jCityTextField.setText(newValue);
        }
    }//GEN-LAST:event_jCityTextFieldKeyTyped

    private void jStateTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jStateTextFieldKeyTyped
        // TODO add your handling code here:
        String value=jStateTextField.getText();
        if(value.length()>14)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (15) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 14);
        jStateTextField.setText(newValue);
        }
    }//GEN-LAST:event_jStateTextFieldKeyTyped

    private void jPhoneTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPhoneTextFieldKeyTyped
        // TODO add your handling code here:
        String value=jPhoneTextField.getText();
        if(value.length()>14)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (15) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 14);
        jPhoneTextField.setText(newValue);
        }
    }//GEN-LAST:event_jPhoneTextFieldKeyTyped

    private void jZipTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jZipTextFieldKeyTyped
        // TODO add your handling code here:
        String value=jZipTextField.getText();
        if(value.length()>7)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (8) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 7);
        jZipTextField.setText(newValue);
        }
    }//GEN-LAST:event_jZipTextFieldKeyTyped

    private void jRegNoTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jRegNoTextFieldKeyTyped
        // TODO add your handling code here:
        String value=jRegNoTextField.getText();
        if(value.length()>9)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (10) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 9);
        jRegNoTextField.setText(newValue);
        }
    }//GEN-LAST:event_jRegNoTextFieldKeyTyped

     void submitData()
    {
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+" prnInsCustomer"+Connect.procInitStart+"?,?,?,?,?,?,?,?"+Connect.procInitEnd);
            try{
            statement.setString(1,jNameTextField.getText());
            statement.setString(2,jAddressTextArea.getText());
            statement.setString(3,jCityTextField.getText());
            statement.setString(4,jStateTextField.getText());
            statement.setString(5,String.valueOf(jCountryComboBox.getSelectedItem()));
            statement.setString(6,jZipTextField.getText());
            statement.setString(7,jPhoneTextField.getText());
            statement.setString(8,jRegNoTextField.getText());

            }
            catch(Exception e)
            {
            System.out.println("Customer.submitData() gave error ! "+e);
            }
        Connect.alert="Y";
        Connect.executeUpdateStatement(statement);
        Connect.closeConnection();
    }
     void resetCountry()
    {
        String[] country=systems.getTableDataArray(OpenMSApp.Database_A,"Country",2);
        country[0]="-- Select --";

        jCountryComboBox.setModel(new DefaultComboBoxModel(country));
        jCountryComboBox.setSelectedItem("NIGERIA");
    }
     void reset(){
        jNameTextField.setText("");
        jAddressTextArea.setText("");
        jCityTextField.setText("");
        jStateTextField.setText("");
        jZipTextField.setText("");
        jPhoneTextField.setText("");
        jRegNoTextField.setText("");
        resetCountry();
    }
     private void disableControls(){
        jNameTextField.setEnabled(false);
        jNameTextField.setEditable(false);
        
        jAddressTextArea.setEnabled(false);
        jAddressTextArea.setEditable(false);
        
        jCityTextField.setEnabled(false);
        jCityTextField.setEditable(false);
        
        jStateTextField.setEnabled(false);
        jStateTextField.setEditable(false);
        
        jZipTextField.setEnabled(false);
        jZipTextField.setEditable(false);
        
        jPhoneTextField.setEnabled(false);
        jPhoneTextField.setEditable(false);
        
        jRegNoTextField.setEnabled(false);
        jRegNoTextField.setEditable(false);
        
        jCountryComboBox.setEnabled(false);
        jRegisterButton.setEnabled(false);
    }
     private void enableControls(){
        jNameTextField.setEnabled(true);
        jNameTextField.setEditable(true);
        
        jAddressTextArea.setEnabled(true);
        jAddressTextArea.setEditable(true);
        
        jCityTextField.setEnabled(true);
        jCityTextField.setEditable(true);
        
        jStateTextField.setEnabled(true);
        jStateTextField.setEditable(true);
        
        jZipTextField.setEnabled(true);
        jZipTextField.setEditable(true);
        
        jPhoneTextField.setEnabled(true);
        jPhoneTextField.setEditable(true);
        
        jRegNoTextField.setEnabled(true);
        jRegNoTextField.setEditable(true);
        
        jCountryComboBox.setEnabled(true);
        jRegisterButton.setEnabled(true);
        this.setTitle("::. New Customer");
    }
     void resetCombo()
    {
        resetCountry();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jAddressLabel;
    private javax.swing.JTextArea jAddressTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JTextField jCityTextField;
    private javax.swing.JComboBox jCountryComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField jNameTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jPhoneTextField;
    private javax.swing.JTextField jRegNoTextField;
    private javax.swing.JButton jRegisterButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jStateTextField;
    private javax.swing.JTextField jZipTextField;
    // End of variables declaration//GEN-END:variables

}