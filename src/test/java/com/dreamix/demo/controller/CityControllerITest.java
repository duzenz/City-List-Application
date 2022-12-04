package com.dreamix.demo.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getCityWithId() throws Exception {
        this.mockMvc.perform(get("/api/cities/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tokyo"))
                .andExpect(jsonPath("$.photo").value("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg/500px-Skyscrapers_of_Shinjuku_2009_January.jpg"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getCityWithNotExistingId() throws Exception {
        this.mockMvc.perform(get("/api/cities/9999"))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getCityByName() throws Exception {
        this.mockMvc.perform(get("/api/cities/name/Tokyo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Tokyo"))
                .andExpect(jsonPath("$.photo").value("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg/500px-Skyscrapers_of_Shinjuku_2009_January.jpg"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getCityByNotExistingName() throws Exception {
        this.mockMvc.perform(get("/api/cities/name/NotExistingCity"))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getCities() throws Exception {
        this.mockMvc.perform(get("/api/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(50)))
                .andExpect(jsonPath("$.content[0].id").value(1))
                .andExpect(jsonPath("$.content[0].name").value("Tokyo"))
                .andExpect(jsonPath("$.content[0].photo").value("https://upload.wikimedia.org/wikipedia/commons/thumb/b/b2/Skyscrapers_of_Shinjuku_2009_January.jpg/500px-Skyscrapers_of_Shinjuku_2009_January.jpg"))
                .andExpect(jsonPath("$.content[1].id").value(2))
                .andExpect(jsonPath("$.content[1].name").value("Jakarta"))
                .andExpect(jsonPath("$.content[1].photo").value("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f6/Jakarta_Pictures-1.jpg/327px-Jakarta_Pictures-1.jpg"))
                .andExpect(jsonPath("$.content[2].id").value(3))
                .andExpect(jsonPath("$.content[2].name").value("Delhi"))
                .andExpect(jsonPath("$.content[2].photo").value("https://upload.wikimedia.org/wikipedia/commons/thumb/5/55/IN-DL.svg/439px-IN-DL.svg.png"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updateCity() throws Exception {
        this.mockMvc.perform(put("/api/cities/100")
                        .contentType("application/json")
                        .content("{\"name\":\"NewName\",\"photo\":\"NewPhoto\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(100))
                .andExpect(jsonPath("$.name").value("NewName"))
                .andExpect(jsonPath("$.photo").value("NewPhoto"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void updateCityWithNotExistingId() throws Exception {
        this.mockMvc.perform(put("/api/cities/9999")
                        .contentType("application/json")
                        .content("{\"name\":\"NewName\",\"photo\":\"NewPhoto\"}"))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
