package com.tm.core;

import java.sql.SQLException;
import java.util.List;

import com.tm.dao.ItemsDao;
import com.tm.entity.Item;
import com.tm.entity.ItemGroup;
import com.tm.exception.BaseRuntimeException;
import com.tm.exception.BusinessLogicException;
import com.tm.exception.RemoteAccessException;

public class CoreManager {
	
	private ItemsDao itemsDao;
	
	public CoreManager() throws RemoteAccessException {
		try {
			itemsDao = AppManager.getInstance().getResourceManager().getItemsDao();
		} catch (SQLException e) {
			throw new RemoteAccessException(e);
		} catch (InstantiationException e) {
			throw new BaseRuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new BaseRuntimeException(e);
		}
	}
	
	public List<ItemGroup> getItemGroups() throws BusinessLogicException {
		try {
			return itemsDao.getItemGroups();
		} catch (SQLException e) {
			throw new BusinessLogicException(e);
		}
	}
	
	public List<Item> getItems(long groupId) throws BusinessLogicException {
		try {
			return itemsDao.getItemsByGroupId(groupId);
		} catch (SQLException e) {
			throw new BusinessLogicException(e);
		}
	}
	
	public long addItem(Item item) throws BusinessLogicException {
		try {
			return itemsDao.insertItem(item);
		} catch (SQLException e) {
			throw new BusinessLogicException(e);
		}
	}
	

}
