package es.rest.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.test.context.junit4.SpringRunner;

import es.rest.ServicesApplication;
import es.rest.entity.Persona;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServicesApplication.class)
public class PersonaRepositoryTest {

	@Resource
	PersonaRepository personaRepository;

	@Test
	public void contextLoads() {
	}

	@Ignore
	@Test
	public void testPersonalFindAll() {
		final List<Persona> personas = personaRepository.findAll();
		assertEquals(personas.size(), 199139);
	}

	@Test
	public void testPersonaFindOne() {
		final Persona persona = personaRepository.findOne(195L);
		assertEquals(persona.getNif(), "00716189");
	}

	@Test
	public void testFindPersonaNombreApellido() {
		final List<Persona> personas = personaRepository
				.findByNombreAndPrimerApellidoOrderByPrimerApellidoAsc("ARACELI", "GARCIA");
		assertEquals(personas.get(0).getNif(), "00716189");
	}

	@Test
	public void testFindLikeNombreApellido() {
		final List<Persona> personas = personaRepository.findLikeNombreApellido("GARC");
		System.out.println(personas.size());
	}

	@Test
	public void testFindAllFiltro() {
		final List<Persona> personas = personaRepository.findAll(PersonaRepository.cumpleFiltro("GARC"));
		System.out.println(personas.size());
	}

	@Test
	public void testFindAllIdMenor() {
		final List<Persona> personas = personaRepository.findAll(PersonaRepository.idMenor(200L));
		System.out.println(personas.size());
	}

	@Test
	public void testFindAllDoubleSpecification() {
		final List<Persona> personas = personaRepository.findAll(
				Specifications.where(PersonaRepository.idMenor(200L)).and(PersonaRepository.cumpleFiltro("GARC")));
		System.out.println(personas.size());
	}
}