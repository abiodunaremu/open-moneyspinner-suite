
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

public class NoteWriterExtension extends NoteWriter{
    
    public NoteWriterExtension(){}
    public void writeCreditOrderNote(String orderCode)
    {
            try
            {
                Connect.createMSSQLConnection();
                Connect.changeDB(OpenMSApp.Database_A);
                statement=Connect.createStatement("Select * from creditorder where cCreditOrderCode= '"+orderCode+"'");
                result=Connect.executeRStatement(statement);
                result.next();
                creditByID=result.getString(2);
                orderByID=result.getString(6);
                orderDate=result.getString(7);
                totalAmount=result.getString(14);
                toBalance=result.getString(15);

                Connect.closeConnection();
            }
            catch(Exception e)
            {
                System.out.println("Sql errror! (jComboBox1ActionPerformed) "+e);
            }

            description=systems.getColumn(OpenMSApp.Database_A,"select * from creditordersummary where cCreditOrderCode='"+orderCode+"'",3);

            amount=systems.getColumn(OpenMSApp.Database_A," Select * from creditordersummary where cCreditOrderCode= '"+orderCode+"'",4);

            serial=systems.getColumn(OpenMSApp.Database_A," Select * from creditordersummary where cCreditOrderCode= '"+orderCode+"'",5);

            creditBy=systems.getValue(OpenMSApp.Database_A," Select * from vwMerchant where cMerchantID= '"+creditByID+"'","vName");

            String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+orderByID+"'","cEmployeeID");

            orderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vlastName").trim();

            orderDate=systems.getValue(OpenMSApp.Database_A,"Select * from creditOrder where cCreditOrderCode= '"+orderCode+"'","dOrderDate");

          try
            {
            file=new java.io.File("Note/CreditOrderNote.htm");
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);

            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            writer.write("<head>");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
            writer.write("<title>Untitled Document</title>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<table width=\"799\" border=\"0\">");
            writer.write("    <tr>");
            writer.write("      <td height=\"95\" colspan=\"2\"><div align=\"center\">");
            writer.write("        <dl>");
            writer.write("          <dt><img src=\"Milliscript_logo.png\" width=\"114\" height=\"63\" alt=\"Company Logo\" /></dt>");
            writer.write("        </dl>");
            writer.write("      </div></td>");
            writer.write("      <td width=\"221\"><h1 align=\"center\">MILLISCRIPT</h1>");
            writer.write("        <dl>");
            writer.write("          <dt>");
            writer.write("            <h3 align=\"center\">IT ENTERPRISES</h3>");
            writer.write("          </dt>");
            writer.write("          <dt>&nbsp; </dt>");
            writer.write("          <dt>");
            writer.write("            <div align=\"center\"></div>");
            writer.write("          </dt>");
            writer.write("          <dt>&nbsp;          </dt>");
            writer.write("      </dl></td>");
            writer.write("      <td colspan=\"2\"><dl>");
            writer.write("        <dt>");
            writer.write("          <div align=\"center\">Plot 1 Aremu Close Owode Apata Ibadan.</div>");
            writer.write("        </dt>");
            writer.write("        <dd>");
            writer.write("          <div align=\"center\">G.P.O Box 18722 Dugbe Ibadan.</div>");
            writer.write("        </dd>");
            writer.write("        <dt>");
            writer.write("          <div align=\"center\">Tel: 08026891916</div>");
            writer.write("        </dt>");
            writer.write("      </dl></td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td height=\"47\" colspan=\"2\">&nbsp;</td>");
            writer.write("      <td><div align=\"center\">");
            writer.write("        <h2>CREDIT ORDER NOTE</h2>");
            writer.write("      </div></td>");
            writer.write("      <td colspan=\"2\">&nbsp;</td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td width=\"79\"><div align=\"left\">Order Code:</div></td>");
            writer.write("      <td width=\"150\"><div align=\"left\">"+orderCode+"</div></td>");
            writer.write("      <td>&nbsp;</td>");
            writer.write("      <td width=\"75\"><div align=\"left\">Order Date:</div></td>");
            writer.write("      <td width=\"252\"><div align=\"left\">"+orderDate+"</div></td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td height=\"35\"><div align=\"left\">Order By:</div></td>");
            writer.write("      <td><p align=\"left\">"+orderBy+"</p></td>");
            writer.write("      <td>&nbsp;</td>");
            writer.write("      <td><div align=\"left\">Merchant:</div></td>");
            writer.write("      <td><div align=\"left\">"+creditBy+"</div></td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td height=\"23\" colspan=\"5\">&nbsp;</td>");
            writer.write("    </tr>");
            writer.write("  </table>");
            writer.write("  <table width=\"799\" border=\"1\">");
            writer.write("    <tr>");
            writer.write("      <td width=\"102\" height=\"49\"><strong>S/N</strong></td>");
            writer.write("      <td colspan=\"3\"><div align=\"center\"><strong>DESCRIPTION</strong></div>");
            writer.write("        <div align=\"center\"></div></td>");
            writer.write("      <td width=\"244\"><div align=\"center\"><strong>AMOUNT (=N=)</strong></div></td>");
            writer.write("    </tr>");

            int x=0;
            int y=serial.length;

            while(x<y)
            {
                writer.write("    <tr>");
                writer.write("      <td height=\"38\">"+serial[x]+"</td>");
                writer.write("      <td colspan=\"3\">"+description[x]+"</td>");
                writer.write("      <td>"+amount[x]+"</td>");
                writer.write("    </tr>");
                x+=1;
                if(x==y){break;}
            }

            writer.write("    <tr>");
            writer.write("      <td height=\"38\" colspan=\"4\"><div align=\"right\"><STRONG>TOTAL AMOUNT</STRONG></div></td>");
            writer.write("      <td>"+totalAmount+"</td>");
            writer.write("    </tr>");
            writer.write("</table>");
            writer.write("  <table width=\"799\" border=\"0\">");
            writer.write("    <tr>");
            writer.write("      <td height=\"62\">&nbsp;</td>");
            writer.write("      <td colspan=\"2\">&nbsp;</td>");
            writer.write("      <td colspan=\"2\">&nbsp;</td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td height=\"67\" colspan=\"2\"><p align=\"center\">_____________________________</p>");
            writer.write("        <p align=\"center\">ORDER BY</p></td>");
            writer.write("      <td height=\"67\" colspan=\"2\"><p align=\"center\">_____________________________</p>");
            writer.write("        <p align=\"center\">FINANCE</p></td>");
            writer.write("      <td height=\"67\"><p align=\"center\">_____________________________</p>");
            writer.write("        <p align=\"center\">APPROVED BY</p></td>");
            writer.write("    </tr>");
            writer.write("</table>");
            writer.write("<p>&nbsp;</p>");
            writer.write("  <p>&nbsp;</p>");
            writer.write("</body>");
            writer.write("</html>");
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeCreditOrderNote gave error! "+e);
        }
        resetVariable();
    }

    public void writeDebitOrderNote(String orderCode)
    {
            try
            {
                Connect.createMSSQLConnection();
                Connect.changeDB(OpenMSApp.Database_A);
                statement=Connect.createStatement("Select * from debitorder where cDebitOrderCode= '"+orderCode+"'");
                result=Connect.executeRStatement(statement);
                result.next();
                debitByID=result.getString(2);
                orderByID=result.getString(5);
                orderDate=result.getString(6);
                totalAmount=result.getString(9);

                Connect.closeConnection();
            }
            catch(Exception e)
            {
                System.out.println("Sql errror! (jComboBox1ActionPerformed) "+e);
            }

            description=systems.getColumn(OpenMSApp.Database_A,"select * from debitordersummary where cDebitOrderCode='"+orderCode+"'",6);

            amount=systems.getColumn(OpenMSApp.Database_A," Select * from debitordersummary where cDebitOrderCode= '"+orderCode+"'",7);

            serial=systems.getColumn(OpenMSApp.Database_A," Select * from debitordersummary where cDebitOrderCode= '"+orderCode+"'",8);

            debitBy=systems.getValue(OpenMSApp.Database_A," Select * from vwMerchant where cMerchantID= '"+debitByID+"'","vName");

            String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+orderByID+"'","cEmployeeID");

            orderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vlastName").trim();

            orderDate=systems.getValue(OpenMSApp.Database_A,"Select * from debitOrder where cDebitOrderCode= '"+orderCode+"'","dOrderDate");

          try
            {
            file=new java.io.File("Note/DebitOrderNote.htm");
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);

            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'\">");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
            writer.write("<head>");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
            writer.write("<title>Debit Note <"+orderCode+"> </title>");
            writer.write("</head>");
            writer.write("<body>");
            writer.write("<table width=\"799\" border=\"0\">");
            writer.write("    <tr>");
            writer.write("      <td height=\"95\" colspan=\"2\"><div align=\"center\">");
            writer.write("        <dl>");
            writer.write("          <dt><img src=\"Milliscript_logo.png\" width=\"114\" height=\"63\" alt=\"Company Logo\" /></dt>");
            writer.write("        </dl>");
            writer.write("      </div></td>");
            writer.write("      <td width=\"221\"><h1 align=\"center\">MILLISCRIPT</h1>");
            writer.write("        <dl>");
            writer.write("          <dt>");
            writer.write("            <h3 align=\"center\">IT ENTERPRISES</h3>");
            writer.write("          </dt>");
            writer.write("          <dt>&nbsp; </dt>");
            writer.write("          <dt>");
            writer.write("            <div align=\"center\"></div>");
            writer.write("          </dt>");
            writer.write("          <dt>&nbsp;          </dt>");
            writer.write("      </dl></td>");
            writer.write("      <td colspan=\"2\"><dl>");
            writer.write("        <dt>");
            writer.write("          <div align=\"center\">Plot 1 Aremu Close Owode Apata Ibadan.</div>");
            writer.write("        </dt>");
            writer.write("        <dd>");
            writer.write("          <div align=\"center\">G.P.O Box 18722 Dugbe Ibadan.</div>");
            writer.write("        </dd>");
            writer.write("        <dt>");
            writer.write("          <div align=\"center\">Tel: 08026891916</div>");
            writer.write("        </dt>");
            writer.write("      </dl></td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td height=\"47\" colspan=\"2\">&nbsp;</td>");
            writer.write("      <td><div align=\"center\">");
            writer.write("        <h2>DEBIT ORDER NOTE</h2>");
            writer.write("      </div></td>");
            writer.write("      <td colspan=\"2\">&nbsp;</td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td width=\"79\"><div align=\"left\">Order Code:</div></td>");
            writer.write("      <td width=\"150\"><div align=\"left\">"+orderCode+"</div></td>");
            writer.write("      <td>&nbsp;</td>");
            writer.write("      <td width=\"75\"><div align=\"left\">Order Date:</div></td>");
            writer.write("      <td width=\"252\"><div align=\"left\">"+orderDate+"</div></td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td height=\"35\"><div align=\"left\">Order By:</div></td>");
            writer.write("      <td><p align=\"left\">"+orderBy+"</p></td>");
            writer.write("      <td>&nbsp;</td>");
            writer.write("      <td><div align=\"left\">Merchant:</div></td>");
            writer.write("      <td><div align=\"left\">"+debitBy+"</div></td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td height=\"23\" colspan=\"5\">&nbsp;</td>");
            writer.write("    </tr>");
            writer.write("  </table>");
            writer.write("  <table width=\"799\" border=\"1\">");
            writer.write("    <tr>");
            writer.write("      <td width=\"102\" height=\"49\"><strong>S/N</strong></td>");
            writer.write("      <td colspan=\"3\"><div align=\"center\"><strong>DESCRIPTION</strong></div>");
            writer.write("        <div align=\"center\"></div></td>");
            writer.write("      <td width=\"244\"><div align=\"center\"><strong>AMOUNT (=N=)</strong></div></td>");
            writer.write("    </tr>");

            int x=0;
            int y=serial.length;

            while(x<y)
            {
                writer.write("    <tr>");
                writer.write("      <td height=\"38\">"+serial[x]+"</td>");
                writer.write("      <td colspan=\"3\">"+description[x]+"</td>");
                writer.write("      <td>"+amount[x]+"</td>");
                writer.write("    </tr>");
                x+=1;
                if(x==y){break;}
            }

            writer.write("    <tr>");
            writer.write("      <td height=\"38\" colspan=\"4\"><div align=\"right\"><strong>TOTAL AMOUNT</strong></div></td>");
            writer.write("      <td>"+totalAmount+"</td>");
            writer.write("    </tr>");
            writer.write("</table>");
            writer.write("  <table width=\"799\" border=\"0\">");
            writer.write("    <tr>");
            writer.write("      <td height=\"62\">&nbsp;</td>");
            writer.write("      <td colspan=\"2\">&nbsp;</td>");
            writer.write("      <td colspan=\"2\">&nbsp;</td>");
            writer.write("    </tr>");
            writer.write("    <tr>");
            writer.write("      <td height=\"67\" colspan=\"2\"><p align=\"center\">_____________________________</p>");
            writer.write("        <p align=\"center\">ORDER BY</p></td>");
            writer.write("      <td height=\"67\" colspan=\"2\"><p align=\"center\">_____________________________</p>");
            writer.write("        <p align=\"center\">FINANCE</p></td>");
            writer.write("      <td height=\"67\"><p align=\"center\">_____________________________</p>");
            writer.write("        <p align=\"center\">APPROVED BY</p></td>");
            writer.write("    </tr>");
            writer.write("</table>");
            writer.write("<p>&nbsp;</p>");
            writer.write("  <p>&nbsp;</p>");
            writer.write("</body>");
            writer.write("</html>");
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeDebitOrderNote gave error! "+e);
        }
        resetVariable();
    }
    public void writeCreditNote(String orderCode)
    {
            try
            {
                Connect.createMSSQLConnection();
                Connect.changeDB(OpenMSApp.Database_A);
                statement=Connect.createStatement("Select * from creditOrder where cCreditOrderCode= '"+orderCode+"'");
                result=Connect.executeRStatement(statement);
                result.next();
                creditByID=result.getString(2);
                creditCode=result.getString(3);
                postDate=result.getString(4);
                orderByID=result.getString(5);
                orderDate=result.getString(6);
                totalAmount=result.getString(9);

                Connect.closeConnection();
            }
            catch(Exception e)
            {
                System.out.println("Sql errror! (jComboBox1ActionPerformed) "+e);
            }

            creditNumber=systems.getColumn(OpenMSApp.Database_A,"select * from creditOrdersummary where cCreditOrderCode='"+orderCode+"'",2);
            creditingDate=systems.getColumn(OpenMSApp.Database_A,"select * from creditOrdersummary where cCreditOrderCode='"+orderCode+"'",3);
            transactionType=systems.getColumn(OpenMSApp.Database_A,"select * from creditOrdersummary where cCreditOrderCode='"+orderCode+"'",4);
            description=systems.getColumn(OpenMSApp.Database_A,"select * from creditOrdersummary where cCreditOrderCode='"+orderCode+"'",6);
            amount=systems.getColumn(OpenMSApp.Database_A," Select * from creditOrdersummary where cCreditOrderCode= '"+orderCode+"'",7);
            serial=systems.getColumn(OpenMSApp.Database_A," Select * from creditOrdersummary where cCreditOrderCode= '"+orderCode+"'",8);

            creditBy=systems.getValue(OpenMSApp.Database_A," Select * from vwMerchant where cMerchantID= '"+creditByID+"'","vName");
            String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+orderByID+"'","cEmployeeID");

            orderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vlastName").trim();

            orderDate=systems.getValue(OpenMSApp.Database_A,"Select * from creditOrder where cCreditOrderCode= '"+orderCode+"'","dOrderDate");
        try
          {
            file=new java.io.File("Note/CreditNote.htm");
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
            writer.write("<title>CREDIT NOTE <"+orderCode+"></title>\n");
            writer.write("</head>\n");

            writer.write("<body>\n");
            writer.write("  <table width=\"801\" border=\"0\">\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"95\" colspan=\"2\"><div align=\"center\">\n");
            writer.write("        <dl>\n");
            writer.write("          <dt><img src=\"Milliscript_logo.png\" width=\"114\" height=\"63\" alt=\"Company Logo\" /></dt>\n");
            writer.write("        </dl>\n");
            writer.write("      </div></td>\n");
            writer.write("      <td width=\"211\"><h1 align=\"center\">MILLISCRIPT</h1>\n");
            writer.write("        <dl>\n");
            writer.write("          <dt>\n");
            writer.write("            <h3 align=\"center\">IT ENTERPRISES</h3>\n");
            writer.write("          </dt>\n");
            writer.write("          <dt>&nbsp; </dt>\n");
            writer.write("          <dt>\n");
            writer.write("            <div align=\"center\"></div>\n");
            writer.write("          </dt>\n");
            writer.write("          <dt>&nbsp;          </dt>\n");
            writer.write("      </dl></td>\n");
            writer.write("      <td colspan=\"2\"><dl>\n");
            writer.write("        <dt>\n");
            writer.write("          <div align=\"center\">Plot 1 Aremu Close Owode Apata Ibadan.</div>\n");
            writer.write("        </dt>\n");
            writer.write("        <dd>\n");
            writer.write("          <div align=\"center\">G.P.O Box 18722 Dugbe Ibadan.</div>\n");
            writer.write("        </dd>\n");
            writer.write("        <dt>\n");
            writer.write("          <div align=\"center\">Tel: 08026891916</div>\n");
            writer.write("        </dt>\n");
            writer.write("      </dl></td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"47\" colspan=\"2\">&nbsp;</td>\n");
            writer.write("      <td><div align=\"center\">\n");
            writer.write("        <h2>CREDIT NOTE </h2>\n");
            writer.write("      </div></td>\n");
            writer.write("      <td colspan=\"2\">&nbsp;</td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td>Credit Code:</td>\n");
            writer.write("      <td>"+creditCode+"</td>\n");
            writer.write("      <td>&nbsp;</td>\n");
            writer.write("      <td>Post Date:</td>\n");
            writer.write("      <td>"+postDate+"</td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td width=\"79\"><div align=\"left\">Order Code:</div></td>\n");
            writer.write("      <td width=\"160\"><div align=\"left\">"+orderCode+"</div></td>\n");
            writer.write("      <td>&nbsp;</td>\n");
            writer.write("      <td width=\"75\"><div align=\"left\">Order Date:</div></td>\n");
            writer.write("      <td width=\"254\"><div align=\"left\">"+orderDate+"</div></td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"35\"><div align=\"left\">Merchant:</div></td>\n");
            writer.write("      <td><p align=\"left\">"+creditBy+"</p></td>\n");
            writer.write("      <td>&nbsp;</td>\n");
            writer.write("      <td><div align=\"left\"></div></td>\n");
            writer.write("      <td><div align=\"left\"></div></td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"23\" colspan=\"5\">&nbsp;</td>\n");
            writer.write("    </tr>\n");
            writer.write("  </table>\n");
            writer.write("  <table width=\"803\" border=\"1\">\n");
            writer.write("    <tr>\n");
            writer.write("      <td width=\"25\" height=\"42\"><strong>S/N</strong></td>\n");
            writer.write("      <td width=\"120\"><strong>TRANSACTION TYPE</strong></td>\n");
            writer.write("      <td width=\"125\"><strong>M. CREDIT NUMBER</strong></td>\n");
            writer.write("      <td width=\"84\"><strong>M. CREDIT DATE</strong></td>\n");
            writer.write("      <td colspan=\"3\"><div align=\"center\"><strong>DESCRIPTION</strong></div>\n");
            writer.write("        <div align=\"center\"></div></td>\n");
            writer.write("      <td width=\"181\"><div align=\"center\"><strong>AMOUNT (=N=)</strong></div></td>\n");
            writer.write("    </tr>\n");

            int x=0;
            int y=serial.length;

            while(x<y)
            {
                String transactionTypeName=systems.getValue(OpenMSApp.Database_A, "Select * from TransactionType where cTransactionTypeCode='"+transactionType[x]+"'", "vTransactionType");
                writer.write("    <tr>\n");
                writer.write("      <td height=\"38\">"+serial[x]+"</td>\n");
                writer.write("      <td>"+transactionTypeName+"</td>\n");
                writer.write("      <td>"+creditNumber[x]+"</td>\n");
                writer.write("      <td>"+creditingDate[x]+"</td>\n");
                writer.write("      <td colspan=\"3\">"+description[x]+"</td>\n");
                writer.write("      <td>"+amount[x]+"</td>\n");
                writer.write("    </tr>\n");
                x+=1;
                if(x==y){break;}
            }

            writer.write("    <tr>\n");
            writer.write("      <td height=\"38\" colspan=\"7\"><div align=\"right\"><strong>TOTAL AMOUNT</strong></div></td>\n");
            writer.write("      <td>"+totalAmount+"</td>\n");
            writer.write("    </tr>\n");
            writer.write("</table>\n");
            writer.write("  <table width=\"801\" border=\"0\">\n");
            writer.write("    <tr>\n");
            writer.write("      <td width=\"231\" height=\"62\">&nbsp;</td>\n");
            writer.write("      <td colspan=\"2\">&nbsp;</td>\n");
            writer.write("      <td colspan=\"2\">&nbsp;</td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"67\" colspan=\"2\"><p align=\"center\">_____________________________</p>\n");
            writer.write("        <p align=\"center\">ORDER BY</p></td>\n");
            writer.write("      <td height=\"67\" colspan=\"2\"><p align=\"center\">_____________________________</p>\n");
            writer.write("        <p align=\"center\">FINANCE</p></td>\n");
            writer.write("      <td width=\"266\" height=\"67\"><p align=\"center\">_____________________________</p>\n");
            writer.write("        <p align=\"center\">APPROVED BY</p></td>\n");
            writer.write("    </tr>\n");
            writer.write("</table>\n");
            writer.write("<p>&nbsp;</p>\n");
            writer.write("  <p>&nbsp;</p>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeCreditNote gave error! "+e);
        }
        resetVariable();
    }
    public void writeDebitNote(String orderCode)
    {
            try
            {
                Connect.createMSSQLConnection();
                Connect.changeDB(OpenMSApp.Database_A);
                statement=Connect.createStatement("Select * from debitOrder where cDebitOrderCode= '"+orderCode+"'");
                result=Connect.executeRStatement(statement);
                result.next();
                debitByID=result.getString(2);
                debitCode=result.getString(3);
                postDate=result.getString(4);
                orderByID=result.getString(5);
                orderDate=result.getString(6);
                totalAmount=result.getString(9);

                Connect.closeConnection();
            }
            catch(Exception e)
            {
                System.out.println("Sql errror! (jComboBox1ActionPerformed) "+e);
            }

            debitNumber=systems.getColumn(OpenMSApp.Database_A,"select * from debitOrdersummary where cDebitOrderCode='"+orderCode+"'",2);
            debitingDate=systems.getColumn(OpenMSApp.Database_A,"select * from debitOrdersummary where cDebitOrderCode='"+orderCode+"'",3);
            transactionType=systems.getColumn(OpenMSApp.Database_A,"select * from debitOrdersummary where cDebitOrderCode='"+orderCode+"'",4);
            description=systems.getColumn(OpenMSApp.Database_A,"select * from debitOrdersummary where cDebitOrderCode='"+orderCode+"'",6);
            amount=systems.getColumn(OpenMSApp.Database_A," Select * from debitOrdersummary where cDebitOrderCode= '"+orderCode+"'",7);
            serial=systems.getColumn(OpenMSApp.Database_A," Select * from debitOrdersummary where cDebitOrderCode= '"+orderCode+"'",8);

            debitBy=systems.getValue(OpenMSApp.Database_A," Select * from vwMerchant where cMerchantID= '"+debitByID+"'","vName");
            String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+orderByID+"'","cEmployeeID");

            orderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID= '"+OrderEmployeeID+"'","vlastName").trim();

            orderDate=systems.getValue(OpenMSApp.Database_A,"Select * from debitOrder where cDebitOrderCode= '"+orderCode+"'","dOrderDate");
        try
          {
            file=new java.io.File("Note/DebitNote.htm");
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
            writer.write("<title>CREDIT NOTE <"+orderCode+"></title>\n");
            writer.write("</head>\n");

            writer.write("<body>\n");
            writer.write("  <table width=\"801\" border=\"0\">\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"95\" colspan=\"2\"><div align=\"center\">\n");
            writer.write("        <dl>\n");
            writer.write("          <dt><img src=\"Milliscript_logo.png\" width=\"114\" height=\"63\" alt=\"Company Logo\" /></dt>\n");
            writer.write("        </dl>\n");
            writer.write("      </div></td>\n");
            writer.write("      <td width=\"211\"><h1 align=\"center\">MILLISCRIPT</h1>\n");
            writer.write("        <dl>\n");
            writer.write("          <dt>\n");
            writer.write("            <h3 align=\"center\">IT ENTERPRISES</h3>\n");
            writer.write("          </dt>\n");
            writer.write("          <dt>&nbsp; </dt>\n");
            writer.write("          <dt>\n");
            writer.write("            <div align=\"center\"></div>\n");
            writer.write("          </dt>\n");
            writer.write("          <dt>&nbsp;          </dt>\n");
            writer.write("      </dl></td>\n");
            writer.write("      <td colspan=\"2\"><dl>\n");
            writer.write("        <dt>\n");
            writer.write("          <div align=\"center\">Plot 1 Aremu Close Owode Apata Ibadan.</div>\n");
            writer.write("        </dt>\n");
            writer.write("        <dd>\n");
            writer.write("          <div align=\"center\">G.P.O Box 18722 Dugbe Ibadan.</div>\n");
            writer.write("        </dd>\n");
            writer.write("        <dt>\n");
            writer.write("          <div align=\"center\">Tel: 08026891916</div>\n");
            writer.write("        </dt>\n");
            writer.write("      </dl></td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"47\" colspan=\"2\">&nbsp;</td>\n");
            writer.write("      <td><div align=\"center\">\n");
            writer.write("        <h2>DEBIT NOTE </h2>\n");
            writer.write("      </div></td>\n");
            writer.write("      <td colspan=\"2\">&nbsp;</td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td>Debit Code:</td>\n");
            writer.write("      <td>"+debitCode+"</td>\n");
            writer.write("      <td>&nbsp;</td>\n");
            writer.write("      <td>Post Date:</td>\n");
            writer.write("      <td>"+postDate+"</td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td width=\"79\"><div align=\"left\">Order Code:</div></td>\n");
            writer.write("      <td width=\"160\"><div align=\"left\">"+orderCode+"</div></td>\n");
            writer.write("      <td>&nbsp;</td>\n");
            writer.write("      <td width=\"75\"><div align=\"left\">Order Date:</div></td>\n");
            writer.write("      <td width=\"254\"><div align=\"left\">"+orderDate+"</div></td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"35\"><div align=\"left\">Merchant:</div></td>\n");
            writer.write("      <td><p align=\"left\">"+debitBy+"</p></td>\n");
            writer.write("      <td>&nbsp;</td>\n");
            writer.write("      <td><div align=\"left\"></div></td>\n");
            writer.write("      <td><div align=\"left\"></div></td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"23\" colspan=\"5\">&nbsp;</td>\n");
            writer.write("    </tr>\n");
            writer.write("  </table>\n");
            writer.write("  <table width=\"803\" border=\"1\">\n");
            writer.write("    <tr>\n");
            writer.write("      <td width=\"25\" height=\"42\"><strong>S/N</strong></td>\n");
            writer.write("      <td width=\"120\"><strong>TRANSACTION TYPE</strong></td>\n");
            writer.write("      <td width=\"125\"><strong>M. DEBIT NUMBER</strong></td>\n");
            writer.write("      <td width=\"84\"><strong>M. DEBIT DATE</strong></td>\n");
            writer.write("      <td colspan=\"3\"><div align=\"center\"><strong>DESCRIPTION</strong></div>\n");
            writer.write("        <div align=\"center\"></div></td>\n");
            writer.write("      <td width=\"181\"><div align=\"center\"><strong>AMOUNT (=N=)</strong></div></td>\n");
            writer.write("    </tr>\n");

            int x=0;
            int y=serial.length;

            while(x<y)
            {
                String transactionTypeName=systems.getValue(OpenMSApp.Database_A, "Select * from TransactionType where cTransactionTypeCode='"+transactionType[x]+"'", "vTransactionType");
                writer.write("    <tr>\n");
                writer.write("      <td height=\"38\">"+serial[x]+". </td>\n");
                writer.write("      <td>"+transactionTypeName+"</td>\n");
                writer.write("      <td>"+debitNumber[x]+"</td>\n");
                writer.write("      <td>"+debitingDate[x]+"</td>\n");
                writer.write("      <td colspan=\"3\">"+description[x]+"</td>\n");
                writer.write("      <td>"+amount[x]+"</td>\n");
                writer.write("    </tr>\n");
                x+=1;
                if(x==y){break;}
            }

            writer.write("    <tr>\n");
            writer.write("      <td height=\"38\" colspan=\"7\"><div align=\"right\"><strong>TOTAL AMOUNT</strong></div></td>\n");
            writer.write("      <td>"+totalAmount+"</td>\n");
            writer.write("    </tr>\n");
            writer.write("</table>\n");
            writer.write("  <table width=\"801\" border=\"0\">\n");
            writer.write("    <tr>\n");
            writer.write("      <td width=\"231\" height=\"62\">&nbsp;</td>\n");
            writer.write("      <td colspan=\"2\">&nbsp;</td>\n");
            writer.write("      <td colspan=\"2\">&nbsp;</td>\n");
            writer.write("    </tr>\n");
            writer.write("    <tr>\n");
            writer.write("      <td height=\"67\" colspan=\"2\"><p align=\"center\">_____________________________</p>\n");
            writer.write("        <p align=\"center\">ORDER BY</p></td>\n");
            writer.write("      <td height=\"67\" colspan=\"2\"><p align=\"center\">_____________________________</p>\n");
            writer.write("        <p align=\"center\">FINANCE</p></td>\n");
            writer.write("      <td width=\"266\" height=\"67\"><p align=\"center\">_____________________________</p>\n");
            writer.write("        <p align=\"center\">APPROVED BY</p></td>\n");
            writer.write("    </tr>\n");
            writer.write("</table>\n");
            writer.write("<p>&nbsp;</p>\n");
            writer.write("  <p>&nbsp;</p>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
            writer.flush();
            writer.close();
        }
        catch(Exception e)
        {
            System.out.println("NoteWriter.writeDebitNote gave error! "+e);
        }
        resetVariable();
    }
    public void writeSingleReceipt(String orderCode, String transferState){

            creditByID=systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "cMerchantID");
            orderByID=systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "cOrderByID");
            orderDate=systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "dOrderDate");
            try
            {
                totalAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "mTotalAmount"));
            }
            catch(Exception e)
            {
                totalAmount="Er!M1C01K0";
            }
            accountCode=systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "cAccountCode");
            transactionDate=systems.extractSQLDate(systems.getDateValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "dCreditDate"));
            transactionNumber=systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "vCreditingNumber");
            transactionTypeCode=systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "cTransactionTypeCode");
            costOrderCode=systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "cCostOrderCode");
            initialTobalance=totalAmount;
            try
            {
                currentTobalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from creditOrder where cCreditOrderCode='"+orderCode+"'", "mTobalance"));
            }
            catch(Exception e)
            {
                currentTobalance="Er!M1C01K0";
            }

            description=systems.getColumn(OpenMSApp.Database_A,"Select * from creditordersummary where cCreditOrderCode= '"+orderCode+"'",3);
            amount=systems.getColumn(OpenMSApp.Database_A,"Select * from creditordersummary where cCreditOrderCode='"+orderCode+"'",4);
            serial=systems.getColumn(OpenMSApp.Database_A,"Select * from creditordersummary where cCreditOrderCode='"+orderCode+"'",5);
            item=systems.getColumn(OpenMSApp.Database_A,"Select * from creditordersummary where cCreditOrderCode='"+orderCode+"'",2);


            creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+creditByID+"'","vTable").trim();
            if(creditByTable.equalsIgnoreCase("Employee"))
            {
            creditBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vlastName").trim();
            }
            else
            {
                creditBy=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+creditByID+"'","vName");
            }

            accountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+accountCode+"'","vAccountUnit");
            transactionType_B=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode='"+transactionTypeCode+"'","vTransactionType");
            String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+orderByID+"'","cEmployeeID");

            orderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

            orderDate=systems.getValue(OpenMSApp.Database_A,"Select * from creditOrder where cCreditOrderCode='"+orderCode+"'","dOrderDate");
            //netAmount=systems.getValue(OpenMSApp.Database_A,"Select * from vwCreditCostOrder where cCreditCostOrderCode='"+costOrderCode+"'","mNetAmount");
           try{
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
            writer.write("<title>Official Receipt '"+orderCode+"'</title>\n");
            writer.write("<link href=\""+Configuration.notePath+"\\style.css\" rel=\"stylesheet\" type=\"text/css\" />\n"); 
            writer.write("<style type=\"text/css\">\n");
            writer.write("<!--\n");
            writer.write("#mainWrapper {\n");
            writer.write("	width: 600px;\n");
            writer.write("	margin: auto;\n");
            writer.write("}\n");
            writer.write("table#listTable {\n");
            writer.write("	font: 0.9em Arial, Helvetica, sans-serif;\n");
            writer.write("	margin: 0px auto;\n");
            writer.write("	width: 98%;\n");
            writer.write("	border-collapse: collapse;\n");
            writer.write("}\n");
            writer.write("#mainWrapper textarea {\n");
            writer.write("	border: thin dotted #F00;\n");
            writer.write("}\n");
            writer.write("#mainWrapper input {\n");
            writer.write("	border: thin solid #F00;\n");   
            writer.write("}\n");
            writer.write("textarea {\n");
            writer.write("	border: thin dotted #F00;\n");
            writer.write("}\n");
            writer.write("input {\n");
            writer.write("	border: thin solid #F00;\n");
            writer.write("}\n");
            writer.write("#leftTopSpace {\n");
            writer.write("	float: left;\n");
            writer.write("	height: 100px;\n");
            writer.write("	width: 250px;\n");
            writer.write("}\n");
            writer.write("#customerNameSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 250px;\n");
            writer.write("}\n");
            writer.write("#invoiceNumberSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 250px;\n");
            writer.write("	float: inherit;\n");
            writer.write("}\n");
            writer.write("#receiptNoSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 130px;\n");
            writer.write("	float: left;\n");
            writer.write("	font: bold 14px \"Lucida Sans Unicode\", \"Lucida Grande\",sans-serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("}\n");
            writer.write("#comment {\n");
            writer.write("	font: bold 10px \"Lucida Sans Unicode\", \"Lucida Grande\",sans-serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("}\n");
            writer.write("#rightTopSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("	height: 100px;\n");
            writer.write("	width: 220px;\n");
            writer.write("}\n");
            writer.write("#dateSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("}\n");
            writer.write("#siteSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("}\n");
            writer.write("#tableSpace {\n");
            writer.write("	margin: 10px auto;\n");
            writer.write("	clear: both;\n");
            writer.write("	font-family: Georgia, \"Times New Roman\", Times, serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("	padding-top: 20px;\n");
            writer.write("	padding-bottom: 10px;\n");
            writer.write("}\n");
            writer.write("table#listTable th {\n");
            writer.write("	text-align: left;\n");
            writer.write("	padding-left: 10px;\n");
            writer.write("	font: normal 1em/30px Tahoma, Geneva, sans-serif;\n");
            writer.write("	background: #000;\n");
            //writer.write("	background: #41637b;\n");
            writer.write("	color: #FFF;\n");
            writer.write("	border-bottom: 0px solid #FFF;\n");
            writer.write("}\n");
            writer.write("#leftBottomSpace {\n");
            writer.write("	float: left;\n");
            writer.write("	height: 88px;\n");
            writer.write("	width: 300px;\n");
            writer.write("}\n");
            writer.write("#rightBottomSpace {\n");
            writer.write("	float: right;\n");
            writer.write("	width: 300px;\n");
            writer.write("}\n");
            writer.write("#sumReceivedSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#previousDueSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#toBalanceSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#transactionTypeSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#amountInWordSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#TransactionNumber {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	clear: both;\n");
            writer.write("}\n");
            writer.write("table#listTable td {\n");
            writer.write("	line-height: 12px;\n");
            writer.write("	padding-left: 10px;\n");
            writer.write("	text-align: left;\n");
            writer.write("	background: #FC0;\n");
            writer.write("}\n");
            writer.write("#serial {\n");
            writer.write("	background: #06F;\n");
            writer.write("}\n");
            writer.write("#item, #description {\n");
            writer.write("	background: #930;\n");
            writer.write("}\n");
            writer.write("#footImageSpace {\n");
            writer.write("	margin-top: 20px;\n");
            writer.write("	padding-top: 20px;\n");
            writer.write("	margin: auto;\n");
            writer.write("}\n");
            writer.write("-->\n");
            writer.write("</style>\n");
            writer.write("</head>\n");
            writer.write("\n");
            writer.write("<body>\n");
            writer.write("<div id=\"mainWrapper\">\n");
            writer.write("  <div id=\"headerImage\">\n");
            String accountCategory=systems.getValue(OpenMSApp.Database_A, "Select * from account where cAccountCode='"+accountCode+"'", "cCategory");
            if(transferState.equalsIgnoreCase(""))
            {
                if(accountCategory.equalsIgnoreCase("HYBRID")||accountCategory.equalsIgnoreCase("EXPENSE")||accountCategory.equalsIgnoreCase("REVENUE")||accountCategory.equalsIgnoreCase("PAYABLE")||accountCategory.equalsIgnoreCase("RECEIVABLE")){
                //writer.write("  <p><img src=\"official_receipt_head01.png\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                writer.write("  <p><img src=\""+Configuration.officialCashReceiptHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                }
                else if(accountCategory.equalsIgnoreCase("INVENTORY")||accountCategory.equalsIgnoreCase("INVENTORY PAYABLE")||accountCategory.equalsIgnoreCase("INVENTORY RECEIVABLE")){
                
                writer.write("  <p><img src=\""+Configuration.officialInventoryReceiptHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                }
                else if(accountCategory.equalsIgnoreCase("ADJUSTMENT PAYABLE")||accountCategory.equalsIgnoreCase("ADJUSTMENT RECEIVABLE")){
                
                writer.write("  <p><img src=\""+Configuration.officialAdjustmentReceiptHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                }
            }
            else
            {
                //writer.write("  <p><img src=\"official_subreceipt_head01.png\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                writer.write("  <p><img src=\""+Configuration.officialCashSubReceiptHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
            }
            writer.write("</div>\n");
            writer.write("<div id=\"leftTopSpace\">\n");
            writer.write("  <div id=\"customerNameSpace\" align=\"left\">\n");
            writer.write("    <textarea name=\"textarea\" cols=\"27\" rows=\"4\" readonly=\"readonly\"  wrap=\"physical\" id=\"textarea\">Customer Name:\n");
            writer.write(""+creditBy+"</textarea>\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"invoiceNumberSpace\">Invoice No.: \n");
            writer.write("    <input name=\"textfield3\" type=\"text\" id=\"textfield3\" value=\""+"N/A"+"\" size=\"20\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"invoiceAmountSpace\">Net Amount: \n");
            writer.write("     <input name=\"textfiel3\" type=\"text\" id=\"textfiel3\" value=\""+/*totalAmount*/"N/A"+"\" size=\"20\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"receiptNoSpace\">NO.: "+orderCode+"</div>\n");
            writer.write("<div id=\"rightTopSpace\">\n");
            writer.write("  <div id=\"dateSpace\">Date:\n");
            writer.write("    <input name=\"textfield2\" type=\"text\" id=\"textfield2\" value=\""+orderDate+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"siteSpace\">Order By:\n");
            writer.write("    <input name=\"textfield\" type=\"text\" id=\"textfield\" value=\""+orderBy+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"tableSpace\">\n");
            writer.write("  <table width=\"88%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" id=\"listTable\">\n");
            writer.write("  <colgroup > \n");
            writer.write("  <col id=\"serial\" />\n");
            writer.write("  <col id=\"item\" />\n");
            writer.write("  <col id=\"description\" />\n");
            writer.write("  <col id=\"amount\" />\n");
            writer.write("  </colgroup>\n");
            writer.write("    <tr>\n");
            writer.write("      <th width=\"53\" scope=\"col\"></th>\n");
            writer.write("      <th width=\"250\" scope=\"col\">ITEM</th>\n");
            writer.write("      <th width=\"313\" scope=\"col\">DESCRIPTION</th>\n");
            writer.write("      <th width=\"250\" scope=\"col\">AMOUNT ("+accountCurrency+")</th>\n");
            writer.write("    </tr>\n");


            int x=0;
            int y=serial.length;

            while(x<y)
            {
            String itemName=systems.getValue(OpenMSApp.Database_A, "Select * from Item where cItemCode='"+item[x].trim()+"'", "vName");

                writer.write("    <tr>\n");
                writer.write("      <th>"+serial[x]+".</th>\n");
                writer.write("      <td>"+itemName+"</td>\n");
                writer.write("      <td>"+description[x]+"</td>\n");
                try
                {
                    writer.write("      <td>"+systems.converToRealMoneyFormat(amount[x])+"</td>\n");
                }
                catch(Exception e)
                {
                    writer.write("      <td>"+"Er!M1C01K0"+"</td>\n");
                }
                writer.write("    </tr>\n");
                x+=1;
                if(x==y){break;}
            }

            /*
            writer.write("    <tr>\n");
            writer.write("      <th>1.</th>\n");
            writer.write("      <td>piop</td>\n");
            writer.write("      <td>dfdfdfff bc hghh</td>\n");
            writer.write("    </tr>\n");
            */

            writer.write("  </table>\n");
            writer.write("<div id=\"comment\">(*T=VAT, *Q=QUANTITY, *R=RATE)</div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"leftBottomSpace\">\n");
            writer.write("  <div id=\"sumReceivedSpace\">Sum Received:      &nbsp; &nbsp; &nbsp; &nbsp;\n");
            writer.write("    <input name=\"textfield4\" type=\"text\" id=\"textfield4\" value=\""+totalAmount+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"previousDueSpace\">Amount Due:  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;\n");
            writer.write("    <input name=\"textfield5\" type=\"text\" id=\"textfield5\" value=\""+
            systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+currentTobalance+" , "+totalAmount.trim()+Connect.procInitEnd, "mSumValue"))+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"toBalanceSpace\">To Balance:  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;\n");
            writer.write("    <input name=\"textfield6\" type=\"text\" id=\"textfield6\" value=\""+currentTobalance+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"transactionTypeSpace\">Transaction Type:  &nbsp; &nbsp;\n");
            writer.write("    <input name=\"textfield7\" type=\"text\" id=\"textfield7\" value=\""+transactionType_B+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"rightBottomSpace\">\n");
            writer.write("  <div id=\"amountInWordSpace\">\n");
            writer.write("    <textarea name=\"textarea2\" cols=\"32\" rows=\"5\" readonly=\"readonly\" wrap=\"hard\" id=\"textarea2\">"+accountCurrency+"</textarea>\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"TransactionNumber\">\n");
            writer.write("  <p>Transaction Type S/N &amp; Date:\n");
            writer.write("    <input name=\"textfield8\" type=\"text\" id=\"textfield8\" value=\""+transactionNumber+" ["+transactionDate+"]\" size=\"60\" readonly=\"readonly\" />\n");
            writer.write("  </p>\n");
            writer.write("</div>\n");
            writer.write("<p>&nbsp;</p>\n");
            //writer.write("<div id=\"footImageSpace\"><img src=\"official_receipt_foot01.png\" width=\"529\" height=\"48\" alt=\"officialReceiptFooter\"/></div></div>\n");

            writer.write("<div id=\"footImageSpace\"><img src=\""+Configuration.officialCashReceiptFooterImageURLPath+"\" width=\"529\" height=\"48\" alt=\"officialReceiptFooter\"/></div></div>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
        }catch(Exception e){System.out.println("Error occured while writing receipt: "+e);}
    }
    public void writeSingleInvoice(String orderCode, String transferState){

            creditByID=systems.getValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "cMerchantID");
            orderByID=systems.getValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "cOrderByID");
            orderDate=systems.getValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "dOrderDate");
            try
            {
                totalAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "mTotalAmount"));
            }
            catch(Exception e)
            {
                totalAmount="Er!M1C01K0";
            }
            accountCode=systems.getValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "cAccountCode");
            transactionDate=systems.extractSQLDate(systems.getDateValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "dCreditDate"));
            transactionNumber=systems.getValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "vCreditingNumber");
            transactionTypeCode=systems.getValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "cTransactionTypeCode");

            initialTobalance=totalAmount;
            try
            {
                currentTobalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'", "mPaid"));
            }
            catch(Exception e)
            {
                currentTobalance="Er!M1C01K0";
            }

            description=systems.getColumn(OpenMSApp.Database_A,"Select * from Invoiceordersummary where cInvoiceOrderCode= '"+orderCode+"'",3);
            amount=systems.getColumn(OpenMSApp.Database_A,"Select * from Invoiceordersummary where cInvoiceOrderCode='"+orderCode+"'",4);
            serial=systems.getColumn(OpenMSApp.Database_A,"Select * from Invoiceordersummary where cInvoiceOrderCode='"+orderCode+"'",5);
            item=systems.getColumn(OpenMSApp.Database_A,"Select * from Invoiceordersummary where cInvoiceOrderCode='"+orderCode+"'",2);


            creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+creditByID+"'","vTable").trim();
            if(creditByTable.equalsIgnoreCase("Employee"))
            {
            creditBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vlastName").trim();
            }
            else
            {
                creditBy=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+creditByID+"'","vName");
            }

            accountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+accountCode+"'","vAccountUnit");
            transactionType_B=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode='"+transactionTypeCode+"'","vTransactionType");
            String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+orderByID+"'","cEmployeeID");

            orderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

            orderDate=systems.getValue(OpenMSApp.Database_A,"Select * from InvoiceOrder where cInvoiceOrderCode='"+orderCode+"'","dOrderDate");
            //netAmount=systems.getValue(OpenMSApp.Database_A,"Select * from vwInvoiceCostOrder where cInvoiceCostOrderCode='"+costOrderCode+"'","mNetAmount");
           try{
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
            writer.write("<title>Official Invoice '"+orderCode+"'</title>\n");        
            writer.write("<link href=\""+Configuration.notePath+"\\style.css\" rel=\"stylesheet\" type=\"text/css\" />\n");        
            writer.write("<style type=\"text/css\">\n");
            writer.write("<!--\n");
            writer.write("#mainWrapper {\n");
            writer.write("	width: 600px;\n");
            writer.write("	margin: auto;\n");
            writer.write("}\n");
            writer.write("table#listTable {\n");
            writer.write("	font: 0.9em Arial, Helvetica, sans-serif;\n");
            writer.write("	margin: 0px auto;\n");
            writer.write("	width: 98%;\n");
            writer.write("	border-collapse: collapse;\n");
            writer.write("}\n");
            writer.write("#mainWrapper textarea {\n");
            writer.write("	border: thin dotted #F00;\n");
            writer.write("}\n");
            writer.write("#mainWrapper input {\n");
            writer.write("	border: thin solid #F00;\n");
            writer.write("}\n");
            writer.write("textarea {\n");
            writer.write("	border: thin dotted #F00;\n");
            writer.write("}\n");
            writer.write("input {\n");
            writer.write("	border: thin solid #F00;\n");
            writer.write("}\n");
            writer.write("#leftTopSpace {\n");
            writer.write("	float: left;\n");
            writer.write("	height: 100px;\n");
            writer.write("	width: 250px;\n");
            writer.write("}\n");
            writer.write("#customerNameSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 250px;\n");
            writer.write("}\n");
            writer.write("#invoiceNumberSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 250px;\n");
            writer.write("	float: inherit;\n");
            writer.write("}\n");
            writer.write("#receiptNoSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 130px;\n");
            writer.write("	float: left;\n");
            writer.write("	font: bold 14px \"Lucida Sans Unicode\", \"Lucida Grande\",sans-serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("}\n");
            writer.write("#comment {\n");
            writer.write("	font: bold 10px \"Lucida Sans Unicode\", \"Lucida Grande\",sans-serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("}\n");
            writer.write("#rightTopSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("	height: 100px;\n");
            writer.write("	width: 220px;\n");
            writer.write("}\n");
            writer.write("#dateSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("}\n");
            writer.write("#siteSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("}\n");
            writer.write("#tableSpace {\n");
            writer.write("	margin: 10px auto;\n");
            writer.write("	clear: both;\n");
            writer.write("	font-family: Georgia, \"Times New Roman\", Times, serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("	padding-top: 20px;\n");
            writer.write("	padding-bottom: 10px;\n");
            writer.write("}\n");
            writer.write("table#listTable th {\n");
            writer.write("	text-align: left;\n");
            writer.write("	padding-left: 10px;\n");
            writer.write("	font: normal 1em/30px Tahoma, Geneva, sans-serif;\n");
            writer.write("	background: #000;\n");
            //writer.write("	background: #41637b;\n");
            writer.write("	color: #FFF;\n");
            writer.write("	border-bottom: 0px solid #FFF;\n");
            writer.write("}\n");
            writer.write("#leftBottomSpace {\n");
            writer.write("	float: left;\n");
            writer.write("	height: 88px;\n");
            writer.write("	width: 300px;\n");
            writer.write("}\n");
            writer.write("#rightBottomSpace {\n");
            writer.write("	float: right;\n");
            writer.write("	width: 300px;\n");
            writer.write("}\n");
            writer.write("#sumReceivedSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#previousDueSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#toBalanceSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#transactionTypeSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#amountInWordSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#TransactionNumber {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	clear: both;\n");
            writer.write("}\n");
            writer.write("table#listTable td {\n");
            writer.write("	line-height: 12px;\n");
            writer.write("	padding-left: 10px;\n");
            writer.write("	text-align: left;\n");
            writer.write("	background: #FC0;\n");
            writer.write("}\n");
            writer.write("#serial {\n");
            writer.write("	background: #06F;\n");
            writer.write("}\n");
            writer.write("#item, #description {\n");
            writer.write("	background: #930;\n");
            writer.write("}\n");
            writer.write("#footImageSpace {\n");
            writer.write("	margin-top: 20px;\n");
            writer.write("	padding-top: 20px;\n");
            writer.write("	margin: auto;\n");
            writer.write("}\n");
            writer.write("-->\n");
            writer.write("</style>\n");
            writer.write("</head>\n");
            writer.write("\n");
            writer.write("<body>\n");
            writer.write("<div id=\"mainWrapper\">\n");
            writer.write("  <div id=\"headerImage\">\n");
            String accountCategory=systems.getValue(OpenMSApp.Database_A, "Select * from account where cAccountCode='"+accountCode+"'", "cCategory");
    
                writer.write("  <p><img src=\""+Configuration.officialInvoiceHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                
            writer.write("</div>\n");
            writer.write("<div id=\"leftTopSpace\">\n");
            writer.write("  <div id=\"customerNameSpace\" align=\"left\">\n");
            writer.write("    <textarea name=\"textarea\" cols=\"27\" rows=\"4\" readonly=\"readonly\"  wrap=\"physical\" id=\"textarea\">Customer Name:\n");
            writer.write(""+creditBy+"</textarea>\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"invoiceNumberSpace\">Invoice No.: \n");
            writer.write("    <input name=\"textfield3\" type=\"text\" id=\"textfield3\" value=\""+"N/A"+"\" size=\"20\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"invoiceAmountSpace\">Net Amount: \n");
            writer.write("     <input name=\"textfiel3\" type=\"text\" id=\"textfiel3\" value=\""+/*totalAmount*/"N/A"+"\" size=\"20\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"receiptNoSpace\">NO.: "+orderCode+"</div>\n");
            writer.write("<div id=\"rightTopSpace\">\n");
            writer.write("  <div id=\"dateSpace\">Date:\n");
            writer.write("    <input name=\"textfield2\" type=\"text\" id=\"textfield2\" value=\""+orderDate+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"siteSpace\">Order By:\n");
            writer.write("    <input name=\"textfield\" type=\"text\" id=\"textfield\" value=\""+orderBy+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"tableSpace\">\n");
            writer.write("  <table width=\"88%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" id=\"listTable\">\n");
            writer.write("  <colgroup > \n");
            writer.write("  <col id=\"serial\" />\n");
            writer.write("  <col id=\"item\" />\n");
            writer.write("  <col id=\"description\" />\n");
            writer.write("  <col id=\"amount\" />\n");
            writer.write("  </colgroup>\n");
            writer.write("    <tr>\n");
            writer.write("      <th width=\"53\" scope=\"col\"></th>\n");
            writer.write("      <th width=\"250\" scope=\"col\">ITEM</th>\n");
            writer.write("      <th width=\"313\" scope=\"col\">DESCRIPTION</th>\n");
            writer.write("      <th width=\"250\" scope=\"col\">AMOUNT ("+accountCurrency+")</th>\n");
            writer.write("    </tr>\n");


            int x=0;
            int y=serial.length;

            while(x<y)
            {
            String itemName=systems.getValue(OpenMSApp.Database_A, "Select * from Item where cItemCode='"+item[x].trim()+"'", "vName");

                writer.write("    <tr>\n");
                writer.write("      <th>"+serial[x]+".</th>\n");
                writer.write("      <td>"+itemName+"</td>\n");
                writer.write("      <td>"+description[x]+"</td>\n");
                try
                {
                    writer.write("      <td>"+systems.converToRealMoneyFormat(amount[x])+"</td>\n");
                }
                catch(Exception e)
                {
                    writer.write("      <td>"+"Er!M1C01K0"+"</td>\n");
                }
                writer.write("    </tr>\n");
                x+=1;
                if(x==y){break;}
            }

            /*
            writer.write("    <tr>\n");
            writer.write("      <th>1.</th>\n");
            writer.write("      <td>piop</td>\n");
            writer.write("      <td>dfdfdfff bc hghh</td>\n");
            writer.write("    </tr>\n");
            */

            writer.write("  </table>\n");
            writer.write("<div id=\"comment\">(*T=VAT, *Q=QUANTITY, *R=RATE)</div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"leftBottomSpace\">\n");
            writer.write("  <div id=\"sumReceivedSpace\">Sum Received:      &nbsp; &nbsp; &nbsp; &nbsp;\n");
            writer.write("    <input name=\"textfield4\" type=\"text\" id=\"textfield4\" value=\""+totalAmount+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"previousDueSpace\">Amount Due:  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;\n");
            writer.write("    <input name=\"textfield5\" type=\"text\" id=\"textfield5\" value=\""+
            systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+currentTobalance+" , "+totalAmount.trim()+Connect.procInitEnd, "mSumValue"))+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"toBalanceSpace\">To Balance:  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;\n");
            writer.write("    <input name=\"textfield6\" type=\"text\" id=\"textfield6\" value=\""+currentTobalance+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"transactionTypeSpace\">Transaction Type:  &nbsp; &nbsp;\n");
            writer.write("    <input name=\"textfield7\" type=\"text\" id=\"textfield7\" value=\""+transactionType_B+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"rightBottomSpace\">\n");
            writer.write("  <div id=\"amountInWordSpace\">\n");
            writer.write("    <textarea name=\"textarea2\" cols=\"32\" rows=\"5\" readonly=\"readonly\" wrap=\"hard\" id=\"textarea2\">"+accountCurrency+"</textarea>\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"TransactionNumber\">\n");
            writer.write("  <p>Transaction Type S/N &amp; Date:\n");
            writer.write("    <input name=\"textfield8\" type=\"text\" id=\"textfield8\" value=\""+transactionNumber+" ["+transactionDate+"]\" size=\"60\" readonly=\"readonly\" />\n");
            writer.write("  </p>\n");
            writer.write("</div>\n");
            writer.write("<p>&nbsp;</p>\n");
            //writer.write("<div id=\"footImageSpace\"><img src=\"official_receipt_foot01.png\" width=\"529\" height=\"48\" alt=\"officialReceiptFooter\"/></div></div>\n");

            writer.write("<div id=\"footImageSpace\"><img src=\""+Configuration.officialCashReceiptFooterImageURLPath+"\" width=\"529\" height=\"48\" alt=\"officialReceiptFooter\"/></div></div>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
        }catch(Exception e){System.out.println("Error occured while writing receipt: "+e);}
    }
    public void writeSingleVoucher(String orderCode,String transferState)
    {

            creditByID=systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "cMerchantID");
            orderByID=systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "cOrderByID");
            orderDate=systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "dOrderDate");
            try
            {
                totalAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "mTotalAmount"));
            }
            catch(Exception e)
            {
                totalAmount="Er!M1C01K0";
            }
            accountCode=systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "cAccountCode");
            transactionDate=systems.extractSQLDate(systems.getDateValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "dDebitDate"));
            transactionNumber=systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "vDebitingNumber");
            transactionTypeCode=systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "cTransactionTypeCode");
            costOrderCode=systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "cCostOrderCode");
            initialTobalance=totalAmount;
            try
            {
                currentTobalance=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select * from debitOrder where cDebitOrderCode='"+orderCode+"'", "mTobalance"));
            }
            catch(Exception e)
            {
                currentTobalance="Er!M1C01K0";
            }

            description=systems.getColumn(OpenMSApp.Database_A,"Select * from debitordersummary where cDebitOrderCode= '"+orderCode+"'",3);
            amount=systems.getColumn(OpenMSApp.Database_A,"Select * from debitordersummary where cDebitOrderCode='"+orderCode+"'",4);
            serial=systems.getColumn(OpenMSApp.Database_A,"Select * from debitordersummary where cDebitOrderCode='"+orderCode+"'",5);
            item=systems.getColumn(OpenMSApp.Database_A,"Select * from debitordersummary where cDebitOrderCode='"+orderCode+"'",2);


            creditByTable=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+creditByID+"'","vTable").trim();
            if(creditByTable.equalsIgnoreCase("Employee"))
            {
            creditBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+creditByID+"'","vlastName").trim();
            }
            else
            {
                creditBy=systems.getValue(OpenMSApp.Database_A,"Select * from vwMerchant where cMerchantID='"+creditByID+"'","vName");
            }

            accountCurrency=systems.getValue(OpenMSApp.Database_A,"Select * from Account where cAccountCode='"+accountCode+"'","vAccountUnit");
            transactionType_B=systems.getValue(OpenMSApp.Database_A,"Select * from TransactionType where cTransactionTypeCode='"+transactionTypeCode+"'","vTransactionType");
            String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select * from login where cLoginCode= '"+orderByID+"'","cEmployeeID");

            orderBy=systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                    systems.getValue(OpenMSApp.Database_A,"Select * from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();

            orderDate=systems.getValue(OpenMSApp.Database_A,"Select * from debitOrder where cDebitOrderCode='"+orderCode+"'","dOrderDate");
            //netAmount=systems.getValue(OpenMSApp.Database_A,"Select * from vwDebitCostOrder where cDebitCostOrderCode='"+costOrderCode+"'","mNetAmount");
            try{
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
            writer.write("<title>Official Voucher '"+orderCode+"'</title>\n");
            writer.write("<style type=\"text/css\">\n");
            writer.write("<!--\n");
            writer.write("#mainWrapper {\n");
            writer.write("	width: 600px;\n");
            writer.write("	margin: auto;\n");
            writer.write("}\n");
            writer.write("table#listTable {\n");
            writer.write("	font: 0.9em Arial, Helvetica, sans-serif;\n");
            writer.write("	margin: 0px auto;\n");
            writer.write("	width: 98%;\n");
            writer.write("	border-collapse: collapse;\n");
            writer.write("}\n");
            writer.write("#mainWrapper textarea {\n");
            writer.write("	border: thin dotted #F00;\n");
            writer.write("}\n");
            writer.write("#mainWrapper input {\n");
            writer.write("	border: thin solid #F00;\n");
            writer.write("}\n");
            writer.write("textarea {\n");
            writer.write("	border: thin dotted #F00;\n");
            writer.write("}\n");
            writer.write("input {\n");
            writer.write("	border: thin solid #F00;\n");
            writer.write("}\n");
            writer.write("#leftTopSpace {\n");
            writer.write("	float: left;\n");
            writer.write("	height: 100px;\n");
            writer.write("	width: 250px;\n");
            writer.write("}\n");
            writer.write("#comment {\n");
            writer.write("	font: bold 14px \"Lucida Sans Unicode\", \"Lucida Grande\",sans-serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("}\n");
            writer.write("#customerNameSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 250px;\n");
            writer.write("}\n");
            writer.write("#invoiceNumberSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 250px;\n");
            writer.write("	float: inherit;\n");
            writer.write("}\n");
            writer.write("#receiptNoSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	width: 130px;\n");
            writer.write("	float: left;\n");
            writer.write("	font: bold 14px \"Lucida Sans Unicode\", \"Lucida Grande\",sans-serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("}\n");
            writer.write("#rightTopSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("	height: 100px;\n");
            writer.write("	width: 220px;\n");
            writer.write("}\n");
            writer.write("#dateSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("}\n");
            writer.write("#siteSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	float: right;\n");
            writer.write("}\n");
            writer.write("#tableSpace {\n");
            writer.write("	margin: 10px auto;\n");
            writer.write("	clear: both;\n");
            writer.write("	font-family: Georgia, \"Times New Roman\", Times, serif;\n");
            writer.write("	text-align: center;\n");
            writer.write("	padding-top: 20px;\n");
            writer.write("	padding-bottom: 10px;\n");
            writer.write("}\n");
            writer.write("table#listTable th {\n");
            writer.write("	text-align: left;\n");
            writer.write("	padding-left: 10px;\n");
            writer.write("	font: normal 1em/30px Tahoma, Geneva, sans-serif;\n");
            writer.write("	background: #000;\n");
            //writer.write("	background: #41637b;\n");
            writer.write("	color: #FFF;\n");
            writer.write("	border-bottom: 0px solid #FFF;\n");
            writer.write("}\n");
            writer.write("#leftBottomSpace {\n");
            writer.write("	float: left;\n");
            writer.write("	height: 88px;\n");
            writer.write("	width: 300px;\n");
            writer.write("}\n");
            writer.write("#rightBottomSpace {\n");
            writer.write("	float: right;\n");
            writer.write("	width: 300px;\n");
            writer.write("}\n");
            writer.write("#sumReceivedSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#previousDueSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#toBalanceSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#transactionTypeSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#amountInWordSpace {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("}\n");
            writer.write("#TransactionNumber {\n");
            writer.write("	margin: 0px;\n");
            writer.write("	padding: 0px;\n");
            writer.write("	clear: both;\n");
            writer.write("}\n");
            writer.write("table#listTable td {\n");
            writer.write("	line-height: 12px;\n");
            writer.write("	padding-left: 10px;\n");
            writer.write("	text-align: left;\n");
            writer.write("	background: #FC0;\n");
            writer.write("}\n");
            writer.write("#serial {\n");
            writer.write("	background: #06F;\n");
            writer.write("}\n");
            writer.write("#item, #description {\n");
            writer.write("	background: #930;\n");
            writer.write("}\n");
            writer.write("#footImageSpace {\n");
            writer.write("	margin-top: 20px;\n");
            writer.write("	padding-top: 20px;\n");
            writer.write("	margin: auto;\n");
            writer.write("}\n");
            writer.write("-->\n");
            writer.write("</style>\n");
            writer.write("</head>\n");
            writer.write("\n");
            writer.write("<body>\n");
            writer.write("<SCRIPT LANGUAGE=\"JavaScript\">\n");
            writer.write("window.print();\n");
            writer.write("</script>\n");
            writer.write("<div id=\"mainWrapper\">\n");
            writer.write("  <div id=\"headerImage\">\n");
            String accountCategory=systems.getValue(OpenMSApp.Database_A, "Select * from account where cAccountCode='"+accountCode+"'", "cCategory");
            if(transferState.equalsIgnoreCase(""))
            {                                
                if(accountCategory.equalsIgnoreCase("HYBRID")||accountCategory.equalsIgnoreCase("EXPENSE")||accountCategory.equalsIgnoreCase("REVENUE")||accountCategory.equalsIgnoreCase("PAYABLE")||accountCategory.equalsIgnoreCase("RECEIVABLE")){
                //writer.write("  <p><img src=\"official_voucher_head01.png\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                writer.write("  <p><img src=\""+Configuration.officialCashVoucherHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                }
                else if(accountCategory.equalsIgnoreCase("INVENTORY")||accountCategory.equalsIgnoreCase("INVENTORY PAYABLE")||accountCategory.equalsIgnoreCase("INVENTORY RECEIVABLE")){
                
                writer.write("  <p><img src=\""+Configuration.officialInventoryVoucherHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                }
                else if(accountCategory.equalsIgnoreCase("ADJUSTMENT PAYABLE")||accountCategory.equalsIgnoreCase("ADJUSTMENT RECEIVABLE")){
                
                writer.write("  <p><img src=\""+Configuration.officialAdjustmentVoucherHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                }
            }
            else
            {
                //writer.write("  <p><img src=\"official_subvoucher_head01.png\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
                writer.write("  <p><img src=\""+Configuration.officialCashSubVoucherHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"header\" /></p>\n");
            }
            writer.write("</div>\n");
            writer.write("<div id=\"leftTopSpace\">\n");
            writer.write("  <div id=\"customerNameSpace\" align=\"left\">\n");
            writer.write("    <textarea name=\"textarea\" cols=\"27\" rows=\"4\" readonly=\"readonly\"  wrap=\"physical\" id=\"textarea\">Customer Name:\n");
            writer.write(""+creditBy+"</textarea>\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"invoiceNumberSpace\">Invoice No.: \n");
            writer.write("    <input name=\"textfield3\" type=\"text\" id=\"textfield3\" value=\""+"N/A"+"\" size=\"20\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"invoiceAmountSpace\">Net Amount: \n");
            writer.write("     <input name=\"textfiel3\" type=\"text\" id=\"textfiel3\" value=\""+/*totalAmount*/"N/A"+"\" size=\"20\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"receiptNoSpace\">NO.: "+orderCode+"</div>\n");
            writer.write("<div id=\"rightTopSpace\">\n");
            writer.write("  <div id=\"dateSpace\">Date:\n");
            writer.write("    <input name=\"textfield2\" type=\"text\" id=\"textfield2\" value=\""+orderDate+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"siteSpace\">Order By:\n");
            writer.write("    <input name=\"textfield\" type=\"text\" id=\"textfield\" value=\""+orderBy+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"tableSpace\">\n");
            writer.write("  <table width=\"88%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" id=\"listTable\">\n");
            writer.write("  <colgroup > \n");
            writer.write("  <col id=\"serial\" />\n");
            writer.write("  <col id=\"item\" />\n");
            writer.write("  <col id=\"description\" />\n");
            writer.write("  <col id=\"amount\" />\n");
            writer.write("  </colgroup>\n");
            writer.write("    <tr>\n");
            writer.write("      <th width=\"53\" scope=\"col\"></th>\n");
            writer.write("      <th width=\"250\" scope=\"col\">ITEM</th>\n");
            writer.write("      <th width=\"313\" scope=\"col\">DESCRIPTION</th>\n");
            writer.write("      <th width=\"250\" scope=\"col\">AMOUNT ("+accountCurrency+")</th>\n");
            writer.write("    </tr>\n");


            int x=0;
            int y=serial.length;

            while(x<y)
            {
            String itemName=systems.getValue(OpenMSApp.Database_A, "Select * from Item where cItemCode='"+item[x].trim()+"'", "vName");

                writer.write("    <tr>\n");
                writer.write("      <th>"+serial[x]+".</td>\n");
                writer.write("      <td>"+itemName+"</td>\n");
                writer.write("      <td>"+description[x]+"</td>\n");
                try
                {
                    writer.write("      <td>"+systems.converToRealMoneyFormat(amount[x])+"</td>\n");
                }
                catch(Exception e)
                {
                    writer.write("      <td>"+"Er!M1C01K0"+"</td>\n");
                }
                writer.write("    </tr>\n");
                x+=1;
                if(x==y){break;}
            }

            /*
            writer.write("    <tr>\n");
            writer.write("      <th>1.</th>\n");
            writer.write("      <td>piop</td>\n");
            writer.write("      <td>dfdfdfff bc hghh</td>\n");
            writer.write("    </tr>\n");
            */

            writer.write("  </table>\n");
            writer.write("<div id=\"comment\">(*T=VAT, *Q=QUANTITY, *R=RATE)</div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"leftBottomSpace\">\n");
            writer.write("  <div id=\"sumReceivedSpace\">Sum Issued:      &nbsp; &nbsp; &nbsp; &nbsp;\n");
            writer.write("    <input name=\"textfield4\" type=\"text\" id=\"textfield4\" value=\""+totalAmount+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"previousDueSpace\">Amount Due:  &nbsp;  &nbsp; &nbsp; &nbsp; &nbsp; \n");
            writer.write("    <input name=\"textfield5\" type=\"text\" id=\"textfield5\" value=\""+
            systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+currentTobalance+" , "+totalAmount.trim()+Connect.procInitEnd, "mSumValue"))+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"toBalanceSpace\">To Balance:  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;\n");
            writer.write("    <input name=\"textfield6\" type=\"text\" id=\"textfield6\" value=\""+currentTobalance+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("  <div id=\"transactionTypeSpace\">Transaction Type:  &nbsp; &nbsp;\n");
            writer.write("    <input name=\"textfield7\" type=\"text\" id=\"textfield7\" value=\""+transactionType_B+"\" readonly=\"readonly\" />\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"rightBottomSpace\">\n");
            writer.write("  <div id=\"amountInWordSpace\">\n");
            writer.write("    <textarea name=\"textarea2\" cols=\"32\" rows=\"5\" readonly=\"readonly\" wrap=\"hard\" id=\"textarea2\">"+accountCurrency+"</textarea>\n");
            writer.write("  </div>\n");
            writer.write("</div>\n");
            writer.write("<div id=\"TransactionNumber\">\n");
            writer.write("  <p>Transaction Type S/N &amp; Date:\n");
            writer.write("    <input name=\"textfield8\" type=\"text\" id=\"textfield8\" value=\""+transactionNumber+" ["+transactionDate+"]\" size=\"60\" readonly=\"readonly\" />\n");
            writer.write("  </p>\n");
            writer.write("</div>\n");
            writer.write("<p>&nbsp;</p>\n");
            //writer.write("<div id=\"footImageSpace\"><img src=\"official_receipt_foot01.png\" width=\"529\" height=\"48\" alt=\"officialReceiptFooter\"/></div></div>\n");

            writer.write("<div id=\"footImageSpace\"><img src=\""+Configuration.officialCashReceiptFooterImageURLPath+"\" width=\"529\" height=\"48\" alt=\"officialReceiptFooter\"/></div></div>\n");
            writer.write("</body>\n");
            writer.write("</html>\n");
            }catch(Exception e){System.out.println("Error occur while writing voucher: "+e);}
     }

    public void writeIncomeStatement(String statementType,String accountName,String accountUnit,String merchantID,String activity,String transferActivityState,String startDate,String endDate)
    {
        String statementMySQLAccountColumn="2";
        String tranferActivityComment="";
        try
        {
            file=new java.io.File(Configuration.incomeStatementNotePath);
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


        writer.write("table#reportSummary {\n");
        writer.write("	font-family: Verdana, Geneva, sans-serif;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	border-collapse: collapse;\n");
        writer.write("}\n");
        writer.write("#reportSummary tr th {\n");
        writer.write("	color: #FFF;\n");
        writer.write("	background: #003C00;\n");
        writer.write("	font-family: Georgia, \"Times New Roman\", Times, serif;\n");
        writer.write("	text-align: left;\n");
        writer.write("	padding-left: 5px;\n");
        writer.write("	font-weight: normal;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	font: normal 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
        writer.write("}\n");
        writer.write("#reportSummary tr td {\n");
        writer.write("	padding-left: 5px;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	background: #FF9;\n");
        writer.write("	font: normal 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
        writer.write("}\n");
        writer.write("table#reportSummary tr:hover {\n");
        writer.write("	background: #060;\n");
        writer.write("	color: #FFF;\n");
        writer.write("}\n");
        writer.write("#reportSummary tr th.demacate {\n");
        writer.write("	background: #090;\n");
        writer.write("	color: #FF0;\n");
        writer.write("}\n");

    writer.write("-->\n");
    writer.write("</style>\n");
            writer.write("</head>\n");

            writer.write("<body>\n");
            writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
            writer.write("  <tr>\n");
            
            writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.incomeStatementHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"statementOfAccount\" /></div></td>\n");


            writer.write("  </tr>\n");
            writer.write("</table>\n");
            
            if(transferActivityState.isEmpty()==false)
            {
                tranferActivityComment=" (TRANSFER ACTIVITY EXCLUDED) ";
            }
            if(statementType.equalsIgnoreCase("FULL_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+" order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 6);


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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");

                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
                            
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"234\" scope=\"col\"><strong>TOTAL DEBITS</strong></th>\n");
                writer.write("    <th width=\"234\" scope=\"col\"><strong>TOTAL CREDITS</strong></th>\n");
                writer.write("    <th width=\"234\" scope=\"col\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"234\" scope=\"col\"><strong>PURCHASE/OPENING STOCK</strong></th>\n");
                writer.write("    <th width=\"234\" scope=\"col\"><strong>SALE/CLOSING STOCK</strong></th>\n");
               
            }
                writer.write("    <th width=\"150\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\" scope=\"col\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="0.00";
                    String statementTotalDebit="0.00";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String closingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mAccountBalance from pay where cAccountCode='"+statementSumAccountCode[xs]+"' ", 1);
                        
                        String openingStock=systems.getValue(OpenMSApp.Database_A, "Select sum(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' and cTransactionType='CNP'  and cStatus='posted'", 1);
                        String openingStockNeg=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, "Select sum(mTotalAmount) from debitOrder where cAccountCode='"+statementSumAccountCode[xs]+"' and cTransactionType='DNP'  and cStatus='posted'", 1));
                        openingStock=systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            openingStock+" , "+openingStockNeg+Connect.procInitEnd,"mSumValue");
                        if(closingStock.length>=1){
                            statementTotalDebit=openingStock;
                            statementTotalCredit=closingStock[closingStock.length-1];
                        }else{
                            statementTotalCredit="0.00";
                            statementTotalDebit="0.00";
                        }
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                        
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue");
                        System.out.println("Statement total credit="+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormatNeg(statementTotalCredit);
                        //statementTotalCredit=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                          //  statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                        
                        statementTotalDebit=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                            statementTotalCredit=systems.converToRealMoneyFormatNeg(statementTotalCredit);
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
                        statementTotalDebit=systems.converToRealMoneyFormatNeg(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalDebit)+"</td>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalCredit)+"</td>\n");
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else
//            {
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//                      
//            }
            
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                                           
                    }
                    
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.setAccountingFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue"))+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 6);

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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                String demacate="N";
                
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>PURCHASE/OPENING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>SALE/CLOSING STOCK</strong></th>\n");
            }
                writer.write("    <th width=\"150\" "+setDemacateSyntax(demacate)+ "><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\" "+setDemacateSyntax(demacate)+ "><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                demacate="N";
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;
                
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="0.00";
                    String statementTotalDebit="0.00";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String closingStock[]=systems.getColumn(OpenMSApp.Database_A,"select mAccountBalance, dOrderDate from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE Pay.cAccountCode='"+statementSumAccountCode[xs]+"' and creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select mAccountBalance,dOrderDate from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode where Pay.cAccountCode='"+statementSumAccountCode[xs]+"' and debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 1);
                                
                  
                        String openingStock=systems.getValue(OpenMSApp.Database_A, "Select sum(mTotalAmount) from creditOrder where cAccountCode='"+statementSumAccountCode[xs]+"' and cTransactionType='CNP' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ", 1);
                        String openingStockNeg=systems.getValue(OpenMSApp.Database_A, "Select sum(mTotalAmount) from debitOrder where cAccountCode='"+statementSumAccountCode[xs]+"' and cTransactionType='DNP'  and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ", 1);
                        openingStock=systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            openingStock+" , "+openingStockNeg+Connect.procInitEnd,"mSumValue");                                     
                                
                                //"Select mAccountBalance from Pay where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ";
                    
                        if(closingStock.length>=1){
                        statementTotalDebit=openingStock;
                        statementTotalCredit=closingStock[closingStock.length-1];
                        }else{
                        statementTotalCredit="0.00";
                        statementTotalDebit="0.00";
                        }
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                        
                        statementTotalCredit=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                        
                        statementTotalDebit=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
                    String statementBalance="";
                    try
                    {
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalCredit);
                        statementTotalCredit=systems.converToRealMoneyFormatNeg(statementTotalCredit);
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
                        statementTotalDebit=systems.converToRealMoneyFormatNeg(statementTotalDebit);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalDebit="Er!M1C01K0";
                    }
                    try
                    {
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalDebit)+"</td>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalCredit)+"</td>\n");
                    
            if(transferActivityState.isEmpty())
            {
                    writer.write("    <td>"+statementBalance+"</td>\n");
            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");                
//            }
//            
            
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                                           
                    }  
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.setAccountingFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check]+Connect.procInitEnd, "mSumValue"))+"</td>\n");
                    writer.write(" </tr>\n");
                    javax.swing.JOptionPane.showMessageDialog(javax.swing.JOptionPane.getRootFrame(),"EXPENSE SUM="+sumExpense[check]+" AND REVENUE SUM="+sumRevenue[check],"NOTICE !",javax.swing.JOptionPane.INFORMATION_MESSAGE);
                            
                }
                    writer.write("</table>\n");

            }
//----begin full group sorting

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 6);


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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                                
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                String demacate="N";
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                     statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                     statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                     statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                     statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                     statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                     statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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

//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//
//            }
                    
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                                           
                    }                     
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by cMerchantID", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 6);

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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//
//            }

                    
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                   
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_ACCOUNT_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 6);


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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                   
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
                   
//            if(transferActivityState.isEmpty())
//            {
//                writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");                        
//            }
 
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                   
                            
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_ACCOUNT_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 6);

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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                
                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//            }

                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                   
                                         
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                     writer.write("</table>\n");

            }

            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 6);


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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
//            if(transferActivityState.isEmpty())
//            {
//                writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//
//            }

                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                      
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }
            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by cActivity", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 6);

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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//            }
                 
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                      
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "") +Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
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
        //resetVariable();
    }
    
    public void writeBalanceSheet(String statementType,String accountName,String accountUnit,String merchantID,String activity,String transferActivityState,String startDate,String endDate)
    {
        String statementMySQLAccountColumn="2";
        String tranferActivityComment="";
        try
        {
            file=new java.io.File(Configuration.balanceSheetNotePath);
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

    writer.write("-->\n");
    writer.write("</style>\n");
            writer.write("</head>\n");

            writer.write("<body>\n");
            writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
            writer.write("  <tr>\n");
            if(transferActivityState.isEmpty())
            {
            writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.balanceSheetHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"statementOfAccount\" /></div></td>\n");
            }
            else
            {
            writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.balanceSheetHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"statementOfAccount\" /></div></td>\n");

            }

            writer.write("  </tr>\n");
            writer.write("</table>\n");
            if(transferActivityState.isEmpty()==false)
            {
                tranferActivityComment=" (TRANSFER ACTIVITY EXCLUDED) ";
            }
            if(statementType.equalsIgnoreCase("FULL_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+" order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 6);


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
                writer.write("    <td colspan=\"4\"><a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement </a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\"></td>\n");
                //writer.write("    <td colspan=\"6\">SCALE OF BALANCES (BALANCE SHEET)</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong>&nbsp;</strong></th>\n");//ACCOUNT NAME
                            
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\" scope=\"col\"><strong>CASH BALANCE</strong></th>\n");
                writer.write("    <th width=\"204\" scope=\"col\"><strong>INVEST BALANCE</strong></th>\n");
                //writer.write("    <th width=\"204\" scope=\"col\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\" scope=\"col\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\" scope=\"col\"><strong>TOTAL REVENUES</strong></th>\n");
               
            }
                writer.write("    <th width=\"150\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\" scope=\"col\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",10);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                
                int xs=0;
                int ys=statementSumAccountName.length;
                
                String demacate="N";
                String[] totalAsset=new String[num];
                String[] totalLiability=new String[num];
                int holder=0;
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' ",1);
                    String statementTotalRevenue=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and cActivity != 'Transfer Activity'  ",1);
                    String statementTotalExpense=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and cActivity != 'Transfer Activity'  ",1);
                    
                    String statementBalance="";
                    String statementIncomeBalance="";
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
                        if(statementTotalRevenue==null||statementTotalRevenue.isEmpty())
                        {statementTotalRevenue="0.00";}
                        else{
                            System.out.println("Total Revenue = "+statementTotalRevenue);
                        statementTotalRevenue=systems.converToRealMoneyFormat(statementTotalRevenue);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalRevenue="Er!M1C01K0";
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
                        if(statementTotalExpense==null||statementTotalExpense.isEmpty()){
                            statementTotalExpense="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalExpense);
                        statementTotalExpense=systems.converToRealMoneyFormat(statementTotalExpense);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalExpense="Er!M1C01K0";
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
                    
                    try
                    {
                        statementIncomeBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalRevenue+" , "+statementTotalExpense+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID"))
                    {
                        writer.write("    <td>"+statementBalance+"</td>\n");                    
                        writer.write("    <td>"+"N/A"+"</td>\n");
                    }
                    else if(statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        String comment="";
                        writer.write("    <td>"+statementBalance+"</td>\n");
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")&&(statementIncomeBalance.contains("-")==false&&statementIncomeBalance.equalsIgnoreCase("0.00")==false))
                            comment=" (Error) ";
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")&&statementIncomeBalance.contains("-")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)
                            comment=" (Error) ";
                        writer.write("    <td>"+statementIncomeBalance+comment+"</td>\n");
                    }
                    else
                    {
                        writer.write("    <td>"+"N/A"+"</td>\n");
                        writer.write("    <td>"+statementIncomeBalance+"</td>\n");
                    }
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+statementBalance+"</td>\n");
//            }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else
//            {
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//                      
//            }
                 
            for(int check=0;check<num;check++)
            {
             if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
             {
                 holder=check;
                 break;
             }
            }   
            
            if(totalAsset[holder]==null)totalAsset[holder]="0.00";
            if(totalLiability[holder]==null)totalLiability[holder]="0.00";

            if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
            {
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY RECEIVABLE")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT RECEIVABLE"))
            {
                String control="";
                if(statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)control="-";
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+control+statementIncomeBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT PAYABLE"))
            {
                totalLiability[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementIncomeBalance+" , "+totalLiability[holder]+Connect.procInitEnd, "mSumValue");
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>ASSET ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalAsset[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>LIABILITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalLiability[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>EQUITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            totalAsset[check]+" , "+totalLiability[check]+Connect.procInitEnd,"mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 6);

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
                writer.write("    <td colspan=\"4\"><a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement </a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                String demacate="N";
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
//                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\" "+setDemacateSyntax(demacate)+ "><strong></strong></td>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>CASH BALANCE</strong></th>\n");
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>INVEST BALANCE</strong></th>\n");
//                writer.write("    <th width=\"225\" "+setDemacateSyntax(demacate)+ "><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>TOTAL REVENUES</strong></th>\n");
            }
                writer.write("    <th width=\"150\" "+setDemacateSyntax(demacate)+ "><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\" "+setDemacateSyntax(demacate)+ "><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",10);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
               
                String[] totalAsset=new String[num];
                String[] totalLiability=new String[num];
                int holder=0;
                
                int xs=0;
                int ys=statementSumAccountName.length;

                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementBalance="";
                    
                    String statementTotalRevenue=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cActivity != 'Transfer Activity'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);
                    String statementTotalExpense=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cActivity != 'Transfer Activity'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);
                    String statementIncomeBalance="";
                    
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
                        if(statementTotalRevenue==null||statementTotalRevenue.isEmpty())
                        {statementTotalRevenue="0.00";}
                        else{
                            System.out.println("Total Revenue = "+statementTotalRevenue);
                        statementTotalRevenue=systems.converToRealMoneyFormat(statementTotalRevenue);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalRevenue="Er!M1C01K0";
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
                        if(statementTotalExpense==null||statementTotalExpense.isEmpty()){
                            statementTotalExpense="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalExpense);
                        statementTotalExpense=systems.converToRealMoneyFormat(statementTotalExpense);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalExpense="Er!M1C01K0";
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
                    
                    try
                    {
                        statementIncomeBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalRevenue+" , "+statementTotalExpense+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID"))
                    {
                        writer.write("    <td>"+statementBalance+"</td>\n");                    
                        writer.write("    <td>"+"N/A"+"</td>\n");
                    }
                    else if(statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        String comment="";
                        writer.write("    <td>"+statementBalance+"</td>\n");
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")&&statementIncomeBalance.contains("-")==false)
                            comment=" (Error) ";
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")&&statementIncomeBalance.contains("-"))
                            comment=" (Error) ";
                        writer.write("    <td>"+statementIncomeBalance+comment+"</td>\n");
                    }
                    else
                    {
                        writer.write("    <td>"+"N/A"+"</td>\n");
                        writer.write("    <td>"+statementIncomeBalance+"</td>\n");
                    }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

            for(int check=0;check<num;check++)
            {
             if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
             {
                 holder=check;
                 break;
             }
            }   
            
            if(totalAsset[holder]==null)totalAsset[holder]="0.00";
            if(totalLiability[holder]==null)totalLiability[holder]="0.00";

            if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
            {
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY RECEIVABLE")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT RECEIVABLE"))
            {
                String control="";
                if(statementSumAccountCategory[xs].equalsIgnoreCase("RECEIVABLE")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)control="-";
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+control+statementIncomeBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT PAYABLE"))
            {
                totalLiability[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementIncomeBalance+" , "+totalLiability[holder]+Connect.procInitEnd, "mSumValue");
            }
////                    
////                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
////                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
////                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+statementBalance+"</td>\n");
////            }
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
////
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
////            }
////            else{
////                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");                
////            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>ASSET ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalAsset[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>LIABILITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalLiability[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>EQUITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            totalAsset[check]+" , "+totalLiability[check]+Connect.procInitEnd,"mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");

            }
          
            
//----begin full group sorting

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 6);


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
                writer.write("    <td colspan=\"4\"><a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement </a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
//                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>CASH BALANCE</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>INVEST BALANCE</strong></th>\n");
//                writer.write("    <th width=\"204\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",10);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                
                String[] totalAsset=new String[num];
                String[] totalLiability=new String[num];
                int holder=0;
                

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementBalance="";
                    
                    String statementTotalRevenue=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and cActivity != 'Transfer Activity'  ",1);
                    String statementTotalExpense=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and cActivity != 'Transfer Activity'  ",1);
                    
                    String statementIncomeBalance="";
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
                        if(statementTotalRevenue==null||statementTotalRevenue.isEmpty())
                        {statementTotalRevenue="0.00";}
                        else{
                            System.out.println("Total Revenue = "+statementTotalRevenue);
                        statementTotalRevenue=systems.converToRealMoneyFormat(statementTotalRevenue);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalRevenue="Er!M1C01K0";
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
                        if(statementTotalExpense==null||statementTotalExpense.isEmpty()){
                            statementTotalExpense="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalExpense);
                        statementTotalExpense=systems.converToRealMoneyFormat(statementTotalExpense);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalExpense="Er!M1C01K0";
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
                    
                    try
                    {
                        statementIncomeBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalRevenue+" , "+statementTotalExpense+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID"))
                    {
                        writer.write("    <td>"+statementBalance+"</td>\n");                    
                        writer.write("    <td>"+"N/A"+"</td>\n");
                    }
                    else if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        String comment="";
                        writer.write("    <td>"+statementBalance+"</td>\n");
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.contains("-")==false)
                            comment=" (Error) ";
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")&&statementIncomeBalance.contains("-"))
                            comment=" (Error) ";
                        writer.write("    <td>"+statementIncomeBalance+comment+"</td>\n");
                    }
                    else
                    {
                        writer.write("    <td>"+"N/A"+"</td>\n");
                        writer.write("    <td>"+statementIncomeBalance+"</td>\n");
                    }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                 
            for(int check=0;check<num;check++)
            {
             if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
             {
                 holder=check;
                 break;
             }
            }   
            
            if(totalAsset[holder]==null)totalAsset[holder]="0.00";
            if(totalLiability[holder]==null)totalLiability[holder]="0.00";

            if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
            {
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY RECIEVABLE")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT RECIEVABLE"))
            {
                String control="";
                if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)control="-";
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+control+statementIncomeBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT PAYABLE"))
            {
                totalLiability[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementIncomeBalance+" , "+totalLiability[holder]+Connect.procInitEnd, "mSumValue");
            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>ASSET ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalAsset[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>LIABILITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalLiability[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>EQUITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            totalAsset[check]+" , "+totalLiability[check]+Connect.procInitEnd,"mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by cMerchantID", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 6);

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
                writer.write("    <td colspan=\"4\"><a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement </a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
//                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>CASH BALANCE</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>INVEST BALANCE</strong></th>\n");
//                writer.write("    <th width=\"225\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",10);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
               
                String[] totalAsset=new String[num];
                String[] totalLiability=new String[num];
                int holder=0;
                
                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementBalance="";
                    String statementTotalRevenue=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cActivity != 'Transfer Activity'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   ",1);
                    String statementTotalExpense=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cActivity != 'Transfer Activity'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'   ",1);
                    String statementIncomeBalance="";
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
                    
                    try
                    {
                        if(statementTotalRevenue==null||statementTotalRevenue.isEmpty())
                        {statementTotalRevenue="0.00";}
                        else{
                            System.out.println("Total Revenue = "+statementTotalRevenue);
                        statementTotalRevenue=systems.converToRealMoneyFormat(statementTotalRevenue);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalRevenue="Er!M1C01K0";
                    }
                    
                    try
                    {
                        if(statementTotalExpense==null||statementTotalExpense.isEmpty()){
                            statementTotalExpense="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalExpense);
                        statementTotalExpense=systems.converToRealMoneyFormat(statementTotalExpense);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalExpense="Er!M1C01K0";
                    }
                      
                    try
                    {
                        statementIncomeBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalRevenue+" , "+statementTotalExpense+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID"))
                    {
                        writer.write("    <td>"+statementBalance+"</td>\n");                    
                        writer.write("    <td>"+"N/A"+"</td>\n");
                    }
                    else if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        String comment="";
                        writer.write("    <td>"+statementBalance+"</td>\n");
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.contains("-")==false)
                            comment=" (Error) ";
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")&&statementIncomeBalance.contains("-"))
                            comment=" (Error) ";
                        writer.write("    <td>"+statementIncomeBalance+comment+"</td>\n");
                    }
                    else
                    {
                        writer.write("    <td>"+"N/A"+"</td>\n");
                        writer.write("    <td>"+statementIncomeBalance+"</td>\n");
                    }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

            for(int check=0;check<num;check++)
            {
             if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
             {
                 holder=check;
                 break;
             }
            }   
            
            if(totalAsset[holder]==null)totalAsset[holder]="0.00";
            if(totalLiability[holder]==null)totalLiability[holder]="0.00";

            if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
            {
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY RECIEVABLE")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT RECIEVABLE"))
            {
                String control="";
                if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)control="-";
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+control+statementIncomeBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT PAYABLE"))
            {
                totalLiability[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementIncomeBalance+" , "+totalLiability[holder]+Connect.procInitEnd, "mSumValue");
            }
////                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
////                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+statementBalance+"</td>\n");
////            }
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
////            }
////            else{
////                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
////
////            }

                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>ASSET ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalAsset[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>LIABILITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalLiability[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>EQUITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            totalAsset[check]+" , "+totalLiability[check]+Connect.procInitEnd,"mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");

            }

            if(statementType.equalsIgnoreCase("FULL_ACCOUNT_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 6);


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
                writer.write("    <td colspan=\"4\"><a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement </a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
//                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>CASH BALANCE</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>INVEST BALANCE</strong></th>\n");
//                writer.write("    <th width=\"204\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",10);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                
                String[] totalAsset=new String[num];
                String[] totalLiability=new String[num];
                int holder=0;

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementBalance="";
                    String statementTotalRevenue=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and cActivity != 'Transfer Activity'  ",1);
                    String statementTotalExpense=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and cActivity != 'Transfer Activity'  ",1);
                    
                    String statementIncomeBalance="";
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
                    
                    try
                    {
                        if(statementTotalRevenue==null||statementTotalRevenue.isEmpty())
                        {statementTotalRevenue="0.00";}
                        else{
                            System.out.println("Total Revenue = "+statementTotalRevenue);
                        statementTotalRevenue=systems.converToRealMoneyFormat(statementTotalRevenue);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalRevenue="Er!M1C01K0";
                    }
                    
                    try
                    {
                        if(statementTotalExpense==null||statementTotalExpense.isEmpty()){
                            statementTotalExpense="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalExpense);
                        statementTotalExpense=systems.converToRealMoneyFormat(statementTotalExpense);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalExpense="Er!M1C01K0";
                    }
                      
                    try
                    {
                        statementIncomeBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalRevenue+" , "+statementTotalExpense+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID"))
                    {
                        writer.write("    <td>"+statementBalance+"</td>\n");                    
                        writer.write("    <td>"+"N/A"+"</td>\n");
                    }
                    else if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        String comment="";
                        writer.write("    <td>"+statementBalance+"</td>\n");
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.contains("-")==false)
                            comment=" (Error) ";
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")&&statementIncomeBalance.contains("-"))
                            comment=" (Error) ";
                        writer.write("    <td>"+statementIncomeBalance+comment+"</td>\n");
                    }
                    else
                    {
                        writer.write("    <td>"+"N/A"+"</td>\n");
                        writer.write("    <td>"+statementIncomeBalance+"</td>\n");
                    }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                 
            for(int check=0;check<num;check++)
            {
             if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
             {
                 holder=check;
                 break;
             }
            }   
            
            if(totalAsset[holder]==null)totalAsset[holder]="0.00";
            if(totalLiability[holder]==null)totalLiability[holder]="0.00";

            if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
            {
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY RECIEVABLE")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT RECIEVABLE"))
            {
                String control="";
                if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)control="-";
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+control+statementIncomeBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT PAYABLE"))
            {
                totalLiability[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementIncomeBalance+" , "+totalLiability[holder]+Connect.procInitEnd, "mSumValue");
            }
////                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
////                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+statementBalance+"</td>\n");
////            }
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
////                   
////            if(transferActivityState.isEmpty())
////            {
////                writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
////            }
////            else{
////                writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");                        
////            }

                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>ASSET ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalAsset[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>LIABILITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalLiability[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>EQUITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            totalAsset[check]+" , "+totalLiability[check]+Connect.procInitEnd,"mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_ACCOUNT_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 6);

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
                writer.write("    <td colspan=\"4\"><a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement </a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
//                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>CASH BALANCE</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>INVEST BALANCE</strong></th>\n");
//                writer.write("    <th width=\"225\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",10);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
               
                String[] totalAsset=new String[num];
                String[] totalLiability=new String[num];
                int holder=0;                

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementBalance="";
                    String statementTotalRevenue=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cActivity != 'Transfer Activity'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);
                    String statementTotalExpense=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cActivity != 'Transfer Activity'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);
                    String statementIncomeBalance="";
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
                    
                    try
                    {
                        if(statementTotalRevenue==null||statementTotalRevenue.isEmpty())
                        {statementTotalRevenue="0.00";}
                        else{
                            System.out.println("Total Revenue = "+statementTotalRevenue);
                        statementTotalRevenue=systems.converToRealMoneyFormat(statementTotalRevenue);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalRevenue="Er!M1C01K0";
                    }
                    
                    try
                    {
                        if(statementTotalExpense==null||statementTotalExpense.isEmpty()){
                            statementTotalExpense="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalExpense);
                        statementTotalExpense=systems.converToRealMoneyFormat(statementTotalExpense);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalExpense="Er!M1C01K0";
                    }
                      
                    try
                    {
                        statementIncomeBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalRevenue+" , "+statementTotalExpense+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID"))
                    {
                        writer.write("    <td>"+statementBalance+"</td>\n");                    
                        writer.write("    <td>"+"N/A"+"</td>\n");
                    }
                    else if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        String comment="";
                        writer.write("    <td>"+statementBalance+"</td>\n");
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.contains("-")==false)
                            comment=" (Error) ";
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")&&statementIncomeBalance.contains("-"))
                            comment=" (Error) ";
                        writer.write("    <td>"+statementIncomeBalance+comment+"</td>\n");
                    }
                    else
                    {
                        writer.write("    <td>"+"N/A"+"</td>\n");
                        writer.write("    <td>"+statementIncomeBalance+"</td>\n");
                    }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                 
            for(int check=0;check<num;check++)
            {
             if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
             {
                 holder=check;
                 break;
             }
            }   
            
            if(totalAsset[holder]==null)totalAsset[holder]="0.00";
            if(totalLiability[holder]==null)totalLiability[holder]="0.00";

            if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
            {
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY RECIEVABLE")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT RECIEVABLE"))
            {
                String control="";
                if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)control="-";
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+control+statementIncomeBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT PAYABLE"))
            {
                totalLiability[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementIncomeBalance+" , "+totalLiability[holder]+Connect.procInitEnd, "mSumValue");
            }
////                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
////                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+statementBalance+"</td>\n");
////            }
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
////            }
////            else{
////                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
////            }

                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>ASSET ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalAsset[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>LIABILITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalLiability[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>EQUITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            totalAsset[check]+" , "+totalLiability[check]+Connect.procInitEnd,"mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");

            }

            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 6);


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
                writer.write("    <td colspan=\"4\"><a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement </a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
//                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME

            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>CASH BALANCE</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>INVEST BALANCE</strong></th>\n");
//                writer.write("    <th width=\"204\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",10);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                
                String[] totalAsset=new String[num];
                String[] totalLiability=new String[num];
                int holder=0;

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    String statementBalance="";
                    String statementTotalRevenue=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and cActivity != 'Transfer Activity'  ",1);
                    String statementTotalExpense=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and cActivity != 'Transfer Activity'  ",1);
                    
                    String statementIncomeBalance="";
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
                    try
                    {
                        if(statementTotalRevenue==null||statementTotalRevenue.isEmpty())
                        {statementTotalRevenue="0.00";}
                        else{
                            System.out.println("Total Revenue = "+statementTotalRevenue);
                        statementTotalRevenue=systems.converToRealMoneyFormat(statementTotalRevenue);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalRevenue="Er!M1C01K0";
                    }
                    
                    try
                    {
                        if(statementTotalExpense==null||statementTotalExpense.isEmpty()){
                            statementTotalExpense="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalExpense);
                        statementTotalExpense=systems.converToRealMoneyFormat(statementTotalExpense);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalExpense="Er!M1C01K0";
                    }
                      
                    try
                    {
                        statementIncomeBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalRevenue+" , "+statementTotalExpense+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID"))
                    {
                        writer.write("    <td>"+statementBalance+"</td>\n");                    
                        writer.write("    <td>"+"N/A"+"</td>\n");
                    }
                    else if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        String comment="";
                        writer.write("    <td>"+statementBalance+"</td>\n");
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.contains("-")==false)
                            comment=" (Error) ";
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")&&statementIncomeBalance.contains("-"))
                            comment=" (Error) ";
                        writer.write("    <td>"+statementIncomeBalance+comment+"</td>\n");
                    }
                    else
                    {
                        writer.write("    <td>"+"N/A"+"</td>\n");
                        writer.write("    <td>"+statementIncomeBalance+"</td>\n");
                    }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                 
            for(int check=0;check<num;check++)
            {
             if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
             {
                 holder=check;
                 break;
             }
            }   
            
            if(totalAsset[holder]==null)totalAsset[holder]="0.00";
            if(totalLiability[holder]==null)totalLiability[holder]="0.00";

            if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
            {
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY RECIEVABLE")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT RECIEVABLE"))
            {
                String control="";
                if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)control="-";
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+control+statementIncomeBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT PAYABLE"))
            {
                totalLiability[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementIncomeBalance+" , "+totalLiability[holder]+Connect.procInitEnd, "mSumValue");
            }
////                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
////                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+statementBalance+"</td>\n");
////            }
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
////            }
////            else{
////                writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
////
////            }
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>ASSET ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalAsset[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>LIABILITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalLiability[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>EQUITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            totalAsset[check]+" , "+totalLiability[check]+Connect.procInitEnd,"mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }
            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by cActivity", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 6);

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
                writer.write("    <td colspan=\"4\"><a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement </a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
//                writer.write("    <td colspan=\"6\"><A href=\"#top\" name=#Summary>BACK TO TOP</A></td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
                writer.write("    <th width=\"204\"><strong>CASH BALANCE</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>INVEST BALANCE</strong></th>\n");
//                writer.write("    <th width=\"225\"><strong>BALANCE</strong></th>\n");
               }
            else
            {
                writer.write("    <th width=\"204\"><strong>TOTAL EXPENSES</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>TOTAL REVENUES</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",10);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                
                String[] totalAsset=new String[num];
                String[] totalLiability=new String[num];
                int holder=0;
                
                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    String statementBalance="";
                    String statementTotalRevenue=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cActivity != 'Transfer Activity'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);
                    String statementTotalExpense=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cActivity != 'Transfer Activity'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' ",1);
                    String statementIncomeBalance="";
                    
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
                    try
                    {
                        if(statementTotalRevenue==null||statementTotalRevenue.isEmpty())
                        {statementTotalRevenue="0.00";}
                        else{
                            System.out.println("Total Revenue = "+statementTotalRevenue);
                        statementTotalRevenue=systems.converToRealMoneyFormat(statementTotalRevenue);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalRevenue="Er!M1C01K0";
                    }
                    
                    try
                    {
                        if(statementTotalExpense==null||statementTotalExpense.isEmpty()){
                            statementTotalExpense="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalExpense);
                        statementTotalExpense=systems.converToRealMoneyFormat(statementTotalExpense);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalExpense="Er!M1C01K0";
                    }
                      
                    try
                    {
                        statementIncomeBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalRevenue+" , "+statementTotalExpense+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");       
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID"))
                    {
                        writer.write("    <td>"+statementBalance+"</td>\n");                    
                        writer.write("    <td>"+"N/A"+"</td>\n");
                    }
                    else if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        String comment="";
                        writer.write("    <td>"+statementBalance+"</td>\n");
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.contains("-")==false)
                            comment=" (Error) ";
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")&&statementIncomeBalance.contains("-"))
                            comment=" (Error) ";
                        writer.write("    <td>"+statementIncomeBalance+comment+"</td>\n");
                    }
                    else
                    {
                        writer.write("    <td>"+"N/A"+"</td>\n");
                        writer.write("    <td>"+statementIncomeBalance+"</td>\n");
                    }
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

                 
            for(int check=0;check<num;check++)
            {
             if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
             {
                 holder=check;
                 break;
             }
            }   
            
            if(totalAsset[holder]==null)totalAsset[holder]="0.00";
            if(totalLiability[holder]==null)totalLiability[holder]="0.00";

            if(statementSumAccountCategory[xs].equalsIgnoreCase("REVENUE")||statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")||statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE"))
            {
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY RECIEVABLE")
                    ||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT RECIEVABLE"))
            {
                String control="";
                if(statementSumAccountCategory[xs].equalsIgnoreCase("RECIEVABLE")&&statementIncomeBalance.equalsIgnoreCase("0.00")==false)control="-";
                totalAsset[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+control+statementIncomeBalance+" , "+totalAsset[holder]+Connect.procInitEnd, "mSumValue");
            }
            if(statementSumAccountCategory[xs].equalsIgnoreCase("PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("INVENTORY PAYABLE")||statementSumAccountCategory[xs].equalsIgnoreCase("ADJUSTMENT PAYABLE"))
            {
                totalLiability[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementIncomeBalance+" , "+totalLiability[holder]+Connect.procInitEnd, "mSumValue");
            }
////                    writer.write("    <td>"+statementTotalDebit+"</td>\n");
////                    writer.write("    <td>"+statementTotalCredit+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+statementBalance+"</td>\n");
////            }
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
////
////                   // writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
////            if(transferActivityState.isEmpty())
////            {
////                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
////            }
////            else{
////                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
////            }
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>ASSET ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalAsset[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>LIABILITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+totalLiability[check]+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    demacate=changeDemacate(demacate);
                    
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>EQUITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            totalAsset[check]+" , "+totalLiability[check]+Connect.procInitEnd,"mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
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
//        resetVariable();
    }
    
    
    public void writeCashFlowStatement(String statementType,String accountName,String accountUnit,String merchantID,String activity,String transferActivityState,String startDate,String endDate)
    {
        String statementMySQLAccountColumn="2";
        String tranferActivityComment="";
        try
        {
            file=new java.io.File(Configuration.cashFlowsNotePath);
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


        writer.write("table#reportSummary {\n");
        writer.write("	font-family: Verdana, Geneva, sans-serif;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	border-collapse: collapse;\n");
        writer.write("}\n");
        writer.write("#reportSummary tr th {\n");
        writer.write("	color: #FFF;\n");
        writer.write("	background: #003C00;\n");
        writer.write("	font-family: Georgia, \"Times New Roman\", Times, serif;\n");
        writer.write("	text-align: left;\n");
        writer.write("	padding-left: 5px;\n");
        writer.write("	font-weight: normal;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	font: normal 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
        writer.write("}\n");
        writer.write("#reportSummary tr td {\n");
        writer.write("	padding-left: 5px;\n");
        writer.write("	border: 1px solid #FFF;\n");
        writer.write("	background: #FF9;\n");
        writer.write("	font: normal 12px \"Lucida Sans Unicode\", \"Lucida Grande\", sans-serif;\n");
        writer.write("}\n");
        writer.write("table#reportSummary tr:hover {\n");
        writer.write("	background: #060;\n");
        writer.write("	color: #FFF;\n");
        writer.write("}\n");
        writer.write("#reportSummary tr th.demacate {\n");
        writer.write("	background: #090;\n");
        writer.write("	color: #FF0;\n");
        writer.write("}\n");

    writer.write("-->\n");
    writer.write("</style>\n");
            writer.write("</head>\n");

            writer.write("<body>\n");
            writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n");
            writer.write("  <tr>\n");
            
            writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.statementOfCashFlowsHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\"statementOfAccount\" /></div></td>\n");


            writer.write("  </tr>\n");
            writer.write("</table>\n");
            
            if(transferActivityState.isEmpty()==false)
            {
                tranferActivityComment=" (TRANSFER ACTIVITY EXCLUDED) ";
            }
            if(statementType.equalsIgnoreCase("FULL_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+"  order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate  != 'NULL'  "+transferActivityState+" order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by dOrderDate", 6);


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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");

                writer.write(" <tr>\n");
                writer.write("<th width=\"362\"><strong></strong></th>\n");//ACCOUNT NAME
                            
            if(transferActivityState.isEmpty())
            {}
            else
            {
                writer.write("    <th width=\"234\" scope=\"col\"><strong>OPERATING ACTIVITY</strong></th>\n");
                writer.write("    <th width=\"234\" scope=\"col\"><strong>FINANCING ACTIVITY</strong></th>\n");
                writer.write("    <th width=\"234\" scope=\"col\"><strong>INVESTING ACTIVITY</strong></th>\n");
                writer.write("    <th width=\"234\" scope=\"col\"><strong>OPENING BALANCE</strong></th>\n");
             
            }
                writer.write("    <th width=\"150\" scope=\"col\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\" scope=\"col\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount", 10);  
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount", 14);                
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumOperating[]=new String[num];
                String sumFinancing[]=new String[num];
                String sumInvesting[]=new String[num];
                String sumOpeningBalance[]=new String[num];
                int holder=0;
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalOperating="0.00";
                    String statementTotalInvesting="0.00";
                    String statementTotalFinancing="0.00";
                    String statementTotalOpeningBalance="0.00";
                    String statementTotalCredit="0.00";
                    String statementTotalDebit="0.00";
                    String statementTotalOpeningBalanceCredit="0.00";
                    String statementTotalOpeningBalanceDebit="0.00";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("Operating Activity"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                        
                        statementTotalOperating=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        statementTotalInvesting="0.00";
                        statementTotalFinancing="0.00";
                         statementTotalOpeningBalance="0.00";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("Financing Activity"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                        
                        statementTotalFinancing=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                         statementTotalOperating="0.00";
                         statementTotalInvesting="0.00";
                         statementTotalOpeningBalance="0.00";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("Investing Activity"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                        
                        statementTotalInvesting=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        //if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        //statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        //}
                         statementTotalOperating="0.00";
                         statementTotalFinancing="0.00";
                         statementTotalOpeningBalance="0.00";
                    }
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")||statementSumAccountTradingType[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountTradingType[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        
                        statementTotalOpeningBalanceCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cTransactionType='CRO' "+transferActivityState+" ",1);
                        statementTotalOpeningBalanceDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cTransactionType='DRO' "+transferActivityState+" ",1);
                        
                        if(statementTotalOpeningBalanceCredit==null||statementTotalOpeningBalanceCredit.isEmpty())
                        {statementTotalOpeningBalanceCredit="0.00";}
                        if(statementTotalOpeningBalanceDebit==null||statementTotalOpeningBalanceDebit.isEmpty())
                        {statementTotalOpeningBalanceDebit="0.00";}
                        
                        statementTotalOpeningBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalOpeningBalanceCredit+" , "+statementTotalOpeningBalanceDebit+Connect.procInitEnd,"mSumValue"));
                        
                        statementTotalFinancing=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalFinancing+" , "+statementTotalOpeningBalance+Connect.procInitEnd,"mSumValue"));
                    }
                    try
                    {
                        if(statementTotalOperating==null||statementTotalOperating.isEmpty())
                        {statementTotalOperating="0.00";}
                        else{
                            System.out.println("Total Operating Activity = "+statementTotalOperating);
                            statementTotalOperating=systems.converToRealMoneyFormatNeg(statementTotalOperating);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalOperating="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalFinancing==null||statementTotalFinancing.isEmpty()){
                            statementTotalFinancing="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalFinancing);
                        statementTotalFinancing=systems.converToRealMoneyFormatNeg(statementTotalFinancing);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalFinancing="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalInvesting==null||statementTotalInvesting.isEmpty()){
                            statementTotalInvesting="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalInvesting);
                        statementTotalInvesting=systems.converToRealMoneyFormatNeg(statementTotalInvesting);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalInvesting="Er!M1C01K0";
                    }
                    try
                    {
                        if(statementTotalOpeningBalance==null||statementTotalOpeningBalance.isEmpty()){
                            statementTotalOpeningBalance="0.00";
                        }
                        else{
                            System.out.println("Total Debit = "+statementTotalOpeningBalance);
                        statementTotalOpeningBalance=systems.converToRealMoneyFormatNeg(statementTotalOpeningBalance);
                        }
                    }
                    catch(Exception e)
                    {
                        statementTotalInvesting="Er!M1C01K0";
                    }
                    
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalOperating)+"</td>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalFinancing)+"</td>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalInvesting)+"</td>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalOpeningBalance)+"</td>\n");
            
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else
//            {
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//                      
//            }
            
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumOperating[holder]==null)sumOperating[holder]="0.00";
                    if(sumFinancing[holder]==null)sumFinancing[holder]="0.00";
                    if(sumInvesting[holder]==null)sumInvesting[holder]="0.00";
                    if(sumOpeningBalance[holder]==null)sumOpeningBalance[holder]="0.00";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("Operating Activity")){
                        sumOperating[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalOperating+" , "+sumOperating[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("Financing Activity")&&(statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")||statementSumAccountTradingType[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountTradingType[xs].equalsIgnoreCase("PAYABLE"))){
                        sumFinancing[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalFinancing+" , "+sumFinancing[holder]+Connect.procInitEnd, "mSumValue");
                        sumOpeningBalance[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalOpeningBalance+" , "+sumOpeningBalance[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("Financing Activity")){
                        sumFinancing[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalFinancing+" , "+sumFinancing[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("Investing Activity")){
                        sumInvesting[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalInvesting+" , "+sumInvesting[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>TOTAL OPERATING ACTIVITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                           +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumOperating[check]+" , "+sumOperating[check] +Connect.procInitEnd, "mSumValue")
                           systems.setAccountingFormat(sumOperating[check])
                            +"</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>TOTAL FINANCING ACTIVITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                            +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue")
                            systems.setAccountingFormat(sumFinancing[check])
                            +"</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>TOTAL INVESTING ACTIVITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                            +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue")
                            systems.setAccountingFormat(sumInvesting[check])
                            +"</td>\n");
                    writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>TOTAL OPENING BALANCE ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                            +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue")
                            systems.setAccountingFormat(sumOpeningBalance[check])
                            +"</td>\n");
                    writer.write(" </tr>\n");
                    demacate=changeDemacate(demacate);
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>NET CASH ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                            +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue")
                            systems.setAccountingFormat(systems.getValue(OpenMSApp.Database_A,"Select "+sumOperating[check]+" + "+sumFinancing[check]+" + "+sumInvesting[check]+" + "+sumOpeningBalance[check]+" AS 'NETCASH'",1))
                            +"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by dOrderDate", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by dOrderDate", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by dOrderDate", 6);

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
                writer.write("    <td colspan=\"6\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                String demacate="N";
                
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"5\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                writer.write("<th width=\"362\" "+setDemacateSyntax(demacate)+ "><strong></strong></td>\n");//ACCOUNT NAME
            if(transferActivityState.isEmpty())
            {
            }
            else
            {
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>OPERATING ACTIVITY</strong></th>\n");
                writer.write("    <th width=\"204\" "+setDemacateSyntax(demacate)+ "><strong>FINANCING ACTIVITY</strong></th>\n");
                writer.write("    <th width=\"225\" "+setDemacateSyntax(demacate)+ "><strong>INVESTING ACTIVITY</strong></th>\n");
                writer.write("    <th width=\"225\" "+setDemacateSyntax(demacate)+ "><strong>OPENING BALANCE</strong></th>\n");
               }
                writer.write("    <th width=\"150\" "+setDemacateSyntax(demacate)+ "><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\" "+setDemacateSyntax(demacate)+ "><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount", 10);  
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount", 14);                
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount",2);
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwCashFlowsAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                demacate="N";
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumOperating[]=new String[num];
                String sumFinancing[]=new String[num];
                String sumInvesting[]=new String[num];
                String sumOpeningBalance[]=new String[num];
                int holder=0;
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalOperating="0.00";
                    String statementTotalInvesting="0.00";
                    String statementTotalFinancing="0.00";
                    String statementTotalOpeningBalance="0.00";
                    String statementTotalCredit="0.00";
                    String statementTotalDebit="0.00";
                    String statementTotalOpeningBalanceCredit="0.00";
                    String statementTotalOpeningBalanceDebit="0.00";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("OPERATING ACTIVITY"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                                                     
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                        
                        statementTotalOperating=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        statementTotalInvesting="0.00";
                        statementTotalFinancing="0.00";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("FINANCING ACTIVITY"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                                                      
                        statementTotalFinancing=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        statementTotalInvesting="0.00";
                        statementTotalOperating="0.00";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("INVESTING ACTIVITY"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        
                        if(statementTotalCredit==null||statementTotalCredit.isEmpty())
                        {statementTotalCredit="0.00";}
                        if(statementTotalDebit==null||statementTotalDebit.isEmpty())
                        {statementTotalDebit="0.00";}
                                                      
                        statementTotalInvesting=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        statementTotalFinancing="0.00";
                        statementTotalOperating="0.00";
                        
                       // if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        //statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        //}
                        statementTotalCredit="0.00";
                    }
                    if(statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")||statementSumAccountTradingType[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountTradingType[xs].equalsIgnoreCase("PAYABLE"))
                    {
                        
                        statementTotalOpeningBalanceCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cTransactionType='CRO' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalOpeningBalanceDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and cTransactionType='DRO' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        
                        if(statementTotalOpeningBalanceCredit==null||statementTotalOpeningBalanceCredit.isEmpty())
                        {statementTotalOpeningBalanceCredit="0.00";}
                        if(statementTotalOpeningBalanceDebit==null||statementTotalOpeningBalanceDebit.isEmpty())
                        {statementTotalOpeningBalanceDebit="0.00";}
                        
                        statementTotalOpeningBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalOpeningBalanceCredit+" , "+statementTotalOpeningBalanceDebit+Connect.procInitEnd,"mSumValue"));
                        
                        statementTotalFinancing=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalFinancing+" , "+statementTotalOpeningBalance+Connect.procInitEnd,"mSumValue"));
                    }
                    /**
                    String statementBalance="0.00";
                    try
                    {
                        if(statementTotalOperating==null||statementTotalOperating.isEmpty())
                        {statementTotalOperating="0.00";}
                        else{
                            System.out.println("Total Credit = "+statementTotalOperating);
                        statementTotalOperating=systems.converToRealMoneyFormat(statementTotalOperating);
                        }

                    }
                    catch(Exception e)
                    {
                        statementTotalOperating="Er!M1C01K0";
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                    }
                    catch(Exception e)
                    {
                        statementBalance="Er!M1C01K0";
                    }**/
                    writer.write("  <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+">"+statementSumAccountName[xs]+"</th>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalOperating)+"</td>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalFinancing)+"</td>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalInvesting)+"</td>\n");
                    writer.write("    <td>"+systems.setAccountingFormat(statementTotalOpeningBalance)+"</td>\n");
                    
            //if(transferActivityState.isEmpty())
            //{
           //         writer.write("    <td>"+statementBalance+"</td>\n");
            //}
                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 5)+"</td>\n");

//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");                
//            }
//            
            
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumOperating[holder]==null)sumOperating[holder]="0.00";
                    if(sumFinancing[holder]==null)sumFinancing[holder]="0.00";
                    if(sumInvesting[holder]==null)sumInvesting[holder]="0.00";
                    if(sumOpeningBalance[holder]==null)sumOpeningBalance[holder]="0.00";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("Operating Activity")){
                        sumOperating[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalOperating+" , "+sumOperating[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("Financing Activity")&&(statementSumAccountCategory[xs].equalsIgnoreCase("HYBRID")||statementSumAccountTradingType[xs].equalsIgnoreCase("RECEIVABLE")||statementSumAccountTradingType[xs].equalsIgnoreCase("PAYABLE"))){
                        sumFinancing[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalFinancing+" , "+sumFinancing[holder]+Connect.procInitEnd, "mSumValue");
                        sumOpeningBalance[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalOpeningBalance+" , "+sumOpeningBalance[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("Financing Activity")){
                        sumFinancing[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalFinancing+" , "+sumFinancing[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("Investing Activity")){
                        sumInvesting[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalInvesting+" , "+sumInvesting[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"6\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>TOTAL OPERATING ACTIVITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                           +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumOperating[check]+" , "+sumOperating[check] +Connect.procInitEnd, "mSumValue")
                           systems.setAccountingFormat(sumOperating[check])
                            +"</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>TOTAL FINANCING ACTIVITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                            +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue")
                            systems.setAccountingFormat(sumFinancing[check])
                            +"</td>\n");
                writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>TOTAL INVESTING ACTIVITY ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                            +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue")
                            systems.setAccountingFormat(sumInvesting[check])
                            +"</td>\n");
                    writer.write(" </tr>\n");
                writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>TOTAL OPENING BALANCE ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                            +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue")
                            systems.setAccountingFormat(sumOpeningBalance[check])
                            +"</td>\n");
                    writer.write(" </tr>\n");
                    demacate=changeDemacate(demacate);
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>NET CASH ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"5\">"
                            +//systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check] +Connect.procInitEnd, "mSumValue")
                            systems.setAccountingFormat(systems.getValue(OpenMSApp.Database_A,"Select "+sumOperating[check]+" + "+sumFinancing[check]+" + "+sumInvesting[check]+" + "+sumOpeningBalance[check]+" AS 'NETCASH'",1))
                            +"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");

            }
//----begin full group sorting

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cMerchantID", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cMerchantID", 6);


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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                                
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                String demacate="N";
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                     statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                     statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                     statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                     statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                     statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                     statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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

//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//
//            }
                    
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                                           
                    }                     
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_MERCHANT_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by cMerchantID", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cMerchantID", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cMerchantID", 6);

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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//
//            }

                    
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                   
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_ACCOUNT_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 6);


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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                   
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
                   
//            if(transferActivityState.isEmpty())
//            {
//                writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");                        
//            }
 
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                   
                            
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }

            if(statementType.equalsIgnoreCase("FULL_ACCOUNT_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by "+statementMySQLAccountColumn+"", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by "+statementMySQLAccountColumn+"", 6);

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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                
                writer.write("</table>\n");
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                    statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//            }

                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                   
                                         
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                     writer.write("</table>\n");

            }

            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_N"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+"  order by cActivity", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode  WHERE debitOrder.dOrderDate  != 'NULL' "+transferActivityState+" order by cActivity", 6);


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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");

            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");
                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                    statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' "+transferActivityState+" ",1);
                    statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
//            if(transferActivityState.isEmpty())
//            {
//                writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//
//            }

                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                      
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "")+Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
                }
                    writer.write("</table>\n");
            }
            if(statementType.equalsIgnoreCase("FULL_ACTIVITY_Y"))
            {
             statementOrderCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 7);

             statementCreditDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" order by cActivity", 10);

             statementCreditCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 3);

             statementDebitCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 4);

             statementAccountCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 9);

             statementMerchantID=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 8);

             statementOrderByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 12);

             statementOrderDate=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" order by cActivity", 13);

             statementConsentByCode=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 14);

             statementAccountBalance=systems.getColumn(OpenMSApp.Database_A,"select * from pay join creditOrder on pay.ccreditcode=creditorder.ccreditcode WHERE creditOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  "+
" union select * from pay join debitorder on pay.cdebitCode=debitorder.cdebitCode WHERE debitOrder.dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+"  order by cActivity", 6);

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
                writer.write("    <td colspan=\"4\"> <a href=\" "+Configuration.accountStatementNoteURLPath+" \">Full Statement</a></td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
//                writer.write("    <td>&nbsp;</td>");
                writer.write("  </tr>");
                writer.write("</table>");

                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
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
                writer.write("    <th width=\"204\"><strong>PURCHASE/CLOSING STOCK</strong></th>\n");
                writer.write("    <th width=\"204\"><strong>SALE/OPENING STOCK</strong></th>\n");
            }
                writer.write("    <th width=\"150\"><strong>CURRENCY</strong></th>\n");
//                writer.write("    <th width=\"150\"><strong>VALUE</strong></th>\n");
                writer.write("  </tr>\n");

                statementSumAccountCode=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account", 1);
                statementSumAccountName=systems.getTableDataArrayNum(OpenMSApp.Database_A, "Account",2);

                int xs=0;
                int ys=statementSumAccountName.length;
                String demacate="N";
                
                statementSumAccountTradingType=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 13);                
                statementSumAccountCategory=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount", 10);  
                statementSumAccountCurrency=systems.getTableDataArrayNum(OpenMSApp.Database_A, "vwTradingAccount",5);
                statementSumDistinctAccountCurrency=systems.getColumn(OpenMSApp.Database_A, "Select DISTINCT(vAccountUnit) from account",1);
                
                int num=statementSumDistinctAccountCurrency.length;
                String sumRevenue[]=new String[num];
                String sumExpense[]=new String[num];
                int holder=0;               
                
                while(xs<ys)
                {
                    demacate=changeDemacate(demacate);
                    String statementTotalCredit="";
                    String statementTotalDebit="";
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK"))
                    {
                        String openingStock[]=systems.getColumn(OpenMSApp.Database_A, "Select mTotalAmount from creditorder where cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted'  and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59' "+transferActivityState+" ", 1);
                        statementTotalCredit=openingStock[0];
                        statementTotalDebit=openingStock[openingStock.length-1];
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalCredit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        statementTotalDebit="";
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE"))
                    {
                        statementTotalCredit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from creditOrder WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A, "Select SUM(mTotalAmount) from debitOrder  WHERE cAccountCode='"+statementSumAccountCode[xs]+"' and cStatus='posted' and dOrderDate BETWEEN '"+startDate+" 00:00:00' AND '"+endDate+" 23:59:59'  "+transferActivityState+" ",1);
                        statementTotalDebit=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
                            statementTotalCredit+" , "+statementTotalDebit+Connect.procInitEnd,"mSumValue"));
                        
                        if(statementSumAccountCategory[xs].equalsIgnoreCase("EXPENSE")){
                        statementTotalDebit=systems.getValue(OpenMSApp.Database_A,"Select -1 * "+statementTotalDebit+" AS 'NEGATION'",1);
                        }
                        statementTotalCredit="";
                    }
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
                        statementBalance=systems.converToRealMoneyFormatNeg(systems.getValue(OpenMSApp.Database_A,Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+
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
//            if(transferActivityState.isEmpty())
//            {
//                    writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 9)+"</td>\n");
//            }
//            else{
//                    writer.write("    <td>"+getAccountValue(systems.getValue(OpenMSApp.Database_A, "Select * from Account Where vAccountName='"+statementSumAccountName[xs]+"'", 10))+"</td>\n");
//            }
                 
                for(int check=0;check<num;check++)
                {
                 if(statementSumAccountCurrency[xs].equalsIgnoreCase(statementSumDistinctAccountCurrency[check]))
                 {
                     holder=check;
                     break;
                 }
                }
                    if(sumExpense[holder]==null)sumExpense[holder]="0";
                    if(sumRevenue[holder]==null)sumRevenue[holder]="0";
                    
                    if(statementSumAccountTradingType[xs].equalsIgnoreCase("PURCHASE")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    }
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("SALE")){
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                  
                    else if(statementSumAccountTradingType[xs].equalsIgnoreCase("STOCK")){
                    sumExpense[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalDebit+" , "+sumExpense[holder]+Connect.procInitEnd, "mSumValue");
                    sumRevenue[holder]=systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+statementTotalCredit+" , "+sumRevenue[holder]+Connect.procInitEnd, "mSumValue");
                    }                      
            
                    writer.write("  </tr>\n");
                    xs+=1;
                    if(xs==ys){break;}
                }
                
                demacate="N";
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"4\">&nbsp;</td>\n");
                writer.write(" </tr>\n");
                
                for(int check=0;check<num;check++)
                {
                    writer.write(" <tr>\n");
                    writer.write("    <th "+setDemacateSyntax(demacate)+"><STRONG>PROFIT (OR LOSS) ["+statementSumDistinctAccountCurrency[check]+"]</STRONG></th>\n");
                    writer.write("    <td colspan=\"3\">"+systems.getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumRevenue[check]+" , "+sumExpense[check].replace("-", "") +Connect.procInitEnd, "mSumValue")+"</td>\n");
                    writer.write(" </tr>\n");
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
        //resetVariable();
    }

}
