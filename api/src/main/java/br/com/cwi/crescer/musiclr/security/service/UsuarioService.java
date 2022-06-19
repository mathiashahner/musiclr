package br.com.cwi.crescer.musiclr.security.service;

import br.com.cwi.crescer.musiclr.security.controller.request.UsuarioRequest;
import br.com.cwi.crescer.musiclr.security.model.Permissao;
import br.com.cwi.crescer.musiclr.security.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static br.com.cwi.crescer.musiclr.security.mapper.UsuarioMapper.toEntity;

@Service
public class UsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario criar(UsuarioRequest request) {

        Usuario usuario = toEntity(request);

        Permissao permissao = new Permissao();
        permissao.setNome("USER");

        List<Permissao> permissoes = new ArrayList<>();
        permissoes.add(permissao);

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setPermissoes(permissoes);

        return usuario;
    }
}
