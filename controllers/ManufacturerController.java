package com.example.productmanagement.controllers;

import com.example.productmanagement.entities.Manufacturer;
import com.example.productmanagement.services.ManufacturerService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ManufacturerController", value = "/ManufacturerController")
public class ManufacturerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        ManufacturerService manufacturerService = new ManufacturerService();
        HttpSession session = request.getSession(true);
        switch (action){
            case "management_factory_view":
                List<Manufacturer> ls = manufacturerService.getAll();
                session.setAttribute("maunfactories",ls);
                response.sendRedirect("management-factory-view.jsp");
                break;
            case "add_manufacturer_view":
                response.sendRedirect("add-manufacturer-view.jsp");
                break;
            case "add_manufacturer":
                String name = request.getParameter("name");
                String contact = request.getParameter("contact");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String website = request.getParameter("website");
                Manufacturer manufacturer = new Manufacturer(name,contact,email,phone,website);
                manufacturerService.save(manufacturer);
                response.sendRedirect("ManufacturerController?action=management_factory_view");
                break;
            case "delete_user":
                String id = request.getParameter("id");
                manufacturerService.delete(id);
                response.sendRedirect("ManufacturerController?action=management_factory_view");
                break;
            case "update_manufactory_view":
                String idSel = request.getParameter("id");
                Manufacturer manufacturerSel = manufacturerService.getManufacturerById(idSel);
                session.setAttribute("ManuUpdate",manufacturerSel);
                response.sendRedirect("update-manufacturer-view.jsp");
                break;
            case "update_manufacturer":
                Manufacturer manuTemp = (Manufacturer) session.getAttribute("ManuUpdate");
                long idManuTemp = manuTemp.getId();
                String nameUpdate = request.getParameter("name");
                String contactUpdate = request.getParameter("contact");
                String emailUpdate = request.getParameter("email");
                String phoneUpdate = request.getParameter("phone");
                String websiteUpdate = request.getParameter("website");
                Manufacturer manufacturerUpdate = new Manufacturer(nameUpdate,contactUpdate,emailUpdate,phoneUpdate,websiteUpdate);
                manufacturerUpdate.setId(idManuTemp);
                manufacturerService.update(manufacturerUpdate);
                response.sendRedirect("ManufacturerController?action=management_factory_view");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
