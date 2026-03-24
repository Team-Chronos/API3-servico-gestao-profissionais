package br.com.chronos.profissionais.repositorio;

import br.com.chronos.profissionais.dominio.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepositorio extends JpaRepository<Projeto, Integer> {
}
