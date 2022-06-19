package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.request.EditarPessoaRequest;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditarPessoaService {

    @Autowired
    private PessoaAutenticadaService pessoaAutenticadaService;

    @Autowired
    private PessoaRepository pessoaRepository;

    public void editar(EditarPessoaRequest request) {

        Pessoa pessoa = pessoaAutenticadaService.get();

        if (request.getNome() != null) {
            pessoa.setNome(request.getNome());
        }

        if (request.getApelido() != null) {
            pessoa.setApelido(request.getApelido());
        }

        if (request.getImagemPerfil() != null) {
            pessoa.setImagemPerfil(request.getImagemPerfil());
        }

        pessoaRepository.save(pessoa);
    }
}
