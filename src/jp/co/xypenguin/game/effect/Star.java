package jp.co.xypenguin.game.effect;

public class Star{
	public Star(int time){
		while(0 <= time){
			int a = (int)(Math.random() * 39) + 1;
			for(int i = 1; i < a; i++){
				System.out.print("@");
			}
			int b = (int)(Math.random() * 100) + 1;
			if(b > 50){
				System.out.print("™");
			}else{
				System.out.print("š");
			}
			for(int i = 0; i < 39 - a; i++){
				System.out.print("@");
			}
			time += -1;
			try{
				Thread.sleep(30);
				for(int j = 0; j < 80; j++){
					System.out.print("\b");
				}
			}catch(InterruptedException e){
			}
		}
	}
}
