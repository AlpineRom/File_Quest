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

import java.util.ArrayList;



import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleAdapter extends ArrayAdapter<File>{
	
	long c;
	public boolean[] thumbselection;
	public boolean MULTI_SELECT;
	public static int FOLDER_TYPE;
	public static int[] FOLDERS = {
		R.drawable.ic_launcher_folder_orange , 
		R.drawable.ic_launcher_folder_violet,
		R.drawable.ic_launcher_folder_oxygen ,
		R.drawable.ic_launcher_folder_yellow,
		R.drawable.ic_launcher_folder_ubuntu , 
		R.drawable.ic_launcher_folder_ubuntu_black ,
		R.drawable.ic_launcher_folder_gnome };
	private ArrayList<File> nList;
	public ArrayList<File> MULTI_FILES;
	private static PackageManager mPack;
	private File file;
	private static Context mContext;
	private LayoutInflater inflater;
	private static PackageInfo pi;
	private static long size;
	public SimpleAdapter(Context context, int textViewResourceId ,ArrayList<File> objects) {
		super(context,textViewResourceId,objects);
		nList = objects;
		c = 0;
		MULTI_FILES = new ArrayList<File>();
		MULTI_SELECT = false;
		thumbselection = new boolean[nList.size()];
		mContext = context;
		inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mPack = mContext.getPackageManager();
	}
	
	private static class FileHolder{
		ImageView FileIcon;
		TextView FileName;
		TextView FileType;
		TextView FileSize;
		CheckBox box;
	}
	
	@Override
	public View getView( int position , View convertView, ViewGroup container){
		file= nList.get(position);
		size = 0;
		final FileHolder nHolder;
		if( convertView == null){
			convertView = inflater.inflate(R.layout.row_list_1, container , false);
			convertView.setMinimumHeight(5);
			nHolder = new FileHolder();
			nHolder.FileIcon = (ImageView)convertView.findViewById(R.id.fileIcon);
			nHolder.FileName = (TextView)convertView.findViewById(R.id.fileName);
			nHolder.FileSize = (TextView)convertView.findViewById(R.id.fileSize);
			nHolder.FileType = (TextView)convertView.findViewById(R.id.fileType);
			nHolder.box = (CheckBox)convertView.findViewById(R.id.checkbox);
			convertView.setTag(nHolder); 
		}else
			nHolder = (FileHolder)convertView.getTag(); 
		
		
		MULTI_FILES.add(null);	
		if(MULTI_SELECT){
			nHolder.box.setVisibility(View.VISIBLE);
			nHolder.box.setId(position);
			nHolder.box.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					CheckBox ch = (CheckBox) v;
					int id = ch.getId();
					if(thumbselection[id]){
						ch.setChecked(false);
						thumbselection[id] = false;
						MULTI_FILES.remove(id);
						MULTI_FILES.add(id, null);
						c--;
					}else{
						c++;
						MULTI_FILES.remove(id);
						MULTI_FILES.add(id, nList.get(id));
						ch.setChecked(true);
						thumbselection[id] = true;
					}
				}
			});
			nHolder.box.setChecked(thumbselection[position]);
		}else
			nHolder.box.setVisibility(View.GONE);
		
		
		
		
		
		nHolder.FileName.setText(file.getName());
		if(file.isDirectory() && file.exists()){
			nHolder.FileType.setText("Directory");
			//Drawable draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_folder_orange);
			//nHolder.FileIcon.setImageDrawable(draw);
			nHolder.FileName.setText(file.getName());
			if(!file.canRead() && !file.canWrite()){
				nHolder.FileSize.setText("Root Access");
				nHolder.FileSize.setTextColor(Color.RED);
			}else {
				nHolder.FileSize.setText(file.listFiles().length + " Items");
				nHolder.FileSize.setTextColor(Color.WHITE);
			}	
		}else if(file.isFile()){
			getSize(file);
			if(size>1024*1024*1024)
				nHolder.FileSize.setText(size/(1024*1024*1024) + " GB");
			else if(size>1024*1024)
				nHolder.FileSize.setText(size/(1024*1024)+ " MB");
			else
				nHolder.FileSize.setText(size/1024 + " KB");
			if( getFileType(file) == "song"){
				nHolder.FileType.setText("Music");
			}else if(getFileType(file) == "image"){
				nHolder.FileType.setText("Image");
			}else if( getFileType(file) == "apk" && file.isFile()){
				nHolder.FileType.setText("App"); 
			}else if(file.getName().endsWith(".pdf") || file.getName().endsWith(".PDF")){
				nHolder.FileType.setText("Pdf");
			}else if(file.getName().endsWith(".txt") || file.getName().endsWith(".TXT")
					|| file.getName().endsWith(".inf") || file.getName().endsWith(".INF")){
				nHolder.FileType.setText("Text");
			}else if( getFileType(file) == "zip"){
				nHolder.FileType.setText("Zip");
			}else if( getFileType(file) == "video"){
				nHolder.FileType.setText("Video");
			}else if( getFileType(file) == "compressed"){
				nHolder.FileType.setText("Archive");
			}else if( getFileType(file) == "document"){
				nHolder.FileType.setText("Document");
			}else if( getFileType(file) == "web"){
				nHolder.FileType.setText("Saved Web Page");
			}
			
			else{
				nHolder.FileType.setText("Unknown");
			}
		}
		new AppImageLoader(nHolder.FileIcon).execute(file.getPath());
		return convertView;
	}
	
	public static String getFileType(File f){
		if( f.getName().endsWith("jpg")||f.getName().endsWith(".JPG")||  f.getName().endsWith(".png") || f.getName().endsWith(".PNG") || f.getName().endsWith(".gif") || f.getName().endsWith(".GIF")
				|| f.getName().endsWith(".JPEG") || f.getName().endsWith(".jpeg") || f.getName().endsWith(".bmp") || f.getName().endsWith(".BMP"))
			return "image";
		if(f.isDirectory())
			return "folder";
		else if(f.getName().endsWith(".zip") || f.getName().endsWith(".ZIP"))
			return "zip";
		else if( f.getName().endsWith("mhtml")||f.getName().endsWith(".MHTML")||  f.getName().endsWith(".HTM") || f.getName().endsWith(".htm") 
				|| f.getName().endsWith(".html") || f.getName().endsWith(".HTML"))
			return "web";
		
		else if(f.getName().endsWith(".tar") || f.getName().endsWith(".TAR") || f.getName().endsWith(".rar") 
				|| f.getName().endsWith("RAR") || f.getName().endsWith(".7z") || f.getName().endsWith(".7Z"))
			return "compressed";
		else if(f.getName().endsWith(".apk") || f.getName().endsWith(".APK"))
			return "apk";
		else if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3") || f.getName().endsWith(".amr") || f.getName().endsWith(".AMR")
				|| f.getName().endsWith(".ogg") || f.getName().endsWith(".OGG")||f.getName().endsWith(".m4a")||f.getName().endsWith(".M4A"))
			return "song";
		else if(f.getName().endsWith(".doc") || f.getName().endsWith(".DOC")
				|| f.getName().endsWith(".DOCX") || f.getName().endsWith(".docx") || f.getName().endsWith(".ppt") || f.getName().endsWith(".PPT"))
			return "document";
		else if(f.getName().endsWith(".txt") || f.getName().endsWith(".TXT") || f.getName().endsWith(".inf") || f.getName().endsWith(".INF"))
			return "text";
		else if(f.getName().endsWith(".mp4") || f.getName().endsWith(".MP4") || f.getName().endsWith(".avi") || f.getName().endsWith(".AVI")
				|| f.getName().endsWith(".FLV") || f.getName().endsWith(".flv") || f.getName().endsWith(".3GP") || f.getName().endsWith(".3gp"))
			return "video";		
		
		return null;
	}
	
	/**
	 * 
	 * @author Anurag
	 *
	 */
	public static class AppImageLoader extends AsyncTask<String , Void , Drawable>{
		Drawable draw = null;
		ImageView image;
		public AppImageLoader(ImageView view) {
			image = view;
		}
		@Override
		protected void onPostExecute(Drawable result) {
			if(draw != null)
				image.setImageDrawable(draw);
			else
			{
				draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_unknown);
				image.setImageDrawable(draw);
			}
			super.onPostExecute(result);
		}
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}
		@Override
		protected Drawable doInBackground(String... arg0) {
			File file = new File(arg0[0]);
			if(file.isDirectory()){
				draw = mContext.getResources().getDrawable(FOLDERS[FOLDER_TYPE]);
			}
			else if(file.getName().endsWith(".apk")){
				/**
				 * There is a bug in loading app icon needs to be fixed
				 */
				try{
					pi = mPack.getPackageArchiveInfo(arg0[0], 0 );
					//pi.applicationInfo.sourceDir = arg0[0];
					pi.applicationInfo.publicSourceDir = arg0[0];
					draw = pi.applicationInfo.loadIcon(mPack);	
				}catch(Exception e){
					draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_apk);
				}
				Bitmap bitmap = ((BitmapDrawable) draw).getBitmap();
		        //int dp5 = (int)(75*getResources().getDisplayMetrics().density);
				draw= new BitmapDrawable(Bitmap.createScaledBitmap(bitmap, 65, 65, true));	
			}else if(file.isFile() && getFileType(file)=="song")
				draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_music);
			else if(file.isFile() && getFileType(file)=="zip")
				draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_zip_it);
			else if(file.isFile() && getFileType(file)=="compressed")
				draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_other_compressed);
			else if(file.isFile() && file.getName().endsWith(".pdf"))
				draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_adobe);
			else if(file.isFile() && getFileType(file)=="image"){
				Bitmap b = BitmapFactory.decodeFile(file.getPath());
				draw = new BitmapDrawable(b);
			}else if(file.isFile() && getFileType(file)=="video")
				draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_video );
			else if(file.isFile() && getFileType(file) == "text")
				draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_text);
			else if(file.isFile() && getFileType(file) == "web")
				draw = mContext.getResources().getDrawable(R.drawable.ic_launcher_web_pages);
			return null;
		}
	}
	
	/**
	 * Function To Calculate File Size
	 * @param file
	 */
	public static void  getSize(File file){
		size = file.length();
	}

	
}
