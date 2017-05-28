package org.max.service.userfileprocessor.service;

import java.util.List;
import java.util.Map;

import org.max.service.userfileprocessor.bean.UserRecordDisplay;
import org.max.service.userfileprocessor.dao.UserRecordDAO;

public class CommonServiceUserDataImpl implements ICommonService{
	
	IUserDataDisplay displayType;
	public CommonServiceUserDataImpl(IUserDataDisplay displayType) {
		this.displayType = displayType;
	}

	@Override
	public List<UserRecordDisplay> execute() {

		return this.displayType.pullData();
	}

}
