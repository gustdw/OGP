package HZ11_person;

import java.util.HashSet;
import java.util.Set;


/**
 * Each instance of this class represents a person in an ... is father of ... graph.
 * 
 * @invar | getFather() == null || getFather().getChildren().contains(this)
 * @invar | getChildren().stream().allMatch(c -> c.getFather() == this)
 */
public class Person {
	
	/**
	 * @peerObject
	 * 
	 * @invar | children != null
	 * @invar | children.stream().allMatch(c -> c != null)
	 * @invar | children.stream().allMatch(c -> c.father == this)
	 * @invar | father == null || father.children.contains(this)
	 */
	private Person father;
	
	/**
	 * @peerObjects
	 */
	private HashSet<Person> children;
	
	/**
	 * @peerObject
	 */
	public Person getFather() {
		return father;
	}
	
	/**
	 * @post | getFather() == null
	 * @post | getChildren().size() == 0
	 */
	public Person() {
		children = new HashSet<>();
	}
	
	/**
	 * @pre | newFather != null
	 * @mutates_properties | this.getFather(), newFather.getChildren()
	 * @post | this.getFather() == newFather
	 * @post | newFather.getChildren().contains(this)
	 */
	public void setFather(Person newFather) {
		father = newFather;
		newFather.children.add(this);
	}
	
	/**
	 * @pre | getFather().getChildren().contains(this)
	 * @pre | getFather() != null
	 * @mutates_properties | this.getFather(), getFather().getChildren()
	 * @post | getFather() == null
	 */
	// @post | getFather().getChildren().contains(this) == false
	public void removeFather() {
		father.children.remove(this);
		father = null;
	}
	
	public Set<Person> getChildren() {
		return Set.copyOf(children);
	}
}	
