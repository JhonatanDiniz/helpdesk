package com.jhonatan.helpdesk.services;

import com.jhonatan.helpdesk.domain.Tecnico;
import com.jhonatan.helpdesk.repositories.TecnicoRepository;
import com.jhonatan.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Long id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
    }
}
