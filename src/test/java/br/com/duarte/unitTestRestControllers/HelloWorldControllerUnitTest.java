package br.com.duarte.unitTestRestControllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class HelloWorldControllerUnitTest extends AbstractControllerTest {

    @BeforeAll
    static void BeforeAll() {
        controller = new HelloWorldController();
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void deveRetornarHttpStatus200() throws Exception {
        getAndExpectedStatusOk("/http/200");
    }

    @Test
    public void deveRetornarHttpStatus404() throws Exception {
        getAndExpectedStatusNotFound("/http/404");
    }

    @Test
    public void deveRetornarHttpStatus201() throws Exception {
        postAndExpectedStatusCreated("/http/201", getDTO());
    }

    @Test
    public void deveRetornarHttpStatus400() throws Exception {
        postAndExpectedStatusBadRequest("/http/400", getDTO());
    }

    public TesteDTO getDTO() {
        TesteDTO dto = new TesteDTO();
        dto.id = 1;
        dto.nome = "Teste";
        return dto;
    }
}