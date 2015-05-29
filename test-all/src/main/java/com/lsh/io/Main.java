package com.lsh.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Random;

/**
 * 
 * @author Luoshuhong
 * 2015年5月29日
 *
 */
public class Main {
	//2.看输出
//	static {
//		showInfo();
//	}
//	private static final int m = 10;
//	private static final String s = new String("123");
//	private static final String s2 = "456";
//	private static void showInfo(){
//		System.out.println(m);
//		System.out.println(s);
//		System.out.println(s2);
//		
//	}
	
	public static void main(String[] args) throws Exception {
		//1.看输出
//		String s1 = "123";
//		String s2 = "123";
//		String s3 = new String("123");
//		String s4 = s3.intern();
//		
//		System.out.println((s1==s2));
//		System.out.println(s2==s3);
//		System.out.println(s3==s4);
//		System.out.println(s1==s4);
//		System.out.println(s3==s4);
//		System.out.println(Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory());
		
		
		//3.从m个数中随机抽出n个数（不重复） 
//		System.out.println(Arrays.asList(randSelect(new Integer[]{1,2,3,4,5,6,7,8,9}, 5)));
//		System.out.println(Arrays.asList(randSelect(new Integer[]{1,2,3,4,5,6,7,8,9}, 5)));
//		System.out.println(Arrays.asList(randSelect(new Integer[]{1,2,3,4,5,6,7,8,9}, 5)));
//		System.out.println(Arrays.asList(randSelect(new Integer[]{1,2,3,4,5,6,7,8,9}, 5)));
//		System.out.println(Arrays.asList(randSelect(new Integer[]{1,2,3,4,5,6,7,8,9}, 5)));
//		System.out.println(Arrays.asList(randSelect(new Integer[]{1,2,3,4,5,6,7,8,9}, 5)));
		
		//IO
		ioTest();
		
		//
//		int total = 10;
//		int count = 0;
//		Random random = new Random();
//		for (int i = 0; i< total; i++) {
//			double x = random.nextDouble();
//			double y = random.nextDouble();
//			System.out.println("x:" + x + "  y:" + y + "  (x*x + y*y):" + (x*x + y*y));
//			if ((x * x + y * y) < 1) {
//				count ++;
//			}
//		}
//		System.out.println(((double)count)/total);
	}
	
	
	
	/**
	 * 从m个数中随机抽出n个数（不重复）
	 * @param data 原始数组
	 * @param n    选n个数
	 * @return 选出的数
	 */
	public static Integer[] randSelect(Integer[] data, int n) {
		if (null == data || data.length < n) {
			return null;
		}
		Integer[] result = new Integer[n];  //结果数据
		Random rand = new Random();
		//方法1
//		for (int i = 0; i < n; i++) {
//			int index = Math.abs(rand.nextInt()) % (data.length - i); //产生一个0-m的随机数  
//			result[i] = data[index];                  		//原始数组中以该随机数为下标的数为一次选取随机数  
//			data[index] = data[data.length - i - 1];        //每次将数据最后一个填补已经被选中的数
//		}
		//方法2
//		Set<Integer> selectData = new HashSet<Integer>();         //记录已经产生是随机数
//		for (int i = 0; i < n; i ++) {
//			int randNum = Math.abs(rand.nextInt() % data.length);
//			while (selectData.contains(randNum)) {                  //每次产生随机数  重复则重新产生
//				randNum = Math.abs(rand.nextInt() % data.length);
//			}
//			
//			selectData.add(randNum);
//			result[i] = data[randNum];
//		}
		return result;
	}
	
	
	public static void ioTest() throws Exception {
		
		//1.FileInputStream与FileOutputStream
		FileInputStream fin = new FileInputStream("D:/1.txt");
		FileOutputStream fout = new FileOutputStream("D:/2.txt");
		byte[] buffer = new byte[1024];
		while (-1 != (fin.read(buffer))) {
			fout.write(buffer);
		}
		fin.close();
		fout.close();
		
		
		//2.BufferedInputStream与BufferedOutputStream
//		BufferedInputStream bin = new BufferedInputStream(new FileInputStream("D:/1.txt"));
//		BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("D:/2.txt"));
//		byte[] buffer = new byte[1024];
//		while (-1 != (bin.read(buffer))) {
//			bout.write(buffer);
//		}
//		bin.close();
//		bout.close();
		
		//3.FileReader与FileWriter
//		FileReader fr = new FileReader("D:/1.txt");
//		FileWriter fw = new FileWriter("D:/2.txt");
//		char[] buffer = new char[1024];
//		while (-1 != (fr.read(buffer))) {
//			fw.write(buffer);
//		}
//		fr.close();
//		fw.close();
		
		//4.BufferedReader与BufferedWriter
//		BufferedReader br = new BufferedReader(new FileReader("D:/1.txt"));
//		BufferedWriter bw = new BufferedWriter(new FileWriter("D:/2.txt"));
//		String line = null;
//		while (null != (line = br.readLine())) {
//			bw.write(line);
//			bw.newLine();
//		}
//		br.close();
//		bw.close();
		
		
		//5.字节流和字符流的转化 InputStreamReader与OutputStreamWriter
		//字节流转换成字符流
//		FileInputStream fileInputStream = new FileInputStream("D:/1.txt");
//		BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream));
//		//字节流转换成字符流
//		FileOutputStream fileOutputStream = new FileOutputStream("D:/2.txt");
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
//		String line = null;
//		while (null != (line = br.readLine())) {
//			bw.write(line);
//			bw.newLine();
//		}
//		br.close();
//		bw.close();
	}
}







