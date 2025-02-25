package app.view;

import app.model.entity.Child;
import app.model.entity.Father;
import app.service.TableService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import app.view.grid.ListedGrid;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@Route
public class MainView extends VerticalLayout {

    TableService tableService;

    HorizontalLayout tableBlock;
    VerticalLayout comboBoxPanel, buttonPanel;

    ComboBox<Father> comboBox;
    ListedGrid<Child> leftTable, rightTable;

    RadioButtonGroup<String> selectTableButtonGroup;

    Button toLeftButton;
    Button toRightButton;

    @Autowired
    public MainView(TableService tableService){

        this.tableService = tableService;

        tableBlock = new HorizontalLayout();
        comboBoxPanel = new VerticalLayout();
        buttonPanel = new VerticalLayout();

        comboBox = new ComboBox<>();
        leftTable = new ListedGrid<>();
        rightTable = new ListedGrid<>();
        selectTableButtonGroup = new RadioButtonGroup<>();

        toLeftButton = new Button("<");
        toRightButton = new Button(">");

        selectTableButtonGroup.setLabel("Select table:");
        selectTableButtonGroup.setItems("Left", "Right");

        comboBoxPanel.add(comboBox, selectTableButtonGroup);
        buttonPanel.add(toLeftButton, toRightButton);
        tableBlock.add(comboBoxPanel, leftTable, buttonPanel, rightTable);

        tableBlock.setSizeFull();

        add(tableBlock);

        buttonPanel.setJustifyContentMode(JustifyContentMode.CENTER);
        buttonPanel.getStyle().setWidth(null);
        comboBoxPanel.getStyle().setWidth(null);
        leftTable.setSizeFull();
        rightTable.setSizeFull();

        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();

        comboBox.setEnabled(false);

    }

    @PostConstruct
    protected void initListeners(){
        toLeftButton.addClickListener(e -> {
            moveItem(0);
        });

        toRightButton.addClickListener(e -> {
            moveItem(1);
        });

        comboBox.addValueChangeListener(e -> {
            setTables();
        });

        selectTableButtonGroup.addValueChangeListener(e -> {
            radioButtonGroupAction();
        });
    }

    protected void moveItem(int mode){
        if (rightTable.getItemList() == null || leftTable.getItemList() == null){
            return;
        }

        Set<Child> bufferList;
        switch (mode){
            case 0 -> {
                bufferList = rightTable.getSelectedItems();
                rightTable.removeAll(bufferList);
                leftTable.addAll(bufferList);
            }

            case 1 -> {
                bufferList = leftTable.getSelectedItems();
                leftTable.removeAll(bufferList);
                rightTable.addAll(bufferList);
            }
        }
    }

    protected void setTables(){
        switch(selectTableButtonGroup.getValue()){
            case "Left" -> {
                if (rightTable.getItemList() != comboBox.getValue().getChildList()){

                    leftTable.removeAllColumns();
                    leftTable.addColumn(Child::getName).setHeader("Name");
                    leftTable.addColumn(Child::getGender).setHeader("Gender");
                    leftTable.addColumn(Child::getAge).setHeader("Age");

                    leftTable.setItemList((comboBox.getValue()).getChildList());
                }
            }
            case "Right" -> {
                if (leftTable.getItemList() != comboBox.getValue().getChildList()){

                    rightTable.removeAllColumns();
                    rightTable.addColumn(Child::getName).setHeader("Name");
                    rightTable.addColumn(Child::getGender).setHeader("Gender");
                    rightTable.addColumn(Child::getAge).setHeader("Age");

                    rightTable.setItemList(((Father)comboBox.getValue()).getChildList());
                }
            }
        }
    }

    protected void radioButtonGroupAction(){
        comboBox.setItems(tableService.getTableItems());
        comboBox.setEnabled(true);
    }
}
