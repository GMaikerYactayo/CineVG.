package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Pelicula;

public class PeliculaImpl extends Conexion implements ICRUD<Pelicula> {

    @Override
    public void regitrar(Pelicula pelicula) throws Exception {
        try {
            this.conectar();
            String sql = "insert into PELICULA"
                    + "(NOMPEL,GENPEL,RESTPEL,TIPPEL,LENPEL,DURPEL,HORPEL,FECPEL,ESTPEL)"
                    + "values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, pelicula.getNOMPEL());
            ps.setString(2, pelicula.getGENPEL());
            ps.setString(3, pelicula.getRESTPEL());
            ps.setString(4, pelicula.getTIPPEL());
            ps.setString(5, pelicula.getLENPEL());
            ps.setString(6, pelicula.getDURPEL());
            ps.setString(7, pelicula.getHORPEL());
            ps.setString(8, pelicula.getFECPEL());
            ps.setString(9, "A");
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error en registrarAlumno " + e.getMessage());
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Pelicula pelicula) throws Exception {
        try {
            this.conectar();
            String sql = "update PELICULA set NOMPEL=?,GENPEL=?,RESTPEL=?,TIPPEL=?,LENPEL=?,DURPEL=?,HORPEL=?,FECPEL=?,ESTPEL=? where IDPEL=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, pelicula.getNOMPEL());
            ps.setString(2, pelicula.getGENPEL());
            ps.setString(3, pelicula.getRESTPEL());
            ps.setString(4, pelicula.getTIPPEL());
            ps.setString(5, pelicula.getLENPEL());
            ps.setString(6, pelicula.getDURPEL());
            ps.setString(7, pelicula.getHORPEL());
            ps.setString(8, pelicula.getFECPEL());
            ps.setString(9, pelicula.getESTPEL());
            ps.setInt(10, pelicula.getIDPEL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void eliminar(Pelicula pelicula) throws Exception {
        try {
            this.conectar();
            String sql = "update PELICULA set ESTPEL = 'I' where IDPEL=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setInt(1, pelicula.getIDPEL());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public List<Pelicula> listar() throws Exception {
        List<Pelicula> listado;
        Pelicula pel;
        try {
            this.conectar();
            String sql = "SELECT IDPEL,NOMPEL,GENPEL,RESTPEL,LENPEL,DURPEL,\n"
                    + "	CONVERT(CHAR(5), HORPEL, 108) AS HORPEL, \n"
                    + "	CONVERT(VARCHAR(20), FECPEL, 106) AS FECPEL, ESTPEL FROM PELICULA";
            listado = new ArrayList();
            Statement st = this.getCn().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                pel = new Pelicula();
                pel.setIDPEL(rs.getInt("IDPEL"));
                pel.setNOMPEL(rs.getString("NOMPEL"));
                pel.setGENPEL(rs.getString("GENPEL"));
                pel.setRESTPEL(rs.getString("RESTPEL"));
                pel.setLENPEL(rs.getString("LENPEL"));
                pel.setDURPEL(rs.getString("DURPEL"));
                pel.setHORPEL(rs.getString("HORPEL"));
                pel.setFECPEL(rs.getString("FECPEL"));
                pel.setESTPEL(rs.getString("ESTPEL"));
                listado.add(pel);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return listado;
    }

}
