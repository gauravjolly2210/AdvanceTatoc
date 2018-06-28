package com.qait.automation.AdvanceTatoc;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestCases2 {

	WebDriver driver;
	JavascriptExecutor jsDriver;
	
	RestClass rest;
	FileHandling file;
	@BeforeClass
	public void openBrowser() {
		driver=new ChromeDriver();
		jsDriver=(JavascriptExecutor) driver;
		jsDriver.executeScript("window.location='http://10.0.1.86/tatoc/advanced/rest'");
	}
	@Test
	public void Step_04_Rest() throws IOException, InterruptedException {
		rest=new RestClass(jsDriver);
		rest.creatingRest();
	}
	@Test(dependsOnMethods="Step_04_Rest")
	public void Step_05_file_Handling() throws IOException, InterruptedException {
		file=new FileHandling(jsDriver);
		file.handlingFiles();
	}
	
	@AfterClass
	public void closeBrowser() {
		driver.close();
	}
}
