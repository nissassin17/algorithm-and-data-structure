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
        final JFrame frame = new JFrame("Kruskal");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Main main = new Main();
        main.init();
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
	}

    private final JButton button1 = new JButton("Init");
    private final JButton button2 = new JButton("Kruskal");

    private final ConsolePanel panel = new ConsolePanel();
    private final JScrollPane scrollpane = new JScrollPane(panel);

 
    UndirectedGraph graph = create_graph();
    List<Edge> mst = new ArrayList<Edge>();
    
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
            	println("kruskal");
            	mst = Kruskal.compute_minimum_spanning_tree(graph);
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
    static void print(UndirectedGraph graph, List<Edge> edges){
    	Image image = main.get_graph_image(graph);
    	highlight_edges(image, graph, edges);
    	main.panel.images.add(image);
    	main.panel.repaint();
    }
    static void print(UndirectedGraph graph, List<Edge> edges, Edge edge){
    	Image image = main.get_graph_image(graph);
    	highlight_edges(image, graph, edges);
    	highlight_edge(image, graph, edge);
    	main.panel.images.add(image);
    	main.panel.repaint();
    }
    
    
    
    
    
    
    //
    //	initialize
    //
    static UndirectedGraph create_graph(){
    	int[][] positions = {{1,0}, {3,0}, {0,1}, {2,1}, {4, 1}, {1,2}, {3,2}};
    	int[][] edges = {{0,1},{0,2},{0,3},  {1,3}, {1,4}, {2,3}, {3,4}, {2,5}, {3,5}, {3,6}, {4,6}, {5,6}};
    	
    	UndirectedGraph graph = new UndirectedGraph();
    	for(int i=0; i<positions.length; i++){
    		Vertex v = new Vertex();
    		v.position = positions[i];
    		v.index = i;
    		graph.vertices.add(v);
    	}
    	for(int i=0; i<edges.length; i++){
    		Edge edge = new Edge();
    		int[] e = edges[i];
    		edge.start = (Vertex) graph.vertices.get(e[0]);
    		edge.end = (Vertex) graph.vertices.get(e[1]);
    		graph.edges.add(edge);
    	}
    	return graph;    	
    }
    static Image get_graph_image(UndirectedGraph graph){
		Image image = main.createImage(300, 130);
    	Graphics g = image.getGraphics();
    	
    	for(Edge edge : graph.edges){
    		Point p = map(edge.start.position);
    		Point q = map(edge.end.position);
        	g.setColor(java.awt.Color.lightGray);
    		g.drawLine(p.x, p.y, q.x, q.y);
    		g.setColor(Color.white);
    		g.drawString(""+edge.cost, (p.x+q.x)/2-5+1, (p.y+q.y)/2+4);
    		g.drawString(""+edge.cost, (p.x+q.x)/2-5-1, (p.y+q.y)/2+4);
       		g.setColor(Color.black);
    		g.drawString(""+edge.cost, (p.x+q.x)/2-5, (p.y+q.y)/2+4);
    	}
    	for(Vertex v : graph.vertices){
    		Point p = map(v.position);
    		int r = 5;
        	g.setColor(java.awt.Color.lightGray);
    		//g.fillOval(p.x-r, p.y-r, r*2, r*2);
    	}
    	
    	return image;
    }
    static void highlight_edge(Image image, UndirectedGraph graph, Edge edge){
    	Graphics g = image.getGraphics();
    	g.setColor(Color.red);
		Point p = map(edge.start.position);
		Point q = map(edge.end.position);
    	int r = 10;
    	g.drawOval((p.x+q.x)/2-r, (p.y+q.y)/2-r, 2*r, 2*r);
    	g.dispose();
    }
    static String get_label(int cost){
		return (cost == Integer.MAX_VALUE) ? "∞" : ""+cost;
    }    
    static void highlight_edges(Image image, UndirectedGraph graph, List<Edge> edges){
    	Graphics g = image.getGraphics();
		g.setColor(Color.red);
		for(Edge edge : edges){
    		Point p = map(edge.start.position);
    		Point q = map(edge.end.position);
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
    	for(Edge edge : graph.edges){
    		edge.cost = (int)(1 + 9*Math.random());
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
