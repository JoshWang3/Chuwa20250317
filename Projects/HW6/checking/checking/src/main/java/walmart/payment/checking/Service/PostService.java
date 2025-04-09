package walmart.payment.checking.Service;

import walmart.payment.checking.dto.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);
    List<PostDto> getPost();
    PostDto getPost(long id);
    PostDto updatePost(PostDto postDto);
    PostDto deletePost(long id);

}
