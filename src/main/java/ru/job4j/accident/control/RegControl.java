package ru.job4j.accident.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accident.model.User;
import ru.job4j.accident.service.RegService;

@Controller
public class RegControl {

    private final RegService regService;

    public RegControl(RegService regService) {
        this.regService = regService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        if (regService.checkUserName(user)) {
            user.setEnabled(true);
            user.setPassword(regService.getPassword(user));
            user.setAuthority(regService.getAuthority("ROLE_USER"));
            regService.save(user);
            return "redirect:/login";
        } else {
            model.addAttribute("errorMessage", "User with this name exists");
            return "reg";
        }
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}
