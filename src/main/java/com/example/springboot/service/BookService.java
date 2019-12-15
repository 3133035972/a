package com.example.springboot.service;


import com.example.springboot.dao.BookDAO;
import com.example.springboot.entity.Book;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BookService {

    @Resource
    BookDAO bookDAO;

    //添加
    public int Insert(Book book)
    {
        return bookDAO.insert(book);
    }

}
