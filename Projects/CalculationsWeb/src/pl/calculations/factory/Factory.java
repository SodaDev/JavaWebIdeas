package pl.calculations.factory;

import java.util.List;

import javax.naming.NamingException;

import pl.calculations.DO.DeviceDO;
import pl.calculations.system.facade.ISystemCalculationsRemote;

public class Factory {
	private static Factory instance = new Factory();
	
	private Factory() {}
	
	public static synchronized Factory getInstance() {
        return instance;
    }
	
	private static ISystemCalculationsRemote getSystemScada() {
		try {
			return EJBBinder.lookupRemoteSystemCalculationsBean();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean checkUserLoginAndPassword(String login, String password){
		return getSystemScada().checkUserLoginAndPassword(login, password);
	}
	
	public boolean createUser(String login, String name, String surname, String eMail, String cellPhone, String pass) {
		return getSystemScada().createUser(login, name, surname, eMail, cellPhone, pass);
	}
	
	public List<DeviceDO> findAllDevices() {
		return getSystemScada().findAllDevices();
	}
}
