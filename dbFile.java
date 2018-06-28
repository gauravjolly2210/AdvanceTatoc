package com.qait.automation.AdvanceTatoc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class dbFile {
	JavascriptExecutor jsDriver;
	public dbFile(JavascriptExecutor jsDriver) {
		this.jsDriver=jsDriver;
	}

	public String[] databaseConnections(String symbol) throws ClassNotFoundException, SQLException {
	Connection con;
	String[] arr=new String[2];
	Class.forName("com.mysql.jdbc.Driver");
	con=DriverManager.getConnection("jdbc:mysql://10.0.1.86/tatoc", "tatocuser","tatoc01");
	String sql="select * from identity where symbol=?";
	PreparedStatement ps=con.prepareStatement(sql);
	ps.setString(1, symbol);
	ResultSet rs=ps.executeQuery();
	rs.next();
	System.out.println(rs.getInt("id"));
	int id=rs.getInt("id");
	String sql2="select * from credentials where id=?";
	PreparedStatement ps2=con.prepareStatement(sql2);
	ps2.setInt(1, id);
	ResultSet rs2=ps2.executeQuery();
	while(rs2.next()) {
	arr[0] =rs2.getString("name");
	arr[1]=rs2.getString("passkey");
			}
	return arr;
	}
	public void queryGate() throws ClassNotFoundException, SQLException {
		String symbol=(String)jsDriver.executeScript("return document.getElementById(\"symboldisplay\").textContent");
		String[] arr= new String[2];
		arr=databaseConnections(symbol);
		String name=arr[0];
		String passkey=arr[1];
		jsDriver.executeScript("document.getElementById(\"name\").value='"+name+"'");
		jsDriver.executeScript("document.getElementById(\"passkey\").value='"+passkey+"'");
		jsDriver.executeScript("document.getElementById(\"submit\").click()");
		
		String response=(String) jsDriver.executeScript("return document.querySelector('h1').textContent");
		Assert.assertEquals(response, "Ooyala Video Player");
		
	}

}
