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
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.anurag.file.quest.R;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MultipleCopy extends Activity {
	
	String ACTION;
	ArrayList<?extends Parcelable> getlist;
	Intent getIntent;
	COPY copy;
	int BUFFER = 256;
	int len;
	String DEST;
	long si;
	private WindowManager.LayoutParams params;
	private Point size;
	Button btn1,btn2;
	TextView copyTo;
	TextView copyFrom;
	TextView copying;
	TextView contentSize;
	TextView time;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.copy_dialog);
		
		params = this.getWindow().getAttributes();
		WindowManager w = getWindowManager();
		size = new Point();
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			w.getDefaultDisplay().getSize(size);
			//params.alpha = 0.8f;
			params.height = size.y*3/5;
			params.width = size.x*4/5;
		}
		si = 0;
		getIntent = getIntent();
		DEST = getIntent.getAction();
		getlist = getIntent.getParcelableArrayListExtra("list");
		len = getlist.size();
		btn1 = (Button)findViewById(R.id.copyOk);
		btn2 = (Button)findViewById(R.id.copyCancel);
		btn1.setVisibility(View.GONE);
		copyTo = (TextView)findViewById(R.id.copyTo);
		copyFrom = (TextView)findViewById(R.id.copyFrom);
		copying  = (TextView)findViewById(R.id.currentFile);
		ACTION = getIntent.getStringExtra("cut");
		contentSize = (TextView)findViewById(R.id.copyFileSize);
		time = (TextView)findViewById(R.id.timeLeft);
				
		copyTo.setText("Copying To :-"+DEST);
		if(getIntent !=null && DEST!=null){
			copy = new COPY();
			copy.execute();
		}else{
			Toast.makeText(getBaseContext(), "An Error occured while copying", Toast.LENGTH_SHORT).show();
			MultipleCopy.this.finish();
		}
		btn2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				copy.cancel(true);
				MultipleCopy.this.finish();
			}
		});
	}
	
	/**
	 * 
	 * @author anurag
	 *
	 */
	class COPY extends AsyncTask<Void, String, Void>{
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			setResult(RESULT_OK);
			MultipleCopy.this.finish();
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(String... val) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(val);
			copyFrom.setText(val[0]);
			copying.setText(val[1]);
			contentSize.setText(val[2]);
			time.setText(val[3]);
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
			File file;
			int j = 0;
			double c = 0;
			for(int i= 0 ; i<len ; ++i){
				file = (File) getlist.get(i);
				if(file!=null){
					getFileSize(file);
					c++;
				}	
			}
			String formated = formatsize();
			for(int i = 0 ; i<len ;++i){
				file = (File) getlist.get(i);
				if(file!=null){
					String[] res = {"Copying : "+file.getName(),size(file),formated,""+(j/c)*100 + " Percent Copied"};
					publishProgress(res);
					copyToDirectory(file.getPath(), DEST);
					j++;
					if(ACTION.equals("cut"))
						deleteTargetForCut(file);
				}
			}
			return null;
		}
		
	}
	/**
	 * 
	 * @param old
	 * @param newDir
	 */
	public void copyToDirectory(String old, String newDir) {
		try{
			File old_file = new File(old);
			File temp_dir = new File(newDir);
			byte[] data = new byte[BUFFER];
			int read = 0;
			
			
				if(old_file.isFile() && temp_dir.isDirectory() && temp_dir.canWrite()){
					String file_name = old.substring(old.lastIndexOf("/"), old.length());
					File cp_file = new File(newDir + file_name);
					try {
						BufferedOutputStream o_stream = new BufferedOutputStream(
														new FileOutputStream(cp_file));
						BufferedInputStream i_stream = new BufferedInputStream(
													   new FileInputStream(old_file));
						while((read = i_stream.read(data, 0, BUFFER)) != -1)
							o_stream.write(data, 0, read);
						o_stream.flush();
						i_stream.close();
						o_stream.close();
						
					}catch (FileNotFoundException e) {
						Log.e("FileNotFoundException", e.getMessage());
					}catch (IOException e) {
						Log.e("IOException", e.getMessage());
					}
					
				}else if(old_file.isDirectory() && temp_dir.isDirectory() && temp_dir.canWrite()) {
					String files[] = old_file.list();
					String dir = newDir + old.substring(old.lastIndexOf("/"), old.length());
					int len = files.length;
					if(!new File(dir).mkdir()){}							
					for(int i = 0; i < len; i++)
						copyToDirectory(old + "/" + files[i], dir);
				}else if(!temp_dir.canWrite()){}
		}catch(Exception e){
			return;
		}
	}
	
	
	/**
	 * 
	 * @param file
	 */
	public void deleteTargetForCut(File file) {
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
						deleteTargetForCut(temp_f);
					else if(temp_f.isFile())
						temp_f.delete();
				}
			}
			if(target.exists())
				if(target.delete()){}
		}
	}
	
	/**
	 * THIS FUNCTION RETURN THE SIZE IF THE GIVEN FIZE IN PARAMETER
	 * @param f
	 * @return
	 */
	public String size(File f){
		long size = f.length();
		if(size>1024*1024*1024){
			size = size/(1024*1024*1024);
			return String.format("File size :%.2f GB", (double)size);
		}
		else if(size > 1024*1024){
			size = size/(1024*1024);
			return String.format("File size :%.2f MB", (double)size);
		}
		else if(size>1024){
			size = size/1024;
			return String.format("File size :%.2f KB", (double)size);
		}
		else{
			return String.format("File size :%.2f Bytes", (double)size);
		}	
	}
	
	
	
	
	/**
	 * 
	 * @param file
	 */
	public void getFileSize(File file){
		if(file.isFile()){
			si = si + file.length();
		}else if(file.isDirectory() && file.listFiles().length !=0){
			File[] a = file.listFiles();
			for(int i = 0 ; i<a.length ; ++i){
				if(a[i].isFile()){
					si = si + a[i].length();
				}else
					getFileSize(a[i]);
			}
		}
	}
	
	/**
	 * THIS FUNCTION RETURN THE SIZE IF THE GIVEN FIZE IN PARAMETER
	 * @param f
	 * @return
	 */
	public String formatsize(){
		//long size = f.length();
		if(si>1024*1024*1024)
			return String.format("Content size :%.2f GB", (double)si/(1024*1024*1024));
		
		else if(si > 1024*1024)
			return String.format("Content size :%.2f MB", (double)si/(1024*1024));
		
		else if(si>1024)
			return String.format("Content size :%.2f KB", (double)si/(1024));
		
		else
			return String.format("Content size :%.2f Bytes", (double)si);
	}
	
}
