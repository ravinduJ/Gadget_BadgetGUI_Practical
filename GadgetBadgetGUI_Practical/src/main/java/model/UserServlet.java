package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserServlet {
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3307/paf_practical", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//Insert user
	public String createPost(String username, String email, String dob, String type) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database";
			}
			

//			LocalDate date = LocalDate.now();
//			LocalTime time = LocalTime.now();

			String query = "Insert into user(username,email,dob,type) values(?,?,?,?) ";
			PreparedStatement ps = con.prepareStatement(query);

			ps.setString(1,username);
			ps.setString(2,email);
			ps.setString(3,dob.toString());
			ps.setString(4,type.toString());
			
			ps.executeQuery(query);
			con.close();

			output = "Insert Success";
		} catch (Exception e) {
			// TODO: handle exception
			output = "Error while inserting the Item";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	//read post
	
		public String read_Post() {
			String output = "";

			try {
				Connection con = connect();

				if (con == null) {
					return "Error";
				}
				output = "<table border='1'><tr><th>User ID</th><th>username</th><th>Email</th><th>dob</th><th>type</th>";
				String query = "select * from user";
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(query);

				while (rs.next()) {
					String id = Integer.toString(rs.getInt("iduser"));
					String username = rs.getString("username");
					String email = rs.getString("email");
					String dob = rs.getString("dob");	
					String type = rs.getString("type");
					output += "<tr><td>" + id + "</td>";
					output += "<td>" + username + "</td>";
					output += "<td>" + email + "</td>";
					output += "<td>" + dob + "</td>";
					output += "<td>" + type + "</td>";
					
					

					output += "<td><input name=\\\"btnUpdate\\\" type=\\\"button\\\" \r\n"
							+ " value=\\\"Update\\\" class=\\\"btn btn-secondary\\\"></td>\"\r\n"
							+ " + \"<td><form method=\\\"post\\\" action=\\\"posts.jsp\\\">\"\r\n"
							+ " + \"<input name=\\\"btnRemove\\\" type=\\\"submit\\\" value=\\\"Remove\\\" \r\n"
							+ " class=\\\"btn btn-danger\\\">\"\r\n"
							+ " + \"<input name=\\\"ID\\\" type=\\\" value=\\\"\" + id\r\n"
							+ " + \"\\\">\" + \"</form></td></tr>";

				}
				con.close();
				output += "</tabel>";
			} catch (Exception e) {
				// TODO: handle exception
				output = "Error while reading the Posts";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		//update User
		
		public String updatePost(String ID, String username, String email, String dob, String password) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for updating.";
				}
				

			

				String query = "UPDATE user SET username=?,password=?,published_date=?,published_time=?, name=?, type=? WHERE iduser=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, username);
				preparedStmt.setString(2, email);
				preparedStmt.setString(3, dob.toString());
				preparedStmt.setString(4, password.toString());
				preparedStmt.setInt(5, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			} catch (Exception e) {
				output = "Error while updating the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		
		
		// delete User
		
		public String deletePost(String ID) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "delete from user where iduser=?";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(ID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Deleted successfully";
			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}
			return output;
		}

}