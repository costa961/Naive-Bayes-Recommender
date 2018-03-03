package Dataset;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Bayes.BayesClassifier;
import Bayes.Item_Category_Probability;
import Bayes.Classifier;



public class Functions{

	public Movie[] parseXml(Movie[] movie_array){

		try{
			File inputFile = new File("content.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("movie");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					if(eElement.getElementsByTagName("id").getLength()!=0){
						for(int i=0;i<eElement.getElementsByTagName("id").getLength();i++){
							movie_array[temp+1].setId(temp+1);
						}
					} 
					if(eElement.getElementsByTagName("director").getLength()!=0){
						for(int i=0;i<eElement.getElementsByTagName("director").getLength();i++){
							movie_array[temp+1].setDirector(eElement.getElementsByTagName("director").item(i).getTextContent());
						}

					}
					if(eElement.getElementsByTagName("writer").getLength()!=0){
						for(int i=0;i<eElement.getElementsByTagName("writer").getLength();i++){
							movie_array[temp+1].setWriter(eElement.getElementsByTagName("writer").item(i).getTextContent());
						}
					}
					if(eElement.getElementsByTagName("star").getLength()!=0){
						for(int i=0;i<eElement.getElementsByTagName("star").getLength();i++){
							movie_array[temp+1].setStar(eElement.getElementsByTagName("star").item(i).getTextContent());
						}
					}		
					if(eElement.getElementsByTagName("genre").getLength()!=0){
						for(int i=0;i<eElement.getElementsByTagName("genre").getLength();i++){
							movie_array[temp+1].setGenre(eElement.getElementsByTagName("genre").item(i).getTextContent());
						}
					} 
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return movie_array;
	}

	public List<User_Item_Rating> parseUir(List<User_Item_Rating> uir_list){

		try{
			Scanner s = new Scanner(new File("ua.base"));

			while (s.hasNext()){
				User_Item_Rating uir = new User_Item_Rating();
				uir.setUser(s.nextInt());       
				uir.setItem(s.nextInt());
				uir.setRating(s.nextInt());
				uir_list.add(uir);
				s.nextInt();      
			}

			s.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}

		return uir_list;

	}

	public List<String> UserTokenPos(int User, List<User_Item_Rating> uir , Movie[] movie_array){
		List<String> pos = new ArrayList<String>();

		for(int i=0;i<uir.size();i++){
			if(uir.get(i).getUser()==User && ((uir.get(i).getRating()==4)||(uir.get(i).getRating()==5))){

				if(!pos.contains(movie_array[uir.get(i).getItem()].getDirector()))
					pos.add(movie_array[uir.get(i).getItem()].getDirector());

				List<String> temp = new ArrayList<String>();

				temp = movie_array[uir.get(i).getItem()].getWriter();
				for (int k=0;k<temp.size();k++){
					if(!pos.contains(temp.get(k)))
						pos.add(temp.get(k));
				}

				temp = movie_array[uir.get(i).getItem()].getStar();
				for (int k=0;k<temp.size();k++){
					if(!pos.contains(temp.get(k)))
						pos.add(temp.get(k));
				}

				temp = movie_array[uir.get(i).getItem()].getGenre();
				for (int k=0;k<temp.size();k++){
					if(!pos.contains(temp.get(k)))
						pos.add(temp.get(k));
				}
			}

		}

		pos.removeAll(Collections.singleton(null));
		return pos;
	}

	public List<String> UserTokenNeg(int User, List<User_Item_Rating> uir , Movie[] movie_array){
		List<String> neg = new ArrayList<String>();

		for(int i=0;i<uir.size();i++){
			if(uir.get(i).getUser()==User && ((uir.get(i).getRating()==1)||(uir.get(i).getRating()==2))){

				if(!neg.contains(movie_array[uir.get(i).getItem()].getDirector()))
					neg.add(movie_array[uir.get(i).getItem()].getDirector());

				List<String> temp = new ArrayList<String>();

				temp = movie_array[uir.get(i).getItem()].getWriter();
				for (int k=0;k<temp.size();k++){
					if(!neg.contains(temp.get(k)))
						neg.add(temp.get(k));
				}

				temp = movie_array[uir.get(i).getItem()].getStar();
				for (int k=0;k<temp.size();k++){
					if(!neg.contains(temp.get(k)))
						neg.add(temp.get(k));
				}

				temp = movie_array[uir.get(i).getItem()].getGenre();
				for (int k=0;k<temp.size();k++){
					if(!neg.contains(temp.get(k)))
						neg.add(temp.get(k)); 
				}
			}

		}

		neg.removeAll(Collections.singleton(null));
		return neg;
	}

	public List <List<String>> ItemNotRated(int User, List<User_Item_Rating> uir , Movie[] movie_array){
		List<List<String>> TokenList = new ArrayList<>();
		List<Integer> ItemRated = new ArrayList<Integer>();

		for(int i=0;i<uir.size();i++){
			if(uir.get(i).getUser()==User)
				ItemRated.add(uir.get(i).getItem());		
		}
		for(int i=1;i<1683;i++){
			if(!ItemRated.contains(i)){
				List <String> pos = new ArrayList<String>();
				List<String> temp = new ArrayList<String>();
				Boolean flag = false;

				pos.add(String.valueOf(movie_array[i].getId()));
				pos.add(movie_array[i].getDirector());

				if(movie_array[i].getWriter().isEmpty()){
					flag = true;
				}else{
					temp = movie_array[i].getWriter();
					for (int k=0;k<temp.size();k++){
						pos.add(temp.get(k));
					}
				}

				if(movie_array[i].getStar().isEmpty()){
					flag = true;
				}else{
					temp = movie_array[i].getStar();
					for (int k=0;k<temp.size();k++){
						pos.add(temp.get(k));
					}
				}

				if(movie_array[i].getGenre().isEmpty()){
					flag = true;
				}else{
					temp = movie_array[i].getGenre();
					for (int k=0;k<temp.size();k++){
						pos.add(temp.get(k));
					}
				}

				if(!pos.contains(null)&&flag==false)
					TokenList.add(pos);
			}
		}

		return TokenList;
	}

	public void Recommend(List<String> UserItemPos, List<String> UserItemNeg, List<List<String>> ItemNotRated){

		final Classifier<String, String> bayes = new BayesClassifier<String, String>();
		List<Item_Category_Probability> result = new ArrayList<Item_Category_Probability>();

		String[] pos = UserItemPos.toArray(new String[0]);
		String[] neg = UserItemNeg.toArray(new String[0]);
		bayes.learn("like",Arrays.asList(pos));
		bayes.learn("not_like",Arrays.asList(neg));

		for(int i=0;i<ItemNotRated.size();i++){
			Item_Category_Probability icp = new Item_Category_Probability();
			icp.setItem(ItemNotRated.get(i).get(0));
			ItemNotRated.get(i).remove(0);
			String[] not_rated = ItemNotRated.get(i).toArray(new String[0]);
			icp.setCategory(bayes.classify(Arrays.asList(not_rated)).getCategory());
			icp.setProbability(bayes.classify(Arrays.asList(not_rated)).getProbability());

			result.add(icp);
		}
		Collections.sort(result, new Comparator<Item_Category_Probability>(){

			public int compare(Item_Category_Probability icp1, Item_Category_Probability icp2) {
				return Float.valueOf(icp2.getProbability()).compareTo(icp1.getProbability()); 
			}
		}); 

		System.out.println("All' utente potrebbero piacere : ");
		for(int i=0;i<10;i++){
			if(result.get(i).getCategory()=="like")
				System.out.println("[ [ Id: "+result.get(i).getItem()+" ] , "+"[ Category: "+result.get(i).getCategory()+" ] , [ Probability: "+result.get(i).getProbability()+" ] ]");	
		}

	}

}

