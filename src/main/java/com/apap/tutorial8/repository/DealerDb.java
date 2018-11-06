package com.apap.tutorial8.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial8.model.DealerModel;

/**
 * DealerDb
 */
@Repository
public interface DealerDb extends JpaRepository<DealerModel, Long> {

}