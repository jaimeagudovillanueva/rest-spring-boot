package es.rest.controller;

import static es.rest.repository.PersonaRepository.cumpleFiltro;
import static es.rest.repository.PersonaRepository.idMenor;

import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.data.jpa.domain.Specifications;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.rest.entity.Persona;
import es.rest.exception.NotFoundException;
import es.rest.hateoas.PersonaResource;
import es.rest.repository.PersonaRepository;

/**
 * Servicios REST con HATEHOAS
 *
 * @author jaime.agudo
 *
 */
@RestController
// Si ponemos el MediaType en la cabecera no hace falta ponerlo en cada método,
// ya que todos devuelven el mismo tipo
@RequestMapping(value = "/personas", produces = { MediaType.APPLICATION_JSON_VALUE, "application/hal+json" })
public class PersonaRestController {

	@Resource
	PersonaRepository personaRepository;

	@RequestMapping(method = RequestMethod.GET)
	public Resources<PersonaResource> obtenerPersonas() {
		return new Resources<>(personaRepository.findAll().stream().filter(persona -> persona.getNombre().length() < 5)
				.map(PersonaResource::new).collect(Collectors.toList()));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{idPersona}")
	public PersonaResource obtenerPersona(@PathVariable final Long idPersona) throws NotFoundException {
		final Persona persona = personaRepository.findOne(idPersona);
		if (persona == null) {
			throw new NotFoundException();
		}
		return new PersonaResource(persona);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/nifoculto")
	public Resources<PersonaResource> obtenerPersonasSinNif() {
		return new Resources<>(personaRepository.findAll().stream().map(persona -> {
			persona.setNif("#########");
			return new PersonaResource(persona);
		}).collect(Collectors.toList()));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/personasFiltradas")
	public Resources<PersonaResource> obtenerPersonasFiltradas(@RequestParam(value = "apellido") final String apellido,
			@RequestParam(value = "id") final Long id) {

		return new Resources<>(personaRepository.findAll(Specifications.where(cumpleFiltro(apellido)).and(idMenor(id)))
				.stream().map(PersonaResource::new).collect(Collectors.toList()));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{idPersona}/nifoculto")
	public PersonaResource obtenerPersonaSinNif(@PathVariable final Long idPersona) throws NotFoundException {
		final Persona persona = personaRepository.findOne(idPersona);
		if (persona != null) {
			persona.setNif("#########");
		} else {
			throw new NotFoundException();
		}
		return new PersonaResource(persona);
	}
}
