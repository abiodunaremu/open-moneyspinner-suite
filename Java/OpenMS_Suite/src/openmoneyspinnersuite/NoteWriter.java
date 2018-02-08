
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

import java.io.*;
import java.sql.*;
import java.util.*;
import java.text.*;

class NoteWriter {

    static FileWriter writer;
    static File file;
    static Systems systems=new Systems();
    static NoteWriterExtension noteX=new NoteWriterExtension();
    static NoteWriterJournal nwj=new NoteWriterJournal();
    static NoteWriterJornalDebit nwjd=new NoteWriterJornalDebit();
    static PreparedStatement statement;
    static ResultSet result;
    static ResultSet resultRow;
    static ResultSet resultValue;
    static String creditBy;
    static String creditByID;
    static String creditByTable;
    static String debitBy;
    static String debitByID;
    static String orderBy;
    static String orderByID;
    static String orderDate;
    static String totalAmount;
    static String toBalance;
    static String amtDue;
    static String postDate;
    static String creditCode;
    static String debitCode;
    static String[] description;
    static String[] debitingDate;
    static String[] debitNumber;
    static String[] creditingDate;
    static String[] creditNumber;
    static String[] transactionType;
    static String[] serial;
    static String[] amount;
    static String[] item;

    static String accountCode;
    static String costOrderCode;
    static String transactionDate;
    static String transactionNumber;
    static String transactionTypeCode;
    static String transactionType_B;
    static String accountCurrency;
    static String netAmount;
    static String initialTobalance;
    static String currentTobalance;

    static String[] statementOrderCode={};
    static String[] statementOrderDate={};
    static String[] statementCreditDebitCode={};
    static String[] statementCreditCode={};
    static String[] statementDebitCode={};
    static String[] statementAccountCode={};
    static String[] statementMerchantID={};
    static String[] statementOrderByCode={};
    static String[] statementConsentByCode={};
    static String[] statementCreditAmount={};
    static String[] statementDebitAmount={};
    static String[] statementAccountBalance={};
    static String[] statementToBalance={};
    static String[] statementAmtDue={};
    static String[] statementTotalAmount={};
    static String[] statementUnit={};

    static String[] statementTransactionTypeCode={};
    static String[] statementTransactionSerialNumber={};
    static java.sql.Date[] statementTransactionDate={};

    static String[] statementSumAccountCode={};
    static String[] statementSumAccountName={};
    static String[] statementSumAccountCurrency={};
    static String[] statementSumDistinctAccountCurrency={};
    static String[] statementSumAccountCategory={};
    static String[] statementSumAccountTradingType={};
    static String[] statementSumCredit={};
    static String[] statementSumDebit={};
    static String[] statementSumBalance={};
    
    public NoteWriter(){}

    public void writeReceipt(String orderCode,String transferState,int quantity)
    {
        try
        {
            //file=new java.io.File("C:/Milliscript_MoneySpinner/Note/officialReceipt.htm");
            file=new java.io.File(Configuration.officialReceiptPath);
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);
            noteX.writeSingleReceipt(orderCode,transferState);
            if(quantity==2){
                noteX.writeSingleReceipt(orderCode,transferState);
            }
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeReciept gave error! "+e);
        }
        resetVariable();
    }

    public void writeInvoice(String orderCode,String transferState,int quantity)
    {
        try
        {
            //file=new java.io.File("C:/Milliscript_MoneySpinner/Note/officialReceipt.htm");
            file=new java.io.File(Configuration.officialInvoicePath);
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);
            noteX.writeSingleInvoice(orderCode,transferState);
            if(quantity==2){
                noteX.writeSingleInvoice(orderCode,transferState);
            }
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeInvoice gave error! "+e);
        }
        resetVariable();
    }
    public void writeVoucher(String orderCode,String transferState,int quantity)
    {
        try
        {
            //file=new java.io.File("C:/Milliscript_MoneySpinner/Note/officialVoucher.htm");
            file=new java.io.File(Configuration.officialVoucherPath);
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);
            noteX.writeSingleVoucher(orderCode, transferState);
            if(quantity==2){
                noteX.writeSingleVoucher(orderCode,transferState);
            }
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeVoucher gave error! "+e);
        }

        resetVariable();
    }
    public String getAccountValue(String accountCategory)
    {
        String accountValue="ASSET";
        if(accountCategory.equalsIgnoreCase("Payable")||accountCategory.equalsIgnoreCase("Inventory Payable"))
        {
            accountValue="LIABILITY";
        }
        if(accountCategory.equalsIgnoreCase("Revenue")||accountCategory.equalsIgnoreCase("Expense")||accountCategory.equalsIgnoreCase("Hybrid"))
        {
            accountValue="N/A";
        }
        return accountValue;
    }
    public void writeAccountStatement(String statementType,String accountName,String accountUnit,String merchantID,String activity,String transferActivityState,String startDate,String endDate,boolean statementOnly)
    {
        String statementMySQLAccountColumn="2";
        String tranferActivityComment="";
        try
        {
            file=new java.io.File(Configuration.accountStatementNotePath);
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
            writer.write("<title>Financial Statement</title>\n");

    writer.write("<style type=\"text/css\">\n");
    writer.write("<!--\n");
    writer.write("table#finstatement {\n");
    writer.write("	text-align: left;\n");
    writer.write("	font: 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
    writer.write("}\n");
    writer.write("table#finstatement th {\n");
    writer.write("	color: #FFF;\n");
    writer.write("	background: #003C00;\n");
    writer.write("	border: 1px solid #FFF;\n");
    writer.write("	padding: 5px;\n");
    writer.write("	margin: 0px;\n");
    writer.write("	text-align: left;\n");
    writer.write("	font-family: \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
    writer.write("}\n");
    writer.write("table#finstatement td {\n");
    writer.write("	border: 1px solid #FFF;\n");
    writer.write("	border-collapse: collapse;\n");
    writer.write("	padding: 5px;\n");
    writer.write("	font-size: 12px;\n");
    writer.write("}\n");
    writer.write("table#finstatement tr:hover {\n");
    writer.write("	background: #060;\n");
    writer.write("	color: #FFF;\n");
    writer.write("}\n");
    writer.write("#orderCode, #account, #orderBy, #creditAmount, #balance {\n");
    writer.write("	background: #EEE753;\n");
    writer.write("}\n");
    writer.write("#creditDebitCode, #merchant, #consentBy, #debitAmount, #unit {\n");
    writer.write("	background: #A7FEF1;\n");
    writer.write("}\n");


        writer.write("table #journal {\n");
        writer.write("	font-family: Verdana, Geneva, sans-serif;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	border-collapse: collapse;\n");
        writer.write("}\n");
        writer.write("#journal tr th {\n");
        writer.write("	color: #FFF;\n");
        writer.write("	background: #003C00;\n");
        writer.write("	font-family: Georgia, \"Times New Roman\", Times, serif;\n");
        writer.write("	text-align: left;\n");
        writer.write("	padding-left: 5px;\n");
        writer.write("	font-weight: normal;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	font: normal 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
        writer.write("}\n");
        writer.write("#journal tr td {\n");
        writer.write("	padding-left: 5px;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	background: #FF9;\n");
        writer.write("	font: normal 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
        writer.write("}\n");
        writer.write("#journal tr th.demacate {\n");
        writer.write("	background: #090;\n");
        writer.write("	color: #FF0;\n");
        writer.write("}\n");

//
//        writer.write("table#reportSummary {\n");
//        writer.write("	font-family: Verdana, Geneva, sans-serif;\n");
//        writer.write("	border: 1px solid #FFF;\n");
//        writer.write("	border-collapse: collapse;\n");
//        writer.write("}\n");
//        writer.write("#reportSummary tr th {\n");
//        writer.write("	color: #FFF;\n");
//        writer.write("	background: #003C00;\n");
//        writer.write("	font-family: Georgia, \"Times New Roman\", Times, serif;\n");
//        writer.write("	text-align: left;\n");
//        writer.write("	padding-left: 5px;\n");
//        writer.write("	font-weight: normal;\n");
//        writer.write("	border: 1px solid #FFF;\n");
//        writer.write("	font: normal 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
//        writer.write("}\n");
//        writer.write("#reportSummary tr td {\n");
//        writer.write("	padding-left: 5px;\n");
//        writer.write("	border: 1px solid #FFF;\n");
//        writer.write("	background: #FF9;\n");
//        writer.write("	font: normal 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
//        writer.write("}\n");
//        writer.write("table#reportSummary tr:hover {\n");
//        writer.write("	background: #060;\n");
//        writer.write("	color: #FFF;\n");
//        writer.write("}\n");
//        writer.write("#reportSummary tr th.demacate {\n");
//        writer.write("	background: #090;\n");
//        writer.write("	color: #FF0;\n");
//        writer.write("}\n");

    writer.write("-->\n");
    writer.write("</style>\n");
            writer.write("</head>\n");

            writer.write("<body>\n");
            writer.write("<SCRIPT LANGUAGE=\"JavaScript\">\n");
            writer.write("window.print();\n");
            writer.write("</script>\n");
            writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
            writer.write("  <tr>\n");
            if(transferActivityState.isEmpty())
            {
            writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.statementOfCashFlowsHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"statementOfAccount\" /></div></td>\n");
            }
            else
            {
            writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.incomeStatementHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"statementOfAccount\" /></div></td>\n");

            }

            writer.write("  </tr>\n");
            writer.write("</table>\n");
            if(transferActivityState.isEmpty()==false)
            {
                tranferActivityComment=" (TRANSFER ACTIVITY EXCLUDED) ";
            }
            
            if(statementType.equalsIgnoreCase("FULL_N"))
            {
                
             String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 7,10,3,4,9,8,12,13,14,6);

//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+" order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 6);

             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];
             
                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">FULL REPORT"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">**</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\"><a href=\" #summary \" name=#top>Summary </a> | <a href=\" "+Configuration.incomeStatementNoteURLPath+" \">Income Statement (P&L)</a> | <a href=\" "+Configuration.balanceSheetNoteURLPath+" \">Scale of Balance (Balance Sheet)</a> | <a href=\" "+
                            Configuration.cashFlowsNoteURLPath+" \">Statement of Cash Flows</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");
                
                if(statementOnly==true)
                {                
                    
                    writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
                    writer.write("  <tr>\n");
                    writer.write("    <td width=\"676\">&nbsp;</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <td><div align=\"center\"><img src=\"statement_Footer.png\" alt=\"statementFooter\" /></div></td>\n");
                    writer.write("  </tr>\n");
                    writer.write("</table>\n");
                    writer.write("</body>\n");
                    writer.write("</html>\n");
                    writer.flush();
                    writer.close();

                    noteX.writeIncomeStatement( statementType, accountName, accountUnit, merchantID, activity, " and cActivity != 'Transfer Activity' ", startDate, endDate);

                    noteX.writeBalanceSheet( statementType, accountName, accountUnit, merchantID, activity, "", startDate, endDate);

                    noteX.writeCashFlowStatement(statementType, accountName, accountUnit, merchantID, activity,  " and cActivity != 'Transfer Activity' ", startDate, endDate);

                    resetVariable();
                    return;     
                }
                
                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");

        writer.write("<colgroup>\n");
        writer.write("<col id=\"orderDate\"/>\n");
        writer.write("<col id=\"orderCode\"/>\n");
        writer.write("<col id=\"creditDebitCode\"/>\n");
        writer.write("<col id=\"account\"/>\n");
        writer.write("<col id=\"merchant\"/>\n");
        writer.write("<col id=\"orderBy\"/>\n");
        writer.write("<col id=\"consentBy\"/>\n");
        writer.write("<col id=\"creditAmount\"/>\n");
        writer.write("<col id=\"debitAmount\"/>\n");
        writer.write("<col id=\"balance\"/>\n");
        writer.write("<col id=\"unit\"/>\n");
        writer.write("</colgroup>\n");

        writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");
                
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");
               
            } 
                
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select cEmployeeID from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select cEmployeeID from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select vFirstName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select vMiddleName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select vlastName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select vFirstName from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select vMiddleName from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select vlastName from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select vTable from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select vFirstName from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select vMiddleName from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select vlastName from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }

                writer.write("</tbody>\n");
                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong>&nbsp;</strong></th>\n");//ACCOUNT NAME
                            
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\" scope=\"col\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\" scope=\"col\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"204\" scope=\"col\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\" scope=\"col\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\" scope=\"col\"><strong>TOTAL REVENUES</strong></th>\n");
               
            }
                writer.write("    <th width=\"150\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\" scope=\"col\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else
            {
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
                      
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_Y"))
            {
             String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 7,10,3,4,9,8,12,13,14,6);

//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 6);

             
             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];


                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">FULL REPORT"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\"><a href=\" #summary \" name=#top>Summary </a> | <a href=\" "+Configuration.incomeStatementNoteURLPath+" \">Income Statement (P&L)</a> | <a href=\" "+Configuration.balanceSheetNoteURLPath+" \">Scale of Balance (Balance Sheet)</a> | <a href=\" "+
                        Configuration.cashFlowsNoteURLPath+" \">Statement of Cash Flows</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                if(statementOnly==true)
                {
                    
                    writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
                    writer.write("  <tr>\n");
                    writer.write("    <td width=\"676\">&nbsp;</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <td><div align=\"center\"><img src=\"statement_Footer.png\" alt=\"statementFooter\" /></div></td>\n");
                    writer.write("  </tr>\n");
                    writer.write("</table>\n");
                    writer.write("</body>\n");
                    writer.write("</html>\n");
                    writer.flush();
                    writer.close();

                    noteX.writeIncomeStatement( statementType, accountName, accountUnit, merchantID, activity, " and cActivity != 'Transfer Activity' ", startDate, endDate);

                    noteX.writeBalanceSheet( statementType, accountName, accountUnit, merchantID, activity, "", startDate, endDate);

                    noteX.writeCashFlowStatement(statementType, accountName, accountUnit, merchantID, activity,  " and cActivity != 'Transfer Activity' ", startDate, endDate);

                    resetVariable();
                    return;     
                }
                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");

            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("  <tr>\n");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");            
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");
               
            } 
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                
            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                
                writer.write("</tbody>\n");
                writer.write("</table>\n");
                String demacate="N";
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\" "+setDemacateSyntax(demacate)+ "><strong></strong></td>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"225\" "+setDemacateSyntax(demacate)+ "><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>TOTAL REVENUES</strong></th>\n");
            }
                writer.write("    <th width=\"150\" "+setDemacateSyntax(demacate)+ "><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\" "+setDemacateSyntax(demacate)+ "><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");                
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");

            }

            if(statementType.equalsIgnoreCase("ACCOUNT_N"))
            {
             accountCode=systems.getValue(OpenMSApp.Database_A, "Select * from Account WHERE vAccountName='"+accountName+"'", "cAccountCode");
             
             String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+" order by dOrderDate", 7,10,3,4,9,8,12,13,14,6);
//             
//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+" order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+" order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' "+transferActivityState+"  order by dOrderDate", 6);

             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];

                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">SINGLE ACCOUNT REPORT OF "+accountName+" ("+accountUnit+")"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">**</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\">&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1074\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></td>\n");            
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");
               
            } 
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");
                writer.write("</table>\n");
                
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"360\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
               
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
               // statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
               // statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                String statementAccountName=systems.getValue(OpenMSApp.Database_A, "Select * from Account Where cAccountCode='"+accountCode+"'", 2);
                //int xs=0;
                //int ys=statementSumAccountName.length;
                String demacate="N";
               // while(xs<ys)
               // {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder  Where cAccountCode='"+accountCode+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  Where cAccountCode='"+accountCode+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                            statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementAccountName+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementAccountName+"'", 5)+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementAccountName+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementAccountName+"'", 10))+"</td>\n");
            }

                    writer.write("  </tr>\n");
                    writer.write("</table>\n");
                   // xs+=1;
                    //if(xs==ys){break;}
                //}
            }
            if(statementType.equalsIgnoreCase("ACCOUNT_Y"))
            {
             accountCode=systems.getValue(OpenMSApp.Database_A, "Select * from Account WHERE vAccountName='"+accountName+"'", "cAccountCode");
             
             String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 7,10,3,4,9,8,12,13,14,6);

//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder  on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where pay.cAccountCode='"+accountCode+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 6);

             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];

                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1074\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">SINGLE ACCOUNT REPORT OF "+accountName+" ("+accountUnit+")"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">"+startDate+" to "+endDate+"</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\">&nbsp;</th>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");            
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");
               
            } 
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");

                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"360\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>BALANCE</strong></th>\n");
            
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
               
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                String demacate="N";
                demacate=changeDemacate(demacate);
                String statementAccountName=systems.getValue(OpenMSApp.Database_A, "Select * from Account Where cAccountCode='"+accountCode+"'", 2);

                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder  Where cAccountCode='"+accountCode+"' and cStatus='posted' AND dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  Where cAccountCode='"+accountCode+"' and cStatus='posted' AND dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementAccountName+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementAccountName+"'", 5)+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementAccountName+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementAccountName+"'", 10))+"</td>\n");
            }
                    writer.write("  </tr>\n");
                    writer.write("</table>\n");
            }
            if(statementType.equalsIgnoreCase("MERCHANT_N"))
            {
             String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditorder.cMerchantID='"+merchantID+"'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 7,10,3,4,9,8,12,13,14,6);
//
//            statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditorder.cMerchantID='"+merchantID+"'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cMerchantID='"+merchantID+"'  "+transferActivityState+" order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cMerchantID='"+merchantID+"' "+transferActivityState+"  order by dOrderDate", 6);

            statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];

                String merchantName="";
                String creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                merchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vlastName").trim();
                }
                else
                {
                    merchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vName");
                }
                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">SINGLE MERCHANT REPORT OF "+merchantName+""+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">**</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\">&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");

                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                //writer.write("    <td width=\"285\"><strong>BALANCE</strong></td>\n");
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                String demacate="N";
                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder Where cMerchantID='"+merchantID+"' and cStatus='posted' and cAccountCode='"+statementSumAccountCode[xs]+"' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder Where cMerchantID='"+merchantID+"' and cStatus='posted' and cAccountCode='"+statementSumAccountCode[xs]+"' "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    //writer.write("    <td>"+statementBalance+"</td>\n");
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                    //writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("MERCHANT_Y"))
            {
             String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 7,10,3,4,9,8,12,13,14,6);

//            statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cMerchantID='"+merchantID+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 6);

             

            statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];
             
             statementAccountBalance=statementRecord[9];

                String merchantName="";
                String creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                merchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vlastName").trim();
                }
                else
                {
                    merchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vName");
                }
                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">SINGLE MERCHANT REPORT OF "+merchantName+""+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\">&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1074\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");

                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"360\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                //writer.write("    <td width=\"285\"><strong>BALANCE</strong></td>\n");
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder Where cMerchantID='"+merchantID+"' and cStatus='posted' and cAccountCode='"+statementSumAccountCode[xs]+"' AND dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"  ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder Where cMerchantID='"+merchantID+"' and cStatus='posted' and cAccountCode='"+statementSumAccountCode[xs]+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"  ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    //writer.write("    <td>"+statementBalance+"</td>\n");
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                    //writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }
//----begin of checking for activity

            if(statementType.equalsIgnoreCase("ACTIVITY_N"))
            {
              String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditorder.cActivity='"+activity+"'   "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cActivity='"+activity+"'   "+transferActivityState+"  order by dOrderDate", 7,10,3,4,9,8,12,13,14,6);
//            statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditorder.cActivity='"+activity+"'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cActivity='"+activity+"'   "+transferActivityState+"  order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cActivity='"+activity+"'   "+transferActivityState+"  order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cActivity='"+activity+"'   "+transferActivityState+"  order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'   "+transferActivityState+"   "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitorder.cActivity='"+activity+"'   "+transferActivityState+"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cActivity='"+activity+"'   "+transferActivityState+"   order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'  "+transferActivityState+"   "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cActivity='"+activity+"'  "+transferActivityState+"   order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'  "+transferActivityState+"   "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cActivity='"+activity+"'  "+transferActivityState+"   order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'  "+transferActivityState+"   "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cActivity='"+activity+"'   "+transferActivityState+"  order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cActivity='"+activity+"'  "+transferActivityState+"   order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditorder.cActivity='"+activity+"'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode Where debitorder.cActivity='"+activity+"'   "+transferActivityState+"  order by dOrderDate", 6);


            statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];
             
                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">SINGLE CASH FLOW ACTIVITY REPORT OF "+activity+""+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">**</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\">&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1074\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

        writer.write("  </thead>\n");
        writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
//                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
//                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                
                String statementAccountValue[]=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit","vAccountName");
                
                String statementAccountCurrency=statementAccountValue[0];
                String statementAccountName=statementAccountValue[1];
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");

                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"360\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                //writer.write("    <td width=\"285\"><strong>BALANCE</strong></td>\n");
                writer.write("    <th width=\"204\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

//                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
//                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                String statemenActCodeName[][]=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1,2);
        
                statementSumAccountCode=statemenActCodeName[0];
                statementSumAccountName=statemenActCodeName[1];

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder Where cActivity='"+activity+"' and cStatus='posted' and cAccountCode='"+statementSumAccountCode[xs]+"'  "+transferActivityState+"  ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder Where cActivity='"+activity+"' and cStatus='posted' and cAccountCode='"+statementSumAccountCode[xs]+"'  "+transferActivityState+"  ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    //writer.write("    <td>"+statementBalance+"</td>\n");
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                    //writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("ACTIVITY_Y"))
            {
              String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   order by dOrderDate", 7,10,3,4,9,8,12,13,14,6);

//            statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  Where debitOrder.cactivity='"+activity+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  order by dOrderDate", 6);

            statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];

                String merchantName="";
                String creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                merchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vlastName").trim();
                }
                else
                {
                    merchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vName");
                }
                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
            
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">SINGLE CASH FLOW ACTIVITY REPORT OF "+activity+""+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\">&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
                writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");

                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"360\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
            }
                //writer.write("    <td width=\"285\"><strong>BALANCE</strong></td>\n");
                writer.write("    <th width=\"204\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder Where cactivity='"+activity+"' and cStatus='posted' and cAccountCode='"+statementSumAccountCode[xs]+"' AND dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"  ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder Where cactivity='"+activity+"' and cStatus='posted' and cAccountCode='"+statementSumAccountCode[xs]+"' AND debitOrder.dOrderDate BETWEEN '"+startDate+"  00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"  ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    //writer.write("    <td>"+statementBalance+"</td>\n");
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                    //writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

//----end of checking for activity
//----begin full group sorting

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_N"))
            {
              String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 7,10,3,4,9,8,12,13,14,6);
//
//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 6);


             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];

                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">FULL, GROUPING ENTITY::MERCHANT"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">**</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\"><a href=\" #summary \" name=#top>Summary </a> | <a href=\" "+Configuration.incomeStatementNoteURLPath+" \">Income Statement (P&L)</a> | <a href=\" "+Configuration.balanceSheetNoteURLPath+" \">Scale of Balance (Balance Sheet)</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }

                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }

                writer.write("</tbody>\n");
                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");

            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");

            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_Y"))
            {
              String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 7,10,3,4,9,8,12,13,14,6);

//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by cMerchantID", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 6);

             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];

                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">FULL, GROUPING ENTITY::MERCHANT"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></td>");
                writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\"><a href=\" #summary \" name=#top>Summary </a> | <a href=\" "+Configuration.incomeStatementNoteURLPath+" \">Income Statement (P&L)</a> | <a href=\" "+Configuration.balanceSheetNoteURLPath+" \">Scale of Balance (Balance Sheet)</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
        writer.write("<colgroup>\n");
        writer.write("<col id=\"orderDate\"/>\n");
        writer.write("<col id=\"orderCode\"/>\n");
        writer.write("<col id=\"creditDebitCode\"/>\n");
        writer.write("<col id=\"account\"/>\n");
        writer.write("<col id=\"merchant\"/>\n");
        writer.write("<col id=\"orderBy\"/>\n");
        writer.write("<col id=\"consentBy\"/>\n");
        writer.write("<col id=\"creditAmount\"/>\n");
        writer.write("<col id=\"debitAmount\"/>\n");
        writer.write("<col id=\"balance\"/>\n");
        writer.write("<col id=\"unit\"/>\n");
        writer.write("</colgroup>\n");

        writer.write("<thead>\n");
                writer.write("  <tr>\n");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

        writer.write("  </thead>\n");
        writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;
                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th >"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");

                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"225\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");

            }

                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");

            }

            if(statementType.equalsIgnoreCase("FULL_ACCOUNT_N"))
            {
              String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 7,10,3,4,9,8,12,13,14,6);

//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 6);


             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];

                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></td>");
                writer.write("    <td width=\"642\">FULL, GROUPING ENTITY::ACCOUNT"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></td>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></td>");
                writer.write("    <td colspan=\"3\">**</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\"><a href=\" #summary \" name=#top>Summary </a> | <a href=\" "+Configuration.incomeStatementNoteURLPath+" \">Income Statement (P&L)</a> | <a href=\" "+Configuration.balanceSheetNoteURLPath+" \">Scale of Balance (Balance Sheet)</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }

                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }

                writer.write("</tbody>\n");
                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
                   
            if(transferActivityState.isEmpty())
            {
                writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");                        
            }

                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_ACCOUNT_Y"))
            {
              String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 7,10,3,4,9,8,12,13,14,6);

//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 6);

             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];
             
                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">FULL, GROUPING ENTITY::ACCOUNT"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\"><a href=\" #summary \" name=#top>Summary </a> | <a href=\" "+Configuration.incomeStatementNoteURLPath+" \">Income Statement (P&L)</a> | <a href=\" "+Configuration.balanceSheetNoteURLPath+" \">Scale of Balance (Balance Sheet)</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("  <tr>\n");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");

                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"225\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            }

                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");

            }

            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_N"))
            {
              String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 7,10,3,4,9,8,12,13,14,6);

//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 6);

             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];
             
                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">FULL, GROUPING ENTITY::CASH FLOWS ACTIVITY"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">**</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\"><a href=\" #summary \" name=#top>Summary </a> | <a href=\" "+Configuration.incomeStatementNoteURLPath+" \">Income Statement (P&L)</a> | <a href=\" "+Configuration.balanceSheetNoteURLPath+" \">Scale of Balance (Balance Sheet)</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
            writer.write("<colgroup>\n");
            writer.write("<col id=\"orderDate\"/>\n");
            writer.write("<col id=\"orderCode\"/>\n");
            writer.write("<col id=\"creditDebitCode\"/>\n");
            writer.write("<col id=\"account\"/>\n");
            writer.write("<col id=\"merchant\"/>\n");
            writer.write("<col id=\"orderBy\"/>\n");
            writer.write("<col id=\"consentBy\"/>\n");
            writer.write("<col id=\"creditAmount\"/>\n");
            writer.write("<col id=\"debitAmount\"/>\n");
            writer.write("<col id=\"balance\"/>\n");
            writer.write("<col id=\"unit\"/>\n");
            writer.write("</colgroup>\n");

            writer.write("<thead>\n");
                writer.write("<tr>");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }

                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");

            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }

                writer.write("</tbody>\n");
                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");

            }

                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }
            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_Y"))
            {
              String statementRecord[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 7,10,3,4,9,8,12,13,14,6);

//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by cActivity", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
//" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 6);

             statementOrderCode=statementRecord[0];

             statementCreditDebitCode=statementRecord[1];

             statementCreditCode=statementRecord[2];

             statementDebitCode=statementRecord[3];

             statementAccountCode=statementRecord[4];

             statementMerchantID=statementRecord[5];

             statementOrderByCode=statementRecord[6];

             statementOrderDate=statementRecord[7];

             statementConsentByCode=statementRecord[8];

             statementAccountBalance=statementRecord[9];
             
                java.util.Calendar calendar=java.util.Calendar.getInstance();
                java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">");
                writer.write("  <tr>");
                writer.write("    <th width=\"72\"><strong>SORT: </strong></th>");
                writer.write("    <td width=\"642\">FULL, GROUPING ENTITY:: CASH FLOWS ACTIVITY"+tranferActivityComment+"</td>");
                writer.write("    <th width=\"53\"><strong>DATE:</strong></th>");
                writer.write("    <td width=\"128\">"+formater.format(calendar.getTime())+"</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <th><strong>PERIOD:</strong></th>");
                writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("  <tr>");
                writer.write("    <td colspan=\"4\"><a href=\" #summary \" name=#top>Summary </a> | <a href=\" "+Configuration.incomeStatementNoteURLPath+" \">Income Statement (P&L)</a> | <a href=\" "+Configuration.balanceSheetNoteURLPath+" \">Scale of Balance (Balance Sheet)</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"finstatement\">");
        writer.write("<colgroup>\n");
        writer.write("<col id=\"orderDate\"/>\n");
        writer.write("<col id=\"orderCode\"/>\n");
        writer.write("<col id=\"creditDebitCode\"/>\n");
        writer.write("<col id=\"account\"/>\n");
        writer.write("<col id=\"merchant\"/>\n");
        writer.write("<col id=\"orderBy\"/>\n");
        writer.write("<col id=\"consentBy\"/>\n");
        writer.write("<col id=\"creditAmount\"/>\n");
        writer.write("<col id=\"debitAmount\"/>\n");
        writer.write("<col id=\"balance\"/>\n");
        writer.write("<col id=\"unit\"/>\n");
        writer.write("</colgroup>\n");

        writer.write("<thead>\n");
                writer.write("  <tr>\n");
                writer.write("    <th width=\"103\" scope=\"col\"><strong></strong></th>\n");//DATE
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ORDER CODE</strong></th>\n");
                writer.write("    <th width=\"107\" scope=\"col\"><strong>CREDIT/DEBIT CODE</strong></th>\n");
                writer.write("    <th width=\"97\" scope=\"col\"><strong>ACCOUNT NAME</strong></th>\n");
                writer.write("    <th width=\"95\" scope=\"col\"><strong>TRANSACTION BY</strong></th>\n");
                writer.write("    <th width=\"70\" scope=\"col\"><strong>ORDER BY</strong></th>\n");
                writer.write("    <th width=\"94\" scope=\"col\"><strong>CONSENT BY</strong></th>\n");
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>DEBITS</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"78\" scope=\"col\"><strong>EXPENSES</strong></th>\n");
                writer.write("    <th width=\"76\" scope=\"col\"><strong>REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"78\" scope=\"col\"><strong>ACCOUNT BALANCE</strong></th>\n");
                writer.write("    <th width=\"46\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
                writer.write("  </tr>\n");
            writer.write("  </thead>\n");
            writer.write(" <tbody>\n");
                int x=0;
                int y=statementOrderCode.length;

                while(x<y)
                {
                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String ConsentEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementConsentByCode[x]+"'","cEmployeeID");

                String statementConsentBy="";
                statementOrderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

                statementConsentBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+ConsentEmployeeID+"'","vlastName").trim();

                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+statementMerchantID[x]+"'","vlastName").trim();
                }
                else
                {
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                if(statementCreditCode[x]==null&&statementDebitCode[x]!=null)
                {
                    creditAmount="N/A";
                    try
                    {
                        debitAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cDebitCode='"+statementDebitCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        debitAmount="Er!M1C01K0";
                    }
                }
                if(statementCreditCode[x]!=null&&statementDebitCode[x]==null)
                {
                    debitAmount="N/A";
                    try
                    {
                        creditAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from Pay where cCreditCode='"+statementCreditCode[x]+"'", "mAmount"));
                    }
                    catch(Exception e)
                    {
                        creditAmount="Er!M1C01K0";
                    }
                }
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");
                writer.write("  <tr>\n");
                writer.write("    <th scope=\"row\">"+systems.extractDate(statementOrderDate[x])+"</th>\n");
                writer.write("    <td>"+statementOrderCode[x]+"</td>\n");
                writer.write("    <td>"+statementCreditDebitCode[x]+"</td>\n");
                writer.write("    <td>"+statementAccountName+"</td>\n");
                writer.write("    <td>"+statementMerchantName+"</td>\n");
                writer.write("    <td>"+statementOrderBy+"</td>\n");
                writer.write("    <td>"+statementConsentBy+"</td>\n");
                writer.write("    <td>"+debitAmount+"</td>\n");
                writer.write("    <td>"+creditAmount+"</td>\n");
                writer.write("    <td>"+statementAccountBalance[x]+"</td>\n");
                writer.write("    <td>"+statementAccountCurrency+"</td>\n");
                writer.write("  </tr>\n");
                    x+=1;
                    if(x==y){break;}
                }
                writer.write("</tbody>\n");

                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"225\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormat(statementTotalCredit);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalCredit="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty()){
                            statementTotalDebit="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalDebit);
                        statementTotalDebit=systems.converToRealMoneyFormat(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                   // writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
            }
            else{
                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");

            }
//-----end full group sorting

            writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
            writer.write("  <tr>\n");
            writer.write("    <td width=\"676\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td><div align=\"center\"><img src=\"statement_Footer.png\" alt=\"statementFooter\" /></div></td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeCreditStatement gave error! "+e);
            e.printStackTrace();
        }
        
    noteX.writeIncomeStatement( statementType, accountName, accountUnit, merchantID, activity, " and cActivity != 'Transfer Activity' ", startDate, endDate);
    
    noteX.writeBalanceSheet( statementType, accountName, accountUnit, merchantID, activity, "", startDate, endDate);
    
    noteX.writeCashFlowStatement(statementType, accountName, accountUnit, merchantID, activity,  " and cActivity != 'Transfer Activity' ", startDate, endDate);

    resetVariable();
    }
    public String setDemacateSyntax(String demacate)
    {
        String demacateSyntax="";
        if(demacate.equalsIgnoreCase("Y"))
        {
            demacateSyntax="class=\"demacate\"";
        }
        return demacateSyntax;
    }
    public String changeDemacate(String demacate)
    {
        if(demacate.equalsIgnoreCase("Y"))
        {
            demacate="N";
        }
        else{
            demacate="Y";
        }
        return demacate;
    }
    public void writeCreditStatement(String statementType,String accountName,String accountUnit,String merchantID,String activity,String itemName,String transferActivityState,String startDate,String endDate)
    {
    nwj.writeCreditStatementJournal(statementType, accountName,accountUnit, merchantID, activity, itemName, transferActivityState, startDate, endDate);
    }

    public void writeDebitStatement(String statementType,String accountName,String accountUnit,String merchantID,String activity,String itemName,String transferActivityState,String startDate,String endDate)
    {
        nwjd.writeDebitStatement(statementType,accountName,accountUnit,merchantID,activity,itemName,transferActivityState,startDate,endDate);
        
    }
    public void writeMyBackup(String filePath)
    {
          try
            {
            file=new java.io.File(filePath);
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);

            writer.write("mysqldump milliscriptmoneyspinnerasxtrial -u "+OpenMSApp.SQL_U+" --password="+OpenMSApp.SQL_P+" 1> "+Configuration.backupDirPath+"ok.mis");

            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeMyBackup gave error! "+e);
        }
    }
    public void resetVariable()
    {
        creditBy="";
        creditByID="";
        debitBy="";
        debitByID="";
        orderBy="";
        orderByID="";
        orderDate="";
        totalAmount="";
        postDate="";
        creditCode="";
        debitCode="";
        description=new String[]{};
        debitingDate=new String[]{};
        debitNumber=new String[]{};
        creditingDate=new String[]{};
        creditNumber=new String[]{};
        transactionType=new String[]{};
        serial=new String[]{};
        amount=new String[]{};
        item=new String[]{};

        accountCode="";
        transactionDate="";
        transactionNumber="";
        transactionTypeCode="";
        transactionType_B="";
        accountCurrency="";

        statementOrderCode=new String[]{};
        statementOrderDate=new String[]{};
        statementCreditDebitCode=new String[]{};
        statementCreditCode=new String[]{};
        statementDebitCode=new String[]{};
        statementAccountCode=new String[]{};
        statementMerchantID=new String[]{};
        statementOrderByCode=new String[]{};
        statementConsentByCode=new String[]{};
        statementCreditAmount=new String[]{};
        statementDebitAmount=new String[]{};
        statementAccountBalance=new String[]{};
        statementUnit=new String[]{};
        statementTotalAmount=new String[]{};

        statementTransactionTypeCode=new String[]{};
        statementTransactionSerialNumber=new String[]{};
        statementTransactionDate=new java.sql.Date[]{};

        statementSumAccountName=new String[]{};
        statementSumAccountCode=new String[]{};
        statementSumAccountCurrency=new String[]{};
        statementSumDistinctAccountCurrency=new String[]{};
        statementSumAccountCategory=new String[]{};
        statementSumAccountTradingType=new String[]{};
        statementSumCredit=new String[]{};
        statementSumDebit=new String[]{};
        statementSumBalance=new String[]{};
    }
}
 
