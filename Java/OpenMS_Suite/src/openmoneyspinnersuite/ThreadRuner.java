
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
import javax.swing.*;
import java.util.*;
import java.text.*;
final class ThreadRuner implements Runnable{
    static JLabel mainLabel;
    Thread tread=new Thread(this);
    public ThreadRuner()
    {
    }
    public void run()
    {
        //mainLabel=new JLabel();
        //DateFormat formater=DateFormat.
        for(;;)
        {
            if(OpenMSApp.appArchType.equalsIgnoreCase("Client"))
                monitorConnection();
            Calendar calendar=Calendar.getInstance();
            DateFormat formater=DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.FULL);
            try
            {
                mainLabel.setText("Today: "+formater.format(calendar.getTime())+OpenMSApp.curIP);
                //System.out.println("thread runing..."+formater.format(calendar.getTime()));
                tread.sleep(1000);
            }
            catch(Exception e)
            {
                System.out.println("ThreadRunner.run gave Error !"+e);
            }
            //monitorHeap();
        }
    }
    public void initComp(JLabel label)
    {

    }
    void monitorConnection()
    {
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            java.sql.Connection connect=java.sql.DriverManager.getConnection("jdbc:mysql://"+Configuration.serverIPAddress+":1202","root","milli");
            connect.prepareStatement("Select * from company");
            if(OpenMSApp.curIP.equalsIgnoreCase(" | Disconnected"))
            Connect.createMSSQLConnection();
            OpenMSApp.curIP=" | Connection IP: "+Configuration.serverIPAddress;
            connect.close();
            connect=null;
        }
        catch(Exception e)
        {
            OpenMSApp.curIP=" | Disconnected";        
        }
    }
    void monitorHeap()
    {
        long freeHeap=Runtime.getRuntime().freeMemory();
        if(freeHeap<3000000)
        System.out.println("Free heap: "+freeHeap);
        if(freeHeap<2900000)
        {        
            System.out.println("Attempting to free more heap... ");
            Connect.closeMainConnection();
            Connect.clearAll();
            Connect.createMSSQLConnection();
            System.gc();
            Runtime.getRuntime().gc();
        }
    }
}
