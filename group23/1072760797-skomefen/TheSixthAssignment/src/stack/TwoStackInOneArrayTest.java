package stack;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TwoStackInOneArrayTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		TwoStackInOneArray ts = new TwoStackInOneArray();
		ts.push1(1);
		Assert.assertEquals(1, ts.peek1());
		ts.push1(2);
		Assert.assertEquals(2, ts.peek1());
		ts.push1(3);
		Assert.assertEquals(3, ts.peek1());
		Assert.assertEquals(3, ts.pop1());
		ts.push1(4);
		Assert.assertEquals(4, ts.peek1());
		ts.push1(5);
		Assert.assertEquals(5, ts.peek1());

		
		ts.push2(1);
		Assert.assertEquals(1, ts.peek2());
		ts.push2(2);
		Assert.assertEquals(2, ts.peek2());
		ts.push2(3);
		Assert.assertEquals(3, ts.peek2());
		Assert.assertEquals(3, ts.pop2());
		ts.push2(4);
		Assert.assertEquals(4, ts.peek2());
		ts.push2(5);
		Assert.assertEquals(5, ts.peek2());
		
		ts.push1(1);
		Assert.assertEquals(1, ts.peek1());
		ts.push1(2);
		Assert.assertEquals(2, ts.peek1());
		ts.push1(3);
		Assert.assertEquals(3, ts.peek1());
		Assert.assertEquals(3, ts.pop1());
		ts.push1(4);
		Assert.assertEquals(4, ts.peek1());
		ts.push1(5);
		Assert.assertEquals(5, ts.peek1());
		
		ts.push1(1);
		Assert.assertEquals(1, ts.peek1());
		ts.push1(2);
		Assert.assertEquals(2, ts.peek1());
		ts.push1(3);
		Assert.assertEquals(3, ts.peek1());
		Assert.assertEquals(3, ts.pop1());
		ts.push1(4);
		Assert.assertEquals(4, ts.peek1());
		ts.push1(5);
		Assert.assertEquals(5, ts.peek1());
		
		ts.push1(1);
		Assert.assertEquals(1, ts.peek1());
		ts.push1(2);
		Assert.assertEquals(2, ts.peek1());
		ts.push1(3);
		Assert.assertEquals(3, ts.peek1());
		Assert.assertEquals(3, ts.pop1());
		ts.push1(4);
		Assert.assertEquals(4, ts.peek1());
		ts.push1(5);
		Assert.assertEquals(5, ts.peek1());
		
		ts.push2(1);
		Assert.assertEquals(1, ts.peek2());
		ts.push2(2);
		Assert.assertEquals(2, ts.peek2());
		ts.push2(3);
		Assert.assertEquals(3, ts.peek2());
		Assert.assertEquals(3, ts.pop2());
		ts.push2(4);
		Assert.assertEquals(4, ts.peek2());
		ts.push2(5);
		Assert.assertEquals(5, ts.peek2());

	}

}
