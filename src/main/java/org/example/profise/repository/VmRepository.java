package org.example.profise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.profise.model.Vm;

@Repository
public interface VmRepository extends JpaRepository<Vm, Long> {

}
