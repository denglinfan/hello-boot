package com.example.helloboot.articles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Given a collection of distinct numbers, return all possible permutations.

 * For example,
 * [1,2,3] have the following permutations:
 * [
 *  [1,2,3],
 *  [1,3,2],
 *  [2,1,3],
 *  [2,3,1],
 *  [3,1,2],
 *  [3,2,1]
 * ]
 */
public class Permutations {

    /**
     * find all of the possible permutations
     * @param distincts a collection of distince numbers
     * @return all possible permutations
     */
    public static List<List<Integer>> permutations(List<Integer> distincts){

        //1.当传入集合为空时，直接返回空
        if(distincts == null || distincts.size() == 0){
            return null;
        }

        List<List<Integer>> permutations = new ArrayList<List<Integer>>();
        //2.当传入集合数量只有一个时，直接将其封装返回
        if(distincts.size() == 1){
            permutations.add(distincts);
            return permutations;
        }

        //3.否则，取出集合中的第一个值，剩余子集进行迭代处理
        Integer firstNum = distincts.remove(0);
        List<List<Integer>> childPermutations = permutations(distincts);

        for(List<Integer> child : childPermutations){
            int totalNum = child.size() + 1;
            for(int i = 0; i < totalNum ; i++){
                List<Integer> middleParams = new ArrayList<Integer>();
                middleParams.addAll(child);

                middleParams.add(i,firstNum);
                permutations.add(middleParams);
            }
        }

        return permutations;
    }

    /**
     * print the result of permutations
     * @param permutations the collections
     */
    public static void listToString(List<List<Integer>> permutations){
        System.out.println("[");
        for(List<Integer> permutation : permutations){
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for(Integer childNum : permutation){
                sb.append(childNum);
                sb.append(",");
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
        System.out.println("]");
    }

    /**
     * This is my own method
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        //这里使用的是jdk8中的stream来对集合进行操作
        return permutations(Arrays.stream(nums).boxed().collect(Collectors.<Integer>toList()));
    }

    /**
     * This is the better method(maybe)
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteBetter(int[] nums){
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(nums == null || nums.length == 0){
            return result;
        }
        DFSHelper(result,nums,0);
        return result;
    }

    private static void DFSHelper(List<List<Integer>> result, int[] nums, int index) {
        if(index == nums.length){
            List<Integer> path = Arrays.stream(nums).boxed().collect(Collectors.<Integer>toList());
            result.add(path);
            return ;
        }

        for(int i = index; i < nums.length; i++){
            swap(nums, index, i);
            DFSHelper(result,nums,index+1);
            swap(nums,index,i);
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args){
        int[] distincts = IntStream.range(1,4).toArray();
        Long beginTime = new Date().getTime();
        List<List<Integer>> permutations = permuteBetter(distincts);
        /*List<List<Integer>> permutations = permute(distincts);*/
        Long endTime = new Date().getTime();

        Long spendTime = endTime - beginTime;
        System.out.println("共花费时间：" + spendTime + "毫秒！");
        System.out.println("总共分解出组合：" + permutations.size() + "组！");
        listToString(permutations);
    }
}
