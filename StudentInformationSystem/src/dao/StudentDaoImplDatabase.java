package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import connection.OracleConnection;
import model.Course;
import model.Qualification;
import model.Registration;
import model.Student;

public class StudentDaoImplDatabase implements StudentDao {
	
	Connection conn;
	PreparedStatement ps;
	
	public StudentDaoImplDatabase() {
		// TODO Auto-generated constructor stub
		conn=OracleConnection.getConnection();
	}
	
	@Override
	public String addNewStudent(Student student) {
		String sql="insert into tbl_students values(seq_stud.nextval,?,?,?,?,?,?)";
		int count=0;
		String message="";
		try {
			ps=conn.prepareStatement(sql);
			//ps.setInt(1, student.getRollNo());
			ps.setString(1, student.getName());
			ps.setDate(2, Date.valueOf(student.getDateOfBirth()));
			ps.setString(3, student.getQualification().name());
			ps.setString(4, student.getPhoneNo());
			ps.setString(5, student.getEmail());
			ps.setString(6, student.getAddress());
			
			
			count=ps.executeUpdate();
			
			if(count>0) {
				message="New student added";
			}
			else {
				message="Error occured";
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public void updateStudentProfile(Student student) {
		// TODO Auto-generated method stub

	}

	@Override
	public void registration(Student student, Course course) {
		

	}
	
	@Override
	public String registrationDb(Registration registration) {
		String sql = "insert into tbl_registrations values(seq_reg.nextval,?,?,?)";
		String message = "";
		try {
			ps = conn.prepareStatement(sql);

			ps.setDate(1, Date.valueOf(registration.getRegistrationDate()));
			ps.setInt(2, registration.getRollNo());
			ps.setInt(3, registration.getCourseId());

			int count = ps.executeUpdate();
			message = count > 0 ? "Registration Successful" : "Error occured.";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

	@Override
	public Map<Student, Course> viewAllRegistrations() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student findStudentByRollNo(int rollNo) {
		// TODO Auto-generated method stub
		String sql="select * from tbl_students where rollNo=?";
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, rollNo);
			
			ResultSet rs=ps.executeQuery();
		Student st=null;
		
		if(rs.next()) {
			st=new Student();
			st.setRollNo(rs.getInt(1));
			st.setName(rs.getString(2));
			st.setDateOfBirth(rs.getDate(3).toLocalDate());
			st.setQualification(Qualification.valueOf(rs.getString(4)));
			st.setPhoneNo(rs.getString(5));
			st.setEmail(rs.getString(6));
			st.setAddress(rs.getString(7));
		}return st;
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Student> viewAllStudent() {
		// TODO Auto-generated method stub
		List<Student> students=new ArrayList<Student>();
		String sql="select * from tbl_students";
		ResultSet rs= null;
		try {
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			Student st = null;
			
			while(rs.next()) {
				st=new Student();
				st.setRollNo(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setDateOfBirth(rs.getDate(3).toLocalDate());
				st.setQualification(Qualification.valueOf(rs.getString(4)));
				st.setPhoneNo(rs.getString(5));
				st.setEmail(rs.getString(6));
				st.setAddress(rs.getString(7));
				students.add(st);
			}
			return students;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String addNewCourse(Course course) {
		// TODO Auto-generated method stub
		
		String sql="insert into tbl_courses values(seq_crs.nextval,?,?,?,?)";
		String message=" ";
		try {
			ps=conn.prepareStatement(sql);
			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getDuration());
			ps.setDouble(3, course.getFee());
			ps.setString(4, course.getEligibility().name());
			
			int count=ps.executeUpdate();
			if(count>0) {
				message="Count added";
			}else {
				message="Eror";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;

	}

	@Override
	public Course findCourseById(int courseId) {
		String sql="select * from tbl_courses where courseid=?";
		ResultSet rs=null;
		try {
			ps=conn.prepareStatement(sql);
			ps.setInt(1, courseId);
			
			rs=ps.executeQuery();
			Course course=null;
			if(rs.next()) {
				course=new Course();
				course.setCourseId(rs.getInt(1));
				course.setCourseName(rs.getString(2));
				course.setDuration(rs.getInt(3));
				course.setFee(rs.getDouble(4));
				course.setEligibility(Qualification.valueOf(rs.getString(5)));
			}
			return course;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	

	@Override
	public List<Course> viewAllCourses() {
		// TODO Auto-generated method stub
		return null;
	}

}
