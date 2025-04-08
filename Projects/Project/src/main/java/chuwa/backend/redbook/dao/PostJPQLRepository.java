package chuwa.backend.redbook.dao;

import chuwa.backend.redbook.entity.Post;

import java.util.List;

public interface PostJPQLRepository {
    List<Post> getAllPostWithJPQL();
}
