package collections;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class GenericCollections {
	
	public static List<String> lettersAndNumbers(List<Character> list) {
		if (list == null) {
			return null;
		}
		if (list.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<String> formattedStrings = new ArrayList<>();
		
		for (int i = list.size() - 1; i >= 0; i-- ) {
			Character ch = list.get(i);
			
			if (Character.isLetter(ch)) {
				if (Character.isLowerCase(ch)) {
					formattedStrings.add("L(" + ch + ")");
				} else {
					formattedStrings.add("U(" + Character.toLowerCase(ch) + ")");
				}
			} else if (Character.isDigit(ch)) {
				formattedStrings.add("N(" + ch + ")");
			}
		}
		
		return formattedStrings;
	}
	
	public static int modify(List<Integer> list) {
		if (list == null) {
			throw new NullPointerException("List cannot be null");
		}
		
		list.removeIf(x -> x % 3 == 0);
		
		for (int i = 1; i <= 5; i++) {
		    list.add(4 * i);
		    list.add(5 * i);
		    list.add(6 * i);
		}
		
		Collections.sort(list);
		
		return 0;
	}
	
	public static void removeEntryNthElement(List<?> list, int n) {
		
	}
}
