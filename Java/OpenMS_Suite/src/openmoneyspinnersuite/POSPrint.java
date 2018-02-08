
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
 * POSPrint.java
 *
 * Created on Jun 17, 2013, 10:59:18 PM
 */
package openmoneyspinnersuite;

/**
 *
 * @author Abiodun
 */
import java.awt.print.*;
import java.awt.print.Printable;
import javax.print.attribute.*;
import javax.print.*;
import java.text.*;
import java.awt.*;
import javax.swing.*;
import java.util.concurrent.*;
import org.apache.pdfbox.pdmodel.*;
import org.apache.pdfbox.pdmodel.edit.*;
import org.apache.pdfbox.pdmodel.graphics.xobject.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDFont;
import java.io.*;

//import sun.swing.PrintingStatus;
//import sun.swing.SwingUtilities2;
import sun.swing.*;
//import sun.swing.text.TextComponentPrintable;
public class POSPrint extends javax.swing.JInternalFrame {
String receiptNo="",accountCode="",customerName="",amountInWords="",transDate="",orderBy="",discountAmount="",footerComment=Configuration.POSFooterComment,totalAmount="";
String[] item,description,amount;
Systems systems=new Systems();
PDDocument doc = null;
int pageIndex=0;
    //private Document model;
    /** Creates new form POSPrint */
    public POSPrint(String creditOrderCode) {
        initComponents();
        setReceiptValue(creditOrderCode);
    }    
    public Printable getPrintable(final MessageFormat headerFormat,
                                  final MessageFormat footerFormat) {
        return jEditorPane1.getPrintable(headerFormat, footerFormat);
    }    
//    public Document getDocument() {
//        return model;
//    }
    void setReceiptValue(String creditOrderCode)
    {
        String receiptContent="";
        receiptNo=creditOrderCode;
        totalAmount=systems.getValue(OpenMSApp.Database_A, "Select mTotalAmount from creditOrder where cCreditOrderCode='"+creditOrderCode+"'", "mTotalAmount");
        String orderSummary[][]=systems.getColumn(OpenMSApp.Database_A, "Select cItemCode,tDescription,mAmount from creditOrderSummary where cCreditOrderCode='"+creditOrderCode+"'", 1,2,3);
        item=orderSummary[0];
        description=orderSummary[1];
        amount=orderSummary[2];
        String orderByID=systems.getValue(OpenMSApp.Database_A, "Select cOrderByID from creditOrder where cCreditOrderCode='"+creditOrderCode+"'", "cOrderByID");
        String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select cEmployeeID from login where cLoginCode= '"+orderByID+"'","cEmployeeID");
        orderBy=systems.getValue(OpenMSApp.Database_A,"Select vFirstName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                systems.getValue(OpenMSApp.Database_A,"Select vMiddleName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                systems.getValue(OpenMSApp.Database_A,"Select vlastName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();
        transDate=systems.getValue(OpenMSApp.Database_A,"Select dOrderDate from creditOrder where cCreditOrderCode='"+creditOrderCode+"'","dOrderDate");
        customerName=systems.getValue(OpenMSApp.Database_A, "Select vName from vwMerchant where cMerchantID='"
                +systems.getValue(OpenMSApp.Database_A, "Select cMerchantID from creditOrder where cCreditOrderCode='"+creditOrderCode+"'", 1)+"'",1);
        receiptContent=receiptContent+OpenMSApp.CompanyName+"\n"+OpenMSApp.CompanyAddress+"\nTEL:"+OpenMSApp.CompanyPhone;
        receiptContent=receiptContent+"\nReceipt No: "+receiptNo+"\t\t Date: "+transDate;
        receiptContent=receiptContent+"\nCustomer Name: "+customerName+"\nOrder By: "+orderBy;
        receiptContent=receiptContent+"\n\nITEM\tQUANTITY\tRATE"
                //+"\tTAX"
                +"\tAMOUNT\n";
        for(int x=0;x<item.length;x++)
            receiptContent=receiptContent+(systems.getValue(OpenMSApp.Database_A, "Select vName from item where cItemCode='"+item[x]+"'", 1).length()>11?systems.getValue(OpenMSApp.Database_A, "Select vName from item where cItemCode='"+item[x]+"'", 1).substring(0, 11):systems.getValue(OpenMSApp.Database_A, "Select vName from item where cItemCode='"+item[x]+"'", 1)) +"\t"
                    +description[x].substring(description[x].lastIndexOf("Q=")+2, description[x].lastIndexOf(","))+"\t"
                    +description[x].substring(description[x].lastIndexOf("R=")+2, description[x].lastIndexOf("]"))+"\t"
                    //+description[x].substring(description[x].lastIndexOf("T=")+2, description[x].lastIndexOf(", Q"))+"\t"
                    +amount[x]+"\n";
        receiptContent=receiptContent+"\nTotal Amount: "+totalAmount;
        receiptContent=receiptContent+"\nSum Received: "+totalAmount;
        receiptContent=receiptContent+"\n"+footerComment;
        //receiptContent=receiptContent+"\nReceived only this text. Thank u!";
        jEditorPane1.setText(receiptContent);
        
        if(jCheckBox1.isSelected())
        print();
    }
    void updatePage(int curPageIndex,PDPage page[],float lastEntry,PDPageContentStream content[])
    {
        if(lastEntry<25)
        {
            try
            {
               content[curPageIndex].drawLine(513, lastEntry+9.1f, 513, 590);
               content[pageIndex].drawLine(448, lastEntry+9.1f, 448, 590);
               content[pageIndex].drawLine(378, lastEntry+9.1f, 378, 590);
               content[pageIndex].drawLine(323, lastEntry+9.1f, 323, 590);
               content[pageIndex].drawLine(178, lastEntry+9, 178, 590);
               content[pageIndex].drawLine(38, lastEntry+9, 38, 590);
               content[pageIndex].close();
                curPageIndex++;
                doc.addPage(page[curPageIndex]);
                lastEntry=580-11.9f; 
            }
            catch(Exception e)
            {               
                System.out.println("Update Page error: "+e);
            }
        }
    }
    void setInvoiceValue(String invoiceOrderCode)
    {
        String receiptContent="";
        receiptNo=invoiceOrderCode;
        totalAmount=systems.getValue(OpenMSApp.Database_A, "Select mTotalAmount from invoiceOrder where cInvoiceOrderCode='"+invoiceOrderCode+"'", "mTotalAmount");
        amountInWords=systems.getValue(OpenMSApp.Database_A, "Select tAmountInWords from invoiceOrder where cInvoiceOrderCode='"+invoiceOrderCode+"'", "tAmountInWords");
        
        accountCode=systems.getValue(OpenMSApp.Database_A, "Select cAccountCode from invoiceOrder where cInvoiceOrderCode='"+invoiceOrderCode+"'", 1);
        String currency=systems.getValue(OpenMSApp.Database_A, "Select vAccountUnit from Account where cAccountCode='"+accountCode+"'", 1);
        discountAmount=systems.getValue(OpenMSApp.Database_A, "Select mDiscount from invoiceOrder where cInvoiceOrderCode='"+invoiceOrderCode+"'", "mDiscount");
        String orderSummary[][]=systems.getColumn(OpenMSApp.Database_A, "Select cItemCode,tDescription,mAmount from invoiceOrderSummary where cinvoiceOrderCode='"+invoiceOrderCode+"'", 1,2,3);
        item=orderSummary[0];
        description=orderSummary[1];
        amount=orderSummary[2];
        String orderByID=systems.getValue(OpenMSApp.Database_A, "Select cOrderByID from invoiceOrder where cInvoiceOrderCode='"+invoiceOrderCode+"'", "cOrderByID");
        String OrderEmployeeID=systems.getValue(OpenMSApp.Database_A,"Select cEmployeeID from login where cLoginCode= '"+orderByID+"'","cEmployeeID");
        orderBy=systems.getValue(OpenMSApp.Database_A,"Select vFirstName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vFirstName").trim()+" "+
                systems.getValue(OpenMSApp.Database_A,"Select vMiddleName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vMiddleName").trim()+" "+
                systems.getValue(OpenMSApp.Database_A,"Select vlastName from Employee where cEmployeeID='"+OrderEmployeeID+"'","vlastName").trim();
        transDate=systems.getValue(OpenMSApp.Database_A,"Select dOrderDate from invoiceOrder where cInvoiceOrderCode='"+invoiceOrderCode+"'","dOrderDate");
        customerName=systems.getValue(OpenMSApp.Database_A, "Select vName from vwMerchant where cMerchantID='"
                +systems.getValue(OpenMSApp.Database_A, "Select cMerchantID from invoiceOrder where cInvoiceOrderCode='"+invoiceOrderCode+"'", 1)+"'",1);
        receiptContent=receiptContent+OpenMSApp.CompanyName+"\n"+OpenMSApp.CompanyAddress+"\nTEL:"+OpenMSApp.CompanyPhone;
        receiptContent=receiptContent+"\nReceipt No: "+receiptNo+"\t\t Date: "+transDate;
        receiptContent=receiptContent+"\nCustomer Name: "+customerName+"\nOrder By: "+orderBy;
        receiptContent=receiptContent+"\n\nITEM\tQUANTITY\tRATE"
                +"\tVAT"
                +"\tAMOUNT\n";
        for(int x=0;x<item.length;x++)
            receiptContent=receiptContent+(systems.getValue(OpenMSApp.Database_A, "Select vName from item where cItemCode='"+item[x]+"'", 1).length()>11?systems.getValue(OpenMSApp.Database_A, "Select vName from item where cItemCode='"+item[x]+"'", 1).substring(0, 11):systems.getValue(OpenMSApp.Database_A, "Select vName from item where cItemCode='"+item[x]+"'", 1)) +"\t"
                    +description[x].substring(description[x].lastIndexOf("Q=")+2, description[x].lastIndexOf(","))+"\t"
                    +description[x].substring(description[x].lastIndexOf("R=")+2, description[x].lastIndexOf("]"))+"\t"
                    +description[x].substring(description[x].lastIndexOf("T=")+2, description[x].lastIndexOf(", Q"))+"\t"
                    +amount[x]+"\n";
        String netAmount="";
        try{
        netAmount=systems.converToRealMoneyFormat(systems.getValue(OpenMSApp.Database_A, Connect.procInit+"  prnSubtractMoney"+Connect.procInitStart+""+totalAmount.trim()+" , "+discountAmount.trim()+Connect.procInitEnd, "mSumValue"));
        }catch(Exception e){System.out.println("Net amount error! "+e);}
        receiptContent=receiptContent+"\nGross Amount: "+totalAmount;
        receiptContent=receiptContent+"\nDiscount: "+discountAmount;
        receiptContent=receiptContent+"\nNet Amount: "+netAmount;
        receiptContent=receiptContent+"\n"+footerComment;
        //receiptContent=receiptContent+"\nReceived only this text. Thank u!";
        jEditorPane1.setText(receiptContent);
        PDDocument doc = null;
         int pageIndex=0;
        float lastEntry[]=new float[100];
        try{
            System.out.println("InvoicePDF Gen Started");
          /* Step 1: Prepare the document.
           */
         doc = new PDDocument();
         PDPage page[] = new PDPage[100];
         page[pageIndex] = new PDPage();
         doc.addPage(page[pageIndex]);
            PDFont fontNo = PDType1Font.HELVETICA_BOLD;
            PDFont font= PDType1Font.HELVETICA;
                    PDXObjectImage image = null,footerImage = null;
         image = new PDJpeg(doc, new FileInputStream(Configuration.officialInvoiceHeaderImagePath));
           
             footerImage = new PDJpeg(doc, new FileInputStream(Configuration.officialInvoiceFooterImagePath));
           PDPageContentStream content[] = new PDPageContentStream[100];
           content[pageIndex]=new PDPageContentStream(doc, page[pageIndex]);
         content[pageIndex].drawImage(image,5,640);
           content[pageIndex].beginText();
           content[pageIndex].setFont( fontNo, 10 );           
               content[pageIndex].moveTextPositionByAmount( 10.00f, 620f );
               content[pageIndex].drawString("Customer Name: ");
           content[pageIndex].endText();
           int currentYTextPosition=620;
           for(String lineCustomerName:systems.wrapString(customerName, 30))
           {
               content[pageIndex].beginText();
               content[pageIndex].setFont( font, 8.5f );           
                   content[pageIndex].moveTextPositionByAmount( 10.00f, currentYTextPosition-=8);
                   content[pageIndex].drawString(lineCustomerName);
               content[pageIndex].endText();
           }
           content[pageIndex].beginText();
           content[pageIndex].setFont( fontNo, 12 );           
               content[pageIndex].moveTextPositionByAmount( 250, 630 );
               content[pageIndex].drawString("NO.: "+receiptNo);
           content[pageIndex].endText();
           content[pageIndex].beginText();
           content[pageIndex].setFont( font, 10 );           
               content[pageIndex].moveTextPositionByAmount( 400, 620 );
               content[pageIndex].drawString("Date: "+transDate);
           content[pageIndex].endText();
           content[pageIndex].beginText();
           content[pageIndex].setFont( font, 10 );           
               content[pageIndex].moveTextPositionByAmount( 400, 605 );
               content[pageIndex].drawString("Order By: "+orderBy);
           content[pageIndex].endText();             
               content[pageIndex].addRect(8, 595, 180, 34);
           
           content[pageIndex].setFont( fontNo, 9); 
           content[pageIndex].beginText();          
               content[pageIndex].moveTextPositionByAmount( 10, 580 );
               content[pageIndex].drawString("S/N");
           content[pageIndex].endText();
           content[pageIndex].beginText();          
               content[pageIndex].moveTextPositionByAmount( 40, 580 );
               content[pageIndex].drawString("ITEM");
           content[pageIndex].endText();
           content[pageIndex].beginText();          
               content[pageIndex].moveTextPositionByAmount( 180, 580 );
               content[pageIndex].drawString("DESCRIPTION");
           content[pageIndex].endText();
           content[pageIndex].beginText();          
               content[pageIndex].moveTextPositionByAmount( 325, 580 );
               content[pageIndex].drawString("QUANTITY");
           content[pageIndex].endText();
           content[pageIndex].beginText();          
               content[pageIndex].moveTextPositionByAmount( 380, 580 );
               content[pageIndex].drawString("RATE");
           content[pageIndex].endText();
           content[pageIndex].beginText();           
               content[pageIndex].moveTextPositionByAmount( 450, 580 );
               content[pageIndex].drawString("VAT");
           content[pageIndex].endText();
           content[pageIndex].beginText();        
               content[pageIndex].moveTextPositionByAmount( 515, 580 );
               content[pageIndex].drawString("AMOUNT ("+currency+")");
           content[pageIndex].endText();
           //draw rect
           content[pageIndex].setStrokingColor(Color.BLUE);
           content[pageIndex].addRect(8, 576, 595, 14);     
           
               content[pageIndex].setFont( font, 8.50f );
               
//               lastEntry=580-((0+1)*12);
               lastEntry[pageIndex]=580-11.9f;
           int leastCellYPosition=(int)lastEntry[pageIndex],l=0;
           for(int x=0;x<item.length;x++)
//           for(int x=0,y=0;y<50&&x<item.length;x++)
           {
              // "ANONYMOUS OR NOT ANONYMOUS THAT IS NOT A PROBLEM ESPECIALLY IF THE LENGHT IS MUCH."
               currentYTextPosition=(int)lastEntry[pageIndex];
           System.out.println(leastCellYPosition+"New lastEntry="+lastEntry[pageIndex]);
               for(String lineSerial:systems.wrapString((x+1)+".", 6))
//               for(String lineSerial:systems.wrapString((x+1)+"."+l++ +"."+y, 6))
               {
                   content[pageIndex].beginText();           
                       content[pageIndex].moveTextPositionByAmount( 10, currentYTextPosition);
                       currentYTextPosition=currentYTextPosition-8;
                       //content[pageIndex].drawString((x+1)+".");
                       content[pageIndex].drawString(lineSerial);
                   content[pageIndex].endText();
               }
               leastCellYPosition=currentYTextPosition<leastCellYPosition?currentYTextPosition:leastCellYPosition;
               
               currentYTextPosition=(int)lastEntry[pageIndex];
               for(String lineItemName:systems.wrapString((systems.getValue(OpenMSApp.Database_A, "Select vName from item where cItemCode='"+item[x]+"'", 1)), 22))
               {
                   content[pageIndex].beginText();       
                       content[pageIndex].moveTextPositionByAmount( 40, currentYTextPosition);
                       currentYTextPosition=currentYTextPosition-8;
                       content[pageIndex].drawString(lineItemName);
//                       content[pageIndex].drawString((systems.getValue(OpenMSApp.Database_A, "Select vName from item where cItemCode='"+item[x]+"'", 1)));
                   content[pageIndex].endText();
               }
               leastCellYPosition=currentYTextPosition<leastCellYPosition?currentYTextPosition:leastCellYPosition;
               
               currentYTextPosition=(int)lastEntry[pageIndex];
               for(String lineItemDescription:systems.wrapString(description[x].substring(0,description[x].lastIndexOf("[")), 25))
               {
                   content[pageIndex].beginText();         
                       content[pageIndex].moveTextPositionByAmount( 180,currentYTextPosition );
                       currentYTextPosition=currentYTextPosition-8;
                       content[pageIndex].drawString(lineItemDescription);
//                       content[pageIndex].drawString(description[x].substring(0,description[x].lastIndexOf("[")));
                   content[pageIndex].endText();
               }
               leastCellYPosition=currentYTextPosition<leastCellYPosition?currentYTextPosition:leastCellYPosition;
               currentYTextPosition=(int)lastEntry[pageIndex];
               for(String lineQty:systems.wrapString(description[x].substring(description[x].lastIndexOf("Q=")+2, description[x].lastIndexOf(",")), 10))
               {
                   content[pageIndex].beginText();                
                       content[pageIndex].moveTextPositionByAmount( 325,currentYTextPosition );
                       currentYTextPosition=currentYTextPosition-8;
                       content[pageIndex].drawString(lineQty);
//                       content[pageIndex].drawString(description[x].substring(description[x].lastIndexOf("Q=")+2, description[x].lastIndexOf(",")));
                   content[pageIndex].endText();
               }
               leastCellYPosition=currentYTextPosition<leastCellYPosition?currentYTextPosition:leastCellYPosition;
               currentYTextPosition=(int)lastEntry[pageIndex];
               for(String lineRate:systems.wrapString(description[x].substring(description[x].lastIndexOf("R=")+2, description[x].lastIndexOf("]")), 15))
               {                   
                   content[pageIndex].beginText();         
                       content[pageIndex].moveTextPositionByAmount( 380,currentYTextPosition );
                       currentYTextPosition=currentYTextPosition-8;
    //                   content[pageIndex].moveTextPositionByAmount( 380, 580-((x+1)*15) );
                       content[pageIndex].drawString(lineRate);                   
//                       content[pageIndex].drawString(description[x].substring(description[x].lastIndexOf("R=")+2, description[x].lastIndexOf("]")));
                   content[pageIndex].endText();
               }
               leastCellYPosition=currentYTextPosition<leastCellYPosition?currentYTextPosition:leastCellYPosition;
               currentYTextPosition=(int)lastEntry[pageIndex];
               for(String lineTax:systems.wrapString(description[x].substring(description[x].lastIndexOf("T=")+2, description[x].lastIndexOf(", Q")), 13))
               {                   
                   content[pageIndex].beginText();         
                       content[pageIndex].moveTextPositionByAmount( 450,currentYTextPosition );
                       currentYTextPosition=currentYTextPosition-8;
                       content[pageIndex].drawString(lineTax);                   
//                       content[pageIndex].drawString(description[x].substring(description[x].lastIndexOf("T=")+2, description[x].lastIndexOf(", Q")));
                   content[pageIndex].endText();
               }
               leastCellYPosition=currentYTextPosition<leastCellYPosition?currentYTextPosition:leastCellYPosition;
               currentYTextPosition=(int)lastEntry[pageIndex];
               for(String lineAmount:systems.wrapString(amount[x], 18))
               {                   
                   content[pageIndex].beginText();         
                       content[pageIndex].moveTextPositionByAmount( 515,currentYTextPosition );
                       currentYTextPosition=currentYTextPosition-8;
                       content[pageIndex].drawString(lineAmount);
//                       content[pageIndex].drawString(amount[x]);
                   content[pageIndex].endText();  
               }
               leastCellYPosition=currentYTextPosition<leastCellYPosition?currentYTextPosition:leastCellYPosition;
               //content[pageIndex].drawLine(492, 492, 492, 580-((x+1)*15)+100);
           content[pageIndex].addRect(8, leastCellYPosition+6.7f, 595, lastEntry[pageIndex]-leastCellYPosition+1); 
               lastEntry[pageIndex]=leastCellYPosition-1.9f;  
               //updatePage(pageIndex,page,lastEntry[pageIndex],content);
//                if(lastEntry[pageIndex]<25)
//                {
//                   //System.out.println("lastEntry h<25="+lastEntry[pageIndex]);
//                    try
//                    {
//        //               content[curPageIndex].drawLine(513, lastEntry+9.1f, 513, 590);
//        //               content[pageIndex].drawLine(448, lastEntry+9.1f, 448, 590);
//        //               content[pageIndex].drawLine(378, lastEntry+9.1f, 378, 590);
//        //               content[pageIndex].drawLine(323, lastEntry+9.1f, 323, 590);
//        //               content[pageIndex].drawLine(178, lastEntry+9, 178, 590);
//        //               content[pageIndex].drawLine(38, lastEntry+9, 38, 590);
//        //               content[pageIndex].close();
//                   System.out.println(pageIndex+"lastEntry h<25="+lastEntry[pageIndex]);
//                        pageIndex+=1;
//                        page[pageIndex]=new PDPage();
//                        doc.addPage(page[pageIndex]);
//                        content[pageIndex]=new PDPageContentStream(doc, page[pageIndex]);
//                        lastEntry[pageIndex]=600-11.9f;
//                        leastCellYPosition=(int)lastEntry[pageIndex];
//                        System.out.println(pageIndex+"lastEntry chngd "+lastEntry[pageIndex]);
//                    }
//                    catch(Exception e)
//                    {               
//                        System.out.println("Update Page error: "+e);
//                    }
//                }
//               x=x==item.length-1?0*((y++)-y):xC;
           }           
           System.out.println("New lastEntry="+lastEntry[pageIndex]);
           if(lastEntry[pageIndex]>200){               
               content[pageIndex].setFont( fontNo, 10 ); 
               content[pageIndex].beginText();          
                   content[pageIndex].moveTextPositionByAmount( 10, lastEntry[pageIndex]-20 );
                   content[pageIndex].drawString("Total Amount ("+currency+"): "+totalAmount);
               content[pageIndex].endText();      
               content[pageIndex].beginText();         
                   content[pageIndex].moveTextPositionByAmount( 10, lastEntry[pageIndex]-35 );
                   content[pageIndex].drawString("Discount Amount ("+currency+"): "+discountAmount);
               content[pageIndex].endText();      
               content[pageIndex].beginText();       
                   content[pageIndex].moveTextPositionByAmount( 10, lastEntry[pageIndex]-50 );
                   content[pageIndex].drawString("Net Amount ("+currency+"): "+netAmount);
               content[pageIndex].endText();    
               content[pageIndex].beginText();       
                   content[pageIndex].moveTextPositionByAmount( 380, lastEntry[pageIndex]-20 );
                   content[pageIndex].drawString("Amount in words: ");
               content[pageIndex].endText();
               content[pageIndex].addRect(378, lastEntry[pageIndex]-53, 225, 42);    
                    content[pageIndex].drawImage(footerImage,5,lastEntry[pageIndex]-200);
                    
                    currentYTextPosition=(int)lastEntry[pageIndex]-20;
               for(String lineAmountInWords:systems.wrapString(amountInWords, 40))
               {
                   content[pageIndex].beginText();
                   content[pageIndex].setFont( font, 8.5f );           
                       content[pageIndex].moveTextPositionByAmount(380,currentYTextPosition-=8);
                       content[pageIndex].drawString(lineAmountInWords);
                   content[pageIndex].endText();
               }
           
           }
           else{
             PDPage lastPage = new PDPage();
             doc.addPage(lastPage);
             pageIndex++;
               content[pageIndex] = new PDPageContentStream(doc, lastPage);               
               content[pageIndex].setFont( fontNo, 10 ); 
               content[pageIndex].beginText();          
                   content[pageIndex].moveTextPositionByAmount( 10, lastEntry[pageIndex]-20 );
                   content[pageIndex].drawString("Total Amount ("+currency+"): "+totalAmount);
               content[pageIndex].endText();      
               content[pageIndex].beginText();         
                   content[pageIndex].moveTextPositionByAmount( 10, lastEntry[pageIndex]-35 );
                   content[pageIndex].drawString("Discount Amount ("+currency+"): "+discountAmount);
               content[pageIndex].endText();      
               content[pageIndex].beginText();       
                   content[pageIndex].moveTextPositionByAmount( 10, lastEntry[pageIndex]-50 );
                   content[pageIndex].drawString("Net Amount ("+currency+"): "+netAmount);
               content[pageIndex].endText();    
               content[pageIndex].beginText();       
                   content[pageIndex].moveTextPositionByAmount( 380, lastEntry[pageIndex]-20 );
                   content[pageIndex].drawString("Amount in words: ");
               content[pageIndex].endText();
               content[pageIndex].addRect(378, lastEntry[pageIndex]-53, 225, 42);    
                    content[pageIndex].drawImage(footerImage,5,lastEntry[pageIndex]-200);
                    
                    currentYTextPosition=(int)lastEntry[pageIndex]-20;
               for(String lineAmountInWords:systems.wrapString(amountInWords, 40))
               {
                   content[pageIndex].beginText();
                   content[pageIndex].setFont( font, 8.5f );           
                       content[pageIndex].moveTextPositionByAmount(380,currentYTextPosition-=8);
                       content[pageIndex].drawString(lineAmountInWords);
                   content[pageIndex].endText();
               }
           
//                   lastPageContent.beginText();
//                   lastPageContent.setFont( font, 10 );           
//                       lastPageContent.moveTextPositionByAmount( 20, 730 );
//                       lastPageContent.drawString("Net Amount: "+totalAmount);
//                   lastPageContent.endText();
//
//             lastPageContent.drawImage(footerImage,5,40);
//               
//             lastPageContent.close();
           }
           for(int px=0;px<=pageIndex;px++)
           {
               content[px].drawLine(513, lastEntry[pageIndex]+9.1f, 513, 590);
               content[px].drawLine(448, lastEntry[pageIndex]+9.1f, 448, 590);
               content[px].drawLine(378, lastEntry[pageIndex]+9.1f, 378, 590);
               content[px].drawLine(323, lastEntry[pageIndex]+9.1f, 323, 590);
               content[px].drawLine(178, lastEntry[pageIndex]+9, 178, 590);
               content[px].drawLine(38, lastEntry[pageIndex]+9, 38, 590);
               content[px].close();
             System.out.println("Closed page:"+px);
           }
        doc.save("C:\\Program files\\Milliscript IT Enterprises\\"+receiptNo+"_invoice_pdf.pdf"); 
        
        //doc.print();
        } catch (Exception e){
             System.out.println("Exception pos"+e);
        }
        try
        {        
            Runtime run=Runtime.getRuntime();
            run.exec("C:\\Program files\\Milliscript IT Enterprises\\"+receiptNo+"_invoice_pdf.pdf");
            //run.exec("C:\\\"Program files\"\\\"Milliscript IT Enterprises\"\\"+receiptNo+"_invoice_pdf.pdf");
        }catch(Exception e)
        {
            System.out.println("System "+e);
        }
        if(jCheckBox1.isSelected())
        print();
    }
    
    public boolean print(final MessageFormat headerFormat,
            final MessageFormat footerFormat, 
            final boolean showPrintDialog,
            final PrintService service,
            final PrintRequestAttributeSet attributes, 
            final boolean interactive)
            throws PrinterException {

        final PrinterJob job = PrinterJob.getPrinterJob();
        final Printable printable;
        final PrintingStatus printingStatus;
        final PageFormat format;
        final boolean isHeadless = GraphicsEnvironment.isHeadless();
        final boolean isEventDispatchThread = 
            SwingUtilities.isEventDispatchThread();
        final Printable textPrintable = getPrintable(headerFormat, footerFormat);
        if (interactive && ! isHeadless) {
            printingStatus = 
                PrintingStatus.createPrintingStatus(this, job);
            printable = 
                printingStatus.createNotificationPrintable(textPrintable);
        } else {
            printingStatus = null; 
            printable = textPrintable;
        }

        if (service != null) {
            job.setPrintService(service);
        }
        //editng printing with new format for POS use
        format=new PageFormat();
        java.awt.print.Paper nb=new java.awt.print.Paper();nb.setSize(7210, 3970);nb.setImageableArea(0, 0, 7210, 3970);
        format.setOrientation(1);format.setPaper(nb);
        
        job.setPrintable(printable);
        job.setJobName("Milliscript MoneySpinner Suite POS Print");
        final PrintRequestAttributeSet attr = (attributes == null) 
            ? new HashPrintRequestAttributeSet() 
            : attributes;        

        //if (showPrintDialog && ! isHeadless && ! job.printDialog(attr)) {
        if (showPrintDialog && ! isHeadless && ! job.printDialog()) {
            return false;
        }

        job.pageDialog(format);
       //         job.validatePage(format);
        /*
         * there are three cases for printing:
         * 1. print non interactively (! interactive || isHeadless)
         * 2. print interactively off EDT
         * 3. print interactively on EDT
         * 
         * 1 and 2 prints on the current thread (3 prints on another thread)
         * 2 and 3 deal with PrintingStatusDialog
         */
        final Callable<Object> doPrint = 
            new Callable<Object>() {
                public Object call() throws Exception {
                    try {
                        //job.print(attr);
                        job.print();
                    } finally {
                        if (printingStatus != null) {
                            printingStatus.dispose();
                        }
                    }
                    return null;
                }
            };

        final FutureTask<Object> futurePrinting = 
            new FutureTask<Object>(doPrint);

        final Runnable runnablePrinting = 
            new Runnable() {
                public void run() {
                    //disable component
                    boolean wasEnabled = false;
                    if (isEventDispatchThread) {
                        if (isEnabled()) {
                            wasEnabled = true;
                            setEnabled(false);
                        }
                    } else {
                        try {
                            wasEnabled = SwingUtilities2.submit(
                                new Callable<Boolean>() {
                                    public Boolean call() throws Exception {
                                        boolean rv = isEnabled();
                                        if (rv) {
                                            setEnabled(false);
                                        } 
                                        return rv;
                                    }
                                }).get();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        } catch (ExecutionException e) {
                            Throwable cause = e.getCause();
                            if (cause instanceof Error) {
                                throw (Error) cause;
                            } 
                            if (cause instanceof RuntimeException) {
                                throw (RuntimeException) cause;
                            } 
                            throw new AssertionError(cause);
                        }
                    }

                    jEditorPane1.getDocument().render(futurePrinting);

                    //enable component
                    if (wasEnabled) {
                        if (isEventDispatchThread) {
                            setEnabled(true);
                        } else {
                            try {
                                SwingUtilities2.submit(
                                    new Runnable() {
                                        public void run() {
                                            setEnabled(true);
                                        }
                                    }, null).get();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } catch (ExecutionException e) {
                                Throwable cause = e.getCause();
                                if (cause instanceof Error) {
                                    throw (Error) cause;
                                } 
                                if (cause instanceof RuntimeException) {
                                    throw (RuntimeException) cause;
                                } 
                                throw new AssertionError(cause);
                            }
                        }
                    }
                }
            };
        
        if (! interactive || isHeadless) {
            runnablePrinting.run();
        } else {
            if (isEventDispatchThread) {
                (new Thread(runnablePrinting)).start();
                printingStatus.showModal(true);
            } else {
                printingStatus.showModal(false);
                runnablePrinting.run();
            }
        }
        
        //the printing is done successfully or otherwise. 
        //dialog is hidden if needed.
        try {
            futurePrinting.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof PrinterAbortException) {
                if (printingStatus != null
                    && printingStatus.isAborted()) {
                    return false;
                } else {
                    throw (PrinterAbortException) cause;
                }
            } else if (cause instanceof PrinterException) {
                throw (PrinterException) cause;
            } else if (cause instanceof RuntimeException) {
                throw (RuntimeException) cause;
            } else if (cause instanceof Error) {
                throw (Error) cause;
            } else {
                throw new AssertionError(cause);
            }
        }
        return true;
    }
    

    void print(){
        try
        {
            //new PrintDocument(jEditorPane1);
            //jEditorPane1.print();
            print(null, null, false, null, null, true);
        }
        catch(Exception e){System.out.println("Error occur while attempting to print POSreceipt: "+e);}
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jCheckBox1 = new javax.swing.JCheckBox();

        setClosable(true);
        setIconifiable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMS_Finance_App.class).getContext().getResourceMap(POSPrint.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setFrameIcon(resourceMap.getIcon("Form.frameIcon")); // NOI18N
        setName("Form"); // NOI18N
        setVisible(true);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jEditorPane1.setEditable(false);
        jEditorPane1.setFont(resourceMap.getFont("jEditorPane1.font")); // NOI18N
        jEditorPane1.setName("jEditorPane1"); // NOI18N
        jScrollPane1.setViewportView(jEditorPane1);

        jCheckBox1.setText(resourceMap.getString("jCheckBox1.text")); // NOI18N
        jCheckBox1.setName("jCheckBox1"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jCheckBox1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jCheckBox1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
