/**
 * Copyright(c) 2013 ANURAG 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * anurag.dev1512@gmail.com
 *
 */
package org.anurag.file.quest;



import android.annotation.SuppressLint;
import net.lingala.zip4j.core.*;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DialogBox extends Activity implements OnClickListener{
	private TextView tv;
	private TextView iTv;
	private ImageView iView;
	private Intent intent;
	private String data;
	private Button b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		WindowManager w = getWindowManager();
		Point size = new Point();
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			w.getDefaultDisplay().getSize(size);
			//params.alpha = 0.8f;
			params.height = size.y*4/5;
			params.width = size.x*4/5;
		}
		setContentView(R.layout.popup_dialog);
		intent = getIntent();
		data = intent.getData().toString();
		tv = (TextView)findViewById(R.id.textMessage);
		iView = (ImageView)findViewById(R.id.popupImage);
		iTv = (TextView)findViewById(R.id.popupTitle);
		
		if(data.equals("FlashableZips")||data.equalsIgnoreCase("FlashableZip")){
			iTv.setText("Flashable Zip");
			iView.setBackgroundResource(R.drawable.ic_launcher_zip_it);
			tv.setText("It Will Create A Unsigned Flashable Zip That is Usable Only In Recovery" +
					" Mode With A Custom Rom");	
		}else if(data.equals("CopyToNextPanel")){
			iTv.setText("Copy");
			iView.setBackgroundResource(R.drawable.ic_launcher_file_task);
			tv.setText("Copy The File To Next Panel");
			params.height = size.y*2/6;
		}else if(data.equals("CutFile")){
			params.height = size.y*2/6;
			iTv.setText("Cut");
			iView.setBackgroundResource(R.drawable.ic_launcher_file_task);
			tv.setText("Remove File To Next Panel");
		}else if(data.equalsIgnoreCase("homeError")){
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  Home Directory Not Found");
			iView.setBackgroundResource(R.drawable.ic_launcher_droid_home);
			tv.setText("It Seems That The Defined Path For Home Directory Deosnot " +
					"Exists,Setting Home Directory To Default Location /sdcard,You" +
					" Can Change The Default Location");
			b= (Button)findViewById(R.id.popupOk);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					DialogBox.this.finish();
				}
			});
		}else if(data.equalsIgnoreCase("renameError")){
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  Cannot Rename File");
			iView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_rename));
			tv.setText("It Seems That You Are Trying To Rename The One Of The" +
					" Root Directories Or File,If Not Then Because Of I/O Error Cannot Rename " +
					" File,Please Try Again Later ");
			b= (Button)findViewById(R.id.popupOk);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					DialogBox.this.finish();
				}
			});
		}else if(data.contains("Null")){
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  Cannot Copy File");
			iView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_file_task));
			tv.setText("It Seems That You Are Trying To Copy One Of The" +
					" Root Directories Or File And NULL POINTER EXCEPTION Was Encountered");
			b= (Button)findViewById(R.id.popupOk);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					DialogBox.this.finish();
				}
			});
		}else if(data.contains("NotFound")){
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  No Activity Found Error");
			iView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_file_task));
			tv.setText("Currently There Is No App Installed On This Device That " +
					"Can Handle This Type Of File");
			b= (Button)findViewById(R.id.popupOk);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					DialogBox.this.finish();
				}
			});
		}
		
		else if(data.contains("unsupportedScreenSize")){
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  UnSupported Screen Size");
			iView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_file_task));
			tv.setText("Currently This App Is Not Available For Screen Resolution " +
					size+" ,Please Stay Tuned For This Fix For Best Experience");
			b= (Button)findViewById(R.id.popupOk);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					setResult(RESULT_OK);
					DialogBox.this.finish();
				}
			});
		}
		
		else if(data.contains("Security")){
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  Security Exception");
			iView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_file_task));
			tv.setText("A Security Exception Was Encountered While Sending The Data To " +
					"The Selected App,Please Try Other Apps For The Same File Type");
			b= (Button)findViewById(R.id.popupOk);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					setResult(RESULT_OK);
					DialogBox.this.finish();
				}
			});
		}else if(data.contains("cloud")){
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  Cloud Storage");
			iView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_ubuntu_one));
			tv.setText("This feature will arrive shortly,these features are in development stage. " +
					"So till then stay tuned to FILE QUEST");
			b= (Button)findViewById(R.id.popupOk);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					setResult(RESULT_OK);
					DialogBox.this.finish();
				}
			});
		}
		
		else if(data.contains("BetaStage")){
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  Development Stage");
			iTv.setTextColor(Color.RED);
			iTv.setTextSize(20.0f);
			String s = "anurag";
			iView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_message));
			tv.setText("IF YOU FOUND ANY COPYRIGHTED CONTENT OR " +
							"RESOURCES THAT IS USED IN THIS APP,PLEASE INFORM ME TO MY GIVEN CONTACT ADDRESS ");
			tv.setTextColor(Color.GREEN);
			b= (Button)findViewById(R.id.popupOk);
			b.setTextColor(Color.YELLOW);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					DialogBox.this.finish();
				}
			});
		}
		
		else{
			b = (Button)findViewById(R.id.popupCancel);
			b.setVisibility(View.GONE);
			iTv.setText("  Cannot Copy File");
			iView.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_file_task));
			tv.setText("Because Of The Unknown Error Cannot Copy File" +
					" Please Try Again Later");
			b= (Button)findViewById(R.id.popupOk);
			b.setText("Ok");
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					DialogBox.this.finish();
				}
			});
		}
		
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.popupOk:
				setResult(1);
				finish();
				break;
			case R.id.popupCancel:
				if(data.equals("CopyToNextPanel") || data.equalsIgnoreCase("CutFile"))
					setResult(10);
				else
					setResult(-1);
				finish();
		}
	}

}
