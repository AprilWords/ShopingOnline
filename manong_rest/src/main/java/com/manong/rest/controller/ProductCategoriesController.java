package com.manong.rest.controller;

import com.manong.rest.service.ProductCategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Res_Categories;

@Controller
@RequestMapping("/rest/")
public class ProductCategoriesController {
    @Autowired
    ProductCategoriesService productCategoriesService;

    @RequestMapping("productCategories/list")
    @ResponseBody
    public Res_Categories getCategories(){
        Res_Categories res_categories = productCategoriesService.getCategories();
        return res_categories;
    }
}
