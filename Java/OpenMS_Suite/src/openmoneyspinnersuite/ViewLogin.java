
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
 * ViewLogin.java
 *
 * Created on Jan 25, 2011, 1:03:00 AM
 */

package openmoneyspinnersuite;

/**
 *
 * @author Abiodun Aremu
 */
import javax.swing.*;
import java.sql.*;
final class ViewLogin extends javax.swing.JInternalFrame {

    Object[][] tableObject=new Object[][]{};
    Systems systems=new Systems();
    PreparedStatement statement;
    String searchValue="";
    Thread processThread=new Thread();
    Thread titleThread=new Thread();
    String[] dbPasswordName;
    
    /** Creates new form ViewLogin */
    ViewLogin() {
        initComponents();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
         tableObject,
            new String [] {
                "Login Code", "Employee Name", "Username", "Password", "Platform", "Status", "Chat Alias", "Chat Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        /* Execute thread */
        processThread=new Thread(new Runnable() {
            public void run() {
            disableControls();
            refreshTable("");
            }
        });
        titleThread=new Thread(new Runnable() {
            public void run() {
                while(processThread.isAlive())setProcessingTitle(titleThread);
                enableControls();
                System.out.println("viewItemThread stopped runing.");
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jRefreshButton = new javax.swing.JButton();
        jBlockButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jActivateButton = new javax.swing.JButton();
        jDecryptPasswordButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(ViewLogin.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setToolTipText(resourceMap.getString("Form.toolTipText")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setVisible(true);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Login Code", "Employee Name", "Username", "Password", "Platform", "Status", "Chat Alias", "Chat Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jRefreshButton.setFont(resourceMap.getFont("jRefreshButton.font")); // NOI18N
        jRefreshButton.setIcon(resourceMap.getIcon("jRefreshButton.icon")); // NOI18N
        jRefreshButton.setText(resourceMap.getString("jRefreshButton.text")); // NOI18N
        jRefreshButton.setName("jRefreshButton"); // NOI18N
        jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshButtonActionPerformed(evt);
            }
        });

        jBlockButton.setFont(resourceMap.getFont("jBlockButton.font")); // NOI18N
        jBlockButton.setIcon(resourceMap.getIcon("jBlockButton.icon")); // NOI18N
        jBlockButton.setText(resourceMap.getString("jBlockButton.text")); // NOI18N
        jBlockButton.setName("jBlockButton"); // NOI18N
        jBlockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBlockButtonActionPerformed(evt);
            }
        });

        jCancelButton.setFont(resourceMap.getFont("jCancelButton.font")); // NOI18N
        jCancelButton.setIcon(resourceMap.getIcon("jCancelButton.icon")); // NOI18N
        jCancelButton.setText(resourceMap.getString("jCancelButton.text")); // NOI18N
        jCancelButton.setName("jCancelButton"); // NOI18N
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        jActivateButton.setFont(resourceMap.getFont("jActivateButton.font")); // NOI18N
        jActivateButton.setIcon(resourceMap.getIcon("jActivateButton.icon")); // NOI18N
        jActivateButton.setText(resourceMap.getString("jActivateButton.text")); // NOI18N
        jActivateButton.setName("jActivateButton"); // NOI18N
        jActivateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jActivateButtonActionPerformed(evt);
            }
        });

        jDecryptPasswordButton.setText(resourceMap.getString("jDecryptPasswordButton.text")); // NOI18N
        jDecryptPasswordButton.setName("jDecryptPasswordButton"); // NOI18N
        jDecryptPasswordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDecryptPasswordButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(jDecryptPasswordButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRefreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jActivateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jBlockButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCancelButton)
                .addGap(108, 108, 108))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jBlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jActivateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jDecryptPasswordButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshButtonActionPerformed
        
        /* Execute thread */
        processThread=new Thread(new Runnable() {
            public void run() {
            disableControls();
            refreshTable("");
            }
        });
        titleThread=new Thread(new Runnable() {
            public void run() {
                while(processThread.isAlive())setProcessingTitle(titleThread);
                enableControls();
                System.out.println("viewItemThread stopped runing.");
            }
        });
        processThread.start();
        titleThread.start();
}//GEN-LAST:event_jRefreshButtonActionPerformed

    private void jBlockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBlockButtonActionPerformed
        
        /* Execute thread */
        processThread=new Thread(new Runnable() {
            public void run() {
            disableControls();
            blockButtonClicked();
            }
        });
        titleThread=new Thread(new Runnable() {
            public void run() {
                while(processThread.isAlive())setProcessingTitle(titleThread);
                enableControls();
                System.out.println("viewItemThread stopped runing.");
            }
        });
        processThread.start();
        titleThread.start();
}//GEN-LAST:event_jBlockButtonActionPerformed
    private void blockButtonClicked()
    {
        if(jTable1.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"PLEASE SELECT A USER !","Allocation ERROR !",JOptionPane.ERROR_MESSAGE);
        } 
        else
        {
            int row=jTable1.getSelectedRow();
            String loginCode=jTable1.getValueAt(row,0).toString();
            blockLogin(loginCode);
        }
        refreshTable(searchValue);    
    }
    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        
        if(processThread.isAlive())
        {
            try{
                processThread.stop();
                titleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. View System User");
            enableControls();
        }
        else
        dispose();
}//GEN-LAST:event_jCancelButtonActionPerformed

    private void jActivateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jActivateButtonActionPerformed
                
        /* Execute thread */
        processThread=new Thread(new Runnable() {
            public void run() {
            disableControls();
            activateButtonClicked();
            }
        });
        titleThread=new Thread(new Runnable() {
            public void run() {
                while(processThread.isAlive())setProcessingTitle(titleThread);
                enableControls();
                System.out.println("viewItemThread stopped runing.");
            }
        });
        processThread.start();
        titleThread.start();
    }//GEN-LAST:event_jActivateButtonActionPerformed

private void jDecryptPasswordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDecryptPasswordButtonActionPerformed
    
        if(jTable1.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"PLEASE SELECT A USER !","Allocation ERROR !",JOptionPane.ERROR_MESSAGE);
        } 
        else {
            int row=jTable1.getSelectedRow();
            String loginCode=jTable1.getValueAt(row,0).toString();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Login Code: "+loginCode+"\nPassword: "+dbPasswordName[row],"Password ENCRYPTED !",JOptionPane.INFORMATION_MESSAGE);
            
        }
}//GEN-LAST:event_jDecryptPasswordButtonActionPerformed
    private void activateButtonClicked()
    {
        if(jTable1.getSelectedRow()==-1)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"PLEASE SELECT A USER !","Allocation ERROR !",JOptionPane.ERROR_MESSAGE);
        } 
        else
        {
            int row=jTable1.getSelectedRow();
            String loginCode=jTable1.getValueAt(row,0).toString();
            activateLogin(loginCode);
        }
        refreshTable(searchValue);    
    }
    private void disableControls()
    {
        jRefreshButton.setEnabled(false);
        jActivateButton.setEnabled(false);
        jBlockButton.setEnabled(false);
    }                                             
    private void enableControls()
    {
        jRefreshButton.setEnabled(true);
        jActivateButton.setEnabled(true);
        jBlockButton.setEnabled(true);
        this.setTitle("::. View System User");
    }
                       
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. View System User - Processing Request");
            thread.sleep(500);
            this.setTitle("::. View System User - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. View System User - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. View System User - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println("Title thread"+e);}
    }
    void print()
    {
        try
        {
            jTable1.print();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    void blockLogin(String loginCode)
    {
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+"  prnBlockLogin"+Connect.procInitStart+"?"+Connect.procInitEnd);
         try{
                statement.setString(1,loginCode);
                System.out.println("Blocked "+loginCode);
            }
            catch(Exception e)
            {
                System.out.println("ViewLoginRecord.blockLogin gave error ! "+e);
            }
        Connect.alert="Y";
        Connect.executeUpdateStatement(statement);
        Connect.closeConnection();
    }
    void activateLogin(String loginCode)
    {
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+"  prnActivateLogin"+Connect.procInitStart+"?"+Connect.procInitEnd);
         try{
                statement.setString(1,loginCode);
                System.out.println("Activate login "+loginCode);
            }
            catch(Exception e)
            {
                System.out.println("ViewLoginRecord.activateLogin gave error ! "+e);
            }
        Connect.alert="Y";
        Connect.executeUpdateStatement(statement);
        Connect.closeConnection();
    }
    void refreshTable(String searchPara)
    {
            String[] dbLoginCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Login",1);
            String[] dbEmployeeCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Login",2);
            String[] dbUserName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Login",3);
            dbPasswordName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Login",4);
            String[] dbProcess=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Login",5);
            String[] dbStatus=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Login",8);
            String[] dbChatAlias=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Login",9);
            String[] dbChatStatus=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Login",10);

            if(searchPara.isEmpty()==false)
            {
                searchValue=searchPara;
                String multipleSearch[]=searchPara.split(",");
                String queryPara="where (cLoginCode = '"+multipleSearch[0]+"') or (cEmployeeID = '"+multipleSearch[0]+"') or (cUserName = '"+multipleSearch[0]+"')  or (cProcess = '"+multipleSearch[0]+"')  or (cStatus = '"+multipleSearch[0]+"') ";

                for(int k=1;k<multipleSearch.length;k++)
                {
                    queryPara+="or (cLoginCode like '%"+multipleSearch[k]+"%') or (cEmployeeID like '%"+multipleSearch[k]+"%') or (cUserName like '%"+multipleSearch[k]+"%')  or (cProcess like '%"+multipleSearch[k]+"%')  or (cStatus like '%"+multipleSearch[k]+"%') ";
                }

                dbLoginCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Login "+queryPara, 1);
                dbEmployeeCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Login "+queryPara, 2);
                dbUserName=systems.getColumn(OpenMSApp.Database_A, "Select * from Login "+queryPara, 3);
                dbPasswordName=systems.getColumn(OpenMSApp.Database_A, "Select * from Login "+queryPara, 4);
                dbProcess=systems.getColumn(OpenMSApp.Database_A, "Select * from Login "+queryPara, 5);
                dbStatus=systems.getColumn(OpenMSApp.Database_A, "Select * from Login "+queryPara, 8);
                dbChatAlias=systems.getColumn(OpenMSApp.Database_A, "Select * from Login "+queryPara, 9);
                dbChatStatus=systems.getColumn(OpenMSApp.Database_A, "Select * from Login "+queryPara, 10);
            }

        int x=0;
        int y=dbLoginCode.length;

        tableObject=new Object[y][8];

        while(x<y)
        {
            String employeeName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+dbEmployeeCode[x]+"'","vFirstName")+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+dbEmployeeCode[x]+"'","vMiddleName")+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+dbEmployeeCode[x]+"'","vlastName");
            tableObject[x][0]=dbLoginCode[x].trim();
            tableObject[x][1]=employeeName.trim();
            tableObject[x][2]=dbUserName[x].trim();
            tableObject[x][3]="**********";//dbPasswordName[x].trim();
            tableObject[x][4]=dbProcess[x].trim();
            tableObject[x][5]=dbStatus[x].trim();
            tableObject[x][6]=dbChatAlias[x].trim();
            tableObject[x][7]=dbChatStatus[x].trim();
            systems.freeHeap();
            x+=1;
            if(x==y){break;}
       }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
         tableObject,
            new String [] {
                "Login Code", "Employee Name", "Username", "Password", "Platform", "Status", "Chat Alias", "Chat Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jActivateButton;
    private javax.swing.JButton jBlockButton;
    private javax.swing.JButton jCancelButton;
    private javax.swing.JButton jDecryptPasswordButton;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
