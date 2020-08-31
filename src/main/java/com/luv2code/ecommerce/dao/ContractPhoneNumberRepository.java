package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.ContractPhoneNumber;
import com.luv2code.ecommerce.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ContractPhoneNumberRepository extends JpaRepository<ContractPhoneNumber, Long> {
    @Query(value = "SELECT * FROM contracts_phone_numbers cp WHERE cp.date_activation like %?1% ",nativeQuery = true)
    List<ContractPhoneNumber> findByDateActivation(String date);
    @Query(value = "SELECT * FROM contracts_phone_numbers cp WHERE cp.contract_id =?1 ",nativeQuery = true)
    List<ContractPhoneNumber> findByContractid(long contract_id);
    @Query(value = "SELECT * FROM contracts_phone_numbers cp WHERE cp.phone_number_id =?1 ",nativeQuery = true)
    List<ContractPhoneNumber> findByPhoneNumberid(long phone_id);
    @Query(value = "SELECT * FROM contracts_phone_numbers cp WHERE cp.contract_id =?1 and cp.phone_number_id =?2 ",nativeQuery = true)
    List<ContractPhoneNumber> findByContractidAndPhoneNumberid(long contract_id,long phone_id);


}
