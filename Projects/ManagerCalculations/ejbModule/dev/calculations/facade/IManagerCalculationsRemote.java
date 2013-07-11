package dev.calculations.facade;

import java.util.List;

import javax.ejb.Remote;

import dev.calculations.entities.Device;

@Remote
public interface IManagerCalculationsRemote {
	List<Device> findAllDevices();
}
