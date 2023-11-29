package com.mitocode.mitoacademia.service.impl;

import com.mitocode.mitoacademia.model.Course;
import com.mitocode.mitoacademia.repo.ICourseRepo;
import com.mitocode.mitoacademia.repo.IGeneRepo;
import com.mitocode.mitoacademia.service.ICourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl extends CRUDServiceImpl<Course,Integer> implements ICourseService{

    private final ICourseRepo repo;
    @Override
    protected IGeneRepo<Course, Integer> getRepo() {
        return repo;
    }
}
