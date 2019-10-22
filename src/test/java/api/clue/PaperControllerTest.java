package api.clue;

import api.clue.web.PaperController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @RunWith(SpringRunner.class)
// @SpringBootTest
public class PaperControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    PaperController target;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    // @Test
    // public void getPaper() throws Exception {
    //     this.mockMvc.perform(MockMvcRequestBuilders.get("/papers/1"))
    //             .andExpect(status().isOk());
    // }
}

