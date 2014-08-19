package com.example.chapter2activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Activity3 extends Activity {
	Button btn_webbrowsers, btn_makecalls, btn_showmaps, btn_choosecontact, btn_notification;
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
		// mo notification
		btn_notification = (Button) findViewById(R.id.btn_notification);
		btn_notification.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				displayNotification();
				
			}
		});
	}
	
	protected void displayNotification(){
		Intent it = new Intent(this, NotificationView.class);
		it.putExtra("notificationID", 1);
		
		PendingIntent pdI = PendingIntent.getActivity(this, 0, it, 0);
		
		NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification notif = new Notification(R.drawable.traitim,
				"Chuẩn bị làm tình...nguyện", java.lang.System.currentTimeMillis());
		
		CharSequence from = "System Alarm";
		CharSequence message = "Đi làm tình ..nguyện";		
		notif.setLatestEventInfo(this, from, message, pdI);
		notif.vibrate =  new long[] {100, 250, 100, 500};
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.count_down);
		mp.start();
		nm.notify(1, notif);
	}
	
}
