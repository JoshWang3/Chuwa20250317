package chuwa.backend.redbookcopy.Service.Impl;

import chuwa.backend.redbookcopy.Dao.PostRepository;
import chuwa.backend.redbookcopy.Dto.PostDto;
import chuwa.backend.redbookcopy.Dto.PostResponse;
import chuwa.backend.redbookcopy.Entity.Post;
import chuwa.backend.redbookcopy.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = DtoToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return entityToDto(savedPost);
    }

    @Override
    public PostDto getPost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        return entityToDto(post);
    }

    @Override
    public PostDto updatePost(Long id, PostDto postDto) {
        Post post = postRepository.findById(id).orElse(null);
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savedPost = postRepository.save(post);
        return entityToDto(savedPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElse(null);
        postRepository.delete(post);
    }

    @Override
    public PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, sort);
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
//        PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        Page<Post> pagePosts = postRepository.findAll(pageRequest);

        List<Post> posts = pagePosts.getContent();
        List<PostDto> postDtos = posts.stream().map(this::entityToDto).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(postDtos);
        postResponse.setPageNo(pagePosts.getNumber());
        postResponse.setPageSize(pagePosts.getSize());
        postResponse.setTotalElements(pagePosts.getTotalElements());
        postResponse.setTotalPages(pagePosts.getTotalPages());
        postResponse.setLast(pagePosts.isLast());
        return postResponse;
    }

    private Post DtoToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
    }

    private PostDto entityToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setDescription(post.getDescription());
        postDto.setContent(post.getContent());
        return postDto;
    }
}
