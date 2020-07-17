package com.szhn.hegx.bms.service.impl;

import com.github.pagehelper.PageHelper;
import com.szhn.hegx.bms.dao.BookDao;
import com.szhn.hegx.bms.model.Book;
import com.szhn.hegx.bms.service.BookService;
import com.szhn.hegx.bms.utils.PageBean;
import com.szhn.hegx.bms.utils.Pagination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>TODO</p>
 *
 * @author 何冠勋
 * @since 2020/7/13
 **/
@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final static Logger LOG = LoggerFactory.getLogger(BookServiceImpl.class);
    @Autowired
    private BookDao bookDao;

    @Override
    @CacheEvict(value = "bms_user", allEntries = true)
    public int insert(Book book) {
        return bookDao.insert(book);
    }

    @Override
    @CacheEvict(value = "bms_user", allEntries = true)
    public int update(Book book) {
        return bookDao.update(book);
    }

    @Override
    @CacheEvict(value = "bms_user", allEntries = true)
    public void deleteById(Integer id) {
        bookDao.deleteById(id);
    }

    @Override
    @Cacheable(value = "bms_user")
    public PageBean<Book> getBookByPage(Book book, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<Book> allItems = bookDao.getBookAll(book);
        int countNums = bookDao.countBook(book);
        PageBean<Book> pageData = new PageBean<>(currentPage, pageSize, countNums);
        pageData.setItems(allItems);
        return pageData;
    }
}
