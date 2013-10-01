package dev.security.facade;

import javax.ejb.Remote;

import pl.soda.generalPurposeObjects.OperationResult;
import dev.security.entities.User;

@Remote
public interface IManagerSecurityRemote {
	public User findUser(int id);
	public User findUserByLogin(String login);
	public User findUserByEMail(String eMail);
	public boolean checkUserLoginAndPassword(String login, String password);
	public OperationResult<Boolean> createUser(User newUser);
}
