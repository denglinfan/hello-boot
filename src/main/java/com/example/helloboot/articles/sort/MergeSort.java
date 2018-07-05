package com.example.helloboot.articles.sort;

import java.util.Arrays;

/**
 * Created with IDEA
 * Description: 归并排序实现
 * User: Charles
 * Date: 2018-07-05 22:01
 */
public class MergeSort {

    public static Integer[] sort(Integer[] array, Integer first, Integer last){
        if(first == null){
            first = 0;
        }
        if(last == null){
            last = array.length - 1;
        }
        if(last - first < 1){
            return null;
        }

        int middle = (first + last) /2;
        sort(array,first,middle);
        sort(array,middle+1,last);

        int f = first,m = middle,i,tmp;
        while (f <= m && m + 1 <= last){
            if(array[f] >= array[m + 1]){
                tmp = array[m + 1];
                for(i = m; i >= f; i--){
                    array[i + 1] = array[i];
                }
                array[f] = tmp;
                m++;
            }else{
                f++;
            }
        }

        return array;
    }

    public static void main(String[] args){
        Integer[] array = {3,6,1,19,4,54,24,76,34,10};
        Arrays.asList(array).forEach(n-> System.out.print(n + "-"));
        sort(array,0,array.length - 1);
        Arrays.asList(array).forEach(n-> System.out.print(n + "-"));
    }
}
