package br.com.cwi.crescer.musiclr.controller;

import br.com.cwi.crescer.musiclr.controller.response.AmizadeResponse;
import br.com.cwi.crescer.musiclr.controller.response.PessoaResponse;
import br.com.cwi.crescer.musiclr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/amizades")
public class AmizadeController {

    @Autowired
    private SolicitacaoAmizadeService solicitacaoAmizadeService;

    @Autowired
    private SolicitarAmizadeService solicitarAmizadeService;

    @Autowired
    private RemoverAmizadeService removerAmizadeService;

    @Autowired
    private ListarAmizadeService listarAmizadeService;

    @Autowired
    private AceitarAmizadeService aceitarAmizadeService;

    @GetMapping
    public Page<PessoaResponse> listar(Pageable pageable) {
        return listarAmizadeService.listar(pageable);
    }

    @GetMapping("/solicitacoes")
    public List<AmizadeResponse> solicitacoes() {
        return solicitacaoAmizadeService.solicitacoes();
    }

    @PostMapping("/solicitar/{pessoaId}")
    @ResponseStatus(CREATED)
    public void solicitar(@PathVariable Long pessoaId) {
        solicitarAmizadeService.solicitar(pessoaId);
    }

    @PutMapping("/aceitar/{amizadeId}")
    public void aceitar(@PathVariable Long amizadeId) {
        aceitarAmizadeService.aceitar(amizadeId);
    }

    @DeleteMapping("/remover/{pessoaId}")
    public void remover(@PathVariable Long pessoaId) {
        removerAmizadeService.remover(pessoaId);
    }
}
