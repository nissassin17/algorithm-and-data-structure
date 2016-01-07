

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

public class Main extends Applet{

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

	//private final JLabel label = new JLabel("Type command and press Run. e.g. 'merge 0 1', 'find 2'");
    private final JButton button1 = new JButton("Run");
    private final JButton button2 = new JButton("Init");

    private final JTextField textField = new JTextField("merge 0 1", 20);
    private final JTextArea textArea = new JTextArea();
    private final ConsolePanel panel = new ConsolePanel();
    private final JScrollPane scrollpane = new JScrollPane(panel);

 
    MergeFindSet mergefindset = null;
    
    // アプレットとして起動した場合
    public void init(){
    	main = this;
    	setLayout(new BorderLayout());
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	run();
            }
        });   	
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	init_MergeFindSet();
            	print("initialize");
            	print(mergefindset);
            }
        });   	

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(5));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //panel.add(label);
        panel.add(textField);
        panel.add(button1);
        panel.add(button2);
        box.add(panel);
        
        box.add(Box.createVerticalStrut(5));
        box.add(scrollpane);
        scrollpane.setPreferredSize(new Dimension(600, 550));
        box.add(Box.createVerticalStrut(5));
        add(box, BorderLayout.NORTH);

        setPreferredSize(new Dimension(600, 600));

        init_MergeFindSet();
    	print(mergefindset);

        Main.console = textArea;

    
        textField.addKeyListener(new java.awt.event.KeyAdapter(){
        	public void keyPressed(KeyEvent e){
        		if (e.getKeyCode() == e.VK_ENTER)
        			run();
        	}
        });
    }
    void run(){
    	String text = textField.getText();
    	StringTokenizer st = new StringTokenizer(text);
    	String mode = "";
    	String prev_token = null;
    	while(st.hasMoreTokens()){
    		String token = st.nextToken();
    		if (token.equals("merge"))
    			mode = "merge";
    		else if (token.equals("find"))
    			mode = "find";
    		else if (mode.equals("merge")){
    			prev_token = token;
    			mode = "merge_next";
    		}
    		else if (mode.equals("merge_next")){
    			mergefindset.merge(mergefindset.find(prev_token), mergefindset.find(token));
    			print("merge "+prev_token+" "+token);
    			print(mergefindset);
    			return;
    		}
    		else if (mode.equals("find")){
    			print("find "+token+ " -> "+mergefindset.find(token));
    			return;
    		}
    	}
    	print("invalid command");
   	
    }
    void init_MergeFindSet(){
       	List list = new ArrayList();
    	for(int i=0; i<10; i++){
    		list.add(""+i);
    	}
    	mergefindset = new MergeFindSet(list);    	
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
    
    
    
    
    
    
    //
    // Utilities
    //
    
    static void print(MergeFindSet mergefindset){
    	print(convertToString(mergefindset));
    }
    static String convertToString(MergeFindSet mergefindset){
    	HashMap<Set,List> set_to_list = new HashMap<Set, List>();
    	for(Object object : mergefindset.object_to_node.keySet()){
    		Set set = mergefindset.find(object);
    		
    		if (set_to_list.get(set) == null)
    			set_to_list.put(set, new ArrayList());
    		((List)set_to_list.get(set)).add(object);
    	}
    	String line = "";
    	for(List list : set_to_list.values()){
    		line += "{ ";
    		for(Object object : list){
    			line+= ""+object+" ";
    		}
    		line += "} ";
    	}
    	return line;
    }
 

    public static JTextArea console;
    public static void print(String line){
    	//console.append(line+"\n");
    	Image image = Main.main.createImage(500, 12);
    	Graphics g = image.getGraphics();
    	g.setColor(java.awt.Color.black);
    	g.drawString(line, 10, 10);
    	g.dispose();
    	main.panel.images.add(image);
    	main.panel.repaint();
    }	

    
  
 }
