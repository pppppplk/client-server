package project.spring.controllers;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import project.spring.repo.ClientRepo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerTest {

    @Autowired
    public MockMvc mvc;



    @Autowired
    public ClientRepo clientRepo;

    /**
     * Проверка Get-запроса, размер массива клиента
     */

    @Test
    public void getClients(){
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/clients/all"))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful())
                    .andExpect(mvcResult -> {
                        String body = mvcResult.getResponse().getContentAsString();
                        JSONArray jsonArray = new JSONArray(body);
                        assertEquals(clientRepo.findAll().size(), jsonArray.length());

                    })
                    .andReturn();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Проверка Get-запроса, поиск клиента по id
     */

    @Test
    public void getClient(){
        Long ClientId = Long.valueOf(1);
        try {
            this.mvc.perform(MockMvcRequestBuilders.get("http://localhost:8080/api/theater/clients/"+ClientId))
                    .andDo(MockMvcResultHandlers.print())
                    .andExpect(status().is2xxSuccessful()).andExpect(mvcResult -> {
                String body = mvcResult.getResponse().getContentAsString();
                JSONArray jsonArray = new JSONArray(body);
                assertEquals(clientRepo.findClientById(ClientId), jsonArray.getLong(Integer.parseInt("id")));
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }









}
