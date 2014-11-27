package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import stubs.DataAccess;
import stubs.Order;

public class DataAccessMock implements DataAccess {

	List<String> calls = new ArrayList<>();
	Map<String, Integer> prices = new HashMap<>();

	public void setPrice(String item, int price) {
		prices.put(item, price);
	}

	public void checkCall(String call) {
		Assert.assertEquals(call, calls.remove(0));
	}

	@Override
	public void save(Integer orderId, Order order) {
		calls.add("save(" + orderId + ", " + System.identityHashCode(order)
				+ ")");
	}

	@Override
	public Order load(Integer orderId) {
		calls.add("load(" + orderId + ")");
		return null;
	}

	@Override
	public int getItemPrice(String item) {
		calls.add("getItemPrice(" + item + ")");
		return prices.get(item);
	}
}
