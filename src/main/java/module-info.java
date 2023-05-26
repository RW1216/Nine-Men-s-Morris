module com.example.ninemensmorris {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ninemensmorris to javafx.fxml;
    exports com.example.ninemensmorris;
}