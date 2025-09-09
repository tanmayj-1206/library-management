package com.tanmay.library_management.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanmay.library_management.DTO.APIResponseWrapper;
import com.tanmay.library_management.DTO.IssuedBooksDTO;
import com.tanmay.library_management.Service.IssueBooksService;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("issue")
public class IssueBooksController {

    @Autowired
    private IssueBooksService issueBooksService;

    @PostMapping("issuebook")
    public ResponseEntity<APIResponseWrapper<String>> issueBook(@RequestBody String bookId) {
        issueBooksService.issueBook(bookId);
        return ResponseEntity.ok(APIResponseWrapper.success("Book issued successfully", null));
    }

    @PostMapping("returnbook")
    public ResponseEntity<APIResponseWrapper<String>> returnBook(@RequestBody String bookId) {
        issueBooksService.returnBook(bookId);
        return ResponseEntity.ok(APIResponseWrapper.success("Book returned successfully", null));
    }

    @GetMapping("getissuedbooks")
    public ResponseEntity<APIResponseWrapper<List<IssuedBooksDTO>>> getIssuedBooks() {
        return ResponseEntity.ok(APIResponseWrapper.success("Issued books retrieved successfully", issueBooksService.getIssuedBooks()));
    }
    
    
}
