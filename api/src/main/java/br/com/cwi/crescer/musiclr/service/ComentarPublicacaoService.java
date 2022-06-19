package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.request.ComentarPublicacaoRequest;
import br.com.cwi.crescer.musiclr.controller.response.ReagirPublicacaoResponse;
import br.com.cwi.crescer.musiclr.model.Comentario;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ComentarPublicacaoService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private BuscarPublicacaoService buscarPublicacaoService;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public ReagirPublicacaoResponse comentar(Long id, ComentarPublicacaoRequest request) {

        Pessoa pessoa = pessoaAutenticadaService.get();
        Publicacao publicacao = buscarPublicacaoService.findById(id);
        Comentario comentario = new Comentario();

        comentario.setTexto(request.getTexto());
        comentario.setPessoa(pessoa);
        comentario.setPublicacao(publicacao);
        comentario.setDataCriacao(LocalDateTime.now());

        comentarioRepository.save(comentario);

        return new ReagirPublicacaoResponse(comentarioRepository
                .countByPublicacao(publicacao));
    }
}
