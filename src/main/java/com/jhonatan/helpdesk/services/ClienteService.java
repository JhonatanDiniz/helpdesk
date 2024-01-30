package com.jhonatan.helpdesk.services;

import com.jhonatan.helpdesk.domain.Cliente;
import com.jhonatan.helpdesk.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }
}
