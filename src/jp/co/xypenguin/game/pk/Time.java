package jp.co.xypenguin.game.pk;

import jp.co.xypenguin.game.pk.GameSystem;

/******************************************************
 *	Time�N���X
 *	�}���`�X���b�h�Ŏ��Ԍo�߂�\��
 *****************************************************/
public class Time implements Runnable{
	private long startTime;
	private long endTime;
	private boolean end;
	private Thread th;
	
	//�R���X�g���N�^
	public Time(){
		end = false;
	}
	
	//���g�̃C���X�^���X��Thread�N���X�̃C���X�^���X���쐬����start������B
	public void start(){
		th = new Thread(this);
		th.start();
	}
	
	//��b���ƂɌo�ߎ��Ԃ��\�������B
	public void run(){
		startTime = System.currentTimeMillis() / 1000;
		
		//�R�}���h�v�����v�g�Ŏ��Ԃ�\��������ɂ�
		//ProcessBuilder�ŐV���Ƀv�����v�g���J���ĕ\����������@�Ȃǂ�����悤�ł����A���܂�g�����肪�悭�Ȃ��Ǝv�����̂Ŏ������܂���B
		while(end != true){
		//	System.out.print("�o�ߎ���" + (System.currentTimeMillis() / 1000 - startTime));
			GameSystem.wait(100);
		}
		endTime = System.currentTimeMillis() / 1000;
	}
	
	//��L�̃��[�v�𔲂���X�C�b�`
	public void stop(){
		end = true;
	}
	
	//start����stop�������܂ł̎���
	public int playtime(){
		return (int)(endTime - startTime);
	}
	
	//���̃��\�b�h���Ăяo�����X���b�h��run���I���܂ŏ�����i�߂Ȃ��B
	public void threadJoin(){
		try{
			th.join();
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}
}
