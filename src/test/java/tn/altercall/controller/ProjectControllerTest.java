package tn.altercall.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import tn.altercall.entities.Project;
import tn.altercall.web.ProjetController;
import tn.altercall.services.ProjectService;

@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest {
    
   // unit test
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProjectService projetService;
    private Project projet;
    private List<Project> projetList;
    
    @InjectMocks
    private ProjetController projetController;
    
    @BeforeEach
    public void setup() {
        projet = new Project(785L, "project test title", 50, "project test description" );
        mockMvc = MockMvcBuilders.standaloneSetup(projetController).build();
    }

    @AfterEach
    void tearDown() {
        projet = null;
    }
    
    // Test Case to Post a Product
//    @Test
//    public void PostMappingOfProject() throws Exception {
//        when(projetService.addProjet(any())).thenReturn(projet);
//        mockMvc.perform(post("/api/projectstest/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(projet)))
//                .andExpect(status().isCreated());
//        verify(projetService, times(1)).addProjet(any());
//    }

    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}