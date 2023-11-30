package com.mitocode.mitoacademia.service.impl;

import com.mitocode.mitoacademia.model.SchoolPeriod;
import com.mitocode.mitoacademia.repo.IGeneRepo;
import com.mitocode.mitoacademia.repo.ISchoolPeriodRepo;
import com.mitocode.mitoacademia.service.ISchoolPeriodService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchoolPeriodServiceImpl extends CRUDServiceImpl<SchoolPeriod,Integer> implements ISchoolPeriodService{

    private final ISchoolPeriodRepo repo;
    
    @Override
    protected IGeneRepo<SchoolPeriod, Integer> getRepo() {
        return repo;
    }
}
