package com.manong.service.impl;

import com.manong.mapper.ProductCategoryMapper;
import com.manong.pojo.ProductCategory;
import com.manong.pojo.ProductCategoryExample;
import com.manong.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EasyUITree;
import pojo.ResponseJsonResult;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;


    public List<EasyUITree> findProductCategoryListByParentId(Short parentid){
        ProductCategoryExample productCategoryExample = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = productCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentid);
        List<ProductCategory> productCategoryLsit = productCategoryMapper.selectByExample(productCategoryExample);
        List<EasyUITree> easyUITrees = new ArrayList<EasyUITree>(productCategoryLsit.size());
        for(ProductCategory productCategory:productCategoryLsit){
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setId(productCategory.getId());
            easyUITree.setText(productCategory.getName());
            easyUITree.setState(productCategory.getParentId()==0?"closed":"open");
            easyUITree.setAttributes(productCategory.getParentId()+"");
            easyUITrees.add(easyUITree);

        }
        return easyUITrees;
    }
    //添加新种类
    public ResponseJsonResult addCategory(Short parentId,String name){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setParentId(parentId);
        productCategory.setName(name);
        productCategoryMapper.insert(productCategory);
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        responseJsonResult.setMsg(productCategory.getId()+"");

        return responseJsonResult;
    }
    /*删除新种类*/
    public ResponseJsonResult delCategory(Short parentid,Short id){
        ProductCategoryExample productCategoryExample = new ProductCategoryExample();
        ProductCategoryExample.Criteria criteria = productCategoryExample.createCriteria();
        if(parentid == 0){
            criteria.andIdEqualTo(id);

            ProductCategoryExample.Criteria criteria1 = productCategoryExample.createCriteria();
            criteria1.andParentIdEqualTo(parentid);
            productCategoryExample.or(criteria1);

        }
        else {
            criteria.andIdEqualTo(id);
        }
        productCategoryMapper.deleteByExample(productCategoryExample);

        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        responseJsonResult.setStatus(200);
        responseJsonResult.setMsg("success");
        return responseJsonResult;
    }
    //修改分类
        public  ResponseJsonResult editCategory(Short id,String name,Short parentId){
        ProductCategoryExample productCategoryExample = new ProductCategoryExample();
        ProductCategory productCategory = new ProductCategory();
        productCategory.setId(id);
        productCategory.setName(name);
        productCategory.setParentId(parentId);
        ProductCategoryExample.Criteria criteria = productCategoryExample.createCriteria();
        criteria.andIdEqualTo(id);
        productCategoryMapper.updateByExample(productCategory,productCategoryExample);
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        responseJsonResult.setStatus(200);
        responseJsonResult.setMsg("success");
        return responseJsonResult;
        }
}
