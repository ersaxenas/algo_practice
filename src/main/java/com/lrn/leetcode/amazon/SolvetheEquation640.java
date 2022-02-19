package com.lrn.leetcode.amazon;

import java.util.Stack;

public class SolvetheEquation640 {

	public static String solveEquation(final String equation) {
		if((equation == null) || (equation.length()==0)) {
			return "No solution";
		}
		String[] spl = equation.split("=");
		Integer[] left = reduceEquation(spl[0]);
		Integer[] right = reduceEquation(spl[1]);
		int xval = left[0] + (right[0]*-1);
		int yval = (left[1]*-1) + right[1];
		if((xval==0) && (yval ==0)) {
			return "Infinite solutions";

		}
		if((xval ==0) && (yval !=0)) {
			return "No solution";
		}
		return "x="+String.valueOf(yval/xval);
	}




	public static Integer[] reduceEquation( String equation) {
		if(!equation.startsWith("-")){
			equation = "+"+equation;
		}
		int xcount=0;
		int ycount=0;
		Stack<Integer> stackx = new Stack<>();
		Stack<Integer> stacky = new Stack<>();
		boolean xfound = false;
		String num = "";
		for(int cnt=equation.length()-1; cnt>=0; cnt--) {
			if(!xfound && (equation.charAt(cnt) == 'x')) {
				xfound = true;
				continue;
			} else if(equation.charAt(cnt) == '+') {
				if(num.length()==0) {
					num = "1";
				}
				if(xfound) {
					stackx.push(Integer.valueOf(num));
					xfound=false;
				} else {
					stacky.push(Integer.valueOf(num));
				}
				num="";
			} else if(equation.charAt(cnt) == '-') {
				if(num.length()==0) {
					num = "1";
				}
				if(xfound) {
					stackx.push(Integer.valueOf(num)*-1);
					xfound=false;
				} else {
					stacky.push(Integer.valueOf(num)*-1);
				}
				num="";
			} else {
				num = equation.charAt(cnt) + num ;
			}
		}
		while(!stackx.isEmpty()) {
			xcount = xcount + stackx.pop();
		}
		while(!stacky.isEmpty()) {
			ycount = ycount + stacky.pop();
		}
		Integer arr[] = new Integer[2];
		arr[0] = xcount;
		arr[1] = ycount;
		return arr;
	}

	public static void main(final String[] args) {

		System.out.println(solveEquation("x+5-3+x=6+x-2"));
		System.out.println(solveEquation("x=x"));
		System.out.println(solveEquation("2x=x"));
		System.out.println(solveEquation("2x+3x-6x=x+2"));
		System.out.println(solveEquation("x=x+2"));

	}

}
