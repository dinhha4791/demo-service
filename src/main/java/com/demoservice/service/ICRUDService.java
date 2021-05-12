package com.demoservice.service;

import java.util.List;

public interface ICRUDService<Rq, Rs> {
    Rs create(Rq rq);
    Rs getById(Long id);
    List<Rs> getAll();
    Rs updateById(Long id, Rq rq);
    void deleteById(Long id);
}
