
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




import org.anurag.file.quest.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectedApp extends Activity{
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
		
		
		setContentView(R.layout.selected_app);
		ImageView iv = (ImageView)findViewById(R.id.selectedImage);
		Button q = (Button)findViewById(R.id.quit);
		Button s = (Button) findViewById(R.id.set);
		TextView tv = (TextView)findViewById(R.id.selectedTitle);
		TextView tv1 = (TextView)findViewById(R.id.selecteddes1);
		TextView tv2 = (TextView)findViewById(R.id.selecteddes2);
		Intent i = getIntent();
		final String action = i.getAction();
		final SharedPreferences pref = getSharedPreferences("DEFAULT_APPS", MODE_PRIVATE);
		final SharedPreferences.Editor edit = pref.edit(); 
		PackageManager pack = getPackageManager();
		PackageInfo info = null;
		try{
			info = pack.getPackageInfo(i.getData().toString(), 0);
		}catch(Exception e){
			
		}
		iv.setImageDrawable(info.applicationInfo.loadIcon(pack));
		tv.setText(info.applicationInfo.loadLabel(pack));
		tv1.setText("PROCESS NAME : " + info.applicationInfo.packageName);
		tv2.setText("VERSION : " + info.versionName);
		
		q.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				setResult(RESULT_CANCELED);
				SelectedApp.this.finish();
			}
		});
		
		s.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(action.equals("MUSIC")){
					edit.putString("MUSIC", null);
					edit.commit();
					Toast.makeText(getBaseContext(),
							"Stored To Default", Toast.LENGTH_SHORT).show();
					SelectedApp.this.finish();
				}else if(action.equals("IMAGE")){
					edit.putString("IMAGE", null);
					edit.commit();
					Toast.makeText(getBaseContext(),
							"Stored To Default", Toast.LENGTH_SHORT).show();
					SelectedApp.this.finish();
				}else if(action.equals("VIDEO")){
					edit.putString("VIDEO", null);
					edit.commit();
					Toast.makeText(getBaseContext(),
							"Stored To Default", Toast.LENGTH_SHORT).show();
					SelectedApp.this.finish();
				}else if(action.equals("ZIP")){
					edit.putString("ZIP", null);
					edit.commit();
					Toast.makeText(getBaseContext(),
							"Stored To Default", Toast.LENGTH_SHORT).show();
					SelectedApp.this.finish();
				}else if(action.equals("PDF")){
					edit.putString("PDF", null);
					edit.commit();
					Toast.makeText(getBaseContext(),
							"Stored To Default", Toast.LENGTH_SHORT).show();
					SelectedApp.this.finish();
				}else if(action.equals("TEXT")){
					edit.putString("TEXT", null);
					edit.commit();
					Toast.makeText(getBaseContext(),
							"Stored To Default", Toast.LENGTH_SHORT).show();
					SelectedApp.this.finish();
				}else if(action.equals("RAR")){
					edit.putString("RAR", null);
					edit.commit();
					Toast.makeText(getBaseContext(),
							"Stored To Default", Toast.LENGTH_SHORT).show();
					SelectedApp.this.finish();
				}
			}
		});
		
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if(keyCode == KeyEvent.KEYCODE_BACK){
			setResult(RESULT_CANCELED);
			SelectedApp.this.finish();
		}
			
		return false;
	}
	
}
