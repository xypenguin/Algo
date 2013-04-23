package jp.co.xypenguin.game.pk;

import java.io.*;
import java.util.*;

public class FileRewrite{
	private ArrayList<String> list;
	
	public FileRewrite(){
	}
	/******************************************************
	 *	txt�t�@�C���̎w��s������������
	 *	�t�@�C�����A�w��s�A������
	 *****************************************************/
	public FileRewrite(String filename,int num,String str){
		list = getFile(filename);
		int a = list.size();
		for(int i = 0; i < num - a; i++){
			list.add("");
		}
		list.set(num - 1,str);
		setFile(list,filename);
	}
	
	/******************************************************
	 *	txt�t�@�C���̎w�蕶����̌�̕����������������B
	 *	�t�@�C�����A�w�蕶����A������
	 *****************************************************/
	public FileRewrite(String filename, String mark, String newline){
		list = getFile(filename);
		int index = 0;
		while(list.get(index).indexOf(mark) == -1){
			index += 1;
			if(index == list.size()){
				System.out.println("�u" + mark + "�v�͌�����܂���B�������������Ă��������B");
			}
		}
		String str = list.get(index);
		list.set(index,str.substring(0,mark.length()) + newline);
		setFile(list,filename);
	}
	
	/******************************************************
	 *	ArrayList<String>���̎w�肳�ꂽ�v�f�ȍ~�̎w�蕶����̌�̕�������擾����B
	 *	list�A�w��v�f�A�w�蕶����
	 *****************************************************/
	public String getStringMark(ArrayList<String> list,String search, String mark){
		int index = list.indexOf(search);
		//�`�F�b�N��O
		if(index == -1){
			System.out.println("�u" + search + "�v�͌�����܂���B�������������Ă��������B");
		}
		while(list.get(index).indexOf(mark) == -1){
			index += 1;
			if(index == list.size()){
				System.out.println("�u" + mark + "�v�͌�����܂���B�������������Ă��������B");
			}
		}
		String str = list.get(index);
		String line = str.substring(mark.length());
		return line;
	}
	/******************************************************
	 *	ArrayList<String>���̎w�肳�ꂽ�v�f�ȍ~�̎w�蕶����̌�̕����������������B
	 *	list�A�w��v�f�A�w�蕶����A���������镶����
	 *****************************************************/
	public ArrayList<String> setStringMark(ArrayList<String> list, String search, String mark, String newline){
		int index = list.indexOf(search);
		//�`�F�b�N��O
		if(index == -1){
			System.out.println("�u" + search + "�v�͌�����܂���B�������������Ă��������B");
		}
		while(list.get(index).indexOf(mark) == -1){
			index += 1;
			if(index == list.size()){
				System.out.println("�u" + mark + "�v�͌�����܂���B�������������Ă��������B");
			}
		}
		String str = list.get(index);
		list.set(index,str.substring(0,mark.length()) + newline);
		return list;
	}
	/******************************************************
	 *	ArrayList<String>���̎w�蕶����̌�̕�������擾����B
	 *	list�A�w�蕶����
	 *****************************************************/
	public String getStringMark(ArrayList<String> list, String mark){
		int index = 0;
		while(list.get(index).indexOf(mark) == -1){
			index += 1;
			if(index == list.size()){
				System.out.println("�u" + mark + "�v�͌�����܂���B�������������Ă��������B");
			}
		}
		String str = list.get(index);
		String line = str.substring(mark.length());
		return line;
	}
	
	/******************************************************
	 *	txt�t�@�C���̕������S�擾����
	 *	ArrayList<String>�ŕԂ��B
	 *****************************************************/
	public ArrayList<String> getFile(String filename){
		list = new ArrayList<String>();
		try{
			File file = new File(filename);
			if(checkFile(file) == true){
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				String line;
				while((line = reader.readLine()) != null){
					list.add(line);
				}
				reader.close();
			}else{
				System.out.println(filename + "��������܂���ł����B");
			}
		}catch(FileNotFoundException e){
			System.out.println(filename + "��������܂���B");
		}catch(IOException e){
			System.out.println(e);
		}
		return list;
	}
	/******************************************************
	 *	ArrayList<String>�̑S�v�f��txt�t�@�C���ɏo��
	 *	
	 *****************************************************/
	public void setFile(ArrayList<String> list,String filename){
		try{
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(String line : list){
				writer.println(line);
			}
			writer.close();
		}catch(FileNotFoundException e){
			System.out.println(filename + "��������܂���B");
		}catch(IOException e){
			System.out.println(e);
		}
	}
	/******************************************************
	 *	�v���C�x�[�g���\�b�h
	 *	�t�@�C�������邩�ǂ����A
	 *	�t�@�C���Ȃ̂��A�ǂݍ��݉\�Ȃ̂����`�F�b�N����B
	 *****************************************************/
	public boolean checkFile(File file){
		if(file.exists()){			//�����̃t�@�C�������݂���̂�
			//���̃t�@�C���͕��ʂ̃t�@�C���Ȃ̂��i�t�H���_�Ƃ��ł͂Ȃ��j
			//�����āA���̃t�@�C���͓ǂݍ��݉\�t�@�C���Ȃ̂��`�F�b�N
			if(file.isFile() && file.canRead()){
				return true;	//�S���\�Ŗ��Ȃ��t�@�C���Ȃ�^��Ԃ�
			}
		}
		return false;	//����ȊO�Ȃ�t�@�C���ɖ�肪����̂ŋU��Ԃ�
	}
}
