package com.jhonatan.helpdesk.services;

import com.jhonatan.helpdesk.domain.Pessoa;
import com.jhonatan.helpdesk.domain.Tecnico;
import com.jhonatan.helpdesk.domain.dtos.TecnicoDTO;
import com.jhonatan.helpdesk.repositories.PessoaRepository;
import com.jhonatan.helpdesk.repositories.TecnicoRepository;
import com.jhonatan.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.jhonatan.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Long id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado id: " + id));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDTO objDto) {
        objDto.setId(null);
        validaCpfEEmail(objDto);
        Tecnico newObj = new Tecnico(objDto);
        return tecnicoRepository.save(newObj);
    }

    public Tecnico update(Long id, TecnicoDTO objDto){
        objDto.setId(id);
        Tecnico obj = findById(id);
        obj = new Tecnico(objDto);
        return tecnicoRepository.save(obj);
    }
    private void validaCpfEEmail(TecnicoDTO objDto) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("Cpf " + objDto.getCpf() + " já cadastrado!");
        }

        obj = pessoaRepository.findByEmail(objDto.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("Email " + objDto.getEmail() + " já cadastrado!");
        }
    }
}
