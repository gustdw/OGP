package linkedHashSet;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ArrayList implements List {
	private Object[] elements = new Object[10];
	private int size;
	
	public ArrayList() {}
	
	@Override
	public Object[] toArray() {
		return Arrays.copyOf(elements, size);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object get(int index) {
		return elements[index];
	}

	private int indexOf(Object value) {
		for (int i=0; i < size; i++)
			if (elements[i].equals(value))
				return i;
		return -1;
	}
	
	@Override
	public boolean contains(Object value) {
		return indexOf(value) != -1;
	}

	@Override
	public void add(int index, Object value) {
		if (elements.length == size) {
			Object[] newElements = new Object[elements.length * 2];
			System.arraycopy(elements, 0, newElements, 0, size);
			elements = newElements;
		}
		System.arraycopy(elements, index, elements, index + 1, size - index);
		elements[index] = value;
		size++;
	}

	@Override
	public void remove(int index) {
		size--;
		System.arraycopy(elements, index + 1, elements, index, size - index);
		elements[size] = null;
	}

	@Override
	public void remove(Object value) {
		int index = indexOf(value);
		if (index != -1)
			remove(index);
	}

}
