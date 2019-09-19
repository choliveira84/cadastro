package br.com.cliente.cadastro;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void methodNotAllowedById_thenStatus405() throws Exception {
		this.mockMvc.perform(get("/clientes/id/")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void methodNotAllowedByNome_thenStatus405() throws Exception {
		this.mockMvc.perform(get("/clientes/nome/")).andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void findClientesById_thenStatus404() throws Exception {
		this.mockMvc.perform(get("/clientes/id/1")).andExpect(status().isNotFound());
	}

	@Test
	public void findClientesByNome_thenStatus404() throws Exception {
		this.mockMvc.perform(get("/clientes/nome/cliente")).andExpect(status().isNotFound());
	}

}
