
package HTTPGenerator;

import java.util.ArrayList;


public class Generator {
    private ArrayList<Item> itemsList;
    public Generator(){
        itemsList = new ArrayList<>();
    }

    public ArrayList<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<Item> itemsList) {
        this.itemsList = itemsList;
    }
    
    
}
