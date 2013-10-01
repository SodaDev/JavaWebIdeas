package pl.calculations.system.facade;

import javax.ejb.Remote;

import pl.calculations.DO.UserDO;
import pl.soda.generalPurposeObjects.OperationResult;

@Remote
public interface ISystemCalculationsRemote {
	public boolean checkUserLoginAndPassword(String login, String password);
	public OperationResult<Boolean> createUser(UserDO newUser);
}
