package org.ultimate.menuItems;

import java.io.File;

import java.util.List;
import org.anurag.file.quest.DialogBox;
import org.anurag.file.quest.R;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.content.ComponentName;
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
public class BluetoothChooser extends ListActivity{
	static Context mContext;
	static PackageManager pack;
	String CLASS;
	String CLASS_NAME;
	boolean seleted = false;
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
		final File f = new File(i.getData().toString());
		pack = getPackageManager();
		i = new Intent(android.content.Intent.ACTION_SEND);
		i.setDataAndType(Uri.fromFile(f),"*/*");		
		setContentView(R.layout.launch_file);
		Button q = (Button)findViewById(R.id.justOnce);
		Button s = (Button) findViewById(R.id.always);
		TextView tv = (TextView)findViewById(R.id.open);
		tv.setText("Select An Application");
		
		
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
			this.finish();
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
				CLASS = info.activityInfo.packageName;
				CLASS_NAME = info.activityInfo.name;
				seleted = true;
			}
		});
		s.setText("Use");
		q.setText("Quit");
		s.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(seleted){
					Intent it = new Intent(Intent.ACTION_SEND);
					it.setComponent(new ComponentName(CLASS, CLASS_NAME));
					it.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(f));
					it.setType("*/*");
					startActivity(it);
					//Toast.makeText(getBaseContext(), CLASS_NAME, Toast.LENGTH_LONG).show();
					finish();
				}
				else
					Toast.makeText(getBaseContext(), "First Select An App For Action",
							Toast.LENGTH_SHORT).show();
			}
		});
		q.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
	
	
	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK)
			finish();
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
