
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
 * NoteViewer.java
 *
 * Created on Jan 23, 2013, 4:42:59 PM
 */
package openmoneyspinnersuite;

/**
 *
 * @author Abiodun
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jdesktop.application.FrameView;

import java.awt.Canvas;
import org.mozilla.xpcom.Mozilla;
import java.awt.Dimension;
import java.awt.Toolkit;
import org.mozilla.interfaces.nsISupports;
import ru.atomation.jbrowser.interfaces.ChromePages;
import ru.atomation.jbrowser.interfaces.*;
import ru.atomation.jbrowser.impl.JBrowserComponent;
import ru.atomation.jbrowser.impl.JBrowserCanvas;
import ru.atomation.jbrowser.impl.JComponentFactory;
import ru.atomation.jbrowser.impl.JTabbedComponentFactory;
import ru.atomation.jbrowser.interfaces.BrowserAdapter;
import ru.atomation.jbrowser.interfaces.BrowserManager;
import static org.mozilla.browser.XPCOMUtils.*;//.qi;
import org.mozilla.interfaces.*;

import java.io.*;
import java.awt.print.*;
import java.awt.*;
import javax.print.*;
import javax.print.attribute.*;
import org.w3c.dom.*;
import javax.print.*;
import org.apache.pdfbox.pdmodel.*;
import ru.atomation.jbrowser.impl.JBrowserBuilder;

import javax.swing.*;
import ru.atomation.jbrowser.impl.JBrowserManager;


public class NoteViewer extends javax.swing.JFrame {

JButton d=new JButton("Refresh");
JButton p=new JButton("Print");
JButton s=new JButton("Switch window");
JMenuBar mb=new JMenuBar();
JMenu m=new JMenu("Refresh");
JCheckBox printIncomeStatement=new JCheckBox("Print Income Statement");
JCheckBox printCashflowStatement=new JCheckBox("Print Statement of Cashflows");
JCheckBox printBalanceSheet=new JCheckBox("Print Balance Sheet");
boolean PRINT_INCOME_STATEMENT=false;
boolean PRINT_STATEMENT_OF_CASHFLOWS=false;
boolean PRINT_BALANCE_SHEET=false;
final int INVOICE_TABINDEX=1;
final int RECEIPT_TABINDEX=2;
final int VOUCHER_TABINDEX=3;
final int CREDIT_JOURNAL_TABINDEX=4;
final int DEBIT_JOURNAL_TABINDEX=5;
final int FINANCIAL_STATEMENT_TABINDEX=6;
final int PROFILE_TABINDEX=7;
////JComponentFactory<Canvas> canvasFactory = OpenMSApp.browserManager.getComponentFactory(JBrowserCanvas.class);
//JBrowserComponent<?> browser =canvasFactory.createBrowser().getBrowserManager().getComponentFactory(JBrowserCanvas.class).createBrowser(true);


        JBrowserComponent<?> receiptBrowser;
        
        JBrowserComponent<?> invoiceBrowser;

        JBrowserComponent<?> voucherBrowser;

        JBrowserComponent<?> creditJournalBrowser;
        
        JBrowserComponent<?> debitJournalBrowser;
        
        JBrowserComponent<?> financialStatementBrowser;
        
        JBrowserComponent<?> profileBrowser;
        
        JTabbedPane tabContainer;
        JTabbedComponentFactory<Canvas> canvasFactory;
                
    /** Creates new form NoteViewer */
    public NoteViewer(String title,String url)
    {
        setTitle("::. Previewer");
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMSApp.class).getContext().getResourceMap(Login.class);
        String filePath=resourceMap.getResourcesDir()+"MiliscriptDMS_logo.png";
        java.net.URL urll = resourceMap.getClassLoader().getResource(filePath);
        this.setIconImage(getToolkit().getImage (urll));
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE); 
        
        mb.add(d);
        mb.add(p);
        mb.add(s);
        mb.add(printIncomeStatement);
        mb.add(printCashflowStatement);
        mb.add(printBalanceSheet);
        
        printIncomeStatement.addActionListener(new java.awt.event.ActionListener()
        {public void actionPerformed(java.awt.event.ActionEvent evt){
            if(printIncomeStatement.isSelected())PRINT_INCOME_STATEMENT=true;
            else PRINT_INCOME_STATEMENT=false;}
        });
        printCashflowStatement.addActionListener(new java.awt.event.ActionListener()
        {public void actionPerformed(java.awt.event.ActionEvent evt){
            if(printCashflowStatement.isSelected())PRINT_STATEMENT_OF_CASHFLOWS=true;
            else PRINT_STATEMENT_OF_CASHFLOWS=false;
        }
        });
        printBalanceSheet.addActionListener(new java.awt.event.ActionListener()
        {public void actionPerformed(java.awt.event.ActionEvent evt){
            if(printBalanceSheet.isSelected())PRINT_BALANCE_SHEET=true;
            else PRINT_BALANCE_SHEET=false;
        }
        });
        d.addActionListener(new java.awt.event.ActionListener()
        {public void actionPerformed(java.awt.event.ActionEvent evt){doTas(canvasFactory.getTabContainer().getSelectedIndex()+1);}
        });
        p.addActionListener(new java.awt.event.ActionListener()
        {public void actionPerformed(java.awt.event.ActionEvent evt){print();}
        });            
        s.addActionListener(new java.awt.event.ActionListener()
        {public void actionPerformed(java.awt.event.ActionEvent evt){switchWindow();}
        });            
        this.setJMenuBar(mb);
        
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        this.setSize((int) (screenSize.getWidth() * 0.75f),
                (int) (screenSize.getHeight() * 0.75f));
        this.setLocationRelativeTo(null);

        tabContainer = new JTabbedPane();
        tabContainer.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        canvasFactory =new JTabbedComponentFactory<Canvas>(tabContainer, JBrowserCanvas.class) {

                    @Override
                    protected JComponent createBrowserComponent(
							JBrowserComponent<? extends Canvas> browser) {
                        JPanel panel = new JPanel(new BorderLayout());
                        panel.add(browser.getComponent(), BorderLayout.CENTER);
                        return panel;
                    }

                    @Override
                    protected JComponent createTabComponent(
							JBrowserComponent<? extends Canvas> browser,
							JComponent browserComponent) {
                        final JLabel label = new JLabel("Loading please wait");

                        label.addMouseListener(new MouseAdapter() {

                            @Override
                            public void mouseClicked(MouseEvent evt) {
                                int index = getTabContainer().indexOfTabComponent(label);
                                if (index != -1) {
                                    if (evt.getButton() != MouseEvent.BUTTON1) {
                                        getTabContainer().remove(index);
                                    } else {
                                        getTabContainer().setSelectedIndex(index);
                                    }
                                }
                            }
                        });

                        browser.addBrowserListener(new BrowserAdapter() {

                            @Override
                            public void onSetTitle(String title) {
                                label.setText(title);
                            }

                            @Override
                            public void onCloseWindow() {
                                int index = getTabContainer().indexOfTabComponent(label);
                                if (index != -1) {
                                    getTabContainer().remove(index);
                                }
                            }
                        });

                        label.setPreferredSize(new Dimension(100, 15));
                        return label;
                    }

                    @Override
                    protected void tabCreated(int index, JComponent browserComponent, JComponent tabComponent) {
                        if (index != -1) {
                            getTabContainer().setSelectedIndex(index);
                        }
                    }

                };

        new JBrowserBuilder().setBrowserFactory(canvasFactory).buildBrowserManager();

        this.getContentPane().add(tabContainer, BorderLayout.CENTER);
        this.setVisible(true);

        invoiceBrowser = canvasFactory.createBrowser();
        invoiceBrowser.setUrl(Configuration.officialInvoiceURLPath);
        
        receiptBrowser = canvasFactory.createBrowser();
        receiptBrowser.setUrl(Configuration.officialReceiptURLPath);
        
        voucherBrowser = canvasFactory.createBrowser();
        voucherBrowser.setUrl(Configuration.officialVoucherURLPath);

        creditJournalBrowser = canvasFactory.createBrowser();
        creditJournalBrowser.setUrl(Configuration.statementOfCashInflowNoteURLPath);
        
        debitJournalBrowser = canvasFactory.createBrowser();
        debitJournalBrowser.setUrl(Configuration.statementOfCashOutflowNoteURLPath);
        
        financialStatementBrowser = canvasFactory.createBrowser();
        financialStatementBrowser.setUrl(Configuration.accountStatementNoteURLPath); 
        
        profileBrowser = canvasFactory.createBrowser();
        profileBrowser.setUrl(Configuration.profileNoteURLPath); System.out.println("Profile URL="+Configuration.profileNoteURLPath);
    }
    void switchWindow()
    {
        OpenMSApp.currentDesktop.getFrame().transferFocus();
    }
    void printSilents()
    {
        try{
		DocFlavor flavor = new DocFlavor("application/x-java-jvm-local-objectref", "java.awt.print.Pageable");
		PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
		PrintService[] service = PrintServiceLookup.lookupPrintServices(flavor, aset);
 
		//send pdf to printer
		Document document = null;
		try{
            		document = receiptBrowser.getDocument();
          		//  document.silentPrint();  
            		PrinterJob printJob = PrinterJob.getPrinterJob();
            		printJob.setPrintService(service[1]);
            		//printJob.setPageable(browser);
            		printJob.print();
 
		}
		finally
		{
		    if( document != null ){
		       //document.close();
                    }
		}
		}
		catch(Exception e){System.out.println("E is -"+e);}    
    }
    void doTas(int tabIndex)
    {
        try{
            java.lang.Runtime run=java.lang.Runtime.getRuntime();
        if(tabIndex==INVOICE_TABINDEX)
        {
            invoiceBrowser.getBrowserManager().getBrowserConfig().enableJavascript(invoiceBrowser);
            invoiceBrowser.refresh();
            run.exec("explorer "+Configuration.officialInvoiceURLPath);
        }
        
        else if(tabIndex==RECEIPT_TABINDEX){
        receiptBrowser.getBrowserManager().getBrowserConfig().enableJavascript(receiptBrowser);
        receiptBrowser.refresh();
                //String script="<SCRIPT LANGUAGE=\"JavaScript\"> window.print(); </script>";
                String script="window.print();";
                org.mozilla.browser.MozillaAutomation.executeJavascript(receiptBrowser, script);
            run.exec("explorer "+Configuration.officialReceiptURLPath);
        }
        
        else if(tabIndex==VOUCHER_TABINDEX){
        voucherBrowser.getBrowserManager().getBrowserConfig().enableJavascript(voucherBrowser);
        voucherBrowser.refresh();
            run.exec("explorer "+Configuration.officialVoucherURLPath);
        }

        else if(tabIndex==CREDIT_JOURNAL_TABINDEX){
        creditJournalBrowser.refresh();
            run.exec("explorer "+Configuration.statementOfCashInflowNoteURLPath);
        }
        
        else if(tabIndex==DEBIT_JOURNAL_TABINDEX){
        debitJournalBrowser.refresh();
            run.exec("explorer "+Configuration.statementOfCashOutflowNoteURLPath);
        }
        
        else if(tabIndex==FINANCIAL_STATEMENT_TABINDEX){
        financialStatementBrowser.refresh();
            run.exec("explorer "+Configuration.accountStatementNoteURLPath);
        }
        
        else if(tabIndex==PROFILE_TABINDEX){
        profileBrowser.refresh();
            run.exec("explorer "+profileBrowser.getUrl());
        }
        
        }
        catch(Exception e)
        {
            System.out.println("Error running run at runtime "+e);
        }
        setTitle("::. Previewer");
        canvasFactory.getTabContainer().setSelectedIndex(tabIndex-1);
    }

    public void print()
    {
        //browser.getComponent().print(browser.getComponent().getGraphics()); 
        //new PrintDocument(browser.getComponent());
        //browser.getComponent().print(browser.getComponent().getGraphics());
        //org.mozilla.interfaces.nsIContentViewer cv=browser.getDocShell().getContentViewer();
        //cv.setPageMode(true, null);
        /*
        org.mozilla.interfaces.nsIPrintSettings nPSs=getService("@mozilla.org/gfx/printsettings-service;1",org.mozilla.interfaces.nsIPrintSettings.class);
        nPSs.setPrintSilent(true);
        nPSs.setPrintToFile(true);
        nPSs.setToFileName("D:\\printfrmpage.pdf");
        nPSs.setOutputFormat(org.mozilla.interfaces.nsIPrintSettings.kOutputFormatPDF);
        
        
        nsIWebBrowser webBrowser = (nsIWebBrowser) receiptBrowser.getWebBrowser();
        nsIInterfaceRequestor ifreq = (nsIInterfaceRequestor) webBrowser
                        .queryInterface(nsIInterfaceRequestor.NS_IINTERFACEREQUESTOR_IID);
        nsIWebBrowserPrint webBrowserPrint = (nsIWebBrowserPrint) ifreq
                        .getInterface(nsIWebBrowserPrint.NS_IWEBBROWSERPRINT_IID);
        webBrowserPrint.print(nPSs, null);
        
        org.mozilla.interfaces.nsIWebBrowserPrint wpp=qi(receiptBrowser.getWebBrowser(), org.mozilla.interfaces.nsIWebBrowserPrint.class);
        org.mozilla.interfaces.nsIPrintSettings nPS=qi(receiptBrowser.getWebBrowser(), org.mozilla.interfaces.nsIPrintSettings.class);
        org.mozilla.interfaces.nsIWebProgressListener nWP=new ru.atomation.jbrowser.impl.JBrowserProgressListener(receiptBrowser);
        wpp.print(nPS, nWP);
        */
        try
        {
            /*
        org.mozilla.interfaces.nsIPrintSettings h = qi(browser.getWebBrowser(), org.mozilla.interfaces.nsIPrintSettings.class);
        org.mozilla.xpcom.Mozilla d=org.mozilla.xpcom.Mozilla.getInstance();
        
       org.mozilla.interfaces.nsIWebProgressListener wl=new ru.atomation.jbrowser.impl.JBrowserProgressListener(browser);
        
        wpp.print(h._clone(),wl);
        int i=wpp.getPrintPreviewNumPages();
        wpp.print(h, wl);
            System.out.println("Writing DOM "+i);//File f= new File("C:\\melt.pdf");
        //org.mozilla.browser.impl.DOMUtils.writeDOMToFile(browser.getDocument(),f);*/
           
            java.lang.Runtime run=java.lang.Runtime.getRuntime();
            
            if(canvasFactory.getTabContainer().getSelectedIndex()==RECEIPT_TABINDEX-1)
            {/*
                BrowserAdapter d=new BrowserAdapter();d.onLoadingEnded();
                JBrowserBuilder g;
                org.mozilla.interfaces.nsIWebBrowserChrome2 mm;
                org.mozilla.browser.XPCOMUtils.register(null, null);
                nsIWebBrowserChrome s=g.getBrowserWindowCreator().createChromeWindow(receiptBrowser, WIDTH);
                nsIWebBrowserChrome2 ss=(nsIWebBrowserChrome2)g.getBrowserWindowCreator().createChromeWindow(receiptBrowser, WIDTH);
		nsIInterfaceRequestor ifreqs = (nsIInterfaceRequestor) s
				.queryInterface(nsIInterfaceRequestor.NS_IINTERFACEREQUESTOR_IID);
		nsIFactory webBrowsernsIFactory = (nsIFactory) ifreqs
				.getInterface(nsIFactory.NS_IFACTORY_IID);
                canvasFactory.getBrowserManager().registerBrowserWindowCreator(canvasFactory);
                ru.atomation.jbrowser.impl.JComponentFactory<Canvas> nj;
                
                nsIComponentManager cm=org.mozilla.xpcom.Mozilla.getInstance().getComponentManager();
                nsIComponentRegistrar cr=org.mozilla.xpcom.Mozilla.getInstance().getComponentRegistrar();
                nsIServiceManager sm=org.mozilla.xpcom.Mozilla.getInstance().getServiceManager();
                cr.cIDToContractID(nsIWebBrowserPrint.NS_IWEBBROWSERPRINT_IID);sm.getService(nsIPrintSettings.NS_IPRINTSETTINGS_IID, nsIPrintSettings.NS_ISUPPORTS_IID);
		//nsIWebBrowser webBrowser = (nsIWebBrowser)s.getWebBrowser();
		nsIWebBrowser webBrowser = (nsIWebBrowser)receiptBrowser.getWebBrowser();
		nsIInterfaceRequestor ifreq = (nsIInterfaceRequestor) webBrowser
				.queryInterface(nsIInterfaceRequestor.NS_IINTERFACEREQUESTOR_IID);
		nsIWebBrowserPrint webBrowserPrint = (nsIWebBrowserPrint) ifreq
				.getInterface(nsIWebBrowserPrint.NS_IWEBBROWSERPRINT_IID);
                ;
		if (webBrowserPrint == null)System.out.println("webBrowser Print=null:"+ Mozilla.NS_ERROR_NO_INTERFACE);
			//throw new SWTError("XPCOM error " + Mozilla.NS_ERROR_NO_INTERFACE);
		nsIPrintSettings printSettings = webBrowserPrint
				.getGlobalPrintSettings();
		if (printSettings == null)System.out.println("Print settings =null:"+ Mozilla.NS_ERROR_NO_INTERFACE);
        		printSettings.setPrintSilent(true);
        		printSettings.setShowPrintProgress(false);
        		printSettings.setTitle("MMSuite Print");
                        double d1[]={8.5,8.0};
                        double d2[]={11.0,10.0};
        		printSettings.getEffectivePageSize(d1,d2);
                
                printSettings.setStartPageRange(1);
        		printSettings.setEndPageRange(1);
        		//printSettings.setPrintRange(nsIPrintSettings.kUseInternalDefault);
                        printSettings.setPrintPageDelay(1);
        		printSettings.setIsInitializedFromPrinter(true);
                        printSettings.setDocURL(null);
                        printSettings.setPrintFrameType(nsIPrintSettings.kSelectedFrame);
                        printSettings.setPrintFrameTypeUsage(nsIPrintSettings.kUseSettingWhenPossible);
                        printSettings.setNumCopies(1);
                        printSettings.setPaperData(nsIPrintSettings.kPaperSizeNativeData);
                        printSettings.getPaperData();
                        printSettings.setHowToEnableFrameUI(nsIPrintSettings.kFrameEnableAll);
                        printSettings.setUnwriteableMarginBottom(0.0);
                        printSettings.setUnwriteableMarginLeft(0.0);
                        printSettings.setUnwriteableMarginRight(0.0);
                        printSettings.setOutputFormat(nsIPrintSettings.kOutputFormatNative);
                        printSettings.setOrientation(nsIPrintSettings.kPortraitOrientation);
                        printSettings.setPaperHeight(11.0);
                        printSettings.setPrintRange(printSettings.getHowToEnableFrameUI());
                        org.mozilla.interfaces.nsIComponentRegistrar j;j.cIDToContractID(receiptBrowser);
                                org.mozilla.interfaces.nsIComponentManager d;
                                org.mozilla.xpcom.Mozilla.getInstance().getComponentRegistrar().isContractIDRegistered(receiptBrowser);
                                
                .setPaperWidth(8.5);
                        printSettings.setResolutionName("300 dpi");
                        printSettings.setPlexName("mozilla:/gfx/printsettings-service");
        org.mozilla.interfaces.nsIPrintSettings nPSs=getService(org.mozilla.interfaces.nsIPrintSettings.NS_IPRINTSETTINGS_IID,org.mozilla.interfaces.nsIPrintSettings.class);
        
                .setScaling(WIDTH);
                
                .setPaperName("Letter");
                .setUnwriteableMarginTop(0.0);
		try {
                        webBrowserPrint.printPreview(printSettings, null, null);
			webBrowserPrint.printPreviewNavigate(nsIWebBrowserPrint.PRINTPREVIEW_HOME, nsIWebBrowserPrint.PRINTPREVIEW_END);
                    
                    webBrowserPrint.print(printSettings, null);webBrowserPrint.getPrintPreviewNumPages();
		} catch (Exception e) {
			System.out.println("wbp.print error: "+e);
			// do nothing
		}
                */
//
//                org.mozilla.interfaces.nsIWebBrowser webBrowser = (nsIWebBrowser)receiptBrowser.getWebBrowser();
//
//                nsIInterfaceRequestor ifreq = (nsIInterfaceRequestor) webBrowser
//                                .queryInterface(nsIInterfaceRequestor.NS_IINTERFACEREQUESTOR_IID);
//                nsIWebBrowserPrint webBrowserPrint = (nsIWebBrowserPrint) ifreq
//                                .getInterface(nsIWebBrowserPrint.NS_IWEBBROWSERPRINT_IID);
//
//
//		if (webBrowserPrint == null)System.out.println("webBrowser Print=null:"+ Mozilla.NS_ERROR_NO_INTERFACE);
//		else System.out.println("webBrowser Print not null");
//			//throw new SWTError("XPCOM error " + Mozilla.NS_ERROR_NO_INTERFACE);
//		nsIPrintSettings printSettings = webBrowserPrint.getGlobalPrintSettings();
//		if (printSettings == null)System.out.println("Print settings =null:"+ Mozilla.NS_ERROR_NO_INTERFACE);
//                        double d1[]={8.5};
//                        double d2[]={11.0};
//        		printSettings.getEffectivePageSize(d1,d2);
//        		printSettings.setPrintSilent(true);
//                        printSettings.setHowToEnableFrameUI(nsIPrintSettings.kFrameEnableAll);
//			printSettings.setShowPrintProgress(false);	
//			printSettings.setTitle("MMSuite Print");		
//        		printSettings.setIsInitializedFromPrinter(true);
//			printSettings.setIsInitializedFromPrefs(true);
//        		printSettings.setPrintFrameType(nsIPrintSettings.kNoFrames);
//                        printSettings.setPrintFrameTypeUsage(nsIPrintSettings.kUseSettingWhenPossible);
//
//                        printSettings.setPrintPageDelay(0);
//			System.out.println("Doc url: "+printSettings.getDocURL());
//			printSettings.setDocURL("file:/C:/help.htm");
//                        printSettings.setNumCopies(1);
//                        printSettings.setPaperData(nsIPrintSettings.kPaperSizeNativeData);
//                        printSettings.getPaperData();
//                        printSettings.setUnwriteableMarginBottom(0.0);
//                        printSettings.setUnwriteableMarginLeft(0.0);
//                        printSettings.setUnwriteableMarginRight(0.0);
//                        printSettings.setUnwriteableMarginTop(0.0);
//                        //printSettings.setOutputFormat(nsIPrintSettings.kOutputFormatNative);
//
//			printSettings.setOrientation(nsIPrintSettings.kPortraitOrientation);
//                        printSettings.setPaperHeight(11.0);
//                        printSettings.setPaperWidth(8.5);
//                        printSettings.setResolutionName("300 dpi");
//                        printSettings.setPaperName("Letter");printSettings.setScaling(100);
//                        printSettings.setPlexName("@mozilla.org/gfx/printsettings-service;1");
//		try {
//			System.out.println("Now Printing");
//			System.out.println("Paper Data: "+printSettings.getPrintRange());
//			System.out.println("Doc url: "+printSettings.getDocURL());
//                        //webBrowserPrint.printPreview(printSettings, win, null);
//			webBrowserPrint.print(printSettings, null);
//			System.out.println("Number of pages: "+webBrowserPrint.getPrintPreviewNumPages());
//			System.out.println("Finished Printing");
//		} catch (Exception e) {
//			System.out.println("wbp.print error: "+e);
//			// do nothing
//		}
                String script="<SCRIPT LANGUAGE=\"JavaScript\"> window.print(); </script>";
                org.mozilla.browser.MozillaAutomation.executeJavascript(receiptBrowser, script);
                run.exec("HTMLPrint.exe \""+Configuration.officialReceiptPath+"\"");
            }
            else if(canvasFactory.getTabContainer().getSelectedIndex()==INVOICE_TABINDEX-1)
            run.exec("HTMLPrint.exe \""+Configuration.officialInvoicePath+"\"");
            
            else if(canvasFactory.getTabContainer().getSelectedIndex()==VOUCHER_TABINDEX-1)
            run.exec("HTMLPrint.exe \""+Configuration.officialVoucherPath+"\"");

            else if(canvasFactory.getTabContainer().getSelectedIndex()==CREDIT_JOURNAL_TABINDEX-1)
            run.exec("HTMLPrint.exe \""+Configuration.statementOfCashInflowNotePath+"\"");

            else if(canvasFactory.getTabContainer().getSelectedIndex()==DEBIT_JOURNAL_TABINDEX-1)
            run.exec("HTMLPrint.exe \""+Configuration.statementOfCashOutflowNotePath+"\"");

            else if(canvasFactory.getTabContainer().getSelectedIndex()==PROFILE_TABINDEX-1)
            run.exec("HTMLPrint.exe \""+Configuration.profileNotePath+"\"");

            else if(canvasFactory.getTabContainer().getSelectedIndex()==FINANCIAL_STATEMENT_TABINDEX-1)
            {                
                if(PRINT_INCOME_STATEMENT==false&&PRINT_STATEMENT_OF_CASHFLOWS==false&&PRINT_BALANCE_SHEET==false)
                run.exec("HTMLPrint.exe \""+Configuration.accountStatementNotePath+"\"");
                if(PRINT_INCOME_STATEMENT==true)
                run.exec("HTMLPrint.exe \""+Configuration.incomeStatementNotePath+"\"");
                if(PRINT_STATEMENT_OF_CASHFLOWS==true)
                run.exec("HTMLPrint.exe \""+Configuration.cashFlowsNotePath+"\"");
                if(PRINT_BALANCE_SHEET==true)
                run.exec("HTMLPrint.exe \""+Configuration.balanceSheetNotePath+"\"");
            }
        }catch(Exception e){System.out.println("print err "+e);}
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("Form"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
