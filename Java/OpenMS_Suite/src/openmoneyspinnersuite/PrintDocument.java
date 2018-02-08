
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
import javax.swing.*;
import java.awt.*;
import java.awt.print.*;
import java.io.*;
import javax.print.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

final class PrintDocument
{
static String showPageDialog="";
	PrintDocument(Component com)
	{
            print(com);
	}

	public void print(Component com)
	{
            PrinterJob p=PrinterJob.getPrinterJob();
            PageableDocument document;
            PrintableDocument documentPainter;
            PageFormat format;
            Graphics g=com.getGraphics();

            try
            {
                format=new PageFormat();
                java.awt.print.Paper nb=new java.awt.print.Paper();nb.setSize(10, 10);nb.setImageableArea(0, 0, 10, 10);
                format.setOrientation(1);format.setPaper(nb);

                document=new PageableDocument(com);

                document.getNumberOfPages();

                documentPainter=new PrintableDocument(com);

                p.setPageable(document);

                p.setPrintable(documentPainter);

                p.validatePage(format);

                p.setCopies(1);

                p.setJobName("Applicant Detail Script - Database Connected");

                p.printDialog();

                p.pageDialog(format);

                p.print();
                
                com.repaint();
            }
            catch(Exception e)
            {
                System.out.print("Can not print the document"+e);
            }
	}
}


class PrintableDocument implements Printable
{
Component com;

public PrintableDocument(Component com)
{
this.com=com;
}

public PrintableDocument(Component com,Graphics g,PageFormat pageFormat,int pageIndex)
{

try{
print(g,pageFormat,pageIndex);

}

catch(Exception e)
{
System.out.print("Error at point Printable.print()"+e);
}
g.dispose();
}

public int print(Graphics g,PageFormat pageFormat,int pageIndex) throws PrinterException
{
if(pageIndex>0)
{

return 1;
}
else
{

/**Graphics cg=getComponentGraphics(g);
Graphics co=SwingGraphics.createSwingGraphics(cg);
Rectangle rect=co.getClipBounds();
int W=com.getWidth();
int H=com.getHeight();
int x=rect.x;
int y=rect.y;**/
Graphics2D g2d=(Graphics2D)g;
//g=com.getGraphics();
//co.setClip(x,y,W,H);

g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
com.print(g);
//GraphicsConfiguration gc=g2d.getDeviceConfiguration();
//gc.getDefaultTransform();
//g.dispose();
/**/
return 0;
}
}

}

class PageableDocument implements Pageable
{
private static Vector page;

public PageableDocument()
{
page= new Vector();
}

public PageableDocument(Object o)
{
page= new Vector(10,0);
page.add(o);
}

public static PageableDocument getPage(int pageIndex) throws ArrayIndexOutOfBoundsException
{ return (PageableDocument)page.elementAt(pageIndex);
}

public int getNumberOfPages()
{
return page.size();
}

public PageFormat getPageFormat(int pageIndex) throws ArrayIndexOutOfBoundsException
{
return getPage(pageIndex).getPageFormat(pageIndex);
}

public Printable getPrintable(int pageIndex) throws IndexOutOfBoundsException
{
 return getPage(pageIndex).getPrintable(pageIndex);
}

}

