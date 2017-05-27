package org.max.service.userfileprocessor.service;

import java.util.List;

import org.max.service.userfileprocessor.bean.UserRecord;

public interface IFileProcessor {
	
	public List<UserRecord> parse();

}
