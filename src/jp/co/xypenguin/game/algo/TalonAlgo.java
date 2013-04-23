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
	 *	�R�D�̈�ԏォ��ꖇ�J�[�h�����o���B
	 *	
	 *****************************************************/
	public Card getCardOne(){
		return talon.poll();
	}
	/******************************************************
	 *
	 *	�R�D�̎c�薇����Ԃ��B
	 *
	 *****************************************************/
	public int getCardCount(){
		return talon.size();
	}
	/******************************************************
	 *
	 *	�R�D�����̂܂ܓn���B
	 *
	 *****************************************************/
	public LinkedList<Card> gettalon(){
		return talon;
	}
}
