package com.lrn.cci.stringsandarrays;

public class OneAway {

	public static void main(final String[] args) {
		OneAway obj = new OneAway();
		System.out.println(obj.checkOneAway("pale", "ple"));
		System.out.println(obj.checkOneAway("pales", "pale"));
		System.out.println(obj.checkOneAway("pale", "bale"));
		System.out.println(obj.checkOneAway("pale", "bake"));
	}

	public boolean checkOneAway(final String source1, final String source2) {
		int src1Len = source1.length();
		int src2Len = source2.length();
		int lenDiff = (src1Len > src2Len) ? src1Len - src2Len : src2Len - src1Len;
		if (lenDiff > 1) {
			return false;
		} else if (lenDiff == 0) {
			/* replace char chase */
			return checkForOneCharReplacement(source1, source2);
		} else {
			/* insert char case */
			return insertOneCharCheck(source1, source2);
		}
	}

	public boolean insertOneCharCheck(final String source1, final String source2) {
		String src1, src2;
		if (source1.length() > source2.length()) {
			src1 = source1;
			src2 = source2;
		} else {
			src2 = source1;
			src1 = source2;
		}
		int srcIndex1 = 0, srcIndex2 = 0;
		boolean differenceFound = false;
		while ((srcIndex1 < src1.length()) && (srcIndex2 < src2.length())) {
			if (src1.charAt(srcIndex1) != src2.charAt(srcIndex2)) {
				if (differenceFound) {
					return false;
				}
				differenceFound = true;
				srcIndex1++;
			} else {
				srcIndex1++;
				srcIndex2++;
			}

		}
		return true;
	}

	public boolean checkForOneCharReplacement(final String source1, final String source2) {
		boolean differenceFound = false;
		for (int index = 0; index < source1.length(); index++) {
			if (source1.charAt(index) != source2.charAt(index)) {
				if (differenceFound) {
					return false;
				}
			}
			differenceFound = true;
		}
		return true;
	}

}
