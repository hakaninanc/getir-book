package com.getir.book.rest.model;

import com.getir.book.model.Book;
import com.getir.book.rest.model.request.BookDetailRequest;
import com.getir.book.rest.model.request.BookRequest;
import com.getir.book.rest.model.request.OrderBookRequest;
import com.getir.book.rest.model.response.BookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book convert(BookRequest request);

    BookResponse convert(Book book);

    BookDetailRequest convertToBookDetail(Book book);

    @Mapping(target = "stock", source = "quantity")
    List<Book> convert(List<OrderBookRequest> orderBookRequest);

    @Mapping(target = "stock", source = "quantity")
    Book convert(OrderBookRequest orderBookRequest);
}
