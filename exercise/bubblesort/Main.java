

import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.swing.*;


import java.util.*;

public class Main extends Applet{

    //
    // User Interface
    //

    private final JButton button1 = new JButton("Random");
    private final JButton button2 = new JButton("BubbleSort");

    private final JTextField textField = new JTextField("1 0 3 2 6 5 9 7 8 4", 30);
    private final JTextArea textArea = new JTextArea();
    private final JScrollPane scrollpane = new JScrollPane(textArea);

    // コンソールから起動した場合
    public static void main(String args[]) {
        final JFrame frame = new JFrame("BubbleSort");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Main main = new Main();
        main.init();
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
	}

    // アプレットとして起動した場合
    public void init(){
    	setLayout(new BorderLayout());
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int[] array = {0,1,2,3,4,5,6,7,8,9};
            	for(int i=0; i<10; i++){
            		int random = (int)(Math.random()*10);
            		int tmp = array[i];
            		array[i] = array[random];
            		array[random] = tmp;
            	}
            	textField.setText(Main.int_array_to_string(array));
            }
        });
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int[] array = Main.string_to_int_array(textField.getText());
            	
            	Main.clear();
            	Main.println("begin");
            	Main.print(array);
            	
            	BubbleSort.sort(array);
            	
            	Main.println("end");
            	Main.print(array);
            }
        });

        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(5));
        box.add(createSubPanel(textField, button1, button2));
        box.add(Box.createVerticalStrut(5));
        box.add(scrollpane);
        scrollpane.setPreferredSize(new Dimension(600, 550));
        box.add(Box.createVerticalStrut(5));
        add(box, BorderLayout.NORTH);

        setPreferredSize(new Dimension(600, 600));

        Main.console = textArea;
        
    }
    private JComponent createSubPanel(JComponent cmp0, JComponent cmp1, JComponent cmp2){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(cmp0);
        panel.add(cmp1);
        panel.add(cmp2);
        return panel;
    }

    
    
    
    
    //
    // Utilities
    //
    
    
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
    public static JTextArea console;
    public static void println(String line){
    	console.append(line+"\n");
    }	
    public static void clear(){
    	console.setText("");
    }	
    static public void print(int[] array){
    	println(""+int_array_to_string(array));
    }
    static public void print(int[] array, int r, int l){
    	String line = "";
    	for(int i=0; i<array.length; i++){
    		if (i == r)
    			line += "[ ";
    		line += ""+array[i]+" ";
    		if (i == l)
    			line += "] ";
    	}
    	println(line);
    }
    static public void print(int[] array, int r, int k, int l){
    	String line = "";
    	for(int i=0; i<array.length; i++){
    		if (i == r)
    			line += "[ ";
    		else if (i == k)
    			line += "| ";
    		line += ""+array[i]+" ";
    		
    		if (i == l)
    			line += "] ";
    	}
    	println(line);
    }

 }
