package com.miguelneto.demo4.repositorio;

import com.miguelneto.demo4.entidades.Calca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CalcaRepositorio extends JpaRepository<Calca, Long> {
}