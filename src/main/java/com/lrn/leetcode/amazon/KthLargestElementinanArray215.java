package com.lrn.leetcode.amazon;

import java.util.Random;

public class KthLargestElementinanArray215 {




	public static int findKthElement(final Integer[] arr, final int k) {
		shuffle(arr);
		int kl = arr.length-k;
		int high = arr.length -1;
		int low = 0;
		while(high>low) {
			int pElem = partition(arr, low, high);
			if(kl < pElem) {
				high = pElem -1;
			} else if(kl > pElem) {
				low = pElem + 1;
			} else {
				return arr[kl];
			}
		}
		return arr[kl];
	}


	public static void sort(final Integer[] arr, final int low, final int high) {
		if(low >=high) {
			return;
		}

		int partition = partition(arr, low, high);
		/*sort left side of the array*/
		sort(arr, low, partition -1);
		/*sort right side of the array*/
		sort(arr, partition +1, high);
	}



	/*quick sort partitioning*/
	public static int partition(final Integer[] arr, final int low, final int high) {
		int partitionElement = arr[low];
		int leftIdx = low;
		int rightIdx = high+1;
		while(true) {

			while(arr[++leftIdx]<partitionElement) {
				if(leftIdx==rightIdx) {
					break;
				}
			}

			while(arr[--rightIdx]>partitionElement) {
				if(leftIdx==rightIdx) {
					break;
				}
			}

			if(leftIdx>rightIdx) {
				break;
			}

			swap(arr,leftIdx,rightIdx);
		}
		swap(arr, low, rightIdx);
		return rightIdx;
	}

	public static void shuffle(final Integer a[]) {

		final Random random = new Random();
		for(int ind = 1; ind < a.length; ind++) {
			final int r = random.nextInt(ind + 1);
			swap(a, ind, r);
		}
	}

	public static void swap(final Integer[] arr, final int idx1, final int idx2) {
		int tmp = arr[idx1];
		arr[idx1] = arr[idx2];
		arr[idx2] = tmp;
	}

	public static void main(final String[] args) {
		Integer[] arr = {3,2,1,5,6,4};
		//		System.out.println(Arrays.asList(arr));
		//		sort(arr, 0, arr.length-1);
		//		System.out.println(Arrays.asList(arr));
		System.out.println(findKthElement(arr, 2));
	}


}
