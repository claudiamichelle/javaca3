package javaca.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name="login")
@NamedQuery(name="Login.findAll", query="SELECT l FROM Login l")
public class Login implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String loginID;

	private String password;

	private boolean status;

	//bi-directional many-to-one association to UserRole
	@ManyToOne
	@JoinColumn(name="roleID")
	private UserRole userrole;

	//bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name="email")
	private User user; //should reference to email not userid

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getLoginID() {
		return loginID;
	}

	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
        return status;
    }
 
    public void setActive(boolean active) {
        this.status = active;
    }

	public UserRole getUserrole() {
		return this.userrole;
	}

	public void setUserrole(UserRole userrole) {
		this.userrole = userrole;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
