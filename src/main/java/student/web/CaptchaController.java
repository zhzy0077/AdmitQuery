package student.web;

import com.github.cage.Cage;
import com.github.cage.GCage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by Zheng on 16/7/7.
 */
@Controller
@RequestMapping(value = "captcha.jpg")
public class CaptchaController {
    @RequestMapping(method = RequestMethod.GET)
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        Cage cage = new GCage();
        Random random = new Random();
        String token = String.valueOf(Math.abs(random.nextInt()) % 9000 + 1000);
        request.getSession().setAttribute("token", token);
        try (OutputStream out = response.getOutputStream()) {
            cage.draw(token, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
