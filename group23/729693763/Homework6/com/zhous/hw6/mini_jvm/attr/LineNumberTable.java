package com.zhous.hw6.mini_jvm.attr;

import com.zhous.hw6.mini_jvm.loader.ByteCodeIterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by soulk on 2017/5/6.
 */
public class LineNumberTable extends AttributeInfo{

    List<LineNumberItem> items = new ArrayList<LineNumberItem>();

    public LineNumberTable(int attrNameIndex, int attrLen) {
        super(attrNameIndex, attrLen);

    }
    public static LineNumberTable parse(ByteCodeIterator iter) {

        int index = iter.nextU2ToInt();
        int len = iter.nextU4ToInt();
        LineNumberTable table = new LineNumberTable(index,len);

        int lineNumberTableLen = iter.nextU2ToInt();

        for(int i=1; i<=lineNumberTableLen; i++){
            LineNumberItem item = new LineNumberItem();
            item.setStartPC(iter.nextU2ToInt());
            item.setLineNum(iter.nextU2ToInt());
            table.addLineNumberItem(item);
        }
        return table;
    }


    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("Line Number Table:\n");
        for(LineNumberItem item : items){
            buffer.append("startPC:"+item.getStartPC()).append(",");
            buffer.append("lineNum:"+item.getLineNum()).append("\n");
        }
        buffer.append("\n");
        return buffer.toString();
    }

    private void addLineNumberItem(LineNumberItem item) {
        this.items.add(item);

    }
    //内部类，每一行只属于Table中的；所以定位为内部类
    private static class LineNumberItem{
        int startPC;
        int lineNum;
        public int getStartPC() {
            return startPC;
        }
        public void setStartPC(int startPC) {
            this.startPC = startPC;
        }
        public int getLineNum() {
            return lineNum;
        }
        public void setLineNum(int lineNum) {
            this.lineNum = lineNum;
        }
    }

}
