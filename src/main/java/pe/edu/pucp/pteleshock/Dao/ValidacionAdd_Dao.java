package pe.edu.pucp.pteleshock.Dao;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.*;
import java.util.ArrayList;
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
            ResultSet rs = statement.executeQuery("select u.correo from usuario u where u.idrol=3");
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
        String asunto = "Confirmación de registro de cuenta";
        String mensaje = "<p><b> Estimada(o) "+nombre+": </b></p><div>Es grato comentarle que su cuenta se ha registrado correctamente</div><p></p><div><img src=\"cid:image\"></div><p></p><div><br>Saludos Cordiales</br><br><FONT COLOR=\"gray\">El equipo técnico de Teleshock</FONT></br></div>";
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
            DataSource fds =  new FileDataSource("C:\\Users\\casa\\Desktop\\Ingenieria Web\\confirmacion_imagen.png");
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
