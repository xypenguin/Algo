//���̃N���X�̖��_
//util�N���X���g�����֗��c�[�� game.pk.FileRewrite;���Ăяo���Ă���̂�util��import���Ă���B
//�t�B�[���h��playerFile���قƂ�ǈӖ��𐬂��Ă��Ȃ��BsavePlayer���\�b�h���X�}�[�g�Ŗ����B
//������
//FileRewrite�N���X����肭�@�\���Ă��Ȃ����߂ɔ������Ă�����AFileRewrite�����������֐����グ��B

package jp.co.xypenguin.game;

import java.io.*;
import java.util.*;
import java.text.*;

import jp.co.xypenguin.game.effect.*;
import jp.co.xypenguin.game.pk.*;

public class Player{
	private File playerFile;
	private String name;	//�v���C���[�̖��O
	private int lv;			//���x���ƌo���l�̊֌W�̓v���C���Ԃ�Q�[�����Ɋ֌W����̂ō��͕��u
	private int ex;			//�Q�[���n�̎Q�l�����Ȃǂ𒲂ׂ�K�v������B
	private String title;	//�̍��B�����̏���B�����ŏo����v���C���[�̑����̈�ł���B
	private int health;		//�w���X�B�Q�[�����v���C����̂ɕK�v�B50�ȉ��ɂȂ����u�Ԃ���24���Ԍo��50�܂ŉ񕜂���B
	private String recovery;//���Ƀw���X���񕜂��鎞��
	private int coin;		//�ۋ��n�B
	private String[] words;	//�Q�[�����̑䎌�B
	
	/**
	  *	�R���X�g���N�^
	  */
	public Player(){
		System.out.println("ID�i���O�j����͂��ă��O�C�����Ă��������B");
		name = GameSystem.inputStringNull(1,16);
		if(name == null){		//���L���̏ꍇ
			name = "�m�[�l�[��";
		}
		playerFile = new File("player_" + name + ".txt");
		FileLoad();
	}
	/**
	  *	���O����t�@�C����ǂݍ��݁A�t�B�[���h�ɃZ�b�g����B
	  *	�t�@�C���������ꍇ�V�K�쐬����B
	  */
	private void FileLoad(){
		FileRewrite fr = new FileRewrite();
		if(fr.checkFile(playerFile) == false){
			System.out.println("�v���C���[�f�[�^������܂���B");
			System.out.println("�A�J�E���g���쐬���܂��B");
			fr.setFile(NewPlayer(name),"player_" + name + ".txt");
			new Star(40);	//���o���ʁB���������B
			System.out.println("[�V�K�A�J�E���g�쐬�����I]");	//�֌W���͖����A���͋C���b�Z�[�W�B
		}
		//�t�@�C������ǂݍ���Ńt�B�[���h�ɃZ�b�g�B
		ArrayList<String> list = fr.getFile("player_" + name + ".txt");
		lv = Integer.parseInt(fr.getStringMark(list,"���x���F"));
		ex = Integer.parseInt(fr.getStringMark(list,"�o���l�F"));
		title = fr.getStringMark(list,"�̍��F");
		health = Integer.parseInt(fr.getStringMark(list,"�Q�[���w���X�F"));
		coin = Integer.parseInt(fr.getStringMark(list,"�ڂ����R�C���F"));
		words = new String[3];
		words[0] = fr.getStringMark(list,"�퓬�O�䎌�F");
		words[1] = fr.getStringMark(list,"�����䎌�F");
		words[2] = fr.getStringMark(list,"�s�k�䎌�F");
	}
	/**
	  *	�V�K�v���C���[�t�@�C���̃e���v��
	  */
	private ArrayList<String> NewPlayer(String name){
		ArrayList<String> list = new ArrayList<String>();
		list.add("�v���C���[���F" + name);
		list.add("���x���F1");
		list.add("�o���l�F0");
		list.add("�̍��F�w���S�ҁx");
		list.add("�Q�[���w���X�F50");
		list.add("����w���X�񕜎��ԁF");
		list.add("�ڂ����R�C���F3");
		list.add("");
		list.add("�퓬�O�䎌�F");
		list.add("�����䎌�F");
		list.add("�s�k�䎌�F");
		list.add("");
		list.add("���A���S���ۂ���");
		list.add("�v���C�񐔁F0");
		list.add("�n�C�X�R�A�F0");
		list.add("");
		list.add("���Q�[��A");
		list.add("�v���C�񐔁F0");
		list.add("�n�C�X�R�A�F0");
		return list;
	}
	/**
	  *	�v���C���[�̃t�B�[���h���ꗗ�\������B
	  */
	public void dispField(){
		System.out.println();
		System.out.println("�v���C���[���F" + name);
		System.out.println("���x���F" + lv);
		System.out.println("�o���l�F" + ex);
		System.out.println("�̍��F" + title);
		System.out.println("�Q�[���w���X�F" + health);
		System.out.println("����w���X�񕜎��ԁF" + recovery);
		System.out.println("�ڂ����R�C���F" + coin);
		System.out.println();
	}
	/**
	  *	�v���C���[�f�[�^���t�@�C���ɏ�������
	  */
	public void savePlayer(){
		FileRewrite fr = new FileRewrite("player_" + name + ".txt","���x���F",String.valueOf(lv));
		fr = new FileRewrite("player_" + name + ".txt","�o���l�F",String.valueOf(ex));
		fr = new FileRewrite("player_" + name + ".txt","�̍��F",title);
		fr = new FileRewrite("player_" + name + ".txt","�Q�[���w���X�F",String.valueOf(health));
		fr = new FileRewrite("player_" + name + ".txt","����w���X�񕜎��ԁF",recovery);
		fr = new FileRewrite("player_" + name + ".txt","�ڂ����R�C���F",String.valueOf(coin));
		fr = new FileRewrite("player_" + name + ".txt","�퓬�O�䎌�F",words[0]);
		fr = new FileRewrite("player_" + name + ".txt","�����䎌�F",words[1]);
		fr = new FileRewrite("player_" + name + ".txt","�s�k�䎌�F",words[2]);
	}
	/**
	  *	�䎌�ύX
	  */
	public void modWords(){
		while(true){
			System.out.println("\n���݂̐ݒ�\n");
			System.out.println("�퓬�O�䎌�F" + words[0]);
			System.out.println("�����䎌�F" + words[1]);
			System.out.println("�s�k�䎌�F" + words[2]);
			System.out.println();
			if(words[0].length() == 0 && words[1].length() == 0 && words[2].length() == 0){
				System.out.println("�䎌�͐ݒ肳��Ă��܂���B�ݒ肵�܂����H");
			}else{
				System.out.println("�ύX���܂����H");
			}
			System.out.println("�ݒ肷��䎌��ԍ��őI�����ĉ������B");
			System.out.println("\n1 �j�퓬�O\n2 �j������\n3 �j�s�k��\n4 �j�ݒ���I������\n");
			Integer num = GameSystem.inputIntegerNull(1,4);
			if(num == null || num == 4){
				System.out.println("�ݒ���I�����܂��B");
				break;
			}else{
				System.out.println("�䎌����͂��Ă��������B");
				words[num - 1] = GameSystem.inputString(1,40);
			}
		}
	}
	/**
	  *	���ݎ��Ԃ�����w���X�񕜎��Ԃ𒴂��Ă����ꍇ�A
	  *	�w���X���ő�l�ł���50�ɂ���B
	  */
	public void recoveryHealth(){
/*		if(1 <= recovery.length()){
			SimpleDateFormat sdformat = new SimpleDateFormat("yyyy.MM.dd.HH");
			Date date = sdformat.parse(recovery);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			Calendar now = Calendar.getInstance();
			int time = now.compareTo(cal);
			if(0 <= time){
				health = 50;
			}
		}
*/	}
	/**
	  *	getName���\�b�h
	  */
	public String getName(){
		return name;
	}
	/**
	  *	getTitle���\�b�h
	  */
	public String getTitle(){
		return title;
	}
	/**
	  *	setTitle���\�b�h
	  */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	  *	getHealth���\�b�h
	  */
	public int getHealth(){
		return health;
	}
	/**
	  *	setHealth���\�b�h
	  */
	public void setHealth(int health){
		this.health = health;
	}
	/**
	  *	getRecoveryNow���\�b�h
	  */
	public String getRecoveryNow(int recoveryHour){
/*		Calendar cal = Calendar.getInstance();//���ݓ������擾
		cal.add(Calendar.HOUR_OF_DAY,recoveryHour);//���ݎ��Ԃ���񕜂܂ł̎��Ԃ𑫂�
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy.MM.dd.HH");//�\���`��
		Date date = sdformat.parse(cal);
		String str = format(cal.setTime(sdformat));
*/		return "";
	}
	/**
	  *	getRecovery���\�b�h
	  */
	public String getRecovery(){
		return recovery;
	}
	/**
	  *	setRecovery���\�b�h
	  */
	public void setRecovery(String recovery){
		this.recovery = recovery;
	}
	/**
	  *	getLv���\�b�h
	  */
	public int getLv(){
		return lv;
	}
	/**
	  *	setLv���\�b�h
	  */
	public void setLv(int lv){
		this.lv = lv;
	}
	/**
	  *	getEx���\�b�h
	  */
	public int getEx(){
		return ex;
	}
	/**
	  *	setEx���\�b�h
	  */
	public void setEx(int ex){
		this.ex = ex;
	}
	/**
	  *	getCoin���\�b�h
	  */
	public int getCoin(){
		return coin;
	}
	/**
	  *	setCoin���\�b�h
	  */
	public void setCoin(int coin){
		this.coin = coin;
	}
	/**
	  *	getWords���\�b�h
	  */
	public String getWords(int index){
		return words[index];
	}
	/**
	  *	setWords���\�b�h
	  */
	public void setWords(int index, String words){
		this.words[index] = words;
	}
	/**
	  *	dispWords���\�b�h
	  */
	public void dispWords(int index){
		if(1 <= words[index].length()){
			System.out.println(name + "�u" + words[index] + "�v");
		}
	}
}
