package test;

import stubs.DataAccess;
import stubs.Order;

public class FailingDataAccessStub implements DataAccess {

	@Override
	public void save(Integer orderId, Order order) {
		throw new RuntimeException("connection error");
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
