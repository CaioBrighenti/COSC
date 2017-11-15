/*

	MovieGraph.java
	COSC 102, Colgate University

	Implements a sparse matrix using hash tables.
	Your code goes here.
	See instructions for explanation of methods.

	You should change DEBUG to false when your testing is complete.
*/

import java.io.*;
import java.util.*;
import java.util.Iterator;

public class MovieGraph
{
	// Holds movies as keys, actors as values
	private HashMap<String, HashSet<String>> movieMap;
	// Holds actors as keys, movies as values
	private HashMap<String, HashSet<String>> actorMap;

	// Debugging output
	private static final boolean DEBUG = false;
	private void pdbg(String s)
	{
		if (DEBUG)
			System.err.println(s);
	}

	// Constructor
	public MovieGraph(String filename) throws IOException, SecurityException, FileNotFoundException
	{
		// Initialize hash maps
		movieMap = new HashMap<String, HashSet<String>>();
		actorMap = new HashMap<String, HashSet<String>>();

		// Initialize file and loop through each line
		Scanner reader = new Scanner(new File(filename));		
		while(reader.hasNext()){
			String line = reader.nextLine();
			
			// Grab movie name
			String nameYear = line.substring(0, line.indexOf("/"));
			String movieName = line.substring(0, nameYear.lastIndexOf("(") - 1);

			// Grab actors
			String actors = line.substring(line.indexOf("/")+1);
			HashSet<String> actorSet = new HashSet<String>();
			while(actors.indexOf("/") != -1){
				int idx = actors.indexOf("/");
				String actorName = actors.substring(0, idx);
				actorSet.add(actorName);
				actors = actors.substring(idx+1);

				// Update actor hash map
				addMovieToActor(actorName, movieName);
			}
			// Ensure last actor is also caught
			// This isn't very clear, need to figure out a better implementation of this
			actorSet.add(actors);
			addMovieToActor(actors, movieName);

			// Add movie,actor combination to movie map
			movieMap.put(movieName, actorSet);
		}
		System.out.println(actorMap);
		System.out.println(movieMap);

		// for debugging
		pdbg(filename);

	}

	// Given an actor name and movie name, adds or updates the actor entry in the actor map
	public void addMovieToActor(String actor, String movie){
		if (actorMap.containsKey(actor))
			actorMap.get(actor).add(movie);
		else{
			HashSet<String> movieSet = new HashSet<String>();
			movieSet.add(movie);
			actorMap.put(actor, movieSet);
		}
	}

	// return the movies in which an actor has appeared
	public Iterable<String> getMovies(String actor)
	{
		// for debugging
		pdbg(actor);
		if (actorMap.containsKey(actor))
			return actorMap.get(actor);
		else
			return null;
	}

	// return the actors in a movie
	public Iterable<String> getActors(String movie)
	{
		// for debugging
		pdbg(movie);
		if (movieMap.containsKey(movie))
			return movieMap.get(movie);
		else
			return null;
	}

	// return the co-stars of an actor (over all movies)
	// Pretty inneficient
	// Grabs all movies actors is in, for each loops through all actors
	// only adds actor to return Set if actor is not already in Set
	public Iterable<String> getCoStars(String actor)
	{
		// for debugging
		pdbg(actor);

		HashSet<String> movies = actorMap.get(actor);
		HashSet<String> actorsReturn = new HashSet<String>();
		Iterator movieIterator = movies.iterator();
        while (movieIterator.hasNext()) {
         	  HashSet<String> actors = movieMap.get(movieIterator.next());
         	  Iterator actorIterator = actors.iterator();
         	  while (actorIterator.hasNext()){
         	  	String tempActor = (String)actorIterator.next();
         	  	if (!tempActor.equals(actor) && !actorsReturn.contains(tempActor))
         	  		actorsReturn.add(tempActor);
         	  }              
        }

		return actorsReturn;
	}

	// return whether an actor has been in a movie
	public boolean isMatch(String actor, String movie)
	{
		// for debugging
		pdbg(actor + " " + movie);
		if (movieMap.containsKey(movie))
			return movieMap.get(movie).contains(actor);
		else
			return false;
	}

	// return the movies in which two given actors have appeared together
	public Iterable<String> getMovieLinks(String actor1, String actor2)
	{
		// for debugging
		pdbg(actor1 + " " + actor2);
		// grab movies for actor 1
		// grab movies for actor 2
		if (!actorMap.containsKey(actor1) || !actorMap.containsKey(actor2))
			return null;

		HashSet<String> moviesReturn = new HashSet<String>();
		HashSet<String> movies1 = actorMap.get(actor1);
		HashSet<String> movies2 = actorMap.get(actor2);
		Iterator moviesIterator = movies1.iterator();
        while (moviesIterator.hasNext()){
         	String tempMovie = (String)moviesIterator.next();
         	if (movies2.contains(tempMovie))
         		moviesReturn.add(tempMovie);
        }  
		return moviesReturn;
	}
}
