package com.example.helloboot.articles.sort;

import java.util.Arrays;

/**
 * Created with IDEA
 * Description: 堆排序实现
 * User: Charles
 * Date: 2018-07-05 21:25
 */
public class HeadSort {

    public static void swap(Integer[] array, Integer i, Integer j){
        Integer tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void maxHeapify(Integer[] array, Integer index, Integer heapSize){
        int iMax,iLeft,iRight;
        while(true){
            iMax = index;
            iLeft = 2 * index + 1;
            iRight = 2 * (index + 1);

            if(iLeft < heapSize && array[index] < array[iLeft]){
                iMax = iLeft;
            }
            if(iRight < heapSize && array[iMax] < array[iRight]){
                iMax = iRight;
            }

            if(iMax != index){
                swap(array,iMax,index);
                index = iMax;
            }
        }
    }

    public static void buildMaxHeap(Integer[] array){
        int iParent = array.length/2 - 1;
        for(int i = iParent; i>0;i--){
            maxHeapify(array,i,array.length);
        }
    }

    public static Integer[] sort(Integer[] array){
        buildMaxHeap(array);

        for(int i = array.length - 1; i > 0; i--){
            swap(array,0,i);
            maxHeapify(array,0,i);
        }

        return array;
    }

    public static void main(String[] args){
        Integer[] array = {3,6,1,19,4,54,24,76,34,10};
        Arrays.asList(array).forEach(n-> System.out.println(n));
        sort(array);
        Arrays.asList(array).forEach(n-> System.out.println(n));
    }
}
