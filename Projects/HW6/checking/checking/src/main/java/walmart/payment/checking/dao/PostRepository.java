package walmart.payment.checking.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import walmart.payment.checking.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    //SELECT * FROM post where id=3;
    //we don't need to write any sql;
    //SQL-- build inside JpaRepository;

    //Hibernate can detect parameters and auto generate findBy
   Post findByTitle(String title);
   Post findByTitleIgnoreCase(String title);
   Post findByTitleAndDescriptionIgnoreCase(String title, String description);
}
