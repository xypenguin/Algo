//このクラスの問題点
//utilクラスを使った便利ツール game.pk.FileRewrite;を呼び出しているのにutilをimportしている。
//フィールドのplayerFileがほとんど意味を成していない。savePlayerメソッドがスマートで無い。
//解決策
//FileRewriteクラスが上手く機能していないために発生している問題、FileRewriteを見直し利便性を上げる。

package jp.co.xypenguin.game;

import java.io.*;
import java.util.*;
import java.text.*;

import jp.co.xypenguin.game.effect.*;
import jp.co.xypenguin.game.pk.*;

public class Player{
	private File playerFile;
	private String name;	//プレイヤーの名前
	private int lv;			//レベルと経験値の関係はプレイ時間やゲーム性に関係するので今は放置
	private int ex;			//ゲーム系の参考文献などを調べる必要がある。
	private String title;	//称号。ただの飾り。文字で出来るプレイヤーの装飾の一つである。
	private int health;		//ヘルス。ゲームをプレイするのに必要。50以下になった瞬間から24時間経つと50まで回復する。
	private String recovery;//次にヘルスが回復する時間
	private int coin;		//課金系。
	private String[] words;	//ゲーム中の台詞。
	
	/**
	  *	コンストラクタ
	  */
	public Player(){
		System.out.println("ID（名前）を入力してログインしてください。");
		name = GameSystem.inputStringNull(1,16);
		if(name == null){		//未記入の場合
			name = "ノーネーム";
		}
		playerFile = new File("player_" + name + ".txt");
		FileLoad();
	}
	/**
	  *	名前からファイルを読み込み、フィールドにセットする。
	  *	ファイルが無い場合新規作成する。
	  */
	private void FileLoad(){
		FileRewrite fr = new FileRewrite();
		if(fr.checkFile(playerFile) == false){
			System.out.println("プレイヤーデータがありません。");
			System.out.println("アカウントを作成します。");
			fr.setFile(NewPlayer(name),"player_" + name + ".txt");
			new Star(40);	//視覚効果。星が動く。
			System.out.println("[新規アカウント作成完了！]");	//関係性は無い、雰囲気メッセージ。
		}
		//ファイルから読み込んでフィールドにセット。
		ArrayList<String> list = fr.getFile("player_" + name + ".txt");
		lv = Integer.parseInt(fr.getStringMark(list,"レベル："));
		ex = Integer.parseInt(fr.getStringMark(list,"経験値："));
		title = fr.getStringMark(list,"称号：");
		health = Integer.parseInt(fr.getStringMark(list,"ゲームヘルス："));
		coin = Integer.parseInt(fr.getStringMark(list,"ぼっちコイン："));
		words = new String[3];
		words[0] = fr.getStringMark(list,"戦闘前台詞：");
		words[1] = fr.getStringMark(list,"勝利台詞：");
		words[2] = fr.getStringMark(list,"敗北台詞：");
	}
	/**
	  *	新規プレイヤーファイルのテンプレ
	  */
	private ArrayList<String> NewPlayer(String name){
		ArrayList<String> list = new ArrayList<String>();
		list.add("プレイヤー名：" + name);
		list.add("レベル：1");
		list.add("経験値：0");
		list.add("称号：『初心者』");
		list.add("ゲームヘルス：50");
		list.add("次回ヘルス回復時間：");
		list.add("ぼっちコイン：3");
		list.add("");
		list.add("戦闘前台詞：");
		list.add("勝利台詞：");
		list.add("敗北台詞：");
		list.add("");
		list.add("■アルゴっぽいの");
		list.add("プレイ回数：0");
		list.add("ハイスコア：0");
		list.add("");
		list.add("■ゲームA");
		list.add("プレイ回数：0");
		list.add("ハイスコア：0");
		return list;
	}
	/**
	  *	プレイヤーのフィールドを一覧表示する。
	  */
	public void dispField(){
		System.out.println();
		System.out.println("プレイヤー名：" + name);
		System.out.println("レベル：" + lv);
		System.out.println("経験値：" + ex);
		System.out.println("称号：" + title);
		System.out.println("ゲームヘルス：" + health);
		System.out.println("次回ヘルス回復時間：" + recovery);
		System.out.println("ぼっちコイン：" + coin);
		System.out.println();
	}
	/**
	  *	プレイヤーデータをファイルに書き込む
	  */
	public void savePlayer(){
		FileRewrite fr = new FileRewrite("player_" + name + ".txt","レベル：",String.valueOf(lv));
		fr = new FileRewrite("player_" + name + ".txt","経験値：",String.valueOf(ex));
		fr = new FileRewrite("player_" + name + ".txt","称号：",title);
		fr = new FileRewrite("player_" + name + ".txt","ゲームヘルス：",String.valueOf(health));
		fr = new FileRewrite("player_" + name + ".txt","次回ヘルス回復時間：",recovery);
		fr = new FileRewrite("player_" + name + ".txt","ぼっちコイン：",String.valueOf(coin));
		fr = new FileRewrite("player_" + name + ".txt","戦闘前台詞：",words[0]);
		fr = new FileRewrite("player_" + name + ".txt","勝利台詞：",words[1]);
		fr = new FileRewrite("player_" + name + ".txt","敗北台詞：",words[2]);
	}
	/**
	  *	台詞変更
	  */
	public void modWords(){
		while(true){
			System.out.println("\n現在の設定\n");
			System.out.println("戦闘前台詞：" + words[0]);
			System.out.println("勝利台詞：" + words[1]);
			System.out.println("敗北台詞：" + words[2]);
			System.out.println();
			if(words[0].length() == 0 && words[1].length() == 0 && words[2].length() == 0){
				System.out.println("台詞は設定されていません。設定しますか？");
			}else{
				System.out.println("変更しますか？");
			}
			System.out.println("設定する台詞を番号で選択して下さい。");
			System.out.println("\n1 ）戦闘前\n2 ）勝利時\n3 ）敗北時\n4 ）設定を終了する\n");
			Integer num = GameSystem.inputIntegerNull(1,4);
			if(num == null || num == 4){
				System.out.println("設定を終了します。");
				break;
			}else{
				System.out.println("台詞を入力してください。");
				words[num - 1] = GameSystem.inputString(1,40);
			}
		}
	}
	/**
	  *	現在時間が次回ヘルス回復時間を超えていた場合、
	  *	ヘルスを最大値である50にする。
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
	  *	getNameメソッド
	  */
	public String getName(){
		return name;
	}
	/**
	  *	getTitleメソッド
	  */
	public String getTitle(){
		return title;
	}
	/**
	  *	setTitleメソッド
	  */
	public void setTitle(String title){
		this.title = title;
	}
	/**
	  *	getHealthメソッド
	  */
	public int getHealth(){
		return health;
	}
	/**
	  *	setHealthメソッド
	  */
	public void setHealth(int health){
		this.health = health;
	}
	/**
	  *	getRecoveryNowメソッド
	  */
	public String getRecoveryNow(int recoveryHour){
/*		Calendar cal = Calendar.getInstance();//現在日時を取得
		cal.add(Calendar.HOUR_OF_DAY,recoveryHour);//現在時間から回復までの時間を足す
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy.MM.dd.HH");//表示形式
		Date date = sdformat.parse(cal);
		String str = format(cal.setTime(sdformat));
*/		return "";
	}
	/**
	  *	getRecoveryメソッド
	  */
	public String getRecovery(){
		return recovery;
	}
	/**
	  *	setRecoveryメソッド
	  */
	public void setRecovery(String recovery){
		this.recovery = recovery;
	}
	/**
	  *	getLvメソッド
	  */
	public int getLv(){
		return lv;
	}
	/**
	  *	setLvメソッド
	  */
	public void setLv(int lv){
		this.lv = lv;
	}
	/**
	  *	getExメソッド
	  */
	public int getEx(){
		return ex;
	}
	/**
	  *	setExメソッド
	  */
	public void setEx(int ex){
		this.ex = ex;
	}
	/**
	  *	getCoinメソッド
	  */
	public int getCoin(){
		return coin;
	}
	/**
	  *	setCoinメソッド
	  */
	public void setCoin(int coin){
		this.coin = coin;
	}
	/**
	  *	getWordsメソッド
	  */
	public String getWords(int index){
		return words[index];
	}
	/**
	  *	setWordsメソッド
	  */
	public void setWords(int index, String words){
		this.words[index] = words;
	}
	/**
	  *	dispWordsメソッド
	  */
	public void dispWords(int index){
		if(1 <= words[index].length()){
			System.out.println(name + "「" + words[index] + "」");
		}
	}
}
