package kok.spring21;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.stereotype.Component;

import javax.servlet.http.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.*;

import org.springframework.web.server.WebFilter;

import java.util.Set;
import java.util.HashSet;


public class myFilter implements Filter {

    public static Set<String> tkns1=new HashSet<>();


    @Override
    public void init(javax.servlet.FilterConfig fc){}

    @Override
    public void destroy(){}

    private void go_to_login(ServletRequest request,ServletResponse response) throws ServletException,java.io.IOException{
        //request.getRequestDispatcher("/auth").forward(request, response);
        ((HttpServletResponse)response).sendRedirect("auth");
    }

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
                        go_to_login(request,response); 
                    else{
                        System.out.println(">>>got tkn="+tkn+"<<");
                        System.out.println((HashSet)tkns1);
                        if(tkns1.contains(tkn)){//успешная авторизация (выдаем страницу) 
                            System.out.println(">>>FLT-3");                       
                            chain.doFilter(request, response);
                        }else{
                            try{
                                session.removeAttribute("tkn");
                            }catch(Exception e){}
                            System.out.println(">>>FLT-4");
                            go_to_login(request,response);    
                        }
                    }    
                }catch(Exception e1){
                    System.out.println(">>>FLT-E");
                    go_to_login(request,response); 
                }                             
            }
        }catch(Exception e){
            System.out.println(">>kokExc1");
            e.printStackTrace();
        }
    }

    // other methods
}