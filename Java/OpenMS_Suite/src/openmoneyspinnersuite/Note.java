
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

final class Note extends javax.swing.JInternalFrame {
PreparedStatement statement;
Systems systems=new Systems();
String merchantID;
    /** Creates new form TransactionType */
    public Note() {
        initComponents();
        reset();
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
        jAddCommentButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        merchantIDComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jAllCommentTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jNewCommentTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setClosable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(Note.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setVisible(true);

        jPanel1.setName("jPanel1"); // NOI18N

        jAddCommentButton.setFont(resourceMap.getFont("jAddCommentButton.font")); // NOI18N
        jAddCommentButton.setIcon(resourceMap.getIcon("jAddCommentButton.icon")); // NOI18N
        jAddCommentButton.setText(resourceMap.getString("jAddCommentButton.text")); // NOI18N
        jAddCommentButton.setName("jAddCommentButton"); // NOI18N
        jAddCommentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddCommentButtonActionPerformed(evt);
            }
        });

        jButton1.setFont(resourceMap.getFont("jButton1.font")); // NOI18N
        jButton1.setIcon(resourceMap.getIcon("jButton1.icon")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        merchantIDComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Select --", "Item 2", "Item 3", "Item 4" }));
        merchantIDComboBox.setName("merchantIDComboBox"); // NOI18N
        merchantIDComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                merchantIDComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jAllCommentTextArea.setColumns(20);
        jAllCommentTextArea.setEditable(false);
        jAllCommentTextArea.setRows(5);
        jAllCommentTextArea.setWrapStyleWord(true);
        jAllCommentTextArea.setName("jAllCommentTextArea"); // NOI18N
        jScrollPane1.setViewportView(jAllCommentTextArea);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jNewCommentTextArea.setColumns(20);
        jNewCommentTextArea.setRows(5);
        jNewCommentTextArea.setWrapStyleWord(true);
        jNewCommentTextArea.setName("jNewCommentTextArea"); // NOI18N
        jScrollPane2.setViewportView(jNewCommentTextArea);

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(871, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 954, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(merchantIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(702, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addContainerGap(906, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jAddCommentButton)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(760, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(merchantIDComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jAddCommentButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );

        getAccessibleContext().setAccessibleName(resourceMap.getString("Form.AccessibleContext.accessibleName")); // NOI18N

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jAddCommentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddCommentButtonActionPerformed
        // TODO add your handling code here:
        if(merchantIDComboBox.getSelectedItem().equals("-- Select --"))
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please enter a Merchant ID","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if(jNewCommentTextArea.getText().trim().isEmpty())
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Please enter a record ","ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else
        {
            submitData();
            updateAllComment();
        }
    }//GEN-LAST:event_jAddCommentButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

private void merchantIDComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_merchantIDComboBoxActionPerformed
        // TODO add your handling code here:
        merchantID=merchantIDComboBox.getSelectedItem().toString();
        if(merchantID.equals("-- Select --"))
        {
            resetMerchantID();
        }
        else
        {
            setTitle("::. Merchant File ( "+systems.getValue(OpenMSApp.Database_A, "Select * from vwMerchant where cMerchantID = '"+merchantID+"'", "vName")+" )");
            jAllCommentTextArea.setText(systems.getValue(OpenMSApp.Database_A, "Select * from Note where cMerchantID = '"+merchantID+"'", "tNote"));
        }
}//GEN-LAST:event_merchantIDComboBoxActionPerformed
public void updateAllComment()
{
    jAllCommentTextArea.setText(systems.getValue(OpenMSApp.Database_A, "Select * from Note where cMerchantID = '"+merchantID+"'", "tNote"));
    jNewCommentTextArea.setText("");
}
    public void resetMerchantID()
    {
        String[] merchant=systems.getTableDataArray(OpenMSApp.Database_A,"vwMerchant",1);
        merchant[0]="-- Select --";
        merchantIDComboBox.setModel(new DefaultComboBoxModel(merchant));
        setTitle("::. Merchant File");
    }     
    public void reset()
    {
        jNewCommentTextArea.setText("");
        jAllCommentTextArea.setText("");
        resetMerchantID();
    }

    public void submitData()
    {
        Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+" prnAddNote"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
            try{
            statement.setString(1,merchantIDComboBox.getSelectedItem().toString());
            statement.setString(2,OpenMSApp.EmployeeName+" "+OpenMSApp.LoginCode+"\n"+jNewCommentTextArea.getText().trim()+"\n");
            }
            catch(Exception e)
            {
            System.out.println("Note.submitData() gave error ! "+e);
            }
        Connect.alert="Y";
        Connect.executeUpdateStatement(statement);
        Connect.closeConnection();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jAddCommentButton;
    private javax.swing.JTextArea jAllCommentTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextArea jNewCommentTextArea;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox merchantIDComboBox;
    // End of variables declaration//GEN-END:variables

}
