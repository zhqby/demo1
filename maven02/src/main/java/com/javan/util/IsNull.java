package com.javan.util;



public class IsNull {
	public static  boolean isNull(String[] colums) {
		for(int i=0;i<colums.length;i++) {
			if(colums[i].equals("")||colums[i]==null)
				return false;
		}
		
		
		return true;
	}
}
