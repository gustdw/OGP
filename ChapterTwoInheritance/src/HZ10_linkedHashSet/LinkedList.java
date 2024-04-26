package HZ10_linkedHashSet;

public class LinkedList implements List {
	private class Node {
		private Node previous;
		private Node next;
		private Object element;
				
		private int getLength() {return this == sentinel? 0 : 1 + next.getLength();}
	}
	private int size;
	private Node sentinel;
	
	private Node getNode(int index) {
		Node node = sentinel;
		if (index <= size/2)
			for(; index>= 0; index--)
				node = node.next;
		else
			for (; index < size; index++)
				node = node.previous;
		return node;
	}
	
	public LinkedList() {
		sentinel = new Node();
		sentinel.previous = sentinel;
		sentinel.next = sentinel;
	}
	
	@Override
	public Object[] toArray() {
		Object[] result = new Object[size()];
		int i = 0;
		for (Node node = sentinel.next; node!= sentinel; node = node.next)
			result[i++] = node.element;
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object get(int index) {
		return getNode(index).element;
	}

	@Override
	public boolean contains(Object value) {
		for (Node node = sentinel.next; node != sentinel; node = node.next)
			if (node.element.equals(value))
				return true;
		return false;
	}

	@Override
	public void add(int index, Object value) {
		Node next = getNode(index);
		Node node = new Node();
		node.element = value;
		node.next = next;
		node.previous = next.previous;
		node.next.previous = node;
		node.previous.next = node;
		size++;
	}
	
	public void add(Object value) {
		add(size, value);
	}

	@Override
	public void remove(int index) {
		Node node = getNode(index);
		node.next.previous = node.previous;
		node.previous.next = node.next;
		size--;
	}

	@Override
	public void remove(Object value) {
		Node node = sentinel.next;
		for(;;) {
			if (node == sentinel)
				return;
			if (node.element.equals(value)) {
				node.next.previous = node.previous;
				node.previous.next = node.next;
				size--;
				return;
			}
			node = node.next;
		}

	}

}
