package com.sevices;

import java.util.List;

import com.entity.Product;
import com.repository.ProductReository;

public class ProductSevices {
	private ProductReository reository;

	public ProductSevices() {
		reository = new ProductReository();
	}
	public Product save(Product p)
	{
		reository.save(p);
		return p;
	}
	public List<Product>getAll()
	{
		List<Product> list =  reository.getAll();
		return list;
	}
	
}
