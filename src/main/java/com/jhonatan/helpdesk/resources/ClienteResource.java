package com.jhonatan.helpdesk.resources;

import com.jhonatan.helpdesk.domain.Cliente;
import com.jhonatan.helpdesk.domain.dtos.ClienteDTO;
import com.jhonatan.helpdesk.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> listCliente = clienteService.findAll();
        List<ClienteDTO> listClienteDto = listCliente.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listClienteDto);
    }
}
