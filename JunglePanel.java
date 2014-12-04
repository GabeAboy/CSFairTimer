/**Gabriel Aboy
 **Jackie Horton CS 110 
 **CS Fair
 **League of Legends Jungle timer */
import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

//Defines each jungle panel

public class JunglePanel extends javax.swing.JPanel {

   private Thread thread;
	private String strStartTime;
	private int min, sec;
   private boolean timerFlag = false;
   
   //Initialize timer at 0
   public JunglePanel(String groupName, String timer) 
   {
    	this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    	
        initComponents();
        jLabelGroupName.setText(groupName);
        jLabelTimer.setText("00:00");
        thread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				changeTime();					
			}
		});
        
        //Splits time min from sec
        String parts[] = timer.split(":");
        min = Integer.parseInt(parts[0]);
        sec = Integer.parseInt(parts[1]);
        
        
        
    }
    //Sets colors
    public JunglePanel(String groupName, String timer, Color color) {
    	this(groupName, timer);
        
        jLabelTimer.setBackground(color);
        jLabelGroupName.setBackground(color);
        this.setBackground(color);
        jLabelTimer.setForeground(Color.white);
        jLabelGroupName.setForeground(Color.white);
        
    }
    
    //Starts thread
    public void startTimer(){
    	timerFlag = true;
    	thread.start();
    }
    
    //Updates time "Decrement"
    protected void changeTime(){
    	while(timerFlag){
    		
    		jLabelTimer.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
    		try{
    			Thread.sleep(1000);
    		}
    		catch(InterruptedException ex){
    			
    		}
    		sec--;
    		if(sec==0){
    			min--;
    			if(min<0)
    				break;
    			else
    				sec=59;
    		}
    		
    	}
    	min=0;
    	sec=0;
    	jLabelTimer.setText(String.format("%02d", min) + ":" + String.format("%02d", sec));
    }

  
    //Check if its done
    public boolean isDone(){
    	return (min==0 && sec==0);
    }
    
    //Initialize components of timer
    private void initComponents() {

        jLabelGroupName = new javax.swing.JLabel();
        jLabelTimer = new javax.swing.JLabel();

        jLabelGroupName.setFont(new java.awt.Font("Tahoma", 0, 12)); 
        jLabelGroupName.setText("jLabelGroupName");

        jLabelTimer.setFont(new java.awt.Font("Times New Roman", 1, 16)); 
        jLabelTimer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTimer.setText("jLabelTimer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelGroupName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelTimer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelGroupName)
                .addGap(26, 26, 26)
                .addComponent(jLabelTimer, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );
    }                        

    
    //Returns components
    public javax.swing.JLabel getjLabelGroupName() {
		return jLabelGroupName;
	}

	public void setjLabelGroupName(javax.swing.JLabel jLabelGroupName) {
		this.jLabelGroupName = jLabelGroupName;
	}

	public javax.swing.JLabel getjLabelTimer() {
		return jLabelTimer;
	}

	public void setjLabelTimer(javax.swing.JLabel jLabelTimer) {
		this.jLabelTimer = jLabelTimer;
	}



	private javax.swing.JLabel jLabelGroupName;
    private javax.swing.JLabel jLabelTimer;
    
                   
}