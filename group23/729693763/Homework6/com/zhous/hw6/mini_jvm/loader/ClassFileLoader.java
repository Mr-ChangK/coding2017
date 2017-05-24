package com.zhous.hw6.mini_jvm.loader;

/**
 * Created by soulk on 2017/4/17.
 */

import com.zhous.hw6.mini_jvm.clz.ClassFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 *支持简单的classpath设置
 * 从文件系统读取一个文件，形成字节数组
 * 验证该文件的魔数
 */

public class ClassFileLoader {

    private List<String> clzPaths = new ArrayList<String>();

    /*
    @className: class的文件名； 从clzPaths中查找是否存在这个
    @return: 存在就返回该文件读取的字节数组, 不存在返回 null
     */
    public byte[] readBinaryCode(String className) {

        //预处理className,将.换成\\，并添加.class
        StringBuilder sb = new StringBuilder(className);
        while(sb.indexOf(".") != -1){
            int start = sb.indexOf(".");
            sb.replace(start,start+1,"\\");
        }
        className = "\\" + sb.append(".class").toString();

        //如果存在多个classPath的话，
        List<String> filePaths = new ArrayList<String>();
        for (String i : clzPaths) {
            filePaths.add(i+className);
        }

        //逐个查询，是否存在这个文件,找到就退出
        File file = null;
        Iterator i = filePaths.iterator();
        while (true){
            file = new File((String)i.next() );
            if(file.exists()){
                break;
            } else if(i.hasNext() ){
                file = null;
            } else{
                break;
            }
        }

        //看文件是否存在，不存在直接退出
        if( !file.exists()){
            return null;
        }

        //加载ClassFile
        int len = (int) file.length();
        byte[] data = new byte[len];
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            try {
                fis.read(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return data;

    }



    public void addClassPath(String path) {

        if(this.clzPaths.contains(path)){
            return;
        } else if(!("".equals(path) ) ){
            clzPaths.add(path);
        }
    }

    public String getClassPath(){
        StringBuilder sb = new StringBuilder();
        Iterator i = this.clzPaths.iterator();

        while(i.hasNext()){
            sb.append(i.next());
            sb.append(";");
        }
        //去除多余的 ';'
        return sb.substring(0,sb.length()-1);
    }

    public ClassFile loadClass(String className) {
        byte[] codes = this.readBinaryCode(className);
        ClassFileParser parser = new ClassFileParser();
        return parser.parse(codes);

    }
}
