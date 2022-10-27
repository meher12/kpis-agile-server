package tn.altercall.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import tn.altercall.entities.Project;
import tn.altercall.web.ProjetController;
import tn.altercall.services.ProjectService;

//@ExtendWith(MockitoExtension.class)
public class ProjectControllerTest extends AbstractTest{


    @Override
    @Before
    public void setUp() {
        super.setUp();
    }
    @Test
    public void getPrjectList() throws Exception {
        String uri = "/projectsList";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Project[] productlist = super.mapFromJson(content, Project[].class);
        assertTrue(productlist.length > 0);
    }

    @Test
    public void createProduct() throws Exception {
        String uri = "/products";
        Project project = new Project();
        project.setTitre("Project kpis");
        String inputJson = super.mapToJson(project);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "project is created successfully");
    }

    @Test
    public void updateProduct() throws Exception {
        String uri = "/products/2";
        Project project = new Project();
        project.setTitre("Lemon kpis");
        String inputJson = super.mapToJson(project);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Product is updated successsfully");
    }

    @Test
    public void deleteProduct() throws Exception {
        String uri = "/projects/2";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Projects is deleted successsfully");
    }


    
   // unit test
   /* @Autowired
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
    }*/
    
    // Test Case to Post a Product
//    @Test
//    public void PostMappingOfProject() throws Exception {
//        when(projetService.addProjet(any())).thenReturn(projet);
//        mockMvc.perform(post("/api/projectstest/").contentType(MediaType.APPLICATION_JSON).content(asJsonString(projet)))
//                .andExpect(status().isCreated());
//        verify(projetService, times(1)).addProjet(any());
//    }

    
  /*  public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/

}
