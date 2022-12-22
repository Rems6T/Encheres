package fr.eni.ENI_Encheres.bo;

import java.sql.Date;

public class Encheres {
	   private int noUtilisateur;
	   private int noArticle;
	   private Date dateEnchere;
	   private int montantEnchere;

	   public Encheres(int noUtilisateur, int noArticle, Date dateEnchere, int montantEnchere) {
	      this.noUtilisateur = noUtilisateur;
	      this.noArticle = noArticle;
	      this.dateEnchere = dateEnchere;
	      this.montantEnchere = montantEnchere;
	   }

	   public int getNoUtilisateur() {
	      return noUtilisateur;
	   }

	   public void setNoUtilisateur(int noUtilisateur) {
	      this.noUtilisateur = noUtilisateur;
	   }

	   public int getNoArticle() {
	      return noArticle;
	   }

	   public void setNoArticle(int noArticle) {
	      this.noArticle = noArticle;
	   }

	   public Date getDateEnchere() {
	      return dateEnchere;
	   }

	   public void setDateEnchere(Date localDate) {
	      this.dateEnchere = localDate;
	   }

	   public int getMontantEnchere() {
	      return montantEnchere;
	   }

	   public void setMontantEnchere(int montantEnchere) {
	      this.montantEnchere = montantEnchere;
	   }

	@Override
	public String toString() {
		return "Encheres [noUtilisateur=" + noUtilisateur + ", noArticle=" + noArticle + ", dateEnchere=" + dateEnchere
				+ ", montantEnchere=" + montantEnchere + "]";
	}
	   
	}
