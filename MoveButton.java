import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class MoveButton  extends JButton implements ActionListener {
  protected JPanel drawingPanel;
  protected View view;
  private MouseHandler mouseHandler;
  private MoveCommand moveCommand;
  private UndoManager undoManager;
  public MoveButton(UndoManager undoManager, View jFrame, JPanel jPanel) {
    super("Move");
    this.undoManager = undoManager;
    addActionListener(this);
    view = jFrame;
    drawingPanel = jPanel;
    mouseHandler = new MouseHandler();
  }
  public void actionPerformed(ActionEvent event) {
    view.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
    // Change cursor when button is clicked
    drawingPanel.addMouseListener(mouseHandler);
  // Start listening for mouseclicks on the drawing panel
  }
  private class MouseHandler extends MouseAdapter {
    private int click = 0;
    public void mouseClicked(MouseEvent event) {
        if (click == 0) {
            moveCommand.setPoint(View.mapPoint(event.getPoint()));
            click++;
        } else {// (click == 2) {
           // moveCommand.placeItem();
            drawingPanel.removeMouseListener(this);
            undoManager.endCommand(moveCommand);
            click = 1;
        }
    }
  }
}