package com.lrn.leetcode.amazon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Minesweeper529 {
	/**
	 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
	 * If an empty square ('E')
	 *    : find adjacent squares.
	 *             : if mines found
	 *                   :then change it to a digit ('1' to '8') representing the number of adjacent mines
	 *             : if mines not found
	 *             	     : then change it to revealed blank ('B') and reveal all of its adjacent squares marking each as E
	 *                     and repeat above process each block
	 */

	public static char[][] updateBoard(final char[][] board, final int[] click) {
		if((board==null) || (board.length == 0)) {
			return board;
		}
		if(board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}
		/*bfs*/
		Queue<int[]> queue = new LinkedList<>();
		queue.add(click);
		while(!queue.isEmpty()) {
			int[] ck = queue.poll();
			int row=ck[0];
			int col=ck[1];
			int mineCount = findMines(board, ck);
			if(mineCount>0) {
				board[row][col] = (char)(mineCount+'0');
			} else {
				List<int[]> lst = findAdj(board, ck);
				lst.forEach(block->{
					if(board[block[0]][block[1]] == 'E') {
						board[block[0]][block[1]] = 'B';
						queue.add(block);
					}

				});
			}
		}

		return board;
	}

	public static int findMines(final char[][] board, final int[] click) {
		int crow=click[0];
		int ccol=click[1];
		int mineCount=0;
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				int row=(crow+i);
				int col = (ccol+j);
				if((i==0) && (j==0)) {
					//System.out.print(" ["+row+","+col +"] ");
					continue;
				}
				if((row<0)||(col<0)||(row>=board.length)||(col>=board[0].length)) {
					continue;
				}
				if(board[row][col] == 'M') {
					mineCount++;
				}
				//System.out.print(" "+row+","+col +" ");
			}
			//System.out.println("\n");
		}
		return mineCount;
	}
	public static List<int[]> findAdj(final char[][] board, final int[] click) {
		int crow=click[0];
		int ccol=click[1];
		List<int[]> lst = new ArrayList<>();
		for(int i=-1; i<=1; i++) {
			for(int j=-1; j<=1; j++) {
				int row=(crow+i);
				int col = (ccol+j);
				if((i==0) && (j==0)) {
					//System.out.print(" ["+row+","+col +"] ");
					continue;
				}
				if((row<0)||(col<0)||(row>=board.length)||(col>=board[0].length)) {
					continue;
				}
				int[] ck = {row,col};
				lst.add(ck);
				//System.out.print(" "+row+","+col +" ");
			}
			//System.out.println("\n");
		}
		return lst;
	}

	public static void main(final String[] args) {
		int[] arr = {3,0};
		char[][] board = {{'E', 'E', 'E', 'E', 'E'},
				{'E', 'E', 'M', 'E', 'E'},
				{'E', 'E', 'E', 'E', 'E'},
				{'E', 'E', 'E', 'E', 'E'}};
		//		for(int row=0; row<board.length; row++) {
		//			int[] clarr= new int[4];
		//			Arrays.fill(clarr, -1);
		//			board[row] = clarr;
		//		}
		//findMines(null, arr);
		char[][] board2 = updateBoard(board, arr);
		for(int i=0; i<board2.length; i++) {
			for(int j=0; j<board[2].length; j++) {
				System.out.print(" "+board2[i][j]+" ");
			}
			System.out.println("");
		}
	}



}
