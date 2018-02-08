
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


final class ViewCompany extends javax.swing.JInternalFrame {

    Object[][] tableObject=new Object[][]{};
    Systems systems=new Systems();
    Thread processThread=new Thread();
    Thread titleThread=new Thread();

    /** Creates new form ViewCredit */
    public ViewCompany() {
        initComponents();
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
        jRefreshButton = new javax.swing.JButton();
        jCancelButton = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(ViewCompany.class);
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
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Company Code", "Name", "Address", "City", "Last Name", "Country", "Zip", "Phone Number", "Registration No."
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
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
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title10")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title13")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title14")); // NOI18N

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(600, Short.MAX_VALUE)
                .addComponent(jRefreshButton)
                .addGap(18, 18, 18)
                .addComponent(jCancelButton)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            this.setTitle("::. View Company");
            enableControls();
        }
        else
        dispose();
    }//GEN-LAST:event_jCancelButtonActionPerformed
                  
    private void setProcessingTitle(Thread thread)
    {
        try{
            this.setTitle("::. View Company - Processing Request");
            thread.sleep(500);
            this.setTitle("::. View Company - Processing Request.");
            thread.sleep(500);
            this.setTitle("::. View Company - Processing Request..");
            thread.sleep(500);
            this.setTitle("::. View Company - Processing Request...");
            thread.sleep(500);
        }catch(Exception e){System.out.println("Title thread"+e);}
    }
    private void disableControls()
    {
        jRefreshButton.setEnabled(false);        
    }   
    private void enableControls()
    {
        jRefreshButton.setEnabled(true);
        this.setTitle("::. View Company");
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
    public void refreshTable(String searchPara)
    {
            String[] dbCustomerID=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company",1);
            String[] dbName=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company",2);
            String[] dbAddress=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company",3);
            String[] dbCity=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company", 4);
            String[] dbState=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company",5);
            String[] dbCountry=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company",6);

            String[] dbZip=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company",7);
            String[] dbPhone=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company",8);
            String[] dbRegistrationNo=systems.getTableDataArrayNum(OpenMSApp.Database_C, "Company",9);

            if(searchPara.isEmpty()==false)
            {
                String multipleSearch[]=searchPara.split(" ");
                String queryPara="where (cCompanyCode like '%"+multipleSearch[0]+"%') or (vName like '%"+multipleSearch[0]+"%') or (cPhone like '%"+multipleSearch[0]+"%') ";

                for(int k=1;k<multipleSearch.length;k++)
                {
                    queryPara+="or (cCompanyCode like '%"+multipleSearch[k]+"%') or (vName like '%"+multipleSearch[k]+"%') or (cPhone like '%"+multipleSearch[k]+"%') ";
                }

                dbCustomerID=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 1);
                dbName=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 2);
                dbAddress=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 3);
                dbCity=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 4);
                dbState=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 5);
                dbCountry=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 6);
                dbZip=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 7);
                dbPhone=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 8);
                dbRegistrationNo=systems.getColumn(OpenMSApp.Database_C, "Select * from Company "+queryPara, 9);
            }

        int x=0;
        int y=dbCustomerID.length;

        tableObject=new Object[y][9];

        while(x<y)
        {
            String country=systems.getValue(OpenMSApp.Database_C, "Select * from Country Where cCountryCode='"+dbCountry[x]+"'", "vCountryName").trim();
            tableObject[x][0]=dbCustomerID[x].trim();
            tableObject[x][1]=dbName[x].trim();
            tableObject[x][2]=dbAddress[x].trim();
            tableObject[x][3]=dbCity[x].trim();
            tableObject[x][4]=dbState[x].trim();

            tableObject[x][5]=country;

            tableObject[x][6]=dbZip[x].trim();
            tableObject[x][7]=dbPhone[x].trim();
            tableObject[x][8]=dbRegistrationNo[x].trim();
            systems.freeHeap();
            x+=1;
            if(x==y){break;}
       }

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            tableObject,
            new String [] {
                "Company Code", "Name", "Address", "City", "State", "Country", "Zip", "Phone Number", "Registration No."
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jCancelButton;
    private javax.swing.JButton jRefreshButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
