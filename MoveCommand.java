import java.awt.*;
import java.util.*;
public class MoveCommand extends Command {
  private Item item;
  private Vector itemList;
  public MoveCommand() {
    itemList = new Vector();
    Enumeration enumeration = model.getSelectedItems();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      itemList.add(item);
    }
    model.deleteSelectedItems();
  }
  
  public void setPoint(Point point) {
    Enumeration enumeration = model.getItems();
    while (enumeration.hasMoreElements()) {
      item = (Item)(enumeration.nextElement());
      if (item.includes(point)) {
        model.markSelected(item);
        break;
      }
    }
}
  
  
  public boolean undo() {
    Enumeration enumeration = itemList.elements();
    while (enumeration.hasMoreElements()) {
      Item item = (Item)(enumeration.nextElement());
      model.addItem(item);
      model.markSelected(item);
    }
    return true;
  }
  public boolean redo() {
    execute();
    return true;
  }
  public void execute() {
    model.deleteSelectedItems();
  }
  }

