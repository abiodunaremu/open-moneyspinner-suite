

// Open MoneySpinner Suite v1
// An open source business management software system written in Java and MySQL
// Recommended IDE is NetBeans IDE 7.0.1
// Support Web Site: http://www.milliscript.com
//
// Copyright (C) 2010-2014, Abiodun Aremu, Ibadan/NIGERIA.
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
public class NoteWriterJournal extends NoteWriter{
    
    public void writeCreditStatementJournal(String statementType,String accountName,String accountUnit,String merchantID,String activity,String itemName,String transferActivityState,String startDate,String endDate)
    {
        String tranferActivityComment="";
            String itemCode=systems.getValue(OpenMSApp.Database_A, "Select * from item where vName='"+itemName+"'", 1);
        try
        {
            file=new java.io.File(Configuration.statementOfCashInflowNotePath);
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
            writer.write("<title>Credit Journal</title>\n");
        writer.write("<style type=\"text/css\">\n");
        writer.write("<!--\n");
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
        writer.write("-->\n");
        writer.write("</style>\n");
            writer.write("</head>\n");
            writer.write("<body>\n");
            writer.write("<SCRIPT LANGUAGE=\"JavaScript\">\n");
            writer.write("window.print();\n");
            writer.write("</script>\n");

            writer.write("<table width=\"1075\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
            writer.write("  <tr>\n");
            if(transferActivityState.isEmpty())
            {
            writer.write("    <td width=\"1075\"><div align=\"center\"><img src=\""+Configuration.statementOfCashInflowsHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"StatementOfAccount\"(CreditOnly)\" /></div></td>\n");
            }
            else
            {
            writer.write("    <td width=\"1075\"><div align=\"center\"><img src=\""+Configuration.statementOfRevenuesHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"StatementOfAccount\"(CreditOnly)\" /></div></td>\n");

            }
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            if(transferActivityState.isEmpty()==false)
            {
                tranferActivityComment=" (TRANSFER ACTIVITY EXCLUDED) ";
            }

            if(statementType.equalsIgnoreCase("FULL_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"   "+
"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+"   "+
"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 20);
             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 21);

             statementAmtDue=new String[statementToBalance.length];
             

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by dOrderDate", 18);

            String demacate="N";
            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\" "+setDemacateSyntax(demacate)+" >FULL REPORT"+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\" >**</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");
            
            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;
            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\"  "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+">ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\"  "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\"  "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th  "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th  "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th  "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th  "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th  "+setDemacateSyntax(demacate)+" colspan=\"3\">ITEM</th>\n");
                        writer.write("    <th  "+setDemacateSyntax(demacate)+" colspan=\"2\">DESCRIPTION</th>\n");
                        writer.write("    <th  "+setDemacateSyntax(demacate)+" colspan=\"2\">AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");

                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th  "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th  "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th  "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");

              x+=1;
              if(x==y){break;}
              demacate=changeDemacate(demacate);
            }
                    demacate=changeDemacate(demacate);
            writer.write("</table>\n");

            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\"  "+setDemacateSyntax(demacate)+" ></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\"   "+setDemacateSyntax(demacate)+" ><strong>TOTAL CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\"  "+setDemacateSyntax(demacate)+" ><strong>TOTAL REVENUES</strong></th>\n");

            }
            writer.write("    <th width=\"432\"  "+setDemacateSyntax(demacate)+" ><strong>CURRENCY</strong></th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  "+transferActivityState+"  ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th   "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 20);
             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 21);

             statementAmtDue=new String[statementToBalance.length];
             
             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 18);

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
            String demacate="N";
            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">FULL REPORT"+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th  "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th  "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");

                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th  "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";} 
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";} 
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");
              x+=1;
              if(x==y){break;}
            demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" ><STRONG>TOTAL CREDITS</STRONG></th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" ><STRONG>TOTAL REVENUES</STRONG></th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" ><STRONG>CURRENCY</STRONG></th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' AND  dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"  ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }


            if(statementType.equalsIgnoreCase("ACCOUNT_N"))
            {
             accountCode=systems.getValue(OpenMSApp.Database_A, "Select * from Account WHERE vAccountName='"+accountName+"'", "cAccountCode");

             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 21);

             statementAmtDue=new String[statementToBalance.length];
             
             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 18);

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
            String demacate="N";
            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE ACCOUNT REPORT OF "+accountName+""+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">**</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");
                            
                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");
            
              x+=1;
              if(x==y){break;}
               demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL CREDITS</th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL REVENUES</th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" >CURRENCY</th>\n");
            writer.write("  </tr>\n");
//
//                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
//                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
//                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
//
//                int xs=0;
//                int ys=statementSumAccountName.length;
//
//                while(xs<ys)
//                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+accountCode+"' and cStatus='posted'  "+transferActivityState+"  ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+accountName+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where cAccountCode='"+accountCode+"'", 5)+"</td>\n");
                    writer.write("  </tr>\n");
//                    xs+=1;
//                    if(xs==ys){break;}
//                }
                    writer.write("</table>\n");
            }


            if(statementType.equalsIgnoreCase("ACCOUNT_Y"))
            {
             accountCode=systems.getValue(OpenMSApp.Database_A, "Select * from Account WHERE vAccountName='"+accountName+"'", "cAccountCode");

             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"'  AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 21);
             
             statementAmtDue= new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where pay.cAccountCode='"+accountCode+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by dOrderDate", 18);

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
            String demacate="N";
            
            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE ACCOUNT REPORT OF "+accountName+""+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\"  "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");

                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");
              x+=1;
              if(x==y){break;}
            demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" ><strong>TOTAL CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" ><strong>TOTAL REVENUES</strong></th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" ><strong>CURRENCY</strong></th>\n");
            writer.write("  </tr>\n");

//                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
//                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
//                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
//
//                int xs=0;
//                int ys=statementSumAccountName.length;
//
//                while(xs<ys)
//                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+accountCode+"' and cStatus='posted' AND dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+accountName+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account WHERE cAccountCode='"+accountCode+"'",5)+"</td>\n");
                    writer.write("  </tr>\n");
//                    xs+=1;
//                    if(xs==ys){break;}
//                }
                    writer.write("</table>\n");
            }


            if(statementType.equalsIgnoreCase("MERCHANT_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"    "+
"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 21);
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 18);
                String demacate="N";
                String headMerchantName="";
                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                headMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vlastName").trim();
                }
                else
                {
                    headMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vName");
                }
            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE MERCHANT REPORT OF "+headMerchantName+""+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">**</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</strong></th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");
                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");

              x+=1;
              if(x==y){break;}
              demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ><strong></strong></td>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL CREDITS</th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL REVENUES</th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" >CURRENCY</th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' AND cMerchantID='"+merchantID+"' and cStatus='posted'   "+transferActivityState+"  ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }


            if(statementType.equalsIgnoreCase("MERCHANT_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'    "+transferActivityState+"   "+
" order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
" order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"   order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'    "+transferActivityState+"  "+
"   order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"    "+
"  order by dOrderDate", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"    "+
"  order by dOrderDate", 21);
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 18);
                String demacate="N";
                String headMerchantName="";
                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                headMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vlastName").trim();
                }
                else
                {
                    headMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vName");
                }
            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE MERCHANT REPORT OF "+headMerchantName+""+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");

                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</strong></th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");

              x+=1;
              if(x==y){break;}
              demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL CREDITS</th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL REVENUES</th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" >CURRENCY</th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"'   "+transferActivityState+"  AND cMerchantID='"+merchantID+"' and cStatus='posted' AND dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("USER_N"))
            {
             String statementOrderDetail[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cOrderByID='"+merchantID+"'   "+transferActivityState+"    "+
"  order by dOrderDate", 7,10,3,4,9,8,12,13,14,6,20,21,16,18);
//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+userID+"'   "+transferActivityState+"    "+
//"  order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"   "+
//"  order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"   "+
//"  order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"    "+
//"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"    "+
//"  order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
//"  order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"    "+
//"  order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'  "+transferActivityState+"    "+
//"  order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"  "+
//"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
//"  order by dOrderDate", 6);
//
//             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
//"  order by dOrderDate", 20);
//
//             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
//"  order by dOrderDate", 21);
//             
//             statementAmtDue=new String[statementToBalance.length];

//             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"   "+
//"  order by dOrderDate", 16);
//
//             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+userID+"'   "+transferActivityState+"   "+
//"  order by dOrderDate", 17);
//
//             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"'   "+transferActivityState+"  "+
//"  order by dOrderDate", 18);
             statementOrderCode=statementOrderDetail[0];

             statementCreditDebitCode=statementOrderDetail[1];

             statementCreditCode=statementOrderDetail[2];

             statementDebitCode=statementOrderDetail[3];

             statementAccountCode=statementOrderDetail[4];

             statementMerchantID=statementOrderDetail[5];

             statementOrderByCode=statementOrderDetail[6];

             statementOrderDate=statementOrderDetail[7];

             statementConsentByCode=statementOrderDetail[8];

             statementAccountBalance=statementOrderDetail[9];

             statementTotalAmount=statementOrderDetail[10];

             statementToBalance=statementOrderDetail[11];
             statementTransactionSerialNumber=statementOrderDetail[12];

             statementAmtDue=new String[statementToBalance.length];
             
             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cOrderByID='"+merchantID+"' "+transferActivityState+" "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=statementOrderDetail[13];
             
                String demacate="N";
                String headMerchantName="";
                String userID=systems.getValue(OpenMSApp.Database_A, "Select cEmployeeID from login where cLoginCode='"+merchantID+"'",1);
                headMerchantName=systems.getValue(OpenMSApp.Database_A, "Select vName from vwMerchant where cMerchantID='"+userID+"'", "vName");
        
                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select vName from vwMerchant where cMerchantID='"+userID+"'","vName").trim();

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE USER REPORT OF "+headMerchantName+""+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">**</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</strong></th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");
                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");

              x+=1;
              if(x==y){break;}
              demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ><strong></strong></td>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL CREDITS</th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL REVENUES</th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" >CURRENCY</th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' AND cMerchantID='"+merchantID+"' and cStatus='posted'   "+transferActivityState+"  ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }


            if(statementType.equalsIgnoreCase("USER_Y"))
            {
             String statementOrderDetail[][]=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cOrderbyID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'    "+transferActivityState+"   "+
" order by dOrderDate", 7,10,3,4,9,8,12,13,14,6,20,21,16,18);
//             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'    "+transferActivityState+"   "+
//" order by dOrderDate", 7);
//
//             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
//" order by dOrderDate", 10);
//
//             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//"  order by dOrderDate", 3);
//
//             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
//"  order by dOrderDate", 4);
//
//             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
//"  order by dOrderDate", 9);
//
//             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
//"   order by dOrderDate", 8);
//
//             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'    "+transferActivityState+"  "+
//"   order by dOrderDate", 12);
//
//             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
//"  order by dOrderDate", 13);
//
//             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
//"  order by dOrderDate", 14);
//
//             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
//"  order by dOrderDate", 6);
//
//             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"    "+
//"  order by dOrderDate", 20);
//
//             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"    "+
//"  order by dOrderDate", 21);
//             
//             statementAmtDue=new String[statementToBalance.length];
//
//             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
//"  order by dOrderDate", 16);
//
//             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
//"  order by dOrderDate", 17);
//
//             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cMerchantID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
//"  order by dOrderDate", 18);

             statementOrderCode=statementOrderDetail[0];

             statementCreditDebitCode=statementOrderDetail[1];

             statementCreditCode=statementOrderDetail[2];

             statementDebitCode=statementOrderDetail[3];

             statementAccountCode=statementOrderDetail[4];

             statementMerchantID=statementOrderDetail[5];

             statementOrderByCode=statementOrderDetail[6];

             statementOrderDate=statementOrderDetail[7];

             statementConsentByCode=statementOrderDetail[8];

             statementAccountBalance=statementOrderDetail[9];

             statementTotalAmount=statementOrderDetail[10];

             statementToBalance=statementOrderDetail[11];
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=statementOrderDetail[12];

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cOrderbyID='"+merchantID+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
" order by dOrderDate", 17);

             statementTransactionTypeCode=statementOrderDetail[13];
             
                String demacate="N";
                String headMerchantName="";
                String userID=systems.getValue(OpenMSApp.Database_A, "Select cEmployeeID from login where cLoginCode='"+merchantID+"'",1);
                headMerchantName=systems.getValue(OpenMSApp.Database_A, "Select vName from vwMerchant where cMerchantID='"+userID+"'", "vName");
        
                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select vName from vwMerchant where cMerchantID='"+userID+"'","vName").trim();

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE USER REPORT OF "+headMerchantName+" "+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            //writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select vAccountUnit from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select vAccountName from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select cEmployeeID from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select vTransactionType from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    statementMerchantName=systems.getValue(OpenMSApp.Database_A,"Select vName from vwMerchant where cMerchantID='"+statementMerchantID[x]+"'","vName");
                }
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");

                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</strong></th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");

              x+=1;
              if(x==y){break;}
              demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL CREDITS</th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL REVENUES</th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" >CURRENCY</th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"'   "+transferActivityState+"  AND cMerchantID='"+merchantID+"' and cStatus='posted' AND dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }
//--- begin check activity

            if(statementType.equalsIgnoreCase("ACTIVITY_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"    "+
"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 21);
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"   "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cactivity='"+activity+"'   "+transferActivityState+"  "+
"  order by dOrderDate", 18);
                String demacate="N";
                String headMerchantName="";
                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                headMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vlastName").trim();
                }
                else
                {
                    headMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vName");
                }
            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=journal>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE CASH INFLOW ACTIVITY REPORT OF "+activity+""+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">**</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");

                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</t>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");

              x+=1;
              if(x==y){break;}
              demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL CREDITS</th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" >TOTAL REVENUES</th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" >CURRENCY</th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"'   "+transferActivityState+"  AND cactivity='"+activity+"' and cStatus='posted' ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }


            if(statementType.equalsIgnoreCase("ACTIVITY_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'    "+transferActivityState+"  "+
" order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+" "+
" order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"  "+
"  order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"   order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"   order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 21);
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cactivity='"+activity+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+" "+
"  order by dOrderDate", 18);
                String demacate="N";
                String headMerchantName="";
                creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vTable").trim();
                if(creditByTable.equalsIgnoreCase("Employee"))
                {
                headMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vFirstName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vMiddleName").trim()+" "+
                        systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+merchantID+"'","vlastName").trim();
                }
                else
                {
                    headMerchantName=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+merchantID+"'","vName");
                }
            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE CASH INFLOW ACTIVITY REPORT OF "+activity+""+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" ><strong>DATE:</strong></th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" ><strong>PERIOD:</strong></th>\n");
            writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");

                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");

              x+=1;
              if(x==y){break;}
              demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ><strong></strong></td>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" ><strong>TOTAL CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" ><strong>TOTAL REVENUES</strong></th>\n");
            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" ><strong>CURRENCY</strong></th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' AND cactivity='"+activity+"' and cStatus='posted' AND dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"  ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

//--- end check activity
//---begin check item

        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeCreditStatement gave error! "+e);
            e.printStackTrace();
        }
        NoteWriterJournalExt newj=new NoteWriterJournalExt();
        newj.writeCreditStatementJournalExt(statementType, accountName, accountUnit, merchantID, activity, itemName, transferActivityState, startDate, endDate,itemCode,tranferActivityComment);
    
        try{
            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cActivity", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cActivity", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cActivity", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cActivity", 21);
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cActivity", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cActivity", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by cActivity", 18);
            String demacate="N";
            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" >SORT: </th>\n");
            writer.write("    <td width=\"642\">FULL, GROUPING ENTITY::CASH INFLOW ACTIVITY"+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" >DATE:</th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >PERIOD:</th>\n");
            writer.write("    <td colspan=\"3\">"+startDate+" TO "+endDate+"</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("</table>\n");

            writer.write("<table width=\"1079\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" id=\"journal\">\n");
            int x=0;
            int y=statementOrderCode.length;

            while(x<y)
            {
                String[] statementItemDescription=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",3);

                String creditAmount="";
                String debitAmount="";
                String statementMerchantName="";
                String statementOrderBy="";
                String statementAccountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountUnit");
                String statementAccountName=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+statementAccountCode[x]+"'","vAccountName");

                String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+statementOrderByCode[x]+"'","cEmployeeID");
                String statementTransactionType=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode= '"+statementTransactionTypeCode[x]+"'","vTransactionType");
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
                    writer.write("  <tr>\n");
                    writer.write("    <th width=\"103\" rowspan=\""+(statementItemDescription.length+4)+"\" "+setDemacateSyntax(demacate)+" >"+statementOrderDate[x]+"</th>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER CODE:</th>\n");
                    writer.write("    <td width=\"121\">"+statementOrderCode[x]+"</td>\n");
                    writer.write("    <th width=\"115\" "+setDemacateSyntax(demacate)+" >ORDER BY:</th>\n");
                    writer.write("    <td width=\"126\">"+statementOrderBy+"</td>\n");
                    writer.write("    <th width=\"143\" "+setDemacateSyntax(demacate)+" >CONSENT BY:</th>\n");
                    writer.write("    <td width=\"125\">"+statementConsentBy+"</td>\n");
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</th>\n");
                    writer.write("    <td width=\"130\">"+statementAccountName+"</td>\n");
                    writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION TYPE:</th>\n");
                    writer.write("    <td>"+statementTransactionType+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION BY:</th>\n");
                    writer.write("    <td>"+statementMerchantName+"</td>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >TRANSACTION S/N &amp; DATE:</th>\n");
                    writer.write("    <td colspan=\"3\">"+statementTransactionSerialNumber[x]+" ["+systems.extractSQLDate(statementTransactionDate[x])+"]</td>\n");
                    writer.write("  </tr>\n");

                        writer.write("  <tr>\n");
                        writer.write("    <th "+setDemacateSyntax(demacate)+" >S/N</th>\n");
                        writer.write("    <th colspan=\"3\" "+setDemacateSyntax(demacate)+" >ITEM</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >DESCRIPTION</th>\n");
                        writer.write("    <th colspan=\"2\" "+setDemacateSyntax(demacate)+" >AMOUNT ("+statementAccountCurrency+")</th>\n");
                        writer.write("  </tr>\n");

                        String[] statementItemAmount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",4);

                        String[] statementItemCode=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",2);
                        String[] statementItemSerial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditOrderSummary where cCreditOrderCode='"+statementOrderCode[x]+"'",5);

                        int xi=0;
                        int yi=statementItemDescription.length;

                        while(xi<yi)
                        {
                            try
                            {statementItemAmount[xi]=systems.converToRealMoneyFormat(statementItemAmount[xi]);}
                            catch(Exception e)
                            {statementItemAmount[xi]="Er!M1C01K0";}
                            String statementItem=systems.getValue(OpenMSApp.Database_A,"Select * from item Where cItemCode='"+statementItemCode[xi]+"'", 2);
                            writer.write("  <tr>\n");
                            writer.write("    <td>"+statementItemSerial[xi]+".</td>\n");
                            writer.write("    <td colspan=\"3\">"+statementItem+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemDescription[xi]+"</td>\n");
                            writer.write("    <td colspan=\"2\">"+statementItemAmount[xi]+"</td>\n");
                            writer.write("  </tr>\n");

                            xi+=1;
                            if(xi==yi){break;}
                        }
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</th>\n");
            try
            {statementTotalAmount[x]=systems.converToRealMoneyFormat(statementTotalAmount[x]);}
            catch(Exception e)
            {statementTotalAmount[x]="Er!M1C01K0";}
            try
            {statementToBalance[x]=systems.converToRealMoneyFormat(statementToBalance[x]);}
            catch(Exception e)
            {statementToBalance[x]="Er!M1C01K0";}
            try
            {
            statementAmtDue[x]=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+statementToBalance[x]+" , "+statementTotalAmount[x]+Connect.procInitEnd, "mSumValue"));
            }
            catch(Exception e)
            {
                statementAmtDue[x]="Er!M1C01K0";
            }
            writer.write("    <td>"+statementTotalAmount[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</th>\n");
            writer.write("    <td>"+statementAmtDue[x]+"</td>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >TO BALANCE:</th>\n");
            writer.write("    <td colspan=\"3\">"+statementToBalance[x]+"</td>\n");
            writer.write("  </tr>\n");

              x+=1;
              if(x==y){break;}
              demacate=changeDemacate(demacate);
            }
            writer.write("</table>\n");
            demacate=changeDemacate(demacate);
            writer.write("<table width=\"1078\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
//            writer.write("    <td>&nbsp;</td>\n");
            writer.write("    <td colspan=\"3\">&nbsp;</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"332\" "+setDemacateSyntax(demacate)+" ><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" ><strong>TOTAL CREDITS</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"306\" "+setDemacateSyntax(demacate)+" ><strong>TOTAL REVENUES</strong></th>\n");

            }
            writer.write("    <th width=\"432\" "+setDemacateSyntax(demacate)+" ><strong>CURRENCY</strong></th>\n");
            writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);

                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' AND  dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"  ",1);

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
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }
/*
 *
 */

//        }
//            catch(Exception e)
//            {
//                System.out.println("Notewriter.writeCreditNote gave error 01 "+e);
//            }
//
//            try{
            writeCreditStatementOther(statementType,accountName,accountUnit,merchantID,activity,itemName,transferActivityState,startDate,endDate);
//--- end group sorting
            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
            writer.write("  <tr>\n");
            writer.write("    <td width=\"1073\">&nbsp;</td>\n");
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

        resetVariable();
    }
    
    public void writeCreditStatementOther(String statementType,String accountName,String accountUnit,String merchantID,String activity,String itemName,String transferActivityState,String startDate,String endDate)
    {}
}
