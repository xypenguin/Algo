package jp.co.xypenguin.game;

import java.io.*;

import jp.co.xypenguin.game.pk.*;

public abstract class CardGame implements PlayGame{
	private File playerFile;
	private String gameName;
	private int playCount;
	private int hiScore;
	private int timeSum;
	
	//�R���X�g���N�^
	public CardGame(){
	
	}
	/******************************************************
	 *	set���\�b�h
	 *
	 *****************************************************/
	public void setFile(File playerFile){
		this.playerFile = playerFile;
	}
	
	/******************************************************
	 *	get���\�b�h
	 *
	 *****************************************************/
	public File getFile(){
		return playerFile;
	}
	
	/******************************************************
	 *	set���\�b�h
	 *
	 *****************************************************/
	public void setGameName(String gameName){
		this.gameName = gameName;
	}
	
	/******************************************************
	 *	get���\�b�h
	 *
	 *****************************************************/
	public String getGameName(){
		return gameName;
	}
	
	/******************************************************
	 *	set���\�b�h
	 *
	 *****************************************************/
	public void setPlayCount(int playCount){
		this.playCount = playCount;
	}
	
	/******************************************************
	 *	get���\�b�h
	 *
	 *****************************************************/
	public int getPlayCount(){
		return playCount;
	}
	
	/******************************************************
	 *	set���\�b�h
	 *
	 *****************************************************/
	public void setHiScore(int hiScore){
		this.hiScore = hiScore;
	}
	
	/******************************************************
	 *	get���\�b�h
	 *
	 *****************************************************/
	public int getHiScore(){
		return hiScore;
	}
	/******************************************************
	 *	set���\�b�h
	 *
	 *****************************************************/
	public void setTimeSum(int timeSum){
		this.timeSum = timeSum;
	}
	
	/******************************************************
	 *	get���\�b�h
	 *
	 *****************************************************/
	public int getTimeSum(){
		return timeSum;
	}
}
