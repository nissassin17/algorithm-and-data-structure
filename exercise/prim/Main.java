import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
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
        final JFrame frame = new JFrame("Prim");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Main main = new Main();
        main.init();
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
	}

    private final JButton button1 = new JButton("Init");
    private final JButton button2 = new JButton("Prim");

    private final ConsolePanel panel = new ConsolePanel();
    private final JScrollPane scrollpane = new JScrollPane(panel);

 
    UndirectedGraph graph = create_graph();
    List<int[]> mst = new ArrayList<int[]>();
    
    // アプレットとして起動した場合
    public void init(){
    	main = this;
    	setLayout(new BorderLayout());
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	set_random_costs();
            	println("initalize graph");
            	print(graph);
            }
        });   	
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	println("prim");
            	mst = Prim.compute_minimum_spanning_tree(graph);
            	println("result");
            	print(graph, mst);
            }
        });

        
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.LEFT));
        controls.add(button1);
        controls.add(button2);
        
        Box box = Box.createVerticalBox();
        box.add(Box.createVerticalStrut(5));
        box.add(controls);
        box.add(Box.createVerticalStrut(5));
        box.add(scrollpane);
        scrollpane.setPreferredSize(new Dimension(600, 550));
        box.add(Box.createVerticalStrut(5));
        add(box, BorderLayout.NORTH);

        setPreferredSize(new Dimension(600, 600));

        set_random_costs();
    	print(graph);
        
    }

    
    

    class ConsolePanel extends JPanel {
    	ArrayList images = new ArrayList();
    	public ConsolePanel(){
    	}
    	public void paint(Graphics g){
    		g.setColor(Color.white);
    		g.fillRect(0,0, getWidth(), getHeight());
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
    void print(UndirectedGraph graph){
    	panel.images.add(get_graph_image(graph));
    	panel.repaint();
    }
    static void print(UndirectedGraph graph, int vertex){
    	Image image = main.get_graph_image(graph);
    	highlight_vertex(image, graph, vertex);
    	main.panel.images.add(image);
    	main.panel.repaint();
    }
    static void print(UndirectedGraph graph, List<int[]> edges){
    	Image image = main.get_graph_image(graph);
    	highlight_edges(image, graph, edges);
    	main.panel.images.add(image);
    	main.panel.repaint();
    }
    static void print(UndirectedGraph graph, List<int[]> edges, int[] C, int vertex){
    	Image image = main.get_graph_image(graph);
    	highlight_vertex(image, graph, vertex);
    	highlight_edges(image, graph, edges);
    	paint_vertex_costs(image, graph, C);
    	main.panel.images.add(image);
    	main.panel.repaint();
    }
    
    
    
    
    
    
    //
    //	initialize
    //
    static UndirectedGraph create_graph(){
    	int[][] positions = {{1,0}, {3,0}, {0,1}, {2,1}, {4, 1}, {1,2}, {3,2}};
    	int[][] edges = {{0,1},{0,2},{0,3},  {1,3}, {1,4}, {2,3}, {3,4}, {2,5}, {3,5}, {3,6}, {4,6}, {5,6}};
    	
    	UndirectedGraph graph = new UndirectedGraph(positions.length, edges.length);
    	graph.positions = positions;
    	graph.edges = edges;
 
    	return graph;
    }
    static Image get_graph_image(UndirectedGraph graph){
		Image image = main.createImage(300, 130);
    	Graphics g = image.getGraphics();
    	
    	for(int i=0; i<graph.edges.length; i++){
    		int[] edge = graph.edges[i];
    		int cost = graph.d[edge[0]][edge[1]];
    		Point p = map(graph.positions[edge[0]]);
    		Point q = map(graph.positions[edge[1]]);
        	g.setColor(java.awt.Color.lightGray);
    		g.drawLine(p.x, p.y, q.x, q.y);
    		g.setColor(Color.white);
    		g.drawString(""+cost, (p.x+q.x)/2-5+1, (p.y+q.y)/2+4);
    		g.drawString(""+cost, (p.x+q.x)/2-5-1, (p.y+q.y)/2+4);
       		g.setColor(Color.black);
    		g.drawString(""+cost, (p.x+q.x)/2-5, (p.y+q.y)/2+4);
    	}
    	for(int i=0; i<graph.n; i++){
    		Point p = map(graph.positions[i]);
    		int r = 5;
        	g.setColor(java.awt.Color.lightGray);
    		//g.fillOval(p.x-r, p.y-r, r*2, r*2);
    	}
    	
    	return image;
    }
    static void highlight_vertex(Image image, UndirectedGraph graph, int i){
    	Graphics g = image.getGraphics();
		Point p = map(graph.positions[i]);
		g.setColor(Color.red);
		int r = 9;
   	 	g.drawOval(p.x-r, p.y-r, r*2, r*2);
    	g.dispose();
    }
    static void paint_vertex_costs(Image image, UndirectedGraph graph, int[] C){
    	
    	Graphics g = image.getGraphics();
    	g.setFont(new java.awt.Font("Helvetica", java.awt.Font.BOLD, 12));
    	for(int i=0; i<graph.n; i++){
    		Point p = map(graph.positions[i]);
    		g.setColor(Color.red);
    		int w = 10+g.getFontMetrics().stringWidth(get_label(C[i]));
    		g.drawString( get_label(C[i]), p.x-w/2+5, p.y+4);
    	}
    	g.dispose();
    }
    static String get_label(int cost){
		return (cost == Integer.MAX_VALUE) ? "∞" : ""+cost;
    }    
    static void highlight_edges(Image image, UndirectedGraph graph, List<int[]> edges){
    	Graphics g = image.getGraphics();
		g.setColor(Color.red);
		for(int[] edge : edges){
    		Point p = map(graph.positions[edge[0]]);
    		Point q = map(graph.positions[edge[1]]);
    		g.drawLine(p.x, p.y, q.x, q.y);
		}
    	g.dispose();
    }
    static Point map(int[] p){
    	int x = 50+p[0]*50;
    	int y = 20+p[1]*50;
    	return new Point(x, y);
    }
    void set_random_costs(){
		for(int i=0; i<graph.n; i++)
			for(int j=0; j<graph.n; j++)
				graph.d[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
		
    	for(int i=0; i<graph.edges.length; i++){
    		int[] edge = graph.edges[i];
    		int cost = (int)(1 + 9*Math.random());
    		graph.d[edge[0]][edge[1]] = cost;
    		graph.d[edge[1]][edge[0]] = cost;
    	}
    	
    	clear();
    	get_graph_image(graph);
    	
    	repaint();
    }
    
    
    
    
    
    
    
    
    
    
    //
    // Utilities
    //
    
    public static void println(String line){
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
