module com.viergui.vierguiwinnt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jfr;


    opens com.viergui.vierguiwinnt to javafx.fxml;
    exports com.viergui.vierguiwinnt;
}