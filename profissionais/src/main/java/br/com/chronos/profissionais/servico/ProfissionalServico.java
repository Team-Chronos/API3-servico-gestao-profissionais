package br.com.chronos.profissionais.servico;

import br.com.chronos.profissionais.api.dto.ProfissionalRequisicao;
import br.com.chronos.profissionais.api.dto.ProfissionalResposta;
import br.com.chronos.profissionais.api.dto.ProjetoVinculadoResposta;
import br.com.chronos.profissionais.dominio.Profissional;
import br.com.chronos.profissionais.dominio.ProfissionalProjeto;
import br.com.chronos.profissionais.dominio.ProfissionalProjetoId;
import br.com.chronos.profissionais.dominio.Projeto;
import br.com.chronos.profissionais.repositorio.ProfissionalProjetoRepositorio;
import br.com.chronos.profissionais.repositorio.ProfissionalRepositorio;
import br.com.chronos.profissionais.repositorio.ProjetoRepositorio;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProfissionalServico {

    private final ProfissionalRepositorio profissionalRepositorio;
    private final ProjetoRepositorio projetoRepositorio;
    private final ProfissionalProjetoRepositorio profissionalProjetoRepositorio;

    public ProfissionalServico(ProfissionalRepositorio profissionalRepositorio,
                               ProjetoRepositorio projetoRepositorio,
                               ProfissionalProjetoRepositorio profissionalProjetoRepositorio) {
        this.profissionalRepositorio = profissionalRepositorio;
        this.projetoRepositorio = projetoRepositorio;
        this.profissionalProjetoRepositorio = profissionalProjetoRepositorio;
    }

    public List<ProfissionalResposta> listar() {
        return profissionalRepositorio.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ProfissionalResposta buscarPorId(Integer id) {
        Profissional profissional = buscarProfissionalOuFalhar(id);
        return toResponse(profissional);
    }

    @Transactional
    public ProfissionalResposta criar(ProfissionalRequisicao request) {
        if (profissionalRepositorio.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Ja existe profissional com esse email.");
        }

        Profissional profissional = new Profissional();
        preencherDados(profissional, request);

        Profissional salvo = profissionalRepositorio.save(profissional);
        return toResponse(salvo);
    }

    @Transactional
    public ProfissionalResposta atualizar(Integer id, ProfissionalRequisicao request) {
        Profissional profissional = buscarProfissionalOuFalhar(id);

        if (!profissional.getEmail().equalsIgnoreCase(request.email())
                && profissionalRepositorio.existsByEmail(request.email())) {
            throw new IllegalArgumentException("Ja existe profissional com esse email.");
        }

        preencherDados(profissional, request);
        Profissional salvo = profissionalRepositorio.save(profissional);
        return toResponse(salvo);
    }

    @Transactional
    public void deletar(Integer id) {
        Profissional profissional = buscarProfissionalOuFalhar(id);
        profissionalRepositorio.delete(profissional);
    }

    @Transactional
    public void vincularProjeto(Integer profissionalId, Integer projetoId, BigDecimal valorHora) {
        Profissional profissional = buscarProfissionalOuFalhar(profissionalId);
        Projeto projeto = projetoRepositorio.findById(projetoId)
                .orElseThrow(() -> new EntityNotFoundException("Projeto nao encontrado."));

        ProfissionalProjetoId id = new ProfissionalProjetoId(projeto.getId(), profissional.getId());

        ProfissionalProjeto vinculo = profissionalProjetoRepositorio.findById(id).orElseGet(ProfissionalProjeto::new);
        vinculo.setId(id);
        vinculo.setProfissional(profissional);
        vinculo.setProjeto(projeto);
        vinculo.setValorHora(valorHora);

        profissionalProjetoRepositorio.save(vinculo);
    }

    @Transactional
    public void desvincularProjeto(Integer profissionalId, Integer projetoId) {
        ProfissionalProjetoId id = new ProfissionalProjetoId(projetoId, profissionalId);
        if (!profissionalProjetoRepositorio.existsById(id)) {
            throw new EntityNotFoundException("Vinculo nao encontrado.");
        }
        profissionalProjetoRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ProjetoVinculadoResposta> listarProjetosVinculados(Integer profissionalId) {
        buscarProfissionalOuFalhar(profissionalId);

        return profissionalProjetoRepositorio.buscarPorProfissionalComProjeto(profissionalId)
                .stream()
                .map(v -> new ProjetoVinculadoResposta(
                        v.getProjeto().getId(),
                        v.getProjeto().getNome(),
                        v.getProjeto().getCodigo(),
                        v.getValorHora()
                ))
                .toList();
    }

    private Profissional buscarProfissionalOuFalhar(Integer id) {
        return profissionalRepositorio.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Profissional nao encontrado."));
    }

    private ProfissionalResposta toResponse(Profissional profissional) {
        return new ProfissionalResposta(
                profissional.getId(),
                profissional.getNome(),
                profissional.getEmail(),
                profissional.getAtivo(),
                profissional.getCargoId()
        );
    }

    private void preencherDados(Profissional profissional, ProfissionalRequisicao request) {
        profissional.setNome(request.nome());
        profissional.setEmail(request.email());
        profissional.setSenhaHash(request.senhaHash());
        profissional.setAtivo(request.ativo());
        profissional.setCargoId(request.cargoId());
    }
}
