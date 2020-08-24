package sendMail;

import javax.mail.Session;
import javax.mail.Transport;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaMailTest {

    public static void sendMail(String recipient) throws Exception {

        System.out.println("Preparing to send Email");

        final String myAccountEmail = "example@gmail.com"; // Enter your gmail (sender)
        final String password = "*******"; // Enter your gmail password

        // Get the session object
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        // recall Message
        Message message = prepareMessage(session, myAccountEmail, recipient);

        // Send message
        Transport.send(message);
        System.out.println("Message Sent Successfully");
    }

    // Compose the message
    private static Message prepareMessage(Session session, String myAccountEmail, String recipient) {

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("My First Email from Java App");
            message.setText("Hello World, \nExample of sending email in Java through SMTP server provided by the host provider.");
            return message;

        } catch (Exception ex) {
            Logger.getLogger(JavaMailTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
