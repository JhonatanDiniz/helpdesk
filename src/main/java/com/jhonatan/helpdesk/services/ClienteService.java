package com.jhonatan.helpdesk.services;

import com.jhonatan.helpdesk.domain.Cliente;
import com.jhonatan.helpdesk.domain.Pessoa;
import com.jhonatan.helpdesk.domain.dtos.ClienteDTO;
import com.jhonatan.helpdesk.repositories.ClienteRepository;
import com.jhonatan.helpdesk.repositories.PessoaRepository;
import com.jhonatan.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jhonatan.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente de Id: " + id + " não encontrado!"));
    }

    public Cliente create(ClienteDTO objDto){
        objDto.setId(null);
        validaCPFEEmail(objDto);
        Cliente newObj = new Cliente(objDto);
        return clienteRepository.save(newObj);
    }

    public Cliente update(Long id, ClienteDTO objDto){
        objDto.setId(id);
        Cliente newObj = findById(id);
        newObj = new Cliente(objDto);
        return clienteRepository.save(newObj);
    }


    private void validaCPFEEmail(ClienteDTO objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("Cpf " + objDto.getCpf() + " já cadastrado!");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());

        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("E-mail " + objDto.getEmail() + " já cadastrado!");
        }
    }
}
