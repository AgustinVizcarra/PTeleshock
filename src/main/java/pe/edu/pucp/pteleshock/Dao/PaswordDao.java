package pe.edu.pucp.pteleshock.Dao;

import pe.edu.pucp.pteleshock.Beans.BUsuario;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.*;
import java.util.Properties;

public class PaswordDao extends BaseDao{
    public void cambiarContra(int idUsuario,String pwd){
        String sql = "update usuario set contrasenia= ? where idusuario= ?";
        try(Connection connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1,pwd);
            preparedStatement.setInt(2,idUsuario);
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
            DataSource fds =  new FileDataSource("C:\\Users\\casa\\Desktop\\Ingenieria Web\\confirmacion_pwd.png");
            imagen.setDataHandler(new DataHandler(fds));
            imagen.setHeader("Content-ID","<image>");
            multipart.addBodyPart(imagen);
            mail.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            transport.connect(owner_cuenta,pswd);
            transport.sendMessage(mail,mail.getRecipients(Message.RecipientType.TO));
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}