package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bill", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertBill(String unit, String tax, String billNo, String amount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into bill (`billId`,`unit`,`tax`,`billNo`,`amount`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, unit);
			preparedStmt.setString(3, tax);
			preparedStmt.setString(4, billNo);
			preparedStmt.setString(5, amount);
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newBill = readBill();
			output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the bill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readBill() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<div class='w-100'><table class='table table-secondary table-hover table-bordered'><tr><th>Units</th>"
					+ "<th>Tax</th><th>Bill No</th>" + "<th>Amount</th>"
					+ "<th>Update</th><th>Remove</th></tr></div>";
			String query = "select * from bill";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String billId = Integer.toString(rs.getInt("billId"));
				String unit = rs.getString("unit");
				String tax = rs.getString("tax");
				String billNo = rs.getString("billNo");
				String amount = rs.getString("amount");

				// Add into the html table
				output += "<tr><td>" + unit + "</td>";
				output += "<td>" + tax + "</td>";
				output += "<td>" + billNo + "</td>";
				output += "<td>" + amount + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' "
						+ "class='btnUpdate btn btn-primary badge-pill' data-itemid='" + billId + "'></td>"
						+ "<td><input name='btnRemove' type='button' value='Remove' "
						+ "class='btnRemove btn btn-danger badge-pill' data-itemid='" + billId + "'></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}

		catch (Exception e) {
			output = "Error while reading the bill.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateBill(String billId, String unit, String tax, String billNo, String amount)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE bill SET unit=?,tax=?,billNo=?,amount=? WHERE billId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, unit);
			preparedStmt.setString(2, tax);
			preparedStmt.setString(3, billNo);
			preparedStmt.setString(4, amount);
			preparedStmt.setInt(5, Integer.parseInt(billId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newBill = readBill();
			output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the bill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String deleteBill(String billId) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from bill where billId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(billId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newBill = readBill();
			output = "{\"status\":\"success\", \"data\": \"" + newBill + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the bill.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
