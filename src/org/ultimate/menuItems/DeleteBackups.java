package org.ultimate.menuItems;

import java.io.File;


import org.anurag.file.quest.R;
import org.anurag.file.quest.RFileManager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
public class DeleteBackups extends Activity{

	private File file;
	private ProgressBar bar;
	private TextView popupTitle;
	private TextView popupMessage;
	private Button btn1;
	private Button btn2;
	private RFileManager  mManager;
	private Thread thread;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		WindowManager w = getWindowManager();
		Point size = new Point();
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			w.getDefaultDisplay().getSize(size);
			//params.alpha = 0.8f;
			params.height = size.y*1/3;
			params.width = size.x*4/5;
		}
		setContentView(R.layout.popup_dialog);
		String PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
		btn1 = (Button)findViewById(R.id.popupOk);
		ImageView v = (ImageView)findViewById(R.id.popupImage);
		v.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_move));
		btn2 = (Button)findViewById(R.id.popupCancel);
		popupTitle = (TextView)findViewById(R.id.popupTitle);
		bar = (ProgressBar)findViewById(R.id.popupProgress);
		popupMessage = (TextView)findViewById(R.id.textMessage);
		file = new File(PATH +  "/File Quest-Beta");
		if(file.exists()){
			file = new File(PATH + "/File Quest-Beta/AppBackup");
			if(file.exists() && file.listFiles().length !=0){
				popupTitle.setText("Confirm Deletion");
				popupMessage.setText("All Your Earlier Backup Of Installed " +
						"Apps Will Be Deleted,Are You Sure To Delete Them");
				final Handler mHandler = new Handler(){
					public void handleMessage(Message msg){
						switch(msg.what){
							case 0 :
								popupMessage.setText("Please Wait While Deleting File");
								bar.setVisibility(View.VISIBLE);
								btn1.setVisibility(View.GONE);
								btn2.setVisibility(View.GONE);
								break;
							case 2 :  	
								btn1.setVisibility(View.VISIBLE);
								btn1.setText("Ok");
								btn2.setVisibility(View.GONE);
								bar.setVisibility(View.GONE);
								popupMessage.setText("All Earlier Backup Of Apps Have Been Deleted");
								setResult(RESULT_OK);
								
						}
					}
				};
				thread = new Thread(new Runnable() {
					@Override
					public void run() {
						mManager.deleteTarget(file);
						mHandler.sendEmptyMessage(2);
					}
				});
				mManager = new RFileManager(mHandler);
				btn1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if(btn2.getVisibility() == View.GONE){
							thread.interrupt();
							DeleteBackups.this.finish();
						}else
							thread.start();
					}
				});
				btn2.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						setResult(-1);
						finish();				
					}
				});
			}
			else{
				popupTitle.setText("Messsge");
				popupMessage.setText("Unable To Locate Earlier Backups,Probably" +
						" No Backup Was Taken");
				btn2.setVisibility(View.GONE);
				btn1.setText("Ok");
				btn1.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						finish();
					}
				});
			}
		}
		else{
			popupTitle.setText("Messsge");
			popupMessage.setText("Unable To Locate Earlier Backups,Probably" +
					" No Backup Was Taken");
			btn2.setVisibility(View.GONE);
			btn1.setText("Ok");
			btn1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		}
		
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(thread.isAlive())
				Toast.makeText(getApplicationContext(), "Please Wait While Deletion Completes", Toast.LENGTH_SHORT).show();
			else 
				finish();
		}
			
		return false;
	}
	

	
	
}
