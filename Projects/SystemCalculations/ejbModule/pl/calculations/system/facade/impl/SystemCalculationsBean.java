package pl.calculations.system.facade.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.naming.NamingException;

import pl.calculations.DO.DeviceDO;
import pl.calculations.system.facade.ISystemCalculationsLocal;
import pl.calculations.system.facade.ISystemCalculationsRemote;
import dev.calculations.entities.Device;
import dev.calculations.facade.IManagerCalculationsRemote;
import dev.security.entities.User;
import dev.security.facade.IManagerSecurityRemote;

@Stateless
public class SystemCalculationsBean implements ISystemCalculationsLocal, ISystemCalculationsRemote{
	
	@EJB(beanName = "ManagerSecurity")
	private IManagerSecurityRemote managerSecurity;
	
	@EJB(beanName = "ManagerCalculationsBean")
	private IManagerCalculationsRemote managerCalculations;
	
	public SystemCalculationsBean() throws NamingException {}
	
	@Override
	public User findUser(int id) {
		return managerSecurity.findUser(id);
	}

	@Override
	public boolean checkUserLoginAndPassword(String login, String password) {
		return managerSecurity.checkUserLoginAndPassword(login, password);
	}

	@Override
	public boolean createUser(String login, String name, String surname, String eMail, String cellPhone, String pass) {
		return managerSecurity.createUser(login, name, surname, eMail, cellPhone, pass);
	}

	@Override
	public List<DeviceDO> findAllDevices() {
		List<Device> deviceList = managerCalculations.findAllDevices();
		List<DeviceDO> deviceMOList = new ArrayList<DeviceDO>();
		
		for(Device d : deviceList){
			deviceMOList.add(new DeviceDO(d));
		}
		
		return deviceMOList;
	}
}
