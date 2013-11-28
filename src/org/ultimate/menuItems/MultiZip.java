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

package org.ultimate.menuItems;

import java.io.File;
import java.util.ArrayList;
import org.anurag.file.quest.R;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MultiZip extends Activity{

	ZipFile zip;
	ArrayList<? extends Parcelable> list;
	ProgressBar bar;
	Intent intent;
	String DEST;
	CheckBox normal;
	CheckBox max;
	TextView level;
	Button st,ca;
	boolean running;
	TextView name;
	TextView lo;
	TextView toFiles;
 	@Override
	protected void onCreate(Bundle onSavedState){
		super.onCreate(onSavedState);
		setContentView(R.layout.zip_file_dialog);
		
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		WindowManager w = getWindowManager();
		Point p = new Point();
		w.getDefaultDisplay().getSize(p);
		params.width = p.x*7/9;
		params.height = p.y*3/5;		
		
		intent = getIntent();
		level = (TextView)findViewById(R.id.zipFormat); 
		normal = (CheckBox)findViewById(R.id.zipChioce);
		max = (CheckBox)findViewById(R.id.tarChioce); 
		bar = (ProgressBar)findViewById(R.id.zipProgressBar);
		list = intent.getParcelableArrayListExtra("list");
		st = (Button)findViewById(R.id.zipOkButton);
		ca = (Button)findViewById(R.id.zipCalcelButton);
		running = false;
		name = (TextView)findViewById(R.id.zipFileName);
		lo = (TextView)findViewById(R.id.zipFileLocation); 
		toFiles = (TextView)findViewById(R.id.zipNoOfFiles);
		
		
		toFiles.setText("TOTAL FILES : "+ intent.getStringExtra("size"));
		lo.setText("LOCATION : "+intent.getAction());
		name.setText("ARCHIVE NAME : Archive.zip");
		DEST = intent.getAction()+"/Archive.zip";
		normal.setText("Normal");
		max.setText("Max");
		level.setText("COMPRESSION");
		normal.setChecked(true);
		max.setChecked(false);
		
		
		normal.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				normal.setChecked(true);
				max.setChecked(false);
			}
		});
		
		max.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				normal.setChecked(false);
				max.setChecked(true);
			}
		});
		st.setText("Start");		
		st.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				normal.setEnabled(false);
				max.setEnabled(false);
				ca.setEnabled(false);
				st.setEnabled(false);
				running = true;
				new ZipFiles().execute();				
			}
		});
		ca.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				MultiZip.this.finish();
			}
		});
		
	}
	
	private class ZipFiles extends AsyncTask<Void, String, Void>{
			@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			setResult(RESULT_OK);
			MultiZip.this.finish();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			bar.setVisibility(View.VISIBLE);
		}

		@Override
		protected void onProgressUpdate(String... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			lo.setText(values[0]);
			toFiles.setText(values[1]);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			File file;
			int j = 0 , c = 0;
			int l = list.size();
			for(int i = 0 ; i <l ; ++i){
				file = (File) list.get(i);
				if(file!=null)
					c++;
			}
			if(new File(DEST).exists())
				new File(DEST).delete();
			ZipParameters para = new ZipParameters();
			if(normal.isChecked())
				para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			else if(max.isChecked())
				para.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_MAXIMUM);
			try{
				zip = new ZipFile(DEST);
				for(int i = 0 ; i<l;++i){
					file = (File)list.get(i);
					if(file!=null){
						j++;
						String[] res = {"Zipping : " + file.getName(),j-1 + " of " + c + " Zipped"};
						publishProgress(res);
						if(file.isDirectory())
							zip.addFolder(file, para);
						else if(file.isFile())
							zip.addFile(file, para);
					}
				}
			}catch(Exception e){
				
			}
			return null;
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			if(running)
				Toast.makeText(getBaseContext(), "Please wait till zipping completes",
						Toast.LENGTH_SHORT).show();
		}
		return false;
	}
	
	
}
