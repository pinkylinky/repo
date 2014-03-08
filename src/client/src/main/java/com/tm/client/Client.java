package com.tm.client;

import java.util.List;

import com.tm.core.AppConfigurator;
import com.tm.core.CoreManager;
import com.tm.entity.Item;
import com.tm.exception.BusinessLogicException;
import com.tm.exception.RemoteAccessException;

public class Client {
	
	public static void main(String[] args) throws RemoteAccessException, BusinessLogicException {
		AppConfigurator.configureDefault();
		CoreManager core = new CoreManager();
		
		Item item = new Item();
		item.setAlias("test");
		item.setName("test test");
		item.addToGroup(1L);
		
		System.out.println(core.addItem(item));
		List<Item> items = core.getItems(1L);
		System.out.println(items.size());
		
	}

}
