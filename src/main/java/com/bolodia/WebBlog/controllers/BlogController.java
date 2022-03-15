package com.bolodia.WebBlog.controllers;


import com.bolodia.WebBlog.models.Post;
import com.bolodia.WebBlog.repo.PostInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {


    @Autowired
    private PostInterface postInterface;


    @GetMapping("/blog")
    public String blogMain(Model model) {


        Iterable < Post > posts = postInterface.findAll ( );

        model.addAttribute ("posts", posts);
        return "blogMain";

    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        Iterable < Post > posts = postInterface.findAll ( );

        model.addAttribute ("posts", posts);
        return "blogAdd";

    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title, @RequestParam String author, @RequestParam String shortText, @RequestParam String fullText, Model model) {
        Post post = new Post (title, author, shortText, fullText);
        postInterface.save (post);
        return "redirect:/blog";

    }

    @GetMapping("/blog/{id}")
    public String blogFullText(@PathVariable(value = "id") long id, Model model) {

        if (postInterface.existsById (id)) {

            Optional < Post > post = postInterface.findById (id);
            ArrayList < Post > res = new ArrayList <> ( );
            post.ifPresent (res::add);
            model.addAttribute ("posts", res);
            return "blogDetails";
        }

        return "redirect:/blog";

    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {

        if (postInterface.existsById (id)) {

            Optional < Post > post = postInterface.findById (id);
            ArrayList < Post > res = new ArrayList <> ( );
            post.ifPresent (res::add);
            model.addAttribute ("posts", res);
            return "blogEdit";
        }

        return "redirect:/blog";


    }


    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@PathVariable(value = "id") long id, @RequestParam String title, @RequestParam String author, @RequestParam String shortText, @RequestParam String fullText, Model model) {
        Post post = postInterface.findById (id)
                .orElseThrow ( );

        post.setTitle (title);
        post.setAuthor (author);
        post.setShortText (shortText);
        post.setFullText (fullText);

        postInterface.save (post);

        return "redirect:/blog";

    }


    @PostMapping("/blog/{id}/remove")
    public String blogPostRemove(@PathVariable(value = "id") long id, Model model) {
        Post post = postInterface.findById (id)
                .orElseThrow ( );
       postInterface.deleteById (id);

        return "redirect:/blog";

    }

}
