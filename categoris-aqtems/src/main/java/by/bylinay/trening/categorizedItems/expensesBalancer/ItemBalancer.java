package by.bylinay.trening.categorizedItems.expensesBalancer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import by.bylinay.trening.categorizedItems.Category;
import by.bylinay.trening.categorizedItems.CategoryImpl;
import by.bylinay.trening.categorizedItems.Item;
import by.bylinay.trening.categorizedItems.SimpleItem;

public class ItemBalancer {

	public Map<Category, Priority> getPriorities(Map<Category, Integer> targets, List<Item> items) {
		int i = 0;
		Map<Category, Priority> priorities = new HashMap<>();
		for (Entry<Category, Integer> target : targets.entrySet()) {
			i++;
			Category category = target.getKey();
			TransactionValue totalValue = getTotalValues(items).get(category.getId());
			Priority priority = null;
			Double percentage = null;
			if (totalValue == null) {
				percentage = 0.0;
				int amount = 0;
				priority = new Priority(percentage, amount);
				// System.out.println("/" + category.getName());
				// System.out.println("/" + priority.getPersent());
				// System.out.println("/" + priority.getAmountMany());
				// System.out.println("/" + priority.getDifferent(target.getValue()));

			} else {
				percentage = getRatio(totalValue.getAmount(), target.getValue());
				priority = new Priority(percentage, totalValue.getAmount());
				System.out.println(i);
				System.out.println(category.getName());
				System.out.println(priority.getPersent());
				System.out.println(priority.getAmountMany());
				System.out.println(priority.getDifferent(target.getValue()));
				System.out.println("||||||");
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

	
	
	public static void main(String[] args) {

		ItemBalancer balancer = new ItemBalancer();
		Category categoryFood = new CategoryImpl(1, "food", 1);
		Category categoryClothes = new CategoryImpl(2, "clothes", 1);
		Category categoryDrink = new CategoryImpl(3, "drink", 1);
		Category categoryFun = new CategoryImpl(4, "fun", 2);
		Category categoryP = new CategoryImpl(5, "P", 1);

		Item item = new SimpleItem(1, "yu", 3, new BigDecimal(8));
		Item item1 = new SimpleItem(2, "yu2", 1, new BigDecimal(5));
		Item item2 = new SimpleItem(3, "yu3", 4, new BigDecimal(9));

		Item item4 = new SimpleItem(4, "my", 3, new BigDecimal(7.1));
		Item item5 = new SimpleItem(5, "my2", 1, new BigDecimal(2));
		Item item6 = new SimpleItem(6, "my3", 4, new BigDecimal(4));
		Item item7 = new SimpleItem(7, "my4", 2, new BigDecimal(6));
		Item item8 = new SimpleItem(8, "my5", 5, new BigDecimal(1));
		Item item9 = new SimpleItem(0, "u", 4, new BigDecimal(1));

		Map<Category, Integer> rules = new HashMap<>();
		Map<Category, Integer> rulesNew = new HashMap<>();
		List<Item> items = new ArrayList<>();
		List<Item> items2 = new ArrayList<>();
		rules.put(categoryFood, 9);
		rules.put(categoryClothes, 18);
		rules.put(categoryDrink, 14);
		rules.put(categoryFun, 6);
		rules.put(categoryP, 11);

		rulesNew.put(categoryFood, 0);
		rulesNew.put(categoryClothes, 18);
		rulesNew.put(categoryDrink, 14);
		rulesNew.put(categoryFun, 3);
		rulesNew.put(categoryP, 10);
		rulesNew.put(categoryDrink, 14);

		items.add(item);
		items.add(item1);
		items.add(item2);

		items2.add(item4);
		items2.add(item5);
		items2.add(item6);
		items2.add(item7);
		items2.add(item8);
		items2.add(item1);
		items2.add(item1);
		items2.add(item9);
		balancer.getPriorities(rulesNew, items2);
		
		 balancer.getPriorities(rules, items);

	}

}
