package com.example.tokyoAnonSpace.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/free")
public class FreeController {

    @GetMapping("/list")
    public String listPage() {
        return "free/list";
    }
}
