package com.luv2code.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="phone_numbers")
@Data
public class PhoneNmber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "phone_id")
    private long id;
    @Column(name = "phonenumber")
    private String phonenumber;
   //3 possible status : A=assigned , F=free , D=de-assigned
   @Column(name = "status")
   private String status;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "contract")
    @JsonIgnore
    @JsonManagedReference("phone-numbers-contract")
    private List<ContractPhoneNumber> contractsphonenumbers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ContractPhoneNumber> getContractsphonenumbers() {
        return contractsphonenumbers;
    }

    public void setContractsphonenumbers(List<ContractPhoneNumber> contractsphonenumbers) {
        this.contractsphonenumbers = contractsphonenumbers;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
