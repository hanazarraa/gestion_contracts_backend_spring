package com.luv2code.ecommerce.controller;

import com.luv2code.ecommerce.dao.ContractSIMRepository;
import com.luv2code.ecommerce.entity.ContractPhoneNumber;
import com.luv2code.ecommerce.entity.ContractSIM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/contracts_sims")
public class ContractSimController {
    @Autowired
    ContractSIMRepository contractSIMRepository;

    //get all contracts
    @GetMapping ("getAll")
    public List<ContractSIM> getAll(){
        return this.contractSIMRepository.findAll();
    }


    @RequestMapping(value="/search",method = RequestMethod.GET)
    @ResponseBody
    public List<ContractSIM> getContractSIMByContractAndSim(@RequestParam(value = "contract_id",required = false) String contract_id,
                                                                        @RequestParam(value = "sim_id",required = false) String sim_id
    ) {

        if(contract_id==null ){
            if(sim_id==null) {
                return this.contractSIMRepository.findAll();
            }else {
                return this.contractSIMRepository.findBySIMid(Long.parseLong(sim_id));
            }
        }else {
            if (sim_id==null) {
                return this.contractSIMRepository.findByContractid(Long.parseLong(contract_id));
            }else {
                return this.contractSIMRepository.findByContractidAndSIMid(Long.parseLong(contract_id),Long.parseLong(sim_id));
            }
        }

    }

}
