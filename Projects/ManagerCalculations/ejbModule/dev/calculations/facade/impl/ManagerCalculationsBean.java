package dev.calculations.facade.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import dev.calculations.entities.Device;
import dev.calculations.facade.IManagerCalculationsLocal;
import dev.calculations.facade.IManagerCalculationsRemote;

@Stateless
@Local(IManagerCalculationsLocal.class)
@Remote(IManagerCalculationsRemote.class)
public class ManagerCalculationsBean implements IManagerCalculationsLocal, IManagerCalculationsRemote{
	@PersistenceContext(unitName="ManagerCalculationsPU")
	private EntityManager manager;
	public static final String NAME = "ManagerCalculationsBean";
	
	public ManagerCalculationsBean() {}

	@SuppressWarnings("unchecked")
	@Override
	public List<Device> findAllDevices() {
		Query query = manager.createNamedQuery(Device.findAll);
		
		return ((List<Device>) query.getResultList()); 
	}
}
