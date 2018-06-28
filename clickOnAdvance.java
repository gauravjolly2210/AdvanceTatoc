package com.qait.automation.AdvanceTatoc;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class clickOnAdvance {

	JavascriptExecutor jsDriver;
	public clickOnAdvance(JavascriptExecutor jsDriver) {
		this.jsDriver=jsDriver;
	}
	public void clickLink() {
		jsDriver.executeScript("document.querySelector(\"a[href='/tatoc/advanced']\").click()");
		String response=(String) jsDriver.executeScript("return document.querySelector('h1').textContent");
		Assert.assertEquals(response, "Hover Menu");
	}
}
