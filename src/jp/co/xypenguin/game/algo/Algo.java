package jp.co.xypenguin.game.algo;

import java.io.*;
import java.util.*;
import jp.co.xypenguin.game.*;
import jp.co.xypenguin.game.algo.*;
import jp.co.xypenguin.game.pk.*;

public class Algo extends CardGame{
	private Player p;
	private TalonAlgo talon;
	private Hand player;
	private Hand com;
	private int score;
	private int playTime;
	private int miss;
	private ArrayList<String> list;
	
	public Algo(Player p){
		this.p = p;
		setGameName("�A���S���ۂ���");
		
	}
	@Override
	public void play(){
		p.setHealth(p.getHealth() - 1);	//�Q�[���J�n���Ƀv���C���[�̃w���X��1����
		p.setRecovery(p.getRecoveryNow(24));//���ݓ���+24���Ԃ̎������擾���ăt�B�[���h�ɃZ�b�g
		
		FileRewrite fr = new FileRewrite();
		list = fr.getFile("player_" + p.getName() + ".txt");
		setPlayCount(Integer.parseInt(fr.getStringMark(list,"��" + getGameName(),"�v���C�񐔁F")));
		setHiScore(Integer.parseInt(fr.getStringMark(list,"��" + getGameName(),"�n�C�X�R�A�F")));
		
		GameSystem.captionSpeed("�Q�[�����w�ڂ��������A���S���ۂ��́x");
		System.out.println();
		p.dispWords(0);
		
		//���Ԃ��v�邽�߃}���`�X���b�h�Ŏ��s���܂��B
		Time time = new Time();
		time.start();
		
		talon = new TalonAlgo();	//�R�D�����܂��B
		player = new Hand(talon, true);	//�v���C���[�̎�D���R�D������܂��B
		com = new Hand(talon, false);	//����̎�D���R�D������܂��B
		
		disp();	//�\���p
		GameSystem.captionSpeed("�ŏ��ɑ���̎�D���ꖇ�A�\�������邱�Ƃ��o���܂��B");
		GameSystem.captionSpeed("�����牽�Ԗڂ�\�������܂����H");
		
		Integer index = GameSystem.inputIntegerNull(1,com.getHand().size());
		if(index == null){	//�������͂��Ȃ������ꍇ�B
			GameSystem.captionSpeed("������œK���ɕ\�������Ă����܂��B");
			com.getHand().get(3).setBothSides(true);	//������4�Ԗڂ̃J�[�h��true�i�\�j�ɂ���
		}else{
			com.getHand().get(index - 1).setBothSides(true);
		}
		
		//�Q�[���I������
		while(allTrue(com) == false && 0 < talon.getCardCount()){
			attack();
		}
		GameSystem.captionSpeed("�Q�[���I���I");
		time.stop();
		//stop���\�b�h���playtime���\�b�h�̎��s����������ƕ\�������������Ȃ�܂��B
		//�Ȃ̂�join�őҋ@�����܂��B
		//java5�ȍ~�ł͕����̃X���b�h�̓����̊Ǘ���CountDownLatch�N���X�𗘗p���邱�Ƃ���������Ă���悤�ł����A
		//join��join�Ŗ��Ȃ��悤�Ɏv���܂����B�Ƃ����̂͌��O�ł���CountDownLatch�N���X��ǂ�ł������o���܂���ł����B
		//�}���`�X���b�h�͓���ł��B
		time.threadJoin();
		GameSystem.captionSpeed("�����l�ł����B");
		playTime = time.playtime();
		score = calcScore();
		GameSystem.captionSpeed("����̃Q�[���̓_���� " + score + " �_�ł��B");
		p.setEx(p.getEx() + (int)(score / 10));
		GameSystem.captionSpeed("�v���C���ԁF" + playTime + "�b�ł��B");
		setPlayCount(getPlayCount() + 1);
		if(getHiScore() < score){
			System.out.println("�n�C�X�R�A�X�V�I�I");
			setHiScore(score);
		}
	}
	/******************************************************
	 *	�R�D�italon�j����J�[�h���ꖇ�������ĕ\�����ɃZ�b�g��
	 *	player��com�̑o���ɓn���B
	 *	
	 *****************************************************/
	private void drawCard(){
		GameSystem.captionSpeed("�R�D����ꖇ���J�[�h�������܂��B");
		
		Card card = talon.getCardOne();
		card.setBothSides(true);
		player.setHand(card);
		player.sort();
		GameSystem.captionSpeed("�����̎�D�̍����� " + (player.getHand().indexOf(card) + 1) + " �Ԗڂɓ���܂����B");
		
		card = talon.getCardOne();
		card.setBothSides(true);
		com.setHand(card);
		com.sort();
		GameSystem.captionSpeed("����̎�D�̍����� " + (com.getHand().indexOf(card) + 1) + " �Ԗڂɓ���܂����B");
	}
	/******************************************************
	 *	����̎�D�̃J�[�h�𓖂Ă�B
	 *	
	 *****************************************************/
	private void attack(){		//�\�[�X�R�[�h�����h���̂ŗǂ��Ȃ��B�œK������K�v������Ǝv���܂��B
		outer:
		for(;;){
			disp();
			GameSystem.captionSpeed("�����牽�Ԗڂ̃J�[�h�𓖂Ă܂����H");
			Integer index = selectCardNum();
			if(index == null){
				if(talon.getCardCount() <= 2){
					GameSystem.captionSpeed("����ȏ�J�[�h���������Ƃ��o���܂���B�M�u�A�b�v���܂����H");
					Boolean b = GameSystem.ynNull();
					if(b == null || b == true){
						talon.gettalon().clear();
					}else{
						continue outer;
					}
				}else{
					GameSystem.captionSpeed("�J�[�h�𓖂Ă��ɐV�����J�[�h�������܂����H");
					Boolean b = GameSystem.ynNull();
					if(b == null || b == true){
						drawCard();
						miss += 1;
						if(miss == 2 && 1 < com.getHandBothSidesFalse()){
							randomTrue();
							miss = 0;
						}
						disp();
						if(2 <= player.getHand().size()){
							GameSystem.captionSpeed("�����̎�D����J�[�h���ꖇ�I�����đ���ɓn���Ă��������B");
							GameSystem.captionSpeed("�����牽�Ԗڂ̃J�[�h��n���܂����H");
							Integer i = GameSystem.inputIntegerNull(1,player.getHand().size());
							if(i == null){
								GameSystem.captionSpeed("������ōœK�ȃJ�[�h��I��ł����܂��B");
								com.setHand(player.getHandOne(1));
							}else{
								com.setHand(player.getHandOne(i));
							}
						}
					}else{
						continue outer;
					}
				}
			}else{
				GameSystem.captionSpeed(index + "�Ԗڂ̃J�[�h�̐����́H");
				int num = GameSystem.inputInt();
				if(com.getHand().get(index - 1).getNumber() == num){
					GameSystem.captionSpeed("�����ł��B");
					com.getHand().get(index - 1).setBothSides(true);
					miss = 0;
				}else{
					GameSystem.captionSpeed("�s�����ł��B");
					if(talon.getCardCount() <= 2){
						talon.gettalon().clear();
					}else{
						drawCard();
						miss += 1;
						if(miss == 2){
							randomTrue();
							miss = 0;
						}
						disp();
						if(2 <= player.getHand().size()){
							GameSystem.captionSpeed("�����̎�D����J�[�h���ꖇ�I�����đ���ɓn���Ă��������B");
							GameSystem.captionSpeed("�����牽�Ԗڂ̃J�[�h��n���܂����H");
							Integer i = GameSystem.inputIntegerNull(1,player.getHand().size());
							if(i == null){
								GameSystem.captionSpeed("������ōœK�ȃJ�[�h��I��ł����܂��B");
								com.setHand(player.getHandOne(1));
							}else{
								com.setHand(player.getHandOne(i));
							}
						}
					}
				}
			}
			break;
		}
	}
	/******************************************************
	 *	���ʂ��Ă���ԍ��̃J�[�h�͑I�ׂȂ�
	 *	
	 *****************************************************/
	private Integer selectCardNum(){
		outer:
		for(;;){
			Integer index = GameSystem.inputIntegerNull(1,com.getHand().size());
			if(index == null){
				index = null;
			}else{
				if(com.getHand().get(index - 1).getBothSides() == true){
				GameSystem.captionSpeed("���̃J�[�h�̐����͂��łɔ������Ă��܂��B");
				continue outer;
				}
			}
			return index;
		}
	}
	/******************************************************
	 *	��D�̃J�[�h���S�ė��\���S�ĕ\�itrue�j��������true
	 *	����ȊO��������false��Ԃ����\�b�h
	 *****************************************************/
	private boolean allTrue(Hand hand){
		boolean value = true;
		for(Card card : hand.getHand()){
			if(card.getBothSides() == false){
				value = false;
			}
		}
		return value;
	}
	/******************************************************
	 *	player��com�̎�D�\����ʗp
	 *	
	 *****************************************************/
	private void disp(){
		System.out.println();
		GameSystem.captionSpeed("����̎�D");
		com.dispHand();
		System.out.println();
		GameSystem.captionSpeed("�����̎�D");
		player.dispHand();
		GameSystem.captionSpeed("�c���D" + talon.getCardCount() + "��");
		System.out.println();
	}
	/******************************************************
	 *	��D���ꖇ�������_���ŕ\�ɂ���
	 *	
	 *****************************************************/
	private void randomTrue(){
		System.out.println();
		GameSystem.captionSpeed("�Ȃ��Ȃ����Ă��Ȃ��悤�ł��ˁB");
		GameSystem.captionSpeed("����̎�D�������_���ňꖇ�\�ɂ��Ă����܂��ˁB");
		int num;
		for(;;){
			num = GameSystem.dice(com.getHand().size()) - 1;
			if(com.getHand().get(num).getBothSides() == false){
				break;
			}
		}
		com.getHand().get(num).setBothSides(true);
	}
	/**
	  *	�X�R�A���v�Z���ĕԂ��B
	  *	
	  */
	private int calcScore(){
		int score;
		score = (int)((talon.getCardCount() * 1000) / (playTime * 0.1) + ((talon.getCardCount() + 1) * 1000) / (40 * 0.1)) - 250;
		return score;
	}
	/**
	  *	�e�L�X�g�ɏ�������
	  *	
	  */
	@Override
	public void savetext(){
		FileRewrite fr = new FileRewrite();
		
		list = fr.setStringMark(list,"��" + getGameName(),"�v���C�񐔁F",String.valueOf(getPlayCount()));
		list = fr.setStringMark(list,"��" + getGameName(),"�n�C�X�R�A�F",String.valueOf(getHiScore()));
		fr.setFile(list,"player_" + p.getName() + ".txt");
	}
}
