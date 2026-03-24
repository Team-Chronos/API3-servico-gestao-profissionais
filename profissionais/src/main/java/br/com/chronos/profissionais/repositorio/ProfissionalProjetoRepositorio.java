package br.com.chronos.profissionais.repositorio;

import br.com.chronos.profissionais.dominio.ProfissionalProjeto;
import br.com.chronos.profissionais.dominio.ProfissionalProjetoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfissionalProjetoRepositorio extends JpaRepository<ProfissionalProjeto, ProfissionalProjetoId> {

    @Query("""
            select pp
            from ProfissionalProjeto pp
            join fetch pp.projeto p
            where pp.profissional.id = :profissionalId
            """)
    List<ProfissionalProjeto> buscarPorProfissionalComProjeto(@Param("profissionalId") Integer profissionalId);
}
