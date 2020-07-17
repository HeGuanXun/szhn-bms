package com.szhn.hegx.bms.controller;

import com.github.pagehelper.PageHelper;
import com.szhn.hegx.bms.model.Book;
import com.szhn.hegx.bms.service.BookService;
import com.szhn.hegx.bms.utils.PageBean;
import com.szhn.hegx.bms.utils.ResultBody;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>TODO</p>
 *
 * @author 何冠勋
 * @since 2020/7/13
 **/
@RestController
@CrossOrigin
@Api(tags = {"图书管理增删改查接口"})
public class BmsBookController {

    private final static Logger LOG = LoggerFactory.getLogger(BmsBookController.class);

    @Autowired
    private BookService bookService;

    @RequestMapping("/insert")
    @ApiOperation(value = "/insert", notes = "新增")
    public ResultBody insert(Book book) {
        bookService.insert(book);
        return new ResultBody();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/deleteById")
    @ApiOperation(value = "/deleteById", notes = "根据id删除图书")
    public ResultBody deleteById(Integer bid) {
        LOG.info("正在删除图书，删除id:", bid);
        bookService.deleteById(bid);
        return ResultBody.success();
    }

    @RequestMapping("/update")
    @ApiOperation(value = "/update", notes = "更新接口")
    public ResultBody update(Book book) {
        bookService.update(book);
        return ResultBody.success();
    }

    @RequestMapping("/getBookByPage")
    @ApiOperation(value = "/getBookByPage", httpMethod = "GET", notes = "查询图书")
    public ResultBody getBook(Book book, int currentPage,int pageSize) {
        PageBean<Book> bookByPage = bookService.getBookByPage(book, currentPage, pageSize);
        return ResultBody.success(bookByPage);
    }
}
