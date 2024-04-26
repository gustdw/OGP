package HZ12_examsAndRooms;

import java.util.HashSet;
import java.util.Set;
import logicalcollections.LogicalSet;

/**
 * Each instance of the class represents a room in which multiple exams can take place
 * 
 * @invar | getExams() == null && getExams().stream().allMatch(exam -> exam.getRooms().contains(this))
 */
public class Room {
	/**
	 * @invar | exams != null
	 * @invar | exams.stream().allMatch(e -> e != null && e.rooms.contains(this))
	 * 
	 * @peerObjects
	 * @represenationObject
	 */
	Set<Exam> exams = new HashSet<>();
	
	String roomNr;
	/**
	 * @creates | result
	 * @peerObject
	 */
	public Set<Exam> getExams() {
		return Set.copyOf(exams);
	}
	
	/**
	 * @post | getRoomNr() == room 
	 * @post | getExams().isEmpty()
	 */
	public Room(String room) {
		roomNr = room;
	}
	
	/**
	 * @pre | newExam != null
	 * @mutates_properties | this.getExams(), newExam.getRooms()
	 * @post | getExams().equals(LogicalSet.plus(old(getExams()), newExam))
	 * @post | newExam.getRooms().equals(LogicalSet.plus(old(newExam.getRooms()), this))
	 */
	public void addExam(Exam newExam) {
		exams.add(newExam);
		newExam.addRoom(this);
	}
	
	/**
	 * @pre | getExams().contains(exam)
	 * @pre | exam.getRooms().contains(this)
	 * @mutates_properties | this.getExams(), exam.getRooms()
	 * @post | getExams().equals(LogicalSet.minus(old(getExams()), exam))
	 * @post | exam.getRooms().equals(LogicalSet.minus(old(exam.getRooms()), this))
	 */
	public void removeExam(Exam exam) {
		exams.remove(exam);
		exam.rooms.remove(this);
	}
	
	public String getRoomNr() {
		return roomNr;
	}
}
