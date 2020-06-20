package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/CarritoServlet")
public class CarritoServlet extends HttpServlet{
    @Override
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        //PROCESAMOS NUEVO ARTICULO
        String articuloNuevo=request.getParameter("articulo");
        //creamos o recuperamos el objeto HTTPSESION
        HttpSession sesion=request.getSession();
        
        //recuperar articulos previos si es que existen
        List<String> articulos=(List<String>) sesion.getAttribute("articulos");
        
        //verificamos si la lista de articulos existe
        if (articulos==null){
        articulos=new ArrayList<>();
        sesion.setAttribute("articulos", articulos);
        }
        //revisamos y agregamos el articulo nuevo
        if (articuloNuevo!=null && !articuloNuevo.trim().equals("")){
        articulos.add(articuloNuevo);
        }
    PrintWriter out=response.getWriter();
    out.print("<h1> List de Articulos</h1>");
    
    //iteramos todos los archivos
    for(String articulo:articulos){
    out.print("<li>"+articulo+"<li>");
    }
    //agregamos link para regresar al inicio
   
    out.print("<a href='/CarritoCompras'>Regresar al Inicio</a>");
    out.close();
    }
    
}
