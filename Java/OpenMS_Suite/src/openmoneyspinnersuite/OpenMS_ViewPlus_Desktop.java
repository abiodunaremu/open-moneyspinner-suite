
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
 * OpenMS_ViewPlus_Desktop.java
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
final public class OpenMS_ViewPlus_Desktop extends FrameView {

    public JDialog printCredit;
    public JDialog printDebit;
    Systems systems=new Systems();
    String printStatus=OpenMSApp.PrintWho+" | "+OpenMSApp.PrintCode;
    Thread TRtread;
     OpenMS_ViewPlus_Desktop(SingleFrameApplication app) {
        super(app);

        if(OpenMSApp.appType.equals("AX, Server, Trial"))
        {
            this.getFrame().setTitle("::. Administrator View ["+systems.toTitleCase(OpenMSApp.EmployeeName)+" : "+OpenMSApp.CompanyName+"] - Milliscript MoneySpinner Suite "+OpenMSApp.appArchType+" 1.5.1 ["+OpenMSApp.trialDay+" Days Left]");
            
        }
        else if(OpenMSApp.appType.equals("Activated"))
        {
            this.getFrame().setTitle("::. Administrator View ["+systems.toTitleCase(OpenMSApp.EmployeeName)+" : "+OpenMSApp.CompanyName+"] - Milliscript MoneySpinner Suite "+OpenMSApp.appArchType+" 1.5.1 ");
        }
        org.jdesktop.application.ResourceMap resourceMap0 = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMSApp.class).getContext().getResourceMap(OpenMS_ViewPlus_Desktop.class);
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
        jResetMenuItem = new javax.swing.JMenuItem();
        jResetComboMenuItem = new javax.swing.JMenuItem();
        jSearchComboMenuItem = new javax.swing.JMenuItem();
        jBackupMenuItem = new javax.swing.JMenuItem();
        jSwitchComboMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPrintSetupCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        LogoutMenuItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        AccountMenu = new javax.swing.JMenu();
        vaHistoryMenuItem = new javax.swing.JMenuItem();
        vaDetailsMenuItem = new javax.swing.JMenuItem();
        vBank = new javax.swing.JMenuItem();
        CreditMenu = new javax.swing.JMenu();
        vcPendingOrderMenuItem = new javax.swing.JMenuItem();
        vcPostedMenuItem = new javax.swing.JMenuItem();
        vcOrderSummaryMenuItem = new javax.swing.JMenuItem();
        DebitMenu = new javax.swing.JMenu();
        vdPendingOrderMenuItem = new javax.swing.JMenuItem();
        vdPostedOrderMenuItem = new javax.swing.JMenuItem();
        vdOrderSummaryMenuItem = new javax.swing.JMenuItem();
        vCustomerMenuItem = new javax.swing.JMenuItem();
        vDepartmentMenuItem = new javax.swing.JMenuItem();
        vDesignationMenuItem = new javax.swing.JMenuItem();
        vEmployeeMenuItem = new javax.swing.JMenuItem();
        LoginMenu = new javax.swing.JMenu();
        loginReportMenuItem = new javax.swing.JMenuItem();
        loginUserMenuItem = new javax.swing.JMenuItem();
        vTransTypeMenuItem = new javax.swing.JMenuItem();
        vVendorMenuItem = new javax.swing.JMenuItem();
        vItemCategoryMenuItem = new javax.swing.JMenuItem();
        vItemLocationMenuItem = new javax.swing.JMenuItem();
        vItemMenuItem = new javax.swing.JMenuItem();
        vItemUnitMenuItem = new javax.swing.JMenuItem();
        vBackupMenuItem = new javax.swing.JMenuItem();
        AccountMenu1 = new javax.swing.JMenu();
        vaMerchantMenuItem1 = new javax.swing.JMenuItem();
        vNoteMenuItem = new javax.swing.JMenuItem();
        vProfileMemberMenuItem = new javax.swing.JMenuItem();
        jWindowMenu = new javax.swing.JMenu();
        jCloseWindowMenuItem = new javax.swing.JMenuItem();
        jWindowMenu1 = new javax.swing.JMenu();
        jAuditInventoryMenuItem = new javax.swing.JMenuItem();
        jInventoryHistoryMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        systemDesktopPane = new javax.swing.JDesktopPane();
        jToolBar2 = new javax.swing.JToolBar();
        jResetComboButton = new javax.swing.JButton();
        jResetButton = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jToolBar3 = new javax.swing.JToolBar();
        jSearchButton = new javax.swing.JButton();
        jSwitchButton = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jGlobalTextField = new javax.swing.JTextField();

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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(OpenMS_ViewPlus_Desktop.class);
        resetAllControlsMenu.setText(resourceMap.getString("resetAllControlsMenu.text")); // NOI18N
        resetAllControlsMenu.setToolTipText(resourceMap.getString("resetAllControlsMenu.toolTipText")); // NOI18N
        resetAllControlsMenu.setName("resetAllControlsMenu"); // NOI18N
        resetAllControlsMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetAllControlsMenuActionPerformed(evt);
            }
        });

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
        jSearchComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSearchComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jSearchComboMenuItem);

        jBackupMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        jBackupMenuItem.setIcon(resourceMap.getIcon("jBackupMenuItem.icon")); // NOI18N
        jBackupMenuItem.setText(resourceMap.getString("jBackupMenuItem.text")); // NOI18N
        jBackupMenuItem.setEnabled(false);
        jBackupMenuItem.setName("jBackupMenuItem"); // NOI18N
        jBackupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBackupMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jBackupMenuItem);

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

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon")); // NOI18N
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jMenuItem2);

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

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getActionMap(OpenMS_ViewPlus_Desktop.class, this);
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

        AccountMenu.setText(resourceMap.getString("AccountMenu.text")); // NOI18N
        AccountMenu.setName("AccountMenu"); // NOI18N

        vaHistoryMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK));
        vaHistoryMenuItem.setText(resourceMap.getString("vaHistoryMenuItem.text")); // NOI18N
        vaHistoryMenuItem.setName("vaHistoryMenuItem"); // NOI18N
        vaHistoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaHistoryMenuItemActionPerformed(evt);
            }
        });
        AccountMenu.add(vaHistoryMenuItem);

        vaDetailsMenuItem.setText(resourceMap.getString("vaDetailsMenuItem.text")); // NOI18N
        vaDetailsMenuItem.setName("vaDetailsMenuItem"); // NOI18N
        vaDetailsMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaDetailsMenuItemActionPerformed(evt);
            }
        });
        AccountMenu.add(vaDetailsMenuItem);

        ViewMenu.add(AccountMenu);

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

        LoginMenu.setText(resourceMap.getString("LoginMenu.text")); // NOI18N
        LoginMenu.setName("LoginMenu"); // NOI18N

        loginReportMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        loginReportMenuItem.setText(resourceMap.getString("loginReportMenuItem.text")); // NOI18N
        loginReportMenuItem.setName("loginReportMenuItem"); // NOI18N
        loginReportMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginReportMenuItemActionPerformed(evt);
            }
        });
        LoginMenu.add(loginReportMenuItem);

        loginUserMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        loginUserMenuItem.setText(resourceMap.getString("loginUserMenuItem.text")); // NOI18N
        loginUserMenuItem.setEnabled(false);
        loginUserMenuItem.setName("loginUserMenuItem"); // NOI18N
        loginUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUserMenuItemActionPerformed(evt);
            }
        });
        LoginMenu.add(loginUserMenuItem);

        ViewMenu.add(LoginMenu);

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

        vItemMenuItem.setText(resourceMap.getString("vItemMenuItem.text")); // NOI18N
        vItemMenuItem.setName("vItemMenuItem"); // NOI18N
        vItemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vItemMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vItemMenuItem);

        vItemUnitMenuItem.setText(resourceMap.getString("vItemUnitMenuItem.text")); // NOI18N
        vItemUnitMenuItem.setName("vItemUnitMenuItem"); // NOI18N
        vItemUnitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vItemUnitMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vItemUnitMenuItem);

        vBackupMenuItem.setText(resourceMap.getString("vBackupMenuItem.text")); // NOI18N
        vBackupMenuItem.setEnabled(false);
        vBackupMenuItem.setName("vBackupMenuItem"); // NOI18N
        vBackupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vBackupMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vBackupMenuItem);

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

        vProfileMemberMenuItem.setText(resourceMap.getString("vProfileMemberMenuItem.text")); // NOI18N
        vProfileMemberMenuItem.setName("vProfileMemberMenuItem"); // NOI18N
        vProfileMemberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vProfileMemberMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vProfileMemberMenuItem);

        menuBar.add(ViewMenu);

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

        jWindowMenu1.setText(resourceMap.getString("jWindowMenu1.text")); // NOI18N
        jWindowMenu1.setName("jWindowMenu1"); // NOI18N

        jAuditInventoryMenuItem.setText(resourceMap.getString("jAuditInventoryMenuItem.text")); // NOI18N
        jAuditInventoryMenuItem.setName("jAuditInventoryMenuItem"); // NOI18N
        jAuditInventoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jAuditInventoryMenuItemActionPerformed(evt);
            }
        });
        jWindowMenu1.add(jAuditInventoryMenuItem);

        jInventoryHistoryMenuItem.setText(resourceMap.getString("jInventoryHistoryMenuItem.text")); // NOI18N
        jInventoryHistoryMenuItem.setName("jInventoryHistoryMenuItem"); // NOI18N
        jInventoryHistoryMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jInventoryHistoryMenuItemActionPerformed(evt);
            }
        });
        jWindowMenu1.add(jInventoryHistoryMenuItem);

        menuBar.add(jWindowMenu1);

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

        systemDesktopPane.setBackground(resourceMap.getColor("systemDesktopPane.background")); // NOI18N

        jToolBar2.setRollover(true);
        jToolBar2.setEnabled(false);
        jToolBar2.setMinimumSize(new java.awt.Dimension(352, 40));
        jToolBar2.setName("jToolBar2"); // NOI18N
        jToolBar2.setPreferredSize(new java.awt.Dimension(352, 40));

        jResetComboButton.setIcon(resourceMap.getIcon("jResetComboButton.icon")); // NOI18N
        jResetComboButton.setToolTipText(resourceMap.getString("jResetComboButton.toolTipText")); // NOI18N
        jResetComboButton.setFocusable(false);
        jResetComboButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jResetComboButton.setName("jResetComboButton"); // NOI18N
        jResetComboButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jResetComboButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetComboButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(jResetComboButton);

        jResetButton.setIcon(resourceMap.getIcon("jResetButton.icon")); // NOI18N
        jResetButton.setToolTipText(resourceMap.getString("jResetButton.toolTipText")); // NOI18N
        jResetButton.setFocusable(false);
        jResetButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jResetButton.setName("jResetButton"); // NOI18N
        jResetButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jResetButtonActionPerformed(evt);
            }
        });
        jToolBar2.add(jResetButton);

        jSplitPane1.setBorder(null);
        jSplitPane1.setDividerLocation(180);
        jSplitPane1.setDividerSize(0);
        jSplitPane1.setAlignmentX(0.5F);
        jSplitPane1.setAlignmentY(0.5F);
        jSplitPane1.setAutoscrolls(true);
        jSplitPane1.setEnabled(false);
        jSplitPane1.setMinimumSize(new java.awt.Dimension(261, 40));
        jSplitPane1.setName("jSplitPane1"); // NOI18N
        jSplitPane1.setPreferredSize(new java.awt.Dimension(261, 40));

        jToolBar3.setRollover(true);
        jToolBar3.setEnabled(false);
        jToolBar3.setName("jToolBar3"); // NOI18N

        jSearchButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/openmoneyspinnersuite/resources/search_32.png"))); // NOI18N
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
        jToolBar3.add(jSearchButton);

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
        jToolBar3.add(jSwitchButton);

        jSplitPane1.setRightComponent(jToolBar3);

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jGlobalTextField.setAlignmentX(0.0F);
        jGlobalTextField.setMaximumSize(new java.awt.Dimension(150, 20));
        jGlobalTextField.setMinimumSize(new java.awt.Dimension(150, 20));
        jGlobalTextField.setName("jGlobalTextField"); // NOI18N
        jToolBar1.add(jGlobalTextField);

        jSplitPane1.setLeftComponent(jToolBar1);

        jToolBar2.add(jSplitPane1);

        jToolBar2.setBounds(0, 0, 1240, 40);
        systemDesktopPane.add(jToolBar2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        setComponent(systemDesktopPane);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
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
    }//GEN-LAST:event_LogoutMenuItemActionPerformed

    private javax.swing.JDesktopPane getDesktopPane()
    {
        return systemDesktopPane;
    }
    private void loginReportMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginReportMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewLoginRecord=new ViewLoginRecord();
        systemDesktopPane.add(viewLoginRecord);
        systemDesktopPane.moveToFront(viewLoginRecord);

        try
        {
            viewLoginRecord.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view Login Record ! "+e);
        }

    }//GEN-LAST:event_loginReportMenuItemActionPerformed

    private void loginUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginUserMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewLogin=new ViewLogin();
        systemDesktopPane.add(viewLogin);
        systemDesktopPane.moveToFront(viewLogin);

        try
        {
            viewLogin.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view Login ! "+e);
        }
    }//GEN-LAST:event_loginUserMenuItemActionPerformed

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

    private void vaHistoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaHistoryMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame vAccountReport=new ViewAccountReport();
        systemDesktopPane.add(vAccountReport);
        systemDesktopPane.moveToFront(vAccountReport);
        try
        {
            vAccountReport.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view account report ! "+e);
        }
    }//GEN-LAST:event_vaHistoryMenuItemActionPerformed

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
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
        if(frameTitle.startsWith("::. View Credits - Order By User"))
        {
            ((ViewCredit)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.startsWith("::. View Credit Report"))
        {
            ((ViewCreditReport)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.startsWith("::. View Debits"))
        {
            ((ViewDebit)systemDesktopPane.getSelectedFrame()).print();
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
        if(frameTitle.equalsIgnoreCase("::. View Item"))
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
        if(frameTitle.startsWith("::. View Pending Credit Order"))
        {
            ((ViewPendingCreditOrder)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.startsWith("::. View Pending Credit Order Summary"))
        {
            ((ViewPendingCreditOrderSummary)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.startsWith("::. View Pending Debit Order"))
        {
            ((ViewPendingCreditOrder)systemDesktopPane.getSelectedFrame()).print();
            return;
        }
        if(frameTitle.startsWith("::. View Pending Debit Order Summary"))
        {
            ((ViewPendingCreditOrderSummary)systemDesktopPane.getSelectedFrame()).print();
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
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
        if(frameTitle.startsWith("::. New Credit Order"))
        {
            ((NewCreditOrder)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.startsWith("::. New Debit Order"))
        {
            ((NewDebitOrder)systemDesktopPane.getSelectedFrame()).resetCombo();
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
        if(frameTitle.equalsIgnoreCase("::. View Merchant"))
        {
            ((ViewMerchant)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
    }    
     void resetAllControls()
    {
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
        if(frameTitle.startsWith("::. View Credit Report"))
        {
            ((ViewCreditReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.startsWith("::. View Debit Report"))
        {
            ((ViewDebitReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. View Merchant"))
        {
            ((ViewMerchant)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
    }
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

    private void vItemMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vItemMenuItemActionPerformed
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
    }//GEN-LAST:event_vItemMenuItemActionPerformed

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

    private void vaDetailsMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaDetailsMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewAccount=new ViewAccount();
        systemDesktopPane.add(viewAccount);
        systemDesktopPane.moveToFront(viewAccount);

        try
        {
            viewAccount.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view Account ! "+e);
        }
    }//GEN-LAST:event_vaDetailsMenuItemActionPerformed

    private void jBackupMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBackupMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame backupDatabase=new BackupDatabase();
        systemDesktopPane.add(backupDatabase);
        systemDesktopPane.moveToFront(backupDatabase);

        try
        {
            backupDatabase.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view Account ! "+e);
        }
    }//GEN-LAST:event_jBackupMenuItemActionPerformed

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

    private void vBackupMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vBackupMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewBackup=new ViewBackup();
        systemDesktopPane.add(viewBackup);
        systemDesktopPane.moveToFront(viewBackup);

        try {
            viewBackup.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding view Backup ! "+e);
        }
}//GEN-LAST:event_vBackupMenuItemActionPerformed

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void newCreditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCreditButtonActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame nCreditOrder=new NewCreditOrder();
        systemDesktopPane.add(nCreditOrder);
        systemDesktopPane.moveToFront(nCreditOrder);

        try {
            nCreditOrder.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding new credit order ! "+e);
        }
}//GEN-LAST:event_newCreditButtonActionPerformed

    private void newDebitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newDebitButtonActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame nDebitOrder=new NewDebitOrder();
        systemDesktopPane.add(nDebitOrder);
        systemDesktopPane.moveToFront(nDebitOrder);
        try {
            nDebitOrder.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding new debit order ! "+e);
        }
}//GEN-LAST:event_newDebitButtonActionPerformed

    private void resetComboControlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetComboControlsActionPerformed
        // TODO add your handling code here:
        resetComboControls();
}//GEN-LAST:event_resetComboControlsActionPerformed

    private void resetAllContolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetAllContolsActionPerformed
        // TODO add your handling code here:
        resetAllControls();
}//GEN-LAST:event_resetAllContolsActionPerformed

    private void jViewAccountDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jViewAccountDetailsButtonActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewAccount=new ViewAccount();
        systemDesktopPane.add(viewAccount);
        systemDesktopPane.moveToFront(viewAccount);

        try {
            viewAccount.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding view Account ! "+e);
        }
}//GEN-LAST:event_jViewAccountDetailsButtonActionPerformed

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
    }
    private void jResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetButtonActionPerformed
        // TODO add your handling code here:
        search();
}//GEN-LAST:event_jResetButtonActionPerformed

    private void jSearchComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchComboMenuItemActionPerformed
        // TODO add your handling code here:
        search();
}//GEN-LAST:event_jSearchComboMenuItemActionPerformed

    private void jSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSearchButtonActionPerformed
        // TODO add your handling code here:
        search();
}//GEN-LAST:event_jSearchButtonActionPerformed

    private void jResetComboButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jResetComboButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jResetComboButtonActionPerformed

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

private void jAuditInventoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jAuditInventoryMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame auditInventory=new AuditInventory();
        systemDesktopPane.add(auditInventory);
        systemDesktopPane.moveToFront(auditInventory);

        try {
            auditInventory.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding audit inventory ! "+e);
        }
}//GEN-LAST:event_jAuditInventoryMenuItemActionPerformed

private void jInventoryHistoryMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jInventoryHistoryMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame sHistory=new InventoryHistory();
        systemDesktopPane.add(sHistory);
        systemDesktopPane.moveToFront(sHistory);
        try
        {
            sHistory.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding stock history ! "+e);
        }
}//GEN-LAST:event_jInventoryHistoryMenuItemActionPerformed

private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed

        String access=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnLogout"+Connect.procInitStart+"'"+OpenMSApp.LoginCode+"'"+Connect.procInitEnd, "cState");

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
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AccountMenu;
    private javax.swing.JMenu AccountMenu1;
    private javax.swing.JMenu CreditMenu;
    private javax.swing.JMenu DebitMenu;
    private javax.swing.JMenu LoginMenu;
    private javax.swing.JMenuItem LogoutMenuItem;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem jAuditInventoryMenuItem;
    private javax.swing.JMenuItem jBackupMenuItem;
    private javax.swing.JMenuItem jCloseWindowMenuItem;
    private javax.swing.JTextField jGlobalTextField;
    private javax.swing.JMenuItem jInventoryHistoryMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JCheckBoxMenuItem jPrintSetupCheckBoxMenuItem;
    private javax.swing.JButton jResetButton;
    private javax.swing.JButton jResetComboButton;
    private javax.swing.JMenuItem jResetComboMenuItem;
    private javax.swing.JMenuItem jResetMenuItem;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JMenuItem jSearchComboMenuItem;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JButton jSwitchButton;
    private javax.swing.JMenuItem jSwitchComboMenuItem;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JMenu jWindowMenu;
    private javax.swing.JMenu jWindowMenu1;
    private javax.swing.JMenuItem loginReportMenuItem;
    private javax.swing.JMenuItem loginUserMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JDesktopPane systemDesktopPane;
    private javax.swing.JMenuItem vBackupMenuItem;
    private javax.swing.JMenuItem vBank;
    private javax.swing.JMenuItem vCustomerMenuItem;
    private javax.swing.JMenuItem vDepartmentMenuItem;
    private javax.swing.JMenuItem vDesignationMenuItem;
    private javax.swing.JMenuItem vEmployeeMenuItem;
    private javax.swing.JMenuItem vItemCategoryMenuItem;
    private javax.swing.JMenuItem vItemLocationMenuItem;
    private javax.swing.JMenuItem vItemMenuItem;
    private javax.swing.JMenuItem vItemUnitMenuItem;
    private javax.swing.JMenuItem vNoteMenuItem;
    private javax.swing.JMenuItem vProfileMemberMenuItem;
    private javax.swing.JMenuItem vTransTypeMenuItem;
    private javax.swing.JMenuItem vVendorMenuItem;
    private javax.swing.JMenuItem vaDetailsMenuItem;
    private javax.swing.JMenuItem vaHistoryMenuItem;
    private javax.swing.JMenuItem vaMerchantMenuItem1;
    private javax.swing.JMenuItem vcOrderSummaryMenuItem;
    private javax.swing.JMenuItem vcPendingOrderMenuItem;
    private javax.swing.JMenuItem vcPostedMenuItem;
    private javax.swing.JMenuItem vdOrderSummaryMenuItem;
    private javax.swing.JMenuItem vdPendingOrderMenuItem;
    private javax.swing.JMenuItem vdPostedOrderMenuItem;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
