package com.manong.rest.controller;

import com.manong.rest.service.ProductCategoriesService;
import com.manong.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.Res_Categories;
import pojo.Res_Product;
import pojo.ResponseJsonResult;

@Controller
@RequestMapping("/rest/")
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("product/list")
    @ResponseBody
    public ResponseJsonResult getCategories(){
        ResponseJsonResult responseJsonResult = productService.getProdcut();
        return responseJsonResult;
    }
}
