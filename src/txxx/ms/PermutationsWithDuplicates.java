package txxx.ms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsWithDuplicates {
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums); // 排序以便跳过重复元素
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        //达到和原来的数组长度一样就递归结束
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //跳过已经处理过的元素
            if (used[i]) {
                continue;
            }
            // 跳过相邻重复的元素
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }
            current.add(nums[i]);
            used[i] = true;
            backtrack(nums, used, current, result);
            //递归结束要删除掉临时的数组中的元素
            current.remove(current.size() - 1);
            //状态也要换成false
            used[i] = false;
        }
    }

    public static void backtrack2(int[] nums,boolean[] used,List<Integer> current,List<List<Integer>> result){
        if(current.size() == nums.length){
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i]){
                continue;
            }
            if(i>0 && nums[i] == nums[i-1] && !used[i-1]){
                continue;
            }
            current.add(nums[i]);
            used[i]= true;
            backtrack2(nums,used,current,result);
            current.remove(current.size()-1);
            used[i]= false;
        }
    }

    public static void backtrack4(int[] nums,boolean[] used,ArrayList<Integer> cur,ArrayList<ArrayList<Integer>> result){
        if(nums.length ==cur.size()){
            result.add(new ArrayList<>(cur));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if(used[i]){
                continue;
            }
            if(i>0&&nums[i]==nums[i-1]&& !used[i]){
                continue;
            }
            cur.add(nums[i]);
            used[i] = true;
            backtrack4(nums,used,cur,result);
            cur.remove(cur.size()-1);
            used[i] = false;
        }
    }
    public static List<List<Integer>> permuteUnique2(int[] nums){
        boolean[] used = new boolean[nums.length];
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(nums,used,current,result);
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2};
        List<List<Integer>> permutations = permuteUnique2(nums);
        System.out.println(permutations);
    }
}
