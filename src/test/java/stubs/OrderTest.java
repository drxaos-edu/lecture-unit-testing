package stubs;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import test.DataAccessAgent;
import test.DataAccessMock;
import test.DataAccessStub;
import test.FailingDataAccessStub;

public class OrderTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void testOrder_failsIfNoDataAccess() {
		Order order = new Order(null, 1);
	}

	@Test
	public void testOrder() {
		DataAccess da = new DataAccessStub();
		Order order = new Order(da, 1);
	}

	@Test
	public void testSave() throws Exception {
		DataAccessAgent da = new DataAccessAgent();
		Order order = new Order(da, 1);
		order.save();
		assertTrue("db save called", da.saveCalled);
		assertEquals("id", (Integer) 1, da.orderId);
		assertEquals("order", order, da.order);
	}

	@Test(expected = OrderException.class)
	public void testSave_databaseError() throws Exception {
		DataAccess da = new FailingDataAccessStub();
		Order order = new Order(da, 1);
		order.save();
	}

	@Test
	public void testTotal() {

		// Giving

		DataAccessMock da = new DataAccessMock();
		da.setPrice("item1", 100);
		da.setPrice("item2", 55);
		da.setPrice("item qwerty", 22);
		Order order = new Order(da, 1);
		order.addItem("item1");
		order.addItem("item2");
		order.addItem("item1");
		order.addItem("item qwerty");
		order.addItem("item1");
		order.addItem("item qwerty");

		// When

		int total = order.total();

		// Then

		assertEquals(399, total);
		da.checkCall("getItemPrice(item1)");
		da.checkCall("getItemPrice(item2)");
		da.checkCall("getItemPrice(item qwerty)");
	}

}
