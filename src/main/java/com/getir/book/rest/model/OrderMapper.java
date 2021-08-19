package com.getir.book.rest.model;


import com.getir.book.model.Order;
import com.getir.book.rest.model.request.OrderRequest;
import com.getir.book.rest.model.response.OrderResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "customer.id", source = "customerId")
    Order convert(OrderRequest orderRequest);

    OrderResponse convert (Order order);

    List<OrderResponse> convert (List<Order> order);




}
