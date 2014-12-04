/**Gabriel Aboy
 **Jackie Horton CS 110 
 **CS Fair
 **League of Legends Jungle timer */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class JungleBoard extends JFrame {
	private JunglePanel [] junglePanels;//array that holds timer
	private JPanel topPanel;
	private JPanel centerPanel;
	private JButton  startButton, b1, b2, b3, b4, b5, b6;
	
	private static final int TOTAL_GROUPS = 6;
	private Thread thread;
	
	
	
	public JungleBoard()
   {
   //GUI panels
		junglePanels = new JunglePanel[TOTAL_GROUPS];
		
		junglePanels[0] = new JunglePanel("Pupple: Lizard(RED)", "5:01",Color.red);
		junglePanels[1] = new JunglePanel("Pupple: Golem(BLUES)", "5:01", Color.blue);
		junglePanels[2] = new JunglePanel("Baron Nashor", "7:01", Color.magenta);
		junglePanels[3] = new JunglePanel("Dragon", "6:01", Color.orange);
		junglePanels[4] = new JunglePanel("Blue: Golem(Blue)", "5:01", Color.blue);
		junglePanels[5] = new JunglePanel("Blue: Lizard(Red)", "5:01", Color.red);
		
		topPanel = new JPanel();//Holds buttons
		startButton = new JButton("Start Game");
      b1 = new JButton("Pupple: Lizard(RED)");
		b2 = new JButton("Pupple: Golem(BLUES)");
      b3 = new JButton("Baron Nashor");
      b4 = new JButton("Dragon");
      b5 = new JButton("Blue: Golem(Blue)");
      b6 = new JButton("Blue: Lizard(Red)");
      
      
      topPanel.add(startButton);
      topPanel.add(b1);
      topPanel.add(b2);
      topPanel.add(b3);
      topPanel.add(b4);
      topPanel.add(b5);
      topPanel.add(b6);
		
		centerPanel = new JPanel();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 500);
		this.setTitle("Jungle Timers");
		
      //GRID LAYOUT PANEL
      centerPanel.setLayout(new GridLayout(3, 2));
		
      //ADDS timer jungle panels to ARRAY
		for(JunglePanel junglePanel:junglePanels)
      {
			centerPanel.add(junglePanel);
		}
		
      //ADDS to GUI
		this.add(topPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		
		//CREATS ACTION FOR START BUTTON
      startButton.addActionListener(new ActionListener() {
			
			@Override
			//Start game button action
         public void actionPerformed(ActionEvent e) {
				startGame();
				
			}
		});
		
      //Makes the timer work 
      
		thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				changeStartTime();
				
			}
		});
		//Creates Button actions
      b1.addActionListener(new ButtonListener());
      b2.addActionListener(new ButtonListener());
      b3.addActionListener(new ButtonListener());
      b4.addActionListener(new ButtonListener());
      b5.addActionListener(new ButtonListener());
      b6.addActionListener(new ButtonListener());
      
      

	}	
   //Catch button actions 
   private class ButtonListener implements ActionListener 
      {
         public void actionPerformed(ActionEvent e)
         {
            String actionCommand = e.getActionCommand();
            //if (thread) not running start it
            if (!thread.isAlive())
            {
               thread.start();
            }
            
            if(actionCommand.equals("Pupple: Lizard(RED)"))
            {  
               /*if (junglePanels[0].isDone())
               {
                    thread.interrupt();
                    javax.swing.JLabel timer = junglePanels[0].getjLabelTimer();
                    timer.setText("5:01");
                    junglePanels[0].setjLabelTimer(timer);
                    thread.start();
               }*/
               junglePanels[0].startTimer();
            }
            if(actionCommand.equals("Pupple: Golem(BLUES)"))
            {
               junglePanels[1].startTimer();
            }
            if(actionCommand.equals("Baron Nashor"))
            {
               junglePanels[2].startTimer();
            }
            if(actionCommand.equals("Dragon"))
            {
               junglePanels[3].startTimer();
            }
            if(actionCommand.equals("Blue: Golem(Blue)"))
            {
               junglePanels[4].startTimer();
            }
            if(actionCommand.equals("Blue: Lizard(Red)"))
            {
               junglePanels[5].startTimer();
            }
         }
      }
   //Start timer! loop
	private void changeStartTime(){
		
		while(true){
      
			GregorianCalendar cal = new GregorianCalendar();
			
			csec++;
			if(csec==60){
				cmin ++;
				csec=0;
				if(cmin==60){
					chr++;
					cmin=0;
				}
			}
			
			
			
			
			
			
			String str = String.format("%02d:%02d:%02d", chr, cmin, csec);
			startButton.setText(str);
			
         try{
				Thread.sleep(1000);
			}
			catch(InterruptedException ex){
				
			}
			boolean done = true;
			for(JunglePanel junglePanel:junglePanels){
				done = junglePanel.isDone();
			}
			if(done)
				break;
		}
	}
   //Start thread
	private void startGame(){
		for(JunglePanel junglePanel:junglePanels){
			junglePanel.startTimer();
		}
	
		thread.start();
	}
	
	
	private int chr=0, cmin=0, csec=0;
	
}