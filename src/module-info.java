/**
 * 
 */
/**
 * 
 */
module Proyecto_EV1_DEIN {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    
	opens application to javafx.graphics, javafx.fxml;
	opens controllers to javafx.graphics, javafx.fxml;
	opens model to javafx.base;
	opens dao to javafx.base;
}