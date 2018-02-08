
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

final  class   ConnectUnsafe {
    
    static  Connection connect;
    static PreparedStatement statement;
    static ResultSet result;
    static String database;
    static String procInit="CALL";
    public  ConnectUnsafe(){
        
    }    

    public static Connection createMSSQLConnection(String dataSource,String userName,String password)
    {
    try
    {
    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    connect=DriverManager.getConnection("jdbc:Odbc:"+dataSource,userName,password);
   // System.out.println("Connection Accertained !");
    return connect;
    }
    catch(Exception e)
    {
        System.out.println("Error while connecting to database "+e);

        JOptionPane.showMessageDialog(JOptionPane.getRootFrame()," UNABLE TO ESTABLISH A CONNECTION \n WITH THE SERVER","Connection ERROR",JOptionPane.ERROR_MESSAGE);
    }
    return connect;
    }

    public static Connection createMySQLConnection(String datatabaseDomain,String userName,String password)
    {
    try
    {
    Class.forName("com.mysql.jdbc.Driver");
    connect=DriverManager.getConnection("jdbc:mysql://"+datatabaseDomain,userName,password);
   // System.out.println("Connection Accertained !");
    return connect;
    }
    catch(Exception e)
    {
        System.out.println("Error while connecting to database "+e);

        JOptionPane.showMessageDialog(JOptionPane.getRootFrame()," UNABLE TO ESTABLISH A CONNECTION \n WITH THE SERVER","Connection ERROR",JOptionPane.ERROR_MESSAGE);
    }
    return connect;
    }

     static Connection createMSSQLConnection()
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
   // System.out.println("Connection Accertained !");
    return connect;
    }
    catch(Exception e)
    {
        System.out.println("Error encounter while entering data in the database "+e);

        JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Can not Establish a Connection \n with the Database","Connection ERROR",JOptionPane.ERROR_MESSAGE);
    }
    return connect;
    }
*
     *
     */

     static PreparedStatement  createStatement(String preparedStatement)
    {
        try
        {
            statement=connect.prepareStatement(preparedStatement);
         //   System.out.println("Query Creation good !");
        }
        catch(Exception  e)
        {
            System.out.println("Query Creation Fail !"+e);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request could not be Granted","Creation ERROR",JOptionPane.ERROR_MESSAGE);
        }
            return  statement;
    }
    
     static ResultSet executeRStatement(PreparedStatement  statement)
    {
        try{
            result=statement.executeQuery();
        //    System.out.println("statement  executed with Result!");            
        }
        catch(Exception e)
        {           
            System.out.println("Query Execution Fail !"+e);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request could not be Granted","Execution ERROR",JOptionPane.ERROR_MESSAGE);            
        }
        return  result;
    }
        public static void executeStatement(PreparedStatement  statement)
        {
        try{
            statement.execute();
        //    System.out.println("Statement  Executed !");            
        }
        catch(Exception e)
        {           
            System.out.println("Query Execution Fail !"+e);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request could not be Granted","Execution ERROR",JOptionPane.ERROR_MESSAGE);            
        }
    }
        public static int executeUpdateStatement(PreparedStatement  statements)
        {
            int i=0;
        try{
            i=statements.executeUpdate();
        //    System.out.println("Statement  Executed !");  
         JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request processed succesfully !","Execution COMPLETE",JOptionPane.INFORMATION_MESSAGE);
        }
        catch(Exception e)
        {           
            System.out.println("Query Execution Fail !"+e);
            e.printStackTrace();
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Request could not be Granted","Execution ERROR",JOptionPane.ERROR_MESSAGE);            
        }
         return i;
    }
     static void changeDB(String database)
    {
        try{
            executeStatement(createStatement("use "+database));   
         //  System.out.println("Database changed !");
         
        }
        catch(Exception e){
            System.out.println("Database not changed !"+e);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Required Database not Available","Execution ERROR",JOptionPane.ERROR_MESSAGE);                        
        }
    }
     static void closeConnection()
    {
        try{
        connect.close();
        //System.out.println("Connection closed !"); 
        //JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Connection Succesfully Closed","Execution SUCCESS",JOptionPane.INFORMATION_MESSAGE);                                               
        }
        catch(Exception e)
        {
            System.out.println("Connection not closed !"+e);
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Unable to Close the Connection","Operation WARNING",JOptionPane.ERROR_MESSAGE);
        }
    }

}
