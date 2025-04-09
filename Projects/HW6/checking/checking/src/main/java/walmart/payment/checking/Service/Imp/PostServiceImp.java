package walmart.payment.checking.Service.Imp;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import walmart.payment.checking.ExceptionHandling.PostNotFoundException;
import walmart.payment.checking.Service.PostService;
import walmart.payment.checking.dao.PostRepository;
import walmart.payment.checking.dto.PostDto;
import walmart.payment.checking.entity.Post;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImp implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        //save the post into the db
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savedPost = postRepository.save(post);
        
        return mapToDto(savedPost);
    }

  private Post mapToPost(PostDto postDto) {
      Post post = new Post();
      post.setId(postDto.getId());
      post.setTitle(postDto.getTitle());
      post.setContent(postDto.getContent());
      post.setDescription(postDto.getDescription());
      return post;
  }

  private PostDto mapToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        postDto.setId(post.getId());

        return postDto;
  }
    @Override
    public List<PostDto> getPost() {
     List<Post> posts = postRepository.findAll();
     return posts.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDto getPost(long id) {
        Post post = postRepository.findById(id).orElse(null);
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto) {
        //1) find the existing post
        Post post = postRepository.findById(postDto.getId()).orElse(null);
        //2) update
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        post.setId(postDto.getId());
        //3) save
        Post savedPost = postRepository.save(post);
        return mapToDto(savedPost);
    }

    @Override
    public PostDto deletePost(long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with ID " + id + " not found."));
        postRepository.delete(post);  // Delete the post from the database
        return mapToDto(post);
    }
      // Delete the post from the database
}