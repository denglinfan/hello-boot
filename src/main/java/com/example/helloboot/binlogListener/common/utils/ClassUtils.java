package com.example.helloboot.binlogListener.common.utils;

public class ClassUtils {

    public static String getShortClassName(String className){
        if(className == null){
            return "";
        }
        if(className.length() == 0){
            return "";
        }
        StringBuilder arrayPrefix = new StringBuilder();

        //处理数组字符
        if(className.startsWith("[")){
            while(className.charAt(0) == '['){
                className = className.substring(1);
                arrayPrefix.append("[]");
            }

            if(className.charAt(0) == 'L' && className.charAt(className.length() - 1) == ';'){
                className = className.substring(1,className.length() - 1);
            }
        }

        int lastDotIdx = className.lastIndexOf('.');
        int innerIdx = className.indexOf('$', lastDotIdx == -1 ? 0 : lastDotIdx + 1);
        String out = className.substring(lastDotIdx + 1);
        if(innerIdx != -1){
            out = out.replace('$','.');
        }
        return out + arrayPrefix;
    }
}
