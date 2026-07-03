package controller;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;
import dao.AmenityRepo;

@WebServlet({"/amenity/create", "/amenity/update"})
public class Amenity extends HttpServlet{
    

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        String path = request.getServletPath();

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("currentUser");

        if(user == null){
            response.sendRedirect("/login");
        }else if(!user.getRole().equals("staff") || !user.getRole().equals("owner")){
            response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Invalid Request");
        }

        if(path.equals("/amenity/create")){

            String name = request.getParameter("amenityName");
            if(name == null){
                response.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Invalid Parameter");
            }
            
            boolean isSuccess = AmenityRepo.amenityCreate(name);

        }else if(path.equals("/amenity/update")){
            
        }

    }
}
