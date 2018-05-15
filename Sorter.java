import java.util.*;

public class Sorter {

	public Sorter(){
	}

	public static ArrayList<Comparable> selectionSort(ArrayList<Comparable> unsorted) {
    ArrayList<Comparable> ans = new ArrayList<Comparable>(unsorted);
    for (int i = 0; i < ans.size(); i++) {
      for (int f = 0; f < ans.size(); f++) {
        if (ans.get(i).compareTo(ans.get(f)) < 0) {
          int pos1 = i;
          int pos2 = f;
          Object swap1 = ans.get(i);
          Object swap2 = ans.get(f);
          ans.set(pos1, (Comparable) swap2);
          ans.set(pos2, (Comparable) swap1);

        }
      }
    }
    return ans;
  }


  /* designing this to sort lengths of suit in Hand's listOfSuits
   * like
   * [] [] [] [] S 
   * [] [] [] [] H
   * [] [] []    C
   * [] []       D
   */
  public static ArrayLizt<Card>[] zelectionZort(ArrayLizt<Card>[] unsorted) {
    ArrayLizt<Card>[] ans = unsorted;
    for (int i = 0; i < ans.length; i++) {
      for (int f = 0; f < ans.length; f++) {
        if (ans[i].size()-ans[f].size() > 0) {
          int pos1 = i;
          int pos2 = f;
          Object swap1 = ans[i];
          Object swap2 = ans[f];
          ans[pos1]=(ArrayLizt<Card>) swap2;
          ans[pos2]= (ArrayLizt<Card>) swap1;
        }
      }
    }
    return ans;
  }



  public ArrayList<Card> sortValues(ArrayList<Card> cards) {
		//System.out.println(cards.toString());
    ArrayList<Card> ans = new ArrayList<Card>(cards);
    for (int i = 0; i < ans.size(); i++) {  
      for (int f = 0; f < ans.size(); f++) {
	        if (ans.get(i).pointValue()-ans.get(f).pointValue() > 0) {
	          int pos1 = i;
	          int pos2 = f;
	          Object swap1 = ans.get(i);
	          Object swap2 = ans.get(f);
	          ans.set(pos1, (Card) swap2);
	          ans.set(pos2, (Card) swap1);
        }
      }
    }
    return ans;
	}

  public ArrayList<Card> findWinner(ArrayList<Card> cards) {
    ArrayList<Card> ans = new ArrayList<Card>(cards);
    return ans; 
  }


}