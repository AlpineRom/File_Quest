package org.ultimate.menuItems;

import java.io.File;

import java.sql.Date;

import org.anurag.file.quest.R;



import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.view.WindowManager;
import android.widget.ImageView;
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
public class FileProperties extends Activity{
	private File file;
	private static long size;
	private TextView dev;
	private TextView info;
	private TextView name;
	private TextView copy;
	private static TextView ver;
	private TextView si;
	private TextView siLen;
	private TextView pack;
	private TextView pro;
	private ImageView vi;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		WindowManager w = getWindowManager();
		Point p = new Point();
		w.getDefaultDisplay().getSize(p);
		params.width = p.x*5/6;
		params.height = p.y*7/10;
		//params.alpha = 0.8f;
		setContentView(R.layout.info_layout);
		size = 0;
		file = new File(getIntent().getData().toString());
		dev = (TextView)findViewById(R.id.developer);
		copy = (TextView)findViewById(R.id.copyright);
		info = (TextView)findViewById(R.id.infoName);
		name = (TextView)findViewById(R.id.name);
		vi = (ImageView)findViewById(R.id.infoIcon);
		vi.setBackgroundResource(R.drawable.ic_launcher_stats);
		info.setText("Properties");
		ver = (TextView)findViewById(R.id.version);
		ver.setText("Type");
		ver = (TextView)findViewById(R.id.versionCode);
		si = (TextView)findViewById(R.id.size);
		siLen = (TextView)findViewById(R.id.sizeLenth); 
		pro = (TextView)findViewById(R.id.process);
		pro.setText("Modified On");
		pro = (TextView)findViewById(R.id.proName);
		pack = (TextView)findViewById(R.id.packageT);
		pack.setText("Internal Phone Storage");
		pack = (TextView)findViewById(R.id.pName);
		
		
		try{
			if(file.canRead()){
				Date mod = new Date(file.lastModified());
				pro.setText("    Modified On : " + mod);
				String availSize;
				String totalSize = null;
				StatFs env = new StatFs(Environment.getExternalStorageDirectory().getPath());
				// TOTAL PHONE STORAGE IN MB
				long total = env.getBlockCount()*env.getBlockSize();
				if(total>=1024*1024*1024)
					totalSize = String.format("%.2f GB", (double)total/(1024*1024*1024));
				else if(total>=1024*1024)
					totalSize = String.format("%.2f MB", (double)total/(1024*1024));
				
				// AVAILABLE STORAGE ON PHONE
				long avail = env.getAvailableBlocks()*env.getBlockSize();
				if(avail>=1024*1024*1024)
					availSize = String.format("%.2f GB", (double)avail/(1024*1024*1024));
				else if(avail>=1024*1024)
					availSize = String.format("%.2f MB", (double)avail/(1024*1024));
				else
					availSize = String.format("%.2f KB", (double)avail/(1024));
				
				pack.setText("    "+availSize+" Free/"+totalSize + " Total");
				
				if(file.isDirectory()){
					dev.setText("Folder");
					copy.setText("    Folder Name : " + file.getName());
					name.setText("    Folder Path : " + file.getAbsolutePath());
					if(file.getName().startsWith("."))
						ver.setText("    Hidden Type");
					else 
						ver.setText("    Non Hidden Type");
					si.setText("Folder Size");
					getFileSize(file);
					if(size >= 1024*1024*1024)
						siLen.setText(String.format("    Folder Size : %.2f GB", (double)size/(1024*1024*1024)));
					else if(size>=1024*1024)
						siLen.setText(String.format("    Folder Size : %.2f MB", (double)size/(1024*1024)));
					else if(size>=1024)
						siLen.setText(String.format("    Folder Size : %.2f KB", (double)size/(1024)));
					else
						siLen.setText("    Folder Size : " + size + " Byte");
					
				}else if(file.isFile()){
					dev.setText("File");
					copy.setText("    File Name : " + file.getName());
					name.setText("    File Path : " + file.getAbsolutePath());
					getFileType(file);
					si.setText("File Size");
					getFileSize(file);
					if(size >= 1024*1024*1024)
						siLen.setText(String.format("    File Size : %.2f GB", (double)size/(1024*1024*1024)));
					else if(size>=1024*1024)
						siLen.setText(String.format("    File Size : %.2f MB", (double)size/(1024*1024)));
					else if(size>=1024)
						siLen.setText(String.format("    File Size : %.2f KB", (double)size/(1024)));
					else
						siLen.setText("    File Size : " + size + " Byte");
				}
				
				
			}else{
				
			}
		}catch(RuntimeException e){
			siLen.setText("    Unavailable");
		}
	}
	/**
	 * 
	 * @param f
	 * @return
	 */
	public void getFileType(File f){
		if(f.getName().endsWith(".zip") || f.getName().endsWith(".ZIP"))
			ver.setText("    Zip File");
		else if(f.getName().endsWith(".tar") || f.getName().endsWith(".TAR") || f.getName().endsWith(".rar") 
				|| f.getName().endsWith("RAR") || f.getName().endsWith(".7z") || f.getName().endsWith(".7Z"))
			ver.setText("    Compressed File");
		else if(f.getName().endsWith(".apk") || f.getName().endsWith(".APK"))
			ver.setText("    Apk File");
		else if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3") || f.getName().endsWith(".amr") || f.getName().endsWith(".AMR")
				|| f.getName().endsWith(".ogg") || f.getName().endsWith(".OGG")||f.getName().endsWith(".m4a")||f.getName().endsWith(".M4A"))
			ver.setText("    Audio File");
		else if(f.getName().endsWith(".doc") || f.getName().endsWith(".DOC")
				|| f.getName().endsWith(".DOCX") || f.getName().endsWith(".docx") || f.getName().endsWith(".ppt") || f.getName().endsWith(".PPT"))
			ver.setText("    Document File");
		else if( f.getName().endsWith("jpg")||f.getName().endsWith(".JPG")||  f.getName().endsWith(".png") || f.getName().endsWith(".PNG") || f.getName().endsWith(".gif") || f.getName().endsWith(".GIF")
				|| f.getName().endsWith(".JPEG") || f.getName().endsWith(".jpeg") || f.getName().endsWith(".bmp") || f.getName().endsWith(".BMP"))
			ver.setText("    Image File");
		else if(f.getName().endsWith(".mp4") || f.getName().endsWith(".MP4") || f.getName().endsWith(".avi") || f.getName().endsWith(".AVI")
				|| f.getName().endsWith(".FLV") || f.getName().endsWith(".flv") || f.getName().endsWith(".3GP") || f.getName().endsWith(".3gp"))
			ver.setText("    Video File");	
		else if(f.getName().endsWith(".txt") || f.getName().endsWith(".TXT"))
			ver.setText("    Text File");
		else if(f.getName().endsWith(".pdf") || f.getName().endsWith(".PDF"))
			ver.setText("    Pdf File");
		else
			ver.setText("    Unknown"); 
	}
		/**
		 * 
		 * @param file
		 */
		public void getFileSize(File file){
			if(file.isFile())
				size = file.length();
			else if(file.isDirectory() && file.listFiles().length !=0){
				File[] a = file.listFiles();
				for(int i = 0 ; i<a.length ; ++i){
					if(a[i].isFile())
						size = size + a[i].length();
					else
						getFileSize(a[i]);
				}
			}
		}
	
}
