package com.qait.automation.AdvanceTatoc;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class menuSelection {
	JavascriptExecutor jsDriver;
	public menuSelection(JavascriptExecutor jsDriver) {
		this.jsDriver=jsDriver;
	}

	public void menu_goNext() {
		jsDriver.executeScript("document.querySelector('span[onclick]').click()");
		String response=(String) jsDriver.executeScript("return document.querySelector('h1').textContent");
		Assert.assertEquals(response, "Query Gate");
	}

}
