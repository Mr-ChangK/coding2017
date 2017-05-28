package stack;

import org.junit.Assert;
import org.junit.Test;

import util.ArrayUtil;

public class ArrayUtilTest {
	
	@Test
	public void test(){
		ArrayUtil[] a = new ArrayUtil[3];
		a[0] = new ArrayUtil();
		a[1] = new ArrayUtil();
		a[2] = new ArrayUtil();

		a = ArrayUtil.changeLength(a,ArrayUtil.class,10);
		Assert.assertEquals(13, a.length);
		
		Object array[] = new Object[3];
		array[0]="aaa";
		array[1]="bbb";
		array[2]="ccc";

		array = ArrayUtil.dilatationInCentre(array, 1, 2);
		for(int i=0;i<array.length;i++)
			System.out.println(array[i]);
		Assert.assertEquals("aaa", array[0]);
		Assert.assertEquals("bbb", array[1]);
		Assert.assertNull(array[2]);
		Assert.assertNull(array[3]);
		Assert.assertEquals("ccc", array[4]);

	}
}
