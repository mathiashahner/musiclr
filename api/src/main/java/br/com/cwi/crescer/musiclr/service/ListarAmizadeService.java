package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.PessoaResponse;
import br.com.cwi.crescer.musiclr.mapper.PessoaMapper;
import br.com.cwi.crescer.musiclr.model.Amizade;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.crescer.musiclr.model.SituacaoAmizade.APROVADA;

@Service
public class ListarAmizadeService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public Page<PessoaResponse> listar(Pageable pageable) {

        Pessoa pessoa = pessoaAutenticadaService.get();

        Page<Amizade> amizades = amizadeRepository
                .buscarAmizadesPorSituacao(pessoa, APROVADA, pageable);

        List<PessoaResponse> response = amizades.stream()
                .map(amizade -> {
                    Pessoa amigo = amizade.getSolicitante().equals(pessoa) ?
                            amizade.getSolicitado() : amizade.getSolicitante();

                    PessoaResponse pessoaResponse = PessoaMapper.toResponse(amigo);
                    pessoaResponse.setAmigo(true);
                    return pessoaResponse;
                })
                .collect(Collectors.toList());

        return new PageImpl<>(response);
    }
}
