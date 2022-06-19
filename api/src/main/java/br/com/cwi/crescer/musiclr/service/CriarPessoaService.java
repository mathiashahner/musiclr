package br.com.cwi.crescer.musiclr.service;

import br.com.cwi.crescer.musiclr.controller.request.CriarPessoaRequest;
import br.com.cwi.crescer.musiclr.controller.response.CriarPessoaResponse;
import br.com.cwi.crescer.musiclr.model.Pessoa;
import br.com.cwi.crescer.musiclr.repository.PessoaRepository;
import br.com.cwi.crescer.musiclr.security.model.Usuario;
import br.com.cwi.crescer.musiclr.security.service.UsuarioService;
import br.com.cwi.crescer.musiclr.validator.ConfirmacaoSenhaValidator;
import br.com.cwi.crescer.musiclr.validator.EmailUnicoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.com.cwi.crescer.musiclr.mapper.CriarPessoaMapper.toEntity;

@Service
public class CriarPessoaService {

    @Autowired
    private ConfirmacaoSenhaValidator confirmacaoSenhaValidator;

    @Autowired
    private EmailUnicoValidator emailUnicoValidator;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UsuarioService usuarioService;

    public CriarPessoaResponse criar(CriarPessoaRequest request) {

        emailUnicoValidator.validar(request.getUsuario().getEmail());
        confirmacaoSenhaValidator.validar(request.getUsuario().getSenha(), request.getUsuario().getConfirmacaoSenha());

        Usuario usuario = usuarioService.criar(request.getUsuario());

        Pessoa pessoa = toEntity(request);
        pessoa.setUsuario(usuario);

        pessoaRepository.save(pessoa);

        return new CriarPessoaResponse(pessoa.getId());
    }
}
