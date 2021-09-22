package com.liveasyassignment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.liveasyassignment.model.DeliveryDetailsModel;

@Repository
public interface DiliveryDetailsRepo extends JpaRepository<DeliveryDetailsModel, Long> {
	public DeliveryDetailsModel findByLoadId(long loadId);
}
