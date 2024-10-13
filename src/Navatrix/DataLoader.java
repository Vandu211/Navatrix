package Navatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataLoader {
	
	
	public static void loadCSV(String filePath) throws IOException, SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Connection connection = DatabaseConnection.getConnection()) {

            String line = br.readLine();  // Read the header line
            if (line == null) return;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                String query = "INSERT INTO Data (id, name, age, city) VALUES (?, ?, ?, ?)"
                +"ON DUPLICATE KEY UPDATE name = VALUES(name), age = VALUES(age), city = VALUES(city)";
                               
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, Integer.parseInt(values[0]));
                    preparedStatement.setString(2, values[1]);
                    preparedStatement.setInt(3, Integer.parseInt(values[2]));
                    preparedStatement.setString(4, values[3]);
                    preparedStatement.executeUpdate();
                }
            }
            System.out.println("CSV data inserted successfully");
        }
    }
	
	
	public static void Read(String table) throws SQLException{
		try(Connection connection=DatabaseConnection.getConnection()){
			 
			String query="SELECT * FROM "+ table;
			try(PreparedStatement preparestatement=connection.prepareStatement(query);
					ResultSet resultSet=preparestatement.executeQuery()){
				int columnCount=resultSet.getMetaData().getColumnCount();
				
				while(resultSet.next()) {
					for(int i=1;i<columnCount;i++)
					{
                        System.out.print(resultSet.getString(i) + " ");

					}
                    System.out.println();

				}
			}
		}
	}
	
	
	public static void update(String table,int id,String Column, String NewValue) throws SQLException{
		 try (Connection connection = DatabaseConnection.getConnection()) {
	            String query = "UPDATE " + table + " SET " + Column + " = ? WHERE id = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, NewValue);
	                preparedStatement.setInt(2, id);
	                preparedStatement.executeUpdate();
	                System.out.println("Data updated successfully");
	            }
	        }
		 
		 
	}
	
	
	
	
	
	public static void delete(String table,int id)throws SQLException{
		try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "DELETE FROM " + table + " WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Data deleted successfully");
            }
        }
	}
}