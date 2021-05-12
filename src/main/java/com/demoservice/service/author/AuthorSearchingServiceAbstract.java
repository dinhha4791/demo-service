package com.demoservice.service.author;

import com.demoservice.repository.AuthorRepository;
import com.demoservice.service.ISearchingService;
import com.demoservice.service.common.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class AuthorSearchingServiceAbstract<A , S, Rs> implements ISearchingService<A, S> {
    @Autowired
    protected AuthorRepository authorRepository;
    @Autowired
    protected MappingService<A, Rs> mappingService;
}
