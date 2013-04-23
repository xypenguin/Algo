package jp.co.xypenguin.game;

import java.io.*;

import jp.co.xypenguin.game.pk.*;

public abstract class CardGame implements PlayGame{
	private File playerFile;
	private String gameName;
	private int playCount;
	private int hiScore;
	private int timeSum;
	
	//コンストラクタ
	public CardGame(){
	
	}
	/******************************************************
	 *	setメソッド
	 *
	 *****************************************************/
	public void setFile(File playerFile){
		this.playerFile = playerFile;
	}
	
	/******************************************************
	 *	getメソッド
	 *
	 *****************************************************/
	public File getFile(){
		return playerFile;
	}
	
	/******************************************************
	 *	setメソッド
	 *
	 *****************************************************/
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
	
	/******************************************************
	 *	getメソッド
	 *
	 *****************************************************/
	public String getGameName(){
		return gameName;
	}
	
	/******************************************************
	 *	setメソッド
	 *
	 *****************************************************/
	public void setPlayCount(int playCount){
		this.playCount = playCount;
	}
	
	/******************************************************
	 *	getメソッド
	 *
	 *****************************************************/
	public int getPlayCount(){
		return playCount;
	}
	
	/******************************************************
	 *	setメソッド
	 *
	 *****************************************************/
	public void setHiScore(int hiScore){
		this.hiScore = hiScore;
	}
	
	/******************************************************
	 *	getメソッド
	 *
	 *****************************************************/
	public int getHiScore(){
		return hiScore;
	}
	/******************************************************
	 *	setメソッド
	 *
	 *****************************************************/
	public void setTimeSum(int timeSum){
		this.timeSum = timeSum;
	}
	
	/******************************************************
	 *	getメソッド
	 *
	 *****************************************************/
	public int getTimeSum(){
		return timeSum;
	}
}
