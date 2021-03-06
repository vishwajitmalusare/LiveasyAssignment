package com.liveasyassignment.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "deliverydetails")
public class DeliveryDetailsModel {
	
	public DeliveryDetailsModel() {
	}
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column( name = "loadId")
	private Long loadId;
	
	@NotNull
	@NotEmpty(message = "Loading point should not be empty")
	private String loadPoint;
	
	@NotNull
	@NotEmpty(message = " Unloading point should not be empty")
	private String unloadingPoint;
	
	@NotNull
	@NotEmpty(message = " Product type should not be empty")
	private String productType;
	
	@NotNull
	@NotEmpty(message = " Truck type should not be empty")
	private String truckType;
	
	@NotNull
	private int noOfTrucks;
	
	@NotNull
	private int weight;
	
	@NotNull
	private int shipperId;
	
	@NotNull
	private LocalDateTime date;

	@ElementCollection
	@MapKeyColumn(name="comment")
	@CollectionTable(name="delivery_details_model_optional",joinColumns =@JoinColumn(name="delivery_details_model_load_id"))
	private Map<String,String > optional;

	public Long getLoadId() {
		return loadId;
	}

	public void setLoadId(Long loadId) {
		this.loadId = loadId;
	}

	public String getLoadPoint() {
		return loadPoint;
	}

	public void setLoadPoint(String loadPoint) {
		this.loadPoint = loadPoint;
	}

	public String getUnloadingPoint() {
		return unloadingPoint;
	}

	public void setUnloadingPoint(String unloadingPoint) {
		this.unloadingPoint = unloadingPoint;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public int getNoOfTrucks() {
		return noOfTrucks;
	}

	public void setNoOfTrucks(int noOfTrucks) {
		this.noOfTrucks = noOfTrucks;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getShipperId() {
		return shipperId;
	}

	public void setShipperId(int shipperId) {
		this.shipperId = shipperId;
	}
	
	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public Map<String, String> getOptional() {
		return optional;
	}

	public void setOptional(Map<String, String> optional) {
		this.optional = optional;
	}

	@Override
	public String toString() {
		return "DeliveryDetailsModel [loadId=" + loadId + ", loadPoint=" + loadPoint + ", unloadingPoint="
				+ unloadingPoint + ", productType=" + productType + ", truckType=" + truckType + ", noOfTrucks="
				+ noOfTrucks + ", weight=" + weight + ", shipperId=" + shipperId + ", date=" + date + ", optional="
				+ optional + "]";
	}
	
	
}
