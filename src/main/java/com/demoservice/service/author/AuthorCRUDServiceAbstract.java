package com.demoservice.service.author;

import com.demoservice.exception.BusinessException;
import com.demoservice.constants.RestConstants;
import com.demoservice.entity.Author;
import com.demoservice.repository.AuthorRepository;
import com.demoservice.repository.BookRepository;
import com.demoservice.service.ICRUDService;
import com.demoservice.service.book.BookCRUDService;
import com.demoservice.service.common.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AuthorCRUDServiceAbstract<A, Rq, Rs>  implements ICRUDService<Rq, Rs> {
    @Autowired
    protected AuthorRepository authorRepository;
    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected BookCRUDService bookCRUDService;
    @Autowired
    protected MappingService<A, Rq> mappingService;

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(
                () -> new BusinessException(RestConstants.BUSINESS_EXCEPTION_CODE, "Could not found Author with id : " + id));
    }
}
