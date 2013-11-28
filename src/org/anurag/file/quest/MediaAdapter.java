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

import java.util.List;




import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MediaAdapter extends ArrayAdapter<String>{

	private static Context mContext;
	String[] list;
	
	public MediaAdapter(Context context, int textViewResourceId,String[] mlist) {
		super(context, textViewResourceId , mlist);
		mContext = context;
		list = mlist;
	}
	
	private static class FileHolder{
		ImageView FileIcon;
		TextView FileName;
		LinearLayout l;
		CheckBox box;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		FileHolder f;
		if(convertView == null){
			f = new FileHolder();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.row_list_1, parent , false);
			f.FileIcon = (ImageView)convertView.findViewById(R.id.fileIcon);
			f.FileName = (TextView)convertView.findViewById(R.id.fileName);
			f.l = (LinearLayout)convertView.findViewById(R.id.row1BtnLayout);
			f.box = (CheckBox)convertView.findViewById(R.id.checkbox);
			convertView.setTag(f); 
		}else
			f = (FileHolder)convertView.getTag();
		f.box.setVisibility(View.GONE);
		f.l.setVisibility(View.GONE);
		if(position == 0){
			f.FileIcon.setBackgroundResource(R.drawable.ic_launcher_music);
			f.FileName.setText(list[position]);
		}else if(position == 1){
			f.FileIcon.setBackgroundResource(R.drawable.ic_launcher_apk);
			f.FileName.setText(list[position]);
		}else if(position == 2){
			f.FileIcon.setBackgroundResource(R.drawable.ic_launcher_ppt);
			f.FileName.setText(list[position]);
		}else if(position == 3){
			f.FileIcon.setBackgroundResource(R.drawable.ic_launcher_images);
			f.FileName.setText(list[position]);
		}else if(position == 4){
			f.FileIcon.setBackgroundResource(R.drawable.ic_launcher_video);
			f.FileName.setText(list[position]);
		}else if(position == 5){
			f.FileIcon.setBackgroundResource(R.drawable.ic_launcher_zip_it);
			f.FileName.setText(list[position]);
		}else if(position == 6){
			f.FileIcon.setBackgroundResource(R.drawable.ic_launcher_other_compressed);
			f.FileName.setText(list[position]);
		}else if(position == 7){
			f.FileIcon.setBackgroundResource(R.drawable.ic_launcher_sdcard);
			f.FileName.setText(list[position]);
		}
		return convertView;
	}
	

}
