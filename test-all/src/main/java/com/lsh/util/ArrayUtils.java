package com.lsh.util;

/**
 * Created by luoshuhong on 2019/5/16.
 */
public class ArrayUtils {
    /**
     * 打印
     * @param arr
     */
    public static void print(int arr[]) {
        if (null == arr || arr.length == 0) {
            return;
        }

        StringBuffer sb = new StringBuffer(arr[0] + "");
        for (int i = 1; i < arr.length; i ++) {
            sb.append(", ").append(arr[i]);
        }
        System.out.println(sb);
    }
}
