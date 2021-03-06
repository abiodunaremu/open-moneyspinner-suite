
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


final class ViewItem extends javax.swing.JInternalFrame {
    Object[][] tableObject=new Object[][]{};
    Systems systems=new Systems();
    Thread processThread=new Thread();
    Thread titleThread=new Thread();
    /** Creates new form ViewCredit */
    public ViewItem() {
        initComponents();
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "Item Code", "Name", "Description", "Type", "Category", "Location", "Price", "Tax", "Quantity", "Total Stock Cost", "Stock Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        
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
        jCheckBox1 = new javax.swing.JCheckBox();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(ViewItem.class);
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
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Item Code", "Name", "Description", "Type", "Category", "Location", "Price", "Quantity", "Tax", "Stock Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title9")); // NOI18N
        jTable1.getColumnModel().getColumn(9).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N

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

        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 858, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 593, Short.MAX_VALUE)
                .addComponent(jRefreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCancelButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
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
            this.setTitle("::. View Item Detail - Processing Request");
            thread.sleep(500);
            this.setTitle("::. View Item Detail - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. View Item Detail - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. View Item Detail - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println("Title thread"+e);}
    }
    private void jCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCancelButtonActionPerformed
        
        if(processThread.isAlive())
        {
            try{
                processThread.stop();
                titleThread.stop();
                System.out.println("Threads successfully stopped.");
            }catch(Exception e){System.out.println("Thread stopping error: "+e);}
            this.setTitle("::. View Item Detail");
            enableControls();
        }
        else
        dispose();
    }//GEN-LAST:event_jCancelButtonActionPerformed
    private void disableControls()
    {
        jRefreshButton.setEnabled(false);
    }
    private void enableControls()
    {
        jRefreshButton.setEnabled(true);
        this.setTitle("::. View Item Detail");
    }
    public void print()
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
    public void refreshTable(String searchPara)
    {
        refresh1(searchPara);
        //refresh2(searchPara);
    }
    void refresh1(String searchPara)
    {
        String[][] itemRecord=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",1,2,3,4,5,6,7,10,8,9,11);
//        String[] itemCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",1);
//        String[] itemName=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",2);
//        String[] itemDescription=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",3);
//        String[] itemType=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",4);
//        String[] itemCategoryCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",5);
//        String[] itemLocationCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",6);
//        String[] itemPrice=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",7);
//        String[] itemTax=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",10);
//        String[] itemQuantity=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",8);
//        String[] itemUnitCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",9);
        
//        String[] itemCode=itemRecord[0];
//        String[] itemName=itemRecord[1];
//        String[] itemDescription=itemRecord[2];
//        String[] itemType=itemRecord[3];
//        String[] itemCategoryCode=itemRecord[4];
//        String[] itemLocationCode=itemRecord[5];
//        String[] itemPrice=itemRecord[6];
//        String[] itemTax=itemRecord[7];
//        //String[] itemQuantity=itemRecord[8];
//        String[] itemUnitCode=itemRecord[9];

            if(searchPara.isEmpty()==false&&searchPara.equalsIgnoreCase(",.")==false)
            {
                String multipleSearch[]=searchPara.split(",");
                String queryPara="where (cItemCode = '"+multipleSearch[0]+"') or (vName = '"+multipleSearch[0]+"') or (cType = '"+multipleSearch[0]+"') ";

                for(int k=1;k<multipleSearch.length;k++)
                {
                    queryPara+="or (cItemCode like '%"+multipleSearch[k]+"%') or (vName like '%"+multipleSearch[k]+"%') or (cType like '%"+multipleSearch[k]+"%') ";
                }

//                itemCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 1);
//                itemName=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 2);
//                itemDescription=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 3);
//                itemType=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 4);
//                itemCategoryCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 5);
//                itemLocationCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 6);
//                itemPrice=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 7);
//                itemTax=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 10);
//                itemQuantity=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 8);
//                itemUnitCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 9);
                
                itemRecord=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara,1,2,3,4,5,6,7,10,8,9,11);
//                itemCode=itemRecord[0];
//                itemName=itemRecord[1];
//                itemDescription=itemRecord[2];
//                itemType=itemRecord[3];
//                itemCategoryCode=itemRecord[4];
//                itemLocationCode=itemRecord[5];
//                itemPrice=itemRecord[6];
//                itemTax=itemRecord[7];
//                //itemQuantity=itemRecord[8];
//                itemUnitCode=itemRecord[9];
            }

        int x=0;
        int y=itemRecord[0].length;

        tableObject=new Object[y][11];

        while(x<y)
        {
           
           //String itemCategory=systems.getValue(OpenMSApp.Database_A, "Select * from ItemCategory Where cItemCategoryCode='"+itemCategoryCode[x]+"'", "vCategoryName").trim();
           //String itemLocation=systems.getValue(OpenMSApp.Database_A, "Select * from ItemLocation Where cItemLocationCode='"+itemLocationCode[x]+"'", "vLocationName").trim();

           String itemCategory=systems.getValue(OpenMSApp.Database_A, "Select vCategoryName from ItemCategory Where cItemCategoryCode='"+itemRecord[4][x]+"'", "vCategoryName").trim();
           String itemLocation=systems.getValue(OpenMSApp.Database_A, "Select vLocationName from ItemLocation Where cItemLocationCode='"+itemRecord[5][x]+"'", "vLocationName").trim();

            //String itemUnit=systems.getValue(OpenMSApp.Database_A, "Select * from ItemUnit Where cItemUnitCode='"+itemUnitCode[x]+"'", "vItemUnitName").trim();

            String itemUnit=systems.getValue(OpenMSApp.Database_A, "Select vItemUnitName from ItemUnit Where cItemUnitCode='"+itemRecord[9][x]+"'", "vItemUnitName").trim();

//            tableObject[x][0]=itemCode[x].trim();
//            tableObject[x][1]=itemName[x].trim();
//            tableObject[x][2]=itemDescription[x].trim();
//            tableObject[x][3]=itemType[x].trim();
//            tableObject[x][4]=itemCategory;
//            tableObject[x][5]=itemLocation;
            
            tableObject[x][0]=itemRecord[0][x].trim();
            tableObject[x][1]=itemRecord[1][x].trim();
            tableObject[x][2]=itemRecord[2][x].trim();
            tableObject[x][3]=itemRecord[3][x].trim();
            tableObject[x][4]=itemCategory;
            tableObject[x][5]=itemLocation;

            try
            {
                tableObject[x][6]=systems.converToRealMoneyFormat(itemRecord[6][x]);
            }
            catch(Exception e)
            {
                tableObject[x][6]="Er!M1C01K0";
            }
            try
            {
//                tableObject[x][7]=systems.converToRealMoneyFormat(itemTax[x]);
                tableObject[x][7]=systems.converToRealMoneyFormat(itemRecord[7][x]);
            }
            catch(Exception e)
            {
                tableObject[x][7]="Er!M1C01K0";
            }
            if(itemRecord[3][x].equalsIgnoreCase("Stock"))
            {
                //tableObject[x][8]=systems.getItemQuantityAvailable(itemRecord[0][x]);
                try{
                tableObject[x][8]=systems.converToRealMoneyFormat(itemRecord[8][x]);  
                }catch(Exception e){
                tableObject[x][8]="Er";
                }
                
                try{
                //tableObject[x][9]=systems.getItemTotalIRCreditCostIn(itemRecord[0][x]);
                tableObject[x][9]=systems.converToRealMoneyFormat(itemRecord[10][x]);                
                }catch(Exception e){
                tableObject[x][9]="Er";}
                if(jCheckBox1.isSelected())
                {
                    try{
                        systems.updateItemStatistics(itemRecord[0][x]);
                    }catch(Exception e){
                        System.out.println("Unable to update item statistics "+itemRecord[0][x]);
                    }
                }
            }
            else
            {
                tableObject[x][8]="N/A";
                tableObject[x][9]="N/A";
            }
            tableObject[x][10]=itemUnit;
            if(searchPara.startsWith(",."))
                systems.updateItemStatistics(itemRecord[0][x]);
            systems.freeHeap();
            x+=1;
            if(x==y){break;}
       }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "Item Code", "Name", "Description", "Type", "Category", "Location", "Price", "Tax", "Quantity", "Total Stock Cost", "Stock Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

    }
    void refresh2(String searchPara)
    {
        String[][] itemRecord=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",1,10,8,9,4);
//        String[] itemCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",1);
//        String[] itemName=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",2);
//        String[] itemDescription=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",3);
//        String[] itemType=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",4);
//        String[] itemCategoryCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",5);
//        String[] itemLocationCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",6);
//        String[] itemPrice=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",7);
//        String[] itemTax=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",10);
//        String[] itemQuantity=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",8);
//        String[] itemUnitCode=systems.getTableDataArrayNum(OpenMSApp.Database_A,"Item",9);
        
//        String[] itemCode=itemRecord[0];
//        String[] itemName=itemRecord[1];
//        String[] itemDescription=itemRecord[2];
//        String[] itemType=itemRecord[3];
//        String[] itemCategoryCode=itemRecord[4];
//        String[] itemLocationCode=itemRecord[5];
//        String[] itemPrice=itemRecord[6];
//        String[] itemTax=itemRecord[7];
//        //String[] itemQuantity=itemRecord[8];
//        String[] itemUnitCode=itemRecord[9];

            if(searchPara.isEmpty()==false)
            {
                String multipleSearch[]=searchPara.split(",");
                String queryPara="where (cItemCode = '"+multipleSearch[0]+"') or (vName = '"+multipleSearch[0]+"') or (cType = '"+multipleSearch[0]+"') ";

                for(int k=1;k<multipleSearch.length;k++)
                {
                    queryPara+="or (cItemCode like '%"+multipleSearch[k]+"%') or (vName like '%"+multipleSearch[k]+"%') or (cType like '%"+multipleSearch[k]+"%') ";
                }

//                itemCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 1);
//                itemName=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 2);
//                itemDescription=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 3);
//                itemType=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 4);
//                itemCategoryCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 5);
//                itemLocationCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 6);
//                itemPrice=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 7);
//                itemTax=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 10);
//                itemQuantity=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 8);
//                itemUnitCode=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara, 9);
                
                itemRecord=systems.getColumn(OpenMSApp.Database_A, "Select * from Item "+queryPara,1,10,8,9,4);
//                itemCode=itemRecord[0];
//                itemName=itemRecord[1];
//                itemDescription=itemRecord[2];
//                itemType=itemRecord[3];
//                itemCategoryCode=itemRecord[4];
//                itemLocationCode=itemRecord[5];
//                itemPrice=itemRecord[6];
//                itemTax=itemRecord[7];
//                //itemQuantity=itemRecord[8];
//                itemUnitCode=itemRecord[9];
            }

        int x=0;
        int y=itemRecord[0].length;

        //tableObject=new Object[y][11];

        while(x<y)
        {
           
           //String itemCategory=systems.getValue(OpenMSApp.Database_A, "Select * from ItemCategory Where cItemCategoryCode='"+itemCategoryCode[x]+"'", "vCategoryName").trim();
           //String itemLocation=systems.getValue(OpenMSApp.Database_A, "Select * from ItemLocation Where cItemLocationCode='"+itemLocationCode[x]+"'", "vLocationName").trim();

           //String itemCategory=systems.getValue(OpenMSApp.Database_A, "Select * from ItemCategory Where cItemCategoryCode='"+itemRecord[4][x]+"'", "vCategoryName").trim();
           //String itemLocation=systems.getValue(OpenMSApp.Database_A, "Select * from ItemLocation Where cItemLocationCode='"+itemRecord[5][x]+"'", "vLocationName").trim();

            //String itemUnit=systems.getValue(OpenMSApp.Database_A, "Select * from ItemUnit Where cItemUnitCode='"+itemUnitCode[x]+"'", "vItemUnitName").trim();

            String itemUnit=systems.getValue(OpenMSApp.Database_A, "Select vItemUnitName from ItemUnit Where cItemUnitCode='"+itemRecord[3][x]+"'", "vItemUnitName").trim();

//            tableObject[x][0]=itemCode[x].trim();
//            tableObject[x][1]=itemName[x].trim();
//            tableObject[x][2]=itemDescription[x].trim();
//            tableObject[x][3]=itemType[x].trim();
//            tableObject[x][4]=itemCategory;
//            tableObject[x][5]=itemLocation;
            
//            tableObject[x][0]=itemRecord[0][x].trim();
//            tableObject[x][1]=itemRecord[1][x].trim();
//            tableObject[x][2]=itemRecord[2][x].trim();
//            tableObject[x][3]=itemRecord[3][x].trim();
//            tableObject[x][4]=itemCategory;
//            tableObject[x][5]=itemLocation;

//            try
//            {
//                tableObject[x][6]=systems.converToRealMoneyFormat(itemRecord[6][x]);
//            }
//            catch(Exception e)
//            {
//                tableObject[x][6]="Er!M1C01K0";
//            }
            try
            {
//                tableObject[x][7]=systems.converToRealMoneyFormat(itemTax[x]);
                tableObject[x][7]=systems.converToRealMoneyFormat(itemRecord[1][x]);
            }
            catch(Exception e)
            {
                tableObject[x][7]="Er!M1C01K0";
            }
            if(itemRecord[4][x].equalsIgnoreCase("Stock"))
            {
                tableObject[x][8]="undercheck";//systems.getItemQuantityAvailable(itemRecord[0][x]);
                
                tableObject[x][9]="undercheck";//systems.getItemTotalIRCreditCostIn(itemRecord[0][x]); 
  
            }
            else
            {
                tableObject[x][8]="N/A";
                tableObject[x][9]="N/A";
            }
            tableObject[x][10]=itemUnit;

            x+=1;
            if(x==y){break;}
       }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "Item Code", "Name", "Description", "Type", "Category", "Location", "Price", "Tax", "Quantity", "Total Stock Cost", "Stock Unit"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });

Connect.closeConnection();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancelButton;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
