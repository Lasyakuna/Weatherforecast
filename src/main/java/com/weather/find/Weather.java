package com.weather.find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Weather {
	
	public static void main(String[] args) throws IOException{
		String ip="https://ipinfo.io/json";
		StringBuilder data=new StringBuilder();
		//create url
		URL ipurl=new URL(ip);
		//to open the url
		URLConnection con =ipurl.openConnection();
		//to read the data which is in json format
		BufferedReader rd=new BufferedReader(new InputStreamReader(con.getInputStream()));
		String line;
		while((line=rd.readLine())!=null)
		{
			data.append(line);
		}
		rd.close();
	     JSONObject obj= new JSONObject(data.toString());
		 String city=obj.getString("city");
		 String region=obj.getString("region");
		 String pin=obj.getString("postal");
		 String loc=obj.getString("loc");
		 String lat=loc.substring(0, 7);
		 String lon=loc.substring(9, 15);
		 String apikey="873e0b50d570db4d13e70928dc00ca50";
    	 String urlString="http://api.openweathermap.org/data/2.5/forecast?lat="+lat+"&lon="+lon+"&appid="+apikey+"&units=metric";
    	
    	
    		StringBuilder result=new StringBuilder();
    		//create url
    		URL url=new URL(urlString);
    		//to open the url
    		URLConnection conn =url.openConnection();
    		//to read the data which is in json format
    		BufferedReader r=new BufferedReader(new InputStreamReader(conn.getInputStream()));
    		String line2;
    		while((line2=r.readLine())!=null)
    		{
    			result.append(line2);
    		}
    		rd.close();
    		
    		 JSONObject jsonObject = new JSONObject(result.toString());
             JSONArray flist = jsonObject.getJSONArray("list");
             //print the details 
             System.out.println("-----------WEATHER FORECAST OF YOUR LOCATION------------");
             System.out.println();
             System.out.println();
             //print the details of the location
             System.out.println("**DETAILS OF YOUR LOCATION**");
             System.out.println("CITY:"+city);
             System.out.println("STATE:"+region);
             System.out.println("PINCODE:"+pin);
             System.out.println();
             System.out.println();
             // Print the weather data in a table format
             System.out.println("**WEATHER FORECAST **");
             System.out.println("Date\t\tTemperature\tHumidity\tWeatherdescription");
             System.out.println("--------------------------------------------------------------------------------------");
             for (int i = 0; i < flist.length(); i++) {
            	 if(i%8==0) {
                 JSONObject forecast = flist.getJSONObject(i);
                 long timestamp = forecast.getLong("dt") * 1000;
                 Date date = new Date(timestamp);
                 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                 String WD = forecast.getJSONArray("weather").getJSONObject(0).getString("description");
                 double temp = forecast.getJSONObject("main").getDouble("temp"); 
                 double humid = forecast.getJSONObject("main").getDouble("humidity"); 
                 System.out.println(dateFormat.format(date) + "\t" + temp + "\t\t"+ humid + "%\t\t" + WD);
             }
             }
	}
}
    	
        