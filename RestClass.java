package com.qait.automation.AdvanceTatoc;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

public class RestClass {
	JavascriptExecutor jsDriver;
	public RestClass(JavascriptExecutor jsDriver) {
		this.jsDriver=jsDriver;
	}
	
	
	public void creatingRest() throws IOException, InterruptedException {
			String Session_id=(String)jsDriver.executeScript("return document.getElementById('session_id').textContent");
			String id= Session_id.substring(Session_id.indexOf(":")+2);
			System.out.println(id);
		    URL url_get = new URL("http://10.0.1.86/tatoc/advanced/rest/service/token/"+id+"");
            HttpURLConnection conn_get = (HttpURLConnection) url_get.openConnection();
            conn_get.setRequestMethod("GET");
            conn_get.setRequestProperty("Accept", "application/json");
            InputStreamReader in = new InputStreamReader(conn_get.getInputStream());
            BufferedReader br = new BufferedReader(in);
            String output;
            String token="";
            while ((output = br.readLine()) != null) {
            	token=new String(output.substring(output.indexOf(":")+2,output.indexOf(",")-1));
            }
            System.out.println(token);    
			 conn_get.disconnect();		
					URL posturl = new URL("http://10.0.1.86/tatoc/advanced/rest/service/register");
					HttpURLConnection postconn = (HttpURLConnection) posturl.openConnection();
					postconn.setDoOutput(true);
					postconn.setRequestMethod("POST");
					postconn.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
					String input = "id="+id+"& signature="+token+"&allow_access=1";
					DataOutputStream wr = new DataOutputStream(postconn.getOutputStream());
					wr.writeBytes(input);
					wr.flush();
					wr.close();

					int responseCode = postconn.getResponseCode();
					postconn.disconnect();
					jsDriver.executeScript("document.querySelector('a').click()");
					Thread.sleep(1000);
					String response=(String) jsDriver.executeScript("return document.querySelector('h1').textContent");
					
					Assert.assertEquals(response, "File Handle");
		    }
}
