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

package org.ultimate.menuItems;


import org.anurag.file.quest.R;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class OnLaunchError extends Activity{

	
	private static ProgressBar bar;
	private static TextView popupTitle;
	private static TextView popupMessage;
	private static Button btn1;
	private static Button btn2;
	
	private static int result;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		WindowManager.LayoutParams params = this.getWindow().getAttributes();
		WindowManager w = getWindowManager();
		Point size = new Point();
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
			w.getDefaultDisplay().getSize(size);
			//params.alpha = 0.8f;
			params.height = size.y*1/3;
			params.width = size.x*4/5;
		}
		setContentView(R.layout.popup_dialog);
		btn1 = (Button)findViewById(R.id.popupOk);
		btn2 = (Button)findViewById(R.id.popupCancel);
		popupTitle = (TextView)findViewById(R.id.popupTitle);
		bar = (ProgressBar)findViewById(R.id.popupProgress);
		popupMessage = (TextView)findViewById(R.id.textMessage);
		ImageView V = (ImageView)findViewById(R.id.popupImage);
		V.setBackgroundResource(R.drawable.ic_launcher_file_task);
		
		bar.setVisibility(View.GONE);
		popupTitle.setText("  Error");
		popupMessage.setText("It Seems That The Selected Directory Choosen For Launch" +
				" Doesnot Exist,Rstoring To Default Setting");
		btn2.setVisibility(View.GONE);
		btn1.setText("Ok");
		btn1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	}
	
}
