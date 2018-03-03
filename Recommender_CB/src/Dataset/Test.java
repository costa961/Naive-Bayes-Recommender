package Dataset;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {

		Movie[] mv = new Movie[1683];
		for (int i=1;i<1683;i++){
			mv[i] = new Movie();
		}

		Functions parser = new Functions();
		mv = parser.parseXml(mv);

		List<User_Item_Rating> list_uir = new ArrayList<User_Item_Rating>();

		parser.parseUir(list_uir);

		//Esempio di raccomandazione per l'utente 13
		
		List<String> training_pos = new ArrayList<String>();
		training_pos = parser.UserTokenPos(13, list_uir, mv);
		System.out.println("Training set Like: ");
        System.out.println(training_pos);
		
		List<String> training_neg = new ArrayList<String>();
		training_neg = parser.UserTokenNeg(13, list_uir, mv);
		System.out.println("Training set Not Like: ");
		System.out.println(training_neg);

		List<List<String>> list_not_rated = new ArrayList<>();
		list_not_rated = parser.ItemNotRated(13, list_uir, mv);

		parser.Recommend(training_pos, training_neg, list_not_rated); 

	}			

}
