import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UbCustData {
	public static List<?> UnboundedData(List<?> firstName) {
		return Arrays.asList(firstName);
	}

	public static void main(String[] args) {
		List<String> fName = Arrays.asList("Bumrah", "Steyn", "Shami", "Siraj" ,"Tavmeet");
		
		System.out.println(UnboundedData(fName));
		
		
		List<String> fltr = 
			fName.stream()
			.filter(x -> x.startsWith("E") && x.endsWith("h"))
			.collect(Collectors.toList());
		System.out.println(fltr);
	}

}
