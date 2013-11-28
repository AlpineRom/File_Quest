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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


@SuppressLint("DefaultLocale")
public class RootAdapter extends ArrayAdapter<File>{

	public static int FOLDER_TYPE;
	public static int[] FOLDERS = {R.drawable.ic_launcher_folder_orange ,
								   R.drawable.ic_launcher_folder_violet,
								   R.drawable.ic_launcher_folder_oxygen , 
								   R.drawable.ic_launcher_folder_yellow,
								   R.drawable.ic_launcher_folder_ubuntu ,
								   R.drawable.ic_launcher_folder_ubuntu_black ,
								   R.drawable.ic_launcher_folder_gnome};
	public boolean MULTI_SELECT;
	public ArrayList<File> MULTI_FILES;
	public boolean[] thumbselection;
	public long C;
	private Context context;
	private ArrayList<File> list;
	private PackageManager manager;
	int l ;
	
	public RootAdapter(Context con, int textViewResourceId, ArrayList<File> objects) {
		super(con , textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		list=objects;
		MULTI_FILES = new ArrayList<File>();
		MULTI_SELECT = false;
		l = list.size();
		context = con;
		C = 0;
		manager = con.getPackageManager();
		thumbselection = new boolean[list.size()];
	}
	
	/**
	 * 
	 * @author Anurag
	 *
	 */
	static class Holder{
		ImageView icon;
		TextView fName;
		TextView fType;
		TextView fSize;
		CheckBox box;
	}
	
	@Override
	public View getView(final int pos, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub
		final File f = list.get(pos);
		Holder h = new Holder();
		if(convertView == null){
			h = new Holder();
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_list_1, arg2 , false);
			h.icon = (ImageView)convertView.findViewById(R.id.fileIcon);
			h.fName = (TextView)convertView.findViewById(R.id.fileName);
			h.fType = (TextView)convertView.findViewById(R.id.fileType);
			h.fSize = (TextView)convertView.findViewById(R.id.fileSize);
			h.box = (CheckBox)convertView.findViewById(R.id.checkbox);
			convertView.setTag(h);
		}
		
		else
			h = (Holder)convertView.getTag();
		MULTI_FILES.add(null);
		if(MULTI_SELECT){
			h.box.setVisibility(View.VISIBLE);
			h.box.setId(pos);
			h.box.setOnClickListener(new View.OnClickListener(){
				@Override
				public void onClick(View v){
					// TODO Auto-generated method stub
					CheckBox ch = (CheckBox) v;
					int id = ch.getId();
					if(thumbselection[id]){
						ch.setChecked(false);
						thumbselection[id] = false;
						MULTI_FILES.remove(id);
						MULTI_FILES.add(id,null);
						C--;	
					}else{
						ch.setChecked(true);
						thumbselection[id] = true;
						MULTI_FILES.remove(id);
						MULTI_FILES.add(id, list.get(id));
						C++;
					}
				}
			});
			h.box.setChecked(thumbselection[pos]);
		}else
			h.box.setVisibility(View.GONE);
		
		h.fName.setText(f.getName());
		
		if(getExt(f).equalsIgnoreCase(".apk")){
			h.fType.setText("Application");
			h.fSize.setText(size(f));
			new ApkImage(h.icon).execute(f.getPath());
		}
		else if(getExt(f).equalsIgnoreCase(".png")||
				getExt(f).equalsIgnoreCase(".jpg")||
				getExt(f).equalsIgnoreCase(".jpeg")){
			h.fSize.setText(size(f));
			h.fType.setText("Image");
			new Image(h.icon).execute(f.getPath());
		}else{
			if(f.isDirectory()){
				h.icon.setImageDrawable(context.getResources().getDrawable(FOLDERS[FOLDER_TYPE]));
				h.fSize.setText(f.list().length +  " Items");
				h.fType.setText("Directory");
			}else if(f.isFile()){
				h.fSize.setText(size(f));
				if(getExt(f).equalsIgnoreCase(".mp3")||
						getExt(f).equalsIgnoreCase(".ogg")||
						getExt(f).equalsIgnoreCase(".amr")||
						getExt(f).equalsIgnoreCase(".acc")||
						getExt(f).equalsIgnoreCase(".m4a")||
						getExt(f).equalsIgnoreCase(".wav")){
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_music));
					h.fType.setText("Music");
				}else if(getExt(f).equalsIgnoreCase(".zip")){
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_zip_it));
					h.fType.setText("Zip");
				}else if(getExt(f).equalsIgnoreCase(".mp4")||
						getExt(f).equalsIgnoreCase(".3gp")||
						getExt(f).equalsIgnoreCase(".flv")||
						getExt(f).equalsIgnoreCase(".avi")||
						getExt(f).equalsIgnoreCase(".mkv")){
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_video));
					h.fType.setText("Video");
				}else if(getExt(f).equalsIgnoreCase(".rar")){
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_other_compressed));
					h.fType.setText("Compressed");
				}else if(getExt(f).equalsIgnoreCase(".htm")||
						getExt(f).equalsIgnoreCase(".html")||
						getExt(f).equalsIgnoreCase(".mhtml")){
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_web_pages));
					h.fType.setText("Web");
				}else if(getExt(f).equalsIgnoreCase(".pdf")){
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_adobe));
					h.fType.setText("Pdf");
				}else if(getExt(f).equalsIgnoreCase(".doc")||
						getExt(f).equalsIgnoreCase(".docx")||
						getExt(f).equalsIgnoreCase(".ppt")){
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_ppt));
					h.fType.setText("Document");
				}else if(getExt(f).equalsIgnoreCase(".txt")||getExt(f).equalsIgnoreCase(".log")){
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_text));
					h.fType.setText("Text");
				}else {
					h.icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_unknown));
					h.fType.setText("Unknown");
				}
				
			}
		}
		
		return convertView;
	}
	
	/**
	 * THIS FUNCTION THE EXTENSION OF THE GIVEN FILE
	 * @param f
	 * @return THE EXTENSION OF A FILE
	 */
	public String getExt(File f){
		String name = f.getName();
		try{
			return name.substring(name.lastIndexOf("."), name.length());
		}catch(IndexOutOfBoundsException e){
			return "";
		}
	}
	
	/**
	 * THIS FUNCTION RETURN THE SIZE IF THE GIVEN FIZE IN PARAMETER
	 * @param f
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public String size(File f){
		long size = f.length();
		if(size>1024*1024*1024)
			return String.format("%.2f GB", (double)size/(1024*1024*1024));
		
		else if(size > 1024*1024)
			return String.format("%.2f MB", (double)size/(1024*1024));
		
		else if(size>1024)
			return String.format("%.2f KB", (double)size/(1024));
		
		else
			return String.format("%.2f Bytes", (double)size);
	}
	
	/**
	 * THIS FUNCTION RETURNS THE ICON OF THE APP IF GIVEN FILE IS AN APP 
	 * @param f
	 * @return
	 */
	private Drawable getApkIcon(String f){
		Drawable draw;
		PackageInfo info;
		try{
			info = manager.getPackageArchiveInfo(f, 0);
			info.applicationInfo.publicSourceDir = f;
			draw = info.applicationInfo.loadIcon(manager);
			return draw;
		}catch(Exception e){
			return context.getResources().getDrawable(R.drawable.ic_launcher_apk);
		}
	}
	
	public class ApkImage extends AsyncTask<String , Void, Void>{

		Drawable draw;
		ImageView icon;
		public ApkImage(ImageView iV ){
			icon = iV;
			
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_apk));
			super.onPreExecute();
		}
		@Override
		protected Void doInBackground(String... path) {
			// TODO Auto-generated method stub
			draw = getApkIcon(path[0]);
			return null;
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			icon.setImageDrawable(draw);
			super.onPostExecute(result);
		}
		
	}

	private class Image extends AsyncTask<String, Void, Void>{

		Bitmap draw;
		ImageView view; 
		public Image(ImageView v){
			view = v;
		}
		
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(draw !=null)
				view.setImageBitmap(draw);
			else if(draw == null)
				view.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_images ));
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			view.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_launcher_images));
		}

		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			draw = getPreview(arg0[0]);
			return null;
		}
		
	}
	
	private  Bitmap getPreview(String url) {
        File image = new File(url);
        int size =72;
        
        InputStream photoStream = null;
		Bitmap mBitmap = null;
		try {
			photoStream = new FileInputStream(image);
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			opts.inSampleSize = 1;

			mBitmap = BitmapFactory.decodeStream(photoStream, null, opts);
			if (opts.outWidth > opts.outHeight && opts.outWidth > size) {
				opts.inSampleSize = opts.outWidth / size;
			} else if (opts.outWidth < opts.outHeight && opts.outHeight > size) {
				opts.inSampleSize = opts.outHeight / size;
			}
			if (opts.inSampleSize < 1) {
				opts.inSampleSize = 1;
			}
			opts.inJustDecodeBounds = false;
			photoStream.close();
			photoStream = new FileInputStream(image);
			mBitmap = BitmapFactory.decodeStream(photoStream, null, opts);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (photoStream != null) {
				try {
					photoStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mBitmap;
      
    }
}
