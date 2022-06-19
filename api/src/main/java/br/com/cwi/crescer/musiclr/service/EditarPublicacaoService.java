package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.request.EditarPublicacaoRequest;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.PublicacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class EditarPublicacaoService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private PublicacaoRepository publicacaoRepository;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    public void editar(Long id, EditarPublicacaoRequest request) {

        Pessoa pessoa = pessoaAutenticadaService.get();
        Publicacao publicacao = buscarPublicacaoService.findById(id);

        if (!publicacaoRepository.existsByIdAndPessoaId(id, pessoa.getId())) {
            throw new ResponseStatusException(UNPROCESSABLE_ENTITY, "Você não pode editar esta publicação porque ela não é sua.");
        }

        publicacao.setPublica(request.isPublica());
        publicacaoRepository.save(publicacao);
    }
}
