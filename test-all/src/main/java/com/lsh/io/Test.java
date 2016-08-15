package com.lsh.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;


public class Test {
	static BufferedReader br;
	static BufferedWriter bw;
	static int i = 1;
	
	public static void main(String[] args) throws Exception {
		br = new BufferedReader(new FileReader("D:/alter_table_comment_comfirm.sql"));
		bw = new BufferedWriter(new FileWriter("D:/可以为空默认值为''.sql"));
		String line = null;
		int count = 0;
		while (null != (line = br.readLine())) {
			if (!line.contains("varchar") || line.contains("DEFAULT") || line.contains("not null")) {
				continue;
			}
			count ++;
			bw.write(line);
			bw.newLine();
		}
		br.close();
		bw.close();
	}
	
	public static void spitFile() throws Exception {
		bw.flush();
		bw.close();
		i ++;
		bw = new BufferedWriter(new FileWriter("D:/sql/" + i + ".sql"));
	}
	
}
