package com.example.jetbrains.persistence;

import com.example.jetbrains.businesslayer.Code;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long>{
}
