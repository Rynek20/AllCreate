
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
    
    public String generate (ArrayList<Item2> itemList){
        StringBuilder sb = new StringBuilder();
        for(Item2 item : itemList){
        sb.append("<div class=\"ramka1\">\n<img class=\"margines\" src=\"");
        sb.append(item.getPath())
                }
        return "";
    }
    
}
