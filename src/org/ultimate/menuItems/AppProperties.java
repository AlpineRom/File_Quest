package org.ultimate.menuItems;
import java.io.File;
import org.anurag.file.quest.R;
import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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
public class AppProperties extends Activity{
    private PackageInfo info;
    private ImageView image;
    private TextView infoName;
    
    
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
		try {
			info = getPackageManager().getPackageInfo(getIntent().getData().toString(), 0);
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		image = (ImageView) findViewById(R.id.infoIcon);
		infoName = (TextView)findViewById(R.id.infoName);
		
		
		
		PackageManager pack = getPackageManager();
		image.setImageDrawable(pack.getApplicationIcon(info.applicationInfo));
		infoName.setText(pack.getApplicationLabel(info.applicationInfo));
		
		infoName = (TextView)findViewById(R.id.developer);
		infoName.setText("Application Name");
    
		infoName = (TextView)findViewById(R.id.copyright);
		infoName.setVisibility(View.GONE);
		
		infoName = (TextView)findViewById(R.id.name);
		infoName.setText("    " + pack.getApplicationLabel(info.applicationInfo));
		
		infoName = (TextView)findViewById(R.id.versionCode);
		try {
			PackageInfo pi = pack.getPackageInfo(info.packageName, 0);
			infoName.setText("    "+pi.versionName);
		} catch (NameNotFoundException e) {
			infoName.setText("Unable To Find Version Of The App");
			e.printStackTrace();
		}
		infoName = (TextView)findViewById(R.id.size);
		infoName.setText("Size");
		
		infoName = (TextView)findViewById(R.id.sizeLenth);
		File f = new File(info.applicationInfo.sourceDir);
		long len = f.length();
		if(len>1024*1024*1024){
			len = len/(1024*1024*1024);
			infoName.setText(String.format("    %.2f GB", (double)len));
		}else if(len>1024*1024){
			len = len/(1024*1024);
			infoName.setText(String.format("    %.2f MB", (double)len));
		}else if(len>1024){
			len = len/(1024);
			infoName.setText(String.format("    %.2f KB",(double)len));
		}else{
			infoName.setText("    "+len+" Bytes");
		}
		
		infoName = (TextView)findViewById(R.id.packageT);
		infoName.setText("Package Name");
		
		infoName = (TextView)findViewById(R.id.pName);
		infoName.setText("    " + info.packageName);
		
		infoName = (TextView)findViewById(R.id.process);
		infoName.setText("Process Name");
		
		infoName = (TextView)findViewById(R.id.proName);
		infoName.setText("    " + info.applicationInfo.processName);
		
    }
	

}
