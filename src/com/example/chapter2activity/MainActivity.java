package com.example.chapter2activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	ImageView image;
	String tag= "";
	int request_code = 1;
	CharSequence[] items = {"A", "B", "C"};
	boolean[] itemsChecked = new boolean [items.length];
	
	private ProgressDialog _progressDialog;
	private int _progress = 0;
	private Handler _progressHandler;
	private Button btn_start_activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // view image
//        image = (ImageView) findViewById(R.id.imageView1);

//        requestWindowFeature(Window.FEATURE_NO_TITLE); hidden title of window
        setContentView(R.layout.activity_main);
        // Create button
        btn_start_activity = (Button) findViewById(R.id.activity2_btn);
        btn_start_activity.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				startActivityForResult(new Intent("com.example.chapter2activity.Activity2"), request_code); // start Activity and wait return result
//	    		startActivity(intent); // start Activity without return result
				Intent intent = new Intent("com.example.chapter2activity.Activity2");//new Intent(MainActivity.this, Activity2.class);
				Bundle extras = new Bundle();
				extras.putString("Name", "Your Name");
				intent.putExtras(extras);
				startActivityForResult(intent, 1);
			}
		});
        Button btn = (Button) findViewById(R.id.btn_dialog);
//        //set event for button
        btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
//				showDialog(0);
				showDialog(1);
				_progress = 0;
				_progressDialog.setProgress(0);
				_progressHandler.sendEmptyMessage(0);
			}
		});
        
        _progressHandler = new Handler(){
        	public void handleMessage(Message msg) {
        		super.handleMessage(msg);
        		if (_progress >=100){
        			_progressDialog.dismiss();
        		} else {
        			_progress ++;
        			_progressDialog.incrementProgressBy(1);
        			_progressHandler.sendEmptyMessageDelayed(0, 100);
        		}
        	}
        };
        Button exit_btn = (Button) findViewById(R.id.btn_exit);
        exit_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				System.exit(0);
			}
		});
        
        Button change_image_btn = (Button) findViewById(R.id.change_image_btn);
        change_image_btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				getImage();
			}
		});
        Log.d(tag, "This is onCreate()");
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data){
//    	if (requestCode == request_code){
//    		if (requestCode == RESULT_OK) {
    			Toast.makeText(this, data.getData().toString(), Toast.LENGTH_SHORT).show();
//    		}
//    	}
    }
    public boolean onKeyDown(int keyCode, KeyEvent event){
    	if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER){
//    		Intent intent = new Intent(this, Activity2.class);
//    		startActivity(intent);
    		startActivity(new Intent("com.example.chapter2activity.Activity2"));
    	}
    	return false;
    }
    
    protected Dialog onCreateDialog(int id) {
    	switch (id) {
    	case 0:
    		return new AlertDialog.Builder(this)
    				.setIcon(R.drawable.ic_launcher)
    				.setTitle("this is Dialog..")
    				.setPositiveButton("OK", new 
    						DialogInterface.OnClickListener() {
    					    public void onClick(DialogInterface dialog, int whichButton) 
    					    {
    					    	Toast.makeText(getBaseContext(), 
    					    			"OK clicked!", Toast.LENGTH_SHORT).show();

    					    }
    				})
    				.setNegativeButton("Cancel", new
    					DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog,
    							int whichButton)
    					{
    						Toast.makeText(getBaseContext(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
    					}
    				})
    				.setMultiChoiceItems(items, itemsChecked, new
    						DialogInterface.OnMultiChoiceClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which, boolean isChecked) {
									Toast.makeText(getBaseContext(), 
											items[which] + (isChecked ? "checked!":"unchecked!"), Toast.LENGTH_SHORT).show();							
								}
							}
    						)
    						.create();
    	case 1:
    		_progressDialog = new ProgressDialog(this);
    		_progressDialog.setIcon(R.drawable.ic_launcher);
    		_progressDialog.setTitle("Đang tải");
    		_progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
    		_progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Hide", new 
    				DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(getBaseContext(), "Hide Click", Toast.LENGTH_SHORT).show();
						}
					});
    		_progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new 
    				DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Toast.makeText(getBaseContext(), "Cancel Clicked", Toast.LENGTH_SHORT).show();
						}
					});
    		return _progressDialog;
    	}
    	return null;
    	
    }

    public void getImage(){
    	ImageView img = (ImageView) findViewById(R.id.imageView1);
    	int[] images = {R.drawable.sexi_1, R.drawable.sexi_2, R.drawable.sexi_3, R.drawable.sexi_4, R.drawable.sexi_5 };
    	int imageId = (int)(Math.random() * images.length);
    	img.setImageDrawable(getResources().getDrawable(images[imageId]));
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void onStart(){
    	super.onStart();
    	Log.d(tag, "This is onStart()");
    }
    
    public void onRestart(){
    	super.onRestart();
    	Log.d(tag, "This is onRestart()");
    }
    
    public void onResume(){
    	super.onResume();
    	Log.d(tag, "This is onResume()");
    }
    
    public void onPause(){
    	super.onPause();
    	Log.d(tag, "This is onPause()");
    }
    
    public void onStop(){
    	super.onStop();
    	Log.d(tag, "This is onStop()");
    }
    
    public void onDestroy(){
    	super.onDestroy();
    	Log.d(tag, "This is onDestroy()");
    }
}
