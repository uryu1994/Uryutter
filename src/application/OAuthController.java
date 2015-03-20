package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;

public class OAuthController {
    
    private String pincode;
    
    @FXML
    private PasswordField pin;
    
    @FXML
    protected void pinAuth(ActionEvent ev) {
        pincode = pin.getText();
    }
    
    public OAuthController() {
        // TODO Auto-generated constructor stub
        FXMLLoader loader = new FXMLLoader(getClass().getResource("OAuth.fxml"));
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getPincode() {
        return pincode;
    }

}
