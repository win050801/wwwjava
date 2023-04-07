package com.example.productmanagement.controllers;

import com.example.productmanagement.entities.Manufacturer;
import com.example.productmanagement.entities.Product;
import com.example.productmanagement.services.ManufacturerService;
import com.example.productmanagement.services.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductController", value = "/ProductController")
public class ProductController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ManufacturerService manufacturerService = new ManufacturerService();
        ProductService productService = new ProductService();

        HttpSession session = request.getSession(true);
        switch (action) {
            case "product_management_view":
                List<Product> products = productService.getAll();
                session.setAttribute("products", products);
                response.sendRedirect("management-product-view.jsp");
                break;
            case "add_product_view":
                response.sendRedirect("add-product-view.jsp");
                List<Manufacturer> ls = manufacturerService.getAll();
                session.setAttribute("maunfactories",ls);
                break;
            case "add_product":
                String name = request.getParameter("name");
                String description = request.getParameter("description");
                long id = Long.parseLong(request.getParameter("manu"));
                productService.save(new Product(name,description,new Manufacturer(id)));
                response.sendRedirect("ProductController?action=product_management_view");
                break;
            case "delete_product":
                String idProductRemove = request.getParameter("id");
                productService.removeProduct(idProductRemove);
                response.sendRedirect("ProductController?action=product_management_view");
                break;
            case "update_product_view":
                String idProductUpdate = request.getParameter("id");
                Product product = productService.getProductById(idProductUpdate);
                List<Manufacturer> lsManu = manufacturerService.getAll();
                session.setAttribute("productUpdate",product);
                session.setAttribute("manufacturers",lsManu);
                response.sendRedirect("update-product-view.jsp");
                break;
            case "update_product":
                String idProduct = request.getParameter("idProduct");
                String nameProduct = request.getParameter("name");
                String descProduct = request.getParameter("description");
                Manufacturer manufacturer = new Manufacturer(Long.parseLong(request.getParameter("manufacturer")));
                Product productUpdated = new Product(nameProduct,descProduct,manufacturer);
                productUpdated.setProductId(Long.parseLong(idProduct));
                productService.updateProduct(productUpdated);
                response.sendRedirect("ProductController?action=product_management_view");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
