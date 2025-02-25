package app.view.grid;

import com.vaadin.flow.component.grid.Grid;

import java.util.Collection;
import java.util.List;

public class ListedGrid<T> extends Grid<T> {

    private List<T> itemList;

    public ListedGrid(){

    }

    public ListedGrid(Class<T> beanType, boolean autoCreateColumns){
        super(beanType, autoCreateColumns);
    }

    public void setItemList(List<T> itemList) {
        this.itemList = itemList;
        refreshItems();
    }

    public List<T> getItemList() {
        return itemList;
    }

    public void addAll(Collection<T> itemList){
        this.itemList.addAll(itemList);
        refreshItems();
    }

    public void removeAll(Collection<T> itemList){
        this.itemList.removeAll(itemList);
        refreshItems();
    }

    public void clean(){
        removeAll(itemList);
        itemList = null;
    }

    private void refreshItems()
    {
        this.setItems(itemList);
    }

}
