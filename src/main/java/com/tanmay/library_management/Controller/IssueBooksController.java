package com.tanmay.library_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.library_management.DTO.IssuedBooksDTO;
import com.tanmay.library_management.Service.IssueBooksService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("issue")
public class IssueBooksController {

    @Autowired
    private IssueBooksService issueBooksService;

    @PostMapping("issuebook")
    public String issueBook(@RequestBody String bookId) {
        return issueBooksService.issueBook(bookId);
    }

    @PostMapping("returnbook")
    public String returnBook(@RequestBody String bookId) {
        return issueBooksService.returnBook(bookId);
    }

    @GetMapping("getissuedbooks")
    public List<IssuedBooksDTO> getIssuedBooks() {
        return issueBooksService.getIssuedBooksByStudentId();
    }
    
    
}
