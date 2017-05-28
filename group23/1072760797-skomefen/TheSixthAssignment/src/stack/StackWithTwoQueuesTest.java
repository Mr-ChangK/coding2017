package stack;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		StackWithTwoQueues q = new StackWithTwoQueues();
		q.push(5);
		q.push(4);
		q.push(8);
		q.push(3);

		Assert.assertEquals(3, q.pop());
		Assert.assertEquals(8, q.pop());

		q.push(7);
		Assert.assertEquals(7, q.pop());

		Assert.assertEquals(4, q.pop());

	}

}
