
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
public class NoteWriterProfile extends NoteWriter{
    
    void writeProfile(String profileName,String query,String tbAttribute[],String dbMemberRecord[][],String multiRow,String profileCode)
    {
        
        try
        {
            file=new java.io.File(Configuration.profileNotePath);
            if(file.exists())
            {
                file.delete();
            }
            writer=new java.io.FileWriter(file, true);
            writer.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n");
            writer.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\n");
            writer.write("<head>\n");
            writer.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n");
            writer.write("<title>Profile</title>\n");

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
        writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.profileHeaderImageURLPath+"\" width=\"595\" height=\"160\" alt=\""+profileName+" Profile\" /></div></td>\n");
        writer.write("  </tr>\n");
        writer.write("</table>\n");
        if(profileName.equalsIgnoreCase("Employee"))
        {
            String[][] dbEmployee=systems.getTableDataArrayNum(OpenMSApp.Database_A, query,1,2,4,3,17,18,15,16,19,23,24);
            java.sql.Date[][] dbEmployeeDate=systems.getTableDateArrayNum(OpenMSApp.Database_A, query,13,21,22);
            String[] dbEmployeeID=dbEmployee[0];
            String[] dbFirstName=dbEmployee[1];
            String[] dbLastName=dbEmployee[2];
            String[] dbMiddleName=dbEmployee[3];
            String[] dbBankCode=dbEmployee[4];
            String[] dbAccount=dbEmployee[5];
            String[] dbDepartment=dbEmployee[6];
            String[] dbDesignation=dbEmployee[7];
            String[] dbBalance=dbEmployee[8];
            String[] dbOvertimeStatus=dbEmployee[9];
            String[] dbSalaryStatus=dbEmployee[10];
            java.sql.Date[] dbDOB=dbEmployeeDate[0];
            java.sql.Date[] dbAppointmentDate=dbEmployeeDate[1];
            java.sql.Date[] dbResignationDate=dbEmployeeDate[2];
            
            int x=dbEmployeeID.length,y=0;
            while(y<x)
            {
                String demacate="Y";
                demacate=changeDemacate(demacate);
                
                String[] dbEmp=systems.getValue(OpenMSApp.Database_A, "Select * from employee where cEmployeeID='"+dbEmployeeID[y]+"'", "vAddress","cCity", "cState", "cCountryCode", "cPhone", "cSex", "cTitle", "cStatus", "cZip");
                String empAddress=dbEmp[0];
                String empCity=dbEmp[1];
                String empState=dbEmp[2];
                String empCountryCode=dbEmp[3];
                String empPhone=dbEmp[4];
                String empSex=dbEmp[5];
                String empTitle=dbEmp[6];
                String empStatus=dbEmp[7];
                String empZip=dbEmp[8]; 
                String empCountry=systems.getValue(OpenMSApp.Database_A, "Select * from Country where cCountryCode='"+empCountryCode+"'", "vCountryName");
                if(empSex.equalsIgnoreCase("M"))
                {
                    empSex="Male";
                }
                else if(empSex.equalsIgnoreCase("F"))
                {
                    empSex="Female";
                }
                String bankName=systems.getValue(OpenMSApp.Database_A, "Select * from Bank Where cBankCode='"+dbBankCode[y]+"'", "vName");
                String departmetName=systems.getValue(OpenMSApp.Database_A, "Select * from Department Where cDepartmentCode='"+dbDepartment[y]+"'", "vDepartmentName");
                String designationName=systems.getValue(OpenMSApp.Database_A, "Select * from Designation Where cDesignationCode='"+dbDesignation[y]+"'", "vDesignationName");
            
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
        writer.write("  <tr>\n");
        writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.noteURLPath+profileName+"_Header.png\" width=\"595\" height=\"160\" alt=\"\" /></div></td>\n");
        writer.write("  </tr>\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"2\" align=\"centre\"> <img src=\""+Configuration.employeeImageDirURLPath+dbEmployeeID[y]+".JPG\" alignment=\"center\"/>"
                        + "<P>"+dbEmployeeID[y]+"</P></td>\n");
                writer.write(" </tr>\n");              
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Title</th>\n");                  
                writer.write("    <td>"+empTitle.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">First Name</th>\n");                  
                writer.write("    <td>"+dbFirstName[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Middle Name</th>\n");                  
                writer.write("    <td>"+dbMiddleName[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Last Name</th>\n");                  
                writer.write("    <td>"+dbLastName[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Status</th>\n");                  
                writer.write("    <td>"+empStatus.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Address</th>\n");                  
                writer.write("    <td>"+empAddress.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">City</th>\n");                  
                writer.write("    <td>"+empCity.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">State</th>\n");                  
                writer.write("    <td>"+empState+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Country</th>\n");                  
                writer.write("    <td>"+empCountry.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Zip</th>\n");                  
                writer.write("    <td>"+empZip.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Sex</th>\n");                  
                writer.write("    <td>"+empSex.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Date of Birth</th>\n");                  
                writer.write("    <td>"+systems.extractSQLDate(dbDOB[y]).toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Phone Number</th>\n");                  
                writer.write("    <td>"+empPhone.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Department</th>\n");                  
                writer.write("    <td>"+departmetName.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Designation</th>\n");                  
                writer.write("    <td>"+designationName.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Bank</th>\n");                  
                writer.write("    <td>"+bankName.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Account Number</th>\n");                  
                writer.write("    <td>"+dbAccount[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Appointment Date</th>\n");                  
                writer.write("    <td>"+systems.overrideNullDate(dbAppointmentDate[y]).toUpperCase() +"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Resignation Date</th>\n");                  
                writer.write("    <td>"+systems.overrideNullDate(dbResignationDate[y]).toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
        writer.write("  <tr>\n");
        writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.noteURLPath+profileName+"_Footer.png\" width=\"595\" height=\"160\" alt=\"\" /></div></td>\n");
        writer.write("  </tr>\n");
                writer.write("</table>\n");
                y++;
            }
        }
        if(profileName.equalsIgnoreCase("Student"))
        {
            String[][] dbStudent=systems.getTableDataArrayNum(OpenMSApp.Database_A, query,1,2,3,4,5,6,7,8,9,10,12,13,14,15,16,17);
            
            String[] dbStudentID=dbStudent[0];
            String[] dbFirstName=dbStudent[1];
            String[] dbMiddleName=dbStudent[2];
            String[] dbLastName=dbStudent[3];
            String[] dbAddress=dbStudent[4];
            String[] dbCity=dbStudent[5];
            String[] dbState=dbStudent[6];
            String[] dbCountryCode=dbStudent[7];
            String[] dbZip=dbStudent[8];
            String[] dbSex=dbStudent[9];
            String[] dbPhone=dbStudent[10];
            String[] dbPostal=dbStudent[11];
            String[] dbEmail=dbStudent[12];
            String[] dbStatus=dbStudent[13];
            String[] dbDateRecorded=dbStudent[14];
            String[] dbLastBatchCode=dbStudent[15];
            java.sql.Date[] dbDOB=systems.getTableDateArrayNum(OpenMSApp.Database_A, query, 11);
            
            int x=dbStudentID.length,y=0;
            while(y<x)
            {
                String demacate="Y";
                demacate=changeDemacate(demacate);
                
                String dbCountry=systems.getValue(OpenMSApp.Database_A, "Select * from Country where cCountryCode='"+dbCountryCode[y]+"'", "vCountryName");
                if(dbSex[y].equalsIgnoreCase("M"))
                {
                    dbSex[y]="Male";
                }
                else if(dbSex[y].equalsIgnoreCase("F"))
                {
                    dbSex[y]="Female";
                }
                
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
        writer.write("  <tr>\n");
        writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.noteURLPath+profileName+"_Header.png\" width=\"595\" height=\"160\" alt=\"\" /></div></td>\n");
        writer.write("  </tr>\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\"2\" align=\"centre\"> <img src=\""+Configuration.studentImageDirURLPath+dbStudentID[y]+".JPG\" alignment=\"center\"/>"
                        + "<P>"+dbStudentID[y]+"</P></td>\n");
                writer.write(" </tr>\n");      
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">First Name</th>\n");                  
                writer.write("    <td>"+dbFirstName[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Middle Name</th>\n");                  
                writer.write("    <td>"+dbMiddleName[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Last Name</th>\n");                  
                writer.write("    <td>"+dbLastName[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Status</th>\n");                  
                writer.write("    <td>"+dbStatus[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Address</th>\n");                  
                writer.write("    <td>"+dbAddress[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">City</th>\n");                  
                writer.write("    <td>"+dbCity[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">State</th>\n");                  
                writer.write("    <td>"+dbState[y]+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Country</th>\n");                  
                writer.write("    <td>"+dbCountry.toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Zip</th>\n");                  
                writer.write("    <td>"+dbZip[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Sex</th>\n");                  
                writer.write("    <td>"+dbSex[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Date of Birth</th>\n");                  
                writer.write("    <td>"+systems.extractSQLDate(dbDOB[y]).toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Phone Number</th>\n");                  
                writer.write("    <td>"+dbPhone[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Postal Address</th>\n");                  
                writer.write("    <td>"+dbPostal[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
                writer.write("  <tr>\n");
                writer.write("    <th "+setDemacateSyntax(demacate)+">Email</th>\n");                  
                writer.write("    <td>"+dbEmail[y].toUpperCase()+"</td>\n");
                writer.write("  </tr>\n");
        writer.write("  <tr>\n");
        writer.write("    <td width=\"895\"><div align=\"center\"><img src=\""+Configuration.noteURLPath+profileName+"_Footer.png\" width=\"595\" height=\"160\" alt=\"\" /></div></td>\n");
        writer.write("  </tr>\n");
                writer.write("</table>\n");
                y++;
            }
        }
        if(profileName.equalsIgnoreCase("Profile Member"))
        {
            int x=dbMemberRecord[0].length,y=0;
            if(multiRow.equalsIgnoreCase("1"))
            {
                String demacate="Y";
                demacate=changeDemacate(demacate);
                writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                writer.write(" <tr>\n");
                writer.write("    <td colspan=\""+(tbAttribute.length-2)+"\" align=\"centre\"> <img src=\""+Configuration.profileImageDirURLPath+dbMemberRecord[0][y]+".JPG\" alignment=\"center\"/>"
                        + "<P>"+dbMemberRecord[0][y].toUpperCase()+"</P></td>\n");
                writer.write(" </tr>\n");  
                writer.write(" <tr>\n");
                for(int xu=0;xu<tbAttribute.length;xu++)
                {
                    if(xu==1||xu==2)
                    {
                    }
                    else
                    {       
                        writer.write("    <th "+setDemacateSyntax(demacate)+">"+systems.toTitleCase(tbAttribute[xu])+"</th>\n"); 
                    }
                }
                writer.write(" </tr>\n");  
                    
                while(y<x)
                {
                    demacate="Y";
                    demacate=changeDemacate(demacate);

                    writer.write("  <tr>\n");
                    for(int xu=0;xu<tbAttribute.length;xu++)
                    {
                        if(xu==1||xu==2)
                        {                        
                        }
                        else
                        {                 
                            writer.write("    <td>"+dbMemberRecord[xu][y].toUpperCase()+"</td>\n");
                        }
                    }                    
                    writer.write("  </tr>\n");       
        writer.write("  <tr>\n");
        writer.write("    <th "+setDemacateSyntax(demacate)+">Comment:</th>\n");
        writer.write("    <td colspan=\""+(tbAttribute.length-2)+"\">"+systems.getValue(OpenMSApp.Database_A,"Select tComment from profileMemberComment where cProfileMemberCode='"+dbMemberRecord[0][y]+"' and cTableCode='"+profileCode+"'",1)+"</td>\n");        
        writer.write("  </tr>\n");
                    y++;if(y==x)break;
                }
                    writer.write("</table>\n");
            }
            else if(multiRow.equalsIgnoreCase("0"))
            {
                while(y<x)
                {

                    System.out.println("Writing profile member,x="+x+",y="+y);
                    String demacate="Y";
                    demacate=changeDemacate(demacate);

                    writer.write("<table width=\"1073\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" id=\"journal\">\n");
                    writer.write("  <tr>\n");
                    writer.write("    <td><div align=\"center\"><img src=\""+Configuration.noteURLPath+profileName+"_Header.png\" width=\"595\" height=\"160\" alt=\"\" /></div></td>\n");
                    writer.write("  </tr>\n");
                    writer.write(" <tr>\n");
                    writer.write("    <td colspan=\"2\" align=\"centre\"> <img src=\""+Configuration.profileImageDirURLPath+dbMemberRecord[0][y]+".JPG\" alignment=\"center\"/>"
                            + "<P>"+dbMemberRecord[0][y].toUpperCase()+"</P></td>\n");
                    writer.write(" </tr>\n");  

                    for(int xu=0;xu<tbAttribute.length;xu++)
                    {
                        if(xu==1||xu==2)
                        {
                        }
                        else
                        {
                            writer.write("  <tr>\n");
                            writer.write("    <th "+setDemacateSyntax(demacate)+">"+systems.toTitleCase(tbAttribute[xu])+"</th>\n");
                            writer.write("    <td>"+dbMemberRecord[xu][y].toUpperCase()+"</td>\n");
                            writer.write("  </tr>\n");
                        }
                    }                   
        writer.write("  <tr>\n");
        writer.write("    <th "+setDemacateSyntax(demacate)+">Comment:</th>\n");
        writer.write("    <td>"+systems.getValue(OpenMSApp.Database_A,"Select tComment from profileMemberComment where cProfileMemberCode='"+dbMemberRecord[0][y]+"' and cTableCode='"+profileCode+"'",1)+"</td>\n");        
        writer.write("  </tr>\n");
                    writer.write("  <tr>\n");
                    writer.write("    <td ><div align=\"center\"><img src=\""+Configuration.noteURLPath+profileName+"_Footer.png\" width=\"595\" height=\"160\" alt=\"\" /></div></td>\n");
                    writer.write("  </tr>\n");
                    writer.write("</table>\n");
                    y++;if(y==x)break;
                }
            }
        }
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
            System.out.println("Error writing profile note "+e);
        }
    
    }
}
