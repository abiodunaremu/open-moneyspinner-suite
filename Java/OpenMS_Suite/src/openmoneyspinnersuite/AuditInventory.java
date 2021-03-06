
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
 * AuditInventory.java
 *
 * Created on Jan 25, 2011, 4:09:19 PM
 */

package openmoneyspinnersuite;

/**
 *
 * @author Abiodun Aremu
 */

import java.sql.*;
import javax.swing.*;

final class AuditInventory extends javax.swing.JInternalFrame {
    Object[][] tableObject=new Object[][]{};
    Systems systems=new Systems();
    PreparedStatement statement;
    Thread processThread=new Thread();
    Thread titleThread=new Thread();
    String title="::. View Stock Statistics";
    /** Creates new form ViewCredit */
    public AuditInventory() {
        initComponents();
        /* Execute refresh thread */
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
        jRefreshButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();
        jMonthComboBox = new javax.swing.JComboBox();
        jDayComboBox = new javax.swing.JComboBox();
        jYearComboBox = new javax.swing.JComboBox();
        jCheckBox1 = new javax.swing.JCheckBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(AuditInventory.class);
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
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Item Name", "Total I/R Credit (Qty)", "Total I/R Debit (Qty)", "Total Revenue (Qty)", "Balance (Qty)", "Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N

        jRefreshButton.setIcon(resourceMap.getIcon("jRefreshButton.icon")); // NOI18N
        jRefreshButton.setText(resourceMap.getString("jRefreshButton.text")); // NOI18N
        jRefreshButton.setName("jRefreshButton"); // NOI18N
        jRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRefreshButtonActionPerformed(evt);
            }
        });

        jCancelButton.setIcon(resourceMap.getIcon("jCancelButton.icon")); // NOI18N
        jCancelButton.setText(resourceMap.getString("jCancelButton.text")); // NOI18N
        jCancelButton.setName("jCancelButton"); // NOI18N
        jCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCancelButtonActionPerformed(evt);
            }
        });

        jMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Month --" }));
        jMonthComboBox.setName("jMonthComboBox"); // NOI18N
        jMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMonthComboBoxActionPerformed(evt);
            }
        });

        jDayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Day --" }));
        jDayComboBox.setName("jDayComboBox"); // NOI18N

        jYearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Year --" }));
        jYearComboBox.setName("jYearComboBox"); // NOI18N

        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 755, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(157, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(63, 63, 63)
                .addComponent(jMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64)
                .addComponent(jRefreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRefreshButtonActionPerformed
        
        /* Execute refresh thread */
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
                System.out.println("accountThread stopped runing.");
            }
        });
        processThread.start();
        titleThread.start();
    }//GEN-LAST:event_jRefreshButtonActionPerformed
                                     
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. View Stock Statistics - Processing Request");
            thread.sleep(500);
            this.setTitle("::. View Stock Statistics - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. View Stock Statistics - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. View Stock Statistics - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println("Title thread"+e);}
    }
    public void reset()
    {
        /* Execute refresh thread */
        processThread=new Thread(new Runnable() {
            public void run() {
                disableControls();
                refreshTable("");
                resetTransDay("");
                resetTransMonth();
                resetTransYear();
                
            }
        });
        titleThread=new Thread(new Runnable() {
            public void run() {
                while(processThread.isAlive())setProcessingTitle(titleThread);
                enableControls();
                System.out.println("accountThread stopped runing.");
            }
        });
        processThread.start();
        titleThread.start();
    }
    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed

        if(processThread.isAlive())
        {
            try{
                processThread.stop();
                titleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. View Stock Statistics");
            enableControls();
        }
        else
        dispose();
    }//GEN-LAST:event_jCancelButtonActionPerformed

private void jMonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMonthComboBoxActionPerformed
        
        String transMonth=jMonthComboBox.getSelectedItem().toString();

        if(transMonth.equals("-- Month --")) {
        } else {
            resetTransDay(transMonth);
        }
}//GEN-LAST:event_jMonthComboBoxActionPerformed
    private void disableControls()
    {
        jRefreshButton.setEnabled(false);
    }
    private void enableControls()
    {
        jRefreshButton.setEnabled(true);
        this.setTitle(title);
    }
    public void resetTransYear()
    {
        String[] year=systems.getYears(1920);
        year[0]="-- Year --";
        jYearComboBox.setModel(new DefaultComboBoxModel(year));
        year=null;
    }
    public void resetTransMonth()
    {
        String[] month=systems.getMonthArray();
        month[0]="-- Month --";

        jMonthComboBox.setModel(new DefaultComboBoxModel(month));
        month=null;
        
    }
    public void resetTransDay(String Month)
    {
        String[] day={"-- Day --"};
        if(Month.isEmpty())
        {
            jDayComboBox.setModel(new DefaultComboBoxModel(day));
        }
        else
        {
            day=systems.getDays(Month);
            day[0]="-- Day --";
            jDayComboBox.setModel(new DefaultComboBoxModel(day));
        }
        day=null;
        
    }
    public void refreshTable(String searchPara)
    {
        String[][] item={{}};
        String[] itemName={};
        String[] itemUnitCode={};
        String[] itemCode={};

            if(searchPara.isEmpty()==false)
            {
                System.out.println("Searchin using parameter: "+searchPara);
                //searchValue=searchPara;
                String multipleSearch[]=searchPara.split(",");
                String queryPara="where cType='Stock' and (cItemCode like '%"+multipleSearch[0]+"%') or (vName like '%"+multipleSearch[0]+"%') ";

                for(int k=1;k<multipleSearch.length;k++)
                {
                    queryPara+=" or (cItemCode like '%"+multipleSearch[k]+"%') or (vName like '%"+multipleSearch[k]+"%') ";
                }
                item=systems.getColumn(OpenMSApp.Database_A,"Select cItemCode,vName,cItemUnitCode from Item "+queryPara,1,2,9);
                itemCode=item[0];
                itemName=item[1];
                itemUnitCode=item[2];

            }else{
                item=systems.getColumn(OpenMSApp.Database_A,"Select cItemCode,vName,cItemUnitCode from Item where cType='Stock'",1,2,3);
                itemName=item[1];
                itemUnitCode=item[2];
                itemCode=item[0];
            }


        int x=0;
        int y=itemCode.length;
        String revenueQty="";
        String irCreditQty="";
        String irDebitQty="";
        String qtyIReceivableCredit="";
        String qtyIPDebit="";
        String previousBal="";
        if(jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
            &&jCheckBox1.isSelected())
        tableObject=new Object[y][10];
        else
        tableObject=new Object[y][9];

        while(x<y)
        {
//            String revenueQty=systems.getItemTotalRevenueQty(itemCode[x]);
//            String irCreditQty=systems.getItemTotalIRCreditQty(itemCode[x]);
//            String irDebitQty=systems.getItemTotalIRDebitQty(itemCode[x]);
//            String iRecievableCreditQty=systems.getItemTotalIReceivableQty(itemCode[x]);
//            String iPayableDebitQty=systems.getItemTotalIRDebitQty(itemCode[x]);
            
            if(jMonthComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Month --")==false
                    &&jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
                    &&jDayComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Day --")==false
                    &&jCheckBox1.isSelected()==false){

                irCreditQty=systems.getItemTotalIRCreditQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'"); 
                irDebitQty=systems.getItemTotalIRDebitQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'"); 
                qtyIReceivableCredit=systems.getItemTotalIReceivableQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'");
                qtyIPDebit=systems.getItemTotalIPDebitQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'");
                revenueQty=systems.getItemTotalRevenueQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'"); 
                title="::. View Stock Statistics - [Summation Till "+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+"]";
            }
            else if(jMonthComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Month --")==false
                    &&jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
                    &&jDayComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Day --")
                    &&jCheckBox1.isSelected()==false){

                irCreditQty=systems.getItemTotalIRCreditQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                irDebitQty=systems.getItemTotalIRDebitQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                qtyIReceivableCredit=systems.getItemTotalIReceivableQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                qtyIPDebit=systems.getItemTotalIPDebitQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                revenueQty=systems.getItemTotalRevenueQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                title="::. View Stock Statistics - [Summation Till "+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +"]";
            }
            else if(jMonthComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Month --")
                    &&jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
                    &&jDayComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Day --")
                    &&jCheckBox1.isSelected()==false){

                irCreditQty=systems.getItemTotalIRCreditQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                irDebitQty=systems.getItemTotalIRDebitQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                qtyIReceivableCredit=systems.getItemTotalIReceivableQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                qtyIPDebit=systems.getItemTotalIPDebitQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                revenueQty=systems.getItemTotalRevenueQty(itemCode[x], "'1920/1/1 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");

                title="::. View Stock Statistics - [Summation Till "+jYearComboBox.getSelectedItem().toString()+"/12/31]";
            }            
            else if(jMonthComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Month --")==false
                    &&jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
                    &&jDayComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Day --")==false
                    &&jCheckBox1.isSelected()){

                irCreditQty=systems.getItemTotalIRCreditQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'"); 
                irDebitQty=systems.getItemTotalIRDebitQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'"); 
                qtyIReceivableCredit=systems.getItemTotalIReceivableQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'");
                qtyIPDebit=systems.getItemTotalIPDebitQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'");
                revenueQty=systems.getItemTotalRevenueQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59'"); 
                previousBal=systems.getItemQuantityAvailable(itemCode[x], "'1920/1/1 00:00:00'", "DATE_SUB('"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" 23:59:59',INTERVAL 1 DAY)");
                title="::. View Stock Statistics - [Strictly "+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+" - "+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+jDayComboBox.getSelectedItem().toString()+"]";
            }
            else if(jMonthComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Month --")==false
                    &&jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
                    &&jDayComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Day --")
                    &&jCheckBox1.isSelected()){

                irCreditQty=systems.getItemTotalIRCreditQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                irDebitQty=systems.getItemTotalIRDebitQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                qtyIReceivableCredit=systems.getItemTotalIReceivableQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                qtyIPDebit=systems.getItemTotalIPDebitQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                revenueQty=systems.getItemTotalRevenueQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +" 23:59:59'");
                previousBal=systems.getItemQuantityAvailable(itemCode[x], "'1920/1/1 00:00:00'", "DATE_SUB('"+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/01 23:59:59',INTERVAL 1 DAY)");
                title="::. View Stock Statistics - [Strictly "+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/01 - "+jYearComboBox.getSelectedItem().toString()+"/"+systems.getMonthDigit(jMonthComboBox.getSelectedItem().toString())+"/"+systems.getMonthMaximumDay(jMonthComboBox.getSelectedItem().toString()) +"]";
            }
            else if(jMonthComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Month --")
                    &&jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
                    &&jDayComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Day --")
                    &&jCheckBox1.isSelected()){

                irCreditQty=systems.getItemTotalIRCreditQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/01/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                irDebitQty=systems.getItemTotalIRDebitQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/01/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                qtyIReceivableCredit=systems.getItemTotalIReceivableQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/01/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                qtyIPDebit=systems.getItemTotalIPDebitQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/01/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                revenueQty=systems.getItemTotalRevenueQty(itemCode[x], "'"+jYearComboBox.getSelectedItem().toString()+"/01/01 00:00:00'", "'"+jYearComboBox.getSelectedItem().toString()+"/12/31 23:59:59'");
                previousBal=systems.getItemQuantityAvailable(itemCode[x], "'1920/1/1 00:00:00'", "DATE_SUB('"+jYearComboBox.getSelectedItem().toString()+"/01/01 23:59:59',INTERVAL 1 DAY)");
                title="::. View Stock Statistics - [Strictly "+jYearComboBox.getSelectedItem().toString()+"/01/01 - "+jYearComboBox.getSelectedItem().toString()+"/12/31]";
            }else{
                String itemData[]=systems.getValue(OpenMSApp.Database_A, "Select * from item where cItemCode='"+itemCode[x]+"'", 8,12,13,14,15,16);

                irCreditQty=itemData[1];
                irDebitQty=itemData[2];
                qtyIReceivableCredit=itemData[3];
                qtyIPDebit=itemData[4];
                revenueQty=itemData[5];
                title="::. View Stock Statistics - [Summation Till Date]";
            }
            String itemUnit=systems.getValue(OpenMSApp.Database_A, "Select * from itemUnit where cItemUnitCode='"+itemUnitCode[x]+"'", 2);

            tableObject[x][0]=itemCode[x].trim();
            tableObject[x][1]=itemName[x].trim();
            tableObject[x][2]=irCreditQty;
            tableObject[x][3]=irDebitQty;
            tableObject[x][4]=revenueQty;
            tableObject[x][5]=qtyIReceivableCredit;
            tableObject[x][6]=qtyIPDebit;
            if(jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
                &&jCheckBox1.isSelected())
            {
                tableObject[x][7]=previousBal;
                tableObject[x][8]=systems.getValue(OpenMSApp.Database_A, "Select ("+previousBal+"+"+irCreditQty+")- ("+irDebitQty+" + "+qtyIPDebit+" + "+revenueQty+" + "+qtyIReceivableCredit+") AS 'Sum'", 1);
                tableObject[x][9]=itemUnit.trim();
            }else
            {
                tableObject[x][7]=systems.getValue(OpenMSApp.Database_A, "Select "+irCreditQty+"- ("+irDebitQty+" + "+qtyIPDebit+" + "+revenueQty+" + "+qtyIReceivableCredit+") AS 'Sum'", 1);
                tableObject[x][8]=itemUnit.trim();            
            }
            systems.freeHeap();
            x+=1;
            if(x==y){break;}
       }

        if(jYearComboBox.getSelectedItem().toString().equalsIgnoreCase("-- Year --")==false
            &&jCheckBox1.isSelected())
        {
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                tableObject,
                new String [] {
                    "Item Code", "Item Name", "Inventory Credit (Qty)", "Inventory Debit (Qty)", "Inventory Receivable (Qty)", "Inventory Payable Debit (Qty)", "Revenue (Qty)", "Previous Balance", "Balance", "Unit"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
        }else
        {
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                tableObject,
                new String [] {
                    "Item Code", "Item Name", "Inventory Credit (Qty)", "Inventory Debit (Qty)", "Inventory Receivable (Qty)", "Inventory Payable Debit (Qty)", "Revenue (Qty)", "Balance", "Unit"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false, false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancelButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jDayComboBox;
    private javax.swing.JComboBox jMonthComboBox;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox jYearComboBox;
    // End of variables declaration//GEN-END:variables

}
