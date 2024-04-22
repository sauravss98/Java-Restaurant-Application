module cafe94.group14a2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;

    opens cafe94.group14a2 to javafx.fxml;
    opens  User to javafx.fxml;
    exports User;
//    exports User;
    exports cafe94.group14a2;
    exports Orders;
    opens Orders to javafx.fxml;
    opens Reservation to javafx.fxml;
    exports Reservation;
    opens Items to javafx.fxml;
    exports Items;
}