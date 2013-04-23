package jp.co.xypenguin.game;

import jp.co.xypenguin.game.algo.*;
import jp.co.xypenguin.game.pk.*;

class Execute{
	public static void main(String[] args){
		System.out.println("�悤�����A�ڂ����Q�[���^�E���ցI");
		Player p = new Player();
		p.recoveryHealth();
		
		PlayGame[] playGame = {
			new Algo(p),
		};
		
		while(true){
			if(1 <= p.getHealth()){
				System.out.println();
				System.out.println("�v���C����Q�[����I�����Ă��������B");
				System.out.println("1 �j�ڂ��������A���S���ۂ���\n101 �j�e��ݒ�\n");
				
				Integer selection = GameSystem.inputIntegerNull(1,101);
				if(selection == null){	//�����I�����Ȃ������ꍇ
					Recommendation r = new Recommendation();	//�������߂��Ăяo���N���X
					selection = r.getSelection();
					if(selection != null){
						playGame[selection - 1].play();
						playGame[selection - 1].savetext();
					}
				}else if(2 <= selection && selection <= 100){
					System.out.println("���̃Q�[���͂܂�����܂���B�s�Ӎ쐬���B");
				}else if(selection == 101){
					p.dispField();
					p.modWords();
					p.dispWords(0);
				}else{
					playGame[selection - 1].play();
					playGame[selection - 1].savetext();
				}
				p.recoveryHealth();
				p.savePlayer();
			}else{
				if(p.getCoin() <= 0){
					break;
				}
				System.out.println("�w���X������܂���B�R�C�����g���܂����H");
				if(GameSystem.yn() == true){
					p.setCoin(p.getCoin() - 1);
					p.setHealth(30);
				}else{
					break;
				}
			}
		}
		System.out.println("�����l�ł����B");
		System.out.println("�܂������V��ł��������ˁB");
	}
}
