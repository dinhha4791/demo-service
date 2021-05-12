package com.demoservice.service.book;

import com.demoservice.repository.BookRepository;
import com.demoservice.service.ISearchingService;
import com.demoservice.service.common.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class BookSearchingServiceAbstract<A , Rs> implements ISearchingService<A, BookSpecification> {
    @Autowired
    protected BookRepository bookRepository;
    @Autowired
    protected MappingService<A, Rs> mappingService;
}
