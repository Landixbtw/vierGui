module com.viergui.vierguiwinnt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.viergui.vierguiwinnt to javafx.fxml;
    exports com.viergui.vierguiwinnt;
}