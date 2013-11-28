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

import java.io.File;
import java.util.ArrayList;

public class MediaManager {
	
	private static ArrayList<File> zList;
	private static ArrayList<File> sList;
	private static ArrayList<File> iList;
	private static ArrayList<File> dList;
	private static ArrayList<File> vList;
	private static ArrayList<File> aList;
	private static ArrayList<File> cList;
	
	public MediaManager(){
		zList = new ArrayList<File>();
		sList = new ArrayList<File>();
		iList = new ArrayList<File>();
		dList = new ArrayList<File>();
		vList = new ArrayList<File>();
		aList = new ArrayList<File>();
		cList = new ArrayList<File>();
	}
	
	
	public void clearList(){
		zList.clear();
		sList.clear();
		iList.clear();
		dList.clear();
		vList.clear();
		aList.clear();
		cList.clear();
	}
	
	/**
	 * Function To return All the Zipped Files Present In Phone
	 * @param file
	 * @return
	 */
	public ArrayList<File> getZipFiles(File file){
		if(file.listFiles() !=null){
			for(File f:file.listFiles())
				if(f.isDirectory())
					getZipFiles(f);
				else if(f.getName().endsWith(".zip") || f.getName().endsWith(".ZIP"))
					zList.add(f);
		}
		return zList;
	}
	
	/**
	 * Function To return All the Other Compressed Files Present In Phone
	 * @param file
	 * @return
	 */
	public ArrayList<File> getCompressedFiles(File file){
		if(file.listFiles() !=null){
			for(File f:file.listFiles())
				if(f.isDirectory())
					getCompressedFiles(f);
				else if(f.getName().endsWith(".tar") || f.getName().endsWith(".TAR") || f.getName().endsWith(".rar") 
						|| f.getName().endsWith("RAR") || f.getName().endsWith(".7z") || f.getName().endsWith(".7Z"))
					cList.add(f);
		}
		return cList;
	}
	
	/**
	 * Function To return All the Apk Files Present In Phone
	 * @param file
	 * @return
	 */
	public ArrayList<File> getApkFiles(File file){
		if(file.listFiles() !=null){
			for(File f:file.listFiles())
				if(f.isDirectory())
					getApkFiles(f);
				else if(f.getName().endsWith(".apk") || f.getName().endsWith(".APK"))
					aList.add(f);	
		}
		return aList;
	}
	/**
	 * Function To return All the Audio Files Present In Phone
	 * @param file
	 * @return
	 */
	public ArrayList<File> getAudioFiles(File file){
		if(file.listFiles() !=null){
			for(File f:file.listFiles())
				if(f.isDirectory())
					getAudioFiles(f);
				else if(f.getName().endsWith(".mp3") || f.getName().endsWith(".MP3") || f.getName().endsWith(".amr") || f.getName().endsWith(".AMR")
						|| f.getName().endsWith(".ogg") || f.getName().endsWith(".OGG"))
					sList.add(f);
		}
		return sList;
	}
	
	/**
	 * Function To return All the Document Files Present In Phone
	 * @param file
	 * @return
	 */
	public ArrayList<File> getDocumentFiles(File file){
		if(file.listFiles() !=null){
			for(File f:file.listFiles())
				if(f.isDirectory())
					getDocumentFiles(f);
				else if(f.getName().endsWith(".pdf") || f.getName().endsWith(".PDF") || f.getName().endsWith(".doc") || f.getName().endsWith(".DOC")
						|| f.getName().endsWith(".DOCX") || f.getName().endsWith(".docx") || f.getName().endsWith(".ppt") || f.getName().endsWith(".PPT"))
					dList.add(f);
		}
		return dList;
	}
	
	/**
	 * Function To return All the Document Files Present In Phone
	 * @param file
	 * @return
	 */
	public ArrayList<File> getImageFiles(File file){
		if(file.listFiles() !=null){
			for(File f:file.listFiles())
				if(f.isDirectory())
					getImageFiles(f);
				else if(f.getName().endsWith(".png") || f.getName().endsWith(".PNG") || f.getName().endsWith(".jpg")
						|| f.getName().endsWith(".JPG") || f.getName().endsWith(".gif") || f.getName().endsWith(".GIF")
						|| f.getName().endsWith(".JPEG") || f.getName().endsWith(".jpeg") || f.getName().endsWith(".bmp") || f.getName().endsWith(".BMP"))
					iList.add(f);
		}
		return iList;
	}
	
	/**
	 * Function To return All the Document Files Present In Phone
	 * @param file
	 * @return
	 */
	public ArrayList<File> getVideoFiles(File file){
		if(file.listFiles() !=null){
			for(File f:file.listFiles())
				if(f.isDirectory())
					getVideoFiles(f);
				else if(f.getName().endsWith(".mp4") || f.getName().endsWith(".MP4") || f.getName().endsWith(".avi") || f.getName().endsWith(".AVI")
						|| f.getName().endsWith(".FLV") || f.getName().endsWith(".flv") || f.getName().endsWith(".3GP") || f.getName().endsWith(".3gp"))
					vList.add(f);
		}
		return vList;
	}
	

}
