package pl.calculations.DO;

import java.io.Serializable;

import dev.calculations.entities.Device;

public class DeviceDO implements Serializable {
	private static final long serialVersionUID = -8699432506853253831L;
	private Device model;
	
	public DeviceDO() {
		this.model = new Device();
	}
	
	public DeviceDO(Device model) {
		super();
		this.model = model;
	}

	//===================== GETTERS & SETTERS ========================//
	public int getId() {
		return model.getId();
	}
	
	public String getName() {
		return model.getName();
	}
	
	public void setName(String name) {
		model.setName(name);
	}
	
	public String getType() {
		return model.getType();
	}
	public void setType(String type) {
		model.setType(type);
	}

	public Device getModel() {
		return model;
	}
}
