package com.jhonatan.helpdesk.resources;

import com.jhonatan.helpdesk.domain.Tecnico;
import com.jhonatan.helpdesk.domain.dtos.TecnicoDTO;
import com.jhonatan.helpdesk.services.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoResources {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Long id){
        Tecnico obj = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
        List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDTO> listDto = list.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDto){
        Tecnico obj = tecnicoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new TecnicoDTO(obj));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> update(@Valid @PathVariable Long id, @RequestBody TecnicoDTO objDto){
        Tecnico obj = tecnicoService.update(id, objDto);
        return ResponseEntity.ok().body(new TecnicoDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> deleteById(@PathVariable Long id){
        tecnicoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
