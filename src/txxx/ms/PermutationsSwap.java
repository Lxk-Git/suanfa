package txxx.ms;

import java.util.ArrayList;
import java.util.List;

public class PermutationsSwap {
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, result);
        return result;
    }

    private static void backtrack(int[] nums, int start, List<List<Integer>> result) {
        if (start == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int num : nums) {
                permutation.add(num);
            }
            result.add(permutation);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i); // 交换元素
            backtrack(nums, start + 1, result);
            swap(nums, start, i); // 撤销交换
        }
    }

    public static void backtrack3(int[] nums,int start,List<List<Integer>> result){
        if(start == nums.length){
            List<Integer> list = new ArrayList<>();
            for (int num : nums) {
                list.add(num);
            }
            result.add(list);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(nums,start,i);
            backtrack3(nums,start+1,result);
            swap(nums,start,i);
        }
    }



    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<List<Integer>> permutations = permute(nums);
        System.out.println(permutations);
    }
}

