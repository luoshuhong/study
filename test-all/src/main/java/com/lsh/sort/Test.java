package com.lsh.sort;


/**
 * 
 * @author luoshuhong
 *	date 2014-12-15
 */
public class Test {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(F(15));
		System.out.println(F(16));
		System.out.println(F(17));
		System.out.println(F(18));
		
//		int[] a = { 5, 2, 9, 4, 9, 1 };
//		int[] b = {5, 4, 9, 4, 9};
//		
//		int largeLen = -1;  //长度
//		int starIndex = 0;  //子数组开始位置
//		for (int i = 0; i < a.length; i++) {
//			for (int j = 0; j < b.length; j++) {
//				int k = i, f = j, length = 0, index = 0;
//				while (k < a.length && f < b.length) {
//					if (a[k] == b[f]) {
//						length ++;
//						index = i;
//						
//						k ++;
//						f ++;
//					} else {
//						break;
//					}
//				}
//				
//				//
//				if (largeLen < length) {
//					largeLen = length;
//					starIndex = index;
//				}
//			}
//		}
//		
//		//打印
//		int count = 0; 
//		for (int i = starIndex; i < a.length; i++ ) {
//			count ++;
//			System.out.print(a[i] + " ");
//			if (count == largeLen) {
//				break;
//			}
//		}
	}
	
	public static long F(long n) {
		try {
			if (n == 1 || n == 0) {
				return 1;
			}
		} catch (Error e) {
			e.printStackTrace();
		}
		
		return n * F(n - 1);
	}
}	
