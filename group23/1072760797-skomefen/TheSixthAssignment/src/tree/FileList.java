package tree;

import java.io.File;

public class FileList {
	public void list(File f) {	
		
		if(f.isFile()){
			System.out.println(f);
			return;
		}

		File files[] = f.listFiles();
		for(File file:files){
			
			if(file.isFile()){
				System.out.println(file);
				continue;
			}
			list(file);
		}
		System.out.println(f);

	}

}
