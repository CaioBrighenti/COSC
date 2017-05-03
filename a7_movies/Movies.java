/*

	Movies.java

	COSC 102, Colgate University
	(c) 2013 Vijay Ramachandran, all rights reserved

	You should not submit or have to modify this file.
*/

import java.io.*;
import java.util.*;

public class Movies
{
	/*
	Movies.main({file})

	Takes the filename of a movie database as an argument.
	All but the first argument will be ignored.
	*/
	public static void main (String[] args) {
		if (args == null || args.length < 1) {
			System.out.println("Movies: error; no database file provided");
			System.exit(1);
			return;
		}

		MovieGraph g;

		try {
			g = new MovieGraph(args[0]);
		} catch (FileNotFoundException e) {
			System.err.println("Movies: database file " + args[0] + " not found");
			System.exit(2);
			return;
		} catch (SecurityException e) {
			System.err.println("Movies: security violation reading file " + args[0]);
			System.exit(3);
			return;
		} catch (IOException e) {
			System.err.println("Movies: I/O error on file " + args[0]);
			System.exit(4);
			return;
		}

		Scanner s = new Scanner(System.in);
		int response = 0;
		String input1;
		String input2;
		Iterable<String> it;

		while (true) {
			System.out.println("What type of query would you like to perform?");
			System.out.println("  0. Quit.");
			System.out.println("  1. Return all the movies in which an actor has appeared.");
			System.out.println("  2. Return all the actors in a movie.");
			System.out.println("  3. Return all the co-stars of an actor (over all movies).");
			System.out.println("  4. Find whether or not an actor appeared in a movie.");
			System.out.println("  5. Return the set of movies, if any, in which two actors appeared together.");

			while (true) {
				System.out.print("Enter [0-5]: ");
				try {
					response = s.nextInt();
				} catch (InputMismatchException e) {
					response = -1;
				} catch (NoSuchElementException e) {
					response = 0;
				} catch (IllegalStateException e) {
					response = 0;
				}

				if (response < 0 || response > 5) {
					System.err.println("  Input unrecognized, try again.");
					continue;
				} else
					break;
			}
			try {
				s.nextLine();
			} catch (NoSuchElementException e) {
				response = 0;
			} catch (IllegalStateException e) {
				response = 0;
			}

			switch (response) {

			case 0:
				System.exit(0);
				return;

			case 1:
				System.out.print("\nEnter the name of the actor: ");
				try {
					input1 = s.nextLine();
				} catch (IllegalStateException e) {
					System.exit(5);
					return;
				} catch (NoSuchElementException e) {
					continue;
				}

				it = g.getMovies(input1);
				if (it != null) {
					for (String m : it) {
						System.out.print("\t");
						System.out.println(m);
					}
				}
				break;

			case 2:
				System.out.print("\nEnter the movie name and year [\"Movie (Year)\"]: ");
				try {
					input1 = s.nextLine();
				} catch (IllegalStateException e) {
					System.exit(5);
					return;
				} catch (NoSuchElementException e) {
					continue;
				}

				it = g.getActors(input1);
				if (it != null) {
					for (String m : it) {
						System.out.print("\t");
						System.out.println(m);
					}
				}
				break;

			case 3:
				System.out.print("\nEnter the name of the actor: ");
				try {
					input1 = s.nextLine();
				} catch (IllegalStateException e) {
					System.exit(5);
					return;
				} catch (NoSuchElementException e) {
					continue;
				}

				it = g.getCoStars(input1);
				if (it != null) {
					for (String m : it) {
						System.out.print("\t");
						System.out.println(m);
					}
				}
				break;

			case 4:
				System.out.print("\nEnter the name of the actor: ");
				try {
					input1 = s.nextLine();
				} catch (IllegalStateException e) {
					System.exit(5);
					return;
				} catch (NoSuchElementException e) {
					continue;
				}

				System.out.print("Enter the name of the movie: ");
				try {
					input2 = s.nextLine();
				} catch (IllegalStateException e) {
					System.exit(5);
					return;
				} catch (NoSuchElementException e) {
					continue;
				}

				System.out.println(g.isMatch(input1, input2) ? "\tYes" : "\tNo");
				break;

			case 5:
				System.out.print("\nEnter the name of the first actor: ");
				try {
					input1 = s.nextLine();
				} catch (IllegalStateException e) {
					System.exit(5);
					return;
				} catch (NoSuchElementException e) {
					continue;
				}

				System.out.print("Enter the name of the second actor: ");
				try {
					input2 = s.nextLine();
				} catch (IllegalStateException e) {
					System.exit(5);
					return;
				} catch (NoSuchElementException e) {
					continue;
				}

				it = g.getMovieLinks(input1, input2);
				if (it != null) {
					for (String m : it) {
						System.out.print("\t");
						System.out.println(m);
					}
				}
				break;

			default:
				break;
				
			}

			System.out.println();
		}
	}
}
