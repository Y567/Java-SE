package com.gyy.service;

import com.gyy.pojo.Book;

import java.util.List;

/**
 * 业务层
 */
public interface BookService {
    /**
     * 增加一本书
     * @param book 书
     */
    void addBook(Book book);

    /**
     * 根据id删除一本书
     * @param id id
     */
    void deleteBookById(int id);

    /**
     * 更新一本书
     * @param book book
     */
    void updateBook(Book book);

    /**
     * 查询所有的书
     * @return 返回一个列表
     */
    List<Book> selectBook();

    /**
     * 根据id查询书籍并回显数据
     * @param bookId 书籍的id
     * @return   返回值
     */
    Book selectBookById(int bookId);
}
