package HZ12_examsAndRooms;

import java.util.HashSet;
import java.util.Set;
import logicalcollections.LogicalSet;

/**
 * Each instance of the class represents an exam that can take place in multiple rooms
 * 
 * @invar | getRooms() == null && getRooms().stream().allMatch(room -> room.getExams().contains(this))
 */
public class Exam {
	/**
	 * @invar | rooms != null
	 * @invar | rooms.stream().allMatch(r -> r != null)
	 * 
	 * @peerObjects
	 * @representationObject
	 */
	Set<Room> rooms = new HashSet<>();

	String courseTitle;

	/**
	 * @creates | result
	 * @peerObject
	 */
	public Set<Room> getRooms() {
		return Set.copyOf(rooms);
	}
	
	/**
	 * @post | getCourseTitle() == course 
	 */
	public Exam(String course) {
		courseTitle = course;
	}
	
	/**
	 * @pre | newRoom != null
	 * @mutates_properties | this.getRooms(), newRoom.getExams()
	 * @post | getRooms().equals(LogicalSet.plus(old(getRooms()), newRoom))
	 * @post | newRoom.getExams().equals(LogicalSet.plus(old(newRoom.getExams()), this))
	 */
	public void addRoom(Room newRoom) {
		rooms.add(newRoom);
		newRoom.addExam(this);
	}
	
	/**
	 * @pre | getRooms().contains(room)
	 * @pre | room.getExams().contains(this)
	 * @mutates_properties | this.getRooms(), room.getExams()
	 * @post | getRooms().equals(LogicalSet.minus(old(getRooms()), room))
	 * @post | room.getExams().equals(LogicalSet.minus(old(room.getExams()), this))
	 */
	public void removeRoom(Room room) {
		rooms.remove(room);
		room.exams.remove(this);
	}

	public String getCourseTitle() {
		return courseTitle;
	}
	
}
