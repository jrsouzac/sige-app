package com.sige.application.repository;

import com.sige.application.model.Endereco;
import com.sige.application.model.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepository extends JpaRepository<Local, Long> {
}
