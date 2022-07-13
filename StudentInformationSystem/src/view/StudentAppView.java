package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import controller.StudentController;
import model.Course;
import model.Qualification;
import model.Registration;
import model.Student;

public class StudentAppView {
	public static void main(String args[]) {
		StudentController controller = new StudentController();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1.Student 2.Admin 3.Exit");
		int userType = sc.nextInt();
		
		if(userType==1) {
			String choice="y";
			do {
				System.out.println("1. Sign up \n2. Update Phone No \n3. View All courses \n4. Register for a course \n5. Sign out");
				int option = sc.nextInt();
				
				switch(option) {
				
				case 1:
					System.out.println("Enter name, date(dd/mm/yyyy), phone number, email, address :");
					String name = sc.next();
					String dateOfBirth=sc.next();
					DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate dob=LocalDate.parse(dateOfBirth,formatter);
					
					String phoneNumber=sc.next();
					String email=sc.next();
					String address=sc.next();
					System.out.println("1.Master 2.Graduate 3.Intermediate 4.Matric");
					int q=sc.nextInt();
					Qualification qualification=null;
					if (q==1) qualification=Qualification.Master;
					if (q==2) qualification=Qualification.Graduate;
					if (q==3) qualification=Qualification.Intermediate;
					if (q==4) qualification=Qualification.Matric;
					
					Student student=new Student(name,dob,qualification,phoneNumber,email,address);
					String message=controller.addNewStudent(student);
					System.out.println(message);
					break;
					
				/*case 5:
					List<Student> students=controller.viewAllStudent();
					Iterator<Student> iterator=students.iterator();
					while(iterator.hasNext()) {
						Student st=iterator.next();
						System.out.println(st.getRollNo()+" "+st.getName()+" "+" "+st.getQualification()) ;
					}
					break;*/
					
				case 4:
					System.out.println("Enter your rollno and course you want to register for");
					int rollNo=sc.nextInt();
					int courseId=sc.nextInt();
					String regDateString=sc.next();
					DateTimeFormatter formatter2=DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDate regDate=LocalDate.parse(regDateString, formatter2);
					
					Registration registration=new Registration(regDate, courseId, rollNo);
					
					message= controller.registrationDb(registration);
					System.out.println(message);
					break;
				}
				
				System.out.println("continue?(y/n");
				choice=sc.next();
			}while(choice=="y"||choice=="Y");
		}
		
		else if(userType==2) {
			System.out.println("1. View All 2.Find student 3.Add new course");
			int option = sc.nextInt();
			switch(option) {
			case 1:
				List<Student> students=controller.viewAllStudent();
				Iterator<Student> iterator=students.iterator();
				while(iterator.hasNext()) {
					Student st=iterator.next();
					System.out.println(st.getRollNo()+" "+st.getName()+" "+" "+st.getQualification()) ;
				}
				break;
				
				
			case 2:
				System.out.println("Enter roll No");
				int rollNo = sc.nextInt();
				Student st=controller.findStudentByRollNo(rollNo);
				if(st!=null) {
					System.out.println(st.getRollNo()+" "+st.getName()+" "+" "+st.getQualification()) ;
				}else {
					System.out.println("Student not found") ;
				}break;
				
				
			case 3:
				System.out.println("Enter course name, duration, fee:");
				String courseName=sc.next();
				int duration=sc.nextInt();
				double fee=sc.nextDouble();
				
				System.out.println("1. Master 2.Graduate 3.Intermediate 4.Matric");
				int q=sc.nextInt();
				Qualification qualification=null;
				if (q==1) qualification=Qualification.Master;
				if (q==2) qualification=Qualification.Graduate;
				if (q==3) qualification=Qualification.Intermediate;
				if (q==4) qualification=Qualification.Matric;
				
				Course course=new Course(courseName, duration, fee, qualification);
				String message = controller.addNewCourse(course);
				System.out.println(message);
				break;
			}
		}else {
			System.out.println("Invalid choice");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
