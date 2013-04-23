package jp.co.xypenguin.game.pk;

import java.io.*;
import java.util.*;

public class FileRewrite{
	private ArrayList<String> list;
	
	public FileRewrite(){
	}
	/******************************************************
	 *	txtファイルの指定行を書き換える
	 *	ファイル名、指定行、文字列
	 *****************************************************/
	public FileRewrite(String filename,int num,String str){
		list = getFile(filename);
		int a = list.size();
		for(int i = 0; i < num - a; i++){
			list.add("");
		}
		list.set(num - 1,str);
		setFile(list,filename);
	}
	
	/******************************************************
	 *	txtファイルの指定文字列の後の文字列を書き換える。
	 *	ファイル名、指定文字列、文字列
	 *****************************************************/
	public FileRewrite(String filename, String mark, String newline){
		list = getFile(filename);
		int index = 0;
		while(list.get(index).indexOf(mark) == -1){
			index += 1;
			if(index == list.size()){
				System.out.println("「" + mark + "」は見つかりません。引数を見直してください。");
			}
		}
		String str = list.get(index);
		list.set(index,str.substring(0,mark.length()) + newline);
		setFile(list,filename);
	}
	
	/******************************************************
	 *	ArrayList<String>内の指定された要素以降の指定文字列の後の文字列を取得する。
	 *	list、指定要素、指定文字列
	 *****************************************************/
	public String getStringMark(ArrayList<String> list,String search, String mark){
		int index = list.indexOf(search);
		//チェック例外
		if(index == -1){
			System.out.println("「" + search + "」は見つかりません。引数を見直してください。");
		}
		while(list.get(index).indexOf(mark) == -1){
			index += 1;
			if(index == list.size()){
				System.out.println("「" + mark + "」は見つかりません。引数を見直してください。");
			}
		}
		String str = list.get(index);
		String line = str.substring(mark.length());
		return line;
	}
	/******************************************************
	 *	ArrayList<String>内の指定された要素以降の指定文字列の後の文字列を書き換える。
	 *	list、指定要素、指定文字列、書き換える文字列
	 *****************************************************/
	public ArrayList<String> setStringMark(ArrayList<String> list, String search, String mark, String newline){
		int index = list.indexOf(search);
		//チェック例外
		if(index == -1){
			System.out.println("「" + search + "」は見つかりません。引数を見直してください。");
		}
		while(list.get(index).indexOf(mark) == -1){
			index += 1;
			if(index == list.size()){
				System.out.println("「" + mark + "」は見つかりません。引数を見直してください。");
			}
		}
		String str = list.get(index);
		list.set(index,str.substring(0,mark.length()) + newline);
		return list;
	}
	/******************************************************
	 *	ArrayList<String>内の指定文字列の後の文字列を取得する。
	 *	list、指定文字列
	 *****************************************************/
	public String getStringMark(ArrayList<String> list, String mark){
		int index = 0;
		while(list.get(index).indexOf(mark) == -1){
			index += 1;
			if(index == list.size()){
				System.out.println("「" + mark + "」は見つかりません。引数を見直してください。");
			}
		}
		String str = list.get(index);
		String line = str.substring(mark.length());
		return line;
	}
	
	/******************************************************
	 *	txtファイルの文字列を全取得して
	 *	ArrayList<String>で返す。
	 *****************************************************/
	public ArrayList<String> getFile(String filename){
		list = new ArrayList<String>();
		try{
			File file = new File(filename);
			if(checkFile(file) == true){
				BufferedReader reader = new BufferedReader(new FileReader(filename));
				String line;
				while((line = reader.readLine()) != null){
					list.add(line);
				}
				reader.close();
			}else{
				System.out.println(filename + "が見つかりませんでした。");
			}
		}catch(FileNotFoundException e){
			System.out.println(filename + "が見つかりません。");
		}catch(IOException e){
			System.out.println(e);
		}
		return list;
	}
	/******************************************************
	 *	ArrayList<String>の全要素をtxtファイルに出力
	 *	
	 *****************************************************/
	public void setFile(ArrayList<String> list,String filename){
		try{
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(filename)));
			for(String line : list){
				writer.println(line);
			}
			writer.close();
		}catch(FileNotFoundException e){
			System.out.println(filename + "が見つかりません。");
		}catch(IOException e){
			System.out.println(e);
		}
	}
	/******************************************************
	 *	プライベートメソッド
	 *	ファイルがあるかどうか、
	 *	ファイルなのか、読み込み可能なのかをチェックする。
	 *****************************************************/
	public boolean checkFile(File file){
		if(file.exists()){			//引数のファイルが存在するのか
			//そのファイルは普通のファイルなのか（フォルダとかではなく）
			//そして、そのファイルは読み込み可能ファイルなのかチェック
			if(file.isFile() && file.canRead()){
				return true;	//全部可能で問題ないファイルなら真を返す
			}
		}
		return false;	//それ以外ならファイルに問題があるので偽を返す
	}
}
