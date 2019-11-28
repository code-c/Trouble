import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class SelectGUI {

    private VBox selectionPage;


    public SelectGUI(){

        selectionPage = new VBox();
        setUp(selectionPage);

    }

    private void setUp(VBox page){
        HBox boxes = new HBox(10);
        boxes.setAlignment(Pos.CENTER);
        for (int i = 0; i < 4; i++) {
            StackPane stacked = new StackPane();
            Label selection = new Label("NONE");
            Rectangle r = new Rectangle();
            r.setWidth(150);
            r.setHeight(150);
            r.setArcWidth(20);
            r.setArcHeight(20);
            r.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent t) {
                    String text = selection.getText();
                    if (text == "NONE") {
                        selection.setText("PLAYER");
                    } else if (text == "PLAYER") {
                        selection.setText("CPU");
                    } else if (text == "CPU") {
                        selection.setText("NONE");
                    }
                }
            });
            switch(i) {
                case 0: //red
                    r.getStyleClass().add("redSelectSquare");
                    break;
                case 1: //yellow
                    r.getStyleClass().add("yellowSelectSquare");
                    break;
                case 2: //green
                    r.getStyleClass().add("greenSelectSquare");
                    break;
                case 3: //blue
                    r.getStyleClass().add("blueSelectSquare");
                    break;
                default:
                    break;
            }
            stacked.getChildren().addAll(r, selection);
            boxes.getChildren().addAll(stacked);
        }


        HBox labels = new HBox(5);

        page.setAlignment(Pos.CENTER);
        page.getChildren().addAll(boxes, labels);


    }


    public VBox getPage(){
        return selectionPage;
    }
}
