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


import java.io.File;

import java.io.FileNotFoundException;



import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.Zip4jConstants;

import android.app.Activity;

import android.content.Intent;

import android.graphics.Point;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
;

public class ZipDialog extends Activity implements OnClickListener{
	private static ProgressMonitor mo = null;
	private ZipFile zip;
	private static File file;
	private String ZIP_INITIALIZE ;
	private static CheckBox zipOption;
	private static CheckBox tarOption;
	private Intent intent;
	private TextView zipBoxTitle;
	private TextView zipFormat;
	private static TextView zipNoOfFiles;
	private static TextView zipFileLocation;
	private static TextView zipFileName;
	private String type;
	private static Thread t;
	private LinearLayout lv;
	private AppManager nManager;
	private static ProgressBar zipProgressBar;
	private static int no = 0;
	private static int count = 0;
	private static Button zipOkBtn;
	private static Button zipCancelBtn;
	private int flashType = 1;
	private static Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
				case 0:
					zipOkBtn.setEnabled(false);
					zipCancelBtn.setEnabled(false);
					zipOption.setEnabled(false);
					tarOption.setEnabled(false);
					zipProgressBar.setVisibility(View.VISIBLE);
					zipNoOfFiles.setText(++count + " Items Packed Out Of " + no);
					zipFileName.setText("Currently Packing :- " + msg.obj);
					break;
				case 1:	
					zipNoOfFiles.setText("All Items Packed ");
					zipFileName.setText(" Adding Script ,Please wait");
					zipProgressBar.setVisibility(View.VISIBLE);
					zipCancelBtn.setEnabled(false);
					zipOkBtn.setEnabled(false);
					zipOption.setEnabled(false);
					tarOption.setEnabled(false);
					break;
				case 2://case 2 when flashable zip file is created successfully	
					zipFileName.setText("Flashable Zip Is Created");
					zipProgressBar.setVisibility(View.GONE);
					zipOkBtn.setVisibility(View.GONE);
					zipCancelBtn.setEnabled(true);
					zipCancelBtn.setText("Finish");
					t = null;
					break;
				
				case 3:
					zipOption.setEnabled(false);
					tarOption.setEnabled(false);
					zipOkBtn.setEnabled(false);
					zipCancelBtn.setClickable(false);
					//WHEN ZIPPING IS IN PROGRESS SETS THE UI WITH CURRENT STATUS 
					if(mo.getState() != ProgressMonitor.STATE_READY){
						zipProgressBar.setVisibility(View.VISIBLE);
						zipNoOfFiles.setText("Please Wait While Zipping");
						zipFileLocation.setText("Zipped File Name : " + file.getName() + ".zip");
						mHandler.sendEmptyMessage(3);
					}else 
						mHandler.sendEmptyMessage(4);
					break;
				case 4:
					//zipOkBtn.setEnabled(true);
					zipOkBtn.setVisibility(View.GONE);
					zipCancelBtn.setVisibility(View.VISIBLE);
					zipCancelBtn.setClickable(true);
					zipCancelBtn.setText("Finish");
					// AFTER ZIPPING SETS THE UI WITH THE REQUIRED STATS
					if(mo.getResult() == ProgressMonitor.RESULT_ERROR){
						zipNoOfFiles.setText("There Was An Error While Zipping");
						zipFileLocation.setText("Unable To Create File : " + file.getName() + ".zip");
					}else
						zipNoOfFiles.setText("Zipping Operation Was Successful");
					zipProgressBar.setVisibility(View.GONE);
					mHandler.sendEmptyMessage(5);
					break;
				
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		WindowManager w = getWindowManager();
		Point p = new Point();
		w.getDefaultDisplay().getSize(p);
		params.width = p.x*7/9;
		params.height = p.y*3/5;
		setContentView(R.layout.zip_file_dialog);
		intent = getIntent();
		
		
		nManager= new AppManager(getApplicationContext(), mHandler);
		type = intent.getData().toString();
		zipBoxTitle = (TextView)findViewById(R.id.zipFileTitle);
		zipNoOfFiles = (TextView)findViewById(R.id.zipNoOfFiles);
		zipFileLocation = (TextView)findViewById(R.id.zipFileLocation);
		zipProgressBar = (ProgressBar)findViewById(R.id.zipProgressBar);
		zipFileName = (TextView)findViewById(R.id.zipFileName);
		lv = (LinearLayout)findViewById(R.id.zipDeleteCheckLayout);
		zipFormat = (TextView)findViewById(R.id.zipFormat);
		zipOption = (CheckBox)findViewById(R.id.zipChioce);
		tarOption = (CheckBox)findViewById(R.id.tarChioce);
		zipOkBtn = (Button)findViewById(R.id.zipOkButton);
		zipCancelBtn = (Button)findViewById(R.id.zipCalcelButton);
		
		if(type.equalsIgnoreCase("FlashableZips")){
			//type = FlashableZips if all apps has to be packed in flashable zip
			//type = FlashableZip if only one app have to be packed in flashable zip
			zipBoxTitle.setText("Flashable Zip");
			zipNoOfFiles.setText("Total Apps " + (no=nManager.get_downloaded_apps().size()));
			zipFileLocation.setText("Location :- /sdcard/Flashable.zip");
			lv.setVisibility(View.GONE);
			zipFormat.setText("Flashable Zip Will Install App as");
			zipOption.setText("User Application");
			tarOption.setText("System Application");
			
			zipOption.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					if(arg1){
						tarOption.setChecked(false);
						flashType = 1;
					}else{
						tarOption.setChecked(true);
						flashType = 2;
					}
						
				}
			});
			tarOption.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					if(arg1){
						zipOption.setChecked(false);
						flashType = 2;
					}else{
						zipOption.setChecked(true);
						flashType = 1;
					}
				}
			});
		}else if(type.equals("createZip")){
			// REARRANGE THE UI FOR ZIPPING FILES OR FOLDER
			String[] zipDetails = intent.getStringArrayExtra("zipDetails");
			file = new File(zipDetails[1]);
			ZIP_INITIALIZE = zipDetails[0] + "/" + file.getName() + ".zip";
			zipFileName.setText("Zipped File Location : " + ZIP_INITIALIZE);
			lv.setVisibility(View.VISIBLE);
			zipNoOfFiles.setText("Ready To Start Zipping Process");
			zipFileLocation.setText("Zipped File Name : " + file.getName() + ".zip");
			zipFormat.setText("Set Compression Level");
			zipOption.setText("Normal");
			tarOption.setText("Maximum");

			zipOption.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					if(arg1)
						tarOption.setChecked(false);
					else
						tarOption.setChecked(true);
						
				}
			});
			tarOption.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					if(arg1)
						zipOption.setChecked(false);
					else
						zipOption.setChecked(true);
				}
			});
		}else{
			//type = FlashableZips if all apps has to be packed in flashable zip
			//type = FlashableZip if only one app have to be packed in flashable zip
			zipBoxTitle.setText("Flashable Zip");
			zipNoOfFiles.setText("One App To Be Packed");
			zipFileLocation.setText("Location :- /sdcard/Flashable.zip");
			lv.setVisibility(View.GONE);
			zipFormat.setText("Flashable Zip Will Install App as");
			zipOption.setText("User Application");
			tarOption.setText("System Application");
			zipOption.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					if(arg1){
						tarOption.setChecked(false);
						flashType = 1;
					}else{
						tarOption.setChecked(true);
						flashType = 2;
					}
				}
			});
			tarOption.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
					if(arg1){
						zipOption.setChecked(false);
						flashType = 2;
					}else{
						zipOption.setChecked(true);
						flashType = 1;
					}
				}
			});
		}
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()){
			case R.id.zipOkButton:
				try {
					if(type.equalsIgnoreCase("FlashableZips")){
						t = new Thread(nManager.new FlashableZips(getApplicationContext() , flashType));
						t.start();
					}else if(type.equalsIgnoreCase("createZip")){
						// INITIALIZE THE ZIP VARIABLES AS INTENT HAS GOT REQUEST FOR ZIPPING THE FILE
						try {
							if(new File(ZIP_INITIALIZE).exists())
								new File(ZIP_INITIALIZE).delete();
							zip = new ZipFile(ZIP_INITIALIZE);
							zip.setRunInThread(true);
							ZipParameters parameters = new ZipParameters();
							parameters.setCompressionMethod(Zip4jConstants.COMP_STORE); 
							// SETS THE COMPRESSION LEVEL AS OPTED BY USER
							if(zipOption.isChecked())
								parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
							else
								parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_MAXIMUM);
							if(file.isDirectory())
								zip.addFolder(file, parameters);
							else if(file.isFile())							
								zip.addFile(file, parameters);
							mo = zip.getProgressMonitor();
							mHandler.sendEmptyMessage(3);
						} catch (ZipException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}catch(NullPointerException e){
							zipOkBtn.setEnabled(false);
							zipCancelBtn.setEnabled(true);
							zipCancelBtn.setText("Ok");
							tarOption.setEnabled(false);
							zipOption.setEnabled(false);
							zipOkBtn.setVisibility(View.GONE);
							zipNoOfFiles.setText("There Was An Error While Zipping");
							zipFileLocation.setText("Unable To Create File : " + file.getName() + ".zip");
							zipNoOfFiles.setText("Zipping Operation Was Successful");
							zipProgressBar.setVisibility(View.GONE);
						}
					}else{
						t = new Thread(nManager.new FlashableZip(type, getApplicationContext(), flashType));
						t.start();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				break;
				
			case R.id.zipCalcelButton:
				if(zipOkBtn.isEnabled())
					this.finish();
				else{
					setResult(RESULT_OK);
					this.finish();
				}
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(t != null)
				Toast.makeText(getApplicationContext(), "Please Wait Till Zipping Completes",
						Toast.LENGTH_SHORT).show();
			else if(mo == null){
				ZipDialog.this.finish();
			}				
			else if(mo.getState() != ProgressMonitor.STATE_READY)
				Toast.makeText(getApplicationContext(), "Please Wait Till Zipping Completes",
						Toast.LENGTH_SHORT).show();
			else if(mo.getState() == ProgressMonitor.STATE_READY){
				setResult(RESULT_OK);
				ZipDialog.this.finish();
			}	
		}
		return false;
	}
	
	
	
	
	
}
