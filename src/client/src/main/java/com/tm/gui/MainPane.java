package com.tm.gui;

import java.util.Arrays;
import java.util.List;

import com.tm.entity.ItemGroup;
import com.tm.gui.model.ItemView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class MainPane extends VBox {
	
	ObservableList<String> options = 
		    FXCollections.observableArrayList(
		        "Option 1",
		        "Option 2",
		        "Option 3"
		    );
		final ComboBox groupsComboBox = new ComboBox(options);
	

	private TableView itemsTable = new TableView();
	
	
	
	private final ObservableList<ItemView> data =
	        FXCollections.observableArrayList(
	            new ItemView("1", "Smith"),
	            new ItemView("2", "Johnson")
	            );
	
	private TreeItem<String> groupTreeRootItem = new TreeItem<String> ("Категории");    
	private TreeView<String> groupTree = new TreeView<String>(groupTreeRootItem);  
	
	
	public MainPane() {
        init();
    }
	
	private void init() {
		
		itemsTable.setEditable(true);
		
		
		 
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(
                new PropertyValueFactory<ItemView, String>("id"));
        
        
        
        TableColumn nameCol = new TableColumn("Название");
        nameCol.setCellValueFactory(
                new PropertyValueFactory<ItemView, String>("name"));
        nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        nameCol.setOnEditCommit(
            new EventHandler<CellEditEvent<ItemView, String>>() {
                @Override
                public void handle(CellEditEvent<ItemView, String> t) {
                    ((ItemView) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setName(t.getNewValue());
                }
            }
        );
        
        itemsTable.setItems(data);
        itemsTable.getColumns().addAll(idCol, nameCol);
        
        final TextField addId = new TextField();
        addId.setPromptText("ID");
        addId.setMaxWidth(idCol.getPrefWidth());
        
        final TextField addName = new TextField();
        addName.setPromptText("Название");
        addName.setMaxWidth(nameCol.getPrefWidth());

        final Button addButton = new Button("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                data.add(new ItemView(
                		addId.getText(),
                		addName.getText()));
                addId.clear();
                addName.clear();
            }
        });
        
        
        
        
        
        
        
        

        getChildren().addAll(groupTree, itemsTable, addButton, addId, addName);
        setAlignment(Pos.CENTER_LEFT);
        setSpacing(5);
	}
	
	public void fillItemsGroups(List<ItemGroup> itemGroups, TreeItem<String> treeItem) {
		for (ItemGroup group : itemGroups) {
            TreeItem<String> empLeaf = new TreeItem<String>(group.getName());
            treeItem.getChildren().add(empLeaf);
            if (!group.getChildGroups().isEmpty()) {
            	fillItemsGroups(group.getChildGroups(), empLeaf);
            }
        } 
	}
	
	public void expandTreeItem(TreeItem<String> treeItem) {
		treeItem.setExpanded(true);
		for (TreeItem<String> child : treeItem.getChildren()) {
			expandTreeItem(child);
		}
	}

	public ComboBox getGroupsComboBox() {
		return groupsComboBox;
	}

	public TableView getItemsTable() {
		return itemsTable;
	}

	public void setItemsTable(TableView itemsTable) {
		this.itemsTable = itemsTable;
	}

	public TreeItem<String> getGroupTreeRootItem() {
		return groupTreeRootItem;
	}
	
	
}
