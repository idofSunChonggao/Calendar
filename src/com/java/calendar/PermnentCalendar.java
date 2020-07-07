package com.java.calendar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;



import java.util.Calendar;
import java.util.GregorianCalendar;


public class PermnentCalendar extends JFrame {
	/**
	 * 
	 */
	private Calendar now = Calendar.getInstance();
	private JTextField year = new JTextField(String.valueOf(now.get(Calendar.YEAR)),4);
	private JTextField month = new JTextField(String.valueOf(now.get(Calendar.MONTH)+1),2);
	private JButton addyear = new JButton(" >>");
	private JButton subyear = new JButton("<< ");
	private JButton addmonth = new JButton(" > ");
	private JButton submonth = new JButton(" < ");
	private JButton weekday[] = new JButton[7];
	private JLabel day[] = new JLabel[42];
	private JLabel today = new JLabel();
	private static final long serialVersionUID = 1L;
	
	public PermnentCalendar() 
	 {
		super("万年历   By scg");
		Container pane =getContentPane();
		pane.setLayout(new BorderLayout());
		JPanel top = new JPanel();
		JPanel center = new JPanel();
		JPanel foot = new JPanel();
		top.setOpaque(false);//透明
		center.setOpaque(false);
		foot.setOpaque(false);
		pane.add(top,BorderLayout.NORTH);
		
		subyear.setBorder(BorderFactory.createRaisedBevelBorder()); //button凸起来的效果
		addyear.setBorder(BorderFactory.createRaisedBevelBorder()); 
		submonth.setBorder(BorderFactory.createRaisedBevelBorder()); 
		addmonth.setBorder(BorderFactory.createRaisedBevelBorder()); 
		top.add(subyear);
		top.add(year);
		top.add(addyear);
		top.add(new JLabel("年"));
		top.add(submonth);
		top.add(month);
		top.add(addmonth);
		top.add(new JLabel("月"));
		pane.add(center,BorderLayout.CENTER);
		center.setLayout(new GridLayout(7, 7));
		String wd[] = { "日", "一", "二", "三", "四", "五", "六" };
		for (int i=0;i<7;i++) 
		{
			weekday[i] = new JButton(wd[i]);
			weekday[i].setContentAreaFilled(false);
			weekday[i].setFont(new Font("宋体", 1 , 16));
			weekday[i].setForeground(Color.WHITE);	
			center.add(weekday[i]);
	    }
		for (int i = 0; i < 42; i++) 
		{      
			   day[i] = new JLabel("", JLabel.CENTER);
			   center.add(day[i]);
		}
		Create(now.get(Calendar.YEAR),now.get(Calendar.MONTH)+1);
		addyear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				year.setText(String.valueOf(Integer.parseInt(year.getText())+1));
				Create(Integer.parseInt(year.getText()),Integer.parseInt(month.getText()));
				
			}
		});
		subyear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				year.setText(String.valueOf(Integer.parseInt(year.getText())-1));
				Create(Integer.parseInt(year.getText()),Integer.parseInt(month.getText()));
				
			}
		});
		addmonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(month.getText())==12)
					month.setText("1");
				else
					month.setText(String.valueOf(Integer.parseInt(month.getText())+1));
				Create(Integer.parseInt(year.getText()),Integer.parseInt(month.getText()));
				
			}
		});
		submonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Integer.parseInt(month.getText())==1)
					month.setText("12");
				else
					month.setText(String.valueOf(Integer.parseInt(month.getText())-1));
				Create(Integer.parseInt(year.getText()),Integer.parseInt(month.getText()));
				
			}
			
		});
		pane.add(foot,BorderLayout.SOUTH);
		foot.add(today);
		today.setFont(new Font("隶书", Font.PLAIN , 16));
		today.setText("今天是 公历"+String.valueOf(now.get(Calendar.YEAR))+"年"
						+String.valueOf(now.get(Calendar.MONTH)+1)+"月"
						+String.valueOf(now.get(Calendar.DATE))+"日"
						+"  星期"+String.valueOf(now.get(Calendar.DAY_OF_WEEK)-1));
		
		 
	 }
	
	public void Create(int y,int m)
	{
		int j=0;
		GregorianCalendar date = new GregorianCalendar(y,m-1,1);//公历类，一月Calendar.Month=0
		date.set(Calendar.DAY_OF_MONTH,1);
			  
		for (int i=Calendar.SUNDAY; i<date.get(Calendar.DAY_OF_WEEK); i++) //Calendar.SUNDAY=1
			day[j++].setText("");
		while (date.get(Calendar.MONTH)==m-1)
		{
			if(date.get(Calendar.YEAR)==now.get(Calendar.YEAR)&&date.get(Calendar.MONTH)==now.get(Calendar.MONTH)&&date.get(Calendar.DAY_OF_MONTH)==now.get(Calendar.DATE))
			{
				day[j].setForeground(Color.MAGENTA);					  
			}
			else if(date.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY)
			{
				day[j].setForeground(Color.RED);
			}
			else
				day[j].setForeground(Color.BLACK);
				day[j].setFont(new Font("宋体", Font.BOLD , 16));
				day[j++].setText(String.valueOf(date.get(Calendar.DAY_OF_MONTH)));
				date.add(Calendar.DAY_OF_MONTH, 1);
		}
		while(j<42)
			day[j++].setText("");
			  
}
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PermnentCalendar app = new PermnentCalendar();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setBounds(400,100,500,400);
		ImageIcon bg = new ImageIcon("bg.jpg"); 
		JLabel label = new JLabel(bg); 
		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
		app.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
		JPanel jp=(JPanel)app.getContentPane(); 
		jp.setOpaque(false);
		app.setVisible(true);
	}
}
