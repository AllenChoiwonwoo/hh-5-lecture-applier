package com.hh99.hh5cleanarchitecture.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hh99.hh5cleanarchitecture.entity.UserApplication;
import com.hh99.hh5cleanarchitecture.service.LectureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = LectureController.class)
@ExtendWith(SpringExtension.class)
class LectureControllerTest {

    @MockBean
    private LectureService lectureService;

    ObjectMapper mapper;
    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    void setUp(){
        mapper = new ObjectMapper();
    }

    @Test
    void apply() throws Exception {
        //given
        ApplyRequest applyRequest = ApplyRequest.builder().userId(1l).sessionId(1l).lectureId(1l).build();
        UserApplication ua = UserApplication.builder().applyId(1l).userId(1l).lectureId(1l).sessionId(1l).build();
        given(lectureService.apply(applyRequest)).willReturn(ua);
        //when
        ResultActions resultActions = mockMvc.perform(post("/lectures/apply")
                .content(mapper.writeValueAsString(applyRequest))
                .contentType(MediaType.APPLICATION_JSON)
        );
        //then
        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        ApplyResponse result = mapper.readValue(contentAsString, ApplyResponse.class);
        assertEquals(1l, result.getApplyId());
    }

    @Test
    void t2() throws JsonProcessingException {
        ApplyRequest applyRequest = ApplyRequest.builder().userId(1l).sessionId(1l).lectureId(1l).build();
        System.out.println(mapper.writeValueAsString(applyRequest));
    }
}