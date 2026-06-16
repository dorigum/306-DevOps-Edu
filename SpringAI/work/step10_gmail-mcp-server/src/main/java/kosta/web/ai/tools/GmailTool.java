package kosta.web.ai.tools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class GmailTool {
    // JavaMailSender는 실제로 Gmail SMTP 서버를 통해 메일을 보내는 Spring의 메일 전송 객체
    private final JavaMailSender mailSender;

    public GmailTool(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Tool(description = "Send gmail")
    public String sendMail(@ToolParam(description = "Receiver email") String to,
                           @ToolParam(description = "Mail subject") String subject,
                           @ToolParam(description = "Mail body") String body) {

        try {
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(to);
            msg.setSubject(subject);
            msg.setText(body);
            mailSender.send(msg);

            return "Mail sent successfully";

        } catch (Exception e) {
            return "Mail send failed : " + e.getMessage();

        }
    }
}