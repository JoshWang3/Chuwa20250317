package walmart.payment.checking.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Component;
import walmart.payment.checking.Service.PostService;
import walmart.payment.checking.dto.PostDto;

import java.util.List;

@Component
public class PostQueryResolver {

    private final PostService postService;

    @Autowired
    public PostQueryResolver(PostService postService) {
        this.postService = postService;
    }

    @QueryMapping
    public List<PostDto> getAllPosts() {
        return postService.getPost();
    }

    @QueryMapping
    public PostDto getPostById(Long id) {
        return postService.getPost(id);
    }
}
