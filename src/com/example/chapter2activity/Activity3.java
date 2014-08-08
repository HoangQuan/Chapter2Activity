package com.example.chapter2activity;

import android.app.Activity;
import android.content.Intent;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class Activity3 extends Activity {
	Button btn_webbrowsers, btn_makecalls, btn_showmaps, btn_choosecontact;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity3);
		
		// Goi webbrowser
		btn_webbrowsers = (Button) findViewById(R.id.btn_webbrowsers);
		btn_webbrowsers.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("https://www.google.com.vn/"));
				startActivity(intent);
				
			}
		});
		// Goi makecall
		btn_makecalls = (Button) findViewById(R.id.btn_makecalls);
		btn_makecalls.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(android.content.Intent.ACTION_DIAL, Uri.parse("tel:+012345678"));
				startActivity(intent);
				
			}
		});	
		// Goi sho maps
		btn_showmaps = (Button) findViewById(R.id.btn_showmaps);
		btn_showmaps.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = 
				new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:37.827500,-122.481670"));
				startActivity(intent);
				
			}
		});	
		// goi choose contact
		btn_choosecontact = (Button) findViewById(R.id.btn_choosecontact);
		btn_choosecontact.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(android.content.Intent.ACTION_PICK);
				intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
				startActivityForResult(intent, 1);
				
			}
		});	
	}
	
}
