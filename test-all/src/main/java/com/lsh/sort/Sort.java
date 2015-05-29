package com.lsh.sort;



/**
 * 排序
 * @author luoshuhong
 *	date 3015-1-2
 */
public class Sort {
	
	/**  
	 * 冒泡法排序<br/>  
	 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。 
	 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。
	 * 在这一点，最后的元素应该会是最大的数。
	 * 针对所有的元素重复以上的步骤，除了最后一个。  
	 * 持续每次对越来越少的元素重复上面的步骤，
	 * 直到没有任何一对数字需要比较。</li>  
	 * @param arr  需要排序的整型数组  
	 */  
	public static void bubbleSort (Integer[] arr) {
		if (null == arr || 0 == arr.length) {
			return;
		}
		
		int tmp = -1; //记录临时数据
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j ++) {
				if (arr[i] > arr[j]) {
					tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
	
	/**
	 *  直接插入排序
	 *  【思想】在要排序的一组数中，假设前面(n-1)[n>=2] 
	 *  个数已经是排好顺序的，现在要把第n个数插到前面的
	 *  有序数中，使得这n个数也是排好顺序的。如此反复循环，
	 *  直到全部排好顺序
	 * @param arr
	 */
	public static void  insertSort (Integer[] arr) {
		if (null == arr || 0 == arr.length) {
			return ;
		}
		
		int temp ; //存放临时数据
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];
			int j = i - 1;
			//将大于temp的值整体后移一个单位
			while (j >= 0 && temp < arr[j]) {    
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}
	}
	
	/**
	 * 简单选择排序
	 * 【思想】在要排序的一组数中，选出最小的一个数与第一个位置的； 
	 * 数交换然后在剩下的数当中再找最小的与第二个位置的数交换，
	 * 如此循环到倒数第二个数和最后一个数比较为止。
	 * @param arr
	 */
	public static void selectSort(Integer[] arr) {
		if (null == arr || 0 == arr.length) {
			return;
		}

		int min;
		for (int i = 0; i < arr.length; i++) {
			//查找最小的数
			min = i;
			for (int j = i; j < arr.length; j++) {  
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			
			if (min != i) {
				int temp = arr[i];
				arr[i] = arr[min];
				arr[min] = temp;
			}
		}
	}
	
	
	/**  
	 * 快速排序
	 * <li>从数列中挑出一个元素，称为“基准”</li>  
	 * <li>重新排序数列，所有元素比基准值小的摆放在基准前面，
	 * 所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。在这个分割之后，  
	 * 该基准是它的最后位置。这个称为分割（partition）操作。</li>  
	 * <li>递归地把小于基准值元素的子数列和大于基准值元素的子数列排序。</li>  
	 * @param arr  
	 * @param start  
	 * @param end  
	 */ 
	public static void quickSort(Integer[] arr, int start, int end) {
		if (start < end) {   
	        int base = arr[start]; // 选定的基准值（第一个数值作为基准值）
	        int i = start, j = end;   
	        while (i <= j) { 
	        	//从左边找出比base大的数
	            while ((arr[i] < base) && (i < end))   
	                i++;   
	          //从右边找出比base小的数
	            while ((arr[j] > base) && (j > start))   
	                j--;   
	            if (i <= j) { 
	                int temp = arr[i];
	                arr[i] = arr[j];
	                arr[j] = temp;
	                i++;
	                j--;
	            }   
	        } 
	        //继续处理左边
	        if (start < j) { 
	        	quickSort(arr, start, j); 
	        }
	        //继续处理右边 
	        if (end > i) { 
	        	quickSort(arr, i, end);  
	        }
	    }   
	}
	
	public static void _quickSort (Integer[] arr, int start, int end) {
		if (null == arr || 0 == arr.length) {
			return;
		}
		
		if (start >= end) {
			return;
		}
		
		int i = start, j = end;
		int base = arr[i];   //已数组第一个作为基数
		while (i <= j) {
			while (arr[i] < base && i < end) {    //从左边找出比base大的数
				i++;
			}
			while (arr[j] > base && j > start) {   //从右边找出比base小的数
				j--;
			}
			
			if (i <= j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				
				i++;
				j--;
			}
		}
		
		if (j > start) {
			_quickSort(arr, start, j);
		}
		if (i < end) {
			_quickSort(arr, i, end);
		}
	}
}
