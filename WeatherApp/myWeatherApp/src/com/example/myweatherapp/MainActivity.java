package com.example.myweatherapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
	
	EditText txtCity;
	ImageView iv;
	TextView weathertype,cityName,temp,humidity;
	ImageButton btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.txtCity = (EditText) this.findViewById(R.id.editText1);
        this.iv = (ImageView) this.findViewById(R.id.imageView1);
        this.weathertype = (TextView) this.findViewById(R.id.textView1);
        this.cityName = (TextView) this.findViewById(R.id.textView2);
        this.temp = (TextView) this.findViewById(R.id.textView3);
        this.humidity = (TextView) this.findViewById(R.id.textView4);
        this.btnGo = (ImageButton) this.findViewById(R.id.imageButton1);
        
        this.btnGo.setOnClickListener(this);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
      
    }

	@Override
	public void onClick(View arg0) {
		
		String mycity = txtCity.getText().toString();
		
		  try {
			  	if(!mycity.equals("")){
				URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+mycity+"&APPID=54bbf5fffbed9ab6f5be5a6a7bc43f7a");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				StringBuffer sb = new StringBuffer();
				InputStream is = conn.getInputStream();
				int c = 0;
				while((c=is.read())!=-1){
					sb.append((char)c);
				}
				
				is.close();
				conn.disconnect();
				
				JSONObject json = new JSONObject(sb.toString());
				JSONArray weather = json.getJSONArray("weather");
				JSONObject desc = weather.getJSONObject(0);
				String ds = desc.getString("description");
				String icon = "w"+desc.getString("icon");
				
				if(icon.equals("w01d"))
					this.iv.setImageResource(R.drawable.w01d);
				if(icon.equals("w01n"))
					this.iv.setImageResource(R.drawable.w01n);
				
				if(icon.equals("w02d"))
					this.iv.setImageResource(R.drawable.w02d);
				if(icon.equals("w02n"))
					this.iv.setImageResource(R.drawable.w02n);
				
				if(icon.equals("w03d"))
					this.iv.setImageResource(R.drawable.w03d);
				if(icon.equals("w03n"))
					this.iv.setImageResource(R.drawable.w03n);
				
				if(icon.equals("w04d"))
					this.iv.setImageResource(R.drawable.w04d);
				if(icon.equals("w04n"))
					this.iv.setImageResource(R.drawable.w04n);
				
				if(icon.equals("w09d"))
					this.iv.setImageResource(R.drawable.w09d);
				if(icon.equals("w09n"))
					this.iv.setImageResource(R.drawable.w09n);
				
				if(icon.equals("w10d"))
					this.iv.setImageResource(R.drawable.w10d);
				if(icon.equals("w10n"))
					this.iv.setImageResource(R.drawable.w10n);
				
				if(icon.equals("w11d"))
					this.iv.setImageResource(R.drawable.w11d);
				if(icon.equals("w11n"))
					this.iv.setImageResource(R.drawable.w11n);
				
				if(icon.equals("w13d"))
					this.iv.setImageResource(R.drawable.w13d);
				if(icon.equals("w13n"))
					this.iv.setImageResource(R.drawable.w13n);
				
				if(icon.equals("w50d"))
					this.iv.setImageResource(R.drawable.w50d);
				if(icon.equals("w50n"))
					this.iv.setImageResource(R.drawable.w50n);
				
				this.weathertype.setText(ds);

				String city = json.getString("name");
				JSONObject main = json.getJSONObject("main");
				String temp = main.getString("temp");
				String hum = main.getString("humidity");
				
				double celsuis = Double.parseDouble(temp)-272.3;
				
				this.cityName.setText("City / Country : "+city);
				this.temp.setText("Temperature : "+String.format("%.2f", celsuis)+" ºC");
				this.humidity.setText("Humidity : "+hum + "%");
				
			  	}
			  	else{
			  		AlertDialog.Builder builder=new AlertDialog.Builder(this);
			  		builder.setTitle("Error!");
			  		builder.setMessage("You must enter City/Country");
			  		builder.setNeutralButton("Okey", null);
			  		AlertDialog dialog = builder.create();
			  		dialog.show();
			  	}
			 	} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}


    
    
}
