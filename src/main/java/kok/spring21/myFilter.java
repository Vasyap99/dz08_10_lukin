package kok.spring21;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Component;
//import org.springframework.core.annotation.Order;

import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

import org.springframework.web.server.WebFilter;

@Component
//@Order(2)
public class myFilter implements Filter {

    @Override
    public void init(javax.servlet.FilterConfig fc){}

    @Override
    public void destroy(){}

    @Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) throws ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println(">>> (FILTER) !!!!!!!!!!!  url:"+req.getRequestURI());

        String servletPath = req.getServletPath();
        System.out.println(">>>"+servletPath+" "+req.getPathInfo()+" "+req.getServletPath()+" "+req.getRequestURL()+" "+req.getQueryString());
        try{
            if (servletPath.startsWith("/auth")||servletPath.startsWith("/register")) {
                chain.doFilter(request, response);
            } else {
                HttpSession session=req.getSession();
                try{ 
                    String tkn=(String)session.getAttribute("tkn");
                    if(tkn==null)
                        request.getRequestDispatcher("/auth").forward(request, response); 
                    else{
                        if(tkn.equals("111")){//успешная авторизация (выдаем страницу)                        
                            chain.doFilter(request, response);
                        }else request.getRequestDispatcher("/auth").forward(request, response);   
                    }    
                }catch(Exception e1){
                    request.getRequestDispatcher("/auth").forward(request, response);   
                }                             
            }
        }catch(Exception e){
            System.out.println(">>kokExc1");
            e.printStackTrace();
        }
    }

    // other methods
}