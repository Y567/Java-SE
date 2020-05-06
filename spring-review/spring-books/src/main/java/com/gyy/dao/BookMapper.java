package com.gyy.dao;

import com.gyy.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 操作书的接口dao
 */
public interface BookMapper {

    /**
     * 增加一本书
     * @param book 书
     */
    void addBook(Book book);

    /**
     * 根据id删除一本书
     * @param id id
     */
    void deleteBookById(@Param("id") int id);

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
}
