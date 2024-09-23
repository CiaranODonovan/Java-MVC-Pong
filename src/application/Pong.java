package application;
import controller.BallManager;
import controller.GameController;
import controller.KeyboardListener;
import controller.MenuController;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import view.GameView;
import view.MenuView;

/**
 * The main class for the Pong game application. This class sets up the game environment,
 * including the game view, menu view, and controllers. It also handles the game loop and
 * transitions between the game and menu scenes.
 */
public class Pong extends Application {
    //main game logic controller
    private final GameController gameController = new GameController();
    //game view
    private GameView canvas;
    //menu logic controller
    private final  MenuController menuController = new MenuController(gameController.getGame());
    //menu view
    private final MenuView menu = new MenuView(menuController);

    /**
     * Starts the application, initializing the primary stage with the game and menu scenes.
     * Sets up the game loop and manages scene transitions based on user input.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        //initialize game root
        BorderPane root = new BorderPane();
        //initialize game canvas
        canvas = new GameView(gameController.getGame().getWidth(),gameController.getGame().getHeight());
        root.setCenter(canvas);
        //initialize game scene
        Scene gameScene = new Scene(root,gameController.getGame().getWidth(),gameController.getGame().getHeight());
        //initialize menu scene
        Scene menuScene = new Scene(menu.getLayout(),300,800);
        //initialize ball management
        BallManager ballManager = new BallManager(gameController);
        //initialize separate ball thread
        Thread thread = new Thread(ballManager);
        //ensure thread correctly exits on window close
        thread.setDaemon(true);
        //initialize pause for score message
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        //initialize keyboard Listener
        KeyboardListener keyboardListener = new KeyboardListener(gameController.getGame());
        //add css components
        String css = this.getClass().getResource("application.css").toExternalForm();
        gameScene.getStylesheets().add(css);
        menuScene.getStylesheets().add(css);



        //Main Game animation
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                //initialize Key listening
                canvas.setOnKeyPressed(keyboardListener);
                canvas.setOnKeyReleased(keyboardListener);
                canvas.setFocusTraversable(true);
                keyboardListener.paddleMovement();

                if(keyboardListener.isGamePaused()) {
                    ballManager.pause();
                    keyboardListener.setWasPaused(true);
                }

                else if(!keyboardListener.isGamePaused() && keyboardListener.isWasPaused()){
                    ballManager.resume();
                    keyboardListener.setWasPaused(false);

                }


                if(keyboardListener.isGameRestart()){
                    gameController.resetGameState(gameController.getGame().getBall().getRandom().nextBoolean()
                            ? gameController.getGame().getBall().getInitialSpeed() : -gameController.getGame().getBall().getInitialSpeed());
                    gameController.getGame().getPlayer1().setScore(0);
                    gameController.getGame().getPlayer2().setScore(0);
                    keyboardListener.setGameRestart(false);
                }


                //pause during score message
                if(canvas.showingScoredMessage(gameController.checkIfScored(),gameController.getGame())){
                    pause.play();
                    pause.setOnFinished(event -> {
                                //reset after pause


                                gameController.resetGameState(gameController.getGame().getBall().getRandom().nextBoolean()
                                        ? gameController.getGame().getBall().getInitialSpeed() : -gameController.getGame().getBall().getInitialSpeed());
                                start();
                    });
                    //stop game loop and ball thread during pause
                    stop();
                    thread.yield();
                }

                else if(gameController.checkIfWon()){
                    canvas.drawGame(gameController.getGame());
                    canvas.showingWonMessage(gameController.checkWhoWon(),gameController.getGame());
                    pause.play();
                    pause.setOnFinished(event1 -> {
                        gameController.resetGameState(gameController.getGame().getBall().getRandom().nextBoolean()
                                ? gameController.getGame().getBall().getInitialSpeed() : -gameController.getGame().getBall().getInitialSpeed());
                        start();});
                    stop();
                    thread.yield();
                }

                else if (!keyboardListener.isGamePaused()){
                    canvas.drawGame(gameController.getGame());}



            }};
        //Window resizing Listeners & render update
        gameScene.widthProperty().addListener(observable ->{
            double factor = gameScene.getWidth()/gameController.getGame().getWidth();
            gameController.getGame().setWidth(gameScene.getWidth());
            gameController.getGame().resizeX(factor);
            gameController.getGame().getPlayer2().getPaddle().getX();
            canvas.drawGame(gameController.getGame());

        });
        gameScene.heightProperty().addListener(observable ->{
            double factor = gameScene.getHeight()/gameController.getGame().getHeight();
            gameController.getGame().setHeight(gameScene.getHeight());
            gameController.getGame().resizeY(factor);
            canvas.drawGame(gameController.getGame());

        });
        //confirmation button listener & start game conditional
        menu.getConfirm().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                primaryStage.setTitle("Pong!");
                gameLoop.start();
                thread.start();
                primaryStage.setScene(gameScene);
                primaryStage.setFullScreen(!primaryStage.isFullScreen());
            }
        });
        //initialize Stage to main menu
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(menuScene);
        primaryStage.show();


    }
    /**
     * The main entry point for all JavaFX applications. Launches the JavaFX application.
     *
     * @param args Command line arguments passed to the application.
     *             Not used in this application.
     */

    public static void main(String[] args) {
        launch(args);
    }
}