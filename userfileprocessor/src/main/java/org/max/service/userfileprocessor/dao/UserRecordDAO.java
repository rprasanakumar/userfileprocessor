package org.max.service.userfileprocessor.dao;




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.max.service.userfileprocessor.bean.UserRecord;
import org.max.service.userfileprocessor.bean.UserVenue;




/** 
 * class for Data access object layer. Interacts  DataBase and Object 
 * @author Prasanna Kumar
 * @version 0.0.1
 * @param  user record object
 */


public class UserRecordDAO {
	
	
	/*
	 * API parameters 
	 * 
	 */
	
	String BASE_URL = "https://api.foursquare.com/v2/venues/search?";
	final String CLIENT_ID= "J1KRSNZ2SUYEJS54AUVNO0UGCECUZC3PCJMRAMRW3K3Y4WWQ";
	final String CLIENT_SECRET= "VHL5NFBWJ2IJD51S4FVCDWYZL40RR4S0XUXMWYULQX4DDYIE";
	String defaultLocation = "35.3219002,-80.7568182";
	final String VER_NO = "20170101";

	
	private SqlSessionFactory sqlSessionFactory; 
	
	public UserRecordDAO(){
		sqlSessionFactory = MyBatisConnectionFactory.getSqlSessionFactory();
	}
	

	/**
	 * @inserts User file record.
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
	 * @throws ParseException 
	 */
	
	public Map<String,Map<String,Long>> selectUserRecordColorNCount(){

		SqlSession session = sqlSessionFactory.openSession();
		Map<String,Map<String,Long>> map=null;
		try {
			map = session.selectMap("UserRecord.getColorNPeople", "key");
		} catch(PersistenceException ex){
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return map;
	}
	
	
	/**
	 * @selects User record .
	 * @return All UserRecord .
	 * @throws ParseException 
	 */
	
	public List<UserRecord> selectUserRecord(){

		SqlSession session = sqlSessionFactory.openSession();
		List<UserRecord> listUserRecord=null;
		try {
			listUserRecord = (List<UserRecord>)session.selectList("UserRecord.getAll");
		} catch(PersistenceException ex){
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return listUserRecord;
	}
	
	/**
	 * @selects User record .
	 * @return UserRecord color and count.
	 * @throws ParseException 
	 */
	
	public List<String> searchUserVenue(String query){
		
		List<String> venueList = new ArrayList<String>();
		try {
			
			String urlEndPoint = BASE_URL+"client_id="+CLIENT_ID+
					"&client_secret="+CLIENT_SECRET+"&v="+VER_NO+"&ll="+defaultLocation+"&query="+query;
		JSONParser parser = new JSONParser();
		URL url = new URL(urlEndPoint);
		HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.connect();
	    int statusCode = con.getResponseCode();
        if (statusCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Object object = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) object;
            JSONArray msg = (JSONArray) ((JSONObject) jsonObject.get("response")).get("venues");
            for(int i = 0;i < msg.size();i++ ) {
            	
                JSONObject jsonObj = (JSONObject) msg.get(i);
                
                String value = (String) jsonObj.get("name");
            
                venueList.add(value);
            }
            }
        }
		catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    return venueList;
}

	
}
