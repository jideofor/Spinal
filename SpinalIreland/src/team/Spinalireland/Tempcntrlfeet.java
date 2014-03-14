package team.Spinalireland;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class Tempcntrlfeet extends Activity implements OnClickListener  {
	
	int tempvalue=0;
	Switch feetswitch;
	Button uparrow;
	Button downarrow;
	boolean temperature=false;// signifies off
	Toast toast;
	
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	      
	        if(savedInstanceState!=null){
	        // set listeners to the buttons
	        
	        tempvalue = savedInstanceState.getInt("MyInt");
	        Log.v("Hi", "in "+ tempvalue);
	        
	        }
	        
	        setContentView(R.layout.temp_ctrl_feet);
	     
	        feetswitch=(Switch)findViewById(R.id.feet_switch);
	        downarrow=(Button)findViewById(R.id.feet_downarrow);
	        uparrow=(Button)findViewById(R.id.feet_uparrow);
	        feetswitch.setOnClickListener(this);
	        uparrow.setOnClickListener(this);
	        downarrow.setOnClickListener(this);
	        Log.v("Hi", "on create "+ tempvalue);
	    }
	@SuppressLint("CutPasteId") @Override
	public void onClick(View v) {
	switch(v.getId()){
		
		
		case R.id.feet_switch:
			 
		Log.v("Hi", "in switch "+ tempvalue);
			ImageView img= (ImageView) findViewById(R.id.feet_imageView);
			
			if(feetswitch.isChecked())
				img.setImageResource(R.drawable.on);
			else{
				img.setImageResource(R.drawable.off);
				tempvalue=0;
				
			}
			break;
				
		
			
		case R.id.feet_uparrow:
			ImageView imgss= (ImageView) findViewById(R.id.feet_imageView);
			if(feetswitch.isChecked()){
			 if(tempvalue<5 && tempvalue>=0){
				 tempvalue++;
				 displayAprropriateImage(tempvalue,imgss);
				 
				 
			 }
			 else{
				  toast =Toast.makeText(getApplicationContext(), 
	                     "The maximum temperature you can increment is 5", Toast.LENGTH_LONG);
				 toast.setGravity(Gravity.TOP|Gravity.LEFT, 0,380);
				 toast.show();

			 }
			 
			
			}
		
				else
				 {
					  toast =Toast.makeText(getApplicationContext(), 
			                     "You can't increment when the switch button is off", Toast.LENGTH_LONG);
						 toast.setGravity(Gravity.TOP|Gravity.LEFT, 0,380);
						 toast.show();

				 }
			break;
			
		case R.id.feet_downarrow:
	
		
			
			ImageView imgs= (ImageView) findViewById(R.id.feet_imageView);
			if(feetswitch.isChecked()){
			 if(tempvalue>0){
				 tempvalue--;
			 displayAprropriateImage(tempvalue,imgs);
			 }
			 else{
				  toast =Toast.makeText(getApplicationContext(), 
		                     "You can't decrement any further", Toast.LENGTH_LONG);
					 toast.setGravity(Gravity.TOP|Gravity.LEFT, 0,380);
					 toast.show();
			 }
			}
			else
			 {
				  toast =Toast.makeText(getApplicationContext(), 
		                     "You can't decrement when the switch button is off", Toast.LENGTH_LONG);
					 toast.setGravity(Gravity.TOP|Gravity.LEFT, 0,380);
					 toast.show();

			 }
			break;
		}
	}

	
	private void displayAprropriateImage(int value,ImageView img){
		
		switch(value){
	
		case 1:
			
			img.setImageResource(R.drawable.on_one);
			break;
		
		case 2:
			img.setImageResource(R.drawable.on_two);
			break;
		
		case 3:
			img.setImageResource(R.drawable.on_three);
			break;
		
		case 4:
			img.setImageResource(R.drawable.on_four);
			break;
		case 5:
			img.setImageResource(R.drawable.on_five);
			break;
		}
		
		
		
	}
	
	@Override
	public void onBackPressed()
	{
	     // code here to show dialog
	     super.onBackPressed();  // optional depending on your needs
	     Intent intent = new Intent(this, MainActivity.class);
	     intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
	     startActivity(intent);
	     Log.v("Hi", "backpressed "+ tempvalue);
	     
	     
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
	  // Save UI state changes to the savedInstanceState.
	  // This bundle will be passed to onCreate if the process is
	  // killed and restarted.
	
	  savedInstanceState.putInt("MyInt", tempvalue);
	  Log.v("Hi", "save instance "+ tempvalue);
	  // etc.
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  // Restore UI state from the savedInstanceState.
	  // This bundle has also been passed to onCreate.
	
	  tempvalue = savedInstanceState.getInt("MyInt");
	  Log.v("Hi", "restore instance "+ tempvalue);
	     
	}
	@Override
	public void onPause(){
		super.onPause();
		Log.v("Hi", " I am on Paused");
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	      Editor editor = sharedPreferences.edit();
				         editor.putInt("Tempfeet",tempvalue);
				         if(tempvalue>0)
				         editor.putBoolean("switch",true);
				         else
				        	editor.putBoolean("switch",false); 
				         editor.commit();

	}
	
	/*
	@Override
	public void onStop(){
		super.onStop();
		Log.v("Hi", " I am on stop");
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	      Editor editor = sharedPreferences.edit();
				         editor.putInt("Temp",0);
				         editor.commit();

	}
	
	

	*/
	
	@Override
	public void onResume(){
		super.onResume();
		 SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
	     tempvalue = sharedPreferences.getInt("Tempfeet", 0);
	   //  boolean turnedoff_on = sharedPreferences.getBoolean("switch", false);
	     Log.v("Hi", "Resume is " +tempvalue );
	     
	   
	     
	     if(tempvalue==1){
		        ImageView img= (ImageView) findViewById(R.id.feet_imageView);
		    	img.setImageResource(R.drawable.on_one);
		    	  feetswitch.setChecked(true);
		    	
		       }
		       if(tempvalue==2){
			        ImageView img= (ImageView) findViewById(R.id.feet_imageView);
			    	img.setImageResource(R.drawable.on_two);
			    	  feetswitch.setChecked(true);
			       }
		       if(tempvalue==3){
			        ImageView img= (ImageView) findViewById(R.id.feet_imageView);
			    	img.setImageResource(R.drawable.on_three);
			    	  feetswitch.setChecked(true);
			       }
		       if(tempvalue==4){
			        ImageView img= (ImageView) findViewById(R.id.feet_imageView);
			    	img.setImageResource(R.drawable.on_four);
			    	  feetswitch.setChecked(true);
			       }
		       if(tempvalue==5){
			        ImageView img= (ImageView) findViewById(R.id.feet_imageView);
			    	img.setImageResource(R.drawable.on_five);
			    	  feetswitch.setChecked(true);
			       }

	}
	
}
