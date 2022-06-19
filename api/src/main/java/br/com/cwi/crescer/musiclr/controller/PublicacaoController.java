package br.com.cwi.crescer.musiclr.controller;

import br.com.cwi.crescer.musiclr.controller.request.ComentarPublicacaoRequest;
import br.com.cwi.crescer.musiclr.controller.request.CriarPublicacaoRequest;
import br.com.cwi.crescer.musiclr.controller.request.EditarPublicacaoRequest;
import br.com.cwi.crescer.musiclr.controller.response.PublicacaoResponse;
import br.com.cwi.crescer.musiclr.controller.response.ReagirPublicacaoResponse;
import br.com.cwi.crescer.musiclr.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/publicacoes")
public class PublicacaoController {

    @Autowired
    private CriarPublicacaoService criarPublicacaoService;

    @Autowired
    private ListarPublicacaoService listarPublicacaoService;

    @Autowired
    private EditarPublicacaoService editarPublicacaoService;

    @Autowired
    private CurtirPublicacaoService curtirPublicacaoService;

    @Autowired
    private DescurtirPublicacaoService descurtirPublicacaoService;

    @Autowired
    private ComentarPublicacaoService comentarPublicacaoService;

    @Autowired
    private BuscarPublicacoesService buscarPublicacoesService;

    @Autowired
    private BuscarPorIdPublicacaoService buscarPorIdPublicacaoService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void criar(@Valid @RequestBody CriarPublicacaoRequest request) {
        criarPublicacaoService.criar(request);
    }

    @GetMapping
    public Page<PublicacaoResponse> listar(Pageable pageable) {
        return listarPublicacaoService.listar(pageable);
    }

    @GetMapping("/{id}")
    public PublicacaoResponse buscarPorId(@PathVariable Long id) {
        return buscarPorIdPublicacaoService.buscarPorId(id);
    }

    @GetMapping("/usuarios/{pessoaId}")
    public Page<PublicacaoResponse> buscar(@PathVariable Long pessoaId, Pageable pageable) {
        return buscarPublicacoesService.buscar(pessoaId, pageable);
    }

    @PutMapping("/{id}/editar")
    public void editar(@PathVariable Long id, @Valid @RequestBody EditarPublicacaoRequest request) {
        editarPublicacaoService.editar(id, request);
    }

    @PostMapping("/{id}/curtir")
    public ReagirPublicacaoResponse curtir(@PathVariable Long id) {
        return curtirPublicacaoService.curtir(id);
    }

    @DeleteMapping("/{id}/descurtir")
    public ReagirPublicacaoResponse descurtir(@PathVariable Long id) {
        return descurtirPublicacaoService.descurtir(id);
    }

    @PostMapping("/{id}/comentar")
    public ReagirPublicacaoResponse comentar(@PathVariable Long id, @Valid @RequestBody ComentarPublicacaoRequest request) {
        return comentarPublicacaoService.comentar(id, request);
    }
}
