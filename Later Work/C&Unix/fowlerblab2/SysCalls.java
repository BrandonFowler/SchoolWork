//CSCD340
//Lab2
//Brandon Fowler

import java.util.Scanner;
import java.io.*;

public class SysCalls{
	public static void main(String[] args)throws FileNotFoundException{
		Scanner fin = new Scanner(new File("test.txt"));
		while(fin.hasNext()){
			String str = fin.nextLine();
			System.out.println(str);
		}
		fin.close();
	}
}
