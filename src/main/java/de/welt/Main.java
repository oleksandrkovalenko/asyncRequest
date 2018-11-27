package de.welt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.welt.domain.MergedResponse;
import de.welt.domain.Post;
import de.welt.domain.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {

    private static final String DOMAIN = "https://jsonplaceholder.typicode.com";

    private ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        String userId = "1";

        Main main = new Main();
        main.requestForUser(userId);

    }

    private void requestForUser(String userId) {
        try {
            requestForUserWithThrow(userId);
        } catch (IOException e) {
            //TODO: Add logging here
        }
    }

    private void requestForUserWithThrow(String userId) throws IOException {
        User user = mapper.readValue(new URL(DOMAIN + "/users/" + userId), User.class);
        List<Post> posts = mapper.readValue(new URL(DOMAIN + "/posts?userId=" + userId), new TypeReference<List<Post>>(){});

        MergedResponse mergedResponse = new MergedResponse(user, posts);
        String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedResponse);

        System.out.println(response);
    }
}
