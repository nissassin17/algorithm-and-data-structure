

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.applet.*;

public class Main extends Applet{
	
    //
    // User Interface
    //
	
    // コンソールから起動した場合
	static Main main;
	public static void main(String args[]) {
        final JFrame frame = new JFrame("HeapSort");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Main main = new Main();
        main.init();
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
	}

    private final JButton button1 = new JButton("Random");
    private final JButton button2 = new JButton("HeapSort");

    private final JTextField textField = new JTextField("1 0 3 2 6 5 9 7 8 4", 30);
    private final ConsolePanel panel = new ConsolePanel();
    private final JScrollPane scrollpane = new JScrollPane(panel);
    
 
    // アプレットとして起動した場合
    public void init(){
    	main = this;
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
            	Main.print("begin");
            	Main.print(array);
            	
            	HeapSort.heapsort(array);
            	
            	Main.print("end");
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
/*
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
*/
    
    
    
  
    
    class ConsolePanel extends JPanel {
    	ArrayList images = new ArrayList();
    	public ConsolePanel(){
    		this.setBackground(Color.white);
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
    static void print(int[] heap){
       	if (heap.length == 0)
    		return;
 
       	main.panel.images.add(getTreeImage(heap));
       	main.panel.repaint();
    }
    static void print(int[] heap, int i, int j){
       	if (heap.length == 0)
    		return;
 
       	Image image = getTreeImage(heap);
       	highlight_node(image, heap,i);
       	highlight_node(image, heap,j);
       	main.panel.images.add(image);
       	main.panel.repaint();
    }
    static Image getTreeImage(int[] heap){
    	int h = compute_height(heap);
    	int w = compute_width(heap);
    	
 		Image image = Main.main.createImage(10+20*w, 10+20*h);
		Graphics g = image.getGraphics();
		g.setColor(java.awt.Color.black);
		drawTree(g, heap, w);
		g.dispose();
    	return image;
    }
    static void highlight_node(Image image, int[] heap, int index){
    	Graphics g = image.getGraphics();
		Point p =get_node_position(index, compute_width(heap));
		g.setColor(Color.red);
		int r = 9;
		int w = 10+g.getFontMetrics().stringWidth(""+heap[index]);
   	 	g.drawOval(p.x-w/2, p.y-r-1, w, r*2);
    	g.dispose();
    }    
    static int compute_width(int[] heap){
       	int h = compute_height(heap);
    	return pow(2, h-1);
    }
    static int compute_height(int[] heap){
    	int w = 1;
    	int r = heap.length;
    	int h = 0;
    	while(r > 0){
    		r -= w;
    		w *= 2;
    		h += 1;
    	}
    	return h;
    }	
    static void drawTree(Graphics g, int[] heap, int width){
    	g.setColor(Color.black);
   	
    	for(int i=0; i<heap.length; i++){
    		Point p = get_node_position(i, width);
    		int w = g.getFontMetrics().stringWidth(""+heap[i]);
			g.drawString(""+heap[i], p.x-w/2, p.y+4);
			
			if (i >= 1){
	    		Point q = get_node_position( (i-1)/2, width);
				//g.drawLine(p.x, p.y, q.x, q.y);

				double l = Math.sqrt((p.x-q.x)*(p.x-q.x)+(p.y-q.y)*(p.y-q.y));
	    		int dx = (int)(8*(q.x-p.x)/l);
	    		int dy = (int)(8*(q.y-p.y)/l);
				g.drawLine(p.x+dx, p.y+dy, q.x-dx, q.y-dy);
			}
    	}
 
    }
    static Point get_node_position(int i, int width){
		int y = log(2, i+1);
		int x = i - (pow(2,y)-1);
		int w = pow(2,y);
		int dx = width / w;
		return new Point((int)( 10+ (dx* (x-(w-1)/2.0) + width/2.0)*20), 10+y*20);
    	
    }
    static int log(int a, double b){
    	int x = 0;
    	while(b>=a){
    		b = b/a;
    		x++;
    	}
    	return x;
    }
    static int pow(int a, int b){
    	int x = 1;
    	for(int i=0; i<b; i++)
    		x *= a;
    	return x;
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
    public static void clear(){
    	main.panel.images = new ArrayList();
    	main.panel.repaint();
    }    
 }
