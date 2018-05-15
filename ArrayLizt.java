import java.util.*;

public class ArrayLizt<Card> extends ArrayList<Card> {
	private String name;

	public ArrayLizt(String newName) {
		name = newName;
	}

	public String getName() {
		return name;
	}

	public ArrayLizt() {
		super();
	}	
}
