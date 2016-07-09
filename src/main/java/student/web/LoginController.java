package student.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import student.model.LoginStatus;
import student.repository.AdministratorRepository;

import javax.servlet.http.HttpSession;

/**
 * Created by Zheng on 16/7/8.
 */
@Controller
@RequestMapping("/admin")
public class LoginController {
    private AdministratorRepository administratorRepository;

    @Autowired
    public LoginController(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String login(HttpSession session) {
        String loginUsername = (String) session.getAttribute("loginUsername");
        if (loginUsername != null) {
            return "redirect:/operate";
        } else {
            return "login";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    LoginStatus processLogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpSession session) {
        LoginStatus loginStatus = new LoginStatus();
        try {
            Boolean result = administratorRepository.checkPassword(username, password);
            if (result) {
                loginStatus.setStatus(0);
                session.setAttribute("loginUsername", username);
            } else {
                loginStatus.setStatus(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            loginStatus.setStatus(1);
        }
        return loginStatus;
    }
}
