package jp.co.xypenguin.game;

import jp.co.xypenguin.game.algo.*;
import jp.co.xypenguin.game.pk.*;

public class Recommendation{
	private Integer selection;
	
	Recommendation(){
		inform();	//情報を表示
		System.out.println("おすすめゲームをプレイしますか？");
		Boolean b = GameSystem.ynNull();
		if(b == null || b == true){
			selection = 1;
		}
	}
	private void inform(){
		System.out.println("今日のオススメゲームは『ぼっち向けアルゴっぽいの』です。");
		System.out.println();
		System.out.println("ただいま経験値2倍キャンペーン実施中！");
		System.out.println("限定コスチュームもゲット出来るかも！？");
		System.out.println();
	}
	public Integer getSelection(){
		return selection;
	}
}
