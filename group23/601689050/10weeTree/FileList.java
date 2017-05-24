import java.io.File;

/**
 * Created by lxx on 2017/5/9.
 */
public class FileList {
    public static void main(String args[]){
        File file = new File("E:/study");
        list(file,"");
    }
    public static String next = " - ";
    public static void list(File f,String str){
        if(f.isDirectory()){
            System.out.println(str+f.getName());
            File[] files = f.listFiles();
            for(File file : files ){
                String newstr = str+next;
                list(file,newstr);
            }
        }else {
            System.out.println(str+f.getName());
        }
    }
}
