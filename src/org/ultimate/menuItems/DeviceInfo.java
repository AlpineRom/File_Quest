package org.ultimate.menuItems;

import java.io.File;



import org.anurag.file.quest.AppManager;
import org.anurag.file.quest.R;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
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
public class DeviceInfo extends Activity{
    private static ApplicationInfo info;
    private ImageView image;
    private TextView infoName;
    
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		params.width = 400;
		params.height = 500;
		params.alpha = 0.8f;
		setContentView(R.layout.info_layout);
		info = new AppManager(getBaseContext()).get_downloaded_apps().get(0);
		image = (ImageView) findViewById(R.id.infoIcon);
		infoName = (TextView)findViewById(R.id.infoName);
		
		
		
		PackageManager pack = getPackageManager();
		image.setBackgroundResource(R.drawable.ic_launcher_device_info);
		infoName.setText("Device Info");
		
		infoName = (TextView)findViewById(R.id.developer);
		infoName.setText("Storage");
    
		//File path = Environment.getExternal
		infoName = (TextView)findViewById(R.id.copyright);
		//infoName.setText("" + path.getAbsolutePath());
		
		infoName = (TextView)findViewById(R.id.name);
		infoName.setText("    Total Downloaded Apps : "+ new AppManager(getBaseContext()).get_downloaded_apps().size());
		
		infoName = (TextView)findViewById(R.id.versionCode);
		
		
		
		infoName = (TextView)findViewById(R.id.size);
		infoName.setText("Size");
		
		infoName = (TextView)findViewById(R.id.sizeLenth);
		File f = new File(info.sourceDir);
		long len = f.length();
		if(len>1024*1024*1024){
			len = len/(1024*1024*1024);
			infoName.setText(""+len+" GB");
		}else if(len>1024*1024){
			len = len/(1024*1024);
			infoName.setText(""+len+" MB");
		}else if(len>1024){
			len = len/(1024);
			infoName.setText(""+len+" KB");
		}else{
			infoName.setText(""+len+" Bytes");
		}
		
		infoName = (TextView)findViewById(R.id.packageT);
		infoName.setText("Package Name");
		
		infoName = (TextView)findViewById(R.id.pName);
		infoName.setText(info.packageName);
		
		infoName = (TextView)findViewById(R.id.process);
		infoName.setText("Process Name");
		
		infoName = (TextView)findViewById(R.id.proName);
		infoName.setText(info.processName);
		
    }
	

}
