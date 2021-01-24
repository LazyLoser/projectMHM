package projectMHM;

import com.mysql.jdbc.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.sqlConnection;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;

public class ResetCon {
    @FXML
    private TextField forgetName,Fquestion1,Fquestion2,Fquestion3;
    @FXML
    private PasswordField forgetPas,forgotCPas;
    public void logout(ActionEvent actionEvent) throws SQLException, IOException {
        String a=forgetPas.getText();
        String b=forgotCPas.getText();
        String c=forgetName.getText();
        String d=Fquestion1.getText();
        String e=Fquestion2.getText();
        String f=Fquestion3.getText();
        if(a.compareTo("")==0||b.compareTo("")==0||c.compareTo("")==0||d.compareTo("")==0||e.compareTo("")==0||f.compareTo("")==0 )
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Oops!");
            alert.setHeaderText("Something went wrong!");
            alert.setContentText("There may be one or more blanks in TextFields.\nPlease Try Again.");
            forgetPas.clear();forgetName.clear();forgotCPas.clear();Fquestion3.clear();Fquestion2.clear();Fquestion1.clear();
            alert.showAndWait();
        }
       else if(a.compareTo(b)==0)
        {
            sqlConnection sqlConnection=new sqlConnection();
            Connection connection=sqlConnection.getConnection();
            PreparedStatement stmt;
            ResultSet rs;
            String query="update login set UserName=?,Password=?,SecurityQuestion1=?,SecurityQuestion2=?,SecurityQuestion3=? where ID=1";
            stmt=connection.prepareStatement(query);
            stmt.setString(1, forgetName.getText());
            stmt.setString(2, forgetPas.getText());
            stmt.setString(3, Fquestion1.getText());
            stmt.setString(4, Fquestion2.getText());
            stmt.setString(5, Fquestion3.getText());
            stmt.execute();
            stmt.close();
            forgetName.clear(); forgetPas.clear(); forgetPas.clear(); Fquestion1.clear(); Fquestion2.clear(); Fquestion3.clear();

            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Change Admin Password");
            alert.setHeaderText(null);
            alert.setContentText("Data has been Updated");
            alert.showAndWait();
            ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
            Parent root= FXMLLoader.load(getClass().getResource("/ProjectMHM/Login.fxml"));
            Stage stage=new Stage();
            Scene scene=new Scene(root);
            stage.setTitle("Mhat Htar Mal Login");
            stage.setScene(scene);
            stage.show();
            Alert alertp=new Alert(Alert.AlertType.INFORMATION);
            alertp.setTitle("Account has been reset!");
            alertp.setHeaderText(null);
            alertp.setContentText("Please Login again with new Password!");
            alertp.showAndWait();
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Password Mismatch");
            alert.setHeaderText(null);
            alert.setContentText("Password Mismatch. Please Try again!");
            alert.showAndWait();
        }
    }

    public void crash(ActionEvent actionEvent)throws IOException {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
        Parent root=FXMLLoader.load(getClass().getResource("/projectMHM/Login.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(root);
        stage.setTitle("Mhat Htar Mal Login");
        stage.setScene(scene);
        stage.show();
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Request Time out");
        alert.setHeaderText("Session Expired");
        alert.setContentText("Please Login Again");
        alert.showAndWait();
    }
}
