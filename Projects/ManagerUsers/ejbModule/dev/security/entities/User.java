package dev.security.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name= User.verifyUserByLoginAndPassword,
				query = "SELECT u FROM User u WHERE u.login = :p_login AND u.password = :p_password"),
	@NamedQuery(name = User.findUserByEMail,
				query = "SELECT u FROM User u Where u.eMail = :p_eMail"),
	@NamedQuery(name = User.findUserByLogin,
				query = "SELECT u FROM User u Where u.login = :p_login")
})
@Table(name = "users", schema="public")
@SequenceGenerator(name = "USERS_ID_SEQ", sequenceName="users_id_seq", initialValue=1, allocationSize=20)
@Entity
public class User implements Serializable{
	private static final long serialVersionUID = -5055907804462622655L;
	
	public static final String verifyUserByLoginAndPassword = "User.findUserByLoginAndPassword";
	public static final String findUserByLogin = "User.findUserByLogin";
	public static final String findUserByEMail = "User.findUserByEMail";
	
	private int id;
	private String login;
	private String password;
	private String name;
	private String surname;
	private String eMail;
	private String cellPhone;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USERS_ID_SEQ")
	@Column(name="ID")
	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	
	@Column(name = "LOGIN")
	public String getLogin() { return login; }
	public void setLogin(String login) { this.login = login; }
	
	@Column(name = "PASSWORD")
	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
	
	@Column(name = "NAME")
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	
	@Column(name = "SURNAME")
	public String getSurname() { return surname; }
	public void setSurname(String surname) { this.surname = surname;}
	
	@Column(name = "E_MAIL")
	public String geteMail() { return eMail;}
	public void seteMail(String eMail) { this.eMail = eMail; }
	
	@Column(name = "CELL")
	public String getCellPhone() { return cellPhone; }
	public void setCellPhone(String cellPhone) { this.cellPhone = cellPhone; }
	
}
