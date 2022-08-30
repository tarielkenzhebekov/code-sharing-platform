package com.example.jetbrains.persistence;

import com.example.jetbrains.businesslayer.Code;

import java.util.UUID;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long>{
    Optional<Code> findByUuid(UUID uuid);
}
