import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import static java.lang.Math.*;

public class Kalk implements ActionListener
{
   JTextField t1;
   JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0;
   JButton bdot, bcl, bmemory;
   JButton bplus, bminus, bmultiply, bdivide, brow, bsqrt, bpow, bproc;

   Boolean addBool = false;
   Boolean minBool = false;
   Boolean divBool = false;
   Boolean mulBool = false;
   Boolean powBool = false;
   Boolean dot_on_screen = false;
    
   double x, buf, mem;
   boolean mem_flag = false;

   void check_dot(){
      dot_on_screen = t1.getText().contains(".");
   }

   void set_number(double number){
      String to_set = Double.toString(number);
      if(number - Math.floor(number) == 0) {
         to_set = Integer.toString((int) number);
      }
      t1.setText(to_set);
      t1.requestFocus();
      check_dot();

   }

   void clear_screen(){
      dot_on_screen = false;
      t1.setText("");
      t1.requestFocus();
      x = 0;
   }

   void screen_append(String text){
      t1.setText(t1.getText()+text);
      t1.requestFocus();
   }

   public void action_on_char(char input){
      System.out.println(input);
      if(Character.isDigit(input)){
         screen_append(input+"");
         x = x*10 + (int) input;
      }
      else if(input == '.') {
         check_dot();
         if(dot_on_screen){
            return;
         }
         dot_on_screen = true;
         screen_append(".");
      }
      else if(input == '+'){
         buf=Double.parseDouble(t1.getText());
         clear_screen();
         addBool = true;
      }
      else if(input == '-'){
         buf=Double.parseDouble(t1.getText());
         clear_screen();
         minBool = true;
      }
      else if(input == '*'){
         buf=Double.parseDouble(t1.getText());
         clear_screen();
         mulBool = true;
      }
      else if(input == '/'){
         buf=Double.parseDouble(t1.getText());
         clear_screen();
         divBool = true;
      }
      else if(input == '^'){
         buf=Double.parseDouble(t1.getText());
         clear_screen();
         powBool = true;
      }
      else if(input == '='){

      }


   }


   public void actionPerformed(ActionEvent e)                  
   {                                                           
      Object target = e.getSource();

//      if(target == bdot){
//         if(dot_on_screen){
//            return;
//         }
//         dot_on_screen = true;
//      }
//
//      if(target==b1 || target==b2 || target==b3 || target==b4 || target==b5
//              || target==b6 || target==b7 || target==b8 || target==b9 || target==b0 || target==bdot)
//      {
//         t1.setText(t1.getText()+((JButton)target).getText());
//         t1.requestFocus();
//      }
//
//      else if(target==bplus)
//      {
//         buf=Double.parseDouble(t1.getText());
//         clear_screen();
//         addBool = true;
//      }
//
//      else if(target==bminus)
//      {
//         buf=Double.parseDouble(t1.getText());
//         clear_screen();
//         minBool = true;
//      }

      if(target==bmultiply)
      {
         action_on_char('*');
      }

      else if(target==bminus)
      {
         action_on_char('-');
      }

      else if(target==bplus)
      {
         action_on_char('+');
      }

      else if(target==bdivide)
      {
         action_on_char('/');
      }

      else if(target==bpow)
      {
         action_on_char('^');
      }

//      else if(target==bdivide)
//      {
//         buf=Double.parseDouble(t1.getText());
//         clear_screen();
//         divBool = true;
//      }

      else if(target == bsqrt) {
         buf = Double.parseDouble(t1.getText());
         if(buf < 0){
            t1.setText("Błąd pierwiastkowania!");
         }else{
            set_number(sqrt(buf));
         }
      }


      else if(target == bproc) {
         x = Double.parseDouble(t1.getText());
         set_number((buf / 100) * x);
      }
 
      else if(target==brow||target==t1) {
         x = Double.parseDouble(t1.getText());
         if (addBool) {
            x = buf + x;
         }
         else if (minBool) {
            x = buf - x;
         }
         else if (mulBool) {
            x = buf * x;
         }
         else if (divBool) {
            if(x == 0){
               t1.setText("Błąd dzielenia przez 0!"); return;
            }else{
               x = buf / x;
            }
         }
         else if (powBool) {
            x = pow(buf, x);
         }
         set_number(x);

         addBool = false;
         minBool = false;
         divBool = false;
         mulBool = false;
         powBool = false;
      }

      else if(target == bcl)
      {
         x = 0;
         buf = 0;
         clear_screen();
      }
      else if(target == bmemory){
         if(mem_flag == false){
            mem_flag = true;
            mem= Double.parseDouble(t1.getText());
            clear_screen();
            bmemory.setForeground(Color.GREEN);
         }else{
            clear_screen();
            set_number(mem);
            mem_flag = false;
            bmemory.setForeground(Color.BLACK);
         }
      }

      else {
         action_on_char(((JButton)target).getText().charAt(0));
      }

   }
 
   void init()                                                                   
   {
      x = 0;
      buf = 0;

      try
      {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
      }
      catch(Exception e){
         System.err.println(e.getMessage());
      }
 
      JFrame f=new JFrame();                                                    
      Container c=f.getContentPane();
      f.setResizable(false);
      GridBagLayout gbl=new GridBagLayout();                                    
      GridBagConstraints gbc=new GridBagConstraints();                          
      gbc.fill=GridBagConstraints.HORIZONTAL;                                   
      c.setLayout(gbl);                                                         
 
 
 
      t1=new JTextField(15);                                                    
      t1.addActionListener(this);                                               
      t1.setHorizontalAlignment(JTextField.RIGHT);
      t1.setEditable(false);
      gbc.gridx=0;                                                              
      gbc.gridy=0;                                                              
      gbc.gridwidth=4;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=5;                                                              
      gbc.insets=new Insets(5,5,0,5);                                           
      gbl.setConstraints(t1,gbc);                                               
      c.add(t1);

      t1.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
            action_on_char(e.getKeyChar());
         }
      }
      );



      //====================cyfry========================
      b1=new JButton("1");                                                      
      b1.addActionListener(this);                                               
      b1.setFocusable(false);                                                   
      gbc.gridx=0;                                                              
      gbc.gridy=1;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                                              
      gbc.insets=new Insets(5,5,5,0);                                           
      gbl.setConstraints(b1,gbc);                                               
      c.add(b1);
      
      b2=new JButton("2");                                                      
      b2.addActionListener(this);                                               
      b2.setFocusable(false);                                                   
      gbc.gridx=1;                                                              
      gbc.gridy=1;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                                                
      gbl.setConstraints(b2,gbc);                                               
      c.add(b2);  

      b3=new JButton("3");                                                      
      b3.addActionListener(this);                                               
      b3.setFocusable(false);                                                   
      gbc.gridx=2;                                                              
      gbc.gridy=1;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                         
      gbl.setConstraints(b3,gbc);                                               
      c.add(b3);  

      b4=new JButton("4");                                                      
      b4.addActionListener(this);                                               
      b4.setFocusable(false);                                                   
      gbc.gridx=0;                                                              
      gbc.gridy=2;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(b4,gbc);                                               
      c.add(b4);
      
      b5=new JButton("5");                                                      
      b5.addActionListener(this);                                               
      b5.setFocusable(false);                                                   
      gbc.gridx=1;                                                              
      gbc.gridy=2;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(b5,gbc);                                               
      c.add(b5); 

      b6=new JButton("6");                                                      
      b6.addActionListener(this);                                               
      b6.setFocusable(false);                                                   
      gbc.gridx=2;                                                              
      gbc.gridy=2;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(b6,gbc);                                               
      c.add(b6); 

      b7=new JButton("7");                                                      
      b7.addActionListener(this);                                               
      b7.setFocusable(false);                                                   
      gbc.gridx=0;                                                              
      gbc.gridy=3;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(b7,gbc);                                               
      c.add(b7);
      
      b8=new JButton("8");                                                      
      b8.addActionListener(this);                                               
      b8.setFocusable(false);                                                   
      gbc.gridx=1;                                                              
      gbc.gridy=3;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(b8,gbc);                                               
      c.add(b8); 

      b9=new JButton("9");                                                      
      b9.addActionListener(this);                                               
      b9.setFocusable(false);                                                   
      gbc.gridx=2;                                                              
      gbc.gridy=3;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(b9,gbc);                                               
      c.add(b9);
      
      b0=new JButton("0");                                                      
      b0.addActionListener(this);                                               
      b0.setFocusable(false);                                                   
      gbc.gridx=0;                                                              
      gbc.gridy=4;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(b0,gbc);                                               
      c.add(b0);
      
      //===============kropka=====================
      bdot=new JButton(".");                                                      
      bdot.addActionListener(this);                                               
      bdot.setFocusable(false);                                                   
      gbc.gridx=1;                                                              
      gbc.gridy=4;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bdot,gbc);                                               
      c.add(bdot);

        //======================clear line====================
      bcl=new JButton("cl");                                                      
      bcl.addActionListener(this);                                               
      bcl.setFocusable(false);                                                   
      gbc.gridx=2;                                                              
      gbc.gridy=4;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bcl,gbc);                                               
      c.add(bcl);

        //==================dzialania matematyczne===================
      bplus=new JButton("+");                                                      
      bplus.addActionListener(this);                                               
      bplus.setFocusable(false);                                                   
      gbc.gridx=3;                                                              
      gbc.gridy=1;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bplus,gbc);                                               
      c.add(bplus);

      bminus=new JButton("-");                                                      
      bminus.addActionListener(this);                                               
      bminus.setFocusable(false);                                                   
      gbc.gridx=3;                                                              
      gbc.gridy=2;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bminus,gbc);                                               
      c.add(bminus);

      bmultiply=new JButton("\u2715");                                                      
      bmultiply.addActionListener(this);                                               
      bmultiply.setFocusable(false);                                                   
      gbc.gridx=3;                                                              
      gbc.gridy=3;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bmultiply,gbc);                                               
      c.add(bmultiply);

      bdivide=new JButton("/");                                                      
      bdivide.addActionListener(this);                                               
      bdivide.setFocusable(false);                                                   
      gbc.gridx=4;                                                              
      gbc.gridy=1;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bdivide,gbc);                                               
      c.add(bdivide);

      bsqrt=new JButton("\u221A");                                                      
      bsqrt.addActionListener(this);                                               
      bsqrt.setFocusable(false);                                                   
      gbc.gridx=4;                                                              
      gbc.gridy=2;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bsqrt,gbc);                                               
      c.add(bsqrt);

      bpow=new JButton("x^");                                                      
      bpow.addActionListener(this);                                               
      bpow.setFocusable(false);                                                   
      gbc.gridx=4;                                                              
      gbc.gridy=3;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bpow,gbc);                                               
      c.add(bpow);

      bproc=new JButton("%");                                                      
      bproc.addActionListener(this);                                               
      bproc.setFocusable(false);                                                   
      gbc.gridx=3;                                                              
      gbc.gridy=4;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bproc,gbc);                                               
      c.add(bproc);

      brow=new JButton("=");                                                      
      brow.addActionListener(this);                                               
      brow.setFocusable(false);                                                   
      gbc.gridx=3;                                                              
      gbc.gridy=4;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bproc,gbc);                                               
      c.add(brow);

      //===================memory==================

      bmemory=new JButton("m");                                                      
      bmemory.addActionListener(this);                                               
      bmemory.setFocusable(false);                                                   
      gbc.gridx=4;                                                              
      gbc.gridy=4;                                                              
      gbc.gridwidth=1;                                                          
      gbc.ipadx=0;                                                              
      gbc.ipady=0;                                          
      gbl.setConstraints(bmemory,gbc);                                               
      c.add(bmemory);


 
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