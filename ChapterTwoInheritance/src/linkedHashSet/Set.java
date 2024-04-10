package linkedHashSet;
import java.util.Arrays;
import java.util.stream.Stream;

import java.util.Arrays;

public interface Set {
	Object[] toArray();
	
	default Stream<Object> stram() {return Arrays.stream(toArray());}
	
	int size();
	
	boolean contains(Object value);
	
	void add(Object value);
	
	void remove(Object value);
}
