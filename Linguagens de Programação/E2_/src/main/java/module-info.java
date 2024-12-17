module org.example.e2_ {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens org.example.e2_ to javafx.fxml;
    exports org.example.e2_;
}