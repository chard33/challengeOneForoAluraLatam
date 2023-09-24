package com.alura.modelo.topico;

import com.alura.modelo.estatusT.StatusTopico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface repositorioTopico extends JpaRepository<Topico, Long>{
   
   Page<Topico> findByStatusNot(StatusTopico statusTopico,
                                      Pageable paginacion);
}
