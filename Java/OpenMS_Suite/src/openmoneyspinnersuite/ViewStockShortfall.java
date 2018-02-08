
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

final class ViewStockShortfall extends javax.swing.JInternalFrame {

    Object[][] tableObject=new Object[][]{};
    Systems systems=new Systems();
    Thread processThread=new Thread();
    Thread titleThread=new Thread();
    /** Creates new form ViewCredit */
    ViewStockShortfall() {
        initComponents();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "Date","Item Code", "Item Name", "Verified By User", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        /* Execute thread */
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
        jLabel1 = new javax.swing.JLabel();
        transMonthComboBox = new javax.swing.JComboBox();
        transDayComboBox = new javax.swing.JComboBox();
        transYearComboBox = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(ViewStockShortfall.class);
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
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Date", "Item Code", "Item Name", "Counted By User", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(10);
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 605, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addComponent(jRefreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 267, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(transMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        
        if(processThread.isAlive())
        {
            try{
                processThread.stop();
                titleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. View Stock Shortfall");
            enableControls();
        }
        else
        dispose();
    }//GEN-LAST:event_jCancelButtonActionPerformed

private void transMonthComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transMonthComboBoxActionPerformed
        // TODO add your handling code here:
        String transMonth=transMonthComboBox.getSelectedItem().toString();

        if(transMonth.equals("-- Month --")) {
        } else {
            resetTransDay(transMonth);
        }
}//GEN-LAST:event_transMonthComboBoxActionPerformed
    void reset()
    {
        refreshTable("");
        resetTransYear();
        resetTransMonth();
        resetTransDay("");
    }

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
    private void disableControls()
    {
        jRefreshButton.setEnabled(false);
    }                                             
    private void enableControls()
    {
        jRefreshButton.setEnabled(true);
        this.setTitle("::. View Stock Shortfall");
    }
                   
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. View Stock Shortfall - Processing Request");
            thread.sleep(500);
            this.setTitle("::. View Stock Shortfall - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. View Stock Shortfall - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. View Stock Shortfall - Processing Request...");
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
    void refreshTable(String searchPara)
    {
        String dateQuery="";
        if(!transYearComboBox.getSelectedItem().equals("-- Year --")&&!String.valueOf(transMonthComboBox.getSelectedItem()).equals("-- Month --")&&!String.valueOf(transDayComboBox.getSelectedItem()).equals("-- Day --"))
        {
            dateQuery=String.valueOf(transYearComboBox.getSelectedItem()).trim()+"-"+Systems.getInstance().getMonthDigit(String.valueOf(transMonthComboBox.getSelectedItem()).trim())+"-"+
                            String.valueOf(transDayComboBox.getSelectedItem()).trim();
            dateQuery=" where dDate = '"+dateQuery+" 00:00:00'";
        }
        String[][] stockShortfall=systems.getTableDataArrayNum(OpenMSApp.Database_A,"StockCount "+dateQuery+" order by dDate",1,2,3,4);
            if(searchPara.isEmpty()==false)
            {
                String multipleSearch[]=searchPara.split(",");
                String queryPara="where (cItemCode = '"+multipleSearch[0]+"') or (cLoginCode = "+multipleSearch[0]+")  or (dDate = "+multipleSearch[0]+") ";// or (cPhone like '%"+multipleSearch[0]+"%') ";

                for(int k=1;k<multipleSearch.length;k++)
                {
                    queryPara+="or (cItemCode like '%"+multipleSearch[k]+"%') or (cLoginCode like '%"+multipleSearch[k]+"%') or (dDate like '%"+multipleSearch[k]+"%') ";// or (cPhone like '%"+multipleSearch[k]+"%') ";
                }
                stockShortfall=systems.getColumn(OpenMSApp.Database_A, "Select * from StockCount "+queryPara+" order by dDate", 1,2,3,4);
            }

        int x=0;
        int y=stockShortfall[0].length;

        tableObject=new Object[y][5];

        while(x<y)
        {
            tableObject[x][0]=stockShortfall[3][x].trim();
            tableObject[x][1]=stockShortfall[0][x].trim();
            tableObject[x][2]=systems.getValue(OpenMSApp.Database_A, "Select * from item where cItemCode='"+stockShortfall[0][x].trim()+"'", 2);
            tableObject[x][3]=stockShortfall[1][x].trim();
            tableObject[x][4]=stockShortfall[2][x].trim();
            systems.freeHeap();
            x+=1;
            if(x==y){break;}
       }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "Date","Item Code", "Item Name", "Verified By User", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox transDayComboBox;
    private javax.swing.JComboBox transMonthComboBox;
    private javax.swing.JComboBox transYearComboBox;
    // End of variables declaration//GEN-END:variables

}
