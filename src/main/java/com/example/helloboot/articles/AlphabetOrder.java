package com.example.helloboot.articles;

import java.util.Arrays;

/**
 * 字典序算法
 * 主流程，返回最近一个大于自身的相同数字组成的整数
 */
public class AlphabetOrder {

    public static int[] findNearestNumber(int[] numbers){
        //拷贝入参，避免直接修改入参
        int[] numberCopy = Arrays.copyOf(numbers,numbers.length);
        /**
         * 1.从后面向前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
         */
        int index = findTransferPoint(numberCopy);

        //如果数字置换边界是0，说明整个数组已经逆序，无法得到更大的相同数字组成的整数，返回自身
        if(index == 0){
            return null;
        }

        /**
         * 2.把逆序区域的前一位和逆序区域中刚刚大于他的数字交换位置
         */
        exchangeHead(numberCopy,index);
        /**
         * 3.把原来的逆序区域装换为顺序
         */
        reverse(numberCopy,index);
        return numberCopy;
    }

    public static int findTransferPoint(int[] numbers){
        for(int i = numbers.length - 1; i > 0; i--){
            if(numbers[i] > numbers[i-1]){
                return i;
            }
        }
        return 0;
    }

    public static int[] exchangeHead(int[] numbers, int index){
        int head = numbers[index - 1];
        for(int i = numbers.length-1; i > 0; i--){
            if(head < numbers[i]){
                numbers[index - 1] = numbers[i];
                numbers[i] = head;
                break;
            }
        }
        return numbers;
    }

    private static int[] reverse(int[] numberCopy, int index) {
        for(int i = index,j = numberCopy.length-1; i < j; j--,i++){
            int temp = numberCopy[i];
            numberCopy[i] = numberCopy[j];
            numberCopy[j] = temp;
        }
        return numberCopy;
    }

    public static void main(String[] args){
        int[] numbers = {1,2,3,4,5};
        for(int i = 0; i < 10; i++){
            numbers = findNearestNumber(numbers);
            outputNumbers(numbers);
        }
    }

    private static void outputNumbers(int[] numbers){
        for(int i : numbers){
            System.out.print(i);
        }
        System.out.println();

    }
}
