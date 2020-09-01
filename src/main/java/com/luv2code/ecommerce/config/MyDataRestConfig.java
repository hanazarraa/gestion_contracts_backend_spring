package com.luv2code.ecommerce.config;

import com.luv2code.ecommerce.entity.Contract;
import com.luv2code.ecommerce.entity.ContractPhoneNumber;
import com.luv2code.ecommerce.entity.Customer;
import com.luv2code.ecommerce.entity.User;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

public class MyDataRestConfig implements RepositoryRestConfigurer {
    @Override
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        HttpMethod[] theUnsupportedActions = {HttpMethod.PUT,  HttpMethod.DELETE};
        config.getExposureConfiguration()
                .forDomainType(User.class)
                .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
                .withCollectionExposure((metdate,httpMethods)->httpMethods.disable(theUnsupportedActions));
        config.getExposureConfiguration()
        .forDomainType(Customer.class)
        .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
        .withCollectionExposure((metdate,httpMethods)->httpMethods.disable(theUnsupportedActions));
        config.getExposureConfiguration()
        .forDomainType(Contract.class)
        .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
        .withCollectionExposure((metdate,httpMethods)->httpMethods.disable(theUnsupportedActions));
       


    }
    

}
