package dev.calculations.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(name= Device.findAll,
				query = "SELECT d FROM Device d")
})
@Table(name = "devices", schema = "public")
@SequenceGenerator(name = "DEVICES_ID_SEQ", sequenceName="device_id_seq", initialValue=1, allocationSize=10)
@Entity
public class Device implements Serializable{
	private static final long serialVersionUID = -2042750123050181909L;
	
	public static final String findAll = "Device.findAll";
	
	private int id;
	private String name;
	private String type;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="DEVICES_ID_SEQ")
	@Column(name="id")
	public int getId() {	return id;	}
	public void setId(int id) {	 this.id = id;	}
	
	@Column(name = "name")
	public String getName() {	return name;	}
	public void setName(String name) {	this.name = name;	}
	
	@Column(name = "type")
	public String getType() {	return type;	}
	public void setType(String type) {	this.type = type;	}
	
}
