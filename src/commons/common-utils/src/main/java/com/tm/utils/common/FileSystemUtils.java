package com.tm.utils.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileSystemUtils {
	
	/*
	 * files operations
	 */
	
	public static File getFile(String path) throws FileNotFoundException {
		File file = new File(path);
		if (!file.isFile()) {
			throw new FileNotFoundException(path);
		}
		return file;
	}
	
	public static File getDirectory(String path) throws DirectoryNotFoundException {
		File dir = new File(path);
		if (!dir.isDirectory()) {
			throw new DirectoryNotFoundException(path);
		}
		return dir;
	}
	
	public static void copyFile(File srcFile, File destDir) throws IOException {
		File destFile = new File(destDir, srcFile.getName());
		byte[] bytes = readBinaryFile(srcFile);
		writeBinaryFile(bytes, destFile);
	}
	
	public static void copyDir(File srcDir, File destDir) {
		//TODO
	}
	
	public static boolean moveFile(File srcFile, File destDir) {
		File destFile = new File(destDir, srcFile.getName());
		return srcFile.renameTo(destFile);
	}
	
	public static boolean moveFile(File srcFile, File destDir, File subFolder) {
		File destDir1 = null;
		if (subFolder != null) {
			destDir1 = buildTargetDir(srcFile, destDir, subFolder);
		} else {
			destDir1 = destDir;
		}
		return moveFile(srcFile, destDir1);
	}
	
	
	public static File buildTargetDir(File file, File destDir, File subFolder) {
		if (subFolder == null)
			return destDir;
		else {
			String inputFolderPathStr = subFolder.getAbsolutePath();
			String fileFolderPathStr = file.getParentFile().getAbsolutePath();
			String subfolderStructure = FileSystemUtils.extractSubfolderStructure(fileFolderPathStr, inputFolderPathStr);
			File targetDir = new File(destDir.getAbsoluteFile() + File.separator + subfolderStructure);
			return targetDir;
		}
	}
	
	public static boolean moveDir(File srcDir, File destDir) {
		return false;//TODO
	}
	
	public static boolean renameFile(File file, String newName) {
		File dir = file.getParentFile();
		File destFile = new File(dir, newName);
		return file.renameTo(destFile);
	}
	
	public static boolean renameDir(File dir, String newName) {
		return renameFile(dir, newName);
	}
	
	public static boolean deleteFile(File file) {
		return file.delete();
	}
	
	public static boolean deleteDir(File dir) {
		if (!dir.exists() || !dir.isDirectory())
		return false;
		
		for (File file : dir.listFiles()) {
			if(file.isDirectory()) {
				deleteDir(file);
			} else {
				deleteFile(file);
			}
		}
		
		return dir.delete();
	}
	
	/*
	 * read/write files
	 */
	
	public static byte[] readBinaryFile(File file) throws IOException {
		
		byte[] result = new byte[(int)file.length()];
		
	    FileInputStream fis = null;
	    BufferedInputStream bis = null;

	    try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);				    	
			bis.read(result); 
	    } finally {
	    	if (fis != null) {
	    		fis.close();
	    	}
	    	if (bis != null) {
	    		bis.close();
	    	}
	    }
	    return result;
	}
	
	public static void writeBinaryFile(byte[] bytes, File file) throws IOException {
		
		FileOutputStream fos = null;
	    BufferedOutputStream bos = null;

	    try {
	    	fos = new FileOutputStream(file);
	    	bos = new BufferedOutputStream(fos);				    	
	    	bos.write(bytes);
	    } finally {
	    	if (fos != null) {
	    		fos.close();
	    	}
	    	if (bos != null) {
	    		bos.close();
	    	}
	    }
		
	}
	
	public static List<String> readTextFile(File file) throws IOException {
		
		List<String> result = new ArrayList<String>();
		
		FileReader fr = null;
		BufferedReader br = null;

	    try {
	    	fr = new FileReader(file);
	    	br = new BufferedReader(fr);
				    	
			String nextLine;			
			while ((nextLine = br.readLine()) != null) {
				result.add(nextLine);
			}
	    } finally {
	    	if (fr != null) {
	    		fr.close();
	    	}
	    	if (br != null) {
	    		br.close();
	    	}
	    }
	    return result;
	}
	
	public static void writeTextFile(List<String> lines, File file) throws IOException {
		
		FileWriter fw = null;
		BufferedWriter bw = null;

	    try {
	    	fw = new FileWriter(file);
	    	bw = new BufferedWriter(fw);
	    	
	    	for (String line : lines) {
	    		bw.write(line);
	    	}
	    } finally {
	    	if (fw != null) {
	    		fw.close();
	    	}
	    	if (bw != null) {
	    		bw.close();
	    	}
	    }
	}
	
	public static void appendStringToTextFile(String str, File file) throws IOException {
		
		FileWriter fw = null;
		BufferedWriter bw = null;

	    try {
	    	fw = new FileWriter(file, true);
	    	bw = new BufferedWriter(fw);	    	
	    	bw.write(str);
	    } finally {
	    	if (fw != null) {
	    		fw.close();
	    	}
	    	if (bw != null) {
	    		bw.close();
	    	}
	    }
	}
	
	/*
	 * File
	 */
	
	public static String getFileNameWithoutExtention(File file) {
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf(".");
		String name = dotIndex == -1 ? fileName : fileName.substring(0, dotIndex);
		return name;
	}
	
	public static String getFileExtention(File file) {
		String fileName = file.getName();
		int dotIndex = fileName.lastIndexOf(".");
		String ext = dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
		return ext;
	}
	
	public static String extractSubfolderStructure(String longerPath, String shorterPath) {
		if (longerPath.length() <= shorterPath.length())
			return "";
		String str = longerPath.replace(shorterPath, "");
		int leadingSlash = str.indexOf(File.separator);
		if (leadingSlash == 0)
			str = str.substring(1);
		int endingSlash = str.lastIndexOf(File.separator);
		if (endingSlash == str.length() - 1)
			str = str.substring(0, endingSlash);
		return str;
	}
	
	public static class FileSystemException extends Exception {
		
		public FileSystemException(String message) {
			super(message);
		}
	}
	
	public static class DirectoryNotFoundException extends FileSystemException {
		
		public DirectoryNotFoundException(String path) {
			super("Директория не найдена: " + path);
		}
	}
	
	public static class FileNotFoundException extends FileSystemException {
		
		public FileNotFoundException(String path) {
			super("Файл не найден: " + path);
		}
	}

}
