package test;

import stubs.DataAccess;
import stubs.Order;

public class DataAccessStub implements DataAccess {

	@Override
	public void save(Integer orderId, Order order) {
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
