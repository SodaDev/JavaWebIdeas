package dev.security.facade.impl;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.security.entities.User;
import dev.security.facade.IManagerSecurityLocal;
import dev.security.facade.IManagerSecurityRemote;

@Stateless
@Local(IManagerSecurityLocal.class)
@Remote(IManagerSecurityRemote.class)
public class ManagerSecurityBean implements IManagerSecurityRemote, IManagerSecurityLocal{
	@PersistenceContext(unitName="ManagerSecurityPU")
	private EntityManager manager;
	
	public static final String NAME = "ManagerSecurityBean";
	
	public ManagerSecurityBean() {}
	
	@Override
	public boolean createUser(String login, String name, String surname, String eMail, String cellPhone, String pass) {
		User newUser = new User();
        newUser.setLogin(login);
        newUser.setName(name);
        newUser.setSurname(surname);
        newUser.seteMail(eMail);
        newUser.setCellPhone(cellPhone);
        newUser.setPassword(pass);
		
       	manager.persist(newUser);	
	
		return true;
	}

	@Override
	public User findUser(int id) {
		return manager.find(User.class, id);
	}

	@Override
	public boolean checkUserLoginAndPassword(String login, String password) {
		Query query = manager.createNamedQuery(User.findUserByLogin);
		query.setParameter("p_login", login);
		query.setParameter("p_password", password);
		
		if(query.getResultList().isEmpty()) 
			return false;
		
		return true;
	}

}
