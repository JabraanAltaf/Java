import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.text.DecimalFormat;
// A GUI program which emulates a virtual vending machine where the stock number of each item changes after a press and there is total sales counter
public class Vending extends JFrame {
	double totalcost = 0.0;
	JButton[] item = new JButton[9]; // Array for each item
	JLabel[] count = new JLabel[9]; // Array for stock labels for each item
	int [] stock = new int[9]; // Array for stock of each item
	double [] cost = new double[9]; // Array for cost of each item
	JButton reset = new JButton("Reset Stock");
	JButton info = new JButton("Vending Info");
	String pound = "\u00a3"; // Used for pound symbol
	
	public Vending(String title) {
		super(title);
		setLayout(null);
	// Initializing each item JButton to have a stock of 5 items.
		for (int i = 0; i < item.length; i++) {
			stock[i] = 5; 
		}
	// Creating JButtons for each item with their cost.
		item[0] = new JButton("Chocolate Jigglypuffs (" + pound + "1.35)");
		cost[0] = 1.35;
		item[1] = new JButton("Caramel Jigglypuffs (" + pound + "0.85)"); 
		cost[1] = 0.85;
		item[2] = new JButton("French Vanilla Jiggylpuffs (" + pound + "1.00)");
		cost[2] = 1.00;
	
		item[3] = new JButton("Chocolate Bombs (" + pound + "1.35)");
		cost[3] = 1.35;
		item[4] = new JButton("Caramel Bombs (" + pound + "0.85)"); 
		cost[4] = 0.85;
		item[5] = new JButton("French Vanilla Bombs (" + pound + "1.00)");
		cost[5] = 1.00;
	
		item[6] = new JButton("Chocolate Twists (" + pound + "1.35)");
		cost[6] = 1.35;
		item[7] = new JButton("Caramel  Twists (" + pound + "0.85)"); 
		cost[7] = 0.85;
		item[8] = new JButton("French Vanilla Twists (" + pound + "1.00)");
		cost[8] = 1.00;
	
		// Adding each of the JButtons to the JFrame and adjusting their size and position
		add(item[0]);
		item[0].setBounds(50,50,250,30);
		add(item[1]);
		item[1].setBounds(50,175,250,30);
		add(item[2]);
		item[2].setBounds(50,300,250,30);
	
		// Making the first column of JButtons to have a White background
		for (int normal = 0; normal < 3; normal++){
			item[normal].setBackground(Color.WHITE);
		}
			
		add(item[3]);
		item[3].setBounds(350,50,250,30);
		add(item[4]);
		item[4].setBounds(350,175,250,30);
		add(item[5]);
		item[5].setBounds(350,300,250,30);
		
		// Making the second column of JButtons have a Green background
		for (int extra = 3; extra < 6; extra++){
			item[extra].setBackground(Color.GREEN);
		}
		
		add(item[6]);
		item[6].setBounds(650,50,250,30);
		add(item[7]);
		item[7].setBounds(650,175,250,30);
		add(item[8]);
		item[8].setBounds(650,300,250,30);
		
		// Making the third column of JButtons have a Yellow background
		for (int twist = 6; twist < 9; twist++){
			item[twist].setBackground(Color.YELLOW);
		}	
		
		
		// Iterating to get JLabels which diaplay the stock count for each item
		for (int i = 0; i < item.length; i++) {
			count[i] = new JLabel ("Stock: " + stock[i]);
		}
		// Inserting each of the JLabels on to the JFrame and adjusting there position and size.
		add(count[0]);
		count[0].setBounds(80,80,150,30);
		add(count[1]);
		count[1].setBounds(80,200,150,30);
		add(count[2]);
		count[2].setBounds(80,330,150,30);
		
		add(count[3]);
		count[3].setBounds(380,70,150,30);
		add(count[4]);
		count[4].setBounds(380,200,150,30);
		add(count[5]);
		count[5].setBounds(380,320,150,30);
		
		add(count[6]);
		count[6].setBounds(680,70,150,30);
		add(count[7]);
		count[7].setBounds(680,200,150,30);
		add(count[8]);
		count[8].setBounds(680,320,150,30);
		
		
		// Adding a the JButton for the Vending information and setting it position,size and color.
		add(info);
		info.setBounds(650,400,250,50);
		info.setBackground(Color.CYAN);
		

		// Adding an action listener for each of the items.
		action act = new action();
		for (int i = 0; i < item.length; i++) {
			item[i].addActionListener(act);
		}
		
		//Adding an action listener for the Vendor information JButton	
		vendinginfo v = new vendinginfo();
		info.addActionListener(v);
	}
	
	// Class which makes a new frame which will display the total cost and allow the user to reset the original JFrame by pressing a button.
	public class info extends JFrame {
		public info(String title) {
			super(title);
			setLayout(null);
		
			DecimalFormat df = new DecimalFormat("0.00"); // Formats total cost to two decimal places
			JLabel total = new JLabel("Sales: " + pound + df.format(totalcost)); 
			add(total);
			total.setBounds(50,0,100,200);
		
			add(reset);
			reset.setBounds(50,150,150,30);
			reset.setBackground(Color.ORANGE);
		
			// Adds action listener to rest stock JButton
			restart r = new restart();
			reset.addActionListener(r); 
		}
	}
	
	// Class which will make a new window which will display when there are 0 items left in stock
	public class Message extends JFrame {
		public Message(String title) {
			super(title);
			setLayout(null);
		
			JLabel error = new JLabel("Sorry item is out of stock!");
			add (error);
			error.setBounds(100,0,200,300);
		
	    }
	}
	// Action listener for pressing each button
	private class action implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			Message frame = new Message("Error Message");
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setBounds(0,0,400,400);
		// Iterating through the items, so when an JButton for an item is pressed, the corresponding stock with that item decrements by 1			
			for (int i = 0; i < item.length; i++) {		
				if (e.getSource() == item[i]) {
					if (e.getSource() == item[i]) { // Finds source of which JButton was pressed
						stock[i] = stock[i] - 1;
						count[i].setText("Stock: "+ stock[i]);
						totalcost = totalcost + cost[i];
					}
					if (stock[i] <= 0) {
						frame.setVisible(true);
						count[i].setText("Stock: "+ 0);
						cost[i] = 0.0; // When the stock is 0, nothing will be added to the price when one clicks on a out-of-stock item.
						item[i].setBackground(Color.RED); // Turns the JButton with 0 stock to red
					}
				}
			
			}
		}
	}
	
	// Action Listener which restarts the Vending Machine with it's original stock after pressing on the "Reset!" JButton
	private class restart implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			dispose();
			Vending frame = new Vending("Vending Machine");
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setBounds(0, 0, 1000, 525);
			frame.setVisible(true);
		}
	}
	
	// Action listener which shows the information on total costs and gives an option to reset, when one presses the "Vendor Info" JButton
	private class vendinginfo implements ActionListener {
		public void actionPerformed (ActionEvent e) {
			info frame = new info("Cost Info");
			frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frame.setBounds(0,0,300,250);
			frame.setVisible(true);
		}
	}
	
	// Main Method which will create a Vending Machine frame
	public static void main(String[] args)  {
			Vending frame = new Vending("Vending Machine");
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.setBounds(0, 0, 1000, 525);
			frame.setVisible(true);
	}
}
	