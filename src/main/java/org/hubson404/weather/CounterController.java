package org.hubson404.weather;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

@RestController
public class CounterController {

    @GetMapping("/counter")
    Map<Object, Object> getCounter(HttpServletRequest request, HttpServletResponse response) {
//        request.getHeaderNames().asIterator().forEachRemaining(System.out::println);
//        response.setHeader("header-from-the-server","value");
        int counter = 0;

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            counter = 1;
            Cookie cookie = new Cookie("counter", String.valueOf(counter));
            response.addCookie(cookie);
            return Map.of("counter", counter);
        }

        Cookie cookie = Arrays.stream(cookies)
                .filter(c -> c.getName().equals("counter"))
                .findFirst()
                .orElseGet(() -> new Cookie("counter", "0"));

        String stringValue = cookie.getValue();
        counter = Integer.parseInt(stringValue);

        counter++;
        cookie.setValue(String.valueOf(counter));
        cookie.setMaxAge(5);

        response.addCookie(cookie);

        return Map.of("counter", counter);
    }

    @GetMapping("/counter/session")
    Map<Object, Object> getSessionCounter(HttpSession httpSession) {

        Object counter = httpSession.getAttribute("counter");
        if (counter == null) {
            counter = 1;
            httpSession.setAttribute("counter", counter);
            return Map.of("counter", counter);
        }

        int couterInt = (int) counter;
        couterInt++;

        httpSession.setAttribute("counter", couterInt);

        return Map.of("counter", couterInt);
    }


}
