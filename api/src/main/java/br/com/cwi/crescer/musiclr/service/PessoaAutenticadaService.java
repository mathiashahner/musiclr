package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.PessoaRepository;
import br.com.cwi.crescer.musiclr.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaAutenticadaService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa get() {
        return pessoaRepository.findByUsuarioId(usuarioAutenticadoService.getId());
    }
}
