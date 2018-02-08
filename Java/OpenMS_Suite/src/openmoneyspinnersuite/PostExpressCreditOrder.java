
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
import java.sql.*;
import javax.swing.*;

final class PostExpressCreditOrder extends javax.swing.JInternalFrame {

    Object[][] tableObject=new Object[][]{};
    Systems systems=new Systems();
    PreparedStatement statement;
    Thread processThread=new Thread();
    Thread titleThread=new Thread();

    /** Creates new form ViewCredit */
    /** Creates new form ViewCredit */
    public PostExpressCreditOrder() {
        initComponents();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "Order Code", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        /* Execute thread */
        processThread=new Thread(new Runnable() {
            public void run() {
            disableControls();
            refreshTable();

            }
        });
        titleThread=new Thread(new Runnable() {
            public void run() {
                while(processThread.isAlive())setProcessingTitle(titleThread);
                enableControls();
                System.out.println("viewIPDebitThread stopped runing.");
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
        jRefreshButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSumTextField = new javax.swing.JTextField();
        jPostButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(PostExpressCreditOrder.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setVisible(true);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Order Code", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTable1MouseReleased(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N

        jRefreshButton.setFont(resourceMap.getFont("jRefreshButton.font")); // NOI18N
        jRefreshButton.setIcon(resourceMap.getIcon("jRefreshButton.icon")); // NOI18N
        jRefreshButton.setText(resourceMap.getString("jRefreshButton.text")); // NOI18N
        jRefreshButton.setName("jRefreshButton"); // NOI18N
        jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshButtonActionPerformed(evt);
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

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jSumTextField.setText(resourceMap.getString("jSumTextField.text")); // NOI18N
        jSumTextField.setEnabled(false);
        jSumTextField.setName("jSumTextField"); // NOI18N

        jPostButton.setText(resourceMap.getString("jPostButton.text")); // NOI18N
        jPostButton.setName("jPostButton"); // NOI18N
        jPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPostButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jPostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRefreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jSumTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshButtonActionPerformed
        
        /* Execute thread */
        processThread=new Thread(new Runnable() {
            public void run() {
            disableControls();
            refreshTable();

            }
        });
        titleThread=new Thread(new Runnable() {
            public void run() {
                while(processThread.isAlive())setProcessingTitle(titleThread);
                enableControls();
                System.out.println("viewIPDebitThread stopped runing.");
            }
        });
        processThread.start();
        titleThread.start();
    }//GEN-LAST:event_jRefreshButtonActionPerformed

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        
        if(processThread.isAlive())
        {
            try{
                processThread.stop();
                titleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. Post Express Credit Order");
            enableControls();
        }
        else
        dispose();
    }//GEN-LAST:event_jCancelButtonActionPerformed

    private void jPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPostButtonActionPerformed
        
        /* Execute thread */
        processThread=new Thread(new Runnable() {
            public void run() {
            disableControls();
            if(jTable1.getSelectedRow()==-1)
            {
                javax.swing.JOptionPane.showMessageDialog(javax.swing.JOptionPane.getRootFrame(),"Please Select An Order !","Allocation ERROR !",javax.swing.JOptionPane.ERROR_MESSAGE);
            }
            if(selectedAccountActive()==false)
            {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame()," SELECTED ACCOUNT IS FROZEN ! \n SEE YOUR ACCOUNT ADMINISTRATOR.","ERROR !",JOptionPane.ERROR_MESSAGE);
                return;
            }
               postSelectedOrderCode();          
                Connect.closeMainConnection();
                Connect.createMSSQLConnection();

            }
        });
        titleThread=new Thread(new Runnable() {
            public void run() {
                while(processThread.isAlive())setProcessingTitle(titleThread);
                enableControls();
                System.out.println("viewIPDebitThread stopped runing.");
            }
        });
        processThread.start();
        titleThread.start();
    }//GEN-LAST:event_jPostButtonActionPerformed

    public void postSelectedOrderCode()
    {
        if(jTable1.getSelectedRow()==-1)
        {
            javax.swing.JOptionPane.showMessageDialog(javax.swing.JOptionPane.getRootFrame(),"Please Select An Order !","Allocation ERROR !",javax.swing.JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            int x[]=jTable1.getSelectedRows();
            int count=jTable1.getSelectedRowCount();
            int y=0;
            System.out.println("POST const no of indices="+x.length+" no of rows selected="+count+" sel row"+jTable1.getSelectedRow());
            if(count>0)
            {
                while(y<count)
                {
                    String postState=systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+jTable1.getValueAt(x[y],0).toString()+"'", "cStatus");
                    if(postState.equalsIgnoreCase("posted")){
                    refreshTable();
                    return;
                    }
                    y+=1;
                    if(count==y){break;}
                }
                y=0;
                while(y<count)
                {
                    String refID=systems.getValue(OpenMSApp.Database_A, "Select cRefID from Creditorder where cCreditOrderCode='"+jTable1.getValueAt(x[y],0).toString()+"'", "cRefID");
                    
                    String costOrderCode=systems.getValue(OpenMSApp.Database_A, "Select cCostOrderCode from Creditorder where cCreditOrderCode='"+jTable1.getValueAt(x[y],0).toString()+"'", "cCostOrderCode");
                    String transType=systems.getValue(OpenMSApp.Database_A, "Select cTransactionType from Creditorder where cCreditOrderCode='"+jTable1.getValueAt(x[y],0).toString()+"'", "cTransactionType");
                    String[] pcPdAccountCode;
                    if(transType.equalsIgnoreCase("IPC")||transType.equalsIgnoreCase("IPD"))
                    {
                        pcPdAccountCode=systems.getColumn(OpenMSApp.Database_A, "Select cRefID from CreditOrder where cRefID='"+refID+"'", 3);
                    
                        //Connect.createMSSQLConnection();
                        Connect.changeDB(OpenMSApp.Database_A);
                        System.out.println("order code="+jTable1.getValueAt(x[y],0).toString()+"first indices="+x[y]);
                        System.out.println("order code pcPd [0]="+pcPdAccountCode[0]);
                        System.out.println("order code pcPd [1]="+pcPdAccountCode[1]);
                        System.out.println("lenght of pcPd ="+pcPdAccountCode.length);

                        statement=Connect.createStatement(Connect.procInit+"  prnUpdPayableCreditOrderOnPost"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
                        try
                        {
                            statement.setString(1,refID);
                            statement.setString(2,pcPdAccountCode[0]);
                        }
                        catch(Exception e)
                        {
                            System.out.println("PostExpressCreditOrder.postSelectedOrderCode gave error! "+e);
                        }
                        Connect.executeUpdateStatement(statement);
                        statement=Connect.createStatement(Connect.procInit+"  prnUpdPayableCreditOrderOnPost"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
                        try
                        {
                            statement.setString(1,refID);
                            statement.setString(2,pcPdAccountCode[1]);
                        }
                        catch(Exception e)
                        {
                            System.out.println("PostExpressCreditOrder.postSelectedOrderCode gave error! "+e);
                        }
                        Connect.executeUpdateStatement(statement);
                        Connect.alert="Y";
                        Connect.closeConnection();
                        break;//STOPS CONTINUOS/MULTIPLE POSTING IF INVENTORY PAYABLE CREDIT TRANSACTION ENCOUNTERED
                    }
                    else
                    {
                        //Connect.createMSSQLConnection();
                        Connect.changeDB(OpenMSApp.Database_A);
                        System.out.println("order code="+jTable1.getValueAt(x[y],0).toString()+"first indices="+x[y]);

                        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderOnPost"+Connect.procInitStart+"?"+Connect.procInitEnd);
                        try
                        {
                            statement.setString(1,refID);
                        }
                        catch(Exception e)
                        {
                            System.out.println("PostExpressCreditOrder.postSelectedOrderCode gave error! "+e);
                        }
                        Connect.executeUpdateStatement(statement);
                        statement=Connect.createStatement(Connect.procInit+"  prnUpdDebitOrderOnPost"+Connect.procInitStart+"?"+Connect.procInitEnd);
                        try
                        {
                            statement.setString(1,refID);
                        }
                        catch(Exception e)
                        {
                            System.out.println("PostExpressCreditOrder.postSelectedOrderCode gave error! "+e);
                        }
                        Connect.executeUpdateStatement(statement);
                        Connect.alert="Y";
                        Connect.closeConnection();
                    
                    }
                    if(!costOrderCode.isEmpty())
                    {
                        String totAmount=systems.getValue(OpenMSApp.Database_A, "Select mTotalAmount from Creditorder where cCreditOrderCode='"+jTable1.getValueAt(x[y],0).toString()+"'", "mTotalAmount");

                        Connect.changeDB(OpenMSApp.Database_A);
                        statement=Connect.createStatement(Connect.procInit+"  prnUpdPayableCreditOrderOnPost"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
                        try
                        {
                            statement.setString(1,costOrderCode);
                            statement.setString(2,totAmount);
                        }
                        catch(Exception e)
                        {
                            System.out.println("PostCreditOrder.postCurrentOrderCode gave error! "+e);
                        }
                        Connect.executeUpdateStatement(statement);
                        Connect.alert="N";
                        Connect.closeConnection();
                    }
                        //implement costing
                        String[] credits=systems.getColumn(OpenMSApp.Database_A, "Select cCreditOrderCode from CreditOrder where cRefID='"+refID+"'", 1);
                        String debit=systems.getValue(OpenMSApp.Database_A, "Select cDebitOrderCode from DebitOrder where cRefID='"+refID+"'", 1);
                        for(int xx=0;xx<credits.length;xx++)
                           systems.implementCosting(credits[xx],"Credit");
                        if(!debit.isEmpty())
                           systems.implementCosting(debit,"Debit");
                    y+=1;
                    if(count==y){break;} 
                }
            }
        }
        refreshTable();
    }
    public boolean selectedAccountActive()
    {
        boolean accountActive=true;

        int x[]=jTable1.getSelectedRows();
        int count=jTable1.getSelectedRowCount();
        int y=0;
        System.out.println("isActAtv no of indices="+x.length+" no of rows selected="+count+" sel row"+jTable1.getSelectedRow());
        if(count>0)
        {
            while(y<count)
            {
                String accountCode=systems.getValue(OpenMSApp.Database_A, "Select * from CreditOrder where cCreditOrderCode='"+jTable1.getValueAt(x[y],0).toString()+"'", "cAccountCode");
                String accountStatus=systems.getValue(OpenMSApp.Database_A, "Select * from Account where cAccountCode='"+accountCode+"'", "cStatus");
                if(accountStatus.equalsIgnoreCase("Frozen"))
                {
                    accountActive=false;
                }
                y+=1;
                if(count==y){break;}
            }
        }
        return accountActive;
    }
    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // TODO add your handling code here:
        String firstValue="0";
        String secondValue="0";
        String sumValue="0";
        int x[]=jTable1.getSelectedRows();
        int count=jTable1.getSelectedRowCount();
        int y=0;
        System.out.println("mr no of indices="+x.length+" no of rows selected="+count+" sel row"+jTable1.getSelectedRow());
        if(count>0)
        {
            while(y<count)
            {
                firstValue=sumValue;
                try
                {
                    secondValue=systems.converToRealMoneyFormat(jTable1.getValueAt(x[y],1).toString());
                }
                catch(Exception e)
                {
                    secondValue="Er!M1C01K0";
                }
                sumValue=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+firstValue+" , "+secondValue+Connect.procInitEnd, "mSumValue");
                System.out.println(firstValue+" "+secondValue+" "+sumValue);
            y+=1;
            if(count==y){break;}
            }
            try
            {
                jSumTextField.setText(systems.converToRealMoneyFormat(sumValue));
            }
            catch(Exception e)
            {
                jSumTextField.setText("Er!M1C01K0");
            }
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        String firstValue="0";
        String secondValue="0";
        String sumValue="0";
        int x[]=jTable1.getSelectedRows();
        int count=jTable1.getSelectedRowCount();
        int y=0;
        System.out.println("mr no of indices="+x.length+" no of rows selected="+count+" sel row"+jTable1.getSelectedRow());
        if(count>0)
        {
            while(y<count)
            {
                firstValue=sumValue;
                try
                {
                    secondValue=systems.converToRealMoneyFormat(jTable1.getValueAt(x[y],1).toString());
                }
                catch(Exception e)
                {
                    secondValue="Er!M1C01K0";
                }
                sumValue=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+firstValue+" , "+secondValue+Connect.procInitEnd, "mSumValue");
                System.out.println(firstValue+" "+secondValue+" "+sumValue);
            y+=1;
            if(count==y){break;}
            }
            try
            {
                jSumTextField.setText(systems.converToRealMoneyFormat(sumValue));
            }
            catch(Exception e)
            {
                jSumTextField.setText("Er!M1C01K0");
            }
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        String firstValue="0";
        String secondValue="0";
        String sumValue="0";
        int x[]=jTable1.getSelectedRows();
        int count=jTable1.getSelectedRowCount();
        int y=0;
        System.out.println("mr no of indices="+x.length+" no of rows selected="+count+" sel row"+jTable1.getSelectedRow());
        if(count>0)
        {
            while(y<count)
            {
                firstValue=sumValue;
                try
                {
                    secondValue=systems.converToRealMoneyFormat(jTable1.getValueAt(x[y],1).toString());
                }
                catch(Exception e)
                {
                    secondValue="Er!M1C01K0";
                }
                sumValue=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+firstValue+" , "+secondValue+Connect.procInitEnd, "mSumValue");
                System.out.println(firstValue+" "+secondValue+" "+sumValue);
            y+=1;
            if(count==y){break;}
            }
            try
            {
                jSumTextField.setText(systems.converToRealMoneyFormat(sumValue));
            }
            catch(Exception e)
            {
                jSumTextField.setText("Er!M1C01K0");
            }
        }
    }//GEN-LAST:event_jTable1MousePressed

    private void jTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseReleased
        // TODO add your handling code here:
        String firstValue="0";
        String secondValue="0";
        String sumValue="0";
        int x[]=jTable1.getSelectedRows();
        int count=jTable1.getSelectedRowCount();
        int y=0;
        System.out.println("mr no of indices="+x.length+" no of rows selected="+count+" sel row"+jTable1.getSelectedRow());
        if(count>0)
        {
            while(y<count)
            {
                firstValue=sumValue;
                try
                {
                    secondValue=systems.converToRealMoneyFormat(jTable1.getValueAt(x[y],1).toString());
                }
                catch(Exception e)
                {
                    secondValue="Er!M1C01K0";
                }
                sumValue=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+firstValue+" , "+secondValue+Connect.procInitEnd, "mSumValue");
                System.out.println(firstValue+" "+secondValue+" "+sumValue);
            y+=1;
            if(count==y){break;}
            }
            try
            {
                jSumTextField.setText(systems.converToRealMoneyFormat(sumValue));
            }
            catch(Exception e)
            {
                jSumTextField.setText("Er!M1C01K0");
            }
        }
    }//GEN-LAST:event_jTable1MouseReleased
    
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. Post Express Credit Order - Processing Request");
            thread.sleep(500);
            this.setTitle("::. Post Express Credit Order - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. Post Express Credit Order - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. Post Express Credit Order - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println("Title thread"+e);}
            this.setTitle("::. Post Express Credit Order");
    }
    private void disableControls()
    {
        jPostButton.setEnabled(false);
        jRefreshButton.setEnabled(false);
    }
    private void enableControls()
    {
        jPostButton.setEnabled(true);
        jRefreshButton.setEnabled(true);
        this.setTitle("::. Post Express Credit Order");
    }
    void refreshTable()
    {
        String[] departmentCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"vwApprovedCreditOrder",1);
        String[] departmentName=systems.getTableDataArrayNum(OpenMSApp.Database_A,"vwApprovedCreditOrder",3);

        int x=0;
        int y=departmentCode.length;

        tableObject=new Object[y][2];

        while(x<y)
        {
            tableObject[x][0]=departmentCode[x].trim();
            tableObject[x][1]=departmentName[x].trim();
            x+=1;
            if(x==y){break;}
       }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "Order Code", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

        String firstValue="0";
        String secondValue="0";
        String sumValue="0";

        int a=tableObject.length;
        int b=0;
        while(b<a)
        {
            if(jTable1.getValueAt(b,1)!=null)
            {
                firstValue=sumValue;
                try
                {
                    secondValue=systems.converToRealMoneyFormat(jTable1.getValueAt(b,1).toString());
                }
                catch(Exception e)
                {
                    secondValue="Er!M1C01K0";
                }
                sumValue=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+firstValue+" , "+secondValue+Connect.procInitEnd, "mSumValue");
                System.out.println(firstValue+" "+secondValue+" "+sumValue);
            }
            b+=1;
            if(b==a){break;}
        }
        try
        {
            jSumTextField.setText(systems.converToRealMoneyFormat(sumValue));
        }
        catch(Exception e)
        {
            jSumTextField.setText("Er!M1C01K0");
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jPostButton;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jSumTextField;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}