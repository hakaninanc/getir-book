package com.getir.book.rest.model;


import com.getir.book.model.Customer;
import com.getir.book.rest.model.request.CustomerRequest;
import com.getir.book.rest.model.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);


    Customer convert (CustomerRequest customerRequest);

    CustomerResponse convert (Customer customer);

}
