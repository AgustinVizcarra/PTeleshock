package pe.edu.pucp.pteleshock.Dao;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import pe.edu.pucp.pteleshock.Beans.InputStreamDataSource;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class ValidacionAdd_Dao extends BaseDao {
    public boolean ruc_unico(String ruc_analizar){
        boolean unico;
        ArrayList<String> rucs = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs = statement.executeQuery("select u.ruc from usuario u where u.idrol=3");
            while(rs.next()){
                rucs.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        unico=rucs.contains(ruc_analizar);
        return unico;
    }
    public boolean nombre_correcto(String nombre_analizar){
        try{
            int aux = Integer.parseInt(nombre_analizar);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    public boolean email_unico(String correo_analizar){
        boolean email_repetido;
        ArrayList<String> correos = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs = statement.executeQuery("select correo from usuario");
            while(rs.next()){
                correos.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        email_repetido = correos.contains(correo_analizar);
        return email_repetido;
    }
   // public boolean email_correcto(String email_analizar)
    //{
      //  return email_analizar.matches("[-\\w\\.]+@\\w+\\.\\w+");
    //}
    public void insertar_farmacia_usuario(String nombre,String ruc,int distrito,String correo){

        int rol_id = 3;
        String sql_usuario="INSERT INTO usuario (idrol, iddistrito, nombre, correo, ruc) values (?,?,?,?,?)";
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql_usuario);) {
            pstmt.setInt(1,rol_id);
            pstmt.setInt(2,distrito);
            pstmt.setString(3,nombre);
            pstmt.setString(4,correo);
            pstmt.setString(5,ruc);
            pstmt.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
    public void insertar_farmacia_farmacia(String direccion,String ruc){
        //se busca el id_de_usuario
        int usuario_id = 0;
        try(Connection connection = this.getConnection();
            Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery("select u.idusuario from usuario u where u.ruc='"+ruc+"';");
            while(rs.next()){
                usuario_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //una vez obtenido el id de usuario se procede a registrar en el campo de farmacia
        String sql_farmacia = "INSERT INTO farmacia (idusuario, direccion, estatus) values(?,?,?)";
        String status = "desbloqueado";
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql_farmacia);) {
            pstmt.setInt(1,usuario_id);
            pstmt.setString(2,direccion);
            pstmt.setString(3,status);
            pstmt.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
    public void insertar_registro_historial(String admin_nombre,String ruc){

        int farmacia_id = 0;
        try(Connection connection = this.getConnection();
            Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery("Select f.idfarmacia from farmacia f inner join usuario u on (u.idusuario=f.idusuario) where u.ruc='"+ruc+"';");
            while(rs.next()){
                farmacia_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int admin_id = 0;
        try(Connection connection = this.getConnection();
            Statement statement = connection.createStatement();) {
            ResultSet rs = statement.executeQuery("Select idusuario from usuario u where u.nombre='"+admin_nombre+"';");
            while(rs.next()){
                admin_id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //una vez con los parametros ya solicitados se registra la accion
        int id_accion = 3;
        String sql_historial = "INSERT INTO historialadmin (idusuario,idfarmacia,idaccion,fechahora) values(?,?,?,now())";
        try(Connection connection = this.getConnection();
            PreparedStatement pstmt = connection.prepareStatement(sql_historial);) {
            pstmt.setInt(1,admin_id);
            pstmt.setInt(2,farmacia_id);
            pstmt.setInt(3,id_accion);
            pstmt.executeUpdate();
        }catch (SQLException e ){
            e.printStackTrace();
        }
    }
    public int getIdusuario(String correo){
        int idusuario=0;
        String sql = "select idusuario from usuario where correo = ?";
        try(Connection  connection = this.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);){
            preparedStatement.setString(1,correo);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                idusuario=rs.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return idusuario;
    }
    public void enviar_correo(String destinario,String nombre){
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host","smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable","true");
        propiedad.setProperty("mail.smtp.port","587");
        propiedad.setProperty("mail.smtp.auth","true");
        Session session = Session.getDefaultInstance(propiedad);
        String owner_cuenta = "TeleshockInc@gmail.com";
        String pswd = "ProyectoTeleshock2021";
        String dest = destinario;
        // int idusuario = this.getIdusuario(destinario);
        // String idstr = String.valueOf(idusuario) ;
        String asunto = "Confirmación de registro de cuenta";
        String mensaje = "<p><b> Estimada(o) "+nombre+": </b></p><div>Es grato comentarle que su cuenta se ha registrado correctamente</div><p></p><div><img src=\"cid:image\"></div><p></p><div><p><b>Para ingresar su nueva " +
                "contraseña ingrese al siguiente enlace:</b></p><p><b><a href=\"http://localhost:8080/PTeleshock_war_exploded/Login_Password?correo="+this.generarToken(dest,nombre)+"\">Ingrese su nueva contraseña</a></b></p></div><p></p><div><br>Saludos Cordiales</br><br><FONT COLOR=\"gray\">El equipo técnico de Teleshock</FONT></br></div>";
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
                InputStream iStream = getClass().getClassLoader().getResource("img/confirmacion_imagen.png").openStream();
                DataSource fds =  new InputStreamDataSource(iStream,"confirmacion_imagen","image/png");
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
    public String generarToken(String destinatario,String nombre){
        String token="";
        String key = "TeleshockToken";
        Algorithm algorithm = Algorithm.HMAC256(key);
        //creacion del token
        Date date = new Date();
        token = JWT.create()
                .withIssuer("Teleshock")//emisor
                .withSubject("email")//sujeto
                .withIssuedAt(date)
                .withExpiresAt(new Date(date.getTime()+540*1000L))//duracion de 9 min
                .withClaim("correo",destinatario)
                .withClaim("usuario",nombre)
                .sign(algorithm);
        return token;
    }
}
