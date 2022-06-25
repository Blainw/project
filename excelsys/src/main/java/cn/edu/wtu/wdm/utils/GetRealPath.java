package cn.edu.wtu.wdm.utils;

import java.io.File;

public class GetRealPath {
    public static String getPath(Object obj){
        File file4 = new File(obj.getClass().getResource("").getPath());
        String path=file4.toString();
        if (path.contains(".")) {
            int index = path.indexOf("excelsys");
            path = path.substring(0, index+8);
        }
        return path;
    }
}
