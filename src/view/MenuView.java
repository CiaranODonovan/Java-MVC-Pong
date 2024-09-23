//Author Ciaran O'Donovan R00238437
package view;
import controller.MenuController;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;



/**
 * Constructs the main menu for the game, allowing players to input settings such as player names,
 * paddle dimensions, ball characteristics, and the winning score. This class binds user inputs to
 * the game configuration, leveraging the {@link MenuController} for game initialization and parameter
 * validation.
 */
public class MenuView {
    private final VBox layout;
    private final MenuController controller;
    private final TextField playerName1 = new TextField();
    private final TextField playerName2 = new TextField();
    private final TextField paddleHeight = new TextField();
    private final TextField paddleWidth = new TextField();
    private final TextField ballSpeedFrequency = new TextField();
    private final TextField initialBallSpeed = new TextField();
    private final TextField ballRadius = new TextField();
    private final TextField maxScore = new TextField();
    private final Button confirmButton = new Button("Confirm & Play");
    private final Button exitButton = new Button("Exit Program");
    private final BooleanProperty confirm = new SimpleBooleanProperty(false);


    /**
     * Initializes a new menu view with user-configurable settings for the game.
     * Sets up UI components and binds them to the provided {@link MenuController}.
     *
     * @param controller The controller responsible for handling the logic based on user input.
     */

    public MenuView(MenuController controller) {
        this.controller = controller;
        layout = new VBox(5);
        layout.setAlignment(Pos.CENTER);

        Label gameTitle = new Label("Pong!");
        gameTitle.setId("gameTitleLabel");

        //player 1 menu GUI
        Label playerName1Label = new Label("Player 1 Name");
        playerName1.setPromptText("Player 1 Name");
        playerName1.setText("Player1");
        playerName1.setMaxWidth(100);
        //player 2 menu GUI

        Label playerName2Label = new Label("Player 2 Name");
        playerName2.setPromptText("Player 2 Name");
        playerName2.setText("Player2");
        playerName2.setMaxWidth(100);
        //paddle height menu GUI

        Label paddleHeightLabel = new Label("Paddle Height");
        paddleHeight.setPromptText("Paddle Height");
        paddleHeight.setText("100");
        paddleHeight.setMaxWidth(100);
        //paddle width menu GUI

        Label paddleWidthLabel = new Label("Paddle Width");
        paddleWidth.setPromptText("Paddle Width");
        paddleWidth.setText("15");
        paddleWidth.setMaxWidth(100);
        //ball speed interval update menu GUI

        Label ballSpeedFrequencyLabel = new Label("Ball Speed Increase Frequency");
        ballSpeedFrequency.setPromptText("Ball Speed Increase Frequency");
        ballSpeedFrequency.setText("2");
        ballSpeedFrequency.setMaxWidth(100);

        //initial ball speed update menu GUI

        Label initialBallSpeedLabel = new Label("Ball Speed");
        initialBallSpeed.setPromptText("Ball Speed");
        initialBallSpeed.setText("2");
        initialBallSpeed.setMaxWidth(100);

        //ball radius menu GUI

        Label ballRadiusLabel = new Label("Ball Radius");
        ballRadius.setPromptText("Ball Radius");
        ballRadius.setText("15");
        ballRadius.setMaxWidth(100);

        //winning score menu GUI

        Label maxScoreLabel = new Label("Winning Score");
        maxScore.setPromptText("Winning Score");
        maxScore.setText("3");
        maxScore.setMaxWidth(100);

        //node layout
        layout.getChildren().addAll(gameTitle, playerName1Label, playerName1,
                playerName2Label, playerName2,
                paddleHeightLabel, paddleHeight,
                paddleWidthLabel, paddleWidth,
                ballSpeedFrequencyLabel, ballSpeedFrequency,
                initialBallSpeedLabel, initialBallSpeed,
                ballRadiusLabel, ballRadius,
                maxScoreLabel, maxScore,
                confirmButton, exitButton);

        //initialize event handling
        handleActions();
    }

    /**
     * Gets the layout of the menu, containing all UI components.
     *
     * @return The VBox containing the layout of the menu.
     */
    public VBox getLayout() {
        return this.layout;
    }

    /**
     * Retrieves the property indicating whether game settings have been confirmed.
     *
     * @return A BooleanProperty that is true if settings are confirmed, otherwise false.
     */
    public BooleanProperty getConfirm() {
        return confirm;
    }

    /**
     * Configures actions for the 'exit' and 'confirm' buttons.
     * 'exit' triggers application termination, while 'confirm' validates and processes input,
     * updating game settings via the controller.
     */
    public void handleActions() {
        //exit program
        exitButton.setOnAction(e -> {
            Platform.exit();
        });
        //start game after exception handling
        confirmButton.setOnAction(e -> {
            String player1Name = processTextFieldString(playerName1);
            if (player1Name.isEmpty()) {
                return;
            }
            controller.setNewPlayer1Name(player1Name);

            String player2Name = processTextFieldString(playerName2);
            if (player2Name.isEmpty()) {
                return;
            }
            controller.setNewPlayer2Name(player2Name);

            double height = processTextFieldDouble(paddleHeight);
            if (height <= 0) {
                return;
            }
            controller.setNewPaddleHeight(height);

            double width = processTextFieldDouble(paddleWidth);
            if (width <= 0) {
                return;
            }
            controller.setNewPaddleWidth(width);


            if (processTextFieldInt(maxScore) <= 0) {
                return;
            }
            controller.setNewWinningScore(processTextFieldInt(maxScore));

            int speedFrequency = processTextFieldInt(ballSpeedFrequency);
            if (processTextFieldInt(ballSpeedFrequency) <= 0) {
                return;
            }
            controller.setNewBallSpeedFrequency(speedFrequency);

            int initialSpeed = processTextFieldInt(initialBallSpeed);
            if (processTextFieldInt(initialBallSpeed) <= 0) {
                return;
            }
            controller.setNewInitialBall(initialSpeed);

            double radius = processTextFieldDouble(ballRadius);
            if (radius <= 0) {
                return;
            }
            controller.setNewBallRadius(radius);

            confirm.set(true);
        });

    }

    /**
     * Processes and validates the text from a TextField as a String.
     * Displays an error and returns an empty string if the input is invalid.
     *
     * @param textField The TextField containing the input string.
     * @return The validated string, or an empty string if validation fails.
     */
    public String processTextFieldString(TextField textField) {
        if (textField.getText().isEmpty()) {
            showErrorMessage("Invalid input. Please enter a name.");
        }

        return textField.getText();


    }

    /**
     * Processes and validates the text from a TextField as a double.
     * Displays an error message if the input is invalid or negative.
     *
     * @param textField The TextField containing the input string.
     * @return The validated double value, or 0.0 if validation fails.
     */
    public double processTextFieldDouble(TextField textField) {

        double num = 0;
        try {
            num = Double.parseDouble(textField.getText());
            if (num > 0) {
                return num;
            } else
                showErrorMessage("Invalid input. Please enter a valid Double value.");
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid input. Please enter a valid Double value.");


        }
        return num;
    }

    /**
     * Processes and validates the text from a TextField as an integer.
     * Displays an error message if the input is invalid or negative.
     *
     * @param textField The TextField containing the input string.
     * @return The validated integer value, or 0 if validation fails.
     */
    public int processTextFieldInt(TextField textField) {
        //default size
        int num = 0;
        try {

            num = Integer.parseInt(textField.getText());
            if (num > 0) {
                return num;
            } else
                showErrorMessage("Invalid input. Please enter a valid Double value.");
        } catch (NumberFormatException e) {
            showErrorMessage("Invalid input. Please enter a valid integer value.");

        }
        return num;
    }

    /**
     * Displays an error message in an alert dialog.
     *
     * @param message The message to display in the alert dialog.
     */
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}