
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
 * @author Abiodun
 */
public class NoteWriterJournalExt extends NoteWriterJournal{
    
    public void writeCreditStatementJournalExt(String statementType,String accountName,String accountUnit,String merchantID,String activity,String itemName,String transferActivityState,String startDate,String endDate,String itemCode,String tranferActivityComment)
    {
        try{
            if(statementType.equalsIgnoreCase("ITEM_N"))
            {

             statementOrderCode=systems.getColumn(OpenMSApp.Database_A, "select distinct creditOrder.cCreditOrderCode from creditorder JOIN creditordersummary on creditOrder.cCreditOrderCode=creditOrderSummary.cCreditOrderCode where creditOrder.cStatus='posted' and cItemCode='"+itemCode+"'"+transferActivityState+
"  order by dOrderDate", 1);

            int xii=0;
            int yii=statementOrderCode.length;

            if(xii<yii)
            {
                System.out.println(itemCode+" - "+statementOrderCode[0]+" - "+yii);
                statementCreditDebitCode=new String[yii];
                statementCreditCode=new String[yii];
                statementDebitCode=new String[yii];
                statementAccountCode=new String[yii];
                statementMerchantID=new String[yii];
                statementOrderByCode=new String[yii];

                statementOrderDate=new String[yii];
                statementConsentByCode=new String[yii];
                statementAccountBalance=new String[yii];
                statementTotalAmount=new String[yii];
                statementToBalance=new String[yii];
                statementAmtDue=new String[yii];
                statementTransactionSerialNumber=new String[yii];

                statementTransactionDate=new java.sql.Date[yii];
                statementTransactionTypeCode=new String[yii];

                while(xii<yii)
                {
             statementCreditDebitCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 10);

             statementCreditCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 3);

             statementDebitCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 4);

             statementAccountCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 9);

             statementMerchantID[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 8);

             statementOrderByCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 12);

             statementOrderDate[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"    "+
"  order by dOrderDate", 13);

             statementConsentByCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"  "+
"  order by dOrderDate", 14);

             statementAccountBalance[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 6);

             statementTotalAmount[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 20);

             statementToBalance[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 21);

             statementTransactionSerialNumber[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 16);

             statementTransactionDate[xii]=systems.getDateValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"   "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"'  "+transferActivityState+"  "+
"  order by dOrderDate", 18);

                xii++;
                if(xii==yii){break;}
                }
            }
            String demacate="N";
            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();

            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE ITEM REPORT OF "+itemName+""+tranferActivityComment+"</td>\n");
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
            writer.write("    <td colspan=\"4\">&nbsp;</th>\n");
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
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "select distinct creditOrder.cCreditOrderCode,SUM(mTotalAmount) from creditorder JOIN creditordersummary on creditOrder.cCreditOrderCode=creditOrderSummary.cCreditOrderCode where cAccountCode='"+statementSumAccountCode[xs]+"' AND creditOrder.cStatus='posted' and cItemCode='"+itemCode+"' "+transferActivityState+"  ",2);
                    //String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' AND cMerchantID='"+merchantID+"' and cStatus='posted'   "+transferActivityState+"  ",1);

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
                    writer.write("    <th  "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }


            if(statementType.equalsIgnoreCase("ITEM_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A, "select distinct creditOrder.cCreditOrderCode from creditorder JOIN creditordersummary on creditOrder.cCreditOrderCode=creditOrderSummary.cCreditOrderCode where creditOrder.cStatus='posted' and cItemCode='"+itemCode+"'  AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+
"  order by dOrderDate", 1);


            int xii=0;
            int yii=statementOrderCode.length;

            if(xii<yii)
            {
                System.out.println(itemCode+" - "+statementOrderCode[0]+" - "+yii);
                statementCreditDebitCode=new String[yii];
                statementCreditCode=new String[yii];
                statementDebitCode=new String[yii];
                statementAccountCode=new String[yii];
                statementMerchantID=new String[yii];
                statementOrderByCode=new String[yii];

                statementOrderDate=new String[yii];
                statementConsentByCode=new String[yii];
                statementAccountBalance=new String[yii];
                statementTotalAmount=new String[yii];
                statementToBalance=new String[yii];
                statementAmtDue=new String[yii];
                statementTransactionSerialNumber=new String[yii];

                statementTransactionDate=new java.sql.Date[yii];
                statementTransactionTypeCode=new String[yii];

                while(xii<yii)
                {
                
             statementOrderCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'    "+transferActivityState+"   "+
" order by dOrderDate", 7);

             statementCreditDebitCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
" order by dOrderDate", 10);

             statementCreditCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 3);

             statementDebitCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 4);

             statementAccountCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 9);

             statementMerchantID[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"   order by dOrderDate", 8);

             statementOrderByCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'    "+transferActivityState+"  "+
"   order by dOrderDate", 12);

             statementOrderDate[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 13);

             statementConsentByCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 14);

             statementAccountBalance[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 6);

             statementTotalAmount[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"    "+
"  order by dOrderDate", 20);

             statementToBalance[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"    "+
"  order by dOrderDate", 21);

             statementTransactionSerialNumber[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 16);

             statementTransactionDate[xii]=systems.getDateValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by dOrderDate", 17);

             statementTransactionTypeCode[xii]=systems.getValue(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  Where  creditOrder.cCreditOrderCode='"+statementOrderCode[xii]+"' AND creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by dOrderDate", 18);

                xii++;
                if(xii==yii){break;}
                }
            }

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
            String demacate="N";
            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">SINGLE ITEM REPORT OF "+itemName+""+tranferActivityComment+"</td>\n");
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
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT RECEIVED:</td>\n");
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
            writer.write("    <td colspan=\"3\">"+statementTotalAmount[x]+"</td>\n");
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
                    //  String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"'   "+transferActivityState+"  AND cMerchantID='"+merchantID+"' and cStatus='posted' AND dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);
                        String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "select distinct creditOrder.cCreditOrderCode,SUM(mTotalAmount) from creditorder JOIN creditordersummary on creditOrder.cCreditOrderCode=creditOrderSummary.cCreditOrderCode where cAccountCode='"+statementSumAccountCode[xs]+"' AND creditOrder.cStatus='posted' and cItemCode='"+itemCode+"'  AND dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  ",2);
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

//---end check item
//--- begin group sorting

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"   "+
"  order by cMerchantID", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+"   "+
"  order by cMerchantID", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 21);
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cMerchantID", 18);

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
            String demacate="N";
            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">FULL ,GROUPING ENTITY::MERCHANT "+tranferActivityComment+"</td>\n");
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
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cMerchantID", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cMerchantID", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cMerchantID", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+"   "+
"  order by cMerchantID", 21);
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cMerchantID", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"  "+
"  order by cMerchantID", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   "+transferActivityState+"   "+
"  order by cMerchantID", 18);

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
            String demacate="N";
            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" ><strong>SORT: </strong></th>\n");
            writer.write("    <td width=\"642\">FULL, GROUPING ENTITY::MERCHANT"+tranferActivityComment+"</td>\n");
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
                    writer.write("    <th>TRANSACTION TYPE:</th>\n");
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


            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"   "+
"  order by cActivity", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+"   "+
"  order by cActivity", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 6);

             statementTotalAmount=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 20);

             statementToBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 21);
             
             statementAmtDue=new String[statementToBalance.length];

             statementTransactionSerialNumber=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 16);

             statementTransactionDate=systems.getDateColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 17);

             statementTransactionTypeCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode   "+transferActivityState+"  "+
"  order by cActivity", 18);

            java.util.Calendar calendar=java.util.Calendar.getInstance();
            java.text.DateFormat formater=java.text.DateFormat.getDateInstance();
            String demacate="N";
            writer.write("<table width=\"1077\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
            writer.write("  <tr>\n");
            writer.write("    <th width=\"72\" "+setDemacateSyntax(demacate)+" >SORT: </th>\n");
            writer.write("    <td width=\"642\">FULL ,GROUPING ENTITY::CASH INFLOW ACTIVITY "+tranferActivityComment+"</td>\n");
            writer.write("    <th width=\"53\" "+setDemacateSyntax(demacate)+" >DATE:</th>\n");
            writer.write("    <td width=\"310\">"+formater.format(calendar.getTime())+"</td>\n");
            writer.write("  </tr>\n");
            writer.write("  <tr>\n");
            writer.write("    <th "+setDemacateSyntax(demacate)+" >PERIOD:</th>\n");
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
                    writer.write("    <th width=\"81\" "+setDemacateSyntax(demacate)+" >ACCOUNT NAME:</td>\n");
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
            writer.write("    <th "+setDemacateSyntax(demacate)+" >AMOUNT DUE:</td>\n");
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
                    writer.write("    <th "+setDemacateSyntax(demacate)+" >"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
                    writer.write("    <td>"+statementSumAccountCurrency[xs]+"</td>\n");
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                    writer.write("</table>\n");
            }

            
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeCreditStatement gave error! "+e);
            e.printStackTrace();
        }
        
    }
}
