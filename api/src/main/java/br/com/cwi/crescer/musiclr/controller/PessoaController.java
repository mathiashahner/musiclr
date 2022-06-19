package br.com.cwi.crescer.musiclr.controller;

import br.com.cwi.crescer.musiclr.controller.request.CriarPessoaRequest;
import br.com.cwi.crescer.musiclr.controller.request.EditarPessoaRequest;
import br.com.cwi.crescer.musiclr.controller.response.CriarPessoaResponse;
import br.com.cwi.crescer.musiclr.controller.response.DetalharPessoaResponse;
import br.com.cwi.crescer.musiclr.controller.response.PessoaResponse;
import br.com.cwi.crescer.musiclr.service.EditarPessoaService;
import br.com.cwi.crescer.musiclr.service.FiltrarPessoaService;
import br.com.cwi.crescer.musiclr.service.CriarPessoaService;
import br.com.cwi.crescer.musiclr.service.DetalharPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/usuarios")
public class PessoaController {

    @Autowired
    private CriarPessoaService criarPessoaService;

    @Autowired
    private DetalharPessoaService detalharPessoaService;

    @Autowired
    private FiltrarPessoaService filtrarPessoaService;

    @Autowired
    private EditarPessoaService editarPessoaService;

    @PostMapping
    @ResponseStatus(CREATED)
    public CriarPessoaResponse criar(@Valid @RequestBody CriarPessoaRequest request) {
        return criarPessoaService.criar(request);
    }

    @GetMapping("/detalhar/{id}")
    public DetalharPessoaResponse detalhar(@PathVariable Long id) {
        return detalharPessoaService.detalhar(id);
    }

    @GetMapping
    public Page<PessoaResponse> buscar(@RequestParam String texto, Pageable pageable) {
        return filtrarPessoaService.buscar(texto, pageable);
    }

    @PutMapping
    public void editar(@RequestBody EditarPessoaRequest request) {
        editarPessoaService.editar(request);
    }
}
