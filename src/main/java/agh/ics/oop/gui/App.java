package agh.ics.oop.gui;
import agh.ics.oop.MoveDirection;
import agh.ics.oop.Observers.IPositionChangeObserver;
import agh.ics.oop.Tools.IEngine;
import agh.ics.oop.Tools.OptionsParser;
import agh.ics.oop.Tools.SimulationEngine;
import agh.ics.oop.Vector2d;
import agh.ics.oop.WorldMaps.GrassField;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class App extends Application {

    int stageHeight;
    int stageWidth;
    int squareSize = 50;
    ArrayList<IPositionChangeObserver> boundaries;
    GrassField map;
    IEngine engine;



    public void init() throws Exception{
        super.init();
        MoveDirection[] directions = new OptionsParser().parse(getParameters().getRaw().toArray(new String[0]));
        map = new GrassField(20);
        Vector2d[] positions = { new Vector2d(19,5), new Vector2d(4,11) };
        engine = new SimulationEngine(directions, map, positions, boundaries);

    }

    public void start(Stage primaryStage){
        Vector2d lowerLeft = map.getMapLeftBottom();
        Vector2d upperRight = map.getMapRightUpper() ;

        Scene scene = new Scene(myGrid(lowerLeft, upperRight), stageWidth, stageHeight);
        primaryStage.setScene(scene);
        primaryStage.show();


        engine.run();

    }

    public GridPane myGrid(Vector2d lowerLeft, Vector2d upperRight){

        // +2 because we need +1 default and +1 for axels description
        int columnCount = upperRight.x - lowerLeft.x + 1;
        int rowCount = upperRight.y - lowerLeft.y + 1;

        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);


        // Grid Preparing
        for ( int i = 0; i <= columnCount; i++ )
            grid.getColumnConstraints().add(new ColumnConstraints( squareSize ));
        for ( int i = 0; i <= rowCount; i++ )
            grid.getRowConstraints().add(new RowConstraints( squareSize ));


        //Labeling the axels
        Label xy = new Label("x|y");
        grid.add(xy, 0, 0);

        for ( int i = 1; i <= columnCount; i++ ) {
            Label label = new Label(String.valueOf( lowerLeft.x + i - 1));
            GridPane.setHalignment( label, HPos.CENTER );
            grid.add(label, i, 0);
        }

        for ( int i = 1; i <= rowCount; i++ ) {
            Label label = new Label(String.valueOf( upperRight.y - i + 1));
            GridPane.setHalignment( label, HPos.CENTER );
            grid.add(label, 0, i);
        }


        // adding elements to the Grid
        map.elementsList.forEach((coords, element) -> {
            Label label = new Label(element.toString());

            GuiElementBox guiElementBox = null;
            try {
                guiElementBox = new GuiElementBox(element);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            GridPane.setHalignment( label, HPos.CENTER );
            grid.add(guiElementBox.container, coords.x+1, rowCount-coords.y,1,1);
        });


        // setting stage size
        this.stageWidth = squareSize*(columnCount+2);
        this.stageHeight = squareSize*(rowCount+2);
        return grid;
    }
}
