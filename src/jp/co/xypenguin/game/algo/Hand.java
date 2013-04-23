package jp.co.xypenguin.game.algo;

import java.util.*;

public class Hand{
	private ArrayList<Card> hand;	//��D
	private TalonAlgo talon;		//�����R�D

	/******************************************************
	 *	�R���X�g���N�^
	 *	�����R�D�iTalonAlgo�j���t�B�[���h�ɑ������
	 *	�R�D�����D�ihand�j��7���擾����
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
	 *	��D��\������B
	 *	card.getBothSides�̓J�[�h�������\���𔻕ʂ���B
	 *	false�͗����Ӗ�����B���Ȃ�J�[�h�̐F�͕����邪�����͌����Ȃ��B
	 *****************************************************/
	public void dispHand(){
		sort();
		for(int i = 0; i < hand.size(); i++){
			Card card = hand.get(i);
			if(card.getBothSides() == true){
				System.out.printf("[%s%2d]",card.getKind(),card.getNumber());
			}else{
				System.out.printf("[%s��]",card.getKind());
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
	 *	�o�u���\�[�g
	 *	�����A���������Ȃ�Ԃ̕���傫���ƕ]������
	 *	
	 *****************************************************/
	public void sort(){
		for(int j = 0; j < hand.size() - 1; j++){
			for(int i = 0; i < hand.size() - 1 - j; i++){
				//�ׂ荇�����J�[�h�̐��̑傫����]�����ē���ւ���
				//�Y�����̑傫�����ɐ��̑傫���J�[�h������B
				if(hand.get(i + 1).getNumber() < hand.get(i).getNumber()){
					Card card = hand.get(i);
					hand.set(i,hand.get(i + 1));
					hand.set(i + 1,card);
				}
				//�ׂ荇�����J�[�h�̎�ނ�]�����ē���ւ���
				//�J�[�h�̐��������ꍇ�A�Y�����̑傫�����ɐԂ�����
				if(hand.get(i + 1).getNumber() == hand.get(i).getNumber() && hand.get(i).getKind().equals("��")){
					Card card = hand.get(i);
					hand.set(i,hand.get(i + 1));
					hand.set(i + 1,card);
				}
			}
		}
	}
	/******************************************************
	 *
	 *	��D�𑝂₷
	 *	
	 *****************************************************/
	public void setHand(Card card){
		hand.add(card);
	}
	/******************************************************
	 *
	 *	��D���ꖇ���o��
	 *	
	 *****************************************************/
	public Card getHandOne(int index){
		Card card = hand.get(index -1);
		hand.remove(index - 1);
		return card;
	}
	/******************************************************
	 *
	 *	��D���擾����
	 *	
	 *****************************************************/
	public ArrayList<Card> getHand(){
		return hand;
	}
	/**
	  *	��D��false�̐���Ԃ��B
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
