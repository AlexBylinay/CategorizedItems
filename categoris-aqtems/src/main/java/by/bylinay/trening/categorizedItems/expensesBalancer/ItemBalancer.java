package by.bylinay.trening.categorizedItems.expensesBalancer;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import by.bylinay.trening.categorizedItems.Category;
import by.bylinay.trening.categorizedItems.Item;


public class ItemBalancer {

	public Map<Category, Priority> getPriorities(Map<Category, Integer> targets, List<Item> items) {
		
		Map<Category, Priority> priorities = new HashMap<>();
		for (Entry<Category, Integer> target : targets.entrySet()) {
			Category category = target.getKey();
			TransactionValue totalValue = getTotalValues(items).get(category.getId());
			Priority priority = null;
			Double percentage = null;
			if (totalValue == null) {
				percentage = 0.0;
				int amount = 0;
				priority = new Priority(percentage, amount);

			} else {
				percentage = getRatio(totalValue.getAmount(), target.getValue());
				priority = new Priority(percentage, totalValue.getAmount());

			}
			priorities.put(category, priority);
		}
		return priorities;
	}

	private Map<Integer, TransactionValue> getTotalValues(List<Item> items) {
		Map<Integer, TransactionValue> totalValues = new HashMap<>();
		for (Item item : items) {
			int categoryId = item.getCategoryId();
			TransactionValue itemValue = new TransactionValue(item.getManeyInСents());
			TransactionValue totalValue = totalValues.get(categoryId);
			if (totalValue == null) {
				totalValue = new TransactionValue(itemValue);
			} else {
				totalValue.add(itemValue);
			}
			totalValues.put(categoryId, totalValue);
		}
		return totalValues;

	}

	private Double getRatio(int value, int target) {
		double result = 0;
		if (target == 0.0) {
			result = value;
		} else {
			result = (double) value / target;
		}
		return result;
	}

}
