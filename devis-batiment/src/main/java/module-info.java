module fr.insa.zoppi {
    requires javafx.controls;
    requires javafx.fxml;

    opens fr.insa.zoppi to javafx.fxml;
    exports fr.insa.zoppi;
}
