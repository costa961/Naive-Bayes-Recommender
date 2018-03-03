package Dataset;

import java.util.ArrayList;
import java.util.List;

public class Movie {
	
	private int id;
	private String director;
	private List<String> writer = new ArrayList<String>();
	private List<String> star = new ArrayList<String>();
	private List<String> genre = new ArrayList<String>();
	
	public Movie(){
		super();	
	}

	public int getId(){
		return id; }
	
	public String getDirector(){
		return director;
	}
	
	public List<String> getWriter(){
		return writer;
	}
	
	public List<String> getStar(){
		return star;
	}
	
	public List<String> getGenre(){
		return genre;
	}
	
	public void setId(int id){
		this.id=id; }
	
	public void setDirector(String director){
		this.director=director;
	}
	
	public void setWriter(String writer){
		this.writer.add(writer);
	}
	
	public void setStar(String star){
		this.star.add(star);
	}
	
	public void setGenre(String genre){
		this.genre.add(genre);
	} 	
	
}
