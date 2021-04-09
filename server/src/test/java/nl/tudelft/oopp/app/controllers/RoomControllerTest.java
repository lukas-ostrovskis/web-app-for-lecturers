package nl.tudelft.oopp.app.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import nl.tudelft.oopp.app.entities.User;
import nl.tudelft.oopp.app.repositories.RoomRepository;
import nl.tudelft.oopp.app.services.RoomService;
import nl.tudelft.oopp.app.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RoomControllerTest {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomService roomService;
    @Autowired
    private static UserService userService;
    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {

    }

    @Test
    @Order(1)
    void createRoom() throws Exception {
        userService.save(new User("Ivan", "ivan@email.com", "student", "0.0.0.0"),
            "IvanPassword");
        mockMvc.perform(MockMvcRequestBuilders
            .get("/room/create?userId=1&password=12345")
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    @Order(2)
    void joinRoom() {
    }

    @Test
    void deleteRoom() {
    }

    @Test
    void archiveRoom() {

    }
}