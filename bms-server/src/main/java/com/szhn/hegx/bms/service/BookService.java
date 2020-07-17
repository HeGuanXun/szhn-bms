package com.szhn.hegx.bms.service;

import com.szhn.hegx.bms.model.Book;
import com.szhn.hegx.bms.utils.PageBean;
import com.szhn.hegx.bms.utils.Pagination;

import java.util.List;

/**
 * <p>TODO</p>
 *
 * @author 何冠勋
 * @since 2020/7/13
 **/
public interface BookService {

    /**
     * @param book
     * @return
     */
    int insert(Book book);

    /**
     * @param book
     * @return
     */
    int update(Book book);

    /**
     * @param id
     */
    void deleteById(Integer id);

    /**分页查询
     * @param Book
     * @return
     */
    PageBean<Book> getBookByPage(Book Book,int currentPage,int pageSize);
}
