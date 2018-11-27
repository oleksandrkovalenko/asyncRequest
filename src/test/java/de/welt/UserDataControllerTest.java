package de.welt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.welt.controller.UserDataController;
import de.welt.domain.MergedResponse;
import de.welt.domain.Post;
import de.welt.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class UserDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testReport() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user-data/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();

        ObjectMapper objectMapper = new ObjectMapper();
        MergedResponse mergedResponse = objectMapper.readValue(contentAsString, MergedResponse.class);

        String userFile = UserDataControllerTest.class.getClassLoader().getResource("user.json").getFile();
        String postsFile = UserDataControllerTest.class.getClassLoader().getResource("posts.json").getFile();
        User user = objectMapper.readValue(new File(userFile), User.class);
        List<Post> posts = objectMapper.readValue(new File(postsFile), new TypeReference<List<Post>>(){});


        assertThat(mergedResponse.getUser(), is(user));
        assertThat(mergedResponse.getPosts(), is(posts));

    }

}
