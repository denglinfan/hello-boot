package com.example.helloboot.articles;

import java.util.*;
import java.util.stream.Stream;

/**
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 *  Return:

 *  [
 *      ["ate", "eat","tea"],
 *      ["nat","tan"],
 *      ["bat"]
 *  ]
 */
public class GroupAnagrams {

    /**
     * my own way
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<List<String>>();
        if(strs == null || strs.length == 0){
            return result;
        }

        Map<String,List<String>> resultMap = new HashMap<>();
        Arrays.stream(strs).forEach(value -> {
            char[] charValues = value.toCharArray();
            Arrays.sort(charValues);
            String mainKey = String.valueOf(charValues);
            List<String> childs = resultMap.get(mainKey);
            if(childs == null){
                childs = new ArrayList<>();
                resultMap.put(mainKey,childs);
                result.add(childs);
            }
            childs.add(value);
        });

        return result;
    }

    /**
     * The better way
     * @param strs
     * @return
     */
    public static List<List<String>> groupAnagrams1(String[] strs){
        List<List<String>> result = new ArrayList<List<String>>();
        if (strs == null || strs.length == 0){
            return result;
        }
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103};

        Map<Integer,List<String>> map = new HashMap<Integer,List<String>>();
        Arrays.stream(strs).forEach(str -> {
            char[] charStr = str.toCharArray();
            int key = 1;
            for(int i = 0; i < charStr.length; i++){
                key *= prime[charStr[i] - 'a'];
            }
            List<String> list = map.get(key);
            if(list == null){
                list = new ArrayList<String>();
                map.put(key,list);
                result.add(list);
            }
            list.add(str);
        });
        return result;
    }

    public static void main(String[] args){
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        /*String[] strs = {"cab","tin","pew","duh","may","ill","buy","bar","max","doc"};*/
        long startTime = new Date().getTime();
        List<List<String>> result = groupAnagrams1(strs);
        long endTime = new Date().getTime();
        System.out.println("耗费时间：" + (endTime - startTime));
        Stream.of(result).forEach(childResult -> System.out.println(childResult.toString()));
    }

}
