package by.bylinay.trening.categorizedItems.expensesBalancer;

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
    Map<Integer, TransactionValue> totalValues = new HashMap<>();

	public Map<Category, Double> getPrioritiesOld(Map<Category, Integer> targets, List<Item> items) {
		Map<Category, Double> indicators = new HashMap<>();
		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			Category category = getCategory(putToArray(targets), item);
			int purpose = targets.get(category);
			double spent = item.getTransactionValue();
			double result = doСounting(spent, purpose);
			indicators.put(category, result);
		}
		return indicators;
	}
	
	
	public Map<Category, Priority> getPriorities(Map<Category, Double> targets, List<Item> items) {
	
	    
	    for (Item item : items) {
	        int categoryId = item.getCategoryId();
	        TransactionValue itemValue = new TransactionValue(item.getTransactionValue());
	        TransactionValue totalValue = totalValues.get(categoryId);
	        if (totalValue == null) {
	            totalValue = new TransactionValue(itemValue);
	            totalValues.put(categoryId, totalValue);
	        } else {
	            totalValue.add(itemValue);
	            totalValues.put(categoryId, totalValue);
	            totalValues.put(categoryId, null);
	        }
	    }
	    Map<Category, Priority> priorities = new HashMap<>();
	    for  (Entry<Category, Double> target : targets.entrySet())
	    {
	        Category category = target.getKey();
	        TransactionValue totalValue = totalValues.get(category.getId());
	        if (totalValue == null) {
	        	 Double percentage = 0.0;
	        	 long amount = 0;
	        	 Priority priority = new Priority(percentage, amount);
		         priorities.put(category, priority);
		      
		         System.out.println("/" + priority.getPersent());
		            System.out.println("/" +category.getName());
		            System.out.println("/" +priority.getAmountMany());
		        
  // TODO что тогда?
	        } else {
	        	
	            Double percentage = (totalValue.divide(target.getValue()));
	            Priority priority = new Priority(percentage, totalValue.getAmount());
	            priorities.put(category, priority);
	            
	            System.out.println(priority.getPersent());
	            System.out.println(category.getName());
	            System.out.println(priority.getAmountMany());
	        }
	    }
	   
	    return priorities;
	}
	

	private Category getCategory(Category[] categories, Item item) {
		Category category = categories[0];
		for (int i = 0; categories[i].getId() != item.getCategoryId(); i++) {
			category = categories[toNum(i)];
		}
		return category;

	}

	private int toNum(int index) {
		return index + 1;
	}

	private Category[] putToArray(Map<Category, Integer> rules) {
		int caunt = 0;
		Category[] categories = new Category[rules.size()];
		for (Category key : rules.keySet()) {
			caunt++;
			categories[caunt - 1] = key;
		}
		return categories;
	}

	private double doСounting(double spent, int amount) {
		double value = (double) spent / amount;
		double scale = Math.pow(10, 3);
		return Math.ceil(value * scale) / scale;
	}

	private void toWatch(Map<Category, Double> priorities) {
		for (Map.Entry<Category, Double> pair : priorities.entrySet()) {
			String name = pair.getKey().getName();
			double value = pair.getValue();
			System.out.println(name + ":" + value);

		}
	}

	public static void main(String[] args) {

		ItemBalancer balancer = new ItemBalancer();
		Category categoryFood = new CategoryImpl(1, "food", 1);
		Category categoryClothes = new CategoryImpl(2, "clothes", 1);
		Category categoryDrink = new CategoryImpl(3, "drink", 1);
		Category categoryFun = new CategoryImpl(4, "fun", 1);
		Category categoryP = new CategoryImpl(5, "P", 1);

		Item item = new SimpleItem(1, "yu", 3, 15.0);
		Item item1 = new SimpleItem(2, "yu2", 1, 16.9);
		Item item2 = new SimpleItem(3, "yu3", 4, 4.4);
	
		
		Item item4 = new SimpleItem(4, "my", 3, 1.3);
		Item item5 = new SimpleItem(5, "my2", 1, 1.6);
		Item item6 = new SimpleItem(6, "my3", 4, 4.0);
		Item item7 = new SimpleItem(7, "my4", 2, 3.6);
		Item item8 = new SimpleItem(8, "my5", 5, 1);
		Item item9 = new SimpleItem(0, "u", 0, 0);
		
		

		Map<Category, Integer> rules = new HashMap<>();
		Map<Category, Double> rulesNew = new HashMap<>();
		List<Item> items = new ArrayList<>();
		List<Item> items2 = new ArrayList<>();
		rules.put(categoryFood, 13);
		rules.put(categoryClothes, 18);
		rules.put(categoryDrink, 9);
		rules.put(categoryFun, 53);
		rules.put(categoryP, 11);
		
		
		rulesNew.put(categoryFood, 0.0);
		rulesNew.put(categoryClothes, 18.09);
		rulesNew.put(categoryDrink, 9.98);
		rulesNew.put(categoryFun, 53.44);
		rulesNew.put(categoryP, 10.0);

		items.add(item);
		items.add(item1);
		items.add(item2);
		
		items2.add(item4);
		items2.add(item5);
		items2.add(item6);
		items2.add(item7);
		items2.add(item8);
		items2.add(item9);

		Category[] grades = { categoryFood, categoryClothes, categoryDrink, categoryFun, categoryP };

		//balancer.getPrioritiesOld(rules, items);
		//balancer.toWatch(balancer.getPrioritiesOld(rules, items));
		TransactionValue trast = new TransactionValue();
		Priority priority = new Priority(22.56, 1236);
		trast.setAmount(200);
		trast.addDouble(1.12);
		//System.out.println(trast.getAmount());
		//System.out.println(priority.getAmount());
		//System.out.println(priority.getAmount());
		balancer.getPriorities(rulesNew, items);
		balancer.getPriorities(rulesNew, items2);

	}

}
