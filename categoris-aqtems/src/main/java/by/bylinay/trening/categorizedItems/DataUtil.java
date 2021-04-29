package by.bylinay.trening.categorizedItems;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	static String getDate() {
		return LocalDate.now().format(formatter);

	}
}
