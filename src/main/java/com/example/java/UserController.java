package com.example.java;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.java.model.User;
import com.example.java.repository.UserRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepo;

    /**
     * @param locale
     * @return
     */

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/manager")
    public String manager(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("username") == null) {
            return "redirect:/";
        }
        User user = userRepo.findUserByUserName(session.getAttribute("username").toString());
        if (user.getRole().equalsIgnoreCase("MANAGER")) {
            return "manager";
        } else {
            modelMap.put("error", "Invalid Account");
            return "redirect:/";
        }
    }

    @RequestMapping("/login")
    public String login(@RequestParam("username") String loginUserName,
            @RequestParam("password") String loginUserPasword,
            HttpSession session, ModelMap modelMap) {
        User user = userRepo.findUserByUserName(loginUserName);
        if (user != null) {
            String username = user.getUserName();
            String password = user.getPassword();
            if (username.equalsIgnoreCase(loginUserName) &&
                    password.equalsIgnoreCase(loginUserPasword)) {
                session.setAttribute("username", username);
                modelMap.put("user", user);
                modelMap.put("success", "success");
                return home(session, modelMap);
            } else {
                modelMap.put("error", "Invalid Account");
                return "index";
            }
        } else {
            modelMap.put("error", "Invalid Account");
            return "index";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session, ModelMap modelMap) {
        session.removeAttribute("username");
        session.invalidate();
        modelMap.put("logout", "logout");
        return "redirect:/";
    }

    @RequestMapping("/home")
    public String home(HttpSession session, ModelMap modelMap) {
        if (session.getAttribute("username") == null) {
            return "redirect:/";
        }
        String userName = session.getAttribute("username").toString();
        User user = userRepo.findUserByUserName(userName);
        modelMap.put("user", user);
        return "home";
    }

}
