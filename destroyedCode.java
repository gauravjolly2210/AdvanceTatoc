package com.qait.automation.AdvanceTatoc;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.JavascriptExecutor;
import com.sun.jersey.api.client.Client;

public class destroyedCode {
	JavascriptExecutor jsDriver;
	public destroyedCode(JavascriptExecutor jsDriver) {
		this.jsDriver=jsDriver;
	}
	
	
	public void creatingRest() throws IOException {
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
//           
//            URL url_post=new URL("http://10.0.1.86/tatoc/advanced/rest/service/register");
//            HttpURLConnection conn_post=(HttpURLConnection) url_post.openConnection();
//            conn_post.setRequestMethod("POST");
//            conn_post.setRequestProperty("Accept", "application/json");
//            OutputStreamWriter out=new OutputStreamWriter(conn_post.getOutputStream());
//            
			 conn_get.disconnect();		
			 
//			 URL url=new URL("http://10.0.1.86/tatoc/advanced/rest/service/register?id="+id+"&signature="+token+"&allow_access=1");
//            	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            	 conn.setRequestMethod("POST");
		        Client client = Client.create();
		        WebResource webResource = client.resource("http://10.0.1.86/tatoc/advanced/rest/service/register");    
		        //String input ="id="+id+"&signature="+token+"&allow_access=1";
		        JSONObject js=new JSONObject();		          
		        JSONArray js_arr=new JSONArray();
		        js_arr.put("id="+id);
		        js_arr.put("signature="+token);
		        js_arr.put("allow_access=1");
		        js.put("POST BODY", js_arr);
		        System.out.println(js.toString());
		        ClientResponse response = webResource.type("application/json").post(ClientResponse.class, js.toString());
		        
		        System.out.println("Output from Server .... \n");
		        String output1 = response.getEntity(String.class);
		        //System.out.println(response.);
		        
    }
}
