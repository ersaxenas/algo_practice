package com.lrn.leetcode.amazon;

public class GlobalandLocalInversions775 {

	public boolean isIdealPermutation(final int[] A) {
		int N = A.length;
		int global=0;
		int local =0;
		for(int i=0; i<=(N-2); i++) {
			/*local*/
			if(A[i] > A[i+1]) {
				local++;
			}
			for(int j=i+1; j<N; j++) {
				/*global check*/
				if(A[i]>A[j]) {
					global++;
				}
			}
		}
		System.out.println("Global "+ global + " local "+ local);
		return global == local;
	}

	public boolean isIdealPermutation2(final int[] A) {
		int N = A.length;
		/*local = global
		 * for i, i+1, i+2
		 * for global == local
		 * arr[i] should not be greater then arr[i+2]
		 * if arr[i] < arr[i+2] then it will make global +1 which will be greater then local
		 * at any given array index value of arr[i] can not be greate then arr[i+1]
		 * here value is max of arr[0] to arr[i]
		 * */
		int imax =0;
		for(int i=0; i<=(N-2); i++) {
			imax = Math.max(imax, A[i]);
			if(imax > A[i+2]) {
				return false;
			}
		}
		return true;
	}

	public static void main(final String[] args) {
		GlobalandLocalInversions775 obj = new GlobalandLocalInversions775();
		//int[] A = {1,0,2};
		int[] A = {1,2,0};
		System.out.println(obj.isIdealPermutation(A));

	}

}
