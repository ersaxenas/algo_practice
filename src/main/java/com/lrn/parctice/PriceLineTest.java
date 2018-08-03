package com.lrn.parctice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PriceLineTest {

	interface aa {
		public void f1() throws Exception;
		public void f2();
	}

	class bb implements aa {
		@Override
		public void f1() throws Exception {}
		@Override
		public void f2() {}
		public void f3() {}

		public void main(final String[] args) {
			aa obj = new bb();
			//obj.f1();
			List<Integer> lst = new ArrayList<>();
			lst.add(1);
			lst.stream().filter(i->i>10).collect(Collectors.toList());
		}



	}

}
