
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
 * OpenMS_Finance_Desktop.java
 */

package openmoneyspinnersuite;

import java.awt.Color;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.*;
/**
 * The application's main frame.
 */
final public class OpenMS_Finance_Desktop extends FrameView {

    public JDialog printCredit;
    public JDialog printDebit;
    Systems systems=new Systems();
    String printStatus=OpenMSApp.PrintWho+" | "+OpenMSApp.PrintCode;
    Thread TRtread;
     OpenMS_Finance_Desktop(SingleFrameApplication app) {
        super(app);

        if(OpenMSApp.appType.equals("AX, Server, Trial"))
        {
            this.getFrame().setTitle("::. Account Delegate ["+systems.toTitleCase(OpenMSApp.EmployeeName)+" : "+OpenMSApp.CompanyName+"] - Milliscript MoneySpinner Suite "+OpenMSApp.appArchType+" 1.5.1 ["+OpenMSApp.trialDay+" Days Left]");
        }
        else if(OpenMSApp.appType.equals("Activated"))
        {
            this.getFrame().setTitle("::. Account Delegate ["+systems.toTitleCase(OpenMSApp.EmployeeName)+" : "+OpenMSApp.CompanyName+"] - Milliscript MoneySpinner Suite "+OpenMSApp.appArchType+" 1.5.1 ");
        }
        org.jdesktop.application.ResourceMap resourceMap0 = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMSApp.class).getContext().getResourceMap(OpenMS_Finance_Desktop.class);
        String filePath=resourceMap0.getResourcesDir()+"MiliscriptDMS_logo.png";
        java.net.URL url = resourceMap0.getClassLoader().getResource(filePath);
        this.getFrame().setIconImage(this.getFrame().getToolkit().getImage(url));

        initComponents();
        ThreadRuner.mainLabel=jLabel1;
        ThreadRuner runner=new ThreadRuner();
        TRtread=new Thread(runner);
        TRtread.start();

        //jLabel1.setText(printStatus);
        //org.jdesktop.application.Application.getInstance(financesystem.FinanceSystemApp.class).getMainFrame().setBackground(Color.red);
        //setTitle("");
        //JFrame main=app.getMainFrame();
        //FinanceSystemApp.getApplication().getMainView().getFrame().setSize(111,5555);
        //FinanceSystemApp.getApplication().getMainView().getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//getMainFrame().setSize(main.getMaximumSize());

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });

        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");

        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }

        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });

        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());

        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });
        OpenMSApp.currentDesktop=this;
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = OpenMSApp.getApplication().getMainFrame();
            aboutBox = new OpenMSAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        OpenMSApp.getApplication().show(aboutBox);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu resetAllControlsMenu = new javax.swing.JMenu();
        NewMenu = new javax.swing.JMenu();
        nCustomerMenuItem = new javax.swing.JMenuItem();
        nVendorMenuItem = new javax.swing.JMenuItem();
        nTransTypeMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        nCreditMenuItem = new javax.swing.JMenuItem();
        nDebitMenuItem = new javax.swing.JMenuItem();
        nInvoiceMenuItem = new javax.swing.JMenuItem();
        nItemCategoryMenuItem = new javax.swing.JMenuItem();
        nItemLocationMenuItem = new javax.swing.JMenuItem();
        nItemMenuItem = new javax.swing.JMenuItem();
        nItemUnitMenuItem = new javax.swing.JMenuItem();
        nCurrencyMenuItem = new javax.swing.JMenuItem();
        nGroupMenuItem = new javax.swing.JMenuItem();
        jResetMenuItem = new javax.swing.JMenuItem();
        jResetComboMenuItem = new javax.swing.JMenuItem();
        jSearchComboMenuItem = new javax.swing.JMenuItem();
        jBulkSMSComboMenuItem = new javax.swing.JMenuItem();
        jChatComboMenuItem = new javax.swing.JMenuItem();
        jSwitchComboMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jAddRowComboMenuItem = new javax.swing.JMenuItem();
        jDeleteRowComboMenuItem = new javax.swing.JMenuItem();
        jMakeOrderComboMenuItem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jPrintMenuItem = new javax.swing.JMenuItem();
        jPrintSetupCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        LogoutMenuItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        vBank = new javax.swing.JMenuItem();
        CreditMenu = new javax.swing.JMenu();
        vcPendingOrderMenuItem = new javax.swing.JMenuItem();
        vcPostedMenuItem = new javax.swing.JMenuItem();
        vcOrderSummaryMenuItem = new javax.swing.JMenuItem();
        vcPendingOrderSummaryMenuItem = new javax.swing.JMenuItem();
        vcInvoiceOrderMenuItem = new javax.swing.JMenuItem();
        DebitMenu = new javax.swing.JMenu();
        vdPendingOrderMenuItem = new javax.swing.JMenuItem();
        vdPostedOrderMenuItem = new javax.swing.JMenuItem();
        vdOrderSummaryMenuItem = new javax.swing.JMenuItem();
        vdPendingOrderSummaryMenuItem = new javax.swing.JMenuItem();
        vCustomerMenuItem = new javax.swing.JMenuItem();
        vDepartmentMenuItem = new javax.swing.JMenuItem();
        vDesignationMenuItem = new javax.swing.JMenuItem();
        vEmployeeMenuItem = new javax.swing.JMenuItem();
        vTransTypeMenuItem = new javax.swing.JMenuItem();
        vVendorMenuItem = new javax.swing.JMenuItem();
        vItemCategoryMenuItem = new javax.swing.JMenuItem();
        vItemLocationMenuItem = new javax.swing.JMenuItem();
        ItemMenu = new javax.swing.JMenu();
        vItemBasicMenuItem = new javax.swing.JMenuItem();
        vItemDetailMenuItem = new javax.swing.JMenuItem();
        vItemUnitMenuItem = new javax.swing.JMenuItem();
        vCurrencyMenuItem = new javax.swing.JMenuItem();
        AccountMenu1 = new javax.swing.JMenu();
        vaMerchantMenuItem1 = new javax.swing.JMenuItem();
        vNoteMenuItem = new javax.swing.JMenuItem();
        vGroupMenuItem = new javax.swing.JMenuItem();
        vProfileMemberMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        eCustomerMenuItem = new javax.swing.JMenuItem();
        eVendorMenuItem = new javax.swing.JMenuItem();
        eItemMenuItem = new javax.swing.JMenuItem();
        PostMenu = new javax.swing.JMenu();
        pCreditMenuItem = new javax.swing.JMenuItem();
        pDebitMenuItem = new javax.swing.JMenuItem();
        PostExpressMenu = new javax.swing.JMenu();
        peCreditMenuItem = new javax.swing.JMenuItem();
        peDebitMenuItem = new javax.swing.JMenuItem();
        jWindowMenu = new javax.swing.JMenu();
        jCloseWindowMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        vTrialMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        MilliscriptMoneySpinnerToolBar = new javax.swing.JToolBar();
        newCreditButton = new javax.swing.JButton();
        newDebitButton = new javax.swing.JButton();
        resetComboControls = new javax.swing.JButton();
        resetAllContols = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jToolBar1 = new javax.swing.JToolBar();
        jGlobalTextField = new javax.swing.JTextField();
        jToolBar2 = new javax.swing.JToolBar();
        jSearchButton = new javax.swing.JButton();
        jSwitchButton = new javax.swing.JButton();
        systemDesktopPane = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 449, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 263, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(OpenMS_Finance_Desktop.class);
        resetAllControlsMenu.setText(resourceMap.getString("resetAllControlsMenu.text")); // NOI18N
        resetAllControlsMenu.setToolTipText(resourceMap.getString("resetAllControlsMenu.toolTipText")); // NOI18N
        resetAllControlsMenu.setName("resetAllControlsMenu"); // NOI18N
        resetAllControlsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetAllControlsMenuActionPerformed(evt);
            }
        });

        NewMenu.setText(resourceMap.getString("NewMenu.text")); // NOI18N
        NewMenu.setName("NewMenu"); // NOI18N
        NewMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewMenuActionPerformed(evt);
            }
        });

        nCustomerMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nCustomerMenuItem.setText(resourceMap.getString("nCustomerMenuItem.text")); // NOI18N
        nCustomerMenuItem.setName("nCustomerMenuItem"); // NOI18N
        nCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCustomerMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nCustomerMenuItem);

        nVendorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nVendorMenuItem.setText(resourceMap.getString("nVendorMenuItem.text")); // NOI18N
        nVendorMenuItem.setName("nVendorMenuItem"); // NOI18N
        nVendorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nVendorMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nVendorMenuItem);

        nTransTypeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nTransTypeMenuItem.setText(resourceMap.getString("nTransTypeMenuItem.text")); // NOI18N
        nTransTypeMenuItem.setName("nTransTypeMenuItem"); // NOI18N
        nTransTypeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nTransTypeMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nTransTypeMenuItem);

        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        nCreditMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nCreditMenuItem.setText(resourceMap.getString("nCreditMenuItem.text")); // NOI18N
        nCreditMenuItem.setName("nCreditMenuItem"); // NOI18N
        nCreditMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCreditMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(nCreditMenuItem);

        nDebitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nDebitMenuItem.setText(resourceMap.getString("nDebitMenuItem.text")); // NOI18N
        nDebitMenuItem.setName("nDebitMenuItem"); // NOI18N
        nDebitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nDebitMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(nDebitMenuItem);

        nInvoiceMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nInvoiceMenuItem.setText(resourceMap.getString("nInvoiceMenuItem.text")); // NOI18N
        nInvoiceMenuItem.setName("nInvoiceMenuItem"); // NOI18N
        nInvoiceMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nInvoiceMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(nInvoiceMenuItem);

        NewMenu.add(jMenu2);

        nItemCategoryMenuItem.setText(resourceMap.getString("nItemCategoryMenuItem.text")); // NOI18N
        nItemCategoryMenuItem.setName("nItemCategoryMenuItem"); // NOI18N
        nItemCategoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nItemCategoryMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nItemCategoryMenuItem);

        nItemLocationMenuItem.setText(resourceMap.getString("nItemLocationMenuItem.text")); // NOI18N
        nItemLocationMenuItem.setName("nItemLocationMenuItem"); // NOI18N
        nItemLocationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nItemLocationMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nItemLocationMenuItem);

        nItemMenuItem.setText(resourceMap.getString("nItemMenuItem.text")); // NOI18N
        nItemMenuItem.setName("nItemMenuItem"); // NOI18N
        nItemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nItemMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nItemMenuItem);

        nItemUnitMenuItem.setText(resourceMap.getString("nItemUnitMenuItem.text")); // NOI18N
        nItemUnitMenuItem.setName("nItemUnitMenuItem"); // NOI18N
        nItemUnitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nItemUnitMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nItemUnitMenuItem);

        nCurrencyMenuItem.setText(resourceMap.getString("nCurrencyMenuItem.text")); // NOI18N
        nCurrencyMenuItem.setName("nCurrencyMenuItem"); // NOI18N
        nCurrencyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCurrencyMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nCurrencyMenuItem);

        nGroupMenuItem.setText(resourceMap.getString("nGroupMenuItem.text")); // NOI18N
        nGroupMenuItem.setName("nGroupMenuItem"); // NOI18N
        nGroupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nGroupMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nGroupMenuItem);

        resetAllControlsMenu.add(NewMenu);

        jResetMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jResetMenuItem.setIcon(resourceMap.getIcon("jResetMenuItem.icon")); // NOI18N
        jResetMenuItem.setText(resourceMap.getString("jResetMenuItem.text")); // NOI18N
        jResetMenuItem.setToolTipText(resourceMap.getString("jResetMenuItem.toolTipText")); // NOI18N
        jResetMenuItem.setName("jResetMenuItem"); // NOI18N
        jResetMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jResetMenuItem);

        jResetComboMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.ALT_MASK));
        jResetComboMenuItem.setIcon(resourceMap.getIcon("jResetComboMenuItem.icon")); // NOI18N
        jResetComboMenuItem.setText(resourceMap.getString("jResetComboMenuItem.text")); // NOI18N
        jResetComboMenuItem.setToolTipText(resourceMap.getString("jResetComboMenuItem.toolTipText")); // NOI18N
        jResetComboMenuItem.setName("jResetComboMenuItem"); // NOI18N
        jResetComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jResetComboMenuItem);

        jSearchComboMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        jSearchComboMenuItem.setIcon(resourceMap.getIcon("jSearchComboMenuItem.icon")); // NOI18N
        jSearchComboMenuItem.setText(resourceMap.getString("jSearchComboMenuItem.text")); // NOI18N
        jSearchComboMenuItem.setToolTipText(resourceMap.getString("jSearchComboMenuItem.toolTipText")); // NOI18N
        jSearchComboMenuItem.setName("jSearchComboMenuItem"); // NOI18N
        jSearchComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jSearchComboMenuItem);

        jBulkSMSComboMenuItem.setText(resourceMap.getString("jBulkSMSComboMenuItem.text")); // NOI18N
        jBulkSMSComboMenuItem.setToolTipText(resourceMap.getString("jBulkSMSComboMenuItem.toolTipText")); // NOI18N
        jBulkSMSComboMenuItem.setName("jBulkSMSComboMenuItem"); // NOI18N
        jBulkSMSComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBulkSMSComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jBulkSMSComboMenuItem);

        jChatComboMenuItem.setText(resourceMap.getString("jChatComboMenuItem.text")); // NOI18N
        jChatComboMenuItem.setToolTipText(resourceMap.getString("jChatComboMenuItem.toolTipText")); // NOI18N
        jChatComboMenuItem.setName("jChatComboMenuItem"); // NOI18N
        jChatComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChatComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jChatComboMenuItem);

        jSwitchComboMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_MASK));
        jSwitchComboMenuItem.setIcon(resourceMap.getIcon("jSwitchComboMenuItem.icon")); // NOI18N
        jSwitchComboMenuItem.setText(resourceMap.getString("jSwitchComboMenuItem.text")); // NOI18N
        jSwitchComboMenuItem.setToolTipText(resourceMap.getString("jSwitchComboMenuItem.toolTipText")); // NOI18N
        jSwitchComboMenuItem.setName("jSwitchComboMenuItem"); // NOI18N
        jSwitchComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSwitchComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jSwitchComboMenuItem);

        jSeparator1.setName("jSeparator1"); // NOI18N
        resetAllControlsMenu.add(jSeparator1);

        jAddRowComboMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F9, 0));
        jAddRowComboMenuItem.setIcon(resourceMap.getIcon("jAddRowComboMenuItem.icon")); // NOI18N
        jAddRowComboMenuItem.setText(resourceMap.getString("jAddRowComboMenuItem.text")); // NOI18N
        jAddRowComboMenuItem.setToolTipText(resourceMap.getString("jAddRowComboMenuItem.toolTipText")); // NOI18N
        jAddRowComboMenuItem.setName("jAddRowComboMenuItem"); // NOI18N
        jAddRowComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAddRowComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jAddRowComboMenuItem);

        jDeleteRowComboMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F11, 0));
        jDeleteRowComboMenuItem.setIcon(resourceMap.getIcon("jDeleteRowComboMenuItem.icon")); // NOI18N
        jDeleteRowComboMenuItem.setText(resourceMap.getString("jDeleteRowComboMenuItem.text")); // NOI18N
        jDeleteRowComboMenuItem.setToolTipText(resourceMap.getString("jDeleteRowComboMenuItem.toolTipText")); // NOI18N
        jDeleteRowComboMenuItem.setName("jDeleteRowComboMenuItem"); // NOI18N
        jDeleteRowComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteRowComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jDeleteRowComboMenuItem);

        jMakeOrderComboMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER, java.awt.event.InputEvent.SHIFT_MASK));
        jMakeOrderComboMenuItem.setIcon(resourceMap.getIcon("jMakeOrderComboMenuItem.icon")); // NOI18N
        jMakeOrderComboMenuItem.setText(resourceMap.getString("jMakeOrderComboMenuItem.text")); // NOI18N
        jMakeOrderComboMenuItem.setToolTipText(resourceMap.getString("jMakeOrderComboMenuItem.toolTipText")); // NOI18N
        jMakeOrderComboMenuItem.setName("jMakeOrderComboMenuItem"); // NOI18N
        jMakeOrderComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMakeOrderComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jMakeOrderComboMenuItem);

        jSeparator3.setName("jSeparator3"); // NOI18N
        resetAllControlsMenu.add(jSeparator3);

        jPrintMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jPrintMenuItem.setIcon(resourceMap.getIcon("jPrintMenuItem.icon")); // NOI18N
        jPrintMenuItem.setText(resourceMap.getString("jPrintMenuItem.text")); // NOI18N
        jPrintMenuItem.setName("jPrintMenuItem"); // NOI18N
        jPrintMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrintMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jPrintMenuItem);

        jPrintSetupCheckBoxMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        jPrintSetupCheckBoxMenuItem.setText(resourceMap.getString("jPrintSetupCheckBoxMenuItem.text")); // NOI18N
        jPrintSetupCheckBoxMenuItem.setName("jPrintSetupCheckBoxMenuItem"); // NOI18N
        jPrintSetupCheckBoxMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPrintSetupCheckBoxMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jPrintSetupCheckBoxMenuItem);

        jSeparator2.setName("jSeparator2"); // NOI18N
        resetAllControlsMenu.add(jSeparator2);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getActionMap(OpenMS_Finance_Desktop.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setToolTipText(resourceMap.getString("exitMenuItem.toolTipText")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(exitMenuItem);

        LogoutMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        LogoutMenuItem.setIcon(resourceMap.getIcon("LogoutMenuItem.icon")); // NOI18N
        LogoutMenuItem.setText(resourceMap.getString("LogoutMenuItem.text")); // NOI18N
        LogoutMenuItem.setName("LogoutMenuItem"); // NOI18N
        LogoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(LogoutMenuItem);

        menuBar.add(resetAllControlsMenu);

        ViewMenu.setText(resourceMap.getString("ViewMenu.text")); // NOI18N
        ViewMenu.setName("ViewMenu"); // NOI18N
        ViewMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewMenuActionPerformed(evt);
            }
        });

        vBank.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK));
        vBank.setText(resourceMap.getString("vBank.text")); // NOI18N
        vBank.setName("vBank"); // NOI18N
        vBank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vBankActionPerformed(evt);
            }
        });
        ViewMenu.add(vBank);

        CreditMenu.setText(resourceMap.getString("CreditMenu.text")); // NOI18N
        CreditMenu.setName("CreditMenu"); // NOI18N

        vcPendingOrderMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        vcPendingOrderMenuItem.setText(resourceMap.getString("vcPendingOrderMenuItem.text")); // NOI18N
        vcPendingOrderMenuItem.setName("vcPendingOrderMenuItem"); // NOI18N
        vcPendingOrderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vcPendingOrderMenuItemActionPerformed(evt);
            }
        });
        CreditMenu.add(vcPendingOrderMenuItem);

        vcPostedMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        vcPostedMenuItem.setText(resourceMap.getString("vcPostedMenuItem.text")); // NOI18N
        vcPostedMenuItem.setName("vcPostedMenuItem"); // NOI18N
        vcPostedMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vcPostedMenuItemActionPerformed(evt);
            }
        });
        CreditMenu.add(vcPostedMenuItem);

        vcOrderSummaryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        vcOrderSummaryMenuItem.setText(resourceMap.getString("vcOrderSummaryMenuItem.text")); // NOI18N
        vcOrderSummaryMenuItem.setName("vcOrderSummaryMenuItem"); // NOI18N
        vcOrderSummaryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vcOrderSummaryMenuItemActionPerformed(evt);
            }
        });
        CreditMenu.add(vcOrderSummaryMenuItem);

        vcPendingOrderSummaryMenuItem.setText(resourceMap.getString("vcPendingOrderSummaryMenuItem.text")); // NOI18N
        vcPendingOrderSummaryMenuItem.setName("vcPendingOrderSummaryMenuItem"); // NOI18N
        vcPendingOrderSummaryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vcPendingOrderSummaryMenuItemActionPerformed(evt);
            }
        });
        CreditMenu.add(vcPendingOrderSummaryMenuItem);

        vcInvoiceOrderMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        vcInvoiceOrderMenuItem.setText(resourceMap.getString("vcInvoiceOrderMenuItem.text")); // NOI18N
        vcInvoiceOrderMenuItem.setName("vcInvoiceOrderMenuItem"); // NOI18N
        vcInvoiceOrderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vcInvoiceOrderMenuItemActionPerformed(evt);
            }
        });
        CreditMenu.add(vcInvoiceOrderMenuItem);

        ViewMenu.add(CreditMenu);

        DebitMenu.setText(resourceMap.getString("DebitMenu.text")); // NOI18N
        DebitMenu.setName("DebitMenu"); // NOI18N

        vdPendingOrderMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        vdPendingOrderMenuItem.setText(resourceMap.getString("vdPendingOrderMenuItem.text")); // NOI18N
        vdPendingOrderMenuItem.setName("vdPendingOrderMenuItem"); // NOI18N
        vdPendingOrderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vdPendingOrderMenuItemActionPerformed(evt);
            }
        });
        DebitMenu.add(vdPendingOrderMenuItem);

        vdPostedOrderMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        vdPostedOrderMenuItem.setText(resourceMap.getString("vdPostedOrderMenuItem.text")); // NOI18N
        vdPostedOrderMenuItem.setName("vdPostedOrderMenuItem"); // NOI18N
        vdPostedOrderMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vdPostedOrderMenuItemActionPerformed(evt);
            }
        });
        DebitMenu.add(vdPostedOrderMenuItem);

        vdOrderSummaryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        vdOrderSummaryMenuItem.setText(resourceMap.getString("vdOrderSummaryMenuItem.text")); // NOI18N
        vdOrderSummaryMenuItem.setName("vdOrderSummaryMenuItem"); // NOI18N
        vdOrderSummaryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vdOrderSummaryMenuItemActionPerformed(evt);
            }
        });
        DebitMenu.add(vdOrderSummaryMenuItem);

        vdPendingOrderSummaryMenuItem.setText(resourceMap.getString("vdPendingOrderSummaryMenuItem.text")); // NOI18N
        vdPendingOrderSummaryMenuItem.setName("vdPendingOrderSummaryMenuItem"); // NOI18N
        vdPendingOrderSummaryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vdPendingOrderSummaryMenuItemActionPerformed(evt);
            }
        });
        DebitMenu.add(vdPendingOrderSummaryMenuItem);

        ViewMenu.add(DebitMenu);

        vCustomerMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        vCustomerMenuItem.setText(resourceMap.getString("vCustomerMenuItem.text")); // NOI18N
        vCustomerMenuItem.setName("vCustomerMenuItem"); // NOI18N
        vCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vCustomerMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vCustomerMenuItem);

        vDepartmentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        vDepartmentMenuItem.setText(resourceMap.getString("vDepartmentMenuItem.text")); // NOI18N
        vDepartmentMenuItem.setName("vDepartmentMenuItem"); // NOI18N
        vDepartmentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vDepartmentMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vDepartmentMenuItem);

        vDesignationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK));
        vDesignationMenuItem.setText(resourceMap.getString("vDesignationMenuItem.text")); // NOI18N
        vDesignationMenuItem.setName("vDesignationMenuItem"); // NOI18N
        vDesignationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vDesignationMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vDesignationMenuItem);

        vEmployeeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        vEmployeeMenuItem.setText(resourceMap.getString("vEmployeeMenuItem.text")); // NOI18N
        vEmployeeMenuItem.setName("vEmployeeMenuItem"); // NOI18N
        vEmployeeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vEmployeeMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vEmployeeMenuItem);

        vTransTypeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK));
        vTransTypeMenuItem.setText(resourceMap.getString("vTransTypeMenuItem.text")); // NOI18N
        vTransTypeMenuItem.setName("vTransTypeMenuItem"); // NOI18N
        vTransTypeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vTransTypeMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vTransTypeMenuItem);

        vVendorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK));
        vVendorMenuItem.setText(resourceMap.getString("vVendorMenuItem.text")); // NOI18N
        vVendorMenuItem.setName("vVendorMenuItem"); // NOI18N
        vVendorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vVendorMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vVendorMenuItem);

        vItemCategoryMenuItem.setText(resourceMap.getString("vItemCategoryMenuItem.text")); // NOI18N
        vItemCategoryMenuItem.setName("vItemCategoryMenuItem"); // NOI18N
        vItemCategoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vItemCategoryMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vItemCategoryMenuItem);

        vItemLocationMenuItem.setText(resourceMap.getString("vItemLocationMenuItem.text")); // NOI18N
        vItemLocationMenuItem.setName("vItemLocationMenuItem"); // NOI18N
        vItemLocationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vItemLocationMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vItemLocationMenuItem);

        ItemMenu.setText(resourceMap.getString("ItemMenu.text")); // NOI18N
        ItemMenu.setName("ItemMenu"); // NOI18N

        vItemBasicMenuItem.setText(resourceMap.getString("vItemBasicMenuItem.text")); // NOI18N
        vItemBasicMenuItem.setName("vItemBasicMenuItem"); // NOI18N
        vItemBasicMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vItemBasicMenuItemActionPerformed(evt);
            }
        });
        ItemMenu.add(vItemBasicMenuItem);

        vItemDetailMenuItem.setText(resourceMap.getString("vItemDetailMenuItem.text")); // NOI18N
        vItemDetailMenuItem.setName("vItemDetailMenuItem"); // NOI18N
        vItemDetailMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vItemDetailMenuItemActionPerformed(evt);
            }
        });
        ItemMenu.add(vItemDetailMenuItem);

        ViewMenu.add(ItemMenu);

        vItemUnitMenuItem.setText(resourceMap.getString("vItemUnitMenuItem.text")); // NOI18N
        vItemUnitMenuItem.setName("vItemUnitMenuItem"); // NOI18N
        vItemUnitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vItemUnitMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vItemUnitMenuItem);

        vCurrencyMenuItem.setText(resourceMap.getString("vCurrencyMenuItem.text")); // NOI18N
        vCurrencyMenuItem.setName("vCurrencyMenuItem"); // NOI18N
        vCurrencyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vCurrencyMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vCurrencyMenuItem);

        AccountMenu1.setText(resourceMap.getString("AccountMenu1.text")); // NOI18N
        AccountMenu1.setName("AccountMenu1"); // NOI18N

        vaMerchantMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        vaMerchantMenuItem1.setText(resourceMap.getString("vaMerchantMenuItem1.text")); // NOI18N
        vaMerchantMenuItem1.setName("vaMerchantMenuItem1"); // NOI18N
        vaMerchantMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaMerchantMenuItem1ActionPerformed(evt);
            }
        });
        AccountMenu1.add(vaMerchantMenuItem1);

        vNoteMenuItem.setText(resourceMap.getString("vNoteMenuItem.text")); // NOI18N
        vNoteMenuItem.setName("vNoteMenuItem"); // NOI18N
        vNoteMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vNoteMenuItemActionPerformed(evt);
            }
        });
        AccountMenu1.add(vNoteMenuItem);

        ViewMenu.add(AccountMenu1);

        vGroupMenuItem.setText(resourceMap.getString("vGroupMenuItem.text")); // NOI18N
        vGroupMenuItem.setName("vGroupMenuItem"); // NOI18N
        vGroupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vGroupMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vGroupMenuItem);

        vProfileMemberMenuItem.setText(resourceMap.getString("vProfileMemberMenuItem.text")); // NOI18N
        vProfileMemberMenuItem.setName("vProfileMemberMenuItem"); // NOI18N
        vProfileMemberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vProfileMemberMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vProfileMemberMenuItem);

        menuBar.add(ViewMenu);

        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        eCustomerMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        eCustomerMenuItem.setText(resourceMap.getString("eCustomerMenuItem.text")); // NOI18N
        eCustomerMenuItem.setName("eCustomerMenuItem"); // NOI18N
        eCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eCustomerMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eCustomerMenuItem);

        eVendorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        eVendorMenuItem.setText(resourceMap.getString("eVendorMenuItem.text")); // NOI18N
        eVendorMenuItem.setName("eVendorMenuItem"); // NOI18N
        eVendorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eVendorMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eVendorMenuItem);

        eItemMenuItem.setText(resourceMap.getString("eItemMenuItem.text")); // NOI18N
        eItemMenuItem.setName("eItemMenuItem"); // NOI18N
        eItemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eItemMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eItemMenuItem);

        menuBar.add(jMenu1);

        PostMenu.setText(resourceMap.getString("PostMenu.text")); // NOI18N
        PostMenu.setName("PostMenu"); // NOI18N
        PostMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostMenuActionPerformed(evt);
            }
        });

        pCreditMenuItem.setText(resourceMap.getString("pCreditMenuItem.text")); // NOI18N
        pCreditMenuItem.setName("pCreditMenuItem"); // NOI18N
        pCreditMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pCreditMenuItemActionPerformed(evt);
            }
        });
        PostMenu.add(pCreditMenuItem);

        pDebitMenuItem.setText(resourceMap.getString("pDebitMenuItem.text")); // NOI18N
        pDebitMenuItem.setName("pDebitMenuItem"); // NOI18N
        pDebitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pDebitMenuItemActionPerformed(evt);
            }
        });
        PostMenu.add(pDebitMenuItem);

        PostExpressMenu.setText(resourceMap.getString("PostExpressMenu.text")); // NOI18N
        PostExpressMenu.setName("PostExpressMenu"); // NOI18N
        PostExpressMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PostExpressMenuActionPerformed(evt);
            }
        });

        peCreditMenuItem.setText(resourceMap.getString("peCreditMenuItem.text")); // NOI18N
        peCreditMenuItem.setName("peCreditMenuItem"); // NOI18N
        peCreditMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peCreditMenuItemActionPerformed(evt);
            }
        });
        PostExpressMenu.add(peCreditMenuItem);

        peDebitMenuItem.setText(resourceMap.getString("peDebitMenuItem.text")); // NOI18N
        peDebitMenuItem.setName("peDebitMenuItem"); // NOI18N
        peDebitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peDebitMenuItemActionPerformed(evt);
            }
        });
        PostExpressMenu.add(peDebitMenuItem);

        PostMenu.add(PostExpressMenu);

        menuBar.add(PostMenu);

        jWindowMenu.setText(resourceMap.getString("jWindowMenu.text")); // NOI18N
        jWindowMenu.setName("jWindowMenu"); // NOI18N

        jCloseWindowMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        jCloseWindowMenuItem.setText(resourceMap.getString("jCloseWindowMenuItem.text")); // NOI18N
        jCloseWindowMenuItem.setName("jCloseWindowMenuItem"); // NOI18N
        jCloseWindowMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCloseWindowMenuItemActionPerformed(evt);
            }
        });
        jWindowMenu.add(jCloseWindowMenuItem);

        menuBar.add(jWindowMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        vTrialMenuItem.setText(resourceMap.getString("vTrialMenuItem.text")); // NOI18N
        vTrialMenuItem.setName("vTrialMenuItem"); // NOI18N
        vTrialMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vTrialMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(vTrialMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(448, 28));

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1213, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(statusMessageLabel))
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)))
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(statusAnimationLabel))
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusMessageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusAnimationLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25))
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(progressBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 14, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        MilliscriptMoneySpinnerToolBar.setRollover(true);
        MilliscriptMoneySpinnerToolBar.setAlignmentY(0.5F);
        MilliscriptMoneySpinnerToolBar.setEnabled(false);
        MilliscriptMoneySpinnerToolBar.setMinimumSize(new java.awt.Dimension(427, 43));
        MilliscriptMoneySpinnerToolBar.setName("MilliscriptMoneySpinnerToolBar"); // NOI18N
        MilliscriptMoneySpinnerToolBar.setPreferredSize(new java.awt.Dimension(473, 38));

        newCreditButton.setIcon(resourceMap.getIcon("newCreditButton.icon")); // NOI18N
        newCreditButton.setText(resourceMap.getString("newCreditButton.text")); // NOI18N
        newCreditButton.setToolTipText(resourceMap.getString("newCreditButton.toolTipText")); // NOI18N
        newCreditButton.setFocusable(false);
        newCreditButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newCreditButton.setName("newCreditButton"); // NOI18N
        newCreditButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newCreditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCreditButtonActionPerformed(evt);
            }
        });
        MilliscriptMoneySpinnerToolBar.add(newCreditButton);

        newDebitButton.setIcon(resourceMap.getIcon("newDebitButton.icon")); // NOI18N
        newDebitButton.setText(resourceMap.getString("newDebitButton.text")); // NOI18N
        newDebitButton.setToolTipText(resourceMap.getString("newDebitButton.toolTipText")); // NOI18N
        newDebitButton.setFocusable(false);
        newDebitButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newDebitButton.setName("newDebitButton"); // NOI18N
        newDebitButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newDebitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newDebitButtonActionPerformed(evt);
            }
        });
        MilliscriptMoneySpinnerToolBar.add(newDebitButton);

        resetComboControls.setIcon(resourceMap.getIcon("resetComboBox.icon")); // NOI18N
        resetComboControls.setToolTipText(resourceMap.getString("resetComboBox.toolTipText")); // NOI18N
        resetComboControls.setFocusable(false);
        resetComboControls.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resetComboControls.setName("resetComboBox"); // NOI18N
        resetComboControls.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        resetComboControls.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetComboControlsActionPerformed(evt);
            }
        });
        MilliscriptMoneySpinnerToolBar.add(resetComboControls);

        resetAllContols.setIcon(resourceMap.getIcon("resetAllControls.icon")); // NOI18N
        resetAllContols.setText(resourceMap.getString("resetAllControls.text")); // NOI18N
        resetAllContols.setToolTipText(resourceMap.getString("resetAllControls.toolTipText")); // NOI18N
        resetAllContols.setFocusable(false);
        resetAllContols.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resetAllContols.setName("resetAllControls"); // NOI18N
        resetAllContols.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        resetAllContols.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetAllContolsActionPerformed(evt);
            }
        });
        MilliscriptMoneySpinnerToolBar.add(resetAllContols);

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(180);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setAlignmentY(0.5F);
        jSplitPane1.setAutoscrolls(true);
        jSplitPane1.setEnabled(false);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(254, 41));
        jSplitPane1.setName("jSplitPane1"); // NOI18N
        jSplitPane1.setPreferredSize(new java.awt.Dimension(261, 38));

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jGlobalTextField.setAlignmentX(0.0F);
        jGlobalTextField.setMaximumSize(new java.awt.Dimension(150, 20));
        jGlobalTextField.setMinimumSize(new java.awt.Dimension(150, 20));
        jGlobalTextField.setName("jGlobalTextField"); // NOI18N
        jToolBar1.add(jGlobalTextField);

        jSplitPane1.setLeftComponent(jToolBar1);

        jToolBar2.setRollover(true);
        jToolBar2.setEnabled(false);
        jToolBar2.setName("jToolBar2"); // NOI18N

        jSearchButton.setIcon(resourceMap.getIcon("jSearchButton.icon")); // NOI18N
        jSearchButton.setToolTipText(resourceMap.getString("jSearchButton.toolTipText")); // NOI18N
        jSearchButton.setFocusable(false);
        jSearchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jSearchButton.setName("jSearchButton"); // NOI18N
        jSearchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(jSearchButton);

        jSwitchButton.setIcon(resourceMap.getIcon("jSwitchButton.icon")); // NOI18N
        jSwitchButton.setToolTipText(resourceMap.getString("jSwitchButton.toolTipText")); // NOI18N
        jSwitchButton.setFocusable(false);
        jSwitchButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jSwitchButton.setName("jSwitchButton"); // NOI18N
        jSwitchButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSwitchButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(jSwitchButton);

        jSplitPane1.setRightComponent(jToolBar2);

        MilliscriptMoneySpinnerToolBar.add(jSplitPane1);

        systemDesktopPane.setBackground(resourceMap.getColor("systemDesktopPane.background")); // NOI18N
        systemDesktopPane.setAutoscrolls(true);
        systemDesktopPane.setName("systemDesktopPane"); // NOI18N
        systemDesktopPane.setRequestFocusEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(1071, 390, 110, 0);
        systemDesktopPane.add(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        setComponent(systemDesktopPane);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
        setToolBar(MilliscriptMoneySpinnerToolBar);
    }// </editor-fold>//GEN-END:initComponents

    private void LogoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutMenuItemActionPerformed
        // TODO add your handling code here:
        String access=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnLogout"+Connect.procInitStart+"'"+OpenMSApp.LoginCode+"'"+Connect.procInitEnd, "cState");

        if(access.equalsIgnoreCase("Out"))
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request Proccessed Successfully !","Execution SUCCESS",JOptionPane.INFORMATION_MESSAGE);
            if(OpenMSApp.appArchType.equals("Server"))
                new Login().setVisible(true);
            else if(OpenMSApp.appArchType.equals("Client"))
                new ClientLogin().setVisible(true);
            this.getFrame().dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"  User did not Login \n"+OpenMSApp.EmployeeName,"Execution SUCCESS",JOptionPane.INFORMATION_MESSAGE);
            if(OpenMSApp.appArchType.equals("Server"))
                new Login().setVisible(true);
            else if(OpenMSApp.appArchType.equals("Client"))
                new ClientLogin().setVisible(true);
            this.getFrame().dispose();
        }
        OpenMSApp.resetUserParameter();
    }//GEN-LAST:event_LogoutMenuItemActionPerformed

    private javax.swing.JDesktopPane getDesktopPane()
    {
        return systemDesktopPane;
    }
    private void nCreditMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nCreditMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame nCreditOrder=new NewCreditOrder();
        systemDesktopPane.add(nCreditOrder);
        systemDesktopPane.moveToFront(nCreditOrder);

        try
        {
            nCreditOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new credit order ! "+e);
        }
    }//GEN-LAST:event_nCreditMenuItemActionPerformed

    private void PostMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostMenuActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new PostCreditOrder());

    }//GEN-LAST:event_PostMenuActionPerformed

    private void vcPendingOrderMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vcPendingOrderMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vPendingCreditOrder=new ViewPendingCreditOrder();
        systemDesktopPane.add(vPendingCreditOrder);
        systemDesktopPane.moveToFront(vPendingCreditOrder);

        try
        {
            vPendingCreditOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view pending credit order ! "+e);
        }
    }//GEN-LAST:event_vcPendingOrderMenuItemActionPerformed

    private void vcPostedMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vcPostedMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vCredit=new ViewCredit();
        systemDesktopPane.add(vCredit);
        systemDesktopPane.moveToFront(vCredit);

        try
        {
            vCredit.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view credit ! "+e);
        }
    }//GEN-LAST:event_vcPostedMenuItemActionPerformed

    private void pCreditMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pCreditMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame pCreditOrder=new PostCreditOrder();
        systemDesktopPane.add(pCreditOrder);
        systemDesktopPane.moveToFront(pCreditOrder);

        try
        {
            pCreditOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding post credit order ! "+e);
        }
    }//GEN-LAST:event_pCreditMenuItemActionPerformed

    private void vcOrderSummaryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vcOrderSummaryMenuItemActionPerformed
        // TODO add your handling code here
        javax.swing.JInternalFrame vCreditReport=new ViewCreditReport();
        systemDesktopPane.add(vCreditReport);
        systemDesktopPane.moveToFront(vCreditReport);

        try
        {
            vCreditReport.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding employee ! "+e);
        }
    }//GEN-LAST:event_vcOrderSummaryMenuItemActionPerformed

    private void vEmployeeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vEmployeeMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewEmployee=new ViewEmployee();
        systemDesktopPane.add(viewEmployee);
        systemDesktopPane.moveToFront(viewEmployee);

        try
        {
            viewEmployee.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view employee ! "+e);
        }
    }//GEN-LAST:event_vEmployeeMenuItemActionPerformed

    private void vCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vCustomerMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewCustomer=new ViewCustomer();
        systemDesktopPane.add(viewCustomer);
        systemDesktopPane.moveToFront(viewCustomer);

        try
        {
            viewCustomer.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view customer ! "+e);
        }
    }//GEN-LAST:event_vCustomerMenuItemActionPerformed

    private void nCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nCustomerMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame newCustomer=new Customer();
        systemDesktopPane.add(newCustomer);
        systemDesktopPane.moveToFront(newCustomer);

        try
        {
            newCustomer.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new customer ! "+e);
        }

    }//GEN-LAST:event_nCustomerMenuItemActionPerformed

    private void eCustomerMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eCustomerMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame editCustomer=new EditCustomer();
        systemDesktopPane.add(editCustomer);
        systemDesktopPane.moveToFront(editCustomer);

        try
        {
            editCustomer.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding edit customer ! "+e);
        }

    }//GEN-LAST:event_eCustomerMenuItemActionPerformed

    private void nTransTypeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nTransTypeMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame tranType=new TransactionType();
        systemDesktopPane.add(tranType);
        systemDesktopPane.moveToFront(tranType);

        try
        {
            tranType.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding transaction type ! "+e);
        }
    }//GEN-LAST:event_nTransTypeMenuItemActionPerformed

    private void ViewMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewMenuActionPerformed

    private void vTransTypeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vTransTypeMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vTranType=new ViewTransactionType();
        systemDesktopPane.add(vTranType);
        systemDesktopPane.moveToFront(vTranType);

        try
        {
            vTranType.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view transaction type ! "+e);
        }
    }//GEN-LAST:event_vTransTypeMenuItemActionPerformed

    private void vDepartmentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vDepartmentMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewDepartment=new ViewDepartment();
        systemDesktopPane.add(viewDepartment);
        systemDesktopPane.moveToFront(viewDepartment);

        try
        {
            viewDepartment.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding department ! "+e);
        }

    }//GEN-LAST:event_vDepartmentMenuItemActionPerformed

    private void vDesignationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vDesignationMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewDesignation=new ViewDesignation();
        systemDesktopPane.add(viewDesignation);
        systemDesktopPane.moveToFront(viewDesignation);

        try
        {
            viewDesignation.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding department ! "+e);
        }

    }//GEN-LAST:event_vDesignationMenuItemActionPerformed

    private void vBankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vBankActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewbank=new ViewBank();
        systemDesktopPane.add(viewbank);
        systemDesktopPane.moveToFront(viewbank);

        try
        {
            viewbank.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view bank ! "+e);
        }
    }//GEN-LAST:event_vBankActionPerformed

    private void nDebitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nDebitMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame nDebitOrder=new NewDebitOrder();
        systemDesktopPane.add(nDebitOrder);
        systemDesktopPane.moveToFront(nDebitOrder);
        try
        {
            nDebitOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new debit order ! "+e);
        }
    }//GEN-LAST:event_nDebitMenuItemActionPerformed

    private void vdPendingOrderMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vdPendingOrderMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vpDebitOrder=new ViewPendingDebitOrder();
        systemDesktopPane.add(vpDebitOrder);
        systemDesktopPane.moveToFront(vpDebitOrder);
        try
        {
            vpDebitOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view pending debit order ! "+e);
        }
    }//GEN-LAST:event_vdPendingOrderMenuItemActionPerformed

    private void vdPostedOrderMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vdPostedOrderMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vDebit=new ViewDebit();
        systemDesktopPane.add(vDebit);
        systemDesktopPane.moveToFront(vDebit);
        try
        {
            vDebit.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view debit ! "+e);
        }
    }//GEN-LAST:event_vdPostedOrderMenuItemActionPerformed

    private void vdOrderSummaryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vdOrderSummaryMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vDebitReport=new ViewDebitReport();
        systemDesktopPane.add(vDebitReport);
        systemDesktopPane.moveToFront(vDebitReport);
        try
        {
            vDebitReport.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view debit report ! "+e);
        }
    }//GEN-LAST:event_vdOrderSummaryMenuItemActionPerformed

    private void pDebitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pDebitMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame pDebitOrder=new PostDebitOrder();
        systemDesktopPane.add(pDebitOrder);
        systemDesktopPane.moveToFront(pDebitOrder);
        try
        {
            pDebitOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding post debit order ! "+e);
        }
    }//GEN-LAST:event_pDebitMenuItemActionPerformed

    private void NewMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewMenuActionPerformed

    private void newDebitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDebitButtonActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame nDebitOrder=new NewDebitOrder();
        systemDesktopPane.add(nDebitOrder);
        systemDesktopPane.moveToFront(nDebitOrder);
        try
        {
            nDebitOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new debit order ! "+e);
        }
    }//GEN-LAST:event_newDebitButtonActionPerformed

    private void newCreditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCreditButtonActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame nCreditOrder=new NewCreditOrder();
        systemDesktopPane.add(nCreditOrder);
        systemDesktopPane.moveToFront(nCreditOrder);

        try
        {
            nCreditOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new credit order ! "+e);
        }
    }//GEN-LAST:event_newCreditButtonActionPerformed

    private void jPrintSetupCheckBoxMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrintSetupCheckBoxMenuItemActionPerformed
        // TODO add your handling code here:
        if(jPrintSetupCheckBoxMenuItem.isSelected())
        {
            PrintDocument.showPageDialog="Y";
        }
        else
        {
            PrintDocument.showPageDialog="N";
        }
    }//GEN-LAST:event_jPrintSetupCheckBoxMenuItemActionPerformed

    private void jPrintMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPrintMenuItemActionPerformed
        // TODO add your handling code here:
        String frameTitle=systemDesktopPane.getSelectedFrame().getTitle();
        if(frameTitle.equalsIgnoreCase("::. Print Receipt"))
        {
            OpenMSApp.PrintComponent=((PrintReceipt)systemDesktopPane.getSelectedFrame()).getPrintComponent();
        }
        if(frameTitle.equalsIgnoreCase("::. View Account"))
        {
            ((ViewAccount)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Account Report"))
        {
            ((ViewAccountReport)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Backup"))
        {
            ((ViewBackup)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Bank"))
        {
            ((ViewBank)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Batch"))
        {
            ((ViewBatch)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.startsWith("::. View Credit Report"))
        {
            ((ViewCreditReport)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.startsWith("::. View Debit Report"))
        {
            ((ViewDebitReport)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Customer"))
        {
            ((ViewCustomer)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Department"))
        {
            ((ViewDepartment)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Designation"))
        {
            ((ViewDesignation)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Employee"))
        {
            ((ViewEmployee)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Detail"))
        {
            ((ViewItem)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Category"))
        {
            ((ViewItemCategory)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Location"))
        {
            ((ViewItemLocation)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Unit"))
        {
            ((ViewItemUnit)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View System User"))
        {
            ((ViewLogin)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View System User Activity"))
        {
            ((ViewLoginRecord)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Merchant"))
        {
            ((ViewMerchant)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Basic"))
        {
            ((ViewItemBasic)systemDesktopPane.getSelectedFrame()).print();
        }
        if(frameTitle.equalsIgnoreCase("::. View Stock Count"))
        {
            ((ViewStockCount)systemDesktopPane.getSelectedFrame()).print();
        }
        if(frameTitle.equalsIgnoreCase("::. View Stock Report"))
        {
            ((StockReport)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Cash Report"))
        {
            ((CashReport)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.startsWith("::. View Pending Credit Order -"))
        {
            int x=JOptionPane.showConfirmDialog(JOptionPane.getRootFrame()," Do you want to print receipt ? \n Click Yes to print receipt or No to print table.","Confirm Print Document !",JOptionPane.YES_NO_CANCEL_OPTION);
            if(x==JOptionPane.NO_OPTION){
            ((ViewPendingCreditOrder)systemDesktopPane.getSelectedFrame()).print();
            }
            if(x==JOptionPane.YES_OPTION){
            ((ViewPendingCreditOrder)systemDesktopPane.getSelectedFrame()).printDoc();
            }
            return;
        }
        if(frameTitle.startsWith("::. View Pending Credit Order Summary"))
        {
            int x=JOptionPane.showConfirmDialog(JOptionPane.getRootFrame()," Do you want to print receipt ? \n Click Yes to print receipt or No to print table.","Confirm Print Document !",JOptionPane.YES_NO_CANCEL_OPTION);
            if(x==JOptionPane.NO_OPTION){
            ((ViewPendingCreditOrderSummary)systemDesktopPane.getSelectedFrame()).print();
            }
            if(x==JOptionPane.YES_OPTION){
            ((ViewPendingCreditOrderSummary)systemDesktopPane.getSelectedFrame()).printDoc();
            }
            return;
        }
        if(frameTitle.startsWith("::. View Credits - Order By User"))
        {
            int x=JOptionPane.showConfirmDialog(JOptionPane.getRootFrame()," Do you want to print receipt ? \n Click Yes to print receipt or No to print table.","Confirm Print Document !",JOptionPane.YES_NO_CANCEL_OPTION);
            if(x==JOptionPane.NO_OPTION){
            ((ViewCredit)systemDesktopPane.getSelectedFrame()).print();
            }
            if(x==JOptionPane.YES_OPTION){
            ((ViewCredit)systemDesktopPane.getSelectedFrame()).printDoc();
            }
            return;
        }
        if(frameTitle.startsWith("::. View Debits - Order By User"))
        {
            int x=JOptionPane.showConfirmDialog(JOptionPane.getRootFrame()," Do you want to print voucher ? \n Click Yes to print receipt or No to print table.","Confirm Print Document !",JOptionPane.YES_NO_CANCEL_OPTION);
            if(x==JOptionPane.NO_OPTION){
            ((ViewDebit)systemDesktopPane.getSelectedFrame()).print();
                        }
            if(x==JOptionPane.YES_OPTION){
            ((ViewDebit)systemDesktopPane.getSelectedFrame()).printDoc();
            }
            return;
        }
        if(frameTitle.startsWith("::. View Pending Debit Order -"))
        {
            int x=JOptionPane.showConfirmDialog(JOptionPane.getRootFrame()," Do you want to print voucher ? \n Click Yes to print receipt or No to print table.","Confirm Print Document !",JOptionPane.YES_NO_CANCEL_OPTION);
            if(x==JOptionPane.NO_OPTION){
            ((ViewPendingDebitOrder)systemDesktopPane.getSelectedFrame()).print();
                        }
            if(x==JOptionPane.YES_OPTION){
            ((ViewPendingDebitOrder)systemDesktopPane.getSelectedFrame()).printDoc();
            }
            return;
        }
        if(frameTitle.startsWith("::. View Pending Debit Order Summary"))
        {
            int x=JOptionPane.showConfirmDialog(JOptionPane.getRootFrame()," Do you want to print voucher ? \n Click Yes to print receipt or No to print table.","Confirm Print Document !",JOptionPane.YES_NO_CANCEL_OPTION);
            if(x==JOptionPane.NO_OPTION){
            ((ViewPendingDebitOrderSummary)systemDesktopPane.getSelectedFrame()).print();
            }
            if(x==JOptionPane.YES_OPTION){
            ((ViewPendingDebitOrderSummary)systemDesktopPane.getSelectedFrame()).printDoc();
            }
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Transaction Type"))
        {
            ((ViewTransactionType)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Vendor"))
        {
            ((ViewVendor)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. Profile Constant"))
        {
            ((ProfileConstant)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Profile Member"))
        {
            ((ViewProfileMembers)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Profile"))
        {
            ((ViewProfile)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(OpenMSApp.PrintComponent!=null)
        {
            new PrintDocument(OpenMSApp.PrintComponent);
            OpenMSApp.PrintComponent=null;
            return;
        }
        else
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Unable to Locate Document","PRINT ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
    }//GEN-LAST:event_jPrintMenuItemActionPerformed

    private void nVendorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nVendorMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame newVendor=new Vendor();
        systemDesktopPane.add(newVendor);
        systemDesktopPane.moveToFront(newVendor);

        try
        {
            newVendor.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding vendor ! "+e);
        }
    }//GEN-LAST:event_nVendorMenuItemActionPerformed

    private void vVendorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vVendorMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewVendor=new ViewVendor();
        systemDesktopPane.add(viewVendor);
        systemDesktopPane.moveToFront(viewVendor);

        try
        {
            viewVendor.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view vendor ! "+e);
        }
    }//GEN-LAST:event_vVendorMenuItemActionPerformed

    private void eVendorMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eVendorMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame editVendor=new EditVendor();
        systemDesktopPane.add(editVendor);
        systemDesktopPane.moveToFront(editVendor);

        try
        {
            editVendor.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding edit vendor ! "+e);
        }
    }//GEN-LAST:event_eVendorMenuItemActionPerformed

    private void resetAllControlsMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetAllControlsMenuActionPerformed
        // TODO add your handling code here:
        resetAllControls();
    }//GEN-LAST:event_resetAllControlsMenuActionPerformed
     void resetComboControls()
    {
        String frameTitle=systemDesktopPane.getSelectedFrame().getTitle();
        if(frameTitle.equalsIgnoreCase("::. New Bank"))
        {
            ((Bank)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. New Item"))
        {
            ((Item)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. New Employee"))
        {
            ((Employee)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. New Account"))
        {
            ((Account)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. New Customer"))
        {
            ((Customer)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. New Vendor"))
        {
            ((Vendor)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. New User"))
        {
            ((LoginRegistration)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.startsWith("::. View Account Report"))
        {
            ((ViewAccountReport)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.startsWith("::. View Credit Report"))
        {
            ((ViewCreditReport)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.startsWith("::. View Debit Report"))
        {
            ((ViewDebitReport)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.startsWith("::. New Credit Order"))
        {
            ((NewCreditOrder)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.startsWith("::. New Debit Order"))
        {
            ((NewDebitOrder)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. View Merchant"))
        {
            ((ViewMerchant)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
    }
    private void resetComboControlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetComboControlsActionPerformed
        // TODO add your handling code here:
        resetComboControls();
    }//GEN-LAST:event_resetComboControlsActionPerformed
     void resetAllControls()
    {
        
        String searchText=jGlobalTextField.getText().trim();
        String frameTitle=systemDesktopPane.getSelectedFrame().getTitle();
        if(frameTitle.equalsIgnoreCase("::. New Bank"))
        {
            ((Bank)systemDesktopPane.getSelectedFrame()).resetCountry();
            ((Bank)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Designation"))
        {
            ((Designation)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Department"))
        {
            ((Department)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Item Category"))
        {
            ((ItemCategory)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Item Location"))
        {
            ((nItemLocation)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Item"))
        {
            ((Item)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. Edit Item"))
        {
            ((EditItem)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. View Item"))
        {
            ((ViewItem)systemDesktopPane.getSelectedFrame()).refresh1(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. New Employee"))
        {
            ((Employee)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. Edit Employee"))
        {
            ((EditEmployee)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Account"))
        {
            ((Account)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Customer"))
        {
            ((Customer)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. Edit Customer"))
        {
            ((EditCustomer)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Vendor"))
        {
            ((Vendor)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. Edit Vendor"))
        {
            ((EditVendor)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New User"))
        {
            ((LoginRegistration)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Transaction Type"))
        {
            ((TransactionType)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. New Credit Order"))
        {
            ((NewCreditOrder)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. View Pending Credit Order"))
        {
            ((ViewPendingCreditOrder)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. Consent Credit Order"))
        {
            ((ConsentCreditOrder)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. Post Credit Order"))
        {
            ((PostCreditOrder)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. View Credits"))
        {
            ((ViewCredit)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. New Debit Order"))
        {
            ((NewDebitOrder)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. View Pending Debit Order"))
        {
            ((ViewPendingDebitOrder)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. Consent Debit Order"))
        {
            ((ConsentDebitOrder)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. Post Debit Order"))
        {
            ((PostDebitOrder)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. View Debits"))
        {
            ((ViewDebit)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. View Credit Report"))
        {
            ((ViewCreditReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. View Debit Report"))
        {
            ((ViewDebitReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Database Backup"))
        {
            ((BackupDatabase)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Item Unit"))
        {
            ((ItemUnit)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. View Account Report"))
        {
            ((ViewAccountReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. New Currency"))
        {
            ((Currency)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. View Merchant"))
        {
            ((ViewMerchant)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. New Stock Count"))
        {
            ((NewStockCount)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. View Stock Report"))
        {
            ((StockReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. View Cash Report"))
        {
            ((CashReport)systemDesktopPane.getSelectedFrame()).reset();
        }
    }
    private void resetAllContolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetAllContolsActionPerformed
        // TODO add your handling code here:
        resetAllControls();
    }//GEN-LAST:event_resetAllContolsActionPerformed

    private void nItemCategoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nItemCategoryMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame itemCategory=new ItemCategory();
        systemDesktopPane.add(itemCategory);
        systemDesktopPane.moveToFront(itemCategory);

        try
        {
            itemCategory.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding item category ! "+e);
        }
    }//GEN-LAST:event_nItemCategoryMenuItemActionPerformed

    private void vItemCategoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vItemCategoryMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewItemCategory=new ViewItemCategory();
        systemDesktopPane.add(viewItemCategory);
        systemDesktopPane.moveToFront(viewItemCategory);

        try
        {
            viewItemCategory.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view item category ! "+e);
        }
    }//GEN-LAST:event_vItemCategoryMenuItemActionPerformed

    private void vItemLocationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vItemLocationMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewItemLocation=new ViewItemLocation();
        systemDesktopPane.add(viewItemLocation);
        systemDesktopPane.moveToFront(viewItemLocation);

        try
        {
            viewItemLocation.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view item location ! "+e);
        }
    }//GEN-LAST:event_vItemLocationMenuItemActionPerformed

    private void nItemLocationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nItemLocationMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame nItemLocation=new nItemLocation();
        systemDesktopPane.add(nItemLocation);
        systemDesktopPane.moveToFront(nItemLocation);

        try
        {
            nItemLocation.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding item location ! "+e);
        }
    }//GEN-LAST:event_nItemLocationMenuItemActionPerformed

    private void nItemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nItemMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame nItem=new Item();
        systemDesktopPane.add(nItem);
        systemDesktopPane.moveToFront(nItem);

        try
        {
            nItem.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding item ! "+e);
        }
    }//GEN-LAST:event_nItemMenuItemActionPerformed

    private void eItemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eItemMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame editItem=new EditItem();
        systemDesktopPane.add(editItem);
        systemDesktopPane.moveToFront(editItem);

        try
        {
            editItem.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding edit item ! "+e);
        }
    }//GEN-LAST:event_eItemMenuItemActionPerformed

    private void vItemUnitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vItemUnitMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vItemUnit=new ViewItemUnit();
        systemDesktopPane.add(vItemUnit);
        systemDesktopPane.moveToFront(vItemUnit);

        try
        {
            vItemUnit.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Account ! "+e);
        }
    }//GEN-LAST:event_vItemUnitMenuItemActionPerformed

    private void nItemUnitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nItemUnitMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame itemUnit=new ItemUnit();
        systemDesktopPane.add(itemUnit);
        systemDesktopPane.moveToFront(itemUnit);

        try
        {
            itemUnit.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Item Unit ! "+e);
        }
    }//GEN-LAST:event_nItemUnitMenuItemActionPerformed

    private void jResetMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetMenuItemActionPerformed
        // TODO add your handling code here:
        resetAllControls();
    }//GEN-LAST:event_jResetMenuItemActionPerformed

    private void jResetComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetComboMenuItemActionPerformed
        // TODO add your handling code here:
        resetComboControls();
    }//GEN-LAST:event_jResetComboMenuItemActionPerformed

    private void jCloseWindowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCloseWindowMenuItemActionPerformed
        // TODO add your handling code here:
        systemDesktopPane.getSelectedFrame().dispose();
}//GEN-LAST:event_jCloseWindowMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void vcPendingOrderSummaryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vcPendingOrderSummaryMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vcPOSummary=new ViewPendingCreditOrderSummary();
        systemDesktopPane.add(vcPOSummary);
        systemDesktopPane.moveToFront(vcPOSummary);

        try {
            vcPOSummary.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding view pending credit order summary ! "+e);
        }
    }//GEN-LAST:event_vcPendingOrderSummaryMenuItemActionPerformed

    private void vdPendingOrderSummaryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vdPendingOrderSummaryMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vdPOSummary=new ViewPendingDebitOrderSummary();
        systemDesktopPane.add(vdPOSummary);
        systemDesktopPane.moveToFront(vdPOSummary);

        try {
            vdPOSummary.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding view pending debit order summary ! "+e);
        }
    }//GEN-LAST:event_vdPendingOrderSummaryMenuItemActionPerformed

    private void peCreditMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peCreditMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame peCreditOrder=new PostExpressCreditOrder();
        systemDesktopPane.add(peCreditOrder);
        systemDesktopPane.moveToFront(peCreditOrder);

        try {
            peCreditOrder.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding post express debit order! "+e);
        }
}//GEN-LAST:event_peCreditMenuItemActionPerformed

    private void peDebitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peDebitMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame peDebitOrder=new PostExpressDebitOrder();
        systemDesktopPane.add(peDebitOrder);
        systemDesktopPane.moveToFront(peDebitOrder);

        try {
            peDebitOrder.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding post express debit order ! "+e);
        }
}//GEN-LAST:event_peDebitMenuItemActionPerformed

    private void PostExpressMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PostExpressMenuActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_PostExpressMenuActionPerformed

     void search()
    {
        String searchText=jGlobalTextField.getText().trim();
        String frameTitle=systemDesktopPane.getSelectedFrame().getTitle();

        if(frameTitle.equalsIgnoreCase("::. View Customer"))
        {
            ((ViewCustomer)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Account"))
        {
            ((ViewAccount)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Backup"))
        {
            ((ViewBackup)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Bank"))
        {
            ((ViewBank)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Department"))
        {
            ((ViewDepartment)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Batch"))
        {
            ((ViewBatch)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Designation"))
        {
            ((ViewDesignation)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Employee"))
        {
            ((ViewEmployee)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Designation"))
        {
            ((ViewDesignation)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Detail"))
        {
            ((ViewItem)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Basic"))
        {
            ((ViewItemBasic)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Category"))
        {
            ((ViewItemCategory)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Item Location"))
        {
            ((ViewItemLocation)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Stock Unit"))
        {
            ((ViewItemUnit)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View System User"))
        {
            ((ViewLogin)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Merchant"))
        {
            ((ViewMerchant)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View System User Activity"))
        {
            ((ViewLoginRecord)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Transaction Type"))
        {
            ((ViewTransactionType)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Vendor"))
        {
            ((ViewVendor)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Currency"))
        {
            ((ViewCurrency)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
        if(frameTitle.equalsIgnoreCase("::. View Profile Member"))
        {
            ((ViewProfileMembers)systemDesktopPane.getSelectedFrame()).refreshTable(searchText);
        }
    }

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        // TODO add your handling code here:
        search();
}//GEN-LAST:event_jSearchButtonActionPerformed

    private void jSearchComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchComboMenuItemActionPerformed
        // TODO add your handling code here:
        search();
}//GEN-LAST:event_jSearchComboMenuItemActionPerformed

    private void jDeleteRowComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteRowComboMenuItemActionPerformed
        // TODO add your handling code here:
        String frameTitle=systemDesktopPane.getSelectedFrame().getTitle();

        if(frameTitle.startsWith("::. New Credit Order"))
        {
            ((NewCreditOrder)systemDesktopPane.getSelectedFrame()).deleteRow();
        }
        if(frameTitle.startsWith("::. New Debit Order"))
        {
            ((NewDebitOrder)systemDesktopPane.getSelectedFrame()).deleteRow();
        }
        if(frameTitle.startsWith("::. New Inventory Payable Debit Order"))
        {
            ((NewSolidLoanDOrder)systemDesktopPane.getSelectedFrame()).deleteRow();
        }
        if(frameTitle.startsWith("::. New Inventory Payable Credit Order"))
        {
            ((NewSolidLoanOrder)systemDesktopPane.getSelectedFrame()).deleteRow();
        }
        if(frameTitle.startsWith("::. New Transfer Order"))
        {
            ((NewTransferOrder)systemDesktopPane.getSelectedFrame()).deleteRow();
        }
    }//GEN-LAST:event_jDeleteRowComboMenuItemActionPerformed

    private void jAddRowComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAddRowComboMenuItemActionPerformed
        // TODO add your handling code here:
        String frameTitle=systemDesktopPane.getSelectedFrame().getTitle();

        if(frameTitle.startsWith("::. New Credit Order"))
        {
            ((NewCreditOrder)systemDesktopPane.getSelectedFrame()).addRow();
        }
        if(frameTitle.startsWith("::. New Debit Order"))
        {
            ((NewDebitOrder)systemDesktopPane.getSelectedFrame()).addRow();
        }
        if(frameTitle.startsWith("::. New Inventory Payable Debit Order"))
        {
            ((NewSolidLoanDOrder)systemDesktopPane.getSelectedFrame()).addRow();
        }
        if(frameTitle.startsWith("::. New Inventory Payable Credit Order"))
        {
            ((NewSolidLoanOrder)systemDesktopPane.getSelectedFrame()).addRow();
        }
        if(frameTitle.startsWith("::. New Transfer Order"))
        {
            ((NewTransferOrder)systemDesktopPane.getSelectedFrame()).addRow();
        }
    }//GEN-LAST:event_jAddRowComboMenuItemActionPerformed

private void jMakeOrderComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMakeOrderComboMenuItemActionPerformed
// TODO add your handling code here:
        String frameTitle=systemDesktopPane.getSelectedFrame().getTitle();

        if(frameTitle.startsWith("::. New Credit Order"))
        {
            ((NewCreditOrder)systemDesktopPane.getSelectedFrame()).performMakeOrder();
        }
        if(frameTitle.startsWith("::. New Debit Order"))
        {
            ((NewDebitOrder)systemDesktopPane.getSelectedFrame()).performMakeOrder();
        }
        if(frameTitle.startsWith("::. New Inventory Payable Debit Order"))
        {
            ((NewSolidLoanDOrder)systemDesktopPane.getSelectedFrame()).performMakeOrder();
        }
        if(frameTitle.startsWith("::. New Inventory Payable Credit Order"))
        {
            ((NewSolidLoanOrder)systemDesktopPane.getSelectedFrame()).performMakeOrder();
        }
        if(frameTitle.startsWith("::. New Transfer Order"))
        {
            ((NewTransferOrder)systemDesktopPane.getSelectedFrame()).performMakeOrder();
        }
        if(frameTitle.startsWith("::. New Inventory Receivable Debit Order"))
        {
            ((NewInventoryReceivableDebit)systemDesktopPane.getSelectedFrame()).performMakeOrder();
        }
        if(frameTitle.startsWith("::. New Inventory Receivable Credit Order"))
        {
            ((NewInventoryReceivableCredit)systemDesktopPane.getSelectedFrame()).performMakeOrder();
        }
}//GEN-LAST:event_jMakeOrderComboMenuItemActionPerformed

private void nCurrencyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nCurrencyMenuItemActionPerformed
// TODO add your handling code here:
        javax.swing.JInternalFrame currency=new Currency();
        systemDesktopPane.add(currency);
        systemDesktopPane.moveToFront(currency);
        try
        {
            currency.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new currency ! "+e);
        }
}//GEN-LAST:event_nCurrencyMenuItemActionPerformed

private void vCurrencyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vCurrencyMenuItemActionPerformed
// TODO add your handling code here:
        javax.swing.JInternalFrame vCurrency=new ViewCurrency();
        systemDesktopPane.add(vCurrency);
        systemDesktopPane.moveToFront(vCurrency);
        try
        {
            vCurrency.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view currency ! "+e);
        }
}//GEN-LAST:event_vCurrencyMenuItemActionPerformed

private void nGroupMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nGroupMenuItemActionPerformed

        javax.swing.JInternalFrame nGroup=new Group();
        systemDesktopPane.add(nGroup);
        systemDesktopPane.moveToFront(nGroup);
        try
        {
            nGroup.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Group ! "+e);
        }
}//GEN-LAST:event_nGroupMenuItemActionPerformed

private void vGroupMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vGroupMenuItemActionPerformed

        javax.swing.JInternalFrame vGroup=new ViewGroup();
        systemDesktopPane.add(vGroup);
        systemDesktopPane.moveToFront(vGroup);
        try
        {
            vGroup.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Group ! "+e);
        }
}//GEN-LAST:event_vGroupMenuItemActionPerformed

private void vaMerchantMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaMerchantMenuItem1ActionPerformed
// TODO add your handling code here:
        javax.swing.JInternalFrame vMerchant=new ViewMerchant();
        systemDesktopPane.add(vMerchant);
        systemDesktopPane.moveToFront(vMerchant);
        try
        {
            vMerchant.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view merchant ! "+e);
        }
}//GEN-LAST:event_vaMerchantMenuItem1ActionPerformed

private void vNoteMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vNoteMenuItemActionPerformed
// TODO add your handling code here:
        javax.swing.JInternalFrame vNote=new Note();
        systemDesktopPane.add(vNote);
        systemDesktopPane.moveToFront(vNote);
        try
        {
            vNote.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Note ! "+e);
        }
}//GEN-LAST:event_vNoteMenuItemActionPerformed

private void vTrialMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vTrialMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewTrial=new TrialDetails();
        systemDesktopPane.add(viewTrial);
        systemDesktopPane.moveToFront(viewTrial);

        try
        {
            viewTrial.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view Trial Detials ! "+e);
        }
}//GEN-LAST:event_vTrialMenuItemActionPerformed

private void jBulkSMSComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBulkSMSComboMenuItemActionPerformed

        javax.swing.JInternalFrame nBulkSMS=new NewBulkSMS();
        systemDesktopPane.add(nBulkSMS);
        systemDesktopPane.moveToFront(nBulkSMS);

        try
        {
            nBulkSMS.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new bulk SMS ! "+e);
        }
}//GEN-LAST:event_jBulkSMSComboMenuItemActionPerformed

private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed

        String access=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnLogout"+Connect.procInitStart+"'"+OpenMSApp.LoginCode+"'"+Connect.procInitEnd, "cState");

        OpenMSApp.resetUserParameter();
        Connect.closeMainConnection();
        System.exit(0);
}//GEN-LAST:event_exitMenuItemActionPerformed

private void vProfileMemberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vProfileMemberMenuItemActionPerformed

        javax.swing.JInternalFrame vProfileMembers=new ViewProfileMembers();
        systemDesktopPane.add(vProfileMembers);
        systemDesktopPane.moveToFront(vProfileMembers);

        try
        {
            vProfileMembers.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view Profile Member ! "+e);
        }
}//GEN-LAST:event_vProfileMemberMenuItemActionPerformed

private void jSwitchComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSwitchComboMenuItemActionPerformed
if(OpenMSApp.d.isVisible())OpenMSApp.d.transferFocus();
}//GEN-LAST:event_jSwitchComboMenuItemActionPerformed

private void jSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSwitchButtonActionPerformed
    if(OpenMSApp.d.isVisible())
    {
        OpenMSApp.d.transferFocus();
        //MilliscriptDMSApp.d.setState(java.awt.Frame.ICONIFIED);
    }
}//GEN-LAST:event_jSwitchButtonActionPerformed

private void jChatComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jChatComboMenuItemActionPerformed

        javax.swing.JInternalFrame nChat=new Chat();
        systemDesktopPane.add(nChat);
        systemDesktopPane.moveToFront(nChat);

        try
        {
            nChat.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new chat ! "+e);
        }
}//GEN-LAST:event_jChatComboMenuItemActionPerformed

private void vItemBasicMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vItemBasicMenuItemActionPerformed

        javax.swing.JInternalFrame vItemBasic=new ViewItemBasic();
        systemDesktopPane.add(vItemBasic);
        systemDesktopPane.moveToFront(vItemBasic);

        try
        {
            vItemBasic.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view item basic ! "+e);
        }
}//GEN-LAST:event_vItemBasicMenuItemActionPerformed

private void vItemDetailMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vItemDetailMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewItem=new ViewItem();
        systemDesktopPane.add(viewItem);
        systemDesktopPane.moveToFront(viewItem);

        try
        {
            viewItem.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view item ! "+e);
        }
}//GEN-LAST:event_vItemDetailMenuItemActionPerformed

private void nInvoiceMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nInvoiceMenuItemActionPerformed

        javax.swing.JInternalFrame nInvoice=new NewInvoice();
        systemDesktopPane.add(nInvoice);
        systemDesktopPane.moveToFront(nInvoice);

        try
        {
            nInvoice.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Invoice ! "+e);
        }
}//GEN-LAST:event_nInvoiceMenuItemActionPerformed

private void vcInvoiceOrderMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vcInvoiceOrderMenuItemActionPerformed

        javax.swing.JInternalFrame vInvoice=new ViewInvoice();
        systemDesktopPane.add(vInvoice);
        systemDesktopPane.moveToFront(vInvoice);

        try
        {
            vInvoice.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Invoice ! "+e);
        }
}//GEN-LAST:event_vcInvoiceOrderMenuItemActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AccountMenu1;
    private javax.swing.JMenu CreditMenu;
    private javax.swing.JMenu DebitMenu;
    private javax.swing.JMenu ItemMenu;
    private javax.swing.JMenuItem LogoutMenuItem;
    private javax.swing.JToolBar MilliscriptMoneySpinnerToolBar;
    private javax.swing.JMenu NewMenu;
    private javax.swing.JMenu PostExpressMenu;
    private javax.swing.JMenu PostMenu;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem eCustomerMenuItem;
    private javax.swing.JMenuItem eItemMenuItem;
    private javax.swing.JMenuItem eVendorMenuItem;
    private javax.swing.JMenuItem jAddRowComboMenuItem;
    private javax.swing.JMenuItem jBulkSMSComboMenuItem;
    private javax.swing.JMenuItem jChatComboMenuItem;
    private javax.swing.JMenuItem jCloseWindowMenuItem;
    private javax.swing.JMenuItem jDeleteRowComboMenuItem;
    private javax.swing.JTextField jGlobalTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMakeOrderComboMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jPrintMenuItem;
    private javax.swing.JCheckBoxMenuItem jPrintSetupCheckBoxMenuItem;
    private javax.swing.JMenuItem jResetComboMenuItem;
    private javax.swing.JMenuItem jResetMenuItem;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JMenuItem jSearchComboMenuItem;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jSwitchButton;
    private javax.swing.JMenuItem jSwitchComboMenuItem;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JMenu jWindowMenu;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem nCreditMenuItem;
    private javax.swing.JMenuItem nCurrencyMenuItem;
    private javax.swing.JMenuItem nCustomerMenuItem;
    private javax.swing.JMenuItem nDebitMenuItem;
    private javax.swing.JMenuItem nGroupMenuItem;
    private javax.swing.JMenuItem nInvoiceMenuItem;
    private javax.swing.JMenuItem nItemCategoryMenuItem;
    private javax.swing.JMenuItem nItemLocationMenuItem;
    private javax.swing.JMenuItem nItemMenuItem;
    private javax.swing.JMenuItem nItemUnitMenuItem;
    private javax.swing.JMenuItem nTransTypeMenuItem;
    private javax.swing.JMenuItem nVendorMenuItem;
    private javax.swing.JButton newCreditButton;
    private javax.swing.JButton newDebitButton;
    private javax.swing.JMenuItem pCreditMenuItem;
    private javax.swing.JMenuItem pDebitMenuItem;
    private javax.swing.JMenuItem peCreditMenuItem;
    private javax.swing.JMenuItem peDebitMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton resetAllContols;
    private javax.swing.JButton resetComboControls;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JDesktopPane systemDesktopPane;
    private javax.swing.JMenuItem vBank;
    private javax.swing.JMenuItem vCurrencyMenuItem;
    private javax.swing.JMenuItem vCustomerMenuItem;
    private javax.swing.JMenuItem vDepartmentMenuItem;
    private javax.swing.JMenuItem vDesignationMenuItem;
    private javax.swing.JMenuItem vEmployeeMenuItem;
    private javax.swing.JMenuItem vGroupMenuItem;
    private javax.swing.JMenuItem vItemBasicMenuItem;
    private javax.swing.JMenuItem vItemCategoryMenuItem;
    private javax.swing.JMenuItem vItemDetailMenuItem;
    private javax.swing.JMenuItem vItemLocationMenuItem;
    private javax.swing.JMenuItem vItemUnitMenuItem;
    private javax.swing.JMenuItem vNoteMenuItem;
    private javax.swing.JMenuItem vProfileMemberMenuItem;
    private javax.swing.JMenuItem vTransTypeMenuItem;
    private javax.swing.JMenuItem vTrialMenuItem;
    private javax.swing.JMenuItem vVendorMenuItem;
    private javax.swing.JMenuItem vaMerchantMenuItem1;
    private javax.swing.JMenuItem vcInvoiceOrderMenuItem;
    private javax.swing.JMenuItem vcOrderSummaryMenuItem;
    private javax.swing.JMenuItem vcPendingOrderMenuItem;
    private javax.swing.JMenuItem vcPendingOrderSummaryMenuItem;
    private javax.swing.JMenuItem vcPostedMenuItem;
    private javax.swing.JMenuItem vdOrderSummaryMenuItem;
    private javax.swing.JMenuItem vdPendingOrderMenuItem;
    private javax.swing.JMenuItem vdPendingOrderSummaryMenuItem;
    private javax.swing.JMenuItem vdPostedOrderMenuItem;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
