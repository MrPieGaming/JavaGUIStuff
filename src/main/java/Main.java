import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Scene scene1, scene2;
    private Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        Label label1 = new Label("Welcome to my little JavaFX Program!");
        Label label2 = new Label("Below are a bunch of buttons that test functionality");

        //Switch Scene Button
        Button button1 = new Button();
        button1.setText("Press here to go forward");
        button1.setOnAction(e -> window.setScene(scene2)); // This is a lambda. It uses code context to know what I'm talking about.

        //Go back Button
        Button button2 = new Button();
        button2.setText("Press here to go back");
        button2.setOnAction(e -> window.setScene(scene1));

        //Alert Message Button
        Button button3 = new Button();
        button3.setText("Click here for an alert!");
        button3.setOnAction(e -> AlertBox.display("Alert", "Here's your alert!"));

        //Exit Application Button
        Button exitButton = new Button();
        exitButton.setText("Exit Application");
        exitButton.setOnAction(e -> closeProgram());

        //Confirm Message Button
        Button button4 = new Button();
        button4.setText("Click here to confirm something!");
        button4.setOnAction(e -> {
            boolean result = ConfirmBox.display("Confirmation", "Are you sure you want to submit this assignment?");
            Stage confirmMsg = new Stage();
            Label msg;
            VBox msgLayout;
            Scene scene3;

            Button okButton = new Button("Ok");
            okButton.setOnAction(ee -> confirmMsg.close());

            if (result) {
                confirmMsg.setMinWidth(300);
                confirmMsg.setMinHeight(130);

                msg = new Label("Confirmed.");

                msgLayout = new VBox();
                msgLayout.getChildren().addAll(msg, okButton);
                msgLayout.setSpacing(10);
                msgLayout.setAlignment(Pos.CENTER);

                scene3 = new Scene(msgLayout);
                confirmMsg.setScene(scene3);
                confirmMsg.show();
            } else {
                confirmMsg.setMinWidth(300);
                confirmMsg.setMinHeight(130);

                msg = new Label("Un-Confirmed.");

                msgLayout = new VBox();
                msgLayout.getChildren().addAll(msg, okButton);
                msgLayout.setSpacing(10);
                msgLayout.setAlignment(Pos.CENTER);

                scene3 = new Scene(msgLayout);
                confirmMsg.setScene(scene3);
                confirmMsg.show();
            }
        });

        //Layout 1
        VBox layout1 = new VBox(20);
        layout1.getChildren().addAll(label1, label2, button1, button3, button4, exitButton);
        layout1.setAlignment(Pos.CENTER);
        scene1 = new Scene(layout1, 300, 300);

        //Layout 2
        StackPane layout2 = new StackPane();
        layout2.getChildren().add(button2);
        layout2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2, 300, 300);

        window.setScene(scene1);
        window.setTitle("Test JavaFX Program");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });
        window.show();
    }

    private void closeProgram() {
        boolean answer = ConfirmBox.display("Exit Window", "Are you sure you want to exit?");

        if (answer) {
            System.out.println("Closing application...");
            window.close();
        }
    }
}