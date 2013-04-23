package jp.co.xypenguin.game.algo;

import java.util.*;

class TalonAlgo{
	private LinkedList<Card> talon;
	
	TalonAlgo(){
		talon = new LinkedList<Card>();
		CardListAlgo cla = new CardListAlgo();
		
		for(int i = 0; i < cla.getCardCount(); i++){
			int ran = (int)(Math.random() * cla.getCardCount());
			if(cla.getCardOne(ran) != null){
				talon.add(cla.getCardOne(ran));
				cla.setCardOne(ran,null);
			}else{
				i += -1;
			}
		}
	}
	/******************************************************
	 *
	 *	山札の一番上から一枚カードを取り出す。
	 *	
	 *****************************************************/
	public Card getCardOne(){
		return talon.poll();
	}
	/******************************************************
	 *
	 *	山札の残り枚数を返す。
	 *
	 *****************************************************/
	public int getCardCount(){
		return talon.size();
	}
	/******************************************************
	 *
	 *	山札をそのまま渡す。
	 *
	 *****************************************************/
	public LinkedList<Card> gettalon(){
		return talon;
	}
}
