
import Clases.Alumno;
import Clases.AlumnoController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    
   /**
    * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
    * methods.
    *
    * @param request servlet request
    * @param response servlet response
    * @throws ServletException if a servlet-specific error occurs
    * @throws IOException if an I/O error occurs
    */
   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter respuesta = response.getWriter()) {    
            respuesta.write("");
           AlumnoController registroAlumno = new AlumnoController();
           String control = request.getParameter("control");
           StringBuffer objetoRespuesta = new StringBuffer();
           if(control.toUpperCase().equals("GUARDAR")){
               //se crea el objeto alumnos con los datos recibidos del navegador a traves de la petición HTTP
               Alumno alumno = new Alumno(
                       Integer.parseInt(request.getParameter("codigo")),
                       request.getParameter("nombre"),
                       request.getParameter("correo"),
                       request.getParameter("direccion"),
                       Integer.parseInt(request.getParameter("genero")));                
                registroAlumno.guardarAlumno(alumno);//almacenarlo en BD                 
           }else if(control.toUpperCase().equals("ELIMINAR")){
               int codigoEliminar= Integer.parseInt(request.getParameter("codigo_alumno"));
               registroAlumno.eliminarALumno(codigoEliminar);
           }
                  
           registroAlumno.getAlumnos(objetoRespuesta);//consultar alumnos en la BD
           respuesta.write(objetoRespuesta.toString());             
                      
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
