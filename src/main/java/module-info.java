module com.example.bitwardendesignconcept_demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.lucene.core;
    requires org.apache.lucene.analysis.common;
    requires java.sql;
    requires lucene.queryparser;


    exports com.example.bitwardendesignconcept_demo.Controllers;
    opens com.example.bitwardendesignconcept_demo.Controllers to javafx.fxml;
    exports com.example.bitwardendesignconcept_demo.Components;
    opens com.example.bitwardendesignconcept_demo.Components to javafx.fxml;
    exports com.example.bitwardendesignconcept_demo;
    opens com.example.bitwardendesignconcept_demo to javafx.fxml;
}
