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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

import android.content.SharedPreferences;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class SFileManager {
	private String PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
	private static Message msg;
	public int SORT_TYPE;
	public static Stack<String> nStack;
	private static ArrayList<File> nFiles;
	public boolean SHOW_HIDDEN_FOLDER = false;
	private static File file;
	private static Handler mHandler;
	private static int BUFFER = 2048;
	public SFileManager(){
		nStack = new Stack<String>();
		nFiles = new ArrayList<File>();
		nStack.push("/");
	}	
	
	public SFileManager(Handler handler){
		this.mHandler = handler;
	}
	/**
	 * Function to return current path  
	 * @return
	 */
	public String getCurrentDirectory(){
		return nStack.peek();
	}
	
	/**
	 * Function To return Current File Name
	 * @return
	 */
	public String getCurrentDirectoryName(){
		file = new File(nStack.peek());
		return file.getName();
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
	
	/**
	 * SORTS THE FILE[] ALPHABETICALLY WITH HAVING FILES FIRST
	 */
	private final static Comparator<File> alphaFileFirst = new Comparator<File>() {
		@Override
		public int compare(File a, File b) {
			boolean aIsFolder = a.isDirectory();
			boolean bIsFolder = b.isDirectory();
			if(bIsFolder == aIsFolder )
				return a.getName().compareToIgnoreCase(b.getName());
			else if(bIsFolder)
				return -1;
			return 1;
		}
	}; 
	
	/**
	 * SORTS THE FILE[] ALPHABETICALLY IRRESPECTIVE OF FILE OR FOLDER
	 */
	
	private final static Comparator<File> alpha = new Comparator<File>() {
		@Override
		public int compare(File a, File b) {
				return a.getName().compareToIgnoreCase(b.getName());
		}
	}; 
	
	/**
	 * Function to Generate Current Directory File List
	 * @return
	 */
	public ArrayList<File> getCurrentFileList(){
		nFiles.clear();
		file = new File(nStack.peek());
		if(SORT_TYPE == 4)
			return getCurrentFileListWithHiddenItemFirst();
		else if(SORT_TYPE == 5)
			return getCurrentFileListWithHiddenItemLast();
		if(file.canRead() && file.exists()){
			File[] files = file.listFiles();
			if(SORT_TYPE == 1)
				Arrays.sort(files,alpha);
			else if(SORT_TYPE == 2)
				Arrays.sort(files,alphaFolderFirst);
			else if(SORT_TYPE == 3)
				Arrays.sort(files,alphaFileFirst);
			for(int i = 0 ;i<files.length ; ++i){
				File f = files[i];
				if(SHOW_HIDDEN_FOLDER)
					nFiles.add(f);
				else if(!f.getName().startsWith("."))
					nFiles.add(f);
			}	
		}
		return  nFiles;
	}
	
	/**
	 * Function to Generate Current Directory File List Without Having Hidden Folders In List
	 * Sorted in alphabetical order
	 * @return
	 */
	public ArrayList<File> getCurrentFileListWithoutHiddenFolders(){
		nFiles.clear();
		file = new File(nStack.peek());
		if(file.canRead() && file.exists()){
			File[] files = file.listFiles();
			Arrays.sort(files,alphaFolderFirst);
			for(int i = 0 ;i<files.length ; ++i)
				if(!files[i].getName().startsWith("."))
					nFiles.add(files[i]);
		}
		return  nFiles;
	}

	/**
	 * function sorting files in alphabetical order
	 * keeping hidden items first
	 * @return
	 */
	public ArrayList<File> getCurrentFileListWithHiddenItemFirst(){
		nFiles.clear();
		file = new File(nStack.peek());
		if(file.canRead() && file.exists()){
			File[] files = file.listFiles();
			Arrays.sort(files,alphaFolderFirst);
			for(int i = 0 ;i<files.length ; ++i)
				if(SHOW_HIDDEN_FOLDER)
					if( files[i].getName().startsWith(".") )
						nFiles.add(files[i]);
			for(int i = 0 ;i<files.length ; ++i)
				if( !files[i].getName().startsWith(".")) 
					nFiles.add(files[i]);
		}
		
		return nFiles;
	}
	
	
	/**
	 * function sorting files in alphabetical order
	 * keeping hidden items first
	 * @return
	 */
	public ArrayList<File> getCurrentFileListWithHiddenItemLast(){
		nFiles.clear();
		file = new File(nStack.peek());
		if(file.canRead() && file.exists()){
			File[] files = file.listFiles();
			Arrays.sort(files,alphaFolderFirst);
			for(int i = 0 ;i<files.length ; ++i)
				if( !files[i].getName().startsWith(".") )
					nFiles.add(files[i]);
			for(int i = 0 ;i<files.length ; ++i)
				if(SHOW_HIDDEN_FOLDER)
					if( files[i].getName().startsWith("."))
						nFiles.add(files[i]);
		}
		
		return nFiles;
	}
	
	public ArrayList<File> giveMeFileList(){
		return getCurrentFileList();
		
	}
	
	/**
	 * Function To Generate Previous Directory List
	 * @return
	 */
	public ArrayList<File> getPreviousFileList(){
		if( nStack.size() >= 2)
			nStack.pop();
		else if( nStack.size() == 0)
			nStack.push("/");
		if(SHOW_HIDDEN_FOLDER)
			return getCurrentFileList();
		else{
			SHOW_HIDDEN_FOLDER = false;
			return getCurrentFileList();
		}
	}
	
	/**
	 * Function To Delete The Given File And Returns Message To Handler
	 * If Deletion is successful returns 0 else returns -1
	 * @param path
	 * @return
	 */
	public void deleteTarget(File file) {
		Message msg = new Message();
		msg.what = 0;
		mHandler.sendMessage(msg);
		File target = file;
		if(target.exists() && target.isFile() && target.canWrite())
			target.delete();
		
		else if(target.exists() && target.isDirectory() && target.canRead()) {
			String[] file_list = target.list();
			
			if(file_list != null && file_list.length == 0) {
				target.delete();
			} else if(file_list != null && file_list.length > 0) {
				
				for(int i = 0; i < file_list.length; i++) {
					File temp_f = new File(target.getAbsolutePath() + "/" + file_list[i]);
					if(temp_f.isDirectory())
						deleteTarget(temp_f);
					else if(temp_f.isFile())
						temp_f.delete();
				}
			}
			if(target.exists())
				if(target.delete()){}
		}
	}
	
	/**
	 * 
	 * If Deletion is successful returns 0 else returns -1
	 * @param path
	 * @return
	 */
	public void deleteFileAfterZipping(File file) {
		Message msg = new Message();
		msg.what = 0;
		//mHandler.sendMessage(msg);
		File target = file;
		if(target.exists() && target.isFile() && target.canWrite())
			target.delete();
		
		else if(target.exists() && target.isDirectory() && target.canRead()) {
			String[] file_list = target.list();
			
			if(file_list != null && file_list.length == 0) {
				target.delete();
			} else if(file_list != null && file_list.length > 0) {
				
				for(int i = 0; i < file_list.length; i++) {
					File temp_f = new File(target.getAbsolutePath() + "/" + file_list[i]);
					if(temp_f.isDirectory())
						deleteTarget(temp_f);
					else if(temp_f.isFile())
						temp_f.delete();
				}
			}
			if(target.exists())
				if(target.delete()){}
		}
	}
	
	
	public void copyToDirectory(String old, String newDir) {
		File old_file = new File(old);
		File temp_dir = new File(newDir);
		byte[] data = new byte[BUFFER];
		int read = 0;
		
		if(old_file.isFile() && temp_dir.isDirectory() && temp_dir.canWrite()){
			String file_name = old.substring(old.lastIndexOf("/"), old.length());
			File cp_file = new File(newDir + file_name);
			try {
				msg = new Message();
				msg.what = 1;
				msg.obj = cp_file.getName();
				mHandler.sendMessage(msg);
				BufferedOutputStream o_stream = new BufferedOutputStream(
												new FileOutputStream(cp_file));
				BufferedInputStream i_stream = new BufferedInputStream(
											   new FileInputStream(old_file));
				while((read = i_stream.read(data, 0, BUFFER)) != -1)
					o_stream.write(data, 0, read);
				o_stream.flush();
				i_stream.close();
				o_stream.close();
				
			}catch (FileNotFoundException e) {
				Log.e("FileNotFoundException", e.getMessage());
			}catch (IOException e) {
				Log.e("IOException", e.getMessage());
			}
			
		}else if(old_file.isDirectory() && temp_dir.isDirectory() && temp_dir.canWrite()) {
			String files[] = old_file.list();
			String dir = newDir + old.substring(old.lastIndexOf("/"), old.length());
			int len = files.length;
			if(!new File(dir).mkdir()){}							
			for(int i = 0; i < len; i++)
				copyToDirectory(old + "/" + files[i], dir);
		}else if(!temp_dir.canWrite()){}
	}
		
	
}
