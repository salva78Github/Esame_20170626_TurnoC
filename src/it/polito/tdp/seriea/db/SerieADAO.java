package it.polito.tdp.seriea.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.seriea.exception.SerieAException;
import it.polito.tdp.seriea.model.Goal;
import it.polito.tdp.seriea.model.GoalsCouple;
import it.polito.tdp.seriea.model.Season;

public class SerieADAO {
	
	public List<Season> listSeasons() throws SerieAException {
		String sql = "SELECT season, description FROM seasons" ;
		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Season> result = new ArrayList<>() ;
		
		try {
			c = DBConnect.getConnection() ;
			st = c.prepareStatement(sql) ;
			rs = st.executeQuery() ;
			
			while(rs.next()) {
				result.add( new Season(rs.getInt("season"), rs.getString("description"))) ;
			}
			
			return result ;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SerieAException("Errore nel recupero delle stagioni.", e) ;
		} finally{
			DBConnect.closeResources(c, st, rs);
		}
	}
	
	
	public List<Goal> listGoals() throws SerieAException{
		String sql = "select distinct m.FTHG, \"H\" as type from matches m union select distinct m.FTAG, \"A\" as type from matches m";
		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Goal> result = new ArrayList<>() ;
		
		try {
			c = DBConnect.getConnection() ;
			st = c.prepareStatement(sql) ;
			rs = st.executeQuery() ;
			
			while(rs.next()) {
				Goal g = new Goal(rs.getInt(1), rs.getString("type"));
				result.add(g) ;
			}
			
			return result ;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SerieAException("Errore nel recupero dei gol.", e) ;
		} finally{
			DBConnect.closeResources(c, st, rs);
		}		
		
		
	}
	
	
	public List<GoalsCouple> listGoalsCouple() throws SerieAException{
		String sql = "select count(*) as numberOfResults, m.FTHG, m.FTAG from matches m group by m.FTHG, m.FTAG";
		Connection c = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		List<GoalsCouple> result = new ArrayList<>() ;
		
		try {
			c = DBConnect.getConnection() ;
			st = c.prepareStatement(sql) ;
			rs = st.executeQuery() ;
			
			while(rs.next()) {
				GoalsCouple gc = new GoalsCouple(new Goal(rs.getInt("m.FTHG"),"H"),
						new Goal(rs.getInt("m.FTAG"),"A"), rs.getInt("numberOfResults"));
				result.add(gc) ;
			}
			
			return result ;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SerieAException("Errore nel recupero dei risultati.", e) ;
		} finally{
			DBConnect.closeResources(c, st, rs);
		}		
		
		
	}
	
	

}
