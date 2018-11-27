package de.welt;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.welt.domain.Post;
import de.welt.domain.User;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(new URL("https://jsonplaceholder.typicode.com/users/1"), User.class);

        List<Post> posts = mapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts?userId=1"), new TypeReference<List<Post>>(){});
        System.out.println(user);
        System.out.println(posts);
    }
}
