package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.mapper.ComentarioMapper;
import br.com.cwi.crescer.musiclr.mapper.PessoaMapper;
import br.com.cwi.crescer.musiclr.model.Comentario;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.model.Publicacao;
import br.com.cwi.crescer.musiclr.repository.ComentarioRepository;
import br.com.cwi.crescer.musiclr.repository.CurtidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.cwi.crescer.musiclr.mapper.ListarPublicacaoMapper.toResponse;

@Service
public class DetalharPublicacaoService {

    @Autowired
    private CurtidaRepository curtidaRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<PublicacaoResponse> detalhar(Page<Publicacao> publicacoes, Pessoa pessoaAutenticada) {

        return publicacoes.stream()
                .map(publicacao -> getPublicacaoResponse(pessoaAutenticada, publicacao))
                .collect(Collectors.toList());
    }

    public PublicacaoResponse getPublicacaoResponse(Pessoa pessoaAutenticada, Publicacao publicacao) {
        PublicacaoResponse responsePubli = toResponse(publicacao);
        List<Comentario> comentarios = comentarioRepository.findAllByPublicacao(publicacao);

        responsePubli.setPessoa(PessoaMapper.toResponse(publicacao.getPessoa()));
        responsePubli.setCurtida(curtidaRepository.existsByPublicacaoAndPessoa(publicacao, pessoaAutenticada));
        responsePubli.setNumCurtidas(curtidaRepository.countByPublicacao(publicacao));
        responsePubli.setNumComentarios(comentarioRepository.countByPublicacao(publicacao));
        responsePubli.setComentarios(ComentarioMapper.toResponse(comentarios));

        return responsePubli;
    }
}
