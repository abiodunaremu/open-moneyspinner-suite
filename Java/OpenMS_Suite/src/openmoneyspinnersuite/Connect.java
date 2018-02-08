
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
 * @author Engineer Abbey
 */

import javax.swing.*;
import java.sql.*;

final public class   Connect {
    
    static Connection connect;
    static String database;
    static String transactionState="Success";
    static String alert="N";
    static String alertRollBack="Y";
    static String procInit="CALL";
    static String procInitStart="(";
    static String procInitEnd=")";
    static String currentDB="";
    static boolean transInProgress=false;
    
      Connect(){
        
    }
       
    synchronized static Connection createMSSQLConnection(String dataSource,String userName,String password)
    {
        transInProgress=true;
        try
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connect=DriverManager.getConnection("jdbc:Odbc:"+dataSource,userName,password);
            connect.setAutoCommit(false);
        return connect;
        }
        catch(Exception e)
        {
            transactionState="rollBack";
            System.out.println("Connect.createMSSQLConnection gave error! "+e);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"UNABLE TO ESTABLISH A CONNECTION \n WITH THE SERVER ON "+Configuration.serverIPAddress,"Connection ERROR",JOptionPane.ERROR_MESSAGE);
        }
        transInProgress=false;
        return connect;
    }
    synchronized static Connection createMySQLConnection(String databaseDomain,String userName,String password)
    {
        transInProgress=true;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connect=DriverManager.getConnection("jdbc:mysql://"+databaseDomain,userName,password);
            connect.setAutoCommit(false);
            return connect;
        }
        catch(Exception e)
        {
            transactionState="rollBack";
            System.out.println("Connect.createMySQLConnection gave error! "+e);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"UNABLE TO ESTABLISH A CONNECTION \n WITH THE SERVER ON "+Configuration.serverIPAddress,"Connection ERROR",JOptionPane.ERROR_MESSAGE);
        }
        transInProgress=false;
        return connect;
    }

    synchronized static Connection createMSSQLConnection()
    {
        return createMySQLConnection(Configuration.serverIPAddress+":1202",OpenMSApp.SQL_U,OpenMSApp.SQL_P);
    }
    /*
    public static Connection createMSSQLConnection()
    {
    try
    {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    connect=DriverManager.getConnection("jdbc:Odbc:AbbeySql","sa","ab");
    connect.setAutoCommit(false);
    return connect;
    }
    catch(Exception e)
    {
        transactionState="rollBack";
        System.out.println("Connect.createMSSQLConnection gave error ! "+e);
        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Can not Establish a Connection \n with the Database","Connection ERROR",JOptionPane.ERROR_MESSAGE);
    }
    return connect;
    }
     *
     */


    synchronized static PreparedStatement createStatement(String preparedStatement)
    {
        transInProgress=true;
        PreparedStatement statement=null;
        try
        {
            statement=connect.prepareStatement(preparedStatement);
        }
        catch(Exception  e)
        {
            transactionState="rollBack";
            System.out.println("Connect.createStatement gave error !"+e);
           //JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request could not be Granted","Creation ERROR",JOptionPane.ERROR_MESSAGE);
        }
        transInProgress=false;
            return  statement;
    }
    
    synchronized static ResultSet executeRStatement(PreparedStatement  statement)
    {
        transInProgress=true;
        ResultSet result=null;
        try{
            result=statement.executeQuery();         
        }
        catch(Exception e)
        {
            transactionState="rollBack";
            System.out.println("Connect.executeRStament gave error Transaction Will Later Roll Back!"+e);
            //JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request could not be Granted","Execution ERROR",JOptionPane.ERROR_MESSAGE);
        }
        transInProgress=false;
        return  result;
    }
        synchronized public static void executeStatement(PreparedStatement  statement)
        {
            transInProgress=true;
        try{
            statement.execute();
            statement.close();
            statement=null;    
        }
        catch(Exception e)
        {
            transactionState="rollBack";
            System.out.println("Connect.executeStatement Gave Error Transaction Will Later Roll Back !"+e);
           // JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request could not be Granted","Execution ERROR",JOptionPane.ERROR_MESSAGE);
        }
        transInProgress=false;
    }
        synchronized public static int executeUpdateStatement(PreparedStatement  statements)
        {
            transInProgress=true;
            int i=0;
        try{
            i=statements.executeUpdate();
            statements.close();
            statements=null; 
            //JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"    Information Submited \n          Successfully !","Execution SUCCESS",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {
            transactionState="rollBack";
            System.out.println("Connect.executeUpdateStatement gave error Transaction Will Later Roll Back! "+e);
            e.printStackTrace();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request could not be granted","Execution ERROR",JOptionPane.ERROR_MESSAGE);            
        }
        transInProgress=false;
         return i;
    }
    synchronized static void changeDB(String database)
    {
        transInProgress=true;
            try{
                executeStatement(createStatement("use "+database)); 
                currentDB=database;
            }
            catch(Exception e){
                transactionState="rollBack";
                System.out.println("Connect.ChangeDB gave error! "+e);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"REQUIRED DATABASE NOT AVAILABLE","Execution ERROR",JOptionPane.ERROR_MESSAGE);
            }
            transInProgress=false;
    }
    synchronized static void closeConnection()
    {
        
        if(transactionState.equalsIgnoreCase("rollBack"))
        {
            try
            {
                connect.rollback();
                //connect.commit();
                if(alertRollBack.equalsIgnoreCase("Y"))
                {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Execution Error Occur While Processing Request \n     Transaction Rolled Back !","Operation WARNING",JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(Exception e)
            {
                System.out.println("connect.closeConnection (Roll Back) gave error ! "+e);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Unable to Close the Connection","Operation WARNING",JOptionPane.ERROR_MESSAGE);
            }

        }
        else
        {
            try
            {
                connect.commit();
                if(alert.equalsIgnoreCase("Y"))
                {
                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request Proccessed Successfully !","Execution COMPLETE",JOptionPane.INFORMATION_MESSAGE);
                }
            }
            catch(Exception e)
            {
                System.out.println("connect.closeConnection (Commit) gave error ! "+e);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Unable to Close the Connection","Operation WARNING",JOptionPane.ERROR_MESSAGE);
            }
        }
        transactionState="Success";
        alert="N";
        alertRollBack="Y";
    }
    synchronized static void closeMainConnection()
    {
        if(transInProgress)
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame()," Close connection ignored \n A transaction is been processed.","Operation WARNING",JOptionPane.ERROR_MESSAGE);
            try
            {
                if(connect!=null)
                connect.close();
            }
            catch(Exception e)
            {
                System.out.println("connect.closeMainConnection (Commit) gave error ! "+e);
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Unable to Close the Connection","Operation WARNING",JOptionPane.ERROR_MESSAGE);
            }
    }
    synchronized static void clearAll()
    {
        try{
            connect.close();
        }catch(Exception e){
            System.out.println("Clear all error:"+e);
        }
        connect=null;
        currentDB=null;
    
    }
}
