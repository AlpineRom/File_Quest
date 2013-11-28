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

package org.anurag.cloud.storage;
/**
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.widget.Toast;

import com.dropbox.sync.android.DbxAccountManager;

public class DropBoxCloud implements OnActivityResultListener{
	private DbxAccountManager AccountManager;
	private Activity activity;
	protected String APP_KEY = "vdzcl9uks21nzl7";
	protected String APP_SECRET_KEY = "cjonnh5wwpu360x";
	
	
	
	/**
	 * CONSTRUCTOR
	 * @param act
	 *//**
	public DropBoxCloud(Activity act , Context con){
		activity = act;
		AccountManager = DbxAccountManager.getInstance(con,APP_KEY, APP_SECRET_KEY);
		//linkDropBoxAccount();
	}
	
	/**
	 * THIS FUNCTION STARTS THE LINKING PROCESS BETWEEN DROPBOX ACCOUNT AND DEVICE
	 *//*
	public void linkDropBoxAccount(){
		// AN INTENT IS FIRED HERE WITH REQUEST CODE 0
		AccountManager.startLink(activity, 0);
	}

	
	@Override
	public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == 0){
			if(resultCode == Activity.RESULT_OK){
				Toast.makeText(activity, "Integrated", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(activity, "Integration Failed", Toast.LENGTH_SHORT).show();
			}
		}
		
		return false;
	}*/
	
	
	
	
	
	
	
	
	
	
//}
