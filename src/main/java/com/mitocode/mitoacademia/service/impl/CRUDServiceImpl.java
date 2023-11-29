package com.mitocode.mitoacademia.service.impl;

import com.mitocode.mitoacademia.exception.ModelNotFoundException;
import com.mitocode.mitoacademia.repo.IGeneRepo;
import com.mitocode.mitoacademia.service.ICRUDService;

import java.lang.reflect.Method;
import java.util.List;

public abstract class CRUDServiceImpl<T, ID> implements ICRUDService<T, ID> {

    protected abstract IGeneRepo<T, ID> getRepo();

    @Override
    public T save(T t) throws Exception {
        return getRepo().save(t) ;
    }

    @Override
    public T update(T t, ID id) throws Exception {
        Class<?> clazz = t.getClass();
        String className = clazz.getSimpleName();
        String methodName = "setId"+className;
        Method setIdMethod = clazz.getMethod(methodName,id.getClass());
        setIdMethod.invoke(t,id);
        getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("Id not Found "+id));
        return getRepo().save(t);
    }

    @Override
    public List<T> readAll() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T readById(ID id) throws Exception {
        return getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("Id not Found "+id));
    }

    @Override
    public void delete(ID id) throws Exception {
        getRepo().findById(id).orElseThrow(()->new ModelNotFoundException("Id not Found "+id));
        getRepo().deleteById(id);
    }
}
