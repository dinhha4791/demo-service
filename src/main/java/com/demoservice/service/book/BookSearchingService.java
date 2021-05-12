package com.demoservice.service.book;

import com.demoservice.entity.Book;
import com.demoservice.rest.payload.book.BookResponseDto;
import com.demoservice.rest.payload.book.BookSearchCriteria;
import com.demoservice.service.common.LoggingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookSearchingService extends BookSearchingServiceAbstract<Book, BookResponseDto> {
    private static final String BOOK_SEARCHING_SERVICE = "BookSearchingService";
    @Autowired
    private LoggingService loggingService;

    @Override
    public List<Book> search(BookSpecification specification) {
        List<Book> books = bookRepository.findAll(specification);
        return books;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<BookResponseDto> searchByAuthor(BookSearchCriteria bookSearchCriteria) {
        loggingService.logInfo(BOOK_SEARCHING_SERVICE, "START searchByAuthor");

        BookSpecification bookSpecification = new BookSpecification(bookSearchCriteria);
        List<BookResponseDto> responses = search(bookSpecification)
                .stream()
                .map(book -> mappingService.convertToDto(book, BookResponseDto.class))
                .collect(Collectors.toList());

        loggingService.logInfo(BOOK_SEARCHING_SERVICE, "END searchByAuthor");
        return responses;
    }
}
