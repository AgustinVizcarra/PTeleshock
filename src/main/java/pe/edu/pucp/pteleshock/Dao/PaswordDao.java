package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.BUsuario;
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
import java.util.Properties;

public class PaswordDao extends BaseDao{
    public void cambiarContra(int idUsuario,String pwd){
        String sql = "update usuario set contrasenia= SHA2(?,256) where idusuario= ?";
        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1,pwd);
            preparedStatement.setInt(2,idUsuario);//aqui tienes que aplicar el hasheo
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void confirmacionCambioContra(int idUsuario){
        String nombre = "";
        String dest = "";
        String sql = "select nombre,correo from usuario where idusuario="+idUsuario;
        try(Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){
            if(rs.next()){
                nombre = rs.getString(1);
                dest = rs.getString(2);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host","smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable","true");
        propiedad.setProperty("mail.smtp.port","587");
        propiedad.setProperty("mail.smtp.auth","true");
        Session session = Session.getDefaultInstance(propiedad);
        String owner_cuenta = "TeleshockInc@gmail.com";
        String pswd = "ProyectoTeleshock2021";
        String asunto = "Confirmación de cambio de contraseña";
        String mensaje = "<p><b> Estimada(o) "+nombre+": </b></p><div>Se ha realizado el cambio de contraseña de manera exitosa</div><p></p><div><img src=\"cid:image\"></div><p></p><p></p><div><br>Saludos Cordiales</br><br><FONT COLOR=\"gray\">El equipo técnico de Teleshock</FONT></br></div>";
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
            //DataSource fds =  new FileDataSource("C:\\Users\\casa\\Desktop\\Ingenieria Web\\confirmacion_pwd.png");
            try {
                InputStream iStream = getClass().getClassLoader().getResource("img/confirmacion_pwd.png").openStream();
                DataSource fds =  new InputStreamDataSource(iStream,"confirmacion_password","image/png");
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
            //imagen.setDataHandler(new DataHandler(fds));
            //imagen.setHeader("Content-ID","<image>");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void enviarCambioContra(String dest){
        ValidacionAdd_Dao validacionAdd_dao = new ValidacionAdd_Dao();
        String nombre = "";
        String sql = "select nombre from usuario where correo=?";
        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1,dest);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                nombre=resultSet.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String token = validacionAdd_dao.generarToken(dest,nombre);
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host","smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable","true");
        propiedad.setProperty("mail.smtp.port","587");
        propiedad.setProperty("mail.smtp.auth","true");
        Session session = Session.getDefaultInstance(propiedad);
        String owner_cuenta = "TeleshockInc@gmail.com";
        String pswd = "ProyectoTeleshock2021";
        String asunto = "Cambio de contraseña";
        String mensaje = "<p><b> Estimada(o) "+nombre+": </b></p><div>Usted ha solicitado un cambio de contraseña</div><p></p><div><img src=\"cid:image\"></div><p></p><div><p><b>Para ingresar su nueva " +
                "contraseña ingrese al siguiente enlace:</b></p><p><b><a href=\"http://localhost:8080/PTeleshock_war_exploded/Login_Password_Recovery?correo="+token+"\">Ingrese su nueva contraseña</a></b></p></div><p></p><div><br>Saludos Cordiales</br><br><FONT COLOR=\"gray\">El equipo técnico de Teleshock</FONT></br></div>";
        MimeMessage mail = new MimeMessage(session);
        try {
            mail.setFrom(new InternetAddress(owner_cuenta));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(dest));
            mail.setSubject(asunto);
            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart html_parte = new MimeBodyPart();
            html_parte.setContent(mensaje, "text/html");
            multipart.addBodyPart(html_parte);
            BodyPart imagen = new MimeBodyPart();
            //añadir la ruta de manera dinamica
            try {
                InputStream iStream = getClass().getClassLoader().getResource("img/cambio_pwd.png").openStream();//aquí se tiene que hacer el cambio
                DataSource fds =  new InputStreamDataSource(iStream,"cambio_pwd","image/png");
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
    public Integer obtenerid(String correo)
    {
        int id = 0;
        String sql = "select idusuario from usuario where correo=?";
        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,correo);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                id= resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }
}
