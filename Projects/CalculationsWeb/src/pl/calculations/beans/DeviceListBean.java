package pl.calculations.beans;

import java.io.Serializable;

import pl.calculations.DO.DeviceDO;
import pl.calculations.factory.Factory;
import pl.calculations.modelObjects.DeviceMO;

public class DeviceListBean implements Serializable{
	private static final long serialVersionUID = -1096887054625476295L;
	private DeviceMO model;
	private DeviceDO[] selectedDevices;
	
	public DeviceListBean() {
		model = new DeviceMO(Factory.getInstance().findAllDevices());
	}

	public DeviceMO getModel() {
		return model;
	}

	public DeviceDO[] getSelectedDevices() {
		return selectedDevices;
	}

	public void setSelectedDevices(DeviceDO[] selectedDevices) {
		this.selectedDevices = selectedDevices;
	}
}
