package com.wuziqi;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wuziqi.model.ChessRecord;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class WuZiQiApplicationTests {

    @Resource
    private MockMvc mockMvc;

    @Test
    public void games() throws Exception {
//        createGame();
        this.mockMvc.perform(get("/games")).andDo(print()).andExpect(status().isOk())
                .andExpect((jsonPath("$.games").exists()));
    }

    @Test
    public void createGame() throws Exception{
        this.mockMvc.perform(post("/games")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId").exists());
    }

    @Test
    public void getDetail() throws Exception{
        createGame();
        this.mockMvc.perform(get("/games/1")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.gameId").exists());
    }

    @Test
    public void positions() throws Exception{
        createGame();
        ChessRecord record = new ChessRecord();
        record.setX(10);
        record.setY(10);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(record);
        this.mockMvc.perform(post("/games/1/positions").contentType(MediaType.APPLICATION_JSON).content(requestJson)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.complete").exists());
    }
}
