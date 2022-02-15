package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class IndexControl {
    @GetMapping("/")
    public String index(Model model) {
        List<String> listUsers = Arrays.asList("Petr Arsentev", "Roman Korolchuk", "Ivan Ivanov", "Alex Sidorov");
        model.addAttribute("listUsers", listUsers);
        return "index";
    }
}