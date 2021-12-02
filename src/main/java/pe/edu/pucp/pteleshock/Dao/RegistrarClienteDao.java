package pe.edu.pucp.pteleshock.Dao;
import pe.edu.pucp.pteleshock.Beans.BCliente;
import pe.edu.pucp.pteleshock.Beans.InputStreamDataSource;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class RegistrarClienteDao extends BaseDao{

        public void añadirCliente(int idDis, String nombre, String apellido, String correo,String dni, String contrasenia) {

            String sql = "INSERT INTO usuario (idrol, iddistrito, nombre, apellido, correo, dni, contrasenia) VALUES (1,?,?,?,?,?,?)";

            try (Connection conn = this.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, idDis);
                pstmt.setString(2, nombre);
                pstmt.setString(3, apellido);
                pstmt.setString(4, correo);
                pstmt.setString(5, dni);
                pstmt.setString(6, contrasenia);
                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public ArrayList<BCliente> listaClientesR(){
        ArrayList<BCliente> listaClientes = new ArrayList<BCliente>();
        String sql="SELECT u.idusuario,u.correo,u.dni FROM usuario u;";

        try (Connection conn = this.getConnection();
             Statement stmt=conn.createStatement();
             ResultSet rs=stmt.executeQuery(sql)) {
            while (rs.next()) {
                BCliente bCliente = new BCliente();
                bCliente.setIdCliente(rs.getInt(1));
                bCliente.setCorreo(rs.getString(2));
                bCliente.setDni(rs.getString(3));
                listaClientes.add(bCliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  listaClientes;
    }


    public boolean correoExistente (String correo){
        boolean siExiste = false;
        ArrayList<BCliente> listaClientes = listaClientesR();
        for(BCliente cliente : listaClientes){
            if(cliente.getCorreo().equals(correo)){
                siExiste = true;
                break;
            }
        }
        return siExiste;
    }

    public boolean dniExistente (String dni){
        boolean siExiste = false;
        ArrayList<BCliente> listaClientes = listaClientesR();
        for(BCliente cliente : listaClientes){
            if(cliente.getDni()!=null){
                if(cliente.getDni().equals(dni)){
                    siExiste = true;
                    break;
                }
            }
        }
        return siExiste;
    }

    public void enviarCorreoRegistro(String dest,String nombre){
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host","smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable","true");
        propiedad.setProperty("mail.smtp.port","587");
        propiedad.setProperty("mail.smtp.auth","true");
        Session session = Session.getDefaultInstance(propiedad);
        String owner_cuenta = "TeleshockInc@gmail.com";
        String pswd = "ProyectoTeleshock2021";
        String asunto = "Confirmación de registro de cuenta de Usuario";
        String mensaje = "<p><b> Estimada(o) "+nombre+": </b></p><div>Es grato comentarle que su cuenta se ha registrado de manera exitosa</div><p></p><div><img src=\"cid:image\"></div><p></p><p></p><div><br>Saludos Cordiales</br><br><FONT COLOR=\"gray\">El equipo técnico de Teleshock</FONT></br></div>";
        MimeMessage mail = new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress(owner_cuenta));
            mail.addRecipient(Message.RecipientType.TO,new InternetAddress(dest));
            mail.setSubject(asunto);
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart html_parte= new MimeBodyPart();
            html_parte.setContent(mensaje,"text/html");
            multipart.addBodyPart(html_parte);
            BodyPart imagen = new MimeBodyPart();
            //DataSource fds =  new FileDataSource("C:\\Users\\casa\\Desktop\\Ingenieria Web\\confirmacion_registro.png");
            try {
                InputStream iStream = getClass().getClassLoader().getResource("img/confirmacion_registro.png").openStream();
                DataSource fds =  new InputStreamDataSource(iStream,"confirmacion_registro","image/png");
                imagen.setDataHandler(new DataHandler(fds));
                imagen.setHeader("Content-ID","<image>");
                multipart.addBodyPart(imagen);
                mail.setContent(multipart);
                Transport transport = session.getTransport("smtp");
                transport.connect(owner_cuenta,pswd);
                transport.sendMessage(mail,mail.getRecipients(Message.RecipientType.TO));
                transport.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

