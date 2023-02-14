module com.example.modul326 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.modul326 to javafx.fxml;
    exports com.example.modul326;
}