package dev.security.facade;

import javax.ejb.Local;

import dev.security.entities.User;

@Local
public interface IManagerSecurityLocal {
	public User findUser(int id);
	public boolean checkUserLoginAndPassword(String login, String password);
	boolean createUser(String login, String name, String surname, String eMail, String cellPhone, String pass);
}
