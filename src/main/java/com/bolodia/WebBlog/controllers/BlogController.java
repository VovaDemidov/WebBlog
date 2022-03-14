package com.bolodia.WebBlog.controllers;


import com.bolodia.WebBlog.models.Post;
import com.bolodia.WebBlog.repo.PostInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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


}
