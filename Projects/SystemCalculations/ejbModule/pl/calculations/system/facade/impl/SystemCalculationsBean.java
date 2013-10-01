package pl.calculations.system.facade.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import pl.calculations.DO.UserDO;
import pl.calculations.assembler.Assembler;
import pl.calculations.system.facade.ISystemCalculationsLocal;
import pl.calculations.system.facade.ISystemCalculationsRemote;
import pl.soda.generalPurposeObjects.OperationResult;
import dev.calculations.facade.IManagerCalculationsRemote;
import dev.security.facade.IManagerSecurityRemote;

@Stateless
public class SystemCalculationsBean implements ISystemCalculationsLocal, ISystemCalculationsRemote{
	
	@EJB(beanName = "ManagerSecurity")
	private IManagerSecurityRemote managerSecurity;
	
	@EJB(beanName = "ManagerCalculationsBean")
	private IManagerCalculationsRemote managerCalculations;
	
	public SystemCalculationsBean() throws NamingException {}
	
	@Override
	public boolean checkUserLoginAndPassword(String login, String password) {
		return managerSecurity.checkUserLoginAndPassword(login, password);
	}

	@Override
	public OperationResult<Boolean> createUser(UserDO newUser) {
		return managerSecurity.createUser(Assembler.getUserFromUserDO(newUser));
	}
}
