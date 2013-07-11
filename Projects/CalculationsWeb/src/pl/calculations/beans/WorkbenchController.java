package pl.calculations.beans;

import javax.annotation.ManagedBean;

@ManagedBean
public class WorkbenchController {
	private DeviceListBean deviceController;
	
	public WorkbenchController() {
		this.deviceController = new DeviceListBean();
	}

	public DeviceListBean getDeviceController() {
		return deviceController;
	}
}
