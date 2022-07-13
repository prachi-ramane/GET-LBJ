package view;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import dao.StudentDao;
import dao.StudentDaoImplInMemory;
import model.Course;
import model.Qualification;
import model.Student;

public class Main {
	public static void main(String args[]) {
		
		StudentDao dao = new StudentDaoImplInMemory();
		Student student1 = new Student("Prachi", LocalDate.of(2001, 11, 04), Qualification.Graduate, "9594538972", "prachi@gmail.com", "Thane");
		Student student2 = new Student("John", LocalDate.of(2000, 2, 17), Qualification.Matric, "9594531234", "john@gmail.com", "Mumbai");
		Student student3 = new Student("Mike", LocalDate.of(2001, 1, 1), Qualification.Master, "9594535678", "mike@gmail.com", "Chennai");
		Student student4 = new Student("Brett", LocalDate.of(2000, 9, 04), Qualification.Intermediate, "9594530123", "brett@gmail.com", "Pune");
		
		//DAO method to addStud
		dao.addNewStudent(student1);
		dao.addNewStudent(student2);
		dao.addNewStudent(student3);
		dao.addNewStudent(student4);
		
		Course course1=new Course("Java", 6, 4000, Qualification.Graduate);
		Course course2=new Course("C++", 5, 3000, Qualification.Graduate);
		Course course3=new Course("Python", 7, 7000, Qualification.Master);
		Course course4=new Course("SQL", 3, 2000, Qualification.Graduate);
		dao.addNewCourse(course1);
		dao.addNewCourse(course2);
		dao.addNewCourse(course3);
		dao.addNewCourse(course4);
		
		System.out.println("View all courses: \n");
		List<Course> courses = dao.viewAllCourses();
		Iterator<Course> iteratorCourse=courses.iterator();
		while(iteratorCourse.hasNext()) {
			Course course=iteratorCourse.next();
			System.out.println(course.getCourseId() +"\t"+course.getCourseName()+"\t"+course.getDuration()+ "\t"+course.getFee());
		}
		
		System.out.println("********************************************************");
		System.out.println("\nView all students : ");
		System.out.println("----------------------------------------------------------");
		List<Student> students = dao.viewAllStudent();
		Iterator<Student> iterator = students.iterator();
		while(iterator.hasNext()) {
			Student student = iterator.next();
			System.out.println(student.getRollNo()+"\t"+student.getName()+"\t"+student.getEmail()+"\t"+student.getPhoneNo()+"\t"+student.getCollegeName());
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("********************************************************");
		System.out.println("Enter student rollno and course for mapping:");
		int rollNumber = sc.nextInt();
		int courseId=sc.nextInt();
		
		Student stud1 = dao.findStudentByRollNo(rollNumber);
		Course c1=dao.findCourseById(courseId);
		
		if(stud1!=null) {
			if(c1!=null) {
				dao.registration(stud1, c1);
				System.out.println("Registration Successful");
			}
			else {
				System.out.println("Course not found");
			}
		}else {
			System.out.println("Student not found");
		}
		System.out.println("********************************************************");
		System.out.println("View all registrations :");
		Map<Student,Course> registrations=dao.viewAllRegistrations();
		Set<Map.Entry<Student,Course>> regs=registrations.entrySet();
		for(Map.Entry<Student, Course> r:regs) {
			Student s=r.getKey();
			Course c=r.getValue();
			System.out.println(s.getRollNo()+" "+s.getName()+" "+c.getCourseId()+" "+c.getCourseName());
		}
		
		/*
		System.out.println("Enter rollNo");
		int rollNo = sc.nextInt();
		
		Student student = dao.findStudentByRollNo(rollNo);
		if(student!=null) {
			System.out.println(student.getRollNo()+" "+ student.getName());
		}
		else {
				System.out.println("Student not found");
		}*/
		
		/**********************************************************************/
		System.out.println("********************************************************");
		System.out.println("\nUpdate Profile :");
		System.out.println("Enter Roll Number :");
		int rollNo = sc.nextInt();
		Student student=dao.findStudentByRollNo(rollNo);
		if (student!=null){
			System.out.println("Enter phone No: ");
			String phoneNo = sc.next();
			student.setPhoneNo(phoneNo);
			dao.updateStudentProfile(student);
		}else {
			System.out.println("Student not found");
		}
		System.out.println("View all students : ");
		students = dao.viewAllStudent();
		iterator = students.iterator();
		while(iterator.hasNext()) {
			 student = iterator.next();
			System.out.println(student.getRollNo()+" "+student.getName()+" "+student.getEmail()+" "+student.getPhoneNo());
		}
		
		
	}
}
