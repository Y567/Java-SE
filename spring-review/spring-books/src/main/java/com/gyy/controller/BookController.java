package com.gyy.controller;

import com.gyy.pojo.Book;
import com.gyy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/book")
public class BookController {

    //需要用到service层实现类对象,controller-->service-->dao
    @Autowired
    @Qualifier("bookService")
    private BookService bookService;
    @RequestMapping("/allBook")
    public String getAllBook(Model model){
        List<Book> books = bookService.selectBook();
        model.addAttribute("books",books);
        return "allBook";
    }

    @RequestMapping("/addBookPage")
    public String toAddBook(){
        return "addBookPage";
    }
    /**
     * 添加书籍的方法这是
     * @param book 参数对象
     */
    @RequestMapping("/addBook")
    public String addBook(Book book){
        System.out.println("假装已经添加书籍了");
        bookService.addBook(book);
        return "allBook";
    }
}
