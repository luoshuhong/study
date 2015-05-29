package com.lsh.test;

import com.lsh.test.a.A;
import com.lsh.test.b.B;

public class Test {
	public static void main(String[] args) {
//		int num = 6000000;
//		int sec = 24 * 60 * 60;
//		
//		System.out.println((num * 0.8) / (sec * 0.2));
		
//		A a = new A();
//		B b = new B();
//		
//		AbstaractC bb = (A)a;
		
		
		System.out.println(1540 *8);
		
		
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("----");
				
			}
		});
		
		thread.setDaemon(true);
		
	}
}
