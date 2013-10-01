package dev.security.facade.impl;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.soda.generalPurposeObjects.OperationResult;
import pl.soda.utilities.StringUtils;
import dev.security.entities.User;
import dev.security.facade.IManagerSecurityLocal;
import dev.security.facade.IManagerSecurityRemote;

@Stateless
@Local(IManagerSecurityLocal.class)
@Remote(IManagerSecurityRemote.class)
public class ManagerSecurityBean implements IManagerSecurityRemote, IManagerSecurityLocal{
	@PersistenceContext(unitName="ManagerSecurityPU")
	private EntityManager entityManager;
	
	public static final String NAME = "ManagerSecurityBean";
	
	public ManagerSecurityBean() {}

	@Override
	public User findUser(int id) {
		return entityManager.find(User.class, id);
	}
	
	@Override
	public User findUserByLogin(String login) {
		Query query = entityManager.createNamedQuery(User.findUserByLogin);
		query.setParameter("p_login", login);
		
		try{
			return (User) query.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}

	@Override
	public User findUserByEMail(String eMail) {
		Query query = entityManager.createNamedQuery(User.findUserByEMail);
		query.setParameter("p_eMail", eMail);
		
		try{
			return (User) query.getSingleResult();
		} catch(NoResultException e){
			return null;
		}
	}	

	@Override
	public boolean checkUserLoginAndPassword(String login, String password) {
		Query query = entityManager.createNamedQuery(User.verifyUserByLoginAndPassword);
		query.setParameter("p_login", login);
		query.setParameter("p_password", password);
		
		if(query.getResultList().isEmpty()) 
			return false;
		
		return true;
	}

	@Override
	public OperationResult<Boolean> createUser(User newUser) {
		StringBuilder sb = new StringBuilder();
		
		if(findUserByLogin(newUser.getLogin()) != null)
			sb.append("Jinks! User with chosen login already exists, please try another cool login").append(StringUtils.NEW_LINE_SEPARATOR);
		if(findUserByEMail(newUser.geteMail()) != null){
			sb.append("User with chosen e-mail address already exists, did you forget your login or password?").append(StringUtils.NEW_LINE_SEPARATOR);
		}
			
		if(!sb.toString().isEmpty())
			return new OperationResult<Boolean>(false, sb.toString());
		
		try{
			entityManager.persist(newUser);
			entityManager.flush();
		} catch (Exception e){
			return new OperationResult<Boolean>(false, e.getMessage());
		}
		return new OperationResult<Boolean>(true, "");
	}
}
