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
public

package org.ultimate.menuItems;

import java.io.File;


import java.util.List;
import org.anurag.file.quest.DialogBox;
import org.anurag.file.quest.R;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
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

public class SelectApp extends ListActivity{
	static Context mContext;
	static PackageManager pack;
	String CLASS;
	String CLASS_NAME;
	boolean selected = false;
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
		
		Intent i = getIntent();
		final String action = i.getAction();
		pack = getPackageManager();
		i = new Intent(android.content.Intent.ACTION_VIEW);
		File file = null;
		
		setContentView(R.layout.launch_file);
		ImageView iv = (ImageView)findViewById(R.id.launchImage);
		Button q = (Button)findViewById(R.id.justOnce);
		Button s = (Button) findViewById(R.id.always);
		TextView tv = (TextView)findViewById(R.id.open);
		
		if(action.equals("MUSIC")){
			tv.setText("Select App For Music Files");
			iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_music));
			file = new File("/sdcard/a.mp3");
			i.setDataAndType(Uri.fromFile(file) , "audio/*");
		}else if(action.equals("IMAGE")){
			tv.setText("Select App For Image Files");
			iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_images));
			file = new File("/sdcard/a.png");
			i.setDataAndType(Uri.fromFile(file) , "image/*");
		}else if(action.equals("VIDEO")){
			tv.setText("Select App For Video Files");
			iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_video));
			file = new File("/sdcard/a.mp4");
			i.setDataAndType(Uri.fromFile(file) , "video/*");
		}else if(action.equals("PDF")){
			tv.setText("Select App For Pdf Files");
			iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_adobe));
			file = new File("/sdcard/a.pdf");
			i.setDataAndType(Uri.fromFile(file) , "application/pdf");
		}else if(action.equals("RAR")){
			tv.setText("Select App For Rar Files");
			iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_other_compressed));
			file = new File("/sdcard/a.rar");
			i.setDataAndType(Uri.fromFile(file) , "application/rar");
		}else if(action.equals("ZIP")){
			tv.setText("Select App For ZiP Files");
			iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_zip_it));
			file = new File("/sdcard/a.zip");
			i.setDataAndType(Uri.fromFile(file) , "application/zip");
		}else if(action.equals("TEXT")){
			tv.setText("Select App For Text Files");
			iv.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_text));
			file = new File("/sdcard/a.txt");
			i.setDataAndType(Uri.fromFile(file) , "text/plain");
		}
		
		final List<ResolveInfo> list  = pack.queryIntentActivities(i, 0);
		if(list.size() == 0){
			/**
			 * NO APPS AVAILABLE TO HANDLE THIS KIND OF FILE TYPE
			 * FINISH THIS CLASS AND SHOW THE MESSAGE THAT NO APP
			 * IS AVAILABLE
			 */
			i = new Intent(getBaseContext(), DialogBox.class);
			i.setData(Uri.parse("NotFound"));
			startActivity(i);
			SelectApp.this.finish();
		}
		
		ListView lv = getListView();
		lv.setSelector(R.drawable.action_item_selected);
		setListAdapter(new OpenItems(getBaseContext(), R.layout.row_list_2, list));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				ResolveInfo info = list.get(position);
				selected = true;
				CLASS = info.activityInfo.packageName;
				CLASS_NAME = info.activityInfo.name;
			}
		});
		s.setText("Set As Default");
		q.setText("Quit");
		s.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SharedPreferences p = getSharedPreferences("DEFAULT_APPS", MODE_PRIVATE);
				SharedPreferences.Editor edit = p.edit();
				
				if(selected){
					if(action.equals("IMAGE")){
						edit.putString("IMAGE", CLASS);
						edit.putString("IMAGE_CLASS_NAME", CLASS_NAME);
						edit.commit();
					}
					else if(action.equals("MUSIC")){
						edit.putString("MUSIC", CLASS);
						edit.putString("MUSIC_CLASS_NAME", CLASS_NAME);
						edit.commit();
					}else if(action.equals("VIDEO")){
						edit.putString("VIDEO", CLASS);
						edit.putString("VIDEO_CLASS_NAME", CLASS_NAME);
						edit.commit();
					}else if(action.equals("ZIP")){
						edit.putString("ZIP", CLASS);
						edit.putString("ZIP_CLASS_NAME", CLASS_NAME);
						edit.commit();
					}else if(action.equals("RAR")){
						edit.putString("RAR", CLASS);
						edit.putString("RAR_CLASS_NAME", CLASS_NAME);
						edit.commit();
					}else if(action.equals("TEXT")){
						edit.putString("TEXT", CLASS);
						edit.putString("TEXT_CLASS_NAME", CLASS_NAME);
						edit.commit();
					}else if(action.equals("PDF")){
						edit.putString("PDF", CLASS);
						edit.putString("PDF_CLASS_NAME", CLASS_NAME);
						edit.commit();
					}
					Toast.makeText(getBaseContext(),
							"Changes Have Been Saved", Toast.LENGTH_SHORT).show();
					SelectApp.this.finish();
				}
				else
					Toast.makeText(getBaseContext(),
							"First Select An App From List", Toast.LENGTH_SHORT).show();
				
			}
		});
		q.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SelectApp.this.finish();
			}
		});
	}
	
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
			SelectApp.this.finish();
		return false;
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
				holder.Name.setText(info.loadLabel(pack));
				holder.Icon.setImageDrawable(info.loadIcon(pack));
			return convertView;
		}
	}
	
	
}
