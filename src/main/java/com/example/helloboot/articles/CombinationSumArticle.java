package com.example.helloboot.articles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Given a set of candidate numbers (C) (without duplicates) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 *      Note:
 *          All numbers (including target) will be positive integers.
 *          The solution set must not contain duplicate combinations.
 *          For example, given candidate set [2, 3, 6, 7] and target 7,
 *          A solution set is:
 *          [
 *              [7],
 *              [2, 2, 3]
 *          ]
 */
public class CombinationSumArticle {

    //次优的算法实现
    private static List<List<Integer>> solution;

    private static List<Integer> curSolution;

    public static List<List<Integer>> combinationSum0(int[] candidates, int target) {
        solution = new ArrayList<List<Integer>>();
        curSolution = new ArrayList<Integer>();
        Arrays.sort(candidates);
        backTrack(candidates, target, 0);
        return solution;
    }

    private static void backTrack(int[] candidates, int target, int lastIdx) {
        if (target == 0) {
            solution.add(new ArrayList<Integer>(curSolution));
        }else if (target < 0) {
            return;
        }else {
            int i = lastIdx;
            while (i < candidates.length) {
                int candidate = candidates[i];
                curSolution.add(candidate);
                backTrack(candidates, target - candidate, i);
                curSolution.remove(curSolution.size() - 1);
                while (i < candidates.length && candidates[i] == candidate) {
                    i++;
                }
            }
        }
    }


    //更优的算法实现
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(candidates);
        dfs(res, candidates, target, new ArrayList<Integer>(), 0);
        return res;
    }

    private static void dfs(List<List<Integer>> res, int[] candidates, int target, List<Integer> path, int start) {
        if(target == 0) {
            res.add(new ArrayList<Integer>(path));
            return;
        }
        if(target > 0) {
            for(int i = start;i < candidates.length && target >= candidates[i];i ++) {
                path.add(candidates[i]);
                dfs(res, candidates, target - candidates[i], path, i);
                path.remove(path.size()-1);
            }
        }
    }

    public static void main(String[] args){
        /*int[] candidates = {1,4,6,8,10,13,16,19};*/
        int[] candidates = IntStream.range(1,8).toArray();

        long startTime = new Date().getTime();
        List<List<Integer>> childArrays = combinationSum(candidates,20);
        long endTime = new Date().getTime();
        System.out.println("总耗时：" + (endTime - startTime));

        int childCount = 0;
        if(childArrays != null && childArrays.size() > 0){
            for(List<Integer> childArray : childArrays){
                System.out.print("这是第" + (++childCount) + "个符合的子集:");
                System.out.println(childArray.toString());
            }
        }
        Date date = new Date();
        date.getTime();
    }

}
