package pl.calculations.system.facade;

import java.util.List;

import javax.ejb.Local;

import dev.security.entities.User;

import pl.calculations.DO.DeviceDO;

@Local
public interface ISystemCalculationsLocal {
	public User findUser(int id);
	public boolean checkUserLoginAndPassword(String login, String password);
	boolean createUser(String login, String name, String surname, String eMail, String cellPhone, String pass);
	List<DeviceDO> findAllDevices();
}
