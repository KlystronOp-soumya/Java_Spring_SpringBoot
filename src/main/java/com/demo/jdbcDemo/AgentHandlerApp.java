package com.demo.jdbcDemo;

import java.util.Scanner;

public class AgentHandlerApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scr = new Scanner(System.in);
		int t;
		int[] ar = new int[5];
		try {
			for(int i = 0;i< 10 ;i++)
			{
				t=scr.nextInt();
				ar[i] = t;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
			System.out.println("Sorry Array size is 5");
		}
		
	}

}
