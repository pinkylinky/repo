package com.tm.gui;

import java.util.ArrayList;
import java.util.List;

import com.tm.core.CoreManager;
import com.tm.entity.ItemGroup;
import com.tm.exception.BusinessLogicException;
import com.tm.exception.RemoteAccessException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;

public class MainPaneController {

	private CoreManager coreManager;
	private MainPane mainPane;
//	
//	
	public MainPaneController(MainPane mainPane) 
			throws RemoteAccessException, BusinessLogicException {
		this.mainPane = mainPane;
		coreManager = new CoreManager();
		init();
	}
	
	public void init() throws BusinessLogicException {
        List<ItemGroup> itemGroups = coreManager.getItemGroups();
        mainPane.fillItemsGroups(itemGroups, mainPane.getGroupTreeRootItem());
        mainPane.expandTreeItem(mainPane.getGroupTreeRootItem());
	}
//	
//	private void initManager() {
//		AppConfig.configure("src");
//        manager = new TestManager();
//        try {
//			manager.parseFile("C:\\Linky\\Study\\Языки\\Словари\\Немецкий.xls");
//		} catch (Exception e) {
//			mainPane.getErrorLabel().setText(e.getMessage());
//			return;
//		}       
//	}
//	
//	private EventHandler<ActionEvent> getTestHandler() {
//		return new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//            	if (!running) 
//            		return;           	
//            	
//            	mainPane.getPrevQuestionLabel().setText(mainPane.getQuestionLabel().getText());	
//            	mainPane.getCorrectAnswerLabel().setText(runner.getCorrectAnswer(word));
//            	mainPane.getPrevAnswerLabel().setText("Было: " + mainPane.getAnswerInput().getText());
//            	
//            	boolean answerCorrect = runner.checkAnswer(mainPane.getAnswerInput().getText(), word);
//            	
//            	if (answerCorrect) {          			
//        			mainPane.getResultLabel().setTextFill(Color.GREEN);
//        			mainPane.getResultLabel().setText("Верно!");
//            	} else {
//            		mainPane.getResultLabel().setTextFill(Color.RED);
//            		mainPane.getResultLabel().setText("Неверно!");
//            	}
//            	word = runner.getNextWord();
//            	if (word != null) {
//            		mainPane.getAnswerInput().clear();
//            		mainPane.getQuestionLabel().setText(word.getValue(Lang.RU.toString()));
//            	} else {
//            		finish();
//            	}
//            }
//        };
//	}
//	
//	private EventHandler<ActionEvent> getStartFinishHandler() {
//		return new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//            	if (running) {
//            		finish();
//            	} else {
//            		start();
//            	}
//            }
//        };
//	}
//	
//	private void setLanguageToggle(List<String> langs) {
//		int langCounter = 0;
//        for (String lang : langs) {
//        	if (lang != null && lang.equals(Lang.RU.toString()))
//        		continue;
//        	langCounter++;
//        	RadioButton rb1 = new RadioButton(lang);
//        	if (langCounter == 1)
//        		rb1.setSelected(true);
//            rb1.setToggleGroup(mainPane.getLanguageToggle());
//
//            mainPane.getLanguageBox().getChildren().add(rb1);
//        }
//	}
//	
//	private void setSectionToggle(TestSectionsCollection sections) {
//
//		sectionCheckboxes = new ArrayList<CheckBox>();
//        for (String sectionName : sections.keySet()) {
//        	CheckBox cb1 = new CheckBox(sectionName);
//        	cb1.setSelected(true);
//        	sectionCheckboxes.add(cb1);
//            mainPane.getSectionBox().getChildren().add(cb1);
//        }
//	}
//    
//    private void start() {
//    	running = true;
//    	clearView();
//    	resetRunner();
//    	fillNextWord();
//    	mainPane.getStartFinishButton().setText("ФИНИШ");
//    }
//    
//    private void resetRunner() {
//    	TestWordsCollection words = sheet.getWords(getActiveSectionNames());
//    	lang = ((RadioButton) mainPane.getLanguageToggle().getSelectedToggle()).getText();
//		runner = new TestRunner(words, Lang.RU.toString(), lang);		
//	}
//
//	private void finish() {
//    	running = false;
//    	mainPane.getAnswerInput().clear();
//    	//clearView();
//    	mainPane.getQuestionLabel().setText("КОНЕЦ. " + runner.getResultStatistics());
//    	mainPane.getStartFinishButton().setText("СТАРТ");
//    }
//    
//    private void clearView() {
//    	mainPane.getQuestionLabel().setText("");
//    	mainPane.getAnswerInput().clear();
//    	mainPane.getPrevQuestionLabel().setText("");	
//    	mainPane.getCorrectAnswerLabel().setText("");
//    	mainPane.getPrevAnswerLabel().setText("");
//    	mainPane.getResultLabel().setText("");
//    }
//    
//    private void fillNextWord() {
//    	word = runner.getNextWord();
//    	mainPane.getQuestionLabel().setText(word.getValue(Lang.RU.toString()));
//    }
//    
//    private List<String> getActiveSectionNames() {
//    	List<String> activeSections = new ArrayList<String>();
//    	for (CheckBox sectionCheckbox : sectionCheckboxes) {
//    		if (sectionCheckbox.isSelected()) {
//    			activeSections.add(sectionCheckbox.getText());
//    		}
//    	}
//		return activeSections;
//    }

}
