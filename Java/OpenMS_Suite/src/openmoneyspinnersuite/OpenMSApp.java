
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
 * OpenMSApp.java
 */

package openmoneyspinnersuite;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import com.lowagie.text.pdf.BaseFont;
import org.jdesktop.application.FrameView;

/**
 * The main class of the application.
 */

final class OpenMSApp extends SingleFrameApplication {
    BaseFont bf;
    
    Systems systems=new Systems();
    static String appArchType="Server";
    static int authorization=0;
    static String UserName="a";
    static String Database_A="MilliscriptMoneySpinnerASXTrial";
    final static String Database_B="Master";
    final static String Database_C="MilliscriptMoneySpinnerASXTrial";
    static String EmployeeName="Milliscript";
    static String CompanyName="Milliscript IT Enterprises";
    static String CompanyAddress="Ibadan, Nigeria";
    static String CompanyPhone="+234";
    static String configName="";
    static String serverIP="";
    static String trialCode="T30R00002";
    static String trialClientCode="T30R00001";
    static String trialDay="";
    static String curIP="";
    //static String employeeImageDirPath=Configuration.employeeImageDirPath;
    static String ApplicationLogon=Configuration.ApplicationLogon;
    static String EmployeeID="0";
    static String LoginCode="0";
    static String PrintDebitCode;
    static String PrintCreditCode;
    static String PrintWho="a";
    static String PrintCode;
    static String PrintInvoiceOrderCode;
    static String PrintCreditOrderCode;
    static String PrintDebitOrderCode;
    static java.awt.Component PrintComponent;
    static String userProcess="t";
    static String appType="Activated";//"AX, Server, Trial";//
    static String bSMSusername="bSMSInvalidUsername";
    static String bSMSpassword="bSMSInvalidPassword";
    static NoteViewer d;
    static FrameView currentDesktop;

    //static BrowserManager browserManager =new JBrowserBuilder().buildBrowserManager();
    
    /**
     * At startup create and show the main frame of the application.
     */

    @Override protected void startup() {
        OpenMSApp.configName=OpenMSApp.CompanyName;
        Configuration.setConfigurations();
       if(userProcess.equalsIgnoreCase("Finance"))
       {
            show(new OpenMS_Finance_Desktop(this));
            return;
       }
       if(userProcess.equalsIgnoreCase("Finance +"))
       {
            show(new OpenMS_FinanceAdmin_Desktop(this));
            return;
       }
       if(userProcess.equalsIgnoreCase("View +"))
       {
            show(new OpenMS_ViewPlus_Desktop(this));
            return;
       }
       if(userProcess.equalsIgnoreCase("Admin"))
       {
            show(new OpenMS_SystemAdmin_Desktop(this));
            return;
       }

        show(new OpenMS_SystemAdmin_Desktop(this));
        serverIP=Configuration.serverIPAddress;
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of OpenMSApp
     */
     static OpenMSApp getApplication() {
        return Application.getInstance(OpenMSApp.class);
    }
        
     static void resetUserParameter()
    {
        OpenMSApp.LoginCode="0";
        OpenMSApp.EmployeeID="0";
        OpenMSApp.EmployeeName="Milliscript";
        OpenMSApp.CompanyName="Milliscript IT Enterprises";
        OpenMSApp.CompanyAddress="Ibadan, Nigeria";
        OpenMSApp.CompanyPhone="+234";
        OpenMSApp.UserName="a";
        OpenMSApp.userProcess="t";
    }
    /**
     * Main method launching the application.
     */
    // static void main(String[] args) {
    //    launch(OpenMSApp.class, args);
    //}
    final static String SQL_U="root";
    final static String SQL_P="milli";
}
