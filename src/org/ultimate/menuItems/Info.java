
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
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;


public class Info extends Activity{

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
		ImageView view = (ImageView)findViewById(R.id.infoIcon);
		view.setBackgroundResource(R.drawable.ic_launcher_info);
	}
	

}
