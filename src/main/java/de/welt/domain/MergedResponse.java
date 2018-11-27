package de.welt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class MergedResponse {
    private User user;
    private List<Post> posts;
}
