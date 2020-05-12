package com.gyy.controller;

import com.gyy.pojo.Book;
import com.gyy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * 用来转发到添加书籍页面的
     */
    @RequestMapping("/toAddBook")
    public String toAddBook(){
        return "addBookPage";
    }


    /**
     * 将页面转发到更新的页面
     */
    @RequestMapping("/toUpdateBook/{bookId}")  //RestFul风格
    public String toUpdateBook(@PathVariable("bookId") int bookId,Model model){
        //先查询书籍，到时候好转发过去回显数据
        Book book = bookService.selectBookById(bookId);
        model.addAttribute("book",book);
        return "updateBookPage";
    }

    /**
     * 添加书籍的方法这是
     * @param book 参数对象
     */
    @RequestMapping("/addBook")
    public String addBook(Book book){
        System.out.println("假装已经添加书籍了");
        bookService.addBook(book);
        return "redirect:/book/allBook";
    }

    /**
     * 更新书籍然后重定向回首页
     * @param book 书籍
     * @return     返回字符串
     */
    @RequestMapping("/updateBook")
    public String updateBook(Book book){
        bookService.updateBook(book);
        //返回首页进行查询所有书籍,不需要加/
        return "redirect:/book/allBook";
    }

    @RequestMapping("/deleteBook/{bookId}")
    public String deleteBook(@PathVariable("bookId") int id){
        bookService.deleteBookById(id);
        //这里的重定向实际上说的是访问后台的allBook
//        System.out.println("删除；1");

        //重定向必须有时候需要从controller层开始加起
        return "redirect:/book/allBook";
    }

    @RequestMapping("/selectBookByName")
    public String selectBookByName(String bookName,Model model){
        List<Book> books = bookService.selectBookByName("%" + bookName + "%");
        if(books.isEmpty()){
            model.addAttribute("error","没数据");
        }
        model.addAttribute("books",books);
        return "allBook";

    }

}
