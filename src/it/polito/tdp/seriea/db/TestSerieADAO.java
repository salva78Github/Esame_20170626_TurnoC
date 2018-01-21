package it.polito.tdp.seriea.db;

import java.util.List;

import it.polito.tdp.seriea.exception.SerieAException;
import it.polito.tdp.seriea.model.Season;
import it.polito.tdp.seriea.model.Team;

public class TestSerieADAO {

	public static void main(String[] args) {
		SerieADAO dao = new SerieADAO();

		List<Season> seasons;
		try {
			seasons = dao.listSeasons();
			System.out.println(seasons);
			System.out.println("Seasons # rows: " + seasons.size());
		} catch (SerieAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
