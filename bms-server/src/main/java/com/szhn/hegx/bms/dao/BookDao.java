package com.szhn.hegx.bms.dao;

import com.szhn.hegx.bms.model.Book;
import com.szhn.hegx.bms.utils.Pagination;

import java.util.List;


/**
 * <p>TODO</p>
 *
 * @author 何冠勋
 * @since 2020/7/13
 **/
public interface BookDao {

    int insert(Book book);

    int update(Book book);

    void deleteById(Integer id);

    List<Book> getBookAll(Book Book);

    int countBook(Book book);

}
