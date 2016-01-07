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
        final JFrame frame = new JFrame("Dijkstra");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Main main = new Main();
        main.init();
        frame.getContentPane().add(main);
        frame.pack();
        frame.setVisible(true);
	}

    private final JButton button1 = new JButton("Init");
    private final JButton button2 = new JButton("Dijkstra");

    private final ConsolePanel panel = new ConsolePanel();
    private final JScrollPane scrollpane = new JScrollPane(panel);

 
    DirectedGraph graph = create_graph();
    
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
            	println("dijkstra");
            	Dijkstra.dijkstra(graph, graph.vertices.get(0));
            	println("result");
            	print(graph);
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
    void print(DirectedGraph graph){
    	panel.images.add(get_graph_image(graph));
    	panel.repaint();
    }
    static void print(DirectedGraph graph, Vertex vertex){
    	Image image = main.get_graph_image(graph);
    	main.highlight_vertex(image, vertex);
    	main.panel.images.add(image);
    	main.panel.repaint();
    }
    
    
    
    //
    //	initialize
    //
    static DirectedGraph create_graph(){
    	int[][] positions = {{1,0}, {3,0}, {0,1}, {2,1}, {4, 1}, {1,2}, {3,2}};
    	int[][] edges = {{0,1},{0,2},{0,3},  {1,3}, {1,4}, {2,3}, {3,4}, {2,5}, {3,5}, {3,6}, {4,6}, {5,6}};
    	DirectedGraph graph = new DirectedGraph();
    	for(int i=0; i<positions.length; i++){
    		Vertex v = new Vertex();
    		v.position = positions[i];
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
    Image get_graph_image(DirectedGraph graph){
		Image image = createImage(300, 130);
    	Graphics g = image.getGraphics();
    	
    	g.setColor(java.awt.Color.black);
    	for(int i=0; i<graph.edges.size(); i++){
    		Edge edge = (Edge) graph.edges.get(i);
    		Point p = map(edge.start.position);
    		Point q = map(edge.end.position);
    		draw_arrow(g, p.x, p.y, q.x, q.y);
    		//g.drawLine(p.x, p.y, q.x, q.y);
    		g.setColor(Color.white);
    		g.drawString(""+edge.cost, (p.x+q.x)/2-5+1, (p.y+q.y)/2+4);
    		g.drawString(""+edge.cost, (p.x+q.x)/2-5-1, (p.y+q.y)/2+4);
       		g.setColor(Color.black);
    		g.drawString(""+edge.cost, (p.x+q.x)/2-5, (p.y+q.y)/2+4);
    	}
    	for(int i=0; i<graph.vertices.size(); i++){
    		Vertex v = (Vertex) graph.vertices.get(i);
    		Point p = map(v.position);
    		//g.fillOval(p.x-r, p.y-r, r*2, r*2);
    		int w = 10+g.getFontMetrics().stringWidth(get_label(v));
    		g.drawString( get_label(v), p.x-w/2+5, p.y+4);
    	}
    	
    	return image;
    }
    String get_label(Vertex v){
		return (v.cost == Integer.MAX_VALUE) ? "∞" : ""+v.cost;
    }
    void highlight_vertex(Image image, Vertex v){
    	Graphics g = image.getGraphics();
		Point p = map(v.position);
		g.setColor(Color.red);
		int r = 9;
		int w = 10+g.getFontMetrics().stringWidth(get_label(v));
   	 	g.drawOval(p.x-w/2, p.y-r-1, w, r*2);
    	g.dispose();
    }
    void draw_arrow(Graphics g, int x0, int y0, int x1, int y1){
    	double dx = x1-x0;
    	double dy = y1-y0;
    	double length = Math.sqrt(dx*dx+dy*dy);
    	dx = dx/length;
    	dy = dy/length;
    	int l = 3;
    	int d = 10;
    	g.drawLine((int)(x0+d*dx), (int)(y0+d*dy), (int)(x1-d*dx), (int)(y1-d*dy));
    	g.drawLine((int)(x1-d*dx), (int)(y1-d*dy), (int)(x1-5*l*dx+l*dy), (int)(y1-5*l*dy-l*dx));
    	g.drawLine((int)(x1-d*dx), (int)(y1-d*dy), (int)(x1-5*l*dx-l*dy), (int)(y1-5*l*dy+l*dx));
    	
    }
    Point map(int[] p){
    	int x = 50+p[0]*50;
    	int y = 20+p[1]*50;
    	return new Point(x, y);
    }
    void set_random_costs(){
    	for(Vertex vertex : graph.vertices){
    		vertex.edges.clear();
    		vertex.cost = 0;
    	}
    	
    	for(int i=0; i<graph.edges.size(); i++){
    		Edge edge = (Edge) graph.edges.get(i);
    		edge.cost = (int)(1 + 9*Math.random());
    		if (Math.random()>0.5){
    			Vertex v = edge.start;
    			edge.start = edge.end;
    			edge.end = v;
    		}
    		edge.start.edges.add(edge);
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
