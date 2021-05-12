package com.demoservice.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISearchingService<A, S> {
    List<A> search(S specification);
}
