package fenetre;


import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JPanel;



@SuppressWarnings("serial")
public class pane extends JPanel{
	ArrayList<Graph> grph=new ArrayList<Graph>() ;
	
	double max ; 
	Date dmin;
	Date dmax;
	
	
	public pane(ArrayList<Graph> grph){
		this.grph=grph;
	
	}
	@SuppressWarnings("null")
	public void paintComponent (Graphics g){

	    super.paintComponent(g);
	    
	    
	    max=0;
		dmin =null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dmin = formatter.parse("31/12/2017");
			dmax=formatter.parse("1/1/2017");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("date max :"+dmax+"\t   date min"+dmin+"      solde :"+max);
		
		for(Graph gr: grph){
			if (dmin.getTime()>gr.getDt().getTime()){
				dmin=gr.getDt();
			}
			if(dmin.getTime()<gr.getDt().getTime()){
				dmax=gr.getDt();
			}
			if(max<gr.getSolde()){
				max=gr.getSolde();
			}
			System.out.println("solde :"+gr.getSolde()+"  date:"+gr.getDt());
		}
		System.out.println("date max :"+dmax+"\t   date min"+dmin+"      solde :"+max);
		double a=0;double b=0;
		double x,y; 
		int id1,id2;
		int[] gre = new int[grph.size()] ;
		int i=0;
		Color[]  col = new Color[3];
		col[0]=Color.BLUE;
		col[1]=Color.red;
		col[2]=Color.green;
		for( Graph  gra : grph){
			gre[i]=gra.getId();
			
			i++;
		}a=0; b=0;
		i=0;int j=0 ;
		id1=grph.get(0).getId();
		id2=grph.get(grph.size()-1).getId();
		if(id1!=id2 ){
			
			for(Graph  gra : grph){
				
				if(i<17)
					if(id1==gre[i+1]){
						
						g.setColor(col[j%3]);
						 y=600*gra.getSolde()/max ;
						 x=900*(gra.getDt().getTime()-dmin.getTime())/(dmax.getTime()-dmin.getTime());
						 System.out.println("x="+x+"  y="+y);
						 g.drawLine(50+(int)a ,650-(int)b ,50+(int)x ,650-(int)y );
						 a=x ; b= y ;
						 id1=gra.getId();
					 }else{ 
						 y=600*gra.getSolde()/max ;
						 x=900*(gra.getDt().getTime()-dmin.getTime())/(dmax.getTime()-dmin.getTime());
						 System.out.println("x="+x+"  y="+y);
						 g.drawLine(50+(int)a ,650-(int)b ,50+(int)x ,650-(int)y );
						 a=0 ; b= 0 ;
						 j++;
						 id1=gra.getId();
						 }
				i++;
			}		
			
		}else{
			for (Graph  gr : grph){
			  a=0; b=0;
			  g.setColor(col[j%3]);
				 y=600*gr.getSolde()/max ;
				 x=900*(gr.getDt().getTime()-dmin.getTime())/(dmax.getTime()-dmin.getTime());
				 System.out.println("x="+x+"  y="+y);
				 g.drawLine(50+(int)a ,650-(int)b ,50+(int)x ,650-(int)y );
				 a=x ; b= y ;
			}
			
		}
		
		
		g.drawLine(50, 50, 50, 650);
		g.drawLine(50, 650,950, 650);
		
	}
}