package by.bylinay.trening.categorizedItems.ExpensesBalancer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.bylinay.trening.categorizedItems.Category;
import by.bylinay.trening.categorizedItems.CategoryImpl;

public class ItemBalancer {
	/**
	 * This map will be archive all circulation money on categories it will be show
	 * how much money we spend and for which category and time, when it will do
	 */
	private Map<LocalDateTime, CategoriesCashValue> archiveCirculation;
	/**
	 * This list will contain all categories and restrictions on money to them
	 */
	public List<CategoriesCashValue> categoriesRules = new ArrayList<>();;

	/**
	 * the method, for setting the constraint, for each category
	 * 
	 * @param categore
	 * @param rule     - constraint
	 */
	private void setRuleForCategory(Category categore, int rule) {
		CategoriesCashValue value = new CategoriesCashValue(categore, rule);
		categoriesRules.add(value);
	}

	/**
	 * the method changes the color of the categories that have an increase in the
	 * limit
	 * 
	 * @param allCash - limit of money
	 */
	public void doControl(int allCash) {
		for (int i = 0; i < categoriesRules.size(); i++) {
			CategoriesCashValue rules = categoriesRules.get(i);
			if (rules.getMoney() >= allCash) {
				rules.getCategory().setColor(2);

			}

		}
	}

	public static void main(String[] args) {
		ItemBalancer balancer = new ItemBalancer();

		Category categoryFood = new CategoryImpl(1, "food", 1);
		Category categoryClothes = new CategoryImpl(2, "clothes", 3);
		Category categoryDrink = new CategoryImpl(2, "drink", 4);
		Category categoryFun = new CategoryImpl(2, "fun", 5);

		balancer.setRuleForCategory(categoryFood, 30);
		balancer.setRuleForCategory(categoryClothes, 3);
		balancer.setRuleForCategory(categoryDrink, 0);
		balancer.setRuleForCategory(categoryFun, 30);

		balancer.categoriesRules.forEach(b -> {
			System.out.print(b.getCategory().getColor());

		});

		System.out.print("////////////");

		balancer.doControl(10);

		balancer.categoriesRules.forEach(b -> {
			System.out.print((b.getCategory().getColor()));

		});
		System.out.print("////////////");
		balancer.categoriesRules.forEach(b -> {
			System.out.print((b.getCategory().getName()));

		});

	}

}
