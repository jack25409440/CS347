import java.io.*;
import java.sql.*;
import java.util.*;

public class Variation2
{
   public static void main(String[] args)
   {
      ArrayList<Integer> list=new ArrayList<Integer>();
      for(int i=0;i<5000000;i++)
         list.add(i+1);
      //System.out.println(list.get(1));
      Collections.shuffle(list);
      System.out.println("Begin Variation 2");
      Variation2 var2=new Variation2();
      try {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    String url = "jdbc:oracle:thin:@localhost:1521:orcl";
                    //@hostname:port:SID
   Connection connection = DriverManager.getConnection(url,"system","oracle");
   Statement sql = connection.createStatement();
   double t1=System.currentTimeMillis();
   var2.createTable(sql);
   var2.insertRows(sql,list);
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
      String lan="Create Table benchmark2("+ 
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

   public void insertRows(Statement sql,ArrayList<Integer> list)
   {
     try{
      int temp=5000000;
      for(int i=0;i<temp;i++)
      {
        int temp1=(int)(Math.random()*4999999)+1;
        int temp2=(int)(Math.random()*4999999)+1;
        String lan="insert into benchmark2 values("+String.valueOf(list.get(i))+","+String.valueOf(temp1)+","+String.valueOf(temp2)+",'"+UUID.randomUUID().toString()+"')";
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
