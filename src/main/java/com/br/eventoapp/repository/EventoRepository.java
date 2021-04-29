package com.br.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.br.eventoapp.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, String> {
	Evento findById(long id);
}
