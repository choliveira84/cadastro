package br.com.cliente.cadastro;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import br.com.cliente.cadastro.controllers.cidade.CidadePostDTO;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	// #region Clientes
	@Test
	public void methodNotAllowedById_thenStatus405() throws Exception {
		this.mockMvc.perform(get("/clientes/id/")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void methodNotAllowedPost_thenStatus400() throws Exception {
		this.mockMvc.perform(post("/clientes/")).andExpect(status().isBadRequest());
	}

	@Test
	public void methodNotAllowedByNome_thenStatus405() throws Exception {
		this.mockMvc.perform(get("/clientes/nome/")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void methodNotAllowedDelete_thenStatus405() throws Exception {
		this.mockMvc.perform(delete("/clientes/")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void methodNotAllowedPatch_thenStatus405() throws Exception {
		this.mockMvc.perform(patch("/clientes/")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void findClienteById_thenStatus404() throws Exception {
		this.mockMvc.perform(get("/clientes/id/1")).andExpect(status().isNotFound());
	}

	@Test
	public void findClienteByNome_thenStatus404() throws Exception {
		this.mockMvc.perform(get("/clientes/nome/cliente")).andExpect(status().isNotFound());
	}

	@Test
	public void deleteClienteById_thenStatus404() throws Exception {
		this.mockMvc.perform(delete("/clientes/1")).andExpect(status().isNotFound());
	}

	// #endregion

	// #region Cidades
	@Test
	public void findCidadeyNome_thenStatus404() throws Exception {
		this.mockMvc.perform(get("/cidades/nome")).andExpect(status().isNotFound());
	}

	@Test
	public void findCidadeyEstado_thenStatus404() throws Exception {
		this.mockMvc.perform(get("/cidades/estado/pe")).andExpect(status().isNotFound());
	}

	@Test
	public void saveCidade_thenStatus404() throws Exception {
		// O estado não existe, logo, deve retornar um 404
		String json = new ObjectMapper().writeValueAsString(new CidadePostDTO("Recife", "XZ"));

		this.mockMvc.perform(post("/cidades").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isNotFound());
	}

	@Test
	public void saveCidade_thenStatus201() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new CidadePostDTO("Recife", "PE"));

		this.mockMvc.perform(post("/cidades").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}

	@Test
	public void findCidadeByNome_thenStatus200() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new CidadePostDTO("Recife", "PE"));

		this.mockMvc.perform(post("/cidades").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		this.mockMvc.perform(get("/cidades/recife")).andExpect(status().isOk());
	}

	@Test
	public void findCidadeByEstado_thenStatus200() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new CidadePostDTO("Recife", "PE"));

		this.mockMvc.perform(post("/cidades").content(json).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());

		this.mockMvc.perform(get("/cidades/estado/pe")).andExpect(status().isOk());
	}

	// #endregion
}
