module aqua {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires sqljdbc42;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
	opens controller to javafx.graphics, javafx.fxml;

	
}
