package org.max.service.userfileprocessor.dao;




import java.util.List;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.max.service.userfileprocessor.bean.UserRecord;




/** 
 * class for Data access object layer. Interacts  DataBase and Object 
 * @author Prasanna Kumar
 * @version 0.0.1
 * @param  user record object
 */


public class UserRecordDAO {
	

	
	private SqlSessionFactory sqlSessionFactory; 
	
	public UserRecordDAO(){
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	

	/**
	 * @inserts User record.
	 * @return UserRecord insert count.
	 */
	
	public int insertUserRecordFile(List<UserRecord> usersList){

		SqlSession session = sqlSessionFactory.openSession();
		
		try {
			session.insert("UserRecord.insertBulkData",  usersList) ;
			session.commit();
		} catch(PersistenceException ex){
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return usersList.size();
	}
	

	

	/**
	 * @selects User record .
	 * @return UserRecord color and count.
	 */
	
	public Map<String,String> selectUserRecordColorNCount(){

		SqlSession session = sqlSessionFactory.openSession();
		Map<String,String> map=null;
		try {
			map = session.selectMap("UserRecord.getColorNPeople", "key");
			session.commit();
		} catch(PersistenceException ex){
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return map;
	}

	
}
