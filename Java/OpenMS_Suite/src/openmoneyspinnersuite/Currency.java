
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

final class Currency extends javax.swing.JInternalFrame {
PreparedStatement statement;
Thread currencyThread=new Thread();
Thread currencyTitleThread=new Thread();
    /** Creates new form TransactionType */
     Currency() {
        initComponents();
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
        jCurrencyTextField = new javax.swing.JTextField();
        jCurrencyRegisterButton = new javax.swing.JButton();
        jCloseButton = new javax.swing.JButton();

        setClosable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(Currency.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setVisible(true);

        jPanel1.setName("jPanel1"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jCurrencyTextField.setText(resourceMap.getString("jCurrencyTextField.text")); // NOI18N
        jCurrencyTextField.setName("jCurrencyTextField"); // NOI18N
        jCurrencyTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jCurrencyTextFieldKeyTyped(evt);
            }
        });

        jCurrencyRegisterButton.setFont(resourceMap.getFont("jCurrencyRegisterButton.font")); // NOI18N
        jCurrencyRegisterButton.setIcon(resourceMap.getIcon("jCurrencyRegisterButton.icon")); // NOI18N
        jCurrencyRegisterButton.setText(resourceMap.getString("jCurrencyRegisterButton.text")); // NOI18N
        jCurrencyRegisterButton.setName("jCurrencyRegisterButton"); // NOI18N
        jCurrencyRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCurrencyRegisterButtonActionPerformed(evt);
            }
        });

        jCloseButton.setFont(resourceMap.getFont("jCloseButton.font")); // NOI18N
        jCloseButton.setIcon(resourceMap.getIcon("jCloseButton.icon")); // NOI18N
        jCloseButton.setText(resourceMap.getString("jCloseButton.text")); // NOI18N
        jCloseButton.setName("jCloseButton"); // NOI18N
        jCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCloseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCurrencyRegisterButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jCloseButton))
                    .addComponent(jCurrencyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCurrencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCloseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCurrencyRegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCurrencyRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCurrencyRegisterButtonActionPerformed
        if(jCurrencyTextField.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please Enter a Currency ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {
            /* Execute submit thread */
            currencyThread=new Thread(new Runnable() {
                public void run() {
                    disableControls();
                    submitData();
                    reset();
                }
            });
            currencyTitleThread=new Thread(new Runnable() {
                public void run() {
                    while(currencyThread.isAlive())setProcessingTitle(currencyTitleThread);
                    enableControls();
                    System.out.println("currencyThread stopped runing.");
                }
            });
            currencyThread.start();
            currencyTitleThread.start();
        }
    }//GEN-LAST:event_jCurrencyRegisterButtonActionPerformed
                             
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. New Currency - Processing Request");
            thread.sleep(500);
            this.setTitle("::. New Currency - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. New Currency - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. New Currency - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println("Title thread"+e);}
    }
    private void jCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCloseButtonActionPerformed
        
        if(currencyThread.isAlive())
        {
            try{
                currencyThread.stop();
                currencyTitleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. New Currency");
            enableControls();
        }
        else
        this.dispose();
    }//GEN-LAST:event_jCloseButtonActionPerformed

    private void jCurrencyTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jCurrencyTextFieldKeyTyped
        // TODO add your handling code here:
        String value=jCurrencyTextField.getText();
        if(value.length()>9)
        {
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Maximum Number of Character (9) Exceeded ","ERROR !",JOptionPane.ERROR_MESSAGE);
        String newValue=String.copyValueOf(value.toCharArray(),0, 9);
        jCurrencyTextField.setText(newValue);
        }
    }//GEN-LAST:event_jCurrencyTextFieldKeyTyped

     void reset()
    {
        jCurrencyTextField.setText("");
    }
     void enableControls()
    {
        jCurrencyTextField.setEnabled(true);
        jCurrencyTextField.setEditable(true);
        jCurrencyRegisterButton.setEnabled(true);
        this.setTitle("::. New Currency");
    }
     void disableControls()
    {
        jCurrencyTextField.setEnabled(false);
        jCurrencyTextField.setEditable(false);
        jCurrencyRegisterButton.setEnabled(false);
    }

     void submitData()
    {
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+" prnInsCurrency"+Connect.procInitStart+"?"+Connect.procInitEnd);
            try{
            statement.setString(1,jCurrencyTextField.getText());
            }
            catch(Exception e)
            {
            System.out.println("Currency.submitData() gave error ! "+e);
            }
        Connect.alert="Y";
        Connect.executeUpdateStatement(statement);
        Connect.closeConnection();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCloseButton;
    private javax.swing.JButton jCurrencyRegisterButton;
    private javax.swing.JTextField jCurrencyTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
