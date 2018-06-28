package com.qait.automation.AdvanceTatoc;

import java.sql.SQLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCases {

	WebDriver driver;
	JavascriptExecutor jsDriver;
	clickOnAdvance click;
	menuSelection select;
	dbFile db;
	RestClass rest;
	@BeforeClass
	public void openBrowser() {
		driver=new ChromeDriver();
		jsDriver=(JavascriptExecutor) driver;
		jsDriver.executeScript("window.location='http://10.0.1.86/tatoc\'");
	}
	
	@Test
	public void Step_01_clicking_On_Advance_option() {
		click=new clickOnAdvance(jsDriver);
		click.clickLink();
	}
	@Test
	public void Step_02_menu_Selection() {
		select=new menuSelection(jsDriver);
		select.menu_goNext();
	}
	@Test
	public void Step_03_dbConnection() throws ClassNotFoundException, SQLException {
		db=new dbFile(jsDriver);
		db.queryGate();
	}

	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}
