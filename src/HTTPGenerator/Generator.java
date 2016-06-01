package HTTPGenerator;

import java.util.ArrayList;

public class Generator {

    private ArrayList<Item> itemsList;

    public Generator() {
        itemsList = new ArrayList<>();
    }

    public ArrayList<Item> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public String generate(ArrayList<Item2> itemList) {
        StringBuilder sb = new StringBuilder();
        for (Item2 item : itemList) {
            sb.append("\n<div class=\"ramka1\">\n"
                    + " <img class=\"margines\" src=\""+item.getPatternPath()+"\" usemap=\"#map\"/>\n"
                    + "	<div class=\"outside\">\n"
                    + "	 <span class=\"brak\"/>\n"
                    + "	 <span class=\"tytul\">"+item.getName()+" <font color=\"#FAB800\">"+item.getModel()+"</font></span>\n"
                    + "	 <div class=\"view\" style=\"background: url('"+item.getPath()+item.getFolder()+"/K/0.jpg"+"');\">\n"
                    + "	 </div>\n"
                    + "	 <div class=\"mini\">\n"
                    + "	  <div class=\"minFoto\">\n"
                    + "	   <img style=\"left:10px;\" src=\""+item.getPath()+item.getFolder()+"/K/0.jpg"+"\"/>\n"
                    + "	   <span style=\"background:  url('"+item.getPath()+item.getFolder()+"/K/0.jpg"+"')\"class=\"panel\"/>\n"
                    + "	 </div>\n"
                    + "	 <div class=\"minFoto\">\n"
                    + "	  <img style=\"left:170px;\"src=\""+item.getPath()+item.getFolder()+"/K/1.jpg"+"\"/>\n"
                    + "	  <span style=\"background:  url('"+item.getPath()+item.getFolder()+"/K/1.jpg"+"');\" class=\"panel\"/>\n"
                    + "	 </div>\n"
                    + "	 <div class=\"minFoto\">\n"
                    + "	  <img style=\"left:330px;\" src=\""+item.getPath()+item.getFolder()+"/K/2.jpg"+"\"/>\n"
                    + "	  <span style=\"background:  url('"+item.getPath()+item.getFolder()+"/K/2.jpg"+"')\"class=\"panel\"/>\n"
                    + "	 </div>\n"
                    + "	 <div class=\"minFoto\">\n"
                    + "	  <img style=\"left:490px;\" src=\""+item.getPath()+item.getFolder()+"/K/3.jpg"+"\"/>\n"
                    + "	  <span style=\"background:  url('"+item.getPath()+item.getFolder()+"/K/3.jpg"+"')\"class=\"panel\"/>\n"
                    + "	 </div>\n"
                    + "	 <div class=\"minFoto\">\n"
                    + "	  <img style=\"left:650px;\" src=\""+item.getPath()+item.getFolder()+"/K/4.jpg"+"\"/>\n"
                    + "	  <span style=\"background:  url('"+item.getPath()+item.getFolder()+"/K/4.jpg"+"')\"class=\"panel\"/>\n"
                    + "</div></div></div></div>");
        }
        return sb.toString();
    }

}
