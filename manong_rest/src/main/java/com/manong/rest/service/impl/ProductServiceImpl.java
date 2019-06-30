package com.manong.rest.service.impl;

import com.github.pagehelper.PageHelper;
import com.manong.mapper.ProductMapper;
import com.manong.pojo.Product;
import com.manong.pojo.ProductExample;
import com.manong.rest.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Res_Product;
import pojo.ResponseJsonResult;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    public ResponseJsonResult getProdcut() {

        PageHelper.startPage(1,20);
        ProductExample productExample = new ProductExample();
        List<Product> productLs = productMapper.selectByExample(productExample);
        List<Res_Product> res_products = new ArrayList<Res_Product>();
        for(int i=0;i<productLs.size();i++){
            Res_Product res_product = new Res_Product();
            Product product = productLs.get(i);
            res_product.setPrice(product.getPrice());
            res_product.setId(product.getId());
            res_product.setImgUrl(product.getImage());
            res_product.setName(product.getName());
            res_products.add(res_product);

        }
        ResponseJsonResult responseJsonResults = new ResponseJsonResult();
        responseJsonResults.setList(res_products);
        return responseJsonResults;
    }
}
