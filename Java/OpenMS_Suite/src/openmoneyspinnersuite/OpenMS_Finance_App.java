
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
 * OpenMS_Finance_App.java
 */

package openmoneyspinnersuite;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

/**
 * The main class of the application.
 */
final class OpenMS_Finance_App extends SingleFrameApplication {

    Systems systems=new Systems();
    static int authorization=0;
    static String UserName="a";
    static String Database_A="MilliscriptDMS";
    static String Database_B="Master";
    static String EmployeeName="Milliscript";
    static String ApplicationLogon="First";
    static String EmployeeID="0";
    static String LoginCode="0";
    static String PrintDebitCode;
    static String PrintCreditCode;
    static String PrintWho="a";
    static String PrintCode;
    static String PrintCreditOrderCode;
    static String PrintDebitOrderCode;
    static java.awt.Component PrintComponent;
    static String userProcess="t";


    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new OpenMS_Finance_Desktop(this));
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
     * @return the instance of MilliscriptDMSApp
     */
    public static OpenMS_Finance_App getApplication() {
        return Application.getInstance(OpenMS_Finance_App.class);
    }

    /**
     * Main method launching the application.
     */
    //public static void main(String[] args) {
    //    launch(MilliscriptDMSApp.class, args);
    //}
}
