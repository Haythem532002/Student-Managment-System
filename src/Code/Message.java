package Code;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    String admin,message;

    public Message(String pseudo, String message) {
        this.admin = pseudo;
        this.message = message;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}

