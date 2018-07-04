package com.lrn.parctice;

public class Amazon20180702_2 {

	class Data implements Comparable<Data>{
		String line;
		String id;
		String substr;

		@Override
		public int compareTo(final Data o) {
			int res = substr.compareTo(o.substr);
			if(res==0) {
				res = id.compareTo(o.id);
			}
			return res;
		}

	}

}
