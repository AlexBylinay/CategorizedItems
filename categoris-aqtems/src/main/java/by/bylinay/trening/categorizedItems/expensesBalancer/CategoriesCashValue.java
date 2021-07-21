package by.bylinay.trening.categorizedItems.expensesBalancer;



import by.bylinay.trening.categorizedItems.Category;



public class CategoriesCashValue {
	public Category category;
	public int money;
	
	public  CategoriesCashValue (Category category, int money) {
		this.category = category;
		this.money = money;
	}

	public Category getCategory() {
		return category;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
