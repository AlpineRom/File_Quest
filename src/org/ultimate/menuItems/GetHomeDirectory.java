package org.ultimate.menuItems;

import java.io.File;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

import org.anurag.file.quest.R;


import android.annotation.SuppressLint;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
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
@SuppressLint("NewApi")
public class GetHomeDirectory extends ListActivity implements OnClickListener{

	private static Stack<File> stack;
	private static File file ;
	private static File[] list;
	private static Intent intent;
	private Button go;
	private TextView t;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
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
		
		
		final ListView lv;
		intent = getIntent();
		file = new File(Environment.getExternalStorageDirectory().getPath());
		stack = new Stack<File>();
		stack.push(file);
		setContentView(R.layout.open_file_dialog);
		t = (TextView)findViewById(R.id.open);
		t.setText("Choose Any Directory For Home");
		
		list = file.listFiles();
		setListAdapter(new Adapter(getBaseContext(), R.layout.row_list_2, list));
		go = (Button)findViewById(R.id.goBack);
		go.setVisibility(View.GONE);
		lv = getListView();
		lv.setSelector(R.drawable.action_item_btn);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,long id) {
				// TODO Auto-generated method stub
				file = list[pos];
				if(file.isDirectory() && file.canRead()){
					go.setVisibility(View.VISIBLE);
					stack.push(file);
					list = file.listFiles();
					t.setText(file.getPath());
					lv.setAdapter(new Adapter(getBaseContext(), R.layout.row_list_2, list));
				}
			}
		});
		
		go.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				stack.pop();
				file = stack.peek();
				t.setText(file.getPath());
				lv.setAdapter(new Adapter(getBaseContext(), R.layout.row_list_2,
						file.listFiles()));
				if(stack.size()<2)
					go.setVisibility(View.GONE);
			}
		});
	}
	
	private class ItemHolder{
		ImageView Icon;
		TextView Name;
	}
	
	public class Adapter extends ArrayAdapter<File>{
		public Adapter(Context context, int textViewResourceId,	File[] objects) {
			super(context, textViewResourceId, objects);
			// TODO Auto-generated constructor stub
			list = objects;
			Arrays.sort(list,alphaFolderFirst);
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			ItemHolder item = new ItemHolder();
			if(convertView == null){
				LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = inflater.inflate(R.layout.row_list_2, parent , false);
				item.Icon = (ImageView)convertView.findViewById(R.id.iconImage2);
				item.Name = (TextView)convertView.findViewById(R.id.directoryName2);
				convertView.setTag(item);
			}else
				item = (ItemHolder)convertView.getTag();
				if(list[position].isDirectory())
					item.Icon.setBackgroundResource(R.drawable.ic_launcher_folder_orange);
				else
					item.Icon.setBackgroundResource(R.drawable.ic_launcher_unknown);
				item.Name.setText(list[position].getName());
			return convertView;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
			case R.id.justOnce:
				intent = new Intent();
				intent.setData(Uri.parse(file.getPath()));
				setResult(RESULT_OK, intent);
				finish();
				break;
			case R.id.always:
				setResult(RESULT_CANCELED);
				this.finish();
				break;
		}
	}
	
	/**
	 * SORTS THE FILE[] ALPHABETICALLY WITH HAVING FOLDERS FIRST
	 */
	private final static Comparator<File> alphaFolderFirst = new Comparator<File>() {
		@Override
		public int compare(File a, File b) {
			boolean aIsFolder = a.isDirectory();
			boolean bIsFolder = b.isDirectory();
			if(bIsFolder == aIsFolder )
				return a.getName().compareToIgnoreCase(b.getName());
			else if(bIsFolder)
				return 1;
			return -1;
		}
	}; 
}


