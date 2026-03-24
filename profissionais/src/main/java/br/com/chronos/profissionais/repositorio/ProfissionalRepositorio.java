package br.com.chronos.profissionais.repositorio;

import br.com.chronos.profissionais.dominio.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepositorio extends JpaRepository<Profissional, Integer> {

    boolean existsByEmail(String email);
}
