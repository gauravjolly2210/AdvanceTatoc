package com.qait.automation.AdvanceTatoc;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import org.apache.commons.io.comparator.LastModifiedFileComparator;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class FileHandling {

	JavascriptExecutor jsDriver;
	public FileHandling(JavascriptExecutor jsDriver) {
	this.jsDriver=jsDriver;
	}

	public void handlingFiles() throws IOException, InterruptedException {
	
		jsDriver.executeScript("document.querySelector('a').click()");	
		Thread.sleep(2000);
	    File file = getTheNewestFile("/home/qainfotech/Downloads/","dat");
		//File file = new File("/home/qainfotech/Downloads/file_handle_test.dat");

		FileInputStream fileInput =new FileInputStream(file);
		Properties prop = new Properties();
		prop.load(fileInput);
		jsDriver.executeScript("document.getElementById('signature').value='"+prop.getProperty("Signature")+"'");
		jsDriver.executeScript("document.querySelector('.submit').click()");
		String response=(String)jsDriver.executeScript("return document.querySelector('h1').textContent");
		Assert.assertEquals(response, "End");
	}
	
	public File getTheNewestFile(String filePath, String ext) {
	    File theNewestFile = null;
	    File dir = new File(filePath);
	    FileFilter fileFilter = new WildcardFileFilter("*." + ext);
	    File[] files = dir.listFiles(fileFilter);

	    if (files.length > 0) {
	        /** The newest file comes first **/
	        Arrays.sort(files, LastModifiedFileComparator.LASTMODIFIED_REVERSE);
	        theNewestFile = files[0];
	    }

	    return theNewestFile;
	}



}
