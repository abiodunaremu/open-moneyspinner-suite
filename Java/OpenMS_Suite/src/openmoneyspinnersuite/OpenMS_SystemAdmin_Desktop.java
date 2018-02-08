
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
 * OpenMS_SystemAdmin_Desktop.java
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
final public class OpenMS_SystemAdmin_Desktop extends FrameView {

    public JDialog printCredit;
    public JDialog printDebit;
    Systems systems=new Systems();
    String printStatus=OpenMSApp.PrintWho+" | "+OpenMSApp.PrintCode;
    Thread TRtread;
      OpenMS_SystemAdmin_Desktop(SingleFrameApplication app) {
        super(app);

        if(OpenMSApp.appType.equals("AX, Server, Trial"))
        {
            this.getFrame().setTitle("::. System Administrator ["+systems.toTitleCase(OpenMSApp.EmployeeName)+" : "+OpenMSApp.CompanyName+"] - Milliscript MoneySpinner Suite "+OpenMSApp.appArchType+" 1.5.1 ["+OpenMSApp.trialDay+" Days Left]");
        }
        else if(OpenMSApp.appType.equals("Activated"))
        {
            this.getFrame().setTitle("::. System Administrator ["+systems.toTitleCase(OpenMSApp.EmployeeName)+" : "+OpenMSApp.CompanyName+"] - Milliscript MoneySpinner Suite "+OpenMSApp.appArchType+" 1.5.1 ");
        }
        org.jdesktop.application.ResourceMap resourceMap0 = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMSApp.class).getContext().getResourceMap(OpenMS_SystemAdmin_Desktop.class);
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
        nBankMenuItem = new javax.swing.JMenuItem();
        nDepartmentMenuItem = new javax.swing.JMenuItem();
        nDesignationMenuItem = new javax.swing.JMenuItem();
        nEmployeeMenuItem = new javax.swing.JMenuItem();
        nLoginMenuItem = new javax.swing.JMenuItem();
        nCompanyMenuItem = new javax.swing.JMenuItem();
        nGroupMenuItem = new javax.swing.JMenuItem();
        nProfileMemberMenuItem = new javax.swing.JMenuItem();
        jResetMenuItem = new javax.swing.JMenuItem();
        jResetComboMenuItem = new javax.swing.JMenuItem();
        jSearchComboMenuItem = new javax.swing.JMenuItem();
        jBackupMenuItem = new javax.swing.JMenuItem();
        jChatComboMenuItem = new javax.swing.JMenuItem();
        jDeleteRowComboMenuItem = new javax.swing.JMenuItem();
        jAddRowComboMenuItem = new javax.swing.JMenuItem();
        jBulkSMSComboMenuItem = new javax.swing.JMenuItem();
        jSwitchComboMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jPrintMenuItem = new javax.swing.JMenuItem();
        jPrintSetupCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        LogoutMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        vBank = new javax.swing.JMenuItem();
        vDepartmentMenuItem = new javax.swing.JMenuItem();
        vDesignationMenuItem = new javax.swing.JMenuItem();
        vEmployeeMenuItem = new javax.swing.JMenuItem();
        LoginMenu = new javax.swing.JMenu();
        loginReportMenuItem = new javax.swing.JMenuItem();
        loginUserMenuItem = new javax.swing.JMenuItem();
        vBackupMenuItem = new javax.swing.JMenuItem();
        vCompanyMenuItem = new javax.swing.JMenuItem();
        AccountMenu1 = new javax.swing.JMenu();
        vaMerchantMenuItem1 = new javax.swing.JMenuItem();
        vNoteMenuItem = new javax.swing.JMenuItem();
        vGroupMenuItem = new javax.swing.JMenuItem();
        vProfileMenuItem = new javax.swing.JMenuItem();
        vProfileMemberMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        eEmployeeMenuItem = new javax.swing.JMenuItem();
        eCompanyMenuItem = new javax.swing.JMenuItem();
        eSystemUserMenuItem = new javax.swing.JMenuItem();
        eProfileMemberMenuItem = new javax.swing.JMenuItem();
        jWindowMenu1 = new javax.swing.JMenu();
        nProfileMenuItem = new javax.swing.JMenuItem();
        nMerchantMenuItem = new javax.swing.JMenuItem();
        nProfileConstantMenuItem = new javax.swing.JMenuItem();
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
        jBackupDatabaseButton = new javax.swing.JButton();
        NewEmployeeButton = new javax.swing.JButton();
        jNewLoginButton = new javax.swing.JButton();
        resetComboControls = new javax.swing.JButton();
        resetAllContols = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jToolBar2 = new javax.swing.JToolBar();
        jSearchButton = new javax.swing.JButton();
        jSwitchButton = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jGlobalTextField = new javax.swing.JTextField();
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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(OpenMS_SystemAdmin_Desktop.class);
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

        nBankMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nBankMenuItem.setText(resourceMap.getString("nBankMenuItem.text")); // NOI18N
        nBankMenuItem.setName("nBankMenuItem"); // NOI18N
        nBankMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nBankMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nBankMenuItem);

        nDepartmentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        nDepartmentMenuItem.setText(resourceMap.getString("nDepartmentMenuItem.text")); // NOI18N
        nDepartmentMenuItem.setName("nDepartmentMenuItem"); // NOI18N
        nDepartmentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nDepartmentMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nDepartmentMenuItem);

        nDesignationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nDesignationMenuItem.setText(resourceMap.getString("nDesignationMenuItem.text")); // NOI18N
        nDesignationMenuItem.setName("nDesignationMenuItem"); // NOI18N
        nDesignationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nDesignationMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nDesignationMenuItem);

        nEmployeeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nEmployeeMenuItem.setText(resourceMap.getString("nEmployeeMenuItem.text")); // NOI18N
        nEmployeeMenuItem.setName("nEmployeeMenuItem"); // NOI18N
        nEmployeeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nEmployeeMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nEmployeeMenuItem);

        nLoginMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nLoginMenuItem.setText(resourceMap.getString("nLoginMenuItem.text")); // NOI18N
        nLoginMenuItem.setName("nLoginMenuItem"); // NOI18N
        nLoginMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nLoginMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nLoginMenuItem);

        nCompanyMenuItem.setText(resourceMap.getString("nCompanyMenuItem.text")); // NOI18N
        nCompanyMenuItem.setName("nCompanyMenuItem"); // NOI18N
        nCompanyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCompanyMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nCompanyMenuItem);

        nGroupMenuItem.setText(resourceMap.getString("nGroupMenuItem.text")); // NOI18N
        nGroupMenuItem.setName("nGroupMenuItem"); // NOI18N
        nGroupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nGroupMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nGroupMenuItem);

        nProfileMemberMenuItem.setText(resourceMap.getString("nProfileMemberMenuItem.text")); // NOI18N
        nProfileMemberMenuItem.setName("nProfileMemberMenuItem"); // NOI18N
        nProfileMemberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nProfileMemberMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nProfileMemberMenuItem);

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

        jChatComboMenuItem.setText(resourceMap.getString("jChatComboMenuItem.text")); // NOI18N
        jChatComboMenuItem.setToolTipText(resourceMap.getString("jChatComboMenuItem.toolTipText")); // NOI18N
        jChatComboMenuItem.setName("jChatComboMenuItem"); // NOI18N
        jChatComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jChatComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jChatComboMenuItem);

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

        jBulkSMSComboMenuItem.setText(resourceMap.getString("jBulkSMSComboMenuItem.text")); // NOI18N
        jBulkSMSComboMenuItem.setToolTipText(resourceMap.getString("jBulkSMSComboMenuItem.toolTipText")); // NOI18N
        jBulkSMSComboMenuItem.setName("jBulkSMSComboMenuItem"); // NOI18N
        jBulkSMSComboMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBulkSMSComboMenuItemActionPerformed(evt);
            }
        });
        resetAllControlsMenu.add(jBulkSMSComboMenuItem);

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

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getActionMap(OpenMS_SystemAdmin_Desktop.class, this);
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
        loginUserMenuItem.setName("loginUserMenuItem"); // NOI18N
        loginUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginUserMenuItemActionPerformed(evt);
            }
        });
        LoginMenu.add(loginUserMenuItem);

        ViewMenu.add(LoginMenu);

        vBackupMenuItem.setText(resourceMap.getString("vBackupMenuItem.text")); // NOI18N
        vBackupMenuItem.setName("vBackupMenuItem"); // NOI18N
        vBackupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vBackupMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vBackupMenuItem);

        vCompanyMenuItem.setText(resourceMap.getString("vCompanyMenuItem.text")); // NOI18N
        vCompanyMenuItem.setName("vCompanyMenuItem"); // NOI18N
        vCompanyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vCompanyMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vCompanyMenuItem);

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

        vProfileMenuItem.setText(resourceMap.getString("vProfileMenuItem.text")); // NOI18N
        vProfileMenuItem.setName("vProfileMenuItem"); // NOI18N
        vProfileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vProfileMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vProfileMenuItem);

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

        eEmployeeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        eEmployeeMenuItem.setText(resourceMap.getString("eEmployeeMenuItem.text")); // NOI18N
        eEmployeeMenuItem.setName("eEmployeeMenuItem"); // NOI18N
        eEmployeeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eEmployeeMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eEmployeeMenuItem);

        eCompanyMenuItem.setText(resourceMap.getString("eCompanyMenuItem.text")); // NOI18N
        eCompanyMenuItem.setName("eCompanyMenuItem"); // NOI18N
        eCompanyMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eCompanyMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eCompanyMenuItem);

        eSystemUserMenuItem.setText(resourceMap.getString("eSystemUserMenuItem.text")); // NOI18N
        eSystemUserMenuItem.setName("eSystemUserMenuItem"); // NOI18N
        eSystemUserMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eSystemUserMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eSystemUserMenuItem);

        eProfileMemberMenuItem.setText(resourceMap.getString("eProfileMemberMenuItem.text")); // NOI18N
        eProfileMemberMenuItem.setName("eProfileMemberMenuItem"); // NOI18N
        eProfileMemberMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eProfileMemberMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eProfileMemberMenuItem);

        menuBar.add(jMenu1);

        jWindowMenu1.setText(resourceMap.getString("jWindowMenu1.text")); // NOI18N
        jWindowMenu1.setName("jWindowMenu1"); // NOI18N

        nProfileMenuItem.setText(resourceMap.getString("nProfileMenuItem.text")); // NOI18N
        nProfileMenuItem.setName("nProfileMenuItem"); // NOI18N
        nProfileMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nProfileMenuItemActionPerformed(evt);
            }
        });
        jWindowMenu1.add(nProfileMenuItem);

        nMerchantMenuItem.setText(resourceMap.getString("nMerchantMenuItem.text")); // NOI18N
        nMerchantMenuItem.setName("nMerchantMenuItem"); // NOI18N
        nMerchantMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nMerchantMenuItemActionPerformed(evt);
            }
        });
        jWindowMenu1.add(nMerchantMenuItem);

        nProfileConstantMenuItem.setText(resourceMap.getString("nProfileConstantMenuItem.text")); // NOI18N
        nProfileConstantMenuItem.setName("nProfileConstantMenuItem"); // NOI18N
        nProfileConstantMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nProfileConstantMenuItemActionPerformed(evt);
            }
        });
        jWindowMenu1.add(nProfileConstantMenuItem);

        menuBar.add(jWindowMenu1);

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
        MilliscriptMoneySpinnerToolBar.setName("MilliscriptMoneySpinnerToolBar"); // NOI18N
        MilliscriptMoneySpinnerToolBar.setPreferredSize(new java.awt.Dimension(469, 38));

        jBackupDatabaseButton.setIcon(resourceMap.getIcon("resetTextControl.icon")); // NOI18N
        jBackupDatabaseButton.setToolTipText(resourceMap.getString("resetTextControl.toolTipText")); // NOI18N
        jBackupDatabaseButton.setEnabled(false);
        jBackupDatabaseButton.setFocusable(false);
        jBackupDatabaseButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jBackupDatabaseButton.setName("resetTextControl"); // NOI18N
        jBackupDatabaseButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jBackupDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBackupDatabaseButtonActionPerformed(evt);
            }
        });
        MilliscriptMoneySpinnerToolBar.add(jBackupDatabaseButton);

        NewEmployeeButton.setIcon(resourceMap.getIcon("NewEmployeeButton.icon")); // NOI18N
        NewEmployeeButton.setToolTipText(resourceMap.getString("NewEmployeeButton.toolTipText")); // NOI18N
        NewEmployeeButton.setFocusable(false);
        NewEmployeeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        NewEmployeeButton.setName("NewEmployeeButton"); // NOI18N
        NewEmployeeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        NewEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewEmployeeButtonActionPerformed(evt);
            }
        });
        MilliscriptMoneySpinnerToolBar.add(NewEmployeeButton);

        jNewLoginButton.setIcon(resourceMap.getIcon("jNewLoginButton.icon")); // NOI18N
        jNewLoginButton.setToolTipText(resourceMap.getString("jNewLoginButton.toolTipText")); // NOI18N
        jNewLoginButton.setFocusable(false);
        jNewLoginButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jNewLoginButton.setName("jNewLoginButton"); // NOI18N
        jNewLoginButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jNewLoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jNewLoginButtonActionPerformed(evt);
            }
        });
        MilliscriptMoneySpinnerToolBar.add(jNewLoginButton);

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

        jToolBar1.setRollover(true);
        jToolBar1.setEnabled(false);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jGlobalTextField.setAlignmentX(0.0F);
        jGlobalTextField.setMaximumSize(new java.awt.Dimension(150, 20));
        jGlobalTextField.setMinimumSize(new java.awt.Dimension(150, 20));
        jGlobalTextField.setName("jGlobalTextField"); // NOI18N
        jToolBar1.add(jGlobalTextField);

        jSplitPane1.setLeftComponent(jToolBar1);

        MilliscriptMoneySpinnerToolBar.add(jSplitPane1);

        systemDesktopPane.setBackground(resourceMap.getColor("systemDesktopPane.background")); // NOI18N
        systemDesktopPane.setAutoscrolls(true);
        systemDesktopPane.setName("systemDesktopPane"); // NOI18N
        systemDesktopPane.setRequestFocusEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setIcon(resourceMap.getIcon("jLabel2.icon")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(1085, 390, 100, 0);
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
    private void nLoginMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nLoginMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame loginRegistration=new LoginRegistration();
        systemDesktopPane.add(loginRegistration);
        systemDesktopPane.moveToFront(loginRegistration);

        try
        {
            loginRegistration.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding Login Registration ! "+e);
        }
    }//GEN-LAST:event_nLoginMenuItemActionPerformed

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

    private void nEmployeeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nEmployeeMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame employee=new Employee();
        systemDesktopPane.add(employee);
        systemDesktopPane.moveToFront(employee);

        try
        {
            employee.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding employee ! "+e);
        }
    }//GEN-LAST:event_nEmployeeMenuItemActionPerformed

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

    private void eEmployeeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eEmployeeMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame editEmployee=new EditEmployee();
        systemDesktopPane.add(editEmployee);
        systemDesktopPane.moveToFront(editEmployee);

        try
        {
            editEmployee.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding edit employee ! "+e);
        }

    }//GEN-LAST:event_eEmployeeMenuItemActionPerformed

    private void ViewMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ViewMenuActionPerformed

    private void nDepartmentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nDepartmentMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame department=new Department();
        systemDesktopPane.add(department);
        systemDesktopPane.moveToFront(department);

        try
        {
            department.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding department ! "+e);
        }

    }//GEN-LAST:event_nDepartmentMenuItemActionPerformed

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

    private void nDesignationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nDesignationMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame designation=new Designation();
        systemDesktopPane.add(designation);
        systemDesktopPane.moveToFront(designation);

        try
        {
            designation.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding department ! "+e);
        }

    }//GEN-LAST:event_nDesignationMenuItemActionPerformed

    private void nBankMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nBankMenuItemActionPerformed
        // TODO add your handling code here:
        Bank bank=new Bank();
        systemDesktopPane.add(bank);
        systemDesktopPane.moveToFront(bank);

        try
        {
            bank.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding department ! "+e);
        }

    }//GEN-LAST:event_nBankMenuItemActionPerformed

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

    private void NewMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NewMenuActionPerformed

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

    private void jBackupDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBackupDatabaseButtonActionPerformed
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
}//GEN-LAST:event_jBackupDatabaseButtonActionPerformed

    private void NewEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewEmployeeButtonActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame employee=new Employee();
        systemDesktopPane.add(employee);
        systemDesktopPane.moveToFront(employee);

        try
        {
            employee.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding employee ! "+e);
        }
}//GEN-LAST:event_NewEmployeeButtonActionPerformed

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
        if(frameTitle.equalsIgnoreCase("::. View Account Report"))
        {
            ((ViewAccountReport)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. Credit Report"))
        {
            ((ViewCreditReport)systemDesktopPane.getSelectedFrame()).resetCombo();
        }
        if(frameTitle.equalsIgnoreCase("::. Debit Report"))
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
        if(frameTitle.equalsIgnoreCase("::. View Merchants"))
        {
            ((ViewMerchant)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Profile"))
        {
            ((ViewProfile)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. New Profile"))
        {
            ((Profile)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. New Profile Member"))
        {
            ((NewProfileMember)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Profile Member"))
        {
            ((ViewProfileMembers)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. Edit Profile Member"))
        {
            ((EditProfileMember)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. Update Merchant List"))
        {
            ((EditMerchantQuery)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. Profile Constant"))
        {
            ((ProfileConstant)systemDesktopPane.getSelectedFrame()).resetCombo();
            return;
        }
    }
    private void resetComboControlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetComboControlsActionPerformed
        // TODO add your handling code here:
        resetComboControls();
    }//GEN-LAST:event_resetComboControlsActionPerformed
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
        if(frameTitle.startsWith("::. View Credit"))
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
        if(frameTitle.startsWith("::. View Debit"))
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
        if(frameTitle.equalsIgnoreCase("::. View Account Report"))
        {
            ((ViewAccountReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. Credit Report"))
        {
            ((ViewCreditReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. Debit Report"))
        {
            ((ViewDebitReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. View Merchant"))
        {
            ((ViewMerchant)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Profile"))
        {
            ((ViewProfile)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. New Profile"))
        {
            ((Profile)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. New Profile Member"))
        {
            ((NewProfileMember)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Profile Member"))
        {
            ((ViewProfileMembers)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. Edit Profile Member"))
        {
            ((EditProfileMember)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. Update Merchant List"))
        {
            ((EditMerchantQuery)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. Profile Constant"))
        {
            ((ProfileConstant)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. New Stock Count"))
        {
            ((NewStockCount)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Stock Count"))
        {
            ((ViewStockCount)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. View Stock Report"))
        {
            ((StockReport)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
        if(frameTitle.equalsIgnoreCase("::. View Cash Report"))
        {
            ((CashReport)systemDesktopPane.getSelectedFrame()).reset();
            return;
        }
    }
    private void resetAllContolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetAllContolsActionPerformed
        // TODO add your handling code here:
        resetAllControls();
    }//GEN-LAST:event_resetAllContolsActionPerformed

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

    private void jNewLoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jNewLoginButtonActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame loginRegistration=new LoginRegistration();
        systemDesktopPane.add(loginRegistration);
        systemDesktopPane.moveToFront(loginRegistration);

        try
        {
            loginRegistration.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding Login Registration ! "+e);
        }
    }//GEN-LAST:event_jNewLoginButtonActionPerformed

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

private void nCompanyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nCompanyMenuItemActionPerformed
// TODO add your handling code here:
        javax.swing.JInternalFrame nCompany=new Company();
        systemDesktopPane.add(nCompany);
        systemDesktopPane.moveToFront(nCompany);

        try {
            nCompany.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding company ! "+e);
        }
}//GEN-LAST:event_nCompanyMenuItemActionPerformed

private void vCompanyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vCompanyMenuItemActionPerformed
// TODO add your handling code here:
        javax.swing.JInternalFrame vCompany=new ViewCompany();
        systemDesktopPane.add(vCompany);
        systemDesktopPane.moveToFront(vCompany);

        try {
            vCompany.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding view company ! "+e);
        }
}//GEN-LAST:event_vCompanyMenuItemActionPerformed

private void eCompanyMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eCompanyMenuItemActionPerformed
// TODO add your handling code here:
        javax.swing.JInternalFrame eCompany=new EditCompany();
        systemDesktopPane.add(eCompany);
        systemDesktopPane.moveToFront(eCompany);

        try {
            eCompany.setSelected(true);
        } catch(Exception e) {
            System.out.println("Error while adding edit company ! "+e);
        }
}//GEN-LAST:event_eCompanyMenuItemActionPerformed

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

        Connect.closeMainConnection();
        System.exit(0);
}//GEN-LAST:event_exitMenuItemActionPerformed

private void nProfileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nProfileMenuItemActionPerformed

        javax.swing.JInternalFrame nProfile=new Profile();
        systemDesktopPane.add(nProfile);
        systemDesktopPane.moveToFront(nProfile);

        try
        {
            nProfile.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Profile ! "+e);
        }
}//GEN-LAST:event_nProfileMenuItemActionPerformed

private void vProfileMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vProfileMenuItemActionPerformed

        javax.swing.JInternalFrame vProfile=new ViewProfile();
        systemDesktopPane.add(vProfile);
        systemDesktopPane.moveToFront(vProfile);

        try
        {
            vProfile.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view Profile ! "+e);
        }
}//GEN-LAST:event_vProfileMenuItemActionPerformed

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

private void nProfileMemberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nProfileMemberMenuItemActionPerformed

        javax.swing.JInternalFrame nProfileMember=new NewProfileMember();
        systemDesktopPane.add(nProfileMember);
        systemDesktopPane.moveToFront(nProfileMember);

        try
        {
            nProfileMember.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Profile Member ! "+e);
        }
}//GEN-LAST:event_nProfileMemberMenuItemActionPerformed

private void eProfileMemberMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eProfileMemberMenuItemActionPerformed

        javax.swing.JInternalFrame eProfileMember=new EditProfileMember();
        systemDesktopPane.add(eProfileMember);
        systemDesktopPane.moveToFront(eProfileMember);

        try
        {
            eProfileMember.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding edit Profile Member ! "+e);
        }
}//GEN-LAST:event_eProfileMemberMenuItemActionPerformed

private void jSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSwitchButtonActionPerformed
    if(OpenMSApp.d.isVisible())
    {
        OpenMSApp.d.transferFocus();
        //MilliscriptDMSApp.d.setState(java.awt.Frame.ICONIFIED);
    }
}//GEN-LAST:event_jSwitchButtonActionPerformed

private void jSwitchComboMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSwitchComboMenuItemActionPerformed
if(OpenMSApp.d.isVisible())OpenMSApp.d.transferFocus();
}//GEN-LAST:event_jSwitchComboMenuItemActionPerformed

private void nMerchantMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nMerchantMenuItemActionPerformed

        javax.swing.JInternalFrame eMerchantQuery=new EditMerchantQuery();
        systemDesktopPane.add(eMerchantQuery);
        systemDesktopPane.moveToFront(eMerchantQuery);

        try
        {
            eMerchantQuery.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding edit Edit Merchant Query ! "+e);
        }
}//GEN-LAST:event_nMerchantMenuItemActionPerformed

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
        if(frameTitle.equalsIgnoreCase("::. New Profile"))
        {
            ((Profile)systemDesktopPane.getSelectedFrame()).addRow();
        }
        if(frameTitle.equalsIgnoreCase("::. New Profile Member"))
        {
            ((NewProfileMember)systemDesktopPane.getSelectedFrame()).addRow();
        }
        if(frameTitle.equalsIgnoreCase("::. Profile Constant"))
        {
            ((ProfileConstant)systemDesktopPane.getSelectedFrame()).addRow();
        }
}//GEN-LAST:event_jAddRowComboMenuItemActionPerformed

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
        if(frameTitle.equalsIgnoreCase("::. New Profile"))
        {
            ((Profile)systemDesktopPane.getSelectedFrame()).deleteRow();
        }
        if(frameTitle.equalsIgnoreCase("::. New Profile Member"))
        {
            ((NewProfileMember)systemDesktopPane.getSelectedFrame()).deleteRow();
        }
        if(frameTitle.equalsIgnoreCase("::. Profile Constant"))
        {
            ((ProfileConstant)systemDesktopPane.getSelectedFrame()).deleteRow();
        }
}//GEN-LAST:event_jDeleteRowComboMenuItemActionPerformed

private void nProfileConstantMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nProfileConstantMenuItemActionPerformed

        javax.swing.JInternalFrame nProfileConstant=new ProfileConstant();
        systemDesktopPane.add(nProfileConstant);
        systemDesktopPane.moveToFront(nProfileConstant);

        try
        {
            nProfileConstant.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding Profile Constant ! "+e);
        }
}//GEN-LAST:event_nProfileConstantMenuItemActionPerformed

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

private void eSystemUserMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eSystemUserMenuItemActionPerformed

        javax.swing.JInternalFrame eEditLogin=new EditLogin();
        systemDesktopPane.add(eEditLogin);
        systemDesktopPane.moveToFront(eEditLogin);

        try
        {
            eEditLogin.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding edit Login ! "+e);
        }
}//GEN-LAST:event_eSystemUserMenuItemActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AccountMenu1;
    private javax.swing.JMenu LoginMenu;
    private javax.swing.JMenuItem LogoutMenuItem;
    private javax.swing.JToolBar MilliscriptMoneySpinnerToolBar;
    private javax.swing.JButton NewEmployeeButton;
    private javax.swing.JMenu NewMenu;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem eCompanyMenuItem;
    private javax.swing.JMenuItem eEmployeeMenuItem;
    private javax.swing.JMenuItem eProfileMemberMenuItem;
    private javax.swing.JMenuItem eSystemUserMenuItem;
    private javax.swing.JMenuItem jAddRowComboMenuItem;
    private javax.swing.JButton jBackupDatabaseButton;
    private javax.swing.JMenuItem jBackupMenuItem;
    private javax.swing.JMenuItem jBulkSMSComboMenuItem;
    private javax.swing.JMenuItem jChatComboMenuItem;
    private javax.swing.JMenuItem jCloseWindowMenuItem;
    private javax.swing.JMenuItem jDeleteRowComboMenuItem;
    private javax.swing.JTextField jGlobalTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JButton jNewLoginButton;
    private javax.swing.JMenuItem jPrintMenuItem;
    private javax.swing.JCheckBoxMenuItem jPrintSetupCheckBoxMenuItem;
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
    private javax.swing.JMenu jWindowMenu;
    private javax.swing.JMenu jWindowMenu1;
    private javax.swing.JMenuItem loginReportMenuItem;
    private javax.swing.JMenuItem loginUserMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem nBankMenuItem;
    private javax.swing.JMenuItem nCompanyMenuItem;
    private javax.swing.JMenuItem nDepartmentMenuItem;
    private javax.swing.JMenuItem nDesignationMenuItem;
    private javax.swing.JMenuItem nEmployeeMenuItem;
    private javax.swing.JMenuItem nGroupMenuItem;
    private javax.swing.JMenuItem nLoginMenuItem;
    private javax.swing.JMenuItem nMerchantMenuItem;
    private javax.swing.JMenuItem nProfileConstantMenuItem;
    private javax.swing.JMenuItem nProfileMemberMenuItem;
    private javax.swing.JMenuItem nProfileMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton resetAllContols;
    private javax.swing.JButton resetComboControls;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JDesktopPane systemDesktopPane;
    private javax.swing.JMenuItem vBackupMenuItem;
    private javax.swing.JMenuItem vBank;
    private javax.swing.JMenuItem vCompanyMenuItem;
    private javax.swing.JMenuItem vDepartmentMenuItem;
    private javax.swing.JMenuItem vDesignationMenuItem;
    private javax.swing.JMenuItem vEmployeeMenuItem;
    private javax.swing.JMenuItem vGroupMenuItem;
    private javax.swing.JMenuItem vNoteMenuItem;
    private javax.swing.JMenuItem vProfileMemberMenuItem;
    private javax.swing.JMenuItem vProfileMenuItem;
    private javax.swing.JMenuItem vTrialMenuItem;
    private javax.swing.JMenuItem vaMerchantMenuItem1;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
