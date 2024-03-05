package com.formation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.formation.entity.Student;
import com.formation.exception.StudentNotFoundException;
import com.formation.service.StudentService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.BDDMockito.given; // Import the necessary class

@WebMvcTest
public class StudentControllerTest {

    @MockBean
    private StudentService studentService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getStudent_ForSavedStudent_Returned() throws Exception {

        // given
        given(studentService.getStudentById(anyLong())).willReturn(
                Student.builder()
                        .id(1l)
                        .name("Mark")
                        .grade(10)
                        .build());

        // when then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1l))
                .andExpect(jsonPath("name").value("Mark"))
                .andExpect(jsonPath("grade").value(10));
    }

    @Test
    void getStudent_ForMissingStudent_status404() throws Exception {
        // given
        given(studentService.getStudentById(anyLong())).willThrow(StudentNotFoundException.class);

        // when then
        mockMvc.perform(get("/students/1"))
                .andExpect(status().isNotFound());

    }
}
