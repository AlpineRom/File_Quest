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

import java.io.BufferedReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.ultimate.menuItems.AppProperties;
import org.ultimate.menuItems.AppStats;
import org.ultimate.menuItems.BluetoothChooser;
import org.ultimate.menuItems.DeleteBackups;
import org.ultimate.menuItems.DeleteFlashable;
import org.ultimate.menuItems.DeviceInfo;
import org.ultimate.menuItems.FileProperties;
import org.ultimate.menuItems.GetHomeDirectory;
import org.ultimate.menuItems.Info;
import org.ultimate.menuItems.MultiSendApps;
import org.ultimate.menuItems.MultiZip;
import org.ultimate.menuItems.MultileAppBackup;
import org.ultimate.menuItems.MultipleCopy;
import org.ultimate.menuItems.OnLaunchError;
import org.ultimate.menuItems.SelectApp;
import org.ultimate.menuItems.SelectedApp;
import org.ultimate.menuItems.SystemFlash;
import org.ultimate.quickaction3D.ActionItem;
import org.ultimate.quickaction3D.QuickAction;
import org.ultimate.quickaction3D.QuickAction.OnActionItemClickListener;
import org.ultimate.root.*;

import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.viewpagerindicator.TitlePageIndicator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.graphics.Point;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StatFs;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class TaskerActivity extends FragmentActivity implements OnClickListener , QuickAction.OnActionItemClickListener{

	/**
	 * The {@link android.support.v4.view.PagerAdapter} that will provide
	 * fragments for each of the sections. We use a
	 * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
	 * will keep every loaded fragment in memory. If this becomes too memory
	 * intensive, it may be best to switch to a
	 * {@link android.support.v4.app.FragmentStatePagerAdapter}.
	 */
	private static SectionsPagerAdapter mSectionsPagerAdapter;

	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	
	private static ArrayList<File> MULTI_GALLERY;
	private static ArrayList<File> MULTI_SIMPLE;
	private static ArrayList<File> MULTI_ROOT;
	
	private static boolean MULTIPLE_COPY;
	private static boolean MULTIPLE_CUT;
	private static boolean MULTIPLE_COPY_GALLERY;
	private static boolean MULTIPLE_CUT_GALLERY;
	private ProgressBar sd;
	private TextView avail,total;
	private long av,to;
	private String sav,sto;
	private static ArrayList<String> EMPTY;
	private static ArrayList<String> EMPTY_APPS;
	
	private static boolean MULTI_SELECT_APPS;
	private static boolean MULTI_SELECT_GALLERY;	
//	private static boolean MULTI_SELECT_MODE;
	
	
	
	private static boolean ENABLE_ON_LAUNCH;
	private static String INTERNAL_PATH_ONE;
	private static String INTERNAL_PATH_TWO;		
	private static String PATH;
	private static int SHOW_APP;
	private static String HOME_DIRECTORY;
	private static int SORT_TYPE;
	private static boolean SHOW_HIDDEN_FOLDERS;
	private static int CURRENT_PREF_ITEM;
	private float TRANSPARENCY_LEVEL;
	private static SharedPreferences.Editor edit;
	private static SharedPreferences preferences;
	private static boolean SEARCH_FLAG = false;
	private static int CREATE_FLAG = 0;
	private static boolean CREATE_FILE = false;
	private static String CREATE_FLAG_PATH;
	private WindowManager.LayoutParams params; 
	private static ListView root;
	private static ListView simple;
	private static ListView media;
	private static ArrayList<File> searchList;
	private static TextView extText;
	private static boolean RENAME_COMMAND = false;
	private static EditText editBox;
	private static EditText extBox;
	private static ViewPager mViewPager;
	private static ViewFlipper mFlipperBottom;
	private static TitlePageIndicator indicator;
	private static RootAdapter nRoot;
	private static SimpleAdapter nSimple;
	private static RFileManager  nRFileManager;
	private static SFileManager nSFManager;
	private static Intent LAUNCH_INTENT;
	private static boolean COPY_COMMAND = false;
	private static boolean CUT_COMMAND = false;
	private static String COPY_STRING;
	private static int CURRENT_ITEM = 0;
	/**
	 * Media Panel Variables
	 */
	private static MediaManager manager;
	private static MediaAdapter adapter;
	private static MediaElementAdapter element;
	private static ArrayList<File> mediaFileList;
	private static boolean elementInFocus = false; 
	private static int pos = 0;
	
	private static ArrayList<File> nFiles;
	private static ArrayList<File> sFiles;
	private static AppManager nManager;
	
	private static ArrayList<ApplicationInfo> nList;
	private static ApplicationInfo info;
	private static AppAdapter nAppAdapter;
	private static ListView APP_LIST_VIEW;
	static int lastItem = 0;
	private static File file;
	private static File file2;
	private static File file0;
	private static boolean mUseBackKey = false;
	private static ViewFlipper mVFlipper;
	private static int LAST_PAGE ;
	AdView ad;
	
	@SuppressWarnings("static-access")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		
		PATH = INTERNAL_PATH_ONE = INTERNAL_PATH_TWO = Environment.getExternalStorageDirectory().getAbsolutePath();
		preferences = getSharedPreferences("MY_APP_SETTINGS", MODE_PRIVATE);
		INTERNAL_PATH_ONE = preferences.getString("INTERNAL_PATH_ONE", PATH);
		INTERNAL_PATH_TWO = preferences.getString("INTERNAL_PATH_TWO", PATH);
		SHOW_APP = preferences.getInt("SHOW_APP", 1);
		CURRENT_ITEM = CURRENT_PREF_ITEM = preferences.getInt("CURRENT_PREF_ITEM", 0);
		TRANSPARENCY_LEVEL = preferences.getFloat("TRANSPARENCY_LEVEL", 0.8f);
		SHOW_HIDDEN_FOLDERS = preferences.getBoolean("SHOW_HIDDEN_FOLDERS", false);
		SORT_TYPE = preferences.getInt("SORT_TYPE",2);
		nRoot.FOLDER_TYPE = nSimple.FOLDER_TYPE = preferences.getInt("FOLDER_TYPE", 3);
		HOME_DIRECTORY = preferences.getString("HOME_DIRECTORY", null);
		ENABLE_ON_LAUNCH = preferences.getBoolean("ENABLE_ON_LAUNCH", false);
		edit = preferences.edit();
		
		SEARCH_FLAG = RENAME_COMMAND = COPY_COMMAND = CUT_COMMAND = MULTIPLE_COPY = MULTIPLE_CUT =   
				CREATE_FILE = false;
		
		params = this.getWindow().getAttributes();
		params.alpha = 0.5f;
		Point size = new Point();
		getWindowManager().getDefaultDisplay().getSize(size);	
		
		setContentView(R.layout.fargment_ui);		
		
		EMPTY = new ArrayList<String>();
		EMPTY_APPS = new ArrayList<String>();
		searchList = new ArrayList<File>();
		mediaFileList = new ArrayList<File>();
		extText = (TextView)findViewById(R.id.extText);
		editBox = (EditText)findViewById(R.id.editBox);
		extBox = (EditText)findViewById(R.id.extBox);
		sd = (ProgressBar)findViewById(R.id.space_indicator);
		avail = (TextView)findViewById(R.id.avail);
		total = (TextView)findViewById(R.id.total);
		
		
		
		
		LinearLayout l = (LinearLayout)findViewById(R.id.ui); 
		ad = new AdView(this, AdSize.SMART_BANNER, "*************");
		l.addView(ad);
		ad.loadAd(new AdRequest());			
				
				
		if(size.x < 480 && size.y<800){
			LAUNCH_INTENT = new Intent(getBaseContext(), DialogBox.class);
			LAUNCH_INTENT.setData(Uri.parse("unsupportedScreenSize"));
			startActivityForResult(LAUNCH_INTENT, 1000);
		}
		
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
		mViewPager = (ViewPager) findViewById(R.id.pager);
		indicator = (TitlePageIndicator)findViewById(R.id.indicator); 
		mViewPager.setAdapter(mSectionsPagerAdapter);
		indicator.setViewPager(mViewPager);
		mViewPager.setCurrentItem(CURRENT_PREF_ITEM);
		mVFlipper = (ViewFlipper)findViewById(R.id.viewFlipperMenu);
		mFlipperBottom = (ViewFlipper)findViewById(R.id.viewFlipperMenuBottom);
		nSFManager = new SFileManager();
		nRFileManager = new RFileManager();
		if(ENABLE_ON_LAUNCH){
			if(new File(INTERNAL_PATH_TWO).exists()){
				if(PATH != INTERNAL_PATH_TWO)
					nRFileManager.nStack.push(INTERNAL_PATH_TWO);
			}else{
				edit.putString("INTERNAL_PATH_TWO", PATH);
				edit.commit();
				LAUNCH_INTENT = new Intent(getBaseContext(), OnLaunchError.class);
				startActivity(LAUNCH_INTENT);			
			}
			
			if(new File(INTERNAL_PATH_ONE).exists()){
				if(PATH != INTERNAL_PATH_ONE)
					nSFManager.nStack.push(INTERNAL_PATH_ONE);
			}else{
				edit.putString("INTERNAL_PATH_ONE", PATH);
				edit.commit();
				LAUNCH_INTENT = new Intent(getBaseContext(), OnLaunchError.class);
				startActivity(LAUNCH_INTENT);			
			}
		}
		
		if(CURRENT_PREF_ITEM !=3)
			LAST_PAGE = 2;
		else 
			LAST_PAGE = 3;
		
		if(CURRENT_PREF_ITEM == 0){
			Button b = (Button)findViewById(R.id.change);
			TextView t = (TextView)findViewById(R.id.addText);
			b.setBackgroundResource(R.drawable.ic_launcher_select_app);
			t.setText("Default App");
			mFlipperBottom.showNext();
			new load().execute();
			LAST_PAGE = 0;
		}
		
		if(CURRENT_PREF_ITEM == 3){
			mVFlipper.setAnimation(nextAnim());
			mVFlipper.showNext();
			mFlipperBottom.showPrevious();
			mFlipperBottom.setAnimation(prevAnim());
		}
		
		nRFileManager.SORT_TYPE =  nSFManager.SORT_TYPE = SORT_TYPE;
		if(SHOW_HIDDEN_FOLDERS){
			nSFManager.SHOW_HIDDEN_FOLDER = true;
			nRFileManager.SHOW_HIDDEN_FOLDER = true;
		}nFiles = nRFileManager.giveMeFileList();
		sFiles = nSFManager.giveMeFileList();
		nSimple = new SimpleAdapter(getApplicationContext(), R.layout.row_list_1 , sFiles);
		nRoot = new RootAdapter(getApplicationContext(), R.layout.row_list_1, nFiles);
		nManager = new AppManager(getBaseContext());
		nManager.SHOW_APP = SHOW_APP;
		nList = nManager.giveMeAppList();
		nAppAdapter = new AppAdapter(getBaseContext(), R.layout.row_list_1, nList);
		
		
		
		for(int i = 0 ; i<15 ; ++i){
			EMPTY.add("  ");
			EMPTY_APPS.add("  ");
			if(i == 9){
				EMPTY.add("No Files Available");
				EMPTY_APPS.add("No User Apps Installed");
			}	
		}
		
		String[] list = {"Music " 
				, "Apk " ,
				"Documents" ,
				"Image " ,
				"Video " ,
				"Zipped " ,
				"Other Compressed " ,
				"SD Card"};
		adapter  = new MediaAdapter(getApplicationContext(), R.layout.row_list_1, list);
		manager = new MediaManager();
		file = new File("/");
		file2 = new File(PATH);
		indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageSelected(int page) {
				// TODO Auto-generated method stub
				mUseBackKey = false;
				Button b = (Button)findViewById(R.id.change);
				TextView t = (TextView)findViewById(R.id.addText);
				if(page == 0){
					if(!elementInFocus){
						mFlipperBottom.showNext();
						mFlipperBottom.setAnimation(nextAnim());
					}					
					b.setBackgroundResource(R.drawable.ic_launcher_select_app);
					t.setText("Default App");
					new load().execute();
					LAST_PAGE = 0;
				}else if(page !=0){
					b.setBackgroundResource(R.drawable.ic_launcher_add_new);
					t.setText("New");
				}
				if(page==1 && LAST_PAGE == 0){
					LAST_PAGE = 1;
					if(!elementInFocus){
						mFlipperBottom.showPrevious();
						mFlipperBottom.setAnimation(prevAnim());
					}
				}
				if(page == 2 && LAST_PAGE == 3){
					LAST_PAGE = 2;
					mFlipperBottom.showNext();
					mFlipperBottom.setAnimation(nextAnim());
					mVFlipper.setAnimation(prevAnim());
					mVFlipper.showPrevious();
				}				
				if(page == 3 && (LAST_PAGE == 2 || LAST_PAGE == 1)){
					LAST_PAGE = 3;
					mFlipperBottom.showPrevious();
					mFlipperBottom.setAnimation(prevAnim());
					if(RENAME_COMMAND || SEARCH_FLAG || CREATE_FILE){
						mVFlipper.setAnimation(prevAnim());
						mVFlipper.showPrevious();
						RENAME_COMMAND = SEARCH_FLAG = CREATE_FILE = false;
					}
					else{
						mVFlipper.setAnimation(nextAnim());
						mVFlipper.showNext();
					}
				}
				if(RENAME_COMMAND || SEARCH_FLAG || CREATE_FILE){
					RENAME_COMMAND = SEARCH_FLAG =  CREATE_FILE = false;
					mVFlipper.showNext();
					mVFlipper.setAnimation(nextAnim());
				}
				
			}
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
			}
		});
		new load().execute();
		super.onCreate(savedInstanceState);
		
		//LAUNCH_INTENT = new Intent(getBaseContext(), ImageViewer.class);
		//startActivity(LAUNCH_INTENT);
	}
	@Override
	protected void onPostResume() {
		params = this.getWindow().getAttributes();
		params.alpha = TRANSPARENCY_LEVEL;
		super.onPostResume();
	}
	@Override
	protected void onResumeFragments(){
		params = this.getWindow().getAttributes();
		params.alpha = TRANSPARENCY_LEVEL;
		super.onResumeFragments();
	}
	@Override
	protected void onStart() {
		params = this.getWindow().getAttributes();
		params.alpha = TRANSPARENCY_LEVEL;
		super.onStart();
	}
	@Override
	protected void onResume() {
		params = this.getWindow().getAttributes();
		params.alpha = 0.85f;
		super.onResume();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		ShowMenu();
		return false;
	}


	
	/**
	 * THIS FUNCTION REINITIALIZES THE VIEW PAGER AS PER GIVEN PARAMETERS AND CONDITIONS 	
	 * @param a
	 * @param context
	 */
	private static void setAdapterAgain(final  int a ,final Context context){
		
		/**
		 * THIS CLASS LOADS THE RESULT THAT HAS TO DISPLAYED IN BACKGROUND
		 * @author Anurag
		 *
		 */
		class LoadSD extends AsyncTask<Void, Void, Void>{
			@Override
			protected void onPostExecute(Void result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				if(nFiles.size() == 0){
					root.setAdapter(new EmptyAdapter(context, R.layout.empty_adapter, EMPTY));
					root.setEnabled(false);
				}	
				if(sFiles.size() == 0){
					simple.setAdapter(new EmptyAdapter(context, R.layout.empty_adapter, EMPTY));
					simple.setEnabled(false);
				}
				if(mediaFileList.size() == 0){
					media.setAdapter(new EmptyAdapter(context, R.layout.empty_adapter, EMPTY));
					media.setEnabled(false);
				}
				mViewPager.setAdapter(mSectionsPagerAdapter);
				mViewPager.setCurrentItem(a);
				if(a == 3){
					//nAppAdapter = new AppAdapter(context, R.layout.row_list_1,nList);
					//nSimple = new FAdapterSimple(context , R.layout.row_list_1, sFiles);
					//nRoot = new RootAdapter(context , R.layout.row_list_1 , nFiles);
					if(MULTI_SELECT_APPS){
						//nAppAdapter = new AppAdapter(context, R.layout.row_list_1,nList);
						nAppAdapter.MULTI_SELECT = true;
						nAppAdapter.C = 0;
						nAppAdapter.thumbSelection = new boolean[nList.size()];
					}
					APP_LIST_VIEW.setAdapter(nAppAdapter);
					APP_LIST_VIEW.setSelection(pos);
				}
				
			}
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
				if(SHOW_HIDDEN_FOLDERS){
					nRFileManager.SHOW_HIDDEN_FOLDER = true;
					nSFManager.SHOW_HIDDEN_FOLDER = true;
				}
				
				try{//TRY BLOCK IS USED BECAUSE I NOTICED THAT WHEN NEW FOLDER
					//WITH HINDI LANGAUGE IS CREATED THROWS INDEXOUTOFBOUND EXCEPTION 
					//I HTINK IT IS APPLICABLE TO OTHER LANGUAGES ALSO
					nFiles = nRFileManager.giveMeFileList();
					sFiles = nSFManager.giveMeFileList();
				}catch(IndexOutOfBoundsException e){
					nFiles = nRFileManager.giveMeFileList();
					sFiles = nSFManager.giveMeFileList();
				}
				
				if(nRoot.MULTI_SELECT){
					nRoot.thumbselection = new boolean[nFiles.size()];
					nRoot.MULTI_FILES = new ArrayList<File>();
					nRoot.C = 0;
				}
				if(nSimple.MULTI_SELECT){
					nSimple.thumbselection = new boolean[sFiles.size()];
					nSimple.MULTI_FILES = new ArrayList<File>();
					nSimple.c = 0;
				}
				mUseBackKey = false;
				return null;
			}
		}
		new LoadSD().execute();	
	}
		
	
	/**
	 * This Function Generate The List Of Current Selected File Types In Media Panel
	 * @param pos
	 */
	public static void loadMediaList(int pos){
		file0 = new File(PATH);
		int sd = 0;
		manager.clearList();
		if( (pos)  == 0)
			mediaFileList = manager.getAudioFiles(file0);
		else if((pos)== 1 )
			mediaFileList = manager.getApkFiles(file0);
		else if((pos) == 2 )
			mediaFileList = manager.getDocumentFiles(file0);
		else if((pos)== 3)
			mediaFileList = manager.getImageFiles(file0);
		else if((pos)== 4)
			mediaFileList = manager.getVideoFiles(file0);
		else if((pos) == 5)
			mediaFileList = manager.getZipFiles(file0);
		else if((pos) == 6)
			mediaFileList = manager.getCompressedFiles(file0);
	}
	
	/**
	 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
	 * one of the sections/tabs/pages.
	 */
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch(position){
				case 0:
					Fragment fragment0 = new MediaPanel();
					return fragment0;
				case 1:
					Fragment fragment = new SimplePanel();
						return fragment;
				case 2:
					Fragment fragment1 = new RootPanel();
					return fragment1;
				case 3:
					Fragment appFragment = new AppPanel();
					return appFragment;
			}
			return null;
		}

		@Override
		public int getCount() {
			return 4;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			String curr;
			switch (position) {
			case 0:
				return "File Gallery";
			case 1:
				if(nSFManager.nStack.size() == 1)
					return "/";
				else if(nSFManager.getCurrentDirectoryName().equals("sdcard0"))
					return "SD Card";
				return nSFManager.getCurrentDirectoryName();
			case 2:
				curr = nRFileManager.getCurrentDirectoryName();
				if(curr.equals("sdcard") || curr.equals("sdcard0") ||
				   curr.equalsIgnoreCase("0")|| curr.equalsIgnoreCase(PATH))
					return "SD Card";
				return nRFileManager.getCurrentDirectoryName();
			case 3:
				return "Your App Store";
			}
			return null;
		}
	}	
	
	
	/**
	 * 
	 * @author anurag
	 *
	 */
	public static class MediaPanel extends ListFragment {
		public MediaPanel() {
			
		}
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			if(media != null && mediaFileList != null)
				if(mediaFileList.size() == 0 && elementInFocus){
					media.setAdapter(new EmptyAdapter(getActivity(), R.layout.empty_adapter, EMPTY));
					media.setEnabled(false);
				}
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			media = getListView();
			media.setSelector(R.drawable.action_item_btn);
			if(elementInFocus == false)
				setListAdapter(adapter);
			else
				setListAdapter(element);
			media.setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
					if(elementInFocus){
						LAUNCH_INTENT = new Intent(getActivity() , LongClickDialog.class);
						if(SEARCH_FLAG)
							file0 = searchList.get(position);
						else
							file0 = mediaFileList.get(position);
						LAUNCH_INTENT.setData(Uri.parse(file0.getAbsolutePath()));
						startActivityForResult(LAUNCH_INTENT , 1);
					}
					return false;
				}
			});
			media.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,final int position, long id){
					
					/**
					 * THIS CLASS STARTS A THREAD IN BACKGROUND AND DOES TASK AND 
					 * DELIVERS THE RESULT TO UI THREAD THROUGH ASYNCTASK CLASS
					 * @author Anurag
					 *
					 */
					class Load extends AsyncTask<Void, Void, Void>{
						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							if(!elementInFocus){
								if(mediaFileList.size()>0){
									element = new MediaElementAdapter(getActivity(), R.layout.row_list_1, mediaFileList);
									if(MULTI_SELECT_GALLERY)
										element.MULTI_SELECT = true;
									else
										element.MULTI_SELECT = false;
									setListAdapter(element);
								}else{
									media.setAdapter(new EmptyAdapter(getActivity(), R.layout.empty_adapter, EMPTY));
									media.setEnabled(false);
								}
								elementInFocus = true;
								mFlipperBottom.showPrevious();
								mFlipperBottom.setAnimation(prevAnim());
							}
							super.onPostExecute(result);
						}

						@Override
						protected Void doInBackground(Void... params) {
							// TODO Auto-generated method stub
							if(elementInFocus){
								LAUNCH_INTENT = new Intent(getActivity() , OpenFiles.class);
								if(SEARCH_FLAG)
									file0 = searchList.get(position);
								else
									file0 = mediaFileList.get(pos=position);
								LAUNCH_INTENT.setData(Uri.parse(file0.getPath()));
								startActivity(LAUNCH_INTENT);
							}else{
								manager.clearList();
								loadMediaList(position);
							}
							return null;
						}
					}
					file0 = new File(PATH);
 					if((pos=position) == 7&& !elementInFocus){
						mViewPager.setCurrentItem(2);
						LAST_PAGE = 1;
						mFlipperBottom.showPrevious();
						mFlipperBottom.setAnimation(prevAnim());
 					}	
					/**else if(elementInFocus){
						LAUNCH_INTENT = new Intent(getActivity() , OpenFiles.class);
						if(SEARCH_FLAG)
							file0 = searchList.get(position);
						else
							file0 = mediaFileList.get(pos=position);
						LAUNCH_INTENT.setData(Uri.parse(file0.getPath()));
						startActivity(LAUNCH_INTENT);
					}else{
						manager.clearList();
						elementInFocus = true;
						loadMediaList(position);
						element = new MediaElementAdapter(getActivity(), R.layout.row_list_1, mediaFileList);
						setListAdapter(element);
					}*/
					else
						new Load().execute();
				}
			});
		}
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(requestCode == 1){
				if(resultCode == 0){
					if(SEARCH_FLAG){
						mVFlipper.showNext();
						mVFlipper.setAnimation(nextAnim());
						SEARCH_FLAG = false;
					}
					LAUNCH_INTENT = new Intent(getActivity() , OpenFiles.class);
					LAUNCH_INTENT.setData(Uri.fromFile(file0));
					startActivity(LAUNCH_INTENT);
				}else if(resultCode == 1){//This Request Performs The Copy File Operation
				    COPY_COMMAND = true;
					COPY_STRING = file0.getAbsolutePath();
				}else if(resultCode == 2){//This Request Performs The Cut File Operation
					CUT_COMMAND = true;
					COPY_STRING = file0.getAbsolutePath();
				}else if(resultCode == 3){
					Toast.makeText(getActivity(), "This Selection Is Not Valid", Toast.LENGTH_SHORT).show();
				}else if(resultCode == 4){
					// CREATES TH ZIP OF THE SELECTED FILE
					LAUNCH_INTENT = new Intent(getActivity(), ZipDialog.class);
					LAUNCH_INTENT.setData(Uri.parse("createZip"));
					String[] zipDetails = {getPathWithoutFilename(file0).getPath() ,file0.getAbsolutePath()};
					LAUNCH_INTENT.putExtra("zipDetails", zipDetails);
					startActivityForResult(LAUNCH_INTENT , 100);
				}else if(resultCode == 5){//this Request launches an activity to delete the selected file
					LAUNCH_INTENT = new Intent(getActivity() , PopupDialog.class);
					LAUNCH_INTENT.setData(Uri.parse(file0.getAbsolutePath()));
					startActivityForResult(LAUNCH_INTENT, 2);//Request 2 for delete result
				}else if(resultCode == 6){
					//Renaming The File
					editBox.setText(file0.getName());
					editBox.setTextColor(Color.WHITE);
					extBox.setVisibility(View.GONE);
					extText.setVisibility(View.GONE);
					mVFlipper.setAnimation(nextAnim());
					mVFlipper.showNext();
					mVFlipper.setAnimation(nextAnim());
					mVFlipper.showNext();
					RENAME_COMMAND = true;
					LinearLayout l = (LinearLayout)getActivity().findViewById(R.id.applyBtn);
					l.setVisibility(View.VISIBLE);
				}else if(resultCode == 7){
					// INITIALIZES THE BLUETOOTH CHOSSER CLASS
					// TO SEND A FILE FROM MEDIA PANEL
					LAUNCH_INTENT = new Intent(getActivity(), BluetoothChooser.class);
					LAUNCH_INTENT.setData(Uri.parse(file0.getPath()));
					startActivity(LAUNCH_INTENT);
				}else if(resultCode == 8){
					LAUNCH_INTENT = new Intent(getActivity(), FileProperties.class);
					LAUNCH_INTENT.setData(Uri.parse(file0.getPath()));
					startActivity(LAUNCH_INTENT);
				}
			}else if(requestCode == 100){
				// RETUNNS THE REQUEST CODE 100 WHEN ZIPPING IS DONE OR INTERUPTED
				if(resultCode == RESULT_OK)
					setAdapterAgain(0, getActivity());
			}else if(requestCode == 2){
				if(resultCode == 5)//Request Code of delete file
					setAdapterAgain(0, getActivity());
			}
			super.onActivityResult(requestCode, resultCode, data);
		}		
	}
	
	/**
	 * 
	 * @author Anurag
	 *
	 */
	public static class SimplePanel extends ListFragment {
		public SimplePanel(){
		}
		int spos;
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			if(simple!=null && sFiles !=null)
				if(sFiles.size() == 0){
					simple.setAdapter(new EmptyAdapter(getActivity(), R.layout.empty_adapter, EMPTY));
					simple.setEnabled(false);
				}
		}
		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			simple = getListView();
			simple.setSelector(R.drawable.action_item_btn);
			setListAdapter(nSimple);
			simple.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View view ,final int position, long id) {
					
					class Load extends AsyncTask<Void, Void, Void>{
						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							if(sFiles.size() == 0)
								setAdapterAgain(1, getActivity());
							else if(file.isDirectory()){
								mViewPager.setAdapter(mSectionsPagerAdapter);
								mViewPager.setCurrentItem(1);
							}							
							if(SEARCH_FLAG){
								SEARCH_FLAG = COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = false;
								mVFlipper.setAnimation(nextAnim());
								mVFlipper.showNext();
							}
							if(CREATE_FILE || RENAME_COMMAND){
								mVFlipper.setAnimation(nextAnim());
								mVFlipper.showNext();
								CREATE_FILE =  RENAME_COMMAND = false;
							}
						}

						@Override
						protected Void doInBackground(Void... params) {
							// TODO Auto-generated method stub
							if(SEARCH_FLAG && searchList.size()>0)
								file = searchList.get(position);
							else 
								file = sFiles.get(position);
							if(file.isDirectory()){
								MULTI_SIMPLE = nSimple.MULTI_FILES;
								nSimple.c = 0;
								nSimple.MULTI_FILES = new ArrayList<File>();
								if(!file.canRead()){
									nSFManager.nStack.push(file.getPath());
									sFiles.clear();
									BufferedReader reader = null; // errReader = null;
									try {
										reader = LinuxShell
												.execute("IFS='\n';CURDIR='"
														+ LinuxShell.getCmdPath(file.getAbsolutePath())
														+ "';for i in `ls $CURDIR`; do if [ -d $CURDIR/$i ]; then echo \"d $CURDIR/$i\";else echo \"f $CURDIR/$i\"; fi; done");
										File f;
										String line;
										while ((line = reader.readLine()) != null) {
											f = new File(line.substring(2));
											sFiles.add(f);
										}
										//nSimple = new FAdapterSimple(getActivity(), R.layout.row_list_1, sFiles);
										//mViewPager.setAdapter(mSectionsPagerAdapter);
										//mViewPager.setCurrentItem(1);
										nSimple.thumbselection = new boolean[sFiles.size()];
									}catch(Exception e){
										nSFManager.nStack.pop();
										sFiles = nSFManager.giveMeFileList();
										nSimple.thumbselection = new boolean[sFiles.size()];
										//nSimple = new FAdapterSimple(getActivity(), R.layout.row_list_1, sFiles);
										//mViewPager.setAdapter(mSectionsPagerAdapter);
										//mViewPager.setCurrentItem(1);
									}								
								}else{
									nSFManager.nStack.push(file.getAbsolutePath());
									sFiles = nSFManager.giveMeFileList();
									nSimple.thumbselection = new boolean[sFiles.size()];
								}							
							}else{
								// SELECTED file IS A FILE THAT HAS TO OPENED BY AN APP
								LAUNCH_INTENT = new Intent(getActivity() , OpenFiles.class);
								LAUNCH_INTENT.setData(Uri.fromFile(file));
								startActivity(LAUNCH_INTENT);								
							}
							
							return null;
						}
						
					}
			
					new Load().execute();
				}
			});
			
			simple.setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int position, long id) {
					spos = position;
					LAUNCH_INTENT = new Intent(getActivity() , LongClickDialog.class);
					if(SEARCH_FLAG)
						file = searchList.get(position);
					else
						file = sFiles.get(position);
					LAUNCH_INTENT.setData(Uri.parse(file.getName()));
					startActivityForResult(LAUNCH_INTENT , 1);
					
					return true;
				}
			});
		}
		
		@Override
		public void onActivityResult(int requestCode, int resultCode,Intent data) {
			if(requestCode == 1){
				if(resultCode == 0){
					/**
					 * THIS CLASS LOADS TH FILE LIST IN BACKGROUND AND DISPLAYS RESULT IN UI THREAD
					 * WHEN LONG CLICKED 
					 * @author Anurag
					 *
					 */
					class Load extends AsyncTask<Void, Void, Void>{
							@Override
							protected void onPostExecute(Void result) {
								// TODO Auto-generated method stub
								super.onPostExecute(result);
								//setAdapterAgain(2 , getActivity());
								if(file.isDirectory()){
									mViewPager.setAdapter(mSectionsPagerAdapter);
									mViewPager.setCurrentItem(1);
									simple.setSelectionFromTop(0, 0);
								}
								
								if(CREATE_FILE || RENAME_COMMAND){
									mVFlipper.setAnimation(nextAnim());
									mVFlipper.showNext();
									CREATE_FILE = RENAME_COMMAND = false;
								}
								
								if(SEARCH_FLAG){
									SEARCH_FLAG = COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = false;
									mVFlipper.setAnimation(nextAnim());
									mVFlipper.showNext();
								}
							}

							@Override
							protected void onProgressUpdate(Void... values) {
								// TODO Auto-generated method stub
								super.onProgressUpdate(values);
							}

							@Override
							protected Void doInBackground(Void... arg0) {
								// TODO Auto-generated method stub
								if(file.isDirectory()){
									MULTI_SIMPLE = nSimple.MULTI_FILES;
									nSimple.c = 0;
									nSimple.MULTI_FILES = new ArrayList<File>();
									if(!file.canRead()){
										nSFManager.nStack.push(file.getPath());
										sFiles.clear();
										BufferedReader reader = null; // errReader = null;
										try {
											reader = LinuxShell
													.execute("IFS='\n';CURDIR='"
															+ LinuxShell.getCmdPath(file.getAbsolutePath())
															+ "';for i in `ls $CURDIR`; do if [ -d $CURDIR/$i ]; then echo \"d $CURDIR/$i\";else echo \"f $CURDIR/$i\"; fi; done");
											File f;
											String line;
											while ((line = reader.readLine()) != null) {
												f = new File(line.substring(2));
												sFiles.add(f);
											}
											//nSimple = new FAdapterSimple(getActivity(), R.layout.row_list_1, sFiles);
											//mViewPager.setAdapter(mSectionsPagerAdapter);
											//mViewPager.setCurrentItem(1);
											nSimple.thumbselection = new boolean[sFiles.size()];
										}catch(Exception e){
											nSFManager.nStack.pop();
											sFiles = nSFManager.giveMeFileList();
											nSimple.thumbselection = new boolean[sFiles.size()];
											//nSimple = new FAdapterSimple(getActivity(), R.layout.row_list_1, sFiles);
											//mViewPager.setAdapter(mSectionsPagerAdapter);
											//mViewPager.setCurrentItem(1);
										}								
									}else{
										nSFManager.nStack.push(file.getAbsolutePath());
										sFiles = nSFManager.giveMeFileList();
										nSimple.thumbselection = new boolean[sFiles.size()];
									}							
								}else{
									// SELECTED file IS A FILE THAT HAS TO OPENED BY AN APP
									LAUNCH_INTENT = new Intent(getActivity() , OpenFiles.class);
									LAUNCH_INTENT.setData(Uri.fromFile(file));
									startActivity(LAUNCH_INTENT);								
								}
								mUseBackKey = false;
								//nRoot = new RootAdapter(context , R.layout.row_list_1 , nFiles);
								return null;
							}
						}
						new Load().execute();
							
				}else if (resultCode == 1){
					if(!file.canRead()){
						Toast.makeText(getActivity(),"An internal error " +
								"encountered,failed to copy",Toast.LENGTH_SHORT).show();
					}else{
						COPY_STRING = file.getPath();
						COPY_COMMAND = true;
					}
				}else if (resultCode == 2){
					if(!file.canRead()){
						Toast.makeText(getActivity(),"An internal error " +
								"encountered,failed to cut",Toast.LENGTH_SHORT).show();
					}else{
						COPY_STRING = file.getPath();
						CUT_COMMAND = true;
					}
				}					
				else if (resultCode == 3){
					if(COPY_COMMAND || CUT_COMMAND){
						CURRENT_ITEM = mViewPager.getCurrentItem();
						if(file.isDirectory()){
							String[] paths = {COPY_STRING , file.getPath()};
							if(COPY_COMMAND)
								LAUNCH_INTENT = new Intent(getActivity(), CopyPopupDialog.class);
							else 
								LAUNCH_INTENT = new Intent(getActivity(), CutFileDialog.class);
							LAUNCH_INTENT.putExtra("path", paths);
							startActivityForResult(LAUNCH_INTENT , 800);
						}else
							Toast.makeText(getActivity(), "Please Select A Valid Directory To Paste", Toast.LENGTH_SHORT).show();
					}else 
						Toast.makeText(getActivity(), "No File Is Selected To Paste", Toast.LENGTH_SHORT).show();
				}	
				else if (resultCode == 4){
					//LAUNCHES THE ACTIVITY FOR SETIING UP PROPERTIES FOR ZIPPING
					LAUNCH_INTENT = new Intent(getActivity(), ZipDialog.class);
					LAUNCH_INTENT.setData(Uri.parse("createZip"));
					String[] zipDetails = {nSFManager.getCurrentDirectory() ,file.getAbsolutePath()};
					LAUNCH_INTENT.putExtra("zipDetails", zipDetails);
					startActivityForResult(LAUNCH_INTENT , 100);
				}else if (resultCode == 5){
					    // IF FILE IS SYSTEM FILE THEN FILE IS GIVEN TO
						// DELETEMULTIPLEFILE CLASS WITH ACTION ROOT 
					    //THAT CAN DELETE SYSTEM FILES ALSO(ROOT FILES)....
						ArrayList<File> temp = new ArrayList<File>();
						temp.add(file);
						LAUNCH_INTENT = new Intent(getActivity(),DeleteMultipleFiles.class);
						LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>)temp);
						if(!file.canRead())
							LAUNCH_INTENT.setAction("root");
						else
							LAUNCH_INTENT.setAction("simple");
						startActivityForResult(LAUNCH_INTENT,2);
					
				}else if (resultCode == 6){
					/**Renaming The File
					TextView tv = (TextView)getActivity().findViewById(R.id.getNameForBox);
					tv.setText("Enter New Name");
					editBox.setText(file.getName());
					editBox.setTextColor(Color.WHITE);
					extBox.setVisibility(View.GONE);
					extText.setVisibility(View.GONE);
					// FLIPPER IS AT MAIN MENU , THEN IT IS DIRECTED TO EDIT MENU
						mVFlipper.setAnimation(nextAnim());
						mVFlipper.showNext();
						mVFlipper.showNext();
					CREATE_FILE = SEARCH_FLAG = COPY_COMMAND = CUT_COMMAND = false;
					RENAME_COMMAND = true;
					LinearLayout l = (LinearLayout)getActivity().findViewById(R.id.applyBtn);
					l.setVisibility(View.VISIBLE);*/
					Toast.makeText(getActivity(), "Renaming of system files is " +
							"blocked", Toast.LENGTH_SHORT).show();
				}else if (resultCode == 7){
					if(file.isDirectory())
						Toast.makeText(getActivity(), "To Transfer A Folder Create Zip", Toast.LENGTH_LONG).show();
					else if(file.isFile()){
						// INITIALIZES THE BLUETOOTH CHOSSER CLASS
						// TO SEND A FILE FROM ROOT PANEL
						LAUNCH_INTENT = new Intent(getActivity(), BluetoothChooser.class);
						LAUNCH_INTENT.setData(Uri.parse(file.getPath()));
						startActivity(LAUNCH_INTENT);
					}
					else
						Toast.makeText(getActivity(), "Not A Valid Item To Send", Toast.LENGTH_LONG).show();
				}
				else if (resultCode == 8){
					LAUNCH_INTENT = new Intent(getActivity() , FileProperties.class);
					LAUNCH_INTENT.setData(Uri.parse(file.getAbsolutePath()));
					startActivity(LAUNCH_INTENT);
				}
			}else if(requestCode == 100){
				// RETUNNS THE REQUEST CODE 100 WHEN ZIPPING IS DONE OR INTERUPTED
				if(resultCode == RESULT_OK)
					setAdapterAgain(1, getActivity());
			}
			else if(requestCode == 2){
				if(resultCode ==RESULT_OK){//IT RECEIVES THE RESULT CODE AFTER DELETING THE FILES 
					if(new File(nSFManager.getCurrentDirectory()).canRead())
						setAdapterAgain(1 , getActivity());
					else {
						//IF CURRENT DIRECTORY IS UNREADABLE THEN READ THROUGH 
						// BUFFER STREAM...
						sFiles.remove(spos);
						simple.setAdapter(nSimple);
					}
					Toast.makeText(getActivity(), "File Deleted Succesfully", Toast.LENGTH_SHORT).show();
				}
			}
			else if(requestCode == 800){
				if(resultCode == 80){
					/**
					 * THIS INTENT RESULT IS FIRED WHEN COPY DIALOG WAS POPED UP AND A
					 * FILE WITH SAME NAME ALREADY EXISTED,THEN USER CHOSSES TO OVERWRITE
					 * THEM AFTER DELETING THOSE FILES , ANOTHER COPY DIALOG IS FIRED TO 
					 * ACTUALLY COPY THOSE FILES
					 */
					String[] paths = {COPY_STRING , file.getPath()};
					if(COPY_COMMAND)
						LAUNCH_INTENT = new Intent(getActivity(), CopyPopupDialog.class);
					else 
						LAUNCH_INTENT = new Intent(getActivity(), CutFileDialog.class);
					LAUNCH_INTENT.putExtra("path", paths);
					COPY_STRING = null;
					CUT_COMMAND = COPY_COMMAND = false;
					startActivityForResult(LAUNCH_INTENT , 800);				
				}else if(resultCode == 70){
					/**
					 * WHEN COPYING IS SUCCESSFUL THEN ADAPTER IS SET AGAIN
					 */
					COPY_STRING = null;
					CUT_COMMAND = COPY_COMMAND = false;
					setAdapterAgain(CURRENT_ITEM, getActivity());
				}else if(resultCode == 90){
					/**
					 * COPYING WAS CANCELLED IN MIDDLE
					 */
					COPY_STRING = null;
					CUT_COMMAND = COPY_COMMAND = false;
					setAdapterAgain(CURRENT_ITEM, getActivity());
				}else if(resultCode == 100){
					/**
					 * COPYING WAS INTERUPTED BCS OF AN EXCEPTION
					 */
					COPY_STRING = null;
					CUT_COMMAND = COPY_COMMAND = false;
					setAdapterAgain(CURRENT_ITEM, getActivity());
					LAUNCH_INTENT = new Intent(getActivity(), DialogBox.class );
					LAUNCH_INTENT.setData(Uri.parse(data.getData().toString()));
					startActivity(LAUNCH_INTENT);
				}
			}
			super.onActivityResult(requestCode, resultCode, data);
		}
	}
	
	/**
	 * 
	 * @author Anurag
	 *
	 */
	public static class RootPanel extends ListFragment  implements OnItemLongClickListener{
		public RootPanel(){
		}
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			
			if(root!=null && nFiles !=null)
				if(nFiles.size() == 0){
					root.setAdapter(new EmptyAdapter(getActivity(), R.layout.empty_adapter, EMPTY));
					root.setEnabled(false);
					//Toast.makeText(getActivity(), "LOAad", Toast.LENGTH_SHORT).show();
				}
			super.onResume();
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			root = getListView();
			root.setSelector(R.drawable.action_item_btn);
			setListAdapter(nRoot);
			root.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,final int position, long arg3) {
					// TODO Auto-generated method stub
					
					class Load extends AsyncTask<Void, Void, Void>{
						@Override
						protected void onPostExecute(Void result) {
							// TODO Auto-generated method stub
							super.onPostExecute(result);
							//setAdapterAgain(2 , getActivity());
							if(nFiles.size() == 0){
								setAdapterAgain(2, getActivity());
							}else if(file2.isDirectory()){
								mViewPager.setAdapter(mSectionsPagerAdapter);
								mViewPager.setCurrentItem(2);
								root.setSelectionFromTop(0, 0);
							}
							
							if(CREATE_FILE || RENAME_COMMAND){
								mVFlipper.setAnimation(nextAnim());
								mVFlipper.showNext();
								CREATE_FILE = RENAME_COMMAND = false;
							}
							
							if(SEARCH_FLAG){
								SEARCH_FLAG = COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = false;
								mVFlipper.setAnimation(nextAnim());
								mVFlipper.showNext();
							}
						}

						@Override
						protected void onProgressUpdate(Void... values) {
							// TODO Auto-generated method stub
							super.onProgressUpdate(values);
						}

						@SuppressWarnings("static-access")
						@Override
						protected Void doInBackground(Void... arg0) {
							// TODO Auto-generated method stub
							if(SEARCH_FLAG )
								file2 = searchList.get(position);
							else
								file2 = nFiles.get(position);
							if(file2.isDirectory()){
								nRFileManager.nStack.push(file2.getPath());
								nFiles = nRFileManager.giveMeFileList();
								
								/**
								 * Initializes the selected check boxes to zero 
								 */
								MULTI_ROOT = nRoot.MULTI_FILES;
								nRoot.thumbselection = new boolean[nFiles.size()];
								nRoot.C = 0;
								nRoot.MULTI_FILES = new ArrayList<File>();
							}else if(file2.isFile()){
								LAUNCH_INTENT = new Intent();
								LAUNCH_INTENT.setAction(LAUNCH_INTENT.ACTION_VIEW);
								LAUNCH_INTENT = new Intent(getActivity(), OpenFiles.class);
								LAUNCH_INTENT.setData(Uri.parse(file2.getAbsolutePath()));
								startActivity(LAUNCH_INTENT);
							}
							mUseBackKey = false;
							//nRoot = new RootAdapter(context , R.layout.row_list_1 , nFiles);
							return null;
						}
						
					}
					new Load().execute();
					
				}
			});
			root.setOnItemLongClickListener(this);
		}
		
		@Override
		public void onActivityResult(int requestCode, int resultCode,Intent data) {
			if(requestCode == 1){
				if(resultCode == 0){
						class Load extends AsyncTask<Void, Void, Void>{
							@Override
							protected void onPostExecute(Void result) {
								// TODO Auto-generated method stub
								super.onPostExecute(result);
								//setAdapterAgain(2 , getActivity());
								if(file2.isDirectory()){
									mViewPager.setAdapter(mSectionsPagerAdapter);
									mViewPager.setCurrentItem(2);
									root.setSelectionFromTop(0, 0);
								}
								
								if(CREATE_FILE || RENAME_COMMAND){
									mVFlipper.setAnimation(nextAnim());
									mVFlipper.showNext();
									CREATE_FILE = RENAME_COMMAND = false;
								}
								
								if(SEARCH_FLAG){
									SEARCH_FLAG = COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = false;
									mVFlipper.setAnimation(nextAnim());
									mVFlipper.showNext();
								}
							}

							@Override
							protected void onProgressUpdate(Void... values) {
								// TODO Auto-generated method stub
								super.onProgressUpdate(values);
							}

							@Override
							protected Void doInBackground(Void... arg0) {
								// TODO Auto-generated method stub
								if(file2.isDirectory()){
									nRFileManager.nStack.push(file2.getPath());
									nFiles = nRFileManager.giveMeFileList();
								}else if(file2.isFile()){
									LAUNCH_INTENT = new Intent();
									LAUNCH_INTENT.setAction(LAUNCH_INTENT.ACTION_VIEW);
									LAUNCH_INTENT = new Intent(getActivity(), OpenFiles.class);
									LAUNCH_INTENT.setData(Uri.parse(file2.getAbsolutePath()));
									startActivity(LAUNCH_INTENT);
								}
								mUseBackKey = false;
								//nRoot = new RootAdapter(context , R.layout.row_list_1 , nFiles);
								return null;
							}
							
						}
						new Load().execute();
						
						
					
				}else if (resultCode == 1){
					COPY_STRING = file2.getPath();
					COPY_COMMAND = true;
				}else if (resultCode == 2){
					COPY_STRING = file2.getPath();
					CUT_COMMAND = true;
				}					
				else if (resultCode == 3){
					if(COPY_COMMAND || CUT_COMMAND){
						CURRENT_ITEM = mViewPager.getCurrentItem();
						if(file2.isDirectory()){
							String[] paths = {COPY_STRING , file2.getPath()};
							if(COPY_COMMAND)
								LAUNCH_INTENT = new Intent(getActivity(), CopyPopupDialog.class);
							else 
								LAUNCH_INTENT = new Intent(getActivity(), CutFileDialog.class);
							LAUNCH_INTENT.putExtra("path", paths);
							startActivityForResult(LAUNCH_INTENT , 800);
						}else
							Toast.makeText(getActivity(), "Please Select A Valid Directory To Paste", Toast.LENGTH_SHORT).show();
					}else 
						Toast.makeText(getActivity(), "No File Is Selected To Paste", Toast.LENGTH_SHORT).show();
				}	
				else if (resultCode == 4){
					//LAUNCHES THE ACTIVITY FOR SETIING UP PROPERTIES FOR ZIPPING
					LAUNCH_INTENT = new Intent(getActivity(), ZipDialog.class);
					LAUNCH_INTENT.setData(Uri.parse("createZip"));
					String[] zipDetails = {nRFileManager.getCurrentDirectory() ,file2.getAbsolutePath()};
					LAUNCH_INTENT.putExtra("zipDetails", zipDetails);
					startActivityForResult(LAUNCH_INTENT , 100);
				}else if (resultCode == 5){
					//RESULT CODE FOR DELETING FILE
					LAUNCH_INTENT = new Intent(getActivity() , PopupDialog.class);
					LAUNCH_INTENT.setData(Uri.parse(file2.getAbsolutePath())); 
					startActivityForResult(LAUNCH_INTENT , 2);//should return 5 as result
				}else if (resultCode == 6){
					//Renaming The File
					TextView tv = (TextView)getActivity().findViewById(R.id.getNameForBox);
					tv.setText("Enter New Name");
					editBox.setText(file2.getName());
					editBox.setTextColor(Color.WHITE);
					extBox.setVisibility(View.GONE);
					extText.setVisibility(View.GONE);
					// FLIPPER IS AT MAIN MENU , THEN IT IS DIRECTED TO EDIT MENU
					mVFlipper.setAnimation(nextAnim());
					mVFlipper.showNext();
					mVFlipper.showNext();
					CREATE_FILE = SEARCH_FLAG = COPY_COMMAND = CUT_COMMAND = false;
					RENAME_COMMAND = true;
					LinearLayout l = (LinearLayout)getActivity().findViewById(R.id.applyBtn);
					l.setVisibility(View.VISIBLE);
				}else if (resultCode == 7){
					if(file2.isDirectory())
						Toast.makeText(getActivity(), "A Folder Cannot Be Shared,Create Zip", Toast.LENGTH_LONG).show();
					else if(file2.isFile()){
						//Toast.makeText(getActivity(), "Still Coding Needs To Be Done", Toast.LENGTH_LONG).show();
						LAUNCH_INTENT = new Intent(getActivity(), BluetoothChooser.class);
						LAUNCH_INTENT.setData(Uri.fromFile(file2));
						startActivity(LAUNCH_INTENT);
					}
					else
						Toast.makeText(getActivity(), "Not A Valid Item To Send", Toast.LENGTH_LONG).show();
						
				}else if (resultCode == 8){
					// LAUNCHES AN ACTIVITY DISPLAYING THE PROPERTIES OF SELECTED FILE
					LAUNCH_INTENT = new Intent(getActivity() , FileProperties.class);
					LAUNCH_INTENT.setData(Uri.parse(file2.getAbsolutePath()));
					startActivity(LAUNCH_INTENT);
				}
			}else if(requestCode == 100){
				// RETUNNS THE REQUEST CODE 100 WHEN ZIPPING IS DONE OR INTERUPTED
				if(resultCode == RESULT_OK)
					setAdapterAgain(2, getActivity());
			}			
			else if(requestCode == 2){
				if(resultCode ==5){//IT RECEIVES THE RESULT CODE AFTER DELETING THE FILES 
					SEARCH_FLAG = COPY_COMMAND = CUT_COMMAND =  CREATE_FILE = RENAME_COMMAND = false;
					setAdapterAgain(2 , getActivity());
				}
			}
			else if(requestCode == 800){
				if(resultCode == 80){
					/**
					 * THIS INTENT RESULT IS FIRED WHEN COPY DIALOG WAS POPED UP AND A
					 * FILE WITH SAME NAME ALREADY EXISTED,THEN USER CHOSSES TO OVERWRITE
					 * THEM, AFTER DELETING THOSE FILES , ANOTHER COPY DIALOG IS FIRED TO 
					 * ACTUALLY COPY THOSE FILES
					 */
					String[] paths = {COPY_STRING , file2.getPath()};
					if(COPY_COMMAND)
						LAUNCH_INTENT = new Intent(getActivity(), CopyPopupDialog.class);
					else 
						LAUNCH_INTENT = new Intent(getActivity(), CutFileDialog.class);
					LAUNCH_INTENT.putExtra("path", paths);
					COPY_STRING = null;
					CUT_COMMAND = COPY_COMMAND = false;
					startActivityForResult(LAUNCH_INTENT , 800);				
				}else if(resultCode == 70){
					/**
					 * WHEN COPYING IS SUCCESSFUL THEN ADAPTER IS SET AGAIN
					 */
					COPY_STRING = null;
					CUT_COMMAND = COPY_COMMAND = false;
					setAdapterAgain(CURRENT_ITEM, getActivity());
				}else if(resultCode == 90){
					/**
					 * COPYING WAS CANCELLED IN MIDDLE
					 */
					COPY_STRING = null;
					CUT_COMMAND = COPY_COMMAND = false;
					setAdapterAgain(CURRENT_ITEM, getActivity());
				}else if(resultCode == 100){
					/**
					 * COPYING WAS INTERUPTED BCS OF AN EXCEPTION
					 */
					COPY_STRING = null;
					CUT_COMMAND = COPY_COMMAND = false;
					setAdapterAgain(CURRENT_ITEM, getActivity());
					LAUNCH_INTENT = new Intent(getActivity(), DialogBox.class );
					LAUNCH_INTENT.setData(Uri.parse(data.getData().toString()));
					startActivity(LAUNCH_INTENT);
				}
			}
			super.onActivityResult(requestCode, resultCode, data);
		}

		@Override
		public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
				int position, long arg3) {
			// TODO Auto-generated method stub
			
			LAUNCH_INTENT = new Intent(getActivity() , LongClickDialog.class);
			if(SEARCH_FLAG)
				file2 = searchList.get(position);
			else
				file2 = nFiles.get(position);
			LAUNCH_INTENT.setData(Uri.parse(file2.getName()));
			startActivityForResult(LAUNCH_INTENT , 1);
			
			return false;
		}

		
	}
		
	/**
	 * 
	 * @author Anurag
	 *
	 */
	public static class AppPanel extends ListFragment{
		public AppPanel(){
		}
		
		@Override
		public void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			if(APP_LIST_VIEW != null && nList != null)
				if(nList.size() == 0){
					APP_LIST_VIEW.setAdapter(new EmptyAdapter(getActivity(), R.layout.empty_adapter, EMPTY_APPS));
					APP_LIST_VIEW.setEnabled(false);
				}
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
			APP_LIST_VIEW = getListView();
			APP_LIST_VIEW.setSelector(R.drawable.action_item_btn);
			
			
			setListAdapter(nAppAdapter);
			if(nList.size() == 0){
				APP_LIST_VIEW.setAdapter(new EmptyAdapter(getActivity(), R.layout.empty_adapter, EMPTY_APPS));
				APP_LIST_VIEW.setEnabled(false);
			}
			APP_LIST_VIEW.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,int position, long id) {
					info = nList.get(position);
					pos = position;
					LAUNCH_INTENT = new Intent(getActivity() , AppBackupDialog.class);
					LAUNCH_INTENT.setData(Uri.parse(info.packageName));
					if(new File(PATH + "/File Quest-Beta").exists())
						startActivityForResult(LAUNCH_INTENT , 2);
					else
						startActivityForResult(LAUNCH_INTENT , 3);
				}
			});
			APP_LIST_VIEW.setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,int position, long id) {
					pos = position; // Taking pos to get the index value for launching an app from nList
					info = nList.get(position);
					LAUNCH_INTENT = new Intent(getActivity() , LongClickDialog.class);
					LAUNCH_INTENT.setData(Uri.parse("Select Action"));
					startActivityForResult(LAUNCH_INTENT , 1);
					return false;
				}
			});
		}
				
		@Override
		public void onActivityResult(int requestCode, int resultCode, Intent data) {
			if(requestCode == 2){
				if(resultCode == RESULT_OK){
					//nList = nManager.giveMeAppList();
					nAppAdapter = new AppAdapter(getActivity(), R.layout.row_list_1, nList);
					if(MULTI_SELECT_APPS){
						nAppAdapter.MULTI_SELECT = true;
						nAppAdapter.thumbSelection = new boolean[nList.size()];
					}	
					APP_LIST_VIEW.setAdapter(nAppAdapter);
					APP_LIST_VIEW.setSelection(pos);
				}
			}
			if(requestCode == 3){
				if(resultCode == RESULT_OK){
					setAdapterAgain(3, getActivity());
				}
			}
			
			else if(requestCode == 1){
				
				if(resultCode == 0){// result is  0 when an app has to be launched 
					LAUNCH_INTENT = new Intent();
					LAUNCH_INTENT = getActivity().getPackageManager().getLaunchIntentForPackage(nList.get(pos).packageName);
					try{
						startActivity(LAUNCH_INTENT);	
						/**
						 * TRY BLOCK HAS BEEN USED IF AN APP LIKE UNLOCKER APP DONOT
						 * HAVE ACTIVITY TO SHOW ,THEN IT THROWS RUNTIME ERROR
						 */
					}catch(RuntimeException e){
						Toast.makeText(getActivity(), "This App Donot Have Activity To Launch",
								Toast.LENGTH_SHORT).show();
					}
				}
				
				else if(resultCode == 1){//This result unisntalls the apk file
					LAUNCH_INTENT = new Intent();
					LAUNCH_INTENT.setAction(LAUNCH_INTENT.ACTION_DELETE);
					LAUNCH_INTENT.setData(Uri.parse("package:"+info.packageName));
					startActivity(LAUNCH_INTENT);
				}else if(resultCode == 2){
					info = nList.get(pos);
					LAUNCH_INTENT = new Intent(getActivity() , AppBackupDialog.class);
					LAUNCH_INTENT.setData(Uri.parse(info.packageName));
					if(new File(PATH + "/File Quest-Beta").exists())
						startActivityForResult(LAUNCH_INTENT , 2);
					else 
						startActivityForResult(LAUNCH_INTENT , 3);
				}else if(resultCode == 3){
					if(nManager.backupExists(info) == 0){
						Toast.makeText(getActivity(),
								"No Backup To Delete", Toast.LENGTH_SHORT).show();
					}else{
						PackageInfo i = null;
						PackageManager m = getActivity().getPackageManager();
						try {
							i = m.getPackageInfo(info.packageName, 0);
						} catch (NameNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						File del = new File(PATH + "/File Quest-Beta/AppBackup/" + info.loadLabel(m) + "-v" + i.versionName + ".apk");
						if(del.delete()){
							Toast.makeText(getActivity(),
									"Backup Has Been Deleted", Toast.LENGTH_SHORT).show();
							nList = nManager.giveMeAppList();
							nAppAdapter = new AppAdapter(getActivity(), R.layout.row_list_1, nList);
							if(MULTI_SELECT_APPS)
								nAppAdapter.MULTI_SELECT = true;
							APP_LIST_VIEW.setAdapter(nAppAdapter);
							APP_LIST_VIEW.setSelection(pos);
						}else
							Toast.makeText(getActivity(),
									"Unable To Delete The Backup", Toast.LENGTH_SHORT).show();
					}
				}else if(resultCode == 4){
					ApplicationInfo i = nManager.giveMeAppList().get(pos);
					if((i.flags & ApplicationInfo.FLAG_SYSTEM) == 1 && 
							   (i.flags & 0x80) == 0 && 
							   i.flags != 0){
						LAUNCH_INTENT = new Intent(getActivity() , SystemFlash.class);
						startActivity(LAUNCH_INTENT);
					}else{
						LAUNCH_INTENT = new Intent(getActivity() , DialogBox.class);
						LAUNCH_INTENT.setData(Uri.parse("FlashableZip"));
						startActivityForResult(LAUNCH_INTENT ,4);
					}					
				}else if(resultCode == 5){
					//INITIALIZES THE BLUETOOTH CHOSSER CLASS TO SEND
					//AN APP 
					LAUNCH_INTENT = new Intent(getActivity(), BluetoothChooser.class);
					LAUNCH_INTENT.setData(Uri.parse(info.sourceDir));
					startActivity(LAUNCH_INTENT);
				}else if(resultCode == 6){
					// DISPLAYS THE SELECTED APP PROPERTIES
					LAUNCH_INTENT = new Intent(getActivity(), AppProperties.class);
					LAUNCH_INTENT.setData(Uri.parse(info.packageName));
					startActivity(LAUNCH_INTENT);
				}
			}else if(requestCode == 4){
				if(resultCode == 1){
						LAUNCH_INTENT = new Intent(getActivity() , ZipDialog.class);
						LAUNCH_INTENT.setData(Uri.parse(info.packageName));
						if(new File(PATH + "/File Quest-Beta").exists())
							startActivityForResult(LAUNCH_INTENT , 6);
						else
							startActivityForResult(LAUNCH_INTENT , 7);
				}
			}
			
			else if(requestCode == 6){
				if(resultCode == RESULT_OK){
					nList = nManager.giveMeAppList();
					nAppAdapter = new AppAdapter(getActivity(), R.layout.row_list_1, nList);
					APP_LIST_VIEW.setAdapter(nAppAdapter);
					APP_LIST_VIEW.setSelection(pos);
				}				
			}
			else if(requestCode == 7){
				if(resultCode == RESULT_OK)
					setAdapterAgain(3, getActivity());
			}
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(final View v){
		CURRENT_ITEM = mViewPager.getCurrentItem();
		switch(v.getId()){
			case R.id.bottom_Quit:
				QuickAction ad = new QuickAction(getApplicationContext());
				ActionItem adi = new ActionItem(0, "Quit The App");
				ad.addActionItem(adi);
				adi = new ActionItem(getResources().getDrawable(R.drawable.org_anurag_questbrowser_underline));
				ad.addActionItem(adi);
				adi = new ActionItem(1, "    Yes", getResources().getDrawable(R.drawable.ic_launcher_apply));
				ad.addActionItem(adi);
				adi = new ActionItem(2, "    No", getResources().getDrawable(R.drawable.ic_launcher_quit));
				ad.addActionItem(adi);
				ad.setOnActionItemClickListener(new OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos, int actionId) {
						// TODO Auto-generated method stub
						if(actionId == 1){
							TaskerActivity.this.finish();
						}
					}
				});
				ad.show(v);
				break;
		
		
			case R.id.bottom_refresh:
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				Toast.makeText(getBaseContext(), "View Refreshed", 
						Toast.LENGTH_SHORT).show();
				break;
		
			case R.id.bottom_paste:
			    if(CURRENT_ITEM == 0){
			    	QuickAction ac = new QuickAction(getBaseContext());
					ActionItem it = new ActionItem(0, "Paste Option Is Not Allowed Here");
					ac.addActionItem(it);
					ac.show(v);
			    }
				else if((CUT_COMMAND || COPY_COMMAND) && COPY_STRING !=null){
					if(CURRENT_ITEM ==2){
						String[] paths = {COPY_STRING , nRFileManager.getCurrentDirectory()};
						if(COPY_COMMAND)
							LAUNCH_INTENT = new Intent(getBaseContext(), CopyPopupDialog.class);
						else 
							LAUNCH_INTENT = new Intent(getBaseContext(), CutFileDialog.class);
						LAUNCH_INTENT.putExtra("path", paths);
						startActivityForResult(LAUNCH_INTENT , 800);
					}else{
						String[] paths = {COPY_STRING , nSFManager.getCurrentDirectory()};
						if(COPY_COMMAND)
							LAUNCH_INTENT = new Intent(getBaseContext(), CopyPopupDialog.class);
						else 
							LAUNCH_INTENT = new Intent(getBaseContext(), CutFileDialog.class);
						LAUNCH_INTENT.putExtra("path", paths);
						startActivityForResult(LAUNCH_INTENT , 800);
					}
				}else if(MULTIPLE_COPY||MULTIPLE_CUT){
					/*
					 * THIS CONDITION HANDLES PASTING OF FILES FOR ROOT AND SD
					 * CARD PANEL 
					 * THIS DOESNOT HANDLE FOR FILES PASTING FROM FILE GALLERY
					 */
					String DEST = null;
					LAUNCH_INTENT = new Intent(getBaseContext(),MultipleCopy.class);
					if(CURRENT_ITEM == 2){
						DEST = nRFileManager.getCurrentDirectory();
						LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) MULTI_ROOT);
					}else if(CURRENT_ITEM == 1){
						DEST = nSFManager.getCurrentDirectory();
						LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) MULTI_SIMPLE);
					}
					LAUNCH_INTENT.setAction(DEST);
					if(MULTIPLE_CUT)
						LAUNCH_INTENT.putExtra("cut", "cut");
					else
						LAUNCH_INTENT.putExtra("cut", "copy");
					MULTIPLE_COPY = MULTIPLE_CUT = false;
					startActivityForResult(LAUNCH_INTENT, 199);
				}else if(MULTIPLE_COPY_GALLERY||MULTIPLE_CUT_GALLERY){
					/*
					 * THIS CONDITION HANDLES PASTING OF FILES FROM GALLERY
					 * 
					 * 
					 */
					String DEST = null;
					LAUNCH_INTENT = new Intent(getBaseContext(),MultipleCopy.class);
					if(CURRENT_ITEM == 2)
						DEST = nRFileManager.getCurrentDirectory();
					else if(CURRENT_ITEM == 1)
						DEST = nSFManager.getCurrentDirectory();
					LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) MULTI_GALLERY);
					
					LAUNCH_INTENT.setAction(DEST);
					if(MULTIPLE_CUT_GALLERY)
						LAUNCH_INTENT.putExtra("cut", "cut");
					else
						LAUNCH_INTENT.putExtra("cut", "copy");
					MULTIPLE_COPY_GALLERY = MULTIPLE_CUT_GALLERY = false;
					startActivityForResult(LAUNCH_INTENT, 199);
				}else{
					QuickAction ac = new QuickAction(getBaseContext());
					ActionItem it = new ActionItem(0, "No files is/are selected to paste here");
					ac.addActionItem(it);
					ac.show(v);
				}
				break;  
				
			case R.id.bottom_copy:
				OPERATION_ON_MULTI_SELECT_FILES(CURRENT_ITEM, 5 ,"First select this multi select mode for current panel,then select files ");
				break;
				
				
			case R.id.bottom_cut:
				OPERATION_ON_MULTI_SELECT_FILES(CURRENT_ITEM, 2 ,
						"First select this multi select mode for current panel,then select files ");
				break;	
				
			case R.id.bottom_zip:
				OPERATION_ON_MULTI_SELECT_FILES(CURRENT_ITEM, 3,"First select this multi select mode for current panel,then select files ");
				break;	
				
			case R.id.bottom_delete:
				OPERATION_ON_MULTI_SELECT_FILES(CURRENT_ITEM, 4,"First select this multi select mode for current panel,then select files");
				break;	
				
		
			case R.id.appSettings:
				ShowMenu();
				break;
				
			
			case R.id.bottom_multi:
				{	QuickAction action = new QuickAction(getBaseContext());
					ActionItem item = new ActionItem();
					
					if(element == null || !elementInFocus){
						item = new ActionItem(-1, "Enable for File Gallery",
								getResources().getDrawable(R.drawable.ic_launcher_images));
						action.addActionItem(item);
					}else{
						if(element.MULTI_SELECT)
							item = new ActionItem(0, "Enabled for File Gallery",
									getResources().getDrawable(R.drawable.ic_launcher_apply));
						else
							item = new ActionItem(0, "Enable for File Gallery",
									getResources().getDrawable(R.drawable.ic_launcher_images));
						action.addActionItem(item);
					}
					
					
					if(nSimple.MULTI_SELECT)
						item = new ActionItem(1, "Enabled for /", 
								getResources().getDrawable(R.drawable.ic_launcher_apply));
					else
						item = new ActionItem(1, "Enable for /", 
								getResources().getDrawable(R.drawable.ic_launcher_system));
					action.addActionItem(item);
					
					if(nRoot.MULTI_SELECT)
						item = new ActionItem(2, "Enabled for Sdcard", 
								getResources().getDrawable(R.drawable.ic_launcher_apply));
					else
						item = new ActionItem(2, "Enable for Sdcard", 
								getResources().getDrawable(R.drawable.ic_launcher_sdcard));
					action.addActionItem(item);
					
					if(MULTI_SELECT_APPS)
						item = new ActionItem(3, "Enabled for App Store",
								getResources().getDrawable(R.drawable.ic_launcher_apply));
					else
						item = new ActionItem(3, "Enable for App Store",
								getResources().getDrawable(R.drawable.ic_launcher_apk));
					action.addActionItem(item);
					action.show(v);
					action.setOnActionItemClickListener(new OnActionItemClickListener() {
						@Override
						public void onItemClick(QuickAction source, int pos, int actionId) {
							// TODO Auto-generated method stub
							switch(actionId){
								
								case -1:
										{
											QuickAction ac = new QuickAction(getBaseContext());
											ActionItem it = new ActionItem(0, "First Go to FILE GALLERY " +
													"and select a category then enable MULTISELECT MODE for that");
											ac.addActionItem(it);
											ac.show(v);
										}
										break;
								case 0:
										if(element.MULTI_SELECT){
											element = new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList);
											element.thumbselection = new boolean[mediaFileList.size()];
											MULTI_SELECT_GALLERY = element.MULTI_SELECT = false;
											media.setAdapter(element);
										}else{
											element = new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList);
											element.thumbselection = new boolean[mediaFileList.size()];
											MULTI_SELECT_GALLERY = element.MULTI_SELECT = true;
											media.setAdapter(element);
											mViewPager.setCurrentItem(0);
										}
										break;	
							
								case 1:
										if(nSimple.MULTI_SELECT){
											nSimple = new SimpleAdapter(getBaseContext(), R.layout.row_list_1, sFiles);
											nSimple.thumbselection = new boolean[sFiles.size()];
											nSimple.MULTI_SELECT = false;
											simple.setAdapter(nSimple);
										}else{
											nSimple = new SimpleAdapter(getBaseContext(), R.layout.row_list_1, sFiles);
											nSimple.thumbselection = new boolean[sFiles.size()];
											nSimple.MULTI_SELECT = true;
											simple.setAdapter(nSimple);
											mViewPager.setCurrentItem(1);
										}									
										break;
								case 2:
										if(nRoot.MULTI_SELECT){
											nRoot = new RootAdapter(getBaseContext(), R.layout.row_list_1, nFiles);
											nRoot.thumbselection = new boolean[nFiles.size()];
											nRoot.MULTI_SELECT = false;
											root.setAdapter(nRoot);
										}else{
											nRoot = new RootAdapter(getBaseContext(), R.layout.row_list_1, nFiles);
											nRoot.thumbselection = new boolean[nFiles.size()];
											nRoot.MULTI_SELECT = true;
											root.setAdapter(nRoot);
											mViewPager.setCurrentItem(2);
										}									    
									break;
									
								case 3:
										if(MULTI_SELECT_APPS)
											MULTI_SELECT_APPS = nAppAdapter.MULTI_SELECT = false;
										else
											MULTI_SELECT_APPS = nAppAdapter.MULTI_SELECT = true;
										APP_LIST_VIEW.setAdapter(nAppAdapter);
										APP_LIST_VIEW.setSelection(pos);
										mViewPager.setCurrentItem(3);				
										break;
							}
						}
					});
				}
				break;
				
			case R.id.bottom_filter:
				{
					/**
					 * THIS BUTTON IS DISABLED ... VISIBILITY-GONE
					 * AS WHEN THIS FUNCTION IS ENABLED MAKES THE APP WORK LAGGY...
					 */
					QuickAction action = new QuickAction(getBaseContext());
					ActionItem it = new ActionItem(1, "App with no backups",
							getResources().getDrawable(R.drawable.ic_launcher_no_backup));
					action.addActionItem(it);
					it = new ActionItem(2, "Apps with backup",
							getResources().getDrawable(R.drawable.ic_launcher_backupall));
					action.addActionItem(it);
					it = new ActionItem(3, "App Size above 10 MB",
							getResources().getDrawable(R.drawable.ic_launcher_10_mb));
					action.addActionItem(it);
					it = new ActionItem(4, "App Size below 5 MB",
							getResources().getDrawable(R.drawable.ic_launcher_system));
					action.addActionItem(it);
					action.show(v);
					action.setOnActionItemClickListener(new OnActionItemClickListener() {
						@Override
						public void onItemClick(QuickAction source, int pos, int actionId) {
							// TODO Auto-generated method stub
							switch(actionId){
										case 1:
											    
												break;
										case 2:	
												
												
												break;
							}
						}
					});
				}
				break;
				
			case R.id.bottom_multi_send_app:
				if(MULTI_SELECT_APPS){
					if(nAppAdapter.C > 0){
						LAUNCH_INTENT = new Intent(getBaseContext() , MultiSendApps.class);
						LAUNCH_INTENT.putParcelableArrayListExtra("list", nAppAdapter.MULTI_APPS);
						try{					
							startActivity(LAUNCH_INTENT);
						}catch(Exception e){
							Toast.makeText(getBaseContext(), "Unable To Send Right now try later", Toast.LENGTH_SHORT).show();
						}
					}else{
						Toast.makeText(getBaseContext(),
								"First select some apps", Toast.LENGTH_SHORT).show();
					}
				}else{
					QuickAction ac = new QuickAction(getBaseContext());
					ActionItem it = new ActionItem(0, "First Enable Multi Select mode for 'Your App Store '" +
							"then you can send multiple apps");
					ac.addActionItem(it);
					ac.show(v);
				}
				break;
			case R.id.bottom_multi_select_backup:
				if(MULTI_SELECT_APPS){
					if(nAppAdapter.C > 0){
						LAUNCH_INTENT = new Intent(getBaseContext(), MultileAppBackup.class);
						LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<?extends Parcelable>)nAppAdapter.MULTI_APPS);
						LAUNCH_INTENT.putExtra("total", sto);
						LAUNCH_INTENT.putExtra("avail", sav);
						LAUNCH_INTENT.setAction(""+nAppAdapter.C);
						startActivityForResult(LAUNCH_INTENT , 98);
					}else{
						Toast.makeText(getBaseContext(),
								"First select some apps", Toast.LENGTH_SHORT).show();
					}
				}else{
					QuickAction ac = new QuickAction(getBaseContext());
					ActionItem it = new ActionItem(0, "First Enable Multi Select mode for 'Your App Store '" +
							"then you can do multiple backups");
					ac.addActionItem(it);
					ac.show(v);
				}					
				break;
								
			case R.id.bottom_user_apps:
				// SETS THE SETTING TO SHOW DOWNLOADED APPS ONLY
				edit.putInt("SHOW_APP", 1);
				edit.commit();
				SHOW_APP = nManager.SHOW_APP = 1;
				Toast.makeText(getBaseContext(), "Now Displaying User Apps Only",
						Toast.LENGTH_SHORT).show();
				nList = nManager.giveMeAppList();
				nAppAdapter = new AppAdapter(getBaseContext(), R.layout.row_list_1, nList);
				APP_LIST_VIEW.setAdapter(nAppAdapter);
				break;
			case R.id.bottom_system_apps:
				// SETS THE SETTING TO SHOW SYSTEM APPS ONLY
				edit.putInt("SHOW_APP", 2);
				edit.commit();
				SHOW_APP = nManager.SHOW_APP = 2;
				Toast.makeText(getBaseContext(), "Now Displaying System Apps Only",
						Toast.LENGTH_SHORT).show();
				nList = nManager.giveMeAppList();
				nAppAdapter = new AppAdapter(getBaseContext(), R.layout.row_list_1, nList);
				APP_LIST_VIEW.setAdapter(nAppAdapter);	
				break;
				
			case R.id.bottom_both_apps:
				// SETS THE SETTING TO SHOW DOWNLOADED AND SYSTEM APPS
				edit.putInt("SHOW_APP", 3);
				edit.commit();
				SHOW_APP = nManager.SHOW_APP = 3;
				Toast.makeText(getBaseContext(), "Now Displaying Both User And System Apps",
						Toast.LENGTH_SHORT).show();
				nList = nManager.giveMeAppList();
				nAppAdapter = new AppAdapter(getBaseContext(), R.layout.row_list_1, nList);
				APP_LIST_VIEW.setAdapter(nAppAdapter);	
				break;	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			case R.id.searchBtn:
				searchList = new ArrayList<File>();
				try{
					if(CURRENT_ITEM == 0 && !elementInFocus){
						QuickAction ac = new QuickAction(getBaseContext());
						ActionItem i = new ActionItem(1 , "Filter Music Files",
								getResources().getDrawable(R.drawable.ic_launcher_music));
						ac.addActionItem(i);
						
						i = new ActionItem(2 , "Filter Apk Files",
								getResources().getDrawable(R.drawable.ic_launcher_apk));
						ac.addActionItem(i);
						
						i = new ActionItem(3 , "Filter Document Files",
								getResources().getDrawable(R.drawable.ic_launcher_ppt));
						ac.addActionItem(i);
						
						i = new ActionItem(4 , "Filter Image Files",
								getResources().getDrawable(R.drawable.ic_launcher_images));
						ac.addActionItem(i);
						
						i = new ActionItem(5 , "Filter Video Files",
								getResources().getDrawable(R.drawable.ic_launcher_video));
						ac.addActionItem(i);
						
						i = new ActionItem(6 , "Filter Zipped Files",
								getResources().getDrawable(R.drawable.ic_launcher_zip_it));
						ac.addActionItem(i);
						
						i = new ActionItem(7 , "Filter Other Compressed Files",
								getResources().getDrawable(R.drawable.ic_launcher_other_compressed));
						ac.addActionItem(i);
						ac.setOnActionItemClickListener(this);
						ac.show(v);
						
					}else{
						search();
					}
				}catch(IllegalStateException e){
					e.printStackTrace();
				}
				break;
		
			case R.id.applyBtn:
				//rename the file with given name from editBox
				editBox = new EditText(getBaseContext());
				editBox = (EditText)findViewById(R.id.editBox);
				String name = editBox.getText().toString();
				//String ext = extBox.getText().toString();
				if(name.length() > 0){
					if(CREATE_FILE){
						// GETS THE PATH WHERE FOLDER OR FILE HAS TO BE CREATED
						
						// CREATE HIDDEN FOLDER
						if(CREATE_FLAG == 1){
							if(name.startsWith("."))
								name = CREATE_FLAG_PATH + "/" + name;
							else if(!name.startsWith("."))
								name = CREATE_FLAG_PATH + "/." + name;
							if(CURRENT_ITEM == 1){
								/*
								 * if(new File(name).mkdir())
									Toast.makeText(getBaseContext(), "File Created At " + name, 
											Toast.LENGTH_SHORT).show();
								else if(!new File(name).mkdir())
									Toast.makeText(getBaseContext(), ""+name, 
											Toast.LENGTH_SHORT).show();*/
								LinuxShell.execute("mkdir "+name);
							}else if(CURRENT_ITEM == 2){
								if(new File(name).mkdir())
									Toast.makeText(getApplicationContext(),"Folder Created At: " + name,
											Toast.LENGTH_LONG).show();
								else if(!new File(name).mkdir())
									Toast.makeText(getApplicationContext(),"Unable To Create Folder",
											Toast.LENGTH_LONG).show();
							}
							setAdapterAgain(CURRENT_ITEM, getBaseContext());
							RENAME_COMMAND = CREATE_FILE = SEARCH_FLAG = CUT_COMMAND = COPY_COMMAND =false;
						}
						// CREATE A SIMPLE FOLDER
						else if(CREATE_FLAG == 2){
							name = CREATE_FLAG_PATH + "/" + name;
							if(CURRENT_ITEM == 1){
								/*if(new File(name).mkdir())
									Toast.makeText(getBaseContext(), "File Has Been Created At " + name, 
											Toast.LENGTH_SHORT).show();
								else if(!new File(name).mkdir())
									Toast.makeText(getBaseContext(), ""+name, 
											Toast.LENGTH_SHORT).show();*/
								LinuxShell.execute("mkdir "+name);
							}else if(CURRENT_ITEM == 2){
								if(new File(name).mkdir())
									Toast.makeText(getApplicationContext(),"Folder Created At: " + name,
											Toast.LENGTH_LONG).show();
								else if(!new File(name).mkdir())
									Toast.makeText(getApplicationContext(),"Unable To Create Folder",
											Toast.LENGTH_LONG).show();
							}
							setAdapterAgain(CURRENT_ITEM, getBaseContext());
							RENAME_COMMAND = CREATE_FILE = SEARCH_FLAG = CUT_COMMAND = COPY_COMMAND =false;
						}
						//CREATE AN EMPTY FILE
						else if(CREATE_FLAG == 3){
							name = CREATE_FLAG_PATH + "/" + name + "."+extBox.getText();
							try{
								if(CURRENT_ITEM == 1){
									if(new File(name).createNewFile())
										Toast.makeText(getBaseContext(), "File Has Been Created At " + name, 
												Toast.LENGTH_SHORT).show();
								}else if(CURRENT_ITEM == 2){
									if(new File(name).createNewFile())
										Toast.makeText(getApplicationContext(),"Folder Created At: " + name,
												Toast.LENGTH_LONG).show();
								}
							}catch(IOException e){
								Toast.makeText(getApplicationContext(),"Unable To Create Folder",
										Toast.LENGTH_LONG).show();
							}
						}
						// AFTER CREATING FILES OR FOLDER AGAIN FLIPPER IS SET TO MAIN MENU
						mVFlipper.setAnimation(nextAnim());
						mVFlipper.showNext();
					}
					//THIS FLIPPER IS SET FOR RENAMING 
					else if(RENAME_COMMAND){
						if(CURRENT_ITEM == 1){
							name = nSFManager.getCurrentDirectory() + "/" + name;
							if(file.renameTo(new File(name))){
								Toast.makeText(getBaseContext(), "Succesfully Renamed To :" + new File(name).getName() ,
										Toast.LENGTH_SHORT).show();
							}
							else{
								/**
								 * THIS INTENT IS FIRED WHEN RENAMING OF FILE FAILS
								 * SHOWING POSSIBLE ERROR 
								 */
								LAUNCH_INTENT = new Intent(getBaseContext(), DialogBox.class);
								LAUNCH_INTENT.setData(Uri.parse("renameError"));
								startActivity(LAUNCH_INTENT);
							}
						}else if (CURRENT_ITEM == 2){
							name = nRFileManager.getCurrentDirectory() + "/" + name;
							if(file2.renameTo(new File(name))){
								Toast.makeText(getBaseContext(), "Succesfully Renamed To :" + new File(name).getName() ,
										Toast.LENGTH_SHORT).show();
							}
							else{
								/**
								 * THIS INTENT IS FIRED WHEN RENAMING OF FILE FAILS
								 * SHOWING POSSIBLE ERROR 
								 */
								LAUNCH_INTENT = new Intent(getBaseContext(), DialogBox.class);
								LAUNCH_INTENT.setData(Uri.parse("renameError"));
								startActivity(LAUNCH_INTENT);
							}
						}
						//AFTER RENAMING THE FOLDER OR FILES THE FLIPPER IS SET AGAIN TO MAIN MENU
						mVFlipper.setAnimation(nextAnim());
						mVFlipper.showNext();
					}
					setAdapterAgain(CURRENT_ITEM, getBaseContext());
					RENAME_COMMAND = CREATE_FILE = SEARCH_FLAG = CUT_COMMAND = COPY_COMMAND =false;
				}else if(name.length() == 0)
					Toast.makeText(getBaseContext(), "Please Enter A Valid Name", 
							Toast.LENGTH_SHORT).show();
				break;
			
			case R.id.homeDirBtn:
				if(HOME_DIRECTORY == null){
					LAUNCH_INTENT = new Intent(getBaseContext(), GetHomeDirectory.class);
					LAUNCH_INTENT.setData(Uri.parse("SetHomeDirectory"));
					startActivityForResult(LAUNCH_INTENT , 400);
				}
				else if(new File(HOME_DIRECTORY).exists()){
					if(CURRENT_ITEM == 2){
						nRFileManager = new RFileManager();
						nRFileManager.SHOW_HIDDEN_FOLDER = SHOW_HIDDEN_FOLDERS;
						nRFileManager.SORT_TYPE = SORT_TYPE;
						nRFileManager.nStack.push(HOME_DIRECTORY);
						nFiles = nRFileManager.giveMeFileList();
						nRoot = new RootAdapter(getBaseContext(), R.layout.row_list_1 , nFiles);
						mViewPager.setAdapter(mSectionsPagerAdapter);
						mViewPager.setCurrentItem(2);
					}else if(CURRENT_ITEM == 1){
						nSFManager = new SFileManager();
						nSFManager.SHOW_HIDDEN_FOLDER = SHOW_HIDDEN_FOLDERS;
						nSFManager.SORT_TYPE = SORT_TYPE;
						nSFManager.nStack.push(HOME_DIRECTORY);
						sFiles = nSFManager.giveMeFileList();
						nSimple = new SimpleAdapter(getBaseContext() , R.layout.row_list_1, sFiles);
						mViewPager.setAdapter(mSectionsPagerAdapter);
						mViewPager.setCurrentItem(1);					
					}else if(CURRENT_ITEM == 0){
						QuickAction d = new QuickAction(getBaseContext());
						ActionItem df = new ActionItem(8, "/",
								getResources().getDrawable(R.drawable.ic_launcher_droid_home));
						d.addActionItem(df);
						df = new ActionItem(9, "SD Card",
								getResources().getDrawable(R.drawable.ic_launcher_droid_home));
						d.addActionItem(df);
						d.show(v);
						d.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
							@Override
							public void onItemClick(QuickAction source, int pos, int actionId) {
								// TODO Auto-generated method stub
								switch(actionId){
									case 8:
										nSFManager = new SFileManager();
										nSFManager.SHOW_HIDDEN_FOLDER = SHOW_HIDDEN_FOLDERS;
										nSFManager.SORT_TYPE = SORT_TYPE;
										nSFManager.nStack.push(HOME_DIRECTORY);
										setAdapterAgain(1 , getBaseContext());
										break;
									case 9:
										nRFileManager = new RFileManager();
										nRFileManager.SHOW_HIDDEN_FOLDER = SHOW_HIDDEN_FOLDERS;
										nRFileManager.SORT_TYPE = SORT_TYPE;
										nRFileManager.nStack.push(HOME_DIRECTORY);
										setAdapterAgain(2 , getBaseContext());
										break;
								}
							}
						});
						
					}
				}else{
					HOME_DIRECTORY = PATH;
					edit.putString("HOME_DIRECTORY", PATH);
					edit.commit();
					LAUNCH_INTENT = new Intent(getBaseContext(), DialogBox.class);
					LAUNCH_INTENT.setData(Uri.parse("homeError"));
					startActivity(LAUNCH_INTENT);
				}
				
				break;
			
			
			case R.id.jumpToBtn:
				
				if(CURRENT_ITEM == 0){
					// IF CURRENT ITEM == 0
					// DISPLAYS LIST THAT IS APPLICABLE TO  ONLY ALL FILE PANEL
					QuickAction ac = new QuickAction(getBaseContext());
					ActionItem i = new ActionItem(8 , "  Jump To Music Files",
							getResources().getDrawable(R.drawable.ic_launcher_music));
					ac.addActionItem(i);
					
					i = new ActionItem(9 , "  Jump To Apk Files",
							getResources().getDrawable(R.drawable.ic_launcher_apk));
					ac.addActionItem(i);
					
					i = new ActionItem(10 , "  Jump To Document Files",
							getResources().getDrawable(R.drawable.ic_launcher_ppt));
					ac.addActionItem(i);
					
					i = new ActionItem(11 , "  Jump To Image Files",
							getResources().getDrawable(R.drawable.ic_launcher_images));
					ac.addActionItem(i);
					
					i = new ActionItem(12 , "  Jump To Video Files",
							getResources().getDrawable(R.drawable.ic_launcher_video));
					ac.addActionItem(i);
					
					i = new ActionItem(13 , "  Jump To Zipped Files",
							getResources().getDrawable(R.drawable.ic_launcher_zip_it));
					ac.addActionItem(i);
					
					i = new ActionItem(14 , "  Jump To Compressed Files",
							getResources().getDrawable(R.drawable.ic_launcher_other_compressed));
					ac.addActionItem(i);
					ac.setOnActionItemClickListener(this);
					ac.show(v);
					
				}else{
					// IF CURRENT ITMEM !=0
					//This option allows user to directly go to specified directory from any directory
					final QuickAction actionJump = new QuickAction(getBaseContext(), 1);
					ActionItem paste = new ActionItem(900, "Paste Here", getResources().getDrawable(R.drawable.ic_launcher_paste));
					ActionItem download = new ActionItem(1000, "Jump To Download Folder", getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
					ActionItem camera = new ActionItem(1100, "Jump To Camera Folder", getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
					ActionItem songs = new ActionItem(1200,"Jump To Music Folder" , getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
					ActionItem pro = new ActionItem(1300, "Properties", getResources().getDrawable(R.drawable.ic_launcher_stats));
					ActionItem apps = new ActionItem(1400, "Selected Default Apps", getResources().getDrawable(R.drawable.ic_launcher_select_app));
					actionJump.addActionItem(paste);
					actionJump.addActionItem(download);
					actionJump.addActionItem(camera);
					actionJump.addActionItem(songs);
					actionJump.addActionItem(pro);
					actionJump.addActionItem(apps);
					actionJump.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
						@Override
						public void onItemClick(QuickAction source, int pos, int actionId) {
							// TODO Auto-generated method stub
							File fJump = null;
							switch(actionId){
								case 900:
									if((CUT_COMMAND || COPY_COMMAND) && COPY_STRING !=null){
										if(CURRENT_ITEM ==2){
											String[] paths = {COPY_STRING , nRFileManager.getCurrentDirectory()};
											if(COPY_COMMAND)
												LAUNCH_INTENT = new Intent(getBaseContext(), CopyPopupDialog.class);
											else 
												LAUNCH_INTENT = new Intent(getBaseContext(), CutFileDialog.class);
											LAUNCH_INTENT.putExtra("path", paths);
											startActivityForResult(LAUNCH_INTENT , 800);
										}else{
											String[] paths = {COPY_STRING , nSFManager.getCurrentDirectory()};
											if(COPY_COMMAND)
												LAUNCH_INTENT = new Intent(getBaseContext(), CopyPopupDialog.class);
											else 
												LAUNCH_INTENT = new Intent(getBaseContext(), CutFileDialog.class);
											LAUNCH_INTENT.putExtra("path", paths);
											startActivityForResult(LAUNCH_INTENT , 800);
										}
									}else
										Toast.makeText(getBaseContext(), "No Files Selected To Paste",
												Toast.LENGTH_SHORT).show();
									break;
							    case 1000:
									fJump = new File(PATH+ "/Download"); 
									if(!fJump.exists())
										fJump.mkdir();
									if(CURRENT_ITEM == 2)
										nRFileManager.nStack.push(fJump.getAbsolutePath());
									else if(CURRENT_ITEM == 1)
										nSFManager.nStack.push(fJump.getAbsolutePath());
									setAdapterAgain(CURRENT_ITEM, getBaseContext());	
									break;
								case 1100:
									fJump = new File(PATH + "/Camera");
									if(!fJump.exists())
										fJump.mkdir();
									if(CURRENT_ITEM == 2)
										nRFileManager.nStack.push(fJump.getAbsolutePath());
									else if(CURRENT_ITEM == 1)
										nSFManager.nStack.push(fJump.getAbsolutePath());
									setAdapterAgain(CURRENT_ITEM, getBaseContext());
									break;
								case 1200:
									fJump = new File(PATH + "/Music");
									if(!fJump.exists())
										fJump.mkdir();
									if(CURRENT_ITEM == 2)
										nRFileManager.nStack.push(fJump.getAbsolutePath());
									else if(CURRENT_ITEM == 1)
										nSFManager.nStack.push(fJump.getAbsolutePath());
									setAdapterAgain(CURRENT_ITEM, getBaseContext());
									break;
								case 1300:
									if(CURRENT_ITEM == 1){
										LAUNCH_INTENT = new Intent(getBaseContext(), FileProperties.class);
										LAUNCH_INTENT.setData(Uri.parse(nSFManager.getCurrentDirectory()));
										startActivity(LAUNCH_INTENT);
									}else if(CURRENT_ITEM == 2){
										LAUNCH_INTENT = new Intent(getBaseContext(), FileProperties.class);
										LAUNCH_INTENT.setData(Uri.parse(file2.getPath()));
										startActivity(LAUNCH_INTENT);
									}
									break;
								case 1400:
									showDefaultApps(v);
							}
						}
					});
					actionJump.show(v);
				}
				
				
				break;
			
			
			case R.id.addBtn:
				
				if(CURRENT_ITEM == 0){
					showDefaultApps(v);
				}
				else{
					final QuickAction action = new QuickAction(getBaseContext() , 1 );
					ActionItem hiddenItem = new ActionItem(-10000, "DropBox", getResources().getDrawable(R.drawable.ic_launcher_drop_box));
					action.addActionItem(hiddenItem);
					hiddenItem = new ActionItem(-9000, "Google Drive", getResources().getDrawable(R.drawable.ic_launcher_google_drive));
					action.addActionItem(hiddenItem);
					
					hiddenItem = new ActionItem(-8000, "Box", getResources().getDrawable(R.drawable.ic_launcher_box));
					action.addActionItem(hiddenItem);
					
					hiddenItem = new ActionItem(-7000, "SkyDrive", getResources().getDrawable(R.drawable.ic_launcher_sky_drive));
					action.addActionItem(hiddenItem);
					
					hiddenItem = new ActionItem(-6000, "Ubuntu One", getResources().getDrawable(R.drawable.ic_launcher_ubuntu_one));
					action.addActionItem(hiddenItem);
					
					hiddenItem = new ActionItem(-5000, "Sugar Sync", getResources().getDrawable(R.drawable.ic_launcher_sugar_sync));
					action.addActionItem(hiddenItem);
					
					
					hiddenItem = new ActionItem(500, "Create Hidden Folder", getResources().getDrawable(R.drawable.ic_launcher_add_new));
					action.addActionItem(hiddenItem);
					ActionItem folderItem = new ActionItem(600, "Create A Folder", getResources().getDrawable(R.drawable.ic_launcher_add_new));
					action.addActionItem(folderItem);
					ActionItem fileItem = new ActionItem(700, "Create An Empty File", getResources().getDrawable(R.drawable.ic_launcher_new_file));
					action.addActionItem(fileItem);
					action.show(v);
					action.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
						@Override
						public void onItemClick(QuickAction source, int pos, int actionId) {
							// TODO Auto-generated method stub
						
						
							if(actionId >=500){
								CREATE_FLAG_PATH = null;
								if(CURRENT_ITEM == 1)
									CREATE_FLAG_PATH = nSFManager.getCurrentDirectory();
								else if(CURRENT_ITEM == 2)
									CREATE_FLAG_PATH = nRFileManager.getCurrentDirectory();
								CREATE_FILE = true;
								editBox.setText(null);
								extBox.setVisibility(View.GONE);
								extText.setVisibility(View.GONE);
								TextView tv = (TextView)findViewById(R.id.getNameForBox);
								switch(actionId){
									case 500:
										tv.setText("Enter Folder Name");
										editBox.setHint("Enter Folder Name");
										CREATE_FLAG = 1;
										break;
									case 600:
										tv.setText("Enter Folder Name");
										editBox.setHint("Enter Folder Name");
										CREATE_FLAG = 2;
										break;
									case 700:
										tv.setText("Enter File Name");
										editBox.setHint("Enter File Name");
										extBox.setTextColor(Color.WHITE);
										extBox.setVisibility(View.VISIBLE);
										extText.setVisibility(View.VISIBLE);
										CREATE_FLAG = 3;
								}
								LinearLayout l = (LinearLayout)findViewById(R.id.applyBtn);
								l.setVisibility(View.VISIBLE);
								editBox.setTextColor(Color.WHITE);
								// if file is to created then edittext for extension of
								// file is also displayed
								if(CREATE_FLAG == 3){
									extText.setVisibility(View.VISIBLE);
									extBox.setVisibility(View.VISIBLE);
								}else{
									extText.setVisibility(View.GONE);
									extBox.setVisibility(View.GONE);
								}
								//then mViewFlipper is rotated to editText for getting text 
								mVFlipper.setAnimation(nextAnim());
								mVFlipper.showNext();
								mVFlipper.showNext();
							}else{
								/**
								 * CLOUD STORAGE OPTIONS
								 */
								LAUNCH_INTENT = new Intent(getBaseContext(), DialogBox.class);
								LAUNCH_INTENT.setData(Uri.parse("cloud"));
								startActivity(LAUNCH_INTENT);								
							}
					}
					
					});
				}
				
				break;
				
			case R.id.appStats:
				// DISPLAYS THE INFORMATION ABOUT THE INSTALLED APPS ON PHONE
				LAUNCH_INTENT = new Intent(getBaseContext(), AppStats.class);
				startActivity(LAUNCH_INTENT);
				break;
				
			case R.id.backupAll:
				QuickAction as = new QuickAction(getBaseContext());
				ActionItem o = new ActionItem(100, "Backup User Apps",
						getResources().getDrawable(R.drawable.ic_launcher_user));
				as.addActionItem(o);
				o = new ActionItem(200, "Backup System Apps", 
						getResources().getDrawable(R.drawable.ic_launcher_system));
				as.addActionItem(o);
				o = new ActionItem(300, "Backup Both Of Them",
						getResources().getDrawable(R.drawable.ic_launcher_both));
				as.addActionItem(o);
				as.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos, int Id) {
						// TODO Auto-generated method stub
						LAUNCH_INTENT = new Intent(TaskerActivity.this , AppBackupDialog.class);
						switch(Id){
							case 100:
								LAUNCH_INTENT.setData(Uri.parse("1BackupAll"));
								break;
							case 200:	
								LAUNCH_INTENT.setData(Uri.parse("2BackupAll"));
								break;
							case 300:
								LAUNCH_INTENT.setData(Uri.parse("3BackupAll"));
						}
						startActivity(LAUNCH_INTENT);
					}
				});
				as.show(v);				
				break;
				
			case R.id.cleanBackups:
				//THIS BUTTON DISPLAYS TWO OPTIONS -1.TO DELETED THE BACKUPS
				//2. DELETE THE FLASHABLE ZIPS CREATED
				QuickAction c = new QuickAction(getBaseContext());
				ActionItem i = new ActionItem(100, "Delete Earlier Backup", 
						getResources().getDrawable(R.drawable.ic_launcher_backupall));
				c.addActionItem(i);
				i = new ActionItem(200,"Delete Flashable Zips",
						getResources().getDrawable(R.drawable.ic_launcher_zip_it));
				c.addActionItem(i);
				c.show(v);
				c.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
					@Override
					public void onItemClick(QuickAction source, int pos, int actionId) {
						// TODO Auto-generated method stub
						switch(actionId){
							case 100:
								LAUNCH_INTENT = new Intent(getBaseContext(), DeleteBackups.class);
								startActivityForResult(LAUNCH_INTENT , 599);
								break;
							case 200:	
								LAUNCH_INTENT = new Intent(getBaseContext(), DeleteFlashable.class);
								startActivity(LAUNCH_INTENT);
						}
					}
				});
				break;
				
				
			case R.id.zipItBtn:
				if(CURRENT_ITEM == 3){
					LAUNCH_INTENT = new Intent(getApplicationContext() , DialogBox.class);
					LAUNCH_INTENT.setData(Uri.parse("FlashableZips"));
					startActivityForResult(LAUNCH_INTENT , 4);//RequestCode 0 for Creating Flashable Zip
				}else{
					LAUNCH_INTENT = new Intent(getBaseContext(), ZipDialog.class);
					LAUNCH_INTENT.setData(Uri.parse("createZip"));
					if(CURRENT_ITEM == 1){
						String[] zipDetails = {nSFManager.getCurrentDirectory() ,file.getAbsolutePath()};
						LAUNCH_INTENT.putExtra("zipDetails", zipDetails);
					}else if(CURRENT_ITEM == 2){
						String[] zipDetails = {nRFileManager.getCurrentDirectory() ,file2.getAbsolutePath()};
						LAUNCH_INTENT.putExtra("zipDetails", zipDetails);
					}
					startActivity(LAUNCH_INTENT);
				}
				
				break;
		}
	}

	/**
	 * OnActivityResult For TaskerActivity Class
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		CURRENT_ITEM = mViewPager.getCurrentItem();
		if(requestCode ==99){
			//AFTER DELETING MULTIPLE FILES..........
			if(resultCode == RESULT_OK){
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				nRoot.C = 0;
			}	
		}else if(requestCode == 199){
			//AFTER PERFORMING MULTIPLE COPING OR MOVING OF FILES......
			if(resultCode == RESULT_OK){
				nRoot.MULTI_FILES = new ArrayList<File>();
				nRoot.C = 0;
				MULTI_ROOT = null;
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
			}	
		}else if(requestCode == 599){
			//AFTER DELETING ALL THE BACKED UP APPS UI IS REFRESHED......
			if(resultCode == RESULT_OK)
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
		}else if(requestCode == 699){
			//AFTER DOING MULTIPLE ZIPPING.........
			if(resultCode == RESULT_OK)
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
		}	
		else if(requestCode == 98){
			if(resultCode == RESULT_OK)
				setAdapterAgain(3, getBaseContext());
		}
		else if(requestCode == 4){
			/**
			 * Returns Result for Flashable Zip , launch the interface for backup
			 */
			if(resultCode == 1){
				LAUNCH_INTENT = new Intent(getApplicationContext() , ZipDialog.class);
				LAUNCH_INTENT.setData(Uri.parse("FlashableZips"));
				startActivityForResult(LAUNCH_INTENT , 6);//RequestCode 6 returned and then adapter is set again
			}
		}else if(requestCode == 5){
			if(resultCode == 1){
				setAdapterAgain(CURRENT_ITEM , getBaseContext());
				CUT_COMMAND = COPY_COMMAND = false;
				mVFlipper.showNext();
				mVFlipper.setAnimation(nextAnim());
			}
		}else if(requestCode == 6){
			if(resultCode == 1){
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				mVFlipper.showNext();
				mVFlipper.setAnimation(nextAnim());
			}
		}
		
		else if(requestCode == 400){
			if(resultCode == RESULT_OK){
				if(data.getData().toString().equals("/sdcard"))
					HOME_DIRECTORY = PATH;
				else 
					HOME_DIRECTORY = data.getData().toString();
				edit.putString("HOME_DIRECTORY", HOME_DIRECTORY) ;
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
			}
		}
		else if(requestCode == 2600){
			if(resultCode == RESULT_OK){
				if(data.getData().toString().equals("/sdcard"))
					INTERNAL_PATH_ONE = PATH;
				else
					INTERNAL_PATH_ONE = data.getData().toString();
				edit.putString("INTERNAL_PATH_ONE", INTERNAL_PATH_ONE);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
			}
		}else if(requestCode == 2700){
			if(resultCode == RESULT_OK){
				if(data.getData().toString().equals("/sdcard"))
					INTERNAL_PATH_TWO = PATH;
				else
					INTERNAL_PATH_TWO = data.getData().toString();
				edit.putString("INTERNAL_PATH_TWO", INTERNAL_PATH_TWO);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
			}
		}
		else if(requestCode == 800){
			if(resultCode == 80){
				/**
				 * THIS INTENT RESULT IS FIRED WHEN COPY DIALOG WAS POPED UP AND A
				 * FILE WITH SAME NAME ALREADY EXISTED,THEN USER CHOSSES TO OVERWRITE
				 * THEM AFTER DELETING THOSE FILES , ANOTHER COPY DIALOG IS FIRED TO 
				 * ACTUALLY COPY THOSE FILES
				 */
				String[] paths = {COPY_STRING , nRFileManager.getCurrentDirectory()};
				if(COPY_COMMAND)
					LAUNCH_INTENT = new Intent(getBaseContext(), CopyPopupDialog.class);
				else 
					LAUNCH_INTENT = new Intent(getBaseContext(), CutFileDialog.class);
				LAUNCH_INTENT.putExtra("path", paths);
				COPY_STRING = null;
				CUT_COMMAND = COPY_COMMAND = false;
				startActivityForResult(LAUNCH_INTENT , 800);				
			}else if(resultCode == 70){
				/**
				 * WHEN COPYING IS SUCCESSFUL THEN ADAPTER IS SET AGAIN
				 */
				COPY_STRING = null;
				CUT_COMMAND = COPY_COMMAND = false;
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
			}else if(resultCode == 90){
				/**
				 * COPYING WAS CANCELLED IN MIDDLE
				 */
				COPY_STRING = null;
				CUT_COMMAND = COPY_COMMAND = false;
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
			}else if(resultCode == 100){
				/**
				 * COPYING WAS INTERUPTED BCS OF AN EXCEPTION
				 */
				COPY_STRING = null;
				CUT_COMMAND = COPY_COMMAND = false;
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				LAUNCH_INTENT = new Intent(getBaseContext(), DialogBox.class );
				LAUNCH_INTENT.setData(Uri.parse(data.getData().toString()));
				startActivity(LAUNCH_INTENT);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
    
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			CURRENT_ITEM = mViewPager.getCurrentItem();
			if((SEARCH_FLAG || RENAME_COMMAND || CREATE_FILE) && (CURRENT_ITEM == 1 || CURRENT_ITEM == 2 || CURRENT_ITEM == 0)){
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				mVFlipper.setAnimation(nextAnim());
				mVFlipper.showNext();
				if(CURRENT_ITEM == 0){
					mFlipperBottom.showNext();
					mFlipperBottom.setAnimation(nextAnim());
				}SEARCH_FLAG = RENAME_COMMAND = COPY_COMMAND = CUT_COMMAND = CREATE_FILE = false;
				if(elementInFocus){
					media.setAdapter(adapter);
					elementInFocus = false;
				}
			}else if(CURRENT_ITEM == 0 && elementInFocus){
				elementInFocus = false;
				mViewPager.setAdapter(mSectionsPagerAdapter);
				mViewPager.setCurrentItem(0);
				mFlipperBottom.showNext();
				mFlipperBottom.setAnimation(nextAnim());
			}else if(CURRENT_ITEM == 0 && !elementInFocus){
				/**
				 *  CHECKS WHETHER THE CURRENT PREF IS 0 
				 *  IF IT IS FOUND 0 THEN IT TAKES ACTION TO EXIT ON CURRENT ITEM=0
				 */
				if(CURRENT_PREF_ITEM == 0){
					if(!mUseBackKey){
						Toast.makeText(getBaseContext(), "Press Back Key Again To Exit",
								Toast.LENGTH_SHORT).show();
						mUseBackKey = true;
					}
					else if(mUseBackKey){
						mUseBackKey = false;
						TaskerActivity.this.finish();
					}
						
				}
				/**
				 * IF CURRENT PREF IS NOT 0 THEN IT DIRECTS VIEW PAGER TO STORED VALUE
				 * AND SETS THE BOTTOM MENU TO MULTIPLE OPTIONS INSTEAD OF
				 * SHOWING SD CARD SPACE
				 */
				else{
					LAST_PAGE = CURRENT_PREF_ITEM;
					mViewPager.setCurrentItem(CURRENT_PREF_ITEM);
					mFlipperBottom.showPrevious();
					mFlipperBottom.setAnimation(prevAnim());
				}	
			}
			else if(CURRENT_ITEM == 3){
				/**
				 *  CHECKS WHETHER THE CURRENT PREF IS 3 
				 *  IF IT IS FOUND 3 THEN IT TAKES ACTION TO EXIT ON CURRENT ITEM = 3
				 */
				if(CURRENT_PREF_ITEM == 3){
					if(!mUseBackKey){
						Toast.makeText(getBaseContext(), "Press Back Key Again To Exit",
								Toast.LENGTH_SHORT).show();
						mUseBackKey = true;
					}
					else if(mUseBackKey){
						mUseBackKey = false;
						TaskerActivity.this.finish();
					}
				}
				/**
				 * IF CURRENT PREF IS NOT 3 THEN IT DIRECTS VIEW PAGER TO STORED VALUE
				 */
				else{
					if(CURRENT_PREF_ITEM == 0){
						mVFlipper.showPrevious();
						mVFlipper.setAnimation(prevAnim());
						if(elementInFocus){
							mFlipperBottom.showNext();
							mFlipperBottom.setAnimation(nextAnim());
						}else if(!elementInFocus){
							mFlipperBottom.showNext();
							mFlipperBottom.setAnimation(nextAnim());
						}
					}
					mViewPager.setCurrentItem(CURRENT_PREF_ITEM);
				}
					
			}
				
			else if(CURRENT_ITEM == 1 && !nSFManager.getCurrentDirectory().equals("/")){
				nSFManager.nStack.pop();
				File fi = new File(nSFManager.nStack.peek());
				if(!fi.canRead()){
					
						sFiles.clear();
						BufferedReader reader = null; // errReader = null;
						try {
							reader = LinuxShell
									.execute("IFS='\n';CURDIR='"
											+ LinuxShell.getCmdPath(fi.getAbsolutePath())
											+ "';for i in `ls $CURDIR`; do if [ -d $CURDIR/$i ]; then echo \"d $CURDIR/$i\";else echo \"f $CURDIR/$i\"; fi; done");
							File f;
							String line;
							while ((line = reader.readLine()) != null) {
								f = new File(line.substring(2));
								sFiles.add(f);
							}
							if(nSimple.MULTI_SELECT){
								nSimple = new SimpleAdapter(getBaseContext(), R.layout.row_list_1, sFiles);
								nSimple.MULTI_SELECT = true;
								nSimple.thumbselection = new boolean[sFiles.size()];
								nSimple.MULTI_FILES = new ArrayList<File>();
							}else
								nSimple = new SimpleAdapter(getBaseContext(), R.layout.row_list_1, sFiles);
							mViewPager.setAdapter(mSectionsPagerAdapter);
							mViewPager.setCurrentItem(1);
						}catch(Exception e){
							LAUNCH_INTENT = new Intent(getBaseContext(), RootDenied.class);
							startActivity(LAUNCH_INTENT);
						}
				}else{
					sFiles = nSFManager.giveMeFileList();
					if(nSimple.MULTI_SELECT){
						nSimple = new SimpleAdapter(getBaseContext(), R.layout.row_list_1, sFiles);
						nSimple.MULTI_SELECT = true;
						nSimple.thumbselection = new boolean[sFiles.size()];
						nSimple.MULTI_FILES = new ArrayList<File>();
					}else
						nSimple = new SimpleAdapter(getBaseContext(), R.layout.row_list_1, sFiles);
					mViewPager.setAdapter(mSectionsPagerAdapter);
					mViewPager.setCurrentItem(1);
					file = new File(nSFManager.getCurrentDirectory());
				}
				
			}else if(CURRENT_ITEM == 1 && nSFManager.getCurrentDirectory().endsWith("/")){
				/**
				 *  CHECKS WHETHER THE CURRENT PREF IS 1 
				 *  IF IT IS FOUND 3 THEN IT TAKES ACTION TO EXIT ON CURRENT ITEM = 1
				 */				
				if(CURRENT_PREF_ITEM == 1){
					if(!mUseBackKey){
						Toast.makeText(getBaseContext(),
								"Press Back Key Again To Exit", Toast.LENGTH_SHORT).show();
						mUseBackKey = true;
					}else if(mUseBackKey){
						mUseBackKey = false;
						TaskerActivity.this.finish();
					}
				}
				/**
				 * IF CURRENT PREF IS NOT 1 THEN IT DIRECTS VIEW PAGER TO STORED VALUE
				 */
				else
					mViewPager.setCurrentItem(CURRENT_PREF_ITEM);
			}
			else if(CURRENT_ITEM == 2 && !nRFileManager.getCurrentDirectory().endsWith(PATH)){
				nFiles = nRFileManager.getPreviousFileList();
				//nRoot = new RootAdapter(getApplicationContext(), R.layout.row_list_1, nFiles);
				nRoot.thumbselection = new boolean[nFiles.size()];
				mViewPager.setAdapter(mSectionsPagerAdapter);
				mViewPager.setCurrentItem(2);
				file2 = new File(nRFileManager.getCurrentDirectory());
			}else if(CURRENT_ITEM == 2 && nRFileManager.getCurrentDirectory().endsWith(PATH)){
					/**
					 *  CHECKS WHETHER THE CURRENT PREF IS 2 
					 *  IF IT IS FOUND 3 THEN IT TAKES ACTION TO EXIT ON CURRENT ITEM = 2
					 */				
					if(CURRENT_PREF_ITEM == 2){
						if(!mUseBackKey){
							Toast.makeText(getBaseContext(),
									"Press Back Key Again To Exit", Toast.LENGTH_SHORT).show();
							mUseBackKey = true;
						}else if(mUseBackKey){
							mUseBackKey = false;
							TaskerActivity.this.finish();
						}
					}
					/**
					 * IF CURRENT PREF IS NOT 2 THEN IT DIRECTS VIEW PAGER TO STORED VALUE
					 */
					else
						mViewPager.setCurrentItem(CURRENT_PREF_ITEM);
			}
				
		}
		return false;
	}
	
	 
	/**
	 * Creates the animation set for next ViewFlippers when loaded.
	 * @return a customized animation for mViewFlippers
	 */
	private static Animation nextAnim(){
		Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT,0.0f , 
												Animation.RELATIVE_TO_PARENT,0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		anim.setDuration(100);
		anim.setInterpolator(new AccelerateInterpolator());
		return anim;
	}
	
	/**
	 * Creates the animation set for previous ViewFlippers when loaded.
	 * @return a customized animation for mViewFlippers
	 */
	private static Animation prevAnim(){
		Animation anim = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f, Animation.RELATIVE_TO_PARENT,0.0f , 
												Animation.RELATIVE_TO_PARENT,0.0f, Animation.RELATIVE_TO_PARENT, 0.0f);
		anim.setDuration(100);
		anim.setInterpolator(new AccelerateInterpolator());
		return anim;
	}


	/**
	 * 
	 */
	@Override
	public void onItemClick(QuickAction source, int pos, int actionId) {
		CURRENT_ITEM = mViewPager.getCurrentItem();
		switch(actionId){
			
			case -1:
				// THIS CASE DIAPLAYS AN ACTIVITY SHOWING THE
				// DEVELOPMENT STAGE OF THE APP
				LAUNCH_INTENT = new Intent(getBaseContext(), DialogBox.class);
				LAUNCH_INTENT.setData(Uri.parse("BetaStage"));
				startActivity(LAUNCH_INTENT);
				break;
		
			case 1:
				// IF CURRENT ITEM IS 0 AND CATEGORIES ARE DIAPLAYED
				// THEN USER SELECTS FILTER BUTTON,IT SHOWS 7 OPTION
				// FIRST OPTION IS CASE 1
				SEARCH_FLAG = true;
				loadMediaList(pos=0);
				if(mediaFileList.size()!=0){
					media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
					search();
				}else 
					Toast.makeText(getBaseContext(), "There Is No Music File Available",
							Toast.LENGTH_SHORT).show();
				
				break;
			case 2:
				// IF CURRENT ITEM IS 0 AND CATEGORIES ARE DIAPLAYED
				// THEN USER SELECTS FILTER BUTTON,IT SHOWS 7 OPTION
				// SECOND OPTION IS CASE 2
				loadMediaList(pos=1);
				SEARCH_FLAG = true;
				if(mediaFileList.size()!=0){
					media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
					search();
				}else 
					Toast.makeText(getBaseContext(), "There Is No Apk File Available",
							Toast.LENGTH_SHORT).show();
				
				break;
			case 3:
				// IF CURRENT ITEM IS 0 AND CATEGORIES ARE DIAPLAYED
				// THEN USER SELECTS FILTER BUTTON,IT SHOWS 7 OPTION
				// THIRT OPTION IS CASE 3
				loadMediaList(pos=2);
				SEARCH_FLAG = true;
				if(mediaFileList.size()!=0){
					media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
					search();
				}else 
					Toast.makeText(getBaseContext(), "There Is Document File Available",
							Toast.LENGTH_SHORT).show();
				
				break;	
				
			case 4:
				// IF CURRENT ITEM IS 0 AND CATEGORIES ARE DIAPLAYED
				// THEN USER SELECTS FILTER BUTTON,IT SHOWS 7 OPTION
				// Fourth OPTION IS CASE 4
				loadMediaList(pos=3);
				SEARCH_FLAG = true;
				if(mediaFileList.size()!=0){
					media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
					search();
				}else 
					Toast.makeText(getBaseContext(), "There Is No Image File Available",
							Toast.LENGTH_SHORT).show();
				
				break;	
				
			case 5:
				// IF CURRENT ITEM IS 0 AND CATEGORIES ARE DIAPLAYED
				// THEN USER SELECTS FILTER BUTTON,IT SHOWS 7 OPTION
				// FIFTH OPTION IS CASE 5
				loadMediaList(pos=4);
				SEARCH_FLAG = true;
				if(mediaFileList.size()!=0){
					media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
					search();
				}else 
					Toast.makeText(getBaseContext(), "There Is No Video File Available",
							Toast.LENGTH_SHORT).show();
				
				break;		
				
			case 6:
				// IF CURRENT ITEM IS 0 AND CATEGORIES ARE DIAPLAYED
				// THEN USER SELECTS FILTER BUTTON,IT SHOWS 7 OPTION
				// SIXTH OPTION IS CASE 6
				loadMediaList(pos=5);
				SEARCH_FLAG = true;
				if(mediaFileList.size()!=0){
					media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
					search();
				}else 
					Toast.makeText(getBaseContext(), "There Is No Zipped File Available",
							Toast.LENGTH_SHORT).show();
				
				break;		
				
			case 7:
				// IF CURRENT ITEM IS 0 AND CATEGORIES ARE DIAPLAYED
				// THEN USER SELECTS FILTER BUTTON,IT SHOWS 7 OPTION
				// SEVENTH OPTION IS CASE 7
				loadMediaList(pos=6);
				SEARCH_FLAG = true;
				if(mediaFileList.size()!=0){
					media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
					search();
				}else 
					Toast.makeText(getBaseContext(), "There Is No Other Compressed File Available",
							Toast.LENGTH_SHORT).show();
				
				break;		
				
			case 8:
				// IF CURRENT ITEM IS 0 AND USER SELECTS JUMP TO BUTTON
				// THEN SEVEN LOCATIONS ARE SHOWN
				// FIRST LOCATION IS CASE 8
				loadMediaList(pos=0);
				elementInFocus = true;
				media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
				break;
				
			case 9:
				// IF CURRENT ITEM IS 0 AND USER SELECTS JUMP TO BUTTON
				// THEN SEVEN LOCATIONS ARE SHOWN
				// SECOND LOCATION IS CASE 9
				loadMediaList(pos=1);
				elementInFocus = true;
				media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
				break;	
				
			case 10:
				// IF CURRENT ITEM IS 0 AND USER SELECTS JUMP TO BUTTON
				// THEN SEVEN LOCATIONS ARE SHOWN
				// THIRD LOCATION IS CASE 10
				loadMediaList(pos=2);
				elementInFocus = true;
				media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
				break;	
				
			case 11:
				// IF CURRENT ITEM IS 0 AND USER SELECTS JUMP TO BUTTON
				// THEN SEVEN LOCATIONS ARE SHOWN
				// FOURTH LOCATION IS CASE 11
				loadMediaList(pos=3);
				elementInFocus = true;
				media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
				break;	
				
			case 12:
				// IF CURRENT ITEM IS 0 AND USER SELECTS JUMP TO BUTTON
				// THEN SEVEN LOCATIONS ARE SHOWN
				// FIFTH LOCATION IS CASE 12
				loadMediaList(pos=4);
				elementInFocus = true;
				media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
				break;	
				
			case 13:
				// IF CURRENT ITEM IS 0 AND USER SELECTS JUMP TO BUTTON
				// THEN SEVEN LOCATIONS ARE SHOWN
				// SIXTH LOCATION IS CASE 13
				loadMediaList(pos=5);
				elementInFocus = true;
				media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
				break;	
				
			case 14:
				// IF CURRENT ITEM IS 0 AND USER SELECTS JUMP TO BUTTON
				// THEN SEVEN LOCATIONS ARE SHOWN
				// SEVENTH LOCATION IS CASE 14
				loadMediaList(pos=6);
				elementInFocus = true;
				media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, mediaFileList));
				break;	
			case 70:
				// DISPLAYS THE DEVICE HARDWARE INFO
				LAUNCH_INTENT = new Intent(getBaseContext(), DeviceInfo.class);
				break;
		
			case 80:
				// SHOWING THE OPTIONS AVAILABLE FOR CHANGING APPREANCE
				QuickAction q = new QuickAction(getBaseContext());
				ActionItem q1 = new ActionItem(100, "  Adjust Transparency",
						getResources().getDrawable(R.drawable.ic_launcher_appreance));
				q.addActionItem(q1);
				if(CURRENT_ITEM !=3){
					q1 = new ActionItem(90, "  Set Folder Icon", 
							getResources().getDrawable(R.drawable.ic_launcher_folder_violet));
					q.addActionItem(q1);
				}
				q.setOnActionItemClickListener(this);
				q.show(indicator);
				break;
				
			case 90:
				//DIRECTED FROM CASE 80
				// DISPLAYS THE OPTIONS AVAILABLE FOR CHANGING FOLDER ICON TO SHOW
				int FOLDER_TYPE = nRoot.FOLDER_TYPE;
				QuickAction ac = new QuickAction(getBaseContext());
				ActionItem it;
				
				if(FOLDER_TYPE == 0)
					it = new ActionItem(2800, "  Window Style Folder",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					it = new ActionItem(2800, "  Window Style Folder",
							getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
				ac.addActionItem(it);
				
				if(FOLDER_TYPE == 1)
					it = new ActionItem(2900, "  Oxygen Violet Folder",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					it = new ActionItem(2900, "  Oxygen Violet Folder",
							getResources().getDrawable(R.drawable.ic_launcher_folder_violet));
				ac.addActionItem(it);
				
				if(FOLDER_TYPE == 2)
					it = new ActionItem(3000, "  Oxygen Orange Folder",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					it = new ActionItem(3000, "  Oxygen Orange Folder",
							getResources().getDrawable(R.drawable.ic_launcher_folder_oxygen));
				ac.addActionItem(it);
				
				if(FOLDER_TYPE == 3)
					it = new ActionItem(3100, "  Yellow Folder",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					it = new ActionItem(3100, "  Yellow Folder",
							getResources().getDrawable(R.drawable.ic_launcher_folder_yellow));
				ac.addActionItem(it);
				
				if(FOLDER_TYPE == 4)
					it = new ActionItem(3101, "  Ubuntu Orange Folder",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					it = new ActionItem(3101, "  Ubuntu Orange Folder",
							getResources().getDrawable(R.drawable.ic_launcher_folder_ubuntu));
				ac.addActionItem(it);
				
				if(FOLDER_TYPE == 5)
					it = new ActionItem(3102, "  Ubuntu Black Folder",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					it = new ActionItem(3102, "  Ubuntu Black Folder",
							getResources().getDrawable(R.drawable.ic_launcher_folder_ubuntu_black));
				ac.addActionItem(it);
				
				if(FOLDER_TYPE == 6)
					it = new ActionItem(3103, "  Gnome Folder",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					it = new ActionItem(3103, "  Gnome Folder",
							getResources().getDrawable(R.drawable.ic_launcher_folder_gnome));
				ac.addActionItem(it);
				
				
				ac.setOnActionItemClickListener(this);
				ac.show(indicator);
				break;
			case 100:
				// DIRECTED FROM CASE 80
				// SHOWING ALL THE TRANSPARENCY LEVEL AVAILABLE
				QuickAction d = new QuickAction(getBaseContext());
				ActionItem l;
				if(TRANSPARENCY_LEVEL == 0.6f)
					l = new ActionItem(1700,"  60% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					l = new ActionItem(1700,"  60% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_appreance));
				d.addActionItem(l);
				
				if(TRANSPARENCY_LEVEL == 0.7f)
					l = new ActionItem(1800,"  70% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					l = new ActionItem(1800,"  70% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_appreance));
				d.addActionItem(l);
				
				if(TRANSPARENCY_LEVEL == 0.8f)
					l = new ActionItem(1900,"  80% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					l = new ActionItem(1900,"  80% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_appreance));
				d.addActionItem(l);
				
				if(TRANSPARENCY_LEVEL == 0.9f)
					l = new ActionItem(2000,"  90% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					l = new ActionItem(2000,"  90% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_appreance));
				d.addActionItem(l);
				
				if(TRANSPARENCY_LEVEL == 1.0f)
					l = new ActionItem(2100,"  100% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					l = new ActionItem(2100,"  100% Opaque" , getResources().getDrawable(R.drawable.ic_launcher_appreance));
				d.addActionItem(l);
				d.setOnActionItemClickListener(this);
				d.show(indicator);
				break;
			case 200:
				// DISPLAYS OPTIONS AVAILABLE IF USER SELECTS FOLDER OPTIONS
				QuickAction a = new QuickAction(getBaseContext());
				ActionItem i = new ActionItem(800, "Set Panel On Startup" , getResources().getDrawable(R.drawable.ic_launcher_startup));
				a.addActionItem(i);
				if(mViewPager.getCurrentItem() !=3){
					i = new ActionItem(900,"Set Directory On Startup" , getResources().getDrawable(R.drawable.ic_launcher_startup));
					a.addActionItem(i);
				}
				a.setOnActionItemClickListener(this);
				a.show(indicator);
				break;
				
			case 300:
				QuickAction b = new QuickAction(getBaseContext());
				ActionItem j;
				if(SHOW_HIDDEN_FOLDERS)
					j = new ActionItem(1000, "Show Hidden Folders" , 
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					j = new ActionItem(1000, "Show Hidden Folders" , 
							getResources().getDrawable(R.drawable.ic_launcher_disabled));
				b.addActionItem(j);
				j = new ActionItem(1100,"Sorting" , getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
				b.addActionItem(j);
				b.setOnActionItemClickListener(this);
				b.show(indicator);
				break;
				
			case 400:
				// LAUNCHES AN INTERFACE FOR SELECTING A DIRECTORY FOR HOME
				// WITH REQUEST CODE 400
				LAUNCH_INTENT = new Intent(getBaseContext(), GetHomeDirectory.class);
				LAUNCH_INTENT.setData(Uri.parse("GetHomeDirectory"));
				startActivityForResult(LAUNCH_INTENT , 400);
				
				break;
			case 500:
				// RESETS APP SETTINGS TO DEFAULT
				edit.clear();
				edit.putString("INTERNAL_PATH_ONE", INTERNAL_PATH_ONE=PATH);
				edit.putString("INTERNAL_PATH_TWO", INTERNAL_PATH_TWO=PATH);
				edit.putInt("SHOW_APP", SHOW_APP=1);
				edit.putInt("CURRENT_PREF_ITEM", CURRENT_PREF_ITEM=2);
				edit.putFloat("TRANSPARENCY_LEVEL", TRANSPARENCY_LEVEL = 0.8f);
				edit.putBoolean("SHOW_HIDDEN_FOLDERS", SHOW_HIDDEN_FOLDERS = false);
				edit.putInt("SORT_TYPE",SORT_TYPE = 2);
				edit.putInt("FOLDER_TYPE", FOLDER_TYPE = 3);
				edit.putString("HOME_DIRECTORY", HOME_DIRECTORY = null);
				edit.putBoolean("ENABLE_ON_LAUNCH", ENABLE_ON_LAUNCH = false);
				edit.commit();
				
				// CLEARING THE DEFAULT SELECTED APPS 
				SharedPreferences pr = getSharedPreferences("DEFAULT_APPS", MODE_PRIVATE);
				SharedPreferences.Editor ed = pr.edit();
				ed.clear();
				ed.commit();
				Toast.makeText(getBaseContext(), "Restored To Default",
						Toast.LENGTH_SHORT).show();
				break;
			case 600:
				CURRENT_ITEM = mViewPager.getCurrentItem();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;
		
			case 700:
				LAUNCH_INTENT = new Intent(getBaseContext(), Info.class);
				startActivity(LAUNCH_INTENT);
				break;
			case 800:
				// DIRECTED FROM CASE 200 
				// IT IS FIRST OPTION AVAILABLE UNDER STARTUP
				QuickAction e = new QuickAction(getBaseContext());
				ActionItem m;
				if(CURRENT_PREF_ITEM == 0)
					m = new ActionItem(2200, "File Gallery" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else 
					m = new ActionItem(2200, "File Gallery" , getResources().getDrawable(R.drawable.ic_launcher_startup));
				e.addActionItem(m);
				
				if(CURRENT_PREF_ITEM == 1)
					m = new ActionItem(2300, "/" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else 
					m = new ActionItem(2300, "/" , getResources().getDrawable(R.drawable.ic_launcher_startup));
				e.addActionItem(m);
				if(CURRENT_PREF_ITEM == 2)
					m = new ActionItem(2400, "SD Card" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else 
					m = new ActionItem(2400, "SD Card" , getResources().getDrawable(R.drawable.ic_launcher_startup));
				e.addActionItem(m);
				
				if(CURRENT_PREF_ITEM == 3)
					m = new ActionItem(2500, "Your App Store" , getResources().getDrawable(R.drawable.ic_launcher_apply));
				else 
					m = new ActionItem(2500, "Your App Store" , getResources().getDrawable(R.drawable.ic_launcher_startup));
				e.addActionItem(m);
				e.setOnActionItemClickListener(this);
				e.show(indicator);
				break;
			case 900:
				// DIRECTED FROM CASE 200 
				// IT IS SECOND OPTION AVAILABLE UNDER STARTUP
				QuickAction f = new QuickAction(getBaseContext());
				ActionItem n = new ActionItem(2600, "/" , getResources().getDrawable(R.drawable.ic_launcher_startup));
				f.addActionItem(n);
				n = new ActionItem(2700,"SD Card" , getResources().getDrawable(R.drawable.ic_launcher_startup));
				f.addActionItem(n);
				
				if(ENABLE_ON_LAUNCH)
					n = new ActionItem(3600,"Enable On Launch" , 
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					n = new ActionItem(3600,"Enable On Launch" , 
							getResources().getDrawable(R.drawable.ic_launcher_disabled));
				f.addActionItem(n);
				
				f.setOnActionItemClickListener(this);
				f.show(indicator);
				break;
				
			case 1000:
				// CASE 300 DIRECTED QUICKACTION TO CASE 1000 , FIRST OPTION UNDER FOLDER OPTIONS
				// IF HIDDEN FOLDERS ARE VISIBLE HIDE IT OTHER WISE MAKE THEM VISIBLE
				if(SHOW_HIDDEN_FOLDERS){
					edit.putBoolean("SHOW_HIDDEN_FOLDERS", false);
					SHOW_HIDDEN_FOLDERS = nRFileManager.SHOW_HIDDEN_FOLDER =  
							nSFManager.SHOW_HIDDEN_FOLDER = false;
				}else{
					SHOW_HIDDEN_FOLDERS = nRFileManager.SHOW_HIDDEN_FOLDER =  
							nSFManager.SHOW_HIDDEN_FOLDER = true;
					edit.putBoolean("SHOW_HIDDEN_FOLDERS", true);
				}edit.commit();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				break;
			case 1100:
				// DISPLAYS THE AVAILABLE SORTING METHODS AVAILABLE
				QuickAction c = new QuickAction(getBaseContext());
				ActionItem k;
				if(SORT_TYPE == 1)
					k = new ActionItem(1200,"Alphabetical Order" ,
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					k = new ActionItem(1200,"Alphabetical Order" ,
							getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
				c.addActionItem(k);
				
				if(SORT_TYPE == 2)
					k = new ActionItem(1300,"Folder First Then File" ,
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					k = new ActionItem(1300,"Folder First Then File" ,
							getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
				c.addActionItem(k);
				
				if(SORT_TYPE == 3)
					k = new ActionItem(1400,"File First Then Folder" ,
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					k = new ActionItem(1400,"File First Then Folder" ,
							getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
				c.addActionItem(k);
				
				if(SORT_TYPE == 4)
					k = new ActionItem(1500,"Hidedn Item First" ,
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					k = new ActionItem(1500,"Hidden Item First" ,
							getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
				c.addActionItem(k);
				
				if(SORT_TYPE == 5)
					k = new ActionItem(1600,"Non Hidden Item First" ,
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					k = new ActionItem(1600,"Non Hidden First" ,
							getResources().getDrawable(R.drawable.ic_launcher_folder_orange));
				c.addActionItem(k);
				c.setOnActionItemClickListener(this);
				c.show(indicator);
				break;
				
			case 1200:
				// DIRECTED FROM CASE 1100
				// SETS SORTING IN ALPHABETICAL ORDER
				SORT_TYPE = nRFileManager.SORT_TYPE = nSFManager.SORT_TYPE = 1;
				edit.putInt("SORT_TYPE", 1);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;
				
			case 1300:
				// SETS SORTING IN FOLDER FIRST THEN FILE
				// DIRECTED FROM CASE 1100
				SORT_TYPE = nRFileManager.SORT_TYPE = nSFManager.SORT_TYPE = 2;
				edit.putInt("SORT_TYPE", 2);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;
				
			case 1400:
				// SETS DORTING IN FILE FIRST THE FOLDER
				// DIRECTED FROM CASE 1100
				SORT_TYPE = nRFileManager.SORT_TYPE = nSFManager.SORT_TYPE = 3;
				edit.putInt("SORT_TYPE", 3);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;	
			
			case 1500:
				// SETS SORTING IN SHOW HIDDEN ITEM FIRST
				// DIRECTED FROM CASE 1100
				SORT_TYPE = nRFileManager.SORT_TYPE = nSFManager.SORT_TYPE = 4;
				edit.putInt("SORT_TYPE", 4);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;	
				
			case 1600:
				// SETS SORTING IN SHOW NON HIDDEN ITEMS FIRST
				// DIRECTED FROM CASE 1100
				SORT_TYPE = nRFileManager.SORT_TYPE = nSFManager.SORT_TYPE = 5;
				edit.putInt("SORT_TYPE", 5);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;	
				
			case 1700:
				//DIRECTED FROM CASE 100
				//SETS THE TRANSPARENCY TO 60%
				TRANSPARENCY_LEVEL = 0.6f;
				edit.putFloat("TRANSPARENCY_LEVEL", 0.6f);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;
			case 1800:
				//DIRECTED FROM CASE 100
				//SETS THE TRANSPARENCY TO 70%
				TRANSPARENCY_LEVEL = 0.7f;
				edit.putFloat("TRANSPARENCY_LEVEL", 0.7f);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;	
				
			case 1900:
				//DIRECTED FROM CASE 100
				//SETS THE TRANSPARENCY TO 80%
				TRANSPARENCY_LEVEL = 0.8f;
				edit.putFloat("TRANSPARENCY_LEVEL", 0.8f);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;	
			case 2000:
				//DIRECTED FROM CASE 100
				//SETS THE TRANSPARENCY TO 90%
				TRANSPARENCY_LEVEL = 0.9f;
				edit.putFloat("TRANSPARENCY_LEVEL", 0.9f);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;	
			case 2100:
				//DIRECTED FROM CASE 100
				//SETS THE TRANSPARENCY TO 100%
				TRANSPARENCY_LEVEL = 1.0f;
				edit.putFloat("TRANSPARENCY_LEVEL", 1.0f);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;	
				
			case 2200:
				//DIRECTED FROM CASE 800
				//SETS THE SETTING TO LOAD ALL FILE PANEL TO LOAD FIRST
				CURRENT_PREF_ITEM = 0;
				edit.putInt("CURRENT_PREF_ITEM", 0);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;
			case 2300:
				//DIRECTED FROM CASE 800
				//SETS THE SETTING TO LOAD SD CARD PANEL-1 TO LOAD FIRST
				CURRENT_PREF_ITEM = 1;
				edit.putInt("CURRENT_PREF_ITEM", 1);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;
			case 2400:
				//DIRECTED FROM CASE 800
				//SETS THE SETTING TO LOAD SD CARD PANEL-2 TO LOAD FIRST
				CURRENT_PREF_ITEM = 2;
				edit.putInt("CURRENT_PREF_ITEM", 2);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;
			case 2500:
				//DIRECTED FROM CASE 800
				//SETS THE SETTING TO LOAD ALL FILE PANEL TO LOAD FIRST
				//CURRENT_PREF_ITEM = 3;
				edit.putInt("CURRENT_PREF_ITEM", 3);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;
				
			case 2600:
				// LAUNCHES AN ACTIVITY TO SELECT THE DIRECTORY FOR INTERNAL STORAGE 1
				// DIRECTED FROM CASE 900
				LAUNCH_INTENT = new Intent(getBaseContext(), GetHomeDirectory.class);
				startActivityForResult(LAUNCH_INTENT , 2600);
				break;
			case 2700:
				// LAUNCHES AN ACTIVITY TO SELECT THE DIRECTORY FOR INTERNAL STORAGE 2
				// DIRECTED FROM CASE 900
				LAUNCH_INTENT = new Intent(getBaseContext(), GetHomeDirectory.class);
				startActivityForResult(LAUNCH_INTENT , 2700);
				break;
				
			case 2800:
				// DIRECTED FROM CAE 90
				// SETS THE FOLDER ICON TO DEFAULT FOLDER ICON
				nRoot.FOLDER_TYPE =  nSimple.FOLDER_TYPE = 0;
				edit.putInt("FOLDER_TYPE", 0);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;
				
			case 2900:
				// DIRECTED FROM CAE 90
				// SETS THE FOLDER ICON TO VIOLET FOLDER ICON
				nRoot.FOLDER_TYPE =  nSimple.FOLDER_TYPE = 1;
				edit.putInt("FOLDER_TYPE", 1);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;	
				
			case 3000:
				// DIRECTED FROM CAE 90
				// SETS THE FOLDER ICON TO OXYGEN FOLDER ICON
				nRoot.FOLDER_TYPE =  nSimple.FOLDER_TYPE = 2;
				edit.putInt("FOLDER_TYPE", 2);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;	
				
			case 3100:
				// DIRECTED FROM CAE 90
				// SETS THE FOLDER ICON TO YELLOW FOLDER ICON
				nRoot.FOLDER_TYPE =  nSimple.FOLDER_TYPE = 3;
				edit.putInt("FOLDER_TYPE", 3);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;
				
			case 3101:
				// DIRECTED FROM CAE 90
				// SETS THE FOLDER ICON TO YELLOW FOLDER ICON
				nRoot.FOLDER_TYPE =  nSimple.FOLDER_TYPE = 4;
				edit.putInt("FOLDER_TYPE", 4);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;	
				
			case 3102:
				// DIRECTED FROM CAE 90
				// SETS THE FOLDER ICON TO YELLOW FOLDER ICON
				nRoot.FOLDER_TYPE =  nSimple.FOLDER_TYPE = 5;
				edit.putInt("FOLDER_TYPE", 5);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;	
			case 3103:
				// DIRECTED FROM CAE 90
				// SETS THE FOLDER ICON TO YELLOW FOLDER ICON
				nRoot.FOLDER_TYPE =  nSimple.FOLDER_TYPE = 6;
				edit.putInt("FOLDER_TYPE", 6);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				setAdapterAgain(CURRENT_ITEM, getBaseContext());
				break;
				
				
				
				
				
				
			case 3200:
				//DIRECTED FROM CASE 400 ONLY FOR APP PANEL
				// DISPLAYS OPTION FOR SETTING TO SHOW USER OR SYSTEM OR BOTH TYPES OF APP
				QuickAction s = new QuickAction(getBaseContext());
				ActionItem ti;
				if(SHOW_APP  == 1)
					ti = new ActionItem(3300, "Show Downloaded Apps",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					ti = new ActionItem(3300, "Show Downloaded Apps",
							getResources().getDrawable(R.drawable.ic_launcher_user));
				s.addActionItem(ti);
				
				if(SHOW_APP == 2)
					ti = new ActionItem(3400, "Show System Apps",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					ti = new ActionItem(3400, "Show System Apps",
							getResources().getDrawable(R.drawable.ic_launcher_system));
				s.addActionItem(ti);
				
				if(SHOW_APP == 3)
					ti = new ActionItem(3500, "Show Both Of Them",
							getResources().getDrawable(R.drawable.ic_launcher_apply));
				else
					ti = new ActionItem(3500, "Show Both Of Them",
							getResources().getDrawable(R.drawable.ic_launcher_both));
				s.addActionItem(ti);
				s.setOnActionItemClickListener(this);
				s.show(indicator);
				break;
			case 3300:
				// SETS THE SETTING TO SHOW DOWNLOADED APPS ONLY
				// DIRECTED FROM CASE 3200
				edit.putInt("SHOW_APP", 1);
				edit.commit();
				SHOW_APP = nManager.SHOW_APP = 1;
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				nList = nManager.giveMeAppList();
				nAppAdapter = new AppAdapter(getBaseContext(), R.layout.row_list_1, nList);
				APP_LIST_VIEW.setAdapter(nAppAdapter);
				break;
			case 3400:
				// SETS THE SETTING TO SHOW SYSTEM APPS ONLY
				// DIRECTED FROM CASE 3200
				edit.putInt("SHOW_APP", 2);
				edit.commit();
				SHOW_APP = nManager.SHOW_APP = 2;
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				nList = nManager.giveMeAppList();
				nAppAdapter = new AppAdapter(getBaseContext(), R.layout.row_list_1, nList);
				APP_LIST_VIEW.setAdapter(nAppAdapter);	
				break;
				
			case 3500:
				// SETS THE SETTING TO SHOW DOWNLOADED AND SYSTEM APPS
				// DIRECTED FROM CASE 3200
				edit.putInt("SHOW_APP", 3);
				edit.commit();
				SHOW_APP = nManager.SHOW_APP = 3;
				Toast.makeText(getBaseContext(), "Settings Have Been Applied",
						Toast.LENGTH_SHORT).show();
				nList = nManager.giveMeAppList();
				nAppAdapter = new AppAdapter(getBaseContext(), R.layout.row_list_1, nList);
				APP_LIST_VIEW.setAdapter(nAppAdapter);	
				break;
				
			case 3600:
				// DIRECTED FROM CASE 900
				// ENABLES OR DISABLES TO SHOW PREFFERED DIRECTORY ON LAUNCH
				if(ENABLE_ON_LAUNCH)
					edit.putBoolean("ENABLE_ON_LAUNCH", ENABLE_ON_LAUNCH = false);
				else
					edit.putBoolean("ENABLE_ON_LAUNCH", ENABLE_ON_LAUNCH = true);
				edit.commit();
				Toast.makeText(getBaseContext(), "Settings Have Been Saved",
						Toast.LENGTH_SHORT).show();
				break;
			
		}
	}
	
	/**
	 * Returns the path only (without file name).
	 * @param file
	 * @return
	 */
	public static File getPathWithoutFilename(File file) {
		 if (file != null) {
			 if (file.isDirectory()) {
				 // no file to be split off. Return everything
				 return file;
			 } else {
				 String filename = file.getName();
				 String filepath = file.getAbsolutePath();
	  
				 // Construct path without file name.
				 String pathwithoutname = filepath.substring(0, filepath.length() - filename.length());
				 if (pathwithoutname.endsWith("/")) {
					 pathwithoutname = pathwithoutname.substring(0, pathwithoutname.length() - 1);
				 }	
				 return new File(pathwithoutname);
			 }
		 }
		 return null;
	}
	
	/**
	 * THIS FUNCTION CONTAINS ALL THE ACTION THAT HAS TO BE PERFORMED WHEN
	 * SEARCH IS IN PROGRESS
	 */
	public void search(){
		try{
			searchList = new ArrayList<File>();
			LinearLayout a = (LinearLayout)findViewById(R.id.applyBtn);
			a.setVisibility(View.GONE);                                                               
			
			if(CURRENT_ITEM == 0){
				if(mediaFileList.size() == 0){
					Toast.makeText(getBaseContext(), "No files available to search",
							Toast.LENGTH_SHORT).show();
					return;
				}
				if(!elementInFocus){
					mFlipperBottom.showPrevious();
					mFlipperBottom.setAnimation(prevAnim());
					elementInFocus = true;
				}				
			}
			//Search Flipper is loaded
			mVFlipper.setAnimation(nextAnim());
			mVFlipper.showNext();
			mVFlipper.showNext();
			SEARCH_FLAG = true;
			// PREVIOUS COMMANDS ARE OVERWRITTEN
			COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = CREATE_FILE = false;
			extBox.setVisibility(View.GONE);
			extText.setVisibility(View.GONE);
			editBox.setTextColor(Color.WHITE);
			editBox.setText(null);
			TextView tv = (TextView)findViewById(R.id.getNameForBox);
			tv.setText("Enter Name To Filter Out");
			editBox.setHint("Enter Name To Filter Out");
			editBox.addTextChangedListener(new TextWatcher() {
				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					searchList.clear();
					// TODO Auto-generated method stub
				}
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					searchList.clear();
					// TODO Auto-generated method stub
				}
				@Override
				public void afterTextChanged(Editable text) {
					// TODO Auto-generated method stub
					searchList.clear();
					File f = new File(PATH);
					if(CURRENT_ITEM == 2)
						f = new File(nRFileManager.getCurrentDirectory());
					else if(CURRENT_ITEM == 1)
						f = new File(nSFManager.getCurrentDirectory());
					final File[] list = f.listFiles();
					final String search = text.toString();
					final String[] fList = f.list();
					new AsyncTask<String[], Void, Void>(){
						@Override
						protected void onPostExecute(Void result) {
							if(SEARCH_FLAG){
								if(CURRENT_ITEM == 2)
									root.setAdapter(new RootAdapter(getBaseContext(), R.layout.row_list_1, searchList));
								else if(CURRENT_ITEM == 1)
									simple.setAdapter(new SimpleAdapter(getBaseContext(), R.layout.row_list_1, searchList));
								else
									media.setAdapter(new MediaElementAdapter(getBaseContext(), R.layout.row_list_1, searchList));
							}	
						}
						@Override
						protected void onPreExecute() {
							if(SEARCH_FLAG){
								if(CURRENT_ITEM == 2)
									root.setAdapter(null);
								else if(CURRENT_ITEM == 1)
									simple.setAdapter(null);
								else 
									media.setAdapter(null);
							}
							super.onPreExecute();
						}
						@Override
						protected Void doInBackground(String[]... arg0) {
							if(SEARCH_FLAG){
								// SEARCH IS PERFORMED FOR CURRENT ITEM 0
								if(CURRENT_ITEM == 0){
									int len = mediaFileList.size();
									for(int i = 0 ; i < len ; ++i){
										File file = mediaFileList.get(i);
										if(file.canRead())
											if(file.getName().toLowerCase().contains(search))
												searchList.add(file);
									}
								}
								// SEARCH IS PERFORMED FOR CURRENT ITEM = 1,2
								else
									for(int i = 0 ; i < fList.length ; ++i){
										if(CURRENT_ITEM == 2){
											if(list[i].canRead())
												if((fList[i].toLowerCase()).contains(search.toLowerCase()))
													searchList.add(list[i]);
										}else if(CURRENT_ITEM == 1){
											if((fList[i].toLowerCase()).contains(search.toLowerCase()))
													searchList.add(list[i]);
										}
								}
							}	
							return null;
						}
					}.execute();
				}
			});
		}catch(IllegalStateException e){
			
		}catch(RuntimeException e){
			
		}catch(Exception e){
			
		}
	}
	
	
	
	
	
	/**
	 * THIS FUNCTION IS USED AT TWO PLACE IN THIS FILE
	 * THIS FUNCTION DISPLAYS THE QUICKACTION VIEW
	 * SHOWING THE DEFAULT APPS SET BY USER FOR CRETAIN FILE TYPES
	 */
	public void showDefaultApps(View v){
		/**
		 * 
		 */
		PackageInfo in;
		PackageManager man = getPackageManager();
		QuickAction q = new QuickAction(getBaseContext());
		final SharedPreferences p = getSharedPreferences("DEFAULT_APPS", MODE_PRIVATE);
		final SharedPreferences.Editor ed = p.edit();
		final String MUSIC = p.getString("MUSIC", null);
		ActionItem it = new ActionItem();
		try{
			in = man.getPackageInfo(MUSIC, 0);
			it.setTitle("Music - " + in.applicationInfo.loadLabel(man));
			it.setIcon(in.applicationInfo.loadIcon(man));
			it.setActionId(500);
		}catch(NameNotFoundException e){
			it = new ActionItem(600 , "Music - No App Set As Default" ,
						getResources().getDrawable(R.drawable.ic_launcher_music));
			ed.putString("MUSIC", null);
			ed.commit();
		}
		q.addActionItem(it);
		
		it = new ActionItem();
		final String IMAGE = p.getString("IMAGE", null);
		try{
			in = man.getPackageInfo(IMAGE, 0);
			it.setTitle("Image - " + in.applicationInfo.loadLabel(man));
			it.setIcon(in.applicationInfo.loadIcon(man));
			it.setActionId(700);
		}catch(NameNotFoundException e){
			ed.putString("IMAGE", null);
			ed.commit();
			it = new ActionItem(800 , "Image - No App Set As Default" ,
						getResources().getDrawable(R.drawable.ic_launcher_images));
			
		}
		q.addActionItem(it);
		
		it = new ActionItem();
		final String VIDEO = p.getString("VIDEO", null);
		try{
			in = man.getPackageInfo(VIDEO, 0);
			it.setTitle("Video - " + in.applicationInfo.loadLabel(man));
			it.setIcon(in.applicationInfo.loadIcon(man));
			it.setActionId(900);
		}catch(NameNotFoundException e){
			ed.putString("VIDEO", null);
			ed.commit();
			it = new ActionItem(1000 , "Video - No App Set As Default" ,
						getResources().getDrawable(R.drawable.ic_launcher_video));
			
		}
		q.addActionItem(it);
		
		it = new ActionItem();
		final String ZIP = p.getString("ZIP", null);
		try{
			in = man.getPackageInfo(ZIP, 0);
			it.setTitle("Zip - " + in.applicationInfo.loadLabel(man));
			it.setIcon(in.applicationInfo.loadIcon(man));
			it.setActionId(1100);
		}catch(NameNotFoundException e){
			ed.putString("ZIP", null);
			ed.commit();
			it = new ActionItem(1200 , "Zip - No App Set As Default" ,
						getResources().getDrawable(R.drawable.ic_launcher_zip_it));
			
		}
		q.addActionItem(it);
		
		it = new ActionItem();
		final String PDF = p.getString("PDF", null);
		try{
			in = man.getPackageInfo(PDF, 0);
			it.setTitle("Pdf - " + in.applicationInfo.loadLabel(man));
			it.setIcon(in.applicationInfo.loadIcon(man));
			it.setActionId(1300);
		}catch(NameNotFoundException e){
			ed.putString("PDF", null);
			ed.commit();
			it = new ActionItem(1400 , "Pdf - No App Set As Default" ,
						getResources().getDrawable(R.drawable.ic_launcher_adobe));
			
		}
		q.addActionItem(it);
		
		it = new ActionItem();
		final String TEXT = p.getString("TEXT", null);
		try{
			in = man.getPackageInfo(TEXT, 0);
			it.setTitle("Docs - " + in.applicationInfo.loadLabel(man));
			it.setIcon(in.applicationInfo.loadIcon(man));
			it.setActionId(1500);
		}catch(NameNotFoundException e){
			ed.putString("TEXT", null);
			ed.commit();
			it = new ActionItem(1600 , "Docs - No App Set As Default" ,
						getResources().getDrawable(R.drawable.ic_launcher_text));
			
		}
		q.addActionItem(it);
		
		it = new ActionItem();
		final String RAR = p.getString("RAR", null);
		try{
			in = man.getPackageInfo(RAR, 0);
			it.setTitle("Rar - " + in.applicationInfo.loadLabel(man));
			it.setIcon(in.applicationInfo.loadIcon(man));
			it.setActionId(1700);
		}catch(NameNotFoundException e){
			ed.putString("RAR", null);
			ed.commit();
			it = new ActionItem(1800 , "Rar - No App Set As Default" ,
						getResources().getDrawable(R.drawable.ic_launcher_other_compressed));
			
		}
		q.addActionItem(it);
		it = new ActionItem(1900, "Clear All Defaults",
				getResources().getDrawable(R.drawable.ic_launcher_move));
		q.addActionItem(it);
		q.setOnActionItemClickListener(new QuickAction.OnActionItemClickListener() {
			@Override
			public void onItemClick(QuickAction source, int pos, int actionId) {
				// TODO Auto-generated method stub
				switch(actionId){
					case 500:
						/**
						 * MUSIC APP HAS BEEN SET TO DEFAULT
						 * THEN DISPLAY ITS INFO AND GIVE OPTION TO
						 * CLEAR DEFAULT
						 */
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectedApp.class);
						LAUNCH_INTENT.setData(Uri.parse(MUSIC));
						LAUNCH_INTENT.setAction("MUSIC");
						startActivity(LAUNCH_INTENT);
						break;
					case 600:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectApp.class);
						LAUNCH_INTENT.setAction("MUSIC");
						startActivity(LAUNCH_INTENT);
						break;
					case 700:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectedApp.class);
						LAUNCH_INTENT.setData(Uri.parse(IMAGE));
						LAUNCH_INTENT.setAction("IMAGE");
						startActivity(LAUNCH_INTENT);
						break;	
					case 800:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectApp.class);
						LAUNCH_INTENT.setAction("IMAGE");
						startActivity(LAUNCH_INTENT);
						break;
					case 900:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectedApp.class);
						LAUNCH_INTENT.setData(Uri.parse(VIDEO));
						LAUNCH_INTENT.setAction("VIDEO");
						startActivity(LAUNCH_INTENT);
						break;
	
					case 1000:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectApp.class);
						LAUNCH_INTENT.setAction("VIDEO");
						startActivity(LAUNCH_INTENT);
						break;
					case 1100:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectedApp.class);
						LAUNCH_INTENT.setData(Uri.parse(ZIP));
						LAUNCH_INTENT.setAction("ZIP");
						startActivity(LAUNCH_INTENT);
						break;
					case 1200:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectApp.class);
						LAUNCH_INTENT.setAction("ZIP");
						startActivity(LAUNCH_INTENT);
						break;
					case 1300:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectedApp.class);
						LAUNCH_INTENT.setData(Uri.parse(PDF));
						LAUNCH_INTENT.setAction("PDF");
						startActivity(LAUNCH_INTENT);
						break;
					case 1400:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectApp.class);
						LAUNCH_INTENT.setAction("PDF");
						startActivity(LAUNCH_INTENT);
						break;
					case 1500:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectedApp.class);
						LAUNCH_INTENT.setData(Uri.parse(TEXT));
						LAUNCH_INTENT.setAction("TEXT");
						startActivity(LAUNCH_INTENT);
						break;
					case 1600:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectApp.class);
						LAUNCH_INTENT.setAction("TEXT");
						startActivity(LAUNCH_INTENT);
						break;
					case 1700:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectedApp.class);
						LAUNCH_INTENT.setData(Uri.parse(RAR));
						LAUNCH_INTENT.setAction("RAR");
						startActivity(LAUNCH_INTENT);
						break;
					case 1800:
						LAUNCH_INTENT = new Intent(getBaseContext(), SelectApp.class);
						LAUNCH_INTENT.setAction("RAR");
						startActivity(LAUNCH_INTENT);
						break;
					case 1900:
						SharedPreferences.Editor ed = p.edit();
						ed.clear();
						ed.commit();
						Toast.makeText(getBaseContext(),
								"Settings Have Been Applied",Toast.LENGTH_SHORT).show();
						break;
				}
			}
		});
		q.show(v);
	}
	
	/**
	 * THIS FUNCTION SHOWS THE SETTINGS OF THE APPS
	 */
	private void ShowMenu(){
		QuickAction action = new QuickAction(getApplicationContext(), 1);
		ActionItem item = new ActionItem(80, "Appearance", getResources().getDrawable(R.drawable.ic_launcher_appreance));
		action.addActionItem(item);
		item = new ActionItem(200, "Start Up", getResources().getDrawable(R.drawable.ic_launcher_startup));
		action.addActionItem(item);
		if(mViewPager.getCurrentItem()!=3){
			item = new ActionItem(300, "Folder Options", getResources().getDrawable(R.drawable.ic_launcher_folder_ubuntu));
			action.addActionItem(item);
			item = new ActionItem(400, "Set Home Directory", getResources().getDrawable(R.drawable.ic_launcher_droid_home));
			action.addActionItem(item);
		}else if(mViewPager.getCurrentItem() == 3){
			item = new ActionItem(3200, "Apps", getResources().getDrawable(R.drawable.ic_launcher_apk));
			action.addActionItem(item);
		}
		item = new ActionItem(500, "Reset To Default", getResources().getDrawable(R.drawable.ic_launcher_move));
		action.addActionItem(item);
		item = new ActionItem(600, "Refresh", getResources().getDrawable(R.drawable.ic_launcher_refresh));
		action.addActionItem(item);
		// yet to be implemented
		//item = new ActionItem(70, "Device Info", getResources().getDrawable(R.drawable.ic_launcher_backupall));
		//action.addActionItem(item);
		
		//item = new ActionItem(-1, "Urgent Message", getResources().getDrawable(R.drawable.ic_launcher_message));
		//action.addActionItem(item);
		
		item = new ActionItem(700, "About Me And My App", getResources().getDrawable(R.drawable.ic_launcher_info));
		action.addActionItem(item);
		action.setAnimStyle(3);
		action.setOnActionItemClickListener(this);
		action.show(indicator);
	}
	
	
	/**
	 * THIS FUNCTION RESETS THE HORIZONTAL SCROLL VIEW TO START
	 * AND DISPLAYS THE APPROPRIATE MESSAGE WHEN MULTI SELECT IS DISABLED
	 * @param str
	 */
	public void MultiModeDisabled(String str){
		HorizontalScrollView v = (HorizontalScrollView)findViewById(R.id.hscrollView);
		if(CURRENT_ITEM == 3)
			v = (HorizontalScrollView)findViewById(R.id.hscrollView2);
		ImageButton btn = (ImageButton)findViewById(R.id.bottom_multi);
		QuickAction action = new QuickAction(getBaseContext());
		ActionItem item = new ActionItem(1, str);
		action.addActionItem(item);
		v.scrollTo(0, 0);
		action.show(btn);
	}
	
	/**
	 * THIS CLASS LOADS THE SDCARD INFO AND REFRESHES THE UI OF BOTTOM 
	 * AND DISPLAYSTHE CURRENT SD CARD STATUS
	 * @author ANURAG
	 *
	 */
	class load extends AsyncTask<Void, Void, Void>{
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			sd.setMax((int)to);
			sd.setProgress((int)(to-av));
			avail.setText(sav);
			total.setText(sto);
		}

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			StatFs fs = new StatFs(Environment.getExternalStorageDirectory().getPath());
			av = fs.getAvailableBlocks()*fs.getBlockSize();
			to = fs.getBlockCount()*fs.getBlockSize();
			                                                                                                               
			if(av>1024*1024*1024)
				sav = String.format("Avalaible %.2f GB", (double)av/(1024*1024*1024));
			
			else if(av > 1024*1024)
				sav = String.format("Avalaible %.2f MB", (double)av/(1024*1024));
			
			else if(av>1024)
				sav = String.format("Avalaible %.2f KB", (double)av/(1024));
			else
				sav = String.format("Avalaible %.2f Bytes", (double)av);
			
			if(to>1024*1024*1024)
				sto = String.format("Total Space %.2f GB", (double)to/(1024*1024*1024));
			
			else if(to > 1024*1024)
				sto = String.format("Total Space %.2f MB", (double)to/(1024*1024));
			
			else if(to>1024)
				sto = String.format("Total Space %.2f KB", (double)to/(1024));
			else
				sto = String.format("Total Space %.2f Bytes", (double)to);
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void OPERATION_ON_MULTI_SELECT_FILES(int ITEM,int action,String str){
		if(ITEM == 0 && element.MULTI_SELECT){
			if(action == 4 && element.C !=0){//DELETE THE MULTIPLE FILES IF ACTIONID = 4
				LAUNCH_INTENT = new Intent(getBaseContext(),DeleteMultipleFiles.class);
				LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) element.MULTI_FILES);
				LAUNCH_INTENT.setAction("media");
				startActivityForResult(LAUNCH_INTENT,99);
			}else if(action == 5 && element.C !=0){
				MULTIPLE_COPY_GALLERY = true;
				MULTIPLE_COPY = MULTIPLE_CUT = COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND =
						MULTIPLE_CUT_GALLERY = SEARCH_FLAG = false;
				MULTI_GALLERY = element.MULTI_FILES;
			}else if(action == 2 && element.C !=0){
				MULTIPLE_CUT_GALLERY = true; 
				MULTI_GALLERY = element.MULTI_FILES;
				MULTIPLE_COPY = MULTIPLE_CUT = COPY_COMMAND = CUT_COMMAND = 
						MULTIPLE_COPY_GALLERY = RENAME_COMMAND = SEARCH_FLAG = false;
			}else if(action == 3 && element.C !=0){
				MULTI_GALLERY = element.MULTI_FILES;
				MULTIPLE_COPY = MULTIPLE_CUT = COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = SEARCH_FLAG = 
						MULTIPLE_COPY_GALLERY = MULTIPLE_CUT_GALLERY = false;
				LAUNCH_INTENT = new Intent(getBaseContext(), MultiZip.class);
				LAUNCH_INTENT.setAction(PATH);
				LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) MULTI_GALLERY);
				LAUNCH_INTENT.putExtra("size", ""+element.C);
				startActivityForResult(LAUNCH_INTENT, 699);
			}else
				Toast.makeText(getBaseContext(), "First select some files",
						Toast.LENGTH_SHORT).show();
		}else if(ITEM == 1 && nSimple.MULTI_SELECT){
			if(action == 4 && nSimple.c !=0){//DELETE THE MULTIPLE FILES IF ACTIONID = 4
				LAUNCH_INTENT = new Intent(getBaseContext(),DeleteMultipleFiles.class);
				LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) nSimple.MULTI_FILES);
				LAUNCH_INTENT.setAction("root");
				startActivityForResult(LAUNCH_INTENT,99);
			}else if(action == 5 && nSimple.c>0){
				MULTIPLE_COPY = true;
				COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = SEARCH_FLAG = false;
				MULTI_SIMPLE = nSimple.MULTI_FILES;
				
				/*
				 * BUG HAS TO BE FIXED WHILE COPYING FILES UNDER ROOT DIRECTORY 
				 * FOR NOW SHOWING TOAST MESSAGE SHOWING BCS OF INTERNAL ERROR
				 * COPYING FAILED 
				 */
				Toast.makeText(getBaseContext(), "An error encounterd,failed to copy" ,
						Toast.LENGTH_SHORT).show();
			}else if(action == 2 && nSimple.c>0){
				MULTIPLE_CUT = true;
				COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = SEARCH_FLAG = false;
				MULTI_SIMPLE = nSimple.MULTI_FILES;
				
				/*
				 * BUG HAS TO BE FIXED WHILE CUTTING FILES UNDER ROOT DIRECTORY 
				 * FOR NOW SHOWING TOAST MESSAGE SHOWING BCS OF INTERNAL ERROR
				 * COPYING FAILED 
				 */
				Toast.makeText(getBaseContext(), "An error encounterd,failed to cut" ,
						Toast.LENGTH_SHORT).show();
			}else if(action == 3 && nSimple.c>0){
				COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = SEARCH_FLAG = false;
				MULTI_SIMPLE = nSimple.MULTI_FILES;
				
				/*
				 * BUG HAS TO BE FIXED WHILE ZIPPING FILES UNDER ROOT DIRECTORY 
				 * FOR NOW SHOWING TOAST MESSAGE SHOWING BCS OF INTERNAL ERROR
				 * COPYING FAILED 
				 */
				Toast.makeText(getBaseContext(), "An error encounterd,failed to zip" ,
						Toast.LENGTH_SHORT).show();
			}else
				Toast.makeText(getBaseContext(), "First select some files",
						Toast.LENGTH_SHORT).show();
			
		}else if(ITEM == 2 && nRoot.MULTI_SELECT){
			if(action == 4 && nRoot.C !=0){//DELETE THE MULTIPLE FILES IF ACTIONID = 4
				LAUNCH_INTENT = new Intent(getBaseContext(),DeleteMultipleFiles.class);
				LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) nRoot.MULTI_FILES);
				LAUNCH_INTENT.setAction("sd");
				startActivityForResult(LAUNCH_INTENT,99);
			}else if(action == 5 && nRoot.C !=0){
				MULTIPLE_COPY = true;
				COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = SEARCH_FLAG = false;
				MULTI_ROOT = nRoot.MULTI_FILES;
			}else if(action == 2 && nRoot.C !=0){
				MULTIPLE_CUT = true; 
				MULTI_ROOT = nRoot.MULTI_FILES;
				COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = SEARCH_FLAG = false;
			}else if(action == 3 && nRoot.C !=0){
				MULTI_ROOT = nRoot.MULTI_FILES;
				MULTIPLE_COPY = MULTIPLE_CUT = COPY_COMMAND = CUT_COMMAND = RENAME_COMMAND = SEARCH_FLAG = false;
				LAUNCH_INTENT = new Intent(getBaseContext(), MultiZip.class);
				LAUNCH_INTENT.setAction(nRFileManager.getCurrentDirectory());
				LAUNCH_INTENT.putParcelableArrayListExtra("list", (ArrayList<? extends Parcelable>) MULTI_ROOT);
				LAUNCH_INTENT.putExtra("size", ""+nRoot.C);
				startActivityForResult(LAUNCH_INTENT, 699);
			}else
				Toast.makeText(getBaseContext(), "First select some files",
						Toast.LENGTH_SHORT).show();
		}else
			MultiModeDisabled(str);
	}	
}
