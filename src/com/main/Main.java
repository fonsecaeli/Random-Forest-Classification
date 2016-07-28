package com.main;

public class Main {

	public static void main(String[] args) {
		Attribute att = new Attribute("Name");
		att.add("eben");
		att.add("eli");
		Attribute att2 = new Attribute("Glasses");
		att2.add("no");
		att2.add("yes");
		Attribute att3 = new Attribute("Test");
		att3.add("testchoice1");
		att3.add("testchoice2");
		Node node = new Node();
		Node node2 = new Node();
		Node node3 = new Node();
		node.setAttribute(att);
		node.getChild("eben").setAttribute(att2);
		node.getChild("eli").setAttribute(att3);
		node.getChild("eben").getChild("no").setDecision("1");
		node.getChild("eben").getChild("yes").setDecision("2");
		node.getChild("eli").getChild("testchoice1").setDecision("1");
		node.getChild("eli").getChild("testchoice2").setDecision("2");
		System.out.println(node);
	}
}
