package mazeGenerator;
import mazeGenerator.algorithms.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;

import java.awt.*;
import java.awt.event.*;

public class MazeUI extends JFrame {

	MazeView currentMaze = new MazeView();
	JPanel panel;
	JSpinner width;
	JSpinner height;
	JSlider cellSize;
	JSlider gapSize;
	Color mazeColor = Color.white;
	Color lineColor = Color.black;
	JComboBox<Algorithms> algorithmsBox;

	public static void main(String[] args) {


		MazeUI ui = new MazeUI();

	}

	public MazeUI(){

		super("Maze Generator");

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth=6;
		c.gridheight=4;
		c.fill=GridBagConstraints.BOTH;
		c.gridx=0;
		c.gridy=0;
		c.weightx=1;
		c.weighty = 5;
		c.anchor = GridBagConstraints.CENTER;
		JScrollPane scroller = new JScrollPane(currentMaze);
		
		panel.add(scroller,c);

		c = new GridBagConstraints();
		c.insets= new Insets(10,10,10,10);
		c.fill=GridBagConstraints.HORIZONTAL;
		c.gridwidth=1;
		c.gridheight=1;
		c.gridx=0;
		c.gridy=4;
		c.anchor=GridBagConstraints.EAST;
		c.weightx=.5;
		panel.add(new JLabel("Maze Width"),c);

		c.gridx=1;
		c.weightx=1;
		c.gridwidth=2;
		c.anchor= GridBagConstraints.WEST;
		width = new JSpinner(new SpinnerNumberModel(25,1,null,1));
		width.addChangeListener(this::reloadChangeEvent);
		panel.add(width,c);

		c.gridx=3;
		c.weightx=.5;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.EAST;
		panel.add(new JLabel("Maze Height"),c);

		c.gridx=4;
		c.weightx=1;
		c.gridwidth=2;
		c.anchor=GridBagConstraints.WEST;
		height= new JSpinner(new SpinnerNumberModel(15,1,null,1));
		height.addChangeListener(this::reloadChangeEvent);
		panel.add(height,c);



		c.gridx=0;

		c.gridy=5;
		c.weightx=.5;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.EAST;
		panel.add(new JLabel("Cell size"),c);

		c.gridx= 1;
		c.weightx=1;
		c.gridwidth=2;
		c.anchor= GridBagConstraints.WEST;
		cellSize = new JSlider(10,30,20);
		cellSize.addChangeListener(this::reloadChangeEvent);
		panel.add(cellSize,c);
		
		c.weightx=.5;
		c.gridx=3;
		c.gridwidth=1;
		c.anchor=GridBagConstraints.EAST;
		panel.add(new JLabel("Gap Size"),c);
		
		c.weightx=1;
		c.gridx = 4;
		c.gridwidth=2;
		c.anchor= GridBagConstraints.WEST;
		gapSize = new JSlider(1,5,3);
		gapSize.addChangeListener(this::reloadChangeEvent);
		panel.add(gapSize,c);
		
		c.weightx=0;
		c.gridx=0;
		c.gridy=6;
		c.gridwidth=1;
		panel.add(new JLabel("Algorithm"), c);
		
		c.gridx=1;		
		c.gridwidth=5;
		algorithmsBox = new JComboBox<>(Algorithms.values());
		algorithmsBox.addActionListener(this::reloadMazeView);
		panel.add(algorithmsBox,c);
		
		c = new GridBagConstraints();
		c.fill=GridBagConstraints.BOTH;
		c.insets= new Insets(10,10,10,10);
		c.gridwidth=4;
		c.gridheight=1;
		c.gridy=8;
		c.gridx=2;
		c.weighty=0;
		c.weightx=0;
		JButton reloadButton = new JButton("Generate new maze");
		reloadButton.addActionListener(this::reloadMazeView);
		panel.add(reloadButton,c);

		c.gridwidth=1;
		c.gridx=0;
		JButton colorButton = new JButton("Change maze color");
		colorButton.addActionListener((e)->{
			Color tempColor = JColorChooser.showDialog(null, "Choose a color", mazeColor);
			if(tempColor != null) {
				mazeColor = tempColor;
				reloadButton.doClick();
			}

		});
		panel.add(colorButton,c);
		
		c.gridx=1;
		JButton lineColorButton = new JButton("Change line color");
		lineColorButton.addActionListener((e)->{
			Color tempColor = JColorChooser.showDialog(null, "Choose a color", mazeColor);
			if(tempColor != null) {
				lineColor = tempColor;
				reloadButton.doClick();
			}
		});
		panel.add(lineColorButton,c);

		this.add(panel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(640, 480);
		this.setVisible(true);

		reloadButton.doClick();
	}

	public void reloadMazeView(ActionEvent e){

		MazeGenerator generator = ((Algorithms)algorithmsBox.getSelectedItem()).getGenerator((Integer)width.getValue(), (Integer)height.getValue());
		MazeNode[][] nodes = generator.getMaze();
		currentMaze.changeNodes(nodes,cellSize.getValue(),gapSize.getValue());
		this.repaint();
	}

	public void reloadChangeEvent(ChangeEvent e){
		reloadMazeView(null);
	}

	public class MazeView extends JComponent{

		MazeNode[][] nodes;
		int cellSize=20;
		int gapSize=2;

		public MazeView(){
			super();

		}

		public void changeNodes(MazeNode[][] nodes, int newCellSize, int newGapSize){
			this.nodes= nodes;
			cellSize=newCellSize;
			gapSize=newGapSize;
			setPreferredSize(new Dimension(nodes.length*(cellSize+gapSize)-gapSize,nodes[0].length*(cellSize+gapSize)-gapSize));
			repaint();
			revalidate();
		}

		protected void paintComponent(Graphics g){

			
			super.paintComponent(g);
			
			if(nodes != null){
				
				g.setColor(MazeUI.this.lineColor);
				g.fillRect(0, 0, nodes.length*(cellSize+gapSize)-gapSize,nodes[0].length*(cellSize+gapSize)-gapSize);
				
				g.setColor(MazeUI.this.mazeColor);
				for(int x = 0;x < nodes.length;x++){
					for(int y = 0;y< nodes[0].length;y++){
						g.fillRect(x*(cellSize+gapSize), y*(cellSize+gapSize), cellSize, cellSize);
						for(Direction d: nodes[x][y].connections){
							switch(d){
							case N:
								g.fillRect(x*(cellSize+gapSize), y*(cellSize+gapSize)-gapSize, cellSize,gapSize);
								break;
							case S:
								g.fillRect(x*(cellSize+gapSize), y*(cellSize+gapSize)+cellSize, cellSize, gapSize);
								break;
							case W:
								g.fillRect(x*(cellSize+gapSize)-gapSize, y*(cellSize+gapSize), gapSize, cellSize);
								break;
							case E:
								g.fillRect(x*(cellSize+gapSize)+cellSize, y*(cellSize+gapSize), gapSize, cellSize);
							}
						}
					}
				}
			}
		}
	}

}
