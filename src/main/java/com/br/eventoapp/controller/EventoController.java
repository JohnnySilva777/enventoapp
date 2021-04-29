package com.br.eventoapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.br.eventoapp.models.Convidado;
import com.br.eventoapp.models.Evento;
import com.br.eventoapp.repository.ConvidadoRepository;
import com.br.eventoapp.repository.EventoRepository;

@Controller
public class EventoController {

	@Autowired
	private EventoRepository er;
	@Autowired
	private ConvidadoRepository cr;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return "redirect:/eventos";
	}

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String form(Evento evento) {
		er.save(evento);
		return "redirect:/cadastrarEvento";
	}

	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Evento> eventos = er.findAll();
		mv.addObject("eventos", eventos);
		return mv;
	}
	
	@RequestMapping(value = "/detalhesEvento/{id}",  method = RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		Evento evento = er.findById(id);
		mv.addObject("evento", evento);
		return mv;
	}
	
	@RequestMapping(value = "/detalhesEvento/{id}",  method = RequestMethod.POST)
	public String detalhesEvento(@PathVariable("id") long id, Convidado convidado) {
		Evento evento = er.findById(id);
		convidado.setEvento(evento);
		cr.save(convidado);
		return "redirect:/detalhesEvento/{id}";
	}
}
