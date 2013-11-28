package org.ultimate.menuItems;

import java.io.File;

import java.util.ArrayList;
import org.anurag.file.quest.AppManager;
import org.anurag.file.quest.R;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Point;
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
public class AppStats extends Activity{
    private ApplicationInfo info;
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
		info = new AppManager(getBaseContext()).get_downloaded_apps().get(0);
		image = (ImageView) findViewById(R.id.infoIcon);
		infoName = (TextView)findViewById(R.id.infoName);
		
		
		
		PackageManager pack = getPackageManager();
		image.setBackgroundResource(R.drawable.ic_launcher_stats);
		infoName.setText("  Stats");
		
		infoName = (TextView)findViewById(R.id.developer);
		infoName.setText("Apps Status");
    
		infoName = (TextView)findViewById(R.id.copyright);
		infoName.setText("    Total System Apps : " + pack.getInstalledApplications(ApplicationInfo.FLAG_SYSTEM).size()+"");
		
		infoName = (TextView)findViewById(R.id.name);
		infoName.setText("    Total Downloaded Apps : "+ new AppManager(getBaseContext()).get_downloaded_apps().size());
		
		infoName = (TextView)findViewById(R.id.version);
		infoName.setText("Android Kernel Version");
		
		infoName = (TextView)findViewById(R.id.versionCode);
		infoName.setText("    "+System.getProperty("os.version"));
		
		
		infoName = (TextView)findViewById(R.id.size);
		infoName.setText("Root Status");
		
		
		infoName = (TextView)findViewById(R.id.sizeLenth);
		if(checkRoot1() && checkRoot2())
			infoName.setText("    Rooted");
		else
			infoName.setText("    Unrooted");
		
		
		long len = 0;
		ArrayList<ApplicationInfo> list = new ArrayList<ApplicationInfo>();
		list = new AppManager(getBaseContext()).getSysApps();
		int size = list.size();
		len = 0;
		infoName = (TextView)findViewById(R.id.packageT);
		infoName.setText("System Apps");
		for(int i = 0 ; i < size ; ++i){
			File file  = new File(list.get(i).sourceDir);
			len+=file.length();
		}
		
		infoName = (TextView)findViewById(R.id.pName);
		if(len>1024*1024*1024){
			len = len/(1024*1024*1024);
			infoName.setText("    Space Occupied (APK SIZE) : "+len+" GB");
		}else if(len>1024*1024){
			len = len/(1024*1024);
			infoName.setText("    Space Occupied (APK SIZE) : "+len+" MB");
		}else if(len>1024){
			len = len/(1024);
			infoName.setText("    Space Occupied (APK SIZE): "+len+" KB");
		}
		
		infoName = (TextView)findViewById(R.id.process);
		infoName.setText("Downloaded Apps");
		
		infoName = (TextView)findViewById(R.id.proName);
		list = new AppManager(getBaseContext()).get_downloaded_apps();
		size = list.size();
		len = 0;
		for(int i = 0 ; i < size ; ++i){
			File file  = new File(list.get(i).sourceDir);
			len+=file.length();
		}
		
		if(len>1024*1024*1024){
			len = len/(1024*1024*1024);
			infoName.setText("    Space Occupied (APK SIZE) : "+len+" GB");
		}else if(len>1024*1024){
			len = len/(1024*1024);
			infoName.setText("    Space Occupied (APK SIZE) : "+len+" MB");
		}else if(len>1024){
			len = len/(1024);
			infoName.setText("    Space Occupied (APK SIZE): "+len+" KB");
		}
		
    }
    boolean checkRoot1(){
		String TAGS = android.os.Build.TAGS;
		if(TAGS != null && TAGS.contains("test-keys"))
			return true;
		return false;
	}
    
    boolean checkRoot2(){
    	File f = new File("/system/app/Superuser.apk");
    	File f2 = new File("/system/app/superuser.apk");
    	if(f.exists() || f2.exists())
    		return true;
    	return false;
    }

}
