package pl.calculations.modelObjects;

import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;

import pl.calculations.DO.DeviceDO;

public class DeviceMO extends ListDataModel<DeviceDO> implements SelectableDataModel<DeviceDO> {

	public DeviceMO() {}
	
	public DeviceMO(List<DeviceDO> data){
		super(data);
	}
	
	@Override
	public DeviceDO getRowData(String rowKey) {
		List<DeviceDO> devices = (List<DeviceDO>) getWrappedData();  
        
        for(DeviceDO deviceDO : devices) {  
            if(deviceDO.getName().equals(rowKey))  
                return deviceDO;  
        }  
          
        return null; 
	}

	@Override
	public Object getRowKey(DeviceDO device) {
		return device.getName();
	}
	
}
