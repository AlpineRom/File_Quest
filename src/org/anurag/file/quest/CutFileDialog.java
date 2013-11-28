
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



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.StatFs;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

@SuppressLint("NewApi")
public class CutFileDialog extends Activity{

	private static TextView copyTo;
	private static TextView copyFrom;
	private static TextView fileSize;
	private static TextView timeLeft;
	private static TextView current;
	private static Thread thread;
	private static File d;
	private static RFileManager manager;
	private static ProgressBar bar;
	private Button ok;
	private WindowManager.LayoutParams params;
	private Point size;
	private Button can;
	private Handler mHandler = new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			    case 1:
					// CASE 1 IS FOR SHOWING WHICH FILE IS BEING COPIED
			    	ok.setVisibility(View.GONE);
			    	current.setText("Copying File :-" +  msg.obj);
			    	StatFs f = new StatFs(Environment.getExternalStorageDirectory().getPath());
			    	long len = f.getAvailableBlocks()*f.getBlockSize();
					String form = null;
					if(len >=1024*1024*1024)
						form = String.format("Space Available :- %.2f GB", (double)len/(1024*1024*1024));
					else if(len>=1024*1024)
						form = String.format("Space Available :- %.2f MB", (double)len/(1024*1024));
					else if(len>=1024)
						form = String.format("Space Available :- %.2f KB", (double)len/(1024));
					else 
						form = String.format("Space Available :- %.2f Bytes", (double)len);
			    	timeLeft.setText(form);
					break;
			    case 2:
			    	manager = new RFileManager();
					manager.deleteTargetForCut(d);
					mHandler.sendEmptyMessage(3);
					break;
			    case 3:
					
					setResult(70);
					if(thread !=null )
						thread.interrupt();
					mHandler.removeMessages(3);
					mHandler.removeMessages(1);
					thread = null;
					finish();
					break;
				case 5:
					// THIS CASE VALID ONLY WHEN EXCEPTIONS OCCURS WHILE COPYING
					setResult(100, new Intent().setData(Uri.parse(msg.obj.toString())));
					if(thread !=null)
						thread.interrupt();
					thread = null;
					manager.mHandler = null;
					CutFileDialog.this.finish();
					break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		params = this.getWindow().getAttributes();
		WindowManager w = getWindowManager();
		size = new Point();
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			w.getDefaultDisplay().getSize(size);
			//params.alpha = 0.8f;
			params.height = size.y*3/5;
			params.width = size.x*4/5;
		}
		setContentView(R.layout.copy_dialog);
		
		ok=(Button)findViewById(R.id.copyOk);
		can = (Button)findViewById(R.id.copyCancel);
		manager = new RFileManager(mHandler);
		bar = (ProgressBar)findViewById(R.id.copyBar);
		copyTo = (TextView)findViewById(R.id.copyTo);
		copyFrom = (TextView)findViewById(R.id.copyFrom);
		fileSize = (TextView)findViewById(R.id.copyFileSize);
		timeLeft = (TextView)findViewById(R.id.timeLeft);
		current = (TextView)findViewById(R.id.currentFile);
		TextView tv = (TextView)findViewById(R.id.header);
		tv.setText("Cutting File");
		
		final String[] paths = getIntent().getStringArrayExtra("path");
		// size of paths is 3 if cut option is selected
		File[] file = new File(paths[1]).listFiles();
		File nw = new File(paths[0]);
		int flag = 0;
		for( int i = 0 ; i<file.length ; ++i){
			d = file[i];
			if(d.getName().equals(nw.getName())){
				flag = 1;
				break;
			}
		}
		if(flag == 0){
			ok.setVisibility(View.GONE);
			thread = new Thread(new Runnable() {
				@Override
				public void run() {
					copyTo.setText("Copying to:- " + paths[1]);
					copyFrom.setText("Copying From :- " + paths[0]);
					File f = d = new File(paths[0]);
					long len = f.length();
					String form = null;
					if(len >=1024*1024*1024)
						form = String.format("File Size :- %.2f GB", (double)len/(1024*1024*1024));
					else if(len>=1024*1024)
						form = String.format("File Size :- %.2f MB", (double)len/(1024*1024));
					else if(len>=1024)
						form = String.format("File Size :- %.2f KB", (double)len/(1024));
					else 
						form = String.format("File Size :- %.2f Bytes", (double)len);
					fileSize.setText(form);
					manager.copyToDirectory(paths[0], paths[1]);
					mHandler.sendEmptyMessage(2);
				}
			});
			thread.start();
		}else if(flag == 1){
			params.height = size.y*2/6;
			copyFrom.setVisibility(View.GONE);
			fileSize.setVisibility(View.GONE);
			current.setVisibility(View.GONE);
			timeLeft.setVisibility(View.GONE); 
			bar.setVisibility(View.GONE);
			can.setText("Dont Copy");
			copyTo.setSingleLine(false);
			copyTo.setText("A File With Same Name Already Exists,Please Select " +
					"An Appropriate Option");
			can.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					finish();
				}
			});
			ok.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					bar.setVisibility(View.VISIBLE);
					manager.deleteTarget(d);
					bar.setVisibility(View.GONE);
					setResult(80);
					finish();
				}
			});
		}
		can.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				// TODO Auto-generated method stub
				if(thread !=null)
					thread.interrupt();
				thread = null;
				manager.mHandler = null;
				setResult(90);
				CutFileDialog.this.finish();
			}
		});
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
