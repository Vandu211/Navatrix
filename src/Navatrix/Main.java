package Navatrix;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		
		try (Scanner sc=new Scanner(System.in);){
			String filepath="C:\\Users\\chava\\java code\\DynamicDataLoader\\Resources\\data.csv";
            DataLoader.loadCSV(filepath);
			
			
			  int ch=0; 
			  int id=0;
			  String Updated_name="";

			 
		do {
			System.out.println();
			System.out.println("enter your choice:");
			System.out.println("1.Display Data");
			System.out.println("2.update data");
			System.out.println("3.delete Data");
			
			 ch=sc.nextInt();
			
			switch(ch)
			 {
			 case 1:
			 {
				 DataLoader.Read("Data");
				 break;
				 
			 }
			 
			 case 2:
	           {
				   System.out.println("enter the id to be updated");
				   id=sc.nextInt();
				  
				   System.out.println("enter the updated name you want");
                   Updated_name=sc.next();
                      
				 DataLoader.update("Data", id, "name", Updated_name);
				 break;
				 
	           }
			 case 3:
			 {
				 System.out.println("Enter the Id you want to delete");
				  id=sc.nextInt();
				 DataLoader.delete("Data", id);
				 break;
				 
			 }
			 default:
                System.out.println("enter correct choice");
				 break;
			 
			 }
			
		}while(ch>0);
			 
			
			
			
			
			
			
            
            
            
            
            
            

		}catch(IOException| SQLException e)
		{
			System.out.println(e);
		}

	}

}
