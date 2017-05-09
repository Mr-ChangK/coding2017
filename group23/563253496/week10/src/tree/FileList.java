package tree;

/**
 * Created by bdl19 on 2017/5/8.
 */

import java.io.File;

public class FileList {


    public static void list(File f,int s) {

        /* if (f.isDirectory()) {
            File[] files = f.listFiles();
            try {
                for (int i = 0; i < files.length; i++) {
                    list(files[i]);
                }

            } catch (NullPointerException e) {
            }
        } else {
            System.out.println(f.getName());

        }*/
        for (int i = 0; i < s; i++) {
            System.out.print("      ");
        }
        System.out.println(f.getName());
        if (f.isDirectory()) {
            try{
                File[] files = f.listFiles();
                for (File file : files
                        ) {
                    list(file,s+1);
                }
            }catch(NullPointerException e){}
        }

    }



    /*private static void recursion(File f, int spacing) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();

            for (File file : files
                    ) {
                recursion(file);
            }
        }
        System.out.println(f.getName());

    }*/

    public static void main(String[] args) {
        File file = new File("e:");
        list(file,0);
    }
}