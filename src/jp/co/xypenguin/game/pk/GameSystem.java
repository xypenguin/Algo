package jp.co.xypenguin.game.pk;

import java.io.*;
import java.util.Scanner;

/************************************************
 *	GameSystemクラス
 *	ゲーム中のシステムをまとめたクラス
 *	・文字表示速度
 *	・エンター入力待ち
 *	・y/n判定
 *	・数字のスロット表示
 *	・ランダム
 *	・配列の型変換
 ***********************************************/
public class GameSystem{

	/******************************************************
	 *	captionSpeedメソッド
	 *	引数の文字列を一文字ずつ表示する
	 *****************************************************/
	public static void captionSpeed(String line){
		for(int i = 0; i < line.length(); i++){	//0から文字列の長さ分のループ
			System.out.print(line.charAt(i));	//文字列を先頭から一文字表示
			wait(20);							//一瞬止まる
		}
		System.out.println();						//改行
	}
	/******************************************************
	 *	captionSpeedメソッド　表示速度変更バージョン
	 *	第1引数の数値の文字送り表示速度になる（0.001秒間隔）
	 *	第2引数の文字列を一文字ずつ表示する
	 *****************************************************/	
	public static void captionSpeed(int x,String line){
		for(int i = 0; i < line.length(); i++){	//0から文字列の長さ分のループ
			System.out.print(line.charAt(i));	//文字列を先頭から一文字表示
			wait(x);							//一瞬止まる
		}
		System.out.println();						//改行
	}
	/******************************************************
	 *	captionSpeedメソッド　表示速度変更、キー入力待ち文字変更バージョン
	 *	第1引数の数値の文字送り表示速度になる（0.001秒間隔）
	 *	第2引数の文字列を第3引数が表示される前に表示する。
	 *	第3引数の文字列を一文字ずつ表示する
	 *****************************************************/	
	public static void captionSpeed(int x,String key,String line){
		for(int i = 0; i < line.length(); i++){		//0から文字列の長さ分のループ
			System.out.print(line.charAt(i) + key);	//文字列を先頭から一文字表示
			for(int j = 0; j < key.length(); j++){	//キー入力待ち文字分を
				if(key.charAt(j) < 256){			//全角か半角かチェックしてから
					System.out.print("\b");			//半角なら1個分バックスペースで戻す。
				}else{
					System.out.print("\b\b");		//全角なら2個分バックスペースで戻す。
				}
			}
			wait(x);							//一瞬止まる
		}
		for(int k = 0; k < key.length(); k++){		//キー入力待ち文字を消す。
			System.out.print("　");
		}
		System.out.println();						//改行
	}
	/************************************************
	 *	waitメソッド
	 *	引数の値の時間だけ処理を止める。
	 *	引数1で0.001秒止まる。
	 ************************************************/
	public static void wait(int x){
		try{
			Thread.sleep(x);
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}
	/************************************************
	 *	enterメソッド
	 *	引数の回数分enterキーを押すまで処理を止める。
	 ***********************************************/
	public static void enter(int x){
		Scanner scan = new Scanner(System.in);
		System.out.println("\r\n");
		System.out.print("次に進む▼");
		for(int i = 0; i < x; i++){
			scan.nextLine();
		}
		System.out.println("\r\n");
	}
	/************************************************
	 *	ynメソッド
	 *	入力を求め yならtrue、nならfalse、を返す。
	 *	戻り値の型はboolean型。
	 ***********************************************/
	public static boolean yn(){
		for(;;){
			try{
				System.out.println("(y)はい　(n)いいえ");
				boolean a;
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(line.length() == 1){				//一文字だけを入力しているか判定。
					char c = line.charAt(0);		//問題なければ、char型変数cをその文字で初期化
					
					//文字コードに変換し、yと等しいか判定。
					if((int)c == 89 || (int)c == 121 || (int)c == 65369 || (int)c == 65337){
					//小文字大文字半角全角の四種類のyを判定。
						a = true;
						return a;
					//nと等しいか判定
					}else if((int)c == 78 || (int)c == 110 || (int)c == 65358 || (int)c == 65326){
						a = false;
						return a;
					}else{
					}
					System.out.println("");
				}
				System.out.println("\"y\"または\"n\"を一文字入力してください。");
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	ynNullメソッド
	 *	入力を求め yならtrue nならfalse 何も入力しなかったらnull を返す。
	 *	戻り値の型はBoolean型。
	 ***********************************************/
	public static Boolean ynNull(){
		for(;;){
			try{
				System.out.println("(y)はい　(n)いいえ");
				Boolean a;
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(line.length() == 0){
					return null;
				}else if(line.length() == 1){				//一文字だけを入力しているか判定。
					char c = line.charAt(0);		//問題なければ、char型変数cをその文字で初期化
					
					//文字コードに変換し、yと等しいか判定。
					if((int)c == 89 || (int)c == 121 || (int)c == 65369 || (int)c == 65337){
					//小文字大文字半角全角の四種類のyを判定。
						a = true;
						return a;
					//nと等しいか判定
					}else if((int)c == 78 || (int)c == 110 || (int)c == 65358 || (int)c == 65326){
						a = false;
						return a;
					}else{
					}
					System.out.println("");
				}
				System.out.println("\"y\"または\"n\"を一文字入力してください。");
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	diceメソッド
	 *	1～引数の整数をランダムで返す。
	 *	サイコロとして使う場合は引数を6に。
	 *	引数に100を入れると1%までの確率判定に。
	 ***********************************************/
	public static int dice(int x){
		x = (int)(Math.random() * x) + 1;
		return x;
	}
	/******************************************************
	 *	numberEffectメソッド
	 *	目まぐるしく変化する数字を表示するだけのメソッド。
	 *	引数の値の桁までの数字で変化を行う。
	 *	引数の型はint型。戻り値は無し。
	 ******************************************************/
	public static void numberEffect(int x){
		try{
		
		//引数を10のべき乗で乗算したものを、変数keta入れて、乱数の最大値を作る。
		int keta = 1;
			for(int i = 0; i < x; i++){
				keta = keta * 10;
			}
			//数字をランダムで表示し続ける処理。
			for(int i = 0; i < 30; i++){		//表示し続ける時間をカウンタ変数で調整
				int y = (int)(Math.random() * ((keta-1)-(keta / 10) + 1)) + (keta / 10);
				System.out.print("\r" + y);
				Thread.sleep(40);
			}
			//最後に表示された数字をバックスペースで全て消す。
			for(int i = 0; i < x; i++){
				System.out.print("\b");
			}
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}
	/******************************************************
	 *	String型の配列に入っている全ての要素の値を
	 *	int型に変換してint型の配列で返す。
	 *****************************************************/
	public static int[] parseInts(String[] s){
		int[] x = new int[s.length];
		for(int i = 0; i < s.length; i++){
			x[i] = Integer.parseInt(s[i]);
		}
		return x;
	}
	/************************************************
	 *	inputIntメソッド
	 *	入力を求め入力された数値を返す。
	 *	戻り値の型はint型。
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
				System.out.println("整数を入力して下さい。");
			}
		}
	}
	/************************************************
	 *	inputIntメソッド　オーバーロード
	 *	入力を求め入力された数値を返す。
	 *	第1引数に下限、第2引数に上限を設定する。
	 *	戻り値の型はint型。
	 ***********************************************/
	public static int inputInt(int min, int max){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				int num = Integer.parseInt(line);
				if(num < min || max < num){
					System.out.println(min + "～" + max + "の値を入力してください。");
				}else{
					return num;
				}
			}catch(IOException e){
				System.out.println(e);
			}catch(NumberFormatException e){
				System.out.println("整数を入力して下さい。");
			}
		}
	}
	/************************************************
	 *	inputIntegerNullメソッド
	 *	入力を求め入力された数値を返す。
	 *	何も入力しなかった場合 null を返す。
	 *	戻り値の型はInteger型。
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
				System.out.println("整数を入力して下さい。");
			}
		}
	}
	/************************************************
	 *	inputIntegerNullメソッド　オーバーロード
	 *	入力を求め入力された数値を返す。
	 *	第1引数に下限、第2引数に上限を設定する。
	 *	何も入力しなかった場合 null を返す。
	 *	戻り値の型はInteger型。
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
						System.out.println(min + "～" + max + "の値を入力してください。");
					}
				}else{
					return null;
				}
			}catch(IOException e){
				System.out.println(e);
			}catch(NumberFormatException e){
				System.out.println("整数を入力して下さい。");
			}
		}
	}
	/************************************************
	 *	inputStringメソッド
	 *	入力を求め入力された文字列を返す。
	 *	戻り値の型はString型。
	 ***********************************************/
	public static String inputString(){
		for(;;){
			try{
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String line = reader.readLine();
				if(line.length() < 1){
					System.out.println("文字を入力してください。");
				}else{
					return line;
				}
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	inputStringメソッド　オーバーロード
	 *	第1引数に下限、第2引数に上限を設定する。
	 *	ただし下限は1にすること。
	 *	下限に1未満の値が入っていた場合、1として処理される。
	 *	
	 *	入力を求め入力された文字列を返す。
	 *	戻り値の型はString型。
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
					System.out.println(min + "文字～" + max + "文字で入力してください。");
				}
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
	/************************************************
	 *	inputStringNull()メソッド
	 *	入力を求め入力された文字列を返す。
	 *	何も入力しなかった場合 null を返す。
	 *	戻り値の型はString型。
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
	 *	inputStringNull()メソッド　オーバーロード
	 *	第1引数に下限、第2引数に上限を設定する。
	 *	入力を求め入力された文字列を返す。
	 *	何も入力しなかった場合 null を返す。
	 *	戻り値の型はString型。
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
					System.out.println(min + "文字～" + max + "文字で入力してください。");
				}
			}catch(IOException e){
				System.out.println(e);
			}
		}
	}
}
