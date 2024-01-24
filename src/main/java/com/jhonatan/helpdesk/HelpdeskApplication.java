package com.jhonatan.helpdesk;

import com.jhonatan.helpdesk.domain.Chamado;
import com.jhonatan.helpdesk.domain.Cliente;
import com.jhonatan.helpdesk.domain.Tecnico;
import com.jhonatan.helpdesk.domain.enums.Perfil;
import com.jhonatan.helpdesk.domain.enums.Prioridade;
import com.jhonatan.helpdesk.domain.enums.Status;
import com.jhonatan.helpdesk.repositories.ChamadoRepository;
import com.jhonatan.helpdesk.repositories.ClienteRepository;
import com.jhonatan.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {

	@Autowired
	TecnicoRepository tecnicoRepository;

	@Autowired
	ClienteRepository clienteRepository;

	@Autowired
	ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico tec1 = new Tecnico(null, "Jhonatan Diniz", "738.156.401-72", "jhonatan@gmail.com", "123");
		tec1.addPerfil(Perfil.TECNICO);

		tecnicoRepository.saveAll(Arrays.asList(tec1));

		Cliente cli1 = new Cliente(null, "Scheila Nunes", "809.380.510-91", "scheila@gmail.com", "123");
		cli1.addPerfil(Perfil.CLIENTE);

		clienteRepository.saveAll(Arrays.asList(cli1));

		Chamado cha1 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 01", "Chamado de número 01", tec1, cli1);

		chamadoRepository.saveAll(Arrays.asList(cha1));
	}
}
