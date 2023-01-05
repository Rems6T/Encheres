package fr.eni.ENI_Encheres.bo;

import java.time.LocalDateTime;

public class Encheres {
	   private int noUtilisateur;
	   private int noArticle;
	   private LocalDateTime dateEnchere;
	   private int montantEnchere;
		private boolean remporte = false;

	   public Encheres(int noUtilisateur, int noArticle, LocalDateTime dateEnchere, int montantEnchere) {
	      this.noUtilisateur = noUtilisateur;
	      this.noArticle = noArticle;
	      this.dateEnchere = dateEnchere;
	      this.montantEnchere = montantEnchere;
	      this.setRemporte(false);
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

	   public LocalDateTime getDateEnchere() {
	      return dateEnchere;
	   }

	   public void setDateEnchere(LocalDateTime localDate) {
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

	public boolean isRemporte() {
		return remporte;
	}

	public void setRemporte(boolean remporte) {
		this.remporte = remporte;
	}
	   
	}
