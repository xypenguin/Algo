package jp.co.xypenguin.game.pk;

import jp.co.xypenguin.game.pk.GameSystem;

/******************************************************
 *	Timeクラス
 *	マルチスレッドで時間経過を表示
 *****************************************************/
public class Time implements Runnable{
	private long startTime;
	private long endTime;
	private boolean end;
	private Thread th;
	
	//コンストラクタ
	public Time(){
		end = false;
	}
	
	//自身のインスタンスでThreadクラスのインスタンスを作成してstartさせる。
	public void start(){
		th = new Thread(this);
		th.start();
	}
	
	//一秒ごとに経過時間が表示される。
	public void run(){
		startTime = System.currentTimeMillis() / 1000;
		
		//コマンドプロンプトで時間を表示させるには
		//ProcessBuilderで新たにプロンプトを開いて表示させる方法などがあるようですが、あまり使い勝手がよくないと思ったので実装しません。
		while(end != true){
		//	System.out.print("経過時間" + (System.currentTimeMillis() / 1000 - startTime));
			GameSystem.wait(100);
		}
		endTime = System.currentTimeMillis() / 1000;
	}
	
	//上記のループを抜けるスイッチ
	public void stop(){
		end = true;
	}
	
	//startからstopを押すまでの時間
	public int playtime(){
		return (int)(endTime - startTime);
	}
	
	//このメソッドを呼び出したスレッドはrunが終わるまで処理を進めない。
	public void threadJoin(){
		try{
			th.join();
		}catch(InterruptedException e){
			System.out.println(e);
		}
	}
}
