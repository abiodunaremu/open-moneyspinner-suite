
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

package openmoneyspinnersuite;

/**
 *
 * @author Abiodun Aremu
 */
import javax.swing.*;
import java.sql.*;

final class EditLogin extends javax.swing.JInternalFrame {
    PreparedStatement statement;
    ResultSet result;
    Systems systems=new Systems();
    Thread processThread=new Thread();
    Thread titleThread=new Thread();

    String dbName="";
    String dbChatAlias="";
    String dbPassword="";
    String dbPlatform="";

    /** Creates new form Customer */
     EditLogin() {
        initComponents();
            /* Create and display the form */
            processThread=new Thread(new Runnable() {
                public void run() {
                    disableControls();
                    reset();
                }
            });
            titleThread=new Thread(new Runnable() {
                public void run() {
                    while(processThread.isAlive())setProcessingTitle(titleThread);
                    enableControls();
                    System.out.println("editLoginThread stopped runing.");
                }
            });
            processThread.start();
            titleThread.start();
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
        jLabel4 = new javax.swing.JLabel();
        jAddressLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jUpdateButton = new javax.swing.JButton();
        jNameTextField = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jCheckBox3 = new javax.swing.JCheckBox();
        jChatAliasTextField = new javax.swing.JTextField();
        jPasswordField = new javax.swing.JPasswordField();
        jConfirmPasswordField = new javax.swing.JPasswordField();
        jLabel6 = new javax.swing.JLabel();
        systemComboBox = new javax.swing.JComboBox();
        LoginCodeComboBox = new javax.swing.JComboBox();

        setClosable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(EditLogin.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setRequestFocusEnabled(false);
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jAddressLabel.setText(resourceMap.getString("jAddressLabel.text")); // NOI18N
        jAddressLabel.setName("jAddressLabel"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jUpdateButton.setFont(resourceMap.getFont("jUpdateButton.font")); // NOI18N
        jUpdateButton.setIcon(resourceMap.getIcon("jUpdateButton.icon")); // NOI18N
        jUpdateButton.setText(resourceMap.getString("jUpdateButton.text")); // NOI18N
        jUpdateButton.setName("jUpdateButton"); // NOI18N
        jUpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jUpdateButtonActionPerformed(evt);
            }
        });

        jNameTextField.setEditable(false);
        jNameTextField.setText(resourceMap.getString("jNameTextField.text")); // NOI18N
        jNameTextField.setName("jNameTextField"); // NOI18N
        jNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNameTextFieldActionPerformed(evt);
            }
        });

        jCheckBox2.setName("jCheckBox2"); // NOI18N
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        jCheckBox1.setName("jCheckBox1"); // NOI18N
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jButton2.setFont(resourceMap.getFont("jButton2.font")); // NOI18N
        jButton2.setIcon(resourceMap.getIcon("jButton2.icon")); // NOI18N
        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jCheckBox3.setName("jCheckBox3"); // NOI18N
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        jChatAliasTextField.setEditable(false);
        jChatAliasTextField.setName("jChatAliasTextField"); // NOI18N
        jChatAliasTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jChatAliasTextFieldKeyTyped(evt);
            }
        });

        jPasswordField.setEnabled(false);
        jPasswordField.setName("jPasswordField"); // NOI18N
        jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jPasswordFieldKeyTyped(evt);
            }
        });

        jConfirmPasswordField.setEnabled(false);
        jConfirmPasswordField.setName("jConfirmPasswordField"); // NOI18N
        jConfirmPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jConfirmPasswordFieldKeyTyped(evt);
            }
        });

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        systemComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Select --", "Finance", "Finance +", "HR", "Inventory", "Inventory +", "View +", "Pay Roll", "Pay Roll +", "Purchase", "Purchase +", "Student +", "Sales", "Sales +", "Admin" }));
        systemComboBox.setToolTipText(resourceMap.getString("systemComboBox.toolTipText")); // NOI18N
        systemComboBox.setEnabled(false);
        systemComboBox.setName("systemComboBox"); // NOI18N

        LoginCodeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Select --", "Item 2", "Item 3", "Item 4" }));
        LoginCodeComboBox.setName("LoginCodeComboBox"); // NOI18N
        LoginCodeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginCodeComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
                        .addComponent(jCheckBox2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jNameTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(jConfirmPasswordField, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(LoginCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCheckBox1)))
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jAddressLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jChatAliasTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jUpdateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(systemComboBox, 0, 201, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBox3)
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(systemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LoginCodeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jChatAliasTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jAddressLabel))
                            .addComponent(jCheckBox3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jConfirmPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jUpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jNameTextFieldActionPerformed

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed

        if(jCheckBox2.isSelected())
        {
            jPasswordField.setEnabled(true);
            jPasswordField.setEditable(true);
            jConfirmPasswordField.setEnabled(true);
            jConfirmPasswordField.setEditable(true);
        }
        else
        {
            jPasswordField.setEnabled(false);
            jPasswordField.setEditable(false);
            jConfirmPasswordField.setEnabled(false);
            jConfirmPasswordField.setEditable(false);
            jPasswordField.setText(dbPassword);
            jConfirmPasswordField.setText(dbPassword);

        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected())
        {
            jNameTextField.setEnabled(true);
            jNameTextField.setEditable(true);
        }
        else
        {
            jNameTextField.setEnabled(false);
            jNameTextField.setText(dbName);
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jUpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jUpdateButtonActionPerformed
        // TODO add your handling code here:

        if(LoginCodeComboBox.getSelectedItem().equals("-- Select --"))
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Select a Login Code ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(isCheckBoxesFalse()==false)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame()," No Data Has Been Edited ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
         if(jNameTextField.getText().isEmpty())
         {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter System User Username","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
         }
         if(jChatAliasTextField.getText().isEmpty())
         {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter System User Chat Alias","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
         }
         if(!String.valueOf(jPasswordField.getPassword()).equals(String.valueOf(jConfirmPasswordField.getPassword())))
         {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Password did not match","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
         }
        else
        {
            /* Create and display the form */
            processThread=new Thread(new Runnable() {
                public void run() {
                    disableControls();
                    submitData();
                    reset();
                }
            });
            titleThread=new Thread(new Runnable() {
                public void run() {
                    while(processThread.isAlive())setProcessingTitle(titleThread);
                    enableControls();
                    System.out.println("processThread stopped runing.");
                }
            });
            processThread.start();
            titleThread.start();
        }
    }//GEN-LAST:event_jUpdateButtonActionPerformed
                                      
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. Edit System User - Processing Request");
            thread.sleep(500);
            this.setTitle("::. Edit System User - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. Edit System User - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. Edit System User - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println("Title thread"+e);}
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if(processThread.isAlive())
        {
            try{
                processThread.stop();
                titleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. Edit System User");
            enableControls();
        }
        else
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed

        if(jCheckBox3.isSelected())
        {
            jChatAliasTextField.setEnabled(true);
            jChatAliasTextField.setEditable(true);
        }
        else
        {
            jChatAliasTextField.setEnabled(false);
            jChatAliasTextField.setEditable(false);
            jChatAliasTextField.setText(dbChatAlias);
        }
}//GEN-LAST:event_jCheckBox3ActionPerformed

private void jChatAliasTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jChatAliasTextFieldKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_jChatAliasTextFieldKeyTyped

private void jPasswordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldKeyTyped
        // TODO add your handling code here:
        String value=String.valueOf(jPasswordField.getPassword()).trim();
        if(value.length()>9)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (10) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 9);
        jPasswordField.setText(newValue);
        }
}//GEN-LAST:event_jPasswordFieldKeyTyped

private void jConfirmPasswordFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jConfirmPasswordFieldKeyTyped
// TODO add your handling code here:
}//GEN-LAST:event_jConfirmPasswordFieldKeyTyped

     void resetEmployeeID()
    {
        String[] employeeID=systems.getTableDataArray(OpenMSApp.Database_A,"Login",1);
        employeeID[0]="-- Select --";
        LoginCodeComboBox.setModel(new DefaultComboBoxModel(employeeID));
    }
private void LoginCodeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginCodeComboBoxActionPerformed
        // TODO add your handling code here:
        String loginCode=LoginCodeComboBox.getSelectedItem().toString();
        if(loginCode.equals("-- Select --"))
        {
             /* Execute submit thread */
            processThread=new Thread(new Runnable() {
                public void run() {
                disableControls();
                resetEmployeeID();
                resetFields();
                }
            });
            titleThread=new Thread(new Runnable() {
                public void run() {
                    while(processThread.isAlive())setProcessingTitle(titleThread);
                    enableControls();
                    System.out.println("NewUserThread stopped runing.");
                }
            });
            processThread.start();
            titleThread.start();
        }
        else
        {
            String login[]=systems.getValue(OpenMSApp.Database_A, "Select vUserName,vPassword,vChatAlias,cProcess,cEmployeeID from Login where cLoginCode= '"+loginCode+"'", 1,2,3,4,5);
            dbName=login[0].trim();
            jNameTextField.setText(dbName);
            dbPassword=login[1].trim();
            jPasswordField.setText(dbPassword);
            jConfirmPasswordField.setText(dbPassword);
            dbChatAlias=login[2].trim();
            jChatAliasTextField.setText(dbChatAlias);
            dbPlatform=login[3].trim();
            systemComboBox.setSelectedItem(dbPlatform);
            String employeeName[]=systems.getValue(OpenMSApp.Database_A,"Select vFirstName,vMiddleName,vlastName from Employee where cEmployeeID= '"+login[4]+"'",1,2,3);
 
            setTitle("::. Edit System User ( "+employeeName[0]+" "+employeeName[1]+" "+employeeName[2]+" )");
            
            resetCheckBoxes();
            disableFields();
        }
}//GEN-LAST:event_LoginCodeComboBoxActionPerformed

     void resetFields()
    {
            disableFields();
            jNameTextField.setText("");
            jChatAliasTextField.setText("");
            jPasswordField.setText("");
            jConfirmPasswordField.setText("");
    }
     void reset()
    {
        resetFields();
        resetCheckBoxes();
        resetCombo();
    }
     void resetCombo()
    {
        resetEmployeeID();
        systemComboBox.setSelectedItem("-- Select --");
    }
     void disableFields()
    {

            jNameTextField.setEnabled(false);
            jNameTextField.setEditable(false);
            jChatAliasTextField.setEnabled(false);
            jChatAliasTextField.setEditable(false);
            jPasswordField.setEnabled(false);
            jPasswordField.setEditable(false);
            jConfirmPasswordField.setEnabled(false);
            jConfirmPasswordField.setEditable(false);

    }
    private void disableControls()
    {

        LoginCodeComboBox.setEnabled(false);
        jUpdateButton.setEnabled(false);
        
        jCheckBox2.setEnabled(false);
        jCheckBox2.setEnabled(false);
        jCheckBox1.setEnabled(false);
        jCheckBox3.setEnabled(false);
    }
    private void enableControls()
    {
        jCheckBox2.setEnabled(true);
        jCheckBox2.setEnabled(true);
        jCheckBox1.setEnabled(true);
        jCheckBox3.setEnabled(true);
        
        if(jCheckBox2.isSelected())
        {
            jPasswordField.setEnabled(true);
            jPasswordField.setEditable(true);
            jConfirmPasswordField.setEnabled(true);
            jConfirmPasswordField.setEditable(true);
        }

        if(jCheckBox1.isSelected())
        {
            jNameTextField.setEnabled(true);
            jNameTextField.setEditable(true);
        }

        if(jCheckBox3.isSelected())
        {
            jChatAliasTextField.setEnabled(true);
            jChatAliasTextField.setEditable(true);
        }
            
        LoginCodeComboBox.setEnabled(true);
        jUpdateButton.setEnabled(true);
        this.setTitle("::. Edit System User");
    }
     boolean isCheckBoxesFalse()
    {
        boolean state = true;
        if((!jCheckBox1.isSelected())&&(!jCheckBox2.isSelected())&&(!jCheckBox3.isSelected()))
        {
            state=false;
        }

        return state;
    }
     void resetCheckBoxes()
    {
        jCheckBox2.setSelected(false);
        jCheckBox1.setSelected(false);
        jCheckBox3.setSelected(false);
    }

     String getDbLocation(String locationName)
    {
        if(locationName.isEmpty())
        {
            locationName="-- Select --";
        }
        return locationName;
    }
    private void submitData()
    {
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+"  prnUpdLogin"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
            try{
            statement.setString(1,LoginCodeComboBox.getSelectedItem().toString());
            statement.setString(2,jNameTextField.getText());
            statement.setString(3,String.valueOf(jPasswordField.getPassword()));
            statement.setString(4,jChatAliasTextField.getText());
            }
            catch(Exception e)
            {
            System.out.println("EditSystemUser.submitData() gave error ! "+e);
            }
        Connect.alert="Y";
        Connect.executeUpdateStatement(statement);
        Connect.closeConnection();
    }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox LoginCodeComboBox;
    private javax.swing.JLabel jAddressLabel;
    private javax.swing.JButton jButton2;
    private javax.swing.JTextField jChatAliasTextField;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JPasswordField jConfirmPasswordField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jNameTextField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JButton jUpdateButton;
    private javax.swing.JComboBox systemComboBox;
    // End of variables declaration//GEN-END:variables

}
