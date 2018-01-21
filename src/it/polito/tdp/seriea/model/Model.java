package it.polito.tdp.seriea.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.seriea.db.SerieADAO;
import it.polito.tdp.seriea.exception.SerieAException;

public class Model {

	private final static SerieADAO dao = new SerieADAO();
	private SimpleDirectedWeightedGraph<Goal, DefaultWeightedEdge> graph;
	private List<Goal> goals;

	public Model() {
		goals = new ArrayList<Goal>();
	}

	public SimpleDirectedWeightedGraph<Goal, DefaultWeightedEdge> creaGrafo() throws SerieAException {

		this.graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		List<Goal> vertexList = listGoals();
		System.out.println("<creaGrafo> numero vertici/squadre: " + vertexList.size());
		// crea i vertici del grafo
		Graphs.addAllVertices(graph, vertexList);

		// crea gli archi del grafo
		// faccio fare tutto il lavoro al dao
		List<GoalsCouple> gcList = dao.listGoalsCouple();
		for (GoalsCouple gc : gcList) {
			DefaultWeightedEdge dwe = this.graph.addEdge(gc.getGoal1(), gc.getGoal2());
			this.graph.setEdgeWeight(dwe, gc.getNumberOfResult());
		}
		System.out.println("<creaGrafo> numero archi: " + this.graph.edgeSet().size());

		return this.graph;

	}

	public List<Integer> listGoalstoInteger() throws SerieAException {
		List<Integer> goalsList = new ArrayList();

		for (Goal g : listGoals()) {
			if (!goalsList.contains(g.getScore())) {
				goalsList.add(g.getScore());
			}
		}

		return goalsList;
	}

	public List<Goal> listGoals() throws SerieAException {
		if (this.goals.size() == 0) {
			this.goals = dao.listGoals();
		}
		return this.goals;
	}

	public List<GoalsCouple> retrieveListOfResults(Goal homeGoals) {

		List<GoalsCouple> gcList = new ArrayList<GoalsCouple>();
		for (DefaultWeightedEdge dwe : this.graph.outgoingEdgesOf(homeGoals)) {
			GoalsCouple gc = new GoalsCouple(this.graph.getEdgeSource(dwe), this.graph.getEdgeTarget(dwe),
					(int) this.graph.getEdgeWeight(dwe));
			gcList.add(gc);

		}

		return gcList;
	}

}
