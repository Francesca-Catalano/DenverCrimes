package it.polito.tdp.crimes.model;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.crimes.db.EventsDao;

public class Model {
	
	private EventsDao dao;
	private Graph<String, DefaultWeightedEdge> graph;

	public Model() {
		
		this.dao = new EventsDao();
		
		
		
	}
	
	public List<String> ListAllCategory()
	{
		return this.dao.listAllCategory();
	}
	public Set<Integer> ListMonths()
	{
		return this.dao.listAllDate();
	}
	
	public void creaGrafo(String category, Integer i)
	{
		this.graph= new SimpleWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		if(this.dao.getAdiacenze(category, i)==null)
		{
			return;
		}
		for(Adiacenze a : this.dao.getAdiacenze(category, i))
		{
			if(!this.graph.containsVertex(a.getA1()))
           {this.graph.addVertex(a.getA1());}
			
			if(!this.graph.containsVertex(a.getA2()))
	           {this.graph.addVertex(a.getA2());}
			
			
			if(this.graph.containsVertex(a.getA1()) && this.graph.containsVertex(a.getA2()))
			{
				DefaultWeightedEdge e = this.graph.getEdge(a.getA1(), a.getA2());
						if(e==null )
						{ if(a.getPeso()>0)
							Graphs.addEdgeWithVertices(this.graph, a.getA1(), a.getA2(), a.getPeso());
							
						}
			}
			
			
		}
	}
	
	public int VertexSize()
	{
		return this.graph.vertexSet().size();
	}
	

	public int EdgeSet()
	{
		return this.graph.edgeSet().size();
	}
	
	public double pesoMedio()
	{
		double peso=0;
		for(DefaultWeightedEdge e : this.graph.edgeSet())
		{
		peso+= this.graph.getEdgeWeight(e);	
		}
		return peso/ EdgeSet();
	}
	public List<Adiacenze> FilteredPesoMedio()
	{
		List<Adiacenze> list = new LinkedList<>();
		for(DefaultWeightedEdge e : this.graph.edgeSet())
		{
			if(this.graph.getEdgeWeight(e)> pesoMedio())
				list.add(new Adiacenze(this.graph.getEdgeSource(e), this.graph.getEdgeTarget(e), this.graph.getEdgeWeight(e)));
		}
		Collections.sort(list);
		//System.out.print("model:"+list.size()+"\n");
		return list;
		
	}
	
	
	
	
}
