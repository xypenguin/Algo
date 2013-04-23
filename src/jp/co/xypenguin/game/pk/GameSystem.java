package jp.co.xypenguin.game.pk;

import java.io.*;
import java.util.Scanner;

/************************************************
 *	GameSystem�N���X
 *	�Q�[�����̃V�X�e�����܂Ƃ߂��N���X
 *	�E�����\�����x
 *	�E�G���^�[���͑҂�
 *	�Ey/n����
 *	�E�����̃X���b�g�\��
 *	�E�����_��
 *	�E�z��̌^�ϊ�
 ***********************************************/
public class GameSystem{

	/******************************************************
	 *	captionSpeed���\�b�h
	 *	�����̕�������ꕶ�����\������
	 *****************************************************/
	public static void captionSpeed(String line){
		for(int i = 0; i < line.length(); i++){	//0���當����̒������̃��[�v
			System.out.print(line.charAt(i));	//�������擪����ꕶ���\��
			wait(20);							//��u�~�܂�
		}
		System.out.println();						//���s
	}
	/******************************************************
	 *	captionSpeed���\�b�h�@�\�����x�ύX�o�[�W����
	 *	��1�����̐��l�̕�������\�����x�ɂȂ�i0.001�b�Ԋu�j
	 *	��2�����̕�������ꕶ�����\������
	 *****************************************************/	
	public static void captionSpeed(int x,String line){
		for(int i = 0; i < line.length(); i++){	//0���當����̒������̃��[�v
			System.out.print(line.charAt(i));	//�������擪����ꕶ���\��
			wait(x);							//��u�~�܂�
		}
		System.out.println();						//���s
	}
	/******************************************************
	 *	captionSpeed���\�b�h�@�\�����x�ύX�A�L�[���͑҂������ύX�o�[�W����
	 *	��1�����̐��l�̕�������\�����x�ɂȂ�i0.001�b�Ԋu�j
	 *	��2�����̕�������3�������\�������O�ɕ\������B
	 *	��3�����̕�������ꕶ�����\������
	 *****************************************************/	
	public static void captionSpeed(int x,String key,String line){
		for(int i = 0; i < line.length(); i++){		//0���當����̒������̃��[�v
			System.out.print(line.charAt(i) + key);	//�������擪����ꕶ���\��
			for(int j = 0; j < key.length(); j++){	//�L�[���͑҂���������
				if(key.charAt(j) < 256){			//�S�p�����p���`�F�b�N���Ă���
					System.out.print("\b");			//���p�Ȃ�1���o�b�N�X�y�[�X�Ŗ߂��B
				}else{
					System.out.print("\b\b");		//�S�p�Ȃ�2���o�b�N�X�y�[�X�Ŗ߂��B
				}
			}
			wait(x);							//��u�~�܂�
		}
		for(int k = 0; k < key.length(); k++){		//�L�[���͑҂������������B
			System.out.print("�@");
		}
		System.out.println();						//���s
	}
	/************************************************
	 *	wait���\�b�h
	 *	�����̒l�̎��Ԃ����������~�߂�B
	 *	����1��0.001�b�~�܂�B
	 ************************************************/
	public static void wait(int x){
		try{
			Thread.sleep(x);
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}
	/************************************************
	 *	enter���\�b�h
	 *	�����̉񐔕�enter�L�[�������܂ŏ������~�߂�B
	 ***********************************************/
	public static void enter(int x){
		Scanner scan = new Scanner(System.in);
		System.out.println("\r\n");
		System.out.print("���ɐi�ށ�");
		for(int i = 0; i < x; i++){
			scan.nextLine();
		}
		System.out.println("\r\n");
	}
	/************************************************
	 *	yn���\�b�h
	 *	���͂����� y�Ȃ�true�An�Ȃ�false�A��Ԃ��B
	 *	�߂�l�̌^��boolean�^�B
	 ***********************************************/
	public static boolean yn(){
		for(;;){
			try{
				System.out.println("(y)�͂��@(n)������");
				boolean a;
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(line.length() == 1){				//�ꕶ����������͂��Ă��邩����B
					char c = line.charAt(0);		//���Ȃ���΁Achar�^�ϐ�c�����̕����ŏ�����
					
					//�����R�[�h�ɕϊ����Ay�Ɠ�����������B
					if((int)c == 89 || (int)c == 121 || (int)c == 65369 || (int)c == 65337){
					//�������啶�����p�S�p�̎l��ނ�y�𔻒�B
						a = true;
						return a;
					//n�Ɠ�����������
					}else if((int)c == 78 || (int)c == 110 || (int)c == 65358 || (int)c == 65326){
						a = false;
						return a;
					}else{
					}
					System.out.println("");
				}
				System.out.println("\"y\"�܂���\"n\"���ꕶ�����͂��Ă��������B");
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	ynNull���\�b�h
	 *	���͂����� y�Ȃ�true n�Ȃ�false �������͂��Ȃ�������null ��Ԃ��B
	 *	�߂�l�̌^��Boolean�^�B
	 ***********************************************/
	public static Boolean ynNull(){
		for(;;){
			try{
				System.out.println("(y)�͂��@(n)������");
				Boolean a;
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(line.length() == 0){
					return null;
				}else if(line.length() == 1){				//�ꕶ����������͂��Ă��邩����B
					char c = line.charAt(0);		//���Ȃ���΁Achar�^�ϐ�c�����̕����ŏ�����
					
					//�����R�[�h�ɕϊ����Ay�Ɠ�����������B
					if((int)c == 89 || (int)c == 121 || (int)c == 65369 || (int)c == 65337){
					//�������啶�����p�S�p�̎l��ނ�y�𔻒�B
						a = true;
						return a;
					//n�Ɠ�����������
					}else if((int)c == 78 || (int)c == 110 || (int)c == 65358 || (int)c == 65326){
						a = false;
						return a;
					}else{
					}
					System.out.println("");
				}
				System.out.println("\"y\"�܂���\"n\"���ꕶ�����͂��Ă��������B");
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	dice���\�b�h
	 *	1�`�����̐����������_���ŕԂ��B
	 *	�T�C�R���Ƃ��Ďg���ꍇ�͈�����6�ɁB
	 *	������100�������1%�܂ł̊m������ɁB
	 ***********************************************/
	public static int dice(int x){
		x = (int)(Math.random() * x) + 1;
		return x;
	}
	/******************************************************
	 *	numberEffect���\�b�h
	 *	�ڂ܂��邵���ω����鐔����\�����邾���̃��\�b�h�B
	 *	�����̒l�̌��܂ł̐����ŕω����s���B
	 *	�����̌^��int�^�B�߂�l�͖����B
	 ******************************************************/
	public static void numberEffect(int x){
		try{
		
		//������10�ׂ̂���ŏ�Z�������̂��A�ϐ�keta����āA�����̍ő�l�����B
		int keta = 1;
			for(int i = 0; i < x; i++){
				keta = keta * 10;
			}
			//�����������_���ŕ\���������鏈���B
			for(int i = 0; i < 30; i++){		//�\���������鎞�Ԃ��J�E���^�ϐ��Œ���
				int y = (int)(Math.random() * ((keta-1)-(keta / 10) + 1)) + (keta / 10);
				System.out.print("\r" + y);
				Thread.sleep(40);
			}
			//�Ō�ɕ\�����ꂽ�������o�b�N�X�y�[�X�őS�ď����B
			for(int i = 0; i < x; i++){
				System.out.print("\b");
			}
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}
	/******************************************************
	 *	String�^�̔z��ɓ����Ă���S�Ă̗v�f�̒l��
	 *	int�^�ɕϊ�����int�^�̔z��ŕԂ��B
	 *****************************************************/
	public static int[] parseInts(String[] s){
		int[] x = new int[s.length];
		for(int i = 0; i < s.length; i++){
			x[i] = Integer.parseInt(s[i]);
		}
		return x;
	}
	/************************************************
	 *	inputInt���\�b�h
	 *	���͂����ߓ��͂��ꂽ���l��Ԃ��B
	 *	�߂�l�̌^��int�^�B
	 ***********************************************/
	public static int inputInt(){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				int num = Integer.parseInt(line);
				return num;
			}catch(IOException e){
				System.out.println(e);
			}catch(NumberFormatException e){
				System.out.println("��������͂��ĉ������B");
			}
		}
	}
	/************************************************
	 *	inputInt���\�b�h�@�I�[�o�[���[�h
	 *	���͂����ߓ��͂��ꂽ���l��Ԃ��B
	 *	��1�����ɉ����A��2�����ɏ����ݒ肷��B
	 *	�߂�l�̌^��int�^�B
	 ***********************************************/
	public static int inputInt(int min, int max){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				int num = Integer.parseInt(line);
				if(num < min || max < num){
					System.out.println(min + "�`" + max + "�̒l����͂��Ă��������B");
				}else{
					return num;
				}
			}catch(IOException e){
				System.out.println(e);
			}catch(NumberFormatException e){
				System.out.println("��������͂��ĉ������B");
			}
		}
	}
	/************************************************
	 *	inputIntegerNull���\�b�h
	 *	���͂����ߓ��͂��ꂽ���l��Ԃ��B
	 *	�������͂��Ȃ������ꍇ null ��Ԃ��B
	 *	�߂�l�̌^��Integer�^�B
	 ***********************************************/
	public static Integer inputIntegerNull(){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				Integer num;
				if(line.length() >= 1){
					num = Integer.valueOf(line);
				}else{
					num = null;
				}
				return num;
			}catch(IOException e){
				System.out.println(e);
			}catch(NumberFormatException e){
				System.out.println("��������͂��ĉ������B");
			}
		}
	}
	/************************************************
	 *	inputIntegerNull���\�b�h�@�I�[�o�[���[�h
	 *	���͂����ߓ��͂��ꂽ���l��Ԃ��B
	 *	��1�����ɉ����A��2�����ɏ����ݒ肷��B
	 *	�������͂��Ȃ������ꍇ null ��Ԃ��B
	 *	�߂�l�̌^��Integer�^�B
	 ***********************************************/
	public static Integer inputIntegerNull(int min, int max){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				Integer num;
				if(line.length() >= 1){
					num = Integer.valueOf(line);
					if(min <= num && num <= max){
						return num;
					}else{
						System.out.println(min + "�`" + max + "�̒l����͂��Ă��������B");
					}
				}else{
					return null;
				}
			}catch(IOException e){
				System.out.println(e);
			}catch(NumberFormatException e){
				System.out.println("��������͂��ĉ������B");
			}
		}
	}
	/************************************************
	 *	inputString���\�b�h
	 *	���͂����ߓ��͂��ꂽ�������Ԃ��B
	 *	�߂�l�̌^��String�^�B
	 ***********************************************/
	public static String inputString(){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(line.length() < 1){
					System.out.println("��������͂��Ă��������B");
				}else{
					return line;
				}
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	inputString���\�b�h�@�I�[�o�[���[�h
	 *	��1�����ɉ����A��2�����ɏ����ݒ肷��B
	 *	������������1�ɂ��邱�ƁB
	 *	������1�����̒l�������Ă����ꍇ�A1�Ƃ��ď��������B
	 *	
	 *	���͂����ߓ��͂��ꂽ�������Ԃ��B
	 *	�߂�l�̌^��String�^�B
	 ***********************************************/
	public static String inputString(int min, int max){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(min <= 0){
					min = 1;
				}
				if(max <= 0){
					max = 1;
				}
				if(min <= line.length() && line.length() <= max){
					return line;
				}else{
					System.out.println(min + "�����`" + max + "�����œ��͂��Ă��������B");
				}
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	inputStringNull()���\�b�h
	 *	���͂����ߓ��͂��ꂽ�������Ԃ��B
	 *	�������͂��Ȃ������ꍇ null ��Ԃ��B
	 *	�߂�l�̌^��String�^�B
	 ***********************************************/
	public static String inputStringNull(){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(line.length() == 0){
					line = null;
				}
				return line;
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	inputStringNull()���\�b�h�@�I�[�o�[���[�h
	 *	��1�����ɉ����A��2�����ɏ����ݒ肷��B
	 *	���͂����ߓ��͂��ꂽ�������Ԃ��B
	 *	�������͂��Ȃ������ꍇ null ��Ԃ��B
	 *	�߂�l�̌^��String�^�B
	 ***********************************************/
	public static String inputStringNull(int min, int max){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(0 >= min){
					min = 1;
				}
				if(0 >= max){
					max = 1;
				}
				if(line.length() == 0){
					return null;
				}else if(min <= line.length() && line.length() <= max){
					return line;
				}else{
					System.out.println(min + "�����`" + max + "�����œ��͂��Ă��������B");
				}
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
}
