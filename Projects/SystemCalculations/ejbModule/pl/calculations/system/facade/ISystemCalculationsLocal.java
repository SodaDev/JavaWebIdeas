package pl.calculations.system.facade;

import javax.ejb.Local;

import pl.calculations.DO.UserDO;
import pl.soda.generalPurposeObjects.OperationResult;

@Local
public interface ISystemCalculationsLocal {
	public boolean checkUserLoginAndPassword(String login, String password);
	public OperationResult<Boolean> createUser(UserDO newUser);
}
