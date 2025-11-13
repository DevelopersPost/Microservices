package org.example.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorCode", "404");
                model.addAttribute("errorMessage", "Page not found");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorCode", "403");
                model.addAttribute("errorMessage", "Access Denied");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorCode", "500");
                model.addAttribute("errorMessage", "Internal Server Error");
                
                // Add error details for debugging
                Object errorMessage = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
                Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
                
                if (errorMessage != null) {
                    model.addAttribute("errorDetail", errorMessage.toString());
                }
                if (exception != null) {
                    model.addAttribute("exception", exception.toString());
                }
            } else {
                model.addAttribute("errorCode", statusCode);
                model.addAttribute("errorMessage", "An error occurred");
            }
        } else {
            model.addAttribute("errorCode", "Unknown");
            model.addAttribute("errorMessage", "An unknown error occurred");
        }
        
        return "error";
    }
}
