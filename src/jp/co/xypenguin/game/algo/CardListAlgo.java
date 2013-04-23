package jp.co.xypenguin.game.algo;

/**********************************************************
 *	クラブとハートをそれぞれ13枚、計26枚を保持するクラス
 *	アルゴ風ゲームで使う
 *
 *********************************************************/
class CardListAlgo{
	private Card[] card;	//カードクラスを保持する配列
	/******************************************************
	 *	コンストラクタ
	 *	カードクラスのインスタンスを13*2枚作成して
	 *	配列にカードに入れる。
	 *****************************************************/
	CardListAlgo(){
		card = new Card[Const.CARD_KIND_COUNT * 2];	//これは配列の確保
		int index = 0;
		int cardNumber = 0;
		for(int i = 0; i < card.length; i++){
			index = 1 + (int)(i / Const.CARD_KIND_COUNT);
			cardNumber = (i % Const.CARD_KIND_COUNT) + 1;
			String color;
			if(Const.CARD_KIND_NAME[index].equals("ハート") == true){
				color = "赤";
			}else{
				color = "黒";
			}
			card[i] = new Card(color,cardNumber);
		}
	}
	
	public Card getCardOne(int index){
		return card[index];
	}
	
	public void setCardOne(int index,Card c){
		card[index] = c;
	}
	
	public int getCardCount(){
		return card.length;
	}
}

