
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
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import  java.io.*;
import  java.util.*;

final class Systems{
Connect con;
Calendar  calendar;
PreparedStatement statement;
static String dtLevel="Process Started";
static int heapCounter=0;
static boolean freeingHeap=false;

    public  Systems(){
    }
    static Systems getInstance()
    {
        return new Systems();
    }
    public  String[]  getSex()
    { 
        
    String sex[]={"--Select--","Female","Male"};
    return    sex;      
        
    }
    public  String[]  getClasses()
    {         
    String classes[]={"--Select--","SSS 3","SSS 2","SSS1","JSS 3","JSS 2","JSS 1"};  
    return    classes;      
        
    }
    public  String[]  getTableDataArray(String database,String  table,int  columnNum)
    {
    ResultSet   result;
    String data[]={};
    int numberRows=0;
    int x=0;

    try
    {
        numberRows=getRows(database,table);
        data=new String[numberRows];
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        result=Connect.executeRStatement(Connect.createStatement("select * from "+table));
        while(result.next())
        {
            x+=1;
            String data2= result.getString(columnNum);
            if(data2==null)
            {
                data[x]=data2;
            }
            else
            {
                data[x]=data2.trim();
            }
            data2=null;
        }
        Connect.closeConnection();
        result.close();
        result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("error"+e);        
    }
        return data;
    }
    public  String[][]  getTableDataArray(String database,String  table,int... columnNum)
    {
    ResultSet   result;
    String data[][]={};
    int numberRows=0;
    int x=0;

    try
    {
        numberRows=getRows(database,table);
        data=new String[columnNum.length][numberRows];
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        result=Connect.executeRStatement(Connect.createStatement("select * from "+table));
        while(result.next())
        {
            x+=1;
            for(int w=0;w<columnNum.length;w++)
            {
                String data2= result.getString(columnNum[w]);
                if(data2==null)
                {
                    data[w][x]=data2;
                }
                else
                {
                    data[w][x]=data2.trim();
                }
                data2=null;
            }
        }
        Connect.closeConnection();
        result.close();
        result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("error"+e);        
    }
        return data;
    }
    public  String[]  getTableDataArrayNum(String database,String  table,int  columnNum)
    {
    ResultSet   result;
    String data[]={};
    int numberRows=0;
    int x=0;

    try{
    numberRows=countRow(database,"Select * from "+table);
    data=new String[numberRows];
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    result=Connect.executeRStatement(Connect.createStatement("select * from "+table));
    while(result.next())
    {
        String data2= result.getString(columnNum);
        if(data2==null)
        {
            data[x]=data2;
        }
        else
        {
            data[x]=data2.trim();
        }
        data2=null;
        x+=1;
    }
    Connect.closeConnection();
    result.close();
    result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("error"+e);
    }
        return data;
    }
    public  String[][]  getTableDataArrayNum(String database,String  table,int... columnNum)
    {
    ResultSet   result;
    String data[][]={};
    int numberRows=0;
    int x=0;

    try{
    numberRows=countRow(database,"Select * from "+table);
    data=new String[columnNum.length][numberRows];
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    result=Connect.executeRStatement(Connect.createStatement("select * from "+table));
    while(result.next())
    {
        for(int w=0;w<columnNum.length;w++)
        {
            String data2= result.getString(columnNum[w]);
            if(data2==null)
            {
                data[w][x]=data2;
            }
            else
            {
                data[w][x]=data2.trim();
            }
            data2=null;
        }
        x+=1;
    }
    Connect.closeConnection();
    result.close();
    result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("error"+e);
    }
        return data;
    }
    public  java.sql.Date[]  getTableDateArrayNum(String database,String  table,int  columnNum)
    {
    ResultSet   result;
    java.sql.Date data[]={};
    int numberRows=0;
    int x=0;

    try{
    numberRows=countRow(database,"Select * from "+table);
    data=new java.sql.Date[numberRows];
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    result=Connect.executeRStatement(Connect.createStatement("select * from "+table));
    while(result.next())
    {
        java.sql.Date data2= result.getDate(columnNum);
        if(data2==null)
        {
            data[x]=data2;
        }
        else
        {
            data[x]=data2;
        }
        data2=null;
        x+=1;
    }
    Connect.closeConnection();
    result.close();
    result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("error"+e);
    }
        return data;
    }
    public  java.sql.Date[][]  getTableDateArrayNum(String database,String  table,int... columnNum)
    {
    ResultSet   result;
    java.sql.Date data[][]={};
    int numberRows=0;
    int x=0;

    try{
    numberRows=countRow(database,"Select * from "+table);
    data=new java.sql.Date[columnNum.length][numberRows];
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    result=Connect.executeRStatement(Connect.createStatement("select * from "+table));
    while(result.next())
    {
        for(int w=0;w<columnNum.length;w++)
        {
            java.sql.Date data2= result.getDate(columnNum[w]);
            if(data2==null)
            {
                data[w][x]=data2;
            }
            else
            {
                data[w][x]=data2;
            }
            data2=null;
        }
        x+=1;
    }
    Connect.closeConnection();
    result.close();
    result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("error"+e);
    }
        return data;
    }
    public String getDateYear(java.sql.Date date)
    {
        String year="-- Year --";
        if(date!=null)
        {
        calendar=Calendar.getInstance();
        calendar.getTime();
        java.util.Date mainDate=new java.util.Date();
                mainDate=date;
        calendar.setTime(mainDate);
        int x=calendar.get(Calendar.YEAR);
        year=String.valueOf(x);
        calendar.clear();
        }
        return year;

    }
    public String getDateDay(java.sql.Date date)
    {
        String day="-- Day --";
        if(date!=null)
        {
        calendar=Calendar.getInstance();
        calendar.getTime();
        java.util.Date mainDate=new java.util.Date();
                mainDate=date;
        calendar.setTime(mainDate);
        int x=calendar.get(Calendar.DAY_OF_MONTH);
        day=String.valueOf(x);
        calendar.clear();
        }
        return day;

    }
    public String getDateMonth(java.sql.Date date)
    {
        String month="-- Month --";
        if(date!=null)
        {
        calendar=Calendar.getInstance();
        calendar.getTime();
        java.util.Date mainDate=new java.util.Date();
                mainDate=date;
        calendar.setTime(mainDate);
        calendar.get(Calendar.MONTH);
        month=calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        calendar.clear();
        }
        return month;

    }
    public  int getRows(String database,String  table)
    {
    ResultSet   rowsRetrived;
    int numberRows=0;

    try{
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    rowsRetrived=Connect.executeRStatement(Connect.createStatement("select * from   "+table));
    //while(rowsRetrived.next()){numberRows=rowsRetrived.getRow();} 
    if(rowsRetrived.last()){numberRows=rowsRetrived.getRow();} 
    numberRows+=1;
    //System.out.println(table+" has number of rows= "+numberRows);
    Connect.closeConnection();
    rowsRetrived.close();
    rowsRetrived=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("System.getRows gave error :"+e);
    }
        return numberRows;
    }
    public boolean isDateFormat(String dateSource)
    {
        boolean isDate=true;
        java.text.DateFormat dateFormat= java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);
        try
        {
            dateFormat.parse(dateSource);
        }
        catch(Exception e)
        {
            isDate=false;
        }

        return isDate;
    }
    boolean isSQLDateFormat(String dateSource)
    {
        boolean isDate=true;
        try
        {
            java.sql.Date.valueOf(dateSource);
        }
        catch(Exception e)
        {
            isDate=false;
        }

        return isDate;
    }
    public String extractDate(String value)
    {
        java.text.DateFormat dateFormater= java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG);
        String date=value;

        if(isDateFormat(value))
        {
            date=dateFormater.format(value);
        }
        return date;
    }
    public String extractSQLDate(java.sql.Date sqlDate)
    {
        String date=getDateDay(sqlDate)+" "+getDateMonth(sqlDate)+", "+getDateYear(sqlDate);

        return date;
    }
   /* public java.sql.Date toSQLDate(String dateValue)
    {
        java.sql.Date sqlDate;
        java.text.DateFormat dateFormater= java.text.DateFormat..getDateInstance(java.text.DateFormat.LONG);
        try
        {
            java.util.Date calDate=dateFormater..parse(dateValue);
            sqlDate.v;
        }
        catch(Exception e)
        {
            System.out.println("Error at Systems.toSQLDate "+e);
        }

        return sqlDate;
    }*/
    public  int countRow(String database,String  query)
    {
    ResultSet   rowsRetrived;
    int numberRows=0;

    try{
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    rowsRetrived=Connect.executeRStatement(Connect.createStatement(query));
    //numberRows=rowsRetrived.getFetchSize();
    if(rowsRetrived.last()){numberRows=rowsRetrived.getRow();}
    //System.out.println("count number of rows= "+numberRows);
    Connect.closeConnection();
    rowsRetrived.close();
    rowsRetrived=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("System.countRow gave error :"+e);
    }
        return numberRows;
    }

    public  String[]  getColumn(String database,String query,int columnNum)
    {
    ResultSet   result;
    String data[]={};
    int numberRows=0;
    int x=0;

    try{
    numberRows=countRow(database,query);
    data=new String[numberRows];
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    result=Connect.executeRStatement(Connect.createStatement(query));
    while(x<numberRows)
    {
        result.next();
        String data2=result.getString(columnNum);
        if(data2==null)
        {
        data[x]=data2;
        }
        else
        {
            data[x]=data2.trim();
        }
            data2=null;
        x+=1;
        if(x==numberRows){break;}
    }
    Connect.closeConnection();
    result.close();
    result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("System.getColumn gave error"+e);
    }
        return data;
    }
    public  String[][]  getColumn(String database,String query,int...columnNum)
    {
    ResultSet   result;
    String data[][]={};
    int numberRows=0;
    int x=0;

    try{
    numberRows=countRow(database,query);
    data=new String[columnNum.length][numberRows];
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    result=Connect.executeRStatement(Connect.createStatement(query));
    while(x<numberRows)
    {
        result.next();
        for(int w=0;w<columnNum.length;w++)
        {
            String data2=result.getString(columnNum[w]);
            if(data2==null)
            {
            data[w][x]=data2;
            }
            else
            {
                data[w][x]=data2.trim();
            }
            data2=null;
        }
        x+=1;
        if(x==numberRows){break;}
    }
    Connect.closeConnection();
    result.close();
    result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("System.getColumn gave error"+e);
    }
        return data;
    }

    public  java.sql.Date[]  getDateColumn(String database,String query,int  columnNum)
    {
    ResultSet   result;
    java.sql.Date data[]={};
    int numberRows=0;
    int x=0;

    try{
    numberRows=countRow(database,query);
    data=new java.sql.Date[numberRows];
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    result=Connect.executeRStatement(Connect.createStatement(query));
    while(x<numberRows)
    {
        result.next();
        java.sql.Date data2=result.getDate(columnNum);
        if(data2==null)
        {
        data[x]=data2;
        }
        else
        {
            data[x]=data2;
        }
            data2=null;
        x+=1;
        if(x==numberRows){break;}
    }
    Connect.closeConnection();
    result.close();
    result=null;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("System.getColumn gave error"+e);
    }
        return data;
    }
    public  java.sql.Date[][]  getDateColumn(String database,String query,int...columnNum)
    {
    ResultSet   result;
    java.sql.Date data[][]={};
    int numberRows=0;
    int x=0;

    try{
    numberRows=countRow(database,query);
    data=new java.sql.Date[columnNum.length][numberRows];
    //Connect.createMSSQLConnection();
    Connect.changeDB(database);
    result=Connect.executeRStatement(Connect.createStatement(query));
    while(x<numberRows)
    {
        result.next();
        for(int w=0;w<columnNum.length;w++)
        {
            java.sql.Date data2=result.getDate(columnNum[w]);
            if(data2==null)
            {
            data[w][x]=data2;
            }
            else
            {
                data[w][x]=data2;
            }
            data2=null;
        }
        x+=1;
        if(x==numberRows){break;}
    }
    Connect.closeConnection();
    result.close();
    result=null;
    numberRows=0;
    }
    catch(Exception e)
    {
        e.printStackTrace();
        System.out.println("System.getColumn gave error"+e);
    }
        return data;
    }
    public String getValue(String database,String query,String column)
    {
        ResultSet rowValue;
        String value="";
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        rowValue=Connect.executeRStatement(Connect.createStatement(query));
        try
        {
            rowValue.next();
            String value2=rowValue.getString(column).trim();
            if(value2==null)
            {
                value=value2;
            }
            else
            {
                value=value2.trim();
            }
            value2=null;
        rowValue.close();
        }
        catch(Exception e)
        {System.out.println("System.getValue gave Error ! "+e);}
        rowValue=null;
        Connect.closeConnection();
        return value;
    }
    public String[] getValue(String database,String query,String... column)
    {
        ResultSet rowValue;
        String value[]={};
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        rowValue=Connect.executeRStatement(Connect.createStatement(query));
        try
        {
            value=new String[column.length];
            rowValue.next();
            for(int w=0;w<column.length;w++)
            {
                String value2=rowValue.getString(column[w]);
                if(value2==null)
                {
                    value[w]=value2;
                }
                else
                {
                    value[w]=value2.trim();
                }
            value2=null;
            }
        rowValue.close();
        }
        catch(Exception e)
        {System.out.println("System.getValue gave Error ! "+e);}

        rowValue=null;
        Connect.closeConnection();
        return value;
    }
    public String getValue(String database,String query,int column)
    {
        ResultSet rowValue;
        String value="";
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        rowValue=Connect.executeRStatement(Connect.createStatement(query));
        try
        {
            rowValue.next();
            String value2=rowValue.getString(column).trim();
            if(value2==null)
            {
                value=value2;
            }
            else
            {
                value=value2.trim();
            }
            
            value2=null;
        rowValue.close();
        }
        catch(Exception e)
        {System.out.println("System.getValue gave Error ! "+e);}
        rowValue=null;
        Connect.closeConnection();
        return value;
    }
    public String[] getValue(String database,String query,int...column)
    {
        ResultSet rowValue;
        String value[]={};
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        rowValue=Connect.executeRStatement(Connect.createStatement(query));
        try
        {
            value=new String[column.length];
            rowValue.next();
            for(int w=0;w<column.length;w++)
            {
                String value2=rowValue.getString(column[w]);
                if(value2==null)
                {
                    value[w]=value2;
                }
                else
                {
                    value[w]=value2.trim();
                }
                
                value2=null;
            }
        rowValue.close();
        }
        catch(Exception e)
        {System.out.println("System.getValue gave Error ! "+e);}
        rowValue=null;
        Connect.closeConnection();
        return value;
    }
    public java.sql.Date getDateValue(String database,String query,String column)
    {
        ResultSet rowValue;
        java.sql.Date value=null;
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        rowValue=Connect.executeRStatement(Connect.createStatement(query));
        try
        {
            rowValue.next();
            java.sql.Date value2=rowValue.getDate(column);
            if(value2==null)
            {
                value=value2;
            }
            else
            {
                value=value2;
            }
            value2=null;
        rowValue.close();
        }
        catch(Exception e)
        {System.out.println("System.getDateValue gave Error ! "+e);}

        rowValue=null;
        Connect.closeConnection();
        return value;
    }
    public java.sql.Date[] getDateValue(String database,String query,String... column)
    {
        ResultSet rowValue;
        java.sql.Date value[]=null;
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        rowValue=Connect.executeRStatement(Connect.createStatement(query));
        try
        {
            value= new java.sql.Date[column.length];
            rowValue.next();
            for(int w=0;w<column.length;w++)
            {
                java.sql.Date value2=rowValue.getDate(column[w]);
                if(value2==null)
                {
                    value[w]=value2;
                }
                else
                {
                    value[w]=value2;
                }
            }
        rowValue.close();
        }
        catch(Exception e)
        {System.out.println("System.getDateValue gave Error ! "+e);}

        Connect.closeConnection();
        return value;
    }
    //i created this new method on 11/22/2012 4:32 and found it more than 50% efficient than previous method
    //when multiple column of a table is needed in a method using the same query
 public java.sql.Date[] getDateValue(String database,String query,int...column)
    {
        ResultSet rowValue;
        java.sql.Date value[]=null;
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        rowValue=Connect.executeRStatement(Connect.createStatement(query));
        try
        {
            value=new java.sql.Date[column.length];
            rowValue.next();
            for(int w=0;w<column.length;w++)
            {
                java.sql.Date value2=rowValue.getDate(column[w]);
                if(value2==null)
                {
                    value[w]=value2;
                }
                else
                {
                    value[w]=value2;
                }
            }
        rowValue.close();
        }
        catch(Exception e)
        {System.out.println("System.getDateValue gave Error ! "+e);}

        Connect.closeConnection();
        return value;
    }
    public java.sql.Date getDateValue(String database,String query,int column)
    {
        ResultSet rowValue;
        java.sql.Date value=null;
        //Connect.createMSSQLConnection();
        Connect.changeDB(database);
        rowValue=Connect.executeRStatement(Connect.createStatement(query));
        try
        {
            rowValue.next();
            java.sql.Date value2=rowValue.getDate(column);
            if(value2==null)
            {
                value=value2;
            }
            else
            {
                value=value2;
            }    
        rowValue.close();        
        }
        catch(Exception e)
        {System.out.println("System.getDateValue gave Error ! "+e);}

        Connect.closeConnection();
        return value;
    }
    public String[] addArray(String[] firstArray,String[] secondArray,String separator,int size)
    {
        String[]   addition={};
        addition=new  String[size];
        int count=0;
            while(count<size)
            {
               addition[count]=firstArray[count]+separator+secondArray[count];
               count++;
            }

       // System.out.println(addition[1]);
        return  addition;
    }
    public String[] addArray(String[] firstArray,String[] secondArray,String separator)
    {
        String[]   addition={};
        addition=new  String[firstArray.length];
        int count=0;
            while(count<firstArray.length)
            {
               addition[count]=firstArray[count]+separator+secondArray[count];
               count++;
            }

       // System.out.println(addition[1]);
        return  addition;
    }
    public  String[] getYearsPlus(int leastYear,int plus)
    {   
        String  years[];
        calendar=Calendar.getInstance();

        @SuppressWarnings("static-access")
        int x=calendar.get(calendar.YEAR);
        years=new  String[x-leastYear+2];  
        int count=0;
        while(x>=leastYear)
        {
            count+=1;
            years[count]=String.valueOf(x+plus);
            //System.out.println(years[count]);
            x--;
        }

        //System.out.println(years[5]);
        return  years;      
    }
    public  String[] getMonths()
    {
        String  months[];
        calendar=Calendar.getInstance();
        int count=0;
        Map m=calendar.getDisplayNames(Calendar.MONTH,Calendar.LONG,Locale.ENGLISH);
        int si=m.size();
        Object  object[]=m.keySet().toArray();
        months=new  String[si+1];
        while(count<si)
        {
            count+=1;
            months[count]=String.valueOf(object[count-1]);
            //System.out.println(months[count]);
        }
        return  months;
    }

    public  String[] getMonthArray()
    {
         String[] months={"","January","February","March","April","May","June","July","August","September","October","November","December"};
         return months;
    }

    public String[] getYears(int leastYear)
    {   
        String  years[];
        calendar=Calendar.getInstance();
        int x=calendar.get(calendar.YEAR);
        years=new  String[x-leastYear+2];  
        int count=0;
        while(x>=leastYear)
        {
            count+=1;
            years[count]=String.valueOf(x);
            //System.out.println(years[count]);
            x--;
        }

        //System.out.println(years[5]);
        return  years;
    }

    public String[] getDays(String month)
    {
        String[] monthDays={};
        calendar=Calendar.getInstance();

        if(month.equalsIgnoreCase("January"))
        {
        calendar.set(Calendar.MONTH,calendar.JANUARY);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("February"))
        {
        calendar.set(Calendar.MONTH,calendar.FEBRUARY);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("March"))
        {
        calendar.set(Calendar.MONTH,calendar.MARCH);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("April"))
        {
        calendar.set(Calendar.MONTH,calendar.APRIL);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("May"))
        {
        calendar.set(Calendar.MONTH,calendar.MAY);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
           // System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("June"))
        {
        calendar.set(Calendar.MONTH,calendar.JUNE);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("July"))
        {
        calendar.set(Calendar.MONTH,calendar.JULY);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("August"))
        {
        calendar.set(Calendar.MONTH,calendar.AUGUST);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("September"))
        {
        calendar.set(Calendar.MONTH,calendar.SEPTEMBER);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("October"))
        {
        calendar.set(Calendar.MONTH,calendar.OCTOBER);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("November"))
        {
        calendar.set(Calendar.MONTH,calendar.NOVEMBER);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }
        if(month.equalsIgnoreCase("December"))
        {
        calendar.set(Calendar.MONTH,calendar.DECEMBER);
        int x=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        monthDays=new String[x+1];
        int count=0;
        while(x>count)
        {
            count+=1;
            monthDays[count]=String.valueOf(count);
            //System.out.println(monthDays[count]);
        }
        }

        return  monthDays;
    }

    public int getMonthMaximumDay(String month)
    {
        int maxDays=0;
        calendar=Calendar.getInstance();

        if(month.equalsIgnoreCase("January"))
        {
            calendar.set(Calendar.MONTH,calendar.JANUARY);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("February"))
        {
            calendar.set(Calendar.MONTH,calendar.FEBRUARY);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("March"))
        {
            calendar.set(Calendar.MONTH,calendar.MARCH);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("April"))
        {
            calendar.set(Calendar.MONTH,calendar.APRIL);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("May"))
        {
            calendar.set(Calendar.MONTH,calendar.MAY);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("June"))
        {
            calendar.set(Calendar.MONTH,calendar.JUNE);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("July"))
        {
            calendar.set(Calendar.MONTH,calendar.JULY);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("August"))
        {
            calendar.set(Calendar.MONTH,calendar.AUGUST);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("September"))
        {
            calendar.set(Calendar.MONTH,calendar.SEPTEMBER);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("October"))
        {
            calendar.set(Calendar.MONTH,calendar.OCTOBER);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("November"))
        {
        calendar.set(Calendar.MONTH,calendar.NOVEMBER);
        maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        if(month.equalsIgnoreCase("December"))
        {
            calendar.set(Calendar.MONTH,calendar.DECEMBER);
            maxDays=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        }
        
        return  maxDays;
    }

    public String getMonthDigit(String month)
    {
        String monthInt="";

        if(month.equalsIgnoreCase("January"))
        {
            monthInt="01";
        }
        if(month.equalsIgnoreCase("February"))
        {
            monthInt="02";
        }
        if(month.equalsIgnoreCase("March"))
        {
            monthInt="03";
        }
        if(month.equalsIgnoreCase("April"))
        {
            monthInt="04";
        }
        if(month.equalsIgnoreCase("May"))
        {
            monthInt="05";
        }
        if(month.equalsIgnoreCase("June"))
        {
            monthInt="06";
        }
        if(month.equalsIgnoreCase("July"))
        {
            monthInt="07";
        }
        if(month.equalsIgnoreCase("August"))
        {
            monthInt="08";
        }
        if(month.equalsIgnoreCase("September"))
        {
            monthInt="09";
        }
        if(month.equalsIgnoreCase("October"))
        {
            monthInt="10";
        }
        if(month.equalsIgnoreCase("November"))
        {
            monthInt="11";
        }
        if(month.equalsIgnoreCase("December"))
        {
            monthInt="12";
        }

        return  monthInt;
    }

    public String getMonthName(String monthDigit)
    {
        String monthName="";

        if(monthDigit.equalsIgnoreCase("01"))
        {
            monthName="January";
        }
        if(monthDigit.equalsIgnoreCase("02"))
        {
            monthName="February";
        }
        if(monthDigit.equalsIgnoreCase("03"))
        {
            monthName="March";
        }
        if(monthDigit.equalsIgnoreCase("04"))
        {
            monthName="April";
        }
        if(monthDigit.equalsIgnoreCase("05"))
        {
            monthName="May";
        }
        if(monthDigit.equalsIgnoreCase("06"))
        {
            monthName="June";
        }
        if(monthDigit.equalsIgnoreCase("07"))
        {
            monthName="July";
        }
        if(monthDigit.equalsIgnoreCase("08"))
        {
            monthName="August";
        }
        if(monthDigit.equalsIgnoreCase("09"))
        {
            monthName="September";
        }
        if(monthDigit.equalsIgnoreCase("10"))
        {
            monthName="October";
        }
        if(monthDigit.equalsIgnoreCase("11"))
        {
            monthName="November";
        }
        if(monthDigit.equalsIgnoreCase("12"))
        {
            monthName="December";
        }

        return  monthName;
    }

    public boolean isStringDigit(String value)
    {
        boolean state=true;

                        int xx=value.length();
                        int yy=0;
                        char[] ch2=value.toCharArray();

                        while(yy<xx)
                        {
                            try
                            {
                                if(!Character.isDigit(ch2[yy]))
                                {
                                    state=false;
                                    //System.out.println("Non digit character found in "+value+" The non-digit is "+ch2[yy]);
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println(e+" Non digit available in "+value+" non digit is "+ch2[yy]);
                            }
                        yy++;
                        }
      return state;
    }
    public boolean isStringBulkSMSFormat(String value)
    {
        boolean state=true;

                        int xx=value.length();
                        int yy=0;
                        char[] ch2=value.toCharArray();

                        while(yy<xx)
                        {
                            try
                            {
                                if((Character.isDigit(ch2[yy])==false)&&!(ch2[yy]==','))
                                {
                                    state=false;
                                    JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"Bulk numbers in wrong format\n Character '"+ch2[yy]+"' found","ERROR !",JOptionPane.ERROR_MESSAGE);
                                    
                                    //System.out.println("Non digit character found in "+value+" The non-digit is "+ch2[yy]);
                                    break;
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println(e+" Non digit available in "+value+" non digit is "+ch2[yy]);
                            }
                        yy++;
                        }
      return state;
    }
    public boolean isStringDecimal(String value)
    {
        boolean state=true;

                        int xx=value.length();
                        int yy=0;
                        char[] ch2=value.toCharArray();
                        int numOfDot=0;

                        while(yy<xx)
                        {
                            try
                            {
                                if(!Character.isDigit(ch2[yy]))
                                {
                                    if(ch2[yy]=='.')
                                    {
                                    }
                                    else
                                    {
                                        state=false;
                                        //System.out.println("1 Non decimal character found in "+value+" The non-decimal is "+ch2[yy]);
                                    }
                                }
                                if(ch2[yy]=='.')
                                {
                                    numOfDot++;
                                }
                                if(numOfDot>1)
                                {
                                    state=false;
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println(e+" Non decimal available in "+value+" non decimal is "+ch2[yy]);
                            }
                        yy++;
                        }
      return state;
    }
    public boolean isStringDecimalNeg(String value)
    {
        boolean state=true;

                        int xx=value.length();
                        int yy=0;
                        char[] ch2=value.toCharArray();
                        int numOfDot=0;

                        while(yy<xx)
                        {
                            try
                            {
                                if(!Character.isDigit(ch2[yy]))
                                {
                                    if(ch2[yy]=='.'||ch2[yy]=='-')
                                    {
                                    }
                                    else
                                    {
                                        state=false;
                                        //System.out.println("1 Non decimal character found in "+value+" The non-decimal is "+ch2[yy]);
                                    }
                                }
                                if(ch2[yy]=='.')
                                {
                                    numOfDot++;
                                }
                                if(numOfDot>1)
                                {
                                    state=false;
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println(e+" Non decimal available in "+value+" non decimal is "+ch2[yy]);
                            }
                        yy++;
                        }
      return state;
    }
    public String toTitleCase(String value)
    {
        boolean state=true;
        boolean programSuccess=true;
        int xx=value.length();
        int yy=0;
        value = value.trim().toLowerCase(Locale.ENGLISH);
        char[] ch2=value.toCharArray();

            while(yy<xx)
            {
                try
                {
                    if(state==true)
                    {
                        ch2[yy]=Character.toTitleCase(ch2[yy]);
                        state=false;
                    }
                    if(ch2[yy]==' ')
                    {
                        state=true;
                    }
                    else{
                        state=false;
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e+" Unable to convert "+value+" into sentence case "+ch2[yy]);
                    programSuccess=false;
                }
            yy++;
            }
            if(programSuccess==true){
                value=String.valueOf(ch2);
            }

      return value;
    }
    public String[] splitSQLMoney(String value)
    {
        //System.out.println(value+" has length "+value.length());
        String[] valueArray=new String[2];
        boolean startNow=false;
        int stage=1;
        char storeOne[];
        char storeTwo[]=new char[4];
        int count=0;
        int counter=0;

        storeTwo[0]='0';
        storeTwo[1]='0';
        storeTwo[2]='0';
        storeTwo[3]='0';

        int xx=value.length();
        int yy=0;
        char[] ch2=value.toCharArray();
        int numOfDot=0;


        if(ch2[0]=='.')
        {
                storeOne=new char[1];
                storeOne[0]='0';
        }
        else
        {
            while(yy<xx)
            {
                if(ch2[yy]=='.')
                {
                    startNow=true;
                }
                if(startNow==true)
                {
                    counter++;
                }
                yy++;
            }
               yy=0;
               storeOne =new char[value.length()-counter];
               //System.out.println(""+counter);
        }
                        while(yy<xx)
                        {
                            try
                            {
                                if(!Character.isDigit(ch2[yy]))
                                {
                                    if(ch2[yy]=='.')
                                    {
                                    }
                                    else
                                    {
                                        //System.out.println("1 Non decimal character found in "+value+" The non-decimal is "+ch2[yy]);
                                    }
                                }
                                if(ch2[yy]=='.')
                                {
                                        stage++;
                                }
                                if(numOfDot>1)
                                {
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println(e+" Non decimal available in "+value+" non decimal is "+ch2[yy]);
                            }
                            //System.out.println(stage);
                        if(stage==1)
                        {
                            storeOne[yy]=ch2[yy];
                        }
                        if(stage==3)
                        {
                            if(count<4)
                            {
                                storeTwo[count]=ch2[yy];
                                count++;
                            }
                        }
                        if(stage==2)
                        {
                            stage++;
                        }
                        yy++;
                        }

      String splitOne=String.valueOf(storeOne);
      String splitTwo=String.valueOf(storeTwo);
      valueArray[0]=splitOne;
      valueArray[1]=splitTwo;

        //System.out.println(valueArray[0]+" = [0] and [1] = "+valueArray[1]);

      return valueArray;
    }

    public String[] splitSQLMoneyNeg(String value)
    {
        //System.out.println(value+" has length "+value.length());
        String[] valueArray=new String[2];
        boolean startNow=false;
        int stage=1;
        char storeOne[];
        char storeTwo[]=new char[4];
        int count=0;
        int counter=0;

        storeTwo[0]='0';
        storeTwo[1]='0';
        storeTwo[2]='0';
        storeTwo[3]='0';

        int xx=value.length();
        int yy=0;
        char[] ch2=value.toCharArray();
        int numOfDot=0;


        if(ch2[0]=='.')
        {
                storeOne=new char[1];
                storeOne[0]='0';
        }
        else
        {
            while(yy<xx)
            {
                if(ch2[yy]=='.')
                {
                    startNow=true;
                }
                if(startNow==true)
                {
                    counter++;
                }
                yy++;
            }
               yy=0;
               storeOne =new char[value.length()-counter];
               //System.out.println(""+counter);
        }
                        while(yy<xx)
                        {
                            try
                            {
                                if(!Character.isDigit(ch2[yy]))
                                {
                                    if(ch2[yy]=='.'||ch2[yy]=='-')
                                    {
                                    }
                                    else
                                    {
                                        System.out.println("1 Non decimal character found in "+value+" The non-decimal is "+ch2[yy]);
                                    }
                                }
                                if(ch2[yy]=='.')
                                {
                                        stage++;
                                }
                                if(numOfDot>1)
                                {
                                }
                            }
                            catch(Exception e)
                            {
                                System.out.println(e+" Non decimal available in "+value+" non decimal is "+ch2[yy]);
                            }
                            //System.out.println(stage);
                        if(stage==1)
                        {
                            storeOne[yy]=ch2[yy];
                        }
                        if(stage==3)
                        {
                            if(count<4)
                            {
                                storeTwo[count]=ch2[yy];
                                count++;
                            }
                        }
                        if(stage==2)
                        {
                            stage++;
                        }
                        yy++;
                        }

      String splitOne=String.valueOf(storeOne);
      String splitTwo=String.valueOf(storeTwo);
      valueArray[0]=splitOne;
      valueArray[1]=splitTwo;

        //System.out.println(valueArray[0]+" = [0] and [1] = "+valueArray[1]);

      return valueArray;
    }

    public String converToRealMoneyFormat(String value)throws Exception
    {
        String realMoney="";
        char[] valueCharA;
        char[] valueCharB;
        char[] valueCharC=new char[2];
        value=value==null?"0.00":value;
        value=value.isEmpty()?"0.00":value;
        
        if(isStringDecimal(value))
        {
            String[] valueArray=splitSQLMoney(value);

     
                valueCharB=valueArray[1].toCharArray();
                valueCharC[0]=valueCharB[0];
                valueCharC[1]=valueCharB[1];
                
                //System.out.println(valueArray[0]+" AND "+valueArray[1]);
                
                realMoney=String.valueOf(valueArray[0])+"."+String.valueOf(valueCharC);
        }
        else
        {
            Exception e=new Exception();
            throw e;
        }
        //System.out.println("real money= "+realMoney);
        return realMoney;
    }


    public String converToRealMoneyFormatNeg(String value)throws Exception
    {
        String realMoney="";
        char[] valueCharA;
        char[] valueCharB;
        char[] valueCharC=new char[2];

        if(isStringDecimalNeg(value))
        {
            String[] valueArray=splitSQLMoneyNeg(value);

     
                valueCharB=valueArray[1].toCharArray();
                valueCharC[0]=valueCharB[0];
                valueCharC[1]=valueCharB[1];
                
                //System.out.println(valueArray[0]+" AND "+valueArray[1]);
                
                realMoney=String.valueOf(valueArray[0])+"."+String.valueOf(valueCharC);
        }
        else
        {
            Exception e=new Exception();
            throw e;
        }
        //System.out.println("real money= "+realMoney);
        return realMoney;
    }

    public Icon getEmployeeImage(String imagePath)
    {
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openmoneyspinnersuite.OpenMSApp.class).getContext().getResourceMap(ViewEmployee.class);
        Icon displayedImage=null;
        displayedImage=resourceMap.getIcon("picLabel.icon");
        Icon empImage=null;
        empImage=new ImageIcon(Configuration.employeeImageDirPath+imagePath+".jpg");
        if(empImage.getIconHeight()!=-1)
        {
            displayedImage=empImage;
        }
        return displayedImage;
    }
    public void deleteEmployeeImage(String imagePath)
    {
       java.io.File file;
       try
        {
            file=new java.io.File(Configuration.employeeImageDirPath+imagePath+".jpg");
            if(file.exists())
            {
                file.delete();
            }
       }
       catch(Exception e)
       {
            System.out.println("Unable to delete file:"+imagePath+" "+e);
       }
    }
    public void deleteImage(String imagePath)
    {
       java.io.File file;
       try
        {
            file=new java.io.File(imagePath);
            if(file.exists())
            {
                file.delete();
            }
       }
       catch(Exception e)
       {
            System.out.println("Unable to delete file:"+imagePath+" "+e);
       }
    }

    public String getItemTotalIRCreditQtyApp(String itemCode)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select cAccountCode from Account where cCategory='INVENTORY'", 1);
        String quantity="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select tDescription from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    1);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
        //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    public String getItemTotalIRCreditQty(String itemCode)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalIRCreditQty"+Connect.procInitStart+"'"+itemCode+"'"+Connect.procInitEnd,1);
        return quantity;
    }
    public String getItemTotalIRCreditQtyApp(String itemCode,String startDate,String endDate)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        String quantity="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select tDescription from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"' AND creditOrder.dCreditDate between "+startDate+" AND "+endDate,
                    1);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
        //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            description=null;
            quantityArray=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        irAccount=null;
        return quantity;
    }
    public String getItemTotalIRCreditQty(String itemCode,String startDate,String endDate)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalIRCreditQtyDatetime"+Connect.procInitStart+"'"+itemCode+"',"+startDate+","+endDate+Connect.procInitEnd,"ItemTotalIRCreditQty");
        return quantity;
    }
    public String getItemTotalIRDebitQtyApp(String itemCode)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        String quantity="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select tDescription from debitordersummary join debitOrder on debitOrderSummary.cDebitOrderCode=debitOrder.cDebitOrderCode where debitOrderSummary.cItemCode='"+
            itemCode+"' AND debitOrder.cStatus='posted' AND debitOrder.cAccountCode='"+irAccount[yi]+"'",
                    1);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
            //System.out.println("no of description debit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    public String getItemTotalIRDebitQty(String itemCode)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalIRDebitQty"+Connect.procInitStart+"'"+itemCode+"'"+Connect.procInitEnd,1);
        return quantity;
    }
    public String getItemTotalIRDebitQtyApp(String itemCode,String startDate,String endDate)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        String quantity="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select tDescription from debitordersummary join debitOrder on debitOrderSummary.cDebitOrderCode=debitOrder.cDebitOrderCode where debitOrderSummary.cItemCode='"+
            itemCode+"' AND debitOrder.cStatus='posted' AND debitOrder.cAccountCode='"+irAccount[yi]+"' AND debitOrder.dOrderDate between "+startDate+" AND "+endDate,
                    1);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
            //System.out.println("no of description debit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            description=null;
            quantityArray=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        irAccount=null;
        return quantity;
    }
    public String getItemTotalIRDebitQty(String itemCode,String startDate,String endDate)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalIRDebitQtyDatetime"+Connect.procInitStart+"'"+itemCode+"',"+startDate+","+endDate+Connect.procInitEnd,1);
        return quantity;
    }
    public String getItemTotalRevenueQtyApp(String itemCode)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='REVENUE'", 1);
        String quantity="0";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND CreditOrder.cAccountCode='"+rAccount[yi]+"' AND creditOrderSummary.cTag='OUT'",
                    3);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    public String getItemTotalRevenueQty(String itemCode)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalRevenueQty"+Connect.procInitStart+"'"+itemCode+"'"+Connect.procInitEnd,1);
        return quantity;
    }
    public String getItemTotalRevenueQtyApp(String itemCode,String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='REVENUE'", 1);
        String quantity="0";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND CreditOrder.cAccountCode='"+rAccount[yi]+"' AND creditOrderSummary.cTag='OUT' AND creditOrder.dOrderDate between "+startDate+" AND "+endDate,
                    3);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            description=null;
            quantityArray=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return quantity;
    }
    public String getItemTotalRevenueQty(String itemCode,String startDate,String endDate)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalRevenueQtyDatetime"+Connect.procInitStart+"'"+itemCode+"',"+startDate+","+endDate+Connect.procInitEnd,1);
        return quantity;
    }
    public String getItemTotalRevenueAmount(String itemCode,String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='REVENUE'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND CreditOrder.cAccountCode='"+rAccount[yi]+"' AND creditOrderSummary.cTag='OUT' AND creditOrder.dCreditDate between "+startDate+" AND "+endDate,
                    4);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getItemTotalInvAmount(String itemCode,String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND CreditOrder.cAccountCode='"+rAccount[yi]+"' AND creditOrderSummary.cTag='OUT' AND creditOrder.dCreditDate between "+startDate+" AND "+endDate,
                    4);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalRevenueAmount(String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='REVENUE'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from creditOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dCreditDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalAccountCreditAmount(String accountCode,String startDate,String endDate)
    {
        String rAccount[]={accountCode};
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from creditOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dOrderDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalExpenseAmount(String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='EXPENSE'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from debitOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dCreditDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalAccountDebitAmount(String accountCode,String startDate,String endDate)
    {
        String rAccount[]={accountCode};
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from debitOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dOrderDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalHybridCrAmount(String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='HYBRID'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from creditOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dCreditDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalHybridDrAmount(String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='HYBRID'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from debitOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dDebitDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalReceivableCrAmount(String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='RECEIVABLE'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from creditOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dCreditDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalReceivableDrAmount(String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='RECEIVABLE'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from debitOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dCreditDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalPayableCrAmount(String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='PAYABLE'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from creditOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dCreditDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getTotalPayableDrAmount(String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='PAYABLE'", 1);
        String totalAmount="0.00";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] amount=getColumn(OpenMSApp.Database_A, 
                    "Select * from debitOrder where cStatus='posted' AND cTransactionType!='TR' AND cAccountCode='"
                    +rAccount[yi]+"' AND dCreditDate between "+startDate+" AND "+endDate, 14);
            int x=amount.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                amount[y]=amount[y].trim();
                //quantityArray[y]=amount[y].substring(amount[y].lastIndexOf("Q=")+2, amount[y].lastIndexOf(","));
                totalAmount=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnAddMoney"+Connect.procInitStart+""+totalAmount+" , "+amount[y]+Connect.procInitEnd, "mSumValue");
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            amount=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return totalAmount;
    }
    public String getItemTotalIRDebitQtyIn(String itemCode,String consOrderCode)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select cAccountCode from Account where cCategory='INVENTORY'", 1);
        String quantity="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select tDescription from debitordersummary join debitOrder on debitOrderSummary.cDebitOrderCode=debitOrder.cDebitOrderCode where debitOrderSummary.cItemCode='"+
            itemCode+"' AND debitOrder.cStatus='posted' AND debitOrderSummary.cTag ='IN' AND debitOrder.cAccountCode='"+irAccount[yi]+"' AND debitOrder.cDebitOrderCode='"+consOrderCode+"'",
                    1);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
        //System.out.println("no of description debit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    public String getItemTotalIPDebitQtyIn(String itemCode,String consOrderCode)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select cAccountCode from Account where cCategory='INVENTORY PAYABLE'", 1);
        String quantity="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select tDescription from debitordersummary join debitOrder on debitOrderSummary.cDebitOrderCode=debitOrder.cDebitOrderCode where debitOrderSummary.cItemCode='"+
            itemCode+"' AND debitOrder.cStatus='posted' AND debitOrderSummary.cTag ='IN' AND debitOrder.cAccountCode='"+irAccount[yi]+"' AND debitOrder.cDebitOrderCode='"+consOrderCode+"'",
                    1);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
        //System.out.println("no of description debit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    public String getItemTotalIPDebitQtyApp(String itemCode)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY PAYABLE'", 1);
        String quantity="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select * from debitordersummary join debitOrder on debitOrderSummary.cDebitOrderCode=debitOrder.cDebitOrderCode where debitOrderSummary.cItemCode='"+
            itemCode+"' AND debitOrder.cStatus='posted' AND debitOrderSummary.cTag ='OUT' AND debitOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
        //System.out.println("no of description debit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    
    public String getItemTotalIPDebitQty(String itemCode)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalIPDebitQty"+Connect.procInitStart+"'"+itemCode+"'"+Connect.procInitEnd,1);
        return quantity;
    }
    
    public String getItemTotalIPDebitQtyApp(String itemCode,String startDate, String endDate)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY PAYABLE'", 1);
        String quantity="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select * from debitordersummary join debitOrder on debitOrderSummary.cDebitOrderCode=debitOrder.cDebitOrderCode where debitOrderSummary.cItemCode='"+
            itemCode+"' AND debitOrder.cStatus='posted' AND debitOrderSummary.cTag ='OUT' AND debitOrder.cAccountCode='"+irAccount[yi]+"' AND debitOrder.dOrderDate between "+startDate+" AND "+endDate,
                    3);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
        //System.out.println("no of description debit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            description=null;
            quantityArray=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        irAccount=null;
        return quantity;
    }
    public String getItemTotalIPDebitQty(String itemCode,String startDate, String endDate)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalIPDebitQtyDatetime"+Connect.procInitStart+"'"+itemCode+"',"+startDate+","+endDate+Connect.procInitEnd,1);
        return quantity;
    }
    public String getItemTotalRevenueQtyIn(String itemCode,String consOrderCode)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select cAccountCode from Account where cCategory='REVENUE'", 1);
        String quantity="0";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of revenue account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select tDescription from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted'  AND creditOrderSummary.cTag ='IN' AND CreditOrder.cAccountCode='"+rAccount[yi]+"' AND CreditOrder.cCreditOrderCode='"+consOrderCode+"'",
                    1);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
            //System.out.println("no of description revenue="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    
    public String getItemTotalIReceivableQtyIn(String itemCode,String consOrderCode)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select cAccountCode from Account where cCategory='INVENTORY RECEIVABLE'", 1);
        String quantity="0";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of inventory receivable account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select tDescription from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted'  AND creditOrderSummary.cTag ='IN' AND CreditOrder.cAccountCode='"+rAccount[yi]+"' AND CreditOrder.cCreditOrderCode='"+consOrderCode+"'",
                    1);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
            //System.out.println("no of description inventory receivable="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    public String getItemTotalIReceivableQtyApp(String itemCode)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY RECEIVABLE'", 1);
        String quantity="0";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of inventory receivable account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted'  AND creditOrderSummary.cTag ='OUT' AND CreditOrder.cAccountCode='"+rAccount[yi]+"'",
                    3);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
            //System.out.println("no of description inventory receivable="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return quantity;
    }
    public String getItemTotalIReceivableQty(String itemCode)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalInventoryReceivableQty"+Connect.procInitStart+"'"+itemCode+"'"+Connect.procInitEnd,1);
        return quantity;
    }
    public String getItemTotalIReceivableQtyApp(String itemCode,String startDate,String endDate)
    {
        String rAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY RECEIVABLE'", 1);
        String quantity="0";
        int xi=rAccount.length;
        int yi=0;
        //System.out.println("no of inventory receivable account="+xi);
        while(yi<xi)
        {
            String[] description=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted'  AND creditOrderSummary.cTag ='OUT' AND CreditOrder.cAccountCode='"+rAccount[yi]+"' AND creditOrder.dOrderDate between "+startDate+" AND "+endDate,
                    3);
            int x=description.length;
            int y=0;
            String[] quantityArray=new String[x];
            //System.out.println("no of description inventory receivable="+x+" for account "+rAccount[yi]);
            while(y<x)
            {
                description[y]=description[y].trim();
                quantityArray[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                quantity=getValue(OpenMSApp.Database_A, "Select "+quantity+" + "+quantityArray[y]+" AS 'Sum' ", 1);
                //System.out.println(description[y]+" extracted qty is "+quantityArray[y]+" sum qty is "+quantity);
                y++;
                if(x==y){break;}
            }
            description=null;
            quantityArray=null;
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        rAccount=null;
        return quantity;
    }
    public String getItemTotalIReceivableQty(String itemCode,String startDate,String endDate)
    {
        String quantity=getValue(OpenMSApp.Database_A, Connect.procInit+" prnGetItemTotalInventoryReceivableQtyDateTime"+Connect.procInitStart+"'"+itemCode+"',"+startDate+","+endDate+Connect.procInitEnd,1);
        return quantity;
    }
    public String getItemTotalIRCreditCostInApp(String itemCode)
    {
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        String cost="0";
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String[] amountIn=getColumn(OpenMSApp.Database_A,
           "Select mAmountItemIn from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"' AND creditOrderSummary.cTag='IN'",
                    8);
            int x=amountIn.length;
            int y=0;
            //String[] quantityArray=new String[x];
            //System.out.println("no of amountIn credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                cost=getValue(OpenMSApp.Database_A, "Select "+cost+" + "+amountIn[y]+" AS 'Sum' ", 1);
                //System.out.println(amountIn[y]+" extracted cost is "+amountIn[y]+" sum cost is "+cost);
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
        return cost;
    }
    public String getItemTotalIRCreditCostIn(String itemCode)
    {
        String cost=getValue(OpenMSApp.Database_A,Connect.procInit+" prnGetItemTotalIRCreditCostIn"+Connect.procInitStart+"'"+itemCode+"'"+Connect.procInitEnd,1);
        return cost;
    }

    void implementFIFO(String itemCode,String accounTag,String consOrderCode){
        
        String itemName=getValue(OpenMSApp.Database_A, "Select vName from Item where cItemCode='"+itemCode+"'", "vName");
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select cAccountCode from Account where cCategory='INVENTORY'", 1);
        //Code to check if an inventory account exists
        if(irAccount.length==0){System.out.println("Abortin FIFO Inventory account does not exist");}
        
        //Code to determine parameters @parameter of item @item with IN tag with and inventory account
        String[] orderCode,accountCode,description,quantity,rate,tag,quantityConsumed;
        
        orderCode=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                +" where creditOrder.cStatus='posted'  AND creditOrderSummary.cItemCode='"+itemCode+"' AND creditOrderSummary.cTag ='IN' "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",1);
        accountCode=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                + " where creditOrder.cStatus='posted'   AND creditOrderSummary.cItemCode='"+itemCode+"' AND creditOrderSummary.cTag ='IN' "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",11);
        description=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                + " where creditOrder.cStatus='posted'  AND creditOrderSummary.cItemCode='"+itemCode+"'  AND creditOrderSummary.cTag ='IN' "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",3);
        
            int x=description.length;
            int y=0;
            quantity=new String[x];
            rate=new String[x];
            if(x==0){System.out.println("Aborting FIFO for item:"+itemCode+" [zero FIFO record]");return;}
            //System.out.println("length of description is "+x);
            
            while(y<x)
            {
                description[y]=description[y].trim();
                quantity[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                rate[y]=description[y].substring(description[y].lastIndexOf("R=")+2, description[y].lastIndexOf("]"));
                
                y++;
                if(x==y){break;}
            }
            
       quantityConsumed=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                + " where creditOrder.cStatus='posted' AND creditOrderSummary.cItemCode='"+itemCode+"' AND creditOrderSummary.cTag ='IN' "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",7);
        //System.out.println("No of row entries in= "+orderCode.length);
        //System.out.println(orderCode[0]+" "+accountCode[0]+" "+description[0]+" "+quantity[0]+" "+rate[0]+" "+quantityConsumed[0]);
        
        //Code to determine the total quantity of item @item currently consumed
        String qtyInvDebitIn=getItemTotalIRDebitQtyIn(itemCode,consOrderCode);
        String qtyInvPayableDebitIn=getItemTotalIPDebitQtyIn(itemCode,consOrderCode);
        String qtyRevCreditIn=getItemTotalRevenueQtyIn(itemCode,consOrderCode);
        String qtyIReceivableCreditIn=getItemTotalIReceivableQtyIn(itemCode,consOrderCode);
        
        //Code to apply FIFO using total qty consumed by inventory debit
        //added new variable consOrderCode 29/5/13 to restrict FIFO to a processed order code
        implementFIFOD(itemCode,qtyInvDebitIn,orderCode,quantity,quantityConsumed,rate,consOrderCode);
        implementFIFOPayable(itemCode,qtyInvPayableDebitIn,orderCode,quantity,quantityConsumed,rate,accountCode,accounTag,itemName,consOrderCode);
                
        //Code to apply FIFO using total qty consumed by revenue
        String totCurrentQtyCons=getValue(OpenMSApp.Database_A, "Select "+qtyIReceivableCreditIn+" + "+qtyRevCreditIn+" AS 'Sum' ", 1);
        //System.out.println("total consumption= "+totCurrentQtyCons);
        if(totCurrentQtyCons.equalsIgnoreCase("0")){System.out.println("Aborting FIFO for item:"+itemCode+"[zeero consumption]");return;}
        else{System.out.println("total consumption= "+totCurrentQtyCons);}
        
        //Code to implement FIFO if first row satisfy quantity consumed
        String sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select ("+quantity[0]+" - "+quantityConsumed[0]+") AS 'Sum'", 1);
        String qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+totCurrentQtyCons+Connect.procInitEnd, "weightValue");
        //System.out.println("Quantity in= "+quantity[0]+" prev quantity consumed="+quantityConsumed[0]+" qtyAvailableMath="+sumDBQtyConsumed);
        String consumedCost;
        String costItemIn;
          if(qtyWeight.equalsIgnoreCase("1 >= 2")){
              //FIRST line of algorithm
              //System.out.println("First row satisfy consumption qty");
              consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+rate[0]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mMultValue");
              quantityConsumed[0]=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+quantityConsumed[0]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mSumValue");
              
              //System.out.println("CONSUMED COST="+consumedCost+" NEW QTYCONSUMED= "+quantityConsumed[0]);
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFirstFIFO"+Connect.procInitStart+"?,?,?,?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("First row satisfy consumption qty");
              //System.out.println("itemCode="+itemCode+"orderCode="+orderCode[0]+"quantityAvaialble="+quantity[0]+"ratee="+rate[0]+"consumedCost="+consumedCost+" quantityConsumed= "+quantityConsumed[0]);
                    statement.setString(1,itemCode);
                    statement.setString(2,orderCode[0]);             
                    statement.setString(3,quantityConsumed[0]);       
                    statement.setString(4,quantity[0]);        
                    statement.setString(5,consumedCost);       
                    statement.setString(6,rate[0]);    
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFO() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
        
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderOnFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
                    statement.setString(1,accountCode[0]);
                    statement.setString(2,accounTag);
                    statement.setString(3,OpenMSApp.LoginCode.trim());
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
                
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderSummaryOnFIFO"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
         try{//ICOC=iTEM CREDIT ORDER CODE
                    statement.setString(1,accountCode[0]);
                    statement.setString(2,"COSTING");
                    statement.setString(3,"Processed Order Code=["+consOrderCode+"] Item Code|Name=["+itemCode+"|"+itemName+"] Quantity consumed=["+totCurrentQtyCons+"] Rate=["+rate[0]+"] "+"ICOC=["+orderCode[0]+"]");
                    statement.setString(4,consumedCost);
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
        
            //System.out.println("----prnupdcreditordersummaryonFIFOEnd---1 --itemCode="+itemCode);
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFIFOEnd"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
        try
        {
            statement.setString(1,itemCode);
            statement.setString(2,consOrderCode);
        }
        catch(Exception e)
        {
            System.out.println("PostDebitOrder.postCurrentOrderCode gave error! @prn"+e);
        }
        Connect.executeUpdateStatement(statement);
        Connect.alert="N";
        Connect.closeConnection();
                                
              costItemIn="";//(quantity[0]-quantityConsumed[0]) * rate[0]
              //update creditOrderSummary set mAmountItemIn=costItemIn where cOrderCode=orderCode and cItemCode=itemCode
          }
          
          //Code to implement FIFO if more than one row satisfy quantity consumed
          if(qtyWeight.equalsIgnoreCase("1 < 2"))
          {
              //System.out.println("more than one row satisfy consumption qty");
                int numRowRequired=0;
        
              //Code to determine num of row satisfy qty consumed
              sumDBQtyConsumed="0";
              qtyWeight="";
              do{
                sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select "+sumDBQtyConsumed+"+("+quantity[numRowRequired]+" - "+quantityConsumed[numRowRequired]+") AS 'Sum'", 1);
                qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+totCurrentQtyCons+Connect.procInitEnd, "weightValue");
                //System.out.println("Quantity in= "+quantity[numRowRequired]+" prev quantity consumed="+quantityConsumed[numRowRequired]+" qtyAvailableMath="+sumDBQtyConsumed);
                
                  numRowRequired++;
                  if(qtyWeight.equalsIgnoreCase("1 >= 2")){
                      System.out.println("Num of rows satisfing consumption="+numRowRequired);
                      break;
                  }                  
              }
              while(qtyWeight.equalsIgnoreCase("1 < 2"));
              
              //Code to determine qty of item before final row  
              String sumQtyPenultimate="0";
              String[] qtyConsumed=new String[numRowRequired-1];
              String[] consCost=new String[numRowRequired-1];
              for(int count=0;count<=numRowRequired-2;count++)
              {              
                  sumQtyPenultimate=getValue(OpenMSApp.Database_A, "Select "+sumQtyPenultimate+"+("+quantity[count]+" - "+quantityConsumed[count]+") AS 'Sum'", 1);
                  qtyConsumed[count]=qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+quantity[count]+" , "+quantityConsumed[count]+Connect.procInitEnd, "mSumValue");
                  consCost[count]=qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+qtyConsumed[count]+" , "+rate[count]+Connect.procInitEnd, "mMultValue");
                  
                  //System.out.println("QtyConsumed= ["+qtyConsumed[count]+"] Rate="+rate[count]+" consCost="+consCost[count]);
                  //System.out.println("Sum of item qty till penultimate for ["+count+"] ="+sumQtyPenultimate);
              }              
              //System.out.println("Sum of item qty till penultimate="+sumQtyPenultimate);
              totCurrentQtyCons=getValue(OpenMSApp.Database_A, "Select ("+totCurrentQtyCons+" - "+sumQtyPenultimate+") AS 'Sum'", 1);
              quantityConsumed[numRowRequired-1]=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+quantityConsumed[numRowRequired-1]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mSumValue");
              
              consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+rate[numRowRequired-1]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mMultValue");
              
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        //First line of algorithm
              for(int row=0;row<=numRowRequired-2;row++)
              {              
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnSecondFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("More than one row satisfy consumption qty");
              //System.out.println("itemCode="+itemCode+"orderCode="+orderCode[0]+"quantityAvaialble="+quantity[0]);
                    statement.setString(1,itemCode);
                    statement.setString(2,orderCode[row]);             
                    statement.setString(3,quantity[row]);  
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFO() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
              
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderOnFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
                    statement.setString(1,accountCode[row]);
                    statement.setString(2,accounTag);
                    statement.setString(3,OpenMSApp.LoginCode.trim());
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
                
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderSummaryOnFIFO"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
         try{//ICOC=iTEM ORDER CODE
                    statement.setString(1,accountCode[row]);
                    statement.setString(2,"COSTING");
                    statement.setString(3,"Processed Order Code=["+consOrderCode+"] IOC=["+orderCode[row]+"]Item Code|Name=["+itemCode+"|"+itemName+"] Quantity consumed=["+qtyConsumed[row]+"] Rate=["+rate[row]+"]");
                    statement.setString(4,consCost[row]);
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
        
              }
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFirstFIFO"+Connect.procInitStart+"?,?,?,?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("More than row satisfy consumption qty");
              //System.out.println("itemCode="+itemCode+"orderCode="+orderCode[numRowRequired-1]+"quantity="+quantity[numRowRequired-1]+"ratee="+rate[numRowRequired-1]+"consumedCost="+consumedCost+" quantityConsumed= "+quantityConsumed[numRowRequired-1]);
                    statement.setString(1,itemCode);
                    statement.setString(2,orderCode[numRowRequired-1]);             
                    statement.setString(3,quantityConsumed[numRowRequired-1]);       
                    statement.setString(4,quantity[numRowRequired-1]);        
                    statement.setString(5,consumedCost);       
                    statement.setString(6,rate[numRowRequired-1]);    
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFO() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderOnFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
                    statement.setString(1,accountCode[numRowRequired-1]);
                    statement.setString(2,accounTag);
                    statement.setString(3,OpenMSApp.LoginCode.trim());
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
                
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderSummaryOnFIFO"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
         try{//ICOC=iTEM ORDER CODE
                    statement.setString(1,accountCode[numRowRequired-1]);
                    statement.setString(2,"COSTING");
                    statement.setString(3,"IOC=["+orderCode[numRowRequired-1]+"]Item Name=["+itemName+"] Quantity consumed=["+totCurrentQtyCons+"] Rate=["+rate[numRowRequired-1]+"]");
                    statement.setString(4,consumedCost);
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
        
        //System.out.println("----prnupdcreditordersummaryonFIFOEnd---2 --itemCode="+itemCode);
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFIFOEnd"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
        try
        {
            statement.setString(1,itemCode);
            statement.setString(2,consOrderCode);
        }
        catch(Exception e)
        {
            System.out.println("PostDebitOrder.postCurrentOrderCode gave error! "+e);
        }
        Connect.executeUpdateStatement(statement);
        Connect.alert="N";
        Connect.closeConnection();
//        Connect.closeMainConnection();
//        Connect.createMSSQLConnection();
          }
    }
    public void implementFIFOD(String itemCode,String qtyInvDebitIn,String orderCode[],String quantity[],String quantityConsumed[],String rate[],String procOrderCode){
    //Code to apply FIFO using total qty consumed by inventory debit
        String totCurrentQtyCons=qtyInvDebitIn;//systems.getValue(OpenMSApp.Database_A, "Select "+qtyInvDebitIn+" + "+qtyRevCreditIn+" AS 'Sum' ", 1);
        if(totCurrentQtyCons.equalsIgnoreCase("0")){System.out.println("D-Aborting FIFO for item:"+itemCode+"[zeero consumption]");return;}
        else{System.out.println("D-total consumption= "+totCurrentQtyCons);}
        
        //Code to implement FIFO if first row satisfy quantity consumed
        String sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select ("+quantity[0]+" - "+quantityConsumed[0]+") AS 'Sum'", 1);
        String qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+totCurrentQtyCons+Connect.procInitEnd, "weightValue");
        //System.out.println("D-Quantity in= "+quantity[0]+" prev quantity consumed="+quantityConsumed[0]+" qtyAvailableMath="+sumDBQtyConsumed);
        String consumedCost;
        String costItemIn;
          if(qtyWeight.equalsIgnoreCase("1 >= 2")){
              //FIRST line of algorithm
              //System.out.println("D-First row satisfy consumption qty");
              consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+rate[0]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mMultValue");
              quantityConsumed[0]=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+quantityConsumed[0]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mSumValue");
              
              //System.out.println("D-CONSUMED COST="+consumedCost+" NEW QTYCONSUMED= "+quantityConsumed[0]);
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFirstFIFO"+Connect.procInitStart+"?,?,?,?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("D-First row satisfy consumption qty");
              //System.out.println("D-itemCode="+itemCode+"orderCode="+orderCode[0]+"quantityAvaialble="+quantity[0]+"ratee="+rate[0]+"consumedCost="+consumedCost+" quantityConsumed= "+quantityConsumed[0]);
                    statement.setString(1,itemCode);
                    statement.setString(2,orderCode[0]);             
                    statement.setString(3,quantityConsumed[0]);       
                    statement.setString(4,quantity[0]);        
                    statement.setString(5,consumedCost);       
                    statement.setString(6,rate[0]);    
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFOD() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
                
        statement=Connect.createStatement(Connect.procInit+"  prnUpdDebitOrderSummaryOnFIFOEnd"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
        try
        {
            statement.setString(1,itemCode);
            statement.setString(2,procOrderCode);
        }
        catch(Exception e)
        {
            System.out.println("PostDebitOrder.implementFIFOD() gave error! "+e);
        }
        Connect.executeUpdateStatement(statement);
        Connect.alert="N";
        Connect.closeConnection();
                                
              costItemIn="";//(quantity[0]-quantityConsumed[0]) * rate[0]
              //update creditOrderSummary set mAmountItemIn=costItemIn where cOrderCode=orderCode and cItemCode=itemCode
          }
          
          //Code to implement FIFO if more than one row satisfy quantity consumed
          if(qtyWeight.equalsIgnoreCase("1 < 2"))
          {
              //System.out.println("D-more than one row satisfy consumption qty");
                int numRowRequired=0;
        
              //Code to determine num of row satisfy qty consumed
              sumDBQtyConsumed="0";
              qtyWeight="";
              do{
                sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select "+sumDBQtyConsumed+"+("+quantity[numRowRequired]+" - "+quantityConsumed[numRowRequired]+") AS 'Sum'", 1);
                qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+totCurrentQtyCons+Connect.procInitEnd, "weightValue");
                //System.out.println("D-Quantity in= "+quantity[numRowRequired]+" prev quantity consumed="+quantityConsumed[numRowRequired]+" qtyAvailableMath="+sumDBQtyConsumed);
                
                  numRowRequired++;
                  if(qtyWeight.equalsIgnoreCase("1 >= 2")){
                      System.out.println("D-Num of rows satisfing consumption="+numRowRequired);
                      break;
                  }                  
              }
              while(qtyWeight.equalsIgnoreCase("1 < 2"));
              
              //Code to determine qty of item before final row  
              String sumQtyPenultimate="0";
              String[] qtyConsumed=new String[numRowRequired-1];
              String[] consCost=new String[numRowRequired-1];
              for(int count=0;count<=numRowRequired-2;count++)
              {              
                  sumQtyPenultimate=getValue(OpenMSApp.Database_A, "Select "+sumQtyPenultimate+"+("+quantity[count]+" - "+quantityConsumed[count]+") AS 'Sum'", 1);
                  qtyConsumed[count]=qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+quantity[count]+" , "+quantityConsumed[count]+Connect.procInitEnd, "mSumValue");
                  consCost[count]=qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+qtyConsumed[count]+" , "+rate[count]+Connect.procInitEnd, "mMultValue");
                  
                  //System.out.println("D-QtyConsumed= ["+qtyConsumed[count]+"] Rate="+rate[count]+" consCost="+consCost[count]);
                  //System.out.println("D-Sum of item qty till penultimate for ["+count+"] ="+sumQtyPenultimate);
              }              
              //System.out.println("D-Sum of item qty till penultimate="+sumQtyPenultimate);
              totCurrentQtyCons=getValue(OpenMSApp.Database_A, "Select ("+totCurrentQtyCons+" - "+sumQtyPenultimate+") AS 'Sum'", 1);
              quantityConsumed[numRowRequired-1]=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+quantityConsumed[numRowRequired-1]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mSumValue");
              
              consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+rate[numRowRequired-1]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mMultValue");
              
//        Connect.createMSSQLConnection();
//        Connect.changeDB(OpenMSApp.Database_A);
        //First line of algorithm
      for(int row=0;row<=numRowRequired-2;row++)
      {              
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnSecondFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("D-More than one row satisfy consumption qty");
              //System.out.println("D-itemCode="+itemCode+"orderCode="+orderCode[0]+"quantityAvaialble="+quantity[0]);
                statement.setString(1,itemCode);
                statement.setString(2,orderCode[row]);             
                statement.setString(3,quantity[row]);  
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFOD() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
        
      }
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFirstFIFO"+Connect.procInitStart+"?,?,?,?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("D-More than row satisfy consumption qty");
              //System.out.println("D-itemCode="+itemCode+"orderCode="+orderCode[numRowRequired-1]+"quantity="+quantity[numRowRequired-1]+"ratee="+rate[numRowRequired-1]+"consumedCost="+consumedCost+" quantityConsumed= "+quantityConsumed[numRowRequired-1]);
                    statement.setString(1,itemCode);
                    statement.setString(2,orderCode[numRowRequired-1]);             
                    statement.setString(3,quantityConsumed[numRowRequired-1]);       
                    statement.setString(4,quantity[numRowRequired-1]);        
                    statement.setString(5,consumedCost);       
                    statement.setString(6,rate[numRowRequired-1]);    
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFOD() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
        statement=Connect.createStatement(Connect.procInit+"  prnUpdDebitOrderSummaryOnFIFOEnd"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
        try
        {
            statement.setString(1,itemCode);
            statement.setString(2,procOrderCode);
        }
        catch(Exception e)
        {
            System.out.println("PostDebitOrder.implementFIFOD gave error! "+e);
        }
        Connect.executeUpdateStatement(statement);
        Connect.alert="N";
        Connect.closeConnection();
        //Connect.closeMainConnection();
        //Connect.createMSSQLConnection();
          }        
    }
    public void implementFIFOPayable(String itemCode,String qtyInvDebitIn,String orderCode[],String quantity[],String quantityConsumed[],String rate[],String accountCode[],String accounTag,String itemName,String procOrderCode){
    //Code to apply FIFO using total qty consumed by inventory debit
        String totCurrentQtyCons=qtyInvDebitIn;//systems.getValue(OpenMSApp.Database_A, "Select "+qtyInvDebitIn+" + "+qtyRevCreditIn+" AS 'Sum' ", 1);
        if(totCurrentQtyCons.equalsIgnoreCase("0")){System.out.println("D-Aborting FIFO for item:"+itemCode+"[zeero consumption]");return;}
        else{System.out.println("D-total consumption= "+totCurrentQtyCons);}
        
        //Code to implement FIFO if first row satisfy quantity consumed
        String sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select ("+quantity[0]+" - "+quantityConsumed[0]+") AS 'Sum'", 1);
        String qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+totCurrentQtyCons+Connect.procInitEnd, "weightValue");
        //System.out.println("D-Quantity in= "+quantity[0]+" prev quantity consumed="+quantityConsumed[0]+" qtyAvailableMath="+sumDBQtyConsumed);
        String consumedCost;
        String costItemIn;
          if(qtyWeight.equalsIgnoreCase("1 >= 2")){
              //FIRST line of algorithm
              //System.out.println("D-First row satisfy consumption qty");
              consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+rate[0]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mMultValue");
              quantityConsumed[0]=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+quantityConsumed[0]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mSumValue");
              
              //System.out.println("D-CONSUMED COST="+consumedCost+" NEW QTYCONSUMED= "+quantityConsumed[0]);
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFirstFIFO"+Connect.procInitStart+"?,?,?,?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("D-First row satisfy consumption qty");
              //System.out.println("D-itemCode="+itemCode+"orderCode="+orderCode[0]+"quantityAvaialble="+quantity[0]+"ratee="+rate[0]+"consumedCost="+consumedCost+" quantityConsumed= "+quantityConsumed[0]);
                    statement.setString(1,itemCode);
                    statement.setString(2,orderCode[0]);             
                    statement.setString(3,quantityConsumed[0]);       
                    statement.setString(4,quantity[0]);        
                    statement.setString(5,consumedCost);       
                    statement.setString(6,rate[0]);    
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFOD() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
                
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderOnFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
                    statement.setString(1,accountCode[0]);
                    statement.setString(2,accounTag);
                    statement.setString(3,OpenMSApp.LoginCode.trim());
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
                
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderSummaryOnFIFO"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
         try{//ICOC=iTEM CREDIT ORDER CODE
                    statement.setString(1,accountCode[0]);
                    statement.setString(2,"COSTING");
                    statement.setString(3,"Processed Order Code=["+procOrderCode+"] Item Code|Name=["+itemCode+"|"+itemName+"] Quantity consumed=["+totCurrentQtyCons+"] Rate=["+rate[0]+"] "+"ICOC=["+orderCode[0]+"]");
                    statement.setString(4,consumedCost);
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
        
        statement=Connect.createStatement(Connect.procInit+"  prnUpdDebitOrderSummaryOnFIFOPayableEnd"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
        try
        {
            statement.setString(1,itemCode);
            statement.setString(2,procOrderCode);
        }
        catch(Exception e)
        {
            System.out.println("PostDebitOrder.implementFIFOD() gave error! "+e);
        }
        Connect.executeUpdateStatement(statement);
        Connect.alert="N";
        Connect.closeConnection();
                                
              costItemIn="";//(quantity[0]-quantityConsumed[0]) * rate[0]
              //update creditOrderSummary set mAmountItemIn=costItemIn where cOrderCode=orderCode and cItemCode=itemCode
          }
          
          //Code to implement FIFO if more than one row satisfy quantity consumed
          if(qtyWeight.equalsIgnoreCase("1 < 2"))
          {
              //System.out.println("D-more than one row satisfy consumption qty");
                int numRowRequired=0;
        
              //Code to determine num of row satisfy qty consumed
              sumDBQtyConsumed="0";
              qtyWeight="";
              do{
                sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select "+sumDBQtyConsumed+"+("+quantity[numRowRequired]+" - "+quantityConsumed[numRowRequired]+") AS 'Sum'", 1);
                qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+totCurrentQtyCons+Connect.procInitEnd, "weightValue");
                //System.out.println("D-Quantity in= "+quantity[numRowRequired]+" prev quantity consumed="+quantityConsumed[numRowRequired]+" qtyAvailableMath="+sumDBQtyConsumed);
                
                  numRowRequired++;
                  if(qtyWeight.equalsIgnoreCase("1 >= 2")){
                      System.out.println("D-Num of rows satisfing consumption="+numRowRequired);
                      break;
                  }                  
              }
              while(qtyWeight.equalsIgnoreCase("1 < 2"));
              
              //Code to determine qty of item before final row  
              String sumQtyPenultimate="0";
              String[] qtyConsumed=new String[numRowRequired-1];
              String[] consCost=new String[numRowRequired-1];
              for(int count=0;count<=numRowRequired-2;count++)
              {              
                  sumQtyPenultimate=getValue(OpenMSApp.Database_A, "Select "+sumQtyPenultimate+"+("+quantity[count]+" - "+quantityConsumed[count]+") AS 'Sum'", 1);
                  qtyConsumed[count]=qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+quantity[count]+" , "+quantityConsumed[count]+Connect.procInitEnd, "mSumValue");
                  consCost[count]=qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+qtyConsumed[count]+" , "+rate[count]+Connect.procInitEnd, "mMultValue");
                  
                  //System.out.println("D-QtyConsumed= ["+qtyConsumed[count]+"] Rate="+rate[count]+" consCost="+consCost[count]);
                  //System.out.println("D-Sum of item qty till penultimate for ["+count+"] ="+sumQtyPenultimate);
              }              
              //System.out.println("D-Sum of item qty till penultimate="+sumQtyPenultimate);
              totCurrentQtyCons=getValue(OpenMSApp.Database_A, "Select ("+totCurrentQtyCons+" - "+sumQtyPenultimate+") AS 'Sum'", 1);
              quantityConsumed[numRowRequired-1]=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+quantityConsumed[numRowRequired-1]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mSumValue");
              
              consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+rate[numRowRequired-1]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mMultValue");
              
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        //First line of algorithm
      for(int row=0;row<=numRowRequired-2;row++)
      {              
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnSecondFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("D-More than one row satisfy consumption qty");
              //System.out.println("D-itemCode="+itemCode+"orderCode="+orderCode[0]+"quantityAvaialble="+quantity[0]);
                statement.setString(1,itemCode);
                statement.setString(2,orderCode[row]);             
                statement.setString(3,quantity[row]);  
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFOD() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderOnFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
                    statement.setString(1,accountCode[row]);
                    statement.setString(2,accounTag);
                    statement.setString(3,OpenMSApp.LoginCode.trim());
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
                
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderSummaryOnFIFO"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
         try{//ICOC=iTEM ORDER CODE
                    statement.setString(1,accountCode[row]);
                    statement.setString(2,"COSTING");
                    statement.setString(3,"Processed Order Code=["+procOrderCode+"] Item Code|Name=["+itemCode+"|"+itemName+"] IOC=["+orderCode[row]+"] Quantity consumed=["+qtyConsumed[row]+"] Rate=["+rate[row]+"]");
                    statement.setString(4,consCost[row]);
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
        
      }
        statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFirstFIFO"+Connect.procInitStart+"?,?,?,?,?,?"+Connect.procInitEnd);
         try{
              //System.out.println("D-More than row satisfy consumption qty");
              //System.out.println("D-itemCode="+itemCode+"orderCode="+orderCode[numRowRequired-1]+"quantity="+quantity[numRowRequired-1]+"ratee="+rate[numRowRequired-1]+"consumedCost="+consumedCost+" quantityConsumed= "+quantityConsumed[numRowRequired-1]);
                    statement.setString(1,itemCode);
                    statement.setString(2,orderCode[numRowRequired-1]);             
                    statement.setString(3,quantityConsumed[numRowRequired-1]);       
                    statement.setString(4,quantity[numRowRequired-1]);        
                    statement.setString(5,consumedCost);       
                    statement.setString(6,rate[numRowRequired-1]);    
            }
            catch(Exception e)
            {
                System.out.println("NewDebitOrder.implementFIFOD() gave error at LINE ONE ! "+e);
            }
        Connect.executeUpdateStatement(statement);
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderOnFIFO"+Connect.procInitStart+"?,?,?"+Connect.procInitEnd);
         try{
                    statement.setString(1,accountCode[numRowRequired-1]);
                    statement.setString(2,accounTag);
                    statement.setString(3,OpenMSApp.LoginCode.trim());
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
                
        statement=Connect.createStatement(Connect.procInit+" prnInsDebitOrderSummaryOnFIFO"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
         try{//ICOC=iTEM ORDER CODE
                    statement.setString(1,accountCode[numRowRequired-1]);
                    statement.setString(2,"COSTING");
                    statement.setString(3,"Processed Order Code=["+procOrderCode+"] Item Code|Name=["+itemCode+"|"+itemName+"] IOC=["+orderCode[numRowRequired-1]+"] Quantity consumed=["+totCurrentQtyCons+"] Rate=["+rate[numRowRequired-1]+"]");
                    statement.setString(4,consumedCost);
            }
            catch(Exception e)
            {
                    System.out.println("NewDebitOrder.makeOrder() gave error ! at line THREE"+e);
            }
        Connect.executeUpdateStatement(statement);
        
        statement=Connect.createStatement(Connect.procInit+"  prnUpdDebitOrderSummaryOnFIFOPayableEnd"+Connect.procInitStart+"?,?"+Connect.procInitEnd);
        try
        {
            statement.setString(1,itemCode);
            statement.setString(2,procOrderCode);
        }
        catch(Exception e)
        {
            System.out.println("PostDebitOrder.implementFIFOD gave error! "+e);
        }
        Connect.executeUpdateStatement(statement);
        Connect.alert="N";
        Connect.closeConnection();
        //Connect.closeMainConnection();
        //Connect.createMSSQLConnection();
          }        
    }
    void implementCosting(String consOrderCode,String creditDebit)
    {    
        String accounTag=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnPrintLastDebitOrderCode"+Connect.procInitStart+Connect.procInitEnd, "cDebitOrderCode");
        if(accounTag.isEmpty())accounTag="DB0000000";
        accounTag=accounTag.substring(6,9);
        System.out.println("Implementing costing for order: "+consOrderCode+" - "+accounTag);
        String[] itemCode={};
        if(creditDebit.equalsIgnoreCase("Credit"))
        itemCode=getColumn(OpenMSApp.Database_A, "Select CreditOrderSummary.cItemCode from CreditOrderSummary join Item on CreditOrderSummary.cItemCode=item.cItemCode where Item.cType='Stock' and CreditOrderSummary.cCreditOrderCode='"+consOrderCode+"'", 1);
        else if(creditDebit.equalsIgnoreCase("Debit"))
        itemCode=getColumn(OpenMSApp.Database_A, "Select DebitOrderSummary.cItemCode from DebitOrderSummary join Item on DebitOrderSummary.cItemCode=item.cItemCode where Item.cType='Stock' and DebitOrderSummary.cDebitOrderCode='"+consOrderCode+"'", 1);

        int x=itemCode.length;

        for(int y=0;y<x;y++)
        {        
            System.out.println("valid entry at make costing order:"+itemCode[y]);
            implementFIFO(itemCode[y],accounTag,consOrderCode);   
            updateItemStatistics(itemCode[y]);
        }

        String[] orderCode=getColumn(OpenMSApp.Database_A, "Select * from account where cTag='"+accounTag+"'", 11);   
        int len=orderCode.length;

        if(len>0){
        //System.out.println("ordercode one to debit act ="+orderCode[0]);
        //Connect.createMSSQLConnection();
        Connect.changeDB(OpenMSApp.Database_A);
        for(int hold=0;hold<len;hold++)
        {
            statement=Connect.createStatement(Connect.procInit+"  prnDebitAccountOnFIFO"+Connect.procInitStart+"?"+Connect.procInitEnd);
            try
            {
                statement.setString(1,orderCode[hold]);
            }
            catch(Exception e)
            {
                System.out.println("New DebitOrder.makeCostingOrder gave error! "+e);
            }
            Connect.executeUpdateStatement(statement);
        }
        Connect.alert="Y";
        Connect.closeConnection();
        }
    }
    void updateItemStatistics(String itemCode)
    { 
        String irCreditQty=getItemTotalIRCreditQty(itemCode);
        String irDebitQty=getItemTotalIRDebitQty(itemCode); 
        String revenueQty=getItemTotalRevenueQty(itemCode);
        String qtyIReceivableCredit=getItemTotalIReceivableQty(itemCode);  
        String qtyIPDebit=getItemTotalIPDebitQty(itemCode);               
        String quantityAvailable=getValue(OpenMSApp.Database_A, "Select "+irCreditQty+"- ("+irDebitQty+" + "+qtyIPDebit+" + "+revenueQty+" + "+qtyIReceivableCredit+") AS 'Sum'", 1);

        Connect.changeDB(OpenMSApp.Database_A);
        statement=Connect.createStatement(Connect.procInit+"  prnUpdItemStatistics"+Connect.procInitStart+"?,?,?,?,?,?,?,?"+Connect.procInitEnd);
        try
        {
            statement.setString(1,itemCode);
            statement.setString(2,getItemTotalIRCreditCostIn(itemCode));
            statement.setString(3,irCreditQty);
            statement.setString(4,irDebitQty);
            statement.setString(5,qtyIReceivableCredit);
            statement.setString(6,qtyIPDebit);
            statement.setString(7,revenueQty);
            statement.setString(8,quantityAvailable);
        }
        catch(Exception e)
        {
            System.out.println("New DebitOrder.makeCostingOrder gave error! "+e);
        }
        Connect.executeUpdateStatement(statement);
        Connect.alert="N";
        Connect.closeConnection();        
    }
    void implementCostingReverse(String consOrderCode,String creditDebit)
    {    
        String accounTag=getValue(OpenMSApp.Database_A, Connect.procInit+"  prnPrintLastDebitOrderCode"+Connect.procInitStart+Connect.procInitEnd, "cDebitOrderCode");
        if(accounTag.isEmpty())accounTag="DB0000000";
        accounTag=accounTag.substring(6,9);
        //System.out.println(accounTag);
        //check if any costing exits for the order
        String[] orderCode=getColumn(OpenMSApp.Database_A, "Select distinct(cDebitOrderCode) from debitOrderSummary where tDescription like'%Processed Order Code=["+consOrderCode+"%'", 1);   
        int len=orderCode.length;
        if(len==0)
        {
            System.out.println("No costing exists for order:"+consOrderCode);
            return;
        }
        boolean fifoDone=false;
        String[] itemCode={};
        String[] description={};
        String[][] orderData={};
        if(creditDebit.equalsIgnoreCase("Credit"))
        orderData=getColumn(OpenMSApp.Database_A, "Select CreditOrderSummary.cItemCode,CreditOrderSummary.tDescription from CreditOrderSummary join Item on CreditOrderSummary.cItemCode=item.cItemCode where Item.cType='Stock' and CreditOrderSummary.cCreditOrderCode='"+consOrderCode+"'", 1,2);
        else if(creditDebit.equalsIgnoreCase("Debit"))
        orderData=getColumn(OpenMSApp.Database_A, "Select DebitOrderSummary.cItemCode,DebitOrderSummary.tDescription from DebitOrderSummary join Item on DebitOrderSummary.cItemCode=item.cItemCode where Item.cType='Stock' and DebitOrderSummary.cDebitOrderCode='"+consOrderCode+"'", 1,2);
        itemCode=orderData[0];
        description=orderData[1];
        
        int x=itemCode.length;
        String[] quantity=new String[x];
        String outOrderCode[]=new String[x];
        String outDescription[]=new String[x];
        String outOrderData[][]={};
        String outQuantity[]=new String[x];
        String outQuantityConsumed[]=new String[x];
        String outRate[]=new String[x];
        for(int y=0;y<x;y++)
        {        
            System.out.println("valid entry at make costing order4resverse:"+itemCode[y]);
            //implementFIFOReverse(itemCode[y],accounTag,consOrderCode);
            description[y]=description[y].trim();
            quantity[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));      
//Select orders with tag 'out' from inventory credit
            outOrderData=getColumn(OpenMSApp.Database_A,
            "Select creditordersummary.cCreditorderCode,creditOrdersummary.tDescription,creditOrdersummary.iQtyConsumed from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                    +" where creditOrder.cStatus='posted'  AND creditOrderSummary.cItemCode='"+itemCode[y]+"' AND creditOrderSummary.iQtyConsumed >0 "
                    + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",1,2,3);

            outOrderCode=outOrderData[0];
            outDescription=outOrderData[1];
            outQuantityConsumed=outOrderData[2];

            for(int yy=0;yy<outOrderCode.length;yy++)
            {        
                //System.out.println("valid entry at make costing order:"+itemCode[y]);
                //implementFIFOReverse(itemCode[y],accounTag,consOrderCode);
                outDescription[yy]=outDescription[yy].trim();
                outQuantity[yy]=outDescription[yy].substring(outDescription[yy].lastIndexOf("Q=")+2, outDescription[yy].lastIndexOf(",")); 
                outRate[yy]=outDescription[yy].substring(outDescription[yy].lastIndexOf("R=")+2, outDescription[yy].lastIndexOf("]"));           
            }
//complete   
//Find out ordercode with thesame or higher item qty as current reversed ordercode            
            for(int yy=outOrderCode.length;yy>0;yy--)
            {        
                String qtyAvailable=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+outQuantity[yy-1]+" , "+outQuantityConsumed[yy-1]+Connect.procInitEnd, "mSumValue");
                String qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+qtyAvailable+" , "+quantity[y]+Connect.procInitEnd, "weightValue");
                
                if(qtyWeight.equalsIgnoreCase("1 >= 2"))
                { 
                    String qtyConsumed=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+qtyAvailable+" , "+quantity[y]+Connect.procInitEnd, "mSumValue");
                    String amtConsumed=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+quantity[y]+" , "+outRate[yy-1]+Connect.procInitEnd, "mMultValue");
                  
                    Connect.changeDB(OpenMSApp.Database_A);
                    statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFIFOFirstReverse"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
                    try
                    {
                        statement.setString(1,outOrderCode[yy-1]);
                        statement.setString(2,itemCode[y]);
                        statement.setString(3,qtyConsumed);
                        statement.setString(4,amtConsumed);
                    }
                    catch(Exception e)
                    {
                        System.out.println("New DebitOrder.makeCostingOrder gave error! "+e);
                    }
                    Connect.executeUpdateStatement(statement);
                    Connect.alert="N";
                    Connect.closeConnection(); 
                    fifoDone=true;
                    break;
                }
            }
//complete   
//Find multiple out order whose item qty sum equals or greater than current reversed order
        if(!fifoDone&&outOrderCode.length>0)
        {
            int indexUsed[]=new int[outOrderCode.length];
            String sumQty="",sumDBQtyConsumed="0",sumQtyPenultimate="0",qtyWeight="";int numRowRequired=0,xy=outOrderCode.length;
              //Code to determine num of row satisfy qty consumed
              
              do{     
                String qtyAvailable=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+outQuantity[xy-1]+" , "+outQuantityConsumed[xy-1]+Connect.procInitEnd, "mSumValue");
                sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select "+sumDBQtyConsumed+"+"+qtyAvailable+" AS 'Sum'", 1);
                qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+quantity[y]+Connect.procInitEnd, "weightValue");
                //System.out.println("Quantity in= "+quantity[numRowRequired]+" prev quantity consumed="+quantityConsumed[numRowRequired]+" qtyAvailableMath="+sumDBQtyConsumed);
                
                  numRowRequired++;xy--;
                  if(qtyWeight.equalsIgnoreCase("1 >= 2")){
                      System.out.println("Num of rows satisfing consumption="+numRowRequired);
                      break;
                  }                  
              }
              while(qtyWeight.equalsIgnoreCase("1 < 2"));
              
            for(int yy=outOrderCode.length,ny=0;yy>0&&ny<numRowRequired-1;yy--,ny++)
            {     
                String qtyAvailable=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+outQuantity[yy-1]+" , "+outQuantityConsumed[yy-1]+Connect.procInitEnd, "mSumValue");
                
                sumQtyPenultimate=getValue(OpenMSApp.Database_A, "Select "+sumQtyPenultimate+"+"+qtyAvailable+" AS 'Sum'", 1);
                String amtConsumed=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+qtyAvailable+" , "+outRate[yy-1]+Connect.procInitEnd, "mMultValue");

                Connect.changeDB(OpenMSApp.Database_A);
                statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFIFOFirstReverse"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
                try
                {
                    statement.setString(1,outOrderCode[yy-1]);
                    statement.setString(2,itemCode[y]);
                    statement.setString(3,qtyAvailable);
                    statement.setString(4,amtConsumed);
                }
                catch(Exception e)
                {
                    System.out.println("New DebitOrder.makeCostingOrder gave error! "+e);
                }
                Connect.executeUpdateStatement(statement);
                Connect.alert="N";
                Connect.closeConnection();                    
            }
            
                    String qtyConsumed=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+sumQtyPenultimate+" , "+quantity[y]+Connect.procInitEnd, "mSumValue");
                    String amtConsumed=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+qtyConsumed+" , "+outRate[outOrderCode.length-numRowRequired]+Connect.procInitEnd, "mMultValue");
                  
                    Connect.changeDB(OpenMSApp.Database_A);
                    statement=Connect.createStatement(Connect.procInit+"  prnUpdCreditOrderSummaryOnFIFOFirstReverse"+Connect.procInitStart+"?,?,?,?"+Connect.procInitEnd);
                    try
                    {
                        statement.setString(1,outOrderCode[outOrderCode.length-numRowRequired]);
                        statement.setString(2,itemCode[y]);
                        statement.setString(3,qtyConsumed);
                        statement.setString(4,amtConsumed);
                    }
                    catch(Exception e)
                    {
                        System.out.println("New DebitOrder.makeCostingOrder gave error! "+e);
                    }
                    Connect.executeUpdateStatement(statement);
                    Connect.alert="N";
                    Connect.closeConnection();
        }
//Complete
        }
     

        if(len>0)
        {
            System.out.println("ordercode 1 to reverse debit act ="+orderCode[0]);
            //Connect.createMSSQLConnection();
            Connect.changeDB(OpenMSApp.Database_A);
            for(int hold=0;hold<len;hold++)
            {
                statement=Connect.createStatement(Connect.procInit+"  prnDebitAccountOnFIFOReverse"+Connect.procInitStart+"?"+Connect.procInitEnd);
                try
                {
                    statement.setString(1,orderCode[hold]);
                }
                catch(Exception e)
                {
                    System.out.println("New DebitOrder.makeCostingOrder gave error! "+e);
                }
                Connect.executeUpdateStatement(statement);
            }
            Connect.alert="Y";
            Connect.closeConnection();
        }
    }
    
    public String getItemQuantityAvailable(String itemCode){
        
                String irCreditQty=getItemTotalIRCreditQty(itemCode);
                String irDebitQty=getItemTotalIRDebitQty(itemCode); 
                String revenueQty=getItemTotalRevenueQty(itemCode);
                String qtyIReceivableCredit=getItemTotalIReceivableQty(itemCode);  
                String qtyIPDebit=getItemTotalIPDebitQty(itemCode);               
                String quantityAvailable=getValue(OpenMSApp.Database_A, "Select "+irCreditQty+"- ("+irDebitQty+" + "+qtyIPDebit+" + "+revenueQty+" + "+qtyIReceivableCredit+") AS 'Sum'", 1);
                //String quantityAvailable=getValue(OpenMSApp.Database_A, "Select "+irCreditQty+"- ("+irDebitQty+" + "+revenueQty+") AS 'Sum'", 1);
                
                return quantityAvailable;
    }
    
    public String getItemQuantityAvailable(String itemCode,String startDate,String endDate){
        
                String irCreditQty=getItemTotalIRCreditQty(itemCode,startDate,endDate);
                String irDebitQty=getItemTotalIRDebitQty(itemCode,startDate,endDate); 
                String revenueQty=getItemTotalRevenueQty(itemCode,startDate,endDate);
                String qtyIReceivableCredit=getItemTotalIReceivableQty(itemCode,startDate,endDate);  
                String qtyIPDebit=getItemTotalIPDebitQty(itemCode,startDate,endDate);               
                String quantityAvailable=getValue(OpenMSApp.Database_A, "Select "+irCreditQty+"- ("+irDebitQty+" + "+qtyIPDebit+" + "+revenueQty+" + "+qtyIReceivableCredit+") AS 'Sum'", 1);
                //String quantityAvailable=getValue(OpenMSApp.Database_A, "Select "+irCreditQty+"- ("+irDebitQty+" + "+revenueQty+") AS 'Sum'", 1);
                
                return quantityAvailable;
    }
    
    public String[] getOrderDate(String itemCode)
    {
        String orderDate[]={};
        int orderDateLength=0;
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String orderDateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    15);
            orderDateLength+=orderDateMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        orderDate=new String[orderDateLength];
        orderDateLength=0;
        while(yi<xi)
        {
            String orderDateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    15);
            int x=orderDateMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                orderDateMini[y]=orderDateMini[y].trim();
                orderDate[orderDateLength]=orderDateMini[y];
                orderDateLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+orderDate.length);
        return orderDate;
    }

    public String[] getOrderCode(String itemCode)
    {
        String orderDate[]={};
        int orderDateLength=0;
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String orderDateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    1);
            orderDateLength+=orderDateMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        orderDate=new String[orderDateLength];
        orderDateLength=0;
        while(yi<xi)
        {
            String orderDateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    1);
            int x=orderDateMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                orderDateMini[y]=orderDateMini[y].trim();
                orderDate[orderDateLength]=orderDateMini[y];
                orderDateLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+orderDate.length);
        return orderDate;
    }

    public String[] getQuantity(String itemCode)
    {
        String quantity[]={};
        int quantityLength=0;
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String quantityMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            quantityLength+=quantityMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        quantity=new String[quantityLength];
        quantityLength=0;
        while(yi<xi)
        {
            String quantityMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            int x=quantityMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                quantityMini[y]=quantityMini[y].trim();
                quantity[quantityLength]=quantityMini[y].substring(quantityMini[y].lastIndexOf("Q=")+2, quantityMini[y].lastIndexOf(","));
                quantityLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+quantity.length);
        return quantity;
    }

    public String[] getRate(String itemCode)
    {
        String rate[]={};
        int rateLength=0;
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String rateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            rateLength+=rateMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        rate=new String[rateLength];
        rateLength=0;
        while(yi<xi)
        {
            String rateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            int x=rateMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                rateMini[y]=rateMini[y].trim();
                rate[rateLength]=rateMini[y].substring(rateMini[y].lastIndexOf("R=")+2, rateMini[y].lastIndexOf("]"));
                rateLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+rate.length);
        return rate;
    }

    public String[] getTax(String itemCode)
    {
        String tax[]={};
        int taxLength=0;
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String taxMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            taxLength+=taxMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        tax=new String[taxLength];
        taxLength=0;
        while(yi<xi)
        {
            String taxMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            int x=taxMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                taxMini[y]=taxMini[y].trim();
                if(taxMini[y].contains("T="))
                    tax[taxLength]=taxMini[y].substring(taxMini[y].lastIndexOf("T=")+2, taxMini[y].lastIndexOf("Q"));
                else
                    tax[taxLength]="0.00";
                taxLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+tax.length);
        return tax;
    }

    public String[] getAmount(String itemCode)
    {
        String amount[]={};
        int amountLength=0;
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String amountMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    4);
            amountLength+=amountMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        amount=new String[amountLength];
        amountLength=0;
        while(yi<xi)
        {
            String amountMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    4);
            int x=amountMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                amountMini[y]=amountMini[y].trim();
                amount[amountLength]=amountMini[y];
                amountLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+amount.length);
        return amount;
    }

    public String[] getQuantityConsumed(String itemCode)
    {
        String qtyConsumed[]={};
        int qtyConsumedLength=0;
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String amountMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    7);
            qtyConsumedLength+=amountMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        qtyConsumed=new String[qtyConsumedLength];
        qtyConsumedLength=0;
        while(yi<xi)
        {
            String qtyConsumedtMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    7);
            int x=qtyConsumedtMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                qtyConsumedtMini[y]=qtyConsumedtMini[y].trim();
                qtyConsumed[qtyConsumedLength]=qtyConsumedtMini[y];
                qtyConsumedLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+qtyConsumed.length);
        return qtyConsumed;
    }
    public String[] getTag(String itemCode)
    {
        String qtyConsumed[]={};
        int qtyConsumedLength=0;
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String amountMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    6);
            qtyConsumedLength+=amountMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        qtyConsumed=new String[qtyConsumedLength];
        qtyConsumedLength=0;
        while(yi<xi)
        {
            String qtyConsumedtMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    6);
            int x=qtyConsumedtMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                qtyConsumedtMini[y]=qtyConsumedtMini[y].trim();
                qtyConsumed[qtyConsumedLength]=qtyConsumedtMini[y];
                qtyConsumedLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+qtyConsumed.length);
        return qtyConsumed;
    }

    public String[] getOrderDate(String itemCode,String accountCode)
    {
        String orderDate[]={};
        int orderDateLength=0;
        String irAccount[]={accountCode};//getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String orderDateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    15);
            orderDateLength+=orderDateMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        orderDate=new String[orderDateLength];
        orderDateLength=0;
        while(yi<xi)
        {
            String orderDateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    15);
            int x=orderDateMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                orderDateMini[y]=orderDateMini[y].trim();
                orderDate[orderDateLength]=orderDateMini[y];
                orderDateLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+orderDate.length);
        return orderDate;
    }

    public String[] getQuantity(String itemCode,String accountCode)
    {
        String quantity[]={};
        int quantityLength=0;
        String irAccount[]={accountCode};//getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String quantityMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            quantityLength+=quantityMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        quantity=new String[quantityLength];
        quantityLength=0;
        while(yi<xi)
        {
            String quantityMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            int x=quantityMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                quantityMini[y]=quantityMini[y].trim();
                quantity[quantityLength]=quantityMini[y].substring(quantityMini[y].lastIndexOf("Q=")+2, quantityMini[y].lastIndexOf(","));
                quantityLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+quantity.length);
        return quantity;
    }

    public String[] getRate(String itemCode,String accountCode)
    {
        String rate[]={};
        int rateLength=0;
        String irAccount[]={accountCode};//getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String rateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            rateLength+=rateMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        rate=new String[rateLength];
        rateLength=0;
        while(yi<xi)
        {
            String rateMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    3);
            int x=rateMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                rateMini[y]=rateMini[y].trim();
                rate[rateLength]=rateMini[y].substring(rateMini[y].lastIndexOf("R=")+2, rateMini[y].lastIndexOf("]"));
                rateLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+rate.length);
        return rate;
    }

    public String[] getAmount(String itemCode,String accountCode)
    {
        String amount[]={};
        int amountLength=0;
        String irAccount[]={accountCode};//getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String amountMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    4);
            amountLength+=amountMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        amount=new String[amountLength];
        amountLength=0;
        while(yi<xi)
        {
            String amountMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    4);
            int x=amountMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                amountMini[y]=amountMini[y].trim();
                amount[amountLength]=amountMini[y];
                amountLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+amount.length);
        return amount;
    }

    public String[] getQuantityConsumed(String itemCode,String accountCode)
    {
        String qtyConsumed[]={};
        int qtyConsumedLength=0;
        String irAccount[]={accountCode};//getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String amountMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    7);
            qtyConsumedLength+=amountMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        qtyConsumed=new String[qtyConsumedLength];
        qtyConsumedLength=0;
        while(yi<xi)
        {
            String qtyConsumedtMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    7);
            int x=qtyConsumedtMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                qtyConsumedtMini[y]=qtyConsumedtMini[y].trim();
                qtyConsumed[qtyConsumedLength]=qtyConsumedtMini[y];
                qtyConsumedLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+qtyConsumed.length);
        return qtyConsumed;
    }
    public String[] getTag(String itemCode,String accountCode)
    {
        String qtyConsumed[]={};
        int qtyConsumedLength=0;
        String irAccount[]={accountCode};//getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        int xi=irAccount.length;
        int yi=0;
        //System.out.println("no of inventory account="+xi);
        while(yi<xi)
        {
            String amountMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    6);
            qtyConsumedLength+=amountMini.length;
            yi++;
            if(xi==yi){break;}
        }
        yi=0;
        qtyConsumed=new String[qtyConsumedLength];
        qtyConsumedLength=0;
        while(yi<xi)
        {
            String qtyConsumedtMini[]=getColumn(OpenMSApp.Database_A,
           "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode where creditOrderSummary.cItemCode='"+
            itemCode+"' AND creditOrder.cStatus='posted' AND creditOrder.cAccountCode='"+irAccount[yi]+"'",
                    6);
            int x=qtyConsumedtMini.length;
            int y=0;
            //System.out.println("no of description credit="+x+" for account "+irAccount[yi]);
            while(y<x)
            {
                qtyConsumedtMini[y]=qtyConsumedtMini[y].trim();
                qtyConsumed[qtyConsumedLength]=qtyConsumedtMini[y];
                qtyConsumedLength++;
                y++;
                if(x==y){break;}
            }
            yi++;
            if(xi==yi){break;}
        }
            //System.out.println("length of order code retrieved= "+qtyConsumed.length);
        return qtyConsumed;
    }

    public String getConsumedCost(String itemCode,String totCurrentQtyCons){        
        
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        //Code to check if an inventory account exists
        if(irAccount.length==0){System.out.println("Abortin FIFO Inventory account does not exist");}
        
        //Code to determine parameters @parameter of item @item with IN tag with and inventory account
        String[] orderCode,accountCode,description,quantity,rate,quantityConsumed;
        
        orderCode=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                +" where creditOrder.cStatus='posted'  AND creditOrderSummary.cItemCode='"+itemCode+"' AND creditOrderSummary.cTag ='IN' "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",1);
        accountCode=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                + " where creditOrder.cStatus='posted'   AND creditOrderSummary.cItemCode='"+itemCode+"' AND creditOrderSummary.cTag ='IN' "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",11);
        description=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                + " where creditOrder.cStatus='posted'  AND creditOrderSummary.cItemCode='"+itemCode+"'  AND creditOrderSummary.cTag ='IN' "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",3);
        
            int x=description.length;
            int y=0;
            quantity=new String[x];
            rate=new String[x];
            String consumedCost="";
            if(x==0){System.out.println("Aborting FIFO for item:"+itemCode+" [zero FIFO record]");return consumedCost;}
            //System.out.println("length of description is "+x);
            
            while(y<x)
            {
                description[y]=description[y].trim();
                quantity[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                rate[y]=description[y].substring(description[y].lastIndexOf("R=")+2, description[y].lastIndexOf("]"));
                
                y++;
                if(x==y){break;}
            }
            
       quantityConsumed=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                + " where creditOrder.cStatus='posted' AND creditOrderSummary.cItemCode='"+itemCode+"' AND creditOrderSummary.cTag ='IN' "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",7);
        //System.out.println("No of row entries in= "+orderCode.length);
        //System.out.println(orderCode[0]+" "+accountCode[0]+" "+description[0]+" "+quantity[0]+" "+rate[0]+" "+quantityConsumed[0]);
                
        //Code to apply FIFO using total qty consumed supplied by user
        if(totCurrentQtyCons.equalsIgnoreCase("0")){System.out.println("Aborting QuantityConsumedCost calc for item:"+itemCode+"[zeero consumption]");return consumedCost;}
        else{System.out.println("total consumption= "+totCurrentQtyCons);}
        
        //Code to implement FIFO if first row satisfy quantity consumed
        String sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select ("+quantity[0]+" - "+quantityConsumed[0]+") AS 'Sum'", 1);
        String qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+totCurrentQtyCons+Connect.procInitEnd, "weightValue");
        //System.out.println("Quantity in= "+quantity[0]+" prev quantity consumed="+quantityConsumed[0]+" qtyAvailableMath="+sumDBQtyConsumed);
        
          if(qtyWeight.equalsIgnoreCase("1 >= 2"))
          {
              //FIRST line of algorithm
              //System.out.println("First row satisfy consumption qty");
              consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+rate[0]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mMultValue");
              quantityConsumed[0]=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+quantityConsumed[0]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mSumValue");
              
              //System.out.println("CONSUMED COST="+consumedCost+" NEW QTYCONSUMED= "+quantityConsumed[0]);
          }
          
          //Code to implement FIFO if more than one row satisfy quantity consumed
          if(qtyWeight.equalsIgnoreCase("1 < 2"))
          {
              //System.out.println("more than one row satisfy consumption qty");
                int numRowRequired=0;
        
              //Code to determine num of row satisfy qty consumed
              sumDBQtyConsumed="0";
              qtyWeight="";
              do{
                sumDBQtyConsumed=getValue(OpenMSApp.Database_A, "Select "+sumDBQtyConsumed+"+("+quantity[numRowRequired]+" - "+quantityConsumed[numRowRequired]+") AS 'Sum'", 1);
                qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnWeighMoney"+Connect.procInitStart+""+sumDBQtyConsumed+" , "+totCurrentQtyCons+Connect.procInitEnd, "weightValue");
                //System.out.println("Quantity in= "+quantity[numRowRequired]+" prev quantity consumed="+quantityConsumed[numRowRequired]+" qtyAvailableMath="+sumDBQtyConsumed);
                
                  numRowRequired++;
                  if(qtyWeight.equalsIgnoreCase("1 >= 2")){
                      System.out.println("Num of rows satisfing consumption="+numRowRequired);
                      break;
                  }                  
              }
              while(qtyWeight.equalsIgnoreCase("1 < 2"));
              
              //Code to determine qty of item before final row  
              String sumQtyPenultimate="0";
              String[] qtyConsumed=new String[numRowRequired-1];
              String[] consCost=new String[numRowRequired-1];
              for(int count=0;count<=numRowRequired-2;count++)
              {              
                  sumQtyPenultimate=getValue(OpenMSApp.Database_A, "Select "+sumQtyPenultimate+"+("+quantity[count]+" - "+quantityConsumed[count]+") AS 'Sum'", 1);
                  qtyConsumed[count]=qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnSubtractMoney"+Connect.procInitStart+""+quantity[count]+" , "+quantityConsumed[count]+Connect.procInitEnd, "mSumValue");
                  consCost[count]=qtyWeight=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+qtyConsumed[count]+" , "+rate[count]+Connect.procInitEnd, "mMultValue");
                  
                  //System.out.println("QtyConsumed= ["+qtyConsumed[count]+"] Rate="+rate[count]+" consCost="+consCost[count]);
                  //System.out.println("Sum of item qty till penultimate for ["+count+"] ="+sumQtyPenultimate);
              }              
              //System.out.println("Sum of item qty till penultimate="+sumQtyPenultimate);
              totCurrentQtyCons=getValue(OpenMSApp.Database_A, "Select ("+totCurrentQtyCons+" - "+sumQtyPenultimate+") AS 'Sum'", 1);
              quantityConsumed[numRowRequired-1]=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+quantityConsumed[numRowRequired-1]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mSumValue");
              
              consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnMultMoney"+Connect.procInitStart+""+rate[numRowRequired-1]+" , "+totCurrentQtyCons+Connect.procInitEnd, "mMultValue");
              String consCostSum="";
          
        //First line of algorithm
              for(int row=0;row<=numRowRequired-2;row++)
              {
                    consCostSum=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+consCostSum+" , "+consCost[row]+Connect.procInitEnd, "mSumValue");              
                    
              }
                    consumedCost=getValue(OpenMSApp.Database_A, Connect.procInit+" prnAddMoney"+Connect.procInitStart+""+consCostSum+" , "+consumedCost+Connect.procInitEnd, "mSumValue");              
                    
          }
          return consumedCost;
    }
    String setAccountingFormat(String value){
        if(value.contains("-"))
            value=value.replace('-', '(')+")";
        return value;
    }
    String encryptFile(String plainTextFile,String cypherTextFile)
    {
        String cypherText="";
        try{
            File file=new File(plainTextFile);
            FileReader fd=new FileReader(file);
            int io=(int)file.length();
            char[] cbkuf=new char[io];
            int i=fd.read(cbkuf);
            //String plainText=String.valueOf(cbkuf);
            //cypherText=encryptText(plainText);
            FileOutputStream fs = new FileOutputStream(cypherTextFile, true);
            ObjectOutputStream oos = new ObjectOutputStream(fs);

            Encoder code = new Encoder(String.valueOf(cbkuf));
            oos.writeObject(code);//serialize

            oos.close();
        }
        catch(IOException ex){System.out.println("Serialization ERROR:"+ex);}
        catch(Exception e){System.out.println(e);}
        System.out.println("Serialization Finish\n\n");
        return cypherText;
    }
    String decryptFile(int x1,int x2,int k1,int k2,String encryptedTextFile)
    {
        String plainText="";
//        try{
//            File file=new File(encryptedTextFile);
//            FileReader fd=new FileReader(file);
//            int io=(int)file.length();
//            char[] cbkuf=new char[io];
//            int i=fd.read(cbkuf);
//            String cypherText=String.valueOf(cbkuf);
//            plainText=decryptText(4,4,2,2,cypherText);
//        }
//        catch(Exception e){System.out.println(e);}
        //De-Serialization
        try{
                FileInputStream fs =new FileInputStream(encryptedTextFile);
                ObjectInputStream ois = new ObjectInputStream(fs);

                Encoder acc1 = (Encoder)ois.readObject();
                plainText=decryptText(x1,x2,k1,k2,acc1.cypherText);
                ois.close();
        }
        catch(Exception ex){System.out.println("DE-Serialization ERROR:"+ex);}
        return plainText;
    }
    String encryptText(String plainText)
    {
        char ca[]=plainText.toCharArray(),p;
        double breedValue[]=new double[ca.length];int x=0;
        for(char c:ca)
        {
            breedValue[x]=breedElement(4,4,2,2,(int)c);            
        }
        String cypherText="";
        return cypherText;
    }
    String decryptText(int x1,int x2,int k1,int k2,double cypher[][])
    {	
            String plain="",plainL[]=new String[cypher.length],plHolder="";int x=0;
            System.out.println("Decryption Level 1");dtLevel="Processing State 1/4";
            for(double a[]:cypher)
            {	
                plHolder="";
                for(double b:a)
                {
                    int indexNow=getBreedIndex(x1+2,x2+2,k1+2,k2+2,Double.valueOf(b));
                    plHolder=plHolder+(char)indexNow;
                }
                plainL[x]=plHolder;
                x++;
            }
            x=0;
            double d[]=new double[plainL.length];
            System.out.println("Decryption Level 2");dtLevel="Processing State 2/4";
            for(String a:plainL)
            {
                int m=1;
                if(a.contains("@"))
                {
                    String hold[]=a.split("@");
                    int y=hold.length;
                    a=hold[0];
                    String s=String.valueOf(getBreedIndex(x1+1,x2+1,k1+1,k2+1,Double.valueOf(hold[1])));
                    m=Integer.valueOf(s);
                }
                d[x++]=Double.valueOf(a)/m;
            }
            System.out.println("Decryption Level 3");dtLevel="Processing State 3/4";
            for(double b:d)
                    plain=plain+(char)getBreedIndex(x1,x2,k1,k2,b);
            return plain;
    }
    double breedElement(int x1,int x2, int k1, int k2, int index){
        double element=Math.pow(k1+index,x1)-(k2+Math.pow(index, x2));        
        return element;    
    }
    double[] breedLimit(int x1,int x2, int k1, int k2, int initIndex, int limit){
        double element[]=new double[limit];
        for(;initIndex<=limit;initIndex++)
        element[initIndex]=Math.pow(k1+initIndex,x1)-(k2+Math.pow(initIndex, x2));
        String s="";
        Byte e=new Byte(s);
        
        return element;    
    }
    int getBreedIndex(int x1,int x2,int k1,int k2, double element)
    {
            int index=0;
            for(int found=2;found!=0;index++)		
            {	
                double elementN=Math.pow(k1+index,x1)-(k2+Math.pow(index, x2));
                found=Double.compare(element,elementN);
            }
            return index-1;
    }
    String createTableQuery(String tableName,String prefix,String attribute[],String type[],String stringLength[],String refTable[],String optional[],String separator[],String multiRow)
    {
        String primary="";
        if(multiRow.equalsIgnoreCase("0"))primary="PRIMARY KEY";
        String finalQuery="",createTableBody="c"+tableName.trim() +"code char(9) not null "+primary+",cLoginCode char(9) not null,dEntryTime datetime not null, ",referenceSyntax="";
        for(int x=0;x<attribute.length;x++)
        {
            if(type[x].trim().equalsIgnoreCase("Date/time"))type[x]="Datetime";
            if(type[x].trim().equalsIgnoreCase("String"))type[x]=" varchar("+stringLength[x].trim()+") ";
            if(type[x].trim().equalsIgnoreCase("Reference"))
            {
                type[x]=" char(9) ";
                String refTablePrimaryKey=getValue(OpenMSApp.Database_A,"Select * from referenceTable where vName='"+refTable[x].trim()+"'",4);
                referenceSyntax=" REFERENCES "+refTable[x].trim().replace(' ', '_') +"("+refTablePrimaryKey.trim()+")" ;
            }
            if(separator[x].trim().equalsIgnoreCase("ON"))separator[x]=",";else separator[x]="";
            if(optional[x].trim().equalsIgnoreCase("0"))optional[x]=" not null ";else optional[x]="";
            createTableBody=createTableBody+attribute[x].trim().replace(' ', '_')+" "+type[x].trim()+" "+optional[x].trim()+referenceSyntax+separator[x].trim();
        }
        String createTableHeading="CREATE TABLE "+tableName+"(";
        String createTableFooter=",iSerial int not null);";
        String generateIDSyntax=" DROP PROCEDURE IF EXISTS `"+OpenMSApp.Database_A+"`.`prnGen"+tableName+"Code`; "
                +" CREATE PROCEDURE `"+OpenMSApp.Database_A+"`.`prnGen"+tableName+"Code`(OUT "+tableName+"Code char(9))"
                +" BEGIN "+"DECLARE COUNTER INT; "+" SELECT count(c"+tableName+"Code) into COUNTER FROM "+tableName+"; "+" SET COUNTER=COUNTER+1; "
                +" CASE "+" WHEN COUNTER>=0 and COUNTER<=9 THEN "+"SET "+tableName+"Code=CONCAT('"+prefix+"000000',CAST(COUNTER AS CHAR(9))); "
                +" WHEN COUNTER>9 and COUNTER<=99 THEN SET "+tableName+"Code=CONCAT('"+prefix+"00000',CAST(COUNTER AS CHAR(9))); "
                +" WHEN COUNTER>99 and COUNTER<=999 THEN SET "+tableName+"Code=CONCAT('"+prefix+"0000',CAST(COUNTER AS CHAR(9))); "
                +" WHEN COUNTER>999 and COUNTER<=9999 THEN SET "+tableName+"Code=CONCAT('"+prefix+"000',CAST(COUNTER AS CHAR(9))); "
                +" WHEN COUNTER>9999 and COUNTER<=99999 THEN SET "+tableName+"Code=CONCAT('"+prefix+"00',CAST(COUNTER AS CHAR(9))); "
                +" WHEN COUNTER>99999 and COUNTER<=999999 THEN SET "+tableName+"Code=CONCAT('"+prefix+"0',CAST(COUNTER AS CHAR(9))); "
                +" WHEN COUNTER>999999 THEN SET "+tableName+"Code=CONCAT('"+prefix+"',CAST(COUNTER AS CHAR(9))); "
                +" END CASE; END;";
        String printIDSyntax=" DROP PROCEDURE IF EXISTS `"+OpenMSApp.Database_A+"`.`prnPrint"+tableName+"Code`; "
                +" CREATE PROCEDURE `"+OpenMSApp.Database_A+"`.`prnPrint"+tableName+"Code`()"
                +" BEGIN "+" Call prnGen"+tableName+"Code(@s); "
                +" SELECT @S AS 'cCode'; END; ";
        finalQuery=createTableHeading+createTableBody+createTableFooter+generateIDSyntax+printIDSyntax;
        return finalQuery;
    }
    String generatePeriod(int quantity)
    {
        String period="";
        for(int x=0;x<quantity;x++)
        {
            period=period+"?";
            if(x<quantity-1)period=period+",";
        }
        return period;
    }
    boolean[] generateBooleanArrayFalse(int quantity)
    {
        boolean[] falseBoolean=new boolean[quantity];
        for(int x=0;x<quantity;x++)
            falseBoolean[x]=false;
        return falseBoolean;
    }
    boolean[] generateBooleanArrayTrue(int quantity)
    {
        boolean[] trueBoolean=new boolean[quantity];
        for(int x=0;x<quantity;x++)
            trueBoolean[x]=true;
        return trueBoolean;
    }
    boolean[] convertBinaryStringArrayToBooleanArray(String binaryString[])
    {
        boolean[] booleanArray=new boolean[binaryString.length];
        for(int x=0;x<binaryString.length;x++)
        {
            if(binaryString[x].equalsIgnoreCase("1"))
                booleanArray[x]=true;
            else
                booleanArray[x]=false;
        }
        return booleanArray;
    }
    boolean[] convertBinaryStringArrayToBooleanArrayFLead(String binaryString[])
    {
        boolean[] booleanArray=new boolean[binaryString.length+1];
        booleanArray[0]=false;
        for(int x=0;x<binaryString.length;x++)
        {
            System.out.println("BS"+x+"="+binaryString[x]);
            if(binaryString[x].equalsIgnoreCase("1"))
                booleanArray[x+1]=true;
            else
                booleanArray[x+1]=false;
        }
        return booleanArray;
    }
    String generateQueryPara(String searchPara, String attribute[])
    {
        String query="";
        int numOfAttribute=attribute.length;
        for(int x=0;x<numOfAttribute;x++){
        query=query+" ("+attribute[x].replaceAll(" ", "_") +" like '%"+searchPara+"%') ";
        if(x<numOfAttribute-1)query=query+" or ";
        }        
        return query;
    }
    void executeSplitedQuery(String DB_Query[],PreparedStatement statement)
    {
        int y=DB_Query.length;
        int procBeginFirst=0;
        int procBeginContinue=1;
        int procEnd=2;
        int notProc=3;
        int queryType=notProc;
        //Connect.createMSSQLConnection();
        statement=Connect.createStatement("set global sql_mode='ANSI'"); 
        Connect.alert="N";
        Connect.executeUpdateStatement(statement);
        //Connect.closeConnection();
        
        //Connect.createMSSQLConnection();
        Systems.dtLevel="Processing State 4/4";
        for(int x=0;x<y;x++)
        {
            if(DB_Query[x].trim().contains("BEGIN")){
                queryType=procBeginFirst;
            }
            if(DB_Query[x].trim().equals("END")){
                queryType=procEnd;
            }
            
            System.out.println("Query "+x+" : Type:"+queryType+" "+DB_Query[x]);
            if(DB_Query[x].trim().isEmpty())
            {
            }else if(queryType==procBeginContinue){
                DB_Query[x]=DB_Query[x-1]+" "+DB_Query[x]+";";
            System.out.println("Query "+x+" > "+DB_Query[x]);
            }else if(queryType==procBeginFirst){
                DB_Query[x]=DB_Query[x]+";";
            System.out.println("Query "+x+" > "+DB_Query[x]);
            }else if(queryType==procEnd){
                DB_Query[x]=DB_Query[x-1]+" "+DB_Query[x]+";";
            System.out.println("Query "+x+" > "+DB_Query[x]);
                statement=Connect.createStatement(DB_Query[x]); 
                Connect.executeUpdateStatement(statement);
                queryType=notProc;
            }else if(queryType==notProc){
                statement=Connect.createStatement(DB_Query[x]); 
                Connect.executeUpdateStatement(statement);
            }
            
            if(queryType==procBeginFirst)queryType=procBeginContinue;
        }
        Connect.alert="Y";
        Connect.closeConnection();
        System.out.println("Query executed successfully.");
    }
    boolean isStringSpacedAlphaNumeric(String value)
    {
        boolean isSpacedAlphaNumeric=true;
        char[] characters=value.toCharArray();
        for(int x=0;x<characters.length;x++)
        {
            if(!Character.isLetterOrDigit(characters[x])&&!Character.isSpaceChar(characters[x]))isSpacedAlphaNumeric=false;
        }
        return isSpacedAlphaNumeric;
    }
    boolean isStringLetter(String value)
    {
        boolean isLetter=true;
        char[] characters=value.toCharArray();
        for(int x=0;x<characters.length;x++)
        {
            if(!Character.isLetter(characters[x]))isLetter=false;
        }
        return isLetter;
    }
    String[] stringArrayToTitleCase(String stringArray[])
    {
        int x=0;
        for(String s:stringArray)
        {
            stringArray[x++]=toTitleCase(s);
        }
        return stringArray;
    }
    String[] toColumnHeaderWithLead(String stringArray[])
    {
        int x=1;
        String columnHeaderWithLead[]=new String[stringArray.length+1];
        columnHeaderWithLead[0]="S/N";
        for(String stg:stringArray)
        {
            columnHeaderWithLead[x++]=stg;
        }
        return columnHeaderWithLead;
    }
    Object[][] generateNullObject(int numOfNull)
    {
        Object object[][]=new Object[][]{};
        Object nullObject[]=new Object[numOfNull];
        
        for(int x=0;x<numOfNull;x++)
            nullObject[x]=null;
        object[0]=nullObject;
        return object;
    }
    String[] mergeArray(String[] array1, String[] array2)
    {
        int x=0;
        String[] mergedArray=new String[array1.length+array2.length];
        for(String s:array1)
            mergedArray[x++]=s;
        x--;
        for(String s:array2)
            mergedArray[++x]=s;
        return mergedArray;
    }
    String overrideNullString(String nullibleValue)
    {
        if(nullibleValue==null)nullibleValue="N/A";
        return nullibleValue;        
    }
    String overrideNullDate(java.sql.Date nullibleValue)
    {
        String returnValue=this.extractSQLDate(nullibleValue);
        if(nullibleValue==null)returnValue="N/A";
        return returnValue;        
    }
    String getDefaultMerchantQuery()
    {
        String query="DROP VIEW IF EXISTS `"+OpenMSApp.Database_A+"`.`vwMerchant`;"
                +" CREATE VIEW `"+OpenMSApp.Database_A+"`.`vwMerchant`"
                +" AS SELECT cVendorID AS \"cMerchantID\",vName,mAccountBalance,'Vendor' AS \"vTable\" From Vendor"
                +" union SELECT cCustomerID,vName,mAccountBalance,'Customer' From Customer"
                +" union SELECT cDesignationCode,vDesignationName,mAccountBalance,'Designation' From Designation"
                +" union SELECT cDepartmentCode,vDepartmentName,0.00 AS mAccountBalance,'Department' From Department"
                +" union SELECT cEmployeeID,CONCAT(vFirstName,' ',vMiddleName,' ',vLastName) AS vName,mAccountBalance,'Employee' From Employee"
                +" union SELECT cStudentID,CONCAT(vFirstName,' ',vMiddleName,' ',vLastName) AS vName,0.00 AS mAccountBalance,'Student' From Student";
        return query;
    }
    String generateMerchantQuery(String tableName,String vNameList[],int updateType)
    {        
        String lastMerchantQuery=getValue(OpenMSApp.Database_A, "Select * from vwMerchantSource", 1);
        
        if(updateType==2)lastMerchantQuery=getDefaultMerchantQuery();
        else if(updateType==0)
        {
            if(lastMerchantQuery==null)lastMerchantQuery=getDefaultMerchantQuery();
            else if(lastMerchantQuery.isEmpty())lastMerchantQuery=getDefaultMerchantQuery();
            if(vNameList.length==1)
            lastMerchantQuery=lastMerchantQuery+" /**"+tableName+"**/ union SELECT c"+tableName+"Code,"+vNameList[0]+",0.00 AS mAccountBalance,'"+tableName+"' From "+tableName+" /**"+tableName+"**/ ";
            else
            {
                String name="CONCAT(";
                for(String s:vNameList)name=name+s+",' ',";
                name=name.substring(0,name.lastIndexOf(",")-1)+") AS vName";
                lastMerchantQuery=lastMerchantQuery+" /**"+tableName+"**/ union SELECT c"+tableName+"Code,"+name+",0.00 AS mAccountBalance,'"+tableName+"' From "+tableName+" /**"+tableName+"**/ ";
            }
        }  
        else if(updateType==1)
            lastMerchantQuery=lastMerchantQuery.replaceAll(lastMerchantQuery.substring(lastMerchantQuery.indexOf(" /**"+tableName+"**/"), lastMerchantQuery.lastIndexOf("/**"+tableName+"**/")+tableName.length()+6), " ");
        System.out.println("Merch Query:"+lastMerchantQuery);
        return lastMerchantQuery;
    }
    void freeHeap()
    {
        if(heapCounter>50)
        {
            freeingHeap=true;
            System.out.println("Attempting to free more heap... ");
            Connect.closeMainConnection();
            Connect.clearAll();
            Connect.createMSSQLConnection();
            System.gc();
            Runtime.getRuntime().gc();
            System.out.println("Attempting to free more heap...finish");
            heapCounter=0;
            freeingHeap=false;
        }
        heapCounter++;
    }
    String[] wrapString(String value, int lineLength)
    {
        char valueChar[]=value.toCharArray();
        String[] wrapedString=new String[(int)Math.ceil((double)valueChar.length/(double)lineLength)];
        int numOfLine=0,numOfChar=0;
        for(char c:valueChar)
        {
            if(wrapedString[numOfLine]==null)wrapedString[numOfLine]="";
            wrapedString[numOfLine]=wrapedString[numOfLine]+String.valueOf(c);
            numOfChar++;
            
            if(numOfChar==lineLength)
            {
                numOfChar=0;
                numOfLine++;
            }
        }
        return wrapedString;
    }
    public String getItemLastCreditRate(String itemCode){        
        String lastRate="";
        String irAccount[]=getColumn(OpenMSApp.Database_A, "Select * from Account where cCategory='INVENTORY'", 1);
        //Code to check if an inventory account exists
        if(irAccount.length==0){System.out.println("Abortin FIFO Inventory account does not exist");}
        
        //Code to determine parameters @parameter of item @item with IN tag with and inventory account
        String[] description,rate;
       
        description=getColumn(OpenMSApp.Database_A,
        "Select * from creditordersummary join creditOrder on creditOrderSummary.cCreditOrderCode=creditOrder.cCreditOrderCode "
                + " where creditOrder.cStatus='posted'  AND creditOrderSummary.cItemCode='"+itemCode+"'  "
                + "AND creditOrder.cAccountCode IN (Select cAccountCode From Account Where cCategory='INVENTORY')",3);
        
            int x=description.length;
            int y=0;
            //quantity=new String[x];
            rate=new String[x];
            String consumedCost="";
            if(x==0){System.out.println("Aborting FIFO for item:"+itemCode+" [zero FIFO record]");return consumedCost;}
            //System.out.println("length of description is "+x);
            
            while(y<x)
            {
                description[y]=description[y].trim();
                //quantity[y]=description[y].substring(description[y].lastIndexOf("Q=")+2, description[y].lastIndexOf(","));
                rate[y]=description[y].substring(description[y].lastIndexOf("R=")+2, description[y].lastIndexOf("]"));
                
                y++;
                if(x==y){break;}
            }
    return lastRate=rate[x-1];
    }
}
