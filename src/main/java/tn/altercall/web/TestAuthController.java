package tn.altercall.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestAuthController {

    @GetMapping(value = "/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping(value = "/dev")
    @PreAuthorize("hasRole('PRODUCTOWNER') or hasRole('SCRUMMASTER') or hasRole('DEVELOPER')")
    public String devAccess() {
        return "DEVELOPER Content...";

    }

    @GetMapping(value = "/scm")
    @PreAuthorize("hasRole('SCRUMMASTER') or hasRole('PRODUCTOWNER')")
    public String scrumMAccess() {
        return "SCRUMMASTER Board.";
    }

    @GetMapping(value = "/po")
    @PreAuthorize("hasRole('PRODUCTOWNER')")
    public String poAccess() {
        return "PRODUCTOWNER Board.";
    }
}