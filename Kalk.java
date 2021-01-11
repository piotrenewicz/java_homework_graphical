import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
 
public class Kalk implements ActionListener
{
   JTextField t1;
   JButton b1;
   JButton bplus,brow;
 
   double x,buf;
 
   public void actionPerformed(ActionEvent e)                  
   {                                                           
      Object target = e.getSource();                           
 
      if(target==b1)                                           
      {                                                        
         t1.setText(t1.getText()+((JButton)target).getText()); 
         t1.requestFocus();                                    
      }                                                        
 
      else if(target==bplus)                                   
      {                                                        
         buf=Double.parseDouble(t1.getText());                 
         t1.setText("");                                       
         t1.requestFocus();                                    
      }                                                        
 
      else if(target==brow||target==t1)                        
      {                                                        
         x=Double.parseDouble(t1.getText());                   
         x=buf+x;                                              
         t1.setText(Double.toString(x));                       
         t1.requestFocus();                                    
      }                                                        
   }                                                           
 
   void init()                                                                   
   {                                                                            
      //try                                                                     
      //{                                                                       
      //UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());    
      //}                                                                       
      //catch(Exception e){}                                                    
 
      JFrame f=new JFrame();                                                    
      Container c=f.getContentPane();                                           
 
      GridBagLayout gbl=new GridBagLayout();                                    
      GridBagConstraints gbc=new GridBagConstraints();                          
      gbc.fill=GridBagConstraints.HORIZONTAL;                                   
      c.setLayout(gbl);                                                         
 
 
 
      t1=new JTextField(15);                                                    
      t1.addActionListener(this);                                               
      t1.setHorizontalAlignment(JTextField.RIGHT);                              
      gbc.gridx=0;                                                              
      gbc.gridy=0;                                                              
      gbc.gridwidth=5;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=5;                                                              
      gbc.insets=new Insets(5,5,0,5);                                           
      gbl.setConstraints(t1,gbc);                                               
      c.add(t1);                                                                
 
 
 
      b1=new JButton("1");                                                      
      b1.addActionListener(this);                                               
      b1.setFocusable(false);                                                   
      gbc.gridx=0;                                                              
      gbc.gridy=1;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                                              
      gbc.insets=new Insets(5,5,0,0);                                           
      gbl.setConstraints(b1,gbc);                                               
      c.add(b1);                                                                
 
 
 
      bplus=new JButton("+");                                                   
      bplus.addActionListener(this);                                            
      bplus.setFocusable(false);                                                
      bplus.setToolTipText("dodawanie");                                        
      gbc.gridx=3;                                                              
      gbc.gridy=1;                                                              
      gbc.gridwidth=2;                                                          
      gbc.ipadx=30;                                                             
      gbc.ipady=0;                                                              
      gbc.insets=new Insets(5,5,0,5);                                           
      gbl.setConstraints(bplus,gbc);                                            
      c.add(bplus);                                                             
 
 
 
      brow=new JButton("=");                                                    
      brow.addActionListener(this);                                             
      brow.setFocusable(false);                                                 
      brow.setToolTipText("wykonaj działanie");                                 
      gbc.gridx=0;                                                              
      gbc.gridy=5;                                                              
      gbc.gridwidth=4;                                                          
      gbc.ipadx=30;                                                             
      gbc.ipady=0;                                                              
      gbc.insets=new Insets(5,5,5,0);                                           
      gbl.setConstraints(brow,gbc);                                             
      c.add(brow);                                                              
 
 
 
      f.pack();                                                                 
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                         
      f.setTitle("Kalk");                                                       
      f.setVisible(true);                                                       
   }                                                                            
 
   public static void main(String[] args)          
   {                                               
      //do wersji 1.4                              
      //new Kalk().init();                         
 
      //od wersji 1.5                              
         SwingUtilities.invokeLater(new Runnable() 
      {                                            
         public void run()                         
         {                                         
            new Kalk().init();                     
         }                                         
      });                                          
   }                                               
}