package by.bylinay.trening.categorizedItems;

import java.util.ArrayList;
import java.util.List;

public enum WordsEnum {
	MANOWAR("Manowar",1), METAL("metal",2), WARRIOR("warior",3), VICTORY("victory",4), VALHALLA("valhalla",5), ODIN("odin",6), SWORD("sword",7);
	public String title;
	public int number;
	WordsEnum (String title, int number){
		this.title = title;
		this.number = number;
	}
	
	public static boolean isValid(String tipe) {
		return getValid(tipe) != null;
	}

	public static WordsEnum getValid(String tipe) {
		for (WordsEnum val : values()) {
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
				 for (WordsEnum val : values()) {
					if (val.getNumber()==number) {
						title= 	val.getTitle();
				}
				 }
				return title;
			}  

	
	public static List<WordsEnum> getAnimals() {
		List<WordsEnum> words = new ArrayList<WordsEnum>();
		for (WordsEnum val : values()) {
			words.add(val);
		}
		return words;

	}

}
