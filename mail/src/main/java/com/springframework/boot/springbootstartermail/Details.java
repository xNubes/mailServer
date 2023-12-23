package com.springframework.boot.springbootstartermail;
public class Details {
    private String name = "Robin";

    private String subject = "Termin";

    private String message = "Ich will meinen Termin ändern";

    private String email ="troumble@gmail.com";

    private String receiver ="robin@juenemanns.de";

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
