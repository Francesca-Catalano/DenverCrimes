package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polito.tdp.crimes.model.Adiacenze;
import it.polito.tdp.crimes.model.Event;


public class EventsDao {
	
	public List<Event> listAllEvents(){
		String sql = "SELECT * FROM events" ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<Event> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"),
							res.getInt("offense_code"),
							res.getInt("offense_code_extension"), 
							res.getString("offense_type_id"), 
							res.getString("offense_category_id"),
							res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"),
							res.getDouble("geo_lon"),
							res.getDouble("geo_lat"),
							res.getInt("district_id"),
							res.getInt("precinct_id"), 
							res.getString("neighborhood_id"),
							res.getInt("is_crime"),
							res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	
	public List<String> listAllCategory(){
		String sql = "select distinct offense_category_id " + 
				"from events " ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			List<String> list = new ArrayList<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getString("offense_category_id"));
					
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	

	public Set<Integer> listAllDate(){
		String sql ="select `reported_date` " + 
				"from events ";
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			
			Set<Integer> list = new HashSet<>() ;
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				try {
					list.add(res.getTimestamp("reported_date").toLocalDateTime().getMonthValue());
					
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}
			
			conn.close();
			return list ;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}


	public List<Adiacenze> getAdiacenze(String category, Integer i) {
		String sql ="select e1.offense_type_id,e2.offense_type_id,COUNT(DISTINCT e1.`neighborhood_id`) as tot " + 
				"from events e1, events e2 " + 
				"where e1.offense_category_id=? " + 
				"and e2.offense_category_id=  e1.offense_category_id " + 
				"and MONTH(e1.reported_date)=? " + 
				"and MONTH(e2.reported_date)=? " + 
				"AND  e1.offense_type_id!=e2.offense_type_id "  + 
				"and e1.neighborhood_id=e2.neighborhood_id " + 
				"group by e1.offense_type_id,e2.offense_type_id ";
		List<Adiacenze> list = new ArrayList<>() ;
		try {
			Connection conn = DBConnect.getConnection() ;

			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1,category );
			st.setInt(2, i);
			st.setInt(3, i);
			
			
			
			ResultSet res = st.executeQuery() ;
			
			while(res.next()) {
				Adiacenze a = new Adiacenze(res.getString("e1.offense_type_id"), res.getString("e2.offense_type_id"), res.getDouble("tot"));
				list.add(a);
			}
			
			conn.close();
			return list;
		

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
		
	}

}
