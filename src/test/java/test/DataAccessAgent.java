package test;

import stubs.DataAccess;
import stubs.Order;

public class DataAccessAgent implements DataAccess {

	public boolean saveCalled = false;
	public Integer orderId;
	public Order order;

	@Override
	public void save(Integer orderId, Order order) {
		saveCalled = true;
		this.orderId = orderId;
		this.order = order;
	}

	@Override
	public Order load(Integer orderId) {
		return null;
	}

	@Override
	public int getItemPrice(String item) {
		return 0;
	}
}
