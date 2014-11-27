package stubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

	DataAccess dataAccess;
	int id;
	List<String> items;

	public Order(DataAccess dataAccess, int id) {
		if (dataAccess == null) {
			throw new IllegalArgumentException("dataAccess");
		}
		this.dataAccess = dataAccess;
		this.id = id;
		this.items = new ArrayList<String>();
	}

	public void addItem(String item) {
		items.add(item);
	}

	public void save() throws OrderException {
		try {
			dataAccess.save(id, this);
		} catch (RuntimeException e) {
			throw new OrderException("db access error", e);
		}
	}

	public int total() {
		int total = 0;
		Map<String, Integer> cache = new HashMap<>();
		for (String item : items) {
			if (cache.containsKey(item)) {
				total += cache.get(item);
			} else {
				int price = dataAccess.getItemPrice(item);
				total += price;
				cache.put(item, price);
			}
		}
		return total;
	}
}
