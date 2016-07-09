package student.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Zheng on 16/7/9.
 */
@Controller
@RequestMapping("/logout")
public class LogoutController {
    @RequestMapping(method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("loginUsername");
        return "redirect:/admin";
    }
}
