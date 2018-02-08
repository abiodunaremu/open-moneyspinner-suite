
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
final class OpenMSView1 extends FrameView {

    public JDialog printCredit;
    public JDialog printDebit;
    Systems systems=new Systems();
    String printStatus=OpenMSApp.PrintWho+" | "+OpenMSApp.PrintCode;
    Thread TRtread;
    public OpenMSView1(SingleFrameApplication app) {
        super(app);

        org.jdesktop.application.ResourceMap resourceMap0 = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMSApp.class).getContext().getResourceMap(OpenMSView1.class);
        
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
        nCreditMenuItem = new javax.swing.JMenuItem();
        nDebitMenuItem = new javax.swing.JMenuItem();
        nDepartmentMenuItem = new javax.swing.JMenuItem();
        nDesignationMenuItem = new javax.swing.JMenuItem();
        nCustomerMenuItem = new javax.swing.JMenuItem();
        nEmployeeMenuItem = new javax.swing.JMenuItem();
        nTransTypeMenuItem = new javax.swing.JMenuItem();
        nLoginMenuItem = new javax.swing.JMenuItem();
        nVendorMenuItem = new javax.swing.JMenuItem();
        nCentreMenuItem = new javax.swing.JMenuItem();
        nStudentMenuItem = new javax.swing.JMenuItem();
        nBatchMenuItem = new javax.swing.JMenuItem();
        nCertificateMenuItem = new javax.swing.JMenuItem();
        nCourseMenuItem = new javax.swing.JMenuItem();
        nRegistrationMenuItem = new javax.swing.JMenuItem();
        nItemCategoryMenuItem = new javax.swing.JMenuItem();
        nItemLocationMenuItem = new javax.swing.JMenuItem();
        nItemMenuItem = new javax.swing.JMenuItem();
        nAccountMenuItem = new javax.swing.JMenuItem();
        nItemUnitMenuItem = new javax.swing.JMenuItem();
        jResetMenuItem = new javax.swing.JMenuItem();
        jResetComboMenuItem = new javax.swing.JMenuItem();
        jBackupMenuItem = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jPrintSetupCheckBoxMenuItem = new javax.swing.JCheckBoxMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        LogoutMenuItem = new javax.swing.JMenuItem();
        ViewMenu = new javax.swing.JMenu();
        AccountMenu = new javax.swing.JMenu();
        vaMerchantMenuItem = new javax.swing.JMenuItem();
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
        vCentreMenuItem = new javax.swing.JMenuItem();
        vStudentMenuItem = new javax.swing.JMenuItem();
        vCourseMenuItem = new javax.swing.JMenuItem();
        vCertificateMenuItem = new javax.swing.JMenuItem();
        vBatchMenuItem = new javax.swing.JMenuItem();
        vRegistrationMenuItem = new javax.swing.JMenuItem();
        vItemCategoryMenuItem = new javax.swing.JMenuItem();
        vItemLocationMenuItem = new javax.swing.JMenuItem();
        vItemMenuItem = new javax.swing.JMenuItem();
        vItemUnitMenuItem = new javax.swing.JMenuItem();
        vBackupMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        eCustomerMenuItem = new javax.swing.JMenuItem();
        eEmployeeMenuItem = new javax.swing.JMenuItem();
        eVendorMenuItem = new javax.swing.JMenuItem();
        eCentreMenuItem = new javax.swing.JMenuItem();
        eStudentMenuItem = new javax.swing.JMenuItem();
        eBatchMenuItem = new javax.swing.JMenuItem();
        eRegistrationMenuItem = new javax.swing.JMenuItem();
        eItemMenuItem = new javax.swing.JMenuItem();
        ConsentMenu = new javax.swing.JMenu();
        cCreditMenuItem = new javax.swing.JMenuItem();
        cDebitMenuItem = new javax.swing.JMenuItem();
        PostMenu = new javax.swing.JMenu();
        pCreditMenuItem = new javax.swing.JMenuItem();
        pDebitMenuItem = new javax.swing.JMenuItem();
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
        newCreditButton = new javax.swing.JButton();
        newDebitButton = new javax.swing.JButton();
        jNewLoginButton = new javax.swing.JButton();
        resetComboControls = new javax.swing.JButton();
        resetAllContols = new javax.swing.JButton();
        jViewAccountDetailsButton = new javax.swing.JButton();
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

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(OpenMSView1.class);
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

        nCreditMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nCreditMenuItem.setText(resourceMap.getString("nCreditMenuItem.text")); // NOI18N
        nCreditMenuItem.setName("nCreditMenuItem"); // NOI18N
        nCreditMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCreditMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nCreditMenuItem);

        nDebitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nDebitMenuItem.setText(resourceMap.getString("nDebitMenuItem.text")); // NOI18N
        nDebitMenuItem.setName("nDebitMenuItem"); // NOI18N
        nDebitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nDebitMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nDebitMenuItem);

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

        nCustomerMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nCustomerMenuItem.setText(resourceMap.getString("nCustomerMenuItem.text")); // NOI18N
        nCustomerMenuItem.setName("nCustomerMenuItem"); // NOI18N
        nCustomerMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCustomerMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nCustomerMenuItem);

        nEmployeeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nEmployeeMenuItem.setText(resourceMap.getString("nEmployeeMenuItem.text")); // NOI18N
        nEmployeeMenuItem.setName("nEmployeeMenuItem"); // NOI18N
        nEmployeeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nEmployeeMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nEmployeeMenuItem);

        nTransTypeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nTransTypeMenuItem.setText(resourceMap.getString("nTransTypeMenuItem.text")); // NOI18N
        nTransTypeMenuItem.setName("nTransTypeMenuItem"); // NOI18N
        nTransTypeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nTransTypeMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nTransTypeMenuItem);

        nLoginMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nLoginMenuItem.setText(resourceMap.getString("nLoginMenuItem.text")); // NOI18N
        nLoginMenuItem.setName("nLoginMenuItem"); // NOI18N
        nLoginMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nLoginMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nLoginMenuItem);

        nVendorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nVendorMenuItem.setText(resourceMap.getString("nVendorMenuItem.text")); // NOI18N
        nVendorMenuItem.setName("nVendorMenuItem"); // NOI18N
        nVendorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nVendorMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nVendorMenuItem);

        nCentreMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nCentreMenuItem.setText(resourceMap.getString("nCentreMenuItem.text")); // NOI18N
        nCentreMenuItem.setName("nCentreMenuItem"); // NOI18N
        nCentreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCentreMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nCentreMenuItem);

        nStudentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nStudentMenuItem.setText(resourceMap.getString("nStudentMenuItem.text")); // NOI18N
        nStudentMenuItem.setName("nStudentMenuItem"); // NOI18N
        nStudentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nStudentMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nStudentMenuItem);

        nBatchMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nBatchMenuItem.setText(resourceMap.getString("nBatchMenuItem.text")); // NOI18N
        nBatchMenuItem.setName("nBatchMenuItem"); // NOI18N
        nBatchMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nBatchMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nBatchMenuItem);

        nCertificateMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nCertificateMenuItem.setText(resourceMap.getString("nCertificateMenuItem.text")); // NOI18N
        nCertificateMenuItem.setName("nCertificateMenuItem"); // NOI18N
        nCertificateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCertificateMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nCertificateMenuItem);

        nCourseMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nCourseMenuItem.setText(resourceMap.getString("nCourseMenuItem.text")); // NOI18N
        nCourseMenuItem.setName("nCourseMenuItem"); // NOI18N
        nCourseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nCourseMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nCourseMenuItem);

        nRegistrationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        nRegistrationMenuItem.setText(resourceMap.getString("nRegistrationMenuItem.text")); // NOI18N
        nRegistrationMenuItem.setName("nRegistrationMenuItem"); // NOI18N
        nRegistrationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nRegistrationMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nRegistrationMenuItem);

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

        nAccountMenuItem.setText(resourceMap.getString("nAccountMenuItem.text")); // NOI18N
        nAccountMenuItem.setName("nAccountMenuItem"); // NOI18N
        nAccountMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nAccountMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nAccountMenuItem);

        nItemUnitMenuItem.setText(resourceMap.getString("nItemUnitMenuItem.text")); // NOI18N
        nItemUnitMenuItem.setName("nItemUnitMenuItem"); // NOI18N
        nItemUnitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nItemUnitMenuItemActionPerformed(evt);
            }
        });
        NewMenu.add(nItemUnitMenuItem);

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

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getActionMap(OpenMSView1.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setIcon(resourceMap.getIcon("exitMenuItem.icon")); // NOI18N
        exitMenuItem.setToolTipText(resourceMap.getString("exitMenuItem.toolTipText")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
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

        vaMerchantMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        vaMerchantMenuItem.setText(resourceMap.getString("vaMerchantMenuItem.text")); // NOI18N
        vaMerchantMenuItem.setName("vaMerchantMenuItem"); // NOI18N
        vaMerchantMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vaMerchantMenuItemActionPerformed(evt);
            }
        });
        AccountMenu.add(vaMerchantMenuItem);

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

        vCentreMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK));
        vCentreMenuItem.setText(resourceMap.getString("vCentreMenuItem.text")); // NOI18N
        vCentreMenuItem.setName("vCentreMenuItem"); // NOI18N
        vCentreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vCentreMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vCentreMenuItem);

        vStudentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK));
        vStudentMenuItem.setText(resourceMap.getString("vStudentMenuItem.text")); // NOI18N
        vStudentMenuItem.setName("vStudentMenuItem"); // NOI18N
        vStudentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vStudentMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vStudentMenuItem);

        vCourseMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        vCourseMenuItem.setText(resourceMap.getString("vCourseMenuItem.text")); // NOI18N
        vCourseMenuItem.setName("vCourseMenuItem"); // NOI18N
        vCourseMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vCourseMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vCourseMenuItem);

        vCertificateMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK));
        vCertificateMenuItem.setText(resourceMap.getString("vCertificateMenuItem.text")); // NOI18N
        vCertificateMenuItem.setName("vCertificateMenuItem"); // NOI18N
        vCertificateMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vCertificateMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vCertificateMenuItem);

        vBatchMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.ALT_MASK));
        vBatchMenuItem.setText(resourceMap.getString("vBatchMenuItem.text")); // NOI18N
        vBatchMenuItem.setName("vBatchMenuItem"); // NOI18N
        vBatchMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vBatchMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vBatchMenuItem);

        vRegistrationMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK));
        vRegistrationMenuItem.setText(resourceMap.getString("vRegistrationMenuItem.text")); // NOI18N
        vRegistrationMenuItem.setName("vRegistrationMenuItem"); // NOI18N
        vRegistrationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vRegistrationMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vRegistrationMenuItem);

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
        vBackupMenuItem.setName("vBackupMenuItem"); // NOI18N
        vBackupMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vBackupMenuItemActionPerformed(evt);
            }
        });
        ViewMenu.add(vBackupMenuItem);

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

        eEmployeeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        eEmployeeMenuItem.setText(resourceMap.getString("eEmployeeMenuItem.text")); // NOI18N
        eEmployeeMenuItem.setName("eEmployeeMenuItem"); // NOI18N
        eEmployeeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eEmployeeMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eEmployeeMenuItem);

        eVendorMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        eVendorMenuItem.setText(resourceMap.getString("eVendorMenuItem.text")); // NOI18N
        eVendorMenuItem.setName("eVendorMenuItem"); // NOI18N
        eVendorMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eVendorMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eVendorMenuItem);

        eCentreMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        eCentreMenuItem.setText(resourceMap.getString("eCentreMenuItem.text")); // NOI18N
        eCentreMenuItem.setName("eCentreMenuItem"); // NOI18N
        eCentreMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eCentreMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eCentreMenuItem);

        eStudentMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        eStudentMenuItem.setText(resourceMap.getString("eStudentMenuItem.text")); // NOI18N
        eStudentMenuItem.setName("eStudentMenuItem"); // NOI18N
        eStudentMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eStudentMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eStudentMenuItem);

        eBatchMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        eBatchMenuItem.setText(resourceMap.getString("eBatchMenuItem.text")); // NOI18N
        eBatchMenuItem.setName("eBatchMenuItem"); // NOI18N
        eBatchMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eBatchMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eBatchMenuItem);

        eRegistrationMenuItem.setText(resourceMap.getString("eRegistrationMenuItem.text")); // NOI18N
        eRegistrationMenuItem.setName("eRegistrationMenuItem"); // NOI18N
        eRegistrationMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eRegistrationMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eRegistrationMenuItem);

        eItemMenuItem.setText(resourceMap.getString("eItemMenuItem.text")); // NOI18N
        eItemMenuItem.setName("eItemMenuItem"); // NOI18N
        eItemMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eItemMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(eItemMenuItem);

        menuBar.add(jMenu1);

        ConsentMenu.setText(resourceMap.getString("ConsentMenu.text")); // NOI18N
        ConsentMenu.setName("ConsentMenu"); // NOI18N

        cCreditMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        cCreditMenuItem.setText(resourceMap.getString("cCreditMenuItem.text")); // NOI18N
        cCreditMenuItem.setName("cCreditMenuItem"); // NOI18N
        cCreditMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cCreditMenuItemActionPerformed(evt);
            }
        });
        ConsentMenu.add(cCreditMenuItem);

        cDebitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        cDebitMenuItem.setText(resourceMap.getString("cDebitMenuItem.text")); // NOI18N
        cDebitMenuItem.setName("cDebitMenuItem"); // NOI18N
        cDebitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cDebitMenuItemActionPerformed(evt);
            }
        });
        ConsentMenu.add(cDebitMenuItem);

        menuBar.add(ConsentMenu);

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
        MilliscriptMoneySpinnerToolBar.setName("MilliscriptMoneySpinnerToolBar"); // NOI18N

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

        jViewAccountDetailsButton.setIcon(resourceMap.getIcon("jViewAccountDetailsButton.icon")); // NOI18N
        jViewAccountDetailsButton.setToolTipText(resourceMap.getString("jViewAccountDetailsButton.toolTipText")); // NOI18N
        jViewAccountDetailsButton.setFocusable(false);
        jViewAccountDetailsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jViewAccountDetailsButton.setName("jViewAccountDetailsButton"); // NOI18N
        jViewAccountDetailsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jViewAccountDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jViewAccountDetailsButtonActionPerformed(evt);
            }
        });
        MilliscriptMoneySpinnerToolBar.add(jViewAccountDetailsButton);

        systemDesktopPane.setBackground(resourceMap.getColor("systemDesktopPane.background")); // NOI18N
        systemDesktopPane.setAutoscrolls(true);
        systemDesktopPane.setName("systemDesktopPane"); // NOI18N
        systemDesktopPane.setRequestFocusEnabled(false);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setIcon(resourceMap.getIcon("jLabel2.icon")); // NOI18N
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jLabel2.setName("jLabel2"); // NOI18N
        jLabel2.setBounds(1090, 390, 95, 67);
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
            new Login().setVisible(true);
            this.getFrame().dispose();
        }
        else
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"  User did not Login \n"+OpenMSApp.EmployeeName,"Execution SUCCESS",JOptionPane.INFORMATION_MESSAGE);

            new Login().setVisible(true);
            this.getFrame().dispose();
        }
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

    private void cCreditMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cCreditMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame cCreditOrder=new ConsentCreditOrder();
        systemDesktopPane.add(cCreditOrder);
        systemDesktopPane.moveToFront(cCreditOrder);
        try
        {
            cCreditOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding consent credit order ! "+e);
        }
    }//GEN-LAST:event_cCreditMenuItemActionPerformed

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

    private void vaMerchantMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vaMerchantMenuItemActionPerformed
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
    }//GEN-LAST:event_vaMerchantMenuItemActionPerformed

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

    private void cDebitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cDebitMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame cDebitOrder=new ConsentDebitOrder();
        systemDesktopPane.add(cDebitOrder);
        systemDesktopPane.moveToFront(cDebitOrder);
        try
        {
            cDebitOrder.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding consent debit order ! "+e);
        }
    }//GEN-LAST:event_cDebitMenuItemActionPerformed

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        String frameTitle=systemDesktopPane.getSelectedFrame().getTitle();
        if(frameTitle.equalsIgnoreCase("::. Print Receipt"))
        {
            OpenMSApp.PrintComponent=((PrintReceipt)systemDesktopPane.getSelectedFrame()).getPrintComponent();
        }
        if(OpenMSApp.PrintComponent==null)
        {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Unable to Locate Document","PRINT ERROR !",JOptionPane.ERROR_MESSAGE);
            return;
        }
        new PrintDocument(OpenMSApp.PrintComponent);
        OpenMSApp.PrintComponent=null;
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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

    private void nCentreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nCentreMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new Centre());
}//GEN-LAST:event_nCentreMenuItemActionPerformed

    private void nStudentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nStudentMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new Student());
}//GEN-LAST:event_nStudentMenuItemActionPerformed

    private void nBatchMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nBatchMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new Batch());
}//GEN-LAST:event_nBatchMenuItemActionPerformed

    private void nCertificateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nCertificateMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new Certificate());
}//GEN-LAST:event_nCertificateMenuItemActionPerformed

    private void eCentreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eCentreMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new EditCentre());
}//GEN-LAST:event_eCentreMenuItemActionPerformed

    private void eStudentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eStudentMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new EditStudent());
}//GEN-LAST:event_eStudentMenuItemActionPerformed

    private void vCentreMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vCentreMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new ViewCentre());
}//GEN-LAST:event_vCentreMenuItemActionPerformed

    private void vStudentMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vStudentMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new ViewStudentRecord());
}//GEN-LAST:event_vStudentMenuItemActionPerformed

    private void nCourseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nCourseMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new Course());
    }//GEN-LAST:event_nCourseMenuItemActionPerformed

    private void vCourseMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vCourseMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new ViewCourse());
    }//GEN-LAST:event_vCourseMenuItemActionPerformed

    private void vCertificateMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vCertificateMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new ViewCertificate());
    }//GEN-LAST:event_vCertificateMenuItemActionPerformed

    private void vBatchMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vBatchMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new ViewBatch());
    }//GEN-LAST:event_vBatchMenuItemActionPerformed

    private void eBatchMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eBatchMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new EditBatch());
    }//GEN-LAST:event_eBatchMenuItemActionPerformed

    private void vRegistrationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vRegistrationMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new ViewRegistration());
    }//GEN-LAST:event_vRegistrationMenuItemActionPerformed

    private void nRegistrationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nRegistrationMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new Registration());
    }//GEN-LAST:event_nRegistrationMenuItemActionPerformed

    private void eRegistrationMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eRegistrationMenuItemActionPerformed
        // TODO add your handling code here:

        //systemDesktopPane.add(new EditRegistration());
    }//GEN-LAST:event_eRegistrationMenuItemActionPerformed
    public void resetComboControls()
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
    }
    private void resetComboControlsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetComboControlsActionPerformed
        // TODO add your handling code here:
        resetComboControls();
    }//GEN-LAST:event_resetComboControlsActionPerformed
    public void resetAllControls()
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
        if(frameTitle.equalsIgnoreCase("::. Debit Report"))
        {
            ((ViewDebitReport)systemDesktopPane.getSelectedFrame()).reset();
        }
        if(frameTitle.equalsIgnoreCase("::. Credit Report"))
        {
            ((ViewCreditReport)systemDesktopPane.getSelectedFrame()).reset();
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

    private void nAccountMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nAccountMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame newAccount=new Account();
        systemDesktopPane.add(newAccount);
        systemDesktopPane.moveToFront(newAccount);

        try
        {
            newAccount.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding new Account ! "+e);
        }
    }//GEN-LAST:event_nAccountMenuItemActionPerformed

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

    private void jViewAccountDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jViewAccountDetailsButtonActionPerformed
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
            System.out.println("Error at MilliscriptDMSView1.jViewAccountDetailsButtonActionPerformed while adding view Account ! "+e);
        }
    }//GEN-LAST:event_jViewAccountDetailsButtonActionPerformed

    private void jCloseWindowMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCloseWindowMenuItemActionPerformed
        // TODO add your handling code here:
        systemDesktopPane.getSelectedFrame().dispose();
    }//GEN-LAST:event_jCloseWindowMenuItemActionPerformed

    private void vBackupMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vBackupMenuItemActionPerformed
        // TODO add your handling code here:
        javax.swing.JInternalFrame viewBackup=new ViewBackup();
        systemDesktopPane.add(viewBackup);
        systemDesktopPane.moveToFront(viewBackup);

        try
        {
            viewBackup.setSelected(true);
        }
        catch(Exception e)
        {
            System.out.println("Error while adding view Backup ! "+e);
        }
    }//GEN-LAST:event_vBackupMenuItemActionPerformed

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

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aboutMenuItemActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu AccountMenu;
    private javax.swing.JMenu ConsentMenu;
    private javax.swing.JMenu CreditMenu;
    private javax.swing.JMenu DebitMenu;
    private javax.swing.JMenu LoginMenu;
    private javax.swing.JMenuItem LogoutMenuItem;
    private javax.swing.JToolBar MilliscriptMoneySpinnerToolBar;
    private javax.swing.JButton NewEmployeeButton;
    private javax.swing.JMenu NewMenu;
    private javax.swing.JMenu PostMenu;
    private javax.swing.JMenu ViewMenu;
    private javax.swing.JMenuItem cCreditMenuItem;
    private javax.swing.JMenuItem cDebitMenuItem;
    private javax.swing.JMenuItem eBatchMenuItem;
    private javax.swing.JMenuItem eCentreMenuItem;
    private javax.swing.JMenuItem eCustomerMenuItem;
    private javax.swing.JMenuItem eEmployeeMenuItem;
    private javax.swing.JMenuItem eItemMenuItem;
    private javax.swing.JMenuItem eRegistrationMenuItem;
    private javax.swing.JMenuItem eStudentMenuItem;
    private javax.swing.JMenuItem eVendorMenuItem;
    private javax.swing.JButton jBackupDatabaseButton;
    private javax.swing.JMenuItem jBackupMenuItem;
    private javax.swing.JMenuItem jCloseWindowMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JButton jNewLoginButton;
    private javax.swing.JCheckBoxMenuItem jPrintSetupCheckBoxMenuItem;
    private javax.swing.JMenuItem jResetComboMenuItem;
    private javax.swing.JMenuItem jResetMenuItem;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JButton jViewAccountDetailsButton;
    private javax.swing.JMenu jWindowMenu;
    private javax.swing.JMenuItem loginReportMenuItem;
    private javax.swing.JMenuItem loginUserMenuItem;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem nAccountMenuItem;
    private javax.swing.JMenuItem nBankMenuItem;
    private javax.swing.JMenuItem nBatchMenuItem;
    private javax.swing.JMenuItem nCentreMenuItem;
    private javax.swing.JMenuItem nCertificateMenuItem;
    private javax.swing.JMenuItem nCourseMenuItem;
    private javax.swing.JMenuItem nCreditMenuItem;
    private javax.swing.JMenuItem nCustomerMenuItem;
    private javax.swing.JMenuItem nDebitMenuItem;
    private javax.swing.JMenuItem nDepartmentMenuItem;
    private javax.swing.JMenuItem nDesignationMenuItem;
    private javax.swing.JMenuItem nEmployeeMenuItem;
    private javax.swing.JMenuItem nItemCategoryMenuItem;
    private javax.swing.JMenuItem nItemLocationMenuItem;
    private javax.swing.JMenuItem nItemMenuItem;
    private javax.swing.JMenuItem nItemUnitMenuItem;
    private javax.swing.JMenuItem nLoginMenuItem;
    private javax.swing.JMenuItem nRegistrationMenuItem;
    private javax.swing.JMenuItem nStudentMenuItem;
    private javax.swing.JMenuItem nTransTypeMenuItem;
    private javax.swing.JMenuItem nVendorMenuItem;
    private javax.swing.JButton newCreditButton;
    private javax.swing.JButton newDebitButton;
    private javax.swing.JMenuItem pCreditMenuItem;
    private javax.swing.JMenuItem pDebitMenuItem;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton resetAllContols;
    private javax.swing.JButton resetComboControls;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JDesktopPane systemDesktopPane;
    private javax.swing.JMenuItem vBackupMenuItem;
    private javax.swing.JMenuItem vBank;
    private javax.swing.JMenuItem vBatchMenuItem;
    private javax.swing.JMenuItem vCentreMenuItem;
    private javax.swing.JMenuItem vCertificateMenuItem;
    private javax.swing.JMenuItem vCourseMenuItem;
    private javax.swing.JMenuItem vCustomerMenuItem;
    private javax.swing.JMenuItem vDepartmentMenuItem;
    private javax.swing.JMenuItem vDesignationMenuItem;
    private javax.swing.JMenuItem vEmployeeMenuItem;
    private javax.swing.JMenuItem vItemCategoryMenuItem;
    private javax.swing.JMenuItem vItemLocationMenuItem;
    private javax.swing.JMenuItem vItemMenuItem;
    private javax.swing.JMenuItem vItemUnitMenuItem;
    private javax.swing.JMenuItem vRegistrationMenuItem;
    private javax.swing.JMenuItem vStudentMenuItem;
    private javax.swing.JMenuItem vTransTypeMenuItem;
    private javax.swing.JMenuItem vTrialMenuItem;
    private javax.swing.JMenuItem vVendorMenuItem;
    private javax.swing.JMenuItem vaDetailsMenuItem;
    private javax.swing.JMenuItem vaHistoryMenuItem;
    private javax.swing.JMenuItem vaMerchantMenuItem;
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
