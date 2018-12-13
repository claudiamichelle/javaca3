package javaca.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userID;

	private String address;

	private String contact;

	   private String email;
	 	public String getEmail() {
		return email;
	}
	 public void setEmail(String email) {
		this.email = email;
	}

	private String firstname;

	private String lastname;

	private String status;

	//bi-directional many-to-one association to LecturerCourse
	@OneToMany(mappedBy="user")
	private List<LecturerCourse> lecturercourses;

	//bi-directional one-to-one association to Login
	@OneToOne(mappedBy="user")
	private Login login;

	//bi-directional many-to-one association to StudentCourse
	@OneToMany(mappedBy="user")
	private List<StudentCourse> studentcourses;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="RoleID")
	private UserRole userrole;

	public User() {
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getFirstName() {
		return this.firstname;
	}

	public void setFirstName(String firstName) {
		this.firstname = firstName;
	}

	public String getLastName() {
		return this.lastname;
	}

	public void setLastName(String lastName) {
		this.lastname = lastName;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<LecturerCourse> getLecturercourses() {
		return this.lecturercourses;
	}

	public void setLecturercourses(List<LecturerCourse> lecturercourses) {
		this.lecturercourses = lecturercourses;
	}

	public LecturerCourse addLecturercours(LecturerCourse lecturercourse) {
		getLecturercourses().add(lecturercourse);
		lecturercourse.setUser(this);

		return lecturercourse;
	}

	public LecturerCourse removeLecturercours(LecturerCourse lecturercourse) {
		getLecturercourses().remove(lecturercourse);
		lecturercourse.setUser(null);

		return lecturercourse;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public List<StudentCourse> getStudentcourses() {
		return this.studentcourses;
	}

	public void setStudentcourses(List<StudentCourse> studentcourses) {
		this.studentcourses = studentcourses;
	}

	public StudentCourse addStudentcours(StudentCourse studentcourse) {
		getStudentcourses().add(studentcourse);
		studentcourse.setUser(this);

		return studentcourse;
	}

	public StudentCourse removeStudentcours(StudentCourse studentcourse) {
		getStudentcourses().remove(studentcourse);
		studentcourse.setUser(null);

		return studentcourse;
	}

	public UserRole getUserrole() {
		return this.userrole;
	}

	public void setUserrole(UserRole userrole) {
		this.userrole = userrole;
	}
	
	public boolean isActive() {
		// TODO Auto-generated method stub
		
		if (this.getStatus().equals("Active")) {
			return true;	
		}
		else return false;
	}

}
