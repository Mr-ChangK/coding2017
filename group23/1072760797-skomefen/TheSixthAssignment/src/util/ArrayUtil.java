package util;

import java.lang.reflect.Array;

public class ArrayUtil {
	
	public static <T>T[] changeLength(T[] array,Class<T>type,int growth) {

		if(array==null){
			return null;
		}
		if(growth==0){
			return array;
		}
		if(growth<0){
		//	T[] newArray = (T[]) new Object[array.length+growth];
			@SuppressWarnings("unchecked")
			T[] newArray = (T[])Array.newInstance(type, array.length+growth);
			System.arraycopy(array, 0, newArray, 0, newArray.length);
			return newArray;
		}
		
		T[] newArray = (T[])Array.newInstance(type, array.length+growth);
		System.arraycopy(array, 0, newArray, 0, array.length);
		
		return newArray;
	}
	public static int[] changeLength(int[] array,int growth) {

		if(array==null){
			return null;
		}
		if(growth==0){
			return array;
		}
		if(growth<0){
			int[] newArray =  new int[array.length+growth];
			System.arraycopy(array, 0, newArray, 0, newArray.length);
			return newArray;
		}
		
		int[] newArray =  new int[array.length+growth];
		System.arraycopy(array, 0, newArray, 0, array.length);
		
		return newArray;
	}
	public static Object[] dilatationInCentre(Object[] array, int start, int growth) {
		if(array==null){
			return null;
		}
		if(growth<=0){
			return array;
		}
		
		Object[] newArray = new Object[growth+array.length];
		System.arraycopy(array, 0, newArray, 0, start+1);
		System.arraycopy(array, start+1, newArray, start+growth+1, array.length-(start+1));
		return newArray;
	}
}
