
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

import java.util.List;



import android.annotation.SuppressLint;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;



public class OpenFiles extends ListActivity{
	private static PackageManager mPack;
	static String NAME;
	static String CLASS_NAME;
	static String MUSIC;
	static String MUSIC_CLASS_NAME;
	static String VIDEO;
	static String VIDEO_CLASS_NAME;
	static String APK ;
	static String APK_CLASS_NAME;
	static String PDF ;
	static String PDF_CLASS_NAME;
	static String IMAGE;
	static String IMAGE_CLASS_NAME;
	static String TEXT ;
	static String TEXT_CLASS_NAME;
	static String ZIP ;
	static String ZIP_CLASS_NAME;
	static String RAR ;
	static String RAR_CLASS_NAME;
	
	
	
	private List<ResolveInfo> list;
	private ResolveInfo info;
	private Intent intent;
	private static Context mContext;
    private File file;
    private Button justOnce;
    private Button always;
    private boolean intentSelected = false;
    @SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			WindowManager w = getWindowManager();
			Point size = new Point();
			w.getDefaultDisplay().getSize(size);
			//params.alpha = 0.8f;
			params.height = size.y*2/3;
			params.width = size.x*4/5;
		}
		
		SharedPreferences prefs = getSharedPreferences("DEFAULT_APPS", MODE_PRIVATE);
		final SharedPreferences.Editor edit = prefs.edit();
		MUSIC = prefs.getString("MUSIC", null);
		MUSIC_CLASS_NAME = prefs.getString("MUSIC_CLASS_NAME", null);
		VIDEO = prefs.getString("VIDEO", null);
		VIDEO_CLASS_NAME = prefs.getString("VIDEO_CLASS_NAME", null);
		APK = prefs.getString("APK", null);
		APK_CLASS_NAME = prefs.getString("APK_CLASS_NAME", null);
		PDF = prefs.getString("PDF", null);
		PDF_CLASS_NAME = prefs.getString("PDF_CLASS_NAME", null);
		IMAGE = prefs.getString("IMAGE", null);
		IMAGE_CLASS_NAME = prefs.getString("IMAGE_CLASS_NAME", null);
		TEXT = prefs.getString("TEXT", null);
		TEXT_CLASS_NAME = prefs.getString("TEXT_CLASS_NAME", null);
		ZIP = prefs.getString("ZIP", null);
		ZIP_CLASS_NAME = prefs.getString("ZIP_CLASS_NAME", null);	
		RAR = prefs.getString("RAR", null);
		RAR_CLASS_NAME = prefs.getString("RAR_CLASS_NAME", null);	
		
		
		intent =getIntent();
		file = new File(intent.getData().toString());
		intent = new Intent();
		intent.setAction(android.content.Intent.ACTION_VIEW);
		try{
			launchApp(file);
			if(intent != null){
				mPack = this.getPackageManager();
				list = mPack.queryIntentActivities(intent , 0);
				if(list.size() == 0){
					/**
					 * NO APPS AVAILABLE TO HANDLE THIS KIND OF FILE TYPE
					 * FINISH THIS CLASS AND SHOW THE MESSAGE THAT NO APP
					 * IS AVAILABLE
					 */
					intent = new Intent(getBaseContext(), DialogBox.class);
					intent.setData(Uri.parse("NotFound"));
					startActivity(intent);
					OpenFiles.this.finish();
				}
			}
			else{
				/**
				 * NO APPS AVAILABLE TO HANDLE THIS KIND OF FILE TYPE
				 * FINISH THIS CLASS AND SHOW THE MESSAGE THAT NO APP
				 * IS AVAILABLE
				 */
				intent = new Intent(getBaseContext(), DialogBox.class);
				intent.setData(Uri.parse("NotFound"));
				startActivity(intent);
				OpenFiles.this.finish();
				
			}
		}catch(Exception e){
			
		}
		
		
		try{
			intent = null;
			setIntentType(file);
			intent.setData(Uri.fromFile(file));
			startActivity(intent);
			OpenFiles.this.finish();
		}catch(Exception e){
			
		}
		
		setContentView(R.layout.launch_file);
		justOnce = (Button)findViewById(R.id.justOnce);
		always = (Button)findViewById(R.id.always);
		final ListView v = getListView(); 
		setListAdapter(new OpenItems(getApplicationContext(), R.layout.row_list_2, list));
		v.setSelector(R.drawable.action_item_selected);
		v.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,long arg3) {
				info = list.get(position);
				intentSelected = true; 
				intent = new Intent(Intent.ACTION_MAIN);
				intent.setAction(Intent.ACTION_VIEW);
				NAME = info.activityInfo.packageName;
				CLASS_NAME = info.activityInfo.name;
				intent.setComponent(new ComponentName(info.activityInfo.packageName, info.activityInfo.name));
				//intent.setClassName(info.activityInfo.packageName, info.activityInfo.name);
				intent.setData(Uri.fromFile(file));
			}
		});
		justOnce.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(intentSelected){
					try{
						startActivity(intent);
						OpenFiles.this.finish();
					}catch(ActivityNotFoundException e){
						intent = new Intent(getBaseContext(), DialogBox.class);
						intent.setData(Uri.parse("NotFound"));
						startActivity(intent);
						OpenFiles.this.finish();
					}catch(SecurityException e){
						intent = new Intent(getBaseContext(), DialogBox.class);
						intent.setData(Uri.parse("Security"));
						startActivity(intent);
						OpenFiles.this.finish();
					}
				}
				else
					Toast.makeText(getApplicationContext(), "First Select An Application", Toast.LENGTH_SHORT).show();
			}
		});
		
		always.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if(intentSelected){
					try{
						if(getFileType(file)=="image"){
							edit.putString("IMAGE", NAME);
							edit.putString("IMAGE_CLASS_NAME", CLASS_NAME);
							edit.commit();
						}
						else if(getFileType(file)=="music"){
							edit.putString("MUSIC", NAME);
							edit.putString("MUSIC_CLASS_NAME", CLASS_NAME);
							edit.commit();
						}else if(getFileType(file)=="apk"){
							edit.putString("APK", NAME);
							edit.putString("APK_CLASS_NAME", CLASS_NAME);
							edit.commit();
						}else if(getFileType(file)=="video"){
							edit.putString("VIDEO", NAME);
							edit.putString("VIDEO_CLASS_NAME", CLASS_NAME);
							edit.commit();
						}else if(getFileType(file)=="zip"){
							edit.putString("ZIP", NAME);
							edit.putString("ZIP_CLASS_NAME", CLASS_NAME);
							edit.commit();
						}else if(getFileType(file)=="rar"){
							edit.putString("RAR", NAME);
							edit.putString("RAR_CLASS_NAME", CLASS_NAME);
							edit.commit();
						}else if(getFileType(file)=="text"){
							edit.putString("TEXT", NAME);
							edit.putString("TEXT_CLASS_NAME", CLASS_NAME);
							edit.commit();
						}else if(getFileType(file)=="pdf"){
							edit.putString("PDF", NAME);
							edit.putString("PDF_CLASS_NAME", CLASS_NAME);
							edit.commit();
						}
						
						
						startActivity(intent);
						OpenFiles.this.finish();
					}catch(ActivityNotFoundException e){
						intent = new Intent(getBaseContext(), DialogBox.class);
						intent.setData(Uri.parse("NotFound"));
						startActivity(intent);
						OpenFiles.this.finish();
					}
				}
				else
					Toast.makeText(getApplicationContext(), "First Select An Application", Toast.LENGTH_SHORT).show();
			}
		});
		
			
	}
	
	
	/**
	 * 
	 * @author Anurag
	 *
	 */
	private static class ItemHolder{
		ImageView Icon;
		TextView Name;
	}
		
	/**
	 * 
	 * @author Anurag
	 *
	 */
	public static class OpenItems extends ArrayAdapter<ResolveInfo>{
		List<ResolveInfo> mList;
		public OpenItems(Context context, int textViewResourceId,List<ResolveInfo> objects) {
			super(context, textViewResourceId , objects);
			mList = objects;
			mContext = context;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ResolveInfo info = mList.get(position);
			ItemHolder holder;
			if(convertView == null){
				holder = new ItemHolder();
				LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.row_list_2, parent , false);
				holder.Icon = (ImageView)convertView.findViewById(R.id.iconImage2);
				holder.Name = (TextView)convertView.findViewById(R.id.directoryName2);
				convertView.setTag(holder);
			}else
				holder = (ItemHolder)convertView.getTag();
				holder.Name.setText(info.loadLabel(mPack));
				holder.Icon.setImageDrawable(info.loadIcon(mPack));
			return convertView;
		}
	}
	
	/**
	 * This function sets the intent for appropriate file type
	 * @param file
	 */
	public void launchApp(File f){
		
		if(f.getName().endsWith(".7z")
				||f.getName().endsWith(".7Z"))
			intent.setDataAndType(Uri.fromFile(f), "application/7z");
		else if(f.getName().endsWith(".mp3") ||f.getName().endsWith(".MP3")||f.getName().endsWith(".ogg")||f.getName().endsWith(".OGG")
				||f.getName().endsWith(".m4a")||f.getName().endsWith(".M4A")||f.getName().endsWith(".amr")||f.getName().endsWith(".AMR"))
			intent.setDataAndType(Uri.fromFile(f), "audio/*");
		
		else if(f.getName().endsWith(".WMV")||f.getName().endsWith("wmv")||f.getName().endsWith(".mp4")||f.getName().endsWith("MP4")||f.getName().endsWith(".3gp")||f.getName().endsWith(".flv")||f.getName().endsWith(".FLV"))
			intent.setDataAndType(Uri.fromFile(f), "video/*");
		
		else if(f.getName().endsWith(".jpg")
				||f.getName().endsWith(".JPG")
				||f.getName().endsWith(".jpeg")
				||f.getName().endsWith(".JPEG")
				||f.getName().endsWith(".png")
				||f.getName().endsWith(".PNG")
				||f.getName().endsWith(".gif")
				||f.getName().endsWith(".GIF")
				||f.getName().endsWith(".tiff")
				||f.getName().endsWith(".TIFF"))
			intent.setDataAndType(Uri.fromFile(f), "image/*");
		
		else if(f.getName().endsWith(".apk")
				||f.getName().endsWith(".APK"))
			intent.setDataAndType(Uri.fromFile(f), "application/vnd.android.package-archive");
		else if(f.getName().endsWith(".rar"))
			intent.setDataAndType(Uri.fromFile(f), "application/rar");
		else if(f.getName().endsWith(".zip")||f.getName().endsWith(".ZIP"))
			intent.setDataAndType(Uri.fromFile(f),"application/zip");
		else if(f.getName().endsWith(".pdf")||f.getName().endsWith(".PDF"))
			intent.setDataAndType(Uri.fromFile(f) , "application/pdf");
		else if(f.getName().endsWith(".DOC")||f.getName().endsWith(".doc"))
			intent.setDataAndType(Uri.fromFile(f) , "text/plain");
		else if(f.getName().endsWith(".TXT")||f.getName().endsWith(".txt")||f.getName().endsWith(".log")||f.getName().endsWith(".LOG"))
			intent.setDataAndType(Uri.fromFile(f) , "text/plain");	
		
		else {
			intent.setDataAndType(Uri.fromFile(f), "application/unknown");
		}
	}
	
	public void setIntentType(File f){
		if( f.getName().endsWith("jpg")||f.getName().endsWith(".JPG")||  f.getName().endsWith(".png") || f.getName().endsWith(".PNG") || f.getName().endsWith(".gif") || f.getName().endsWith(".GIF")
				|| f.getName().endsWith(".JPEG") || f.getName().endsWith(".jpeg") || f.getName().endsWith(".bmp") || f.getName().endsWith(".BMP")){
			if(IMAGE != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(IMAGE, IMAGE_CLASS_NAME));
			}
		}	
		else if(f.getName().endsWith(".zip") || f.getName().endsWith(".ZIP")){
			if(ZIP != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(ZIP, ZIP_CLASS_NAME));
			}
		}
			
		else if(f.getName().endsWith(".rar")|| f.getName().endsWith("RAR")){
			if(RAR != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(RAR, RAR_CLASS_NAME));
			}
		}
		else if(f.getName().endsWith(".pdf")|| f.getName().endsWith("PDF")){
			if(PDF != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(PDF, PDF_CLASS_NAME));
			}
		}
		
		else if(f.getName().endsWith(".apk") || f.getName().endsWith(".APK")){
			if(APK != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(APK, APK_CLASS_NAME));
			}
		}			
		else if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3") || f.getName().endsWith(".amr") || f.getName().endsWith(".AMR")
				|| f.getName().endsWith(".ogg") || f.getName().endsWith(".OGG")||f.getName().endsWith(".m4a")||f.getName().endsWith(".M4A")){
			if(MUSIC != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(MUSIC, MUSIC_CLASS_NAME));
			}
		}			
		else if(f.getName().endsWith(".doc") || f.getName().endsWith(".DOC")
				|| f.getName().endsWith(".DOCX") || f.getName().endsWith(".docx") || f.getName().endsWith(".ppt") || f.getName().endsWith(".PPT")){
			if(TEXT != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(TEXT, TEXT_CLASS_NAME));
			}
		}		
		else if(f.getName().endsWith(".txt") || f.getName().endsWith(".TXT") || f.getName().endsWith(".inf") || f.getName().endsWith(".INF")|| f.getName().endsWith(".log") || f.getName().endsWith(".LOG")){
			if(TEXT != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(TEXT, TEXT_CLASS_NAME));
			}
		}		
		else if(f.getName().endsWith(".mp4") || f.getName().endsWith(".MP4") || f.getName().endsWith(".avi") || f.getName().endsWith(".AVI")
				|| f.getName().endsWith(".FLV") || f.getName().endsWith(".flv") || f.getName().endsWith(".3GP") || f.getName().endsWith(".3gp")){
			if(VIDEO != null){
				intent = new Intent(Intent.ACTION_VIEW);
				intent.setComponent(new ComponentName(VIDEO, VIDEO_CLASS_NAME));
			}
		}
		else intent = null;		
	}
	
	public String getFileType(File f){
		if( f.getName().endsWith("jpg")||f.getName().endsWith(".JPG")||  f.getName().endsWith(".png") || f.getName().endsWith(".PNG") || f.getName().endsWith(".gif") || f.getName().endsWith(".GIF")
				|| f.getName().endsWith(".JPEG") || f.getName().endsWith(".jpeg") || f.getName().endsWith(".bmp") || f.getName().endsWith(".BMP"))
			return "image";
		
		if(f.getName().endsWith(".zip") || f.getName().endsWith(".ZIP"))
			return "zip";
		else if(f.getName().endsWith(".rar") || f.getName().endsWith("RAR"))
			return "rar";
		else if(f.getName().endsWith(".apk") || f.getName().endsWith(".APK"))
			return "apk";
		else if(f.getName().endsWith(".pdf") || f.getName().endsWith(".PDF"))
			return "pdf";
		else if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3") || f.getName().endsWith(".amr") || f.getName().endsWith(".AMR")
				|| f.getName().endsWith(".ogg") || f.getName().endsWith(".OGG")||f.getName().endsWith(".m4a")||f.getName().endsWith(".M4A"))
			return "music";
		else if(f.getName().endsWith(".doc") || f.getName().endsWith(".DOC")
				|| f.getName().endsWith(".DOCX") || f.getName().endsWith(".docx") || f.getName().endsWith(".ppt") || f.getName().endsWith(".PPT"))
			return "text";
		else if(f.getName().endsWith(".txt") || f.getName().endsWith(".TXT") || f.getName().endsWith(".inf") || f.getName().endsWith(".INF"))
			return "text";
		else if(f.getName().endsWith(".mp4") || f.getName().endsWith(".MP4") || f.getName().endsWith(".avi") || f.getName().endsWith(".AVI")
				|| f.getName().endsWith(".FLV") || f.getName().endsWith(".flv") || f.getName().endsWith(".3GP") || f.getName().endsWith(".3gp"))
			return "video";		
		
		return null;
	}
	
	
}
