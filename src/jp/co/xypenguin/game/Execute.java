package jp.co.xypenguin.game;

import jp.co.xypenguin.game.algo.*;
import jp.co.xypenguin.game.pk.*;

class Execute{
	public static void main(String[] args){
		System.out.println("ようこそ、ぼっちゲームタウンへ！");
		Player p = new Player();
		p.recoveryHealth();
		
		PlayGame[] playGame = {
			new Algo(p),
		};
		
		while(true){
			if(1 <= p.getHealth()){
				System.out.println();
				System.out.println("プレイするゲームを選択してください。");
				System.out.println("1 ）ぼっち向けアルゴっぽいの\n101 ）各種設定\n");
				
				Integer selection = GameSystem.inputIntegerNull(1,101);
				if(selection == null){	//何も選択しなかった場合
					Recommendation r = new Recommendation();	//おすすめを呼び出すクラス
					selection = r.getSelection();
					if(selection != null){
						playGame[selection - 1].play();
						playGame[selection - 1].savetext();
					}
				}else if(2 <= selection && selection <= 100){
					System.out.println("他のゲームはまだありません。鋭意作成中。");
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
				System.out.println("ヘルスが足りません。コインを使いますか？");
				if(GameSystem.yn() == true){
					p.setCoin(p.getCoin() - 1);
					p.setHealth(30);
				}else{
					break;
				}
			}
		}
		System.out.println("お疲れ様でした。");
		System.out.println("また明日遊んでくださいね。");
	}
}
