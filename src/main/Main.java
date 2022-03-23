package main;

import facebook.fbJSONParser;
import instagram.isJSONParser;
import naverblog.naverBlogModuler;
import twitter.twitterModuler;
import youtube.yoJSONParser;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
			
			switch (args[0]) {
			case "f":
				System.out.println("페이스북 데이터");
				fbJSONParser fb = new fbJSONParser();
				break;
			case "i":
				System.out.println("인스타그램 데이터");
				isJSONParser is = new isJSONParser();
				break;
			case "t":
				System.out.println("트위터 데이터");
				twitterModuler tw = new twitterModuler();
				break;
			case "n":
				System.out.println("네이버블로그 데이터");
				naverBlogModuler na = new naverBlogModuler();
				break;
			case "y":
				System.out.println("유튜브 데이터");
				yoJSONParser yo = new yoJSONParser();
				break;
			case "a":
				System.out.println("모든 데이터");
				fbJSONParser fb1 = new fbJSONParser();
				isJSONParser is1 = new isJSONParser();
				twitterModuler tw1 = new twitterModuler();
				naverBlogModuler na1 = new naverBlogModuler();
				yoJSONParser yo1 = new yoJSONParser();
				break;
			case "e":
				System.exit(0);
			default:	
			}		
	}
}
