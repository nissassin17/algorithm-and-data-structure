

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;


import java.util.*;
import java.applet.*;

public class Main extends Applet{

    //
    // User Interface
    //

    // コンソールから起動した場合
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
    private final JButton button2 = new JButton("MergeSort");

    private final JTextField textField = new JTextField("1 0 3 2 6 5 9 7 8 4", 30);
    private final JTextArea textArea = new JTextArea();
    private final JScrollPane scrollpane = new JScrollPane(textArea);

 
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
            	List list = Main.string_to_int_list(textField.getText());
            	
            	Main.clear();
            	Main.println("begin");
            	Main.print(list);
            	
            	list = MergeSort.mergesort(list);
            	
            	Main.println("end");
            	Main.print(list);
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
       
    
    static public String int_array_to_string(int[] array){
    	String line = "";
    	for(int i=0; i<array.length; i++){
    		line += ""+array[i]+" ";
    	}
    	return line;
    }	
    static public List string_to_int_list(String line){
    	ArrayList list = new ArrayList();
    	StringTokenizer st = new java.util.StringTokenizer(line);
    	while(st.hasMoreTokens()){
    		list.add(new Integer(st.nextToken()));
    	}
    	return list;
    }    
    static public String int_list_to_string(List list){
    	String line = "";
    	for(int i=0; i<list.size(); i++){
    		line += ""+((Integer)list.get(i)).intValue()+" ";
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
    static public void print(List array){
    	println(""+int_list_to_string(array));
    }
    static public void print(List array0, String s0, List array1, String s1, List array2){
    	println(""+int_list_to_string(array0)+" "+s0+" "+int_list_to_string(array1)+" "+s1+" "+int_list_to_string(array2));
    }
    static public void print(List array, int r, int l){
    	String line = "";
    	for(int i=0; i<array.size(); i++){
    		if (i == r)
    			line += "[ ";
    		line += ""+array.get(i)+" ";
    		if (i == l)
    			line += "] ";
    	}
    	println(line);
    }    
 }
