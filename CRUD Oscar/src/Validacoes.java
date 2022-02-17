import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacoes {
	
	public static boolean validarPreco(String preco) {
        boolean precoValido = false;
        if (preco != null && preco.length() > 0) {
            String expression = "[0-9-.]+";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(preco);
            if (matcher.matches()) {
            	precoValido = true;
            }
        }
        return precoValido;
    }
	
	public static boolean validarData(String strDate) {
	    String dateFormat = "dd/MM/uuuu";

	    DateTimeFormatter dateTimeFormatter = DateTimeFormatter
	    .ofPattern(dateFormat)
	    .withResolverStyle(ResolverStyle.STRICT);
	    try {
	        LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
	        return true;
	    } catch (DateTimeParseException e) {
	       return false;
	    } 
	}
	
}
