package com.manong.controller;

import com.manong.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import pojo.EasyUITree;
import pojo.ResponseJsonResult;

import java.util.List;

/*
* 商品分类控制管理器
* */
@Controller
@RequestMapping("/product_category")
public class ProductCategoryController {
    /**
     * 根据parentid返回数据列表
     */
    @Autowired
    ProductCategoryService productCategoryService;
    @RequestMapping("/list")
    @ResponseBody
    //返回json数据
    public List<EasyUITree> gerProductCategoryByParentId(@RequestParam(value = "id", defaultValue = "0") Short parentId){

        List<EasyUITree> easyUITrees = productCategoryService.findProductCategoryListByParentId(parentId);
        return easyUITrees;
    }
    /*添加分类*/
    @RequestMapping("/add")
    @ResponseBody
    //返回json数据
    public ResponseJsonResult addCategory(Short parentId,String name){
        ResponseJsonResult responseJsonResult = productCategoryService.addCategory(parentId,name);
        return responseJsonResult;
    }
    /*
    * 删除分类
    * */
    @RequestMapping("/del")
    @ResponseBody
    public ResponseJsonResult deleteCategory(Short parentId,Short id){
        ResponseJsonResult responseJsonResult = productCategoryService.delCategory(parentId,id);
        return responseJsonResult;
    }
    /*
    *
    * 修改分类
    * */
    @RequestMapping("/edit")
    @ResponseBody
    public  ResponseJsonResult editCategory(Short id,String name,Short parentId){
        ResponseJsonResult responseJsonResult = productCategoryService.editCategory(id,name,parentId);
        return responseJsonResult;
    }
}
