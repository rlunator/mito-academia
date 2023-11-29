package com.mitocode.mitoacademia.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IGeneRepo <T, ID> extends JpaRepository<T, ID> {

}
