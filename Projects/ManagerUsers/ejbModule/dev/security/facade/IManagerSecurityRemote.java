package dev.security.facade;

import javax.ejb.Remote;

import dev.security.entities.User;

@Remote
public interface IManagerSecurityRemote {
	public User findUser(int id);
	public boolean checkUserLoginAndPassword(String login, String password);
	boolean createUser(String login, String name, String surname, String eMail, String cellPhone, String pass);
}
