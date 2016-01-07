

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Image;

import javax.swing.*;

import java.util.*;


import java.util.*;
import java.applet.*;

public class Main extends Applet implements ActionListener{

    //
    // User Interface
    //

    // コンソールから起動した場合
	static Main main;
	public static void main(String args[]) {
        final JFrame frame = new JFrame("MergeSort");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Main main = new Main();
        main.init();
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
	}

    private final JButton button1 = new JButton("Random");
    private final JButton button2 = new JButton("Clear");
    private final JButton button3 = new JButton("Insert");
    private final JButton button4 = new JButton("Delete");

    private final JTextField textField = new JTextField("1 3 2", 20);
    private final JTextArea textArea = new JTextArea();
    private final ConsolePanel panel = new ConsolePanel();
    private final JScrollPane scrollpane = new JScrollPane(panel);

 
    BinarySearchTree binarysearchtree = new BinarySearchTree();
    
    // アプレットとして起動した場合
    public void init(){
    	main = this;
    	setLayout(new BorderLayout());
    	
    	button1.addActionListener(this);
    	button2.addActionListener(this);
    	button3.addActionListener(this);
    	button4.addActionListener(this);
 
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(5));
        
        
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel1.add(textField);
        panel1.add(button1);
        panel1.add(button2);
        box.add(panel1);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel2.add(button3);
        panel2.add(button4);
        box.add(panel2);
        
        box.add(Box.createVerticalStrut(5));
        box.add(scrollpane);
        scrollpane.setPreferredSize(new Dimension(600, 500));
        box.add(Box.createVerticalStrut(5));
        add(box, BorderLayout.NORTH);

        setPreferredSize(new Dimension(600, 600));

    }
    public void actionPerformed(ActionEvent e) {
		String label = ((javax.swing.JButton) e.getSource()).getLabel();
		if (label.equals("Random")) {
			int[] array = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			for (int i = 0; i < 10; i++) {
				int random = (int) (Math.random() * 10);
				int tmp = array[i];
				array[i] = array[random];
				array[random] = tmp;
			}
			textField.setText(Main.int_array_to_string(array));
		}
		if (label.equals("Clear")) {
			binarysearchtree = new BinarySearchTree();
			panel.images = new ArrayList();
			panel.repaint();
		}
		if (label.equals("Insert")) {
			int[] array = Main.string_to_int_array(textField.getText());
			for (int i = 0; i < array.length; i++) {
				binarysearchtree.insert(array[i]);

				print("insert " + array[i]);
				print(binarysearchtree);
			}
		}
		if (label.equals("Delete")) {
			int[] array = Main.string_to_int_array(textField.getText());
			for (int i = 0; i < array.length; i++) {
				binarysearchtree.delete(array[i]);

				print("delete " + array[i]);
				print(binarysearchtree);
			}
		}
	}    
    
    

    class ConsolePanel extends JPanel {
    	ArrayList images = new ArrayList();
    	public ConsolePanel(){
    	}
    	public void paint(Graphics g){
    		g.clearRect(0,0, getWidth(), getHeight());
    		int y = 0;
    		for(int i=0; i<images.size(); i++){
    			Image image = (Image) images.get(i);
    			g.drawImage(image, 0, y, null);
    			y += image.getHeight(null);
    		}
            setPreferredSize(new Dimension(500,y));
            main.scrollpane.validate();
            main.remove(main.scrollpane);
            main.add(main.scrollpane);
    	}
    }
    static void print(BinarySearchTree tree){
    	main.panel.images.add(getTreeImage(tree));
    	main.panel.repaint();
    }
    static Image getTreeImage(BinarySearchTree tree){
    	HashMap xpos = new HashMap();
    	int max_depth = compute_depth(tree.root);
    	int max_width = compute_width(tree.root, 10, xpos);
		Image image = main.createImage(10+max_width, 10+20*max_depth);
		Graphics g = image.getGraphics();
		g.setColor(java.awt.Color.black);
		drawTree(g, tree.root, 0, xpos);
		g.dispose();
    	return image;
    }
    static void drawTree(Graphics g, Node node, int y, HashMap xpos){
    	if (node == null)
    		return;
    	int x0 = ((Integer)xpos.get(node)).intValue();

    	drawTree(g, node.left, y+1, xpos);
    	g.drawString(""+node.object, x0-5, y*20+15);
    	drawTree(g, node.right,y+1, xpos);

    	if (node.left != null){
    		int x1 = ((Integer)xpos.get(node.left)).intValue();
    		g.drawLine(x0-2, y*20+17, x1+2, y*20+20+4);
    	}
    	if (node.right != null){
    		int x1 = ((Integer)xpos.get(node.right)).intValue();
    		g.drawLine(x0+2, y*20+17, x1-2, y*20+20+4);
    	}
    }
    static int compute_depth(Node node){
    	if (node == null)
    		return 0;
    	if (node == node.left || node == node.right){
    		System.out.println("error "+ node.object);
    		return 0;
    	}
    	return 1+Math.max(compute_depth(node.left), compute_depth(node.right));
    }
    // 降りていくのは左端の座標
    // あがっていくのはサブツリーの幅
    // セットしていくのはノードのｘ座標　xpos
    static int compute_width(Node node, int parent_x, HashMap xpos){
    	if (node == null)
    		return 0;
    	int w0 = compute_width(node.left, parent_x, xpos);
    	xpos.put(node, new Integer(parent_x+w0));
    	int w1 = compute_width(node.right, parent_x + w0 + 10, xpos);
    	return w0 + 10 + w1;
    }
    public static void print(String line){
    	Image image = Main.main.createImage(500, 12);
    	Graphics g = image.getGraphics();
    	g.setColor(java.awt.Color.black);
    	g.drawString(line, 10, 10);
    	g.dispose();
    	main.panel.images.add(image);
    	main.panel.repaint();
    }	     
    
    
    
    
    
    
    
    
    //
    // Utilities
    //
//    
//    static void print(BinarySearchTree binarysearchtree){
//    	String line = "";
//    	println(line);
//    }
// 
//
//    public static JTextArea console;
//    public static void println(String line){
//    	//console.append(line+"\n");
//    	Image image = Main.main.createImage(500, 10);
//    	Graphics g = image.getGraphics();
//    	g.setColor(java.awt.Color.black);
//    	g.drawString(line, 10, 10);
//    	g.dispose();
//    	main.panel.images.add(image);
//    	main.panel.repaint();
//    }	
//    public static void clear(){
//    	console.setText("");
//    }	
    
    static public int[] string_to_int_array(String line){
    	ArrayList list = new ArrayList();
    	StringTokenizer st = new java.util.StringTokenizer(line);
    	while(st.hasMoreTokens()){
    		list.add(new Integer(st.nextToken()));
    	}
    	int[] array = new int[list.size()];
    	for(int i=0; i<list.size(); i++){
    		Integer integer = (Integer) list.get(i);
    		array[i] = integer.intValue();
    	}
    	return array;
    }    
    static public String int_array_to_string(int[] array){
    	String line = "";
    	for(int i=0; i<array.length; i++){
    		line += ""+array[i]+" ";
    	}
    	return line;
    }
 }
