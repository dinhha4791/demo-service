package com.demoservice.service.book;

import com.demoservice.exception.BusinessException;
import com.demoservice.constants.RestConstants;
import com.demoservice.entity.Book;
import com.demoservice.repository.BookRepository;
import com.demoservice.service.ICRUDService;
import com.demoservice.service.author.AuthorCRUDService;
import com.demoservice.service.common.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BookCRUDServiceAbstract<A, Rq, Rs> implements ICRUDService<Rq, Rs> {
    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected AuthorCRUDService authorCRUDService;
    @Autowired
    protected MappingService<A, Rq> mappingService;

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new BusinessException(RestConstants.BUSINESS_EXCEPTION_CODE, "Could not found Book with id : " + id));
    }
}
