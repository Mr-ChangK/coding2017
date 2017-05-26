package tree;

import java.io.File;

import org.junit.Test;

public class FileListTest {
	
	@Test
	public void test(){
		FileList fl = new FileList();
		File f = new File("E:\\java学习提高班资料");
		fl.list(f);
	}
	
}
