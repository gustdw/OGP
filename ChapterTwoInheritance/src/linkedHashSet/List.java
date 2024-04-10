package linkedHashSet;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public interface List {
	Object[] toArray();
	
	default Stream<Object> stram() {return Arrays.stream(toArray());}
	
	int size();
	
	Object get(int index);
	
	boolean contains(Object value);
	
	void add(int index, Object value);
	
	default void add(Object value) {add(size(), value);}
	
	void remove(int index);
	
	void remove(Object value);
}
