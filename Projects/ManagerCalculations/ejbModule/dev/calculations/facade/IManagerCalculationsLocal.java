package dev.calculations.facade;

import java.util.List;

import javax.ejb.Local;

import dev.calculations.entities.Device;

@Local
public interface IManagerCalculationsLocal {
	List<Device> findAllDevices();
}
