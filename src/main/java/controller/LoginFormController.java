package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.LoginInfoDTO;

import java.io.IOException;

public class LoginFormController {
    Stage stage = new Stage();
    @FXML
    private CheckBox chkRemember;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    ObservableList<LoginInfoDTO> loginInfoDTOS = FXCollections.observableArrayList(
            new LoginInfoDTO("Admin", 1234),
            new LoginInfoDTO("Rishindu", 2005),
            new LoginInfoDTO("User", 1111)
    );

    public boolean checkPassword(String username, String password) {
        for (LoginInfoDTO user : loginInfoDTOS) {
            if (username.equals(user.getUsername()) && Integer.parseInt(password) == user.getPassword()) {
                return true;
            }
        }
        return false;
    }

    public void cleanText() {
        txtUsername.setText("");
        txtPassword.setText("");
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        boolean isUser = checkPassword(txtUsername.getText(), txtPassword.getText());
        if (isUser) {
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboard.fxml"))));
                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            stage.setTitle("Dashboard");
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid User");
            alert.setContentText("Login Failed!");
            alert.showAndWait();
            cleanText();
        }
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        btnLoginOnAction(actionEvent);
    }

    public void txtUsernameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }
}