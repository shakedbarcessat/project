module com.example.pentagogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pentagogame to javafx.fxml;
    exports com.example.pentagogame;
    exports com.example.pentagogame.Controller;
    opens com.example.pentagogame.Controller to javafx.fxml;
    exports com.example.pentagogame.View;
    opens com.example.pentagogame.View to javafx.fxml;
}