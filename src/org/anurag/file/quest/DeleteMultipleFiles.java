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

import java.io.IOException;
import java.util.ArrayList;

import org.ultimate.root.RootUtils;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class DeleteMultipleFiles extends Activity implements OnClickListener{

	private ArrayList<?extends Parcelable> list;
	private ProgressBar bar;
	private TextView popupTitle;
	private TextView popupMessage;
	private Button btn1;
	private Button btn2;
	private Intent intent;
	String ACTION;
	
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
			params.height = size.y*2/3;
			params.width = size.x*4/5;
		}
		setContentView(R.layout.delete_multiple_files);
		ImageView v = (ImageView)findViewById(R.id.popupImage);
		v.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_delete));
		intent = getIntent();
		list = intent.getParcelableArrayListExtra("list");
		btn1 = (Button)findViewById(R.id.popupOk);
		btn2 = (Button)findViewById(R.id.popupCancel);
		popupTitle = (TextView)findViewById(R.id.popupTitle);
		
		ACTION = intent.getAction();
		bar = (ProgressBar)findViewById(R.id.popupProgress);
		popupMessage = (TextView)findViewById(R.id.textMessage);
		if(ACTION.equals("root")){
			popupTitle.setText("Confirm Deletion At Root");
			popupMessage.setText("Are You Sure to Delete The Folders and Files :" +
					" You are trying to delete system files,this will surely effect your " +
					"device.....");
		}else{
			popupTitle.setText("Confirm Deletion");
			popupMessage.setText("Are You Sure to Delete The Folders and Files ");
		}
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			DeleteMultipleFiles.this.finish();
		}
			
		return false;
	}
	class Delete extends AsyncTask<Void, Void, Void>{
		int i , l = list.size();		
		File file;
		public Delete(){
			
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			setResult(RESULT_OK);
			finish();
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			bar.setVisibility(View.VISIBLE);
			popupMessage.setText("Please wait while Deleting Folders and files");
			btn1.setVisibility(View.GONE);
			btn2.setVisibility(View.GONE);
		}
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			for(i = 0 ; i < l ; ++i){
				file = (File) list.get(i);
				try{
					if(file!=null){
						if(ACTION.equals("root")){
							RootUtils.DeleteFileRoot(file.getPath());
						}
						else
							deleteTarget(file);
					}	
				}catch(Exception e){
					
				}
			}
			return null;
		}
		
	}

	/**
	 * Function To Delete The Given File And Returns Message To Handler
	 * If Deletion is successful returns 0 else returns -1
	 * @param path
	 * @return
	 */
	public void deleteTarget(File file) {
		File target = file;
		if(target.exists() && target.isFile() && target.canWrite())
			target.delete();
		
		else if(target.exists() && target.isDirectory() && target.canRead()) {
			String[] file_list = target.list();
			
			if(file_list != null && file_list.length == 0) {
				target.delete();
			} else if(file_list != null && file_list.length > 0) {
				
				for(int i = 0; i < file_list.length; i++) {
					File temp_f = new File(target.getAbsolutePath() + "/" + file_list[i]);
					if(temp_f.isDirectory())
						deleteTarget(temp_f);
					else if(temp_f.isFile())
						temp_f.delete();
				}
			}
			if(target.exists())
				if(target.delete()){}
		}
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
				case R.id.popupOk:
									new Delete().execute();
									//Toast.makeText(getBaseContext(), ""+list.size(), Toast.LENGTH_SHORT).show();
									break;
				case R.id.popupCancel:
									DeleteMultipleFiles.this.finish();
									break;
		}
	}
	
}
