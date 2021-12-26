package com.wuziqi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.wuziqi.model.ChessRecord;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenTests {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    private MockMvc mockMvc;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Test
    public void test(){
        log.info(passwordEncoder.encode("admin"));
        log.info(passwordEncoder.encode("admin"));
        log.info(passwordEncoder.encode("admin"));
    }

    @Test
    public void testLogin() throws Exception {
        Map map = new HashMap<>();
        map.put("username","admin");
        map.put("password","admin");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(map);
        this.mockMvc.perform(post("/api/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson)).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void testWithToken() throws Exception {
//        Map map = new HashMap<>();
//        map.put("username","admin");
//        map.put("password","admin");
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
//        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
//        String requestJson = ow.writeValueAsString(map);
        this.mockMvc.perform(post("/api/test")
                        .header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYzOTA5ODgwNiwiaWF0IjoxNjM5MDEyNDA2fQ.u3Zp-tKjCMbkvX7ps8nKfND9Je_hd_7ZqMhuOBJMtq-h2j3TZHDo1Q-fOdwuU7N_OQukws83JUdenqOm_oDjGw")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestJson)
        ).andDo(print()).andExpect(status().isOk());
    }
}
