package com.manong.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manong.mapper.ProductMapper;
import com.manong.pojo.Product;
import com.manong.pojo.ProductExample;
import com.manong.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.EasyGrid;
import pojo.ResponseJsonResult;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
   @Autowired
   ProductMapper productMapper;
    public ResponseJsonResult saveProduct(Product product) {
        product.setStatus((byte)1);
        productMapper.insert(product);
        ResponseJsonResult responseJsonResult = new ResponseJsonResult();
        responseJsonResult.setStatus(200);
        return responseJsonResult;
    }

    public EasyGrid listProduct(int pages, int rows) {
        ProductExample productExample = new ProductExample();
        PageHelper.startPage(pages,rows);
        List<Product> productList = productMapper.selectByExample(productExample);
        PageInfo<Product> pageInfo = new PageInfo<Product>(productList);
        EasyGrid easyGrid = new EasyGrid();
        easyGrid.setTotal((int)pageInfo.getTotal());
        easyGrid.setRows(productList);
        return easyGrid;
    }
}
