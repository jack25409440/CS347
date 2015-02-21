import java.io.*;
import java.sql.*;
import java.util.*;

public class Variation1
{
   public static void main(String[] args)
   {
      System.out.println("Begin Variation 1");
      Variation1 var1=new Variation1();
      try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                    //@hostname:port:SID
   Connection connection = DriverManager.getConnection(url,"system","oracle");
   Statement sql = connection.createStatement();
   double t1=System.currentTimeMillis();
   var1.createTable(sql);
   var1.insertRows(sql);
   double t2=System.currentTimeMillis();
   System.out.println(t2-t1);
      }
     catch(Exception e)
      { 
          e.printStackTrace();
       }
   }

  
   public void createTable(Statement sql)
   {
      try
      {
      String lan="Create Table benchmark1("+ 
                                          "theKey NUMBER PRIMARY KEY,"+
                                           "columnA NUMBER,"+ 
                                            "columnB NUMBER,"+ 
                                           "filler CHAR(247))";
      sql.executeQuery(lan);
      }
      catch(Exception ex)
      {
         ex.printStackTrace();
      }
   }

   public void insertRows(Statement sql)
   {
     try{
      int temp=5000000;
      for(int i=0;i<temp;i++)
      {
        int temp1=(int)(Math.random()*4999999)+1;
        int temp2=(int)(Math.random()*4999999)+1;
        String lan="insert into benchmark1 values("+String.valueOf(i+1)+","+String.valueOf(temp1)+","+String.valueOf(temp2)+",'"+UUID.randomUUID().toString()+"')";
        //System.out.println(lan); 
        sql.executeQuery(lan);
      }
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
   }
}
