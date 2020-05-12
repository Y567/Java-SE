package com.gyy.service.impl;

import com.gyy.dao.BookMapper;
import com.gyy.pojo.Book;
import com.gyy.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {

    //需要一个dao层的实现类来调用dao层的方法,到时候让spring来注入
    private BookMapper bookMapper;

    //set方式的注入
    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    public void addBook(Book book) {
        bookMapper.addBook(book);
    }

    public void deleteBookById(int id) {
        bookMapper.deleteBookById(id);
    }

    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    public List<Book> selectBook() {
        return bookMapper.selectBook();
    }

    public Book selectBookById(int bookId) {
        return bookMapper.selectBookById(bookId);
    }

    public List<Book> selectBookByName(String name){
        return bookMapper.selectBookByName(name);
    }
}
