package org.max.service.userfileprocessor.service;

import java.util.List;
import java.util.Map;

import org.max.service.userfileprocessor.dao.UserRecordDAO;

public class CommonServiceUserDataImpl implements ICommonService{
	

	@Override
	public int execute() {

		UserRecordDAO dao = new UserRecordDAO();
		Map<String, String> map = dao.selectUserRecordColorNCount();
		return map.size();
	}

}
