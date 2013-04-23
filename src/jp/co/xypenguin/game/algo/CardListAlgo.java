package jp.co.xypenguin.game.algo;

/**********************************************************
 *	�N���u�ƃn�[�g�����ꂼ��13���A�v26����ێ�����N���X
 *	�A���S���Q�[���Ŏg��
 *
 *********************************************************/
class CardListAlgo{
	private Card[] card;	//�J�[�h�N���X��ێ�����z��
	/******************************************************
	 *	�R���X�g���N�^
	 *	�J�[�h�N���X�̃C���X�^���X��13*2���쐬����
	 *	�z��ɃJ�[�h�ɓ����B
	 *****************************************************/
	CardListAlgo(){
		card = new Card[Const.CARD_KIND_COUNT * 2];	//����͔z��̊m��
		int index = 0;
		int cardNumber = 0;
		for(int i = 0; i < card.length; i++){
			index = 1 + (int)(i / Const.CARD_KIND_COUNT);
			cardNumber = (i % Const.CARD_KIND_COUNT) + 1;
			String color;
			if(Const.CARD_KIND_NAME[index].equals("�n�[�g") == true){
				color = "��";
			}else{
				color = "��";
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

