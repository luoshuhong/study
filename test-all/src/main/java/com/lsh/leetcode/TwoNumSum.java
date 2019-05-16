package com.lsh.leetcode;

import com.lsh.util.ArrayUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 * @see  https://leetcode-cn.com/problems/two-sum/
 * Created by luoshuhong on 2019/5/16.
 */
public class TwoNumSum {
    public static void main(String[] args) {
        ArrayUtils.print(twoSum(new int[]{2, 7, 11, 15}, 9));
        ArrayUtils.print(twoSum0(new int[]{2, 7, 11, 15}, 9));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> temMap = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            temMap.put(nums[i], i);
        }

        for (int i = 0; i < nums.length; i++) {
            int sub = target - nums[i];
            if (temMap.containsKey(sub) && temMap.get(sub) != i)  {
                return new int[]{i,temMap.get(sub)};
            }
        }

        return new int[2];
    }

    public static int[] twoSum0(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i,j};
                }
            }
        }

        return new int[2];
    }


}
