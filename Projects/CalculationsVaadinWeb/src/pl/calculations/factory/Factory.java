package pl.calculations.factory;

import javax.naming.NamingException;

import pl.calculations.system.facade.ISystemCalculationsRemote;
import pl.skeleton.transferObjects.UserMO;
import pl.soda.generalPurposeObjects.OperationResult;

public class Factory {
	private static Factory instance = new Factory();
	
	private Factory() {}
	
	public static synchronized Factory getInstance() {
        return instance;
    }
	
	private static ISystemCalculationsRemote getSystemCalculations() {
		try {
			return EJBBinder.lookupRemoteSystemCalculationsBean();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean checkUserLoginAndPassword(String login, String password){
		if(login == null || login.isEmpty() || password == null || password.isEmpty())
			return false;
		return getSystemCalculations().checkUserLoginAndPassword(login, password);
	}
	
	public OperationResult<Boolean> createUser(UserMO userMO) {
		return getSystemCalculations().createUser(userMO.getModel());
	}
}
