package jp.co.xypenguin.game;

import jp.co.xypenguin.game.algo.*;
import jp.co.xypenguin.game.pk.*;

public class Recommendation{
	private Integer selection;
	
	Recommendation(){
		inform();	//����\��
		System.out.println("�������߃Q�[�����v���C���܂����H");
		Boolean b = GameSystem.ynNull();
		if(b == null || b == true){
			selection = 1;
		}
	}
	private void inform(){
		System.out.println("�����̃I�X�X���Q�[���́w�ڂ��������A���S���ۂ��́x�ł��B");
		System.out.println();
		System.out.println("�������܌o���l2�{�L�����y�[�����{���I");
		System.out.println("����R�X�`���[�����Q�b�g�o���邩���I�H");
		System.out.println();
	}
	public Integer getSelection(){
		return selection;
	}
}
