package fenetre;

import java.util.Date;

public class Graph {
private  Date dt ;
private double solde;
private int id;

public int getId() {
	return id;
}
public Date getDt() {
	return dt;
}
public double getSolde() {
	return solde;
}


public void setDt(Date dt) {
	this.dt = dt;
}
public void setSolde(double solde) {
	this.solde = solde;
}
public void setId(int id) {
	this.id = id;
}

}
