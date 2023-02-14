module com.example.modul326 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens com.example.modul326 to javafx.fxml;
    exports com.example.modul326;
}