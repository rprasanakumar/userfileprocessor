package org.max.service.userfileprocessor.service;

import java.util.ArrayList;
import java.util.List;

import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.dao.UserRecordDAO;

public class RecordCommonServiceImpl implements  ICommonService{
	
	UserRecord record;
	public RecordCommonServiceImpl(UserRecord record) {
		this.record = record;
		
	}

	@Override
	public List<? extends Object> execute() {
		List<UserRecord> recordList = new ArrayList<UserRecord>();
		UserRecordDAO recordDAO = new UserRecordDAO();
		UserRecord recordOutput= recordDAO.insertUserRecord(record);
		recordList.add(recordOutput);
		
		return recordList;
		
		
	}

}
