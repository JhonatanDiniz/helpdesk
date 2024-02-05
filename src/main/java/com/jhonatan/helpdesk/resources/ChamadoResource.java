package com.jhonatan.helpdesk.resources;

import com.jhonatan.helpdesk.domain.Chamado;
import com.jhonatan.helpdesk.domain.dtos.ChamadoDTO;
import com.jhonatan.helpdesk.services.ChamadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Long id){
        Chamado obj = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<Chamado> list = chamadoService.findAll();
        List<ChamadoDTO> listDto = list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "cliente/{id}")
    public ResponseEntity<List<ChamadoDTO>> findByCliente(@PathVariable Long id){
        List<Chamado> list = chamadoService.findbyCliente(id);
        List<ChamadoDTO> listDto = list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "tecnico/{id}")
    public ResponseEntity<List<ChamadoDTO>> findByTecnico(@PathVariable Long id){
        List<Chamado> list = chamadoService.findByTecnico(id);
        List<ChamadoDTO> listDto = list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDto){
        Chamado chamado = chamadoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(chamado.getId()).toUri();
        return ResponseEntity.created(uri).body(new ChamadoDTO(chamado));
    }
}
