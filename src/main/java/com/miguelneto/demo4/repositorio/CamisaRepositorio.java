package com.miguelneto.demo4.repositorio;

import com.miguelneto.demo4.entidades.Camisa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CamisaRepositorio extends JpaRepository<Camisa, Long> {
}