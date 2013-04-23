package jp.co.xypenguin.game.algo;

import java.util.*;

public class Hand{
	private ArrayList<Card> hand;	//手札
	private TalonAlgo talon;		//扱う山札

	/******************************************************
	 *	コンストラクタ
	 *	扱う山札（TalonAlgo）をフィールドに代入して
	 *	山札から手札（hand）に7枚取得する
	 *****************************************************/
	Hand(TalonAlgo talon, boolean bothSides){
		this.talon = talon;
		hand = new ArrayList<Card>();
		for(int i = 0; i < 8; i++){
			hand.add(talon.getCardOne());
		}
		if(bothSides == true){
			for(Card card : hand){
				card.setBothSides(true);
			}
		}
	}
	/******************************************************
	 *
	 *	手札を表示する。
	 *	card.getBothSidesはカードが裏か表かを判別する。
	 *	falseは裏を意味する。裏ならカードの色は分かるが数字は見えない。
	 *****************************************************/
	public void dispHand(){
		sort();
		for(int i = 0; i < hand.size(); i++){
			Card card = hand.get(i);
			if(card.getBothSides() == true){
				System.out.printf("[%s%2d]",card.getKind(),card.getNumber());
			}else{
				System.out.printf("[%s※]",card.getKind());
			}
			System.out.print(" ");
		}
		System.out.println();
		for(int i = 0; i < hand.size(); i++){
			System.out.printf("  %2d   ",i + 1);
		}
		System.out.println();
	}
	/******************************************************
	 *
	 *	バブルソート
	 *	昇順、同じ数字なら赤の方を大きいと評価する
	 *	
	 *****************************************************/
	public void sort(){
		for(int j = 0; j < hand.size() - 1; j++){
			for(int i = 0; i < hand.size() - 1 - j; i++){
				//隣り合ったカードの数の大きさを評価して入れ替える
				//添え字の大きい方に数の大きいカードを入れる。
				if(hand.get(i + 1).getNumber() < hand.get(i).getNumber()){
					Card card = hand.get(i);
					hand.set(i,hand.get(i + 1));
					hand.set(i + 1,card);
				}
				//隣り合ったカードの種類を評価して入れ替える
				//カードの数が同じ場合、添え字の大きい方に赤を入れる
				if(hand.get(i + 1).getNumber() == hand.get(i).getNumber() && hand.get(i).getKind().equals("赤")){
					Card card = hand.get(i);
					hand.set(i,hand.get(i + 1));
					hand.set(i + 1,card);
				}
			}
		}
	}
	/******************************************************
	 *
	 *	手札を増やす
	 *	
	 *****************************************************/
	public void setHand(Card card){
		hand.add(card);
	}
	/******************************************************
	 *
	 *	手札を一枚取り出す
	 *	
	 *****************************************************/
	public Card getHandOne(int index){
		Card card = hand.get(index -1);
		hand.remove(index - 1);
		return card;
	}
	/******************************************************
	 *
	 *	手札を取得する
	 *	
	 *****************************************************/
	public ArrayList<Card> getHand(){
		return hand;
	}
	/**
	  *	手札のfalseの数を返す。
	  */
	public int getHandBothSidesFalse(){
		int count = 0;
		for(int i = 0; i < hand.size(); i++){
			if(hand.get(i).getBothSides() == false){
				count += 1;
			}
		}
		return count;
	}
}
