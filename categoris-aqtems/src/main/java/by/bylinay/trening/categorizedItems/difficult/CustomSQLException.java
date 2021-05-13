package by.bylinay.trening.categorizedItems.difficult;

import java.sql.SQLException;

public class CustomSQLException extends SQLException {
    private String message;

    public CustomSQLException(String message) {
        this.message = message;
    }

    public String getMessage() {
    	  System.out.println(message);
        return message;
          }


}