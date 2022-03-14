package com.bolodia.WebBlog.repo;

import com.bolodia.WebBlog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostInterface extends CrudRepository< Post, Long  > {



}
