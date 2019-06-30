package com.manong.rest.service;

import org.springframework.stereotype.Service;
import pojo.Res_Categories;
@Service
public interface ProductCategoriesService {
    Res_Categories getCategories();
}
