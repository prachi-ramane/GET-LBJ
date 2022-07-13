package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import model.Course;
import model.Registration;
import model.Student;

public class StudentDaoImplInMemory implements StudentDao {

	public static List<Student> students = new ArrayList<Student>();
	public static Set<Course> courses= new HashSet<Course>();
	public static Map<Student,Course> registrations = new HashMap<>();
	
	@Override
	public String addNewStudent(Student student) {
		students.add(student);
		return "";
	}

	@Override
	public void updateStudentProfile(Student student) {
		Student student1 = findStudentByRollNo(student.getRollNo());
		int index;
		index=students.indexOf(student1);
		students.set(index, student);
	}

	@Override
	public void registration(Student student, Course course) {
		registrations.put(student,course);
	}
	
	@Override
	public Student findStudentByRollNo(int rollNo) {
		//boolean isFound= students.stream().anyMatch(st->st.getRollNo()==rollNo);
		return students.stream().filter(st->st.getRollNo()==rollNo)
		.findFirst()
		.orElse(null)	;
	}

	@Override
	public List<Student> viewAllStudent() {
		return students.stream().collect(Collectors.toList());
	}

	@Override
	public String addNewCourse(Course course) {
		courses.add(course);
		return " ";
	}

	@Override
	public List<Course> viewAllCourses() {
		return courses.stream().collect(Collectors.toList());
	}

	@Override
	public Map<Student, Course> viewAllRegistrations() {
		return registrations;
	}

	@Override
	public Course findCourseById(int courseId) {
		return courses.stream()
				.filter(c->c.getCourseId()==courseId)
				.findAny().orElse(null);
	}

	@Override
	public String registrationDb(Registration registration) {
		// TODO Auto-generated method stub
		return null;
	}

}
