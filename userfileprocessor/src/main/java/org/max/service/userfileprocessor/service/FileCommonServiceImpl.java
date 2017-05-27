package org.max.service.userfileprocessor.service;

import java.util.List;

import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.dao.UserRecordDAO;

public class FileCommonServiceImpl implements ICommonService{

	
	String path;
	IFileProcessor processor;
	UserRecordDAO dao;
	public FileCommonServiceImpl(String path) {
		
		this.path = path;
	}
	
	@Override
	public int execute() {
		int countRecord =0;
		dao = new UserRecordDAO();
		processor = new FileProcessorCSV(this.path);
		List<UserRecord> recordList= processor.parse();
		if(!recordList.isEmpty()) {
			countRecord = dao.insertUserRecordFile(recordList);
		}
		return countRecord;
		
	}
	
	

}
