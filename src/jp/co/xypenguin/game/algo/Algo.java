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
		setGameName("アルゴっぽいの");
		
	}
	@Override
	public void play(){
		p.setHealth(p.getHealth() - 1);	//ゲーム開始時にプレイヤーのヘルスが1減る
		p.setRecovery(p.getRecoveryNow(24));//現在日時+24時間の時刻を取得してフィールドにセット
		
		FileRewrite fr = new FileRewrite();
		list = fr.getFile("player_" + p.getName() + ".txt");
		setPlayCount(Integer.parseInt(fr.getStringMark(list,"■" + getGameName(),"プレイ回数：")));
		setHiScore(Integer.parseInt(fr.getStringMark(list,"■" + getGameName(),"ハイスコア：")));
		
		GameSystem.captionSpeed("ゲーム名『ぼっち向けアルゴっぽいの』");
		System.out.println();
		p.dispWords(0);
		
		//時間を計るためマルチスレッドで実行します。
		Time time = new Time();
		time.start();
		
		talon = new TalonAlgo();	//山札を作ります。
		player = new Hand(talon, true);	//プレイヤーの手札を山札から作ります。
		com = new Hand(talon, false);	//相手の手札を山札から作ります。
		
		disp();	//表示用
		GameSystem.captionSpeed("最初に相手の手札を一枚、表示させることが出来ます。");
		GameSystem.captionSpeed("左から何番目を表示させますか？");
		
		Integer index = GameSystem.inputIntegerNull(1,com.getHand().size());
		if(index == null){	//何も入力しなかった場合。
			GameSystem.captionSpeed("こちらで適当に表示させておきます。");
			com.getHand().get(3).setBothSides(true);	//左から4番目のカードをtrue（表）にする
		}else{
			com.getHand().get(index - 1).setBothSides(true);
		}
		
		//ゲーム終了条件
		while(allTrue(com) == false && 0 < talon.getCardCount()){
			attack();
		}
		GameSystem.captionSpeed("ゲーム終了！");
		time.stop();
		//stopメソッド後にplaytimeメソッドの実行が早すぎると表示がおかしくなります。
		//なのでjoinで待機させます。
		//java5以降では複数のスレッドの同期の管理にCountDownLatchクラスを利用することが推奨されているようですが、
		//joinはjoinで問題ないように思いました。というのは建前でありCountDownLatchクラスを読んでも理解出来ませんでした。
		//マルチスレッドは難しいです。
		time.threadJoin();
		GameSystem.captionSpeed("お疲れ様でした。");
		playTime = time.playtime();
		score = calcScore();
		GameSystem.captionSpeed("今回のゲームの点数は " + score + " 点です。");
		p.setEx(p.getEx() + (int)(score / 10));
		GameSystem.captionSpeed("プレイ時間：" + playTime + "秒です。");
		setPlayCount(getPlayCount() + 1);
		if(getHiScore() < score){
			System.out.println("ハイスコア更新！！");
			setHiScore(score);
		}
	}
	/******************************************************
	 *	山札（talon）からカードを一枚ずつ引いて表向きにセットし
	 *	playerとcomの双方に渡す。
	 *	
	 *****************************************************/
	private void drawCard(){
		GameSystem.captionSpeed("山札から一枚ずつカードを引きます。");
		
		Card card = talon.getCardOne();
		card.setBothSides(true);
		player.setHand(card);
		player.sort();
		GameSystem.captionSpeed("自分の手札の左から " + (player.getHand().indexOf(card) + 1) + " 番目に入れました。");
		
		card = talon.getCardOne();
		card.setBothSides(true);
		com.setHand(card);
		com.sort();
		GameSystem.captionSpeed("相手の手札の左から " + (com.getHand().indexOf(card) + 1) + " 番目に入れました。");
	}
	/******************************************************
	 *	相手の手札のカードを当てる。
	 *	
	 *****************************************************/
	private void attack(){		//ソースコードが見辛いので良くない。最適化する必要があると思います。
		outer:
		for(;;){
			disp();
			GameSystem.captionSpeed("左から何番目のカードを当てますか？");
			Integer index = selectCardNum();
			if(index == null){
				if(talon.getCardCount() <= 2){
					GameSystem.captionSpeed("これ以上カードを引くことが出来ません。ギブアップしますか？");
					Boolean b = GameSystem.ynNull();
					if(b == null || b == true){
						talon.gettalon().clear();
					}else{
						continue outer;
					}
				}else{
					GameSystem.captionSpeed("カードを当てずに新しくカードを引きますか？");
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
							GameSystem.captionSpeed("自分の手札からカードを一枚選択して相手に渡してください。");
							GameSystem.captionSpeed("左から何番目のカードを渡しますか？");
							Integer i = GameSystem.inputIntegerNull(1,player.getHand().size());
							if(i == null){
								GameSystem.captionSpeed("こちらで最適なカードを選んでおきます。");
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
				GameSystem.captionSpeed(index + "番目のカードの数字は？");
				int num = GameSystem.inputInt();
				if(com.getHand().get(index - 1).getNumber() == num){
					GameSystem.captionSpeed("正解です。");
					com.getHand().get(index - 1).setBothSides(true);
					miss = 0;
				}else{
					GameSystem.captionSpeed("不正解です。");
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
							GameSystem.captionSpeed("自分の手札からカードを一枚選択して相手に渡してください。");
							GameSystem.captionSpeed("左から何番目のカードを渡しますか？");
							Integer i = GameSystem.inputIntegerNull(1,player.getHand().size());
							if(i == null){
								GameSystem.captionSpeed("こちらで最適なカードを選んでおきます。");
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
	 *	判別している番号のカードは選べない
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
				GameSystem.captionSpeed("そのカードの数字はすでに判明しています。");
				continue outer;
				}
			}
			return index;
		}
	}
	/******************************************************
	 *	手札のカードが全て裏表が全て表（true）だったらtrue
	 *	それ以外だったらfalseを返すメソッド
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
	 *	playerとcomの手札表示画面用
	 *	
	 *****************************************************/
	private void disp(){
		System.out.println();
		GameSystem.captionSpeed("相手の手札");
		com.dispHand();
		System.out.println();
		GameSystem.captionSpeed("自分の手札");
		player.dispHand();
		GameSystem.captionSpeed("残り手札" + talon.getCardCount() + "枚");
		System.out.println();
	}
	/******************************************************
	 *	手札を一枚をランダムで表にする
	 *	
	 *****************************************************/
	private void randomTrue(){
		System.out.println();
		GameSystem.captionSpeed("なかなか当てられないようですね。");
		GameSystem.captionSpeed("相手の手札をランダムで一枚表にしておきますね。");
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
	  *	スコアを計算して返す。
	  *	
	  */
	private int calcScore(){
		int score;
		score = (int)((talon.getCardCount() * 1000) / (playTime * 0.1) + ((talon.getCardCount() + 1) * 1000) / (40 * 0.1)) - 250;
		return score;
	}
	/**
	  *	テキストに書き込む
	  *	
	  */
	@Override
	public void savetext(){
		FileRewrite fr = new FileRewrite();
		
		list = fr.setStringMark(list,"■" + getGameName(),"プレイ回数：",String.valueOf(getPlayCount()));
		list = fr.setStringMark(list,"■" + getGameName(),"ハイスコア：",String.valueOf(getHiScore()));
		fr.setFile(list,"player_" + p.getName() + ".txt");
	}
}
