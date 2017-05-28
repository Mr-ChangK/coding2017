package com.coding.basic.tree;

import java.io.File;

public class FileList {
	
	public void list(File f) {
		if(f.isDirectory()){
			File[] files = f.listFiles();
			for(File file : files){
				String fileName = file.getName();
				if(fileName.startsWith(".")){
					continue;
				}
				System.out.print(file.getParentFile() + file.getName());
				if(file.isDirectory()){
					System.out.println("/");
					list(file);
				}else{
					System.out.println("");
				}
			}
		}else{
			System.out.println(f.getName());
		}
	}

	public static void main(String[] args) {
		File rootFile = new File("/Users/jie/Documents/git/gitRepository/coding2017/group23/1028361767");
		new FileList().list(rootFile);
	}
}