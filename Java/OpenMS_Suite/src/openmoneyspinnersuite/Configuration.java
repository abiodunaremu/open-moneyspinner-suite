
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
import java.util.*;
import java.io.*;

public class Configuration {

    static String configFilePath="C:/Program Files/Milliscript IT Enterprises/Config"+OpenMSApp.configName+".ini";
    static String ApplicationLogon="First";
//    static String serverIPAddress="";
//    static String employeeImageDirPath="C:\\Milliscript_MoneySpinner\\Images\\Employee\\Passport\\";
//    static String studentImageDirPath="C:\\Milliscript_MoneySpinner\\Images\\Student\\Passport\\";
//    static String studentSignatureImageDirPath="C:\\Milliscript_MoneySpinner\\Images\\Student\\Signature\\";
//    static String accountStatementNotePath="C:\\Milliscript_MoneySpinner\\Note\\statementOfAccount.htm";
//    static String statementOfCashInflowNotePath="C:\\Milliscript_MoneySpinner\\Note\\statementOfCredit.htm";
//    static String statementOfCashOutflowNotePath="C:\\Milliscript_MoneySpinner\\Note\\statementOfDebit.htm";
//    static String ApplicationLogon="First";
//    static String incomeStatementHeaderImagePath="C:\\Milliscript_MoneySpinner\\Note\\incomeStatement_Head.png";
//    static String statementOfCashInflowsHeaderImagePath="C:\\Milliscript_MoneySpinner\\Note\\statementOfCashInflows_Head.png";
//    static String statementOfCashOutflowsHeaderImagePath="C:\\Milliscript_MoneySpinner\\Note\\statementOfCashOutflows_Head.png";
//    static String statementOfCashFlowsHeaderImagePath="C:\\Milliscript_MoneySpinner\\Note\\statementOfCashFlows_Head.png";
//    static String statementOfRevenuesHeaderImagePath="C:\\Milliscript_MoneySpinner\\Note\\statementOfRevenues_Head.png";
//    static String statementOfExpensesHeaderImagePath="C:\\Milliscript_MoneySpinner\\Note\\statementOfExpenses_Head.png";
//    static String backupDirPath="C:\\Milliscript_MoneySpinner\\Backup\\";
    
    static String serverIPAddress="";
    static String initServerIPAddress="";
    static String backupDirPath="";
    static String employeeImageDirPath="";
    static String studentImageDirPath="";
    static String profileImageDirPath="";
    static String studentSignatureImageDirPath="";
    static String notePath="";
    static String profileNotePath="";
    static String accountStatementNotePath="";
    static String incomeStatementNotePath="";
    static String balanceSheetNotePath="";
    static String cashFlowsNotePath="";
    static String statementOfCashInflowNotePath="";
    static String statementOfCashOutflowNotePath="";
    static String profileHeaderImagePath="";
    static String incomeStatementHeaderImagePath="";
    static String balanceSheetHeaderImagePath="";
    static String statementOfCashInflowsHeaderImagePath="";
    static String statementOfCashOutflowsHeaderImagePath="";
    static String statementOfCashFlowsHeaderImagePath="";
    static String statementOfRevenuesHeaderImagePath="";
    static String statementOfExpensesHeaderImagePath="";
    static String officialInvoicePath="";
    static String officialReceiptPath="";
    static String officialVoucherPath="";
    static String officialCashReceiptHeaderImagePath="";
    static String officialCashVoucherHeaderImagePath="";
    static String officialInventoryReceiptHeaderImagePath="";
    static String officialInventoryVoucherHeaderImagePath="";
    static String officialAdjustmentReceiptHeaderImagePath="";
    static String officialAdjustmentVoucherHeaderImagePath="";

    static String officialCashReceiptFooterImagePath="";
    static String officialCashVoucherFooterImagePath="";
    static String officialInvoiceFooterImagePath="";
    static String officialInvoiceFooterImagePath2="";
    static String officialSignedInvoiceFooterImagePath="";
    static String officialInventoryReceiptFooterImagePath="";
    static String officialInventoryVoucherFooterImagePath="";
    static String officialAdjustmentReceiptFooterImagePath="";
    static String officialAdjustmentVoucherFooterImagePath="";

    static String officialCashSubReceiptHeaderImagePath="";
    static String officialInvoiceHeaderImagePath="";
    static String officialCashSubVoucherHeaderImagePath="";
    static String officialInventorySubReceiptHeaderImagePath="";
    static String officialInventorySubVoucherHeaderImagePath="";
    static String POSFooterComment="";
//URL PATHS
    static String employeeImageDirURLPath="";
    static String studentImageDirURLPath="";
    static String profileImageDirURLPath="";
    static String studentSignatureImageDirURLPath="";
    static String noteURLPath="";
    static String profileNoteURLPath="";
    static String accountStatementNoteURLPath="";
    static String incomeStatementNoteURLPath="";
    static String balanceSheetNoteURLPath="";
    static String cashFlowsNoteURLPath="";
    static String statementOfCashInflowNoteURLPath="";
    static String statementOfCashOutflowNoteURLPath="";
    static String profileHeaderImageURLPath="";
    static String incomeStatementHeaderImageURLPath="";
    static String balanceSheetHeaderImageURLPath="";
    static String statementOfCashInflowsHeaderImageURLPath="";
    static String statementOfCashOutflowsHeaderImageURLPath="";
    static String statementOfCashFlowsHeaderImageURLPath="";
    static String statementOfRevenuesHeaderImageURLPath="";
    static String statementOfExpensesHeaderImageURLPath="";
    static String officialReceiptURLURLPath="";
    static String officialVoucherURLPath="";
    static String officialReceiptURLPath="";
    static String officialInvoiceURLPath="";
    static String officialInvoiceHeaderImageURLPath="";
    static String officialCashReceiptHeaderImageURLPath="";
    static String officialCashVoucherHeaderImageURLPath="";
    static String officialInventoryReceiptHeaderImageURLPath="";
    static String officialInventoryVoucherHeaderImageURLPath="";
    static String officialAdjustmentReceiptHeaderImageURLPath="";
    static String officialAdjustmentVoucherHeaderImageURLPath="";

    static String officialInvoiceFooterImageURLPath="";
    static String officialInvoiceFooterImageURLPath2="";
    static String officialSignedInvoiceFooterImageURLPath="";
    static String officialCashReceiptFooterImageURLPath="";
    static String officialCashVoucherFooterImageURLPath="";
    static String officialInventoryReceiptFooterImageURLPath="";
    static String officialInventoryVoucherFooterImageURLPath="";
    static String officialAdjustmentReceiptFooterImageURLPath="";
    static String officialAdjustmentVoucherFooterImageURLPath="";

    static String officialCashSubReceiptHeaderImageURLPath="";
    static String officialCashSubVoucherHeaderImageURLPath="";
    static String officialInventorySubReceiptHeaderImageURLPath="";
    static String officialInventorySubVoucherHeaderImageURLPath="";
    
     static String readConfig(String fileLocation,String config)
    {
        String configValue=null;
        try
        {
            File file=new File(fileLocation);
            FileReader fd=new FileReader(file);
            int io=(int)file.length();
            char[] cbkuf=new char[io];
            int i=fd.read(cbkuf);
            System.out.println("No of character read from "+fileLocation+": "+i+" or "+cbkuf.length);

            String fileContent[]= String.valueOf(cbkuf).split(";");
            int ii=fileContent.length;

            System.out.println("No of split: "+ii);
            for(int x=0;x<ii;x++)
            {
                if(fileContent[x].indexOf(config) != -1)
                {
                    configValue=fileContent[x].substring(fileContent[x].indexOf('"')+1, fileContent[x].lastIndexOf('"'));
                    //System.out.println("Found: "+configValue);
                }
            }
        }
        catch(Exception e)
        {System.out.println("Error reading file: "+fileLocation+": "+e);}

        return configValue;
    }

    static void setConfigurations()
    {
        configFilePath="C:/Program Files/Milliscript IT Enterprises/Config"+OpenMSApp.configName+".ini";
        Configuration.serverIPAddress=OpenMSApp.serverIP.isEmpty()?Configuration.readConfig(Configuration.configFilePath, "ServerIPAddress"):OpenMSApp.serverIP;
        Configuration.POSFooterComment=Configuration.readConfig(Configuration.configFilePath, "POSFooterComment");
        Configuration.initServerIPAddress=Configuration.serverIPAddress;
        Configuration.notePath=Configuration.readConfig(Configuration.configFilePath, "notePath");
        Configuration.profileNotePath=Configuration.readConfig(Configuration.configFilePath, "profileNotePath");
        Configuration.accountStatementNotePath=Configuration.readConfig(Configuration.configFilePath, "accountStatementNotePath");
        Configuration.incomeStatementNotePath=Configuration.readConfig(Configuration.configFilePath, "incomeStatementNotePath");
        Configuration.balanceSheetNotePath=Configuration.readConfig(Configuration.configFilePath, "balanceSheetNotePath");
        Configuration.cashFlowsNotePath=Configuration.readConfig(Configuration.configFilePath, "cashFlowsNotePath");
        Configuration.backupDirPath=Configuration.readConfig(Configuration.configFilePath, "backupDirPath");
        Configuration.employeeImageDirPath=Configuration.readConfig(Configuration.configFilePath, "employeeImageDirPath");
        Configuration.incomeStatementHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "incomeStatementHeaderImagePath");
        Configuration.balanceSheetHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "balanceSheetHeaderImagePath");
        Configuration.statementOfCashFlowsHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashFlowsHeaderImagePath");
        Configuration.statementOfCashInflowNotePath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashInflowNotePath");
        Configuration.statementOfCashInflowsHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashInflowsHeaderImagePath");
        Configuration.statementOfCashOutflowNotePath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashOutflowNotePath");
        Configuration.statementOfCashOutflowsHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashOutflowsHeaderImagePath");
        Configuration.statementOfExpensesHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "statementOfExpensesHeaderImagePath");
        Configuration.statementOfRevenuesHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "statementOfRevenuesHeaderImagePath");
        Configuration.profileImageDirPath=Configuration.readConfig(Configuration.configFilePath, "profileImageDirPath");
        Configuration.studentImageDirPath=Configuration.readConfig(Configuration.configFilePath, "studentImageDirPath");
        Configuration.studentSignatureImageDirPath=Configuration.readConfig(Configuration.configFilePath, "studentSignatureImageDirPath");

        Configuration.officialInvoicePath=Configuration.readConfig(Configuration.configFilePath, "officialInvoicePath");
        Configuration.officialReceiptPath=Configuration.readConfig(Configuration.configFilePath, "officialReceiptPath");
        Configuration.officialVoucherPath=Configuration.readConfig(Configuration.configFilePath, "officialVoucherPath");     
        Configuration.profileHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "profileHeaderImagePath");
        Configuration.officialInvoiceHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialInvoiceHeaderImagePath");
        Configuration.officialCashReceiptHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialCashReceiptHeaderImagePath");
        Configuration.officialCashVoucherHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialCashVoucherHeaderImagePath");
        Configuration.officialInventoryReceiptHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialInventoryReceiptHeaderImagePath");
        Configuration.officialInventoryVoucherHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialInventoryVoucherHeaderImagePath");
        Configuration.officialAdjustmentReceiptHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialAdjustmentReceiptHeaderImagePath");
        Configuration.officialAdjustmentVoucherHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialAdjustmentVoucherHeaderImagePath");

        Configuration.officialCashReceiptFooterImagePath=Configuration.readConfig(Configuration.configFilePath, "officialCashReceiptFooterImagePath");
        Configuration.officialCashVoucherFooterImagePath=Configuration.readConfig(Configuration.configFilePath, "officialCashVoucherFooterImagePath");
        Configuration.officialInvoiceFooterImagePath=Configuration.readConfig(Configuration.configFilePath, "officialInvoiceFooterImagePath");
        Configuration.officialSignedInvoiceFooterImagePath=Configuration.readConfig(Configuration.configFilePath, "officialSignedInvoiceFooterImagePath");
        Configuration.officialInventoryReceiptFooterImagePath=Configuration.readConfig(Configuration.configFilePath, "officialInventoryReceiptFooterImagePath");
        Configuration.officialInventoryVoucherFooterImagePath=Configuration.readConfig(Configuration.configFilePath, "officialInventoryVoucherFooterImagePath");
        Configuration.officialAdjustmentReceiptFooterImagePath=Configuration.readConfig(Configuration.configFilePath, "officialAdjustmentReceiptFooterImagePath");
        Configuration.officialAdjustmentVoucherFooterImagePath=Configuration.readConfig(Configuration.configFilePath, "officialAdjustmentVoucherFooterImagePath");

        Configuration.officialCashSubReceiptHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialCashSubReceiptHeaderImagePath");
        Configuration.officialCashSubVoucherHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialCashSubVoucherHeaderImagePath");
        Configuration.officialInventorySubReceiptHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialInventorySubReceiptHeaderImagePath");
        Configuration.officialInventorySubVoucherHeaderImagePath=Configuration.readConfig(Configuration.configFilePath, "officialInventorySubVoucherHeaderImagePath");
    //URL PATH
        
        Configuration.noteURLPath=Configuration.readConfig(Configuration.configFilePath, "noteURLPath");
        Configuration.profileNoteURLPath=Configuration.readConfig(Configuration.configFilePath, "profileNoteURLPath");
        Configuration.accountStatementNoteURLPath=Configuration.readConfig(Configuration.configFilePath, "accountStatementNoteURLPath");
        Configuration.incomeStatementNoteURLPath=Configuration.readConfig(Configuration.configFilePath, "incomeStatementNoteURLPath");
        Configuration.balanceSheetNoteURLPath=Configuration.readConfig(Configuration.configFilePath, "balanceSheetNoteURLPath");
        Configuration.cashFlowsNoteURLPath=Configuration.readConfig(Configuration.configFilePath, "cashFlowsNoteURLPath");
        
        Configuration.employeeImageDirURLPath=Configuration.readConfig(Configuration.configFilePath, "employeeImageDirURLPath");
        Configuration.incomeStatementHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "incomeStatementHeaderImageURLPath");
        Configuration.balanceSheetHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "balanceSheetHeaderImageURLPath");
        Configuration.statementOfCashFlowsHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashFlowsHeaderImageURLPath");
        Configuration.statementOfCashInflowNoteURLPath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashInflowNoteURLPath");
        Configuration.statementOfCashInflowsHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashInflowsHeaderImageURLPath");
        Configuration.statementOfCashOutflowNoteURLPath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashOutflowNoteURLPath");
        Configuration.statementOfCashOutflowsHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "statementOfCashOutflowsHeaderImageURLPath");
        Configuration.statementOfExpensesHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "statementOfExpensesHeaderImageURLPath");
        Configuration.statementOfRevenuesHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "statementOfRevenuesHeaderImageURLPath");
        Configuration.studentImageDirURLPath=Configuration.readConfig(Configuration.configFilePath, "studentImageDirURLPath");
        Configuration.profileImageDirURLPath=Configuration.readConfig(Configuration.configFilePath, "profileImageDirURLPath");
        Configuration.studentSignatureImageDirURLPath=Configuration.readConfig(Configuration.configFilePath, "studentSignatureImageDirURLPath");

        Configuration.officialInvoiceURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInvoiceURLPath");
        Configuration.officialReceiptURLPath=Configuration.readConfig(Configuration.configFilePath, "officialReceiptURLPath");
        Configuration.officialVoucherURLPath=Configuration.readConfig(Configuration.configFilePath, "officialVoucherURLPath");
        Configuration.profileHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "profileHeaderImageURLPath");
        Configuration.officialCashReceiptHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialCashReceiptHeaderImageURLPath");
        Configuration.officialInvoiceHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInvoiceHeaderImageURLPath");
        Configuration.officialCashVoucherHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialCashVoucherHeaderImageURLPath");
        Configuration.officialInventoryReceiptHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInventoryReceiptHeaderImageURLPath");
        Configuration.officialInventoryVoucherHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInventoryVoucherHeaderImageURLPath");
        Configuration.officialAdjustmentReceiptHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialAdjustmentReceiptHeaderImageURLPath");
        Configuration.officialAdjustmentVoucherHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialAdjustmentVoucherHeaderImageURLPath");

        Configuration.officialCashReceiptFooterImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialCashReceiptFooterImageURLPath");
        Configuration.officialCashVoucherFooterImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialCashVoucherFooterImageURLPath");
        Configuration.officialInvoiceFooterImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInvoiceFooterImageURLPath");
        Configuration.officialSignedInvoiceFooterImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialSignedInvoiceFooterImageURLPath");
        Configuration.officialInventoryReceiptFooterImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInventoryReceiptFooterImageURLPath");
        Configuration.officialInventoryVoucherFooterImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInventoryVoucherFooterImageURLPath");
        Configuration.officialAdjustmentReceiptFooterImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialAdjustmentReceiptFooterImageURLPath");
        Configuration.officialAdjustmentVoucherFooterImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialAdjustmentVoucherFooterImageURLPath");

        Configuration.officialCashSubReceiptHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialCashSubReceiptHeaderImageURLPath");
        Configuration.officialCashSubVoucherHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialCashSubVoucherHeaderImageURLPath");
        Configuration.officialInventorySubReceiptHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInventorySubReceiptHeaderImageURLPath");
        Configuration.officialInventorySubVoucherHeaderImageURLPath=Configuration.readConfig(Configuration.configFilePath, "officialInventorySubVoucherHeaderImageURLPath");
        officialInvoiceFooterImagePath2=officialInvoiceFooterImagePath;
        officialInvoiceFooterImageURLPath2=officialInvoiceFooterImageURLPath;
    }
}
