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
				System.out.println("���̽��� ������");
				fbJSONParser fb = new fbJSONParser();
				break;
			case "i":
				System.out.println("�ν�Ÿ�׷� ������");
				isJSONParser is = new isJSONParser();
				break;
			case "t":
				System.out.println("Ʈ���� ������");
				twitterModuler tw = new twitterModuler();
				break;
			case "n":
				System.out.println("���̹���α� ������");
				naverBlogModuler na = new naverBlogModuler();
				break;
			case "y":
				System.out.println("��Ʃ�� ������");
				yoJSONParser yo = new yoJSONParser();
				break;
			case "a":
				System.out.println("��� ������");
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
