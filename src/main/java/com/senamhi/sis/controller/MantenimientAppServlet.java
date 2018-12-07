/*
* Servlet de mantenimiento del modulo SIS
* Fecha: 09/10/2018
 */
package com.senamhi.sis.controller;

import com.senamhi.sis.dto.UmbralesPorEstacion;
import com.senamhi.sis.repository.UmbralesPorEstacionRepository;
import com.senamhi.sis.repository.impl.UmbralesPorEstacionRepositoryImpl;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MantenimientAppServlet", urlPatterns = {"/exec.do"})
public class MantenimientAppServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String accion = request.getParameter("accion");
        String param = "?";

        if (accion.equals("CONSULTAR")) {
            String v_cod_esta = request.getParameter("codigo_estacion");
            Integer v_cod_var = Integer.valueOf(request.getParameter("codigo_variable"));

            UmbralesPorEstacion ue = new UmbralesPorEstacion();
            ue.setCodEstacion(v_cod_esta);
            ue.setCodVariable(v_cod_var);

            UmbralesPorEstacionRepository re = new UmbralesPorEstacionRepositoryImpl();
            List<UmbralesPorEstacion> listUE = re.get(ue);
            if (listUE.size() == 0) {
                accion = "NO_REGISTRADO";
            } else {
                accion = "SI_REGISTRADO";
            }
            param += "accion=" + accion + "&cod_esta=" + v_cod_esta + "&cod_var=" + v_cod_var;
        } else if (accion.equals("CONTINUAR_SIN_REGISTRAR")) {
            param = "";
        } else if (accion.equals("ELIMINAR_Y_REGISTRAR")) {
            String v_cod_esta = request.getParameter("codigo_estacion");
            Integer v_cod_var = Integer.valueOf(request.getParameter("codigo_variable"));
            param += "accion=" + accion + "&cod_esta=" + v_cod_esta + "&cod_var=" + v_cod_var;
        } else if (accion.equals("INSERTAR")) {
            String v_cod_esta = request.getParameter("codigo_estacion");
            Integer v_cod_var = Integer.valueOf(request.getParameter("codigo_variable"));

            Double limite_malo_izquierda = Double.valueOf(request.getParameter("lim_maloi"));
            Double limite_malo_derecha = Double.valueOf(request.getParameter("lim_malod"));

            Double limite_dudoso_izquierda1 = Double.valueOf(request.getParameter("lim_dudosoi1"));
            Double limite_dudoso_izquierda2 = Double.valueOf(request.getParameter("lim_dudosoi2"));

            Double limite_dudoso_derecha1 = Double.valueOf(request.getParameter("lim_dudosod1"));
            Double limite_dudoso_derecha2 = Double.valueOf(request.getParameter("lim_dudosod2"));

            Double limite_bueno_izquierda = Double.valueOf(request.getParameter("lim_buenoi"));
            Double limite_bueno_derecha = Double.valueOf(request.getParameter("lim_buenod"));

            List<UmbralesPorEstacion> list = new LinkedList<>();
            UmbralesPorEstacion dto = null;

            //limite_malo_izquierda
            dto = new UmbralesPorEstacion();
            dto.setCodEstacion(v_cod_esta);
            dto.setCodVariable(v_cod_var);
            dto.setNr2(limite_malo_izquierda);
            dto.setCodFlagData("M");
            list.add(dto);

            //limite_malo_derecha
            dto = new UmbralesPorEstacion();
            dto.setCodEstacion(v_cod_esta);
            dto.setCodVariable(v_cod_var);
            dto.setNr1(limite_malo_derecha);
            dto.setCodFlagData("M");
            list.add(dto);

            //limite_dudoso_izquierda
            dto = new UmbralesPorEstacion();
            dto.setCodEstacion(v_cod_esta);
            dto.setCodVariable(v_cod_var);
            dto.setNr1(limite_dudoso_izquierda1);
            dto.setNr2(limite_dudoso_izquierda2);
            dto.setCodFlagData("D");
            list.add(dto);

            //limite_dudoso_derecha
            dto = new UmbralesPorEstacion();
            dto.setCodEstacion(v_cod_esta);
            dto.setCodVariable(v_cod_var);
            dto.setNr1(limite_dudoso_derecha1);
            dto.setNr2(limite_dudoso_derecha2);
            dto.setCodFlagData("D");
            list.add(dto);

            //limite_bueno
            dto = new UmbralesPorEstacion();
            dto.setCodEstacion(v_cod_esta);
            dto.setCodVariable(v_cod_var);
            dto.setNr1(limite_bueno_izquierda);
            dto.setNr2(limite_bueno_derecha);
            list.add(dto);

            UmbralesPorEstacionRepository re = new UmbralesPorEstacionRepositoryImpl();
            re.insert(list);
            accion = "ATENDIDO";
            param += "accion=" + accion;
        }

        response.sendRedirect("sisdad/extremosPorEstacion" + param);
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
