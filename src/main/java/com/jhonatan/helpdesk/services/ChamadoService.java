package com.jhonatan.helpdesk.services;

import com.jhonatan.helpdesk.domain.Chamado;
import com.jhonatan.helpdesk.domain.Cliente;
import com.jhonatan.helpdesk.domain.Tecnico;
import com.jhonatan.helpdesk.repositories.ChamadoRepository;
import com.jhonatan.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TecnicoService tecnicoService;

    public Chamado findById(Long id){
        Optional<Chamado> obj = chamadoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id: " + id));
    }

    public List<Chamado> findAll(){
        return chamadoRepository.findAll();
    }

    public List<Chamado> findbyCliente(Long id){
        Cliente cliente = clienteService.findById(id);
        if(cliente.getChamados().size() <= 0){
            throw new ObjectNotFoundException("Cliente com id " + id + " não possui chamados");
        }
        return chamadoRepository.findByClienteId(id);
    }

    public List<Chamado> findByTecnico(Long id){
        Tecnico tecnico = tecnicoService.findById(id);
        if(tecnico.getChamados().size() <= 0){
            throw new ObjectNotFoundException("Tecnico com id " + id + " não possui chamados");
        }
        return chamadoRepository.findByTecnicoId(id);
    }
}
