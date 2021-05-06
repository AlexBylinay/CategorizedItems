package by.bylinay.trening.categorizedItems.dontuse;

import java.util.ArrayList;
import java.util.List;

public enum AnimalsEnum {
	CAT("cat", 1), RACCOON("raccoon",2), PANDA("panda",3), BEAR("bear",4), PARROT("parrot",5), TIGER("tiger",6), YETI("yeti",7), LESHII("leshii",8), GIRAFFE("giraffe",9);

	public String title;
	public int number;
	AnimalsEnum (String title, int number){
		this.title = title;
		this.number = number;
	}
	
	public static boolean isValid(String tipe) {
		return getValid(tipe) != null;
	}

	public static AnimalsEnum getValid(String tipe) {
		for (AnimalsEnum val : values()) {
			if (tipe == val.title) {
				return val;
			}
		}
		return null;
	}

	public String getTitle() {
		return title;
	}

	public int getNumber() {
		return number;
	}

	public static String getTypString(int number) {
		   String title = null;
				 for (AnimalsEnum val : values()) {
					if (val.getNumber()==number) {
						title= 	val.getTitle();
				}
				 }
				return title;
			}  

	
	public static List<AnimalsEnum> getAnimals() {
		List<AnimalsEnum> animals = new ArrayList<AnimalsEnum>();
		for (AnimalsEnum val : values()) {
			animals.add(val);
		}
		return animals;

	}
	
	
}
