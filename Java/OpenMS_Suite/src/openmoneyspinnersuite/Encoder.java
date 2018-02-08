
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
 * @author Abiodun
 */
import java.io.*;
class Encoder implements Serializable{
    double cypherText[][];
    public Encoder(){}
        public Encoder(String plainText)
	{
		this.cypherText=encryptText(4,4,2,2,plainText);
	}
    	public double breedElement(int x1,int x2, int k1, int k2, int index)
    	{
        	double element=Math.pow(k1+index,x1)-(k2+Math.pow(index, x2));
        
        	return element;    
    	}
	public double[][] encryptText(int x1,int x2,int k1,int k2,String plainText)
    	{
	System.out.println("Encyption level 1 started...");
        char ca[]=plainText.toCharArray(),p;
        double breedValue[]=new double[ca.length];
        double breedValueNP[]=new double[ca.length];
	int maxLength=0;
	int x=0,r=0;
        for(char c:ca)
        {
            breedValue[x++]=breedElement(x1,x2,k1,k2,(int)c);
            breedValueNP[x-1]=breedElement(x1,x2,k1,k2,(int)c);
        }
	String norep[]=new String[ca.length];int as=0;
	System.out.println("Encyption level 2 started...");
	for(double d:breedValueNP)
	{
		int m=1;
		for(double e:breedValueNP)
		{
			if(d==e)
			{
				m++;
			}
		}
		if(m==1)
		norep[r++]=String.valueOf(d);
		else{
		norep[r++]=String.valueOf((long)d*m)+"@"+breedElement(x1+1,x2+1,k1+1,k2+1,m);
		breedValueNP[r-1]=d*m;
		}
		if(norep[r-1].length()>maxLength)maxLength=norep[r-1].length();
            	
	}int countMain=0;double cypher[][]=new double[ca.length][maxLength];
	System.out.println("Encyption level 3 started...");
	for(String snp:norep)
	{        
		double cypherValue[]=new double[snp.toCharArray().length];
		int count=0;
		for(char cy:snp.toCharArray())
        	{
            		cypherValue[count++]=breedElement(x1+2,x2+2,k1+2,k2+2,(int)cy);
			//System.out.print((long)cypherValue[count-1]+",");
        	}
		cypher[countMain++]=cypherValue;		
	}
        return cypher;
    }
}
