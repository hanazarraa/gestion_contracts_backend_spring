package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.ContractPhoneNumber;
import com.luv2code.ecommerce.entity.ContractSIM;
import com.luv2code.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContractSIMRepository extends JpaRepository<ContractSIM, Long> {
    @Query(value = "SELECT * FROM contracts_SIMS cs WHERE cs.contract_id =?1 ",nativeQuery = true)
    List<ContractSIM> findByContractid(long contract_id);
    @Query(value = "SELECT * FROM contracts_SIMS cs WHERE cs.sim_id =?1 ",nativeQuery = true)
    List<ContractSIM> findBySIMid(long sim_id);
    @Query(value = "SELECT * FROM contracts_SIMS cs WHERE cs.contract_id =?1 and cs.sim_id =?2 ",nativeQuery = true)
    List<ContractSIM> findByContractidAndSIMid(long contract_id,long sim_id);

}
