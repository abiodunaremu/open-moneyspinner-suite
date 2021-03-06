
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

final class NewStockCount extends javax.swing.JInternalFrame {
PreparedStatement statement;
Thread stockCountThread=new Thread();
Thread stockCountTitleThread=new Thread();
Object[][] tableObject=new Object[][]{{null,null,null,null,null}};
Systems systems=new Systems();

    /** Creates new form TransactionType */
    NewStockCount() {
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
        jStockCountRegisterButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        transMonthComboBox = new javax.swing.JComboBox();
        transDayComboBox = new javax.swing.JComboBox();
        transYearComboBox = new javax.swing.JComboBox();

        setClosable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(NewStockCount.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setVisible(true);

        jPanel1.setName("jPanel1"); // NOI18N

        jStockCountRegisterButton.setIcon(resourceMap.getIcon("jStockCountRegisterButton.icon")); // NOI18N
        jStockCountRegisterButton.setText(resourceMap.getString("jStockCountRegisterButton.text")); // NOI18N
        jStockCountRegisterButton.setName("jStockCountRegisterButton"); // NOI18N
        jStockCountRegisterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jStockCountRegisterButtonActionPerformed(evt);
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

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "S/N", "Item Code", "Item Name", "Quantity", "Supply Shortfall", "Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title9")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title10")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        transMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Month --" }));
        transMonthComboBox.setName("transMonthComboBox"); // NOI18N
        transMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transMonthComboBoxActionPerformed(evt);
            }
        });

        transDayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Day --" }));
        transDayComboBox.setName("transDayComboBox"); // NOI18N

        transYearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Year --" }));
        transYearComboBox.setName("transYearComboBox"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 533, Short.MAX_VALUE)
                .addComponent(jStockCountRegisterButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(361, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jStockCountRegisterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(transMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(77, Short.MAX_VALUE)))
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

    private void jStockCountRegisterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jStockCountRegisterButtonActionPerformed

            /* Execute submit thread */
            stockCountThread=new Thread(new Runnable() {
                public void run() {
                    disableControls();
                    submitData();
                    reset();
                }
            });
            stockCountTitleThread=new Thread(new Runnable() {
                public void run() {
                    while(stockCountThread.isAlive())setProcessingTitle(stockCountTitleThread);
                    enableControls();
                    System.out.println("stockCountThread stopped runing.");
                }
            });
            stockCountThread.start();
            stockCountTitleThread.start();
        
    }//GEN-LAST:event_jStockCountRegisterButtonActionPerformed
                              
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. New Stock Count - Processing Request");
            thread.sleep(500);
            this.setTitle("::. New Stock Count - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. New Stock Count - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. New Stock Count - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println();}
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        if(stockCountThread.isAlive())
        {
            try{
                stockCountThread.stop();
                stockCountTitleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. New Stock Count");
            enableControls();
        }
        else
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

private void transMonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transMonthComboBoxActionPerformed
        // TODO add your handling code here:
        String transMonth=transMonthComboBox.getSelectedItem().toString();

        if(transMonth.equals("-- Month --")) {
        } else {
            resetTransDay(transMonth);
        }
}//GEN-LAST:event_transMonthComboBoxActionPerformed

    public void resetTransYear()
    {
        String[] year=Systems.getInstance().getYears(1920);
        year[0]="-- Year --";
        transYearComboBox.setModel(new DefaultComboBoxModel(year));
        year=null;
    }
    public void resetTransMonth()
    {
        String[] month=Systems.getInstance().getMonthArray();
        month[0]="-- Month --";

        transMonthComboBox.setModel(new DefaultComboBoxModel(month));
        month=null;
        
    }
    public void resetTransDay(String Month)
    {
        String[] day={"-- Day --"};
        if(Month.isEmpty())
        {
            transDayComboBox.setModel(new DefaultComboBoxModel(day));
        }
        else
        {
            day=Systems.getInstance().getDays(Month);
            day[0]="-- Day --";
            transDayComboBox.setModel(new DefaultComboBoxModel(day));
        }
        day=null;
        
    }
     void reset()
    {
        resetTransDay("");
        resetTransMonth();
        resetTransYear();
        refreshTable("");
    }
     void refreshTable(String searchPara)
     {
        String item[][]=systems.getColumn(OpenMSApp.Database_A, "Select * from Item where cType='Stock'",1,2,9);
        int x=0,y=item[0].length;
        
        tableObject=new Object[y][6];
        for(;x<y;x++)
        {
            tableObject[x][0]=x+1;
            tableObject[x][1]=item[0][x].trim();
            tableObject[x][2]=item[1][x].trim();
            tableObject[x][3]="";
            tableObject[x][4]="";
            tableObject[x][5]=systems.getValue(OpenMSApp.Database_A, "Select * from itemUnit where cItemUnitCode='"+item[2][x].trim()+"'", 2);
        }   

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "S/N", "Item Code", "Item Name", "Total Count",  "Supply Shortfall", "Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false,false, false,true,true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(40);
        jTable1.getColumnModel().getColumn(0).setMaxWidth(40);
        Connect.closeMainConnection();
        Connect.createMSSQLConnection(); 
     }
    private void disableControls()
    {
        jStockCountRegisterButton.setEnabled(false);
    }
    private void enableControls()
    {
        jStockCountRegisterButton.setEnabled(true);
        this.setTitle("::. New Stock Count");
    }

    void resetCombo()
    {
        resetTransYear();
        resetTransMonth();
    }
     void submitData()
    {
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        
        int x=tableObject.length;
        int y=0;
        while(y<x)
        { 
            if(jTable1.getValueAt(y,3).toString().isEmpty()==false&&systems.isStringDigit(jTable1.getValueAt(y,3).toString()))
            {
                statement=Connect.createStatement(Connect.procInit+" prnInsStockCount"+Connect.procInitStart+"?,?,?,?,?"+Connect.procInitEnd);
                try
                {
                    statement.setString(1,jTable1.getValueAt(y, 1).toString());
                    statement.setString(2,OpenMSApp.LoginCode);
                    statement.setString(3,jTable1.getValueAt(y, 3).toString());
                    statement.setString(4,new String(String.valueOf(transYearComboBox.getSelectedItem()).trim()+"-"+Systems.getInstance().getMonthDigit(String.valueOf(transMonthComboBox.getSelectedItem()).trim())+"-"+
                            String.valueOf(transDayComboBox.getSelectedItem()).trim()));
                    statement.setString(5,jTable1.getValueAt(y, 4).toString());
                }
                catch(Exception e)
                {
                    System.out.println("stockCount.submitData() gave error ! "+e);
                }

                Connect.executeUpdateStatement(statement);
            }
            y+=1;
            if(y==x){break;}
        }
        Connect.alert="Y";
        Connect.closeConnection();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jStockCountRegisterButton;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox transDayComboBox;
    private javax.swing.JComboBox transMonthComboBox;
    private javax.swing.JComboBox transYearComboBox;
    // End of variables declaration//GEN-END:variables

}
